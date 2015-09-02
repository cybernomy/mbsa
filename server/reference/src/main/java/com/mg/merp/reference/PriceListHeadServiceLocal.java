/*
 * PriceListHeadServiceLocal.java
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
package com.mg.merp.reference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.reference.model.PriceListHead;

/**
 * ������-��������� "��������� �����-������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PriceListHeadServiceLocal.java,v 1.3 2009/02/11 14:35:47 sharapov Exp $
 */
public interface PriceListHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PriceListHead, Integer> {
	
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/reference/PriceListHead"; //$NON-NLS-1$
	
	/**
	 * ����������� ���� ������������ �����-�����
	 * @param priceListHeadIds - ������ ��������������� �����-������
	 */
	void recalcPrices(Serializable[] priceListHeadIds);
	
	/**
	 * ��������� ����������
	 * @param priceListHeadId - ������������� �����-�����
	 * @param actualDate - ���� ������������
	 * @param percent - �������
	 * @param precision - �������� ����������
	 */
	void overestimation(Serializable priceListHeadId, Date actualDate, BigDecimal percent, Integer precision);
	
	byte[] loadPriceTypeBrowse(int aPriceListId) throws com.mg.framework.api.ApplicationException;

	void addPriceType(int aPriceListId, int aPriceTypeId, short aPriority, java.lang.String aFormula) throws com.mg.framework.api.ApplicationException;

	void deletePriceType(int aPriceListId, int aPriceTypeId) throws com.mg.framework.api.ApplicationException;

	void updatePriceType(int aPriceListId, int aPriceTypeId, short priority, java.lang.String aFormula) throws com.mg.framework.api.ApplicationException;

}
