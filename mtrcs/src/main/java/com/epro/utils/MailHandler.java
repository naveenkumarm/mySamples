/*package statements*/
package com.epro.utils;

/*import statements*/
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

 public class MailHandler
 {
	private static String strMIMEType = null;
	private static String strFrom = null;
	private static String strBCC = null;
	private static String strPop3 = null;
	private static int portNumber;
	private static String strPop3Mail = null;
	private static String strPop3Pass = null;

    //To Log the output for debugging
	private static Log log = LogFactory.getLog(MailHandler.class.getName());
	
	static {		
		ResourceBundle bundle = ResourceBundle.getBundle("mail.MessageResources");		
		strMIMEType = bundle.getString("MAIL_MIMETYPE");
		strFrom = bundle.getString("MAIL_FROM_ADDRESS");
		strBCC = bundle.getString("MAIL_BCC_ADDRESS");
		strPop3 = bundle.getString("POP3_SERVER");
		portNumber = Integer.parseInt(bundle.getString("POP3_PORT"));
		strPop3Mail = bundle.getString("POP3.MAILID");
		strPop3Pass = bundle.getString("POP3.PASSWORD");
		System.out.println("strMIMEType - "+strMIMEType);
		System.out.println("strFrom - "+strFrom);
		System.out.println("strBCC - "+strBCC);
		System.out.println("strPop3 - "+strPop3);
		System.out.println("portNumber - "+portNumber);
		System.out.println("strPop3Mail - "+strPop3Mail);
		System.out.println("strPop3Pass - "+strPop3Pass);
	}
	/**
	 *	Method used to send mail
	 * @param strTo To Address of whom the mail is to be sent
	 * @param strContent Content of the mail
	 * @param strSubject Mail Subject
	 * @return true/false
	 */
	public static boolean sendMail(String strTo, String strContent, String strSubject)
	{
		log.debug("Processing send mail request:");
		Properties props = new Properties();
		
		Transport transport=null;
		Message message = null;

		props.put("mail.smtp.host", strPop3);
		// props.put("mail.smtp.auth","true");
		props.put("mail.smtp.port", Integer.toString(portNumber));
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl", "true");
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.debug", "true");  
		log.debug("Setting the mail domain for sending the message");
		
		//setting the mail domain for sending the message
		Session session = Session.getDefaultInstance(props, null);
		message = new MimeMessage(session);

		//Create MulitiPart
		Multipart multipart = new MimeMultipart();		

		try
		{
			InternetAddress [  ] bccAdd = InternetAddress.parse(strBCC);
			InternetAddress [  ] add = InternetAddress.parse(strTo);
			transport = session.getTransport("smtp");
			transport.connect(strPop3, portNumber, strPop3Mail, strPop3Pass);
			log.debug(strPop3);
			log.debug(portNumber);
			
			message.setFrom(new InternetAddress(strFrom));
			message.setRecipients(Message.RecipientType.TO, add);
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
			message.setSubject(strSubject);

			//create part one
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(strContent, strMIMEType);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(strContent, strMIMEType);
			message.saveChanges();

			transport.sendMessage(message,add);
			transport.sendMessage(message, bccAdd);
			
			log.debug("Mail sent successfully");
		}
		catch(AddressException ae)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("AddressException in sendMail(): "+ae.getMessage());
			return false;
		}
		catch(SendFailedException sfe)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("SendFailedException in sendMail(): "+sfe.getMessage());
			return false;
		}
		catch(MessagingException mex)
		{
			System.out.println("MessagingException in sendMail(): "+mex.getMessage());
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("MessagingException in sendMail(): "+mex.getMessage());
			return false;
		}	
		transport=null;
		message = null;
		log.info("Send mail request processed successfully");
		return true;
	}
	  
	/**
	 *	Method used to send mail
	 * @param strFrom From Address from which the mail will be sent
	 * @param strTo To Address of whom the mail is to be sent
	 * @param strTo Bcc Address of whom the mail is to be sent
	 * @param strContent Content of the mail
	 * @param strSubject Mail Subject
	 * @return true/false
	 */
	public static boolean sendMail(String strFrom, String strTo, String strBcc, String strContent, String strSubject)
	{
		log.debug("Processing send mail request:");
		Properties props = new Properties();
		
		Transport transport=null;
		Message message = null;

		props.put("mail.smtp.host", strPop3);
		// props.put("mail.smtp.auth","true");
		props.put("mail.smtp.port", Integer.toString(portNumber));
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl", "true");
		props.put("mail.smtp.auth", "true");
		
		log.debug("Setting the mail domain for sending the message");
		
		//setting the mail domain for sending the message
		Session session = Session.getDefaultInstance(props, null);
		message = new MimeMessage(session);

		//Create MulitiPart
		Multipart multipart = new MimeMultipart();		

		try
		{
			InternetAddress [  ] bccAdd = InternetAddress.parse(strBcc);
			InternetAddress [  ] add = InternetAddress.parse(strTo);
			transport = session.getTransport("smtp");
			transport.connect(strPop3, portNumber, strPop3Mail, strPop3Pass);
			
			message.setFrom(new InternetAddress(strFrom));
			message.setRecipients(Message.RecipientType.TO, add);
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
			message.setSubject(strSubject);

			//create part one
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(strContent, strMIMEType);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(strContent, strMIMEType);
			message.saveChanges();

			transport.sendMessage(message,add);
			transport.sendMessage(message, bccAdd);
			
			log.debug("Mail sent successfully");
		}
		catch(AddressException ae)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("AddressException in sendMail(): "+ae.getMessage());
			return false;
		}
		catch(SendFailedException sfe)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("SendFailedException in sendMail(): "+sfe.getMessage());
			return false;
		}
		catch(MessagingException mex)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("MessagingException in sendMail(): "+mex.getMessage());
			return false;
		}	
		transport=null;
		message = null;
		log.info("Send mail request processed successfully");
		return true;
	}
	  /**
	 *	Method used to send mail with attachments 
	 * @param strTo To Address of whom the mail is to be sent
	 * @param strContent Content of the mail
	 * @param strSubject Mail Subject
	 * @param strPath File Path
	 * @return true/false
	 */
	public static boolean sendMailAttachment(String strTo[], String strContent, String strSubject, String[] strPath)
	{
		log.info("Processing sending mail with attachment(s) request:");
		
		Properties props = new Properties();
		Transport transport=null;
		Message message = null;
		
		props.put("mail.smtp.host", strPop3);
		//props.put("mail.smtp.auth","true");
	       props.put("mail.smtp.port", Integer.toString(portNumber));        
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.ssl","true");
	        props.put("mail.smtps.auth", "true");   
		
		log.debug("Setting the mail domain for sending the message");
		
		//setting the mail domain for sending the message
		Session session = Session.getDefaultInstance(props, null);
		message = new MimeMessage(session);
		//Create MulitiPart
		Multipart multipart = new MimeMultipart();			
		
		try
		{			
			InternetAddress [  ] bccAdd = InternetAddress.parse(strBCC);

			//	InternetAddress [  ] add = InternetAddress.parse(strTo);
			InternetAddress[] addressTo = new InternetAddress[strTo.length]; 
		    for (int i = 0; i < strTo.length; i++)
		    {
		        addressTo[i] = new InternetAddress(strTo[i]);
		    }

		   	transport = session.getTransport("smtp");
			transport.connect(strPop3,portNumber,strPop3Mail,strPop3Pass);
		
			message.setFrom(new InternetAddress(strFrom));
		//	message.setRecipients(Message.RecipientType.TO, add);
		    message.setRecipients(Message.RecipientType.TO, addressTo);			
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
			message.setSubject(strSubject);
		
		//	create part one
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(strContent, strMIMEType);
			multipart.addBodyPart(messageBodyPart);
			
			// messageBodyPart = new MimeBodyPart();
			
			DataSource source = null;
			if (strPath != null)
			{
				log.debug("strPath.length "+strPath.length);
				for(int i =0; i < strPath.length;i++)
				{
					log.debug("strPath.length "+strPath[i]);
					if(strPath != null && !strPath[i].equalsIgnoreCase("null") && !strPath[i].equalsIgnoreCase(""))
					{
						messageBodyPart = new MimeBodyPart();
						source = new FileDataSource(strPath[i]);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(strPath[i]);
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}
		
			message.setContent(multipart);
			message.saveChanges();
		
			transport.sendMessage(message,addressTo);
			transport.sendMessage(message, bccAdd);
			
			log.info("Mail with attachment(s) sent successfully");
		}
		catch(AddressException ae)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("AddressException in sendMailAttachment(): "+ae.getMessage());
			return false;
		}
		catch(SendFailedException sfe)
		{
			log.error("Mail cannot be sent due to Send Failed Exception");
			log.error("SendFailedException in sendMailAttachment(): "+sfe.getMessage());
			return false;
		}
		catch(MessagingException mex)
		{
			log.error("Mail cannot be sent due to Messaging Exception");
			log.error("MessagingException in sendMailAttachment(): "+mex.getMessage());
			mex.printStackTrace();
			return false;
		}		
		
		transport=null;
		message = null;
		log.info("Send mail with attachment(s) request processed successfully");
		return true;
	}
	
	
	
	  /**
	 *	Method used to send mail with attachments 
	 * @param strFrom From Address from whom the mail will be sent
	 * @param strBcc Bcc Address for whom the mail is to be sent
	 * @param strTo To Address of whom the mail is to be sent
	 * @param strContent Content of the mail
	 * @param strSubject Mail Subject
	 * @param strPath File Path
	 * @return true/false
	 */
	public static boolean sendMailAttachment(String strFrom, String strBcc, String strTo[], String strContent, String strSubject, String[] strPath)
	{
		log.info("Processing sending mail with attachment(s) request:");
		
		strFrom = strFrom;
		strBcc = strBcc;
		
		Properties props = new Properties();
		Transport transport=null;
		Message message = null;
		
		props.put("mail.smtp.host", strPop3);
		//props.put("mail.smtp.auth","true");
	       props.put("mail.smtp.port", Integer.toString(portNumber));        
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.ssl","true");
	        props.put("mail.smtps.auth", "true");   
		
		log.debug("Setting the mail domain for sending the message");
		
		//setting the mail domain for sending the message
		Session session = Session.getDefaultInstance(props, null);
		message = new MimeMessage(session);
		//Create MulitiPart
		Multipart multipart = new MimeMultipart();			
		
		try
		{			
			InternetAddress [  ] bccAdd = InternetAddress.parse(strBcc);

			//	InternetAddress [  ] add = InternetAddress.parse(strTo);
			InternetAddress[] addressTo = new InternetAddress[strTo.length]; 
		    for (int i = 0; i < strTo.length; i++)
		    {
		        addressTo[i] = new InternetAddress(strTo[i]);
		    }

		   	transport = session.getTransport("smtp");
			transport.connect(strPop3,portNumber,strPop3Mail,strPop3Pass);
		
			message.setFrom(new InternetAddress(strFrom));
		//	message.setRecipients(Message.RecipientType.TO, add);
		    message.setRecipients(Message.RecipientType.TO, addressTo);			
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
			message.setSubject(strSubject);
		
		//	create part one
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(strContent, strMIMEType);
			multipart.addBodyPart(messageBodyPart);
			
			// messageBodyPart = new MimeBodyPart();
			
			DataSource source = null;
			if (strPath != null)
			{
				log.debug("strPath.length "+strPath.length);
				for(int i =0; i < strPath.length;i++)
				{
					log.debug("strPath.length "+strPath[i]);
					if(strPath != null && !strPath[i].equalsIgnoreCase("null") && !strPath[i].equalsIgnoreCase(""))
					{
						messageBodyPart = new MimeBodyPart();
						source = new FileDataSource(strPath[i]);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(strPath[i]);
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}
		
			message.setContent(multipart);
			message.saveChanges();
		
			transport.sendMessage(message,addressTo);
			transport.sendMessage(message, bccAdd);
			
			log.info("Mail with attachment(s) sent successfully");
		}
		catch(AddressException ae)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("AddressException in sendMailAttachment(): "+ae.getMessage());
			return false;
		}
		catch(SendFailedException sfe)
		{
			log.error("Mail cannot be sent due to Send Failed Exception");
			log.error("SendFailedException in sendMailAttachment(): "+sfe.getMessage());
			return false;
		}
		catch(MessagingException mex)
		{
			log.error("Mail cannot be sent due to Messaging Exception");
			log.error("MessagingException in sendMailAttachment(): "+mex.getMessage());
			mex.printStackTrace();
			return false;
		}		
		
		transport=null;
		message = null;
		log.info("Send mail with attachment(s) request processed successfully");
		return true;
	}
	
 
 /**
	 *	Method used to send mail with attachments along with file names
	 * @param strFrom From Address from whom the mail will be sent
	 * @param strBcc Bcc Address for whom the mail is to be sent
	 * @param strTo To Address of whom the mail is to be sent
	 * @param strContent Content of the mail
	 * @param strSubject Mail Subject
	 * @param strPath File Path
	 * @param strFileName File Name to be set while sending the mail
	 * @return true/false
	 */
	public static boolean sendMailAttachment(String strFrom, String strBcc, String strTo[], String strContent, String strSubject, String[] strPath, String[] strFileName)
	{
		log.info("Processing sending mail with attachment(s) request:");
		
		strFrom = strFrom;
		strBcc = strBcc;
		
		Properties props = new Properties();
		Transport transport=null;
		Message message = null;
		
		props.put("mail.smtp.host", strPop3);
		//props.put("mail.smtp.auth","true");
	       props.put("mail.smtp.port", Integer.toString(portNumber));        
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.ssl","true");
	        props.put("mail.smtps.auth", "true");   
		
		log.debug("Setting the mail domain for sending the message");
		
		//setting the mail domain for sending the message
		Session session = Session.getDefaultInstance(props, null);
		message = new MimeMessage(session);
		//Create MulitiPart
		Multipart multipart = new MimeMultipart();			
		
		try
		{			
		
			InternetAddress [  ] bccAdd = InternetAddress.parse(strBcc);
			log.debug("strTo.length 1"+strTo.length);
			//	InternetAddress [  ] add = InternetAddress.parse(strTo);
			InternetAddress[] addressTo = new InternetAddress[strTo.length];
			log.debug("strTo.length 2"+strTo.length);
		    for (int i = 0; i < strTo.length; i++)
		    {
		        addressTo[i] = new InternetAddress(strTo[i]);
		    }
		    log.debug("strTo.length 3"+strTo.length);
		   	transport = session.getTransport("smtp");
			transport.connect(strPop3,portNumber,strPop3Mail,strPop3Pass);
			log.debug("strFrom"+strFrom);
			message.setFrom(new InternetAddress(strFrom));
		    message.setRecipients(Message.RecipientType.TO, addressTo);			
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
			message.setSubject(strSubject);
			log.debug("strFrom"+strSubject);
		
		//	create part one
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(strContent, strMIMEType);
			multipart.addBodyPart(messageBodyPart);
			
			// messageBodyPart = new MimeBodyPart();
			
			DataSource source = null;
			if (strPath != null)
			{
				log.debug("strPath.length "+strPath.length);
				for(int i =0; i < strPath.length;i++)
				{
					log.debug("strPath.length "+strPath[i]);
					if(strPath != null && !strPath[i].equalsIgnoreCase("null") && !strPath[i].equalsIgnoreCase(""))
					{
						messageBodyPart = new MimeBodyPart();
						source = new FileDataSource(strPath[i]);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(strFileName[i]);
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}
		
			message.setContent(multipart);
			message.saveChanges();
		
			transport.sendMessage(message,addressTo);
			transport.sendMessage(message, bccAdd);
			
			log.info("Mail with attachment(s) sent successfully");
		}
		catch(AddressException ae)
		{
			log.error("Mail cannot be sent due to invalid/wrong email address");
			log.error("AddressException in sendMailAttachment(): "+ae.getMessage());
			return false;
		}
		catch(SendFailedException sfe)
		{
			log.error("Mail cannot be sent due to Send Failed Exception");
			log.error("SendFailedException in sendMailAttachment(): "+sfe.getMessage());
			return false;
		}
		catch(MessagingException mex)
		{
			log.error("Mail cannot be sent due to Messaging Exception");
			log.error("MessagingException in sendMailAttachment(): "+mex.getMessage());
			mex.printStackTrace();
			return false;
		}		
		
		transport=null;
		message = null;
		log.info("Send mail with attachment(s) request processed successfully");
		return true;
	}
	
}

