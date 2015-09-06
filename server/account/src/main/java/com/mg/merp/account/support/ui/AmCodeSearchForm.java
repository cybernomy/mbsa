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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.account.model.AmCode;

/**
 * Контроллер формы поиска бизнес-компонента "Шифры амортизации"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: AmCodeSearchForm.java,v 1.3 2009/02/09 09:25:16 safonov Exp $
 */
public class AmCodeSearchForm extends AbstractSearchForm {
  private final static String LOAD_AMCODE_EJBQL = "from AmCode"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Code", "CName"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  private DefaultTableController amcodeList;

  public AmCodeSearchForm() {
    amcodeList = new DefaultTableController(new DefaultEntityListTableModel<AmCode>() {
      @Override
      protected void doLoad() {
        setEntityList(MiscUtils.convertUncheckedList(AmCode.class, OrmTemplate.getInstance().find(LOAD_AMCODE_EJBQL)), fieldList);
      }
    });
    amcodeList.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) amcodeList.getModel()).getSelectedEntities();
  }

}
