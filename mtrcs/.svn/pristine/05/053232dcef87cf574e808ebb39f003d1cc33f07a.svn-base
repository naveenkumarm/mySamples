package com.epro.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epro.adapter.CustomerAdapter;
import com.epro.beans.CustomerInfo;

@Controller
public class CustomerController {
	@Autowired
	private CustomerAdapter customerAdapter;
	private final String DATASOURCE_URL_GET = "/ems-web/ems/customer/get";
	
	@RequestMapping(value = "/customer/view")
	public String getCustomerView(ModelMap model,@RequestParam(value = "request", required = true) String type) {
		if(type.equals("get")){
			model.addAttribute("datasource", DATASOURCE_URL_GET);
		}
		model.addAttribute("title", "Customer Information");
		return "customerView";
	}
	
	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
	public String saveCustomerInfo(ModelMap model,@ModelAttribute("customerInfo") CustomerInfo custInfo) {
		boolean success = customerAdapter.saveCustomerInfo(custInfo);
		List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		/*if(success){
			customerList = customerAdapter.getCustomerInfo();
		}
		model.addAttribute("customerList", customerList);*/
		model.addAttribute("success", success);
		return "jsonView";
	}
	
	@RequestMapping(value = "/customer/get")
	public String getCustomerInfo(ModelMap model) {

		List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		customerList = customerAdapter.getCustomerInfo("-1");
		model.addAttribute("customerList", customerList);
		return "jsonView";
	}
	@RequestMapping(value = "/customer/edit")
	public String editCustomerInfo(ModelMap model,
			@RequestParam(value = "customerId", required = true) String customerId) {

		List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		customerList = customerAdapter.getCustomerInfo(customerId);
		model.addAttribute("customer", customerList.get(0));
		return "jsonView";
	}
	@RequestMapping(value = "/customer/delete")
	public String deleteCustomerInfo(ModelMap model,
			@RequestParam(value = "customerId", required = true) String customerId) {

		boolean success = customerAdapter.deleteCustomerInfo(customerId);
		/*List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		if(success){
			customerList = customerAdapter.getCustomerInfo();
		}*/
		model.addAttribute("success", success);
		return "jsonView";
	}
	
	@RequestMapping(value = "/customer/add")
	public String addCustomerInfo(ModelMap model) {

		return "saveCustomerInfo";
	}
}
