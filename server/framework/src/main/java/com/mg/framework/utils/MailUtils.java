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
 * ������� �������� �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MailUtils.java,v 1.1 2007/10/01 11:04:08 safonov Exp $
 */
public class MailUtils {

	/**
	 * �������� ������
	 * 
	 * @return	������
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
	 * ��������� ���������
	 * 
	 * @param to		����, ������ email ������� ����������� �������
	 * @param subject	����
	 * @param body		����������
	 * @param charset	��� MIME
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
	 * ��������� ���������, ��������� ����� ����� ��� MIME "text/plain", ��������� "UTF-8"
	 * 
	 * @param to		����, ������ email ������� ����������� �������
	 * @param subject	����
	 * @param body		����������
	 */
	public static void send(String to, String subject, String body) {
		send(to, subject, body, "text/plain;charset=\"UTF-8\"");
	}
	
}
