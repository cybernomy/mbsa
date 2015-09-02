/*
 * BillHeadInServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.BillHead;

/**
 * ������ - ��������� "�������� �����"
 * 
 * @author leonova
 * @version $Id: BillHeadInServiceLocal.java,v 1.3 2006/09/20 11:02:09 safonov Exp $
 */
public interface BillHeadInServiceLocal
   extends com.mg.merp.document.GoodsDocument<BillHead, Integer, BillHeadModelInServiceLocal, BillSpecInServiceLocal>
{
	/**
	 * ��� ����� ��� �������� ������
	 */
	final static short FOLDER_PART = 49;
	
	/**
	 * docsection ��� �������� ������
	 */	
	final static short DOCSECTION = 25;
}
