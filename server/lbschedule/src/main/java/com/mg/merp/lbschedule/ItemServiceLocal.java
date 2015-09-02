/*
 * ItemServiceLocal.java
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

import com.mg.merp.lbschedule.model.Item;

/**
 * ������ ������-���������� "������ ������� ���������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemServiceLocal.java,v 1.2 2007/04/17 12:48:40 sharapov Exp $
 */
public interface ItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Item, Integer> {
   
	/**
    * ������ ���� ������ ������
    * @param item - ����� �������
    */
   void computeResultDate(Item item);
   
   /**
    * ������ ����� ������
    * @param item - ����� �������
    */
   void computeResultSum(Item item);

}
