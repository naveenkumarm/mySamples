package com.epro.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.client.core.WebServiceTemplate;

import com.epro.adapter.CustomerAdapter;
import com.epro.beans.CustomerInfo;
import com.epro.mapper.CustomerBeanMapper;
import com.epro.schema.CustomerInformation;
import com.epro.schema.DeleteCustomerInfoRequest;
import com.epro.schema.DeleteCustomerInfoResponse;
import com.epro.schema.EchoServiceResponse;
import com.epro.schema.GetCustomerInfoRequest;
import com.epro.schema.GetCustomerInfoResponse;
import com.epro.schema.SaveCustomerInfoRequest;
import com.epro.schema.SaveCustomerInfoResponse;

public class CustomerAdapterImpl implements CustomerAdapter {
	
	private WebServiceTemplate webServiceTemplate;	
	public CustomerAdapterImpl(
			WebServiceTemplate webServiceTemplate) {
		super();
		this.webServiceTemplate = webServiceTemplate;
	}
	@Override
	public boolean saveCustomerInfo(CustomerInfo custInfo){
		SaveCustomerInfoRequest request = new SaveCustomerInfoRequest();
		SaveCustomerInfoResponse response = new SaveCustomerInfoResponse();
		request.setCustomerInfo(CustomerBeanMapper.mapCustomerInfoTomapCustomerInfoDto(custInfo));
		try{
			response = (SaveCustomerInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}
	@Override
	public List<CustomerInfo> getCustomerInfo(String customerId){
		GetCustomerInfoRequest request = new GetCustomerInfoRequest();
		request.setCustomerId(customerId);
		GetCustomerInfoResponse response = new GetCustomerInfoResponse();
		try{
			response = (GetCustomerInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		List<CustomerInfo> customerInfos = new ArrayList<CustomerInfo>();
		for(CustomerInformation customerInformation : response.getCustomers()){
			customerInfos.add(CustomerBeanMapper.mapCustomerInfoDtoTomapCustomerInfo(customerInformation));
		}
		return customerInfos;
	}
	@Override
	public boolean deleteCustomerInfo(String customerId){
		DeleteCustomerInfoRequest request = new DeleteCustomerInfoRequest();
		request.setCustomerId(customerId);
		DeleteCustomerInfoResponse response = new DeleteCustomerInfoResponse();
		try{
			response = (DeleteCustomerInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}
}
