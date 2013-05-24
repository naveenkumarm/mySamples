/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.login.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.model.Users;
import com.epro.framework.util.HelperClass;

/*
 * MyAccountValidator.java
 * Class description goes here.
 *
 * @version 1.0 03-Apr-2012
 * @author saravanau
 */

@Component("MyAccountRoleMappingFormValidator")
public class MyAccountValidator implements Validator{
 	
	@Autowired
	private LoginService loginService;
 
	private Logger log = Logger.getLogger(MyAccountValidator.class);
 
    /**
     * This method returns true if the user already registered or Not.
     * @param model
     * @param users
     * @return
     */
    public boolean validateUser(ModelMap model, Users users) {
		try {
			boolean isNameExist = loginService.isUserExists(users);
			boolean isEmailExist = loginService.isEmailExists(users);

			if (isNameExist) {
				log.info("Userid:"+users.getUserId()+users.getFirstName()+":User Name already exist");
			    model.addAttribute("SameUserNameExist", "User Name already exist");
			   
			}
			if (isEmailExist) {
				log.info("Userid:"+users.getUserId()+users.getEmailId()+":Email Name already exist");
			    model.addAttribute("SameEmailExist", "Email already registered");
			    
			}
			if (isNameExist || isEmailExist) {
			    return true;
			}
		} 
		catch (Exception e) {
			log.error("Failed to validate User:"+e.getMessage());
		}
		return false;
    }

	@Override
	public boolean supports(Class<?> arg0) {
 		return false;
	}
 
	/* 
	 * This method is to validate the "myaccount" module.
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object model, Errors errors) {
		try {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId","myaccount.email.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","myaccount.firstname.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","myaccount.lastname.required");
			Users user = (Users) model;
			boolean isValidMail = EmailValidator.getInstance().isValid(user.getEmailId());
			log.info("validMail:::>>>" + isValidMail);
			
			if (!isValidMail) {
				errors.rejectValue("emailId", "myaccount.email.invalid");
			}

			if (user.getFirstName() != "") {
				if (!HelperClass.validateStringRegex(user.getFirstName(),"^[a-zA-Z\\s]+$")) {
				errors.rejectValue("firstName","myaccount.firstname.invalid");
				}
			}
			if (user.getLastName() != "") {
				if (!HelperClass.validateStringRegex(user.getLastName(),"^[a-zA-Z\\s]+$")) {
				errors.rejectValue("lastName", "myaccount.lastname.invalid");
				}
			}
			
			if((user.getPassword() != null && !user.getPassword().equals(""))){
 				 if(user.getPassword().length() < 8){
					errors.rejectValue("password","user.password.charlength");
				}else if(!HelperClass.validateStringRegex(user.getPassword(),HelperClass.ATLEAST_ONEALPHA_NUMERIC_PASSSWORD_REGEX)){
					errors.rejectValue("password","user.password.atleastone.alphanumeric");
				}
			}

			
		} catch (Exception e) {
			log.error("Failed to validate User:" + e.getMessage());
		}
	}
}
