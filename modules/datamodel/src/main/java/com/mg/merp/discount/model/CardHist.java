/*
 * OvrCardHist.java
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
package com.mg.merp.discount.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CardHist.java,v 1.4 2006/08/30 11:59:50 leonova Exp $
 */
public class CardHist extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.discount.model.Card Card;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.security.model.SecUser User;

  private java.util.Date TimeStamp;

  private java.math.BigDecimal Discount;

  private java.lang.String Comments;

  // Constructors

  /**
   * default constructor
   */
  public CardHist() {
  }

  /**
   * constructor with id
   */
  public CardHist(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.discount.model.Card getCard() {
    return this.Card;
  }

  public void setCard(com.mg.merp.discount.model.Card DisCard) {
    this.Card = DisCard;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("Discount.CardHist.User")
  public com.mg.merp.security.model.SecUser getUser() {
    return this.User;
  }

  public void setUser(com.mg.merp.security.model.SecUser SecUsers) {
    this.User = SecUsers;
  }

  /**
   *
   */
  @DataItemName("Discount.CardHist.TimeStamp")
  public java.util.Date getTimeStamp() {
    return this.TimeStamp;
  }

  public void setTimeStamp(java.util.Date TimeStamp) {
    this.TimeStamp = TimeStamp;
  }

  /**
   *
   */
  @DataItemName("Discount.CardHist.Discount")
  public java.math.BigDecimal getDiscount() {
    return this.Discount;
  }

  public void setDiscount(java.math.BigDecimal Discount) {
    this.Discount = Discount;
  }

  /**
   *
   */
  @DataItemName("Discount.CardHist.Comments")
  public java.lang.String getComments() {
    return this.Comments;
  }

  public void setComments(java.lang.String Comments) {
    this.Comments = Comments;
  }

}