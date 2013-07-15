package com.epro.beans;


public class Resource {
	private Employee employee;
	private String projectResourceId;
	private String roleId;
	private String roleName;
	private String startDate;
	private String endDate;
	private String billable;
	private String currentLocation;
	private String activeStatus;
	private int allocationPercentage;
	private String sumOfAllocationPercent;
	private String removeInd;
	
	public String getRemoveInd() {
		return removeInd;
	}
	public void setRemoveInd(String removeInd) {
		this.removeInd = removeInd;
	}
	public String getSumOfAllocationPercent() {
		return sumOfAllocationPercent;
	}
	public void setSumOfAllocationPercent(String sumOfAllocationPercent) {
		this.sumOfAllocationPercent = sumOfAllocationPercent;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public int getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(int allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setProjectResourceId(String projectResourceId) {
		this.projectResourceId = projectResourceId;
	}
	public String getProjectResourceId() {
		return projectResourceId;
	}
}
