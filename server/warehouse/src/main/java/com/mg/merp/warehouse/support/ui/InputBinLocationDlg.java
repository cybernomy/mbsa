/*
 * InputBinLocationDlg.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.Warehouse;

import java.math.BigDecimal;

/**
 * Контроллер диалога ввода секции хранения
 *
 * @author Artem V. Sharapov
 * @version $Id: InputBinLocationDlg.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class InputBinLocationDlg extends DefaultWizardDialog {

  @DataItemName("Reference.Code") //$NON-NLS-1$
  private String catalogCode;

  @DataItemName("Reference.Name") //$NON-NLS-1$
  private String catalogName;

  @DataItemName("Document.DocSpec.Quantity1") //$NON-NLS-1$
  private BigDecimal quantity;

  private BinLocation binLocation;
  private Warehouse warehouse;


  public InputBinLocationDlg() {
  }

  /**
   * Запустить диалог на показ
   *
   * @param quantity    - кол-во
   * @param catalogCode - код позиции каталога
   * @param catalogName - наименование позиции каталога
   * @param warehouse   - склад
   */
  public void execute(BigDecimal quantity, String catalogCode, String catalogName, Warehouse warehouse) {
    this.quantity = quantity;
    this.catalogCode = catalogCode;
    this.catalogName = catalogName;
    this.warehouse = warehouse;
    execute();
  }

  /**
   * @return the catalogCode
   */
  public String getCatalogCode() {
    return this.catalogCode;
  }

  /**
   * @param catalogCode the catalogCode to set
   */
  public void setCatalogCode(String catalogCode) {
    this.catalogCode = catalogCode;
  }

  /**
   * @return the catalogName
   */
  public String getCatalogName() {
    return this.catalogName;
  }

  /**
   * @param catalogName the catalogName to set
   */
  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }

  /**
   * @return the quantity
   */
  public BigDecimal getQuantity() {
    return this.quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the binLocation
   */
  public BinLocation getBinLocation() {
    return this.binLocation;
  }

  /**
   * @param binLocation the binLocation to set
   */
  public void setBinLocation(BinLocation binLocation) {
    this.binLocation = binLocation;
  }

  /**
   * @return the warehouse
   */
  public Warehouse getWarehouse() {
    return this.warehouse;
  }

  /**
   * @param warehouse the warehouse to set
   */
  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

}
