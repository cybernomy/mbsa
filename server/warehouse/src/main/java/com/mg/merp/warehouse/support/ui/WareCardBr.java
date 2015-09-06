/*
 * WareCardBr.java
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.support.ui.CatalogFolderTreeNode;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы браузера КСУ
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WareCardBr.java,v 1.14 2008/10/28 07:37:14 safonov Exp $
 */
public class WareCardBr extends DefaultHierarchyBrowseForm {
  private static final String WARECARD_TABLE_NAME = "table";
  private final String INIT_QUERY_TEXT = "select %s from StockCard sc %s where sc.Stock.Id = :warehouseId %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private Serializable warehouseId;

  public WareCardBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CatalogFolder");
    tree.setParentPropertyName("CatalogFolder.Id");
    restrictionFormName = "com/mg/merp/warehouse/resources/WareCardRest.mfd.xml";

  }

  /**
   * @param warehouseId The warehouseId to set.
   */
  protected void setWarehouseId(Serializable warehouseId) {
    this.warehouseId = warehouseId;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<CatalogFolder> list = OrmTemplate.getInstance().find(CatalogFolder.class, String.format("from CatalogFolder cf where %s order by cf.Id", DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1)));
    return CatalogFolderTreeNode.createTree(list);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

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
        result.add(new TableEJBQLFieldDef(StockCard.class, "Id", "sc.Id", true));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Catalog.Code", "sc.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Catalog.FullName", "sc.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Catalog.Measure1", "meas1.Code", "left join sc.Catalog.Measure1 as meas1", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Catalog.Measure2", "meas2.Code", "left join sc.Catalog.Measure2 as meas2", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "CardNumber", "sc.CardNumber", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "SupplyNorm", "sc.SupplyNorm", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Quantity", "sc.Quantity", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Reserve", "sc.Reserve", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Mol", "m.Code", "left join sc.Mol as m", true));
        result.add(new TableEJBQLFieldDef(StockCard.class, "PlanIn", "sc.PlanIn", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "PlanOut", "sc.PlanOut", false));
        result.add(new TableEJBQLFieldDef(StockCard.class, "Catalog.Articul", "sc.Catalog.Articul", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    WareCardRest restForm = (WareCardRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" and ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "sc.Catalog.Folder", 1, "folder", folderEntity, paramsName, paramsValue, true))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.CardNumber", restForm.getCardNumberFrom(), restForm.getCardNumberTo(), "cardNumFrom", "cardNumTo", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("sc.Catalog.BarCode", restForm.getBarCode(), "barCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("sc.Catalog.PluCode", restForm.getPluCode(), "pluCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("sc.Catalog.Code", restForm.getCode(), "code", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("sc.Catalog.FullName", restForm.getName(), "name", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("m", restForm.getMol(), "mol", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.SupplyNorm", restForm.getSupplyNormFrom(), restForm.getSupplyNormTo(), "supplyNormFrom", "supplyNormTo", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.Reserve", restForm.getReserveFrom(), restForm.getReserveTo(), "reserveFrom", "reserveTo", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.Quantity", restForm.getQuantityFrom(), restForm.getQuantityTo(), "quantityFrom", "quantityTo", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.PlanIn", restForm.getPlanInFrom(), restForm.getPlanInTo(), "planInFrom", "planInTo", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("sc.PlanOut", restForm.getPlanOutFrom(), restForm.getPlanOutTo(), "planOutFrom", "planOutTo", paramsName, paramsValue, false));
    paramsName.add("warehouseId");
    paramsValue.add(warehouseId);

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());


  }

  protected void doOnRun() {
    super.doOnRun();
    PopupMenu menu = view.getWidget(WARECARD_TABLE_NAME).getPopupMenu();
    menu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    menu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
  }

  /**
   * показать форму
   */
  public void execute(Serializable warehouseId) {
    this.warehouseId = warehouseId;
    Warehouse warehouse = ServerUtils.getPersistentManager().find(Warehouse.class, warehouseId);
    setTitle(Messages.getInstance().getMessage(Messages.WARECARD_BR_TITLE, new String[]{warehouse.getFullName().trim()}));
    run();
  }

}
