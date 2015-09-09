/*
 * BomRouteResource.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: BomRouteResource.java,v 1.5 2007/07/30 10:25:11 safonov Exp $
 */
public class BomRouteResource extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.mfreference.model.CostDetail standartCostDetail;

  private com.mg.merp.mfreference.model.BomRoute BomRoute;

  private java.lang.Short ResourceType;

  private java.lang.Integer TimeSequence;

  private java.util.Date EffOnDate;

  private java.util.Date EffOffDate;

  private java.lang.String Comment;

  // Constructors

  /**
   * default constructor
   */
  public BomRouteResource() {
  }

  /**
   * constructor with id
   */
  public BomRouteResource(java.lang.Integer Id) {
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

  @DataItemName("MfReference.BomRouteResource.ResourceGroup")
  public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup() {
    return this.ResourceGroup;
  }

  public void setResourceGroup(
      com.mg.merp.mfreference.model.ResourceGroup ResourceGroup) {
    this.ResourceGroup = ResourceGroup;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  public com.mg.merp.mfreference.model.CostDetail getStandartCostDetail() {
    return this.standartCostDetail;
  }

  public void setStandartCostDetail(
      com.mg.merp.mfreference.model.CostDetail CostDetail) {
    this.standartCostDetail = CostDetail;
  }

  /**
   *
   */

  public com.mg.merp.mfreference.model.BomRoute getBomRoute() {
    return this.BomRoute;
  }

  public void setBomRoute(com.mg.merp.mfreference.model.BomRoute BomRoute) {
    this.BomRoute = BomRoute;
  }

  /**
   *
   */

  public java.lang.Short getResourceType() {
    return this.ResourceType;
  }

  public void setResourceType(java.lang.Short ResourceType) {
    this.ResourceType = ResourceType;
  }

  /**
   *
   */

  @DataItemName("MfReference.BomRouteResource.TimeSequence")
  public java.lang.Integer getTimeSequence() {
    return this.TimeSequence;
  }

  public void setTimeSequence(java.lang.Integer TimeSequence) {
    this.TimeSequence = TimeSequence;
  }

  /**
   *
   */
  @DataItemName("MfReference.BomRouteResource.EffOnDate")
  public java.util.Date getEffOnDate() {
    return this.EffOnDate;
  }

  public void setEffOnDate(java.util.Date EffOnDate) {
    this.EffOnDate = EffOnDate;
  }

  /**
   *
   */
  @DataItemName("MfReference.BomRouteResource.EffOffDate")
  public java.util.Date getEffOffDate() {
    return this.EffOffDate;
  }

  public void setEffOffDate(java.util.Date EffOffDate) {
    this.EffOffDate = EffOffDate;
  }

  /**
   *
   */

  @DataItemName("MfReference.BomRouteResource.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }
}