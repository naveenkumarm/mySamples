package com.epro.beans;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

	private String userId;
	private String loginId;
	private String password;
	public String userName;
	private String userFirstName;
	private String userLastName;
	private String timeSheetCutoffInd;
	private String maxHours;
	private List<Role> userRoles = new ArrayList<Role>();
	boolean isActive;
	boolean isDeleted;
	private String roleId;
	private String roleName;
	private String employeeId;
	private String sourceFrom;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getTimeSheetCutoffInd() {
		return timeSheetCutoffInd;
	}
	public void setTimeSheetCutoffInd(String timeSheetCutoffInd) {
		this.timeSheetCutoffInd = timeSheetCutoffInd;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginId() {
		return loginId;
	}
	public List<Role> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}
	public boolean isIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getMaxHours() {
		return maxHours;
	}
	public void setMaxHours(String maxHours) {
		this.maxHours = maxHours;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public String getSourceFrom() {
		return sourceFrom;
	}
	
}
