/*
 * HolidaysRest.java
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
import com.mg.framework.generic.ui.DefaultRestrictionForm;

/**
 * Контроллер формы условий отбора праздничных дней
 *
 * @author leonova
 * @version $Id: HolidaysRest.java,v 1.3 2006/10/03 05:36:17 leonova Exp $
 */
public class HolidaysRest extends DefaultRestrictionForm {

  @DataItemName("Reference.Holidays.HYear")
  private Integer year = null;


  @Override
  protected void doClearRestrictionItem() {
    this.year = null;
  }


  /**
   * @return Returns the year.
   */
  protected Integer getYear() {
    return year;
  }


}
