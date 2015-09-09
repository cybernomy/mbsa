/*
 * FeeSummaryHead.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FeeSummaryHead.java,v 1.5 2008/02/29 12:33:10 safonov Exp $
 */
public class FeeSummaryHead extends com.mg.merp.document.model.DocHead
    implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

  // Fields

  private com.mg.merp.salary.model.PayRoll PayRoll;

  // Constructors

  /**
   * default constructor
   */
  public FeeSummaryHead() {
  }

  // Property accessors
  @DataItemName("Salary.FeeSummaryHead.PayRoll")
  public com.mg.merp.salary.model.PayRoll getPayRoll() {
    return this.PayRoll;
  }

  public void setPayRoll(com.mg.merp.salary.model.PayRoll SalPayRoll) {
    this.PayRoll = SalPayRoll;
  }

}