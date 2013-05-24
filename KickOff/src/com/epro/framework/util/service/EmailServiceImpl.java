/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */
package com.epro.framework.util.service;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.epro.framework.util.EmailInfo;

/*
 * EmailServiceImpl.java
 * Class implements EmailService.
 *
 * @version 1.0 June 15, 2012
 * @author antonyj
 */

@Service("emailService")
public class EmailServiceImpl implements EmailService{
	
	Logger log = Logger.getLogger(EmailServiceImpl.class);
 
	@Autowired
	private JavaMailSenderImpl mailSender; 
	
	@Autowired
	private VelocityEngine velocityEngine;  

	public EmailServiceImpl(){
	    MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
	}
	
 
	@Override
	public boolean sendMail(EmailInfo emailInfo) {
		try { 
			  
		    EmailMimeMessagePreparator emailMimeMessagePreparator = new EmailMimeMessagePreparator(emailInfo);
		    MimeMessage mimeMessage = mailSender.createMimeMessage(); 
		    
		    emailMimeMessagePreparator.prepare(mimeMessage);
		    mailSender.send(emailMimeMessagePreparator);
	    } catch(Exception ex) { 
	    	ex.printStackTrace();
	         log.error("Exception in Sending Email :"+ExceptionUtils.getStackTrace(ex));
	    } 
	    return true;
   }
	
	  public class EmailMimeMessagePreparator implements MimeMessagePreparator
      {
	      private EmailInfo emailInfo;
	      
	      public EmailMimeMessagePreparator(EmailInfo emailInfo){
	         this.emailInfo = emailInfo;
	      }
	      
	      @Override
          public void prepare(MimeMessage mimeMessage){
              
              try {
                   
                  MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, emailInfo.getIsPlainTextAttachment());
                  messageHelper.setSubject(emailInfo.getSubject());
                  messageHelper.setTo(new InternetAddress(emailInfo.getEmailId()));
                  messageHelper.setFrom(new InternetAddress("sns-admin@epro-tech.com"));
                  
                  if(velocityEngine == null){
                	  System.out.println("velocity engine : is Null");
                  }
                  
                  if(emailInfo == null){
                	  System.out.println("email info is null ");
                  }
                  
                  if(emailInfo.getTemplate() == null ){
                	  System.out.println("email info template is NUll ");
                  }
                  
                  if(emailInfo.getAttributesMap() == null){
                	  System.out.println("email info attributes map is null");
                  }
                  
                  String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailInfo.getTemplate(), emailInfo.getAttributesMap());
                  messageHelper.setText(body, true);
                   
                  if(emailInfo.getIsPlainTextAttachment()){
                      byte[] attachmentData = emailInfo.getAttachmentData().getBytes();
                      InputStreamSource source= new ByteArrayResource(attachmentData);
                      messageHelper.addAttachment(emailInfo.getAttachmentName(), source,"text/plain");
                  }
                  
              } catch (AddressException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } catch (VelocityException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } catch (MessagingException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } 
             
          }
 
      }

	
}
