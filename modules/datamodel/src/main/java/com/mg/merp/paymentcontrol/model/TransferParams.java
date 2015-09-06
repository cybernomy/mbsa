/*
 * TransferParams.java
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
package com.mg.merp.paymentcontrol.model;

import com.mg.merp.core.model.Folder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Модель данных для "Внутреннего перемещения средств"
 *
 * @author Artem V. Sharapov
 * @version $Id: TransferParams.java,v 1.1 2007/05/14 05:12:10 sharapov Exp $
 */
public class TransferParams {

  private Folder resourceFolderExpense;
  private PmcResource resourceExpense;
  private Date dateExpense;
  private BigDecimal sumExpense;
  private Liability liabilityModelExpense;

  private Folder resourceFolderIncome;
  private PmcResource resourceIncome;
  private Date dateIncome;
  private BigDecimal sumIncome;
  private Liability liabilityModelIncome;

  public TransferParams(Folder resourceFolderExpense, PmcResource resourceExpense, Date dateExpense, BigDecimal sumExpense, Liability liabilityModelExpense, Folder resourceFolderIncome, PmcResource resourceIncome, Date dateIncome, BigDecimal sumIncome, Liability liabilityModelIncome) {
    this.resourceFolderExpense = resourceFolderExpense;
    this.resourceExpense = resourceExpense;
    this.dateExpense = dateExpense;
    this.sumExpense = sumExpense;
    this.liabilityModelExpense = liabilityModelExpense;
    this.resourceFolderIncome = resourceFolderIncome;
    this.resourceIncome = resourceIncome;
    this.dateIncome = dateIncome;
    this.sumIncome = sumIncome;
    this.liabilityModelIncome = liabilityModelIncome;
  }

  /**
   * @return the dateExpense
   */
  public Date getDateExpense() {
    return dateExpense;
  }

  /**
   * @param dateExpense the dateExpense to set
   */
  public void setDateExpense(Date dateExpense) {
    this.dateExpense = dateExpense;
  }

  /**
   * @return the dateIncome
   */
  public Date getDateIncome() {
    return dateIncome;
  }

  /**
   * @param dateIncome the dateIncome to set
   */
  public void setDateIncome(Date dateIncome) {
    this.dateIncome = dateIncome;
  }

  /**
   * @return the liabilityModelExpense
   */
  public Liability getLiabilityModelExpense() {
    return liabilityModelExpense;
  }

  /**
   * @param liabilityModelExpense the liabilityModelExpense to set
   */
  public void setLiabilityModelExpense(Liability liabilityModelExpense) {
    this.liabilityModelExpense = liabilityModelExpense;
  }

  /**
   * @return the liabilityModelIncome
   */
  public Liability getLiabilityModelIncome() {
    return liabilityModelIncome;
  }

  /**
   * @param liabilityModelIncome the liabilityModelIncome to set
   */
  public void setLiabilityModelIncome(Liability liabilityModelIncome) {
    this.liabilityModelIncome = liabilityModelIncome;
  }

  /**
   * @return the resourceExpense
   */
  public PmcResource getResourceExpense() {
    return resourceExpense;
  }

  /**
   * @param resourceExpense the resourceExpense to set
   */
  public void setResourceExpense(PmcResource resourceExpense) {
    this.resourceExpense = resourceExpense;
  }

  /**
   * @return the resourceFolderExpense
   */
  public Folder getResourceFolderExpense() {
    return resourceFolderExpense;
  }

  /**
   * @param resourceFolderExpense the resourceFolderExpense to set
   */
  public void setResourceFolderExpense(Folder resourceFolderExpense) {
    this.resourceFolderExpense = resourceFolderExpense;
  }

  /**
   * @return the resourceFolderIncome
   */
  public Folder getResourceFolderIncome() {
    return resourceFolderIncome;
  }

  /**
   * @param resourceFolderIncome the resourceFolderIncome to set
   */
  public void setResourceFolderIncome(Folder resourceFolderIncome) {
    this.resourceFolderIncome = resourceFolderIncome;
  }

  /**
   * @return the resourceIncome
   */
  public PmcResource getResourceIncome() {
    return resourceIncome;
  }

  /**
   * @param resourceIncome the resourceIncome to set
   */
  public void setResourceIncome(PmcResource resourceIncome) {
    this.resourceIncome = resourceIncome;
  }

  /**
   * @return the sumExpense
   */
  public BigDecimal getSumExpense() {
    return sumExpense;
  }

  /**
   * @param sumExpense the sumExpense to set
   */
  public void setSumExpense(BigDecimal sumExpense) {
    this.sumExpense = sumExpense;
  }

  /**
   * @return the sumIncome
   */
  public BigDecimal getSumIncome() {
    return sumIncome;
  }

  /**
   * @param sumIncome the sumIncome to set
   */
  public void setSumIncome(BigDecimal sumIncome) {
    this.sumIncome = sumIncome;
  }

}
