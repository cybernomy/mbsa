/*
 * RemnAnlServiceBean.java
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

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.merp.account.RemnAnlServiceLocal;
import com.mg.merp.account.generic.RemnServiceBean;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnAnl;

/**
 * Реализация бизнес-компонента "Остатки и обороты по аналитическим счетам бух. учета"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnAnlServiceBean.java,v 1.9 2009/03/18 10:19:27 sharapov Exp $
 */
@Stateless(name="merp/account/RemnAnlService") //$NON-NLS-1$
public class RemnAnlServiceBean extends RemnServiceBean<RemnAnl, Integer> implements RemnAnlServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.account.generic.RemnServiceBean#getAllAccountsList()
	 */
	@Override
	protected AccPlan[] getAllAccountsList() {
		List<AccPlan> accounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccPlan.class)
				.add(Restrictions.eq("IsAnl", true))); //$NON-NLS-1$
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
				.addOrder(Order.desc("DateFrom")));  //$NON-NLS-1$
		Period prevPeriod = prevPeriods.get(0);
		
		// delete lines with empty turnover
		OrmTemplate.getInstance().bulkUpdate("delete from RemnAnl r where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID) and (not exists (select s from EconomicSpec s where (s.RemnAnlDb.Id = r.id) or (s.RemnAnlKt.Id = r.id) ))", new String[] {"periodID", "accountID"}, new Object[] {accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// update begin remains with zero
		OrmTemplate.getInstance().bulkUpdate("update RemnAnl r set r.RemnBeginNatDb = 0, r.RemnBeginNatKt = 0, r.RemnBeginCurDb = 0, r.RemnBeginCurKt = 0 where (r.Period.Id = :periodID) and (r.AccPlan.Id = :accountID)", new String[] {"periodID", "accountID"}, new Object[] {accPeriod.getId(), account.getId()}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		List<Integer> pervRemnIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(RemnAnl.class)
				.setProjection(Projections.property("Id")) //$NON-NLS-1$
				.add(Restrictions.and(Restrictions.eq("Period", prevPeriod), Restrictions.eq("AccPlan", account))));  //$NON-NLS-1$ //$NON-NLS-2$

		for (Integer pervRemnId : pervRemnIDs) {
			RemnAnl prevRemnAnlEntity = load(pervRemnId);
			getPersistentManager().refresh(prevRemnAnlEntity);

			Criteria remnAnlCriteria = OrmTemplate.createCriteria(RemnAnl.class)
				.setProjection(Projections.property("Id")) //$NON-NLS-1$
				.add(Restrictions.and(Restrictions.eq("Period", accPeriod), Restrictions.eq("AccPlan", account)));  //$NON-NLS-1$ //$NON-NLS-2$

			if(prevRemnAnlEntity.getAnlPlan1() != null)
				remnAnlCriteria.add(Restrictions.eq("AnlPlan1", prevRemnAnlEntity.getAnlPlan1())); //$NON-NLS-1$
			else
				remnAnlCriteria.add(Restrictions.isNull("AnlPlan1")); //$NON-NLS-1$
			if(prevRemnAnlEntity.getAnlPlan2() != null)
				remnAnlCriteria.add(Restrictions.eq("AnlPlan2", prevRemnAnlEntity.getAnlPlan2()));  //$NON-NLS-1$
			else
				remnAnlCriteria.add(Restrictions.isNull("AnlPlan2")); //$NON-NLS-1$
			if(prevRemnAnlEntity.getAnlPlan3() != null)
				remnAnlCriteria.add(Restrictions.eq("AnlPlan3", prevRemnAnlEntity.getAnlPlan3()));  //$NON-NLS-1$
			else
				remnAnlCriteria.add(Restrictions.isNull("AnlPlan3")); //$NON-NLS-1$
			if(prevRemnAnlEntity.getAnlPlan4() != null)
				remnAnlCriteria.add(Restrictions.eq("AnlPlan4", prevRemnAnlEntity.getAnlPlan4()));  //$NON-NLS-1$
			else
				remnAnlCriteria.add(Restrictions.isNull("AnlPlan4")); //$NON-NLS-1$
			if(prevRemnAnlEntity.getAnlPlan5() != null)
				remnAnlCriteria.add(Restrictions.eq("AnlPlan5", prevRemnAnlEntity.getAnlPlan5())); //$NON-NLS-1$
			else
				remnAnlCriteria.add(Restrictions.isNull("AnlPlan5")); //$NON-NLS-1$
			List<Integer> remnIDs = ormTemplateInst.findByCriteria(remnAnlCriteria);

			if(remnIDs.isEmpty()) {
				RemnAnl newRemnAnlEntity = initialize();
				newRemnAnlEntity.setAccPlan(account);
				newRemnAnlEntity.setPeriod(accPeriod);

				newRemnAnlEntity.setAnlPlan1(prevRemnAnlEntity.getAnlPlan1());
				newRemnAnlEntity.setAnlPlan2(prevRemnAnlEntity.getAnlPlan2());
				newRemnAnlEntity.setAnlPlan3(prevRemnAnlEntity.getAnlPlan3());
				newRemnAnlEntity.setAnlPlan4(prevRemnAnlEntity.getAnlPlan4());
				newRemnAnlEntity.setAnlPlan5(prevRemnAnlEntity.getAnlPlan5());

				newRemnAnlEntity.setRemnBeginNatDb(prevRemnAnlEntity.getRemnEndNatDb());
				newRemnAnlEntity.setRemnBeginNatKt(prevRemnAnlEntity.getRemnEndNatKt());
				newRemnAnlEntity.setRemnBeginCurDb(prevRemnAnlEntity.getRemnEndCurDb());
				newRemnAnlEntity.setRemnBeginCurKt(prevRemnAnlEntity.getRemnEndCurKt());

				getPersistentManager().persist(newRemnAnlEntity);
			}
			else {
				RemnAnl updRemnAnlEntity = load(remnIDs.get(0));

				updRemnAnlEntity.setRemnBeginNatDb(prevRemnAnlEntity.getRemnEndNatDb());
				updRemnAnlEntity.setRemnBeginNatKt(prevRemnAnlEntity.getRemnEndNatKt());
				updRemnAnlEntity.setRemnBeginCurDb(prevRemnAnlEntity.getRemnEndCurDb());
				updRemnAnlEntity.setRemnBeginCurKt(prevRemnAnlEntity.getRemnEndCurKt());

				getPersistentManager().merge(updRemnAnlEntity);
			}
		}
	}

	private void setEmptyValues(RemnAnl entity) {
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
	protected void onCreate(RemnAnl entity) {
		setEmptyValues(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(RemnAnl entity) {
		setEmptyValues(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.generic.RemnServiceBean#getQueryNameDeleteEmptyStrings()
	 */
	@Override
	protected String getQueryNameRemoveEmptyRecords() {
		return "Account.RemnAnl.removeEmptyRecords"; //$NON-NLS-1$
	}

}
