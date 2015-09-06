/*
 * AccGroupSearchForm.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.account.model.AccGroup;
import com.mg.merp.account.model.AccKind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonova
 * @version $Id: AccGroupSearchForm.java,v 1.2 2009/02/09 09:25:16 safonov Exp $
 */
public class AccGroupSearchForm extends AbstractSearchForm {
  private final static String LOAD_ANLPLAN_EJBQL = "from AccGroup as ag"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"GCode", "GName"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  protected AccKind accKind;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController accGroupList;

  @Override
  protected void doOnRun() {
    accGroupList = new DefaultTableController(new DefaultEntityListTableModel<AccGroup>() {
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("accKind");
        paramsValue.add(accKind);
        setEntityList(MiscUtils.convertUncheckedList(AccGroup.class, OrmTemplate.getInstance().findByNamedParam(LOAD_ANLPLAN_EJBQL.concat(" where ag.AccKind = :accKind"), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
      }
    });
    accGroupList.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) accGroupList.getModel()).getSelectedEntities();
  }

  public void setAccKind(AccKind accKind) {
    this.accKind = accKind;
  }

}
