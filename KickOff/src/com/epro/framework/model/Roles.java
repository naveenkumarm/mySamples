package com.epro.framework.model;

import java.util.Set;

public class Roles {

	private int roleId;
	private String roleName;
	private String organization;
 	private Boolean status;
	private Boolean isDeleted;
 	private Set<RoleAccessPermission> roleAccessPermissionses;
	private long clientId;
	
	public Roles(){
	}
	
	public Roles(int roleId,String roleName,String organization,Boolean status,Boolean isDeleted,Set roleAccessPermissionses){
		this.roleId = roleId;
		this.roleName = roleName;
		this.organization = organization;
 		this.status = status;
		this.isDeleted = isDeleted;
		this.roleAccessPermissionses = roleAccessPermissionses;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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

 	
	public Set<RoleAccessPermission> getRoleAccessPermissionses() {
		return roleAccessPermissionses;
	}

	public void setRoleAccessPermissionses(Set<RoleAccessPermission> roleAccessPermissionses) {
		this.roleAccessPermissionses = roleAccessPermissionses;
	}
	
	public long getClientId() {
		return clientId;
	}
	
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

}
