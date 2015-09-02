/*
 * CardServiceLocal.java
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

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.discount.model.Card;

/**
 * ������-��������� "���������� �����"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardServiceLocal.java,v 1.4 2007/10/05 07:21:13 sharapov Exp $
 */
public interface CardServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Card, Integer> {
	
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME= "merp/discount/Card"; //$NON-NLS-1$ 
	
	/**
	 * ��� ����� ��� ���������� ����
	 */
	final static short FOLDER_PART = 13100;
	
	/**
	 * �������� ������ �� ������� ���������� ����� �� ����
	 * @param disCard - ���������� �����
	 * @param actualDate - ���� �����������
	 * @return ������ �� ������� ���������� ����� �� ����
	 */
	BigDecimal getDiscountFromHistory(Card disCard, Date actualDate);

	com.mg.framework.api.AttributeMap getCardByOwner(int ownerId);

	int[] getCardsByContractor(int contractorId);

}
