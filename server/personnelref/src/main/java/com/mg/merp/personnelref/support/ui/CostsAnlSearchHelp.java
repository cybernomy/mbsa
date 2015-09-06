/*
 * CostsAnlSearchHelp.java
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
 * Базовый класс поиска сущностей "Аналитика состава затрат"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlSearchHelp.java,v 1.3 2007/07/09 08:07:47 sharapov Exp $
 */
public abstract class CostsAnlSearchHelp extends AbstractSearchHelp {

  private final String FORM_NAME = "com/mg/merp/personnelref/resources/CostsAnlSearchForm.mfd.xml"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    CostsAnlSearchForm searchForm = (CostsAnlSearchForm) UIProducer.produceForm(FORM_NAME);
    searchForm.addSearchHelpListener(this);
    searchForm.setAnaliticsLevel(getAnaliticsLevel());
    searchForm.run(UIUtils.isModalMode());
  }

  /**
   * Получить уровень аналитики состава затрат
   */
  abstract short getAnaliticsLevel();

}
