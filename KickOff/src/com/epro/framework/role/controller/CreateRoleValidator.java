package com.epro.framework.role.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Roles;
import com.epro.framework.role.service.RoleService;
import com.epro.framework.util.HelperClass;

@Component("createRoleValidator")
public class CreateRoleValidator implements Validator {
	private Logger log = Logger.getLogger(CreateRoleValidator.class);

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private RoleService roleService;

 	@Override
	public boolean supports(Class<?> clazz) {
		return Roles.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
	}

	public void roleValidate(Roles model, Errors errors, UserSessionInfo userSessionInfo) {
		try {
 			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "createrole.status.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "createrole.rolename.required");

			Roles role = (Roles) model;
 
			if (role.getRoleName() != null && role.getRoleName().trim().length() > 0) {

				if (!HelperClass.validateStringRegex(role.getRoleName(), messageSource.getMessage("role.rolename.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("roleName", "role.roleName.Invalid");
				} 
				if (roleService.isRoleNameExist(role)) {
					log.info("Role Name Exist");
					errors.rejectValue("roleName", "role.rolename.exist");
				}
			}
		} catch (Exception e) {
			log.info("Exception in CreateRoleValidator " + e.getMessage());
		}
	}

}
