/*
 * ItemSpecMt.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.lbschedule.ItemSpecAltServiceLocal;
import com.mg.merp.lbschedule.ItemSpecServiceLocal;
import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecAlt;
import com.mg.merp.lbschedule.model.ItemSpecTax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Позиция спецификации пункта графика исполнения
 * обязательств"
 *
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecMt.java,v 1.2 2007/04/24 15:42:23 sharapov Exp $
 */
public class ItemSpecMt extends DefaultMaintenanceForm implements MasterModelListener {

  private final String paramName = "itemSpec"; //$NON-NLS-1$
  private MaintenanceTableController altTable;
  private AttributeMap altTableProperties = new LocalDataTransferObject();
  private ItemSpecAltServiceLocal altService = (ItemSpecAltServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ItemSpecAltServiceLocal.LOCAL_SERVICE_NAME);
  private DefaultTableController taxTable;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private BigDecimal oldQuantity;


  public ItemSpecMt() throws Exception {
    addMasterModelListener(this);

    altTable = new MaintenanceTableController(altTableProperties);
    altTable.initController(altService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from ItemSpecAlt isa %s where isa.ItemSpec = :itemSpec"; //$NON-NLS-1$

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(paramName);
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(ItemSpecAlt.class, "Id", "isa.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ItemSpecAlt.class, "Catalog.Code", "cat.Code", "left join isa.Catalog cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(ItemSpecAlt.class, "Catalog.FullName", "cat.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, altService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(altTable);

    taxTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from ItemSpecTax ist %s where ist.ItemSpec = :itemSpec"; //$NON-NLS-1$

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(paramName);
        paramsValue.add(getEntity());
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(ItemSpecTax.class, "Id", "ist.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ItemSpecTax.class, "Tax", "t.Code", "left join ist.Tax t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(ItemSpecTax.class, "Summa", "ist.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ItemSpecTax.class, "Price", "ist.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }
    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    altTableProperties.put("ItemSpec", event.getEntity()); //$NON-NLS-1$
    refreshTaxTable();
    oldQuantity = (BigDecimal) event.getEntity().getAttribute("Qty1"); //$NON-NLS-1$
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    view.getWidget("altTable").setReadOnly(true); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnSave()
   */
  @Override
  protected void doOnSave() {
    recomputeSum();
  }

  /**
   * Обработчик кнопки "Расчитать"
   *
   * @param event - событие
   */
  public void onActionComputeSum(WidgetEvent event) {
    recomputeSum();
  }

  private void recomputeSum() {
    ((ItemSpecServiceLocal) getService()).recomputeSum((ItemSpec) getEntity(), oldQuantity);
  }

  /**
   * Обработчик события КМ "Обновить"
   *
   * @param event - событие
   */
  public void onActionRefreshTaxTable(WidgetEvent event) {
    refreshTaxTable();
  }

  public void refreshTaxTable() {
    taxTable.getModel().load();
  }

}
