/*
 * Schedule.java
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "График иполнения обязательств"
 *
 * @author Artem V. Sharapov
 * @version $Id: Schedule.java,v 1.7 2007/04/21 11:49:33 sharapov Exp $
 */
public class Schedule extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Comments;

  private java.lang.String Name;

  private ScheduleStatus Status;

  private java.util.Set<Item> LsItems;

  // Constructors

  /**
   * default constructor
   */
  public Schedule() {
  }

  /**
   * constructor with id
   */
  public Schedule(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  public void setFolder(com.mg.merp.core.model.Folder Folder) {
    this.Folder = Folder;
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
  @DataItemName("LbSchedule.Schedule.Comment") //$NON-NLS-1$
  public java.lang.String getComments() {
    return this.Comments;
  }

  public void setComments(java.lang.String Comments) {
    this.Comments = Comments;
  }

  /**
   *
   */
  @DataItemName("LbSchedule.Schedule.Name") //$NON-NLS-1$
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  public ScheduleStatus getStatus() {
    return this.Status;
  }

  public void setStatus(ScheduleStatus Status) {
    this.Status = Status;
  }

  /**
   * @return the lsItems
   */
  public java.util.Set<Item> getLsItems() {
    return LsItems;
  }

  /**
   * @param lsItems the lsItems to set
   */
  public void setLsItems(java.util.Set<Item> lsItems) {
    LsItems = lsItems;
  }

}