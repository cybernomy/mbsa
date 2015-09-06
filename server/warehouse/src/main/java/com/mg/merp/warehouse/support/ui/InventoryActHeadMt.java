/*
 * InventoryActHeadMt.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.EmployeesServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.support.ui.EmplBr;
import com.mg.merp.warehouse.InventoryActHeadServiceLocal;
import com.mg.merp.warehouse.InventoryActSpecServiceLocal;
import com.mg.merp.warehouse.model.InventoryActCommission;
import com.mg.merp.warehouse.model.InventoryActHead;
import com.mg.merp.warehouse.model.InventoryActSpec;
import com.mg.merp.warehouse.support.InventoryParametrs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки актов инвентаризации
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: InventoryActHeadMt.java,v 1.15 2009/02/05 10:03:53 sharapov Exp $
 */
public class InventoryActHeadMt extends GoodsDocumentMaintenanceForm {
  private final static String LOAD_COMMLINK_EJBQL = "select iac.Id, iac.Contractor.Code from InventoryActCommission iac where iac.InventoryAct = :inventoryAct"; //$NON-NLS-1$
  private final static int REAL_QUANTITY_COLUMN = 5;
  private final String EXECUTE_INVENTORY_WIDGET = "executeInventory"; //$NON-NLS-1$
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected DefaultTableController taxesTable;
  private Integer invActCommisionLink;
  private InventoryActSpecServiceLocal inventoryActSpecService = (InventoryActSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InventoryActSpec"); //$NON-NLS-1$;

  public InventoryActHeadMt() throws Exception {
    super();
    specService = inventoryActSpecService;
    spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == REAL_QUANTITY_COLUMN && !isDocLineTableReadOnly();
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      public void setValueAt(Object value, int row, int column) {
        if (column == REAL_QUANTITY_COLUMN) {
          Object[] item = getRowList().get(row);
          //загружаем спецификацию для сохранения введенного фактического количества
          InventoryActSpec docSpec = inventoryActSpecService.load((Integer) item[0]);
          item[REAL_QUANTITY_COLUMN] = (BigDecimal) value;
          docSpec.setRealQuantity((BigDecimal) value);
          inventoryActSpecService.store(docSpec);
          refreshModel();
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
       */
      @Override
      public Class<?> getColumnClass(int column) {
        if (column == REAL_QUANTITY_COLUMN)
          return BigDecimal.class;
        else
          return super.getColumnClass(column);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = new LinkedHashSet<TableEJBQLFieldDef>();
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Id", "ds.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Catalog.Code", "ds.Catalog.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Catalog.FullName", "ds.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Catalog.Measure1", "meas1.Code", "left join ds.Catalog.Measure1 as meas1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Quantity", "ds.Quantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "RealQuantity", "ds.RealQuantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Catalog.Measure2", "meas2.Code", "left join ds.Catalog.Measure2 as meas2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Quantity2", "ds.Quantity2", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "RealQuantity2", "ds.RealQuantity2", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Price", "ds.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Summa", "ds.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "RealSumma", "ds.RealSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "BestBefore", "ds.BestBefore", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Weight", "ds.Weight", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Volume", "ds.Volume", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Catalog.Articul", "ds.Catalog.Articul", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Comment", "ds.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActSpec.class, "Contractor", "contr.Code", "left join ds.Contractor as contr", false));             //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
       */
      @Override
      protected String getDocSpecModelName() {
        return InventoryActSpec.class.getName();
      }

    });
    addMasterModelListener(spec);

    taxesTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(InventoryActCommission.class, "Id", "iac.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(InventoryActCommission.class, "Contractor", "iac.Contractor", true)); //$NON-NLS-1$ //$NON-NLS-2$
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          invActCommisionLink = null;
        else {
          Object[] row = getRowList().get(rows[0]);
          invActCommisionLink = (Integer) row[0];
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("inventoryAct"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        setQuery(LOAD_COMMLINK_EJBQL, paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(this);
    //taxesTable.getModel().load();
    //showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    taxesTable.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    getInventoryActHeadService().computeShortageAndExsessSum((InventoryActHead) getEntity());
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    PopupMenu menu = view.getWidget("taxesTable").getPopupMenu(); //$NON-NLS-1$
    menu.getMenuItem("includeItem").setEnabled(false); //$NON-NLS-1$
    menu.getMenuItem("excludeItem").setEnabled(false); //$NON-NLS-1$
    view.getWidget(SPEC_TABLE_WIDGET).getPopupMenu().getMenuItem(EXECUTE_INVENTORY_WIDGET).setEnabled(false);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    super.doOnEdit();
    view.getWidget(SPEC_TABLE_WIDGET).getPopupMenu().getMenuItem(EXECUTE_INVENTORY_WIDGET).setEnabled(false);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    view.getWidget(SPEC_TABLE_WIDGET).getPopupMenu().getMenuItem(EXECUTE_INVENTORY_WIDGET).setEnabled(!readOnly);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#setSpecificationEditable()
   */
  @Override
  public void setSpecificationEditable() {
    super.setSpecificationEditable();
    view.getWidget(SPEC_TABLE_WIDGET).getPopupMenu().getMenuItem(EXECUTE_INVENTORY_WIDGET).setEnabled(true);
    ((GoodsDocSpecMaintenanceEJBQLTableModel) spec.getModel()).fireTableStructureChanged();
  }

  public void onActionInclude(WidgetEvent event) throws ApplicationException {
    final EmployeesServiceLocal service = (EmployeesServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Employees"); //$NON-NLS-1$
    final EmplBr form = (EmplBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);

    form.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {

      }

      public void searchPerformed(SearchHelpEvent event) {
        final Contractor tax = ((Contractor) event.getItems()[0]);
        getCommisionService().includeInvCommision((InventoryActHead) getEntity(), tax);
        taxesTable.getModel().load();
      }

    });
    form.run();
  }

  public void onActionExclude(WidgetEvent event) throws ApplicationException {
    if (invActCommisionLink == null)
      return;
    getCommisionService().excludeInvCommision(ServerUtils.getPersistentManager().find(InventoryActCommission.class, invActCommisionLink));
    taxesTable.getModel().load();
  }

  /**
   * Обработчик пункта КМ "Провести инвентаризацию"
   *
   * @param event - событие
   */
  public void onActionExecuteInventarization(WidgetEvent event) {
    final WarehouseInventoryParamsDlg dialog = (WarehouseInventoryParamsDlg) UIProducer.produceForm("com/mg/merp/warehouse/resources/WarehouseInventoryParamsDlg.mfd.xml"); //$NON-NLS-1$
    dialog.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        InventoryActHead invAct = (InventoryActHead) getEntity();
        getInventoryActHeadService().executeStockInventory(invAct
            , new InventoryParametrs(
            (OrgUnit) ServerUtils.getPersistentManager().find(OrgUnit.class, invAct.getSrcStock().getId())
            , invAct.getSrcMol()
            , invAct.getEndDate()
            , dialog.getCatalogCodeFrom()
            , dialog.getCatalogCodeTill()
            , dialog.getStockInventoryKind()
            , dialog.getMolInventoryKind()
            , dialog.isIncludePositionsWithZeroRemn()
            , dialog.isDeleteSpecList()));
        spec.refresh();
      }
    });

    dialog.addCancelActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        // do nothing
      }
    });
    dialog.execute();
  }

  private InventoryActHeadServiceLocal getInventoryActHeadService() {
    return (InventoryActHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InventoryActHead"); //$NON-NLS-1$
  }

  private InventoryActHeadServiceLocal getCommisionService() {
    return (InventoryActHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InventoryActHead"); //$NON-NLS-1$
  }

}
