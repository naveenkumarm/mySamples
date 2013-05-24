package com.epro.framework.model;

import java.util.Date;

public class VoiceBioLog {
			
	private int logId;
	private int userIdentity;
	private long clientId;
	private String voiceBioMetricType;
	private Date timeStamp;
	private String status;
	private int score;
	
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
	public String getVoiceBioMetricType() {
		return voiceBioMetricType;
	}
	public void setVoiceBioMetricType(String voiceBioMetricType) {
		this.voiceBioMetricType = voiceBioMetricType;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	

}
