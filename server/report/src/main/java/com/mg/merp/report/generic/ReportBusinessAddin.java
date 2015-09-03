/*
 * ReportBusinessAddin.java
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
package com.mg.merp.report.generic;

import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BUSINESS_SERVICE;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.DATASET_COLUMN_NAMES;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.DATASET_COLUMN_TYPES;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.DATASET_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.ENTITY_IDS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.REPORT_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.REPORT_CONTEXT_PARAMS;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.dataset.ColumnDef;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.support.dataset.DataSetImpl;
import com.mg.framework.support.report.ReportUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.report.parameters.ReportParameter;

/**
 * Базовый класс BAi формирования набора данных для генератора отчётов. Класс
 * алгоритма должен реализовывать следующий метод
 * <code>protected void doFillDataSet()</code> и возвращать заполненый набор
 * данных, представляющий из себя {@link com.mg.framework.api.dataset.DataSet DataSet}. Алгоритм
 * имеет доступ к параметрам набора данных через метод {@link #getDataSetParameter(String)}. Доступ к идентификатору
 * выбраных сущностей осуществляется вызовом метода {@link #getEntityIds()}, возвращающего массив объектов типа
 * java.io.Serializable.
 * 
 * <p>Пример1:
 * 
 * <pre>
 * protected void doFillDataSet() {
 * 	Set&lt;String&gt; keys = getParamNames();
 * 	for (String s : keys)
 * 		setParameter(s, &quot;param_&quot; + s);
 * 	addRow();
 * 	setField(&quot;v2&quot;, (Integer) getEntityId());
 * 	setField(&quot;code&quot;, &quot;СТРОКА&quot;);
 * 	addRow();
 * 	setField(&quot;v2&quot;, new Integer(777));
 * 	setField(&quot;code&quot;, &quot;string777&quot;);
 * }</pre>
 * 
 * <p>Пример2:
 * 
 * <pre>
 * protected void doFillDataSet() {
 * 	Object[] r1 = {(Integer) getEntityId(), &quot;BAR1&quot;, &quot;asd&quot;};
 * 	Object[] r2 = {new Integer(2), &quot;CODE2&quot;, &quot;123&quot;};
 * 	Object[] r3 = {new Integer(3), &quot;FOO!&quot;, &quot;___&quot;};
 * 	putData(r1);
 * 	putData(r2);
 * 	putData(r3);
 * }</pre>
 * 
 * <p>Пример3: Параметры набора данных
 * 
 * <pre>
 * protected void doFillDataSet() {
 * 	Date docDate = (Date) getDataSetParameter("DocDate");
 * 	Object[] r1 = {(Integer) getEntityId(), &quot;BAR1&quot;, docDate};
 * 	putData(r1);
 * }</pre>
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: ReportBusinessAddin.java,v 1.15 2008/03/27 08:09:01 safonov Exp $
 */
public abstract class ReportBusinessAddin extends AbstractBusinessAddin<DataSet> {
	/**
	 * Метаданные для построения DataSet
	 */
	//private RelationInformation relationInfo;
	private String[] columnNames;
	private String[] columnTypes;
	/**
	 * идентификаторы выбранных сущностей, может быть <code>null</code>
	 */
	private Serializable[] entityIds;
	/**
	 * бизнес-компонет для которого генерируется отчет
	 */
	private BusinessObjectService businessService;
	/**
	 * набор данных
	 */
	private DataSet dataSet;
	/**
	 * Входные параметры отчёта
	 */
	private Map<String, ReportParameter> reportParameterValues;

	/**
	 * Входные параметры для конкретного набора данных
	 */
	private Map<String, Object> datataSetParameterValues;

	/**
	 * контекст выполнения отчета, в данном контексте возможно сохранять данные доступные
	 * для всех BAi участвующих в формировании отчета
	 */
	private Map<String, Object> reportContext;

