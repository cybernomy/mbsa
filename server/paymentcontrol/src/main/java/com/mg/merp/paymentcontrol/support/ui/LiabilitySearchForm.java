/*
 * LiabilitySearchForm.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.reference.support.ReferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Обязательств"
 *
 * @author Artem V. Sharapov
 * @version $Id: LiabilitySearchForm.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class LiabilitySearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select distinct %s from Liability l %s where l.IsModel = 0 %s"; //$NON-NLS-1$
  private PersistentObject folderEntity = null;
  private MaintenanceTreeController tree;
  private AttributeMap treeUIProperties = new LocalDataTransferObject();
  private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
  private DefaultTableController table;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  @Override
  protected void doOnRun() {
    tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
       */
      @Override
      public TreeNode getRootNode() {
        return ReferenceUtils.loadFolderHierarchy(LiabilityServiceLocal.FOLDER_PART);
      }
    });

    tree.initController(folderService);
    treeUIProperties.put("FolderType", LiabilityServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeChangeEvent event) {
        folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
      }
    });

    table = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {

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
        result.add(new TableEJBQLFieldDef(Liability.class, "Id", "l.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Priority", "l.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Num", "l.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Receivable", "l.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "RegDate", "l.RegDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DateToExecute", "l.DateToExecute", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "SumCur", "l.SumCur", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "From", "f.Code", "left join l.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "FromBankAcc", "fa.Name", "left join l.FromBankAcc as fa", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "To", "t.Code", "left join l.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "ToBankAcc", "ta.Name", "left join l.ToBankAcc as ta", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurCode", "l.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurRateAuthority", "ca.Code", "left join l.CurRateAuthority as ca", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurRateType", "ct.Code", "left join l.CurRateType as ct", false));                     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "PaymentDelay", "l.PaymentDelay", false));                     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocDate", "l.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocNumber", "l.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocType", "dt.Code", "left join l.DocType as dt", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocDate", "l.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocNumber", "l.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocType", "bdt.Code", "left join l.BaseDocType as bdt", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractDate", "l.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractNumber", "l.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractType", "crt.Code", "left join l.ContractType as crt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "Comments", "l.Comments", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "PrefResource", "pr.Name", "left join l.PrefResource as pr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "PrefResourceFolder", "prf.FName", "left join l.PrefResourceFolder as prf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        StringBuilder whereText = new StringBuilder();
        whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "l.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
      }
    });
    tree.addMasterModelListener(table);
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    Serializable[] selectedKeys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (selectedKeys != null && selectedKeys.length > 0)
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(Liability.class, selectedKeys[0])};
    else
      return null;
  }

}
