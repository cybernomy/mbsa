/*
 * WarehouseSrcSearchHelp.java
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

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Базовый класс поисковика склада-приемника с установкой МОЛ(по умолчанию)
 *
 * @author Artem V. Sharapov
 * @version $Id: WarehouseWithMOLSearchHelp.java,v 1.1 2007/11/12 10:32:45 sharapov Exp $
 */
public abstract class WarehouseWithMOLSearchHelp extends WarehouseSearchHelp {

  private WarehouseServiceLocal warehouseService = (WarehouseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);

  /**
   * Получить аттрибут МОЛ для экспорта
   *
   * @return аттрибут МОЛ для экспорта
   */
  abstract String getMolExportAttribute();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{getMolExportAttribute()};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    Warehouse warehouse = (Warehouse) event.getItems()[0];
    if (warehouse != null)
      setExportContextValue(getMolExportAttribute(), warehouseService.getWarehouseDefaultMOL(warehouse));
  }

}
