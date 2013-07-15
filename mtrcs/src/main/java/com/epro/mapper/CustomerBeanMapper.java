package com.epro.mapper;


import com.epro.beans.CustomerInfo;
import com.epro.schema.CustomerInformation;



public class CustomerBeanMapper {

	public static CustomerInformation mapCustomerInfoTomapCustomerInfoDto(CustomerInfo customerInfo) {
		CustomerInformation customerInformation = new CustomerInformation();
		if (customerInfo == null) {
			return null;
		}
		customerInformation.setCustomerId(customerInfo.getCustomerId());
		customerInformation.setCustomerName(customerInfo.getCustomerName());
		customerInformation.setLineOfBusiness(customerInfo.getLineOfBusiness());
		customerInformation.setPrimaryContactPerson(customerInfo.getPrimaryContactPerson());
		customerInformation.setAddress(customerInfo.getAddress());
		customerInformation.setCountry(customerInfo.getCountry());
		customerInformation.setState(customerInfo.getState());
		customerInformation.setPhoneNumber(customerInfo.getPhoneNumber());
		customerInformation.setFaxNumber(customerInfo.getFaxNumber());
		customerInformation.setWebsite(customerInfo.getWebsite());
		return customerInformation;
	}
	public static CustomerInfo mapCustomerInfoDtoTomapCustomerInfo(CustomerInformation customerInfo) {
		CustomerInfo customerInformation = new CustomerInfo();
		if (customerInfo == null) {
			return null;
		}
		customerInformation.setCustomerId(customerInfo.getCustomerId());
		customerInformation.setCustomerName(customerInfo.getCustomerName());
		customerInformation.setLineOfBusiness(customerInfo.getLineOfBusiness());
		customerInformation.setPrimaryContactPerson(customerInfo.getPrimaryContactPerson());
		customerInformation.setAddress(customerInfo.getAddress());
		customerInformation.setCountry(customerInfo.getCountry());
		customerInformation.setState(customerInfo.getState());
		customerInformation.setPhoneNumber(customerInfo.getPhoneNumber());
		customerInformation.setFaxNumber(customerInfo.getFaxNumber());
		customerInformation.setDeleteInd(customerInfo.getDeleteInd());
		customerInformation.setWebsite(customerInfo.getWebsite());
		return customerInformation;
	}

	

}
