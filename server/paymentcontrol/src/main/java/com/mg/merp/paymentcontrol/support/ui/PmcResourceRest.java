/*
 * PmcResourceRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.utils.StringUtils;

import java.util.Date;

/**
 * Контроллер формы условий отбора "Средств платежа"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PmcResourceRest.java,v 1.5 2007/09/10 11:19:21 sharapov Exp $
 */
public class PmcResourceRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("PaymentControl.Resource.Name") //$NON-NLS-1$
  private String name = StringUtils.EMPTY_STRING;

  @DataItemName("PaymentControl.Cond.PmcResource.ActDate") //$NON-NLS-1$
  private Date actDate = null;

  private boolean isActDateEnabled = true;


  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.actDate = null;
    this.name = StringUtils.EMPTY_STRING;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    setActDateEnable(isActDateEnabled);
  }

  protected void setActDateEnable(boolean isEnabled) {
    view.getWidget("actDate").setReadOnly(!isEnabled);
  }

  /**
   * @return Returns the actDate.
   */
  public Date getActDate() {
    return actDate;
  }

  /**
   * @param actDate the actDate to set
   */
  public void setActDate(Date actDate) {
    this.actDate = actDate;
  }

  /**
   * @return Returns the name.
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the isActDateEnabled
   */
  public boolean isActDateEnabled() {
    return this.isActDateEnabled;
  }

  /**
   * @param isActDateEnabled the isActDateEnabled to set
   */
  public void setActDateEnabled(boolean isActDateEnabled) {
    this.isActDateEnabled = isActDateEnabled;
  }

}
