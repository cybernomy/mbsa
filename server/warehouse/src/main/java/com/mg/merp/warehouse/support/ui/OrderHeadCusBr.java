/*
 * OrderHeadCusBr.java
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
import com.mg.merp.warehouse.OrderHeadCusServiceLocal;
import com.mg.merp.warehouse.model.OrderHead;

import java.util.Set;

/**
 * Контроллер формы списка заказов покупателей
 *
 * @author leonova
 * @version $Id: OrderHeadCusBr.java,v 1.15 2009/02/10 14:30:22 safonov Exp $
 */
public class OrderHeadCusBr extends OrderBr {

  public OrderHeadCusBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", OrderHeadCusServiceLocal.FOLDER_PART);
    restrictionFormName = "com/mg/merp/warehouse/resources/OrderHeadCusRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(OrderHeadCusServiceLocal.FOLDER_PART);
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
    return new OrderMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(OrderHead.class, "SummaCurWithDiscount", "d.SummaCurWithDiscount", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "SummaNatWithDiscount", "d.SummaNatWithDiscount", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "DiscountOnDoc", "d.DiscountOnDoc", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "DiscountOnLine", "d.DiscountOnLine", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "SrcStock", "ss.Code", "left join d.SrcStock as ss", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "SrcMol", "sm.Code", "left join d.SrcMol as sm", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "DiscountFolder", "df.FName", "left join d.DiscountFolder as df", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "Comment", "d.Comment", false));
        result.add(new TableEJBQLFieldDef(OrderHead.class, "Description", "d.Description", false));

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
