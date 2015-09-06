/*
 * InputQuantityDlg.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ui.widget.Dialog;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.reference.model.Currency;

import java.math.BigDecimal;

/**
 * Контроллер диалога ввода количеств
 *
 * @author Artem V. Sharapov
 * @version $Id: InputQuantityDlg.java,v 1.3 2008/09/10 07:34:18 sharapov Exp $
 */
public class InputQuantityDlg extends DefaultDialog {

  public static final String WINDOW_NAME = "com.mg.merp.reference.InputQuantityDlg"; //$NON-NLS-1$

  private final static String QUANTITY_2_WIDGET = "quantity2"; //$NON-NLS-1$
  private final static String QUANTITY_2_LABEL_WIDGET = "quantity2Label"; //$NON-NLS-1$
  private final static String MEASURE_2_CODE_WIDGET = "measure2Code"; //$NON-NLS-1$

  private final static String PRICE_WIDGET = "price"; //$NON-NLS-1$
  private final static String PRICE_LABEL_WIDGET = "priceLabel"; //$NON-NLS-1$
  private final static String CURRENCY_WIDGET = "currency"; //$NON-NLS-1$

  /**
   * Высота интерфесного элемента
   */
  private final static int WIDGET_HEIGHT = 20;

  /**
   * Высота формы
   */
  private final static int FORM_HEIGHT = 126;

  /**
   * Ширина формы
   */
  private final static int FORM_WIDTH = 330;
  //private Currency currency = null;
  protected String currency;
  private BigDecimal quantity1;
  private String measure1Code;
  private BigDecimal quantity2;
  private String measure2Code;
  private BigDecimal price;
  private boolean isShowQuantity2Fields = true;
  private boolean isShowPriceFields = true;

  public InputQuantityDlg() {
  }

  /**
   * Запустить диалог
   *
   * @param quantity1             - кол-во в основной ЕИ
   * @param measureCode1          - код основной ЕИ
   * @param quantity2             - кол-во в дополнительной ЕИ
   * @param measureCode2          - код дополнительной ЕИ
   * @param price                 - цена
   * @param currency              - валюта
   * @param isShowQuantity2Fields - признак "показывать поля для кол-ва в дополнительной ЕИ"
   * @param isShowPriceFields     - признак "показывать поля для цены"
   */
  public void execute(BigDecimal quantity1, String measureCode1, BigDecimal quantity2, String measureCode2, BigDecimal price, Currency currency, boolean isShowQuantity2Fields, boolean isShowPriceFields) {
    this.quantity1 = quantity1;
    this.measure1Code = measureCode1;
    this.quantity2 = quantity2;
    this.measure2Code = measureCode2;
    this.price = price;
    if (currency != null)
      this.currency = currency.getCode().trim();
    this.isShowQuantity2Fields = isShowQuantity2Fields;
    this.isShowPriceFields = isShowPriceFields;
    execute();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    adjustForm();
  }

  private void adjustForm() {
    view.getWidget(QUANTITY_2_WIDGET).setVisible(isShowQuantity2Fields);
    view.getWidget(QUANTITY_2_LABEL_WIDGET).setVisible(isShowQuantity2Fields);
    view.getWidget(MEASURE_2_CODE_WIDGET).setVisible(isShowQuantity2Fields);

    view.getWidget(PRICE_WIDGET).setVisible(isShowPriceFields);
    view.getWidget(PRICE_LABEL_WIDGET).setVisible(isShowPriceFields);
    view.getWidget(CURRENCY_WIDGET).setVisible(isShowPriceFields);

    int formHeight = FORM_HEIGHT;
    if (!isShowQuantity2Fields)
      formHeight = formHeight - WIDGET_HEIGHT;
    if (!isShowPriceFields)
      formHeight = formHeight - WIDGET_HEIGHT;

    ((Dialog) view.getWindow()).setSize(FORM_WIDTH, formHeight);
  }

  /**
   * @return the quantity1
   */
  public BigDecimal getQuantity1() {
    return this.quantity1;
  }

  /**
   * @param quantity1 the quantity1 to set
   */
  public void setQuantity1(BigDecimal quantity1) {
    this.quantity1 = quantity1;
  }

  /**
   * @return the quantity2
   */
  public BigDecimal getQuantity2() {
    return this.quantity2;
  }

  /**
   * @param quantity2 the quantity2 to set
   */
  public void setQuantity2(BigDecimal quantity2) {
    this.quantity2 = quantity2;
  }

  /**
   * @return the measure1Code
   */
  public String getMeasure1Code() {
    return this.measure1Code;
  }

  /**
   * @param measure1Code the measure1Code to set
   */
  public void setMeasure1Code(String measureCode1) {
    this.measure1Code = measureCode1;
  }

  /**
   * @return the measure2Code
   */
  public String getMeasure2Code() {
    return this.measure2Code;
  }

  /**
   * @param measure2Code the measure2Code to set
   */
  public void setMeasure2Code(String measureCode2) {
    this.measure2Code = measureCode2;
  }

  /**
   * @return the isShowQuantity2Fields
   */
  public boolean getIsShowQuantity2Fields() {
    return this.isShowQuantity2Fields;
  }

  /**
   * @param isShowQuantity2Fields the isShowQuantity2Fields to set
   */
  public void setShowQuantity2Fields(boolean isQuantity2Enable) {
    this.isShowQuantity2Fields = isQuantity2Enable;
  }

  /**
   * @return the price
   */
  public BigDecimal getPrice() {
    return this.price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * @return the isShowPriceFields
   */
  public boolean getIsShowPriceFields() {
    return this.isShowPriceFields;
  }

  /**
   * @param isShowPriceFields the isShowPriceFields to set
   */
  public void setShowPriceFields(boolean isShowPriceFields) {
    this.isShowPriceFields = isShowPriceFields;
  }

}
