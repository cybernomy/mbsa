/*
 * CurrencyRateServiceBean.java
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

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRate;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Курсы валют"
 *
 * @author leonova
 * @version $Id: CurrencyRateServiceBean.java,v 1.7 2007/07/11 08:18:34 safonov Exp $
 */
@Stateless(name = "merp/reference/CurrencyRateService")
public class CurrencyRateServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CurrencyRate, Integer> implements CurrencyRateServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, CurrencyRate entity) {
    context.addRule(new MandatoryAttribute(entity, "Currency2"));
    context.addRule(new MandatoryAttribute(entity, "CurrencyRateAuthority"));
    context.addRule(new MandatoryAttribute(entity, "CurrencyRateType"));
    context.addRule(new MandatoryAttribute(entity, "EffectiveDate"));
    context.addRule(new MandatoryAttribute(entity, "Rate"));
  }

  protected BigDecimal doGetCurrencyRate(Currency currencyTo, Currency currencyFrom, CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, Date effectiveDate) throws CurrencyRateNotFoundException {
    if (currencyTo == null || currencyFrom == null || currencyTo.getId().equals(currencyFrom.getId()))
      return BigDecimal.ONE;//нет одной из валют или для одинаковых валют курс 1
    else {
      if (effectiveDate == null)
        effectiveDate = DateTimeUtils.nowTimestamp();
      else
        effectiveDate = DateTimeUtils.toTimestamp(effectiveDate);
      String currencyToCode = currencyTo == null ? null : currencyTo.getCode();
      String currencyFromCode = currencyFrom == null ? null : currencyFrom.getCode();
      Integer rateAuthorityId = rateAuthority == null ? null : rateAuthority.getId();
      Integer rateTypeId = rateType == null ? null : rateType.getId();
//			DetachedCriteria dc = DetachedCriteria.forClass(CurrencyRate.class, "cr2")
//					.createAlias("cr2.Currency1", "c21")
//					.createAlias("cr2.Currency2", "c22")
//					.setProjection(Projections.max("cr2.EffectiveDate"))
//					.add(Restrictions.eq("c21.Code", currency1.getCode()))
//					.add(Restrictions.eq("c22.Code", currency2.getCode()))
//					.add(Restrictions.eq("cr2.CurrencyRateType", rateType))
//					.add(Restrictions.eq("cr2.CurrencyRateAuthority", rateAuthority))
//					.add(Restrictions.le("cr2.EffectiveDate", effectiveDate));
//			result = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CurrencyRate.class, "cr1")
//					.createAlias("cr1.Currency1", "c11")
//					.createAlias("cr1.Currency2", "c12")
//					.setProjection(Projections.property("cr1.Rate"))
//					.add(Restrictions.eq("c11.Code", currency1.getCode()))
//					.add(Restrictions.eq("c12.Code", currency2.getCode()))
//					.add(Restrictions.eq("cr1.CurrencyRateType", rateType))
//					.add(Restrictions.eq("cr1.CurrencyRateAuthority", rateAuthority))
//					//.add(Subqueries.propertyEq("cr1.EffectiveDate", dc)));
      //используем SQL, предыдущий код не работает, вероятно дефект в hibernate
      List<BigDecimal> result = JdbcTemplate.getInstance()
          .query(new StringBuilder("select cr.rate from ref_currency_rate cr where ((cr.currency_code1 = ?) and (cr.currency_code2 = ?)")
              .append(" and (cr.rate_authority_id = ?) and (cr.rate_type_id = ?)")
              .append(" and (cr.effective_date =")
              .append(" (select max(cr1.effective_date) from ref_currency_rate cr1 where ((cr1.effective_date <= ?)")
              .append(" and (cr1.currency_code1 = ?) and (cr1.currency_code2 = ?)")
              .append(" and (cr1.rate_authority_id = ?) and (cr1.rate_type_id = ?)))))").toString()
              , new Object[]{currencyToCode, currencyFromCode, rateAuthorityId, rateTypeId, effectiveDate, currencyToCode, currencyFromCode, rateAuthorityId, rateTypeId}, new RowMapper<BigDecimal>() {

            public BigDecimal mapRow(ResultSet rs, int rowNum) throws SQLException {
              return rs.getBigDecimal(1);
            }

          });
      if (result.size() == 0)
        throw new CurrencyRateNotFoundException(currencyToCode, currencyFromCode, /*rateAuthority.getCode(), rateType.getCode()*/null, null, effectiveDate);
      else
        return (BigDecimal) result.get(0);
    }
  }


  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrencyRateServiceLocal#getCurrencyRate(com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.CurrencyRateAuthority, com.mg.merp.reference.model.CurrencyRateType, java.util.Date)
   */
  @PermitAll
  public BigDecimal getCurrencyRate(Currency currencyTo, Currency currencyFrom, CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, Date effectiveDate) throws CurrencyRateNotFoundException {
    return doGetCurrencyRate(currencyTo, currencyFrom, rateAuthority, rateType, effectiveDate);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrencyRateServiceLocal#getIndirectCurrencyRate(com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.Currency, com.mg.merp.reference.model.CurrencyRateAuthority, com.mg.merp.reference.model.CurrencyRateType, java.util.Date)
   */
  @PermitAll
  public BigDecimal getIndirectCurrencyRate(Currency currencyTo, Currency currencyFrom, CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, Date effectiveDate) throws CurrencyRateNotFoundException {
    //меняем местами валюты
    return doGetCurrencyRate(currencyFrom, currencyTo, rateAuthority, rateType, effectiveDate);
  }

}
