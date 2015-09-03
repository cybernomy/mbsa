/*
 * CalcListServiceBean.java
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PersonnelConfig;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.personnelref.model.TariffingCategory;
import com.mg.merp.personnelref.support.PersonnelrefUtils;
import com.mg.merp.salary.CalcListFeeParamServiceLocal;
import com.mg.merp.salary.CalcListFeeServiceLocal;
import com.mg.merp.salary.CalcListSectionServiceLocal;
import com.mg.merp.salary.CalcListServiceLocal;
import com.mg.merp.salary.model.CalcList;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.CalcListFeeParam;
import com.mg.merp.salary.model.CalcListSection;
import com.mg.merp.salary.model.CalcListSectionRef;
import com.mg.merp.salary.model.CalculationParams;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.FeeRef;
import com.mg.merp.salary.model.FeeRefParam;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.ReplacedFee;
import com.mg.merp.salary.model.TariffingInFee;
import com.mg.merp.salary.model.TripleSumSign;

/**
 * Реализация бизнес-компонента "Расчетные листки"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListServiceBean.java,v 1.9 2009/03/17 09:18:49 safonov Exp $
 */
@Stateless(name="merp/salary/CalcListService") //$NON-NLS-1$
public class CalcListServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcList, Integer> implements CalcListServiceLocal {

