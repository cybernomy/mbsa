/*
 * WarehouseTransactionDayBr.java
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

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.WarehouseTransactionDayServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.model.WarehouseTransactionDay;
import com.mg.merp.warehouse.support.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Операционный день"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTransactionDayBr.java,v 1.1 2007/11/29 08:48:48 alikaev Exp $
 */
public class WarehouseTransactionDayBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from WarehouseTransactionDay wtd where %s";     //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Warehouse warehouse;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(WarehouseTransactionDay.class, "Id", "wtd.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WarehouseTransactionDay.class, "Stock", "wtd.Stock", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WarehouseTransactionDay.class, "ClosedDay", "wtd.ClosedDay", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WarehouseTransactionDay.class, "UserStockClosed", "wtd.UserStockClosed", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WarehouseTransactionDay.class, "OperationDate", "wtd.OperationDate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    };
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("stock");
    paramsValue.add(warehouse);
    StringBuilder whereText = new StringBuilder("(0 = 0)")
        .append(" and (wtd.Stock = :stock)");
    return String.format(INIT_QUERY_TEXT, fieldsList, whereText);
  }

  /**
   * показать форму
   */
  public void execute(Serializable warehouseId) {
    Warehouse warehouse = ServerUtils.getPersistentManager().find(Warehouse.class, warehouseId);
    this.warehouse = warehouse;
    uiProperties.put("Stock", warehouse); //$NON-NLS-1$;
    setTitle(Messages.getInstance().getMessage(Messages.WH_TRANSACTION_DAY_BR_TITLE, new String[]{warehouse.getFullName().trim()}));
    run();
  }

  /**
   * Обработчик события "Закрыть день"
   */
  public void onActionCloseDay(WidgetEvent event) throws Exception {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    WarehouseTransactionDayServiceLocal wtdService = (WarehouseTransactionDayServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseTransactionDayServiceLocal.SERVICE_NAME);
    wtdService.closeTransactionDay(keys);
    table.refresh();
  }

  /**
   * Обработчик события "Открыть день"
   */
  public void onActionOpenDay(WidgetEvent event) throws Exception {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    WarehouseTransactionDayServiceLocal wtdService = (WarehouseTransactionDayServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseTransactionDayServiceLocal.SERVICE_NAME);
    wtdService.openTransactionDay(keys);
    table.refresh();
  }

}
