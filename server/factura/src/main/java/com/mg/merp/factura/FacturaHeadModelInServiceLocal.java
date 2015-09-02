/*
 * FacturaHeadModelInServiceLocal.java
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
package com.mg.merp.factura;

import com.mg.merp.document.DocumentPattern;
import com.mg.merp.factura.model.FacturaHeadModel;

/**
 * 
 * @author leonova
 * @version $Id: FacturaHeadModelInServiceLocal.java,v 1.3 2006/09/20 10:53:41 safonov Exp $
 */
public interface FacturaHeadModelInServiceLocal
   extends DocumentPattern<FacturaHeadModel, Integer>
{
	/**
	 * тип папки для образцов входящих счет - фактур
	 */
	final static short FOLDER_PART = 22;
}
