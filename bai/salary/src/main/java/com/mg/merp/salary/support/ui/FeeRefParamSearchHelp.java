/*
 * FeeRefParamSearchHelp.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.salary.FeeRefParamServiceLocal;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.FeeModelParam;

/**
 * Поисковик сущностей "Параметр начисления/удержания"
 *
 * @author Artem V. Sharapov
 * @version $Id: FeeRefParamSearchHelp.java,v 1.1 2007/07/09 08:33:47 sharapov Exp $
 */
public class FeeRefParamSearchHelp extends AbstractSearchHelp {

  private final String FORM_NAME = "com/mg/merp/salary/resources/FeeRefParamSearchForm.mfd.xml"; //$NON-NLS-1$

  private final String FEE_MODEL_IMPORT = "FeeModel"; //$NON-NLS-1$
  private final String FEE_MODEL_PARAM_ID_IMPORT = "Id"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    FeeRefParamSearchForm searchForm = (FeeRefParamSearchForm) UIProducer.produceForm(FORM_NAME);
    searchForm.addSearchHelpListener(this);

    FeeModel feeModel = (FeeModel) getImportContextValue(FEE_MODEL_IMPORT);
    if (feeModel == null) {
      FeeModelParam feeModelParam = ServerUtils.getPersistentManager().find(FeeModelParam.class, getImportContextValue(FEE_MODEL_PARAM_ID_IMPORT));
      feeModel = feeModelParam == null ? null : feeModelParam.getFeeModel();
    }
    searchForm.setFeeRef(feeModel == null ? null : feeModel.getFeeRef());

    searchForm.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{FEE_MODEL_IMPORT, FEE_MODEL_PARAM_ID_IMPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    MaintenanceHelper.view((FeeRefParamServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FeeRefParamServiceLocal.LOCAL_SERVICE_NAME), (Integer) entity.getPrimaryKey(), null, null);
  }

}
