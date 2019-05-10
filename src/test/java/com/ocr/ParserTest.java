package com.ocr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author  Zandy Marantal
 * @version 1.0
 * @since   2019-04-26
 *
 */

@DisplayName("Tests for the Parser class")
public class ParserTest {

	private Parser parser;
	private String document;
	private ContactInfo test_ci;
	
    @BeforeEach
    void createAndInitializeParser() {
    	parser = new Parser();
    }	
	
	/**
	 * Test method for {@link ocr.Parser#getContactInfo(java.lang.String)}.
	 */
	@Test
	@DisplayName("Test Case1: Parse Example1")
	void testGetContactInfoExample1() {
		document = "ABC LTD\nMike Smith\nSoftware Engineer\n(410)555-1234\nmsmith@abc.com\n";
		test_ci = parser.getContactInfo(document);
		//System.out.println(test_ci.getName());
		assertTrue(test_ci.getName().startsWith("Mike Smith"), "Failed to parse Name field");
		//System.out.println(test_ci.getPhoneNumber());
		assertTrue(test_ci.getPhoneNumber().startsWith("4105551234"), "Failed to parse Phone Number field");
		//System.out.println(test_ci.getEmailAddress());
		assertTrue(test_ci.getEmailAddress().startsWith("msmith@abc.com"), "Failed to parse Email Address field");
	}

	@Test
	@DisplayName("Test Case2: Parse Example2")
	void testGetContactInfoExample2() {
		document = "Foobar Technologies\nAnalytic Developer\nLisa Haung\n1234 Sentry Road\nColumbia, MD 12345\n"
				   + "Phone: 410-555-1234\nFax: 410-555-4321\nlisa.haung@foobartech.com\n";
		test_ci = parser.getContactInfo(document);
		//System.out.println(test_ci.getName());
		assertTrue(test_ci.getName().startsWith("Lisa Haung"), "Failed to parse Name field");
		//System.out.println(test_ci.getPhoneNumber());
		assertTrue(test_ci.getPhoneNumber().startsWith("4105551234"), "Failed to parse Phone Number field");
		//System.out.println(test_ci.getEmailAddress());
		assertTrue(test_ci.getEmailAddress().startsWith("lisa.haung@foobartech.com"), "Failed to parse Email Address field");
	}
	
	@Test
	@DisplayName("Test Case3: Parse Example3")
	void testGetContactInfoExample3() {
		document = "Arthur Wilson\nSoftware Engineer\nDecision & Security Technologies\nABC Technologies\n"
				   + "123 North 11th Street\nSuite 229\nArlington, VA 22209\nTel: +1 (703) 555-1259\n"
				   + "Fax: +1 (703) 555-1200\nawilson@abctech.com";
		test_ci = parser.getContactInfo(document);
		//System.out.println(test_ci.getName());
		assertTrue(test_ci.getName().startsWith("Arthur Wilson"), "Failed to parse Name field");
		//System.out.println(test_ci.getPhoneNumber());
		assertTrue(test_ci.getPhoneNumber().startsWith("17035551259"), "Failed to parse Phone Number field");
		//System.out.println(test_ci.getEmailAddress());
		assertTrue(test_ci.getEmailAddress().startsWith("awilson@abctech.com"), "Failed to parse Email Address field");
	}
}
