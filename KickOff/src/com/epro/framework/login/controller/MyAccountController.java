/**
\ * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.login.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Users;
import com.epro.framework.user.service.UserService;
import com.epro.framework.util.ApplicationConstants;

/*
 * MyAccountController.java
 * Class description goes here.
 *
 * @version 1.0 30-Mar-2012
 * @author saravanau
 */
@Controller
@RequestMapping("/login")
public class MyAccountController {
	
	private Logger log = Logger.getLogger(MyAccountController.class);
	
	@Autowired
	private LoginService loginservice;
	@Autowired
	private MyAccountValidator accountvalidator;
 	@Autowired
	private UserService userService;
 
	/**
	 * This method is used to show the userAccount
	 * @param model
	 * @param session
	 * @param request
	 * @param response
	 * @return myAccount page.
	 */
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public String showMyAccount(ModelMap model, HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		try {
			UserSessionInfo userSessionInfo = null;
			Users user = null;
			
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null){
				userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
				int userId = userSessionInfo.getUserId();
				user = userService.getUserByUserId(userId);
			}
			
			if (user != null) {
				request.setAttribute(ApplicationConstants.USER_SESSION_KEY, user);
				model.addAttribute(ApplicationConstants.USER_SESSION_KEY, user);
				
				log.info("my account form shown");
			}
		} 
		catch (Exception e) {
			log.info("Exception in showing my account form method"+ExceptionUtils.getStackTrace(e));
		}
		return "myAccount";

	}
 
	/**
	 * This method is to update the user account.
	 * @param users
	 * @param result
	 * @param model
	 * @param status
	 * @param session
	 * @param locale
	 * @param request
	 * @return myAccount page.
	 */
	@RequestMapping(value = "/updateMyAccount", method = RequestMethod.POST)
	public String updateMyAccount(@ModelAttribute("LOGGED_IN_USER") Users users, BindingResult result, ModelMap model, SessionStatus status,
			HttpSession session, Locale locale,HttpServletRequest request) {
		try {
			log.info("inside update user");
			UserSessionInfo userSessionInfo = null;
			Users user = null;
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
				int userId = userSessionInfo.getUserId();
				user = userService.getUserByUserId(userId);
			}
			if(user != null && userSessionInfo != null){
 				if (userSessionInfo.getUserId() == users.getUserId()) {
					accountvalidator.validate(users, result);
					if (result.hasErrors()) {
						model.addAttribute(ApplicationConstants.USER_SESSION_KEY, users);
						return "myAccount";
					} else {
						boolean dBRespone = loginservice.updateUser(user, users);
						if (dBRespone) {
							userSessionInfo.setUsername(user.getUserName());
							userSessionInfo.setFirstName(user.getFirstName());
							userSessionInfo.setLastName(user.getLastName());
							userSessionInfo.setEmailId(user.getEmailId());
							session.setAttribute(ApplicationConstants.USER_SESSION_KEY, userSessionInfo);
							model.addAttribute("update", "Your account updated successfully");
							model.addAttribute("LOGGED_IN_USER", users);
							return "myAccount";
							
						}
					}
				}
			}
		} 
		catch (Exception e) {
			model.addAttribute("errorResponse","Failed to update account.");
			log.error("Failed to update User:" + ExceptionUtils.getStackTrace(e));
		}
		return "myAccount";
	}

}