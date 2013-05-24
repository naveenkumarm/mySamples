package com.epro.framework.user.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.user.model.UserListInfo;
import com.epro.framework.user.service.UserService;
import com.epro.framework.util.ApplicationConstants;

/*
 * UserController.java
 * Class which is used to controls the user creation, update, delete and display users list.
 *
 * @version 1.0 .
 * @author Pradeepkumar.
 */
@Controller
@RequestMapping("/user")
public class UserController{

	private Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

 
	@Autowired
	private UserValidator createUserValidator;

	/**
	 * Method which will be used for populating command and form object
	 * arguments.
	 * 
	 * @param binder
	 *            Binder for data binding from web request parameters to
	 *            JavaBean objects.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dobFormat, true));
		binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dobFormat, true));
		binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dobFormat, true));
	}

	/**
	 * This method used to show the userList.jsp.
	 * 
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String showUsers(ModelMap model, HttpSession session, HttpServletRequest request) {
		try {
			if (request.getParameter("response") != null && request.getParameter("errorresponse") != null) {
				model.addAttribute("response", request.getParameter("response"));
				model.addAttribute("errorresponse", request.getParameter("errorresponse"));
			}

			UserSessionInfo user = null;

			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				user = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}

  		} catch (Exception e) {
			log.error("Exception in showUsers::" + ExceptionUtils.getStackTrace(e));
		}

		return "usersList";
	}

	/**
	 * This method will show create user form.
	 * 
	 * @param model
	 * @param request
	 * @return createUserPage page
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String createUser(ModelMap model, HttpServletRequest request) {
		try {
			Users user = new Users();
			user.setStatus(false);
			 
			UserSessionInfo userSessionInfo = null;
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				userSessionInfo = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}
 
			List<Roles> roleInfoList = userService.getRoleInfo();
			model.addAttribute("roleInfoList", roleInfoList);
			
			model.addAttribute("user", user);
			model.addAttribute("headers", "Create User");
			model.addAttribute("Save", "Save");
		} catch (Exception e) {
			log.error("Exception in createUser::" + ExceptionUtils.getStackTrace(e));
		}
		return "createUserPage";
	}

	 
	/**
	 * This Method used to save the newly created user
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Users user, BindingResult result, ModelMap model, HttpSession session, Locale locale) {

		UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
		List<Roles> roleInfoList = null;
		try {
 			createUserValidator.userValidate(user, result, userSessionInfo);
 			
 			if(!result.hasErrors()){
 				//Save
 				int UserId = userService.saveUser(user);
 				
 				if (UserId == 0) {
					log.info("User " + user.getUserName() + " save failed.");
 					model.addAttribute("Error", messageSource.getMessage("user.save.failure", new String[] { user.getUserName() }, locale));
					model.addAttribute("user", user);
  					model.addAttribute("roleInfoList", roleInfoList);
					model.addAttribute("headers", "Create User");
					if (user.getUserId() != 0) {
						model.addAttribute("Save", "Modify");
					} else {
						model.addAttribute("Save", "Save");
					}

					return "createUserPage";

				} else {
  					log.info("User " + user.getUserName() + " saved successfully.");
 					model.addAttribute("response", "User \"" + user.getUserName() + "\" saved successfully");
					model.addAttribute("errorresponse", "");
 					model.addAttribute("searchUserName", "");
					model.addAttribute("headers", "Users");
 					return "usersList";
				}

 			} else {
 				
 				roleInfoList = userService.getRoleInfo();
				model.addAttribute("roleInfoList", roleInfoList);
 				model.addAttribute("headers", "Create User");
				if (user.getUserId() != 0) {
					model.addAttribute("Save", "Modify");
				} else {
					model.addAttribute("Save", "Save");
				}
 				return "createUserPage";
 			}
 	 			 
		} catch (Exception e) {
			model.addAttribute("user", user);
  			model.addAttribute("response", "");
			model.addAttribute("errorresponse", "Failed to save user!");
 			model.addAttribute("roleInfoList", roleInfoList);
			model.addAttribute("headers", "Create User");
 			model.addAttribute("Save", "Save");
			log.error("Exception in saveUser::" + ExceptionUtils.getStackTrace(e));
		}
		model.addAttribute("Error", messageSource.getMessage("user.save.failure", new String[] { user.getUserName() }, locale));
		return "createUserPage";
	}

	/**
	 * This method used for getting the information of user
	 * 
	 * @param userId
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public void getUserByUserId(int userId, ModelMap model, HttpServletResponse response, HttpSession session) {
		UserSessionInfo userSession = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
		try {
			SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/yyyy");
			Users user = new Users();
			if (user.getUserId() != userId) {
				user = userService.getUserByUserId(userId);

				if (user != null && user.getUserId() > 0) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("confirmPassword", user.getPassword());
 					jsonObject.put("email", user.getEmailId().toString());
 					jsonObject.put("extn1", ""+user.getExtn1());
					jsonObject.put("extn2", ""+user.getExtn2());
 					jsonObject.put("firstName", user.getFirstName().toString());
 					jsonObject.put("lastName", user.getLastName().toString());
					jsonObject.put("role", user.getRoles().getRoleName());
					jsonObject.put("mobileNumber1", user.getMobileNumber1().toString());
					jsonObject.put("mobileNumber2", ""+user.getMobileNumber2());
					jsonObject.put("password", user.getPassword().toString());
 					jsonObject.put("active", user.getStatus().toString());
					jsonObject.put("userName", user.getUserName().toString());
					jsonObject.put("workingPhone1", user.getWorkingPhone1().toString());
					jsonObject.put("workingPhone2", ""+user.getWorkingPhone2());
					jsonObject.put("department", ""+user.getDepartment());
					jsonObject.put("employeeId", ""+user.getEmployeeId());
  
					response.getWriter().write(jsonObject.toString());
				} else {
					log.error("UserId not found::::" + userId);
				}
			}
		} catch (Exception e) {
			log.error("Exception in getUserByUserId::" + ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * This method used to edit already created user
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editUserPage", method = RequestMethod.GET)
	public String editUser(String userId, ModelMap model, HttpServletResponse response, HttpSession session) {
		 
		try {
			Users user = userService.getUserByUserId(Integer.parseInt(userId));
			List<Roles> roleInfoList = userService.getRoleInfo();
			model.addAttribute("roleInfoList", roleInfoList);
 			model.addAttribute("user", user);
			model.addAttribute("headers", "Modify User");
			model.addAttribute("Save", "Modify");
		} catch (Exception e) {
			log.error("Exception in editUser::" + ExceptionUtils.getStackTrace(e));
		}
		return "createUserPage";
	}

	/**
	 * 
	 * This method used to delete the user
	 * 
	 * @param userId
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteUserInfo", method = RequestMethod.GET)
	public String deleteUser(int userId, HttpServletResponse response, ModelMap model, HttpSession session, HttpServletRequest request) {
		try {
			boolean result = false;
			Users user = userService.getUserByUserId(userId);
			if(user != null){
 				  result = userService.deleteUser(user);
			}

			if (result) {
				model.addAttribute("response", "User \"" + user.getUserName() + "\" deleted successfully");
				model.addAttribute("errorresponse", "");
			} else {
				model.addAttribute("response", "");
				model.addAttribute("errorresponse", "Failed to delete user!");
			}

		} catch (Exception e) {
			log.error("Exception in deleteUser::" + ExceptionUtils.getStackTrace(e));
			model.addAttribute("response", "");
			model.addAttribute("errorresponse", "Failed to delete user!");
		}

		UserSessionInfo user = null;

		if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
			user = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
		}
 		return "usersList";
	}

	/**
	 * This method is to get the roleId and roleName.
	 * 
	 * @param roleLevel
	 * @param tenantId
	 * @param subTenantId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRoleInfo", method = RequestMethod.GET)
	public @ResponseBody
	String getRoleInfo(int roleLevel, int tenantId, int subTenantId, HttpServletResponse response) {
		try {
			ArrayList arrayObj = new ArrayList();
			List<Roles> roleInfoList = userService.getRoleInfo();

			for (int i = 0; i < roleInfoList.size(); i++) {
				Roles roleInfo = roleInfoList.get(i);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("roleId", roleInfo.getRoleId());
				jsonObject.put("roleName", roleInfo.getRoleName());

				arrayObj.add(jsonObject);
			}

			JSONObject myObj = new JSONObject();
			myObj.put("total", roleInfoList.size());
			myObj.put("success", true);
			myObj.put("roleInfo", arrayObj);

			return myObj.toString();

		} catch (Exception e) {
			log.error("Exception in getRoleInfo::" + ExceptionUtils.getStackTrace(e));
		}

		return "";
	}

	/**
	 * This method used to show the userList in Datatable.
	 * 
	 * @param levelIdParam
	 * @param tenantIdParam
	 * @param subTenantIdParam
	 * @param userNameKey
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userListInfo", method = RequestMethod.GET)
	public @ResponseBody
	String getUsersDataTable(String levelIdParam, String tenantIdParam, String subTenantIdParam, String userNameKey, Model model, HttpSession session, HttpServletRequest request) {
		try {
			int startRecord = 0;
			int recordsToShow = 0;

			String sEcho = request.getParameter("sEcho");

			if (request.getParameter("iDisplayStart") != null) {
				startRecord = Integer.parseInt(request.getParameter("iDisplayStart"));
				startRecord = startRecord + 1;
			}

			if (request.getParameter("iDisplayLength") != null) {
				recordsToShow = Integer.parseInt(request.getParameter("iDisplayLength"));
			}

			ArrayList arrayObj = new ArrayList();
			List<UserListInfo> userList = new ArrayList<UserListInfo>();
			JSONObject itemObj = new JSONObject();
			UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
  
			userList = userService.getUsersDataTable(startRecord, recordsToShow, userNameKey);

			int totalRows = 0;
			for (int i = 0; i < userList.size(); i++) {
				UserListInfo userListInfo = userList.get(i);
				totalRows = userListInfo.getTotalRows();
				itemObj = JSONObject.fromObject(userListInfo);
				arrayObj.add(itemObj);
			}
			JSONObject myObj = new JSONObject();
			myObj.put("sEcho", sEcho);
			myObj.put("iTotalRecords", totalRows);
			myObj.put("iTotalDisplayRecords", totalRows);
			myObj.put("aaData", arrayObj);

			// convert the JSON object to string and send the response back
			return myObj.toString();
		} catch (Exception e) {
			log.error("Exception in getUsersDataTable::" + ExceptionUtils.getStackTrace(e));
			return "";
		}
	}
}
