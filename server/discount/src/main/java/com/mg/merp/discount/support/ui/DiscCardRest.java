/*
 * DiscCardRest.java
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
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.reference.model.Contractor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер формы условий отбора "Дисконтных карт"
 *
 * @author Artem V. Sharapov
 * @version $Id: DiscCardRest.java,v 1.1 2007/12/04 14:58:15 sharapov Exp $
 */
public class DiscCardRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("Discount.Card.CardNum") //$NON-NLS-1$
  private String disCardNumber;

  @DataItemName("Discount.Car.Owner") //$NON-NLS-1$
  private Contractor disCardOwner;

  @DataItemName("Discount.Car.Owner") //$NON-NLS-1$
  private Contractor disCardUser;

  private BigDecimal creditLimitFrom;
  private BigDecimal creditLimitTill;

  private Date actDate;
  private CardActivityKind activity;


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    disCardNumber = null;
    disCardOwner = null;
    disCardUser = null;
    creditLimitFrom = null;
    creditLimitTill = null;
    actDate = null;
    activity = null;
  }

  /**
   * @return the disCardNumber
   */
  public String getDisCardNumber() {
    return this.disCardNumber;
  }

  /**
   * @param disCardNumber the disCardNumber to set
   */
  public void setDisCardNumber(String disCardNumber) {
    this.disCardNumber = disCardNumber;
  }

  /**
   * @return the disCardOwner
   */
  public Contractor getDisCardOwner() {
    return this.disCardOwner;
  }

  /**
   * @param disCardOwner the disCardOwner to set
   */
  public void setDisCardOwner(Contractor disCardOwner) {
    this.disCardOwner = disCardOwner;
  }

  /**
   * @return the disCardUser
   */
  public Contractor getDisCardUser() {
    return this.disCardUser;
  }

  /**
   * @param disCardUser the disCardUser to set
   */
  public void setDisCardUser(Contractor disCardUser) {
    this.disCardUser = disCardUser;
  }

  /**
   * @return the creditLimitFrom
   */
  public BigDecimal getCreditLimitFrom() {
    return this.creditLimitFrom;
  }

  /**
   * @param creditLimitFrom the creditLimitFrom to set
   */
  public void setCreditLimitFrom(BigDecimal creditLimitFrom) {
    this.creditLimitFrom = creditLimitFrom;
  }

  /**
   * @return the creditLimitTill
   */
  public BigDecimal getCreditLimitTill() {
    return this.creditLimitTill;
  }

  /**
   * @param creditLimitTill the creditLimitTill to set
   */
  public void setCreditLimitTill(BigDecimal creditLimitTill) {
    this.creditLimitTill = creditLimitTill;
  }

  /**
   * @return the actDate
   */
  public Date getActDate() {
    return this.actDate;
  }

  /**
   * @param actDate the actDate to set
   */
  public void setActDate(Date actDate) {
    this.actDate = actDate;
  }

  /**
   * @return the activity
   */
  public CardActivityKind getActivity() {
    return this.activity;
  }

  /**
   * @param activity the activity to set
   */
  public void setActivity(CardActivityKind activity) {
    this.activity = activity;
  }


}
