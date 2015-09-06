/*
 * ConstantRest.java
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
package com.mg.merp.baiengine.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;

/**
 * Контроллер условий отбора для бизнес-компонента "Константы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantRest.java,v 1.1 2007/08/21 12:56:51 alikaev Exp $
 */
public class ConstantRest extends DefaultHierarhyRestrictionForm {
  @DataItemName("BAi.Code")
  public java.lang.String code = null;
  @DataItemName("BAi.ConstValue.StartDate")
  public java.util.Date startDate = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.code = null;
    this.startDate = null;
  }

  public java.lang.String getCode() {
    return code;
  }

  public java.util.Date getStartDate() {
    return startDate;
  }

}
