package com.epro.framework.login.controller;

/*
 * LoginController.java
 * Class description goes here.
 *
 * @version 1.0 26-Mar-2012
 * @author saravanau
 */
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.login.util.UserAccessRightsInfo;
import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.RoleAccessPermission;
import com.epro.framework.model.Users;
import com.epro.framework.security.SecurityHolder;
import com.epro.framework.util.ApplicationConstants;
import com.epro.framework.util.EmailInfo;
import com.epro.framework.util.MenuInfo;
import com.epro.framework.util.SubMenuInfo;
import com.epro.framework.util.service.EmailService;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private EmailService emailService;
	 
 	
  	private Logger log = Logger.getLogger(LoginController.class);
 
	/**
	 * This method is used to display the login form from loginForm.jsp
	 * @param model
	 * @return loginForm Page.
	 */
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {
		try {
			log.info("Login form shown");
			Users users = new Users();
			model.addAttribute("users", users);
		} 
		catch (Exception e) {
			log.error("Exception in showform method"+ExceptionUtils.getStackTrace(e));
		}
		return "loginform";
	}
	
 	/**
	 * This method is used to display forgotPassword.jsp
	 * @param model
	 * @return forget password page.
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String showForgotPassword(ModelMap model) {
		return "forgotpassword";
	}
	
	 
	/**
	 * This method is used to reset Password
	 * @param userName
	 * @param email
	 * @param model
	 * @return forgotpassword page.
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(String userName,String email,Model model) {
		String oldPassword="";
		try {
		Users user = loginservice.validateUser(userName,email);
 		if(user != null){
			oldPassword = user.getPassword();
		 	Random r = new Random();
	        String password = new BigInteger(130, r).toString(32);
	        password = password.substring(0,4) + password.substring(10,15);
	        log.info("Random Password for ["+ userName +"] is: " + password);
	        
			boolean isReset = loginservice.resetPassword(user,password);
			if(isReset){
				EmailInfo emailInfo = new EmailInfo();
				emailInfo.setTemplate(ApplicationConstants.RESET_TEMPLATE);
 				Hashtable<String, String> attributes = new Hashtable<String, String>();
				attributes.put("firstname", user.getFirstName());
				attributes.put("lastname", user.getLastName());
				attributes.put("password",password);
				
				emailInfo.setAttributesMap(attributes);
				emailInfo.setEmailId(user.getEmailId());
				
				emailInfo.setSubject("SNS - Reset Password");
				boolean isMailSent = emailService.sendMail(emailInfo);
 				if(isMailSent){
					model.addAttribute("SUCCESS","Your new password has been sent to your Registered Email");
				}
				else{
 					loginservice.resetOldPassword(user,oldPassword);
 					model.addAttribute("ERROR","Unexpected error occurs while sending email");	
				}
			}
 		}
 		else{
			model.addAttribute("ERROR","Invalid UserName (or) Email Address");
			return "forgotpassword";
		}
		return "forgotpassword";
 		}
		catch(Exception e){
			log.error("Failed to resetPassword:" + ExceptionUtils.getStackTrace(e));
			model.addAttribute("ERROR","Unexpected error occurs while Reset Password");
			return "forgotpassword";
		}
	}
 
	/**
	 *  This method is to validate the username and password
	 * @param user
	 * @param error
	 * @param model
	 * @param session
	 * @param request
	 * @param response
	 * @return loginform page.
	 */
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	public String validateUser(@ModelAttribute("users") Users user, @RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {  
 			boolean loginSuccess = false;
			
			if (!error) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth.getName() != null && auth.getName().trim().compareTo("") != 0) {
					// get user object to hold it in session
					user = loginservice.searchUser(auth.getName()); 
					if(user != null){
					 
						boolean activeuser = user.getStatus();
						boolean deleteduser = user.getIsDeleted();
						if (user != null && activeuser && !deleteduser) {
							log.debug("User is Active:"+activeuser);
							loginSuccess = true;
							
							UserSessionInfo userSessionInfo = new UserSessionInfo();
							userSessionInfo.setUserId(user.getUserId());
							userSessionInfo.setUsername(user.getUserName());
							userSessionInfo.setFirstName(user.getFirstName());
							userSessionInfo.setLastName(user.getLastName());
							userSessionInfo.setEmailId(user.getEmailId());
							userSessionInfo.setMobileNumber(user.getMobileNumber1());
							userSessionInfo.setSessionId(session.getId());
							userSessionInfo.setRoleId(user.getRoles().getRoleId());
							userSessionInfo.setRoleName(user.getRoles().getRoleName());
							userSessionInfo.setClientId(user.getClients().getClientId());
							userSessionInfo.setLogoPath(user.getClients().getLogoPath());
 							log.info("user name :" + userSessionInfo.getUsername());
							log.info("user id :" + userSessionInfo.getUserId());
 							
							if(user.getRoles().getRoleAccessPermissionses() != null && user.getRoles().getStatus()){
								for(RoleAccessPermission roleAccessPermission : user.getRoles().getRoleAccessPermissionses()){
									
									long menuId = roleAccessPermission.getMenuId();
									long subMenuId = roleAccessPermission.getSubMenuId();
 									MenuInfo menuInfo = SecurityHolder.APPLICATION_MENUS.get(menuId);
									SubMenuInfo subMenuInfo = null;
									
									if(subMenuId > 0 && menuInfo != null){
										List<SubMenuInfo> subMenuInfoList = SecurityHolder.APPLICATION_SUBMENUS.get(menuInfo.getMenuName());
										for(int i=0;i<subMenuInfoList.size();i++){ 
											if(subMenuInfoList.get(i).getSubMenuId() == subMenuId){
												subMenuInfo = subMenuInfoList.get(i);
 												break;
											}
										}
									}
									
  									if(subMenuId == 0 && menuInfo != null){
 										if(roleAccessPermission.getIsViewEnabled()){
 											userSessionInfo.getIsMenuEnabled().put(menuInfo.getMenuName(), true);
											}
										else
											userSessionInfo.getIsMenuEnabled().put(menuInfo.getMenuName(), false);
									}
									else{
										if(roleAccessPermission.getIsViewEnabled() ||
												roleAccessPermission.getIsEditEnabled() || roleAccessPermission.getIsDeleteEnabled()
												|| roleAccessPermission.getIsCreateEnabled()){
											
											userSessionInfo.getIsSubMenuEnabled().put(subMenuInfo.getSubMenuName(), true);
											UserAccessRightsInfo userAccessRightsInfo = new UserAccessRightsInfo();
											userAccessRightsInfo.setCreateEnabled(roleAccessPermission.getIsCreateEnabled());
											userAccessRightsInfo.setViewEnabled(roleAccessPermission.getIsViewEnabled());
											userAccessRightsInfo.setEditEnabled(roleAccessPermission.getIsEditEnabled());
											userAccessRightsInfo.setDeleteEnabled(roleAccessPermission.getIsDeleteEnabled());
											userSessionInfo.getAccessRights().put(subMenuInfo.getSubMenuName(), userAccessRightsInfo);
										}
										else
											userSessionInfo.getIsSubMenuEnabled().put(subMenuInfo.getSubMenuName(), false); 
									}
								}
							}else{
								log.info("[ "+user.getUserName()+" ] User Role Is InActive");
								model.addAttribute("users", new Users());
								model.put("error", " Role Is Inactive");
								return "loginform";
							}
							
							session.setAttribute(ApplicationConstants.USER_SESSION_KEY, userSessionInfo);
							
							if (session.getAttribute("LOGGED_IN_TIME") == null) {	
								String sessionId = session.getId();
								session.setAttribute("sessionToken", sessionId);
								session.setAttribute("LOGGED_IN_TIME", DateFormat.getInstance().format(Calendar.getInstance().getTime()));
							}
							// to display the username in loginsuccess.jsp
							model.addAttribute(ApplicationConstants.USER_SESSION_KEY, user); 
							return "myAccount";
							
						}
					}
					
				}
			} 
			else {
				try {
					model.addAttribute("users", new Users());
					model.put("error", "You have entered an invalid username or password!");
					return "loginform";

				} 
				catch (Exception e) {
					log.error("Failed to get Login Page:" + ExceptionUtils.getStackTrace(e));
				}
			}
			//If login fails,checking the user is active or not.
			if (!loginSuccess) {
				if (user != null) {
					if (!user.getStatus()) {
 						model.put("error", "Your account in not activated");
						log.info("[ " + user.getUserName() + " ] User Is InActive");
					}  
				} else {
					model.put("error", "You have entered an invalid username or password");
				}
			}
 			return "loginform";
			
		} 
		catch (Exception e) {
			log.error("Error: " +ExceptionUtils.getStackTrace(e));
		}
 		return "loginform";
	}
 	 
     
}
