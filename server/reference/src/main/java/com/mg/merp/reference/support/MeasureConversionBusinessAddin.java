/*
 * MeasureConversionBusinessAddin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;

/**
 * ������� ����� ������-���������� �������������� ������ ���������. ����� ���������� ������
 * ������������� ��������� ����� <code>protected void doPerform() throws Exception</code>.
 * ����� ���������� ����������� ����������.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected void doPerform() throws Exception {
 *     complete(MoonUtils.getMoonPhase(getConvTime()).multiply(getValueFrom()));
 * }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: MeasureConversionBusinessAddin.java,v 1.1 2007/06/20 13:56:53 safonov Exp $
 */
public abstract class MeasureConversionBusinessAddin extends AbstractBusinessAddin<BigDecimal> {
	public final static String MEASURE_FROM_PARAM = "MEASURE_FROM_PARAM"; //$NON-NLS-1$
	public final static String MEASURE_TO_PARAM = "MEASURE_TO_PARAM"; //$NON-NLS-1$
	public final static String CATALOG_PARAM = "CATALOG_PARAM"; //$NON-NLS-1$
	public final static String VALUE_FROM_PARAM = "VALUE_FROM_PARAM"; //$NON-NLS-1$
	public final static String CONV_TIME_PARAM = "CONV_TIME_PARAM"; //$NON-NLS-1$
	
	private Measure measureFrom;
	private Measure measureTo;
	private Catalog catalog;
	private BigDecimal valueFrom;
	private Date convTime;

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		measureFrom = (Measure) params.get(MEASURE_FROM_PARAM);
		measureTo = (Measure) params.get(MEASURE_TO_PARAM);
		catalog = (Catalog) params.get(CATALOG_PARAM);
		valueFrom = (BigDecimal) params.get(VALUE_FROM_PARAM);
		convTime = (Date) params.get(CONV_TIME_PARAM);
	}

	/**
	 * �������� ������� �������� ��� ������� ���������� ��������������
	 * 
	 * @return ������� ��������
	 */
	protected Catalog getCatalog() {
		return catalog;
	}

	/**
	 * �������� ����� �� ������� ���������� ��������������
	 * 
	 * @return ����� ��������������
	 */
	protected Date getConvTime() {
		return convTime;
	}

	/**
	 * �������� �� �� ������� ���������� ��������������
	 * 
	 * @return �� ��������
	 */
	protected Measure getMeasureFrom() {
		return measureFrom;
	}

	/**
	 * �������� �� � ������� ���������� ��������������
	 * 
	 * @return �� ��������
	 */
	protected Measure getMeasureTo() {
		return measureTo;
	}

	/**
	 * �������� ��������� ����������
	 * 
	 * @return ��������� ����������
	 */
	protected BigDecimal getValueFrom() {
		return valueFrom;
	}

}
