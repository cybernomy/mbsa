/**
 * AdminMessageQueueHandler.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import com.mg.framework.support.Messages;

/**
 * Обработчик получений сообщений от администратора системы пользователю
 * 
 * @author Oleg V. Safonov
 * @version $Id: AdminMessageQueueHandler.java,v 1.1 2008/07/14 14:11:27 safonov Exp $
 */
public class AdminMessageQueueHandler implements Observer, Serializable {
	private String httpSessionId;
	private String messageBody;
	private Date messageTime;

	public AdminMessageQueueHandler(String httpSessionId) {
		this.httpSessionId = httpSessionId;
		AdminMessageSender.getInstance().addObserver(this);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof AdminMessageSender.AdminMessage) {
			AdminMessageSender.AdminMessage message = (AdminMessageSender.AdminMessage) arg;
			for (String id : message.getSessionIds())
				//для каждой сессии создается свой обработчик, проверяем есть ли сообщение для
				//нашей сесии
				if (id != null && id.equals(httpSessionId)) {
					synchronized(this) {
						messageBody = message.getMessage();
						messageTime = message.getMessageTime();
						break;
					}
				}
		}
	}

	/**
	 * получить сообщение
	 * 
	 * @return	сообщение или <code>null</code> если сообщений не было
	 */
	public String getMessage() {
		synchronized(this) {
			String result = messageBody;
			if (result != null)
				result = Messages.getInstance().getMessage(Messages.ADMIN_MESSAGE_BODY, new Object[] {messageTime, messageBody});
			//сбросим сообщение после получения, считаем обработанным
			messageBody = null;
			messageTime = null;
			return result;
		}
	}
	
}
