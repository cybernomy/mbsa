/*
 * RemnAccServiceBean.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.merp.account.RemnAccServiceLocal;
import com.mg.merp.account.generic.RemnServiceBean;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnAcc;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Остатки и обороты по счетам бух. учета"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnAccServiceBean.java,v 1.10 2009/03/18 10:19:27 sharapov Exp $
 */
@Stateless(name = "merp/account/RemnAccService") //$NON-NLS-1$
public class RemnAccServiceBean extends RemnServiceBean<RemnAcc, Integer> implements RemnAccServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getAllAccountsList()
   */
  @Override
  protected AccPlan[] getAllAccountsList() {
    List<AccPlan> accounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccPlan.class).add(Restrictions.isNotNull("Id"))); //$NON-NLS-1$
    return accounts.toArray(new AccPlan[accounts.size()]);
  }

  /*
   * (non-Javadoc)
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

    // delete lines with empty turnover
    OrmTemplate.getInstance().bulkUpdate("delete from RemnAcc r where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID) and (not exists (select s from EconomicSpec s where (s.RemnAccDb.Id = r.id) or (s.RemnAccKt.Id = r.id) ))", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // update begin remains with zero
    OrmTemplate.getInstance().bulkUpdate("update RemnAcc r set r.RemnBeginNatDb = 0, r.RemnBeginNatKt = 0, r.RemnBeginCurDb = 0, r.RemnBeginCurKt = 0 where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID)", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    List<Integer> remnIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(RemnAcc.class)
        .setProjection(Projections.property("Id")) //$NON-NLS-1$
        .add(Restrictions.and(Restrictions.eq("Period", accPeriod), Restrictions.eq("AccPlan", account))));    //$NON-NLS-1$ //$NON-NLS-2$
    List<Integer> prevRemnIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(RemnAcc.class)
        .setProjection(Projections.property("Id")) //$NON-NLS-1$
        .add(Restrictions.and(Restrictions.eq("Period", prevPeriod), Restrictions.eq("AccPlan", account)))); //$NON-NLS-1$ //$NON-NLS-2$
    if (!prevRemnIDs.isEmpty()) {
      Integer prevRemnId = prevRemnIDs.get(0);
      RemnAcc remnAccEntity = load(prevRemnId);
      getPersistentManager().refresh(remnAccEntity);

      if (remnIDs.isEmpty()) {
        RemnAcc newRemnAccEntity = this.initialize();
        newRemnAccEntity.setPeriod(accPeriod);
        newRemnAccEntity.setAccPlan(account);

        newRemnAccEntity.setRemnBeginNatDb(remnAccEntity.getRemnEndNatDb());
        newRemnAccEntity.setRemnBeginNatKt(remnAccEntity.getRemnEndNatKt());
        newRemnAccEntity.setRemnBeginCurDb(remnAccEntity.getRemnEndCurDb());
        newRemnAccEntity.setRemnBeginCurKt(remnAccEntity.getRemnEndCurKt());

        getPersistentManager().persist(newRemnAccEntity);
      } else {
        RemnAcc updRemnAccEntity = load(remnIDs.get(0));
        updRemnAccEntity.setRemnBeginNatDb(remnAccEntity.getRemnEndNatDb());
        updRemnAccEntity.setRemnBeginNatKt(remnAccEntity.getRemnEndNatKt());
        updRemnAccEntity.setRemnBeginCurDb(remnAccEntity.getRemnEndCurDb());
        updRemnAccEntity.setRemnBeginCurKt(remnAccEntity.getRemnEndCurKt());

        getPersistentManager().merge(updRemnAccEntity);
      }
    }
  }

  private void setEmptyValues(RemnAcc entity) {
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
  protected void onCreate(RemnAcc entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(RemnAcc entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.RemnAccServiceLocal#overestimationCurAcc(com.mg.framework.api.AttributeMap)
   */
  public void overestimationCurAcc(AttributeMap rec) throws ApplicationException {
    //TODO
    //((RemnAccDomainImpl) getDomain()).overestimationCurAcc(rec);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getQueryTextDeleteEmptyStrings()
   */
  @Override
  protected String getQueryNameRemoveEmptyRecords() {
    return "Account.RemnAcc.removeEmptyRecords"; //$NON-NLS-1$
  }

}
