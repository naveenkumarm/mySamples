package com.epro.beans;

import java.util.List;

public class ResourceDTO {
	
	private List<String> employeeId;
	private List<String> firstName;
	private List<String> lastName;
	private List<String> employeeRole;
	private List<String> startDate;
	private List<String> endDate;
	private List<String> billable;
	private List<String> currentLocation;
	private List<String> activeStatus;
	private List<Integer> allocationPercentage;
	public List<String> getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(List<String> employeeId) {
		this.employeeId = employeeId;
	}
	public List<String> getFirstName() {
		return firstName;
	}
	public void setFirstName(List<String> firstName) {
		this.firstName = firstName;
	}
	public List<String> getLastName() {
		return lastName;
	}
	public void setLastName(List<String> lastName) {
		this.lastName = lastName;
	}
	public List<String> getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(List<String> employeeRole) {
		this.employeeRole = employeeRole;
	}
	public List<String> getStartDate() {
		return startDate;
	}
	public void setStartDate(List<String> startDate) {
		this.startDate = startDate;
	}
	public List<String> getEndDate() {
		return endDate;
	}
	public void setEndDate(List<String> endDate) {
		this.endDate = endDate;
	}
	public List<String> getBillable() {
		return billable;
	}
	public void setBillable(List<String> billable) {
		this.billable = billable;
	}
	public List<String> getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(List<String> currentLocation) {
		this.currentLocation = currentLocation;
	}
	public List<String> getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(List<String> activeStatus) {
		this.activeStatus = activeStatus;
	}
	public void setAllocationPercentage(List<Integer> allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	public List<Integer> getAllocationPercentage() {
		return allocationPercentage;
	}
	
}