/*
 * CurrencyServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.CurrencyConversionResult;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Валюты"
 *
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: CurrencyServiceBean.java,v 1.12 2008/04/25 13:48:20 safonov Exp $
 */
@Stateless(name = "merp/reference/CurrencyService")
public class CurrencyServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Currency, Integer> implements CurrencyServiceLocal {
  private void adjustMeasure(Currency entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  @Override
  protected void onCreate(Currency entity) {
    adjustMeasure(entity);
  }

  @Override
  protected void onStore(Currency entity) {
    adjustMeasure(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Currency entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "BankCode"));
    context.addRule(new MandatoryStringAttribute(entity, "FullName"));
    context.addRule(new MandatoryStringAttribute(entity, "Iso"));
    context.addRule(new MandatoryAttribute(entity, "Gender"));
    context.addRule(new MandatoryStringAttribute(entity, "AltName1"));
    context.addRule(new MandatoryStringAttribute(entity, "AltName2"));
    context.addRule(new MandatoryStringAttribute(entity, "AltName3"));
    context.addRule(new MandatoryStringAttribute(entity, "AltUnitName1"));
    context.addRule(new MandatoryStringAttribute(entity, "AltUnitName2"));
    context.addRule(new MandatoryStringAttribute(entity, "AltUnitName3"));
    context.addRule(new MandatoryAttribute(entity, "RoundPrice"));
    context.addRule(new MandatoryAttribute(entity, "RoundSum"));
  }

  /**
   * реализация конвертации валют, итоговый результат округляется либо до масштаба входящей суммы
   */
  protected CurrencyConversionResult doConversion(Currency currencyTo, Currency currencyFrom,
                                                  CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) {
    CurrencyRateServiceLocal rateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
    try {
      BigDecimal rate = rateService.getCurrencyRate(currencyTo, currencyFrom, rateAuthority, rateType, effectiveDate);
      return new CurrencyConversionResultImpl(MathUtils.multiply(currencyFromAmount, rate, new RoundContext(currencyFromAmount.scale())), rate, true);
    } catch (CurrencyRateNotFoundException rnf1) {
      try {
        BigDecimal rate = rateService.getCurrencyRate(currencyFrom, currencyTo, rateAuthority, rateType, effectiveDate);
        return new CurrencyConversionResultImpl(MathUtils.divide(currencyFromAmount, rate, new RoundContext(currencyFromAmount.scale())), rate, false);
      } catch (CurrencyRateNotFoundException rnf2) {
        throw rnf1;
      }
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrencyServiceLocal#conversion(com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.CurrencyRateAuthority, com.mg.merp.reference.model.CurrencyRateType, java.util.Date, java.math.BigDecimal)
   */
  @PermitAll
  public BigDecimal conversion(Currency currencyTo, Currency currencyFrom,
                               CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException {
    if (currencyFromAmount == null)
      throw new IllegalArgumentException("currencyFromAmount is null");
    return doConversion(currencyTo, currencyFrom, rateAuthority, rateType, effectiveDate, currencyFromAmount).getAmount();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrencyServiceLocal#conversionEx(com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.CurrencyRateAuthority, com.mg.merp.reference.model.CurrencyRateType, java.util.Date, java.math.BigDecimal)
   */
  @PermitAll
  public CurrencyConversionResult conversionEx(Currency currencyTo, Currency currencyFrom, CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException {
    if (currencyFromAmount == null)
      throw new IllegalArgumentException("currencyFromAmount is null");
    return doConversion(currencyTo, currencyFrom, rateAuthority, rateType, effectiveDate, currencyFromAmount);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrencyServiceLocal#findByCode(java.lang.String)
   */
  public Currency findByCode(String code) {
    if (StringUtils.stringNullOrEmpty(code))
      return null;

    List<Currency> list = findByCriteria(Restrictions.eq("UpCode", code.trim().toUpperCase()));
    return list.isEmpty() ? null : list.get(0);
  }

}
