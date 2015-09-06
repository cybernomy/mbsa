/*
 * LinkStage.java
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


/**
 * @author hbm2java
 * @version $Id: LinkStage.java,v 1.2 2005/07/26 12:35:01 safonov Exp $
 */
public class LinkStage extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.docprocess.model.DocProcessStage nextStage;
  private com.mg.merp.docprocess.model.DocProcessStage prevStage;
  private com.mg.merp.core.model.SysClient SysClient;
  private boolean directly;


  // Constructors

  /**
   * default constructor
   */
  public LinkStage() {
  }

  /**
   * constructor with id
   */
  public LinkStage(java.lang.Integer Id) {
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

  public com.mg.merp.docprocess.model.DocProcessStage getNextStage() {
    return this.nextStage;
  }

  public void setNextStage(com.mg.merp.docprocess.model.DocProcessStage Docprocessstage) {
    this.nextStage = Docprocessstage;
  }

  /**

   */

  public com.mg.merp.docprocess.model.DocProcessStage getPrevStage() {
    return this.prevStage;
  }

  public void setPrevStage(com.mg.merp.docprocess.model.DocProcessStage Docprocessstage_1) {
    this.prevStage = Docprocessstage_1;
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

  public boolean isDirectly() {
    return this.directly;
  }

  public void setDirectly(boolean Directly) {
    this.directly = Directly;
  }


}