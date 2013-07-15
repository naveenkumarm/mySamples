package com.epro.beans;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private String groupId;
	private String groupName;
    private List<Project> projectList = new ArrayList<Project>();
    
    public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	public List<Project> getProjectList() {
		return projectList;
	}

}
