/*
 * PaymentSearchForm.java
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
package com.mg.merp.paymentalloc.support.ui;

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
import com.mg.merp.paymentalloc.PaymentServiceLocal;
import com.mg.merp.paymentalloc.model.Payment;
import com.mg.merp.reference.support.ReferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Записи журнала платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentSearchForm.java,v 1.2 2007/06/05 12:50:57 sharapov Exp $
 */
public class PaymentSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select distinct %s from Payment p %s where p.IsModel = 0 %s"; //$NON-NLS-1$
  private PersistentObject folderEntity = null;
  private MaintenanceTreeController tree;
  private AttributeMap treeUIProperties = new LocalDataTransferObject();
  private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
  private DefaultTableController table;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
       */
      @Override
      public TreeNode getRootNode() {
        return ReferenceUtils.loadFolderHierarchy(PaymentServiceLocal.FOLDER_PART);
      }
    });

    tree.initController(folderService);
    treeUIProperties.put("FolderType", PaymentServiceLocal.FOLDER_PART); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(Payment.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Planned", "p.Planned", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "PDate", "p.PDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurCode", "p.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRateType", "p.CurRateType.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRateAuthority", "p.CurRateAuthority.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRate", "p.CurRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "SumCur", "p.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "SumNat", "p.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocType", "dt.Code", "left join p.DocType dt", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocNumber", "p.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocDate", "p.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocType", "bdt.Code", "left join p.BaseDocType bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocNumber", "p.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocDate", "p.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractType", "ct.Code", "left join p.ContractType ct", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractNumber", "p.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractDate", "p.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractorTo", "cto.Code", "left join p.ContractorTo as cto", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractorFrom", "cfrom.Code", "left join p.ContractorFrom as cfrom", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "Description", "p.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Comments", "p.Comments", false));    //$NON-NLS-1$ //$NON-NLS-2$
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
        whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "p.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
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
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(Payment.class, selectedKeys[0])};
    else
      return null;
  }

}
