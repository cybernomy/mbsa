/**
 * SetupMaintenanceTableFormImpl.java
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
package com.mg.merp.core.support.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.ColumnsTableModel;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.SetupMaintenanceTableForm;
import com.mg.framework.support.ui.widget.TableColumnInfo;

/**
 * Реализация формы настройки внешнего вида таблицы поддержки бизнес-компонентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: SetupMaintenanceTableFormImpl.java,v 1.2 2009/02/04 14:09:27 safonov Exp $
 */
public class SetupMaintenanceTableFormImpl extends DefaultDialog implements
		SetupMaintenanceTableForm {
	private List<TableColumnInfo> fieldInfos;
	protected DefaultTableController tableFields;

	public SetupMaintenanceTableFormImpl() {
		tableFields = new DefaultTableController(new ColumnsTableModel() {

			public int getRowCount() {
				return fieldInfos.size();
			}

			public Object getValueAt(int row, int column) {
				switch (column) {
				case 0:
					String title = fieldInfos.get(row).getTitle();
					//удалим перенос строк, чтобы умещалось по высоте в строке таблицы
					return title != null ? title.replaceAll("<br>", " ").replaceAll("\n", " ").replaceAll("\r", " ") : null;
				case 1:
					return fieldInfos.get(row).isVisible();
				default:
					return null;
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
			 */
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 1 ? Boolean.class : super.getColumnClass(column);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				//у обязательных полей нельзя сбросить признак видимости
				return column == 1 && !fieldInfos.get(row).isMandatory();
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#setValueAt(java.lang.Object, int, int)
			 */
			@Override
			public void setValueAt(Object value, int row, int column) {
				if (column == 1)
					fieldInfos.get(row).setVisible((Boolean) value);
			}

		});
	}

	private void setVisibleAllColumns(boolean isVisible) {
		for (TableColumnInfo info : fieldInfos)
			info.setVisible(isVisible);
		
		((ColumnsTableModel) tableFields.getModel()).fireModelChange();
	}

	protected void onActionSelectAll(WidgetEvent event) {
		setVisibleAllColumns(true);
	}

	protected void onActionDeselectAll(WidgetEvent event) {
		setVisibleAllColumns(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.SetupMaintenanceTableForm#execute(com.mg.framework.support.ui.widget.MaintenanceTableModel)
	 */
	public void execute(final MaintenanceTableModel tableModel) {
		fieldInfos = tableModel.getColumnInfos();
		addOkActionListener(new FormActionListener() {

			public void actionPerformed(FormEvent event) {
				List<String> visibleFields = new ArrayList<String>();
				for (TableColumnInfo info : fieldInfos)
					if (info.isVisible())
						visibleFields.add(info.getName());
				tableModel.setVisibleColumns(visibleFields);
			}
			
		});

		run(true);
	}

}
