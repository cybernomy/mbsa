/**
 * MessageCreator.java
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
package com.mg.framework.api.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Обработчик создания сообщения для сессии JMS
 * 
 * @author Oleg V. Safonov
 * @version $Id: MessageCreator.java,v 1.1 2008/04/25 07:23:23 safonov Exp $
 */
public interface MessageCreator {

	/**
	 * создать сообщение
	 * 
	 * @param session	сессия JMS
	 * @return	сообщение
	 * @throws JMSException
	 */
	Message create(Session session) throws JMSException;

}
