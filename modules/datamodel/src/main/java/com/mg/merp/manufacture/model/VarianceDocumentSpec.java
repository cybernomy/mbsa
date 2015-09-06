/*
 * VarianceDocumentSpec.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture.model;


/**
 * @author hbm2java
 * @version $Id: VarianceDocumentSpec.java,v 1.4 2006/06/06 07:58:18 leonova Exp $
 */
public class VarianceDocumentSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

  // Fields
  private com.mg.merp.mfreference.model.CostCategories CostCategory;
  private VarianceType VarianceType;


  // Constructors

  /**
   * default constructor
   */
  public VarianceDocumentSpec() {
  }


  // Property accessors

  /**

   */

  public com.mg.merp.mfreference.model.CostCategories getCostCategory() {
    return this.CostCategory;
  }

  public void setCostCategory(com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
    this.CostCategory = MfCostCategories;
  }

  /**

   */

  public VarianceType getVarianceType() {
    return this.VarianceType;
  }

  public void setVarianceType(VarianceType VarianceType) {
    this.VarianceType = VarianceType;
  }


}