/*
 * FeatureLinkServiceLocal.java
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

import java.util.Map;

import com.mg.merp.core.model.FeatureLink;

/**
 * Бизнес-компонент "Связь пользовательских полей с бизнес-компонентами"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: FeatureLinkServiceLocal.java,v 1.3 2007/01/25 15:40:10 safonov Exp $
 */
public interface FeatureLinkServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<FeatureLink, Integer>
{

	/**
	 * загрузка значений пользовательских полей
	 * 
	 * @param classId	идентификатор бизнес-компонента
	 * @param entityId	идентификатор сущности
	 * @return	значения пользовательских полей
	 */
	Map<String, Object> loadValues(int classId, Object entityId);
	
	/**
	 * сохранение значений пользовательских полей
	 * 
	 * @param fieldsValues	значения пользовательских полей
	 * @param classId		идентификатор бизнес-компонента
	 * @param entityId		идентификатор сущности
	 */
	void storeValues(Map<String, Object> fieldsValues, int classId, Object entityId);
	
	/**
	 * удаление значений пользовательских полей
	 * 
	 * @param classId	идентификатор бизнес-компонента
	 * @param entityId	идентификатор сущности
	 */
	void removeValues(int classId, Object entityId);
	
}
