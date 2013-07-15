package com.epro.beans;

import java.util.List;

public class HandoverProjectInfo {
	private List<HandoverDocument> handoverDocuments;
	private List<CheckListInfo> checkListInfos;
	public List<HandoverDocument> getHandoverDocuments() {
		return handoverDocuments;
	}
	public void setHandoverDocuments(List<HandoverDocument> handoverDocuments) {
		this.handoverDocuments = handoverDocuments;
	}
	public void setCheckListInfos(List<CheckListInfo> checkListInfos) {
		this.checkListInfos = checkListInfos;
	}
	public List<CheckListInfo> getCheckListInfos() {
		return checkListInfos;
	}

}
