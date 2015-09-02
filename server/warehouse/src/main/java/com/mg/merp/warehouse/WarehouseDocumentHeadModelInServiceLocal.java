/*
 * WarehouseDocumentHeadModelInServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.warehouse;

import com.mg.merp.document.DocumentPattern;
import com.mg.merp.warehouse.model.StockDocumentHeadModel;

/**
 * 
 * @author leonova
 * @version $Id: WarehouseDocumentHeadModelInServiceLocal.java,v 1.3 2006/09/20 11:02:09 safonov Exp $
 */
public interface WarehouseDocumentHeadModelInServiceLocal
   extends DocumentPattern<StockDocumentHeadModel, Integer>
{
	/**
	 * ��� ����� ��� �������� ��������� �������
	 */
	final static short FOLDER_PART = 32;
}
