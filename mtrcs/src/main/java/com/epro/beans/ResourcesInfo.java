package com.epro.beans;

import java.util.List;

public class ResourcesInfo {
	private List<Resource> resourceList;
	private String projectId;
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
}
