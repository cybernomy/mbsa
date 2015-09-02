/*
 * ItemSpecServiceLocal.java
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
package com.mg.merp.lbschedule;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecCreateData;

/**
 * ������ ������-���������� "������������ ������ ������� ���������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecServiceLocal.java,v 1.2 2007/04/17 12:48:40 sharapov Exp $
 */
public interface ItemSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ItemSpec, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/ItemSpec"; //$NON-NLS-1$
	
	/**
	 * �������� ������� ������������ ������ ������� ���������� ������������
	 * @param item - ����� �������
	 * @param itemSpecCreateData - ������ ��� �������� ������� ������������
	 */
	void addSpec(Item item, ItemSpecCreateData[] itemSpecCreateData);

	/**
	 * ������� ������� ������������ ������ ������� ���������� ������������
	 * @param specIDs - �������������� ������� ������������
	 */
	void removeSpec(Serializable[] specIDs);
	
	/**
	 * ����������� ����� ������� ������������
	 * @param itemSpec - ������� ������������
	 * @param oldQuantity
	 */
	void recomputeSum(ItemSpec itemSpec, BigDecimal oldQuantity);

}
