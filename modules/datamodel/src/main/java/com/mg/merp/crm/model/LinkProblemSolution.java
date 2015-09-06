/*
 * LinkProblemSolution.java
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
package com.mg.merp.crm.model;


/**
 * @author hbm2java
 * @version $Id: LinkProblemSolution.java,v 1.2 2006/09/06 05:18:40 leonova Exp $
 */
public class LinkProblemSolution extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.crm.model.LinkProblemSolutionId Id;


  // Constructors

  /**
   * default constructor
   */
  public LinkProblemSolution() {
  }

  /**
   * constructor with Id
   */
  public LinkProblemSolution(com.mg.merp.crm.model.LinkProblemSolutionId Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public com.mg.merp.crm.model.LinkProblemSolutionId getId() {
    return this.Id;
  }

  public void setId(com.mg.merp.crm.model.LinkProblemSolutionId Id) {
    this.Id = Id;
  }


}