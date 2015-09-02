/*
 * ColumnsTableModel.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.support.ui.widget.TableColumnModel;

/**
 * Базовый класс модели таблицы поддерживающий список столбцов установленных в описателе
 * форм
 * 
 * @author Oleg V. Safonov
 * @version $Id: ColumnsTableModel.java,v 1.1 2007/05/28 13:31:12 safonov Exp $
 */
public abstract class ColumnsTableModel extends AbstractTableModel {
	private String[] columnsName;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doSetColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
	 */
	@Override
	protected void doSetColumns(TableColumnModel[] columns) {
		this.columnsName = new String[columns.length];
		for (int i = 0; i < columns.length; i++)
			this.columnsName[i] = columns[i].getTitle();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnsName[column];
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnsName.length;
	}

}
