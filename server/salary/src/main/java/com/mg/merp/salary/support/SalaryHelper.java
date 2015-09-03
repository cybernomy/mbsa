/*
 * SalaryHelper.java
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.ConstantServiceLocal;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.DeductionClass;
import com.mg.merp.personnelref.model.DeductionKind;
import com.mg.merp.personnelref.model.FamilyDeductions;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PersonnelService;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.Rise;
import com.mg.merp.personnelref.model.RiseScale;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.TarifCategType;
import com.mg.merp.personnelref.model.TariffScaleClass;
import com.mg.merp.personnelref.model.Tariffing;
import com.mg.merp.personnelref.support.PersonnelrefUtils;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.CalcListFeeParam;
import com.mg.merp.salary.model.DoubleSumSign;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.FeeParamType;
import com.mg.merp.salary.model.FeeRefParam;
import com.mg.merp.salary.model.IncludedFee;
import com.mg.merp.salary.model.MinSalary;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.TaxRate;
import com.mg.merp.salary.model.TripleSumSign;

/**
 * Класс вспомогательных функций для вычислений в алгоритмах з/п
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id$
 */
public class SalaryHelper {
	
	private final static String WORK_NORMS_QUERY_NAME = "Salary.SalaryHelper.workNorms"; //$NON-NLS-1$
	private final static ConstantServiceLocal constantService = (ConstantServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ConstantServiceLocal.SERVICE_NAME);
	
	
	/**
	 * Получить аналитику состава затрат указанную в тарифе 
	 * @param tariffCode - код тарифа
	 * @param positionFill - занимаимая должность
	 * @param staffList - штатное расписание
	 * @param actualDate - на дату
	 * @return аналитика(всех уровней) состава затрат указанная в тарифе
	 */
	public static CostsAnlResult getCostsAnlFromTariff(String tariffCode, PositionFill positionFill, StaffList staffList, Date actualDate) {
		List<Tariffing> tariffings = getTariffList(tariffCode, actualDate, positionFill, staffList);

		if(tariffings.size() > 0) {
			Tariffing tariffing = tariffings.get(0);
			return new CostsAnlResult(
					tariffing.getCostsAnl1(),
					tariffing.getCostsAnl2(),
					tariffing.getCostsAnl3(),
					tariffing.getCostsAnl4(),
					tariffing.getCostsAnl5());
		}
		else
			return new CostsAnlResult();
	}

