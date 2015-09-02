/*
 * ZipCodeServiceLocal.java
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

import com.mg.merp.reference.model.ZipCode;

/**
 * ������ ������-���������� "������"
 * 
 * @author leonova
 * @version $Id: ZipCodeServiceLocal.java,v 1.3 2008/09/23 09:16:56 safonov Exp $
 */
public interface ZipCodeServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ZipCode, Integer> {

	/**
	 * ����� �� ����
	 * 
	 * @param code	���
	 * @return	������ ��� <code>null</code> ���� �� ������
	 */
	public ZipCode findByCode(String code);

}
