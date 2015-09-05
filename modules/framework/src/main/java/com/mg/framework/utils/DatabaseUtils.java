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
 * Утилиты работы с базой данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseUtils.java,v 1.15 2008/12/23 09:12:32 safonov Exp $
 */
public class DatabaseUtils {
	private static Logger logger = ServerUtils.getLogger(DatabaseUtils.class);
	
	/**
	 * Тип СУБД
	 * 
	 */
	public enum DBMSType {
		/**
		 * неизвестная СУБД
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
		 * не поддерживается
		 */
		DB2,
		/**
		 * MSSQL
		 * не поддерживается
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
	 * СУБД из семейства Interbase
	 */
	public final static EnumSet<DBMSType> INTERBASE_FAMILY = EnumSet.of(DBMSType.INTERBASE, DBMSType.FIREBIRD);
	
	/**
	 * получить тип СУБД для текущего источника данных
	 * 
	 * @return	тип СУБД или <code>DBMSType.UNKNOWN</> если обнаружена не поддерживамая СУБД или произошла ошибка доступа к СУБД
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
	 * получить тип СУБД по имени
	 * 
	 * @param databaseName	имя СУБД
	 * @return	тип СУБД или <code>DBMSType.UNKNOWN</> если обнаружена не поддерживамая СУБД
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
	 * получить следующее значение последовательности
	 * 
	 * @param sequenceName	наименование последовательности
	 * @return	следующее значение
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
	 * формируем запрос (EJBQL) для выборки пользовательских полей в браузерах бизнес-компонентов
	 * 
	 * @param queryString	строка запроса для выборки данных
	 * @param service		бизнес-компонент
	 * @param keyName		имя поля первичного ключа
	 * @param fieldsSet		список полей необходимых для выборки
	 * @return	текст запроса
	 */
	public static String embedAddinFieldsBrowseEJBQL(final String queryString, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			final String keyName, Set<String> fieldsSet) {
		return queryString;
	}
	
	/**
	 * формирует список полей по умолчанию для выборки пользовательских полей
	 * 
	 * @param fieldsSet	начальный список полей
	 * @param service	бизнес-компонент
	 * @return	список полей с пользовательскими полями
	 */
	public static Set<String> embedAddinFieldsDefaultFieldsSet(Set<String> fieldsSet, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		return fieldsSet;
	}
	
	/**
	 * генерация элемента <code>where</code> запроса EJBQL для выборки
	 * иерархических объектов без учета структуры иерархии, но учитывая права пользователей.
	 * <p> Например:
	 * <blockquote><pre>
	 *   String ejbqlText = "from CatalogFolder cf where".concat(DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1).concat(" order by cf.Id");
	 * </pre></blockquote>
	 * 
	 * @param folderFieldName	наименование поля объекта отвечающего за связь со структурой
	 * @param folderPart		вид иерархии
	 * @return	строку элемента <code>where</code>
	 */
	public static String generateFlatBrowseWhereEJBQL(final String folderFieldName, final int folderPart) {
		return String.format(" exists (from FolderRights fr where (fr.FolderId = %s) and (fr.SecGroups in (%s)) and (fr.FolderPart = %d) and (fr.Permission = true))", folderFieldName, ServerUtils.getUserProfile().getGroupsCommaText(), folderPart); //$NON-NLS-1$
	}
	
	/**
	 * генерация критериев для выборки
	 * иерархических объектов без учета структуры иерархии, но учитывая права пользователей.
	 * 
	 * @param criteria			критерии
	 * @param folderFieldName	наименование поля объекта отвечающего за связь со структурой
	 * @param folderPart		вид иерархии
	 * @return	критерии
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
	 * добавляет префикс <code>and</code> в строку запроса для организации множественных условий если
	 * это не первое ограничение
	 * 
	 * @param restrictionText	строка ограничения
	 * @param firstRestriction	первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
	 */
	private static String generateRestrictionPrefix(final String restrictionText, boolean firstRestriction) {
		if (!firstRestriction)
			return " and ".concat(restrictionText);
		else
			return restrictionText;
	}
	
