/*
 * SerialNumberModelItem.java
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
package com.mg.merp.warehouse.support.ui;

/**
 * Модель данных выбора серийных номеров
 *
 * @author Artem V. Sharapov
 * @version $Id: SerialNumberModelItem.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class SerialNumberModelItem {
  private String number;
  private boolean isChecked;

  public SerialNumberModelItem(String number, boolean isChecked) {
    this.number = number;
    this.isChecked = isChecked;
  }

  public SerialNumberModelItem(String number) {
    this(number, false);
  }

  /**
   * @return the number
   */
  public String getNumber() {
    return this.number;
  }

  /**
   * @param number the number to set
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   * @return the isChecked
   */
  public boolean getIsChecked() {
    return this.isChecked;
  }

  /**
   * @param isChecked the isChecked to set
   */
  public void setChecked(boolean isChecked) {
    this.isChecked = isChecked;
  }

}
