package com.epro.beans;

public class Employee {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String allocationPercent;
	private String sumOfAllocationPercent;
	private String projectLastReleasedDate;
	
	public String getSumOfAllocationPercent() {
		return sumOfAllocationPercent;
	}
	public void setSumOfAllocationPercent(String sumOfAllocationPercent) {
		this.sumOfAllocationPercent = sumOfAllocationPercent;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public String getAllocationPercent() {
		return allocationPercent;
	}
	public void setAllocationPercent(String allocationPercent) {
		this.allocationPercent = allocationPercent;
	}
	public String getProjectLastReleasedDate() {
		return projectLastReleasedDate;
	}
	public void setProjectLastReleasedDate(String projectLastReleasedDate) {
		this.projectLastReleasedDate = projectLastReleasedDate;
	}

}
