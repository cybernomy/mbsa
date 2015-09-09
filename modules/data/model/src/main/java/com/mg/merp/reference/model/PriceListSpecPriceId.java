/*
 * PriceListSpecPriceId.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;


/**
 * @author hbm2java
 * @version $Id: PriceListSpecPriceId.java,v 1.3 2006/12/27 05:59:37 sharapov Exp $
 */
public class PriceListSpecPriceId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields
  private com.mg.merp.reference.model.PriceListSpec PriceListSpec;
  private com.mg.merp.reference.model.PriceType PriceType;
  private com.mg.merp.core.model.SysClient SysClient;

  // Constructors

  /**
   * default constructor
   */
  public PriceListSpecPriceId() {
  }

  // Property accessors

  /**

   */
  public com.mg.merp.reference.model.PriceListSpec getPriceListSpec() {
    return this.PriceListSpec;
  }

  public void setPriceListSpec(com.mg.merp.reference.model.PriceListSpec PriceListSpec) {
    this.PriceListSpec = PriceListSpec;
  }

  /**

   */
  public com.mg.merp.reference.model.PriceType getPriceType() {
    return this.PriceType;
  }

  public void setPriceType(com.mg.merp.reference.model.PriceType PriceType) {
    this.PriceType = PriceType;
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
  public boolean equals(Object other) {
    if ((this == other)) return true;
    if ((other == null)) return false;
    if (!(other instanceof PriceListSpecPriceId)) return false;
    PriceListSpecPriceId castOther = (PriceListSpecPriceId) other;

    return (this.getPriceListSpec() == castOther.getPriceListSpec()) || (this.getPriceListSpec() == null ? false : (castOther.getPriceListSpec() == null ? false : this.getPriceListSpec().equals(castOther.getPriceListSpec())))
        && (this.getPriceType() == castOther.getPriceType()) || (this.getPriceType() == null ? false : (castOther.getPriceType() == null ? false : this.getPriceType().equals(castOther.getPriceType())))
        && (this.getSysClient() == castOther.getSysClient()) || (this.getSysClient() == null ? false : (castOther.getSysClient() == null ? false : this.getSysClient().equals(castOther.getSysClient())));
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + this.getPriceListSpec().hashCode();
    result = 37 * result + this.getPriceType().hashCode();
    result = 37 * result + this.getSysClient().hashCode();

    return result;
  }

}