package com.ocr;

/**
 * <h1><code>BusinessCardParser</code></h1><p>
 * <b>Description:</b> Interface specification for parsing business card information</p>
 * @author  Lyzander Marantal
 * @version 1.0
 * @since   2019-04-26
*/

/**
 * 
 * @return ContactInfo
 */
public interface BusinessCardParser {
	ContactInfo getContactInfo(String document);
}
