/*
 * VersionStatusServiceLocal.java
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

import java.util.Date;

import com.mg.merp.paymentcontrol.model.Version;
import com.mg.merp.paymentcontrol.model.VersionStatus;
import com.mg.merp.paymentcontrol.model.VersionStatusKind;

/**
 * ������ ������-���������� "������ ������ ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: VersionStatusServiceLocal.java,v 1.2 2007/05/14 04:59:59 sharapov Exp $
 */
public interface VersionStatusServiceLocal extends com.mg.framework.api.DataBusinessObjectService<VersionStatus, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/VersionStatus"; //$NON-NLS-1$
	
	/**
	 * �������� ������ ������
	 * @param version - ������ ������������
	 * @param kind - ������
	 * @param createDate - ���� ��������
	 * @param dateFrom - ���� � 
	 * @param dateTill - ���� ��
	 */
	void addVersionStatus(Version version, VersionStatusKind kind, Date createDate, Date dateFrom, Date dateTill);
	
}
