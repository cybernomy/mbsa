/*
 * FeeSummaryHeadBr.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.model.FeeSummaryHead;

import java.util.Set;

/**
 * Контроллер формы списка сводов по н/у
 *
 * @author leonova
 * @version $Id: FeeSummaryHeadBr.java,v 1.12 2009/02/10 14:37:49 safonov Exp $
 */
public class FeeSummaryHeadBr extends GoodsDocumentBrowseForm {
  protected String INIT_QUERY_TEXT = "select distinct %s from FeeSummaryHead d %s %s order by d.DocDate, d.Id ";

  public FeeSummaryHeadBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", FeeSummaryHeadServiceLocal.FOLDER_PART);
    restrictionFormName = "com/mg/merp/salary/resources/FeeSummaryHeadRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(FeeSummaryHeadServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    fieldDefs = ((DocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DocumentMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocHead.class, "From", "d.From.Code", false));
        result.add(new TableEJBQLFieldDef(DocHead.class, "To", "d.To.Code", false));
        result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
        result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
        result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
        result.add(new TableEJBQLFieldDef(FeeSummaryHead.class, "PayRoll", "pr.Number", "left join d.PayRoll as pr", false));
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