	/**
	 * Получить вычет
	 * @param deductionKindCode - код вида вычета
	 * @param actualDate - на дату
	 * @param income - доход
	 * @return вычет на дату
	 */
	public static BigDecimal getDeduction(String deductionKindCode, Date actualDate, BigDecimal income) {
		BigDecimal result = BigDecimal.ZERO;

		List<DeductionKind> deductionKinds = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DeductionKind.class)
								.add(Restrictions.eq("DCode", deductionKindCode)) //$NON-NLS-1$
								.add(Restrictions.le("BeginDate", actualDate)) //$NON-NLS-1$
								.addOrder(Order.desc("BeginDate"))); //$NON-NLS-1$

		BigDecimal minSalary = getMinSalary(actualDate);
		if(deductionKinds.size() > 0) {
			DeductionKind deductionKind = deductionKinds.get(0);
			if(deductionKind.getMaxIncome() == null || income.compareTo(deductionKind.getMaxIncome()) <= 0)
				result = deductionKind.getMinSalaryNumber().multiply(minSalary).add(deductionKind.getFixedSum());
		}
		return result;
	}

	/**
	 * Получить сумму вычетов на членов семьи
	 * @param deductionClass - вид вычетов (если NULL то для всех видов)
	 * @param actualDate - на дату
	 * @param income - доход
	 * @param personalAccount - лицевой счет сотрудника
	 * @return сумма вычетов на членов семьи
	 */
	public static BigDecimal getFamilyDeductionSum(DeductionClass deductionClass, Date actualDate, BigDecimal income, PersonalAccount personalAccount) {
		Criteria criteria = OrmTemplate.createCriteria(FamilyDeductions.class)
					.createAlias("DeductionKind", "dk", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("FamilyMember", "fm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("fm.NaturalPerson", personalAccount.getPersonnel().getPerson())) //$NON-NLS-1$
					.add(Restrictions.and(Restrictions.le("BeginDate", actualDate), Restrictions.ge("EndDate", actualDate))); //$NON-NLS-1$ //$NON-NLS-2$

		if(deductionClass != null)
			criteria.add(Restrictions.eq("dk.DeductionClass", deductionClass)); //$NON-NLS-1$

		List<FamilyDeductions> familyDeductions = OrmTemplate.getInstance().findByCriteria(criteria);
		BigDecimal totalDeductionSum = BigDecimal.ZERO;

		if(!familyDeductions.isEmpty()) {
			BigDecimal minSalary = getMinSalary(actualDate);
			for(FamilyDeductions familyDeduction : familyDeductions) {
				DeductionKind deductionKind = familyDeduction.getDeductionKind();
				BigDecimal maxIncome = deductionKind.getMaxIncome();
				if(maxIncome == null || income.compareTo(maxIncome) <= 0) {
					BigDecimal deductionSum = deductionKind.getFixedSum().add(deductionKind.getMinSalaryNumber()).multiply(minSalary);
					totalDeductionSum = totalDeductionSum.add(deductionSum.multiply(familyDeduction.getRatio()));
				}
			}
		}
		return totalDeductionSum;
	}

	/**
	 * Получить размер минимальной зарплаты из справочника на указанную дату
	 * @param actualDate - дата
	 * @return размер минимальной зарплаты из справочника на указанную дату
	 */
	public static BigDecimal getMinSalary(Date actualDate) {
		List<MinSalary> minSalaries = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(MinSalary.class)
							.add(Restrictions.le("BeginDate", actualDate)) //$NON-NLS-1$
							.addOrder(Order.desc("BeginDate"))); //$NON-NLS-1$

		if(minSalaries.size() > 0)
			return minSalaries.get(0).getMinSalary();
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Получить сумму н/у по коду, за указанный период
	 * @param feeCode - код н/у
	 * @param beginDate - дата начала периода
	 * @param endDate -  дата окончания периода
	 * @param payRoll - расчетная ведомость. Если NULL, то для всех расчетных ведомостей
	 * @param positionFill - исполняемая должность. Если NULL, то для всех должностей, исполняемых сотрудником
	 * @param personalAccount - лицевой счет сотрудника
	 * @return сумма н/у по коду, за указанный период
	 */
	public static BigDecimal getFeeSum(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, PersonalAccount personalAccount) {
		Criteria criteria = OrmTemplate.createCriteria(CalcListFee.class)
					.setProjection(Projections.sum("Summa")) //$NON-NLS-1$
					.createAlias("FeeModel", "fm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("fm.FeeRef", "fr", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("fr.FCode", feeCode)) //$NON-NLS-1$
					.add(Restrictions.disjunction(
							Restrictions.between("PeriodBeginDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.between("PeriodEndDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.and(Restrictions.le("PeriodBeginDate", beginDate), Restrictions.ge("PeriodEndDate", beginDate)), //$NON-NLS-1$ //$NON-NLS-2$
							Restrictions.and(Restrictions.le("PeriodBeginDate", endDate), Restrictions.ge("PeriodEndDate", endDate)))); //$NON-NLS-1$ //$NON-NLS-2$

		if(payRoll != null && positionFill != null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else if(payRoll != null && positionFill == null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}
		else if(payRoll == null && positionFill != null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else if(payRoll == null && positionFill == null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}
		BigDecimal feeSum = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		return feeSum == null ? BigDecimal.ZERO : feeSum; 
	}

	/**
	 * Получить сумму начислений/удержаний, коды которых указаны на закладке "входящие н/у" справочника н/у, за указанный период
	 * @param beginDate - дата начала периода
	 * @param endDate - дата окончания периода
	 * @param payRoll - расчетная ведомость. Если null, то для всех расчетных ведомостей
	 * @param positionFill - исполняемая должность. Если null, то для всех должностей, исполняемых сотрудником
	 * @param feeSign - знак, с которым входят начисления:
	 * 					TripleSumSign.NONE - взять все входящие начисления. Входящие со знаком "плюс" прибавляются к общей сумме, входящие со знаком "минус" вычитаются из общей суммы;
	 * 					TripleSumSign.Plus - складываются только начисления, входящие со знаком "плюс";
	 * 					TripleSumSign.Minus - складываются только начисления, входящие со знаком "минус". Результат > 0 !
	 * @param feeModel - образец начислений/удержаний
	 * @param personalAccount - лицевой счет сотрудника
	 * @return сумма начислений/удержаний, коды которых указаны на закладке "входящие н/у" справочника н/у, за указанный период
	 */
	public static BigDecimal getIncludedFeeSum(Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, TripleSumSign feeSign, FeeModel feeModel, PersonalAccount personalAccount) {
		BigDecimal includedFeeSum = BigDecimal.ZERO;
		List<IncludedFee> includedFees = getIncludedFees(feeSign, feeModel);

		for(IncludedFee includedFee : includedFees) {
			DoubleSumSign feeSumSign = includedFee.getSumSign();
			if(DoubleSumSign.PLUS == feeSumSign)
				includedFeeSum = includedFeeSum.add(getFeeSum(includedFee.getIncludedFee().getFCode(), beginDate, endDate, payRoll, positionFill, personalAccount));
			if(DoubleSumSign.MINUS == feeSumSign)
				includedFeeSum = includedFeeSum.subtract(getFeeSum(includedFee.getIncludedFee().getFCode(), beginDate, endDate, payRoll, positionFill, personalAccount));
		}

		return TripleSumSign.Minus == feeSign ? includedFeeSum.negate() : includedFeeSum;
	}

	/**
	 * Получить сумму н/у, коды которых указаны на закладке "входящие н/у" справочника н/у, за указанный период. Расширенный набор параметров.
	 * @param beginDate - дата начала периода
	 * @param endDate - дата окончания периода
	 * @param payRoll - р/в. Если null, то для всех р/в
	 * @param payRollKindName - наименование типа р/в. Если null, то для всех р/в.
	 * @param positionFillKindCode - код вида исполнения должности. Если null, то для всех должностей, исполняемых сотрудником
	 * @param positionFill - исполненяемая должность. Если null, то для всех должностей, исполняемых сотрудником
	 * @param taxCalcKindCode - код схемы расчета налогов для должности. Если null, то для всех должностей, исполняемых сотрудником
	 * @param feeSign - знак, с которым входят начисления
	 * @param feeModel - образец начислений/удержаний
	 * @return сумма н/у, коды которых указаны на закладке "входящие н/у" справочника н/у, за указанный период
	 */
	public static BigDecimal getIncludedFeeSumEx(Date beginDate, Date endDate, PayRoll payRoll, String payRollKindName, String positionFillKindCode, PositionFill positionFill, PersonalAccount personalAccount, String taxCalcKindCode, TripleSumSign feeSign, FeeModel feeModel) {
		BigDecimal includedFeeSum = BigDecimal.ZERO;
		
		List<IncludedFee> includedFees = getIncludedFees(feeSign, feeModel);
		if(includedFees.isEmpty())
			return includedFeeSum;

		for(IncludedFee includedFee : includedFees) {
			BigDecimal feeSum = BigDecimal.ZERO;
			DoubleSumSign feeSumSign = includedFee.getSumSign();

			List<BigDecimal> feeSumList = OrmTemplate.getInstance().findByCriteria(getCriteriaForIncludedFeeSumEx(includedFee.getIncludedFee().getFCode(), beginDate, endDate, payRoll, payRollKindName, positionFillKindCode, positionFill, personalAccount, taxCalcKindCode, feeSign, feeModel));
			for(BigDecimal feeSumItem : feeSumList)
				feeSum = feeSum.add(feeSumItem); 

			if(DoubleSumSign.PLUS == feeSumSign)
				includedFeeSum = includedFeeSum.add(feeSum);
			if(DoubleSumSign.MINUS == feeSumSign)
				includedFeeSum = includedFeeSum.subtract(feeSum);
		}

		return TripleSumSign.Minus == feeSign ? includedFeeSum.negate() : includedFeeSum;
	}
	
	private static Criteria getCriteriaForIncludedFeeSumEx(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, String payRollKindName, String positionFillKindCode, PositionFill positionFill, PersonalAccount personalAccount, String taxCalcKindCode, TripleSumSign feeSign, FeeModel feeModel) {
		boolean isCalcListAliasCreated = false;
		boolean isPositionFillAliasCreated = false;
		
		Criteria criteria = OrmTemplate.createCriteria(CalcListFee.class)
					.setProjection(Projections.distinct(Projections.property("Summa"))) //$NON-NLS-1$
					.createAlias("FeeModel", "fm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("fm.FeeRef", "fr", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("fr.FCode", feeCode)) //$NON-NLS-1$
					.add(Restrictions.disjunction(
							Restrictions.between("PeriodBeginDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.between("PeriodEndDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.and(Restrictions.le("PeriodBeginDate", beginDate), Restrictions.ge("PeriodEndDate", beginDate)), //$NON-NLS-1$ //$NON-NLS-2$
							Restrictions.and(Restrictions.le("PeriodBeginDate", endDate), Restrictions.ge("PeriodEndDate", endDate)))); //$NON-NLS-1$ //$NON-NLS-2$

		if(payRollKindName != null) {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			criteria.createAlias("cl.PayRoll", "pr", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("pr.RollKind", "rk", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("rk.Name", payRollKindName)); //$NON-NLS-1$
		}
		if(payRoll != null) {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
		}
		if(positionFillKindCode != null) {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			if(!isPositionFillAliasCreated) {
				createPositionFillAlias(criteria);
				isPositionFillAliasCreated = true; 
			}
			criteria.createAlias("pf.PositionFillKind", "pfk", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("pfk.KCode", positionFillKindCode)); //$NON-NLS-1$
		}
		if(positionFill != null) {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			if(!isPositionFillAliasCreated) {
				createPositionFillAlias(criteria);
				isPositionFillAliasCreated = true; 
			}
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}
		if(taxCalcKindCode != null) {
			if(!isCalcListAliasCreated) {
				createCalcListAlias(criteria);
				isCalcListAliasCreated = true;
			}
			if(!isPositionFillAliasCreated) {
				createPositionFillAlias(criteria);
				isPositionFillAliasCreated = true; 
			}
			criteria.createAlias("pf.SlPositionUnique", "slp", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.disjunction(
					Restrictions.between("slp.BeginDate", beginDate, endDate), //$NON-NLS-1$
					Restrictions.between("slp.EndDate", beginDate, endDate), //$NON-NLS-1$
					Restrictions.and(Restrictions.le("slp.BeginDate", beginDate), Restrictions.ge("slp.EndDate", beginDate)))); //$NON-NLS-1$ //$NON-NLS-2$

			criteria.createAlias("slp.TaxCalcKind", "tck", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("tck.Code", taxCalcKindCode)); //$NON-NLS-1$
		}
		return criteria;
	}

	/**
	 * Получить н/у, последнеее по дате начала действия, за указанный период.
	 * @param feeCode - код н/у
	 * @param beginDate - дата начала периода
	 * @param endDate - дата окончания периода
	 * @param payRoll - р/в. Если null, то для всех р/в
	 * @param positionFill - исполненяемая должность. Если null, то для всех должностей, исполняемых сотрудником
	 * @param personalAccount - личевой счет сотрудника
	 * @return н/у, последнеее по дате начала действия, за указанный период
	 */
	public static BigDecimal getLastFee(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, PersonalAccount personalAccount) {
		Criteria criteria = OrmTemplate.createCriteria(CalcListFee.class)
					.setProjection(Projections.property("Summa")) //$NON-NLS-1$
					.createAlias("FeeModel", "fm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("fm.FeeRef", "fr", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("fr.FCode", feeCode)) //$NON-NLS-1$
					.add(Restrictions.disjunction(
							Restrictions.between("BeginDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.between("EndDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.and(Restrictions.le("BeginDate", beginDate), Restrictions.ge("EndDate", beginDate)), //$NON-NLS-1$ //$NON-NLS-2$
							Restrictions.and(Restrictions.le("BeginDate", endDate), Restrictions.ge("EndDate", endDate)))) //$NON-NLS-1$ //$NON-NLS-2$
					.addOrder(Order.desc("BeginDate")); //$NON-NLS-1$

		if(payRoll != null && positionFill != null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else if(payRoll != null && positionFill == null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}
		else if(payRoll == null && positionFill != null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else if(payRoll == null && positionFill == null) {
			criteria.createAlias("CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}

		List<BigDecimal> feeSumList = OrmTemplate.getInstance().findByCriteria(criteria);
		if(!feeSumList.isEmpty())
			return feeSumList.get(0);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Получить стаж сотрудника в годах по коду стажа
	 * @param serviceKindCode - код стажа
	 * @param actualDate - фактическая дата
	 * @param personalAccount - личевой счет сотрудника
	 * @return стаж сотрудника в годах по коду стажа
	 */
	public static BigDecimal getLengthOfService(String serviceKindCode, Date actualDate, PersonalAccount personalAccount) {
		Criteria criteria = OrmTemplate.createCriteria(PersonnelService.class)
					.createAlias("ServiceKind", "sk", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("sk.KCode", serviceKindCode)) //$NON-NLS-1$
					.add(Restrictions.eq("Personnel", personalAccount.getPersonnel())); //$NON-NLS-1$

		List<PersonnelService> personnelServiceList = OrmTemplate.getInstance().findByCriteria(criteria);
		if(personnelServiceList.isEmpty())
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.SERVICE_KIND_CODE_NOT_FOUND), serviceKindCode));
		

		PersonnelService personnelService = personnelServiceList.get(0);
		Date serviceBeginDate = personnelService.getBeginDate();
		Date serviceEndDate = personnelService.getEndDate();

		if(serviceBeginDate == null)
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.BEGIN_DATE_OF_SERVICE_LENGTH_NOT_FOUND), serviceKindCode));

		if(serviceEndDate == null || serviceEndDate.compareTo(actualDate) > 0)
			serviceEndDate = actualDate;

		if(serviceEndDate.compareTo(serviceBeginDate) < 0)
			return BigDecimal.ZERO;
		else
			return new BigDecimal(PersonnelrefUtils.getYearSpan(serviceBeginDate, serviceEndDate));
	}

	/**
	 * Получить количество ставок, которое занимает сотрудник по указанной должности, на дату начала действия н/у
	 * @param positionFill - исполненяемая должность
	 * @return количество ставок, которое занимает сотрудник по указанной должности, на дату начала действия н/у
	 */
	public static BigDecimal getNumberOfRates(PositionFill positionFill) {
		return positionFill.getRateNumber() == null ? BigDecimal.ZERO : positionFill.getRateNumber(); 
	}

	/**
	 * Получить параметр текущего н/у
	 * @param paramCode - код параметра
	 * @param calcListFee - начисление/удержание
	 * @return значение параметра начисления/удержания по коду параметра
	 */
	public static Object getParam(String paramCode, CalcListFee calcListFee) {
		if(!isParametrBelongsToCalcListFee(paramCode, calcListFee))
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.PARAM_IS_NOT_BELONGS_TO_CALC_LIST_FEE), paramCode, calcListFee.getId()));


		List<CalcListFeeParam> calcListFeeParams = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFeeParam.class)
				.createAlias("FeeRefParam", "frp", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("CalcListFee", calcListFee)) //$NON-NLS-1$
				.add(Restrictions.eq("frp.PCode", paramCode))); //$NON-NLS-1$

		if(calcListFeeParams.size() > 0) {
			CalcListFeeParam calcListFeeParam = calcListFeeParams.get(0); 
			return getExactFeeParamValue(calcListFeeParam.getParamValue(), calcListFeeParam.getFeeRefParam().getParamType());
		}
		else
			return null; 
	}

	/**
	 * Получить параметр н/у, последнего по дате начала действия, за указанный период
	 * @param feeCode - код н/у
	 * @param paramCode - код параметра
	 * @param beginDate - дата начала периода
	 * @param endDate - дата окончания периода
	 * @param payRoll - р/в. Если null, то для всех р/в
	 * @param positionFill - исполненяемая должность. Если null, то для всех должностей, исполняемых сотрудником
	 * @param personalAccount - лицевой счет сотрудника
	 * @return параметр н/у, последнего по дате начала действия, за указанный период
	 */
	public static Object getParamFromLastFee(String feeCode, String paramCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, PersonalAccount personalAccount) {
		List<FeeRefParam> feeRefParams = getParamByParamCodeAndFeeCode(paramCode, feeCode);
		if(feeRefParams.isEmpty())
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.PARAM_IS_NOT_BELONGS_TO_CALC_LIST_FEE_CODE), paramCode, feeCode));

		FeeParamType feeParamType = feeRefParams.get(0).getParamType();

		Criteria criteria = OrmTemplate.createCriteria(CalcListFeeParam.class)
					.setProjection(Projections.property("ParamValue"))			 //$NON-NLS-1$
					.createAlias("FeeRefParam", "frp", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("CalcListFee", "clf", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("clf.FeeModel", "fm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("fm.FeeRef", "fr", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("frp.PCode", paramCode)) //$NON-NLS-1$
					.add(Restrictions.eq("fr.FCode", feeCode)) //$NON-NLS-1$
					.add(Restrictions.disjunction(
							Restrictions.between("clf.BeginDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.between("clf.EndDate", beginDate, endDate), //$NON-NLS-1$
							Restrictions.and(Restrictions.le("clf.BeginDate", beginDate), Restrictions.ge("clf.EndDate", beginDate)), //$NON-NLS-1$ //$NON-NLS-2$
							Restrictions.and(Restrictions.le("clf.BeginDate", endDate), Restrictions.ge("clf.EndDate", endDate)))) //$NON-NLS-1$ //$NON-NLS-2$
					.addOrder(Order.desc("clf.BeginDate")); //$NON-NLS-1$

		if(payRoll != null && positionFill != null) {
			criteria.createAlias("clf.CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		} 
		else if(payRoll != null && positionFill == null) {
			criteria.createAlias("clf.CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PayRoll", payRoll)); //$NON-NLS-1$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		} 
		else if(payRoll == null && positionFill != null) {
			criteria.createAlias("clf.CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("cl.PositionFill", positionFill)); //$NON-NLS-1$
		}
		else if(payRoll == null && positionFill == null) {
			criteria.createAlias("clf.CalcListSection", "cls", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cls.CalcList", "cl", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.createAlias("cl.PositionFill", "pf", JoinType.INNER_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
			criteria.add(Restrictions.eq("pf.PersonalAccount", personalAccount)); //$NON-NLS-1$
		}

		List<Object> calcListFeeParams = OrmTemplate.getInstance().findByCriteria(criteria);
		if(calcListFeeParams.size() > 0) 
			return getExactFeeParamValue((String) calcListFeeParams.get(0), feeParamType);
		else
			return getExactFeeParamValue(null, feeParamType);
	}

	/**
	 * Получить код вида исполнения указанной должности
	 * @param positionFill - исполненяемая должность
	 * @return код вида исполнения указанной должности
	 */
	public static String getPositionKindCode(PositionFill positionFill) {
		if(positionFill != null)
			return positionFill.getPositionFillKind().getKCode().trim();
		else
			return StringUtils.EMPTY_STRING;
	}

	/**
	 * Получить код категории персонала для указанной должности 
	 * @param positionFill - исполненяемая должность
	 * @param actualDate - фактическая дата
	 * @param staffList - штатное расписание
	 * @return код категории персонала для указанной должности
	 */
	public static String getStaffCategoryCode(PositionFill positionFill, Date actualDate, StaffList staffList) {
		List<String> staffCategoryCodes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(StaffListPosition.class)
				.setProjection(Projections.property("sc.CCode"))			 //$NON-NLS-1$
				.createAlias("StaffCategory", "sc", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
				.createAlias("StaffListUnit", "slu", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("slu.StaffList", staffList)) //$NON-NLS-1$
				.add(Restrictions.eq("SlPositionUniqueId", positionFill.getSlPositionUnique().getSlPositionUniqueId())) //$NON-NLS-1$
				.add(Restrictions.and(Restrictions.le("BeginDate", actualDate), Restrictions.ge("EndDate", actualDate)))); //$NON-NLS-1$ //$NON-NLS-2$

		if(!staffCategoryCodes.isEmpty())
			return staffCategoryCodes.get(0).trim();
		else
			return StringUtils.EMPTY_STRING;
	}

	/**
	 * Получить по коду тариф из лицевого счета сотрудника, действующий на дату начала действия н/у
	 * @param tariffCode - код тарифа
	 * @param positionFill - исполненяемая должность
	 * @param staffList - штатное расписание
	 * @param beginDate - дата начала действия н/у
	 * @param contextParams - параметры контекста выполнения расчета
	 * @return тариф из лицевого счета сотрудника, действующий на дату начала действия н/у.
	 */
	public static BigDecimal getTariff(String tariffCode, PositionFill positionFill, StaffList staffList, Date beginDate, Map<String, ? extends Object> contextParams) {
		BigDecimal resultTariff = BigDecimal.ZERO;
		List<Tariffing> tariffings = getTariffList(tariffCode, beginDate, positionFill, staffList);

		if(tariffings.isEmpty())
			return resultTariff;

		Tariffing tariffing = tariffings.get(0);
		TarifCategType tariffCategoryType = tariffing.getCategory().getCType();

		if(TarifCategType.RATE == tariffCategoryType)
			resultTariff = tariffing.getRateOfSalary() == null ? BigDecimal.ZERO : tariffing.getRateOfSalary();

		if(TarifCategType.TARIFF == tariffCategoryType)
			resultTariff = getValueFromTariffScale(tariffing.getTariffScaleCode(), tariffing.getTariffClass(), beginDate, contextParams);

		if(TarifCategType.RISE == tariffCategoryType) {
			if(!tariffing.getUseRiseReference())
				resultTariff = tariffing.getRiseValue();
			else
				resultTariff = getRiseValue(tariffing.getRise(), tariffing.getRiseScale());
		}

		if(TarifCategType.MIN == tariffCategoryType)
			resultTariff = tariffing.getMinSalaryNumber().multiply(getMinSalary(beginDate));

		return resultTariff;
	}

	/**
	 * Получить норму рабочего времени в днях, по заданному исполнению должности, на заданную дату
	 * @param positionFill - исполненяемая должность
	 * @param actualDate - фактическая дата
	 * @param staffList - штатное расписанте
	 * @return норма рабочего времени в днях, по заданному исполнению должности, на заданную дату
	 */
	public static BigDecimal getWorkDaysNorm(PositionFill positionFill, Date actualDate, StaffList staffList) {
		WorkNormResult workNorm = getWorkNorms(positionFill, actualDate, staffList);

		if(workNorm.daysNorm == null)
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.DAYS_NORM_NOT_FOUND), actualDate));
		else
			return new BigDecimal(workNorm.daysNorm);
	}

	/**
	 * Получить норму рабочего времени в часах, по заданному исполнению должности, на заданную дату
	 * @param positionFill - исполненяемая должность
	 * @param actualDate - фактическая дата
	 * @param staffList - штатное расписанте
	 * @return норма рабочего времени в часах, по заданному исполнению должности, на заданную дату
	 */
	public static BigDecimal getWorkHoursNorm(PositionFill positionFill, Date actualDate, StaffList staffList) {
		WorkNormResult workNorm = getWorkNorms(positionFill, actualDate, staffList);

		if(workNorm.hoursNorm == null)
			throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.HOURS_NORM_NOT_FOUND), actualDate));
		else
			return new BigDecimal(workNorm.hoursNorm);
	}

	/**
	 * Получить информацию о налоге по указанному размеру дохода
	 * @param taxCode - код налога из справочника
	 * @param actualDate - фактическая дата
	 * @param scaleNumber - номер шкалы
	 * @param income - сумма дохода
	 * @return информация о налоге по указанному размеру дохода
	 */
	public static TaxResult getTaxByIncome(String taxCode, Date actualDate, Integer scaleNumber, BigDecimal income) {
		return getTaxInfo(true, income, taxCode, actualDate, scaleNumber, null);
	}

	/**
	 * Получить информацию о налоге по номеру ставки
	 * @param taxCode - код налога из справочника
	 * @param actualDate - фактическая дата
	 * @param scaleNumber - номер шкалы
	 * @param rateNumber - номер ставки
	 * @return информация о налоге по номеру ставки
	 */
	public static TaxResult getTaxByNumber(String taxCode, Date actualDate, Integer scaleNumber, Integer rateNumber) {
		return getTaxInfo(false, null, taxCode, actualDate, scaleNumber, rateNumber);
	}
	
	/**
	 * Получить значение константы из справочника по коду и дате актуальности
	 * @param constantCode - код константы
	 * @param actualDate - дата актуальности
	 * @return значение константы из справочника по коду и дате актуальности, или <code>null</code> если не найдено
	 */
	public static Object getConstantValue(String constantCode, Date actualDate) {
		return constantService.getActualValue(constantCode, actualDate);
	}

	/**
	 * 
	 * @param costsAnl1 - аналитика состава затрат 1-го уровня
	 * @param costsAnl2 - аналитика состава затрат 2-го уровня
	 * @param costsAnl3 - аналитика состава затрат 3-го уровня
	 * @param costsAnl4 - аналитика состава затрат 4-го уровня
	 * @param costsAnl5 - аналитика состава затрат 5-го уровня
	 * @return
	 */
	public static boolean isCostsAnlEmpty(CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
		return (costsAnl1 == null) && (costsAnl2 == null) && (costsAnl3 == null) && (costsAnl4 == null) && (costsAnl5 == null);
	}


	/**
	 * Получить информацию о налоге
	 * @param isByIncome - признак отбора налогов: true - по указанному размеру дохода; false - по номеру ставки
	 * @param actualIncome - сумма дохода
	 * @param taxCode - код налога из справочника
	 * @param actualDate - фактическая дата
	 * @param scaleNumber - номер шкалы
	 * @param rateNumber - номер ставки
	 * @return информация о налоге
	 */
	private static TaxResult getTaxInfo(boolean isByIncome, BigDecimal actualIncome, String taxCode, Date actualDate, Integer scaleNumber, Integer rateNumber) {
		Criteria criteria = OrmTemplate.createCriteria(TaxRate.class)
					.setProjection(Projections.projectionList(
							Projections.property("RNumber"), //$NON-NLS-1$
							Projections.property("MinIncome"), //$NON-NLS-1$
							Projections.property("MaxIncome"), //$NON-NLS-1$
							Projections.property("TaxPercent"),  //$NON-NLS-1$
							Projections.property("ConstValue"), //$NON-NLS-1$
							Projections.property("Privilegeratio"))) //$NON-NLS-1$
					.createAlias("TaxScale", "ts", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("ts.TaxHead", "th", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.le("ts.BeginDate", actualDate)) //$NON-NLS-1$
					.add(Restrictions.eq("ts.SNumber", scaleNumber)) //$NON-NLS-1$
					.add(Restrictions.eq("th.TCode", taxCode)) //$NON-NLS-1$
					.addOrder(Order.desc("ts.BeginDate")) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<TaxResult>() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
					 */
					public TaxResult transformTuple(Object[] tuple, String[] aliases) {
						return new TaxResult(
								(Integer) tuple[0], 
								(BigDecimal) tuple[1], 
								(BigDecimal) tuple[2], 
								(BigDecimal) tuple[3], 
								(BigDecimal) tuple[4], 
								(BigDecimal) tuple[5]);
					}
				});

		if(isByIncome) {
			criteria.add(Restrictions.le("MinIncome", actualIncome)); //$NON-NLS-1$
			criteria.addOrder(Order.desc("MinIncome")); //$NON-NLS-1$
		}
		else 
			criteria.add(Restrictions.le("RNumber", rateNumber)); //$NON-NLS-1$

		List<TaxResult> taxList = OrmTemplate.getInstance().findByCriteria(criteria);

		if(!taxList.isEmpty())
			return taxList.get(0);
		else
			return new TaxResult();
	}

	/**
	 * Получить норму рабочего времени, по заданному исполнению должности, на заданную дату
	 * @param positionFill - исполненяемая должность
	 * @param actualDate - фактическая дата
	 * @param staffList - штатное расписанте
	 * @return норма рабочего времени, по заданному исполнению должности, на заданную дату
	 */
	private static WorkNormResult getWorkNorms(PositionFill positionFill, Date actualDate, StaffList staffList) {
		List<Integer[]> workNorms = MiscUtils.convertUncheckedList(Integer[].class, OrmTemplate.getInstance().findByNamedQueryAndNamedParam(WORK_NORMS_QUERY_NAME,
				new String[]{"staffList", "positionFill", "actualDate"}, new Object[] {staffList, positionFill, actualDate})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if(!workNorms.isEmpty()) {
			Integer[] workNorm = workNorms.get(0);
			return new WorkNormResult(workNorm[0], workNorm[1]);
		}
		else
			return new WorkNormResult();
	}

	/**
	 * Получить значение из шкалы надбавки
	 * @param tariffScaleCode - код шкалы надбавки
	 * @param classNumber
	 * @param actualDate - дата актуальности
	 * @return значение из шкалы по коду надбавки на дату 
	 */
	private static BigDecimal getValueFromTariffScale(String tariffScaleCode, Integer classNumber, Date actualDate, Map<String, ? extends Object> contextParams) {
		BigDecimal tariffScaleValue = BigDecimal.ZERO;
		List<TariffScaleClass> tariffScaleClassList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(TariffScaleClass.class)
				.createAlias("TariffScale", "ts", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("ts.SCode", tariffScaleCode)) //$NON-NLS-1$
				.add(Restrictions.eq("ClassNumber", classNumber)) //$NON-NLS-1$
				.add(Restrictions.le("ts.BeginDate", actualDate)) //$NON-NLS-1$
				.addOrder(Order.desc("ts.BeginDate"))); //$NON-NLS-1$

		if(tariffScaleClassList.isEmpty())
			return tariffScaleValue;

		final TariffScaleClass tariffScaleClass = tariffScaleClassList.get(0);
		if(tariffScaleClass.getTariffScale().getFirstClassAlg() == null)
			tariffScaleValue = tariffScaleClass.getRate();
		else {
			TariffScaleValueResultListener tariffScaleValueResultListener = new TariffScaleValueResultListener();
			BusinessAddinEngineLocator.locate().perform(tariffScaleClass.getTariffScale().getFirstClassAlg(), contextParams, tariffScaleValueResultListener);
			tariffScaleValue = tariffScaleClass.getFactor().multiply(tariffScaleValueResultListener.result);
		}
		return tariffScaleValue;
	}

	/**
	 * Получить процент надбавки
	 * @param rise - надбавка
	 * @param riseScale - шкала надбавки
	 * @return процент надбавки
	 */
	private static BigDecimal getRiseValue(Rise rise, RiseScale riseScale) {
		//TODO: Legasy stub: непонятно откуда брать стаж, по которому определяется процент надбавки
		return BigDecimal.ZERO;
	}

	/**
	 * Получить список начислений/удержаний, которые входят в образец начислений/удержаний
	 * @param feeSign - знак, с которым входят начисления
	 * @param feeModel - образец начислений/удержаний
	 * @return список начислений/удержаний, которые входят в образец начислений/удержаний
	 */
	private static List<IncludedFee> getIncludedFees(TripleSumSign feeSign, FeeModel feeModel) {
		Criteria criteria = OrmTemplate.createCriteria(IncludedFee.class)
					.add(Restrictions.eq("FeeRef", feeModel.getFeeRef())); //$NON-NLS-1$

		if(TripleSumSign.Plus == feeSign)
			criteria.add(Restrictions.eq("SumSign", DoubleSumSign.PLUS)); //$NON-NLS-1$
		else if(TripleSumSign.Minus == feeSign) 
			criteria.add(Restrictions.eq("SumSign", DoubleSumSign.MINUS)); //$NON-NLS-1$

		return OrmTemplate.getInstance().findByCriteria(criteria);
	}

	/**
	 * Поверить принадлежность параметра начислению/удержанию
	 * @param paramCode - код параметра
	 * @param calcListFee - начисление/удержание
	 * @return true - если заданный параметр принадлежит заданному начислению/удержанию
	 */
	private static boolean isParametrBelongsToCalcListFee(String paramCode, CalcListFee calcListFee) {
		boolean isBelongs = false;
		Set<FeeRefParam> feeRefParams = calcListFee.getFeeModel().getFeeRef().getFeeRefParams();
		for(FeeRefParam feeRefParam : feeRefParams) { 
			if(paramCode.compareTo(feeRefParam.getPCode().trim()) == 0) {
				isBelongs = true;
				break;
			}
		}
		return isBelongs;
	}

	/**
	 * Получить список параметров по коду параметра и коду начисления/удержания
	 * @param paramCode - код параметра
	 * @param feeCode - код начисления/удержания
	 * @return список параметров по коду параметра и коду начисления/удержания
	 */
	private static List<FeeRefParam> getParamByParamCodeAndFeeCode(String paramCode, String feeCode) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FeeRefParam.class)
												.createAlias("FeeRef", "fr", JoinType.INNER_JOIN)									 //$NON-NLS-1$ //$NON-NLS-2$
												.add(Restrictions.eq("fr.FCode", feeCode)) //$NON-NLS-1$
												.add(Restrictions.eq("PCode", paramCode))); //$NON-NLS-1$
	}

	/**
	 * Получить значение параметра в соответсвии с его типом
	 * @param feeParamValue - значение параметра
	 * @param feeParamType - тип параметра
	 * @return значение параметра в соответсвии с его типом
	 */
	private static Object getExactFeeParamValue(String feeParamValue, FeeParamType feeParamType) {
		Object exactParamValue = null;
		if(feeParamValue != null && feeParamValue != StringUtils.EMPTY_STRING) {
			if(FeeParamType.NUMBER == feeParamType)
				exactParamValue = new BigDecimal(feeParamValue);
			if(FeeParamType.STRING == feeParamType)
				exactParamValue = feeParamValue;
			if(FeeParamType.DATE == feeParamType)
				exactParamValue = DateTimeUtils.toDate(feeParamValue);
		}
		else {
			if(FeeParamType.NUMBER == feeParamType)
				exactParamValue = BigDecimal.ZERO;
			if(FeeParamType.STRING == feeParamType)
				exactParamValue = StringUtils.EMPTY_STRING;
			if(FeeParamType.DATE == feeParamType)
				exactParamValue = null;
		}
		return exactParamValue;
	}

	/**
	 * Получить список тарифов должности
	 * @param tariffCode - код тарифа
	 * @param actualDate - фактическая дата
	 * @param positionFill - занимаемая должность
	 * @param staffList - штатное расписание
	 * @return список тарифов должности
	 */
	private static List<Tariffing> getTariffList(String tariffCode, Date actualDate, PositionFill positionFill, StaffList staffList) {
		DetachedCriteria dc = DetachedCriteria.forClass(PositionFill.class)
								.setProjection(Projections.property("SlPositionUnique.SlPositionUniqueId")) //$NON-NLS-1$
								.add(Restrictions.eq("Id", positionFill.getId())); //$NON-NLS-1$

		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Tariffing.class)
				.createAlias("Category", "tc", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("tc.CCode", tariffCode)) //$NON-NLS-1$
				.add(Restrictions.and(Restrictions.le("BeginDate", actualDate), Restrictions.ge("EndDate", actualDate))) //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.or(
						Restrictions.and(Restrictions.eq("StaffList", staffList), Subqueries.propertyEq("SlPositionUniqueId", dc)), //$NON-NLS-1$ //$NON-NLS-2$
						Restrictions.eq("PositionFill", positionFill)))); //$NON-NLS-1$
	}
	
	private static void createCalcListAlias(Criteria criteria) {
		criteria.createAlias("CalcListSection", "cls", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
		criteria.createAlias("cls.CalcList", "cl", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static void createPositionFillAlias(Criteria criteria) {
		criteria.createAlias("cl.PositionFill", "pf", JoinType.LEFT_JOIN); //$NON-NLS-1$ //$NON-NLS-2$
	}

	static class TariffScaleValueResultListener implements BusinessAddinListener<BigDecimal> {

		private BigDecimal result = BigDecimal.ZERO;

		public TariffScaleValueResultListener() {
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#aborted(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void aborted(BusinessAddinEvent<BigDecimal> event) {

		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#completed(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void completed(BusinessAddinEvent<BigDecimal> event) {
			result = event.getResult() == null ? BigDecimal.ZERO : event.getResult();
		}

	}
	
	static class WorkNormResult {

		Integer daysNorm;
		Integer hoursNorm;

		public WorkNormResult() {
		}

		public WorkNormResult(Integer daysNorm, Integer hoursNorm) {
			this.daysNorm = daysNorm;
			this.hoursNorm = hoursNorm;
		}
	}

}
