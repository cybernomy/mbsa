/*
 * WarehouseDocumentOutBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.warehouse.WarehouseDocumentHeadOutServiceLocal;
import com.mg.merp.warehouse.model.InvoiceHead;
import com.mg.merp.warehouse.model.StockDocumentHead;

import java.util.Set;

/**
 * Контроллер формы списка расходных ордеров
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WarehouseDocumentOutBr.java,v 1.12 2009/02/10 14:29:13 safonov Exp $
 */
public class WarehouseDocumentOutBr extends WarehouseDocumentBr {

  public WarehouseDocumentOutBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", WarehouseDocumentHeadOutServiceLocal.FOLDER_PART);
    restrictionFormName = "com/mg/merp/warehouse/resources/WarehouseDocumentHeadOutRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(WarehouseDocumentHeadOutServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restGoodDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restGoodDocument.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false));
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new WarehouseDocumentMaintenanceEJBQLTableModel() {
      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(StockDocumentHead.class, "SummaNatWithDiscount", "d.SummaNatWithDiscount", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHead.class, "SummaCurWithDiscount", "d.SummaCurWithDiscount", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHead.class, "DiscountOnDoc", "d.DiscountOnDoc", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHead.class, "DiscountOnLine", "d.DiscountOnLine", false));
        result.add(new TableEJBQLFieldDef(StockDocumentHead.class, "DiscountFolder", "df.FName", "left join d.DiscountFolder as df", false));
        result.add(new TableEJBQLFieldDef(InvoiceHead.class, "SrcStock", "ss.Code", "left join d.SrcStock as ss", false));
        result.add(new TableEJBQLFieldDef(InvoiceHead.class, "SrcMol", "sm.Code", "left join d.SrcMol as sm", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        //throw new ApplicationException(createQueryText());
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

