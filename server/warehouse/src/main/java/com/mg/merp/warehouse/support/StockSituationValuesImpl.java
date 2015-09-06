/* StockSituationValuesImpl.java
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
package com.mg.merp.warehouse.support;

import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.model.Warehouse;

import java.math.BigDecimal;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: StockSituationValuesImpl.java,v 1.3 2008/09/09 12:27:55 sharapov Exp $
 */
public class StockSituationValuesImpl implements StockSituationValues {

  private Warehouse warehouse;

  private BigDecimal available1;

  private BigDecimal available2;

  private BigDecimal located1;

  private BigDecimal located2;

  private BigDecimal planningReceipt1;

  private BigDecimal planningReceipt2;

  private BigDecimal planningIssue1;

  private BigDecimal planningIssue2;

  private BigDecimal reserved1;

  private BigDecimal reserved2;


  public StockSituationValuesImpl() {
  }

  public StockSituationValuesImpl(Warehouse warehouse, BigDecimal available1, BigDecimal available2, BigDecimal located1, BigDecimal located2, BigDecimal planningReceipt1, BigDecimal planningReceipt2, BigDecimal planningIssue1, BigDecimal planningIssue2, BigDecimal reserved1, BigDecimal reserved2) {
    this.warehouse = warehouse;
    this.available1 = available1;
    this.available2 = available2;
    this.located1 = located1;
    this.located2 = located2;
    this.planningReceipt1 = planningReceipt1;
    this.planningReceipt2 = planningReceipt2;
    this.planningIssue1 = planningIssue1;
    this.planningIssue2 = planningIssue2;
    this.reserved1 = reserved1;
    this.reserved2 = reserved2;
  }

  public OrgUnit getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getAvailable()
   */
  public BigDecimal getAvailable1() {
    return available1;
  }

  public void setAvailable1(BigDecimal available1) {
    this.available1 = available1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getAvailable2()
   */
  public BigDecimal getAvailable2() {
    return available2;
  }

  public void setAvailable2(BigDecimal available2) {
    this.available2 = available2;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getFact()
   */
  public BigDecimal getLocated1() {
    return located1;
  }

  public void setLocated1(BigDecimal located1) {
    this.located1 = located1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getFact2()
   */
  public BigDecimal getLocated2() {
    return located2;
  }

  public void setLocated2(BigDecimal located2) {
    this.located2 = located2;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getPlanArrival()
   */
  public BigDecimal getPlanningReceipt1() {
    return planningReceipt1;
  }

  public void setPlanningReceipt1(BigDecimal planningReceipt1) {
    this.planningReceipt1 = planningReceipt1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getPlanArrival2()
   */
  public BigDecimal getPlanningReceipt2() {
    return planningReceipt2;
  }

  public void setPlanningReceipt2(BigDecimal planningReceipt2) {
    this.planningReceipt2 = planningReceipt2;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getPlanDisposal()
   */
  public BigDecimal getPlanningIssue1() {
    return planningIssue1;
  }

  public void setPlanningIssue1(BigDecimal planningIssue1) {
    this.planningIssue1 = planningIssue1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getPlanDisposal2()
   */
  public BigDecimal getPlanningIssue2() {
    return planningIssue2;
  }

  public void setPlanningIssue2(BigDecimal planningIssue2) {
    this.planningIssue2 = planningIssue2;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getReserve()
   */
  public BigDecimal getReserved1() {
    return reserved1;
  }

  public void setReserved1(BigDecimal reserved1) {
    this.reserved1 = reserved1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.reference.QuanOnStockValues#getReserve2()
   */
  public BigDecimal getReserved2() {
    return reserved2;
  }

  public void setReserved2(BigDecimal reserved2) {
    this.reserved2 = reserved2;
  }

}
