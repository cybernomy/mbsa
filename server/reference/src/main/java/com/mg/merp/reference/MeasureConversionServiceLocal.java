/*
 * MeasureConversionServiceLocal.java
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
package com.mg.merp.reference;

import java.math.BigDecimal;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.MeasureConversion;

/**
 * ������ ������-��������� �������������� ��
 * 
 * @author leonova
 * @version $Id: MeasureConversionServiceLocal.java,v 1.2 2006/12/02 13:32:40
 *          safonov Exp $
 */
public interface MeasureConversionServiceLocal
		extends
		com.mg.framework.api.DataBusinessObjectService<MeasureConversion, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/reference/MeasureConversion";

	/**
	 * �������������� ��
	 * 
	 * @param measureFrom
	 *            �� ��������
	 * @param measureTo
	 *            �� ��������
	 * @param catalog
	 *            ������� �������� ��� ������� ���������� ��������������
	 * @param convTime
	 *            ����� �� ������� ���������� ��������������
	 * @param valueFrom
	 *            ���������� � ������� ��
	 * @return ���������� � �������������� ��
	 * @throws InvalidMeasureConversion
	 *             � ������ ������ ��������������
	 */
	BigDecimal conversion(Measure measureFrom, Measure measureTo,
			Catalog catalog, java.util.Date convTime, BigDecimal valueFrom)
			throws InvalidMeasureConversion;

}
