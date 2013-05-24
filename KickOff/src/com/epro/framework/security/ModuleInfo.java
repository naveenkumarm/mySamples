/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.security;

import java.util.List;

/*
 * ModuleInfo.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class ModuleInfo {
	private int moduleId;
	private String moduleName;
	private List<String> viewModeUrls;
	private List<String> createModeUrls;
	private List<String> editModeUrls;
	private List<String> deleteModeUrls; 
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
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
	 
}
