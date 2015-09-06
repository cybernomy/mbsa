/*
 * ContractorCardRest.java
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
package com.mg.merp.settlement.support.ui;

import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;

import java.math.BigDecimal;

/**
 * Контроллер формы условий отбора "Карточек расчетов с партнерами"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractorCardRest.java,v 1.1 2007/03/28 11:07:55 sharapov Exp $
 */
public class ContractorCardRest extends DefaultHierarhyRestrictionForm {

  // Fields

  //@DataItemName("Reference.Code")
  private String code = ""; //$NON-NLS-1$

  //@DataItemName("Reference.Name")
  private String name = ""; //$NON-NLS-1$

  private BigDecimal totalIncomeFrom;
  private BigDecimal totalIncomeTo;

  private BigDecimal totalExpensesFrom;
  private BigDecimal totalExpensesTo;

  private BigDecimal debitorInDebSumFrom;
  private BigDecimal debitorInDebSumTo;

  private BigDecimal creditorInDebSumFrom;
  private BigDecimal creditorInDebSumTo;

  private BigDecimal debitorInDebLimitFrom;
  private BigDecimal debitorInDebLimitTo;

  private BigDecimal creditorInDebLimitFrom;
  private BigDecimal creditorInDebLimitTo;

  private BigDecimal planIncomeFrom;
  private BigDecimal planIncomeTo;

  private BigDecimal planExpensesFrom;
  private BigDecimal planExpensesTo;


