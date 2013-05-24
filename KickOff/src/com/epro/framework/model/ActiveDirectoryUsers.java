package com.epro.framework.model;

public class ActiveDirectoryUsers {
	
	private int ADUserId;
	private int userIdentity;
	private long clientId;
	private boolean isActive;
	private boolean isVoiceBioEnabled;
	
	public int getUserIdentity() {
		return userIdentity;
	}
	public void setUserIdentity(int userIdentity) {
		this.userIdentity = userIdentity;
	}
	public int getADUserId() {
		return ADUserId;
	}
	public void setADUserId(int aDUserId) {
		ADUserId = aDUserId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getIsVoiceBioEnabled() {
		return isVoiceBioEnabled;
	}
	public void setIsVoiceBioEnabled(boolean isVoiceBioEnabled) {
		this.isVoiceBioEnabled = isVoiceBioEnabled;
	}
}
