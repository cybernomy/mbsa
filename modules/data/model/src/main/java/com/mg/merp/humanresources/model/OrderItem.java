/*
 * OrderItem.java
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
package com.mg.merp.humanresources.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Пункты приказа"
 *
 * @author Artem V. Sharapov
 * @version $Id: OrderItem.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderItem extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.humanresources.model.Order Order;
  private com.mg.merp.humanresources.model.OrderItemKind OrderItemKind;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.util.Set<OrderItemRollback> OrderRollbackItems;
  private java.util.Set<OrderItemParam> OrderItemParams;


  // Constructors

  /**
   * default constructor
   */
  public OrderItem() {
  }

  /**
   * constructor with id
   */
  public OrderItem(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  /**
   *
   * @param Id
   */
  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.humanresources.model.Order getOrder() {
    return this.Order;
  }

  /**
   *
   * @param HrOrder
   */
  public void setOrder(com.mg.merp.humanresources.model.Order HrOrder) {
    this.Order = HrOrder;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  /**
   *
   * @param SysClient
   */
  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.humanresources.model.OrderItemKind getOrderItemKind() {
    return this.OrderItemKind;
  }

  /**
   *
   * @param HrOrderItemKind
   */
  public void setOrderItemKind(com.mg.merp.humanresources.model.OrderItemKind HrOrderItemKind) {
    this.OrderItemKind = HrOrderItemKind;
  }

  /**
   * @return the orderItemParams
   */
  public java.util.Set<OrderItemParam> getOrderItemParams() {
    return this.OrderItemParams;
  }

  /**
   * @param orderItemParams the orderItemParams to set
   */
  public void setOrderItemParams(java.util.Set<OrderItemParam> orderItemParams) {
    this.OrderItemParams = orderItemParams;
  }

  /**
   * @return the orderRollbackItems
   */
  public java.util.Set<OrderItemRollback> getOrderRollbackItems() {
    return this.OrderRollbackItems;
  }

  /**
   * @param orderRollbackItems the orderRollbackItems to set
   */
  public void setOrderRollbackItems(java.util.Set<OrderItemRollback> orderRollbackItems) {
    this.OrderRollbackItems = orderRollbackItems;
  }

}