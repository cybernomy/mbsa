/*
 * PriceListSpecBr.java
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
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.PriceListFolderServiceLocal;
import com.mg.merp.reference.PriceListSpecServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.PriceListSpecPrice;
import com.mg.merp.reference.model.PriceListSpecPriceId;
import com.mg.merp.reference.model.PriceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера спецификаций прайс-листов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListSpecBr.java,v 1.22 2008/11/20 14:51:41 safonov Exp $
 */
public class PriceListSpecBr extends DefaultHierarchyBrowseForm implements MasterModelListener {
  private final String INIT_QUERY_TEXT = "select %s from PriceListSpec pls %s where pls.PriceListHeadId = :prListHeadId %s order by cat.Code, pls.ActDate %s "; //$NON-NLS-1$
  private final String INIT_PRICELISTFOLDER_QUERY_TEXT = "from PriceListFolder plf where plf.PriceListHead.Id = :prListHeadId and %s order by plf.Parent.Id, plf.FName"; //$NON-NLS-1$
  private final String ADD_FROM_CATALOG_FOLDER_MENUITEM = "addFromCatalogFolder";
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected Serializable prListHeadId;
  private boolean columnAdjusted = false;


  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public PriceListSpecBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PriceListFolder"); //$NON-NLS-1$
    tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
    tree.addMasterModelListener(this);
    restrictionFormName = "com/mg/merp/reference/resources/PriceListSpecRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<PriceListFolder> list = MiscUtils.convertUncheckedList(PriceListFolder.class, OrmTemplate.getInstance().findByNamedParam(String.format(INIT_PRICELISTFOLDER_QUERY_TEXT, DatabaseUtils.generateFlatBrowseWhereEJBQL("plf.Id", 2)), new String[]{"prListHeadId"}, new Object[]{(Integer) prListHeadId})); //$NON-NLS-1$ //$NON-NLS-2$
    return PriceListFolderTreeNode.createTree(list);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    PriceListSpecRest restForm = (PriceListSpecRest) getRestrictionForm();
    PriceType restPriceType = restForm.getPriceType();
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("prListHeadId"); //$NON-NLS-1$
    paramsValue.add((Integer) prListHeadId);
    String orderByText = StringUtils.EMPTY_STRING;
    StringBuilder whereText = new StringBuilder().append(DatabaseUtils.formatEJBQLHierarchyRestriction(restForm.isUseHierarchy(), "pls.Folder", 2, "folder", folderEntity, paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("pls.SName", restForm.getSpecName(), "specName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.FullName", restForm.getSpecFullName(), "specFullName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.Articul", restForm.getArticul(), "articul", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("pls.InternalCode", restForm.getInternalCode(), "internalCode", paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
        //append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pls.ActDateTill", restForm.getDateTill(), null, "dateTill", null, paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("cat.GoodType", restForm.getGoodType(), "goodType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("cat.Code", restForm.getCode(), "code", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "pls.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
    if (restForm.getDateTill() != null) {
      whereText = whereText.append(" and (:dateTill between pls.ActDate and pls.ActDateTill)");
      paramsName.add("dateTill");
      paramsValue.add(restForm.getDateTill());
    }
    if (restForm.isNotInUse()) {
      whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("pls.Canceled", restForm.isShowInUse(), "showInUse", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (restForm.isNotInUsePriceType()) {
      whereText = whereText.append(" and (plsp.Id.PriceListSpec = pls)"); //$NON-NLS-1$
      if (restPriceType != null)
        whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("plsp.Id.PriceType", restPriceType, "priceType", paramsName, paramsValue, false));  //$NON-NLS-1$ //$NON-NLS-2$
      else
        orderByText = ", pt.Code";
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString(), orderByText);
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
        PriceListSpecRest restForm = (PriceListSpecRest) getRestrictionForm();
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Id", "pls.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Catalog.Code", "cat.Code", "left join pls.Catalog as cat", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Catalog.FullName", "cat.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        if (restForm.isNotInUsePriceType()) {
          result.add(new TableEJBQLFieldDef(PriceListSpecPrice.class, "Price", "plsp.Price", "join pls.PriceListSpecPrice as plsp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          result.add(new TableEJBQLFieldDef(PriceListSpecPriceId.class, "PriceType", "pt.Code", "join plsp.Id.PriceType as pt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          columnAdjusted = true;
        } else
          columnAdjusted = false;
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Catalog.Measure1", "meas.Code", "left join cat.Measure1 as meas", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Catalog.Articul", "cat.Articul", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef("BasePrice", PriceListSpec.class, "Price", "pls.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "LastCost", "pls.LastCost", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "SName", "pls.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "Canceled", "pls.Canceled", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "ActDate", "pls.ActDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        //comment by OVS, свойство не предназначено для UI
        //result.add(new TableEJBQLFieldDef(PriceListSpec.class, "ActDateTill", "pls.ActDateTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListSpec.class, "InternalCode", "pls.InternalCode", false));    //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        PriceListSpecRest restForm = (PriceListSpecRest) getRestrictionForm();
        if ((restForm.isNotInUsePriceType() && !columnAdjusted) || !restForm.isNotInUsePriceType() && columnAdjusted)
          fireTableStructureChanged();
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    };
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    boolean changeHierarPerm = SecurityUtils.tryCheckPermission(new BusinessMethodPermission(service.getBusinessServiceMetadata().getName(), BusinessMethodPermission.CHANGE_HIERARCHY_METHOD));
    if (!changeHierarPerm)
      view.getWidget(TREE_WIDGET).getPopupMenu().getMenuItem(ADD_FROM_CATALOG_FOLDER_MENUITEM).setEnabled(false);
    view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (folderEntity != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 2, "com.mg.merp.reference.model.PriceListFolder", "Parent.Id"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @param prListHead The prListHead to set.
   */
  protected void setPrListHeadId(Serializable prListHeadId) {
    this.prListHeadId = prListHeadId;
    treeUIProperties.put("PriceListHead.Id", prListHeadId); //$NON-NLS-1$
    uiProperties.put("PriceListHeadId", prListHeadId); //$NON-NLS-1$
  }

  /**
   * Обработчик пункта КМ "Пересчитать цены"
   *
   * @param event - событие
   */
  public void onActionCalculatePrice(WidgetEvent event) {
    PersistentObject[] searchedEntities = getSearchedEntities();
    if (searchedEntities == null || searchedEntities.length < 1)
      return;

    PriceListSpecServiceLocal priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListSpecServiceLocal.SERVICE_NAME);
    for (PersistentObject priceListSpec : searchedEntities)
      priceListSpecService.calcPrices((PriceListSpec) priceListSpec);
  }

  /**
   * Обработчик пункта КМ "Добавить из каталога"
   *
   * @param event - событие
   */
  public void onActionAddFromCatalog(WidgetEvent event) {
    SearchHelp catalogSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.CatalogSearchHelp");
    catalogSearchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        PriceListSpecServiceLocal priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListSpecServiceLocal.SERVICE_NAME);
        List<Catalog> catalogs = new ArrayList<Catalog>();
        for (PersistentObject catalog : event.getItems())
          catalogs.add((Catalog) catalog);
        priceListSpecService.addFromCatalog((Integer) prListHeadId, (PriceListFolder) folderEntity, catalogs);
        table.refresh();
      }
    });
    try {
      catalogSearchHelp.search();
    } catch (Exception e) {
      getLogger().debug("Could not searched catalog");
    }
  }

  /**
   * Обработчик пункта КМ "Добавить из каталога"
   *
   * @param event - событие
   */
  public void onActionAddFromCatalogFolder(WidgetEvent event) {
    SearchHelp catalogFolderSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.CatalogFolderSearchHelp");
    catalogFolderSearchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        PriceListFolderServiceLocal priceListFolderService = (PriceListFolderServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListFolderServiceLocal.SERVICE_NAME);
        priceListFolderService.addFromCatalog((Integer) event.getItems()[0].getPrimaryKey(), (Integer) folderEntity.getPrimaryKey(), true);
        table.refresh();
        tree.refresh();
      }
    });
    try {
      catalogFolderSearchHelp.search();
    } catch (Exception e) {
      getLogger().debug("Could not searched catalog");
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#getRestrictionForm()
   */
  @Override
  protected RestrictionForm getRestrictionForm() {
    PriceListSpecRest restForm = (PriceListSpecRest) super.getRestrictionForm();
    restForm.setPriceListHeadId((Integer) prListHeadId);
    return restForm;
  }

}

