/**
 * MailUtils.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.framework.utils;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mg.framework.api.InternalException;

/**
 * Утилиты отправки почтовых сообщений
 * 
 * @author Oleg V. Safonov
 * @version $Id: MailUtils.java,v 1.1 2007/10/01 11:04:08 safonov Exp $
 */
public class MailUtils {

	/**
	 * получить сессию
	 * 
	 * @return	сессия
	 */
	private static Session getMailSession() {
		try {
			InitialContext ic = new InitialContext();
			return (Session) ic.lookup("java:/Mail");
		} catch (NamingException e) {
			throw new InternalException("Mail service not found", e);
		}		
	}
	
	/**
	 * отправить сообщение
	 * 
	 * @param to		кому, список email адресов разделенных запятой
	 * @param subject	тема
	 * @param body		содержимое
	 * @param charset	тип MIME
	 */
	public static void send(String to, String subject, String body, String mimeType) {
		Session session = getMailSession();
		
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom();
	        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to, false));
	        msg.setSubject(subject);

	        msg.setDataHandler(new DataHandler(body, mimeType));
	        msg.setHeader("X-Mailer", "JavaMailer");
	        msg.setSentDate(DateTimeUtils.nowDate());
	        
	        Transport.send(msg);
		} catch (MessagingException e) {
			throw new InternalException("Send mail failed", e);
		}
	}

	/**
	 * отправить сообщение, сообщение будет иметь тип MIME "text/plain", кодировку "UTF-8"
	 * 
	 * @param to		кому, список email адресов разделенных запятой
	 * @param subject	тема
	 * @param body		содержимое
	 */
	public static void send(String to, String subject, String body) {
		send(to, subject, body, "text/plain;charset=\"UTF-8\"");
	}
	
}
