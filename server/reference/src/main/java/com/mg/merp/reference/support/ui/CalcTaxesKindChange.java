/*
 * CalcTaxesKindChange.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.reference.model.CalcTaxesSubject;
import com.mg.merp.reference.model.Tax;

/**
 * @author leonova
 * @version $Id: CalcTaxesKindChange.java,v 1.1 2006/10/24 10:30:54 leonova Exp $
 */
public class CalcTaxesKindChange extends DefaultDialog {

  private Tax tax;
  @DataItemName("Reference.TaxLink.FeeOrder")
  private Short feeOrder;
  private CalcTaxesSubject subject;
  @DataItemName("Reference.CalcTaxLink.Included")
  private boolean included;

  public Short getFeeOrder() {
    return feeOrder;
  }

  public void setFeeOrder(Short feeOrder) {
    this.feeOrder = feeOrder;
  }

  public boolean isIncluded() {
    return included;
  }

  public void setIncluded(boolean included) {
    this.included = included;
  }

  public CalcTaxesSubject getSubject() {
    return subject;
  }

  public void setSubject(CalcTaxesSubject subject) {
    this.subject = subject;
  }

  public Tax getTax() {
    return tax;
  }

  public void setTax(Tax tax) {
    this.tax = tax;
  }


}
