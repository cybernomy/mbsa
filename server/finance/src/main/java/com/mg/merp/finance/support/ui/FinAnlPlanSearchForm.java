/*
 * FinAnlPlanSearchForm.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отображения SearchHelp для аналитических счетов в финансовых
 *
 * @author leonova
 * @version $Id: FinAnlPlanSearchForm.java,v 1.2 2009/02/09 09:46:07 safonov Exp $
 */
public class FinAnlPlanSearchForm extends AbstractSearchForm {
  private final static String LOAD_ANLPLAN_EJBQL = "from Analytics as anl"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Id", "Code", "AnlName"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  protected short anlLevel;
  protected Account accPlan;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController finAnlList;

  @Override
  protected void doOnRun() {
    finAnlList = new DefaultTableController(new DefaultEntityListTableModel<Analytics>() {
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("accPlan");
        paramsValue.add(accPlan);
        paramsName.add("anlLevel");
        paramsValue.add(anlLevel);
        setEntityList(MiscUtils.convertUncheckedList(Analytics.class, OrmTemplate.getInstance().findByNamedParam(LOAD_ANLPLAN_EJBQL.concat(" where anl.FinAcc = :accPlan and anl.AnlLevel = :anlLevel"), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
      }
    });
    finAnlList.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) finAnlList.getModel()).getSelectedEntities();
  }

  public void setSearchParams(Account accPlan, short anlLevel) {
    this.accPlan = accPlan;
    this.anlLevel = anlLevel;
  }

}
