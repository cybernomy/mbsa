/*
 * CatalogServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Catalog;

/**
 * ������-��������� "������� ������� � �����"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: CatalogServiceLocal.java,v 1.2 2007/08/10 13:35:15 safonov Exp $
 */
public interface CatalogServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<Catalog, Integer> {

	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/reference/Catalog"; //$NON-NLS-1$

	/**
	 * ����� �� ����
	 * 
	 * @param code	���
	 * @return	������� �������� ��� <code>null</code> ���� �� �������
	 */
	Catalog findByCode(String code);

	/**
	 * ����� �� �����-����
	 * 
	 * @param barCode	����� ���
	 * @return	������� �������� ��� <code>null</code> ���� �� �������
	 */
	Catalog findByBarCode(String barCode);

}
