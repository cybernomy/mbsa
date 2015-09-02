/*
 * LiabilityServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import java.math.BigDecimal;

import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.paymentcontrol.model.Liability;

/**
 * ������ ������-���������� "������ ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityServiceLocal.java,v 1.5 2007/06/04 05:06:19 sharapov Exp $
 */
public interface LiabilityServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Liability, Integer> {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Liability"; //$NON-NLS-1$
	
	/**
	 * ��� ����� ��� ������� ������������
	 */
	final static short FOLDER_PART = 13401;

	/**
	 * �������� ������������� �� �������
	 * @param pattern - �������
	 * @param folder - �����-��������
	 * @return �������������
	 */
	Liability createByPattern(Liability pattern, Folder folder);
	
	/**
	 * �������� ����������� �����
	 * @param liabilityId - ������������� �������������
	 * @param versionId - ������������� ������ ������������
	 * @return �����
	 */
	BigDecimal getExecutedSum(Integer liabilityId, Integer versionId);
	
	/**
	 * �������� ������������� ������� �������������
	 * @param liabilityId - ������������ �������������
	 * @param versionId - ������������ ������ ������������
	 * @return ������������� �������
	 */
	BigDecimal getRemnSum(Integer liabilityId, Integer versionId);
	
	/**
	 * �������� �������� ����� ������� ������������
	 * @return �������� ����� ������� ������������
	 */
	Folder getRootFolder();
		
	/**
	 * ����� �������� (��� ���������)
	 * @param liability - �������������
	 * @return ��������
	 */
	DocHead findDoc(Liability liability);
	
	/**
	 * ����� ��������-��������� (��� ���������)
	 * @param liability - �������������
	 * @return ��������-���������
	 */
	DocHead findBaseDoc(Liability liability);
	
	/**
	 * ����� �������� (��� ���������)
	 * @param liability - �������������
	 * @return ��������
	 */
	DocHead findContract(Liability liability);
	
}
