/**
 * EntityAuditListenerBean.java
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
import com.mg.framework.api.orm.EntityAuditEvent;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.DatabaseAudit;
import com.mg.merp.core.model.DatabaseAuditDetail;
import com.mg.merp.core.model.SysClient;

/**
 * Реализация обработчика события аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityAuditListenerBean.java,v 1.4 2008/05/29 08:37:10 safonov Exp $
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="topic/com/mg/jet/entityaudit")
})
public class EntityAuditListenerBean implements MessageListener {
	private static Logger logger = ServerUtils.getLogger(EntityAuditListenerBean.class);

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMsg = (ObjectMessage) message;
			EntityAuditEvent auditEvent = (EntityAuditEvent) objMsg.getObject();
			PersistentManager pm = ServerUtils.getPersistentManager();

			DatabaseAudit da = new DatabaseAudit();
			da.setSysClient(pm.find(SysClient.class, auditEvent.getSystemTenantId()));
			da.setAuditedEntityName(auditEvent.getEntityName());
			da.setAuditType(auditEvent.getAuditType());
			da.setUserName(auditEvent.getUserName());
			da.setEventDateTime(auditEvent.getEventDateTime());
			pm.persist(da);
			
			//primary key
			DatabaseAuditDetail dad = new DatabaseAuditDetail();
			dad.setDatabaseAudit(da);
			dad.setPropertyName(auditEvent.getIdentifierPropertyName());
			switch (auditEvent.getAuditType()) {
			case CREATE:
				dad.setState(auditEvent.getIdentifier());
				break;
			case MODIFY:
				dad.setState(auditEvent.getIdentifier());
				dad.setOldState(auditEvent.getIdentifier());
				break;
			case REMOVE:
				dad.setOldState(auditEvent.getIdentifier());
				break;
			}
			pm.persist(dad);			
			
			//properties
			for (int i = 0; i < auditEvent.getNames().length; i++) {
				dad = new DatabaseAuditDetail();
				dad.setDatabaseAudit(da);
				dad.setPropertyName(auditEvent.getNames()[i]);
				dad.setState(auditEvent.getState()[i]);
				dad.setOldState(auditEvent.getOldState()[i]);
				pm.persist(dad);
			}
		} catch (Exception e) {
			logger.error("entity audit failed", e);
		}
	}

}
