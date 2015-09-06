/*
 * OrderHead.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: OrderHead.java,v 1.7 2008/02/29 12:34:16 safonov Exp $
 */
public class OrderHead extends com.mg.merp.document.model.DocHead implements
    java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

  // Fields

  private com.mg.merp.reference.model.Contractor Responsible;

  private com.mg.merp.reference.model.Contractor Consumer;

  private java.math.BigDecimal SummaCurWithDiscount;

  private java.math.BigDecimal SummaNatWithDiscount;

  private java.math.BigDecimal AddExpenses;

  private java.math.BigDecimal DiscountOnDoc;

  private java.math.BigDecimal DiscountOnLine;

  private OrderDueDateKind DueDateKind;

  private java.math.BigDecimal DueDateQuan;

  private java.util.Date DueDate;

  private java.lang.Short CreditTerm;

  private java.math.BigDecimal Penalty;

  private java.lang.String Comment;

  private boolean FixedInput;

  private OrderStatus Status;

  // Constructors

  /**
   * default constructor
   */
  public OrderHead() {
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.Responsible")
  public com.mg.merp.reference.model.Contractor getResponsible() {
    return this.Responsible;
  }

  public void setResponsible(
      com.mg.merp.reference.model.Contractor Responsible) {
    this.Responsible = Responsible;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.Consumer")
  public com.mg.merp.reference.model.Contractor getConsumer() {
    return this.Consumer;
  }

  public void setConsumer(com.mg.merp.reference.model.Contractor Consumer) {
    this.Consumer = Consumer;
  }


  /**
   *
   */

  @DataItemName("Warehouse.OrderHead.AddExpenses")
  public java.math.BigDecimal getAddExpenses() {
    return this.AddExpenses;
  }

  public void setAddExpenses(java.math.BigDecimal AddExpenses) {
    this.AddExpenses = AddExpenses;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.DiscountOnDoc")
  public java.math.BigDecimal getDiscountOnDoc() {
    return this.DiscountOnDoc;
  }

  public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
    this.DiscountOnDoc = DiscountOnDoc;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.DiscountOnLine")
  public java.math.BigDecimal getDiscountOnLine() {
    return this.DiscountOnLine;
  }

  public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
    this.DiscountOnLine = DiscountOnLine;
  }

  /**
   *
   */
  public OrderDueDateKind getDueDateKind() {
    return this.DueDateKind;
  }

  public void setDueDateKind(OrderDueDateKind DueDateKind) {
    this.DueDateKind = DueDateKind;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.DueDateQuan")
  public java.math.BigDecimal getDueDateQuan() {
    return this.DueDateQuan;
  }

  public void setDueDateQuan(java.math.BigDecimal DueDateQuan) {
    this.DueDateQuan = DueDateQuan;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.DueDate")
  public java.util.Date getDueDate() {
    return this.DueDate;
  }

  public void setDueDate(java.util.Date DueDate) {
    this.DueDate = DueDate;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.CreditTerm")
  public java.lang.Short getCreditTerm() {
    return this.CreditTerm;
  }

  public void setCreditTerm(java.lang.Short CreditTerm) {
    this.CreditTerm = CreditTerm;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.Penalty")
  public java.math.BigDecimal getPenalty() {
    return this.Penalty;
  }

  public void setPenalty(java.math.BigDecimal Penalty) {
    this.Penalty = Penalty;
  }

  /**
   *
   */

  @DataItemName("Warehouse.OrderHead.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Warehouse.OrderHead.FixedInput")
  public boolean getFixedInput() {
    return this.FixedInput;
  }

  public void setFixedInput(boolean FixedInput) {
    this.FixedInput = FixedInput;
  }

  /**
   *
   */

  public OrderStatus getStatus() {
    return this.Status;
  }

  public void setStatus(OrderStatus Status) {
    this.Status = Status;
  }

  /**
   * @return the summaCurWithDiscount
   */
  @DataItemName("Warehouse.BillHead.SummaCurWithDiscount")
  public java.math.BigDecimal getSummaCurWithDiscount() {
    return SummaCurWithDiscount;
  }

  /**
   * @param summaCurWithDiscount the summaCurWithDiscount to set
   */
  public void setSummaCurWithDiscount(java.math.BigDecimal summaCurWithDiscount) {
    SummaCurWithDiscount = summaCurWithDiscount;
  }

  /**
   * @return the summaNatWithDiscount
   */
  @DataItemName("Warehouse.BillHead.SummaNatWithDiscount")
  public java.math.BigDecimal getSummaNatWithDiscount() {
    return SummaNatWithDiscount;
  }

  /**
   * @param summaNatWithDiscount the summaNatWithDiscount to set
   */
  public void setSummaNatWithDiscount(java.math.BigDecimal summaNatWithDiscount) {
    SummaNatWithDiscount = summaNatWithDiscount;
  }

}