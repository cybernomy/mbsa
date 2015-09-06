/*
 * WarehouseDocumentHeadModelOutBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.support.ReferenceUtils;
import com.mg.merp.warehouse.WarehouseDocumentHeadModelOutServiceLocal;
import com.mg.merp.warehouse.model.StockDocumentHeadModel;

import java.util.Set;

/**
 * Контроллер формы списка образцов расходных ордеров
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WarehouseDocumentModelOutBr.java,v 1.4 2006/09/12 10:51:47 leonova Exp $
 */
public class WarehouseDocumentModelOutBr extends WarehouseDocumentModelBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from StockDocumentHeadModel dhm %s %s";

  public WarehouseDocumentModelOutBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", WarehouseDocumentHeadModelOutServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return ReferenceUtils.loadFolderHierarchy(WarehouseDocumentHeadModelOutServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    fieldDefs = ((WarehouseDocModelMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new WarehouseDocModelMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(StockDocumentHeadModel.class, "SummaCurWithDiscount", "dhm.SummaCurWithDiscount", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHeadModel.class, "SummaNatWithDiscount", "dhm.SummaNatWithDiscount", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHeadModel.class, "DiscountOnDoc", "dhm.DiscountOnDoc", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHeadModel.class, "DiscountOnLine", "dhm.DiscountOnLine", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
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

}
