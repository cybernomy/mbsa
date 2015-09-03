/**
 * SecurityAuditListenerBean.java
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
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.security.SecurityAuditEvent;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SecurityAudit;
import com.mg.merp.core.model.SysClient;

/**
 * Реализация обработчика события аудита безопасности
 * 
 * @author Oleg V. Safonov
 * @version $Id: SecurityAuditListenerBean.java,v 1.3 2008/05/29 08:37:10 safonov Exp $
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="topic/com/mg/jet/securityaudit")
})
public class SecurityAuditListenerBean implements MessageListener {
	private static Logger logger = ServerUtils.getLogger(SecurityAuditListenerBean.class);

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMsg = (ObjectMessage) message;
			SecurityAuditEvent event = (SecurityAuditEvent) objMsg.getObject();
			PersistentManager pm = ServerUtils.getPersistentManager();
			
			SecurityAudit sa = new SecurityAudit();
			sa.setAuditType(event.getAuditType());
			sa.setSysClient(pm.find(SysClient.class, event.getSystemTenantId()));
			sa.setUserName(event.getUserName());
			sa.setAuditBean(event.getBeanName());
			sa.setEventDateTime(event.getEventDateTime());
			sa.setDetails(event.getDetails());
			
			pm.persist(sa);
		} catch (Exception e) {
			logger.error("security audit failed", e);
		}
	}

}
