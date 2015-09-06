/*
 * RiseScaleSearchForm.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.Rise;
import com.mg.merp.personnelref.model.RiseScale;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Шкала надбавки"
 *
 * @author Artem V. Sharapov
 * @version $Id: RiseScaleSearchForm.java,v 1.1 2007/07/09 08:07:47 sharapov Exp $
 */
public class RiseScaleSearchForm extends AbstractSearchForm {

  private final String MASTER_INIT_QUERY_TEXT = "select %s from Rise r"; //$NON-NLS-1$
  private final String DETAIL_INIT_QUERY_TEXT = "select %s from RiseScale rc where rc.Rise.Id = :riseId"; //$NON-NLS-1$
  private DefaultTableController masterTable;
  private DefaultTableController detailTable;
  private List<String> detailParamsName = new ArrayList<String>();
  private List<Object> detailParamsValue = new ArrayList<Object>();

  private Integer detailSelectedId;


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    masterTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Rise.class, "Id", "r.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Rise.class, "RCode", "r.RCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Rise.class, "RName", "r.RName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText());
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) masterTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(MASTER_INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length != 0) {
          detailParamsName.clear();
          detailParamsValue.clear();
          detailParamsName.add("riseId"); //$NON-NLS-1$
          detailParamsValue.add(getRowList().get(rows[0])[0]);
          detailTable.getModel().load();
        }
      }
    });

    detailTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(RiseScale.class, "Id", "rc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RiseScale.class, "ScaleNumber", "rc.ScaleNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RiseScale.class, "SName", "rc.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RiseScale.class, "BeginDate", "rc.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), detailParamsName.toArray(new String[detailParamsName.size()]), detailParamsValue.toArray(new Object[detailParamsValue.size()]));
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) detailTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(DETAIL_INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          detailSelectedId = null;
        else
          detailSelectedId = (Integer) getRowList().get(rows[0])[0];
      }
    });
    super.doOnRun();
    masterTable.getModel().load();
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    if (detailSelectedId == null)
      return new PersistentObject[0];
    else
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(RiseScale.class, detailSelectedId)};
  }

}
