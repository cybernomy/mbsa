/*
 * PayRollServiceBean.java
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

package com.mg.merp.salary.support;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.personnelref.model.CalcPeriod;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PersonnelConfig;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.PayRollServiceLocal;
import com.mg.merp.salary.PaySheetServiceLocal;
import com.mg.merp.salary.PaySheetSpecServiceLocal;
import com.mg.merp.salary.model.CalcList;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.FeeSummaryHead;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.PaySheet;
import com.mg.merp.salary.model.PaySheetSpec;
import com.mg.merp.salary.model.RollKind;

/**
 * Реализация бизнес-компонента "Расчетные ведомости" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PayRollServiceBean.java,v 1.6 2007/08/27 06:19:40 sharapov Exp $
 */
@Stateless(name="merp/salary/PayRollService") //$NON-NLS-1$
public class PayRollServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PayRoll, Integer> implements PayRollServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PayRoll entity) {
		context.addRule(new MandatoryAttribute(entity, "Number")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CalcPeriod")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "RollKind")); //$NON-NLS-1$
	}
		
	/* (non-Javadoc)
	 * @see com.mg.merp.salary.PayRollServiceLocal#createPaySheet(java.lang.Integer)
	 */
	public PaySheet createPaySheet(Integer payRollId) {
		return doCreatePaySheetByPayRoll(payRollId);
	}
		
	/* (non-Javadoc)
	 * @see com.mg.merp.salary.PayRollServiceLocal#createFeeSummary(java.io.Serializable[], com.mg.merp.document.model.DocHeadModel)
	 */
	public FeeSummaryHead createFeeSummary(Serializable[] payRollIds, DocHeadModel feeSummaryPattern) {
		return doCreateFeeSummary(payRollIds, feeSummaryPattern);
	}
	
	/**
	 * Создать свод начислений/удержаний по аналитике 
	 * @param payRollIds - список идентификаторов расчетных ведомостей
	 * @param feeSummaryPattern - образец свода начислений/удержаний по аналитике
	 * @return свод начислений/удержаний по аналитике
	 */
	protected FeeSummaryHead doCreateFeeSummary(Serializable[] payRollIds, DocHeadModel feeSummaryPattern) {
		List<CostAnaliticsInfo> agregateListOfAnalitics = getAgregateListOfAnalitics(payRollIds);
		if(agregateListOfAnalitics.isEmpty())
			return null;
		
		CreateFeeSummarySpecInfo[] feeSummarySpecs = new CreateFeeSummarySpecInfo[agregateListOfAnalitics.size()];  
		BigDecimal docSum = getDocSumAndPrepareFeeSummarySpecs(agregateListOfAnalitics, feeSummarySpecs);
		
		PayRoll payRoll = null;
		if(payRollIds.length == 1)
			payRoll = getPersistentManager().find(PayRoll.class, payRollIds[0]);
			
		FeeSummaryHead feeSummaryHead = initializeAndCreateFeeSummaryHead(feeSummaryPattern, docSum, payRoll);
		ServerUtils.getPersistentManager().flush(); // to avoid HibernateException: this instance does not yet exist as a row in the database
		createFeeSummarySpecs(feeSummaryHead, feeSummarySpecs);
		
		return feeSummaryHead;
	}
	
	/**
	 * Получить сгруппированный по аналитике список сумм
	 * @param payRollIds - список идентификаторов расчетных ведомостей
	 * @return сгруппированный по аналитике список сумм
	 */
	protected List<CostAnaliticsInfo> getAgregateListOfAnalitics(Serializable[] payRollIds) {
		List<CostAnaliticsInfo> resultList = new ArrayList<CostAnaliticsInfo>();
		List<CostAnaliticsInfo> allFeesList = getAllFeesList(payRollIds);
		CostAnaliticsInfo prevAnlInfo = new CostAnaliticsInfo();
		int j = 0;
		for (int i = 0; i < allFeesList.size(); i++) {
			CostAnaliticsInfo currentAnlInfo = allFeesList.get(i);
				// если аналитика в следующей записи не совпадает с предыдущей
			if (currentAnlInfo.costsAnl1.getId() != prevAnlInfo.costsAnl1.getId()
			 || currentAnlInfo.costsAnl2.getId() != prevAnlInfo.costsAnl2.getId()
			 || currentAnlInfo.costsAnl3.getId() != prevAnlInfo.costsAnl3.getId()
			 || currentAnlInfo.costsAnl4.getId() != prevAnlInfo.costsAnl4.getId()
			 || currentAnlInfo.costsAnl5.getId() != prevAnlInfo.costsAnl5.getId()) {
				// то открыть новую сумму
				j++;
				resultList.add(new CostAnaliticsInfo(
												BigDecimal.ZERO,
												currentAnlInfo.costsAnl1,
												currentAnlInfo.costsAnl2,
												currentAnlInfo.costsAnl3,
												currentAnlInfo.costsAnl4,
												currentAnlInfo.costsAnl5));
			}
			
			resultList.get(j - 1).sum = resultList.get(j - 1).sum.add(currentAnlInfo.sum);
			
			prevAnlInfo.costsAnl1 = currentAnlInfo.costsAnl1;
			prevAnlInfo.costsAnl2 = currentAnlInfo.costsAnl2;
			prevAnlInfo.costsAnl3 = currentAnlInfo.costsAnl3;
			prevAnlInfo.costsAnl4 = currentAnlInfo.costsAnl4;
			prevAnlInfo.costsAnl5 = currentAnlInfo.costsAnl5;
		}
		return resultList;
	}
	
