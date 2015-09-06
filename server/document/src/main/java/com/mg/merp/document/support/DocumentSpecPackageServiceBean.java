/*
 * DocumentSpecPackageServiceBean.java
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

package com.mg.merp.document.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.document.DocumentSpecPackageServiceLocal;
import com.mg.merp.document.model.DocumentSpecPackage;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Упаковки товара позиции спецификации документа"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocumentSpecPackageServiceBean.java,v 1.5 2007/08/20 14:59:50 safonov Exp $
 */
@Stateless(name = "merp/document/DocumentSpecPackageService") //$NON-NLS-1$
public class DocumentSpecPackageServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocumentSpecPackage, Integer> implements DocumentSpecPackageServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, DocumentSpecPackage entity) {
    context.addRule(new MandatoryAttribute(entity, "Packing")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(DocumentSpecPackage entity) {
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(DocumentSpecPackage entity) {
    adjustEntity(entity);
  }

  protected void adjustEntity(DocumentSpecPackage entity) {
    doComputeWeightAndVolume(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocumentSpecPackageServiceLocal#computeWeightAndVolume(com.mg.merp.document.model.DocumentSpecPackage)
   */
  @PermitAll
  public void computeWeightAndVolume(DocumentSpecPackage documentSpecPackage) {
    doComputeWeightAndVolume(documentSpecPackage);
  }

  protected void doComputeWeightAndVolume(DocumentSpecPackage documentSpecPackage) {
    if (documentSpecPackage == null)
      return;

    BigDecimal packingWeight = BigDecimal.ZERO;
    BigDecimal packingVolume = BigDecimal.ZERO;
    if (documentSpecPackage.getPacking() != null) {
      packingWeight = documentSpecPackage.getPacking().getWeight() == null ? BigDecimal.ZERO : documentSpecPackage.getPacking().getWeight();
      packingVolume = documentSpecPackage.getPacking().getVolume() == null ? BigDecimal.ZERO : documentSpecPackage.getPacking().getVolume();
    }
    documentSpecPackage.setWeight(packingWeight.multiply(documentSpecPackage.getQuantity() == null ? BigDecimal.ZERO : documentSpecPackage.getQuantity()));
    documentSpecPackage.setVolume(packingVolume.multiply(documentSpecPackage.getQuantity() == null ? BigDecimal.ZERO : documentSpecPackage.getQuantity()));
  }

}
