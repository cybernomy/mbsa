/**
 * SystemAuditListenerBean.java
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
package com.mg.merp.core.support;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.mg.framework.api.Logger;
import com.mg.framework.api.SystemAuditEvent;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysAudit;
import com.mg.merp.core.model.SysClient;

/**
 * Реализация обработчика события аудита системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemAuditListenerBean.java,v 1.3 2008/05/29 08:37:10 safonov Exp $
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="topic/com/mg/jet/systemaudit")
})
public class SystemAuditListenerBean implements MessageListener {
	private static Logger logger = ServerUtils.getLogger(SystemAuditListenerBean.class);

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMsg = (ObjectMessage) message;
			SystemAuditEvent event = (SystemAuditEvent) objMsg.getObject();
			PersistentManager pm = ServerUtils.getPersistentManager();
			
			SysAudit sa = new SysAudit();
			sa.setSysClient(pm.find(SysClient.class, event.getSystemTenantId()));
			sa.setUserName(event.getUserName());
			sa.setAuditBean(event.getBeanName());
			sa.setEventDateTime(event.getEventDateTime());
			sa.setOperation(event.getOperation());
			sa.setDetails(event.getDetails());
			
			pm.persist(sa);
		} catch (Exception e) {
			logger.error("security audit failed", e);
		}
	}

}
