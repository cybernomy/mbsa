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
 * ������-��������� ��������� ������ ��������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditSetupServiceLocal.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public interface DatabaseAuditSetupServiceLocal extends
		DataBusinessObjectService<DatabaseAuditSetup, Integer> {

	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/core/DatabaseAuditSetup";
	
	/**
	 * �������� ��������� ������ ���������
	 * 
	 * @return	����� ���������
	 */
	List<EntityAuditItem> loadEntityAudit();

	/**
	 * �������� ��������� ������ ��������� ��������
	 * 
	 * @param entityName	��� ��������
	 * @return	����� ���������
	 */
	List<PropertyAuditItem> loadPropertyAudit(String entityName);
	
	/**
	 * ���������� ������� ������ �������� ��������
	 * 
	 * @param entityName	��� ��������
	 * @param audit	������� ������
	 */
	void setAuditCreate(String entityName, boolean audit);
	
	/**
	 * ���������� ������� ������ ��������� ��������
	 * 
	 * @param entityName	��� ��������
	 * @param audit	������� ������
	 */
	void setAuditModify(String entityName, boolean audit);
	
	/**
	 * ���������� ������� ������ �������� ��������
	 * 
	 * @param entityName	��� ��������
	 * @param audit	������� ������
	 */
	void setAuditRemove(String entityName, boolean audit);
	
	/**
	 * ���������� ������� ������ ��������� �������� ��������
	 * 
	 * @param entityName	��� ��������
	 * @param propertyName	��� ��������
	 * @param audit	������� ������
	 */
	void setAuditModify(String entityName, String propertyName, boolean audit);
	
}
