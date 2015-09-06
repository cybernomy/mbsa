/*
 * PositionFillInStaffListSearchForm.java
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
package com.mg.merp.salary.support.ui;

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
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.StaffListUnit;
import com.mg.merp.personnelref.support.ui.StaffListUnitTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Занимаемые должности в ШР"
 *
 * @author Artem V. Sharapov
 * @version $Id: PositionFillInStaffListSearchForm.java,v 1.1 2007/07/09 08:33:47 sharapov Exp $
 */
public class PositionFillInStaffListSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from PersonalAccount pa, StaffListPosition sp join pa.SetOfSalPositionFill pf %s %s and sp.SlPositionUniqueId = pf.SlPositionUnique.SlPositionUniqueId and sp.StaffListUnit.StaffList.Id = :staffList"; //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "SlPositionUnique", "pf.SlPositionUnique.SlPositionUniqueId", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Position", "p.Name", "left join pf.Position as p", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "PositionFillKind", "pfk.KCode", "left join pf.PositionFillKind as pfk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "BeginDate", "pf.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "EndDate", "pf.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "RateNumber", "pf.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "IsBasic", "pf.IsBasic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Surname", "pa.Personnel.Person.Surname", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Name", "pa.Personnel.Person.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Patronymic", "pa.Personnel.Person.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonalAccount.class, "ANumber", "pa.ANumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "StaffCategory", "sc.CCode", "left join sp.StaffCategory as sc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "RateNumber", "sp.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "HolidayNumber", "sp.HolidayNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkCondition", "wc.CCode", "left join sp.WorkCondition as wc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(StaffListPosition.class, "WorkSchedule", "ws.SCode", "left join sp.WorkSchedule as ws", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
    table.getModel().load();
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
      selectedEntities[i] = persistentManager.find(PositionFill.class, selectedIds[i]);

    return selectedEntities;
  }

  /**
   * Установить идентификатор ШР для поиска лицевых счетов сотрудников
   */
  public void setStaffListId(Integer staffListId) {
    this.staffListId = staffListId;
    paramsName.add("staffList"); //$NON-NLS-1$
    paramsValue.add(staffListId);
  }

}
