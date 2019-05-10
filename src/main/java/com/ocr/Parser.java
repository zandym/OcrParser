package com.ocr;

import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1><code>Business card information parser</code></h1><p>
 * @author  Zandy Marantal
 * @version 1.0
 * @since   2019-04-26
*/
public class Parser implements BusinessCardParser {
	
	// Class variables used by the inner class to obtain the output
	private String contactName = null;
	private String contactPhoneNumber = null;
	private String contactEmailAddress = null;
	
	// Message to be displayed if info not found
	static private String MSG_NOT_FOUND = "Not Found";
	
	/**
	 * Helper function used to validate if a string contains a valid phone number
	 * 
	 * @param str
	 * @return true if string contains a valid phone number, otherwise it returns false
	 */
	private boolean isValidPhoneNumber(String str) {
		String phone_pattern = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
		// Remove any nun-numeric character
	    if (str.replaceAll("[^\\d]", "").matches(phone_pattern)) {     
	        return true;
	    } else {     
	        return false;
		}
	}

	/**
	 * Helper function used to validate if a string contains a valid email address
	 * 
	 * @param str
	 * @return true if string contains a valid email, otherwise it returns false
	 */
	private boolean isValidEmailAddress(String str) {
		String email_pattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	    if (str.matches(email_pattern)) {     
	        return true;
	    } else {     
	        return false;
		}
	}

	/**
	 * Parsing Logic:
	 *   PhoneNumber  - Get the first occurrence of a string that has at least 10 digits	
	 *   EmailAddress - Find the last entry having the '@' sign.  
	 *   Name         - Name can be found at any line so use the email address to try to find it
	 * 
	 * @see ocr.BusinessCardParser#getContactInfo(String document)
	 */
	public ContactInfo getContactInfo(String document) {
		// Convert string input to a List for easier processing
		List<String> v_card = new ArrayList<String>();
		v_card.addAll(Arrays.asList(document.split("\n")));
		
		// Loop through list to get relevant fields 
		ListIterator<String> vcard_iterator = v_card.listIterator();
		String line_entry;
		while(vcard_iterator.hasNext()) {
			line_entry =  vcard_iterator.next().trim();

			// Get contactPhoneNumber and contactEmailAddress
			if ( (this.contactPhoneNumber == null) &&  isValidPhoneNumber(line_entry) ) {
				// Strip all non-numeric characters before storing telephone number
				this.contactPhoneNumber = line_entry.replaceAll("[^\\d]", "");
			} 

			if ( (this.contactEmailAddress == null) && isValidEmailAddress(line_entry) ) {
				this.contactEmailAddress = line_entry;
			}	
		}	// end while loop
		
		// Get contact name ONLY if the email address has been found as we need to do some 
		// pattern matching to identify which line has the name field
		if ((this.contactName == null) && (this.contactEmailAddress != null)) {
			// Get the user part of the email address
			int at_index = this.contactEmailAddress.indexOf('@');
			String email_user = this.contactEmailAddress.substring(0, at_index).trim();
			
			// Try to further truncate the user name if it has a '.' present
			// Can be further expander to look for '_' as well
			int dot_index = email_user.indexOf('.');
			// Reset to zero if not found
			if (dot_index == -1) { 
				dot_index = 0; 
			} else {
				dot_index++ ;
			}
			String trim_email_user = email_user.substring(dot_index, email_user.length());
			
			// Array 2nd Pass: Perform pattern matching to get the Name once the email address has been identified
			int min_len = trim_email_user.length();
			for(String line: v_card) {
				// Skip line if contains the email address bec we don't want to compare it to itself
				if (line.equals(this.contactEmailAddress)) {
					continue;
				}
				
				// Further trim the string to be compared since the truncated portion of the name might contain leading spaces
				String vline = line.substring(line.length() - min_len).trim();
				if ( trim_email_user.length() <= vline.trim().length() ) {
					min_len = trim_email_user.length();
				} else {
					min_len = vline.trim().length();					
				}

				// Compare the partial strings
				if (vline.substring(vline.length()-min_len).equalsIgnoreCase(trim_email_user.substring(trim_email_user.length()-min_len))) {
					this.contactName = line;
					break;
				}
			}	// end for loop
		}

		// If contactName is not found, display not found information
		if  (this.contactName == null) {
			this.contactName = Parser.MSG_NOT_FOUND;
		}
		// If contactPhoneNumber is not found, display not found information
		if  (this.contactPhoneNumber == null) {
			this.contactPhoneNumber = Parser.MSG_NOT_FOUND;
		}
		// If contactEmailAddress is not found, display not found information
		if  (this.contactEmailAddress == null) {
			this.contactEmailAddress = Parser.MSG_NOT_FOUND;
		}
		
		return new OcrContactInfo();
	}

	////////////////////////////////////////////////////////////////////////////	
	// Inner class representing ContactInfo
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <h1><code>OcrContactInfo</code></h1><p>
	 * <b>Description:</b> Class implementation of ContactInfo</p>
	 * @author  Zandy Marantal
	 * @version 1.0
	 * @since   2019-04-26
	*/
	class OcrContactInfo implements ContactInfo {

		/* 
		 * @see ocr.ContactInfo#getName()
		 */
		public String getName() {
			return contactName;
		}

		/* 
		 * @see ocr.ContactInfo#getPhoneNumber()
		 */
		public String getPhoneNumber() {
			return contactPhoneNumber;
		}

		/*
		 * @see ocr.ContactInfo#getEmailAddress()
		 */
		public String getEmailAddress() {
			return contactEmailAddress;
		}
	}

	/**
	   * Application main method which makes use of the Parser class.
	   * The application accepts multi-line input from the command line
	   * and stops accepting input once a blank line is encountered.
	   * It then parses the information given and displays the Name, Phone, and Email address as output.
	   */
	public static void main(String[] args) {
	
		System.out.println("Please enter Contact Info to parse and then hit the Enter key:\n");
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(System.in);
		try {
			String line = null;
			while(!(line = scanner.nextLine()).isEmpty()) {
				sb.append(line);
				sb.append("\n");
			}
		} finally {
			scanner.close();			
		}

		// Initialize parser
		Parser parser = new Parser();

		// Get contact information
		ContactInfo contact_info = parser.getContactInfo(sb.toString());
		
		// Display output
		System.out.println("Name: "  + contact_info.getName());		
		System.out.println("Email: " + contact_info.getEmailAddress());
		System.out.println("Phone: " + contact_info.getPhoneNumber());
	}
}
