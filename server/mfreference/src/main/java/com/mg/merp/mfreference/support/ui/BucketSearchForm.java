/*
 * BucketSearchForm.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.PlanningLevelBucket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonova
 * @version $Id: BucketSearchForm.java,v 1.2 2009/02/09 11:58:46 safonov Exp $
 */
public class BucketSearchForm extends AbstractSearchForm {
  private final static String LOAD_ANLPLAN_EJBQL = "from PlanningLevelBucket as plb"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Id", "StartDate", "EndDate", "BucketOffset"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  protected PlanningLevel planningLevel;
  protected boolean orderBucket;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController plLevelList;

  @Override
  protected void doOnRun() {
    plLevelList = new DefaultTableController(new DefaultEntityListTableModel<PlanningLevelBucket>() {
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("planningLevel");
        paramsValue.add(planningLevel);
        setEntityList(MiscUtils.convertUncheckedList(PlanningLevelBucket.class, OrmTemplate.getInstance().findByNamedParam(LOAD_ANLPLAN_EJBQL.concat(" where plb.PlanningLevel = :planningLevel"), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
      }
    });
    plLevelList.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) plLevelList.getModel()).getSelectedEntities();
  }

  public void setSearchParams(PlanningLevel planningLevel, boolean orderBucket) {
    this.planningLevel = planningLevel;
    this.orderBucket = orderBucket;
  }

}
