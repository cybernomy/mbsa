/* WarehouseMaintenanceTableController.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.support.ui.OrgUnitSearchHelp;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;

import java.io.Serializable;

/**
 * Реализация адаптера таблицы поддержки складов
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseMaintenanceTableController.java,v 1.2 2008/05/23 16:10:21 safonov Exp $
 */
public class WarehouseMaintenanceTableController extends
    MaintenanceTableController {

  private WarehouseServiceLocal whServ;

  public WarehouseMaintenanceTableController(AttributeMap uiProperties) {
    super(uiProperties);
    whServ = (WarehouseServiceLocal) ApplicationDictionaryLocator
        .locate().getBusinessService(
            WarehouseServiceLocal.LOCAL_SERVICE_NAME);
  }

  @Override
  protected void doAdd() {
    OrgUnitSearchHelp searchHelp = (OrgUnitSearchHelp) SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.OrgUnitSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      private int id;

      public void searchCanceled(SearchHelpEvent event) {

      }

      public void searchPerformed(SearchHelpEvent event) {
        Warehouse warehouse = whServ.addWarehouse((OrgUnit) event.getItems()[0]);
        id = warehouse.getId();
        MaintenanceHelper.edit(whServ, (PersistentObject) warehouse, null, new MaintenanceFormActionListener() {
          public void performed(MaintenanceFormEvent event) {
            refresh();
          }

          public void canceled(MaintenanceFormEvent event) {
            whServ.eraseWarehouse(id);
          }
        });
      }

    });
    searchHelp.search();
  }

  @Override
  protected void doErase() {
    Serializable[] keys = ((MaintenanceTableModel) getModel()).getSelectedPrimaryKeys();
    whServ.eraseWarehouse((Integer) keys[0]);
    refresh();
  }

}
