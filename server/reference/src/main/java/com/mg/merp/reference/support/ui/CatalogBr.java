/*
 * CatalogBr.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.StockSituationBrowser;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера каталога
 *
 * @author Oleg V. Safonov
 * @version $Id: CatalogBr.java,v 1.11 2008/02/21 12:44:15 alikaev Exp $
 */
public class CatalogBr extends DefaultHierarchyBrowseForm implements StockSituationBrowser {
  private final String INIT_QUERY_TEXT = "select %s from Catalog cat %s %s order by cat.Code ";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public CatalogBr() {
    super();

    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CatalogFolder");
    tree.setParentPropertyName("CatalogFolder.Id");
    restrictionFormName = "com/mg/merp/reference/resources/CatalogRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
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
    List<CatalogFolder> list = OrmTemplate.getInstance().find(CatalogFolder.class, String.format("from CatalogFolder cf where %s order by cf.CatalogFolder.Id, cf.FName", DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1)));
    return CatalogFolderTreeNode.createTree(list);
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
    CatalogRest restForm = (CatalogRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "cat.Folder", 1, "folder", folderEntity, paramsName, paramsValue, true)).
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.Code", restForm.getCode(), "code", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.FullName", restForm.getName(), "name", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("cat.GoodType", restForm.getGoodType(), "goodType", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.PluCode", restForm.getPluCode(), "pluCode", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.BarCode", restForm.getBarCode(), "barCode", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("cat.Measure1", restForm.getMeasure1(), "measure1", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("cat.Measure2", restForm.getMeasure2(), "measure2", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("cat.Okdp", restForm.getOkdpCode(), "okdpCode", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("cat.MarketingMargin", restForm.getTradeTaxFrom(), restForm.getTradeTaxTo(), "taxFrom", "taxTo", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "cat.Id", restForm.getAddinFieldsRestriction(), false));
    if (restForm.isNotInUse()) {
      whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("cat.IsNotInUse", restForm.isShowInUse(), "showInUse", paramsName, paramsValue, false));
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

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
        result.add(new TableEJBQLFieldDef(Catalog.class, "Id", "cat.Id", true));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Code", "cat.Code", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "FullName", "cat.FullName", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Measure1", "meas1.Code", "left join cat.Measure1 as meas1", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Measure2", "meas2.Code", " left join cat.Measure2 as meas2 ", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "GoodType", "cat.GoodType", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "BarCode", "cat.BarCode", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "PluCode", "cat.PluCode", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Okdp", "okdp.Code", " left join cat.Okdp as okdp ", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "IsHasWeight", "cat.IsHasWeight", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Weight", "cat.Weight", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "WeightMeasure", "weightmeas.Code", " left join cat.WeightMeasure as weightmeas ", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Volume", "cat.Volume", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "VolumeMeasure", "volumemeas.Code", " left join cat.VolumeMeasure as volumemeas ", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "MarketingMargin", "cat.MarketingMargin", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "ShelfLife", "cat.ShelfLife", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "IsNotInUse", "cat.IsNotInUse", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "Articul", "cat.Articul", false));
        result.add(new TableEJBQLFieldDef(Catalog.class, "TaxGroup", "taxgroup.Code", " left join cat.TaxGroup as taxgroup ", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (folderEntity != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 1, "com.mg.merp.reference.model.CatalogFolder", "CatalogFolder.Id");
  }

  public void onActionShowStockSituation(WidgetEvent event) throws Exception {
    CurrentStockSituationLocator.locate().showSituationForm(
        ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys());
  }

}
