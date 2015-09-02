/*
 * DefaultEJBQLTableModel.java
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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.StringUtils;

/**
 * ����������� ���������� ������ ������� ��� ����������� ������ ���������� � ������� �����
 * ��������� �������� (EJBQL)
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultEJBQLTableModel.java,v 1.11 2008/12/23 09:26:01 safonov Exp $
 */
public class DefaultEJBQLTableModel extends AbstractTableModel {
	protected List<Object[]> rowList = new ArrayList<Object[]>();
	private TableColumnModel[] columnsModel = new TableColumnModel[0];
	private Map<String, FieldMetadata> columnsDef = new LinkedHashMap<String, FieldMetadata>();
	private boolean isColumnModelCreated = false;
	private Set<TableEJBQLFieldDef> fieldDefs = null;
	protected int[] selectedRows = new int[0];
	private OrmTemplate ormTemplate;

	protected TableColumnModel[] getColumnModel() {
		if (!isColumnModelCreated && columnsModel.length == 0) {
			//���� ������ � ��������� ������, �� ��������� columnsDef � ������� ������ ��������
			createColumnsModel();
			isColumnModelCreated = true;
			columnsModel = new TableColumnModel[columnsDef.size()];
			if (columnsDef.size() != 0) {
				int index = 0;
				for (Map.Entry<String, FieldMetadata> entry : columnsDef.entrySet()) {
					columnsModel[index++] = new TableColumnModel(entry.getKey(), entry.getValue());
				}
			}
		}
		return columnsModel;
	}

	protected OrmTemplate getOrmTemplate() {
		if (ormTemplate == null)
			ormTemplate = OrmTemplate.getInstance();
		return ormTemplate;
	}

	/**
	 * �������� ������ ������, ������ ������� �������� ������� �������
	 * 
	 * @return ������ ������
	 */
	protected List<Object[]> getRowList() {
		return rowList;
	}

	/**
	 * �������� ������ ��������, ����� ���� ������������� � ���������� ��� �������� ������
	 * � ������ runtime
	 *
	 */
	protected void createColumnsModel() {
		addColumnDefs(getFieldDefsSet());
	}
	
	/**
	 * ��������� �������� � ������ ��������, ������������������ ���������� ��������� ������ ���������
	 * � ������������������� ����� � ������ ������� ����������� ������� ��� ������ ������
	 * 
	 * @param entityClazz	����� ��������
	 * @param propertyName	������������ �������� ������������� � ������ �������
	 * @param columnName	������������ �������, ����� ���� <code>null</code>, ����� � �������� ������������ ������� ����� ����������� �������� <code>propertyName</code>
	 */
	protected final void addColumnDef(Class<? extends PersistentObject> entityClazz, String propertyName, String columnName) {
		columnsDef.put(columnName != null ? columnName : propertyName, ApplicationDictionaryLocator.locate().getFieldMetadata(ReflectionUtils.getPropertyReflectionMetadata(entityClazz, propertyName)));
	}
	
	/**
	 * ��������� �������� � ������ �������� �� ��������� ���������� ����� ������� EJBQL
	 * 
	 * @param fieldDefs	������ ���������� �����
	 */
	protected final void addColumnDefs(Set<TableEJBQLFieldDef> fieldDefs) {
		for (TableEJBQLFieldDef fieldDef : fieldDefs) {
			String columnName = fieldDef.getAlias();
			columnsDef.put(columnName, fieldDef.getFieldMetadata());
		}
	}
	
	/**
	 * ��������� �������� ���������������� ����� (� ���������� ������� ������� �������������� ��������) �
	 * ������ ��������, ������������ ������ � ������ ���������� � ������ ���������������� �����
	 * 
	 * @param service ������-���������
	 */
	protected void addAddinFieldDef(DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		//TODO implement
	}
	
	/**
	 * ���������� ������ ���������� ����� �� ��������� ������������ � �������, �������� ���������������
	 * � ������ ����������
	 * 
	 * @return	������ ���������� �����
	 */
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		return new LinkedHashSet<TableEJBQLFieldDef>();
	}

	protected Set<TableEJBQLFieldDef> doCreateFieldDefsSet() {
		return getDefaultFieldDefsSet();
	}

	/**
	 * ���������� ������ ���������� ����� ������������ � �������
	 * 
	 * @return	������ ���������� �����
	 */
	public final Set<TableEJBQLFieldDef> getFieldDefsSet() {
		if (fieldDefs == null) {
			//TODO implement, �������� ����������� ���� ��� ������������
			fieldDefs = doCreateFieldDefsSet();			
		}
		return fieldDefs;
	}

	/**
	 * ������������� ������ ������� ��� ������
	 * 
	 * @param rowList ������ �������
	 */
	@SuppressWarnings("unchecked")
	public void setRowList(List rowList) {
		if (rowList == null)
			throw new IllegalArgumentException("RowList is null");
		
		this.rowList = new ArrayList<Object[]>();
		for (int i = 0; i < rowList.size(); i++) {
			Object tuple = rowList.get(i);
			//���� ������� ������� ������, �� �������� ���� ������, �.�. ������ ������ ���� ����������
			//�.�. ��� ������� ������� �� �������� �������� ���������� �����
			if (i == 0 && tuple instanceof Object[]) {
				this.rowList.addAll(rowList);
				break;
			} else {
				//����������� � ������ ������ ������, ����� �������� ��������, ���� � ��������������
				//������ ������ ���� ����
				this.rowList.add(new Object[] {tuple});
			}
		}
		fireModelChange();
	}
	
	/**
	 * ��������� ������ ������� � ���������� �������
	 * 
	 * @param queryString	����� ������ (������ ������������� ���������� EJBQL)
	 * @param paramNames	������ ������������ ���������� �������
	 * @param values		������ �������� ����������
	 */
	public void setQuery(final String queryString, final String[] paramNames, final Object[] values) {
		setRowList(getOrmTemplate().findByNamedParam(queryString, paramNames, values));
	}

	/**
	 * ��������� ������ �������
	 * 
	 * @param queryString	����� ������ (������ ������������� ���������� EJBQL)
	 */
	public void setQuery(final String queryString) {
		setRowList(getOrmTemplate().find(queryString));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doSetColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
	 */
	@Override
	protected void doSetColumns(TableColumnModel[] columns) {
		columnsModel = columns;
		createColumnsModel();
		isColumnModelCreated = true;
		//���������� ������ ����� ������������� � ��������� �����
		for (TableColumnModel column : columnsModel) {
			if (StringUtils.stringNullOrEmpty(column.getTitle()))
				column.setMetadata(columnsDef.get(column.getFieldName()));
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
		selectedRows = rows;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#fireTableStructureChanged()
	 */
	@Override
	public void fireTableStructureChanged() {
		//������� ��� ��� ��������� ��������� �������
		isColumnModelCreated = false;
		columnsModel = new TableColumnModel[0];
		if (fieldDefs != null)
			fieldDefs.clear();
		fieldDefs = null;
		columnsDef.clear();
		super.fireTableStructureChanged();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return getRowList().size();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return getColumnModel().length;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return getColumnModel()[column].getTitle();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		return getRowList().get(row)[column];
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getDecoratorValueAt(int row, int column) {
		return columnsModel[column].getDecoratorValue(getValueAt(row, column));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getFieldMetadata(int)
	 */
	@Override
	public FieldMetadata getColumnMetadata(int column) {
		return columnsModel[column].getMetadata();
	}

}