	/**
	 * генерация элемента <code>where</code> запроса EJBQL для ограничения по текстовому полю
	 * <p> Например:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLStringRestriction("cat.Code", restForm.getCode(), "code", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName			имя поля по которому происходит ограничение
	 * @param value				значение поля
	 * @param paramName			имя параметра запроса
	 * @param paramsName		список имен параметров запроса
	 * @param paramsValue		список значений параметров запроса
	 * @param firstRestriction	первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
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
	 * генерация элемента <code>where</code> запроса EJBQL для ограничения по полю иерархии
	 * 
	 * @param isUseHierarchy	использовать отбор по иерархии, если <code>false</code>, то будет сгенерирован запрос без учета структуры иерархии
	 * @param folderFieldName	наименование поля объекта отвечающего за связь со структурой
	 * @param folderPart		вид иерархии
	 * @param folderParamName	имя параметра объекта иерархии
	 * @param folderEntity		объект иерархии
	 * @param paramsName		список имен параметров запроса
	 * @param paramsValue		список значений параметров запроса
	 * @param firstRestriction	первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
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
	 * генерация элемента <code>where</code> запроса EJBQL для ограничения по полю
	 * <p> Например:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRestriction("cat.GoodType", restForm.getGoodType(), "goodType", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName			имя поля по которому происходит ограничение
	 * @param value				значение поля
	 * @param paramName			имя параметра запроса
	 * @param paramsName		список имен параметров запроса
	 * @param paramsValue		список значений параметров запроса
	 * @param firstRestriction		первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
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
	 * генерация элемента <code>where</code> запроса EJBQL для ограничения по полю диапазоном значений
	 * <p> Например:
	 * <blockquote><pre>
	 *   whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("cat.MarketingMargin", restForm.getTradeTaxFrom(), restForm.getTradeTaxTo(), "taxFrom", "taxTo", paramsName, paramsValue, false));
	 * </pre></blockquote>
	 * 
	 * @param fieldName		имя поля по которому происходит ограничение
	 * @param value1		значение поля "левой" границы
	 * @param value2		значение поля "правой" границы
	 * @param paramName1	имя параметра запроса для "левой" границы
	 * @param paramName2	имя параметра запроса для "правой" границы
	 * @param paramsName	список имен параметров запроса
	 * @param paramsValue	список значений параметров запроса
	 * @param firstRestriction		первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
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
	 * генерация элемента <code>where</code> запроса EJBQL для ограничения по пользовательским полям
	 * 
	 * @param service	бизнес-компонент
	 * @param keyName	имя поля первичного ключа
	 * @param addinFieldsRestriction	ограничения по пользовательским полям
	 * @param firstRestriction	первое ограничение, если <code>false</code>, то в результате будет установлен суффикс <code>" and "</code>
	 * @return	строку элемента <code>where</code>
	 */
	public static String formatEJBQLAddinFieldsRestriction(DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service, final String keyName, AddinFieldsRestriction addinFieldsRestriction, boolean firstRestriction) {
		//TODO implement
		return "";
	}
	
	/**
	 * генерация списка полей браузера по умолчанию из строкового представления, разделителем является символ '<strong>,</strong>'
	 * <p> Например:
	 * <blockquote><pre>
	 *   fieldsSet = DatabaseUtils.generateDefaultFieldsSet("Id,Code,Name", service));
	 * </pre></blockquote>
	 * 
	 * @param fieldsSet	строковое представление
	 * @param service	бизнес-компонент
	 * @return	множество полей
	 */
	public static Set<String> generateDefaultFieldsSet(final String fieldsSet, DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service) {
		Set<String> result = new LinkedHashSet<String>();
		result.addAll(StringUtils.split(fieldsSet, ",")); //$NON-NLS-1$
		return DatabaseUtils.embedAddinFieldsDefaultFieldsSet(result, service);
	}
	
	/**
	 * генерация списка полей для выражения <code>select</code> EJBQL запроса
	 * 
	 * @param fieldDefs	описатели полей
	 * @return	список полей
	 */
	public static String generateEJBQLSelectClause(Set<TableEJBQLFieldDef> fieldDefs) {
		StringBuilder result = new StringBuilder();
		for (TableEJBQLFieldDef fieldDef : fieldDefs) {
			//не включаем пользовательские поля в предложение select
			if (fieldDef.getFieldName().startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX))
				continue;

			if (result.length() != 0)
				result.append(", ");
			result.append(fieldDef.getFieldName());
		}
		return result.toString();
	}

	/**
	 * генерация выражения <code>from</code> EJBQL запроса
	 * 
	 * @param fieldDefs	описатели полей
	 * @return	выражение <code>from</code>
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
	 * формирует список описателей полей по умолчанию для выборки пользовательских полей
	 * 
	 * @param fieldDefs	начальный список описателей полей
	 * @param service	бизнес-компонент
	 * @return	список описателей полей с пользовательскими полями
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
