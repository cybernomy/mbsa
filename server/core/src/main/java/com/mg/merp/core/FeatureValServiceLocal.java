/*
 * FeatureValServiceLocal.java
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
package com.mg.merp.core;

import com.mg.merp.core.model.FeatureVal;

/**
 * ������-��������� "�������� ���������������� �����"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: FeatureValServiceLocal.java,v 1.3 2007/01/25 15:40:10 safonov Exp $
 */
public interface FeatureValServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<FeatureVal, Integer>
{

	/**
	 * �������� �������� ������������� ����
	 * 
	 * @param featureId	������������� ��������� ����������������� ����
	 * @return	��������
	 */
	String[] loadEnumValues(int featureId);

}
