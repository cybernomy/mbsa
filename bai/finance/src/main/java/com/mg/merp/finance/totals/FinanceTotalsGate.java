/*
 * FinanceTotalsGate.java
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
package com.mg.merp.finance.totals;
//TODO: 1. Подведение итогов по признакам
//TODO: 2. классу DataSet приделать метод, предоставляющий возможность использовать аггрегацию 
//из Sohlman DataSet
//TODO: 3. реализовать метод getTotalCorDataSet
//TODO: 4. изменить метод fillCorDataSet у класса RowCalculator и mergeCorAccounts у класса 
//FinanceTotalsFacade, что бы выборка корсчетов не "схлопывалась"
//TODO: 5. Использовать кеширование для AccountCache и PeriodCache
//TODO: 6. Изменить имеющиеся SQL запросы так, чтобы они стали вызывать хранимые процедуры.
//все переменные передавать как параметры
//TODO: 7. убрать все deprecated методы
//TODO: 8. Реализовать исключения(где необходимо), возникающие при работе, 
//для отображения на клиенте
//TODO: 9. com.mg.merp.totals.finance.helperclasses.Utils проблемы
//если имя сервиса содержит и прописные и строчные буквы. 
//TODO: 10. com.mg.merp.totals.finance.helperclasses.Utils, метод getSomeCode. 
//Предусмотреть возможность формирования кода, если нет ни поля name, ни поля code
//TODO: 11. Убрать неиспользующиеся классы и методы.
//TODO: 12. Использовать вместо HashMap класс FastHashMap пакета org.hibernate.util
//TODO: 13. Выбрасывать исключение, если пользователь вызывает методы open,
//getDataSet, getCorDataSet, getTotalCorDataSet до установки необходимых 
//параметров. И методы getDataSet, getCorDataSet, getTotalCorDataSet до вызова open
//TODO: leonova Желательно, чтобы при вызове функции getColIDbyName возвращала
//id не только по имени, но и по алиасу


import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.dataset.ColumnDef;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.support.dataset.DataSetImpl;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtil;
import com.mg.merp.finance.totals.FinanceTotals.ComputeKind;
import com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind;
import com.mg.merp.finance.totals.helperclasses.Period;
import com.mg.merp.finance.totals.helperclasses.ResStr;
import com.mg.merp.finance.totals.helperclasses.caches.AccountCache;
import com.mg.merp.finance.totals.helperclasses.caches.PeriodCache;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.AccountItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.CorrDataItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.PeriodItem;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeCur;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeNat;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckNone;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeCur;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeNat;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.DatePositionInPeriod.dppBegin;
import static com.mg.merp.finance.totals.FinanceTotals.DatePositionInPeriod.dppEnd;
import static com.mg.merp.finance.totals.FinanceTotals.DatePositionInPeriod.dppOutside;
import static com.mg.merp.finance.totals.FinanceTotals.DatePositionInPeriod.dppUnknown;
import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.ftkAcc;
import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.ftkFeat;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: FinanceTotalsGate.java,v 1.11 2007/09/17 12:12:25 alikaev Exp $
 */
public class FinanceTotalsGate {

  protected static final String ROW_ID_NAME = "_ROW_ID_";
  static HashMap<String, String> fieldAliases = new HashMap<String, String>();

  static {
    fieldAliases.put("RBN", "REMNBEGNAT");
    fieldAliases.put("RBC", "REMNBEGCUR");
    fieldAliases.put("IN", "INCOMENAT");
    fieldAliases.put("IC", "INCOMECUR");
    fieldAliases.put("ON", "OUTCOMENAT");
    fieldAliases.put("OC", "OUTCOMECUR");
    fieldAliases.put("REN", "REMNENDNAT");
    fieldAliases.put("REC", "REMNENDCUR");
    fieldAliases.put("RBNP", "REMNBEGNATPLAN");
    fieldAliases.put("RBCP", "REMNBEGCURPLAN");
    fieldAliases.put("INP", "INCOMENATPLAN");
    fieldAliases.put("ICP", "INCOMECURPLAN");
    fieldAliases.put("ONP", "OUTCOMENATPLAN");
    fieldAliases.put("OCP", "OUTCOMECURPLAN");
    fieldAliases.put("RENP", "REMNENDNATPLAN");
    fieldAliases.put("RECP", "REMNENDCURPLAN");
    fieldAliases.put("RBND", "REMNBEGNATDIFF");
    fieldAliases.put("RBCD", "REMNBEGCURDIFF");
    fieldAliases.put("IND", "INCOMENATDIFF");
    fieldAliases.put("ICD", "INCOMECURDIFF");
    fieldAliases.put("OND", "OUTCOMENATDIFF");
    fieldAliases.put("OCD", "OUTCOMECURDIFF");
    fieldAliases.put("REND", "REMNENDNATDIFF");
    fieldAliases.put("RECD", "REMNENDCURDIFF");
  }

