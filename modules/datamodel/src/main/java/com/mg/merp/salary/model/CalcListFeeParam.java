/*
 * CalcListFeeParam.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: CalcListFeeParam.java,v 1.4 2006/10/23 08:36:44 leonova Exp $
 */
public class CalcListFeeParam extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.salary.model.CalcListFee CalcListFee;
  private com.mg.merp.salary.model.FeeRefParam FeeRefParam;
  private java.lang.String ParamValue;


  // Constructors

  /**
   * default constructor
   */
  public CalcListFeeParam() {
  }

  /**
   * constructor with id
   */
  public CalcListFeeParam(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
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

  public com.mg.merp.salary.model.CalcListFee getCalcListFee() {
    return this.CalcListFee;
  }

  public void setCalcListFee(com.mg.merp.salary.model.CalcListFee SalCalcListFee) {
    this.CalcListFee = SalCalcListFee;
  }

  /**

   */

  public com.mg.merp.salary.model.FeeRefParam getFeeRefParam() {
    return this.FeeRefParam;
  }

  public void setFeeRefParam(com.mg.merp.salary.model.FeeRefParam SalFeeRefParam) {
    this.FeeRefParam = SalFeeRefParam;
  }

  /**

   */
  @DataItemName("Salary.CalcListFeeParam.ParamValue")
  public java.lang.String getParamValue() {
    return this.ParamValue;
  }

  public void setParamValue(java.lang.String ParamValue) {
    this.ParamValue = ParamValue;
  }


}