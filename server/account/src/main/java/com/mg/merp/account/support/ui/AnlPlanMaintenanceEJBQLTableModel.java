/*
 * AnlPlantMaintenanceEJBQLTableModel.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.AnlPlanServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: AnlPlanMaintenanceEJBQLTableModel.java,v 1.3 2009/02/25 15:09:59 safonov Exp $
 */
public abstract class AnlPlanMaintenanceEJBQLTableModel extends DefaultMaintenanceEJBQLTableModel {
  protected final String INIT_QUERY_TEXT = "select %s from AnlPlan anl %s where anl.AccPlan = :account and anl.AnlLevel = :anlLevel";
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected AnlPlanServiceLocal service;
  protected String fieldsList;
  protected String fromList;
  protected AccPlan accPlan;

  protected abstract short getAnalitikaLevel();

  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("account");
    paramsValue.add(accPlan);
    paramsName.add("anlLevel");
    paramsValue.add(getAnalitikaLevel());
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "Id", "anl.Id", true));
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "Code", "anl.Code", false));
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "AnlName", "anl.AnlName", false));
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "UseStdForm", "anl.UseStdForm", false));
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "StdForm", "anl.StdForm", false));
    result.add(new TableEJBQLFieldDef(AnlPlan.class, "Parent", "par.Code", "left join anl.Parent as par", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
  }

  public void setAccPlan(AccPlan accPlan) {
    this.accPlan = accPlan;
  }

}
