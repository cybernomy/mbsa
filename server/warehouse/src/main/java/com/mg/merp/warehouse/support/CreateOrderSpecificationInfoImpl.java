/*
 * CreateOrderSpecificationInfoImpl.java
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
package com.mg.merp.warehouse.support;

import com.mg.merp.document.support.CreateSpecificationInfoImpl;
import com.mg.merp.warehouse.model.OrderSpec;
import com.mg.merp.warehouse.model.OrderStatus;
import com.mg.merp.warehouse.model.Warehouse;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс дополнительной информации для обработки массовых операций (bulk) со спецификациями заказов
 * поставщиков/покупателей
 *
 * @author Oleg V. Safonov
 * @version $Id: CreateOrderSpecificationInfoImpl.java,v 1.1 2007/07/27 12:05:44 safonov Exp $
 */
public class CreateOrderSpecificationInfoImpl extends
    CreateSpecificationInfoImpl {
  private Date requiredDate;
  private Date promisedDate;
  private String vendorItemCode;
  private Warehouse warehouse;
  private BigDecimal qtyAccepted;
  private BigDecimal qtyInvoiced;
  private BigDecimal qtyReturned;
  private BigDecimal qtyShipped;
  private BigDecimal qtyPicked;
  private BigDecimal qtyOutstanding;
  private boolean closedForPlanning;
  private OrderStatus status;

  /**
   * Коструктор
   */
  public CreateOrderSpecificationInfoImpl(Integer catalogId, Integer pricelistId, BigDecimal price, BigDecimal quantity1, BigDecimal quantity2, Date requiredDate, Date promisedDate, String vendorItemCode, Warehouse warehouse, BigDecimal qtyAccepted, BigDecimal qtyInvoiced, BigDecimal qtyReturned, BigDecimal qtyShipped, BigDecimal qtyPicked, BigDecimal qtyOutstanding, boolean closedForPlanning, OrderStatus status) {
    super(catalogId, pricelistId, price, quantity1, quantity2);
    this.requiredDate = requiredDate;
    this.promisedDate = promisedDate;
    this.vendorItemCode = vendorItemCode;
    this.warehouse = warehouse;
    this.qtyAccepted = qtyAccepted;
    this.qtyInvoiced = qtyInvoiced;
    this.qtyReturned = qtyReturned;
    this.qtyShipped = qtyShipped;
    this.qtyPicked = qtyPicked;
    this.qtyOutstanding = qtyOutstanding;
    this.closedForPlanning = closedForPlanning;
    this.status = status;
  }

  public OrderSpec initOrderSpec(OrderSpec result) {
    result.setRequiredDate(getRequiredDate());
    result.setPromisedDate(getPromisedDate());
    result.setVendorItemCode(getVendorItemCode());
    result.setWarehouse(getWarehouse());
    result.setQtyAccepted(getQtyAccepted());
    result.setQtyInvoiced(getQtyInvoiced());
    result.setQtyOutstanding(getQtyOutstanding());
    result.setQtyPicked(getQtyPicked());
    result.setQtyReturned(getQtyReturned());
    result.setQtyShipped(getQtyShipped());
    result.setClosedForPlanning(isClosedForPlanning());
    result.setStatus(getStatus());
    return result;
  }

  /**
   * @return the closedForPlanning
   */
  public boolean isClosedForPlanning() {
    return closedForPlanning;
  }

  /**
   * @return the promisedDate
   */
  public Date getPromisedDate() {
    return promisedDate;
  }

  /**
   * @return the qtyAccepted
   */
  public BigDecimal getQtyAccepted() {
    return qtyAccepted;
  }

  /**
   * @return the qtyInvoiced
   */
  public BigDecimal getQtyInvoiced() {
    return qtyInvoiced;
  }

  /**
   * @return the qtyOutstanding
   */
  public BigDecimal getQtyOutstanding() {
    return qtyOutstanding;
  }

  /**
   * @return the qtyPicked
   */
  public BigDecimal getQtyPicked() {
    return qtyPicked;
  }

  /**
   * @return the qtyReturned
   */
  public BigDecimal getQtyReturned() {
    return qtyReturned;
  }

  /**
   * @return the qtyShipped
   */
  public BigDecimal getQtyShipped() {
    return qtyShipped;
  }

  /**
   * @return the requiredDate
   */
  public Date getRequiredDate() {
    return requiredDate;
  }

  /**
   * @return the status
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * @return the vendorItemCode
   */
  public String getVendorItemCode() {
    return vendorItemCode;
  }

  /**
   * @return the warehouse
   */
  public Warehouse getWarehouse() {
    return warehouse;
  }

}
