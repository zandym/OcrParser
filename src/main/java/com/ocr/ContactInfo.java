package com.ocr;

/**
 * <h1><code>ContactInfo</code></h1><p>
 * <b>Description:</b> Interface specification for business card contact information</p>
 * @author  Lyzander Marantal
 * @version 1.0
 * @since   2019-04-26
*/

public interface ContactInfo {

	/**
	 * 
	 * @return Full name of the individual (eg. John Smith, Susan Malick)
	 */
	String getName();
		
	/**
	 * 
	 * @return Phone number formatted as a sequence of digits
	 */
	String getPhoneNumber(); 

	/**
	 * 
	 * @return Email address
	 */
    String getEmailAddress();
    	
}
