/**
 * DatabaseAuditServiceImpl.java
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
package com.mg.framework.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.transaction.Status;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.EntityMode;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.pretty.MessageHelper;
import org.hibernate.type.Type;

import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.DatabaseAuditType;
import com.mg.framework.api.orm.EntityAuditEvent;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.JmsUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация сервиса аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditServiceImpl.java,v 1.3 2008/05/29 08:19:28 safonov Exp $
 */
public class DatabaseAuditServiceImpl {
	private static Logger logger = ServerUtils.getLogger(DatabaseAuditServiceImpl.class);
	private String destinationName = "topic/com/mg/jet/entityaudit";
	private Map<String, EntityAuditSetup> entityAuditSetup;
	private ReadWriteLock entityAuditSetupLock;
	private boolean isAuditActivated;

	public DatabaseAuditServiceImpl(boolean isAuditActivated) {
		this.isAuditActivated = isAuditActivated;
		entityAuditSetup = new HashMap<String, EntityAuditSetup>();
		entityAuditSetupLock = new ReentrantReadWriteLock();
	}
	
	private class EntityAuditSetup {
		private boolean auditCreate = false;
		private boolean auditModify = false;
		private boolean auditRemove = false;
		private List<String> propertyNames;
		
		private EntityAuditSetup(PersistentObject auditSetup) {
			setAudit(auditSetup);
		}
		
		private boolean isAuditModifyAllProperties() {
			return auditModify && propertyNames == null;
		}

		private boolean isAuditModify() {
			return auditModify;
		}

		private boolean isAuditModifyProperty(String propertyName) {
			//попадает в аудит если список свойств пустой (это значит что аудируем все свойства)
			//или есть в списке
			return auditModify && (propertyNames == null || propertyNames.contains(propertyName));
		}
		
		private void setAudit(PersistentObject auditSetup) {
			Boolean auditCreate = (Boolean) auditSetup.getAttribute("AuditCreate");
			if (auditCreate != null)
				this.auditCreate = this.auditCreate || auditCreate;
			Boolean auditModify = (Boolean) auditSetup.getAttribute("AuditModify");
			if (auditModify != null)
				this.auditModify = this.auditModify || auditModify;
			Boolean auditRemove = (Boolean) auditSetup.getAttribute("AuditRemove");
			if (auditRemove != null)
				this.auditRemove = this.auditRemove || auditRemove;
			
			String propertyName = (String) auditSetup.getAttribute("PropertyName");
			if (propertyName != null && auditModify) {
				if (propertyNames == null)
					propertyNames = new ArrayList<String>();
				propertyNames.add(propertyName);
			}
		}
		
		private boolean isAuditCreate() {
			return auditCreate;
		}

		private boolean isAuditRemove() {
			return auditRemove;
		}

	}
	
	public void loadAuditSetup() {
		boolean startTran = false;
		try {
			logger.info("load audit setup");
			
			startTran = ServerUtils.getTransactionManager().getStatus() == Status.STATUS_NO_TRANSACTION;
			if (startTran)
				ServerUtils.getTransactionManager().begin();
			
			List<PersistentObject> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.DatabaseAuditSetup"));
			
			Lock lock = entityAuditSetupLock.writeLock();
			lock.lock();
			try {
				if (entityAuditSetup != null)
					entityAuditSetup.clear();

				for (PersistentObject item : list) {
					String entityName = (String) item.getAttribute("AuditedEntityName");
					EntityAuditSetup auditSetup = entityAuditSetup.get(entityName);
					if (auditSetup == null)
						entityAuditSetup.put(entityName, new EntityAuditSetup(item));
					else
						auditSetup.setAudit(item);
				}
			} finally {
				lock.unlock();
			}
			
			if (startTran)
				ServerUtils.getTransactionManager().commit();
		} catch (Exception e) {
			logger.error("load audit setup failed", e);
			try {
				if (startTran)
					ServerUtils.getTransactionManager().rollback();
			} catch (Exception ee) {
				logger.error("transaction rollback failed", ee);
			}
		}
	}
	
	public void stop() {
		Lock lock = entityAuditSetupLock.writeLock();
		lock.lock();
		try {
			entityAuditSetup.clear();
		} finally {
			lock.unlock();
		}
	}
	
	private void sendAuditMessage(EntityAuditEvent message) throws Exception {
		JmsUtils.sendObjectMessageToTopic(destinationName, message);
	}
	
	private String entityPropertyToString(Object state, ClassMetadata metadata) {
		if (state != null) {
			Serializable id = metadata.getIdentifier(state, EntityMode.POJO);
			return MessageHelper.infoString(metadata.getEntityName(), id);
		} else
			return null;
	}
	
