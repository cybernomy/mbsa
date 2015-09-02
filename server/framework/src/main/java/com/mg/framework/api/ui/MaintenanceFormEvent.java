/*
 * MaintenanceFormEvent.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.orm.PersistentObject;

/**
 * ������� ����� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceFormEvent.java,v 1.1 2006/05/10 13:19:49 safonov Exp $
 */
public class MaintenanceFormEvent extends FormEvent {
	private PersistentObject entity;
	
	/**
	 * �������� ������� ����� ���������
	 * 
	 * @param source	�����
	 * @param entity	������ ��������
	 */
	public MaintenanceFormEvent(Form source, PersistentObject entity) {
		super(source);
		this.entity = entity;
	}
	
	/**
	 * ���������� ������ �������� �������������� � ����� ���������
	 * 
	 * @return	������ ��������
	 * @throws	IllegalStateException ���� ������ �������� <code>null</code>
	 */
	public PersistentObject getEntity() {
		if (entity == null)
			throw new IllegalStateException("Entity is null");
		return entity;
	}
}
