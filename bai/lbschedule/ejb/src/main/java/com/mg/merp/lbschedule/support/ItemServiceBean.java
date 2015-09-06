/*
 * ItemServiceBean.java
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

package com.mg.merp.lbschedule.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.lbschedule.ItemServiceLocal;
import com.mg.merp.lbschedule.model.DateOffSetKind;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.Schedule;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.Holidays;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Пункты графиков исполнения обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemServiceBean.java,v 1.7 2007/07/12 13:23:03 safonov Exp $
 */
@Stateless(name = "merp/lbschedule/ItemService") //$NON-NLS-1$
public class ItemServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Item, Integer> implements ItemServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Item entity) {
    context.addRule(new MandatoryAttribute(entity, "ResultDate")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "ResultDateEnd")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "CurCode")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "CurRateType")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "CurRateAuthority")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.lbschedule.ItemServiceLocal#computeResultDate(com.mg.merp.lbschedule.model.Item)
   */
  @PermitAll
  public void computeResultDate(Item item) {
    internalComputeResultDate(item);
  }

  private void internalComputeResultDate(Item item) {
    if (item == null)
      return;

    Date resultDate = null;

    if (item.getIsAbsDate()) {
      if (!isDateConditionsValid(item.getIsDateRelDoc(), item.getIsDateRelEnd()))
        throw new BusinessException(Messages.getInstance().getMessage(Messages.INVALID_DATE_CONDITIONS));

      if (item.getIsDateRelDoc())
        resultDate = computeDate(getDocHead(item).getDocDate(), item.getDateOffSet(), item.getDateOffSetKind());
      else {
        if (item.getDateRelItem() != null)
          if (item.getIsDateRelEnd())
            resultDate = computeDate(item.getDateRelItem().getResultDateEnd(), item.getDateOffSet(), item.getDateOffSetKind());
          else
            resultDate = computeDate(item.getDateRelItem().getResultDate(), item.getDateOffSet(), item.getDateOffSetKind());
      }
      item.setResultDate(resultDate);

      if (item.getResultDateEnd() == null)
        item.setResultDateEnd(resultDate);
    }
  }

  private boolean isDateConditionsValid(boolean isDateRelDoc, boolean isDateRelEnd) {
    if (isDateRelDoc && isDateRelEnd)
      return false;
    else
      return true;
  }

  private DocHead getDocHead(Item item) {
    Schedule schedule = item.getSchedule();
    ScheduleDocHeadLink scheduleDocHeadLink = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ScheduleDocHeadLink.class)
        .add(Restrictions.eq("Schedule", schedule))); //$NON-NLS-1$
    //return  ServerUtils.getPersistentManager().find(DocHead.class, schedule.getDocId());
    if (scheduleDocHeadLink != null)
      return scheduleDocHeadLink.getDocHead();
    else
      return null;
  }

  private Date computeDate(Date initialDate, Integer dateOffset, DateOffSetKind dateOffSetKind) {
    Locale serverLocale = ServerUtils.getUserLocale();
    if (dateOffset == null)
      dateOffset = 0;

    if (dateOffSetKind == DateOffSetKind.WORKDAY)
      return incWorkDay(initialDate, dateOffset);
    if (dateOffSetKind == DateOffSetKind.DAY)
      return ScheduleDateTimeUtils.incDay(initialDate, dateOffset, serverLocale);
    if (dateOffSetKind == DateOffSetKind.MONTH)
      return ScheduleDateTimeUtils.incMonth(initialDate, dateOffset, serverLocale);
    if (dateOffSetKind == DateOffSetKind.YEAR)
      return ScheduleDateTimeUtils.incYear(initialDate, dateOffset, serverLocale);
    return null;
  }

  private Date incWorkDay(Date date, Integer daysAmount) {
    Locale locale = ServerUtils.getUserLocale();
    Date result = date;
    List<Holidays> holidays = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Holidays.class)
        .add(Restrictions.ge("HDate", date))); //$NON-NLS-1$

    for (int i = 0; i < daysAmount; i++) {
      do {
        result = ScheduleDateTimeUtils.incDay(result, 1, locale);
      }
      while (isWeekend(result) || isHoliday(result, holidays));
    }
    return result;
  }

  private boolean isHoliday(Date date, List<Holidays> holidays) {
    for (Holidays holiday : holidays) {
      if (date.equals(holiday.getHDate()))
        return true;
    }
    return false;
  }

  private boolean isWeekend(Date date) {
    Calendar calendar = Calendar.getInstance(ServerUtils.getUserLocale());
    calendar.setTime(date);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
      return true;
    else
      return false;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.lbschedule.ItemServiceLocal#computeResultSum(com.mg.merp.lbschedule.model.Item)
   */
  @PermitAll
  public void computeResultSum(Item item) {
    internalComputeResultSum(item);
  }

  private void internalComputeResultSum(Item item) {
    if (item == null)
      return;

    BigDecimal resultSum = BigDecimal.ZERO;

    if (item.getIsAbsSum()) {
      if (!isSumConditionsValid(item.getIsSumRelDoc(), item.getIsRelFact()))
        throw new BusinessException(Messages.getInstance().getMessage(Messages.INVALID_SUM_CONDITIONS));

      if (item.getIsSumRelDoc()) {
        DocHead docHead = getDocHead(item);
        resultSum = computeSum(item, docHead.getCurrency(), docHead.getSumCur());
      } else {
        Item sumRelItem = item.getSumRelItem();
        if (sumRelItem != null)
          resultSum = computeSum(item, sumRelItem.getCurCode(), sumRelItem.getFactSum());
      }
      item.setResultSum(resultSum);
    }
  }

  private boolean isSumConditionsValid(boolean isSumRelDoc, boolean isSumRelFact) {
    if (isSumRelDoc && isSumRelFact)
      return false;
    else
      return true;
  }

  private BigDecimal computeSum(Item item, Currency currencyFrom, BigDecimal currencyFromAmount) {
    if (currencyFromAmount == null)
      return BigDecimal.ZERO;

    BigDecimal sumToCompute = BigDecimal.ZERO;
    BigDecimal persent = BigDecimal.ZERO;

    if (!isSameCurrency(item.getCurCode(), currencyFrom))
      sumToCompute = convertIntoItemCurrency(item, currencyFrom, currencyFromAmount);
    else
      sumToCompute = currencyFromAmount;

    if (item.getPerc() != null)
      persent = item.getPerc();

    return sumToCompute.divide(new BigDecimal(100)).multiply(persent);
    //return MathUtils.round(sumToCompute.divide(new BigDecimal(100)).multiply(persent), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC));
  }

  private BigDecimal convertIntoItemCurrency(Item item, Currency currencyFrom, BigDecimal currencyFromAmount) {
    if (currencyFromAmount == null)
      return BigDecimal.ZERO;
    else
      return getCurencyConverter().conversion(item.getCurCode(), currencyFrom, item.getCurRateAuthority(), item.getCurRateType(), item.getResultDate(), currencyFromAmount);
  }

  private boolean isSameCurrency(Currency currency1, Currency currency2) {
    if (currency1.getId().equals(currency2.getId()))
      return true;
    else
      return false;
  }

  private CurrencyServiceLocal getCurencyConverter() {
    return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
  }

}
