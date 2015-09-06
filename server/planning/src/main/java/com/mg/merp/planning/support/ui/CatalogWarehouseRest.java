/*
 * WarehouseCatalogRest.java
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
package com.mg.merp.planning.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Контроллер формы условий отбора складов хранения товаров
 *
 * @author leonova
 * @version $Id: CatalogWarehouseRest.java,v 1.4 2006/10/16 10:53:29 leonova Exp $
 */
public class CatalogWarehouseRest extends DefaultRestrictionForm {

  @DataItemName("Planning.CatWarehouse.Contractor")
  private Warehouse warehouseCode = null;
  private Catalog catalogCode = null;
  @DataItemName("Planning.Cond.CatWarehouse.MrpDampingDaysFrom")
  private java.lang.Short mrpDampingDaysFrom = null;
  @DataItemName("Planning.Cond.CatWarehouse.MrpDampingDaysTill")
  private java.lang.Short mrpDampingDaysTill = null;

  @Override
  protected void doClearRestrictionItem() {
    this.warehouseCode = null;
    this.catalogCode = null;
    this.mrpDampingDaysFrom = null;
    this.mrpDampingDaysTill = null;
  }

  /**
   * @return Returns the catalogCode.
   */
  protected Catalog getCatalogCode() {
    return catalogCode;
  }

  /**
   * @return Returns the mrpDampingDaysFrom.
   */
  protected java.lang.Short getMrpDampingDaysFrom() {
    return mrpDampingDaysFrom;
  }

  /**
   * @return Returns the mrpDampingDaysTill.
   */
  protected java.lang.Short getMrpDampingDaysTill() {
    return mrpDampingDaysTill;
  }

  /**
   * @return Returns the warehouseCode.
   */
  protected Warehouse getWarehouseCode() {
    return warehouseCode;
  }


}
