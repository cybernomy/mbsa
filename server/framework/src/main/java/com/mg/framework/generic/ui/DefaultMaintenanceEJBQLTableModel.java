/*
 * DefaultMaintenanceEJBQLTableModel.java
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

package com.mg.framework.generic.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableColumnInfo;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.MiscUtils;

/**
 * Стандартная реализация модели таблицы поддержки для отображения данных полученных с помощью языка
 * объектных запросов (EJBQL)
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMaintenanceEJBQLTableModel.java,v 1.5 2009/02/09 14:30:35 safonov Exp $
 */
public abstract class DefaultMaintenanceEJBQLTableModel extends DefaultEJBQLTableModel
		implements MaintenanceTableModel {
	private CustomFieldsManager customFieldsManager;
	protected Map<Integer, Map<String, Object>> customFieldValues = new HashMap<Integer, Map<String,Object>>();
	protected Map<Integer, String> customFieldNames;
	protected DataBusinessObjectService<?, ?> service;
	protected List<TableColumnInfo> columnInfos = null;
	protected List<String> visibleColumnNames = null;
	/**
	 * идентификатор мастера
	 */
	private Serializable masterKey;

	private String getCustomFieldName(int column) {
		if (customFieldNames == null) {
			customFieldNames = new HashMap<Integer, String>();
			TableColumnModel[] columnModels = getColumnModel();
			for (int i = 0; i < columnModels.length; i++) {
				String fieldName = columnModels[i].getFieldName();
				if (fieldName.startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX))
					customFieldNames.put(i, fieldName);
			}
		}
		
		return customFieldNames.get(column);
	}

	private Object getCustomFieldNameValue(int row, int column, String fieldName) {
		Map<String, Object> customFiledsTuple = customFieldValues.get(row);
		if (customFiledsTuple == null) {
			//System.out.println("load custom files for row: " + row);
			customFiledsTuple = getCustomFieldsManager().loadValues(service, getRowList().get(row)[getPrimaryKeyFieldIndex()]);
			customFieldValues.put(row, customFiledsTuple);
		}
		Object result = customFiledsTuple.get(fieldName);
		if (result instanceof Object[])
			result = MiscUtils.getArrayCustomFieldTextRepresentation((Object[]) result, getColumnModel()[column].getMetadata());
		return result;
	}

	protected CustomFieldsManager getCustomFieldsManager() {
		if (customFieldsManager == null)
			customFieldsManager = CustomFieldsManagerLocator.locate();
		return customFieldsManager;
	}
	
	/**
	 * возвращает индекс столбца первичного ключа, должен быть переопределен в классе наследнике если отличается от 0,
	 * значение находится в диапазоне от 0 до количество столбцов в браузере - 1
	 * 
	 * @return индекс
	 */
	protected int getPrimaryKeyFieldIndex() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#getPrimaryKey()
	 */
	public Serializable getPrimaryKey(int row) {
		return (Serializable) getRowList().get(row)[getPrimaryKeyFieldIndex()];
	}

	/**
	 * получить идентификатор мастера, значение будет доступно после срабатывания слушателя
	 * таблицы мастера
	 * 
	 * @return	идентификатор мастера
	 */
	protected Serializable getMasterKey() {
		return masterKey;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getSelectedPrimaryKeys()
	 */
	@Override
	public Serializable[] getSelectedPrimaryKeys() {
		Serializable[] result = new Serializable[selectedRows.length];
		for (int i = 0; i < selectedRows.length; i++)
			result[i] = (Serializable) getRowList().get(selectedRows[i])[getPrimaryKeyFieldIndex()];
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setCurrentMaster(java.io.Serializable)
	 */
	public void setCurrentMaster(Serializable masterKey) {
		this.masterKey = masterKey;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setRowList(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setRowList(List rowList) {
		customFieldValues.clear();
		super.setRowList(rowList);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#fireTableStructureChanged()
	 */
	@Override
	public void fireTableStructureChanged() {
		if (customFieldNames != null)
			customFieldNames.clear();
		customFieldNames = null;
		super.fireTableStructureChanged();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		String customFieldName = getCustomFieldName(column);
		if (customFieldName != null && service != null)
			return getCustomFieldNameValue(row, column, customFieldName);
		else
			return super.getValueAt(row, column);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#doCreateFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> doCreateFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = new LinkedHashSet<TableEJBQLFieldDef>();
		Set<TableEJBQLFieldDef> defaultFieldDefs = getDefaultFieldDefsSet();
		for (TableEJBQLFieldDef fldDef : defaultFieldDefs) {
			if (fldDef.isMandatory()) {
				result.add(fldDef);
				continue;
			}
			
			if (visibleColumnNames != null && visibleColumnNames.indexOf(fldDef.getAlias()) != -1
					|| visibleColumnNames == null && !fldDef.getAlias().startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX)) {
				result.add(fldDef);
				continue;
			}
		}

		//создадим описатель видимых полей
		if (columnInfos == null) {
			columnInfos = new ArrayList<TableColumnInfo>();
			for (TableEJBQLFieldDef fldDef : defaultFieldDefs) {
				//если нет метаданных то используем псевдоним как заголовок
				String title = fldDef.getFieldMetadata() == null ? fldDef.getAlias() : fldDef.getFieldMetadata().getHeader();
				//если не устанавливали видимость полей и поле не является пользовательским, то поля видны
				//пользовательские поля делаем невидимыми по умолчанию, до тех пор пока пользователь не отметит
				//их как видимые
				boolean isVisible = visibleColumnNames == null ? !fldDef.getAlias().startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX) : visibleColumnNames.indexOf(fldDef.getAlias()) != -1;
				columnInfos.add(new TableColumnInfo(fldDef.getAlias(), title, isVisible, fldDef.isMandatory()));
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService<?, ?> service) {
		this.service = service;
	}

	/**
	 * @return the visibleColumns
	 */
	public List<TableColumnInfo> getColumnInfos() {
		return columnInfos == null ? null : Collections.unmodifiableList(columnInfos);
	}

	/**
	 * @param visibleColumnNames the visibleColumns to set
	 */
	public void setVisibleColumns(List<String> visibleColumnNames) {
		if (visibleColumnNames == null || visibleColumnNames.isEmpty())
			return;
		
		this.visibleColumnNames = visibleColumnNames;

		//перегрузим таблицу если только была заполнена, иначе просто установим список видимых полей
		//при формировании таблицы они будут учтены
		if (columnInfos != null) {
			for (TableColumnInfo info : columnInfos)
				info.setVisible(visibleColumnNames.indexOf(info.getName()) != -1);

			fireTableStructureChanged();
			load();
		}
	}

}