	public void auditCreate(PostInsertEvent createEvent) {
		if (!isAuditActivated)
			return;

		try {
			Lock lock = entityAuditSetupLock.readLock();
			lock.lock();
			try {
				EntityAuditSetup auditSetup = entityAuditSetup.get(createEvent.getPersister().getEntityName());
				if (auditSetup ==  null || !auditSetup.isAuditCreate())
					return;
			} finally {
				lock.unlock();
			}
			
			String[] names = createEvent.getPersister().getPropertyNames();
			String[] stateStr = new String[names.length];
			String[] oldStateStr = new String[names.length];
			
			for (int i = 0; i < names.length; i++) {
				Type type = createEvent.getPersister().getPropertyType(names[i]);
				if (type.isCollectionType())
					continue;
				
				if (type.isEntityType()) {
					ClassMetadata metadata = createEvent.getPersister().getFactory().getClassMetadata(type.getName());
					stateStr[i] = entityPropertyToString(createEvent.getState()[i], metadata);
					oldStateStr[i] = null;
				} else {
					stateStr[i] = createEvent.getState()[i] == null ? null : createEvent.getState()[i].toString();
					oldStateStr[i] = null;
				}
			}
			
			sendAuditMessage(new EntityAuditEvent(createEvent.getPersister().getEntityName(), DatabaseAuditType.CREATE,
					createEvent.getId().toString(), createEvent.getPersister().getIdentifierPropertyName(), names, stateStr, oldStateStr));
		} catch (Exception e) {
			logger.error("audit create failed", e);
		}
	}
	
	public void auditModify(PostUpdateEvent modifyEvent) {
		if (!isAuditActivated)
			return;

		try {
			List<Integer> auditedList;
			String[] names;
			Lock lock = entityAuditSetupLock.readLock();
			lock.lock();
			try {
				EntityAuditSetup auditSetup = entityAuditSetup.get(modifyEvent.getPersister().getEntityName());
				if (auditSetup ==  null || !auditSetup.isAuditModify())
					return;

				auditedList = new ArrayList<Integer>();
				names = modifyEvent.getPersister().getPropertyNames();
				if (!auditSetup.isAuditModifyAllProperties()) {
					for (int i = 0; i < names.length; i++)
						if (auditSetup.isAuditModifyProperty(names[i]))
							auditedList.add(i);
				} else {
					for (int i = 0; i < names.length; i++)
						auditedList.add(i);
				}
			} finally {
				lock.unlock();
			}
			
			if (auditedList.isEmpty())
				return;
			
			String[] stateStr = new String[auditedList.size()];
			String[] oldStateStr = new String[auditedList.size()];
			String[] auditedNames = new String[auditedList.size()];
			
			int j = 0;
			for (int i = 0; i < names.length; i++) {
				if (!auditedList.contains(i))
					continue;
				
				Type type = modifyEvent.getPersister().getPropertyType(names[i]);
				if (type.isCollectionType())
					continue;
				
				auditedNames[j] = names[i];
				if (type.isEntityType()) {
					ClassMetadata metadata = modifyEvent.getPersister().getFactory().getClassMetadata(type.getName());
					stateStr[j] = entityPropertyToString(modifyEvent.getState()[i], metadata);
					oldStateStr[j] = entityPropertyToString(modifyEvent.getOldState()[i], metadata);
				} else {
					stateStr[j] = modifyEvent.getState()[i] == null ? null : modifyEvent.getState()[i].toString();
					oldStateStr[j] = modifyEvent.getOldState()[i] == null ? null : modifyEvent.getOldState()[i].toString();
				}
				
				j++;
			}
			
			//нет свойств для аудита
			if (j == 0)
				return;
			
			//свойств для аудита меньше чем было установлено
			if (names.length > j) {
				auditedNames = (String[]) ArrayUtils.subarray(auditedNames, 0, j);
				stateStr = (String[]) ArrayUtils.subarray(stateStr, 0, j);
				oldStateStr = (String[]) ArrayUtils.subarray(oldStateStr, 0, j);
			}
			
			sendAuditMessage(new EntityAuditEvent(modifyEvent.getPersister().getEntityName(), DatabaseAuditType.MODIFY,
					modifyEvent.getId().toString(), modifyEvent.getPersister().getIdentifierPropertyName(), auditedNames, stateStr, oldStateStr));
		} catch (Exception e) {
			logger.error("audit modify failed", e);
		}
	}
	
	public void auditRemove(PostDeleteEvent removeEvent) {
		if (!isAuditActivated)
			return;

		try {
			Lock lock = entityAuditSetupLock.readLock();
			lock.lock();
			try {
				EntityAuditSetup auditSetup = entityAuditSetup.get(removeEvent.getPersister().getEntityName());
				if (auditSetup ==  null || !auditSetup.isAuditRemove())
					return;
			} finally {
				lock.unlock();
			}
			
			String[] names = removeEvent.getPersister().getPropertyNames();
			String[] stateStr = new String[names.length];
			String[] oldStateStr = new String[names.length];
			
			for (int i = 0; i < names.length; i++) {
				Type type = removeEvent.getPersister().getPropertyType(names[i]);
				if (type.isCollectionType())
					continue;
				
				if (type.isEntityType()) {
					ClassMetadata metadata = removeEvent.getPersister().getFactory().getClassMetadata(type.getName());
					oldStateStr[i] = entityPropertyToString(removeEvent.getDeletedState()[i], metadata);
					stateStr[i] = null;
				} else {
					oldStateStr[i] = removeEvent.getDeletedState()[i] == null ? null : removeEvent.getDeletedState()[i].toString();
					stateStr[i] = null;
				}
			}
			
			sendAuditMessage(new EntityAuditEvent(removeEvent.getPersister().getEntityName(), DatabaseAuditType.REMOVE,
					removeEvent.getId().toString(), removeEvent.getPersister().getIdentifierPropertyName(), names, stateStr, oldStateStr));
		} catch (Exception e) {
			logger.error("audit create failed", e);
		}		
	}

	public String getJmsDestinationName() {
		return destinationName;
	}
	
	public void setJmsDestinationName(String name) {
		if (name != null)
			destinationName = name;
	}
	
}
