/*
 * HibernateListenerInjectorImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.management.ObjectName;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.jboss.deployment.DeploymentException;
import org.jboss.hibernate.ListenerInjector;
import org.jboss.hibernate.jmx.HibernateMBean;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.system.server.ServerConfigLocator;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация "слушателя" событий Hibernate
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernateListenerInjectorImpl.java,v 1.10 2008/10/09 13:27:41 safonov Exp $
 */
public class HibernateListenerInjectorImpl implements ListenerInjector {
	private Logger logger = ServerUtils.getLogger(HibernateListenerInjectorImpl.class);

	private void setGlobalAttributes(PersistentObject entity, Object[] state, String[] propertyNames) {
        for (int i = 0; i < state.length; i++) {
        	if (propertyNames[i].equals("SysClient") && state[i] == null) {
        		state[i] = ServerUtils.getCurrentSession().getSystemTenant();
        		((PersistentObject) entity).setAttribute("SysClient", state[i]);
        		break;
        	}
        }		
	}
	
	private AttributeMap createAttributesMap(String[] propertyNames, Object[] state) {
		AttributeMap result = new LocalDataTransferObject();
		for (int i = 0; i < propertyNames.length; i++)
			result.put(propertyNames[i], state[i]);
		return result;
	}

	private void setUpdatedAttributes(PersistentObject entity, Object[] state, EntityPersister entityPersister) {
		String[] propertyNames = entityPersister.getPropertyNames();
		int versionProperty = entityPersister.getVersionProperty();
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			if (versionProperty == i) //http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4535
				continue;
			
			String name = propertyNames[i];
			
			Type type = entityPersister.getPropertyType(name);
			//не обрабатываем сущности и коллекции, т.к. их нельзя менять в перехватчиках
			if (type.isCollectionType() || type.isEntityType())
				continue;
			
			Object valueEntity = entity.getAttribute(name);
			Object value = state[i];
			if (valueEntity == null) {
				if (value != null)
					state[i] = null;
			} else if (value == null || !value.equals(valueEntity))
					state[i] = valueEntity;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.hibernate.ListenerInjector#injectListeners(javax.management.ObjectName, org.hibernate.cfg.Configuration)
	 */
	public void injectListeners(ObjectName objectName, Configuration configuration)
			throws DeploymentException {
		configuration.setListener("pre-insert", new PreInsertEventListener() {

			public boolean onPreInsert(PreInsertEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return false;				

		        setGlobalAttributes((PersistentObject) entity, event.getState(), event.getPersister().getPropertyNames());

				EntityInterceptorManagerLocator.locate().invokeOnPrePersistInterceptor((PersistentObject) entity);
				setUpdatedAttributes((PersistentObject) entity, event.getState(), event.getPersister());
				return false;
			}
			
		});
		configuration.setListener("post-insert", new PostInsertEventListener() {

			public void onPostInsert(PostInsertEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostPersistInterceptor((PersistentObject) entity);
			}
			
		});
		configuration.setListener("post-commit-insert", new PostInsertEventListener() {

			public void onPostInsert(PostInsertEvent event) {
				DatabaseAuditServiceLocator.locate().auditCreate(event);

				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostCommitPersistInterceptor((PersistentObject) entity);
			}
			
		});
		configuration.setListener("pre-delete", new PreDeleteEventListener() {

			public boolean onPreDelete(PreDeleteEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return false;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPreRemoveInterceptor((PersistentObject) entity);
				return false;
			}
			
		});
		configuration.setListener("post-delete", new PostDeleteEventListener() {

			public void onPostDelete(PostDeleteEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostRemoveInterceptor((PersistentObject) entity);
			}
			
		});
		configuration.setListener("post-commit-delete", new PostDeleteEventListener() {

			public void onPostDelete(PostDeleteEvent event) {
				DatabaseAuditServiceLocator.locate().auditRemove(event);

				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostCommitRemoveInterceptor((PersistentObject) entity);
			}
			
		});
		configuration.setListener("pre-update", new PreUpdateEventListener() {

			public boolean onPreUpdate(PreUpdateEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return false;				

		        setGlobalAttributes((PersistentObject) entity, event.getState(), event.getPersister().getPropertyNames());

				EntityInterceptorManagerLocator.locate().invokeOnPreUpdateInterceptor((PersistentObject) entity, createAttributesMap(event.getPersister().getPropertyNames(), event.getOldState()));
				setUpdatedAttributes((PersistentObject) entity, event.getState(), event.getPersister());
				return false;
			}
			
		});
		configuration.setListener("post-update", new PostUpdateEventListener() {

			public void onPostUpdate(PostUpdateEvent event) {
				Object entity = event.getEntity();
				if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostUpdateInterceptor((PersistentObject) entity, createAttributesMap(event.getPersister().getPropertyNames(), event.getOldState()));
				setUpdatedAttributes((PersistentObject) entity, event.getState(), event.getPersister());
			}
			
		});
		configuration.setListener("post-commit-update", new PostUpdateEventListener() {

			public void onPostUpdate(PostUpdateEvent event) {
				DatabaseAuditServiceLocator.locate().auditModify(event);

				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostCommitUpdateInterceptor((PersistentObject) entity, createAttributesMap(event.getPersister().getPropertyNames(), event.getOldState()));
			}
			
		});
		configuration.setListener("post-load", new PostLoadEventListener() {

			public void onPostLoad(PostLoadEvent event) {
				Object entity = event.getEntity();
		        if (!(entity instanceof PersistentObject))
		            return;				
				
				EntityInterceptorManagerLocator.locate().invokeOnPostLoadInterceptor((PersistentObject) entity);
			}
			
		});
		
		//load workaround for http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4413
		try {
			File workaround = new File(ServerConfigLocator.locate().getServerHomeDir().getAbsolutePath().concat("/mg-custom/patches/workaround_MBSA-4413.hbm.xml"));
			configuration.addFile(workaround);
			logger.info("Install patch MBSA-4413");
		} catch (Exception e) {
			logger.error("Install patch MBSA-4413 failed", e);
		}
		
		//load workaround for http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4866
		try {
			HibernateMBean service = (HibernateMBean) MBeanProxyExt.create(HibernateMBean.class, objectName);
			String dialect = service.getDialect();
			Class<?> dialectClass = ServerUtils.loadClass(dialect);
			//установка патча производится только для Firebird и Interbase
			if (org.hibernate.dialect.InterbaseDialect.class.isAssignableFrom(dialectClass)) {
				File workaround = new File(ServerConfigLocator.locate().getServerHomeDir().getAbsolutePath().concat("/mg-custom/patches/workaround_MBSA-4866.hbm.xml"));
				configuration.addFile(workaround);
				logger.info("Install patch MBSA-4866");
			}
		} catch (Exception e) {
			logger.error("Install patch MBSA-4866 failed", e);
		}			

		//setup global tenant filter
		Map<String, Object> paramTypes = new HashMap<String, Object>();
		paramTypes.put("sysClientId", new IntegerType());
		configuration.addFilterDefinition(new FilterDefinition("__mg_tenantFilter", "CLIENT_ID = :sysClientId", paramTypes));
	}

}
