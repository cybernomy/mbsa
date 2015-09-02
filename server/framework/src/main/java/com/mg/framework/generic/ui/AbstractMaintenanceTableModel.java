/*
 * AbstractMaintenanceTableModel.java
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
package com.mg.framework.generic.ui;

import java.io.Serializable;

import com.mg.framework.support.ui.widget.MaintenanceTableModel;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractMaintenanceTableModel.java,v 1.2 2006/08/29 09:00:05 safonov Exp $
 */
public abstract class AbstractMaintenanceTableModel extends AbstractTableModel implements MaintenanceTableModel {
	/**
	 * ������������� �������
	 */
	private Serializable masterKey;
	protected abstract Serializable[] doGetSelectedPrimaryKeys();
	
	/**
	 * �������� ������������� �������, �������� ����� �������� ����� ������������ ���������
	 * ������� �������
	 * 
	 * @return	������������� �������
	 */
	protected Serializable getMasterKey() {
		return masterKey;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#getCurrentPrimaryKey()
	 */
	public Serializable[] getSelectedPrimaryKeys() {
		return doGetSelectedPrimaryKeys();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setCurrentMaster(java.io.Serializable)
	 */
	public void setCurrentMaster(Serializable masterKey) {
	}

}
