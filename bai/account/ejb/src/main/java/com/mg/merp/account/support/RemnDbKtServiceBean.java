/*
 * RemnDbKtServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.RemnDbKtServiceLocal;
import com.mg.merp.account.generic.RemnServiceBean;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlForm;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnDbKt;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Ведомость расчетов с контрагентами"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnDbKtServiceBean.java,v 1.9 2009/03/18 10:19:27 sharapov Exp $
 */
@Stateless(name = "merp/account/RemnDbKtService") //$NON-NLS-1$
public class RemnDbKtServiceBean extends RemnServiceBean<RemnDbKt, Integer> implements RemnDbKtServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getAllAccountsList()
   */
  @Override
  protected AccPlan[] getAllAccountsList() {
    List<AccPlan> accounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccPlan.class)
        .add(Restrictions.between("AnlForm", AnlForm.DKBASE, AnlForm.REPORTFACE))); //$NON-NLS-1$
    return accounts.toArray(new AccPlan[accounts.size()]);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#carryForwardBalance(com.mg.merp.account.model.Period, com.mg.merp.account.model.AccPlan)
   */
  @Override
  protected void carryForwardBalance(Period accPeriod, AccPlan account) {
    OrmTemplate ormTemplateInst = OrmTemplate.getInstance();
    // find previous period, may be several, get first
    List<Period> prevPeriods = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(Period.class)
        .add(Restrictions.lt("DateTo", accPeriod.getDateFrom())) //$NON-NLS-1$
        .addOrder(Order.desc("DateFrom"))); //$NON-NLS-1$
    Period prevPeriod = prevPeriods.get(0);

    //delete lines with empty turnover
    OrmTemplate.getInstance().bulkUpdate("delete from RemnDbKt r where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID) and (not exists (select s from EconomicSpec s where (s.RemnDb.Id = r.id) or (s.RemnKt.Id = r.id) ))", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // update begin remains with zero
    OrmTemplate.getInstance().bulkUpdate("update RemnDbKt r set r.RemnBeginNatDb = 0, r.RemnBeginNatKt = 0, r.RemnBeginCurDb = 0, r.RemnBeginCurKt = 0 where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID)", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    List<Integer> pervRemnIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(RemnDbKt.class)
        .setProjection(Projections.property("Id")) //$NON-NLS-1$
        .add(Restrictions.and(Restrictions.eq("Period", prevPeriod), Restrictions.eq("AccPlan", account)))); //$NON-NLS-1$ //$NON-NLS-2$

    for (Integer pervRemnId : pervRemnIDs) {
      RemnDbKt prevRemnDbKtEntity = load(pervRemnId);
      getPersistentManager().refresh(prevRemnDbKtEntity);

      Criteria remnDbKtCriteria = OrmTemplate.createCriteria(RemnDbKt.class)
          .setProjection(Projections.property("Id")) //$NON-NLS-1$
          .add(Restrictions.conjunction(
              Restrictions.eq("Period", accPeriod),  //$NON-NLS-1$
              Restrictions.eq("AccPlan", account),  //$NON-NLS-1$
              Restrictions.eq("DocBaseType", prevRemnDbKtEntity.getDocBaseType()),  //$NON-NLS-1$
              Restrictions.eq("DocType", prevRemnDbKtEntity.getDocType()))); //$NON-NLS-1$

      if (prevRemnDbKtEntity.getAnlPlan1() != null)
        remnDbKtCriteria.add(Restrictions.eq("AnlPlan1", prevRemnDbKtEntity.getAnlPlan1())); //$NON-NLS-1$
      else
        remnDbKtCriteria.add(Restrictions.isNull("AnlPlan1")); //$NON-NLS-1$
      if (prevRemnDbKtEntity.getAnlPlan2() != null)
        remnDbKtCriteria.add(Restrictions.eq("AnlPlan2", prevRemnDbKtEntity.getAnlPlan2())); //$NON-NLS-1$
      else
        remnDbKtCriteria.add(Restrictions.isNull("AnlPlan2")); //$NON-NLS-1$
      if (prevRemnDbKtEntity.getAnlPlan3() != null)
        remnDbKtCriteria.add(Restrictions.eq("AnlPlan3", prevRemnDbKtEntity.getAnlPlan3())); //$NON-NLS-1$
      else
        remnDbKtCriteria.add(Restrictions.isNull("AnlPlan3")); //$NON-NLS-1$
      if (prevRemnDbKtEntity.getAnlPlan4() != null)
        remnDbKtCriteria.add(Restrictions.eq("AnlPlan4", prevRemnDbKtEntity.getAnlPlan4())); //$NON-NLS-1$
      else
        remnDbKtCriteria.add(Restrictions.isNull("AnlPlan4")); //$NON-NLS-1$
      if (prevRemnDbKtEntity.getAnlPlan5() != null)
        remnDbKtCriteria.add(Restrictions.eq("AnlPlan5", prevRemnDbKtEntity.getAnlPlan5())); //$NON-NLS-1$
      else
        remnDbKtCriteria.add(Restrictions.isNull("AnlPlan5")); //$NON-NLS-1$
      List<Integer> remnIDs = ormTemplateInst.findByCriteria(remnDbKtCriteria);

      if (remnIDs.isEmpty()) {
        RemnDbKt newDbKtEntity = initialize();
        newDbKtEntity.setAccPlan(account);
        newDbKtEntity.setPeriod(accPeriod);

        newDbKtEntity.setAnlPlan1(prevRemnDbKtEntity.getAnlPlan1());
        newDbKtEntity.setAnlPlan2(prevRemnDbKtEntity.getAnlPlan2());
        newDbKtEntity.setAnlPlan3(prevRemnDbKtEntity.getAnlPlan3());
        newDbKtEntity.setAnlPlan4(prevRemnDbKtEntity.getAnlPlan4());
        newDbKtEntity.setAnlPlan5(prevRemnDbKtEntity.getAnlPlan5());

        newDbKtEntity.setContractor(prevRemnDbKtEntity.getContractor());
        newDbKtEntity.setDocBaseType(prevRemnDbKtEntity.getDocBaseType());
        newDbKtEntity.setDocNumber(prevRemnDbKtEntity.getDocNumber());
        newDbKtEntity.setDocBaseNumber(prevRemnDbKtEntity.getDocBaseNumber());
        newDbKtEntity.setDocBaseDate(prevRemnDbKtEntity.getDocBaseDate());
        newDbKtEntity.setDocType(prevRemnDbKtEntity.getDocType());
        newDbKtEntity.setDocDate(prevRemnDbKtEntity.getDocDate());

        newDbKtEntity.setRemnBeginNatDb(prevRemnDbKtEntity.getRemnEndNatDb());
        newDbKtEntity.setRemnBeginNatKt(prevRemnDbKtEntity.getRemnEndNatKt());
        newDbKtEntity.setRemnBeginCurDb(prevRemnDbKtEntity.getRemnEndCurDb());
        newDbKtEntity.setRemnBeginCurKt(prevRemnDbKtEntity.getRemnEndCurKt());

        getPersistentManager().persist(newDbKtEntity);
      } else {
        RemnDbKt updDbKtEntity = load(remnIDs.get(0));

        updDbKtEntity.setRemnBeginNatDb(prevRemnDbKtEntity.getRemnEndNatDb());
        updDbKtEntity.setRemnBeginNatKt(prevRemnDbKtEntity.getRemnEndNatKt());
        updDbKtEntity.setRemnBeginCurDb(prevRemnDbKtEntity.getRemnEndCurDb());
        updDbKtEntity.setRemnBeginCurKt(prevRemnDbKtEntity.getRemnEndCurKt());

        getPersistentManager().merge(updDbKtEntity);
      }
    }
  }

  private void setEmptyValues(RemnDbKt entity) {
    if (entity.getRemnBeginNatDb() == null) {
      entity.setRemnBeginNatDb(new BigDecimal(0));
    }
    if (entity.getRemnBeginNatKt() == null) {
      entity.setRemnBeginNatKt(new BigDecimal(0));
    }
    if (entity.getRemnBeginCurDb() == null) {
      entity.setRemnBeginCurDb(new BigDecimal(0));
    }
    if (entity.getRemnBeginCurKt() == null) {
      entity.setRemnBeginCurKt(new BigDecimal(0));
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(RemnDbKt entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(RemnDbKt entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, RemnDbKt entity) {
    super.onValidate(context, entity);
    context.addRule(new MandatoryAttribute(entity, "Contractor")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getQueryNameDeleteEmptyStrings()
   */
  @Override
  protected String getQueryNameRemoveEmptyRecords() {
    return "Account.RemnDbKt.removeEmptyRecords"; //$NON-NLS-1$
  }

}
