package com.epro.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epro.patient.beans.User;
import com.epro.patient.service.AuthenticationService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;
	
	/**
     * Returns the login View 
     * @param model
     */
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		System.out.println("hit login controller");
		return "login";
	}
	
	/**
     * Returns the login View 
     * @param model
     */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String validateUser(ModelMap model, @ModelAttribute("user")User user) {
		System.out.println("hit login validate");
		User userObj = authenticationService.authenticate(user);
		if(userObj != null)
		{
			return "redirect:/patient/view";	
		}
		return "login";
	}
}
