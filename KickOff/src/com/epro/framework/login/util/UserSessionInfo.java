package com.epro.framework.login.util;

import java.util.Hashtable;

public class UserSessionInfo {
	
	private String sessionId;
	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNumber;
	private String roleLevel;
	private int roleLevelId;
	private int roleId;
	private String roleName;
	private Hashtable<String, Boolean> isMenuEnabled = new Hashtable<String, Boolean>();
	private Hashtable<String, Boolean> isSubMenuEnabled = new Hashtable<String, Boolean>();
	private Hashtable<String, UserAccessRightsInfo> accessRights = new Hashtable<String, UserAccessRightsInfo>();
	private int tenantId;
	private int subTenantId;
	private long clientId;
	private String logoPath;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	} 
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public Hashtable<String, Boolean> getIsMenuEnabled() {
		return isMenuEnabled;
	}
	public void setIsMenuEnabled(Hashtable<String, Boolean> isMenuEnabled) {
		this.isMenuEnabled = isMenuEnabled;
	}
	public Hashtable<String, Boolean> getIsSubMenuEnabled() {
		return isSubMenuEnabled;
	}
	public void setIsSubMenuEnabled(Hashtable<String, Boolean> isSubMenuEnabled) {
		this.isSubMenuEnabled = isSubMenuEnabled;
	}
	public Hashtable<String, UserAccessRightsInfo> getAccessRights() {
		return accessRights;
	}
	public void setAccessRights(Hashtable<String, UserAccessRightsInfo> accessRights) {
		this.accessRights = accessRights;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public int getSubTenantId() {
		return subTenantId;
	}
	public void setSubTenantId(int subTenantId) {
		this.subTenantId = subTenantId;
	}
	public int getRoleLevelId() {
		return roleLevelId;
	}
	public void setRoleLevelId(int roleLevelId) {
		this.roleLevelId = roleLevelId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	
}
