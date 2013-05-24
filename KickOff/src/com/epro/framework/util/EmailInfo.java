/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */
package com.epro.framework.util;


/*
 * EmailInfo.java
 * Info class for Email.
 *
 * @version 1.0 June 15, 2012
 * @author antonyj
 */


import java.util.Hashtable;


public class EmailInfo {

	private String template;
	private String EmailId;
	private String Subject;
	private String message;
	private boolean isPlainTextAttachment;
	private String attachmentName;
	private String attachmentData; 
	 
	private Hashtable<String, String> attributesMap;
	

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Hashtable<String, String> getAttributesMap() {
		return attributesMap;
	}
	public void setAttributesMap(Hashtable<String, String> attributesMap) {
		this.attributesMap = attributesMap;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}

    public boolean getIsPlainTextAttachment() {
        return isPlainTextAttachment;
    }

    public void setIsPlainTextAttachment(boolean isPlainTextAttachment) {
        this.isPlainTextAttachment = isPlainTextAttachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }
 

    public void setPlainTextAttachment(boolean isPlainTextAttachment) {
        this.isPlainTextAttachment = isPlainTextAttachment;
    }

}
