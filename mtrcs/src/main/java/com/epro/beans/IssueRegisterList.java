package com.epro.beans;

import java.util.List;

public class IssueRegisterList {
	private List<IssueRegister> issueRegisterList;
	private String projectId;
	public List<IssueRegister> getIssueRegisterList() {
		return issueRegisterList;
	}
	public void setIssueRegisterList(List<IssueRegister> issueRegisterList) {
		this.issueRegisterList = issueRegisterList;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
