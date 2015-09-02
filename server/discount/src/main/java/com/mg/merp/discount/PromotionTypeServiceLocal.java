/*
 * PromotionTypeServiceLocal.java
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
package com.mg.merp.discount;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.discount.model.PromotionType;

/**
 * ������-��������� "��� ���������� �����������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionTypeServiceLocal.java,v 1.1 2007/10/30 13:55:56 sharapov Exp $
 */
public interface PromotionTypeServiceLocal extends DataBusinessObjectService<PromotionType, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/discount/PromotionType"; //$NON-NLS-1$
	
}
