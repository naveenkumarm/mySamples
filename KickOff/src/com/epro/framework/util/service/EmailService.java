/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */
package com.epro.framework.util.service;

import com.epro.framework.util.EmailInfo;

/*
 * EmailService 
 * Interface EmailService.
 *
 * @version 1.0 June 15, 2012
 * @author antonyj
 */

public interface EmailService {
	 
	public boolean sendMail(EmailInfo emailInfo);
}

