/*
 * SalaryBusinessAddin.java
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

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.DeductionClass;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.salary.model.CalcList;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.TripleSumSign;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Базовый класс бизнес-расширения расчета зарплаты
 *
 * @author Artem V. Sharapov
 * @version $Id: SalaryBusinessAddin.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public abstract class SalaryBusinessAddin<T> extends AbstractBusinessAddin<T> {

  public final static String STAFF_LIST_PARAM = "STAFF_LIST_PARAM"; //$NON-NLS-1$
  public final static String FEE_MODEL_PARAM = "FEE_MODEL_PARAM"; //$NON-NLS-1$
  public final static String PAY_ROLL_PARAM = "PAY_ROLL_PARAM"; //$NON-NLS-1$
  public final static String CALC_LIST_PARAM = "CALC_LIST_PARAM"; //$NON-NLS-1$
  public final static String CALC_LIST_FEE_PARAM = "CALC_LIST_FEE_PARAM"; //$NON-NLS-1$
  public final static String POSITION_FILL_PARAM = "POSITION_FILL_PARAM"; //$NON-NLS-1$
  public final static String PERSONAL_ACCOUNT_PARAM = "PERSONAL_ACCOUNT_PARAM"; //$NON-NLS-1$
  public final static String BEGIN_DATE_PARAM = "BEGIN_DATE_PARAM"; //$NON-NLS-1$
  public final static String END_DATE_PARAM = "END_DATE_PARAM"; //$NON-NLS-1$
  public final static String PERIOD_BEGIN_DATE_PARAM = "PERIOD_BEGIN_DATE_PARAM"; //$NON-NLS-1$
  public final static String PERIOD_END_DATE_PARAM = "PERIOD_END_DATE_PARAM"; //$NON-NLS-1$
  public final static String COSTS_ANL_1_PARAM = "COSTS_ANL_1_PARAM"; //$NON-NLS-1$
  public final static String COSTS_ANL_2_PARAM = "COSTS_ANL_2_PARAM"; //$NON-NLS-1$
  public final static String COSTS_ANL_3_PARAM = "COSTS_ANL_3_PARAM"; //$NON-NLS-1$
  public final static String COSTS_ANL_4_PARAM = "COSTS_ANL_4_PARAM"; //$NON-NLS-1$
  public final static String COSTS_ANL_5_PARAM = "COSTS_ANL_5_PARAM"; //$NON-NLS-1$

  private StaffList staffList;
  private FeeModel feeModel;
  private PayRoll payRoll;
  private CalcList calcList;
  private CalcListFee calcListFee;
  private PositionFill positionFill;
  private PersonalAccount personalAccount;
  private Date beginDate;
  private Date endDate;
  private Date periodBeginDate;
  private Date periodEndDate;
  private CostsAnl costsAnl1;
  private CostsAnl costsAnl2;
  private CostsAnl costsAnl3;
  private CostsAnl costsAnl4;
  private CostsAnl costsAnl5;

  private Map<String, ? extends Object> contextParams;


  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    contextParams = params;
    staffList = (StaffList) params.get(STAFF_LIST_PARAM);
    feeModel = (FeeModel) params.get(FEE_MODEL_PARAM);
    payRoll = (PayRoll) params.get(PAY_ROLL_PARAM);
    calcList = (CalcList) params.get(CALC_LIST_PARAM);
    calcListFee = (CalcListFee) params.get(CALC_LIST_FEE_PARAM);
    positionFill = (PositionFill) params.get(POSITION_FILL_PARAM);
    personalAccount = (PersonalAccount) params.get(PERSONAL_ACCOUNT_PARAM);
    beginDate = (Date) params.get(BEGIN_DATE_PARAM);
    endDate = (Date) params.get(END_DATE_PARAM);
    periodBeginDate = (Date) params.get(PERIOD_BEGIN_DATE_PARAM);
    periodEndDate = (Date) params.get(PERIOD_END_DATE_PARAM);
    costsAnl1 = (CostsAnl) params.get(COSTS_ANL_1_PARAM);
    costsAnl2 = (CostsAnl) params.get(COSTS_ANL_2_PARAM);
    costsAnl3 = (CostsAnl) params.get(COSTS_ANL_3_PARAM);
    costsAnl4 = (CostsAnl) params.get(COSTS_ANL_4_PARAM);
    costsAnl5 = (CostsAnl) params.get(COSTS_ANL_5_PARAM);
  }


  /**
   * @return the beginDate
   */
  protected Date getBeginDate() {
    return this.beginDate;
  }

  /**
   * Получить расчетный листок
   *
   * @return расчетный листок
   */
  protected CalcList getCalcList() {
    return this.calcList;
  }

  /**
   * @return the calcListFee
   */
  protected CalcListFee getCalcListFee() {
    return this.calcListFee;
  }

  /**
   * Получить аналитику состава затрат 1-го уровня
   *
   * @return аналитика состава затрат 1-го уровня
   */
  protected CostsAnl getCostsAnl1() {
    return this.costsAnl1;
  }

  /**
   * Получить аналитику состава затрат 2-го уровня
   *
   * @return аналитика состава затрат 2-го уровня
   */
  protected CostsAnl getCostsAnl2() {
    return this.costsAnl2;
  }

  /**
   * Получить аналитику состава затрат 3-го уровня
   *
   * @return аналитика состава затрат 3-го уровня
   */
  protected CostsAnl getCostsAnl3() {
    return this.costsAnl3;
  }

  /**
   * Получить аналитику состава затрат 4-го уровня
   *
   * @return аналитика состава затрат 4-го уровня
   */
  protected CostsAnl getCostsAnl4() {
    return this.costsAnl4;
  }

  /**
   * Получить аналитику состава затрат 5-го уровня
   *
   * @return аналитика состава затрат 5-го уровня
   */
  protected CostsAnl getCostsAnl5() {
    return this.costsAnl5;
  }

  /**
   * @return the endDate
   */
  protected Date getEndDate() {
    return this.endDate;
  }

  /**
   * Получить образец начислений/удержаний
   *
   * @return образец начислений/удержаний
   */
  protected FeeModel getFeeModel() {
    return this.feeModel;
  }

  /**
   * Получить расчетную ведомость
   *
   * @return расчетная ведомость
   */
  protected PayRoll getPayRoll() {
    return this.payRoll;
  }

  /**
   * @return the periodBeginDate
   */
  protected Date getPeriodBeginDate() {
    return this.periodBeginDate;
  }

  /**
   * @return the periodEndDate
   */
  protected Date getPeriodEndDate() {
    return this.periodEndDate;
  }

  /**
   * Получить лицевой счет сотрудника
   *
   * @return лицевой счет сотрудника
   */
  protected PersonalAccount getPersonalAccount() {
    return this.personalAccount;
  }

  /**
   * Получить должность занимаимую сотрудником
   *
   * @return должность занимаимая сотрудником
   */
  protected PositionFill getPositionFill() {
    return this.positionFill;
  }

  /**
   * Получить штатное расписание
   *
   * @return штатное расписание
   */
  protected StaffList getStaffList() {
    return this.staffList;
  }


  /**
   * Получить размер минимальной зарплаты из справочника на указанную дату
   *
   * @param actualDate - дата
   * @return размер минимальной зарплаты из справочника на указанную дату
   */
  protected BigDecimal getMinSalary(Date actualDate) {
    return SalaryHelper.getMinSalary(actualDate);
  }

  /**
   * Получить аналитику состава затрат указанную в тарифе
   *
   * @param tariffCode - код тарификации
   * @return аналитика(всех уровней) состава затрат указанная в тарифе
   */
  protected CostsAnlResult getCostsAnlFromTariff(String tariffCode) {
    return SalaryHelper.getCostsAnlFromTariff(tariffCode, positionFill, staffList, beginDate);
  }

  /**
   * Получить вычет
   *
   * @param deductionKindCode - код вида вычета из справочника
   * @param actualDate        - фактическая дата
   * @param income            - сумма дохода. Если она превышает максимальную сумма дохода, то
   *                          результат будет равен 0
   * @return сумма вычета на дату
   */
  protected BigDecimal getDeduction(String deductionKindCode, Date actualDate, BigDecimal income) {
    return SalaryHelper.getDeduction(deductionKindCode, actualDate, income);
  }

  /**
   * Получить сумму вычетов, действующих на членов семьи
   *
   * @param deductionClass - категория вычетов: NULL - все вычеты, без учета категории; 0 - "нет"; 1
   *                       - "на себя"; 2 - "на детей"; 3 - "на иждивенцев"
   * @param actualDate     -  фактическая дата
   * @param income         - сумма дохода. Если она превышает максимальную сумма дохода, до которой
   *                       действует вычет, этот вычет не будет учитываться.
   * @return сумма вычета на членов семьи
   */
  protected BigDecimal getFamilyDeductionSum(DeductionClass deductionClass, Date actualDate, BigDecimal income) {
    return SalaryHelper.getFamilyDeductionSum(deductionClass, actualDate, income, personalAccount);
  }

  /**
   * Получить сумму начислений/удержаний по коду, за указанный пеериод
   *
   * @param feeCode      - код начисления/удержания
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @param payRoll      - расчетная ведомость. Если NULL, то для всех расчетных ведомостей
   * @param positionFill - исполнение должности. Если NULL, то для всех должностей, исполняемых
   *                     сотрудником
   * @return сумма начислений/удержаний по коду, за указанный пеериод
   */
  protected BigDecimal getFeeSum(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
    return SalaryHelper.getFeeSum(feeCode, beginDate, endDate, payRoll, positionFill, personalAccount);
  }

  /**
   * Получить сумму начислений/удержаний, коды которых указаны на закладке "входящие н/у"
   * справочника н/у, за указанный период
   *
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @param payRoll      - расчетная ведомость. Если null, то для всех расчетных ведомостей
   * @param positionFill - исполняемая должность. Если null, то для всех должностей, исполняемых
   *                     сотрудником
   * @param feeSign      - знак, с которым входят начисления: TripleSumSign.NONE - взять все
   *                     входящие начисления. Входящие со знаком "плюс" прибавляются к общей сумме,
   *                     входящие со знаком "минус" вычитаются из общей суммы; TripleSumSign.Plus -
   *                     складываются только начисления, входящие со знаком "плюс";
   *                     TripleSumSign.Minus - складываются только начисления, входящие со знаком
   *                     "минус". Результат > 0 !
   * @return сумма начислений/удержаний, коды которых указаны на закладке "входящие н/у" справочника
   * н/у, за указанный период
   */
  protected BigDecimal getIncludedFeeSum(Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, TripleSumSign feeSign) {
    return SalaryHelper.getIncludedFeeSum(beginDate, endDate, payRoll, positionFill, feeSign, feeModel, personalAccount);
  }

  /**
   * Получить сумму н/у, коды которых указаны на закладке "входящие н/у" справочника н/у, за
   * указанный период. Расширенный набор параметров.
   *
   * @param beginDate            - дата начала периода
   * @param endDate              - дата окончания периода
   * @param payRoll              - р/в. Если null, то для всех р/в.
   * @param payRollKindName      - наименование типа р/в. Если null, то для всех р/в.
   * @param positionFillKindCode - код вида исполнения должности. Если null, то для всех должностей,
   *                             исполняемых сотрудником
   * @param positionFill         - исполнямая должность. Если null, то для всех должностей,
   *                             исполняемых сотрудником
   * @param taxCalcKindCode      - код схемы расчета налогов для должности. Если null, то для всех
   *                             должностей, исполняемых сотрудником
   * @param feeSign              - знак, с которым входят начисления: TripleSumSign.NONE - взять все
   *                             входящие начисления. Входящие со знаком "плюс" прибавляются к общей
   *                             сумме, входящие со знаком "минус" вычитаются из общей суммы;
   *                             TripleSumSign.Plus - складываются только начисления, входящие со
   *                             знаком "плюс"; TripleSumSign.Minus - складываются только
   *                             начисления, входящие со знаком "минус". Результат > 0 !
   * @return сумма н/у, коды которых указаны на закладке "входящие н/у" справочника н/у, за
   * указанный период
   */
  protected BigDecimal getIncludedFeeSumEx(Date beginDate, Date endDate, PayRoll payRoll, String payRollKindName, String positionFillKindCode, PositionFill positionFill, String taxCalcKindCode, TripleSumSign feeSign) {
    return SalaryHelper.getIncludedFeeSumEx(beginDate, endDate, payRoll, payRollKindName, positionFillKindCode, positionFill, personalAccount, taxCalcKindCode, feeSign, feeModel);
  }

  /**
   * Получить н/у, последнеее по дате начала действия, за указанный период. Использовать осторожно!
   *
   * @param feeCode      - код н/у
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @param payRoll      - р/в. Если null, то для всех р/в
   * @param positionFill - исполненяемая должность. Если null, то для всех должностей, исполняемых
   *                     сотрудником
   * @return н/у, последнеее по дате начала действия, за указанный период
   */
  protected BigDecimal getLastFee(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
    return SalaryHelper.getLastFee(feeCode, beginDate, endDate, payRoll, positionFill, personalAccount);
  }

  /**
   * Получить стаж сотрудника в годах по коду стажа
   *
   * @param serviceKindCode - код стажа
   * @param actualDate      - фактическая дата
   * @return стаж сотрудника в годах по коду стажа
   */
  protected BigDecimal getLengthOfService(String serviceKindCode, Date actualDate) {
    return SalaryHelper.getLengthOfService(serviceKindCode, actualDate, personalAccount);
  }

  /**
   * Получить количество ставок, которое занимает сотрудник по указанной должности, на дату начала
   * действия н/у
   *
   * @param positionFill - исполненяемая должность
   * @return количество ставок, которое занимает сотрудник по указанной должности, на дату начала
   * действия н/у
   */
  protected BigDecimal getNumberOfRates(PositionFill positionFill) {
    return SalaryHelper.getNumberOfRates(positionFill);
  }

  /**
   * Получить параметр текущего н/у
   *
   * @param paramCode - код параметра
   * @return параметр текущего н/у
   */
  protected Object getParam(String paramCode) {
    return SalaryHelper.getParam(paramCode, calcListFee);
  }

  /**
   * Получить параметр н/у, последнего по дате начала действия, за указанный период. Использовать
   * осторожно!
   *
   * @param feeCode      - код н/у
   * @param paramCode    - код параметра
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @param payRoll      - р/в. Если null, то для всех р/в
   * @param positionFill - исполненяемая должность. Если null, то для всех должностей, исполняемых
   *                     сотрудником
   * @return параметр н/у, последнего по дате начала действия, за указанный период
   */
  protected Object getParamFromLastFee(String feeCode, String paramCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
    return SalaryHelper.getParamFromLastFee(feeCode, paramCode, beginDate, endDate, payRoll, positionFill, personalAccount);
  }

  /**
   * Получить код вида исполнения указанной должности
   *
   * @param positionFill - исполненяемая должность
   * @return код вида исполнения указанной должности
   */
  protected String getPositionKindCode(PositionFill positionFill) {
    return SalaryHelper.getPositionKindCode(positionFill);
  }

  /**
   * Получить код категории персонала для указанной должности
   *
   * @param positionFill - исполненяемая должность
   * @param actualDate   - фактическая дата
   * @return код категории персонала для указанной должности
   */
  protected String getStaffCategoryCode(PositionFill positionFill, Date actualDate) {
    return SalaryHelper.getStaffCategoryCode(positionFill, actualDate, staffList);
  }

  /**
   * Получить по коду тариф из лицевого счета сотрудника, действующий на дату начала действия н/у
   *
   * @param tariffCode - код тарифа
   * @return тариф из лицевого счета сотрудника, действующий на дату начала действия н/у
   */
  protected BigDecimal getTariff(String tariffCode) {
    return SalaryHelper.getTariff(tariffCode, positionFill, staffList, beginDate, contextParams);
  }

  /**
   * Получить норму рабочего времени в днях, по заданному исполнению должности, на заданную дату
   *
   * @param positionFill - исполненяемая должность
   * @param actualDate   - фактическая дата
   * @return норма рабочего времени в днях, по заданному исполнению должности, на заданную дату
   */
  protected BigDecimal getWorkDaysNorm(PositionFill positionFill, Date actualDate) {
    return SalaryHelper.getWorkDaysNorm(positionFill, actualDate, staffList);
  }

  /**
   * Получить норму рабочего времени в часах, по заданному исполнению должности, на заданную дату
   *
   * @param positionFill - исполненяемая должность
   * @param actualDate   - фактическая дата
   * @return норма рабочего времени в часах, по заданному исполнению должности, на заданную дату
   */
  protected BigDecimal getWorkHoursNorm(PositionFill positionFill, Date actualDate) {
    return SalaryHelper.getWorkHoursNorm(positionFill, actualDate, staffList);
  }

  /**
   * Получить информацию о налоге по указанному размеру дохода
   *
   * @param taxCode     - код налога из справочника
   * @param actualDate  - фактическая дата
   * @param scaleNumber - номер шкалы
   * @param income      - сумма дохода
   * @return информация о налоге по указанному размеру дохода
   */
  protected TaxResult getTaxByIncome(String taxCode, Date actualDate, Integer scaleNumber, BigDecimal income) {
    return SalaryHelper.getTaxByIncome(taxCode, actualDate, scaleNumber, income);
  }

  /**
   * Получить информацию о налоге по номеру ставки
   *
   * @param taxCode     - код налога из справочника
   * @param actualDate  - фактическая дата
   * @param scaleNumber - номер шкалы
   * @param rateNumber  - номер ставки
   * @return информация о налоге по номеру ставки
   */
  protected TaxResult getTaxByNumber(String taxCode, Date actualDate, Integer scaleNumber, Integer rateNumber) {
    return SalaryHelper.getTaxByNumber(taxCode, actualDate, scaleNumber, rateNumber);
  }

  /**
   * @return true, если все уровни аналитики состава затрат не заданы
   */
  protected boolean isCostsAnlEmpty() {
    return SalaryHelper.isCostsAnlEmpty(costsAnl1, costsAnl2, costsAnl3, costsAnl4, costsAnl5);
  }

  /**
   * Получить значение константы из справочника по коду и дате актуальности
   *
   * @param constantCode - код константы
   * @param actualDate   - дата актуальности
   * @return значение константы из справочника по коду и дате актуальности
   */
  protected Object getConstant(String constantCode, Date actualDate) {
    return SalaryHelper.getConstantValue(constantCode, actualDate);
  }


  /**
   * Получить количество дней за указанный период, для которых в графике работ есть запись с
   * указанным типом времени
   *
   * @param timeKindCode - код типа времени
   * @param positionFill - занимаемая должность
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @return количество дней за указанный период, для которых в графике работ есть запись с
   * указанным типом времени
   */
  protected BigDecimal getDaysFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
    return TableHelper.getDaysFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
  }

  /**
   * Получить количество часов с указанным типом времени из графика работ, за указанный период
   *
   * @param timeKindCode - код типа времени
   * @param positionFill - занимаемая должность
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @return количество часов с указанным типом времени из графика работ, за указанный период. Для
   * типов времени с учетом по дням вернет 0.
   */
  protected BigDecimal getHoursFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
    return TableHelper.getHoursFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
  }

  /**
   * Получить количество дней за указанный период, для которых в табеле есть запись с указанным
   * типом времени
   *
   * @param timeKindCode - код типа времени
   * @param positionFill - занимаемая должность
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @return количество дней за указанный период, для которых в табеле есть запись с указанным типом
   * времени
   */
  protected BigDecimal getDaysFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
    return TableHelper.getDaysFromTimeBoard(timeKindCode, positionFill, beginDate, endDate);
  }

  /**
   * Получить количество часов с указанным типом времени из табеля, за указанный период. Для типов
   * времени с учетом по дням вернет 0.
   *
   * @param timeKindCode - код типа времени
   * @param positionFill - занимаемая должность
   * @param beginDate    - дата начала периода
   * @param endDate      - дата окончания периода
   * @return количество часов с указанным типом времени из табеля, за указанный период. Для типов
   * времени с учетом по дням вернет 0.
   */
  public BigDecimal getHoursFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
    return TableHelper.getHoursFromTimeBoard(timeKindCode, positionFill, beginDate, endDate);
  }

  /**
   * Возвращает true, если для сотрудника задан график работ на указанную дату
   *
   * @param positionFill - занимаемая должность
   * @param actualDate   - фактическая дата
   * @return true, если для сотрудника задан график работ на указанную дату
   */
  protected boolean isScheduleExists(PositionFill positionFill, Date actualDate) {
    return TableHelper.isScheduleExists(positionFill, actualDate, staffList);
  }

  /**
   * Возвращает true, если на указанную дату сотрудник есть в табеле
   *
   * @param positionFill - занимаемая должность
   * @param actualDate   - занимаемая должность
   * @return true, если на указанную дату сотрудник есть в табеле
   */
  protected boolean isTimeBoardExists(PositionFill positionFill, Date actualDate) {
    return TableHelper.isTimeBoardExists(positionFill, actualDate);
  }

}
