/*
 * BinLocationSearchForm.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Секции хранения"
 *
 * @author Artem V. Sharapov
 * @version $Id: BinLocationSearchForm.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class BinLocationSearchForm extends AbstractSearchForm {

  protected MaintenanceTableController table;
  private AttributeMap binLocationProperties = new LocalDataTransferObject();
  private BinLocationServiceLocal binLocationService = (BinLocationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BinLocationServiceLocal.LOCAL_SERVICE_NAME);
  private Warehouse warehouse;


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    binLocationProperties.put("Warehouse", warehouse); //$NON-NLS-1$
    table = new MaintenanceTableController(binLocationProperties);
    table.initController(binLocationService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from BinLocation bl %s where bl.Warehouse = :warehouse"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        StringBuilder whereText = new StringBuilder();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("warehouse"); //$NON-NLS-1$
        paramsValue.add(warehouse);
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(BinLocation.class, "Id", "bl.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "Code", "bl.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "Description", "bl.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "Type", "t.Code", "left join bl.Type as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "Zone", "z.Code", "left join bl.Zone as z", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "VolumeMeasure", "vm.Code", "left join bl.VolumeMeasure as vm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteVolume", "bl.InfiniteVolume", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumVolume", "bl.MaximumVolume", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "WeightMeasure", "wm.Code", "left join bl.WeightMeasure as wm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteWeight", "bl.InfiniteWeight", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumWeight", "bl.MaximumWeight", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "QuanMeasure", "qm.Code", "left join bl.QuanMeasure as qm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteQuan", "bl.InfiniteQuan", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumQuan", "bl.MaximumQuan", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, binLocationService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    super.doOnRun();
    if (warehouse != null)
      setTitle(Messages.getInstance().getMessage(Messages.BINLOCATION_BR_TITLE, new String[]{warehouse.getFullName().trim()}));
    table.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    Serializable[] selectedPrimaryKeys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (selectedPrimaryKeys == null || selectedPrimaryKeys.length == 0)
      return new PersistentObject[0];
    else {
      PersistentObject[] searchedEntities = new PersistentObject[selectedPrimaryKeys.length];
      for (int i = 0; i < searchedEntities.length; i++)
        searchedEntities[i] = binLocationService.load((Integer) selectedPrimaryKeys[i]);
      return searchedEntities;
    }
  }

  /**
   * Установить склад для отображения секций хранения
   *
   * @param warehouse - склад
   */
  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#run()
   */
  @Override
  public void run() {
    if (warehouse != null)
      super.run();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#run(boolean)
   */
  @Override
  public void run(boolean modal) {
    if (warehouse != null)
      super.run(modal);
  }

}