	protected List<CostAnaliticsInfo> getAllFeesList(Serializable[] payRollIds) {
		 return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFee.class)
				 	.setProjection(Projections.projectionList(
				 			Projections.property("Summa"), //$NON-NLS-1$
				 			Projections.property("CostsAnl1"), //$NON-NLS-1$
				 			Projections.property("CostsAnl2"), //$NON-NLS-1$
				 			Projections.property("CostsAnl3"), //$NON-NLS-1$
				 			Projections.property("CostsAnl4"), //$NON-NLS-1$
				 			Projections.property("CostsAnl5"))) //$NON-NLS-1$
				 	.createAlias("CalcListSection", "cls") //$NON-NLS-1$ //$NON-NLS-2$
				 	.createAlias("cls.CalcList", "cl") //$NON-NLS-1$ //$NON-NLS-2$
				 	.add(Restrictions.in("cl.PayRoll.Id", (Object[]) payRollIds)) //$NON-NLS-1$
				 	.addOrder(Order.asc("CostsAnl1")) //$NON-NLS-1$
				 	.addOrder(Order.asc("CostsAnl2")) //$NON-NLS-1$
				 	.addOrder(Order.asc("CostsAnl3")) //$NON-NLS-1$
				 	.addOrder(Order.asc("CostsAnl4")) //$NON-NLS-1$
				 	.addOrder(Order.asc("CostsAnl5")) //$NON-NLS-1$
				 	.setResultTransformer(new ResultTransformer<CostAnaliticsInfo>() {

				 		/* (non-Javadoc)
				 		 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
				 		 */
				 		public CostAnaliticsInfo transformTuple(Object[] tuple, String[] aliases) {
				 			return new CostAnaliticsInfo(
												(BigDecimal) tuple[0],
												tuple[1] == null ? new CostsAnl() : (CostsAnl) tuple[1],
												tuple[2] == null ? new CostsAnl() : (CostsAnl) tuple[2],
												tuple[3] == null ? new CostsAnl() : (CostsAnl) tuple[3],
												tuple[4] == null ? new CostsAnl() : (CostsAnl) tuple[4],
												tuple[4] == null ? new CostsAnl() : (CostsAnl) tuple[5]);
				 		}
				}));
	}
	
	/**
	 * Получить сумму документа и подготовить позиции спецификации для массового добавления
	 * @param agregateListOfAnalitics - сгруппированный по аналитике список сумм
	 * @param feeSummarySpecs - позиции спецификации(пустой список)
	 * @return сумма документа
	 */
	protected BigDecimal getDocSumAndPrepareFeeSummarySpecs(List<CostAnaliticsInfo> agregateListOfAnalitics, CreateFeeSummarySpecInfo[] feeSummarySpecs) {
		BigDecimal docSum = BigDecimal.ZERO;
		
		Catalog costAnalitics = getCostsAnlFromPersonnelModuleConfiguraition();
		if(costAnalitics == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PERSONEL_CONFIG_COSTS_ANL_NOT_FOUND));
			
		for (int i = 0; i < agregateListOfAnalitics.size(); i++) {
			CostAnaliticsInfo costAnaliticsInfo = agregateListOfAnalitics.get(i);
			feeSummarySpecs[i] = new CreateFeeSummarySpecInfo(
													costAnalitics.getId(),
													costAnaliticsInfo.sum,
													costAnaliticsInfo.costsAnl1.getId() == null ? null : costAnaliticsInfo.costsAnl1,
													costAnaliticsInfo.costsAnl2.getId() == null ? null : costAnaliticsInfo.costsAnl2,
													costAnaliticsInfo.costsAnl3.getId() == null ? null : costAnaliticsInfo.costsAnl3,
													costAnaliticsInfo.costsAnl4.getId() == null ? null : costAnaliticsInfo.costsAnl4,
													costAnaliticsInfo.costsAnl5.getId() == null ? null : costAnaliticsInfo.costsAnl5);
			
			docSum = docSum.add(costAnaliticsInfo.sum);
		}
		return docSum;
	}
	
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected FeeSummaryHead initializeAndCreateFeeSummaryHead(DocHeadModel feeSummaryPattern, BigDecimal docSum, PayRoll payRoll) {
		FeeSummaryHead feeSummaryHead = (FeeSummaryHead) getFeeSummaryHeadService().createByPattern(feeSummaryPattern, null);
		feeSummaryHead.setSumCur(docSum);
		feeSummaryHead.setSumNat(docSum);
		feeSummaryHead.setPayRoll(payRoll);
		getFeeSummaryHeadService().create(feeSummaryHead);
		return (FeeSummaryHead) feeSummaryHead;
	}
		
	private void createFeeSummarySpecs(FeeSummaryHead feeSummaryHead, CreateFeeSummarySpecInfo[] feeSummarySpecs) {
		getFeeSummaryHeadService().getSpecificationService().bulkCreate(feeSummaryHead, feeSummarySpecs);
	}
	
	/**
	 * Получить аналитику состава затрат из конфигурации модуля "Управление персоналом"
	 * @return аналитика состава затрат или <code>null</code>, если не найдена
	 */
	protected Catalog getCostsAnlFromPersonnelModuleConfiguraition() {
		PersonnelConfig config = ServerUtils.getPersistentManager().find(PersonnelConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
		if(config != null)
			return config.getCostsAnl();
		else
			return null;
	}

	/**
	 * Создать платежную ведомость на основе рассчетной
	 * @param payRollId - идентификатор рассчетной ведомости
	 * @return платежная ведомость
	 */
	protected PaySheet doCreatePaySheetByPayRoll(Integer payRollId) {
		if(payRollId == null)
			return null;
		PayRoll payRoll = load(payRollId);
		
		List<BasicPositionFillResult> personalAccountList = getBasicPositionFillList(payRoll);
		// пустая ведомость или нет базовых должностей
		if(personalAccountList.isEmpty())
			throw new BusinessException(Messages.getInstance().getMessage(Messages.BASIC_POSITION_IN_PAYROLL_NOT_FOUND));
		
		PaySheet paySheet = initializePaySheet(payRoll);
		createPaySheet(paySheet);
		
		RollKind rollKind = payRoll.getRollKind();
		CalcPeriod calcPeriod = payRoll.getCalcPeriod();
		BigDecimal paySheetFullSum = BigDecimal.ZERO;
		BigDecimal personTotalSum = BigDecimal.ZERO;
		PaySheetSpec[] paySheetSpecs = new PaySheetSpec[personalAccountList.size()]; 
		
		// заполним спецификацию(строки платежной ведомости)
		for (int i = 0; i < personalAccountList.size(); i++) {
			BasicPositionFillResult result = personalAccountList.get(i);
			personTotalSum = getPersonTotalSum(result.personalAccount, rollKind, calcPeriod);
			paySheetSpecs[i] = initializePaySheetSpec(paySheet, result.positionFill, personTotalSum);
			paySheetFullSum = paySheetFullSum.add(personTotalSum);
		}
		createPaySheetSpec(paySheetSpecs);
		
		paySheet.setSummaFull(paySheetFullSum);
		storePaySheet(paySheet);
		
		return paySheet;
	}
	
	protected List<BasicPositionFillResult> getBasicPositionFillList(PayRoll payRoll) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcList.class)
					.setProjection(Projections.projectionList(Projections.property("PositionFill"), Projections.property("pf.PersonalAccount"))) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("PositionFill", "pf", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("PayRoll", payRoll)) //$NON-NLS-1$
					.add(Restrictions.eq("pf.IsBasic", true)) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<BasicPositionFillResult>() {

						/* (non-Javadoc)
						 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
						 */
						public BasicPositionFillResult transformTuple(Object[] tuple, String[] aliases) {
							return new BasicPositionFillResult((PositionFill) tuple[0], (PersonalAccount) tuple[1]);
						}
					}));
	}
	
	protected BigDecimal getPersonTotalSum(PersonalAccount personalAccount, RollKind rollKind, CalcPeriod calcPeriod) {
		BigDecimal personTotalSum = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(CalcList.class)
										.setProjection(Projections.sum("TotalSumma")) //$NON-NLS-1$
										.createAlias("PositionFill", "pf", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
										.createAlias("PayRoll", "pr", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
										.add(Restrictions.eq("pr.RollKind", rollKind)) //$NON-NLS-1$
										.add(Restrictions.eq("pr.CalcPeriod", calcPeriod)) //$NON-NLS-1$
										.add(Restrictions.eq("pf.PersonalAccount", personalAccount))); //$NON-NLS-1$
		
		return personTotalSum == null ? BigDecimal.ZERO : personTotalSum; 
	}
	
	protected PaySheet initializePaySheet(PayRoll payRoll) {
		PaySheet paySheet = new PaySheet();
		paySheet.setPayRoll(payRoll);
		return paySheet;
	}
		
	private void createPaySheet(PaySheet paySheet) {
		getPaySheetService().create(paySheet);
	}
	
	private void storePaySheet(PaySheet paySheet) {
		getPaySheetService().store(paySheet);
	}
	
	private void createPaySheetSpec(PaySheetSpec[] paySheetSpecs) {
		if(paySheetSpecs != null && paySheetSpecs.length > 0) {
			PaySheetSpecServiceLocal paySheetSpecService = getPaySheetSpecService();
			for (PaySheetSpec paySheetSpec : paySheetSpecs)
				paySheetSpecService.create(paySheetSpec);
		}
	}
	
	protected PaySheetSpec initializePaySheetSpec(PaySheet paySheet, PositionFill basicPositionFill, BigDecimal fullSum) {
		PaySheetSpec paySheetSpec = new PaySheetSpec();
		paySheetSpec.setPaySheet(paySheet);
		paySheetSpec.setPositionFill(basicPositionFill);
		paySheetSpec.setSummaFull(fullSum);
		paySheetSpec.setSummaPaidOut(BigDecimal.ZERO);
		paySheetSpec.setSummaDeposited(BigDecimal.ZERO);
		paySheetSpec.setSummaRest(fullSum);
		return paySheetSpec;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PayRollServiceLocal#clearAndRecalc(int)
	 */
	public void clearAndRecalc(int payRollId) throws ApplicationException {
		//TODO: implement
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PayRollServiceLocal#recalc(int)
	 */
	public void recalc(int payRollId) throws ApplicationException {
		//TODO: implemet
	}
	
	private PaySheetServiceLocal getPaySheetService() {
		return (PaySheetServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaySheetServiceLocal.SERVICE_NAME);
	}
	
	private PaySheetSpecServiceLocal getPaySheetSpecService() {
		return (PaySheetSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaySheetSpecServiceLocal.SERVICE_NAME);
	}
	
	private FeeSummaryHeadServiceLocal getFeeSummaryHeadService() {
		return (FeeSummaryHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FeeSummaryHeadServiceLocal.SERVICE_NAME);
	}
	

	private class BasicPositionFillResult {
		
		PositionFill positionFill;
		PersonalAccount personalAccount;
		
		public BasicPositionFillResult(PositionFill positionFill, PersonalAccount personalAccount) {
			this.positionFill = positionFill;
			this.personalAccount = personalAccount;
		}
	}
	
	private class CostAnaliticsInfo {
		
		BigDecimal sum;
		CostsAnl costsAnl1;
		CostsAnl costsAnl2;
		CostsAnl costsAnl3;
		CostsAnl costsAnl4;
		CostsAnl costsAnl5;
		
		public CostAnaliticsInfo() {
			sum = BigDecimal.ZERO;
			costsAnl1 = new CostsAnl();
			costsAnl2 = new CostsAnl();
			costsAnl3 = new CostsAnl();
			costsAnl4 = new CostsAnl();
			costsAnl5 = new CostsAnl();
		}

		public CostAnaliticsInfo(BigDecimal sum, CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
			this.sum = sum;
			this.costsAnl1 = costsAnl1;
			this.costsAnl2 = costsAnl2;
			this.costsAnl3 = costsAnl3;
			this.costsAnl4 = costsAnl4;
			this.costsAnl5 = costsAnl5;
		}
	}

}
