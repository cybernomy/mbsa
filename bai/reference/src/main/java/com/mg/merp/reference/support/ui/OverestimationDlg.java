/*
 * OverestimationDlg.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultDialog;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер диалога "Переоценка"
 *
 * @author Artem V. Sharapov
 * @version $Id: OverestimationDlg.java,v 1.2 2009/02/12 07:59:01 sharapov Exp $
 */
public class OverestimationDlg extends DefaultDialog {

  public static final String WINDOW_NAME = "com.mg.merp.reference.OverestimationDlg";

  @DataItemName("Reference.OverestimationDlg.Percent")
  private BigDecimal percent;
  private Date actualDate;
  private Integer precision;

  public OverestimationDlg() {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    view.pack();
    super.doOnRun();
  }

  /**
   * @return the actualDate
   */
  public Date getActualDate() {
    return this.actualDate;
  }

  /**
   * @param actualDate the actualDate to set
   */
  public void setActualDate(Date actualDate) {
    this.actualDate = actualDate;
  }

  /**
   * @return the percent
   */
  public BigDecimal getPercent() {
    return this.percent;
  }

  /**
   * @param percent the percent to set
   */
  public void setPercent(BigDecimal percent) {
    this.percent = percent;
  }

  /**
   * @return the precision
   */
  public Integer getPrecision() {
    return this.precision;
  }

  /**
   * @param precision the precision to set
   */
  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

}