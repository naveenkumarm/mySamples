/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/*
 * HelperClass.java
 * Class description goes here.
 *
 * @version 1.0 Mar 29, 2012
 * @author raajeswarir
 */

public class HelperClass {
	
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String STRING_PATTERN = "^[a-zA-Z\\s]+$";
	public static final String NUMBER_PATTERN ="^[0-9]*$";
	public static final String PHONE_PATTERN ="^[0-9-]*$";
	public static final String ALPHA_NUMERIC_PASSSWORD_REGEX = "^[a-zA-Z0-9_]*$";
	public static final String PLUGINNAME_REGEX = "^[a-zA-Z0-9_.]*$";
	public static final String ATLEAST_ONEALPHA_NUMERIC_PASSSWORD_REGEX = "^(?=.{8,})(?=.*[a-zA-Z])(?=.*[0-9]).*$";
	
	private static Logger log = Logger.getLogger(HelperClass.class);
	
	public static final SimpleDateFormat DATEFORMAT_MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyy");
	public static final DateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ms");
	public static final DateFormat DATE_FORMAT_SQL_WITHOUT_MS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final DateFormat DATE_FORMAT_MM_DD_YYY_HH_MM_FORMAT = new SimpleDateFormat("MM-dd-yyyy hh:mm");
	public static final DateFormat DATE_FORMAT_MM_DD_YYY_HH_MM_24_HOURS_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	public static final DateFormat DATE_FORMAT_FOR_REPORT= new SimpleDateFormat("MM-dd-yyyy HH:mm");
	public static final SimpleDateFormat TIME = new SimpleDateFormat("HH:mm");
	public static final DateFormat MONTHFORMAT = new SimpleDateFormat("MMMM - yyyy");
	public static final	DateFormat YYMMDDFORMAT = new SimpleDateFormat("yyyy/MM/dd"); 	
	public static final String SUCCESS_MESSAGE_START_TAG = "<span class='successMessageStyle'> <img src='../images/tickIcon.png' class='successResponseImageStyle' height='21px' width='21px'>";
	public static final String MESSAGE_END_TAG = "</span>";
	public static final String ERROR_MESSAGE_START_TAG = "<span class='errorMessageStyle'><img src='../images/crossIcon.png' class='errorResponseImageStyle' height='21px' width='21px' >";
	

	/**
	 * Method which is used to validate string with the given regular regexPattern
	 * @param string String that is to be validated.
	 * @param regexPattern Regular expression pattern used to validate string.
	 * @return True if string is valid else returns false.
	 */
	public static synchronized boolean validateStringRegex(String string, String regexPattern) {
		try {
			Pattern pattern = Pattern.compile(regexPattern);
			Matcher m = pattern.matcher(string.trim());
			return m.matches();
		} catch (Exception e) {
			log.error("Failed to validate string regex:" + ExceptionUtils.getStackTrace(e));
			return false;
		}
	}
	
	
	public static String formatDate_MMDDYYYY(Date date){
		return DATEFORMAT_MM_DD_YYYY.format(date);
	}
	
	public static String formatDate_MMDDYYYYHHMM(Date date){
		return DATE_FORMAT_MM_DD_YYY_HH_MM_24_HOURS_FORMAT.format(date);
	}
}

