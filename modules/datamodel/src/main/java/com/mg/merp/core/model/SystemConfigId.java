/*
 * SystemConfigId.java
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
 * @version $Id: SystemConfigId.java,v 1.2 2006/11/02 15:44:39 safonov Exp $
 */
public class SystemConfigId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  // private com.mg.merp.reference.model.Currency Currency;
// private com.mg.merp.reference.model.Currency Currency_1;
  private short MajorVersion;
  private short MinorVersion;
  private short Release;
  private java.lang.Short UseAnaliticLink;
  private java.lang.Short RecurseFolder;
  private java.lang.Short RecurseCatfolder;
  private java.lang.Short RecurseOrgunit;
  private java.lang.Short DelaysOn;
  private java.lang.Short CurrencyPrec;
  private short DeferRemnacc;
  private short LogV1DocDel;
  private short LogV1Rollback;
  private java.lang.Short LogV1Partner;
  private java.lang.Short LogV1Catalog;


  // Constructors

  /**
   * default constructor
   */
  public SystemConfigId() {
  }


  // Property accessors
  /**

   */

//    public com.mg.merp.reference.model.Currency getCurrency () {
//        return this.Currency;
//    }
//    
//   public void setCurrency (com.mg.merp.reference.model.Currency Currency) {
//        this.Currency = Currency;
//    }
  /**

   */

//    public com.mg.merp.reference.model.Currency getCurrency_1 () {
//        return this.Currency_1;
//    }
//    
//   public void setCurrency_1 (com.mg.merp.reference.model.Currency Currency_1) {
//        this.Currency_1 = Currency_1;
//    }

  /**

   */

  public short getMajorVersion() {
    return this.MajorVersion;
  }

  public void setMajorVersion(short MajorVersion) {
    this.MajorVersion = MajorVersion;
  }

  /**

   */

  public short getMinorVersion() {
    return this.MinorVersion;
  }

  public void setMinorVersion(short MinorVersion) {
    this.MinorVersion = MinorVersion;
  }

  /**

   */

  public short getRelease() {
    return this.Release;
  }

  public void setRelease(short Release) {
    this.Release = Release;
  }

  /**

   */

  public java.lang.Short getUseAnaliticLink() {
    return this.UseAnaliticLink;
  }

  public void setUseAnaliticLink(java.lang.Short UseAnaliticLink) {
    this.UseAnaliticLink = UseAnaliticLink;
  }

  /**

   */

  public java.lang.Short getRecurseFolder() {
    return this.RecurseFolder;
  }

  public void setRecurseFolder(java.lang.Short RecurseFolder) {
    this.RecurseFolder = RecurseFolder;
  }

  /**

   */

  public java.lang.Short getRecurseCatfolder() {
    return this.RecurseCatfolder;
  }

  public void setRecurseCatfolder(java.lang.Short RecurseCatfolder) {
    this.RecurseCatfolder = RecurseCatfolder;
  }

  /**

   */

  public java.lang.Short getRecurseOrgunit() {
    return this.RecurseOrgunit;
  }

  public void setRecurseOrgunit(java.lang.Short RecurseOrgunit) {
    this.RecurseOrgunit = RecurseOrgunit;
  }

  /**

   */

  public java.lang.Short getDelaysOn() {
    return this.DelaysOn;
  }

  public void setDelaysOn(java.lang.Short DelaysOn) {
    this.DelaysOn = DelaysOn;
  }

  /**

   */

  public java.lang.Short getCurrencyPrec() {
    return this.CurrencyPrec;
  }

  public void setCurrencyPrec(java.lang.Short CurrencyPrec) {
    this.CurrencyPrec = CurrencyPrec;
  }

  /**

   */

  public short getDeferRemnacc() {
    return this.DeferRemnacc;
  }

  public void setDeferRemnacc(short DeferRemnacc) {
    this.DeferRemnacc = DeferRemnacc;
  }

  /**

   */

  public short getLogV1DocDel() {
    return this.LogV1DocDel;
  }

  public void setLogV1DocDel(short LogV1DocDel) {
    this.LogV1DocDel = LogV1DocDel;
  }

  /**

   */

  public short getLogV1Rollback() {
    return this.LogV1Rollback;
  }

  public void setLogV1Rollback(short LogV1Rollback) {
    this.LogV1Rollback = LogV1Rollback;
  }

  /**

   */

  public java.lang.Short getLogV1Partner() {
    return this.LogV1Partner;
  }

  public void setLogV1Partner(java.lang.Short LogV1Partner) {
    this.LogV1Partner = LogV1Partner;
  }

  /**

   */

  public java.lang.Short getLogV1Catalog() {
    return this.LogV1Catalog;
  }

  public void setLogV1Catalog(java.lang.Short LogV1Catalog) {
    this.LogV1Catalog = LogV1Catalog;
  }

}