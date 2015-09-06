/*
 * ImportProcessor.java
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
package com.mg.merp.exchange.model;


/**
 * @author hbm2java
 * @version $Id: ImportProcessor.java,v 1.1 2005/06/10 06:53:17 safonov Exp $
 */
public class ImportProcessor extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.exchange.model.Site ExchSite;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.lang.String BeanName;
  private java.lang.String Origin;
  private java.math.BigDecimal OldKey;
  private java.math.BigDecimal NewKey;
  private java.lang.Integer Packet;
  private java.util.Date PacketTime;


  // Constructors

  /**
   * default constructor
   */
  public ImportProcessor() {
  }

  /**
   * constructor with id
   */
  public ImportProcessor(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.exchange.model.Site getExchSite() {
    return this.ExchSite;
  }

  public void setExchSite(com.mg.merp.exchange.model.Site ExchSite) {
    this.ExchSite = ExchSite;
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

  public java.lang.String getBeanName() {
    return this.BeanName;
  }

  public void setBeanName(java.lang.String BeanName) {
    this.BeanName = BeanName;
  }

  /**

   */

  public java.lang.String getOrigin() {
    return this.Origin;
  }

  public void setOrigin(java.lang.String Origin) {
    this.Origin = Origin;
  }

  /**

   */

  public java.math.BigDecimal getOldKey() {
    return this.OldKey;
  }

  public void setOldKey(java.math.BigDecimal OldKey) {
    this.OldKey = OldKey;
  }

  /**

   */

  public java.math.BigDecimal getNewKey() {
    return this.NewKey;
  }

  public void setNewKey(java.math.BigDecimal NewKey) {
    this.NewKey = NewKey;
  }

  /**

   */

  public java.lang.Integer getPacket() {
    return this.Packet;
  }

  public void setPacket(java.lang.Integer Packet) {
    this.Packet = Packet;
  }

  /**

   */

  public java.util.Date getPacketTime() {
    return this.PacketTime;
  }

  public void setPacketTime(java.util.Date PacketTime) {
    this.PacketTime = PacketTime;
  }


}