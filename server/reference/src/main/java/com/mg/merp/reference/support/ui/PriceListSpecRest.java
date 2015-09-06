/*
 * PriceListSpecRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.PriceType;

/**
 * Контроллер формы условий отбора спецификаций прайс-листов
 *
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListSpecRest.java,v 1.5 2008/10/13 05:50:58 sharapov Exp $
 */
public class PriceListSpecRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("Reference.Cond.PriceListSpec.SpecName") //$NON-NLS-1$
  private String specName = null;

  @DataItemName("Reference.Cond.PriceListSpec.SpecFullName") //$NON-NLS-1$
  private String specFullName = null;

  @DataItemName("Reference.Cond.PriceListSpec.Code") //$NON-NLS-1$
  private String code = null;

  /*
   * Тип позиции
   */
  private CatalogType goodType = null;

  /*
   * Действительно на
   */
  @DataItemName("Reference.Cond.PriceListSpec.DateTill") //$NON-NLS-1$
  private java.util.Date dateTill = null;

  /*
   * Артикул поставщика
   */
  @DataItemName("Reference.Cond.PriceListSpec.InternalCode") //$NON-NLS-1$
  private String internalCode = null;

  /*
   * Артикул
   */
  @DataItemName("Reference.Cond.PriceListSpec.Articul") //$NON-NLS-1$
  private String articul = null;

  @DataItemName("Reference.Cond.PriceListSpec.NotInUse")     //$NON-NLS-1$
  private boolean notInUse = false;

  @DataItemName("Reference.Cond.PriceListSpec.ShowInUse")     //$NON-NLS-1$
  private boolean showInUse = false;

  /**
   * Использовать признак "Тип цены"
   */
  @DataItemName("Reference.Cond.PriceListSpec.NotInUsePriceType")     //$NON-NLS-1$
  private boolean notInUsePriceType = false;

  /**
   * тип цены
   */
  @DataItemName("Reference.PriceType")     //$NON-NLS-1$
  private PriceType priceType = null;

  private Integer priceListHeadId;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.specName = null;
    this.specFullName = null;
    this.code = null;
    this.internalCode = null;
    this.articul = null;
    this.goodType = null;
    this.dateTill = null;
    this.notInUse = false;
    this.showInUse = false;
    this.notInUsePriceType = false;
    this.priceType = null;
  }

  /**
   * @return наименование спецификации(позиции) прайс-листа
   */
  public String getSpecName() {
    return specName;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @return артикул поставщика
   */
  public String getInternalCode() {
    return internalCode;
  }

  /**
   * @return артикул
   */
  public String getArticul() {
    return articul;
  }

  /**
   * @return наименование в прайс-листе
   */
  public String getSpecFullName() {
    return specFullName;
  }

  /**
   * @return действительно на
   */
  public java.util.Date getDateTill() {
    return dateTill;
  }

  /**
   * @return тип позиции
   */
  public CatalogType getGoodType() {
    return goodType;
  }

  /**
   * @return the notInUse
   */
  public boolean isNotInUse() {
    return notInUse;
  }

  /**
   * @return the showInUse
   */
  public boolean isShowInUse() {
    return showInUse;
  }

  public PriceType getPriceType() {
    return priceType;
  }

  public boolean isNotInUsePriceType() {
    return notInUsePriceType;
  }

  /**
   * @return the priceListHeadId
   */
  public Integer getPriceListHeadId() {
    return this.priceListHeadId;
  }

  /**
   * Установить идентификатор прайс-листа
   *
   * @param priceListHeadId - идентификатор прайс-листа
   */
  public void setPriceListHeadId(Integer priceListHeadId) {
    this.priceListHeadId = priceListHeadId;
  }

}
