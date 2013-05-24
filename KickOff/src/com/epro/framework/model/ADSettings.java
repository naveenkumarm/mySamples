package com.epro.framework.model;

public class ADSettings {
	
	private int settingsId;
	private long clientId;
	private String key;
	private String value;
	
	public int getSettingsId() {
		return settingsId;
	}
	public void setSettingsId(int settingsId) {
		this.settingsId = settingsId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
