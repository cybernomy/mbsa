/*
 * TaxSumBuf.java
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
package com.mg.merp.reference.model;


/**
 * @author hbm2java
 * @version $Id: TaxSumBuf.java,v 1.1 2005/06/10 06:51:40 safonov Exp $
 */
public class TaxSumBuf extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.reference.model.TaxSumBufId id;


  // Constructors

  /**
   * default constructor
   */
  public TaxSumBuf() {
  }

  /**
   * constructor with id
   */
  public TaxSumBuf(com.mg.merp.reference.model.TaxSumBufId id) {
    this.id = id;
  }


  // Property accessors

  /**

   */

  public com.mg.merp.reference.model.TaxSumBufId getId() {
    return this.id;
  }

  public void setId(com.mg.merp.reference.model.TaxSumBufId id) {
    this.id = id;
  }


}