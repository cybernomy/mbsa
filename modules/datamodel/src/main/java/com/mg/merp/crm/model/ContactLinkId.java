/*
 * ContactLinkId.java
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
 * @version $Id: ContactLinkId.java,v 1.1 2005/06/10 06:52:20 safonov Exp $
 */
public class ContactLinkId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.crm.model.Contact CrmContact;
  private com.mg.merp.crm.model.Relation CrmRelation;


  // Constructors

  /**
   * default constructor
   */
  public ContactLinkId() {
  }


  // Property accessors

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

  public com.mg.merp.crm.model.Contact getCrmContact() {
    return this.CrmContact;
  }

  public void setCrmContact(com.mg.merp.crm.model.Contact CrmContact) {
    this.CrmContact = CrmContact;
  }

  /**

   */

  public com.mg.merp.crm.model.Relation getCrmRelation() {
    return this.CrmRelation;
  }

  public void setCrmRelation(com.mg.merp.crm.model.Relation CrmRelation) {
    this.CrmRelation = CrmRelation;
  }

  public boolean equals(Object other) {
    if ((this == other)) return true;
    if ((other == null)) return false;
    if (!(other instanceof ContactLinkId)) return false;
    ContactLinkId castOther = (ContactLinkId) other;

    return (this.getSysClient() == castOther.getSysClient()) || (this.getSysClient() == null ? false : (castOther.getSysClient() == null ? false : this.getSysClient().equals(castOther.getSysClient())))
        && (this.getCrmContact() == castOther.getCrmContact()) || (this.getCrmContact() == null ? false : (castOther.getCrmContact() == null ? false : this.getCrmContact().equals(castOther.getCrmContact())))
        && (this.getCrmRelation() == castOther.getCrmRelation()) || (this.getCrmRelation() == null ? false : (castOther.getCrmRelation() == null ? false : this.getCrmRelation().equals(castOther.getCrmRelation())));
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + this.getSysClient().hashCode();
    result = 37 * result + this.getCrmContact().hashCode();
    result = 37 * result + this.getCrmRelation().hashCode();
    return result;
  }


}