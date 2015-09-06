/*
 * PeriodItem.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.totals.helperclasses.jdbctemplates;

import java.util.Date;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: PeriodItem.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class PeriodItem {
  private int id;
  private Date beginDate;
  private Date endDate;

  public PeriodItem() {
  }

  public PeriodItem(int id, Date beginDate, Date endDate) {
    this.id = id;
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
