/*
 * CustomsDeclarationStateValuesImpl.java
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
package com.mg.merp.warehouse.support;

import java.io.Serializable;

/**
 * @author Artem V. Sharapov
 * @version $Id: CustomsDeclarationStateValuesImpl.java,v 1.1 2008/08/27 09:41:21 sharapov Exp $
 */
public class CustomsDeclarationStateValuesImpl implements Serializable {

  private Integer customsDeclarationId;
  private Integer countryOfOriginId;

  public CustomsDeclarationStateValuesImpl() {
  }

  public CustomsDeclarationStateValuesImpl(Integer customsDeclarationId, Integer countryOfOriginId) {
    this.customsDeclarationId = customsDeclarationId;
    this.countryOfOriginId = countryOfOriginId;
  }

  /**
   * @return the customsDeclarationId
   */
  public Integer getCustomsDeclarationId() {
    return this.customsDeclarationId;
  }

  /**
   * @param customsDeclarationId the customsDeclarationId to set
   */
  public void setCustomsDeclarationId(Integer customsDeclarationId) {
    this.customsDeclarationId = customsDeclarationId;
  }

  /**
   * @return the countryOfOriginId
   */
  public Integer getCountryOfOriginId() {
    return this.countryOfOriginId;
  }

  /**
   * @param countryOfOriginId the countryOfOriginId to set
   */
  public void setCountryOfOriginId(Integer countryOfOriginId) {
    this.countryOfOriginId = countryOfOriginId;
  }

}
