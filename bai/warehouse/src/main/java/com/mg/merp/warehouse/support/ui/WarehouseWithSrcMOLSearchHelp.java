/*
 * WarehouseWithSrcMOLSearchHelp.java
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
 * Поисковик склада-источника. После выбора склада-источника осуществляется установка МОЛ(по
 * умолчанию) для склада-источника
 *
 * @author Artem V. Sharapov
 * @version $Id: WarehouseWithSrcMOLSearchHelp.java,v 1.1 2007/11/12 10:32:45 sharapov Exp $
 */
public class WarehouseWithSrcMOLSearchHelp extends WarehouseWithMOLSearchHelp {

  private final String SRC_MOL_EXPORT = "SrcMol"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.support.ui.WarehouseWithMOLSearchHelp#getMolExportAttribute()
   */
  @Override
  String getMolExportAttribute() {
    return SRC_MOL_EXPORT;
  }

}
