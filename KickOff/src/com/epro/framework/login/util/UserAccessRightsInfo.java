package com.epro.framework.login.util;

public class UserAccessRightsInfo {

	private boolean isViewEnabled;
	private boolean isCreateEnabled;
	private boolean isEditEnabled;
	private boolean isDeleteEnabled;
	
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
	
}