  protected FinanceTotalsKind kind;
  private Set<String> fields;
  private Set<String> accCodes;
  private Set<String> featCodes;
  private int periodId1;
  private int periodId2;
  private Date date1;
  private Date date2;
  private Set<Integer> accIDs;
  private Set<Integer> featureIDs;
  private List<Integer> accountFolderIds;
  private List<Integer> featureFolderId;
  private String currencyCode;
  private int[] anlId = new int[FinanceTotals.MAX_FINANLLEVEL];
  private Map<Integer, Set<Integer>> analytics;
  private Map<Integer, Set<Integer>> featAnalytics;
  private FinanceTotals worker;
  private DataSet ds;
  private boolean isPreferPeriods;
  private boolean isSelectCorAccounts;
  private Map<Integer, List<CorrDataItem>> corDsMap;
  private DataSet totalCorDs;
  private boolean isTotalCorDsReady;

  public FinanceTotalsGate() {
    kind = ftkAcc;
    ds = new DataSetImpl();
    analytics = new LinkedHashMap<Integer, Set<Integer>>();
    featAnalytics = new LinkedHashMap<Integer, Set<Integer>>();
  }

  private static DataSet fillCordataSet(DataSet cds, List<CorrDataItem> lst) {
    for (Iterator<CorrDataItem> it = lst.listIterator(); it.hasNext(); ) {
      CorrDataItem item = it.next();
      List<Object> row = new LinkedList<Object>();
      row.add(item.accId);
      row.add(item.accCode);
      row.add(item.incomeNat);
      row.add(item.outcomeNat);
      row.add(item.incomeCur);
      row.add(item.outcomeCur);
      row.add(item.incomeNatPlan);
      row.add(item.outcomeNatPlan);
      row.add(item.incomeCurPlan);
      row.add(item.outcomeCurPlan);

      cds.addRow(row);
    }
    return cds;
  }

  private void createWorker() {
    if (kind == ftkAcc)
      worker = new FinAccTotals();
    else if (kind == ftkFeat)
      worker = new FinFeatTotals();

    worker.setAccountsIDs(accIDs);
    worker.setAccountFolderIds(accountFolderIds);
    worker.setCurrencyCode(currencyCode);
    worker.setAnlIdList(analytics);

    if (kind == ftkFeat) {
      ((FinFeatTotals) worker).setFeatureIds(featureIDs);
      ((FinFeatTotals) worker).setFeatureFolderId(featureFolderId);
      ((FinFeatTotals) worker).setFeatAnlIdList(featAnalytics);
    }
  }

  private void mergeData(DataSet srcDS, boolean isEnd, int periodId, Date date1, Date date2) {
    srcDS.firstRow();
    while (!srcDS.isEndOfSet()) {
      LinkedList<Object> fields = srcDS.getRowIndex(srcDS.getCurPos());
      if (ds.locate(fields) > 0) {
        mergeCorAccounts(false, (Integer) srcDS.getValueAt("id"), date1, date2);
        mergeFields(srcDS);
      } else {
        initNewRecord(srcDS);
        mergeCorAccounts(true, (Integer) srcDS.getValueAt("id"), date1, date2);
        resetValues();
      }
      if (isEnd)
        calculateRow(periodId);
      srcDS.nextRow();
    }
  }

  private void initNewRecord(DataSet srcDS) {
    Object[] row = srcDS.getRow();
    ds.addRow(row);
    ds.setValueAt(ds.getRowCount(), ROW_ID_NAME);
  }

