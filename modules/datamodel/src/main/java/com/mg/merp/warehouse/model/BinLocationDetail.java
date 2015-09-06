/*
 * BinLocationDetail.java
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
 * Модель бизнес-компонента "Секции хранения в партии"
 *
 * @author Artem V. Sharapov
 * @version $Id: BinLocationDetail.java,v 1.2 2007/07/19 11:25:02 sharapov Exp $
 */
public class BinLocationDetail extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;
  private com.mg.merp.warehouse.model.BinLocation BinLocation;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.warehouse.model.StockBatch StockBatch;
  private java.math.BigDecimal Quantity;
  private java.lang.Integer DocSpecId;
  private short Kind;


  // Constructors

  /**
   * default constructor
   */
  public BinLocationDetail() {
  }

  /**
   * constructor with id
   */
  public BinLocationDetail(int Id) {
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
  public com.mg.merp.warehouse.model.BinLocation getBinLocation() {
    return this.BinLocation;
  }

  public void setBinLocation(com.mg.merp.warehouse.model.BinLocation BinLocation) {
    this.BinLocation = BinLocation;
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
  public com.mg.merp.warehouse.model.StockBatch getStockBatch() {
    return this.StockBatch;
  }

  public void setStockBatch(com.mg.merp.warehouse.model.StockBatch StockBatch) {
    this.StockBatch = StockBatch;
  }

  /**

   */
  @DataItemName("Warehouse.BinLocationDetail.Quantity") //$NON-NLS-1$
  public java.math.BigDecimal getQuantity() {
    return this.Quantity;
  }

  public void setQuantity(java.math.BigDecimal Quantity) {
    this.Quantity = Quantity;
  }

  /**

   */
  public java.lang.Integer getDocSpecId() {
    return this.DocSpecId;
  }

  public void setDocSpecId(java.lang.Integer DocSpecId) {
    this.DocSpecId = DocSpecId;
  }

  /**

   */
  public short getKind() {
    return this.Kind;
  }

  public void setKind(short Kind) {
    this.Kind = Kind;
  }

}