/*
 * CatalogPriceServiceLocal.java
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
package com.mg.merp.reference;

import java.util.Date;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.Currency;

/**
 * ������-��������� "����������� ����"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: CatalogPriceServiceLocal.java,v 1.2 2007/07/27 12:08:16 safonov Exp $
 */
public interface CatalogPriceServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CatalogPrice, Integer>
{
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/reference/CatalogPrice";
	
	/**
	 * ����� ���������� ����������� ����
	 * 
	 * @param actualityDate	���� ������������
	 * @param catalog	������� ��������
	 * @param currency	������
	 * @return	����������� ����
	 */
	CatalogPrice findActual(Date actualityDate, Catalog catalog, Currency currency);

}
