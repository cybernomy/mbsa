/*
 * CustomFieldsManager.java
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
package com.mg.framework.api.metadata;

import java.util.Map;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;


/**
 * �������� ���������� ����������������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManager.java,v 1.3 2008/12/23 09:09:50 safonov Exp $
 */
public interface CustomFieldsManager {
	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=CustomFieldsManagerService";

	/**
	 * ��� ������������ ������������� ������� ��� ����������� ���������������� �����
	 * � ������ ���������
	 */
	final static String CUSTOM_FIELDS_AREA_MACROS = "com.mg.framework.CustomFieldsMacros";
	
	/**
	 * ������� ������������ ����������������� ��������
	 */
	final static String CUSTOM_FIELD_NAME_PREFIX = "FEAT$";
	
	/**
	 * �������� ���������� ������ �������� ���� ������
	 */
	final static String INDEX_DELIMITER = "$";

	/**
	 * �������� ���������� ���������������� �����
	 * 
	 * @param service	������-���������
	 * @return	���������� ���������������� �����
	 */
	FieldMetadata[] loadFieldsMetadata(DataBusinessObjectService<?, ?> service);

	/**
	 * ��������� ���� ������� ��� ����������� ���������������� ����� � ������ ���������
	 * 
	 * @param service	������-���������
	 * @return	���� �������
	 */
	String generateMaintenanceArea(DataBusinessObjectService<?, ?> service);

	/**
	 * ���������� �������� ���������������� �����
	 * 
	 * @param fieldsValues	��������
	 * @param service		������-���������
	 * @param key			������������� ��������
	 */
	void storeValues(Map<String, Object> fieldsValues, DataBusinessObjectService<?, ?> service, Object key);
	
	/**
	 * �������� �������� ���������������� �����
	 * 
	 * @param service	������-���������
	 * @param key		������������� ��������
	 * @return	��������
	 */
	Map<String, Object> loadValues(DataBusinessObjectService<?, ?> service, Object key);
	
	/**
	 * �������� ����������� ��� �������� ���������������� �����
	 * 
	 * @param service	������-���������
	 * @param key		������������� ��������
	 * @return	�����������
	 */
	EntityCustomFieldsStorage createStorage(DataBusinessObjectService<?, ?> service, Object key);
	
	/**
	 * ���������� �������� ���������������� �����
	 * 
	 * @param storage	�����������
	 * @param service	������-���������
	 * @param key		������������� ��������
	 */
	void storeValues(EntityCustomFieldsStorage storage, DataBusinessObjectService<?, ?> service, Object key);
	
	/**
	 * �������� ���������������� �����
	 * 
	 * @param service	������-���������
	 * @param key		������������� ��������
	 */
	void removeValues(DataBusinessObjectService<?, ?> service, Object key);
	
	/**
	 * ����������� ���������������� �����
	 * 
	 * @param serviceSrc	������-��������� ��������
	 * @param entitySrc		�������� ��������
	 * @param serviceDst	������-��������� ��������
	 * @param entityDst		�������� ��������
	 */
	void cloneValues(DataBusinessObjectService<?, ?> serviceSrc, PersistentObject entitySrc,
			DataBusinessObjectService<?, ?> serviceDst, PersistentObject entityDst);
	
}
