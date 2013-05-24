/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.login.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * EmailValidator.java
 * Class description goes here.
 *
 * @version 1.0 30-Mar-2012
 * @author saravanau
 */

public class EmailValidator {

	private Pattern pattern;

	private static EmailValidator ev;

	private EmailValidator() {

	}

	/**
	 * Method which is used to check whether given value is valid or not
	 * 
	 * @param value Value which is to be validated.
	 * @return True if Valid else returns false.
	 */
	public boolean isValid(Object value) {
		if (value == null)
			return true;
		if (!(value instanceof String))
			return false;
		String string = (String) value;
		if (string.length() == 0)
			return true;
		Matcher m = this.pattern.matcher(string);
		return m.matches();
	}

	/**
	 * Method which is used to initialize class variables.
	 */
	private void initialize() {
		this.pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
 	 
	public static synchronized EmailValidator getInstance() {
		if (ev == null) {
			ev = new EmailValidator();
			ev.initialize();
		}
		return ev;
	}

}
