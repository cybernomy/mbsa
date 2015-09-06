/*
 * BinLocationSearchHelp.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;

import java.io.Serializable;

/**
 * Механизм поиска сущностей "Секции хранения"
 *
 * @author Artem V. Sharapov
 * @version $Id: BinLocationSearchHelp.java,v 1.4 2008/07/15 08:24:22 safonov Exp $
 */
public class BinLocationSearchHelp extends AbstractSearchHelp {

  private final String WAREHOUSE_IMPORT_NAME = "warehouse"; //$NON-NLS-1$
  private final String SEARCH_FORM_NAME = "com.mg.merp.warehouse.BinLocationSearchForm"; //$NON-NLS-1$
  protected BinLocationServiceLocal binLocationService = (BinLocationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BinLocationServiceLocal.LOCAL_SERVICE_NAME);


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    BinLocationSearchForm binLocationSearchForm = (BinLocationSearchForm) ApplicationDictionaryLocator.locate().getWindow(SEARCH_FORM_NAME);
    binLocationSearchForm.setWarehouse(getWarehouseContextValue());
    binLocationSearchForm.addSearchHelpListener(this);
    binLocationSearchForm.run(UIUtils.isModalMode());
  }

  /**
   * Получить "Склад" из контекста импорта
   *
   * @return "Склад" из контекста импорта
   */
  protected Warehouse getWarehouseContextValue() {
    return (Warehouse) getImportContextValue(WAREHOUSE_IMPORT_NAME);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{WAREHOUSE_IMPORT_NAME};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    MaintenanceHelper.view(binLocationService, (Serializable) entity.getPrimaryKey(), null, null);
  }

}