	private final String REMOVE_FEES_QUERY_NAME = "Salary.CalcListServiceBean.removeFees"; //$NON-NLS-1$
	private final String CLEAR_SECTION_SUM_QUERY_NAME = "Salary.CalcListServiceBean.clearSectionSum"; //$NON-NLS-1$
	private final String SET_SECTION_SUM_QUERY_NAME = "Salary.CalcListServiceBean.setSectionSum"; //$NON-NLS-1$
	private final String OPEN_OR_CLOSE_CALC_LIST_QUERY_NAME = "Salary.CalcListServiceBean.openOrCloseCalcList"; //$NON-NLS-1$
	//private final String CLEAR_TOTAL_SUM_QUERY_NAME = "Salary.CalcListServiceBean.clearTotalSum"; //$NON-NLS-1$


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CalcList entity) {
		context.addRule(new MandatoryAttribute(entity, "PositionFill")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(CalcList entity) {
		addCalcListSections(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(CalcList entity) {
		prepareForStore(entity);
	}


	/* (non-Javadoc)
	 * @see com.mg.merp.salary.CalcListServiceLocal#addCalcLists(com.mg.merp.personnelref.model.PositionFill[], int)
	 */
	public void addCalcLists(PositionFill[] positionFills, int payRollId) {
		doAddCalcLists(positionFills, payRollId);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.salary.CalcListServiceLocal#calculate(java.io.Serializable[], boolean)
	 */
	public void calculate(Serializable[] calcListIds, boolean isClear) {
		doCalculate(calcListIds, isClear);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.salary.CalcListServiceLocal#setClosed(java.io.Serializable[], boolean)
	 */
	public void setClosed(Serializable[] calcListIds, boolean isClosed) {
		doSetClosed(calcListIds, isClosed);
	}

	protected void doSetClosed(Serializable[] calcListIds, boolean isClosed) {
		doBulkOperation(OPEN_OR_CLOSE_CALC_LIST_QUERY_NAME, new String[] {"isClosed", "calcListIds"}, new Object[] {isClosed, calcListIds}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected void doCalculate(Serializable[] calcListIds, boolean isClear) {
		if(calcListIds != null && calcListIds.length > 0) {
			CalculationParams calculationParams = getCalculationParams(((CalcList) load((Integer) calcListIds[0])).getPayRoll());
			for (int i = 0; i < calcListIds.length; i++)
				doPrepareCalcList((CalcList) load((Integer) calcListIds[i]), calculationParams, isClear);
		}
	}

	/**
	 * Подготовить расчетный листок для расчета
	 * @param calcList - расчетный листок
	 * @param calculationParams - параметры расчета
	 * @param isClear - признак "очистки" расчетного листка перед расчетом
	 */
	protected void doPrepareCalcList(CalcList calcList, CalculationParams calculationParams, boolean isClear) {
		if(calcList.getIsClosed())
			return;

		boolean isNeedCalcListToRecalc = false;

		if(isClear)
			clearCalcList(calcList); // удалим все н/у из расчетного листка (кроме не подлежащих пересчету)


		PositionFill positionFill = calcList.getPositionFill();

		CalculationParams actualCalculationParams = new CalculationParams(
				PersonnelrefUtils.getMaxDate(calculationParams.getPeriodBeginDate(), positionFill.getBeginDate()),
				PersonnelrefUtils.getMinDate(calculationParams.getPeriodEndDate(), positionFill.getEndDate()),
								calculationParams.getStaffList());

		// возьмем список всех н/у из лицевого счета сотрудника (с учетом даты действия или периода за который начисляется)
		List<FeeModel> feeModelList = getFeesFromPersonalAccount(positionFill.getPersonalAccount(), actualCalculationParams);
		if(feeModelList.size() == 0)
			return;

		PayRoll payRoll = calcList.getPayRoll();
		List<FeeModel> selectedFeeModelList = new ArrayList<FeeModel>();
		// цикл по всем начислениям
		for (FeeModel feeModel : feeModelList) {
			// не расчитываем этот образец, если тип ведомости задан и не совпадает с указанным в расчетной ведомости
			if(feeModel.getRollKind() != null && feeModel.getRollKind().getId() != payRoll.getRollKind().getId())
				continue;

			// не расчитываем этот образец, если исполняемая должность задана и не совпадает с указанной в расчетном листке
			if(feeModel.getPositionFill() != null && feeModel.getPositionFill().getId() != calcList.getPositionFill().getId())
				continue;

			// если указано, что считать только для базовой должности, то и должность из расчетного листа должна быть базовой
			if(feeModel.getUseBasicPosition() && !positionFill.getIsBasic())
				continue;

			// добавим в список реально действующих начислений
			selectedFeeModelList.add(feeModel);
		}

		List<CalcListFee> existingFeeList = getFeesFromCalcList(calcList);

		// цикл по реально действующим начислениям
		for(FeeModel selectedFeeModel : selectedFeeModelList) {
			// проверим существует ли в РЛ это начисление, если существует, то его не надо добавлять в РЛ
			if(isFeePresent(existingFeeList, selectedFeeModel))
				continue;

			// если указана конкретная сумма, то нет необходимости выполнять алгоритм
			if(selectedFeeModel.getSumma() != null)
				addFeeToCalcList(calcList, selectedFeeModel, actualCalculationParams);
			else // если алгоритм расчета указан, используем его
				if(selectedFeeModel.getFeeRef() != null && selectedFeeModel.getFeeRef().getCalcAlg() != null)
					isNeedCalcListToRecalc = doPrepareCalcListFee(calcList, positionFill, selectedFeeModel, selectedFeeModelList, actualCalculationParams);
		}

		if(isNeedCalcListToRecalc) {
			calcList.setIsCalculated(false);
			store(calcList);
		}
		else
			doPerformCalculation(calcList, calculationParams);
	}

	/**
	 * Выполнить расчет
	 * @param calcList - расчетный листок
	 * @param calculationParams - параметры расчета
	 */
	protected void doPerformCalculation(CalcList calcList, CalculationParams calculationParams) {
		if(calcList.getNeedParams())
			return;

		List<CalcListFee> calcListFees = getFeesFromCalcList(calcList);
		RoundContext currencyPrecisionRoundContext = getCurrencyPrecisionRoundContext();

		// цикл по начислениям
		for(CalcListFee calcListFee : calcListFees) {
			if(calcListFee.getDontRecalc())
				continue;

			// если задана сумма н/у в лицевом счете, то подставить ее
			if(calcListFee.getFeeModel() != null && calcListFee.getFeeModel().getSumma() != null) {
				calcListFee.setSumma(calcListFee.getFeeModel().getSumma());
				getCalcListFeeService().store(calcListFee);
			} // если есть алгоритм, то рассчитать
			else if(calcListFee.getFeeModel().getFeeRef().getCalcAlg() != null) {
					Map<String, Object> calcContextParams = getCalcContextParams(calcList, calcListFee, calculationParams);

					List<CalcListFeeParam> calcListFeeParams = getCalcListFeeParamsFromCalcListFee(calcListFee);
					// цикл по параметрам
					for(CalcListFeeParam calcListFeeParam : calcListFeeParams) {
						FeeRefParam feeRefParam = calcListFeeParam.getFeeRefParam();
						// если есть алгоритм, то расчитать
						if(feeRefParam.getCalcAlg() != null) {
							if(!(feeRefParam.getCalcOnce() && calcListFee.getIsCalculated())) {
								FeeParamBusinessAddinListener feeParamBusinessAddinListener = new FeeParamBusinessAddinListener();
								BusinessAddinEngineLocator.locate().perform(feeRefParam.getCalcAlg(), calcContextParams, feeParamBusinessAddinListener);
								calcListFeeParam.setParamValue(feeParamBusinessAddinListener.result.toString());
								getCalcListFeeParamService().store(calcListFeeParam);
							}
						}
					}
					FeeBusinessAddinListener feeBusinessAddinListener = new FeeBusinessAddinListener();
					BusinessAddinEngineLocator.locate().perform(calcListFee.getFeeModel().getFeeRef().getCalcAlg(), calcContextParams, feeBusinessAddinListener);
					proccessCalcListFeeAfterCalculation(calcListFee, feeBusinessAddinListener.result, currencyPrecisionRoundContext);
				}
		}
		calcList.setIsCalculated(true);
		store(calcList);
	}

	/**
	 * Получить список параметров начисления/удержания расчетного листка
	 * @param calcListFee - начисление/удержание расчетного листка
	 * @return список параметров начисления/удержания расчетного листка
	 */
	protected List<CalcListFeeParam> getCalcListFeeParamsFromCalcListFee(CalcListFee calcListFee) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFeeParam.class)
												.createAlias("FeeRefParam", "frp", JoinType.INNER_JOIN)						 //$NON-NLS-1$ //$NON-NLS-2$
												.add(Restrictions.eq("CalcListFee", calcListFee)) //$NON-NLS-1$
												.addOrder(Order.asc("frp.Priority"))); //$NON-NLS-1$
	}

	/**
	 * Получить параметры контекста выполнения расчета
	 * @param calcList - расчетный листок
	 * @param calcListFee - начисление/удержание расчетного листка
	 * @param calculationParams - дополнительные параметры, используемые в контексте выполнения расчета
	 * @return параметры контекста выполнения расчета
	 */
	protected Map<String, Object> getCalcContextParams(CalcList calcList, CalcListFee calcListFee, CalculationParams calculationParams) {
		Map<String, Object> calcContextParams = new HashMap<String, Object>();

		calcContextParams.put(SalaryBusinessAddin.STAFF_LIST_PARAM, calculationParams.getStaffList());
		calcContextParams.put(SalaryBusinessAddin.FEE_MODEL_PARAM, calcListFee.getFeeModel());
		calcContextParams.put(SalaryBusinessAddin.PAY_ROLL_PARAM, calcList.getPayRoll());
		calcContextParams.put(SalaryBusinessAddin.CALC_LIST_PARAM, calcList);
		calcContextParams.put(SalaryBusinessAddin.CALC_LIST_FEE_PARAM, calcListFee);
		calcContextParams.put(SalaryBusinessAddin.POSITION_FILL_PARAM, calcList.getPositionFill());
		calcContextParams.put(SalaryBusinessAddin.PERSONAL_ACCOUNT_PARAM, calcList.getPositionFill().getPersonalAccount());
		calcContextParams.put(SalaryBusinessAddin.BEGIN_DATE_PARAM, calcListFee.getBeginDate());
		calcContextParams.put(SalaryBusinessAddin.END_DATE_PARAM, calcListFee.getEndDate());
		calcContextParams.put(SalaryBusinessAddin.PERIOD_BEGIN_DATE_PARAM, calculationParams.getPeriodBeginDate());
		calcContextParams.put(SalaryBusinessAddin.PERIOD_END_DATE_PARAM, calculationParams.getPeriodEndDate());

		if(calcListFee.getCostsAnl1() != null)
			calcContextParams.put(SalaryBusinessAddin.COSTS_ANL_1_PARAM, calcListFee.getCostsAnl1());
		if(calcListFee.getCostsAnl2() != null)
			calcContextParams.put(SalaryBusinessAddin.COSTS_ANL_2_PARAM, calcListFee.getCostsAnl2());
		if(calcListFee.getCostsAnl3() != null)
			calcContextParams.put(SalaryBusinessAddin.COSTS_ANL_3_PARAM, calcListFee.getCostsAnl3());
		if(calcListFee.getCostsAnl4() != null)
			calcContextParams.put(SalaryBusinessAddin.COSTS_ANL_4_PARAM, calcListFee.getCostsAnl4());
		if(calcListFee.getCostsAnl5() != null)
			calcContextParams.put(SalaryBusinessAddin.COSTS_ANL_5_PARAM, calcListFee.getCostsAnl5());

		return calcContextParams;
	}

	/**
	 * Обработать начисление/удержание после выполнения расчета
	 * @param calcListFee - начисление/удержание
	 * @param calcResult - результаты расчета
	 * @param currencyPrecisionRoundContext - контекст округления денежных величин
	 */
	protected void proccessCalcListFeeAfterCalculation(CalcListFee calcListFee, final FeeBAiResult calcResult, RoundContext currencyPrecisionRoundContext) {
		updateCalcListFeeByCalculationResult(calcListFee, calcResult, currencyPrecisionRoundContext);
		if(MathUtils.compareToZero(calcListFee.getSumma()) == 0 && !calcListFee.getFeeModel().getFeeRef().getIsZeroIncluded())
			getCalcListFeeService().erase(calcListFee);
		else
			getCalcListFeeService().store(calcListFee);
	}

	/**
	 * Изменить начисление/удержание в соответсвии с результатами расчета
	 * @param calcListFee - начисление/удержание
	 * @param calcResult - результаты расчета
	 * @param currencyPrecisionRoundContext - контекст округления денежных величин
	 */
	protected void updateCalcListFeeByCalculationResult(CalcListFee calcListFee, final FeeBAiResult calcResult, RoundContext currencyPrecisionRoundContext) {
		calcListFee.setSumma(MathUtils.round(calcResult.getSum(), currencyPrecisionRoundContext));

		if(calcResult.getBeginDate() != null)
			calcListFee.setBeginDate(calcResult.getBeginDate());
		if(calcResult.getEndDate() != null)
			calcListFee.setEndDate(calcResult.getEndDate());

		if(calcResult.getPeriodBeginDate() != null)
			calcListFee.setPeriodBeginDate(calcResult.getPeriodBeginDate());
		if(calcResult.getPeriodEndDate() != null)
			calcListFee.setPeriodEndDate(calcResult.getPeriodEndDate());

		if(calcResult.getCostsAnl1() != null)
			calcListFee.setCostsAnl1(calcResult.getCostsAnl1());
		if(calcResult.getCostsAnl2() != null)
			calcListFee.setCostsAnl2(calcResult.getCostsAnl2());
		if(calcResult.getCostsAnl3() != null)
			calcListFee.setCostsAnl3(calcResult.getCostsAnl3());
		if(calcResult.getCostsAnl4() != null)
			calcListFee.setCostsAnl4(calcResult.getCostsAnl4());
		if(calcResult.getCostsAnl5() != null)
			calcListFee.setCostsAnl5(calcResult.getCostsAnl5());

		calcListFee.setIsCalculated(true);
	}

	/**
	 * Получить контекст округления денежных величин (из конфигурации модуля "Справочник персонала")
	 * @return контекст округления денежных величин
	 */
	protected RoundContext getCurrencyPrecisionRoundContext() {
		return new RoundContext(getModuleConfiguration().getCurrencyPrec());
	}


	protected List<FeeModel> getFeesFromPersonalAccount(PersonalAccount personalAccount, CalculationParams actualCalculationParams) {
		Criteria criteria = OrmTemplate.createCriteria(FeeModel.class)
					.createAlias("FeeRef", "sfr", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("CalcPeriod", "pcp", JoinType.LEFT_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("PersonalAccount", personalAccount)) //$NON-NLS-1$
					.add(Restrictions.or(
							Restrictions.and(
									Restrictions.disjunction(
											Restrictions.between("BeginDate", actualCalculationParams.getPeriodBeginDate(), actualCalculationParams.getPeriodEndDate()), //$NON-NLS-1$
											Restrictions.between("EndDate", actualCalculationParams.getPeriodBeginDate(), actualCalculationParams.getPeriodEndDate()), //$NON-NLS-1$
											Restrictions.and(
													Restrictions.le("BeginDate", actualCalculationParams.getPeriodBeginDate()), //$NON-NLS-1$
													Restrictions.ge("EndDate", actualCalculationParams.getPeriodBeginDate()))), //$NON-NLS-1$
									Restrictions.isNull("CalcPeriod")),  //$NON-NLS-1$
							Restrictions.eq("pcp.BeginDate", actualCalculationParams.getPeriodBeginDate()))) //$NON-NLS-1$
					.addOrder(Order.desc("sfr.Priority")); //$NON-NLS-1$

		return OrmTemplate.getInstance().findByCriteria(criteria);
	}

	/**
	 * Получить список начислений/удержаний расчетного листка
	 * @param calcList - расчетный листок
	 * @return список начислений/удержаний расчетного листка
	 */
	protected List<CalcListFee> getFeesFromCalcList(CalcList calcList) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFee.class)
												.createAlias("CalcListSection", "scls", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
												.createAlias("FeeModel", "sfm", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
												.createAlias("sfm.FeeRef", "sfr", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
												.add(Restrictions.eq("scls.CalcList", calcList)) //$NON-NLS-1$
												.addOrder(Order.asc("sfr.Priority"))); //$NON-NLS-1$
	}

	private boolean isFeePresent(List<CalcListFee> existingFeeList, FeeModel feeModel) {
		boolean isPresent = false;
		for(CalcListFee existingFee : existingFeeList) {
			if(existingFee.getFeeModel().getId() == feeModel.getId()) {
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}

	protected void addFeeToCalcList(CalcList calcList, FeeModel feeModel, CalculationParams calculationParams) {
		CalcListFeeServiceLocal сalcListFeeService = getCalcListFeeService();
		createCalcListFee(initializeCalcListFee(feeModel, getCalcListSectionByCalcListAndReferencedCalcListSection(calcList, feeModel.getFeeRef().getCalcListSectionRef()), calculationParams.getPeriodBeginDate(), calculationParams.getPeriodEndDate(), сalcListFeeService), сalcListFeeService);
	}

	protected CalcListFee initializeCalcListFee(FeeModel feeModel, CalcListSection calcListSection, Date periodBeginDate, Date periodEndDate, CalcListFeeServiceLocal сalcListFeeService) {
		CalcListFee calcListFee = сalcListFeeService.initialize();
		calcListFee.setCalcListSection(calcListSection);
		calcListFee.setFeeModel(feeModel);
		calcListFee.setBeginDate(PersonnelrefUtils.getMaxDate(feeModel.getBeginDate(), periodBeginDate));
		calcListFee.setEndDate(PersonnelrefUtils.getMinDate(feeModel.getEndDate(), periodEndDate));
		calcListFee.setPeriodBeginDate(periodBeginDate);
		calcListFee.setPeriodEndDate(periodEndDate);
		calcListFee.setNeedParams(false);
		calcListFee.setIsCalculated(true);
		calcListFee.setDontRecalc(false);
		calcListFee.setSumma(feeModel.getSumma());
		return calcListFee;
	}

	protected CalcListFee initializeCalcListFee(FeeModel feeModel, CalcListSection calcListSection) {
		CalcListFee calcListFee = getCalcListFeeService().initialize();
		calcListFee.setCalcListSection(calcListSection);
		calcListFee.setFeeModel(feeModel);
		calcListFee.setNeedParams(false);
		calcListFee.setIsCalculated(false);
		calcListFee.setDontRecalc(false);
		return calcListFee;
	}

	protected void createCalcListFee(CalcListFee calcListFee, CalcListFeeServiceLocal сalcListFeeService) {
		сalcListFeeService.create(calcListFee);
	}

	/**
	 * Получить раздел расчетного листка
	 * @param calcList - расчетный листок
	 * @param referenceCalcListSection - раздел расчетного листка из справочника
	 * @return раздел расчетного листка
	 */
	protected CalcListSection getCalcListSectionByCalcListAndReferencedCalcListSection(CalcList calcList, CalcListSectionRef referenceCalcListSection) {
		List<CalcListSection> calcListSections = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListSection.class)
									.add(Restrictions.eq("CalcList", calcList)) //$NON-NLS-1$
									.add(Restrictions.eq("CalcListSectionRef", referenceCalcListSection))); //$NON-NLS-1$

		if(!calcListSections.isEmpty())
			return calcListSections.get(0);
		else
			return null;
	}

	/**
	 * Очистить расчетный листок
	 * @param calcList - расчетный листок
	 */
	protected void clearCalcList(CalcList calcList) {
		final String PARAM_NAME = "calcList"; //$NON-NLS-1$

		doBulkOperation(REMOVE_FEES_QUERY_NAME, new String[] {PARAM_NAME}, new Object[] {calcList});
		doBulkOperation(CLEAR_SECTION_SUM_QUERY_NAME, new String[] {PARAM_NAME}, new Object[] {calcList});
		//doBulkOperation(CLEAR_TOTAL_SUM_QUERY_NAME, new String[] {PARAM_NAME}, new Object[] {calcList.getId()});
	}

	protected void doBulkOperation(String queryName, String[] queryParamNames, Object[] queryParamValues) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery(queryName, queryParamNames, queryParamValues);
	}

	/**
	 * Подготовить начисления/удержания для проведения расчета
	 * @param calcList - расчетный листок
	 * @param positionFill - занимаемая должность
	 * @param feeModel - образец начисления/удержания
	 * @param feeModelList - список образцов начислений/удержаний
	 * @param calculationParams - параметры расчета
	 * @return true - если требуется перерасчет
	 */
	protected boolean doPrepareCalcListFee(CalcList calcList, PositionFill positionFill, FeeModel feeModel, List<FeeModel> feeModelList, CalculationParams calculationParams) {
		boolean isNeedRecalc = false;
		Date feeBeginDate;
		Date feeEndDate;

		// если период в образце н/у не указан, то учитываем расчетный период
		if(feeModel.getCalcPeriod() == null) {
			feeBeginDate = PersonnelrefUtils.getMaxDate(feeModel.getBeginDate(), calculationParams.getPeriodBeginDate());
			feeEndDate = PersonnelrefUtils.getMinDate(feeModel.getEndDate(), calculationParams.getPeriodEndDate());
		}
		else { // иначе рассчитать строго по диапазону действия н/у
			feeBeginDate = feeModel.getBeginDate();
			feeEndDate = feeModel.getEndDate();
		}

		List<DateInterval> dateIntervals1 = new ArrayList<DateInterval>(); // интервалы без вытеснений
		// выделим интервалы на которых начиление не вытесняется
		if(!getNotReplacedIntervals(feeModel, feeModelList, feeBeginDate, feeEndDate, dateIntervals1))
			return isNeedRecalc;

		List<DateInterval> dateIntervals2 = new ArrayList<DateInterval>(); // интервалы постоянства тарифов
		for(int i = 0; i < dateIntervals1.size(); i++) {
			// поверим, все ли тарифы есть для данного алгоритма в данном периоде
			if(!getTariffsPermanencyIntervals(feeModel, calculationParams.getStaffList(), positionFill, dateIntervals1.get(i).beginDate, dateIntervals1.get(i).endDate, dateIntervals2))
				return isNeedRecalc;

			// выберем из справочника те параметры, которых нет в образце
			List<Integer> feeRefParamIds = getParamIdListFromFeeRef(feeModel);

			// цикл по временным интервалам
			for(int j = 0; j < dateIntervals2.size(); j++) {
				// создадим начисление в расчетном листке (с теми параметрами, которые есть в образце)
				CalcListFee calcListFee = initializeCalcListFee(feeModel, getCalcListSectionByCalcListAndReferencedCalcListSection(calcList, feeModel.getFeeRef().getCalcListSectionRef()));
				createCalcListFee(calcListFee, getCalcListFeeService());

				// добавление параметров
				for(int k = 0; k < feeRefParamIds.size(); k++) {
					FeeRefParam feeRefParam = ServerUtils.getPersistentManager().find(FeeRefParam.class, feeRefParamIds.get(k));
					CalcListFeeParam calcListFeeParam = getCalcListFeeParamService().initialize();

					// если есть алгоритм, то установим нулевое значение параметра
					if(feeRefParam.getCalcAlg() != null)
						calcListFeeParam.setParamValue(StringUtils.EMPTY_STRING);
					else { // если нет, то установим пустой параметр и выставим признак "требуется перерасчет"
						calcListFeeParam.setParamValue(null);
						isNeedRecalc = true;
					}

					calcListFeeParam.setCalcListFee(calcListFee);
					calcListFeeParam.setFeeRefParam(feeRefParam);

					createCalcListFeeParam(calcListFeeParam);
				}

				// запись начисления
				makeAccrual(calcListFee, feeModel, dateIntervals2.get(j).beginDate, dateIntervals2.get(j).endDate, calculationParams.getPeriodBeginDate(), calculationParams.getPeriodEndDate());
				storeCalcListFee(calcListFee);
			}
		}
		return isNeedRecalc;
	}

	protected void makeAccrual(CalcListFee calcListFee, FeeModel feeModel, Date beginDate, Date endDate, Date periodBeginDate, Date periodEndDate) {
		calcListFee.setSumma(BigDecimal.ZERO);
		calcListFee.setBeginDate(beginDate);
		calcListFee.setEndDate(endDate);
		calcListFee.setPeriodBeginDate(periodBeginDate);
		calcListFee.setPeriodEndDate(periodEndDate);
		calcListFee.setCostsAnl1(feeModel.getCostsAnl1());
		calcListFee.setCostsAnl2(feeModel.getCostsAnl2());
		calcListFee.setCostsAnl3(feeModel.getCostsAnl3());
		calcListFee.setCostsAnl4(feeModel.getCostsAnl4());
		calcListFee.setCostsAnl5(feeModel.getCostsAnl5());
	}

	/**
	 * Получить из справочника список идентификаторов тех параметров, которых нет в образце начисления/удержания
	 * @param feeModel - образец начислений/удержаний
	 * @return список идентификаторов тех параметров, которых нет в образце начисления/удержания
	 */
	private List<Integer> getParamIdListFromFeeRef(FeeModel feeModel) {
		Object[] queryParams = new Object[] {feeModel.getId()};
		StringBuilder queryText = new StringBuilder()
		.append("select sfrp.id ")  //$NON-NLS-1$
		.append("from sal_fee_ref_param sfrp ") //$NON-NLS-1$
		.append("join sal_fee_ref sfr on sfr.id = sfrp.fee_ref_id ") //$NON-NLS-1$
		.append("join sal_fee_model sfm on sfm.fee_ref_id = sfr.id ") //$NON-NLS-1$
		.append("left join sal_fee_model_param sfmp on ") //$NON-NLS-1$
		.append("(sfmp.fee_ref_param_id = sfrp.id) ") //$NON-NLS-1$
		.append("and (sfmp.fee_model_id = sfm.id) ") //$NON-NLS-1$
		.append("where (sfm.id = ?) ") //$NON-NLS-1$
		.append("and (sfmp.id is null) ") //$NON-NLS-1$
		.append("order by sfrp.priority"); //$NON-NLS-1$

		return JdbcTemplate.getInstance().query(queryText.toString(), queryParams, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return JdbcUtils.getIntegerValue(rs, 1);
			}
		});
	}

	private boolean getNotReplacedIntervals(FeeModel feeModel, List<FeeModel> feeModelList, Date intervalBeginDate, Date intervalEndDate, List<DateInterval> dateIntervals) {
		boolean result = true;
		// границы действия начислений
		int feeBeginDate;
		int feeEndDate;
		// если TRUE, то в этот день не вытесняется
		boolean notReplacedDays[];

		// загрузим из справочника список начислений, которые вытесняют данное
		List<ReplacedFee> replacedFeeList = getReplacedFeeList(feeModel.getFeeRef());

		// нет вытесняющих начислений
		if(replacedFeeList.size() == 0) {
			dateIntervals.add(new DateInterval(intervalBeginDate, intervalEndDate));
			return result;
		}

		// в начальном состоянии весь интервал не вытеснен
		notReplacedDays = new boolean[(int) DateTimeUtils.getDaysBetween(intervalEndDate, intervalBeginDate) + 1];
		for(int i = 0; i < notReplacedDays.length; i++)
			notReplacedDays[i] = true;

		// составим список невытесненных дней
		for(int i = 0; i < feeModelList.size(); i++) {
			for(int j = 0; j < replacedFeeList.size(); j++) {
				// найдем в лицевом счете вытесняющие начисления
				if(replacedFeeList.get(j).getFeeRef().getId() == feeModelList.get(i).getFeeRef().getId()) {
					FeeModel currentFeeModel = feeModelList.get(i);
					feeBeginDate = getIntDate(PersonnelrefUtils.getMaxDate(currentFeeModel.getBeginDate(), intervalBeginDate));
					feeEndDate = getIntDate(PersonnelrefUtils.getMinDate(currentFeeModel.getEndDate(), intervalEndDate));
					// установим признак, что день вытеснен
					for(int k = feeBeginDate; k <= feeEndDate; k++)
						notReplacedDays[k - getIntDate(intervalBeginDate)] = false;
				}
			}
		}

		// разобъем список на интервалы дат
		boolean intervalIsOpen = false;
		int j = 0;
		for(int i = 0; i < notReplacedDays.length; i++) {
			if(!notReplacedDays[i] && intervalIsOpen) {
				// закроем интервал
				dateIntervals.get(j - 1).endDate = DateTimeUtils.incDay(intervalBeginDate, i - 1);
				intervalIsOpen = false;
			}
			if(notReplacedDays[i] && !intervalIsOpen) {
				// откроем интервал
				j++;
				dateIntervals.add(new DateInterval(DateTimeUtils.incDay(intervalBeginDate, i), null)); // BeginDate
				intervalIsOpen = true;
			}
		}
		// закроем последний интервал
		if(intervalIsOpen)
			dateIntervals.get(j - 1).endDate = intervalEndDate;

		if(j == 0)
			result = false;

		return result;
	}

	private boolean getTariffsPermanencyIntervals(FeeModel feeModel, StaffList staffList,  PositionFill positionFill, Date intervalBeginDate, Date intervalEndDate, List<DateInterval> dateIntervals) {
		boolean result = true;
		// возьмем список тарифов, действующих в текущий расчетный период
		List<PeriodTarif> periodTarifs = getGetUsedTariffing(feeModel.getFeeRef(), positionFill, intervalBeginDate, intervalEndDate, staffList);

		// проверим есть ли все нужные тарифы в лицевом счете (без учета дат)
		for(PeriodTarif periodTarif : periodTarifs) {
			if(periodTarif.tarifId == null) { // один из тарифов вообще отсутствует
				result = false;
				break;
			}
		}
		if(!result)
			return result;

		// возьмем список категорий тарификации должности, используемых в начислении
		List<TariffingCategory> categories = getTarrifingCategories(feeModel.getFeeRef());

		// тарифы не используются
		if(categories.isEmpty()) {
			dateIntervals.add(new DateInterval(intervalBeginDate, intervalEndDate));
			return result;
		}

		Integer[] currentTariffsId = new Integer[categories.size()];
		Integer[] yesterdayTariffsId = new Integer[categories.size()];

		// определим интервалы постоянства тарифов
		// для этого в пределах расчетного периода сделаем цикл по дням
		Date currentDate = intervalBeginDate;
		boolean intervalIsOpen = false;
		int j = 0;
		while(currentDate.compareTo(intervalEndDate) <= 0) {
			boolean isMissingTariff = false;
			boolean isChangedTariff = false;
			// заполним список текущих тарифов
			for(int i = 0; i < categories.size(); i++) {
				currentTariffsId[i] = findTariff(currentDate, categories.get(i), periodTarifs);
				if(currentTariffsId[i] == null)
					isMissingTariff = true;
				if(currentTariffsId[i].compareTo(yesterdayTariffsId[i] == null ? 0 : yesterdayTariffsId[i]) != 0)
					isChangedTariff = true;
			}

			// проанализируем список
			if(isMissingTariff) { // нет одного из тарифов
				if(intervalIsOpen) {
					// закроем текущий интервал
					dateIntervals.get(j - 1).endDate = DateTimeUtils.incDay(currentDate, -1); // EndDate
					intervalIsOpen = false;
				}
			}
			else if(isChangedTariff) { // один из тарифов изменился
				if(intervalIsOpen) {
					// закроем текущий интервал
					dateIntervals.get(j - 1).endDate = DateTimeUtils.incDay(currentDate, -1); // EndDate
				}
				// откроем новый интервал
				j++;
				dateIntervals.add(new DateInterval(currentDate, null)); // BeginDate
				intervalIsOpen = true;
			}
			// установим курсор на следующий день
			for(int i = 0; i < categories.size(); i++)
				yesterdayTariffsId[i] = currentTariffsId[i];
			currentDate = DateTimeUtils.incDay(currentDate, 1);
		}
		// закроем последний интервал
		if(intervalIsOpen)
			dateIntervals.get(j - 1).endDate = DateTimeUtils.incDay(currentDate, -1); // EndDate

		if(j == 0)
			result = false;

		return result;
	}

	private int findTariff(Date currentDate, TariffingCategory tariffingCategory, List<PeriodTarif> periodTarifs) {
		Integer tariffId = null;
		for(PeriodTarif periodTarif : periodTarifs) {
			if((periodTarif.categoryId.compareTo(tariffingCategory.getId()) == 0)
					&& (currentDate.compareTo(periodTarif.beginDate) >= 0)
					&& (currentDate.compareTo(periodTarif.endDate) <= 0)) {
				tariffId = periodTarif.tarifId;
				break;
			}
		}
		return tariffId;
	}

	private List<TariffingCategory> getTarrifingCategories(FeeRef referenceFee) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(TariffingInFee.class)
												.setProjection(Projections.groupProperty("TariffingCategory")) //$NON-NLS-1$
												.add(Restrictions.eq("FeeRef", referenceFee))); //$NON-NLS-1$
	}

	private List<PeriodTarif> getGetUsedTariffing(FeeRef referenceFee, PositionFill positionFill, Date feeBeginDate, Date feeEndDate, StaffList staffList) {
		Object[] queryParams = new Object[] {
						feeBeginDate,
						feeEndDate,
						feeBeginDate,
						feeEndDate,
						feeBeginDate,
						feeEndDate,
						staffList.getId(),
						positionFill.getId(),
						positionFill.getId(),
						referenceFee.getId()};

		StringBuilder queryText = new StringBuilder()
		.append("select ptc.id category_id, pt.id tariffing_id, pt.begindate, pt.enddate ") //$NON-NLS-1$
		.append("from sal_tariffing_in_fee stif ") //$NON-NLS-1$
		.append("join pref_tariffing_category ptc on ptc.id = stif.tariffing_category_id ") //$NON-NLS-1$
		.append("left join pref_tariffing pt on ") //$NON-NLS-1$
		.append("(pt.category_id = ptc.id) ") //$NON-NLS-1$
		.append("and ( ") //$NON-NLS-1$
		.append("(pt.begindate between ? and ?) or ") //$NON-NLS-1$
		.append("(pt.enddate between ? and ?) or ") //$NON-NLS-1$
		.append("(? between pt.begindate and pt.enddate) or ") //$NON-NLS-1$
		.append("(? between pt.begindate and pt.enddate) ") //$NON-NLS-1$
		.append(") ") //$NON-NLS-1$
		.append("and ( ") //$NON-NLS-1$
		.append("( ") //$NON-NLS-1$
		.append("(pt.stafflist_id = ?) ") //$NON-NLS-1$
		.append("and (pt.sl_position_unique_id = ") //$NON-NLS-1$
		.append("(select spf2.sl_position_unique_id from sal_position_fill spf2 where spf2.id = ?) ") //$NON-NLS-1$
		.append(") ") //$NON-NLS-1$
		.append(") ") //$NON-NLS-1$
		.append("or (pt.positionfill_id = ?) ") //$NON-NLS-1$
		.append(") ") //$NON-NLS-1$
		.append("where (stif.fee_ref_id = ?)"); //$NON-NLS-1$

		List<PeriodTarif> periodTarifs = JdbcTemplate.getInstance().query(queryText.toString(), queryParams, new RowMapper<PeriodTarif>() {
			public PeriodTarif mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new PeriodTarif(JdbcUtils.getIntegerValue(rs, 1), JdbcUtils.getIntegerValue(rs, 2), rs.getDate(3), rs.getDate(4));
			}
		});

		return periodTarifs;
	}

	private int getIntDate(Date date) {
		Calendar calendar = Calendar.getInstance(ServerUtils.getUserLocale());
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	private List<ReplacedFee> getReplacedFeeList(FeeRef referenceFee) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ReplacedFee.class).add(Restrictions.eq("FeeRef", referenceFee))); //$NON-NLS-1$
	}

	protected CalculationParams getCalculationParams(PayRoll payRoll) {
		return new CalculationParams(payRoll.getCalcPeriod().getBeginDate(), payRoll.getCalcPeriod().getEndDate(), payRoll.getCalcPeriod().getStaffList());
	}

	protected void doAddCalcLists(PositionFill[] positionFills, int payRollId) {
		if(positionFills != null && positionFills.length > 0) {
			CalcList[] calcLists = new CalcList[positionFills.length];
			PayRoll payRoll = ServerUtils.getPersistentManager().find(PayRoll.class, payRollId);
			for(int i = 0; i < positionFills.length; i++)
				calcLists[i] = initializeCalcList(positionFills[i], payRoll);
			createCalcLists(calcLists);
		}
	}

	protected CalcList initializeCalcList(PositionFill positionFill, PayRoll payRoll) {
		CalcList calcList = initialize();
		calcList.setPositionFill(positionFill);
		calcList.setPayRoll(payRoll);
		calcList.setNeedParams(false);
		calcList.setIsCalculated(false);
		calcList.setIsClosed(false);
		calcList.setTotalSumma(BigDecimal.ZERO);
		calcList.setPositiveSumma(BigDecimal.ZERO);
		calcList.setNegativeSumma(BigDecimal.ZERO);
		calcList.setNeutralSumma(BigDecimal.ZERO);
		return calcList;
	}

	protected void createCalcLists(CalcList[] calcLists) {
		if(calcLists != null && calcLists.length > 0)
			for(int i = 0; i < calcLists.length; i++)
				create(calcLists[i]);
	}


	/**
	 * Добавить список разделов расчетного листка (из справочника)
	 * @param calcList - расчетный листок
	 */
	protected void addCalcListSections(CalcList calcList) {
		CalcListSectionServiceLocal calcListSectionService = getCalcListSectionService();
		List<CalcListSectionRef> referenceCalcListSections = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListSectionRef.class).add(Restrictions.isNotNull("Id"))); //$NON-NLS-1$

		CalcListSection[] calcListSections = new CalcListSection[referenceCalcListSections.size()];
		for (int j = 0; j < referenceCalcListSections.size(); j++)
			calcListSections[j] = initializeCalcListSection(calcList, referenceCalcListSections.get(j), calcListSectionService);

		createCalcListSections(calcListSections, calcListSectionService);
	}

	protected CalcListSection initializeCalcListSection(CalcList calcList, CalcListSectionRef referenceCalcListSection, CalcListSectionServiceLocal calcListSectionService) {
		CalcListSection calcListSection = calcListSectionService.initialize();
		calcListSection.setCalcList(calcList);
		calcListSection.setCalcListSectionRef(referenceCalcListSection);
		calcListSection.setTotalSumma(BigDecimal.ZERO);
		return calcListSection;
	}

	protected void createCalcListSections(CalcListSection[] calcListSections, CalcListSectionServiceLocal calcListSectionService) {
		if(calcListSections != null && calcListSections.length > 0)
			for(int i = 0; i < calcListSections.length; i++)
				calcListSectionService.create(calcListSections[i]);
	}

	protected void prepareForStore(CalcList calcList) {
		if(hasNonCalculatedFees(calcList))
			calcList.setNeedParams(true);
		else
			calcList.setNeedParams(false);
		calculateTotalSum(calcList);
	}

	protected void calculateTotalSum(CalcList calcList) {
		BigDecimal calcListSum = BigDecimal.ZERO;
		BigDecimal positiveSum = BigDecimal.ZERO;
		BigDecimal negativeSum = BigDecimal.ZERO;
		BigDecimal neutralSum = BigDecimal.ZERO;

		List<CalcListSection> calcListSections = getAllSections(calcList);
		for(CalcListSection calcListSection : calcListSections) {
			BigDecimal sectionSum = BigDecimal.ZERO;

			List<CalcListFee> calcListSectionFees = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFee.class)
										.add(Restrictions.eq("CalcListSection", calcListSection))); //$NON-NLS-1$

			for(CalcListFee calcListSectionFee : calcListSectionFees) {
				TripleSumSign sumSign = calcListSectionFee.getFeeModel().getFeeRef().getSumSign();
				if(sumSign != null) {
					if(TripleSumSign.Plus == sumSign)
						sectionSum = sectionSum.add(calcListSectionFee.getSumma());
					if(TripleSumSign.Minus == sumSign)
						sectionSum = sectionSum.subtract(calcListSectionFee.getSumma());
				}
			}

			setSectionSum(calcListSection, sectionSum);

			TripleSumSign sumSign = calcListSection.getCalcListSectionRef().getSumSign();
			if(TripleSumSign.Plus == sumSign) {
				calcListSum = calcListSum.add(sectionSum);
				positiveSum = positiveSum.add(sectionSum);
			}

			if(TripleSumSign.Minus == sumSign) {
				calcListSum = calcListSum.subtract(sectionSum);
				negativeSum = negativeSum.add(sectionSum);
			}

			if(TripleSumSign.NONE == sumSign)
				neutralSum = neutralSum.add(sectionSum);
		}
		calcList.setTotalSumma(calcListSum);
		calcList.setPositiveSumma(positiveSum);
		calcList.setNegativeSumma(negativeSum);
		calcList.setNeutralSumma(neutralSum);
	}

	protected void setSectionSum(CalcListSection calcListSection, BigDecimal totalSum) {
		doBulkOperation(SET_SECTION_SUM_QUERY_NAME, new String[] {"totalSum", "calcListSectionId"}, new Object[] {totalSum, calcListSection.getId()}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected List<CalcListSection> getAllSections(CalcList calcList) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListSection.class)
												.add(Restrictions.eq("CalcList", calcList))); //$NON-NLS-1$
	}

	protected boolean hasNonCalculatedFees(CalcList calcList) {
		List<CalcListFee> calcListFees = getFeesFromCalcList(calcList);
		for (CalcListFee calcListFee : calcListFees) {
			if(calcListFee.getNeedParams() != false)
				return true;
		}
		return false;
	}


	protected void createCalcListFeeParam(CalcListFeeParam calcListFeeParam) {
		getCalcListFeeParamService().create(calcListFeeParam);
	}

	protected void storeCalcListFee(CalcListFee calcListFee) {
		getCalcListFeeService().store(calcListFee);
	}

	private CalcListSectionServiceLocal getCalcListSectionService() {
		return (CalcListSectionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcListSectionServiceLocal.LOCAL_SERVICE_NAME);
	}

	private CalcListFeeServiceLocal getCalcListFeeService() {
		return (CalcListFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcListFeeServiceLocal.LOCAL_SERVICE_NAME);
	}

	private CalcListFeeParamServiceLocal getCalcListFeeParamService() {
		return (CalcListFeeParamServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcListFeeParamServiceLocal.LOCAL_SERVICE_NAME);
	}

	protected PersonnelConfig getModuleConfiguration() {
		return ServerUtils.getPersistentManager().find(PersonnelConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
	}

	private class FeeParamBusinessAddinListener implements BusinessAddinListener<BigDecimal> {

		private BigDecimal result = BigDecimal.ZERO;

		public FeeParamBusinessAddinListener() {
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#aborted(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void aborted(BusinessAddinEvent<BigDecimal> event) {
			// Do nothing
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#completed(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void completed(BusinessAddinEvent<BigDecimal> event) {
			result = event.getResult() == null ? BigDecimal.ZERO : event.getResult();
		}

	}

	private class FeeBusinessAddinListener implements BusinessAddinListener<FeeBAiResult> {

		private FeeBAiResult result;

		public FeeBusinessAddinListener() {
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#aborted(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void aborted(BusinessAddinEvent<FeeBAiResult> event) {
			// Do nothing
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#completed(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void completed(BusinessAddinEvent<FeeBAiResult> event) {
			result = event.getResult();
		}

	}

	private class DateInterval {

		Date beginDate;
		Date endDate;

		public DateInterval() {
		}

		/**
		 * @param beginDate - начальная дата интервала
		 * @param endDate - конечная дата интервала
		 */
		public DateInterval(Date beginDate, Date endDate) {
			this.beginDate = beginDate;
			this.endDate = endDate;
		}

	}

	private class PeriodTarif {

		Integer categoryId;
		Integer tarifId;
		Date beginDate;
		Date endDate;

		/**
		 * @param tarifId
		 * @param categoryId
		 * @param beginDate
		 * @param endDate
		 */
		public PeriodTarif(Integer categoryId, Integer tarifId, Date beginDate, Date endDate) {
			this.categoryId = categoryId;
			this.tarifId = tarifId;
			this.beginDate = beginDate;
			this.endDate = endDate;
		}

	}



	public int getFromNextPeriod(int calcListId) throws ApplicationException {
		return 0;//((CalcListDomainImpl) getDomain()).getFromNextPeriod(calcListId);
	}


	public int getFromPrevPeriod(int calcListId) throws ApplicationException {
		return 0;//((CalcListDomainImpl) getDomain()).getFromPrevPeriod(calcListId);
	}

	public int getFromAnotherPayRoll(int calcListId, int payRollId) throws ApplicationException {
		return 0;//((CalcListDomainImpl) getDomain()).getFromAnotherPayRoll(calcListId, payRollId);
	}
}
