/**
 * DatabaseAuditSetupServiceLocal.java
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
package com.mg.merp.core;

import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.DatabaseAuditSetup;

/**
 * Бизнес-компонент настройки аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditSetupServiceLocal.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public interface DatabaseAuditSetupServiceLocal extends
		DataBusinessObjectService<DatabaseAuditSetup, Integer> {

	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/core/DatabaseAuditSetup";
	
	/**
	 * загрузка настройки аудита сущностей
	 * 
	 * @return	аудит сущностей
	 */
	List<EntityAuditItem> loadEntityAudit();

	/**
	 * загрузка настройки аудита атрибутов сущности
	 * 
	 * @param entityName	имя сущности
	 * @return	аудит атрибутов
	 */
	List<PropertyAuditItem> loadPropertyAudit(String entityName);
	
	/**
	 * установить признак аудита создания сущности
	 * 
	 * @param entityName	имя сущности
	 * @param audit	признак аудита
	 */
	void setAuditCreate(String entityName, boolean audit);
	
	/**
	 * установить признак аудита изменения сущности
	 * 
	 * @param entityName	имя сущности
	 * @param audit	признак аудита
	 */
	void setAuditModify(String entityName, boolean audit);
	
	/**
	 * установить признак аудита удаления сущности
	 * 
	 * @param entityName	имя сущности
	 * @param audit	признак аудита
	 */
	void setAuditRemove(String entityName, boolean audit);
	
	/**
	 * установить признак аудита изменения атрибута сущности
	 * 
	 * @param entityName	имя сущности
	 * @param propertyName	имя атрибута
	 * @param audit	признак аудита
	 */
	void setAuditModify(String entityName, String propertyName, boolean audit);
	
}
