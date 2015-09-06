/*
 * PersonnelRest.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.reference.support.ui.NaturalPersonRest;

/**
 * @author leonova
 * @version $Id: PersonnelRest.java,v 1.3 2006/08/15 04:03:22 leonova Exp $
 */
public class PersonnelRest extends NaturalPersonRest {

  @DataItemName("PersonnelRef.Cond.Personnel.TableNumberFrom")
  private String tableNumberFrom = null;
  @DataItemName("PersonnelRef.Cond.Personnel.TableNumberTill")
  private String tableNumberTill = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.tableNumberFrom = null;
    this.tableNumberTill = null;
  }

  /**
   * @return Returns the tableNumberFrom.
   */
  public String getTableNumberFrom() {
    return tableNumberFrom;
  }

  /**
   * @return Returns the tableNumberTill.
   */
  public String getTableNumberTill() {
    return tableNumberTill;
  }


}
