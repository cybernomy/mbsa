/*
 * CatalogRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.Okdp;

import java.math.BigDecimal;

/**
 * Условия отбора каталога
 *
 * @author Oleg V. Safonov
 * @version $Id: CatalogRest.java,v 1.7 2008/10/09 06:54:01 safonov Exp $
 */
public class CatalogRest extends DefaultHierarhyRestrictionForm {
  @DataItemName("Reference.Code")
  private String code = "";
  @DataItemName("Reference.Name")
  private String name = "";
  private CatalogType goodType = null;
  @DataItemName("Reference.Catalog.PluCode")
  private String pluCode = "";
  @DataItemName("Reference.Catalog.BarCode")
  private String barCode = "";
  @DataItemName("Reference.Catalog.Measure1")
  private Measure measure1 = null;
  @DataItemName("Reference.Catalog.Measure2")
  private Measure measure2 = null;
  private Okdp okdpCode = null;
  @DataItemName("Reference.CatalogCond.NotInUse")
  private boolean notInUse = false;
  @DataItemName("Reference.CatalogCond.ShowInUse")
  private boolean showInUse = false;
  @DataItemName("Reference.CatalogCond.TradeTaxFrom")
  private BigDecimal tradeTaxFrom = null;
  @DataItemName("Reference.CatalogCond.TradeTaxTo")
  private BigDecimal tradeTaxTo = null;
  /*	private QuantityKind quantityKind = null;
      private int quantityCondKind = 0;*/
  @DataItemName("Reference.CatalogCond.QuantityValue")
  private double quantityValue = 0;

  public CatalogRest() {
    super();
    registerRestrictionItem("code", null, true);
    registerRestrictionItem("name", null, true);
    registerRestrictionItem("pluCode", null, true);
    registerRestrictionItem("barCode", null, true);
    registerRestrictionItem("measure1", null, true);
    registerRestrictionItem("measure2", null, true);
    registerRestrictionItem("okdpCode", null, true);
    registerRestrictionItem("notInUse", false, true);
    registerRestrictionItem("showInUse", false, true);
    registerRestrictionItem("goodType", null, true);
    registerRestrictionItem("tradeTaxFrom", null, true);
    registerRestrictionItem("tradeTaxTo", null, true);
  }

  /**
   * @return Returns the code.
   */
  public String getCode() {
    return code;
  }

  /**
   * @return Returns the name.
   */
  public String getName() {
    return name;
  }

  /**
   * @return Returns the goodType.
   */
  public CatalogType getGoodType() {
    return goodType;
  }

  /**
   * @return Returns the tradeTaxFrom.
   */
  public BigDecimal getTradeTaxFrom() {
    return tradeTaxFrom;
  }

  /**
   * @return Returns the tradeTaxTo.
   */
  public BigDecimal getTradeTaxTo() {
    return tradeTaxTo;
  }

  /**
   * @return Returns the quantityValue.
   */
  public double getQuantityValue() {
    return quantityValue;
  }

  /**
   * @return Returns the barCode.
   */
  public String getBarCode() {
    return barCode;
  }

  /**
   * @return Returns the pluCode.
   */
  public String getPluCode() {
    return pluCode;
  }

  /**
   * @return Returns the measure1.
   */
  public com.mg.merp.reference.model.Measure getMeasure1() {
    return measure1;
  }

  /**
   * @return Returns the measure2.
   */
  public com.mg.merp.reference.model.Measure getMeasure2() {
    return measure2;
  }

  /**
   * @return Returns the okdpCode.
   */
  public com.mg.merp.reference.model.Okdp getOkdpCode() {
    return okdpCode;
  }

  /**
   * @return Returns the notInUse.
   */
  public boolean isNotInUse() {
    return notInUse;
  }

  /**
   * @return Returns the showInUse.
   */
  public boolean isShowInUse() {
    return showInUse;
  }

}
