package com.epro.framework.client.controller;

import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epro.framework.client.service.ClientService;
import com.epro.framework.model.Clients;
import com.epro.framework.util.HelperClass;

@Component("clientValidator")
public class ClientValidator implements Validator {

	private Logger log = Logger.getLogger(ClientValidator.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ClientService clientService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Clients.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		/*
		 * private long clientId; private String clientName; private String
		 * description; private String businessType; private String address;
		 * private String city; private String state; private String zip;
		 * private String country; private boolean status; private String
		 * contactPerson; private String workPhone; private String faxNo;
		 * private String mobileNumber; private String emailId; private Date
		 * modifiedDate; private boolean isDeleted; private String logoPath;
		 */

		try {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientName", "client.clientname.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessType", "client.businesstype.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "client.address.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "client.city.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "client.state.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "client.zip.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "client.country.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson", "client.contactPerson.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workPhone", "client.workPhone.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "client.mobileNumber.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "client.emailId.required");

			Clients client = (Clients) object;

			/* Client Name Validation */
			if (client.getClientName() != null && client.getClientName().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getClientName(), messageSource.getMessage("myaccount.firstname.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("clientName", "client.clientName.invalid");
				}
			}

			/* User Name */
			if (client.getClientName() != null && client.getClientName().trim().length() > 0) {
				if (clientService.isClientNameExist(client)) {
					log.info("Client Name Exist");
					errors.rejectValue("clientName", "client.clientname.exist");
				}
			}

			/* Work1 Validation */
			if (client.getWorkPhone() != null && client.getWorkPhone().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getWorkPhone(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("workPhone", "client.WorkPhone.invalid");
				}
			}
			/* Work2 Validation */
			if (client.getMobileNumber() != null && client.getMobileNumber().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getMobileNumber(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("mobileNumber", "client.mobilenumber.invalid");
				}
			}
			/* Extn1 Validation */
			if (client.getFaxNo() != null && client.getFaxNo().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getFaxNo(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("faxNo", "client.faxNo.invalid");
				}
			}

			/* Extn2 Validation */
			if (client.getZip() != null && client.getZip().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getZip(), messageSource.getMessage("common.phone.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("zip", "client.zip.invalid");
				}
			}

			/* Email Id Validation */
			if (client.getEmailId() != null && client.getEmailId().trim().length() > 0) {
				if (!HelperClass.validateStringRegex(client.getEmailId(), messageSource.getMessage("common.email.regex", new String[] {}, Locale.US))) {
					errors.rejectValue("emailId", "client.email.invalid");
				} else {
					// if (userService.isEmailExist(client)) {
					// errors.rejectValue("emailId", "user.email.exist");
					// }
				}
			}
		} catch (Exception e) {
			log.error("Exception In Client Validator:::" +ExceptionUtils.getStackTrace(e));
		}

	}
}
