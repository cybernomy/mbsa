/*
 * LiabilityRest.java
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
import com.mg.merp.reference.model.Contractor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер формы условий отбора "Обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityRest.java,v 1.3 2007/05/14 05:23:52 sharapov Exp $
 */
public class LiabilityRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("PaymentControl.Cond.Liability.RegDate1") //$NON-NLS-1$
  private Date regDate1 = null;
  @DataItemName("PaymentControl.Cond.Liability.RegDate2") //$NON-NLS-1$
  private Date regDate2 = null;
  @DataItemName("PaymentControl.Cond.Liability.DateToExecute1") //$NON-NLS-1$
  private Date dateToExecute1 = null;
  @DataItemName("PaymentControl.Cond.Liability.DateToExecute2") //$NON-NLS-1$
  private Date dateToExecute2 = null;
  @DataItemName("PaymentControl.PartnerTo") //$NON-NLS-1$
  private Contractor toCode = null;
  @DataItemName("PaymentControl.PartnerFrom") //$NON-NLS-1$
  private Contractor fromCode = null;
  @DataItemName("PaymentControl.Cond.Liability.Sum1") //$NON-NLS-1$
  private BigDecimal sum1 = null;
  @DataItemName("PaymentControl.Cond.Liability.Sum2") //$NON-NLS-1$
  private BigDecimal sum2 = null;
  @DataItemName("PaymentControl.Cond.Liability.LESExecuted") //$NON-NLS-1$
  private boolean lesExecuted = false;
  @DataItemName("PaymentControl.Cond.Liability.LESPartExecuted") //$NON-NLS-1$
  private boolean lesPartExecuted = false;
  @DataItemName("PaymentControl.Cond.Liability.LESNotExecuted") //$NON-NLS-1$
  private boolean lesNotExecuted = false;
  @DataItemName("PaymentControl.Cond.Liability.ShowLiabByOrg") //$NON-NLS-1$
  private boolean showLiabByOrg = false;
  @DataItemName("PaymentControl.Cond.Liability.ShowLiabSelectRes") //$NON-NLS-1$
  private boolean showLiabSelectRes = false;
  private int direction = 0;

  private boolean isDateToExecuteEnabled = true;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.regDate1 = null;
    this.regDate2 = null;
    this.dateToExecute1 = null;
    this.dateToExecute2 = null;
    this.toCode = null;
    this.fromCode = null;
    this.sum1 = null;
    this.sum2 = null;
    this.lesExecuted = false;
    this.lesNotExecuted = false;
    this.lesPartExecuted = false;
    this.showLiabByOrg = false;
    this.showLiabSelectRes = false;
    this.direction = 0;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    setToExecuteDateEnabled(isDateToExecuteEnabled);
  }

  private void setToExecuteDateEnabled(boolean isEnabled) {
    view.getWidget("dateToExecute1").setReadOnly(!isEnabled);
    view.getWidget("dateToExecute2").setReadOnly(!isEnabled);
  }


  /**
   * @return Returns the dateToExecute1.
   */
  protected Date getDateToExecute1() {
    return dateToExecute1;
  }

  /**
   * @return Returns the dateToExecute2.
   */
  protected Date getDateToExecute2() {
    return dateToExecute2;
  }

  /**
   * @return Returns the direction.
   */
  protected int getDirection() {
    return direction;
  }

  /**
   * @return Returns the fromCode.
   */
  protected Contractor getFromCode() {
    return fromCode;
  }

  /**
   * @return Returns the lesExecuted.
   */
  protected boolean isLesExecuted() {
    return lesExecuted;
  }

  /**
   * @return Returns the lesNotExecuted.
   */
  protected boolean isLesNotExecuted() {
    return lesNotExecuted;
  }

  /**
   * @return Returns the lesPartExecuted.
   */
  protected boolean isLesPartExecuted() {
    return lesPartExecuted;
  }

  /**
   * @return Returns the regDate1.
   */
  protected Date getRegDate1() {
    return regDate1;
  }

  /**
   * @return Returns the regDate2.
   */
  protected Date getRegDate2() {
    return regDate2;
  }

  /**
   * @return Returns the showLiabByOrg.
   */
  protected boolean isShowLiabByOrg() {
    return showLiabByOrg;
  }

  /**
   * @return Returns the showLiabSelectRes.
   */
  protected boolean isShowLiabSelectRes() {
    return showLiabSelectRes;
  }

  /**
   * @return Returns the sum1.
   */
  protected BigDecimal getSum1() {
    return sum1;
  }

  /**
   * @return Returns the sum2.
   */
  protected BigDecimal getSum2() {
    return sum2;
  }

  /**
   * @return Returns the toCode.
   */
  protected Contractor getToCode() {
    return toCode;
  }

  public boolean isDateToExecuteEnabled() {
    return isDateToExecuteEnabled;
  }

  public void setDateToExecuteEnabled(boolean isDateToExecuteEnabled) {
    this.isDateToExecuteEnabled = isDateToExecuteEnabled;
  }

}
