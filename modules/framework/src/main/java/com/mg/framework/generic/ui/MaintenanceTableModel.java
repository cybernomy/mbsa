/*
 * MaintenanceTableModel.java
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

/**
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableModel.java,v 1.2 2006/10/21 10:41:59 safonov Exp $
 */
public abstract class MaintenanceTableModel extends AbstractTableModel {

	protected abstract void doRefresh();
	
	protected abstract Serializable doGetCurrentPrimaryKey();
	
	/**
	 * Return primary key of current row
	 * 
	 * @return	primary key
	 */
	public Serializable getCurrentPrimaryKey() {
		return doGetCurrentPrimaryKey();
	}
	
	/**
	 * Refresh model
	 *
	 */
	public void refresh() {
		doRefresh();
	}
	
}
