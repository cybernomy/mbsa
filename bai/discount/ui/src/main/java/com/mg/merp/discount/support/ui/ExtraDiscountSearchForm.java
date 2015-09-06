/*
 * ExtraDiscountSearchForm.java
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
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.discount.model.Card;
import com.mg.merp.discount.model.ExtraDiscount;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonova
 * @version $Id: ExtraDiscountSearchForm.java,v 1.2 2009/02/09 09:27:58 safonov Exp $
 */
public class ExtraDiscountSearchForm extends AbstractSearchForm {
  private final static String LOAD_DISCOUNT_EJBQL = "from ExtraDiscount as ed where ed.Card = :card"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Name", "Discount"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
  protected Card card;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController discountList;

  @Override
  protected void doOnRun() {
    discountList = new DefaultTableController(new DefaultEntityListTableModel<ExtraDiscount>() {
      @Override
      protected void doLoad() {
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("card");
        paramsValue.add(card);
        setEntityList(MiscUtils.convertUncheckedList(ExtraDiscount.class, OrmTemplate.getInstance().findByNamedParam(LOAD_DISCOUNT_EJBQL, paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
      }
    });
    discountList.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    return ((DefaultEntityListTableModel<?>) discountList.getModel()).getSelectedEntities();
  }

  public void setCard(Card card) {
    this.card = card;
  }

}
