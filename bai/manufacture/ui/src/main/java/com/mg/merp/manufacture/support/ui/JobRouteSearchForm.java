/*
 * JobRouteSearchForm.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobRoute;

import java.util.Set;

/**
 * @author Oleg V. Safonov
 * @version $Id: JobRouteSearchForm.java,v 1.2 2009/02/09 11:54:03 safonov Exp $
 */
public class JobRouteSearchForm extends AbstractSearchForm {
  private final String LOAD_JOB_ROUTE_EJBQL_NAME = "Manufacture.JobRouteSearchForm.loadJobRoute";
  private DefaultTableController jobRoutes;
  private Job job;
  private int jobRouteId = 0;

  public JobRouteSearchForm() {
    jobRoutes = new DefaultTableController(new DefaultEJBQLTableModel() {

      @Override
      protected void doLoad() {
        setRowList(OrmTemplate.getInstance().findByNamedQueryAndNamedParam(LOAD_JOB_ROUTE_EJBQL_NAME, "job", job));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(JobRoute.class, "Id", "jr.Id", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "OperNum", "jr.OperNum", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "Description", "jr.Description", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "CompleteFlag", "jr.CompleteFlag", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyReceived", "jr.QtyReceived", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyComplete", "jr.QtyComplete", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyScrapped", "jr.QtyScrapped", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyMoved", "jr.QtyMoved", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "ControlPointFlag", "jr.ControlPointFlag", true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          return;
        jobRouteId = (Integer) getRowList().get(rows[0])[0];
      }

    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return new PersistentObject[]{ServerUtils.getPersistentManager().find(JobRoute.class, jobRouteId)};
  }

  /**
   * запуск формы
   *
   * @param job ЗНП
   */
  public void execute(Job job) {
    this.job = job;
    jobRoutes.getModel().load();
    run(UIUtils.isModalMode());
  }

}
