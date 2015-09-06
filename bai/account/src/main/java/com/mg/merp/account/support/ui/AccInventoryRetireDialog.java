/*
 * AccInventoryRetireDialog.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.support.Messages;
import com.mg.merp.document.model.DocType;

import java.util.Date;

/**
 * Контроллер ввода данных для проведения операции списания инвентарных карточек
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccInventoryRetireDialog.java,v 1.1 2008/04/29 05:29:04 alikaev Exp $
 */
public class AccInventoryRetireDialog extends DefaultWizardDialog {

  /**
   * заголовок дилогового окна
   */
  private String dialogTitle;

  /**
   * дата учета
   */
  private Date revalDate;

  /**
   * тип документа
   */
  private DocType docType;

  /**
   * номер документа
   */
  private String docNumber;

  /**
   * дата документа
   */
  private Date docDate;

  /**
   * тип документа основания
   */
  private DocType baseDocType;

  /**
   * номер докумнта основания
   */
  private String baseDocNumber;

  /**
   * дата документа основания
   */
  private Date baseDocDate;

  /**
   * счет
   */
  private AccPlan AccPlan;

  /**
   * аналитика 1-го уровня
   */
  @DataItemName("Account.Inventory.Anl1")
  private AnlPlan anl1;

  /**
   * аналитика 2-го уровня
   */
  @DataItemName("Account.Inventory.Anl2")
  private AnlPlan anl2;
  /**
   * аналитика 3-го уровня
   */
  @DataItemName("Account.Inventory.Anl3")
  private AnlPlan anl3;

  /**
   * аналитика 4-го уровня
   */
  @DataItemName("Account.Inventory.Anl4")
  private AnlPlan anl4;

  /**
   * аналитика 5-го уровня
   */
  @DataItemName("Account.Inventory.Anl5")
  private AnlPlan anl5;

  public AccInventoryRetireDialog() {
  }

  public Date getRevalDate() {
    return revalDate;
  }

  public void setRevalDate(Date revalDate) {
    this.revalDate = revalDate;
  }

  public DocType getDocType() {
    return docType;
  }

  public void setDocType(DocType docType) {
    this.docType = docType;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public Date getDocDate() {
    return docDate;
  }

  public void setDocDate(Date docDate) {
    this.docDate = docDate;
  }

  public DocType getBaseDocType() {
    return baseDocType;
  }

  public String getBaseDocNumber() {
    return baseDocNumber;
  }

  public Date getBaseDocDate() {
    return baseDocDate;
  }

  public AccPlan getAccPlan() {
    return AccPlan;
  }

  public void setAccPlan(AccPlan accPlan) {
    AccPlan = accPlan;
  }

  public AnlPlan getAnl1() {
    return anl1;
  }

  public void setAnl1(AnlPlan anl1) {
    this.anl1 = anl1;
  }

  public AnlPlan getAnl2() {
    return anl2;
  }

  public void setAnl2(AnlPlan anl2) {
    this.anl2 = anl2;
  }

  public AnlPlan getAnl3() {
    return anl3;
  }

  public void setAnl3(AnlPlan anl3) {
    this.anl3 = anl3;
  }

  public AnlPlan getAnl4() {
    return anl4;
  }

  public void setAnl4(AnlPlan anl4) {
    this.anl4 = anl4;
  }

  public AnlPlan getAnl5() {
    return anl5;
  }

  public void setAnl5(AnlPlan anl5) {
    this.anl5 = anl5;
  }

  public void setDialogTitle(String dialogTitle) {
    this.dialogTitle = dialogTitle;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    if (this.revalDate == null || this.AccPlan == null || this.docType == null || StringUtils.stringNullOrEmpty(this.docNumber) || this.docDate == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_INVENTORY_RETIRE_DLG_CHECK));
    else
      super.onActionOk(event);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    view.setTitle(this.dialogTitle);
    super.doOnRun();
  }

}