  // Methods

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    code = ""; //$NON-NLS-1$
    name = ""; //$NON-NLS-1$
    totalIncomeFrom = null;
    totalIncomeTo = null;
    totalExpensesFrom = null;
    totalExpensesTo = null;
    debitorInDebSumFrom = null;
    debitorInDebSumTo = null;
    creditorInDebSumFrom = null;
    creditorInDebSumTo = null;
    debitorInDebLimitFrom = null;
    debitorInDebLimitTo = null;
    creditorInDebLimitFrom = null;
    creditorInDebLimitTo = null;
    planIncomeFrom = null;
    planIncomeTo = null;
    planExpensesFrom = null;
    planExpensesTo = null;
  }

  // Property accessors

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the totalIncomeFrom
   */
  public BigDecimal getTotalIncomeFrom() {
    return totalIncomeFrom;
  }

  /**
   * @param totalIncomeFrom the totalIncomeFrom to set
   */
  public void setTotalIncomeFrom(BigDecimal totalIncomeFrom) {
    this.totalIncomeFrom = totalIncomeFrom;
  }

  /**
   * @return the totalIncomeTo
   */
  public BigDecimal getTotalIncomeTo() {
    return totalIncomeTo;
  }

  /**
   * @param totalIncomeTo the totalIncomeTo to set
   */
  public void setTotalIncomeTo(BigDecimal totalIncomeTo) {
    this.totalIncomeTo = totalIncomeTo;
  }

  /**
   * @return the totalExpensesFrom
   */
  public BigDecimal getTotalExpensesFrom() {
    return totalExpensesFrom;
  }

  /**
   * @param totalExpensesFrom the totalExpensesFrom to set
   */
  public void setTotalExpensesFrom(BigDecimal totalExpensesFrom) {
    this.totalExpensesFrom = totalExpensesFrom;
  }

  /**
   * @return the totalExpensesTo
   */
  public BigDecimal getTotalExpensesTo() {
    return totalExpensesTo;
  }

  /**
   * @param totalExpensesTo the totalExpensesTo to set
   */
  public void setTotalExpensesTo(BigDecimal totalExpensesTo) {
    this.totalExpensesTo = totalExpensesTo;
  }

  /**
   * @return the debitorInDebSumFrom
   */
  public BigDecimal getDebitorInDebSumFrom() {
    return debitorInDebSumFrom;
  }

  /**
   * @param debitorInDebSumFrom the debitorInDebSumFrom to set
   */
  public void setDebitorInDebSumFrom(BigDecimal debitorInDebSumFrom) {
    this.debitorInDebSumFrom = debitorInDebSumFrom;
  }

  /**
   * @return the debitorInDebSumTo
   */
  public BigDecimal getDebitorInDebSumTo() {
    return debitorInDebSumTo;
  }

  /**
   * @param debitorInDebSumTo the debitorInDebSumTo to set
   */
  public void setDebitorInDebSumTo(BigDecimal debitorInDebSumTo) {
    this.debitorInDebSumTo = debitorInDebSumTo;
  }

  /**
   * @return the creditorInDebSumFrom
   */
  public BigDecimal getCreditorInDebSumFrom() {
    return creditorInDebSumFrom;
  }

  /**
   * @param creditorInDebSumFrom the creditorInDebSumFrom to set
   */
  public void setCreditorInDebSumFrom(BigDecimal creditorInDebSumFrom) {
    this.creditorInDebSumFrom = creditorInDebSumFrom;
  }

  /**
   * @return the creditorInDebSumTo
   */
  public BigDecimal getCreditorInDebSumTo() {
    return creditorInDebSumTo;
  }

  /**
   * @param creditorInDebSumTo the creditorInDebSumTo to set
   */
  public void setCreditorInDebSumTo(BigDecimal creditorInDebSumTo) {
    this.creditorInDebSumTo = creditorInDebSumTo;
  }

  /**
   * @return the debitorInDebLimitFrom
   */
  public BigDecimal getDebitorInDebLimitFrom() {
    return debitorInDebLimitFrom;
  }

  /**
   * @param debitorInDebLimitFrom the debitorInDebLimitFrom to set
   */
  public void setDebitorInDebLimitFrom(BigDecimal debitorInDebLimitFrom) {
    this.debitorInDebLimitFrom = debitorInDebLimitFrom;
  }

  /**
   * @return the debitorInDebLimitTo
   */
  public BigDecimal getDebitorInDebLimitTo() {
    return debitorInDebLimitTo;
  }

  /**
   * @param debitorInDebLimitTo the debitorInDebLimitTo to set
   */
  public void setDebitorInDebLimitTo(BigDecimal debitorInDebLimitTo) {
    this.debitorInDebLimitTo = debitorInDebLimitTo;
  }

  /**
   * @return the creditorInDebLimitFrom
   */
  public BigDecimal getCreditorInDebLimitFrom() {
    return creditorInDebLimitFrom;
  }

  /**
   * @param creditorInDebLimitFrom the creditorInDebLimitFrom to set
   */
  public void setCreditorInDebLimitFrom(BigDecimal creditorInDebLimitFrom) {
    this.creditorInDebLimitFrom = creditorInDebLimitFrom;
  }

  /**
   * @return the creditorInDebLimitTo
   */
  public BigDecimal getCreditorInDebLimitTo() {
    return creditorInDebLimitTo;
  }

  /**
   * @param creditorInDebLimitTo the creditorInDebLimitTo to set
   */
  public void setCreditorInDebLimitTo(BigDecimal creditorInDebLimitTo) {
    this.creditorInDebLimitTo = creditorInDebLimitTo;
  }

  /**
   * @return the planIncomeFrom
   */
  public BigDecimal getPlanIncomeFrom() {
    return planIncomeFrom;
  }

  /**
   * @param planIncomeFrom the planIncomeFrom to set
   */
  public void setPlanIncomeFrom(BigDecimal planIncomeFrom) {
    this.planIncomeFrom = planIncomeFrom;
  }

  /**
   * @return the planIncomeTo
   */
  public BigDecimal getPlanIncomeTo() {
    return planIncomeTo;
  }

  /**
   * @param planIncomeTo the planIncomeTo to set
   */
  public void setPlanIncomeTo(BigDecimal planIncomeTo) {
    this.planIncomeTo = planIncomeTo;
  }

  /**
   * @return the planExpensesFrom
   */
  public BigDecimal getPlanExpensesFrom() {
    return planExpensesFrom;
  }

  /**
   * @param planExpensesFrom the planExpensesFrom to set
   */
  public void setPlanExpensesFrom(BigDecimal planExpensesFrom) {
    this.planExpensesFrom = planExpensesFrom;
  }

  /**
   * @return the planExpensesTo
   */
  public BigDecimal getPlanExpensesTo() {
    return planExpensesTo;
  }

  /**
   * @param planExpensesTo the planExpensesTo to set
   */
  public void setPlanExpensesTo(BigDecimal planExpensesTo) {
    this.planExpensesTo = planExpensesTo;
  }

}
