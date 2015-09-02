/*
 * DisCardSelectTableModel.java
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
import com.mg.merp.discount.model.Card;
import com.mg.merp.retail.support.Messages;

/**
 * Модель таблицы "Выбор дисконтной карты"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DisCardSelectTableModel.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class DisCardSelectTableModel extends AbstractTableModel {
	
	private String[] columnNames;
	private List<Card> tableList = new ArrayList<Card>();
	private Card selectedItem;
	
	
	public DisCardSelectTableModel() {
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
		Card item = tableList.get(row);
		switch (column) {
			case 0: 
				return item.getCardNum();
			case 1:
				return item.getOwner().getUpCode().trim();
		}
		return null;
	}

	/**
	 * @return the tableList
	 */
	public List<Card> getTableList() {
		return this.tableList;
	}

	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(List<Card> tableList) {
		this.tableList = tableList;
		fireModelChange();
	}
	
	private String[] getColumnNames() {
		Messages msg = Messages.getInstance();
		return new String[] {msg.getMessage(Messages.DIS_CARD_SELECT_NUMBER), msg.getMessage(Messages.DIS_CARD_SELECT_OWNER)};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
		if (rows.length == 0)
			selectedItem = null;
		else 
			selectedItem = tableList.get(rows[0]);
	}
	
	public Card getSelectedItem() {
		return this.selectedItem;
	}

}
