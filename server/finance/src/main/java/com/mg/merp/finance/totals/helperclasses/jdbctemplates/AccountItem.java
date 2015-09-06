/*
 * AccountItem.java
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

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: AccountItem.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class AccountItem {
  private int id;
  private String upcode;

  public AccountItem() {
  }

  public AccountItem(int id, String upcode) {
    this.id = id;
    this.upcode = upcode;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUpCode() {
    return upcode;
  }

  public void setCode(String upcode) {
    this.upcode = upcode;
  }
}
