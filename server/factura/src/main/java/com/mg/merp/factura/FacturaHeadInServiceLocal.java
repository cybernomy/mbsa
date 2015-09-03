/*
 * FacturaHeadInServiceLocal.java
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
package com.mg.merp.factura;

import com.mg.merp.factura.model.FacturaHead;

/**
 * 
 * @author leonova
 * @version $Id: FacturaHeadInServiceLocal.java,v 1.3 2006/09/20 10:53:41 safonov Exp $
 */
public interface FacturaHeadInServiceLocal
   extends com.mg.merp.document.GoodsDocument<FacturaHead, Integer, FacturaHeadModelInServiceLocal, FacturaSpecInServiceLocal>
{
	/**
	 * тип папки для входящих счет - фактур
	 */
	final static short FOLDER_PART = 21;
	
	/**
	 * docsection для входящих счет - фактур
	 */
	final static short DOCSECTION = 12;
}
