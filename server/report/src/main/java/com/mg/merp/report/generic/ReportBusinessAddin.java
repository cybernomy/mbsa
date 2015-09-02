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
 * ������� ����� BAi ������������ ������ ������ ��� ���������� �������. �����
 * ��������� ������ ������������� ��������� �����
 * <code>protected void doFillDataSet()</code> � ���������� ���������� �����
 * ������, �������������� �� ���� {@link com.mg.framework.api.dataset.DataSet DataSet}. ��������
 * ����� ������ � ���������� ������ ������ ����� ����� {@link #getDataSetParameter(String)}. ������ � ��������������
 * �������� ��������� �������������� ������� ������ {@link #getEntityIds()}, ������������� ������ �������� ����
 * java.io.Serializable.
 * 
 * <p>������1:
 * 
 * <pre>
 * protected void doFillDataSet() {
 * 	Set&lt;String&gt; keys = getParamNames();
 * 	for (String s : keys)
 * 		setParameter(s, &quot;param_&quot; + s);
 * 	addRow();
 * 	setField(&quot;v2&quot;, (Integer) getEntityId());
 * 	setField(&quot;code&quot;, &quot;������&quot;);
 * 	addRow();
 * 	setField(&quot;v2&quot;, new Integer(777));
 * 	setField(&quot;code&quot;, &quot;string777&quot;);
 * }</pre>
 * 
 * <p>������2:
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
 * <p>������3: ��������� ������ ������
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
	 * ���������� ��� ���������� DataSet
	 */
	//private RelationInformation relationInfo;
	private String[] columnNames;
	private String[] columnTypes;
	/**
	 * �������������� ��������� ���������, ����� ���� <code>null</code>
	 */
	private Serializable[] entityIds;
	/**
	 * ������-�������� ��� �������� ������������ �����
	 */
	private BusinessObjectService businessService;
	/**
	 * ����� ������
	 */
	private DataSet dataSet;
	/**
	 * ������� ��������� ������
	 */
	private Map<String, ReportParameter> reportParameterValues;

	/**
	 * ������� ��������� ��� ����������� ������ ������
	 */
	private Map<String, Object> datataSetParameterValues;

	/**
	 * �������� ���������� ������, � ������ ��������� �������� ��������� ������ ���������
	 * ��� ���� BAi ����������� � ������������ ������
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
	 * ���������� ����������
	 */
	private void prepareDataSetParams() {
		String[] paramNames = datataSetParameterValues.keySet().toArray(new String[datataSetParameterValues.size()]);
		for (String paramName : paramNames) {
			datataSetParameterValues.put(paramName,
					ReportUtils.convertParamValue(paramName, datataSetParameterValues.get(paramName), ServerUtils.getPersistentManager().getDelegate()));
		}
		Object ids = datataSetParameterValues.get(RptProperties.ENTITY_IDS_DATASET_PARAMETER);
		if (ids != null) {
			//�������� ����� ���� �������� ��� ��������� ���������, ����������� ��������� � ������
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
	 * �������� DataSet �� ������ ����������, ������������ � {@link #relationInfo}
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
	 * ���������� ������ � ����� ������
	 * 
	 * @param data	������ ������, �������������� ����� ���� ������ ������ ������
	 */
	protected void putData(Object[] data) {
		dataSet.addRow(data);
	}

	/**
	 * ��������� �������� ���� fieldNum � value ��� ������� ������
	 * 
	 * @param fieldNum	����� �������
	 * @param value	����� ��������
	 */
	protected void setField(int fieldNum, Object value) {
		dataSet.setValueAt(value, fieldNum);
	}

	/**
	 * ��������� �������� ���� fieldName � value ��� ������� ������
	 * 
	 * @param fieldName	��� �������
	 * @param value	����� ��������
	 */
	protected void setField(String fieldName, Object value) {
		dataSet.setValueAt(value, fieldName);
	}

	/**
	 * ���������� ����� ������ ������ � ����� ������
	 * 
	 * @return ������� �������
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
	 * ���������� ������ ������, ���������� �������������� � ������� �����������
	 */
	protected abstract void doFillDataSet();

	/**
	 * ��������� �������������� �������� ��� ������� ������������ �����
	 *
	 * @return ������������� ������� ��������, ����� ���� <code>null</code>
	 * @deprecated ����������� {@link #getEntityId()}
	 */
	@Deprecated
	public Serializable getRecordId() {
		return getEntityId();
	}

	/**
	 * ��������� �������������� �������� ��� ������� ������������ �����
	 * 
	 * @return	������������� ������ �������� �� ������ ��������������� ��� <code>null</code> ���� ������ ������
	 */
	protected Serializable getEntityId() {
		if (entityIds != null && entityIds.length > 0)
			return entityIds[0];
		else
			return null;
	}
	
	/**
	 * ��������� ������ ��������������� ��������� ��� ������� ������������ �����
	 * 
	 * @return	������ ��������������� ��������� ��� <code>null</code> ���� ������ ������
	 */
	protected Serializable[] getEntityIds() {
		return entityIds;
	}
	
	/**
	 * ��������� ������-���������� ��� �������� ������������ �����
	 * 
	 * @return	������-��������� ��� <code>null</code> ���� �� ����������
	 */
	protected BusinessObjectService getBusinessService() {
		return businessService;
	}
	
	/**
	 * ��������� ������ ���� ���������� ������
	 * 
	 * @return	������ ���� ����������
	 * 
	 * @deprecated
	 */
	@Deprecated
	public Set<String> getReportParamNames() {
		return reportParameterValues.keySet();
	}

	/**
	 * ��������� ��������� ������ �� �����
	 * 
	 * @param name	��� ���������
	 * @return	�������� ���������
	 * 
	 * @deprecated ����������� {@link #getDataSetParameter(String)}
	 */
	@Deprecated
	public ReportParameter getReportParameter(String name) {
		return reportParameterValues.get(name);
	}

	/**
	 * ��������� �������� ��������� ������
	 * 
	 * @param name	��� ���������
	 * @param param	�������� ���������
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void setReportParameter(String name, ReportParameter param) {
		reportParameterValues.put(name, param);
	}

	/**
	 * ���������� ���������� ���������, ���������� ������� ����� ������������ ������ ������
	 * 
	 * @deprecated	�� ������� ������ ������� � 4.0.3
	 */
	@Deprecated
	protected void complete() {
		//�������� �������� � doPerform, �.�. � ������ ���� BAi ����� ���� ������ ������ ������� ���������� 
		//super.complete(dataSet);
	}

	/**
	 * ��������� ������ ��� ���������� ������ ������
	 * 
	 * @return ������ ��� ���������� ������ ������
	 */
	protected Set<String> getDataSetParamNames() {
		return datataSetParameterValues.keySet();
	}

	/**
	 * ��������� ��������� �������� ������ ������ �� �����
	 * 
	 * @param name	��� ���������
	 * @return �������� ���������
	 */
	protected Object getDataSetParameter(String name) {
		return datataSetParameterValues.get(name);
	}

	/**
	 * �������� �������� ���������
	 * 
	 * @param name	��� ���������
	 * @return	�������� ��� <code>null</code> ���� �� ����������
	 */
	protected Object getReportContextValue(String name) {
		return reportContext.get(name);
	}
	
	/**
	 * ���������� �������� ���������
	 * 
	 * @param name	��� ���������
	 * @param value	�������� ���������
	 */
	protected void setReportContextValue(String name, Object value) {
		reportContext.put(name, value);
	}
	
	/**
	 * �������� ������ ���� ���������
	 * 
	 * @return	������ ����
	 */
	protected Set<String> getReportContextNames() {
		return reportContext.keySet();
	}
	
}
