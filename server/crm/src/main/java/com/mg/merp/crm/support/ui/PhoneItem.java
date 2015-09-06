/*
 * PhoneItem.java
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

/**
 * Информациия о телефоне
 *
 * @author Artem V. Sharapov
 * @version $Id: PhoneItem.java,v 1.1 2007/02/07 07:00:13 sharapov Exp $
 */
public class PhoneItem {

  // Fields

  private java.lang.Integer id;
  private java.lang.String areaCode;
  private java.lang.String phone;
  private java.lang.String phoneKind;

  public PhoneItem() {
  }

  public PhoneItem(Integer id, String areaCode, String phone, java.lang.String phoneKind) {
    this.id = id;
    this.areaCode = areaCode;
    this.phone = phone;
    this.phoneKind = phoneKind;
  }

  public PhoneItem(java.lang.Integer id) {
    this.id = id;
  }

  // Property accessors

  /**
   * @return the areaCode
   */
  public java.lang.String getAreaCode() {
    return areaCode;
  }

  /**
   * @param areaCode the areaCode to set
   */
  public void setAreaCode(java.lang.String areaCode) {
    this.areaCode = areaCode;
  }

  /**
   * @return the id
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(java.lang.Integer id) {
    this.id = id;
  }

  /**
   * @return the phone
   */
  public java.lang.String getPhone() {
    return phone;
  }

  /**
   * @param phone the phone to set
   */
  public void setPhone(java.lang.String phone) {
    this.phone = phone;
  }

  /**
   * @return the phoneKind
   */
  public String getPhoneKind() {
    return phoneKind;
  }

  /**
   * @param phoneKind the phoneKind to set
   */
  public void setPhoneKind(String phoneKind) {
    this.phoneKind = phoneKind;
  }
}
