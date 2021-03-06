/*
 * PmcResourceExSearchHelp.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.paymentcontrol.model.PmcResource;

import java.util.Date;

/**
 * Поисковик сущностей "Средств платежа" Специализирован для диалога "Внутреннее перемещение
 * средств"
 *
 * @author Artem V. Sharapov
 * @version $Id: PmcResourceExSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcResourceExSearchHelp extends AbstractSearchHelp {

  private final String DATE_IMPORT = "dateIncome";  //$NON-NLS-1$
  private final String RESOURCE_IMPORT = "resourceExpense"; //$NON-NLS-1$
  private final String CURRENCY_EXPORT = "incomeCur"; //$NON-NLS-1$
  private PmcResourceExSearchForm form;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    form = (PmcResourceExSearchForm) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcResourceSearchForm.mfd.xml"); //$NON-NLS-1$;
    form.addSearchHelpListener(this);
    Date dateIncome = (Date) getImportContextValue(DATE_IMPORT);
    PmcResource resourceIncome = (PmcResource) getImportContextValue(RESOURCE_IMPORT);
    form.setSearchParams(dateIncome, resourceIncome);
    form.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{RESOURCE_IMPORT, DATE_IMPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{CURRENCY_EXPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    setExportContextValue(CURRENCY_EXPORT, ((PmcResource) event.getItems()[0]).getCurCode());
  }

}
