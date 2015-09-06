/*
 * OrgUnit.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: OrgUnit.java,v 1.5 2006/11/02 16:20:44 safonov Exp $
 */
@DataItemName("Reference.OrgUnit")
public class OrgUnit extends com.mg.merp.reference.model.Contractor implements
    java.io.Serializable {

  // Fields

  // private java.lang.Integer Id;
//	private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;

  private com.mg.merp.reference.model.Contractor Partner;

  private OrgUnitType OrgUnitKind;

  private java.lang.Boolean IsOffice;

  private java.lang.Boolean IsStore;

  private java.lang.String FolderTag;

  // Constructors

  /**
   * default constructor
   */
  public OrgUnit() {
  }

  // Property accessors
  /**
   *
   */
//	@DataItemName("Reference.OrgUnit.ResourceGroup")
//	public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup() {
//		return this.ResourceGroup;
//	}
//
//	public void setResourceGroup(
//			com.mg.merp.mfreference.model.ResourceGroup ResourceGroup) {
//		this.ResourceGroup = ResourceGroup;
//	}

  /**
   *
   */
  @DataItemName("Reference.OrgUnit.Partner")
  public com.mg.merp.reference.model.Contractor getPartner() {
    return this.Partner;
  }

  public void setPartner(com.mg.merp.reference.model.Contractor Partner) {
    this.Partner = Partner;
  }

  /**
   *
   */

  public OrgUnitType getOrgUnitKind() {
    return this.OrgUnitKind;
  }

  public void setOrgUnitKind(OrgUnitType Kind) {
    this.OrgUnitKind = Kind;
  }

  /**
   *
   */
  @DataItemName("Reference.OrgUnit.IsOffice")
  public java.lang.Boolean getIsOffice() {
    return this.IsOffice;
  }

  public void setIsOffice(java.lang.Boolean IsOffice) {
    this.IsOffice = IsOffice;
  }

  /**
   *
   */
  @DataItemName("Reference.OrgUnit.IsStore")
  public java.lang.Boolean getIsStore() {
    return this.IsStore;
  }

  public void setIsStore(java.lang.Boolean IsStore) {
    this.IsStore = IsStore;
  }

  /**
   *
   */
  @DataItemName("Reference.FolderTag")
  public java.lang.String getFolderTag() {
    return this.FolderTag;
  }

  public void setFolderTag(java.lang.String FolderTag) {
    this.FolderTag = FolderTag;
  }

}