  private List<CorrDataItem> fillCorDataList(int remnId, Date date1, Date date2) {
    LinkedHashSet<ComputeKind> calcKinds = new LinkedHashSet<ComputeKind>();
    calcKinds.add(ckIncomeNat);
    calcKinds.add(ckIncomeCur);
    calcKinds.add(ckOutcomeNat);
    calcKinds.add(ckOutcomeCur);

    if (worker.computeKinds.contains(ckIncomeNatPlan)
        || worker.computeKinds.contains(ckIncomeCurPlan)
        || worker.computeKinds.contains(ckOutcomeNatPlan)
        || worker.computeKinds.contains(ckOutcomeCurPlan)
        || worker.computeKinds.contains(ckEndNatPlan)
        || worker.computeKinds.contains(ckEndCurPlan)) {
      calcKinds.add(ckIncomeNatPlan);
      calcKinds.add(ckIncomeCurPlan);
      calcKinds.add(ckOutcomeNatPlan);
      calcKinds.add(ckOutcomeCurPlan);
    }
    RowCalculator calc = new RowCalculator(kind, remnId, 0, 0, calcKinds,
        PeriodCache.getInstance(), AccountCache.getInstance(), date1,
        date2);
    List<CorrDataItem> correspond = null;
    try {
      correspond = calc.fillCorDataSet();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return correspond;
  }

  private void mergeCorAccounts(boolean isNewRecord, Integer remnId, Date date1, Date date2) {
    if (isSelectCorAccounts) {
      List<CorrDataItem> correspond = fillCorDataList(remnId, date1, date2);
      Integer pos = (Integer) ds.getValueAt(ROW_ID_NAME);
      if (isNewRecord)
        corDsMap.put(pos, correspond);
      else {
        List<CorrDataItem> tmp = corDsMap.get(pos);
        correspond = mergeCorrDataItemLists(correspond, tmp);
        corDsMap.put(pos, correspond);
      }
    }
  }

  private List<CorrDataItem> mergeCorrDataItemLists(List<CorrDataItem> list1, List<CorrDataItem> list2) {
    if (list1 != null && list2 != null && !list1.isEmpty() && !list2.isEmpty()) {
      LinkedList<CorrDataItem> result = new LinkedList<CorrDataItem>();
      for (Iterator<CorrDataItem> it1 = list1.listIterator(); it1.hasNext(); ) {
        CorrDataItem item1 = it1.next();
        for (Iterator<CorrDataItem> it2 = list2.listIterator(); it2.hasNext(); ) {
          CorrDataItem item2 = it2.next();
          if (item1.accId == item2.accId) {
            item1.incomeCur = item1.incomeCur.add(item2.incomeCur);
            item1.incomeNat = item1.incomeNat.add(item2.incomeNat);
            item1.incomeCurPlan = item1.incomeCurPlan
                .add(item2.incomeCurPlan);
            item1.incomeNatPlan = item1.incomeNatPlan
                .add(item2.incomeNatPlan);

            item1.outcomeCur = item1.outcomeCur
                .add(item2.outcomeCur);
            item1.outcomeNat = item1.outcomeNat
                .add(item2.outcomeNat);
            item1.outcomeCurPlan = item1.outcomeCurPlan
                .add(item2.outcomeCurPlan);
            item1.outcomeNatPlan = item1.outcomeNatPlan
                .add(item2.outcomeNatPlan);
          } else result.add(item2);
          result.add(item1);
        }
      }
      return result;
    } else return list1;
  }

  private void calculateRow(Integer periodId) {
    Object fld = ds.getValueAt("ACC_ID");
    Integer accId;
    if (fld != null)
      accId = (Integer) fld;
    else
      accId = 0;

    LinkedHashSet<ComputeKind> ck = new LinkedHashSet<ComputeKind>();
    ck.add(ckNone);

    RowCalculator calc = new RowCalculator(kind, 0, accId, periodId, ck,
        PeriodCache.getInstance(), AccountCache.getInstance(), date1,
        date2);

    for (Iterator<String> it = fields.iterator(); it.hasNext(); ) {
      String colName = it.next();
      Field field = ReflectionUtils.findDeclaredField(
          RowCalculator.class, colName);
      if (field != null)
        try {
          BigDecimal value = (BigDecimal) ds.getValueAt(colName);
          if (value != null)
            field.set(calc, value);
        } catch (Exception ex) {
        }
    }
    calc.calculateEnd();
    for (Iterator<String> it = fields.iterator(); it.hasNext(); ) {
      String colName = it.next();
      Field field = ReflectionUtils.findDeclaredField(RowCalculator.class, colName);
      if (field != null)
        try {
          BigDecimal value = (BigDecimal) field.get(calc);
          if (value != null)
            ds.setValueAt(value, colName);
        } catch (Exception ex) {
        }
    }
  }

  private void parseAccounts() throws DataAccessException {
    // accounts
    if (accCodes != null && !accCodes.isEmpty()) {
      List<AccountItem> accItemIds = AccountCache.getFinAccountsByCode(accCodes);
      if (accItemIds.size() != accCodes.size())
        throw new IllegalArgumentException(ResStr.SINVALID_ACC_CODE);
      for (Iterator<AccountItem> it = accItemIds.listIterator(); it.hasNext(); )
        worker.addAccount(it.next().getId());
    }
    // analytics
    worker.setAnlIdList(analytics);
    if (kind.equals(ftkFeat)) {
      // features
      if (featCodes != null) {
        List<AccountItem> featItemIds = AccountCache.getFinAccountsByCode(featCodes);
        for (Iterator it = featItemIds.listIterator(); it.hasNext(); )
          ((FinFeatTotals) worker).addFeature(((AccountItem) it.next()).getId());
      }
      // feature analytics
      ((FinFeatTotals) worker).setFeatAnlIdList(featAnalytics);
    }
  }

  private Map<Integer, List<CorrDataItem>> processCorAccounts(Date date1, Date date2) {
    if (isSelectCorAccounts) {
      LinkedHashMap<Integer, List<CorrDataItem>> cDsMap = new LinkedHashMap<Integer, List<CorrDataItem>>();
      ds.firstRow();
      while (!ds.isEndOfSet()) {
        List<CorrDataItem> correspond = fillCorDataList((Integer) ds.getValueAt("id"), date1, date2);
        cDsMap.put((Integer) ds.getValueAt(ROW_ID_NAME), correspond);
        ds.nextRow();
      }
      return cDsMap;
    } else
      return null;
  }

  private void mergeFields(DataSet srcDS) {
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeNat")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeNat"))), "IncomeNat");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeNat")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeNat"))), "OutcomeNat");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeCur")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeCur"))), "IncomeCur");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeCur")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeCur"))), "OutcomeCur");

    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeNatPlan")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeNatPlan"))), "IncomeNatPlan");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeNatPlan")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeNatPlan"))), "OutcomeNatPlan");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeCurPlan")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeCurPlan"))), "IncomeCurPlan");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeCurPlan")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeCurPlan"))), "OutcomeCurPlan");

    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeNat")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeNat"))), "IncomeNat");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeNat")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeNat"))), "OutcomeNat");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("IncomeCur")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("IncomeCur"))), "IncomeCur");
    ds.setValueAt(NullToZero(((BigDecimal) ds.getValueAt("OutcomeCur")))
        .add(NullToZero((BigDecimal) srcDS.getValueAt("OutcomeCur"))), "OutcomeCur");

    resetValues();

  }

  private BigDecimal NullToZero(BigDecimal value) {
    return value == null ? new BigDecimal(0) : value;
  }

  private void resetValues() {
    ds.setValueAt(null, "ID");
    ds.setValueAt(null, "PERIOD_ID");
    ds.setValueAt(null, "PNAME");
    ds.setValueAt(null, "DATEFROM");
    ds.setValueAt(null, "DATETO");
  }

  public void setKind(FinanceTotalsKind kind) {
    this.kind = kind;
  }

  @SuppressWarnings("unchecked")
  @Deprecated
  public void setFields(String fieldSet) {
    List<String> lst = StringUtil.split(fieldSet,
        FinanceTotals.DLM_FIELDSSET);
    setFieldsSet(lst);
  }

  public void setFields(String... arr) {
    List<String> lst = Arrays.asList(arr);
    setFieldsSet(lst);
  }

  private void setFieldsSet(List<String> fields) {
    this.fields = new LinkedHashSet<String>();
    for (Iterator it = fields.listIterator(); it.hasNext(); ) {
      String token = (String) it.next();
      String fieldName = getRealFieldName(token);
      this.fields.add(fieldName.toUpperCase(ServerUtils.getUserLocale()));
      if (fieldName.equalsIgnoreCase("FEAT_CODE") || fieldName.equalsIgnoreCase("FANL"))
        kind = ftkFeat;
      isPreferPeriods = (fieldName.equalsIgnoreCase("PERIOD_ID")
          || fieldName.equalsIgnoreCase("PNAME") || fieldName.equalsIgnoreCase("DATEFROM")
          || fieldName.equalsIgnoreCase("DATETO")) || isPreferPeriods;
    }
  }

  public void setPeriods(Integer periodId1, Integer periodId2) {
    this.periodId1 = periodId1;
    this.periodId2 = periodId2;
  }

  public void setDates(Date date1, Date date2) {
    this.date1 = date1;
    this.date2 = date2;
  }

  @SuppressWarnings("unchecked")
  @Deprecated
  public void setAccounts(String value) {
    List<String> lst = StringUtil.split(value, FinanceTotals.DLM_FIELDSSET);
    accCodes = new LinkedHashSet<String>(lst);
  }

  public void setAccountsCodes(String... value) {
    List<String> lst = Arrays.asList(value);
    accCodes = new LinkedHashSet<String>(lst);
  }

  public void setAccountsCodes(Set<String> value) {
    accCodes = value;
  }

  public void setAccountsId(Integer... idSet) {
    accIDs = new LinkedHashSet<Integer>(Arrays.asList(idSet));
  }

  public void setAccountsId(Set<Integer> idSet) {
    accIDs = idSet;
  }

  public void setAccountFolderId(List<Integer> accountFolderIds) {
    this.accountFolderIds = accountFolderIds;
  }

  public void setAnlId(Integer level, Integer value) {
    if (level < 1 || level > FinanceTotals.MAX_FINANLLEVEL)
      throw new IllegalArgumentException(ResStr.SINVALID_ANL_LEVEL);
    this.anlId[level] = value;
  }

  @SuppressWarnings("unchecked")
  @Deprecated
  public void setAnalytics(int level, String value) {
    if (level < 1 || level > FinanceTotals.MAX_FINANLLEVEL)
      throw new IllegalArgumentException(ResStr.SINVALID_ANL_LEVEL);
    List<String> l2 = StringUtil.split(value, FinanceTotals.DLM_FIELDSSET);
    List<Integer> lst = new LinkedList<Integer>();
    for (Iterator<String> it = l2.listIterator(); it.hasNext(); )
      lst.add(Integer.parseInt(it.next()));

    analytics.put(level, new LinkedHashSet<Integer>(lst));
  }

  public void setAnalytics(int level, Integer... value) {
    if (level < 1 || level > FinanceTotals.MAX_FINANLLEVEL)
      throw new IllegalArgumentException(ResStr.SINVALID_ANL_LEVEL);
    List<Integer> lst = Arrays.asList(value);
    analytics.put(level, new LinkedHashSet<Integer>(lst));
  }

  public void setFeatureId(Set<Integer> featureIds) {
    this.featureIDs = featureIds;
    this.kind = ftkFeat;
  }

  public void setFeatureId(Integer... idSet) {
    this.featureIDs = new LinkedHashSet<Integer>(Arrays.asList(idSet));
    ;
  }

  @SuppressWarnings("unchecked")
  @Deprecated
  public void setFeatures(String value) {
    List<String> lst = StringUtil.split(value, FinanceTotals.DLM_FIELDSSET);
    featCodes = new LinkedHashSet<String>(lst);
    this.kind = ftkFeat;
  }

  public void setFeatures(String... value) {
    List<String> lst = Arrays.asList(value);
    featCodes = new LinkedHashSet<String>(lst);
    this.kind = ftkFeat;
  }

  public void setFeatureFolderId(List<Integer> featureFolderId) {
    this.featureFolderId = featureFolderId;
    this.kind = ftkFeat;
  }

  public void setFeatAnalytics(int level, Integer... value) {
    if (level < 1 || level > FinanceTotals.MAX_FINANLLEVEL)
      throw new IllegalArgumentException(ResStr.SINVALID_ANL_LEVEL);
    List<Integer> lst = Arrays.asList(value);
    featAnalytics.put(level, new LinkedHashSet<Integer>(lst));
  }

  public void setCurrencyCode(String curCode) {
    this.currencyCode = curCode.toUpperCase();
  }

  public void setCorAccounts(String value) {
  }

  public void selectCorAccounts(boolean value) {
    this.isSelectCorAccounts = value;
  }

  public void open() throws Exception {
    if (kind == ftkFeat && isSelectCorAccounts)
      throw new RuntimeException(ResStr.SCANT_SEL_CORACCOUNTS);
    createWorker();
    parseAccounts();
    worker.setFieldSet(fields);

    Period dpp1 = new Period();
    dpp1.setDatePosition(dppUnknown);
    Period dpp2 = new Period();
    dpp2.setDatePosition(dppUnknown);

    if (date1 != null && date1.after(new Date(0))) {
      dpp1 = PeriodCache.getPeriod(date1);
      periodId1 = dpp1.getId();
      if (dpp1.getDatePosition() == dppOutside)
        throw new RuntimeException(ResStr.SINVALID_PERIOD);
    }
    if (date2 != null && date2.after(new Date(0))) {
      dpp2 = PeriodCache.getPeriod(date2);
      periodId2 = dpp2.getId();
      if (dpp2.getDatePosition() == dppOutside)
        throw new RuntimeException(ResStr.SINVALID_PERIOD);
    }

    // по периодам
    if (((dpp1.getDatePosition() == dppUnknown && dpp2.getDatePosition() == dppUnknown)
        || (dpp1.getDatePosition() == dppBegin && dpp2.getDatePosition() == dppEnd))
        && isPreferPeriods) {
      worker.setPeriods(periodId1, periodId2);
      worker.open(false);
      ds = worker.getDataSet();
      corDsMap = processCorAccounts(null, null);
    } else {
      if (date1 == null)
        date1 = PeriodCache.findById(periodId1).getBeginDate();
      if (date2 == null)
        date2 = PeriodCache.findById(periodId2).getEndDate();
      // по датам
      if (periodId1 == periodId2) {
        worker.setPeriods(periodId1, periodId1);
        worker.setDates(date1, date2);
        worker.open(false);
        // worker.open(true);

        ds = worker.getDataSet();
        corDsMap = processCorAccounts(date1, date2);
      } else {
        worker.setPeriods(periodId1, periodId1);
        worker.setDates(date1, PeriodCache.findById(periodId1).getEndDate());
        worker.open(true);
        ds = worker.getDataSet();

        corDsMap = processCorAccounts(date1, PeriodCache.findById(periodId1).getEndDate());

        List<PeriodItem> periodItems = PeriodCache.getPeriodsBetween(date1, date2);
        if (periodItems != null && !periodItems.isEmpty()) {
          for (Iterator<PeriodItem> it = periodItems.listIterator(); it.hasNext(); ) {
            worker.setDates(null, null);
            int prId = (it.next()).getId();
            worker.setPeriods(prId, prId);
            worker.open(true);
            mergeData(worker.getDataSet(), false, prId, null, null);
          }
        }
        worker.setPeriods(periodId2, periodId2);
        worker.setDates(PeriodCache.findById(periodId2).getBeginDate(), date2);
        worker.open(true);
        mergeData(worker.getDataSet(), true, periodId2,
            PeriodCache.findById(periodId2).getBeginDate(), date2);
      }
    }
    ds.firstRow();
  }

  public DataSet getDataSet() {
    return ds;
  }

  public DataSet getCorDataSet() {
    if (!isSelectCorAccounts)
      throw new RuntimeException(ResStr.SCOR_ACCOUNTS_NOT_SEL);
    int cp = ds.getCurPos();
    if (cp != -1) {
      List<CorrDataItem> lst = corDsMap.get(cp);
      if (lst != null && !lst.isEmpty())
        return makeDataset(lst);
      else
        return null;
    } else
      return null;
  }

  private DataSet makeDataset(List<CorrDataItem> lst) {
    DataSet cds = initCorDataSet();
    cds = fillCordataSet(cds, lst);
    return cds;
  }

  private DataSet initCorDataSet() {
    DataSet cds = new DataSetImpl();
    ColumnDef[] cd = new ColumnDef[10];
    cd[0] = new ColumnDef("ACC_ID", Integer.class);
    cd[1] = new ColumnDef("ACC_CODE", String.class);
    cd[2] = new ColumnDef("INCOMENAT", BigDecimal.class);
    cd[3] = new ColumnDef("OUTCOMENAT", BigDecimal.class);
    cd[4] = new ColumnDef("INCOMECUR", BigDecimal.class);
    cd[5] = new ColumnDef("OUTCOMECUR", BigDecimal.class);
    cd[6] = new ColumnDef("INCOMENATPLAN", BigDecimal.class);
    cd[7] = new ColumnDef("OUTCOMENATPLAN", BigDecimal.class);
    cd[8] = new ColumnDef("INCOMECURPLAN", BigDecimal.class);
    cd[9] = new ColumnDef("OUTCOMECURPLAN", BigDecimal.class);
    cds.defineColumns(cd);

    return cds;
  }

  public DataSet getTotalCorDataSet() {
    if (!isSelectCorAccounts)
      throw new RuntimeException(ResStr.SCOR_ACCOUNTS_NOT_SEL);

    if (!isTotalCorDsReady) {
      if (totalCorDs == null)
        totalCorDs = new DataSetImpl();
      else
        totalCorDs.clear();

      ds.firstRow();
      while (!ds.isEndOfSet()) {
        Integer rowID = (Integer) ds.getValueAt("ROW_ID_NAME");
        DataSet corDS = new DataSetImpl();
        // TODO: corDs := FCorDsList.Data[PoIntegerer(rowId)];
        corDS.firstRow();
        while (!corDS.isEndOfSet()) {
          // TODO: если что-то есть, то редактируем, иначе добавляем
                    /*
           * if FTotalCorDs.Locate('ACC_ID',
					 * VarArrayOf([corDs.FieldByName('ACC_ID').AsInteger]), [])
					 * then FTotalCorDs.Edit else FTotalCorDs.Append;
					 */
          for (Integer i = 1; i < totalCorDs.getColumnCount(); i++) {
            String fn = totalCorDs.getColumnsInfo()[i].getFieldName();
            if (fn.equalsIgnoreCase("ACC_ID") || fn.equalsIgnoreCase("ACC_CODE"))
              totalCorDs.setValueAt(corDS.getValueAt(i), i);
            else
              totalCorDs.setValueAt((
                  (BigDecimal) totalCorDs.getValueAt(i)).add(
                  (BigDecimal) corDS.getValueAt(fn)), i);
          }
          corDS.nextRow();
        }

        ds.nextRow();
      }
      /*
			 * FDs.First; while not FDs.Eof do begin rowId :=
			 * FDs.FieldByName(cRowIdName).AsInteger; corDs :=
			 * FCorDsList.Data[PoIntegerer(rowId)];
			 * 
			 * corDs.First; while not corDs.Eof do begin if
			 * FTotalCorDs.Locate('ACC_ID',
			 * VarArrayOf([corDs.FieldByName('ACC_ID').AsInteger]), []) then
			 * FTotalCorDs.Edit else FTotalCorDs.Append; for i:=0 to
			 * FTotalCorDs.FieldCount-1 do begin fn :=
			 * FTotalCorDs.Fields[i].FieldName; if (fn = 'ACC_ID') or (fn =
			 * 'ACC_CODE') then FTotalCorDs.Fields[i].Value :=
			 * corDs.FieldByName(fn).Value else FTotalCorDs.Fields[i].AsFloat :=
			 * FTotalCorDs.Fields[i].AsFloat + corDs.FieldByName(fn).AsFloat;
			 * end; FTotalCorDs.Post; corDs.Next; end;
			 * 
			 * FDs.Next; end; FDs.GotoBookmark(bm); finally
			 * FDs.FreeBookmark(bm); end;
			 */
      isTotalCorDsReady = true;
    }
    return totalCorDs;
  }

  public String getRealFieldName(String str) {
    String upp = str.toUpperCase();
    return fieldAliases.containsKey(upp) ? fieldAliases.get(upp) : str;
  }

}
