/*
 * DatabaseUtils.java
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
package com.mg.framework.utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Property;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.api.ui.AddinFieldsRestriction;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;

/**
 * ������� ������ � ����� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseUtils.java,v 1.15 2008/12/23 09:12:32 safonov Exp $
 */
public class DatabaseUtils {
	private static Logger logger = ServerUtils.getLogger(DatabaseUtils.class);
	
	/**
	 * ��� ����
	 * 
	 */
	public enum DBMSType {
		/**
		 * ����������� ����
		 */
		UNKNOWN,
		/**
		 * INTERBASE
		 */
		INTERBASE,
		/**
		 * ORACLE
		 */
		ORACLE,
		/**
		 * DB2
		 * �� ��������������
		 */
		DB2,
		/**
		 * MSSQL
		 * �� ��������������
		 */
		MSSQL,
		/**
		 * FIREBIRD
		 */
		FIREBIRD,
		/**
		 * POSTGRESQL
		 */
		POSTGRESQL
	}
	
	/**
	 * ���� �� ��������� Interbase
	 */
	public final static EnumSet<DBMSType> INTERBASE_FAMILY = EnumSet.of(DBMSType.INTERBASE, DBMSType.FIREBIRD);
	
	/**
	 * �������� ��� ���� ��� �������� ��������� ������
	 * 
	 * @return	��� ���� ��� <code>DBMSType.UNKNOWN</> ���� ���������� �� ������������� ���� ��� ��������� ������ ������� � ����
	 * 
	 * @see DBMSType
	 */
	public static DBMSType getDBMSType() {
		DBMSType result = DBMSType.UNKNOWN;
		java.sql.Connection conn = ServerUtils.getConnection();
		String dbName;
		try {
			try {
				dbName = conn.getMetaData().getDatabaseProductName().toUpperCase();
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("During get DBMS type database access error occurs", e);
			return result;
		}
		return getDBMSType(dbName);
	}

	/**
	 * �������� ��� ���� �� �����
	 * 
	 * @param databaseName	��� ����
	 * @return	��� ���� ��� <code>DBMSType.UNKNOWN</> ���� ���������� �� ������������� ����
	 */
	public static DBMSType getDBMSType(String databaseName) {
		DBMSType result = DBMSType.UNKNOWN;
		String dbName = databaseName.toUpperCase();
		if (dbName.startsWith("INTERBASE"))
			result = DBMSType.INTERBASE;
		else if (dbName.startsWith("FIREBIRD"))
			result = DBMSType.FIREBIRD;
		else if (dbName.equals("POSTGRESQL"))
			result = DBMSType.POSTGRESQL;
		else if (dbName.equals("ORACLE"))
			result = DBMSType.ORACLE;
		return result;
	}

	/**
	 * �������� ��������� �������� ������������������
	 * 
	 * @param sequenceName	������������ ������������������
	 * @return	��������� ��������
	 */
	public static Object getSequenceNextValue(String sequenceName) {
		String sql;
		switch (getDBMSType()) {
		case FIREBIRD:
		case INTERBASE:
			sql = "select gen_id(" + sequenceName + ", 1) from RDB$DATABASE";
			break;
		case POSTGRESQL:
			sql = "select nextval('" + sequenceName + "')";
			break;
		case ORACLE:
			sql = "select " + sequenceName + ".nextval from dual";
			break;
		default:
			throw new UnsupportedOperationException("Unknown database");
		}
		List<Object> list = JdbcTemplate.getInstance().query(sql, new Object[] {}, new RowMapper<Object>() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getObject(1);
			}
		});
		return list.get(0);
	}

	/**
	 * ��������� ������ (EJBQL) ��� ������� ���������������� ����� � ��������� ������-�����������
	 * 
	 * @param queryString	������ ������� ��� ������� ������
	 * @param service		������-���������
	 * @param keyName		��� ���� ���������� �����
	 * @param fieldsSet		������ ����� ����������� ��� �������
	 * @return	����� �������
	 */
	public static String embedAddinFieldsBrowseEJBQL(final String queryString, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			final String keyName, Set<String> fieldsSet) {
		return queryString;
	}
	
	/**
	 * ��������� ������ ����� �� ��������� ��� ������� ���������������� �����
	 * 
	 * @param fieldsSet	��������� ������ �����
	 * @param service	������-���������
	 * @return	������ ����� � ����������������� ������
	 */
	public static Set<String> embedAddinFieldsDefaultFieldsSet(Set<String> fieldsSet, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		return fieldsSet;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� �������
	 * ������������� �������� ��� ����� ��������� ��������, �� �������� ����� �������������.
	 * <p> ��������:
	 * <blockquote><pre>
	 *   String ejbqlText = "from CatalogFolder cf where".concat(DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1).concat(" order by cf.Id");
	 * </pre></blockquote>
	 * 
	 * @param folderFieldName	������������ ���� ������� ����������� �� ����� �� ����������
	 * @param folderPart		��� ��������
	 * @return	������ �������� <code>where</code>
	 */
	public static String generateFlatBrowseWhereEJBQL(final String folderFieldName, final int folderPart) {
		return String.format(" exists (from FolderRights fr where (fr.FolderId = %s) and (fr.SecGroups in (%s)) and (fr.FolderPart = %d) and (fr.Permission = true))", folderFieldName, ServerUtils.getUserProfile().getGroupsCommaText(), folderPart); //$NON-NLS-1$
	}
	
	/**
	 * ��������� ��������� ��� �������
	 * ������������� �������� ��� ����� ��������� ��������, �� �������� ����� �������������.
	 * 
	 * @param criteria			��������
	 * @param folderFieldName	������������ ���� ������� ����������� �� ����� �� ����������
	 * @param folderPart		��� ��������
	 * @return	��������
	 */
	public static Criteria generateFlatBrowseCriteria(final Criteria criteria, final String folderFieldName, final int folderPart) {
		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.FolderRights", "fr").add(Property.forName("fr.FolderId").eqProperty(folderFieldName))
				.add(Restrictions.eq("fr.FolderPart", (short) folderPart))
				.add(Restrictions.eq("fr.Permission", true))
				.add(Restrictions.in("fr.SecGroups.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups())))
				.setProjection(Projections.property("fr.Id"));
		return criteria.add(Subqueries.exists(dc));
	}
	
	/**
	 * ��������� ������� <code>and</code> � ������ ������� ��� ����������� ������������� ������� ����
	 * ��� �� ������ �����������
	 * 
	 * @param restrictionText	������ �����������
	 * @param firstRestriction	������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 */
	private static String generateRestrictionPrefix(final String restrictionText, boolean firstRestriction) {
		if (!firstRestriction)
			return " and ".concat(restrictionText);
		else
			return restrictionText;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� ����������� �� ���������� ����
	 * <p> ��������:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLStringRestriction("cat.Code", restForm.getCode(), "code", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName			��� ���� �� �������� ���������� �����������
	 * @param value				�������� ����
	 * @param paramName			��� ��������� �������
	 * @param paramsName		������ ���� ���������� �������
	 * @param paramsValue		������ �������� ���������� �������
	 * @param firstRestriction	������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 */
	public static String formatEJBQLStringRestriction(final String fieldName, final String value, final String paramName, List<String> paramsName, List<Object> paramsValue, boolean firstRestriction) {
		String result = "";
		if (!StringUtils.stringNullOrEmpty(value)) {
			String prmValue = value;
			//replace * to %
			if (value.indexOf("*") != -1)
				prmValue = value.replace("*", "%");
			int index = prmValue.indexOf("%");
			if (index == -1) {
				result = String.format("(upper(%s) = :%s)", fieldName, paramName);
			}
			else {
				result = String.format("(upper(%s) like :%s)", fieldName, paramName);
			}
			paramsName.add(paramName);
			paramsValue.add(StringUtils.toUpperCase(prmValue));
			result = generateRestrictionPrefix(result, firstRestriction);
		}
		return result;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� ����������� �� ���� ��������
	 * 
	 * @param isUseHierarchy	������������ ����� �� ��������, ���� <code>false</code>, �� ����� ������������ ������ ��� ����� ��������� ��������
	 * @param folderFieldName	������������ ���� ������� ����������� �� ����� �� ����������
	 * @param folderPart		��� ��������
	 * @param folderParamName	��� ��������� ������� ��������
	 * @param folderEntity		������ ��������
	 * @param paramsName		������ ���� ���������� �������
	 * @param paramsValue		������ �������� ���������� �������
	 * @param firstRestriction	������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 * 
	 * @see #generateFlatBrowseWhereEJBQL(String, int)
	 */
	public static String formatEJBQLHierarchyRestriction(boolean isUseHierarchy, final String folderFieldName, final int folderPart, final String folderParamName, Object folderEntity, List<String> paramsName, List<Object> paramsValue, boolean firstRestriction) {
		String result;
		if (isUseHierarchy) {
			result = String.format("(%s = :%s)", folderFieldName, folderParamName);
			paramsName.add(folderParamName);
			paramsValue.add(folderEntity);			
		}
		else {
			result = DatabaseUtils.generateFlatBrowseWhereEJBQL(folderFieldName, folderPart);
		}
		result = generateRestrictionPrefix(result, firstRestriction);
		return result;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� ����������� �� ����
	 * <p> ��������:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRestriction("cat.GoodType", restForm.getGoodType(), "goodType", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName			��� ���� �� �������� ���������� �����������
	 * @param value				�������� ����
	 * @param paramName			��� ��������� �������
	 * @param paramsName		������ ���� ���������� �������
	 * @param paramsValue		������ �������� ���������� �������
	 * @param firstRestriction		������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 */
	public static String formatEJBQLObjectRestriction(final String fieldName, final Object value, final String paramName, List<String> paramsName, List<Object> paramsValue, boolean firstRestriction) {
		String result = "";
		if (value != null) {
			result = String.format("(%s = :%s)", fieldName, paramName);
			paramsName.add(paramName);
			paramsValue.add(value);
			result = generateRestrictionPrefix(result, firstRestriction);
		}
		return result;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� ����������� �� ���� ���������� ��������
	 * <p> ��������:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("cat.MarketingMargin", restForm.getTradeTaxFrom(), restForm.getTradeTaxTo(), "taxFrom", "taxTo", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName		��� ���� �� �������� ���������� �����������
	 * @param value1		�������� ���� "�����" �������
	 * @param value2		�������� ���� "������" �������
	 * @param paramName1	��� ��������� ������� ��� "�����" �������
	 * @param paramName2	��� ��������� ������� ��� "������" �������
	 * @param paramsName	������ ���� ���������� �������
	 * @param paramsValue	������ �������� ���������� �������
	 * @param firstRestriction		������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 */
	public static String formatEJBQLObjectRangeRestriction(final String fieldName, final Object value1, final Object value2, final String paramName1, final String paramName2, List<String> paramsName, List<Object> paramsValue, boolean firstRestriction) {
		String result = "";
		if (value1 != null && value1.equals(value2)) {
			result = String.format("(%s = :%s)", fieldName, paramName1);
			paramsName.add(paramName1);
			paramsValue.add(value1);
		}
		else if (value1 != null || value2 != null) {
			if (value1 == null) {
				result = String.format("(%s <= :%s)", fieldName, paramName2);
				paramsName.add(paramName2);
				paramsValue.add(value2);				
			}
			else if (value2 == null) {
				result = String.format("(%s >= :%s)", fieldName, paramName1);
				paramsName.add(paramName1);
				paramsValue.add(value1);
			}
			else {
				result = String.format("(%s between :%s and :%s)", fieldName, paramName1, paramName2);
				paramsName.add(paramName1);
				paramsValue.add(value1);
				paramsName.add(paramName2);
				paramsValue.add(value2);				
			}
		}
		if (!result.equals(""))
			result = generateRestrictionPrefix(result, firstRestriction);
		return result;
	}
	
	/**
	 * ��������� �������� <code>where</code> ������� EJBQL ��� ����������� �� ���������������� �����
	 * 
	 * @param service	������-���������
	 * @param keyName	��� ���� ���������� �����
	 * @param addinFieldsRestriction	����������� �� ���������������� �����
	 * @param firstRestriction	������ �����������, ���� <code>false</code>, �� � ���������� ����� ���������� ������� <code>" and "</code>
	 * @return	������ �������� <code>where</code>
	 */
	public static String formatEJBQLAddinFieldsRestriction(DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service, final String keyName, AddinFieldsRestriction addinFieldsRestriction, boolean firstRestriction) {
		//TODO implement
		return "";
	}
	
	/**
	 * ��������� ������ ����� �������� �� ��������� �� ���������� �������������, ������������ �������� ������ '<strong>,</strong>'
	 * <p> ��������:
	 * <blockquote><pre>
	 *   fieldsSet = DatabaseUtils.generateDefaultFieldsSet("Id,Code,Name", service));
	 * </pre></blockquote>
	 * 
	 * @param fieldsSet	��������� �������������
	 * @param service	������-���������
	 * @return	��������� �����
	 */
	public static Set<String> generateDefaultFieldsSet(final String fieldsSet, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		Set<String> result = new LinkedHashSet<String>();
		result.addAll(StringUtils.split(fieldsSet, ",")); //$NON-NLS-1$
		return DatabaseUtils.embedAddinFieldsDefaultFieldsSet(result, service);
	}
	
	/**
	 * ��������� ������ ����� ��� ��������� <code>select</code> EJBQL �������
	 * 
	 * @param fieldDefs	��������� �����
	 * @return	������ �����
	 */
	public static String generateEJBQLSelectClause(Set<TableEJBQLFieldDef> fieldDefs) {
		StringBuilder result = new StringBuilder();
		for (TableEJBQLFieldDef fieldDef : fieldDefs) {
			//�� �������� ���������������� ���� � ����������� select
			if (fieldDef.getFieldName().startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX))
				continue;

			if (result.length() != 0)
				result.append(", ");
			result.append(fieldDef.getFieldName());
		}
		return result.toString();
	}

	/**
	 * ��������� ��������� <code>from</code> EJBQL �������
	 * 
	 * @param fieldDefs	��������� �����
	 * @return	��������� <code>from</code>
	 */
	public static String generateEJBQLFromClause(Set<TableEJBQLFieldDef> fieldDefs) {
		StringBuilder result = new StringBuilder();
		for (TableEJBQLFieldDef fieldDef : fieldDefs) {
			String join = fieldDef.getJoinClause();
			if (join != null)
				result.append(StringUtils.BLANK_STRING).append(join);
		}
		return result.toString();
	}

	/**
	 * ��������� ������ ���������� ����� �� ��������� ��� ������� ���������������� �����
	 * 
	 * @param fieldDefs	��������� ������ ���������� �����
	 * @param service	������-���������
	 * @return	������ ���������� ����� � ����������������� ������
	 */
	public static Set<TableEJBQLFieldDef> embedAddinFieldsDefaultEJBQLFieldDefs(Set<TableEJBQLFieldDef> fieldDefs, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		if (service != null) {
			FieldMetadata[] metadata = CustomFieldsManagerLocator.locate().loadFieldsMetadata(service);
			for (FieldMetadata meta : metadata) {
				TableEJBQLFieldDef fieldDef = new TableEJBQLFieldDef(meta);
				fieldDefs.add(fieldDef);
			}
		}
		return fieldDefs;
	}

}
