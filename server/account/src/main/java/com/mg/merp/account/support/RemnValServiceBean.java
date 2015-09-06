/*
 * RemnValServiceBean.java
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
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.RemnValServiceLocal;
import com.mg.merp.account.generic.RemnServiceBean;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlForm;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnVal;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Остатки и обороты по ТМЦ бух. учета"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnValServiceBean.java,v 1.11 2009/03/18 10:19:27 sharapov Exp $
 */
@Stateless(name = "merp/account/RemnValService") //$NON-NLS-1$
public class RemnValServiceBean extends RemnServiceBean<RemnVal, Integer> implements RemnValServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getAllAccountsList()
   */
  @Override
  protected AccPlan[] getAllAccountsList() {
    List<AccPlan> accounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccPlan.class)
        .add(Restrictions.disjunction(
            Restrictions.between("AnlForm", AnlForm.CALCCOST, AnlForm.MBP), //$NON-NLS-1$
            Restrictions.eq("AnlForm", AnlForm.BASEMEANS), //$NON-NLS-1$
            Restrictions.eq("AnlForm", AnlForm.REALISARION)))); //$NON-NLS-1$
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

    // delete lines with empty turnover
    OrmTemplate.getInstance().bulkUpdate("delete from RemnVal r where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID) and (not exists (select s from EconomicSpec s where (s.RemnValDb.Id = r.id) or (s.RemnValKt.Id = r.id) ))", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // update begin remains with zero
    OrmTemplate.getInstance().bulkUpdate("update RemnVal r set r.RemnBeginNat = 0, r.BeginQuan = 0, r.RemnBeginCur = 0 where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID)", new String[]{"periodID", "accountID"}, new Object[]{accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    List<Integer> pervRemnIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(RemnVal.class)
        .setProjection(Projections.property("Id")) //$NON-NLS-1$
        .add(Restrictions.and(
            Restrictions.eq("Period", prevPeriod), //$NON-NLS-1$
            Restrictions.eq("AccPlan", account))));  //$NON-NLS-1$

    for (Integer pervRemnId : pervRemnIDs) {
      RemnVal prevRemnValEntity = load(pervRemnId);
      getPersistentManager().refresh(prevRemnValEntity);

      Criteria remnValCriteria = OrmTemplate.createCriteria(RemnVal.class)
          .setProjection(Projections.property("Id")) //$NON-NLS-1$
          .add(Restrictions.conjunction(
              Restrictions.eq("Period", accPeriod),  //$NON-NLS-1$
              Restrictions.eq("AccPlan", account),  //$NON-NLS-1$
              Restrictions.eq("Catalog", prevRemnValEntity.getCatalog()))); //$NON-NLS-1$

      if (prevRemnValEntity.getAnlPlan1() != null)
        remnValCriteria.add(Restrictions.eq("AnlPlan1", prevRemnValEntity.getAnlPlan1())); //$NON-NLS-1$
      else
        remnValCriteria.add(Restrictions.isNull("AnlPlan1")); //$NON-NLS-1$
      if (prevRemnValEntity.getAnlPlan2() != null)
        remnValCriteria.add(Restrictions.eq("AnlPlan2", prevRemnValEntity.getAnlPlan2())); //$NON-NLS-1$
      else
        remnValCriteria.add(Restrictions.isNull("AnlPlan2")); //$NON-NLS-1$
      if (prevRemnValEntity.getAnlPlan3() != null)
        remnValCriteria.add(Restrictions.eq("AnlPlan3", prevRemnValEntity.getAnlPlan3())); //$NON-NLS-1$
      else
        remnValCriteria.add(Restrictions.isNull("AnlPlan3")); //$NON-NLS-1$
      if (prevRemnValEntity.getAnlPlan4() != null)
        remnValCriteria.add(Restrictions.eq("AnlPlan4", prevRemnValEntity.getAnlPlan4())); //$NON-NLS-1$
      else
        remnValCriteria.add(Restrictions.isNull("AnlPlan4")); //$NON-NLS-1$
      if (prevRemnValEntity.getAnlPlan5() != null)
        remnValCriteria.add(Restrictions.eq("AnlPlan5", prevRemnValEntity.getAnlPlan5())); //$NON-NLS-1$
      else
        remnValCriteria.add(Restrictions.isNull("AnlPlan5")); //$NON-NLS-1$
      List<Integer> remnIDs = ormTemplateInst.findByCriteria(remnValCriteria);

      if (remnIDs.isEmpty()) {
        RemnVal newRemnValEntity = initialize();
        newRemnValEntity.setAccPlan(account);
        newRemnValEntity.setPeriod(accPeriod);

        newRemnValEntity.setAnlPlan1(prevRemnValEntity.getAnlPlan1());
        newRemnValEntity.setAnlPlan2(prevRemnValEntity.getAnlPlan2());
        newRemnValEntity.setAnlPlan3(prevRemnValEntity.getAnlPlan3());
        newRemnValEntity.setAnlPlan4(prevRemnValEntity.getAnlPlan4());
        newRemnValEntity.setAnlPlan5(prevRemnValEntity.getAnlPlan5());

        newRemnValEntity.setCatalog(prevRemnValEntity.getCatalog());
        newRemnValEntity.setContractor(prevRemnValEntity.getContractor());
        newRemnValEntity.setBatchId(prevRemnValEntity.getBatchId());

        newRemnValEntity.setRemnBeginNat(prevRemnValEntity.getRemnEndNat());
        newRemnValEntity.setRemnBeginCur(prevRemnValEntity.getRemnEndCur());
        newRemnValEntity.setBeginQuan(prevRemnValEntity.getEndQuan());

        getPersistentManager().persist(newRemnValEntity);
      } else {
        RemnVal updRemnValEntity = load(remnIDs.get(0));

        updRemnValEntity.setRemnBeginNat(prevRemnValEntity.getRemnEndNat());
        updRemnValEntity.setRemnBeginCur(prevRemnValEntity.getRemnEndCur());
        updRemnValEntity.setBeginQuan(prevRemnValEntity.getEndQuan());

        getPersistentManager().merge(updRemnValEntity);
      }
    }
  }

  private void setEmptyValues(RemnVal entity) {
    if (entity.getRemnBeginCur() == null) {
      entity.setRemnBeginCur(new BigDecimal(0));
    }
    if (entity.getRemnBeginNat() == null) {
      entity.setRemnBeginNat(new BigDecimal(0));
    }
    if (entity.getBeginQuan() == null) {
      entity.setBeginQuan(new BigDecimal(0));
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(RemnVal entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(RemnVal entity) {
    setEmptyValues(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, RemnVal entity) {
    super.onValidate(context, entity);
    context.addRule(new MandatoryAttribute(entity, "Contractor")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Catalog"));     //$NON-NLS-1$
  }

  /**
   * Не реализован
   */
  public byte[] loadBatchBrowse(int remnId) throws ApplicationException {
    //TODO
    //return ((RemnValDomainImpl) getDomain()).loadBatchBrowse(remnId);
    return null;
  }

  /**
   * Не реализован
   */
  public void evaluateOutCost(int[] keys) throws ApplicationException {
    //TODO
    //((RemnValDomainImpl) getDomain()).evaluateOutCost(keys);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.generic.RemnServiceBean#getQueryTextDeleteEmptyStrings()
   */
  @Override
  protected String getQueryNameRemoveEmptyRecords() {
    return "Account.RemnVal.removeEmptyRecords"; //$NON-NLS-1$
  }

}
