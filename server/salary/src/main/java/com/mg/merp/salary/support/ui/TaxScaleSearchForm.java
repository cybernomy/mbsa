/*
 * TaxScaleSearchForm.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.salary.model.TaxScale;


/**
 * @author leonova
 * @version $Id: TaxScaleSearchForm.java,v 1.2 2009/02/09 12:14:10 safonov Exp $
 */
public class TaxScaleSearchForm extends AbstractSearchForm {
  private final static String LOAD_TAX_SCALE_EJBQL = "from TaxScale"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"SNumber", "BeginDate", "TaxPayer", "SName"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$

  private DefaultTableController taxScaleList;

  public TaxScaleSearchForm() {
    taxScaleList = new DefaultTableController(new DefaultEntityListTableModel<TaxScale>() {
      @Override
      protected void doLoad() {
        setEntityList(MiscUtils.convertUncheckedList(TaxScale.class, OrmTemplate.getInstance().find(LOAD_TAX_SCALE_EJBQL)), fieldList);
      }
    });
    taxScaleList.getModel().load();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) taxScaleList.getModel()).getSelectedEntities();
  }

}
