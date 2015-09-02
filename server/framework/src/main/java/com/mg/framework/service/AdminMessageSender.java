/**
 * AdminMessageSender.java
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

import com.mg.framework.utils.DateTimeUtils;

/**
 * –еализаци€ сервиса отправки сообщений администратора системы пользовател€м. »спользуетс€
 * простейша€ реализаци€, не поддерживаетс€ кластерна€ установка.
 * 
 * @author Oleg V. Safonov
 * @version $Id: AdminMessageSender.java,v 1.1 2008/07/14 14:11:27 safonov Exp $
 */
public class AdminMessageSender extends Observable {
	private static AdminMessageSender instance = new AdminMessageSender();

	/**
	 *  ласс сообщение администратора
	 */
	public class AdminMessage implements Serializable {
		private String[] sessionIds;
		private String message;
		private Date messageTime;

		public AdminMessage(String[] sessionIds, String message) {
			super();
			this.sessionIds = sessionIds;
			this.message = message;
			this.messageTime = DateTimeUtils.nowDate();
		}

		/**
		 * список сессий
		 * 
		 * @return the sessionIds
		 */
		public String[] getSessionIds() {
			return sessionIds;
		}

		/**
		 * сообщение
		 * 
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * врем€ создани€ сообщени€
		 * 
		 * @return the messageTime
		 */
		public Date getMessageTime() {
			return messageTime;
		}

	}
	
	/**
	 * получить экземпл€р сервиса
	 * 
	 * @return	экземпл€р сервиса
	 */
	public static AdminMessageSender getInstance() {
		return instance;
	}

	/**
	 * отправить сообщение пользовател€м
	 * 
	 * @param sessionIds	список сессий пользователей
	 * @param message	сообщение
	 */
	public void sendMessage(String[] sessionIds, String message) {
		setChanged();
		notifyObservers(new AdminMessage(sessionIds, message));
	}
	
}
