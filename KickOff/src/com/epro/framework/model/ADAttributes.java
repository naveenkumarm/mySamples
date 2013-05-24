package com.epro.framework.model;

public class ADAttributes {
	
	private int ADAttributeId;
	private int ADUserId;
	private String attributeName;
	private String attributeValue;
	
	public int getADAttributeId() {
		return ADAttributeId;
	}
	public void setADAttributeId(int aDAttributeId) {
		ADAttributeId = aDAttributeId;
	}
	public int getADUserId() {
		return ADUserId;
	}
	public void setADUserId(int aDUserId) {
		ADUserId = aDUserId;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	

}
