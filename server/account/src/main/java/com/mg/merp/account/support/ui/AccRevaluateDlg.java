/*
 * AccRevaluateDlg.java
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
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.support.Messages;
import com.mg.merp.document.model.DocType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер ввода параметров для операции переоценка/дооценка с инвентарными карточками
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccRevaluateDlg.java,v 1.1 2008/04/29 05:29:04 alikaev Exp $
 */
public class AccRevaluateDlg extends DefaultWizardDialog {

  private String accRevaluateDlgTitle;
  private Date revalDate;
  private Kind kind = Kind.FACTOR;
  private BigDecimal value = BigDecimal.ZERO;
  private DocType docType;
  private String docNumber;
  private Date docDate;
  private DocType baseDocType;
  private String baseDocNumber;
  private Date baseDocDate;
  private AccPlan AccPlan;
  @DataItemName("Account.Inventory.Anl1")
  private AnlPlan anl1;
  @DataItemName("Account.Inventory.Anl2")
  private AnlPlan anl2;
  @DataItemName("Account.Inventory.Anl3")
  private AnlPlan anl3;
  @DataItemName("Account.Inventory.Anl4")
  private AnlPlan anl4;
  @DataItemName("Account.Inventory.Anl5")
  private AnlPlan anl5;

  public AccRevaluateDlg() {
  }

  /**
   * Дата учета
   *
   * @return revalDate
   */
  public Date getRevalDate() {
    return revalDate;
  }

  /**
   * Значение по признаку
   *
   * @return value
   */
  public BigDecimal getValue() {
    return value;
  }

  /**
   * Тип документа
   *
   * @return docType
   */
  public DocType getDocType() {
    return docType;
  }

  /**
   * Номер документа
   *
   * @return docNumber
   */
  public String getDocNumber() {
    return docNumber;
  }

  /**
   * Дата документа
   *
   * @return docDate
   */
  public Date getDocDate() {
    return docDate;
  }

  /**
   * Тип документа-основания
   *
   * @return baseDocType
   */
  public DocType getBaseDocType() {
    return baseDocType;
  }

  /**
   * Номер документа-основания
   *
   * @return baseDocNumber
   */
  public String getBaseDocNumber() {
    return baseDocNumber;
  }

  /**
   * Дата документа-основания
   *
   * @return baseDocDate
   */
  public Date getBaseDocDate() {
    return baseDocDate;
  }

  /**
   * Счет
   *
   * @return AccPlan
   */
  public AccPlan getAccPlan() {
    return AccPlan;
  }

  /**
   * Аналитика 1-го уровня
   *
   * @return anl1
   */
  public AnlPlan getAnl1() {
    return anl1;
  }

  /**
   * Аналитика 2-го уровня
   *
   * @return anl2
   */
  public AnlPlan getAnl2() {
    return anl2;
  }

  /**
   * Аналитика 3-го уровня
   *
   * @return anl3
   */
  public AnlPlan getAnl3() {
    return anl3;
  }

  /**
   * Аналитика 4-го уровня
   *
   * @return anl4
   */
  public AnlPlan getAnl4() {
    return anl4;
  }

  /**
   * Аналитика 5-го уровня
   *
   * @return anl5
   */
  public AnlPlan getAnl5() {
    return anl5;
  }

  /**
   * Признак: 0 - фактор, 1 - до суммы, 2 - на сумму
   *
   * @return kind
   */
  public Kind getKind() {
    return kind;
  }

  /**
   * @param accRevaluateDlgTitle задаваемое accRevaluateDlgTitle
   */
  public void setAccRevaluateDlgTitle(String accRevaluateDlgTitle) {
    this.accRevaluateDlgTitle = accRevaluateDlgTitle;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    setTitle(this.accRevaluateDlgTitle);
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    if (this.revalDate == null || this.AccPlan == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_REVALUTE_NOT_CHOOSE_FIELDS));
    else
      super.onActionOk(event);
  }

  private enum Kind {
    /**
     * Коэффициент
     */
    @EnumConstantText("resource://com.mg.merp.account.resources.dataitemlabels#AccRevaluateDlg.Kind.Factor")
    FACTOR,

    /**
     * До суммы
     */
    @EnumConstantText("resource://com.mg.merp.account.resources.dataitemlabels#AccRevaluateDlg.Kind.Amount")
    AMOUNT,

    /**
     * На сумму
     */
    @EnumConstantText("resource://com.mg.merp.account.resources.dataitemlabels#AccRevaluateDlg.Kind.Delta")
    DELTA
  }

}
