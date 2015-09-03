/**
 * DatabaseAuditSetupServiceBean.java
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.DatabaseAuditSetupServiceLocal;
import com.mg.merp.core.EntityAuditItem;
import com.mg.merp.core.PropertyAuditItem;
import com.mg.merp.core.model.DatabaseAuditSetup;

/**
 * Реализация бизнес-компонента настройки аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditSetupServiceBean.java,v 1.4 2008/03/05 08:55:27 sharapov Exp $
 */
@Stateless(name="merp/core/DatabaseAuditSetupService")
public class DatabaseAuditSetupServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<DatabaseAuditSetup, Integer> implements
		DatabaseAuditSetupServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#loadEntityAudit()
	 */
	@SuppressWarnings("unchecked")
	@PermitAll
	public List<EntityAuditItem> loadEntityAudit() {
		Map<String, EntityAuditItem> entityMap = new HashMap<String, EntityAuditItem>();
		//load metadata from ORM datawarehouse
		Map<String, ClassMetadata> map = ((Session) ServerUtils.getPersistentManager().getDelegate()).getSessionFactory().getAllClassMetadata();
		ApplicationDictionary ad = ApplicationDictionaryLocator.locate();
		for (String entityName : map.keySet()) {
			String i18nName = null;
			try {
				FieldMetadata metadata = ad.getFieldMetadata(ReflectionUtils.getClassReflectionMetadata(ServerUtils.loadClass(entityName)));
				if (metadata != null)
					i18nName = metadata.getShortLabel();
			} catch (Exception e) {
				getLogger().error("Load metadata failed", e);
			}
			EntityAuditItem item = new EntityAuditItem(i18nName, entityName, false, false, false);
			entityMap.put(entityName, item);
		}
		//load setup from database
		List<DatabaseAuditSetup> setupList = findByCriteria(Restrictions.isNull("PropertyName"), Restrictions.disjunction(Restrictions.isNotNull("AuditCreate"), Restrictions.isNotNull("AuditModify"), Restrictions.isNotNull("AuditRemove")));
		for (DatabaseAuditSetup auditSetup : setupList) {
			EntityAuditItem item = entityMap.get(auditSetup.getAuditedEntityName());
			if (item != null) {
				if (auditSetup.getAuditCreate() != null)
					item.setAuditCreate(auditSetup.getAuditCreate());
				if (auditSetup.getAuditModify() != null)
					item.setAuditModify(auditSetup.getAuditModify());
				if (auditSetup.getAuditRemove() != null)
					item.setAuditRemove(auditSetup.getAuditRemove());
			} else {
				getLogger().warn(String.format("Entity %s is not deployed on server", auditSetup.getEntityName().trim()));
			}
		}
		List<EntityAuditItem> result = new ArrayList<EntityAuditItem>(entityMap.values());
		Collections.sort(result, new Comparator<EntityAuditItem>() {

			public int compare(EntityAuditItem o1, EntityAuditItem o2) {
				return o1.getEntityName().compareTo(o2.getEntityName());
			}
			
		});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#loadPropertyAudit(java.lang.String)
	 */
	@PermitAll
	public List<PropertyAuditItem> loadPropertyAudit(String entityName) {
		Map<String, PropertyAuditItem> propertyMap = new HashMap<String, PropertyAuditItem>();
		//load metadata from ORM datawarehouse
		ApplicationDictionary ad = ApplicationDictionaryLocator.locate();
		ClassMetadata metadata = ((Session) ServerUtils.getPersistentManager().getDelegate()).getSessionFactory().getClassMetadata(entityName);
		for (String propertyName : metadata.getPropertyNames()) {
			if (!metadata.getPropertyType(propertyName).isCollectionType()) {
				String i18nName = null;
				try {
					Class<?> clazz = ServerUtils.loadClass(entityName);
					if (PersistentObject.class.isAssignableFrom(clazz)) {
						FieldMetadata fldMetadata = ad.getFieldMetadata(clazz.asSubclass(PersistentObject.class), propertyName);
						if (fldMetadata != null)
							i18nName = fldMetadata.getShortLabel();
					}
				} catch (Exception e) {
					getLogger().error("Load metadata failed", e);
				}
				PropertyAuditItem item = new PropertyAuditItem(i18nName, propertyName, false);
				propertyMap.put(propertyName, item);
			}
		}
		//load setup from database
		List<DatabaseAuditSetup> setupList = findByCriteria(Restrictions.eq("AuditedEntityName", entityName), Restrictions.isNotNull("PropertyName"));
		for (DatabaseAuditSetup auditSetup : setupList) {
			PropertyAuditItem item = propertyMap.get(auditSetup.getPropertyName());
			if (item != null) {
				item.setAudit(auditSetup.getAuditModify());//для свойств аудит только на изменения
			} else {
				getLogger().warn(String.format("Entity %s is not contain property %s", entityName, auditSetup.getPropertyName()));
			}
		}
		List<PropertyAuditItem> result = new ArrayList<PropertyAuditItem>(propertyMap.values());
		Collections.sort(result, new Comparator<PropertyAuditItem>() {

			public int compare(PropertyAuditItem o1, PropertyAuditItem o2) {
				return o1.getPropertyName().compareTo(o2.getPropertyName());
			}
			
		});
		return result;
	}

	private DatabaseAuditSetup loadAuditSetup(String entityName, String auditName) {
		List<DatabaseAuditSetup> setupList = findByCriteria(Restrictions.eq("AuditedEntityName", entityName), Restrictions.isNull("PropertyName"), Restrictions.isNotNull(auditName));
		if (setupList.isEmpty())
			return null;
		else
			return setupList.get(0); //должен быть всегда один
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#setAuditCreate(java.lang.String, boolean)
	 */
	public void setAuditCreate(String entityName, boolean audit) {
		DatabaseAuditSetup auditSetup = loadAuditSetup(entityName, "AuditCreate");
		if (auditSetup == null) {
			auditSetup = initialize();
			auditSetup.setAuditedEntityName(entityName);
			auditSetup.setAuditCreate(audit);
			create(auditSetup);
		} else {
			auditSetup.setAuditCreate(audit);
			store(auditSetup);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#setAuditModify(java.lang.String, boolean)
	 */
	public void setAuditModify(String entityName, boolean audit) {
		if (audit)
			OrmTemplate.getInstance().bulkUpdateByNamedQuery("Core.DatabaseAuditSetup.deletePropertyAuditModify", "entityName", entityName);
		
		DatabaseAuditSetup auditSetup = loadAuditSetup(entityName, "AuditModify");
		if (auditSetup == null) {
			auditSetup = initialize();
			auditSetup.setAuditedEntityName(entityName);
			auditSetup.setAuditModify(audit);
			create(auditSetup);
		} else {
			auditSetup.setAuditModify(audit);
			store(auditSetup);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#setAuditModify(java.lang.String, java.lang.String, boolean)
	 */
	public void setAuditModify(String entityName, String propertyName,
			boolean audit) {
		DatabaseAuditSetup auditSetup = loadAuditSetup(entityName, "AuditModify");
		//удалим запись отвечающую за аудит всех атрибутов объекта, т.к. создаем для конкретного атрибута
		if (auditSetup != null)
			erase(auditSetup);
		
		//ищем запись для нужного атрибута
		List<DatabaseAuditSetup> auditList = findByCriteria(Restrictions.eq("AuditedEntityName", entityName), Restrictions.eq("PropertyName", propertyName));
		if (!auditList.isEmpty())
			auditSetup = auditList.get(0);
		else
			auditSetup = null;
		
		if (auditSetup == null) {
			auditSetup = initialize();
			auditSetup.setAuditedEntityName(entityName);
			auditSetup.setPropertyName(propertyName);
			auditSetup.setAuditModify(audit);
			create(auditSetup);
		} else {
			auditSetup.setAuditModify(audit);
			store(auditSetup);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.core.DatabaseAuditSetupServiceLocal#setAuditRemove(java.lang.String, boolean)
	 */
	public void setAuditRemove(String entityName, boolean audit) {
		DatabaseAuditSetup auditSetup = loadAuditSetup(entityName, "AuditRemove");
		if (auditSetup == null) {
			auditSetup = initialize();
			auditSetup.setAuditedEntityName(entityName);
			auditSetup.setAuditRemove(audit);
			create(auditSetup);
		} else {
			auditSetup.setAuditRemove(audit);
			store(auditSetup);
		}
	}

}
