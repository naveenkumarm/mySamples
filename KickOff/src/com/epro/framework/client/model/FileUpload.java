/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.client.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 * FileUpload.java
 * Class description goes here.
 *
 * @version 1.0 19-Apr-2012
 * @author saravanau
 */

/*
 * getter and setter methods for multipart resolver 
 *   
 */

public class FileUpload {
	
	private long clientId;
	
	private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

}
