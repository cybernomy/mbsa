/*
 * Strings.java
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
package com.mg.merp.core.model;


/**
 * @author hbm2java
 * @version $Id: Strings.java,v 1.1 2005/06/10 06:52:28 safonov Exp $
 */
public class Strings extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.core.model.StringsId id;
  private java.lang.String Text;


  // Constructors

  /**
   * default constructor
   */
  public Strings() {
  }

  /**
   * constructor with id
   */
  public Strings(com.mg.merp.core.model.StringsId id) {
    this.id = id;
  }


  // Property accessors

  /**

   */

  public com.mg.merp.core.model.StringsId getId() {
    return this.id;
  }

  public void setId(com.mg.merp.core.model.StringsId id) {
    this.id = id;
  }

  /**

   */

  public java.lang.String getText() {
    return this.Text;
  }

  public void setText(java.lang.String Text) {
    this.Text = Text;
  }


}