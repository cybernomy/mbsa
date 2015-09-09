/*
 * BankRequisId.java
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
 * @version $Id: BankRequisId.java,v 1.1 2005/06/10 06:51:44 safonov Exp $
 */
public class BankRequisId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private java.lang.Integer Partner;
  private java.lang.String Bank;
  private java.lang.String BankAcc;
  private java.lang.String CorrAcc;
  private java.lang.String BankAddr;
  private java.lang.String BankCity;
  private java.lang.String BankIdent;
  private boolean IsDefault;
  private java.lang.String BankBranch;
  private java.lang.String Unid;


  // Constructors

  /**
   * default constructor
   */
  public BankRequisId() {
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

  public java.lang.Integer getPartner() {
    return this.Partner;
  }

  public void setPartner(java.lang.Integer Partner) {
    this.Partner = Partner;
  }

  /**

   */

  public java.lang.String getBank() {
    return this.Bank;
  }

  public void setBank(java.lang.String Bank) {
    this.Bank = Bank;
  }

  /**

   */

  public java.lang.String getBankAcc() {
    return this.BankAcc;
  }

  public void setBankAcc(java.lang.String BankAcc) {
    this.BankAcc = BankAcc;
  }

  /**

   */

  public java.lang.String getCorrAcc() {
    return this.CorrAcc;
  }

  public void setCorrAcc(java.lang.String CorrAcc) {
    this.CorrAcc = CorrAcc;
  }

  /**

   */

  public java.lang.String getBankAddr() {
    return this.BankAddr;
  }

  public void setBankAddr(java.lang.String BankAddr) {
    this.BankAddr = BankAddr;
  }

  /**

   */

  public java.lang.String getBankCity() {
    return this.BankCity;
  }

  public void setBankCity(java.lang.String BankCity) {
    this.BankCity = BankCity;
  }

  /**

   */

  public java.lang.String getBankIdent() {
    return this.BankIdent;
  }

  public void setBankIdent(java.lang.String BankIdent) {
    this.BankIdent = BankIdent;
  }

  /**

   */

  public boolean getIsDefault() {
    return this.IsDefault;
  }

  public void setIsDefault(boolean IsDefault) {
    this.IsDefault = IsDefault;
  }

  /**

   */

  public java.lang.String getBankBranch() {
    return this.BankBranch;
  }

  public void setBankBranch(java.lang.String BankBranch) {
    this.BankBranch = BankBranch;
  }

  /**

   */

  public java.lang.String getUnid() {
    return this.Unid;
  }

  public void setUnid(java.lang.String Unid) {
    this.Unid = Unid;
  }

  public boolean equals(Object other) {
    if ((this == other)) return true;
    if ((other == null)) return false;
    if (!(other instanceof BankRequisId)) return false;
    BankRequisId castOther = (BankRequisId) other;

    return (this.getId() == castOther.getId()) || (this.getId() == null ? false : (castOther.getId() == null ? false : this.getId().equals(castOther.getId())))
        && (this.getPartner() == castOther.getPartner()) || (this.getPartner() == null ? false : (castOther.getPartner() == null ? false : this.getPartner().equals(castOther.getPartner())))
        && (this.getBank() == castOther.getBank()) || (this.getBank() == null ? false : (castOther.getBank() == null ? false : this.getBank().equals(castOther.getBank())))
        && (this.getBankAcc() == castOther.getBankAcc()) || (this.getBankAcc() == null ? false : (castOther.getBankAcc() == null ? false : this.getBankAcc().equals(castOther.getBankAcc())))
        && (this.getCorrAcc() == castOther.getCorrAcc()) || (this.getCorrAcc() == null ? false : (castOther.getCorrAcc() == null ? false : this.getCorrAcc().equals(castOther.getCorrAcc())))
        && (this.getBankAddr() == castOther.getBankAddr()) || (this.getBankAddr() == null ? false : (castOther.getBankAddr() == null ? false : this.getBankAddr().equals(castOther.getBankAddr())))
        && (this.getBankCity() == castOther.getBankCity()) || (this.getBankCity() == null ? false : (castOther.getBankCity() == null ? false : this.getBankCity().equals(castOther.getBankCity())))
        && (this.getBankIdent() == castOther.getBankIdent()) || (this.getBankIdent() == null ? false : (castOther.getBankIdent() == null ? false : this.getBankIdent().equals(castOther.getBankIdent())))
        //&& (this.getIsDefault()==castOther.getIsDefault()) || (this.getIsDefault()==null ? false : (castOther.getIsDefault()==null ? false : this.getIsDefault().equals(castOther.getIsDefault())))
        && (this.getBankBranch() == castOther.getBankBranch()) || (this.getBankBranch() == null ? false : (castOther.getBankBranch() == null ? false : this.getBankBranch().equals(castOther.getBankBranch())))
        && (this.getUnid() == castOther.getUnid()) || (this.getUnid() == null ? false : (castOther.getUnid() == null ? false : this.getUnid().equals(castOther.getUnid())));
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + this.getId().hashCode();
    result = 37 * result + this.getPartner().hashCode();
    result = 37 * result + this.getBank().hashCode();
    result = 37 * result + this.getBankAcc().hashCode();
    result = 37 * result + this.getCorrAcc().hashCode();
    result = 37 * result + this.getBankAddr().hashCode();
    result = 37 * result + this.getBankCity().hashCode();
    result = 37 * result + this.getBankIdent().hashCode();
    //result = 37 * result + this.getIsDefault().hashCode();
    result = 37 * result + this.getBankBranch().hashCode();
    result = 37 * result + this.getUnid().hashCode();
    return result;
  }


}