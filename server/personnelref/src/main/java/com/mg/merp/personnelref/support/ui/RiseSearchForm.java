/*
 * CurrencySearchForm.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.personnelref.model.Rise;

/**
 * Форма поиска бизнес-компонента "Надбавки"
 *
 * @author leonova
 * @version $Id: RiseSearchForm.java,v 1.2 2009/02/09 12:11:17 safonov Exp $
 */
public class RiseSearchForm extends AbstractSearchForm {
  private final static String LOAD_RISE_EJBQL = "from Rise"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"ScaleNumber", "SName", "BeginDate"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$

  private DefaultTableController riseList;

  public RiseSearchForm() {
    riseList = new DefaultTableController(new DefaultEntityListTableModel<Rise>() {
      @Override
      protected void doLoad() {
        setEntityList(MiscUtils.convertUncheckedList(Rise.class, OrmTemplate.getInstance().find(LOAD_RISE_EJBQL)), fieldList);
      }
    });
    riseList.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) riseList.getModel()).getSelectedEntities();
  }

}
