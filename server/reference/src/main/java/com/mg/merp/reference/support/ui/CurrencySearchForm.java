/*
 * CurrencySearchForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.merp.reference.model.Currency;

/**
 * Контроллер формы поиска бизнес-компонента "Валюта"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: CurrencySearchForm.java,v 1.2 2008/03/14 13:41:09 sharapov Exp $
 */
public class CurrencySearchForm extends AbstractSearchForm {

  private final static String LOAD_CURRENCY_EJBQL = "from Currency"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Code", "FullName", "Iso"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  private DefaultTableController currencyList;

  public CurrencySearchForm() {
    currencyList = new DefaultTableController(new DefaultEntityListTableModel<Currency>() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setEntityList(MiscUtils.convertUncheckedList(Currency.class, OrmTemplate.getInstance().find(LOAD_CURRENCY_EJBQL)), fieldList);
      }
    });
    currencyList.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<Currency>) currencyList.getModel()).getSelectedEntities();
  }

}
