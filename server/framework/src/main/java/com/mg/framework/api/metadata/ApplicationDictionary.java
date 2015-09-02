/*
 * ApplicationDictionary.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api.metadata;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;

/**
 * ����������� ���������� �������
 * 
 * @author Oleg V. Safonov
 * $Id: ApplicationDictionary.java,v 1.7 2008/03/03 13:11:02 safonov Exp $
 */
public interface ApplicationDictionary {
	
	/**
	 * �������� ����� �� �����
	 * 
	 * @param name	��� ������
	 * @return		�����
	 */
    Domain getDomain(String name);
    
    /**
     * �������� ������� ������ �� �����
     * 
     * @param name	��� �������� ������
     * @return		������� ������
     */
    DataItem getDataItem(String name);
    
    /**
     * �������� ���������� �������� �������-��������
     * 
     * @param entityClazz	����� �������-��������
     * @param propertyName	��� ��������
     * @return				���������� ��������
     */
    FieldMetadata getFieldMetadata(Class<? extends PersistentObject> entityClazz, String propertyName);
    
    /**
     * �������� ���������� ��������
     * 
     * @param propertyMetadata	�������� ��������
     * @return					���������� ��������
     */
    FieldMetadata getFieldMetadata(ReflectionMetadata propertyMetadata);

    /**
     * ������� ����� ������������� ��� ������-����������
     * 
     * @param service	������-���������
     * @param formName	��� �����, ����� ���� <code>null</code> ��� ������ �������
     * @return			�����
     */
    com.mg.framework.api.ui.Form getMaintenaceForm(DataBusinessObjectService<?, ?> service, String formName);
    
    /**
     * ������� ����� ������ ��� ������-����������
     * 
     * @param service	������-���������
     * @param formName	��� �����, ����� ���� <code>null</code> ��� ������ �������
     * @return			�����
     */
    com.mg.framework.api.ui.Form getBrowseForm(DataBusinessObjectService<?, ?> service, String formName);

    /**
     * ������� ���� ����������
     * 
     * @param windowName	��� ���� ����������
     * @return	����
     */
    com.mg.framework.api.ui.Form getWindow(String windowName);
    
    /**
     * �������� ��� ���� ����������
     */
    void invalidateWindowCache();
    
    /**
     * ������� ������-���������
     * 
     * @param name	��� ������ ����������
     * @return		������ ���������
     */
    BusinessObjectService getBusinessService(String name);
}
