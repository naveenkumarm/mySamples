package com.epro.framework.user.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Users;
import com.epro.framework.user.service.UserService;
import com.epro.framework.util.HelperClass;

@Component("userValidator")
public class UserValidator implements Validator {

	private Logger log = Logger.getLogger(UserValidator.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Users.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {

	}

	public void userValidate(Users user, BindingResult errors, UserSessionInfo userSessionInfo) {
		try {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "createuser.firstName.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "createuser.lastName.required");
 			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "createuser.userName.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "createuser.password.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "createuser.department.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "createuser.employeeId.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingPhone1", "createuser.workingPhone1.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber1", "createuser.mobileNumber1.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "createuser.emailId.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "createuser.status.required");

 			/* Role Validation */
			if (user.getRoles().getRoleId() <= 0) {
				errors.rejectValue("roles.roleId", "user.role.select");
			}
			/* First Name Validation */
			if (user.getFirstName() != null && user.getFirstName().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getFirstName(), messageSource.getMessage("myaccount.firstname.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("firstName", "user.firstname.validation");
				}
			}
			/* Last Name Validation */
			if (user.getLastName() != null && user.getLastName().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getLastName(), messageSource.getMessage("myaccount.lastname.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("lastName", "user.lastname.validation");
				}
			}

			/* User Name */
			if (user.getUserName() != null && user.getUserName().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getUserName(), messageSource.getMessage("user.username.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("userName", "user.username.validation");
				} else {
					if (userService.isUserNameExist(user)) {
						log.info("User Name Exist");
						errors.rejectValue("userName", "user.username.exist");
					}
				}
			}

			/* password and Confirmation */
			if ((user.getPassword() != null && !user.getPassword().equals(""))) {

				if (user.getPassword().length() < 8) {
					errors.rejectValue("password", "user.password.charlength");
				} else if (!HelperClass.validateStringRegex(user.getPassword(), HelperClass.ATLEAST_ONEALPHA_NUMERIC_PASSSWORD_REGEX)) {
					errors.rejectValue("password", "user.password.atleastone.alphanumeric");
				}
			}

			/* Work1 Validation */
			if (user.getWorkingPhone1() != null && user.getWorkingPhone1().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getWorkingPhone1(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("workingPhone1", "user.phonenumber1.invalid");
				}
			}
			/* Work2 Validation */
			if (user.getWorkingPhone2() != null && user.getWorkingPhone2().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getWorkingPhone2(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("workingPhone2", "user.phonenumber2.invalid");
				}
			}
			/* Extn1 Validation */
			if (user.getExtn1() != null && user.getExtn1().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getExtn1(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("extn1", "user.extn1.invalid");
				}
			}

			/* Extn2 Validation */
			if (user.getExtn2() != null && user.getExtn2().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getExtn2(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("extn2", "user.extn2.invalid");
				}
			}

			/* Mobile Number1 Validation */
			if (user.getMobileNumber1() != null && user.getMobileNumber1().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getMobileNumber1(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("mobileNumber1", "user.mobilenumber1.invalid");
				}
			}
			/* Mobile Number2 Validation */
			if (user.getMobileNumber2() != null && user.getMobileNumber2().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getMobileNumber2(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("mobileNumber2", "user.mobilenumber2.invalid");
				}
			}

 			/* Email Id Validation */
			if (user.getEmailId() != null && user.getEmailId().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(user.getEmailId(), messageSource.getMessage("common.email.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("emailId", "user.email.invalid");
				} else {
					if (userService.isEmailExist(user)) {
						errors.rejectValue("emailId", "user.email.exist");
					}
				}
			}
		} catch (Exception e) {
			log.info("Exception In User Validator:::" + e.getMessage());
		}

	}

}
