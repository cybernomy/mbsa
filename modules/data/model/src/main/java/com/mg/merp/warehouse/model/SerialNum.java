/*
 * SerialNum.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Серийные номера"
 *
 * @author Artem V. Sharapov
 * @version $Id: SerialNum.java,v 1.3 2007/07/19 11:25:02 sharapov Exp $
 */
public class SerialNum extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.warehouse.model.StockBatch Batch;
  private java.lang.String SerialNum;
  private java.lang.Integer InComeDocSpecId;
  private java.lang.Integer OutComeDocSpecId;


  // Constructors

  /**
   * default constructor
   */
  public SerialNum() {
  }

  /**
   * constructor with id
   */
  public SerialNum(int Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID") //$NON-NLS-1$
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  /**

   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */
  public com.mg.merp.warehouse.model.StockBatch getBatch() {
    return this.Batch;
  }

  public void setBatch(com.mg.merp.warehouse.model.StockBatch Batch) {
    this.Batch = Batch;
  }

  /**

   */
  @DataItemName("Warehouse.SerialNum.SerialNumber") //$NON-NLS-1$
  public java.lang.String getSerialNum() {
    return this.SerialNum;
  }

  public void setSerialNum(java.lang.String SerialNum) {
    this.SerialNum = SerialNum;
  }

  /**

   */
  public java.lang.Integer getInComeDocSpecId() {
    return this.InComeDocSpecId;
  }

  public void setInComeDocSpecId(java.lang.Integer InComeDocSpecId) {
    this.InComeDocSpecId = InComeDocSpecId;
  }

  /**

   */
  public java.lang.Integer getOutComeDocSpecId() {
    return this.OutComeDocSpecId;
  }

  public void setOutComeDocSpecId(java.lang.Integer OutComeDocSpecId) {
    this.OutComeDocSpecId = OutComeDocSpecId;
  }

}