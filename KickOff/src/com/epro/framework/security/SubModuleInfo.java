/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.security;

import java.util.List;

/*
 * SubModuleInfo.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class SubModuleInfo {
	private int subModuleId;
	private String subModuleName;
	private List<String> viewModeUrls;
	private List<String> createModeUrls;
	private List<String> editModeUrls;
	private List<String> deleteModeUrls; 
	 
	public List<String> getViewModeUrls() {
		return viewModeUrls;
	}
	public void setViewModeUrls(List<String> viewModeUrls) {
		this.viewModeUrls = viewModeUrls;
	}
	public List<String> getCreateModeUrls() {
		return createModeUrls;
	}
	public void setCreateModeUrls(List<String> createModeUrls) {
		this.createModeUrls = createModeUrls;
	}
	public List<String> getEditModeUrls() {
		return editModeUrls;
	}
	public void setEditModeUrls(List<String> editModeUrls) {
		this.editModeUrls = editModeUrls;
	}
	public List<String> getDeleteModeUrls() {
		return deleteModeUrls;
	}
	public void setDeleteModeUrls(List<String> deleteModeUrls) {
		this.deleteModeUrls = deleteModeUrls;
	}
	public int getSubModuleId() {
		return subModuleId;
	}
	public void setSubModuleId(int subModuleId) {
		this.subModuleId = subModuleId;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
}
