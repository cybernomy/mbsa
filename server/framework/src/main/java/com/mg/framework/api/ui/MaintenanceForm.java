/*
 * MaintenanceForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;

/**
 * ����������� ����� ��������� ��� ������-����������. ����������� ����������� ������������� ��������
 * ��� ��������-��������� ������-���������� �������, ��������, �����������.
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceForm.java,v 1.2 2008/03/07 12:53:39 safonov Exp $
 */
public interface MaintenanceForm extends Form {

	/**
	 * ����� ���������� �������� � ����� ���������
	 */
	enum RefreshMode {
		/**
		 * ��������� ����� �������� � ���������
		 */
		AFTER_CREATE,
		/**
		 * ��������� ����� ��������� � ���������
		 */
		AFTER_STORE
	}

	/**
	 * �������� ��������� �� ������� ����� ���������
	 * 
	 * @param listener	���������
	 */
	void addMaintenanceFormActionListener(final MaintenanceFormActionListener listener);
	
	/**
	 * ������� ��������� �� ������� ����� ���������
	 * 
	 * @param listener	���������
	 */
	void removeMaintenanceFormActionListener(final MaintenanceFormActionListener listener);

	/**
	 * ������ ����� ���������
	 * 
	 * @param service	������-���������
	 * @param entity	������-��������
	 * @param action	��������
	 */
	void execute(final DataBusinessObjectService<PersistentObject, Serializable> service,
			final PersistentObject entity, final MaintenanceAction action);

}
