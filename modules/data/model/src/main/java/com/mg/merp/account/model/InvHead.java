/*
 * InvHead.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InvHead.java,v 1.8 2006/11/02 15:40:06 safonov Exp $
 */
public class InvHead extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields
  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.account.model.Okof Okof;

  private com.mg.merp.account.model.Inventory Parent;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.account.model.InvLocation InvLocation;

  private com.mg.merp.reference.model.Contractor Contractor;

  private java.lang.String GroupNum;

  private java.lang.String CardNum;

  private java.lang.String ObjNum;

  private java.lang.String Manufacturer;

  private java.lang.String Model;

  private java.lang.String SerialNum;

  private java.lang.String PasspNum;

  private java.lang.String InOperDocNum;

  private java.util.Date InOperDate;

  private java.lang.String OutOperDocNum;

  private java.util.Date OutOperDate;

  private boolean IsComplex;

  private boolean IsCommon;

  private java.lang.String Comment;

  private java.lang.String InvName;

  private java.lang.String IncomeDocNum;

  private java.util.Date IncomeDate;

  private java.util.Date ProductDate;

  private java.util.Set SetOfInventory;

  private java.util.Set setOfAccInvProduction;

  // Constructors

  /**
   * default constructor
   */
  public InvHead() {
  }

  /**
   * constructor with id
   */
  public InvHead(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.Catalog")
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  public com.mg.merp.account.model.Okof getOkof() {
    return this.Okof;
  }

  public void setOkof(com.mg.merp.account.model.Okof Okof) {
    this.Okof = Okof;
  }

  /**
   *
   */

  public com.mg.merp.account.model.Inventory getParent() {
    return this.Parent;
  }

  public void setParent(com.mg.merp.account.model.Inventory Inventory) {
    this.Parent = Inventory;
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


  public com.mg.merp.account.model.InvLocation getInvLocation() {
    return this.InvLocation;
  }

  public void setInvLocation(com.mg.merp.account.model.InvLocation Invlocation) {
    this.InvLocation = Invlocation;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.Contractor")
  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.GroupNum")
  public java.lang.String getGroupNum() {
    return this.GroupNum;
  }

  public void setGroupNum(java.lang.String Groupnum) {
    this.GroupNum = Groupnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.CardNum")
  public java.lang.String getCardNum() {
    return this.CardNum;
  }

  public void setCardNum(java.lang.String Cardnum) {
    this.CardNum = Cardnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.ObjNum")
  public java.lang.String getObjNum() {
    return this.ObjNum;
  }

  public void setObjNum(java.lang.String Objnum) {
    this.ObjNum = Objnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.Manufacturer")
  public java.lang.String getManufacturer() {
    return this.Manufacturer;
  }

  public void setManufacturer(java.lang.String Manufacturer) {
    this.Manufacturer = Manufacturer;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.Model")
  public java.lang.String getModel() {
    return this.Model;
  }

  public void setModel(java.lang.String Model) {
    this.Model = Model;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.SerialNum")
  public java.lang.String getSerialNum() {
    return this.SerialNum;
  }

  public void setSerialNum(java.lang.String Serialnum) {
    this.SerialNum = Serialnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.PasspNum")
  public java.lang.String getPasspNum() {
    return this.PasspNum;
  }

  public void setPasspNum(java.lang.String Passpnum) {
    this.PasspNum = Passpnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.InOperDocNum")
  public java.lang.String getInOperDocNum() {
    return this.InOperDocNum;
  }

  public void setInOperDocNum(java.lang.String Inoperdocnum) {
    this.InOperDocNum = Inoperdocnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.InOperDate")
  public java.util.Date getInOperDate() {
    return this.InOperDate;
  }

  public void setInOperDate(java.util.Date Inoperdate) {
    this.InOperDate = Inoperdate;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.OutOperDocNum")
  public java.lang.String getOutOperDocNum() {
    return this.OutOperDocNum;
  }

  public void setOutOperDocNum(java.lang.String Outoperdocnum) {
    this.OutOperDocNum = Outoperdocnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.OutOperDate")
  public java.util.Date getOutOperDate() {
    return this.OutOperDate;
  }

  public void setOutOperDate(java.util.Date Outoperdate) {
    this.OutOperDate = Outoperdate;
  }

  /**
   *
   */

  public boolean getIsComplex() {
    return this.IsComplex;
  }

  public void setIsComplex(boolean IsComplex) {
    this.IsComplex = IsComplex;
  }

  /**
   *
   */

  public boolean getIsCommon() {
    return this.IsCommon;
  }

  public void setIsCommon(boolean IsCommon) {
    this.IsCommon = IsCommon;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.InvName")
  public java.lang.String getInvName() {
    return this.InvName;
  }

  public void setInvName(java.lang.String Invname) {
    this.InvName = Invname;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.IncomeDocNum")
  public java.lang.String getIncomeDocNum() {
    return this.IncomeDocNum;
  }

  public void setIncomeDocNum(java.lang.String Incomedocnum) {
    this.IncomeDocNum = Incomedocnum;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.IncomeDate")
  public java.util.Date getIncomeDate() {
    return this.IncomeDate;
  }

  public void setIncomeDate(java.util.Date Incomedate) {
    this.IncomeDate = Incomedate;
  }

  /**
   *
   */
  @DataItemName("Account.InvHead.ProductDate")
  public java.util.Date getProductDate() {
    return this.ProductDate;
  }

  public void setProductDate(java.util.Date Productdate) {
    this.ProductDate = Productdate;
  }

  /**
   *
   */
  public java.util.Set getSetOfInventory() {
    return this.SetOfInventory;
  }

  public void setSetOfInventory(java.util.Set SetOfInventory) {
    this.SetOfInventory = SetOfInventory;
  }

  /**
   *
   */
  public java.util.Set getSetOfAccInvProduction() {
    return this.setOfAccInvProduction;
  }

  public void setSetOfAccInvProduction(java.util.Set SetOfAccInvproduction) {
    this.setOfAccInvProduction = SetOfAccInvproduction;
  }

}