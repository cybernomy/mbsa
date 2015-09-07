/*
 * TariffScaleClassSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Разряд тарифной сетки"
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: TariffScaleClassSearchHelp.java,v 1.3 2007/07/18 11:12:13 sharapov Exp $
 */
public class TariffScaleClassSearchHelp extends AbstractSearchHelp {

  private final String SEARCH_FORM_NAME = "com/mg/merp/personnelref/resources/TariffScaleClassSearchForm.mfd.xml"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    TariffScaleClassSearchForm searchForm = (TariffScaleClassSearchForm) UIProducer.produceForm(SEARCH_FORM_NAME);
    searchForm.addSearchHelpListener(this);
    searchForm.run(UIUtils.isModalMode());
  }

}
