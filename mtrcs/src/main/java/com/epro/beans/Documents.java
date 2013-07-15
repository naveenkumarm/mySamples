package com.epro.beans;

public class Documents {
	private String description;
	private String projectId;
	private String projectDocId;
	private String documentName;
	private String documentType;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectDocId() {
		return projectDocId;
	}
	public void setProjectDocId(String projectDocId) {
		this.projectDocId = projectDocId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
