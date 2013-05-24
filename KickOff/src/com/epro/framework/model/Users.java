package com.epro.framework.model;


import java.util.Date;

public class Users {

	private int userId;
  	private Roles roles;
  	private String employeeId;
  	private String department;
 	private String firstName;
	private String lastName;
 	private String userName;
	private String password;
 	private String workingPhone1;
	private String workingPhone2;
	private String extn1;
	private String extn2;
	private String mobileNumber1;
	private String mobileNumber2;
 	private String emailId;
	private Boolean status;
	private Date modifiedTime;
   	private Boolean isDeleted;
   	private boolean isAdmin;
   	private Clients clients;
	
 
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
  
 	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
 	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

  	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

 	public String getWorkingPhone1() {
		return workingPhone1;
	}

	public void setWorkingPhone1(String workingPhone1) {
		this.workingPhone1 = workingPhone1;
	}

	public String getWorkingPhone2() {
		return workingPhone2;
	}

	public void setWorkingPhone2(String workingPhone2) {
		this.workingPhone2 = workingPhone2;
	}

	public String getExtn1() {
		return extn1;
	}

	public void setExtn1(String extn1) {
		this.extn1 = extn1;
	}

	public String getExtn2() {
		return extn2;
	}

	public void setExtn2(String extn2) {
		this.extn2 = extn2;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

 	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
  
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

}
