/*
 * DefaultMaintenanceEntityListTableModel.java
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
import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableColumnInfo;

/**
 * Стандартная модель поддержки списка объектов сущностей
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMaintenanceEntityListTableModel.java,v 1.4 2009/02/09 14:30:35 safonov Exp $
 */
public abstract class DefaultMaintenanceEntityListTableModel<E extends PersistentObject> extends
		DefaultEntityListTableModel<E> implements MaintenanceTableModel {

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#getCurrentPrimaryKey()
	 */
	public Serializable[] getSelectedPrimaryKeys() {
		E[] selected = getSelectedEntities();
		Serializable[] result = new Serializable[selected.length];
		for (int i = 0; i < selected.length; i++)
			result[i] = (Serializable) selected[i].getPrimaryKey();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setCurrentMaster(java.io.Serializable)
	 */
	public void setCurrentMaster(Serializable masterKey) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService<?, ?> service) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#getColumnInfos()
	 */
	public List<TableColumnInfo> getColumnInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setVisibleColumns(java.util.List)
	 */
	public void setVisibleColumns(List<String> visibleColumns) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#getPrimaryKey()
	 */
	public Serializable getPrimaryKey(int row) {
		return (Serializable) getEntityList().get(row).getPrimaryKey();
	}

}
