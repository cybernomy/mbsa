/*
 * LinkSymptomProblem.java
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
 * @version $Id: LinkSymptomProblem.java,v 1.2 2006/09/06 05:18:40 leonova Exp $
 */
public class LinkSymptomProblem extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.crm.model.LinkSymptomProblemId Id;


  // Constructors

  /**
   * default constructor
   */
  public LinkSymptomProblem() {
  }

  /**
   * constructor with Id
   */
  public LinkSymptomProblem(com.mg.merp.crm.model.LinkSymptomProblemId Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public com.mg.merp.crm.model.LinkSymptomProblemId getId() {
    return this.Id;
  }

  public void setId(com.mg.merp.crm.model.LinkSymptomProblemId Id) {
    this.Id = Id;
  }


}