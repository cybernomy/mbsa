/*
 * FeeRefServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.support.DateIntervalRule;
import com.mg.merp.personnelref.support.PersonnelrefUtils;
import com.mg.merp.salary.FeeRefParamServiceLocal;
import com.mg.merp.salary.FeeRefServiceLocal;
import com.mg.merp.salary.IncludedFeeServiceLocal;
import com.mg.merp.salary.ReplacedFeeServiceLocal;
import com.mg.merp.salary.TariffingInFeeServiceLocal;
import com.mg.merp.salary.model.FeePerioicity;
import com.mg.merp.salary.model.FeeRef;
import com.mg.merp.salary.model.FeeRefParam;
import com.mg.merp.salary.model.FeeType;
import com.mg.merp.salary.model.IncludedFee;
import com.mg.merp.salary.model.ReplacedFee;
import com.mg.merp.salary.model.TariffingInFee;
import com.mg.merp.salary.model.TripleSumSign;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Начисления / удержания"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeRefServiceBean.java,v 1.6 2007/11/08 07:02:22 sharapov Exp $
 */
@Stateless(name = "merp/salary/FeeRefService") //$NON-NLS-1$
public class FeeRefServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeeRef, Integer> implements FeeRefServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, FeeRef entity) {
    context.addRule(new MandatoryStringAttribute(entity, "FCode")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "CalcListSectionRef")); //$NON-NLS-1$
    context.addRule(new DateIntervalRule(entity));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(FeeRef entity) {
    entity.setFeeType(FeeType.BASE);
    entity.setPeriodiCity(FeePerioicity.CONSTANT);
    entity.setSumSign(TripleSumSign.NONE);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(FeeRef entity) {
    adjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(FeeRef entity) {
    adjust(entity);
  }

  private void adjust(FeeRef entity) {
    PersonnelrefUtils.checkDateInterval(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(FeeRef entity) {
    entity.setFCode(entity.getFCode().trim().concat("clone")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(FeeRef entity, FeeRef entityClone) {
    final String FEE_REF_ATTRIBUTE_NAME = "FeeRef"; //$NON-NLS-1$
    FeeRefParamServiceLocal feeRefParamService = (FeeRefParamServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FeeRefParamServiceLocal.LOCAL_SERVICE_NAME);
    TariffingInFeeServiceLocal tariffingInFeeService = (TariffingInFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/TariffingInFee"); //$NON-NLS-1$
    IncludedFeeServiceLocal includedFeeService = (IncludedFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/IncludedFee"); //$NON-NLS-1$
    ReplacedFeeServiceLocal replacedFeeService = (ReplacedFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/ReplacedFee"); //$NON-NLS-1$

    AttributeMap initAttributes = new LocalDataTransferObject();
    initAttributes.put(FEE_REF_ATTRIBUTE_NAME, entityClone);
    // копирование параметров
    for (FeeRefParam feeRefParam : feeRefParamService.findByCriteria(Restrictions.eq(FEE_REF_ATTRIBUTE_NAME, entity)))
      feeRefParamService.clone(feeRefParam, true, initAttributes);
    // копирование используемых тарифов
    for (TariffingInFee tariffingInFee : tariffingInFeeService.findByCriteria(Restrictions.eq(FEE_REF_ATTRIBUTE_NAME, entity)))
      tariffingInFeeService.clone(tariffingInFee, true, initAttributes);
    // копирование входящих н/у
    for (IncludedFee includedFee : includedFeeService.findByCriteria(Restrictions.eq(FEE_REF_ATTRIBUTE_NAME, entity)))
      includedFeeService.clone(includedFee, true, initAttributes);
    // копирование вытесняемых н/у
    for (ReplacedFee replacedFee : replacedFeeService.findByCriteria(Restrictions.eq(FEE_REF_ATTRIBUTE_NAME, entity)))
      replacedFeeService.clone(replacedFee, true, initAttributes);
  }

}
