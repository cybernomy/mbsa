/*
 * BankAccountSearchForm.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.model.Contractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отображения SearchHelp для банковских счетов
 *
 * @author leonova
 * @version $Id: BankAccountSearchForm.java,v 1.2 2009/02/09 16:14:14 safonov Exp $
 */
public class BankAccountSearchForm extends AbstractSearchForm {
  private final static String LOAD_BANKACC_EJBQL = "from BankAccount as ba where ba.Contractor = :contractor"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Name", "Account"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  protected Contractor contractor;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController bankAccountList;

  @Override
  protected void doOnRun() {
    bankAccountList = new DefaultTableController(new DefaultEntityListTableModel<BankAccount>() {
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("contractor");
        paramsValue.add(contractor);
        setEntityList(MiscUtils.convertUncheckedList(BankAccount.class, OrmTemplate.getInstance().findByNamedParam(LOAD_BANKACC_EJBQL, paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
      }
    });
    bankAccountList.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) bankAccountList.getModel()).getSelectedEntities();
  }

  public void setContractor(Contractor contractor) {
    this.contractor = contractor;
  }

}
