/*
 * SymptomRest.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.crm.model.Problem;

/**
 * Контроллер условий отбора симптомов
 *
 * @author leonova
 * @version $Id: SymptomRest.java,v 1.1 2006/10/16 11:03:03 leonova Exp $
 */
public class SymptomRest extends DefaultHierarhyRestrictionForm {
  @DataItemName("CRM.BigName")
  public String name = "";
  public Problem problem = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.name = "";
    this.problem = null;
  }

  public String getName() {
    return name;
  }

  public Problem getProblem() {
    return problem;
  }
}
