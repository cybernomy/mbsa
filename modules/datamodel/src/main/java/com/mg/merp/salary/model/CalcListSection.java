/*
 * CalcListSection.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Разделы расчетного листка"
 *
 * @author Artem V. Sharapov
 * @version $Id: CalcListSection.java,v 1.5 2007/07/09 08:21:56 sharapov Exp $
 */
public class CalcListSection extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.salary.model.CalcList CalcList;

  private com.mg.merp.salary.model.CalcListSectionRef CalcListSectionRef;

  private java.math.BigDecimal TotalSumma;

  private java.util.Set<CalcListFee> CalcListFees;

  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public CalcListSection() {
  }

  /**
   * constructor with id
   */
  public CalcListSection(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  public com.mg.merp.salary.model.CalcList getCalcList() {
    return this.CalcList;
  }

  public void setCalcList(com.mg.merp.salary.model.CalcList SalCalcList) {
    this.CalcList = SalCalcList;
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
  public com.mg.merp.salary.model.CalcListSectionRef getCalcListSectionRef() {
    return this.CalcListSectionRef;
  }

  public void setCalcListSectionRef(
      com.mg.merp.salary.model.CalcListSectionRef SalCalcListSectionRef) {
    this.CalcListSectionRef = SalCalcListSectionRef;
  }

  /**
   *
   */
  @DataItemName("Salary.ListSection.TotalSumma") //$NON-NLS-1$
  public java.math.BigDecimal getTotalSumma() {
    return this.TotalSumma;
  }

  public void setTotalSumma(java.math.BigDecimal TotalSumma) {
    this.TotalSumma = TotalSumma;
  }

  /**
   * @return the calcListFees
   */
  public java.util.Set<CalcListFee> getCalcListFees() {
    return this.CalcListFees;
  }

  /**
   * @param calcListFees the calcListFees to set
   */
  public void setCalcListFees(java.util.Set<CalcListFee> calcListFees) {
    this.CalcListFees = calcListFees;
  }

}