	@SuppressWarnings("unchecked")
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		//relationInfo = (RelationInformation) params.get(DATASET_METADATA);
		columnNames = (String[]) params.get(DATASET_COLUMN_NAMES);
		columnTypes = (String[]) params.get(DATASET_COLUMN_TYPES);
		entityIds = (Serializable[]) params.get(ENTITY_IDS);
		businessService = (BusinessObjectService) params.get(BUSINESS_SERVICE);
		reportParameterValues = (Map<String, ReportParameter>) params.get(REPORT_PARAMS);
		datataSetParameterValues = (Map<String, Object>) params.get(DATASET_PARAMS);
		reportContext = (Map<String, Object>) params.get(REPORT_CONTEXT_PARAMS);
		prepareDataSetParams();
	}

	/**
	 * подготовка параметров
	 */
	private void prepareDataSetParams() {
		String[] paramNames = datataSetParameterValues.keySet().toArray(new String[datataSetParameterValues.size()]);
		for (String paramName : paramNames) {
			datataSetParameterValues.put(paramName,
					ReportUtils.convertParamValue(paramName, datataSetParameterValues.get(paramName), ServerUtils.getPersistentManager().getDelegate()));
		}
		Object ids = datataSetParameterValues.get(RptProperties.ENTITY_IDS_DATASET_PARAMETER);
		if (ids != null) {
			//параметр может быть массивом или одиночным значением, преобразуем одиночное в массив
			if (ids instanceof Serializable[])
				entityIds = (Serializable[]) ids;
			else
				entityIds = new Serializable[] {(Serializable) ids};
		}
		Object bService = datataSetParameterValues.get(RptProperties.BUSINESS_SERVICE_DATASET_PARAMETER);
		if (bService != null)
			businessService = (BusinessObjectService) bService;
	}

	/**
	 * создание DataSet на основе метаданных, содержащихся в {@link #relationInfo}
	 * 
	 * @throws ClassNotFoundException
	 */
	private void createDataSet() throws ClassNotFoundException {
		/*String[] colNames = relationInfo.getTableColumnNames();
		ColumnDef[] defFields = new ColumnDef[colNames.length];
		for (int i = 0; i < defFields.length; i++)
			defFields[i] = new ColumnDef(colNames[i], relationInfo.getTableColumnType(colNames[i]));*/
		ColumnDef[] defFields = new ColumnDef[columnNames.length];
		for (int i = 0; i < defFields.length; i++)
			defFields[i] = new ColumnDef(columnNames[i], columnTypes[i]);
		dataSet = new DataSetImpl(defFields);
	}

	/**
	 * добавление записи в набор данных
	 * 
	 * @param data	массив данных, представляющих собой одну запись набора данных
	 */
	protected void putData(Object[] data) {
		dataSet.addRow(data);
	}

	/**
	 * Установка значения поля fieldNum в value для текущей строки
	 * 
	 * @param fieldNum	номер столбца
	 * @param value	новое значение
	 */
	protected void setField(int fieldNum, Object value) {
		dataSet.setValueAt(value, fieldNum);
	}

	/**
	 * Установка значения поля fieldName в value для текущей строки
	 * 
	 * @param fieldName	имя столбца
	 * @param value	новое значение
	 */
	protected void setField(String fieldName, Object value) {
		dataSet.setValueAt(value, fieldName);
	}

	/**
	 * добавление новой пустой строки в набор данных
	 * 
	 * @return позиция курсора
	 */
	protected int addRow() {
		return dataSet.addRow(new Object[dataSet.getColumnsInfo().length]);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#doPerform()
	 */
	@Override
	protected final void doPerform() throws Exception {
		createDataSet();
		doFillDataSet();
		super.complete(dataSet);
	}

	/**
	 * заполнение набора данных, необходимо переопределять в классах наследниках
	 */
	protected abstract void doFillDataSet();

	/**
	 * получение идентификатора сущности для которой генерируется отчет
	 *
	 * @return идентификатор текущей сущности, может быть <code>null</code>
	 * @deprecated используйте {@link #getEntityId()}
	 */
	@Deprecated
	public Serializable getRecordId() {
		return getEntityId();
	}

	/**
	 * получение идентификатора сущности для которой генерируется отчет
	 * 
	 * @return	идентификатор первой сущности из списка идентификаторов или <code>null</code> если список пустой
	 */
	protected Serializable getEntityId() {
		if (entityIds != null && entityIds.length > 0)
			return entityIds[0];
		else
			return null;
	}
	
	/**
	 * получение списка идентификаторов сущностей для которых генерируется отчет
	 * 
	 * @return	список идентификаторов сущностей или <code>null</code> если список пустой
	 */
	protected Serializable[] getEntityIds() {
		return entityIds;
	}
	
	/**
	 * получение бизнес-компонента для которого генерируется отчет
	 * 
	 * @return	бизнес-компонент или <code>null</code> если не установлен
	 */
	protected BusinessObjectService getBusinessService() {
		return businessService;
	}
	
	/**
	 * получение списка имен параметров отчета
	 * 
	 * @return	список имен параметров
	 * 
	 * @deprecated
	 */
	@Deprecated
	public Set<String> getReportParamNames() {
		return reportParameterValues.keySet();
	}

	/**
	 * получение параметра отчёта по имени
	 * 
	 * @param name	имя параметра
	 * @return	значение параметра
	 * 
	 * @deprecated используйте {@link #getDataSetParameter(String)}
	 */
	@Deprecated
	public ReportParameter getReportParameter(String name) {
		return reportParameterValues.get(name);
	}

	/**
	 * установка значение параметра отчёта
	 * 
	 * @param name	имя параметра
	 * @param param	значение параметра
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void setReportParameter(String name, ReportParameter param) {
		reportParameterValues.put(name, param);
	}

	/**
	 * завершение выполнения алгоритма, необходимо вызвать после формирования набора данных
	 * 
	 * @deprecated	не требует вызова начиная с 4.0.3
	 */
	@Deprecated
	protected void complete() {
		//вызываем напрямую в doPerform, т.к. в данном типе BAi может быть только прямой порядок выполнения 
		//super.complete(dataSet);
	}

	/**
	 * получение списка имён параметров набора данных
	 * 
	 * @return список имён параметров набора данных
	 */
	protected Set<String> getDataSetParamNames() {
		return datataSetParameterValues.keySet();
	}

	/**
	 * получение параметра текущего набора данных по имени
	 * 
	 * @param name	имя параметра
	 * @return значение параметра
	 */
	protected Object getDataSetParameter(String name) {
		return datataSetParameterValues.get(name);
	}

	/**
	 * получить значение контекста
	 * 
	 * @param name	имя контекста
	 * @return	значение или <code>null</code> если не установлен
	 */
	protected Object getReportContextValue(String name) {
		return reportContext.get(name);
	}
	
	/**
	 * установить значение контекста
	 * 
	 * @param name	имя контекста
	 * @param value	значение контекста
	 */
	protected void setReportContextValue(String name, Object value) {
		reportContext.put(name, value);
	}
	
	/**
	 * получить список имен контекста
	 * 
	 * @return	список имен
	 */
	protected Set<String> getReportContextNames() {
		return reportContext.keySet();
	}
	
}
