/*
 * PositionFillSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.salary.model.FeeModel;

/**
 * Поисковик сущностей "Занимаемые должности" Специализирован для БК "Образцы начислений/удержаний"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PositionFillSearchHelp.java,v 1.4 2007/07/09 08:33:47 sharapov Exp $
 */
public class PositionFillSearchHelp extends AbstractSearchHelp {

  private final String FORM_NAME = "com/mg/merp/salary/resources/PositionFillSearchForm.mfd.xml"; //$NON-NLS-1$
  private final String PERSONAL_ACCOUNT_IMPORT = "PersonalAccount"; //$NON-NLS-1$
  private final String FEE_MODEL_ID_IMPORT = "Id"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    PositionFillSearchForm searchForm = (PositionFillSearchForm) UIProducer.produceForm(FORM_NAME);
    searchForm.addSearchHelpListener(this);
    PersonalAccount personalAccount = (PersonalAccount) getImportContextValue(PERSONAL_ACCOUNT_IMPORT);
    if (personalAccount == null) {
      FeeModel feeModel = ServerUtils.getPersistentManager().find(FeeModel.class, getImportContextValue(FEE_MODEL_ID_IMPORT));
      if (feeModel != null)
        personalAccount = feeModel.getPersonalAccount();
    }
    searchForm.setPersonalAccount(personalAccount);
    searchForm.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{PERSONAL_ACCOUNT_IMPORT, FEE_MODEL_ID_IMPORT};
  }

}
