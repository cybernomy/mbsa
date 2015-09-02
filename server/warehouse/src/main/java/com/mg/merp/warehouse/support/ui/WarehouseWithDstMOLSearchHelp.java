/*
 * WarehouseWithDstMOLSearchHelp.java
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
package com.mg.merp.warehouse.support.ui;

/**
 * Поисковик склада-приемника.
 * После выбора склада-приемника осуществляется установка МОЛ(по умолчанию) для склада-приемника
 *  
 * @author Artem V. Sharapov
 * @version $Id: WarehouseWithDstMOLSearchHelp.java,v 1.1 2007/11/12 10:32:45 sharapov Exp $
 */
public class WarehouseWithDstMOLSearchHelp extends WarehouseWithMOLSearchHelp {
	
	private final String DST_MOL_EXPORT = "DstMol"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.WarehouseWithMOLSearchHelp#getMolExportAttribute()
	 */
	@Override
	String getMolExportAttribute() {
		return DST_MOL_EXPORT;
	}

}
