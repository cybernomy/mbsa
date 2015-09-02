/*
 * TurnAccFlatServiceBean.java
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

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.finance.TurnAccFlatServiceLocal;
import com.mg.merp.finance.TurnFeatureServiceLocal;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.FinPeriod;
import com.mg.merp.finance.model.TurnAccount;
import com.mg.merp.finance.model.TurnFeature;

/**
 * Реализация бизнес-компонента "Остатки и обороты по счетам финансового учета"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: TurnAccFlatServiceBean.java,v 1.10 2008/02/12 14:25:07 alikaev Exp $
 */
@Stateless(name="merp/finance/TurnAccFlatService") //$NON-NLS-1$
public class TurnAccFlatServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<TurnAccount, Integer> implements TurnAccFlatServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.finance.TurnAccFlatServiceLocal#carryForward(com.mg.merp.finance.model.FinPeriod, com.mg.merp.finance.model.FinPeriod, boolean, java.util.List)
	 */
	public void carryForward(FinPeriod periodFrom, FinPeriod periodTo, boolean allAcc, Account[] accList) {
		internalCarryForward(periodFrom, periodTo, allAcc, accList);
	}

	/**
	 * Перенос остатков финансового учета
	 * @param periodFrom - с фин.периода
	 * @param periodTo - по фин.период
	 * @param allAcc - по всем счетам
	 * @param accList - список выбранных счетов
	 */
	protected void internalCarryForward (FinPeriod periodFrom, FinPeriod periodTo, boolean allAcc, Account[] accList) {
		Messages errorMsg = Messages.getInstance();
		if(periodFrom == null || periodTo == null)
			throw new BusinessException(errorMsg.getMessage(Messages.TURNACC_INVALID_PERIOD_RANGE));

		List<FinPeriod> finPeriods = getFinPeriodsRange(periodFrom, periodTo);

		if(finPeriods.isEmpty())
			throw new BusinessException(errorMsg.getMessage(Messages.TURNACC_INVALID_PERIOD_RANGE));

		if(allAcc) 
			accList = getAllAccountsList(); 

		if(accList == null)	
			throw new BusinessException(errorMsg.getMessage(Messages.TURNACC_INVALID_ACCOUNTS_LIST));

		for (Account account : accList) {
			for (FinPeriod period : finPeriods) {
				if(period.getDateClose() != null)
					throw new BusinessException(errorMsg.getMessage(Messages.PERIOD_CLOSED));
				else
					carryForwardBalance(period, account);
			}
		}
	}

	/**
	 * Получить диапазон фин.периодов подлежащий переносу остатков
	 * @param periodFrom - с фин.периода
	 * @param periodTo - по фин.период
	 * @return диапазон фин.периодов подлежащий переносу остатков
	 */
	protected List<FinPeriod> getFinPeriodsRange(FinPeriod periodFrom, FinPeriod periodTo) {
		List<FinPeriod> finPeriods = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FinPeriod.class)
				.add(Restrictions.conjunction(Restrictions.gt("DateFrom", periodFrom.getDateFrom()), Restrictions.le("DateFrom", periodTo.getDateFrom()))) //$NON-NLS-1$ //$NON-NLS-2$
				.addOrder(Order.asc("DateFrom"))); //$NON-NLS-1$
		return finPeriods;
	}

	/**
	 * Получить список всех счетов финансового учета 
	 * @return список всех счетов финансового учета
	 */
	protected Account[] getAllAccountsList() {
		List<Account> accounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Account.class).add(Restrictions.isNotNull("Id"))); //$NON-NLS-1$
		return accounts.toArray(new Account[accounts.size()]);
	}

	/**
	 * Перенос остатков
	 * @param finPeriod - фин.период
	 * @param account - фин.счет
	 */
	protected void carryForwardBalance(FinPeriod finPeriod, Account account) {
		OrmTemplate ormTemplateInst = OrmTemplate.getInstance();
		// find previous period, may be several, get first
		List<FinPeriod> prevPeriods = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(FinPeriod.class)
				.add(Restrictions.lt("DateTo", finPeriod.getDateFrom())) //$NON-NLS-1$
				.addOrder(Order.desc("DateFrom"))); //$NON-NLS-1$
		FinPeriod prevPeriod = prevPeriods.get(0);
		// delete lines with empty turnover
		/*
			List<Integer> taIds = MiscUtils.convertUncheckedList(Integer.class, OrmTemplate.getInstance().findByNamedParam("select ta.id from TurnAccount as ta where (ta.Account.id = :accountID) and (ta.Period.id = :periodID) and (not exists (select s from Specification s where (s.SrcTurnAcc.id = ta.id) or (s.DstTurnAcc.id = ta.id) ))", new String[] {"periodID", "accountID"}, new Object[] {prevPeriod.getId(), account.getId()}));
			for (Integer taId : taIds) {
				OrmTemplate.getInstance().bulkUpdate("delete from TurnFeature tf where (tf.TurnAccount.id = :ta_id) and (not exists (select s from Specification s where (s.SrcTurnFeat.id = tf.id) or (s.DstTurnFeat.id = tf.id) ))", "ta_id", taId);
				OrmTemplate.getInstance().bulkUpdate("delete from TurnAccount ta where (ta.id = :ta_id) and (not exists (select tf from TurnFeature tf where (tf.TurnAccount.id = :ta_id)))", "ta_id", taId);
			}
		 */
		// update opening balances with zero 
		/*
			List<Integer> taIDs = MiscUtils.convertUncheckedList(Integer.class, OrmTemplate.getInstance().findByNamedParam("select ta.id from TurnAccount ta where (ta.Period.id = :periodID) and (ta.Account.id = :accountID)", new String[] {"periodID", "accountID"}, new Object[] {prevPeriod.getId(), account.getId()}));
			for (Integer taId : taIDs) {
				OrmTemplate.getInstance().bulkUpdate("update TurnFeature tf set tf.RemnBegCur = 0, tf.RemnBegNat = 0, tf.RemnBegCurPlan = 0, tf.RemnBegNatPlan = 0 where (tf.TurnAccount.id = :ta_id)", "ta_id", taId);
				OrmTemplate.getInstance().bulkUpdate("update TurnAccount ta set ta.RemnBegCur = 0, ta.RemnBegNat = 0, ta.RemnBegCurPlan = 0, ta.RemnBegNatPlan = 0 where (ta.id = :ta_id)", "ta_id", taId);
			}
		 */
		// carry forward 
		List<Integer> prevTaId = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(TurnAccount.class)
				.setProjection(Projections.property("Id")) //$NON-NLS-1$
				.add(Restrictions.and(Restrictions.eq("Period", prevPeriod), Restrictions.eq("Account", account)))); //$NON-NLS-1$ //$NON-NLS-2$
		if (prevTaId != null && !prevTaId.isEmpty()) {
			Integer prevTaID = prevTaId.get(0);	
			// TurnAccount load 
			TurnAccount ta = load(prevTaID);
			getPersistentManager().refresh(ta);
			// find corresponding TurnAccount entity in current period

			Criteria turnAccountCriteria = OrmTemplate.createCriteria(TurnAccount.class)
			.setProjection(Projections.property("Id")) //$NON-NLS-1$
			.add(Restrictions.conjunction(Restrictions.eq("Period", finPeriod), Restrictions.eq("Account", account))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			if(ta.getAnalytics1() != null)
				turnAccountCriteria.add(Restrictions.eq("Analytics1", ta.getAnalytics1())); //$NON-NLS-1$
			else
				turnAccountCriteria.add(Restrictions.isNull("Analytics1")); //$NON-NLS-1$
			if(ta.getAnalytics2() != null)
				turnAccountCriteria.add(Restrictions.eq("Analytics2", ta.getAnalytics2())); //$NON-NLS-1$
			else
				turnAccountCriteria.add(Restrictions.isNull("Analytics2")); //$NON-NLS-1$
			if(ta.getAnalytics3() != null)
				turnAccountCriteria.add(Restrictions.eq("Analytics3", ta.getAnalytics3())); //$NON-NLS-1$
			else
				turnAccountCriteria.add(Restrictions.isNull("Analytics3")); //$NON-NLS-1$
			if(ta.getAnalytics4() != null)
				turnAccountCriteria.add(Restrictions.eq("Analytics4", ta.getAnalytics4())); //$NON-NLS-1$
			else
				turnAccountCriteria.add(Restrictions.isNull("Analytics4")); //$NON-NLS-1$
			if(ta.getAnalytics5() != null)
				turnAccountCriteria.add(Restrictions.eq("Analytics5", ta.getAnalytics5())); //$NON-NLS-1$
			else
				turnAccountCriteria.add(Restrictions.isNull("Analytics5")); //$NON-NLS-1$
			List<java.lang.Integer> taids = ormTemplateInst.findByCriteria(turnAccountCriteria);

			if(taids.isEmpty()) {
				TurnAccount taEntity = this.initialize();
				taEntity.setAccount(account);
				taEntity.setPeriod(finPeriod);
				taEntity.setCurCode(ta.getCurCode());

				taEntity.setAnalytics1(ta.getAnalytics1());
				taEntity.setAnalytics2(ta.getAnalytics2());
				taEntity.setAnalytics3(ta.getAnalytics3());
				taEntity.setAnalytics4(ta.getAnalytics4());
				taEntity.setAnalytics5(ta.getAnalytics5());

				taEntity.setRemnBegCur(ta.getRemnEndCur());
				taEntity.setRemnBegNat(ta.getRemnEndNat());
				taEntity.setRemnBegCurPlan(ta.getRemnEndCurPlan());
				taEntity.setRemnBegNatPlan(ta.getRemnEndNatPlan());

				getPersistentManager().persist(taEntity);
				taids.add(taEntity.getId());
			}
			else {
				TurnAccount taEntityUpd = load(taids.get(0));
				taEntityUpd.setRemnBegCur(ta.getRemnEndCur());
				taEntityUpd.setRemnBegNat(ta.getRemnEndNat());
				taEntityUpd.setRemnBegCurPlan(ta.getRemnEndCurPlan());
				taEntityUpd.setRemnBegNatPlan(ta.getRemnEndNatPlan());

				getPersistentManager().merge(taEntityUpd);
			}
			// carry forward TurnFeature
			List<Integer> prevTFIDs = ormTemplateInst.findByCriteria(OrmTemplate.createCriteria(TurnFeature.class)
					.setProjection(Projections.property("Id")) //$NON-NLS-1$
					.add(Restrictions.eq("TurnAccount.Id", prevTaID))); //$NON-NLS-1$
			for (Integer prevTFId : prevTFIDs) {
				if(!prevTFIDs.isEmpty()) {
					// TurnFeature load
					TurnFeature tf = getPersistentManager().find(TurnFeature.class, prevTFId);
					getPersistentManager().refresh(tf);
					// find corresponding TurnFeature entity in current period
					Criteria turnFeatureCriteria = OrmTemplate.createCriteria(TurnFeature.class)
					.setProjection(Projections.property("Id")) //$NON-NLS-1$
					.add(Restrictions.conjunction(
							Restrictions.eq("Period", finPeriod),  //$NON-NLS-1$
							Restrictions.eq("TurnAccount", load(taids.get(0))), //account $NON-NLS-1$ 
							Restrictions.eq("Feature", tf.getFeature())));  //$NON-NLS-1$

					if(tf.getFeatureAnalytics1() != null)
						turnFeatureCriteria.add(Restrictions.eq("FeatureAnalytics1", tf.getFeatureAnalytics1())); //$NON-NLS-1$
					else
						turnFeatureCriteria.add(Restrictions.isNull("FeatureAnalytics1")); //$NON-NLS-1$
					if(tf.getFeatureAnalytics2() != null)
						turnFeatureCriteria.add(Restrictions.eq("FeatureAnalytics2", tf.getFeatureAnalytics2())); //$NON-NLS-1$
					else
						turnFeatureCriteria.add(Restrictions.isNull("FeatureAnalytics2")); //$NON-NLS-1$
					if(tf.getFeatureAnalytics3() != null)
						turnFeatureCriteria.add(Restrictions.eq("FeatureAnalytics3", tf.getFeatureAnalytics3())); //$NON-NLS-1$
					else
						turnFeatureCriteria.add(Restrictions.isNull("FeatureAnalytics3")); //$NON-NLS-1$
					if(tf.getFeatureAnalytics4() != null)
						turnFeatureCriteria.add(Restrictions.eq("FeatureAnalytics4", tf.getFeatureAnalytics4())); //$NON-NLS-1$
					else
						turnFeatureCriteria.add(Restrictions.isNull("FeatureAnalytics4")); //$NON-NLS-1$
					if(tf.getFeatureAnalytics5() != null)
						turnFeatureCriteria.add(Restrictions.eq("FeatureAnalytics5", tf.getFeatureAnalytics5())); //$NON-NLS-1$
					else
						turnFeatureCriteria.add(Restrictions.isNull("FeatureAnalytics5")); //$NON-NLS-1$
					List<Integer> tfIds = ormTemplateInst.findByCriteria(turnFeatureCriteria);

					if(tfIds.isEmpty()) {
						TurnFeatureServiceLocal turnFeatureService = (TurnFeatureServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/TurnFeature"); //$NON-NLS-1$
						TurnFeature tfEntity = turnFeatureService.initialize();

						tfEntity.setTurnAccount(load(taids.get(0)));
						tfEntity.setPeriod(finPeriod);
						tfEntity.setCurCode(tf.getCurCode());
						tfEntity.setFeature(tf.getFeature());

						tfEntity.setAnalytics1(tf.getAnalytics1());
						tfEntity.setAnalytics2(tf.getAnalytics2());
						tfEntity.setAnalytics3(tf.getAnalytics3());
						tfEntity.setAnalytics4(tf.getAnalytics4());
						tfEntity.setAnalytics5(tf.getAnalytics5());

						tfEntity.setRemnBegCur(tf.getRemnEndCur());
						tfEntity.setRemnBegNat(tf.getRemnEndNat());
						tfEntity.setRemnBegCurPlan(tf.getRemnEndCurPlan());
						tfEntity.setRemnBegNatPlan(tf.getRemnEndNatPlan());

						getPersistentManager().merge(tfEntity);
					}
					else {
						TurnFeatureServiceLocal turnFeatureService = (TurnFeatureServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/TurnFeature"); //$NON-NLS-1$
						TurnFeature tfEntityUpd = turnFeatureService.load(tfIds.get(0));
						tfEntityUpd.setRemnBegCur(tf.getRemnEndCur());
						tfEntityUpd.setRemnBegNat(tf.getRemnEndNat());
						tfEntityUpd.setRemnBegCurPlan(tf.getRemnEndCurPlan());
						tfEntityUpd.setRemnBegNatPlan(tf.getRemnEndNatPlan());

						getPersistentManager().merge(tfEntityUpd);
					}
				}
			}
		}
	}
}
