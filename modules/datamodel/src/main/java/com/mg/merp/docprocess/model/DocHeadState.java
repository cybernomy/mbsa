/*
 * DocHeadState.java
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
package com.mg.merp.docprocess.model;

import java.io.Serializable;

/**
 * @author hbm2java
 * @version $Id: DocHeadState.java,v 1.3 2006/03/01 16:03:02 safonov Exp $
 */
public class DocHeadState extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.docprocess.model.DocAction docAction;

  private com.mg.merp.security.model.SecUser user;

  private java.math.BigDecimal readySum;

  private java.lang.Integer Data1;

  private java.lang.Integer Data2;

  private java.util.Date dateTime;

  private Serializable stateValue;

  private java.util.Set<DocSpecState> docSpecStates;

  // Constructors

  /**
   * default constructor
   */
  public DocHeadState() {
  }

  /**
   * constructor with id
   */
  public DocHeadState(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.docprocess.model.DocAction getDocAction() {
    return this.docAction;
  }

  public void setDocAction(com.mg.merp.docprocess.model.DocAction Docaction) {
    this.docAction = Docaction;
  }

  /**
   *
   */

  public com.mg.merp.security.model.SecUser getUser() {
    return this.user;
  }

  public void setUser(com.mg.merp.security.model.SecUser SecUsers) {
    this.user = SecUsers;
  }

  /**
   *
   */

  public java.math.BigDecimal getReadySum() {
    return this.readySum;
  }

  public void setReadySum(java.math.BigDecimal Readysumma) {
    this.readySum = Readysumma;
  }

  /**
   *
   */

  public java.lang.Integer getData1() {
    return this.Data1;
  }

  public void setData1(java.lang.Integer Data1) {
    this.Data1 = Data1;
  }

  /**
   *
   */

  public java.lang.Integer getData2() {
    return this.Data2;
  }

  public void setData2(java.lang.Integer Data2) {
    this.Data2 = Data2;
  }

  /**
   *
   */

  public java.util.Date getDateTime() {
    return this.dateTime;
  }

  public void setDateTime(java.util.Date Datetime) {
    this.dateTime = Datetime;
  }

  /**
   *
   */

  public java.util.Set<DocSpecState> getDocSpecStates() {
    return this.docSpecStates;
  }

  public void setDocSpecStates(java.util.Set<DocSpecState> SetOfDocspecstate) {
    this.docSpecStates = SetOfDocspecstate;
  }

  /**
   * @return Returns the stateValue.
   */
  public Serializable getStateValue() {
    return stateValue;
  }

  /**
   * @param stateValue The stateValue to set.
   */
  public void setStateValue(Serializable data) {
    this.stateValue = data;
  }

}