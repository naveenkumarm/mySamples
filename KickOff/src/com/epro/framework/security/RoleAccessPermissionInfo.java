/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.security;


/*
 * AccessPermissionInfo.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class RoleAccessPermissionInfo {

	private long permissionId;	
	private int roleId;
	private int levelId;
	private long menuId;		
	private long subMenuId;	
	private boolean isViewEnabled;
	private boolean isCreateEnabled;
	private boolean isEditEnabled;
	private boolean isDeleteEnabled;
	private boolean isAllChecked;
	
	public long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public long getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(long subMenuId) {
		this.subMenuId = subMenuId;
	}
	public boolean isViewEnabled() {
		return isViewEnabled;
	}
	public void setViewEnabled(boolean isViewEnabled) {
		this.isViewEnabled = isViewEnabled;
	}
	public boolean isCreateEnabled() {
		return isCreateEnabled;
	}
	public void setCreateEnabled(boolean isCreateEnabled) {
		this.isCreateEnabled = isCreateEnabled;
	}
	public boolean isEditEnabled() {
		return isEditEnabled;
	}
	public void setEditEnabled(boolean isEditEnabled) {
		this.isEditEnabled = isEditEnabled;
	}
	public boolean isDeleteEnabled() {
		return isDeleteEnabled;
	}
	public void setDeleteEnabled(boolean isDeleteEnabled) {
		this.isDeleteEnabled = isDeleteEnabled;
	}
	public boolean isAllChecked() {
		return isAllChecked;
	}
	public void setAllChecked(boolean isAllChecked) {
		this.isAllChecked = isAllChecked;
	} 
	 
	 
	
}
