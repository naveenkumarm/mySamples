package com.epro.framework.model;

import java.util.Date;

public class PasswordResetLog {
	
	private int logId;
	private int userIdentity;
	private long clientId;
	private Date resetTimeStamp;
	
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getUserIdentity() {
		return userIdentity;
	}
	public void setUserIdentity(int userIdentity) {
		this.userIdentity = userIdentity;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public Date getResetTimeStamp() {
		return resetTimeStamp;
	}
	public void setResetTimeStamp(Date resetTimeStamp) {
		this.resetTimeStamp = resetTimeStamp;
	}

}
