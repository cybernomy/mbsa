/*
 * VarianceDocumentHead.java
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
package com.mg.merp.manufacture.model;


/**
 * @author hbm2java
 * @version $Id: VarianceDocumentHead.java,v 1.6 2008/02/29 12:35:31 safonov Exp $
 */
public class VarianceDocumentHead extends com.mg.merp.document.model.DocHead
    implements java.io.Serializable, org.hibernate.bytecode.internal.javassist.FieldHandled {

  // Fields

  private com.mg.merp.mfreference.model.WorkCenter workCenter;

  private com.mg.merp.manufacture.model.Job job;

  // Constructors

  /**
   * default constructor
   */
  public VarianceDocumentHead() {
  }

  // Property accessors

  /**
   *
   */
  public com.mg.merp.mfreference.model.WorkCenter getWC() {
    return this.workCenter;
  }

  public void setWC(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
    this.workCenter = MfWorkCenter;
  }

  /**
   *
   */
  public com.mg.merp.manufacture.model.Job getJob() {
    return this.job;
  }

  public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
    this.job = MfJob;
  }

}