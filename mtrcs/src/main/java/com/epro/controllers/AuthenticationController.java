package com.epro.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epro.adapter.AuthenticationAdapter;
import com.epro.adapter.ProjectAdapter;
import com.epro.beans.CustomerInfo;
import com.epro.beans.LookUp;
import com.epro.beans.Role;
import com.epro.beans.UserInfo;
import com.epro.schema.EmployeeInfo;
import com.google.gson.Gson;

@Controller
public class AuthenticationController {

	
	private static final Logger LOG = Logger.getLogger(AuthenticationController.class);
	Gson gson = new Gson();

	@Autowired
	private AuthenticationAdapter authenticationAdapter;
	@Autowired
	private ProjectAdapter projectAdapter;
	/**
     * Returns the login View 
     * @param model
     */
	@RequestMapping(value = "/login/view", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model, @RequestParam(value = "reason", required = false) String reason
			) {
		LOG.info("Return the Login View Page :");
		if(reason != null){
				if(reason.equals("error")){
					model.put("error", "You have entered an invalid username or password!");
				}else if(reason.equals("logout")){
					model.put("error", "You have successfully logged out ");
				}else if(reason.equals("session")){
					model.put("error", "Session Expired");
				}
		}
		return "loginView";
	}

	/**
	 * This method is to validate the username and password
	 */
	@RequestMapping(value = "/login/validateUser", method = RequestMethod.GET)
	public String validateUser(ModelMap model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean loginSuccess = false;
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserInfo user = new UserInfo();
				if (auth.getName() != null && auth.getName().trim().compareTo("") != 0) {
					// get user object to hold it in session
					LOG.info("Auth get name is >>>>>>>"+auth.getName());
					LOG.info("Auth get cred is >>>>>>>"+auth.getCredentials());
					user = authenticationAdapter.loginRequest(auth.getName(),""+auth.getCredentials());
					LOG.info("user searched successfully");
					//displaying the user name is active and deleted or not.
					if (user != null) {
						loginSuccess = true;	
						session.setAttribute("userInfo",user);
						List<String> rolesList = new ArrayList<String>();
						if(user.getUserRoles() != null && user.getUserRoles().size() > 0){
							for(Role roles:user.getUserRoles()){
								rolesList.add(roles.getRoleName());
							}
						}
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Date date = new Date();
						session.setAttribute("loginId", user.getLoginId());
						session.setAttribute("date", dateFormat.format(date));
						session.setAttribute("sessionInit", true);
						session.setAttribute("roles", rolesList);
						if (session.getAttribute("LOGGED_IN_TIME") == null) {	
							String sessionId = session.getId();
							session.setAttribute("sessionToken", sessionId);
							LOG.info("session id : "+sessionId);
 						}
   						return "redirect:/ems/landing/view";
					}
				}
			
			//If login fails,checking the user is active or not.
			if (!loginSuccess) {
				session.setAttribute("sessionInit", null);
			}
			return "redirect:/ems/login/view?reason=error";
		} 
		catch (Exception e) {
			LOG.error("Error: " + e.getMessage());
		}
 		return "redirect:/ems/login/view?reason=error";
	}

	/**
     * method used to return landing page 
     * @param model
     * @param Request
     */
	@RequestMapping(value = "/landing/view", method = RequestMethod.GET)
	public String loginSuccess(ModelMap model,HttpServletRequest request) {
		LOG.info("Enter into Landing page:");
		HttpSession session = request.getSession();
		model.addAttribute("loginId", session.getAttribute("loginId"));
		model.addAttribute("date", session.getAttribute("date"));
		return "landingView";
	}
	
	/**
	 * This method to denied the user from invalid access.
	 */

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String getDeniedPage() {
		LOG.info("Access Denied.");
		return "accessDenied";
	}
	
	/**
	 * This method to retrive password for given LoginId.
	 */

	@RequestMapping(value = "/login/forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(ModelMap model, @RequestParam("loginId") String loginId) {
		LOG.info("Forget Password method in");
		boolean status = false; 
		try {
			String password = authenticationAdapter.getPassword(loginId);
			LOG.info("Password retrived is :"+password);
			LOG.info("Password Length is :"+password.length());
			if (password.length()==0) {
				status = false;
				
			} else {
				status = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("status", status);
		return "jsonView";
	}

	/**
	 * This method is used to view changepassword
	 */
	@RequestMapping(value = "/login/changePassword", method = RequestMethod.GET)
	public String getChangePassword(ModelMap model,HttpServletRequest request) {
		LOG.info("ChangePassword");
		HttpSession session = request.getSession();
		String loginId=(String)session.getAttribute("loginId");
		String user = authenticationAdapter.getPassword(loginId);
		model.addAttribute("oldpassword", user);
		model.addAttribute("loginId", session.getAttribute("loginId"));
		model.addAttribute("title", "Change Password");
		return "changePassword";
	}

	/**
	 * This method is used to changepassword saved in table
	 */
	@RequestMapping(value = "/login/savePassword", method = RequestMethod.GET)
	public String savePassword(ModelMap model,@RequestParam("loginId") String loginId,@RequestParam("password") String password) {
		LOG.info("save password");
		boolean success = authenticationAdapter.savePassword(loginId,password);
		model.addAttribute("success", success);
		return "jsonView";
	}
		
	/**
	 * This method is used to view create user page
	 */
	
	@RequestMapping(value = "/login/userview")
	public String getCustomerView(ModelMap model,@RequestParam(value = "request", required = true) String type) {
			if(type.equals("get")){
				model.addAttribute("datasource", true);
			}
			List<LookUp> roles = projectAdapter.getLookUpValue("user-role", "");
			model.addAttribute("employeeRoles", gson.toJson(roles));
			model.addAttribute("pageType",type);
			model.addAttribute("title", "User Information");
		return "createUser";
	}
	
	/**
	 * This method is used to create user record saved in table
	 */
	@RequestMapping(value = "/login/saveUser", method = RequestMethod.POST)
	public String saveUser(ModelMap model,@ModelAttribute("userInfo")UserInfo userInfo) {
			LOG.info("login user List success");	
			boolean success = authenticationAdapter.saveUser(userInfo);
			model.addAttribute("success", success);
		return "jsonView";
	}	
	
	/**
	 * This method is used to create user record saved in table
	 */
	@RequestMapping(value = "/login/getUser")
	public String getUserInfo(ModelMap model) {
			LOG.info("login user List");
			List<UserInfo> userList = new ArrayList<UserInfo>();
			userList=authenticationAdapter.getUserDetailsList(null,null);
			model.addAttribute("userList", userList);
			return "jsonView";
	}
	
	/**
	 * This method is used to create user record saved in table
	 */
	@RequestMapping(value = "/login/edit")
	public String editCustomerInfo(ModelMap model,
			@RequestParam(value = "loginId", required = true) String loginId,@RequestParam(value = "roleId") String roleId) {
			LOG.info("edit user");	
			String type="edit";
			List<UserInfo> userList = new ArrayList<UserInfo>();
			List<LookUp> roles = projectAdapter.getLookUpValue("user-role", "");
			userList=authenticationAdapter.getUserDetailsList(loginId,roleId);
			model.addAttribute("pageType",type);
			model.addAttribute("employeeRoles", gson.toJson(roles));
			model.addAttribute("userId", userList.get(0).getUserId());
			model.addAttribute("user", userList.get(0));
			return "jsonView";
		}
	
	/**
	 * This method is used to create user record saved in table
	 */
	@RequestMapping(value = "/login/delete")
	public String deleteUserInfo(ModelMap model,
			@RequestParam(value = "loginId") String loginId,@RequestParam(value = "roleId") String roleId) {
			LOG.info("delete user");
			boolean success = authenticationAdapter.deleteUserInfo(loginId,roleId);
			model.addAttribute("success", success);
			return "jsonView";
	}
	
}
