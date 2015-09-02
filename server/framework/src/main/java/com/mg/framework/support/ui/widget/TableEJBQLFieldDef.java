/*
 * TableEJBQLFieldDef.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;

/**
 * �������� ���� EJBQL ������� ��� �������� ������ �������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableEJBQLFieldDef.java,v 1.4 2008/12/23 09:17:02 safonov Exp $
 */
public class TableEJBQLFieldDef implements Serializable {
	private String alias;
	private String fieldName;
	private Class<? extends PersistentObject> entityClazz;
	private String entityPropertyName;
	private String joinClause;
	private boolean isMandatory;
	private FieldMetadata fieldMetadata;

	/**
	 * �����������, ��������� ��� ���� �� ��������� ������� ����������
	 * 
	 * @param alias					��������� ����, ����� ���� <code>null</code>, ����� � �������� ������������ ������� ����� ����������� �������� <code>entityPropertyName</code>
	 * @param entityClazz			����� ��������
	 * @param entityPropertyName	������������ �������� ������������� � ������ �������
	 * @param fieldName				������������ ���� � �������
	 * @param joinClause			����������� join ��� ����
	 * @param isMandatory			������� ������������� ��������� � ������
	 */
	public TableEJBQLFieldDef(String alias, Class<? extends PersistentObject> entityClazz, String entityPropertyName, String fieldName, String joinClause, boolean isMandatory) {
		super();
		this.alias = alias != null ? alias : entityPropertyName;
		this.fieldName = fieldName;
		this.entityClazz = entityClazz;
		this.entityPropertyName = entityPropertyName;
		this.isMandatory = isMandatory;
		this.joinClause = joinClause;
		loadFieldMetadata();
	}

	/**
	 * ����������� �����������
	 * 
	 * @param alias
	 * @param entityClazz
	 * @param entityPropertyName
	 * @param fieldName
	 * @param isMandatory
	 * 
	 * @see #TableEJBQLFieldDef(String, Class, String, String, String, boolean)
	 */
	public TableEJBQLFieldDef(String alias, Class<? extends PersistentObject> entityClazz, String entityPropertyName, String fieldName, boolean isMandatory) {
		this(alias, entityClazz, entityPropertyName, fieldName, null, isMandatory);
	}

	/**
	 * ����������� �����������
	 * 
	 * @param entityClazz
	 * @param entityPropertyName
	 * @param fieldName
	 * @param isMandatory
	 * 
	 * @see #TableEJBQLFieldDef(String, Class, String, String, String, boolean)
	 */
	public TableEJBQLFieldDef(Class<? extends PersistentObject> entityClazz, String entityPropertyName, String fieldName, boolean isMandatory) {
		this(null, entityClazz, entityPropertyName, fieldName, null, isMandatory);
	}

	/**
	 * ����������� �����������
	 * 
	 * @param entityClazz
	 * @param entityPropertyName
	 * @param fieldName
	 * @param joinClause
	 * @param isMandatory
	 * 
	 * @see #TableEJBQLFieldDef(String, Class, String, String, String, boolean)
	 */
	public TableEJBQLFieldDef(Class<? extends PersistentObject> entityClazz, String entityPropertyName, String fieldName, String joinClause, boolean isMandatory) {
		this(null, entityClazz, entityPropertyName, fieldName, joinClause, isMandatory);
	}

	/**
	 * ����������� �����������
	 * 
	 * @param fieldMetadata
	 */
	public TableEJBQLFieldDef(FieldMetadata fieldMetadata) {
		if (fieldMetadata == null)
			throw new IllegalArgumentException("Metadata is null");
		this.alias = fieldMetadata.getName();
		this.fieldName = fieldMetadata.getName();
		this.fieldMetadata = fieldMetadata;
	}

	private void loadFieldMetadata() {
		fieldMetadata = ApplicationDictionaryLocator.locate().getFieldMetadata(entityClazz, entityPropertyName);
	}
	
	public static TableEJBQLFieldDef findByAlias(Set<TableEJBQLFieldDef> fieldDefs, String alias) {
		for (TableEJBQLFieldDef fieldDef : fieldDefs) {
			if (fieldDef.getAlias().equals(alias))
				return fieldDef;
		}
		return null;
	}
	
	/**
	 * @return Returns the alias.
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @return Returns the entityClazz.
	 */
	public Class<? extends PersistentObject> getEntityClazz() {
		return entityClazz;
	}

	/**
	 * @return Returns the entityPropertyName.
	 */
	public String getEntityPropertyName() {
		return entityPropertyName;
	}

	/**
	 * @return Returns the fieldName.
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return Returns the isMandatory.
	 */
	public boolean isMandatory() {
		return isMandatory;
	}

	/**
	 * @return Returns the joinClause.
	 */
	public String getJoinClause() {
		return joinClause;
	}

	/**
	 * @return Returns the fieldMetadata.
	 */
	public FieldMetadata getFieldMetadata() {
		return fieldMetadata;
	}

}
