/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.login.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.util.ApplicationConstants;

/*
 * Logoutcontroller.java
 * Class description goes here.
 *
 * @version 1.0 30-Mar-2012
 * @author saravanau
 */
@Controller
@RequestMapping("/login")
public class LogoutController {
	
	private Logger log = Logger.getLogger(LogoutController.class);
 
	/**
	 * This method is used to display the session if expired.
	 * @param model
	 * @param session
	 * @return sessionExpired page.
	 */
	@RequestMapping(value = "/sessionExpired", method = RequestMethod.GET)
	public String showSessionExpire(ModelMap model, HttpSession session) {
		try {
			session.invalidate();
			log.debug("Session has been Expired.");
		}
		catch (Exception e) {
			log.error("Exception in getSessionExpiredPage method"+ExceptionUtils.getStackTrace(e));
		}
		return "sessionExpired";
	}

 
	/**
	 *  This method is to logout the from application.
	 * @param model
	 * @param session
	 * @return loginform page.
	 */
	@RequestMapping(value = "/logout")
	public String logout(ModelMap model,HttpSession session) {
		try {
			model.addAttribute("Logout","You are successfully logged out");
			UserSessionInfo userSessionInfo = null;
			if(session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null){
				userSessionInfo = (UserSessionInfo)session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}
			if(userSessionInfo != null){
				/*loginservice.saveLogoutTime(userSessionInfo.getSessionId());*/
				session.invalidate();
				log.info("Logout time saved.....");
			}
			return "loginform";
		} catch (Exception e) {
			log.error("Exception in logout method"+ExceptionUtils.getStackTrace(e));
			return null;
  		}
	}
  
	/**
	 * This method to denied the user from invalid access.
	 * @return accessDenied page.
	 */
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String showAccessDenied() {
		log.info("Access Denied.");
		return "accessDenied";
	}
}
