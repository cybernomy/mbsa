/*
 * CalcPeriodServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.UIProfileManagerLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.CalcPeriod;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Расчетные периоды"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: CalcPeriodServiceBean.java,v 1.8 2008/05/29 04:54:32 alikaev Exp $
 */
@Stateless(name = "merp/personnelref/CalcPeriodService") //$NON-NLS-1$
public class CalcPeriodServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcPeriod, Integer> implements CalcPeriodServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final CalcPeriod entity) {
    context.addRule(new MandatoryStringAttribute(entity, "PName")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "BeginDate")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "EndDate")); //$NON-NLS-1$

    context.addRule(new Rule() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#getMessage()
       */
      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.CALC_PERIOD_END_DATE_AFTER_BEGIN_DATE);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
       */
      public void validate(ValidationContext context) {
        if (!isPeriodDatesValid(entity))
          context.getStatus().error(this);
      }
    });

    context.addRule(new Rule() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#getMessage()
       */
      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.CALC_PERIOD_IS_CROSS);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
       */
      public void validate(ValidationContext context) {
        if (!context.getStatus().isError())
          if (isPeriodCross(entity))
            context.getStatus().error(this);
      }
    });
  }

  /**
   * Проверить корректность диапазона дат
   *
   * @param calcPeriod - расчетный период
   * @return true - если диапазона дат корректен
   */
  protected boolean isPeriodDatesValid(CalcPeriod calcPeriod) {
    Date beginDate = calcPeriod.getBeginDate();
    Date endDate = calcPeriod.getEndDate();
    if (beginDate == null || endDate == null)
      return true;
    return beginDate.compareTo(endDate) > 0 ? false : true;
  }

  /**
   * Проверить пересечение даным периодом других периодов
   *
   * @param calcPeriod - расчетный период
   * @return true - если данный период пересекает другие периоды
   */
  protected boolean isPeriodCross(CalcPeriod calcPeriod) {
    Criteria criteria = OrmTemplate.createCriteria(CalcPeriod.class)
        .setProjection(Projections.rowCount())
        .add(Restrictions.or(
            Restrictions.and(Restrictions.le("BeginDate", calcPeriod.getBeginDate()), Restrictions.ge("EndDate", calcPeriod.getBeginDate())), //$NON-NLS-1$ //$NON-NLS-2$
            Restrictions.and(Restrictions.le("BeginDate", calcPeriod.getEndDate()), Restrictions.ge("EndDate", calcPeriod.getEndDate())))); //$NON-NLS-1$ //$NON-NLS-2$

    // при редактировании периода исключаем изменяемый период из списка существующих периодов
    if (calcPeriod.getId() != null)
      criteria.add(Restrictions.ne("Id", calcPeriod.getId())); //$NON-NLS-1$

    Integer crossedPeriodsCount = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

    if (crossedPeriodsCount > 0)
      return true;
    else
      return false;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.personnelref.CalcPeriodServiceLocal#getCurrentCalcPeriod()
   */
  @PermitAll
  public CalcPeriod getCurrentCalcPeriod() {
    return doGetCurrentCalcPeriod();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.personnelref.CalcPeriodServiceLocal#setCurrentCalcPeriod(java.lang.Integer)
   */
  public void setCurrentCalcPeriod(Integer calcPeriodId) {
    doSetCurrentCalcPeriod(calcPeriodId);
  }

  protected CalcPeriod doGetCurrentCalcPeriod() {
    int currentCalcPeriodId = UIProfileManagerLocator.locate().load(CalcPeriodServiceLocal.PROFILE_NAME).getProperty(CalcPeriodServiceLocal.PROFILE_PROPERTY, 0);
    CalcPeriod calcPeriod = ServerUtils.getPersistentManager().find(CalcPeriod.class, currentCalcPeriodId);
    if (calcPeriod == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CURRENT_CALC_PERIOD_IS_NOT_SET));
    else
      return calcPeriod;
  }

  protected void doSetCurrentCalcPeriod(Integer calcPeriodId) {
    CalcPeriod calcPeriod = ServerUtils.getPersistentManager().find(CalcPeriod.class, calcPeriodId);
    if (calcPeriod.getStaffList() == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_CHOOSE_STAFFLIST_BY_SEARCHED_CALCPERIOD));
    UIProfile profile = UIProfileManagerLocator.locate().load(CalcPeriodServiceLocal.PROFILE_NAME);
    profile.setProperty(CalcPeriodServiceLocal.PROFILE_PROPERTY, calcPeriodId);
    UIProfileManagerLocator.locate().store(profile);
  }

}
