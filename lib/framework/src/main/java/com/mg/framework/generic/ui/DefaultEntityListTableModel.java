/*
 * DefaultEntityListTableModel.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.StringUtils;

/**
 * модель предназначена для отображения списка объектов сущностей
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultEntityListTableModel.java,v 1.8 2008/07/28 14:38:14 safonov Exp $
 */
public abstract class DefaultEntityListTableModel<E extends PersistentObject> extends AbstractTableModel {
	protected List<E> entityList = new ArrayList<E>();
	private E[] selectedEntities;
	private TableColumnModel[] columnsModel = new TableColumnModel[0];
	private Map<String, FieldMetadata> columnsDef = new HashMap<String, FieldMetadata>();
	private Class<E> entityClass = ReflectionUtils.getGenericClass(getClass(), 0);

	protected List<E> getEntityList() {
		return entityList;
	}
	
	protected void doSetColumns(TableColumnModel[] columns) {
		columnsModel = columns;
		ApplicationDictionary appDic = ApplicationDictionaryLocator.locate();
		for (TableColumnModel column : columnsModel) {
			if (StringUtils.stringNullOrEmpty(column.getTitle())) {
				FieldMetadata metadata = columnsDef.get(column.getFieldName());
				if (metadata == null) {
					metadata = appDic.getFieldMetadata(ReflectionUtils.getPropertyReflectionMetadata(entityClass, column.getFieldName()));
					columnsDef.put(column.getFieldName(), metadata);
				}
				column.setMetadata(metadata);
			}
		}
	}
	
	/**
	 * установка списка атрибутов для вывода
	 * 
	 * @param columnsName	список атрибутов для вывода в таблице
	 */
	public void setColumns(String[] columnsName) {
		columnsModel = new TableColumnModel[columnsName.length];
		int i = 0;
		ApplicationDictionary appDic = ApplicationDictionaryLocator.locate();
		for (String columnName : columnsName) {
			FieldMetadata metadata = columnsDef.get(columnName);
			if (metadata == null) {
				metadata = appDic.getFieldMetadata(ReflectionUtils.getPropertyReflectionMetadata(entityClass, columnName));
				columnsDef.put(columnName, metadata);
			}
			columnsModel[i++] = new TableColumnModel(columnName, metadata);
		}
	}
	
	/**
	 * установка списка сущностей
	 * 
	 * @param entityList	список сущностей
	 * @param columnsName	список атрибутов для вывода в таблице
	 */
	public void setEntityList(List<E> entityList, String[] columnsName) {
		this.entityList = entityList;
		setColumns(columnsName);
	}
	
	/**
	 * установка списка сущностей
	 * 
	 * @param entityList	список сущностей
	 */
	public void setEntityList(List<E> entityList) {
		this.entityList = entityList;
		fireModelChange();
	}
	
	/**
	 * получить список отмеченных объектов-сущностей
	 * 
	 * @return	список сущностей
	 */
	@SuppressWarnings("unchecked")
	public E[] getSelectedEntities() {
		if (selectedEntities == null)
			return (E[]) Array.newInstance(entityClass, 0);
		else
			return selectedEntities;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return getEntityList().size();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnsModel.length;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnsModel[column].getTitle();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		TableColumnModel columnModel = columnsModel[column];
		return getEntityList().get(row).getAttribute(columnModel.getFieldName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getDecoratorValueAt(int row, int column) {
		TableColumnModel columnModel = columnsModel[column];
		return columnModel.getDecoratorValue(getEntityList().get(row).getAttribute(columnModel.getFieldName()));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
	 */
	@Override
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void setSelectedRows(int[] rows) {
		if (rows.length != 0) {
			//selectedEntities = new T[rows.length];
			List<E> entities = new ArrayList<E>();
			for (int row : rows)
				entities.add(getEntityList().get(row));
			selectedEntities = entities.toArray((E[]) Array.newInstance(entityClass, entities.size()));
		}
		else
			selectedEntities = (E[]) Array.newInstance(entityClass, 0); //create empty array
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getFieldMetadata(int)
	 */
	@Override
	public FieldMetadata getColumnMetadata(int column) {
		return columnsModel[column].getMetadata();
	}

}
