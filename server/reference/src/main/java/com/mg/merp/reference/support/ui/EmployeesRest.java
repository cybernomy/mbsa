/*
 * EmployeesRest.java
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


/**
 * Контроллер формы условий отбора сотрудников
 *
 * @author Artem V. Sharapov
 */
public class EmployeesRest extends NaturalPersonRest {

  @DataItemName("Reference.Cond.Employees.TableNumber") //$NON-NLS-1$
  private String tableNumber = null;

  @DataItemName("Reference.Cond.Employees.PersonCode") //$NON-NLS-1$
  private String personCode = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.tableNumber = null;
    this.personCode = null;
  }

  /**
   * @return табельный номер
   */
  public String getTableNumber() {
    return tableNumber;
  }

  /**
   * @return Код(ФИО)
   */
  public String getPersonCode() {
    return personCode;
  }

}
