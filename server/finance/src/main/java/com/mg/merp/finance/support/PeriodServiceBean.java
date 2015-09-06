/*
 * PeriodServiceBean.java
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

package com.mg.merp.finance.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.finance.PeriodServiceLocal;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.FinPeriod;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Периоды финансового учета"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodServiceBean.java,v 1.11 2008/09/19 11:12:42 sharapov Exp $
 */
@Stateless(name = "merp/finance/PeriodService") //$NON-NLS-1$
public class PeriodServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FinPeriod, Integer> implements PeriodServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final FinPeriod entity) {
    context.addRule(new MandatoryStringAttribute(entity, "PName")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "DateFrom")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "DateTo")); //$NON-NLS-1$
    context.addRule(new Rule() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#getMessage()
       */
      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.FINPERIOD_IS_CROSS);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
       */
      public void validate(ValidationContext context) {
        if (!context.getStatus().isError())
          if (isPeriodInvalid(entity))
            context.getStatus().error(this);
      }
    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(FinPeriod entity) {
    if (!isPeriodInUse(entity))
      super.onErase(entity);
    else
      throw new BusinessException(Messages.getInstance().getMessage(Messages.FINPERIOD_IS_IN_USE));
  }

  /**
   * Проверка фин.периода на принадлежность фин.операциям
   *
   * @param entity - фин.период
   * @return результат проверки
   */
  private boolean isPeriodInUse(FinPeriod entity) {
    Object count = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(FinOperation.class)
        .setProjection(Projections.rowCount())
        .add(Restrictions.between("KeepDate", entity.getDateFrom(), entity.getDateTo()))); //$NON-NLS-1$

    return (Integer) count > 0 ? true : false;
  }

  /**
   * Проверка корректности фин.периода
   *
   * @param entity - фин.период
   * @return результат проверки
   */
  private boolean isPeriodInvalid(FinPeriod entity) {
    // проверка корректности диапазона
    if ((entity.getDateFrom().compareTo(entity.getDateTo()) == 0) || (entity.getDateFrom().compareTo(entity.getDateTo()) > 0))
      return true;
    // проверка на пересечение
    Criteria criteria = OrmTemplate.createCriteria(FinPeriod.class)
        .add(Restrictions.or(
            Restrictions.or(
                Restrictions.and(Restrictions.le("DateFrom", entity.getDateFrom()), Restrictions.ge("DateTo", entity.getDateFrom())),
                Restrictions.and(Restrictions.le("DateFrom", entity.getDateTo()), Restrictions.ge("DateTo", entity.getDateTo()))),
            Restrictions.and(Restrictions.ge("DateFrom", entity.getDateFrom()), Restrictions.le("DateTo", entity.getDateTo()))));
    // при редактировании периода исключаем изменяемый период из списка существующих периодов
    if (entity.getId() != null)
      criteria.add(Restrictions.ne("Id", entity.getId())); //$NON-NLS-1$
    List<FinPeriod> finPeriods = OrmTemplate.getInstance().findByCriteria(criteria);

    return finPeriods.size() > 0 ? true : false;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.PeriodServiceLocal#closePeriod(java.io.Serializable[])
   */
  public void closePeriod(Serializable[] periodIds) {
    if (periodIds.length != 0) {
      for (int i = 0; i < periodIds.length; i++) {
        FinPeriod fp = load((Integer) periodIds[i]);
        internalClosePeriod(fp);
      }
    }
  }

  /**
   * Закрыть период
   *
   * @param finPeriod - сущность период фин. учета
   */
  private void internalClosePeriod(FinPeriod finPeriod) {
    finPeriod.setDateClose(DateTimeUtils.nowDate());
    finPeriod.setWhoClosed(ServerUtils.getUserProfile().getUserName());
    getPersistentManager().merge(finPeriod);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.PeriodServiceLocal#openPeriod(java.io.Serializable[])
   */
  public void openPeriod(Serializable[] periodIds) {
    if (periodIds.length != 0) {
      for (int i = 0; i < periodIds.length; i++) {
        FinPeriod fp = load((Integer) periodIds[i]);
        internalOpenPeriod(fp);
      }
    }
  }

  /**
   * Открыть период
   *
   * @param finPeriod - сущность период фин. учета
   */
  private void internalOpenPeriod(FinPeriod finPeriod) {
    finPeriod.setDateClose(null);
    finPeriod.setWhoClosed(null);
    getPersistentManager().merge(finPeriod);
  }

  private void internalCheckPeriod(FinPeriod period) {
    if (period.getDateClose() != null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PERIOD_CLOSED));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.finance.PeriodServiceLocal#findByDate(java.util.Date)
   */
  @PermitAll
  public FinPeriod findByDate(Date date) {
    List<FinPeriod> list = findByCriteria(Restrictions.le("DateFrom", date), Restrictions.ge("DateTo", date)); //$NON-NLS-1$ //$NON-NLS-2$
    if (list.isEmpty())
      throw new BusinessException(Messages.getInstance().getMessage(Messages.INVALID_PERIOD));
    return list.get(0);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.finance.PeriodServiceLocal#checkPeriod(java.util.Date)
   */
  @PermitAll
  public void checkPeriod(Date date) {
    internalCheckPeriod(findByDate(date));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.finance.PeriodServiceLocal#checkPeriod(java.lang.Integer)
   */
  @PermitAll
  public void checkPeriod(Integer periodId) {
    internalCheckPeriod(load(periodId));
  }

}
