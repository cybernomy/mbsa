/* ReportParameterImpl.java
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
package com.mg.merp.report.parameters.support;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.report.engine.api.ICascadingParameterGroup;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.model.api.ScalarParameterHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import com.ibm.icu.math.BigDecimal;
import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.report.parameters.ReportParameter;
import com.mg.merp.report.parameters.SelectionChoice;
import com.mg.merp.report.support.RptEngineImpl;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ReportParameterImpl.java,v 1.10 2008/10/24 14:04:53 safonov Exp $
 */
public class ReportParameterImpl implements ReportParameter {
	private static Logger logger = ServerUtils.getLogger(ReportParameterImpl.class);
	/**
	 * Параметр
	 */
	private IScalarParameterDefn param;

	private IParameterGroupDefn paramGroup;
	
	private ReportParameter.DataType dataType = null;
	
	private Object value;
	
	private String searchHelpName;
	
	private String entityPropertyText;
	
	private String entityPropertyTextFormat;
	
	private RptEngineImpl rptEngine;
	
	public ReportParameterImpl(IScalarParameterDefn birtParam, IParameterGroupDefn paramGroup,
			Object defaultValue, RptEngineImpl rptEngine) {
		this.rptEngine = rptEngine;
		param = birtParam;
		this.paramGroup = paramGroup;
		value = defaultValue;
		int tp = param.getDataType();
		switch (tp) {
		case IScalarParameterDefn.TYPE_STRING:
			dataType = handleStringType(param);
			break;
		case IScalarParameterDefn.TYPE_FLOAT:
			dataType = ReportParameter.DataType.FLOAT;
			break;
		case IScalarParameterDefn.TYPE_DECIMAL:
			dataType = ReportParameter.DataType.NUMBER;
			break;
		case IScalarParameterDefn.TYPE_DATE_TIME:
			dataType = ReportParameter.DataType.DATE_TIME;
			break;
		case IScalarParameterDefn.TYPE_BOOLEAN:
			dataType = ReportParameter.DataType.BOOLEAN;
			break;
		case IScalarParameterDefn.TYPE_INTEGER:
			dataType = ReportParameter.DataType.INTEGER;
			break;
		case IScalarParameterDefn.TYPE_DATE:
			dataType = ReportParameter.DataType.DATE;
			break;
		case IScalarParameterDefn.TYPE_TIME:
			dataType = ReportParameter.DataType.TIME;
			break;
		default:
			dataType = ReportParameter.DataType.UNKNOWN;
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.report.ReportParameter#getDisplayName()
	 */
	public String getDisplayName() {
		return param.getPromptText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.report.ReportParameter#getName()
	 */
	public String getName() {
		return param.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.report.ReportParameter#getParameterType()
	 */
	public ParameterType getParameterType() {
		switch (param.getParameterType()) {
		case IParameterDefnBase.SCALAR_PARAMETER:
			return ReportParameter.ParameterType.SCALAR_PARAMETER;
		case IParameterDefnBase.FILTER_PARAMETER:
			return ReportParameter.ParameterType.FILTER_PARAMETER;
		case IParameterDefnBase.LIST_PARAMETER:
			return ReportParameter.ParameterType.LIST_PARAMETER;
		case IParameterDefnBase.TABLE_PARAMETER:
			return ReportParameter.ParameterType.TABLE_PARAMETER;
		case IParameterDefnBase.PARAMETER_GROUP:
			return ReportParameter.ParameterType.PARAMETER_GROUP;
		case IParameterDefnBase.CASCADING_PARAMETER_GROUP:
			return ReportParameter.ParameterType.CASCADING_PARAMETER_GROUP;
		default:
			return ReportParameter.ParameterType.SCALAR_PARAMETER;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.report.ReportParameter#getUserPropertyValue(java.lang.String)
	 */
	public String getUserPropertyValue(String name) {
		return param.getUserPropertyValue(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.report.ReportParameter#getUserPropertyValues()
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserPropertyValues() {
		return param.getUserPropertyValues();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getValue()
	 */
	public Object getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		if (dataType == ReportParameter.DataType.TIME)
			this.value = value != null ? new java.sql.Time(((Date) value).getTime()) : null;
		else if (dataType == ReportParameter.DataType.DATE)
			this.value = value != null ? new java.sql.Date(((Date) value).getTime()) : null;
		else
			this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getSearchHelpName()
	 */
	public String getSearchHelpName() {
		return searchHelpName;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#setSearchHelpName(java.lang.String)
	 */
	public void setSearchHelpName(String searchHelpName) {
		this.searchHelpName = searchHelpName;
	}
		
	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getEntityPropertyText()
	 */
	public String getEntityPropertyText() {
		return this.entityPropertyText;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getEntityPropertyTextFormat()
	 */
	public String getEntityPropertyTextFormat() {
		return this.entityPropertyTextFormat;
	}

	private DataType handleStringType(IScalarParameterDefn param) {
		if (param.getDataType() != IScalarParameterDefn.TYPE_STRING)
			throw new IllegalArgumentException("Parameter type is not string");
		
		String defValue = param.getDefaultValue();
		if (defValue != null && defValue.startsWith("${entity:") && defValue.endsWith("}")) {
			//${entity:<entity name>[#<identificator value>][;searchHelp:<search help name>]}
			List<String> descs = StringUtils.split(defValue.substring(2, defValue.length() - 1), ";");
			for (String desc : descs) {
				if (desc.startsWith("entity:")) {
					//set entity
					String entityDesc = desc.substring(7);
					String entityName;
					int valueIdx = entityDesc.indexOf("#");
					if (valueIdx != -1) {
						//есть значение
						entityName = entityDesc.substring(0, valueIdx);
						ClassMetadata classMetadata = ((Session) ServerUtils.getPersistentManager().getDelegate()).getSessionFactory().getClassMetadata(entityName);
						if (classMetadata == null)
							throw new IllegalArgumentException("Metadata of entity not found: " + entityName);
						Type type = classMetadata.getIdentifierType();
						Object entityID = entityDesc.substring(valueIdx + 1, entityDesc.length());
						if (type instanceof IntegerType)
							entityID = Integer.parseInt((String) entityID);
						if (type instanceof LongType)
							entityID = Long.parseLong((String) entityID);
						this.value = ServerUtils.getPersistentManager().find(entityName, entityID);
					} else {
						entityName = entityDesc;
						this.value = null;
					}
					
					DataItem dataItem = getFieldDataItem(entityName);
					if(dataItem != null) {
						entityPropertyText = dataItem.getEntityPropertyText();
						entityPropertyTextFormat = dataItem.getEntityTextFormat();
						// если не установлен, то пытаемся взять из описателя сущности
						if(searchHelpName == null) 
							searchHelpName = dataItem.getSearchHelpName();
					}
				} else if (desc.startsWith("searchHelp:")) {
					//set search help
					String searchHelpName = desc.substring(11);
					this.searchHelpName = searchHelpName;
				}
			}
			return ReportParameter.DataType.ENTITY;
		} else
			return ReportParameter.DataType.STRING;
	}
	
	/**
	 * Получить элемент данных по имени сущности
	 * @param entityName - наименование сущности
	 * @return элемент данных или <code>null</code> если не найден
	 */
	private DataItem getFieldDataItem(String entityName) {
		ReflectionMetadata reflectionMetadata = null;
		try {
			reflectionMetadata = ReflectionUtils.getClassReflectionMetadata(ServerUtils.loadClass(entityName));
		} catch (ClassNotFoundException e) {
			logger.error("Couldn't retrive metadata for entity: " + entityName, e);
		}
		
		if(reflectionMetadata != null && reflectionMetadata.getDataItemName() != null)
			return ApplicationDictionaryLocator.locate().getDataItem(reflectionMetadata.getDataItemName());
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getDataType()
	 */
	public DataType getDataType() {
		return dataType;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getJavaType()
	 */
	public Class<?> getJavaType() {
		if (value != null)
			return value.getClass();
		else
			switch (dataType) {
			case BOOLEAN:
				return Boolean.class;
			case DATE:
			case DATE_TIME:
				return Date.class;
			case ENTITY:
				return PersistentObject.class;
			case FLOAT:
				return Float.class;
			case INTEGER:
				return Integer.class;
			case NUMBER:
				return BigDecimal.class;
			case STRING:
				return String.class;
			case TIME:
				return Time.class;
			default:
				return null;
			}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#hasListValues()
	 */
	public boolean hasListValues() {
		return param.getSelectionListType() != IScalarParameterDefn.SELECTION_LIST_NONE;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getSelectionList()
	 */
	public List<SelectionChoice> getSelectionList() {
		if (param.getSelectionListType() == IScalarParameterDefn.SELECTION_LIST_NONE)
			return new ArrayList<SelectionChoice>();
		if (cascade()) {
			//для каскадных параметров готовим значения параметров из его же группы
			//но которые выше по иерархии
			List<ReportParameter> params = rptEngine.findGroupReportParameters(groupName());
			int length = params.indexOf(this);
			Object[] groupKeys = new Object[length];
			for (int i = 0; i < length; i++)
				groupKeys[i] = params.get(i).getValue();
			return rptEngine.getSelectionListForCascadingGroup(param.getName(), groupName(), groupKeys);
		} else
			return rptEngine.getParameterSelectionList(param.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#getControlType()
	 */
	public ControlType getControlType() {
		int tp = param.getControlType();
		ScalarParameterHandle scalarParamHandle = null;
		Object paramHandle = param.getHandle();
		if (paramHandle instanceof ScalarParameterHandle)
			scalarParamHandle = (ScalarParameterHandle) paramHandle;
		switch (tp) {
		case IScalarParameterDefn.TEXT_BOX:
			return ControlType.TEXT_BOX;
		case IScalarParameterDefn.LIST_BOX:
			boolean isMultiValue = false;
			if (scalarParamHandle != null)
				isMultiValue = DesignChoiceConstants.SCALAR_PARAM_TYPE_MULTI_VALUE
						.equalsIgnoreCase(scalarParamHandle.getParamType());
			return isMultiValue ? ControlType.LIST_BOX : ControlType.COMBO_BOX; 
		case IScalarParameterDefn.CHECK_BOX:
			return ControlType.CHECK_BOX;
		case IScalarParameterDefn.RADIO_BUTTON:
			return ControlType.RADIO_BUTTON;
		default:
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#isHidden()
	 */
	public boolean isHidden() {
		return param.isHidden();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#isValueConcealed()
	 */
	public boolean isValueConcealed() {
		return param.isValueConcealed();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#cascade()
	 */
	public boolean cascade() {
		return paramGroup instanceof ICascadingParameterGroup;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#groupName()
	 */
	public String groupName() {
		return paramGroup != null ? paramGroup.getName() : null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#indexInGroup()
	 */
	public int indexInGroup() {
		//загрузим параметры из своей группы
		List<ReportParameter> groupParams = rptEngine.findGroupReportParameters(groupName());
		return groupParams.indexOf(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.ReportParameter#isRequired()
	 */
	public boolean isRequired() {
		return param.isRequired();
	}

}
