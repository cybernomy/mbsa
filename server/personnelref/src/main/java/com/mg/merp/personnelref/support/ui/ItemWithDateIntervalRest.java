/*
 * ItemWithDateIntervalRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;

import java.util.Date;

/**
 * Контроллер формы условий отбора по учетным периодам
 *
 * @author leonova
 * @version $Id: ItemWithDateIntervalRest.java,v 1.7 2006/08/28 12:42:24 leonova Exp $
 */
public class ItemWithDateIntervalRest extends
    DefaultRestrictionForm {

  @DataItemName("PersonnelRef.Cond.BeginDate")
  private Date beginDate = null;
  @DataItemName("PersonnelRef.Cond.EndDate")
  private Date endDate = null;
/*	private boolean arbitraryPeriod = false;*/


  @Override
  protected void doClearRestrictionItem() {
    this.beginDate = null;
    this.endDate = null;
  }

  /**
   * @return Returns the beginDate.
   */
  public Date getBeginDate() {
    return beginDate;
  }

  /**
   * @return Returns the endDate.
   */
  public Date getEndDate() {
    return endDate;
  }


}
