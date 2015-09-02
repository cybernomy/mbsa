/*
 * CustomerSelectTableModel.java
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
package com.mg.merp.retail.support.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.retail.support.Messages;

/**
 * Модель таблицы "Выбор покупателя"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomerSelectTableModel.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class CustomerSelectTableModel extends AbstractTableModel {
	
	private String[] columnNames;
	private List<CustomerSelectTableModelItem> tableList = new ArrayList<CustomerSelectTableModelItem>();
	private Contractor selectedCustomer;
	private String disCardOwner;
	private String disCardUser;
	private Messages msg = Messages.getInstance();
	
	
	public CustomerSelectTableModel() {
		disCardOwner = msg.getMessage(Messages.CUSTOM_SELECT_DIS_CARD_OWNER);
		disCardUser = msg.getMessage(Messages.CUSTOM_SELECT_DIS_CARD_USER);
		columnNames = getColumnNames();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return tableList.size();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		CustomerSelectTableModelItem item = tableList.get(row);
		switch (column) {
			case 0: 
				return item.getContractor().getUpCode().trim();
			case 1:
				return item.isDisCardOwner() == true ? disCardOwner : disCardUser;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
		if (rows.length == 0)
			selectedCustomer = null;
		else 
			selectedCustomer = tableList.get(rows[0]).getContractor();
	}

	private String[] getColumnNames() {
		return new String[] {msg.getMessage(Messages.CUSTOM_SELECT_PARTNER), msg.getMessage(Messages.CUSTOM_SELECT_STATUS)};
	}

	/**
	 * @return the tableList
	 */
	public List<CustomerSelectTableModelItem> getTableList() {
		return this.tableList;
	}

	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(List<CustomerSelectTableModelItem> tableList) {
		this.tableList = tableList;
		fireModelChange();
	}

	/**
	 * @return the selectedCustomer
	 */
	public Contractor getSelectedCustomer() {
		return this.selectedCustomer;
	}
	
}
