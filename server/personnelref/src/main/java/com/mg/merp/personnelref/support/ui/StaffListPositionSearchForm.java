/*
 * StaffListPositionSearchForm.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
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
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.StaffListUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Должности в штатном расписании"
 *
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionSearchForm.java,v 1.1 2007/07/10 07:34:05 sharapov Exp $
 */
public class StaffListPositionSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from StaffListPosition sp %s %s"; //$NON-NLS-1$
  private PersistentObject folderEntity = null;
  private MaintenanceTreeController tree;
  private AttributeMap treeUIProperties = new LocalDataTransferObject();
  private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListUnit"); //$NON-NLS-1$
  private DefaultTableController table;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Integer staffListId;

  private Integer[] selectedIds;


  /* (non-Javadoc)
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
        List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList.Id = :staffList order by slu.Id", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)), "staffList", staffListId)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return StaffListUnitTreeNode.createTree(list);
      }
    });

    tree.initController(folderService);
    tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeChangeEvent event) {
        folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
        table.getModel().load();
      }
    });

    table = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "Id", "sp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "Position", "sp.Position.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "SlPositionUniqueId", "sp.SlPositionUniqueId", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "StaffCategory", "sc.CCode", "left join sp.StaffCategory as sc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "BeginDate", "sp.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "EndDate", "sp.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "RateNumber", "sp.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "HolidayNumber", "sp.HolidayNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkCondition", "wc.CCode", "left join sp.WorkCondition as wc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkSchedule", "ws.SCode", "left join sp.WorkSchedule as ws", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl1", "anl1.ACode", "left join sp.CostsAnl1 anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl2", "anl2.ACode", "left join sp.CostsAnl2 anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl3", "anl3.ACode", "left join sp.CostsAnl3 anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl4", "anl4.ACode", "left join sp.CostsAnl4 anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "CostsAnl5", "anl5.ACode", "left join sp.CostsAnl5 anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "TaxCalcKind", "tck.Code", "left join sp.TaxCalcKind as tck", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        StringBuilder whereText = new StringBuilder("where"); //$NON-NLS-1$
        whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "sp.StaffListUnit", 6, "folder", folderEntity, paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        selectedIds = new Integer[rows.length];
        for (int i = 0; i < rows.length; i++)
          selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
      }
    });
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    if (selectedIds == null)
      return new PersistentObject[0];

    PersistentManager persistentManager = ServerUtils.getPersistentManager();
    PersistentObject[] selectedEntities = new PersistentObject[selectedIds.length];
    for (int i = 0; i < selectedIds.length; i++)
      selectedEntities[i] = persistentManager.find(StaffListPosition.class, selectedIds[i]);

    return selectedEntities;
  }

  /**
   * Установить идентификатор штатного расписания для поиска должностей
   */
  public void setStaffListId(Integer staffListId) {
    this.staffListId = staffListId;
  }

}
