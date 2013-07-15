package com.epro.beans;

import java.util.List;

public class HandoverDocument {
	private byte[] file;
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
	public void setFile(byte[] file) {
		this.file = file;
	}
	public byte[] getFile() {
		return file;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentType() {
		return documentType;
	}

}
