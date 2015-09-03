/*
 * RowCalculator.java
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

import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndCur;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndNat;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckEndNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeCur;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeNat;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckIncomeNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeCur;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeCurPlan;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeNat;
import static com.mg.merp.finance.totals.FinanceTotals.ComputeKind.ckOutcomeNatPlan;
import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.ftkAcc;
import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.ftkFeat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.jdbc.DataAccessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.totals.FinanceTotals.ComputeKind;
import com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind;
import com.mg.merp.finance.totals.helperclasses.ResStr;
import com.mg.merp.finance.totals.helperclasses.caches.AccountCache;
import com.mg.merp.finance.totals.helperclasses.caches.PeriodCache;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.CorrDataItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.CorrDataItemRM;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.IncomeOutcomeItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.IncomeOutcomeRM;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: RowCalculator.java,v 1.5 2007/09/17 12:12:25 alikaev Exp $
 */
public class RowCalculator {
	private FinanceTotalsKind kind;
	private int rowId;
	private int accId;
	private int periodId;
	private Set<ComputeKind> computeKinds;
	private Date date1;
	private Date date2;
	private String specIncomeFieldName;
	private String specOutcomeFieldName;
	private String fieldsClauseIncome;
	private String fieldsClauseOutcome;
	private boolean needFact;
	private boolean needPlan;
	private BigDecimal REMNBEGNAT;
	private BigDecimal REMNBEGCUR;
	private BigDecimal INCOMENAT;
	private BigDecimal INCOMECUR;
	private BigDecimal OUTCOMENAT;
	private BigDecimal OUTCOMECUR;
	private BigDecimal REMNENDNAT;
	private BigDecimal REMNENDCUR;
	private BigDecimal REMNBEGNATPLAN;
	private BigDecimal REMNBEGCURPLAN;
	private BigDecimal INCOMENATPLAN;
	private BigDecimal INCOMECURPLAN;
	private BigDecimal OUTCOMENATPLAN;
	private BigDecimal OUTCOMECURPLAN;
	private BigDecimal REMNENDNATPLAN;
	private BigDecimal REMNENDCURPLAN;

	private BigDecimal INCOMENATDIFF;
	private BigDecimal REMNBEGCURDIFF;
	private BigDecimal REMNBEGNATDIFF;
	private BigDecimal REMNENDNATDIFF;
	private BigDecimal INCOMECURDIFF;
	private BigDecimal OUTCOMECURDIFF;
	private BigDecimal OUTCOMENATDIFF;
	private BigDecimal REMNENDCURDIFF;

	public RowCalculator(FinanceTotalsKind kind, int rowId, int anAccId,
			int periodId, Set<ComputeKind> computeKinds,
			PeriodCache periodCache, AccountCache anAccountCache, Date date1,
			Date date2)	{
		// init

		specIncomeFieldName = StringUtils.EMPTY_STRING;
		specOutcomeFieldName = StringUtils.EMPTY_STRING;
		fieldsClauseIncome = StringUtils.EMPTY_STRING;
		fieldsClauseOutcome = StringUtils.EMPTY_STRING;

		REMNBEGNAT = new BigDecimal(0);
		REMNBEGCUR = new BigDecimal(0);
		INCOMENAT = new BigDecimal(0);
		INCOMECUR = new BigDecimal(0);
		OUTCOMENAT = new BigDecimal(0);
		OUTCOMECUR = new BigDecimal(0);
		REMNENDNAT = new BigDecimal(0);
		REMNENDCUR = new BigDecimal(0);

		REMNBEGNATPLAN = new BigDecimal(0);
		REMNBEGCURPLAN = new BigDecimal(0);
		INCOMENATPLAN = new BigDecimal(0);
		INCOMECURPLAN = new BigDecimal(0);
		OUTCOMENATPLAN = new BigDecimal(0);
		OUTCOMECURPLAN = new BigDecimal(0);
		REMNENDNATPLAN = new BigDecimal(0);
		REMNENDCURPLAN = new BigDecimal(0);

		INCOMENATDIFF = new BigDecimal(0);
		REMNBEGCURDIFF = new BigDecimal(0);
		REMNBEGNATDIFF = new BigDecimal(0);
		REMNENDNATDIFF = new BigDecimal(0);
		INCOMECURDIFF = new BigDecimal(0);
		OUTCOMECURDIFF = new BigDecimal(0);
		OUTCOMENATDIFF = new BigDecimal(0);
		REMNENDCURDIFF = new BigDecimal(0);

		this.kind = kind;
		this.rowId = rowId;
		this.accId = anAccId;
		this.periodId = periodId;
		this.computeKinds = computeKinds;
		this.date1 = date1;
		this.date2 = date2;

		if (this.kind == ftkAcc) {
			specIncomeFieldName = "dstturnacc_id"; //$NON-NLS-1$
			specOutcomeFieldName = "srcturnacc_id"; //$NON-NLS-1$
		} else if (this.kind == ftkFeat) {
			specIncomeFieldName = "dstturnfeat_id"; //$NON-NLS-1$
			specOutcomeFieldName = "srcturnfeat_id"; //$NON-NLS-1$
		}

		if (this.computeKinds.contains(ckIncomeNat)
				|| this.computeKinds.contains(ckEndNat)
				|| this.computeKinds.contains(ckIncomeNatPlan)
				|| this.computeKinds.contains(ckEndNatPlan))		
			fieldsClauseIncome = fieldsClauseIncome + "sum(s.sumnat) sumnat,"; //$NON-NLS-1$
		
		if (this.computeKinds.contains(ckIncomeCur)
				|| this.computeKinds.contains(ckEndCur)
				|| this.computeKinds.contains(ckIncomeCurPlan)
				|| this.computeKinds.contains(ckEndCurPlan))
			fieldsClauseIncome = fieldsClauseIncome + "sum(s.sumcur) sumcur,"; //$NON-NLS-1$
		
		if (fieldsClauseIncome.length() > 0 && fieldsClauseIncome.endsWith(",")) //$NON-NLS-1$
			fieldsClauseIncome = fieldsClauseIncome.substring(0,
					fieldsClauseIncome.length() - 1);

		if (this.computeKinds.contains(ckOutcomeNat)
				|| this.computeKinds.contains(ckEndNat)
				|| this.computeKinds.contains(ckOutcomeNatPlan)
				|| this.computeKinds.contains(ckEndNatPlan))
			fieldsClauseOutcome = fieldsClauseOutcome + "sum(s.sumnat) sumnat,"; //$NON-NLS-1$

		if (this.computeKinds.contains(ckOutcomeCur)
				|| this.computeKinds.contains(ckEndCur)
				|| this.computeKinds.contains(ckOutcomeCurPlan)
				|| this.computeKinds.contains(ckEndCurPlan))
			fieldsClauseOutcome = fieldsClauseOutcome + "sum(s.sumcur) sumcur,"; //$NON-NLS-1$
		
		if (fieldsClauseOutcome.length() > 0 && fieldsClauseOutcome.endsWith(",")) //$NON-NLS-1$
			fieldsClauseOutcome = fieldsClauseOutcome.substring(0, fieldsClauseOutcome.length() - 1);

		if (this.computeKinds.contains(ckIncomeNat)
				|| this.computeKinds.contains(ckIncomeCur)
				|| this.computeKinds.contains(ckOutcomeNat)
				|| this.computeKinds.contains(ckOutcomeCur)
				|| this.computeKinds.contains(ckEndNat)
				|| this.computeKinds.contains(ckEndCur))
			needFact = true;

		if (this.computeKinds.contains(ckIncomeNatPlan)
				|| this.computeKinds.contains(ckIncomeCurPlan)
				|| this.computeKinds.contains(ckOutcomeNatPlan)
				|| this.computeKinds.contains(ckOutcomeCurPlan)
				|| this.computeKinds.contains(ckEndNatPlan)
				|| this.computeKinds.contains(ckEndCurPlan))
			needPlan = true;
	}

	protected void readValuesIncome(IncomeOutcomeItem value, boolean isPlan) {
		BigDecimal sumnat = value.getSumNat();
		BigDecimal sumcur = value.getSumCur();

		if (isPlan)
			INCOMENATPLAN = sumnat;
		else
			INCOMENAT = sumnat;

		if (isPlan)
			INCOMECURPLAN = sumcur;
		else
			INCOMECUR = sumcur;
	}

	protected void readValuesOutcome(IncomeOutcomeItem value, boolean isPlan) {
		BigDecimal sumnat = value.getSumNat();
		BigDecimal sumcur = value.getSumCur();

		if (sumnat != null)
			if (isPlan)
				OUTCOMENATPLAN = sumnat;
			else
				OUTCOMENAT = sumnat;

		if (sumcur != null)
			if (isPlan)
				OUTCOMECURPLAN = sumcur;
			else
				OUTCOMECUR = sumcur;
	}

	protected void calculatePeriod() throws DataAccessException	{
		List<IncomeOutcomeItem> iolst = null;
		String strSQL = StringUtils.EMPTY_STRING;
		if (needFact) {
			if (fieldsClauseIncome.length() > 0) {
				strSQL = "select " + fieldsClauseIncome //$NON-NLS-1$
						+ " from finspec s where (planned=0) and (s." //$NON-NLS-1$
						+ specIncomeFieldName + " = " + rowId + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				iolst = JdbcTemplate.getInstance().query(strSQL, null,
						new IncomeOutcomeRM());
				if (iolst != null && !iolst.isEmpty())
					readValuesIncome(iolst.get(0), false);
			}

			if (fieldsClauseOutcome.length() > 0) {
				strSQL = "select " + fieldsClauseOutcome //$NON-NLS-1$
						+ " from finspec s where (planned=0) and (s." //$NON-NLS-1$
						+ specOutcomeFieldName + " = " + rowId + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				iolst = JdbcTemplate.getInstance().query(strSQL, null,
						new IncomeOutcomeRM());
				if (iolst != null && !iolst.isEmpty())
					readValuesOutcome(iolst.get(0), false);
			}
		}

		if (needPlan) {
			if (fieldsClauseIncome.length() > 0) {
				strSQL = "select " + fieldsClauseIncome //$NON-NLS-1$
						+ " from finspec s where (planned=1) and (s." //$NON-NLS-1$
						+ specIncomeFieldName + " = " + rowId + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				iolst = JdbcTemplate.getInstance().query(strSQL, null,
						new IncomeOutcomeRM());
				if (iolst != null && !iolst.isEmpty())
					readValuesIncome(iolst.get(0), true);
			}

			if (fieldsClauseOutcome.length() > 0) {
				strSQL = "select " + fieldsClauseOutcome //$NON-NLS-1$
						+ " from finspec s where (planned=1) and (s." //$NON-NLS-1$
						+ specOutcomeFieldName + " = " + rowId + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				iolst = JdbcTemplate.getInstance().query(strSQL, null,
						new IncomeOutcomeRM());
				if (iolst != null && !iolst.isEmpty())
					readValuesOutcome(iolst.get(0), true);
			}
		}

		calculateEnd();
	}

    private void calculateDate(String sWhere, Object[] params) throws DataAccessException {
    	List<IncomeOutcomeItem> iolst = null;
    	if (needFact) {
			if (fieldsClauseIncome.length() > 0) {
				Object[] objs = {fieldsClauseIncome, 0, specIncomeFieldName, rowId, sWhere};  
				iolst = getIncomeOutcome(objs, params);
				if (iolst != null && !iolst.isEmpty())
					readValuesIncome(iolst.get(0), false);
			}

			if (fieldsClauseOutcome.length() > 0) {
				Object[] objs = {fieldsClauseOutcome, 0, specOutcomeFieldName, rowId, sWhere};  
				iolst = getIncomeOutcome(objs, params);
				if (iolst != null && !iolst.isEmpty())
					readValuesOutcome(iolst.get(0), false);
			}
		}
		if (needPlan) {
			if (fieldsClauseIncome.length() > 0){
				Object[] objs = {fieldsClauseIncome, 1, specIncomeFieldName, rowId, sWhere};  
				iolst = getIncomeOutcome(objs, params);
				if (iolst != null && !iolst.isEmpty())
					readValuesIncome(iolst.get(0), true);
			}
			
			if (fieldsClauseOutcome.length() > 0) {
				Object[] objs = {fieldsClauseOutcome, 1, specOutcomeFieldName, rowId, sWhere};  
				iolst = getIncomeOutcome(objs, params);
				if (iolst != null && !iolst.isEmpty())
					readValuesOutcome(iolst.get(0), true);
			}
		}
		calculateEnd();
    }
	
	protected void calculateBetweenDates() throws DataAccessException {
		String sWhere = StringUtils.EMPTY_STRING;
		Object[] params;
		if (!PeriodCache.isBeginOfPeriod(periodId, date1)) {
			// остатки на начало
			sWhere = " and (h.keepdate < ? )"; //$NON-NLS-1$
			params = new Object[1];
			params[0] = new Timestamp(date1.getTime());

			calculateDate(sWhere, params);
			swapRemn();
		}
		
		sWhere = " and (h.keepdate between ? and ?)"; //$NON-NLS-1$
		params = new Object[2];
		params[0] = new Timestamp(date1.getTime());
		params[1] = new Timestamp(date2.getTime());
		
		calculateDate(sWhere, params);
	}
	
	private static List<IncomeOutcomeItem> getIncomeOutcome(Object[] sql, Object[] params) throws DataAccessException {
		String strSQL = String.format(ResStr.SBETWEEN_DATES_SQL, sql);
		return JdbcTemplate.getInstance().query(strSQL, params,	new IncomeOutcomeRM());
	}

	protected void swapRemn() {
		REMNBEGNAT = new BigDecimal(REMNENDNAT.doubleValue());
		REMNBEGCUR = new BigDecimal(REMNENDCUR.doubleValue());
		REMNBEGNATPLAN = new BigDecimal(REMNENDNATPLAN.doubleValue());
		REMNBEGCURPLAN = new BigDecimal(REMNENDCURPLAN.doubleValue());
	}

	public void calculate() throws DataAccessException {
		if (date1 != null && date2 != null)
			calculateBetweenDates();
		else
			calculatePeriod();
	}

	public void calculateEnd() {
		REMNENDCUR = REMNBEGCUR.add(INCOMECUR).add(OUTCOMECUR.negate());
		REMNENDNAT = REMNBEGNAT.add(INCOMENAT).add(OUTCOMENAT.negate());

		REMNENDCURPLAN = REMNBEGCURPLAN.add(INCOMECURPLAN).add(OUTCOMECURPLAN.negate());
		REMNENDNATPLAN = REMNBEGNATPLAN.add(INCOMENATPLAN).add(OUTCOMENATPLAN.negate());

		REMNBEGNATDIFF = REMNBEGNAT.add(REMNBEGNATPLAN.negate());
		REMNBEGCURDIFF = REMNBEGCUR.add(REMNBEGCURPLAN.negate());
		INCOMENATDIFF = INCOMENAT.add(INCOMENATPLAN.negate());
		OUTCOMENATDIFF = OUTCOMENAT.add(OUTCOMENATPLAN.negate());
		INCOMECURDIFF = INCOMECUR.add(INCOMECURPLAN.negate());
		OUTCOMECURDIFF = OUTCOMECUR.add(OUTCOMECURPLAN.negate());
		REMNENDNATDIFF = REMNENDNAT.add(REMNENDNATPLAN.negate());
		REMNENDCURDIFF = REMNENDCUR.add(REMNENDCURPLAN.negate());
	}

	private String fillCorDSCreateSQL(boolean isIncome, boolean isPlan,	String where, String join) {
		String plan = "0"; //$NON-NLS-1$
		String inOut = "s.dstacc_id"; //$NON-NLS-1$
		String fieldsClause = fieldsClauseOutcome;
		String spec = specOutcomeFieldName;

		if (isPlan)
			plan = "1"; //$NON-NLS-1$
		if (isIncome) {
			inOut = "s.srcacc_id"; //$NON-NLS-1$
			fieldsClause = fieldsClauseIncome;
			spec = specIncomeFieldName;
		}

		String str = "select " + inOut + ", cor_acc.code acc_code, " //$NON-NLS-1$ //$NON-NLS-2$
				+ fieldsClause + " from finspec s " + join //$NON-NLS-1$
				+ " join finaccount cor_acc on cor_acc.id = " + inOut //$NON-NLS-1$
				+ " where (s.planned=" + plan + ") and (s." + spec + " = " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ rowId + ")" + where + " group by " + inOut + ", cor_acc.code"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return str;
	}

	protected List<CorrDataItem> fillCorDataSet() throws DataAccessException {
		String sJoin = StringUtils.EMPTY_STRING;
		String sWhere = StringUtils.EMPTY_STRING;
		String strSQL = StringUtils.EMPTY_STRING;

		Object[] params = null;
		HashMap<Integer, CorrDataItem> resultSetMap = new LinkedHashMap<Integer, CorrDataItem>();

		if (((date1 != null) && (date2 != null))) {
			sJoin = " join finoper h on h.id = s.finoper_id "; //$NON-NLS-1$
			sWhere = " and (h.keepdate between ? and ? ) "; //$NON-NLS-1$
			params = new Object[2];
			params[0] = new Timestamp(date1.getTime());
			params[1] = new Timestamp(date2.getTime());
		}
		if (needFact) {
			if (fieldsClauseIncome.length() > 0) {
				strSQL = fillCorDSCreateSQL(true, false, sWhere, sJoin);
				List<CorrDataItem> list = JdbcTemplate.getInstance().query(strSQL, params, new CorrDataItemRM());

				// HAshMap необходим для, того, чтобы можно было брать
				// быстро запись по ACC_ID
				for (Iterator<CorrDataItem> it = list.listIterator(); it.hasNext();) {
					CorrDataItem item = it.next();
					Integer key = item.accId;
					CorrDataItem newItem = null;
					if (resultSetMap.containsKey(key)) {
						newItem = resultSetMap.get(key);
						newItem.incomeNat = newItem.incomeNat.add(item.sumCur_Nat.getSumNat());
						newItem.incomeCur = newItem.incomeCur.add(item.sumCur_Nat.getSumCur());
					} else {
						newItem = new CorrDataItem(item);
						newItem.incomeNat = item.sumCur_Nat.getSumNat();
						newItem.incomeCur = item.sumCur_Nat.getSumCur();
					}
					resultSetMap.put(key, newItem);
				}

			}

			if (fieldsClauseOutcome.length() > 0) {
				strSQL = fillCorDSCreateSQL(false, false, sWhere, sJoin);
				List<CorrDataItem> list = JdbcTemplate.getInstance().query(strSQL, params, new CorrDataItemRM());

				for (Iterator<CorrDataItem> it = list.listIterator(); it.hasNext();) {
					CorrDataItem item = it.next();
					Integer key = item.accId;
					CorrDataItem newItem = null;
					if (resultSetMap.containsKey(key)) {
						newItem = resultSetMap.get(key);
						newItem.outcomeNat = newItem.outcomeNat.add(item.sumCur_Nat.getSumNat());
						newItem.outcomeCur = newItem.outcomeCur.add(item.sumCur_Nat.getSumCur());
					} else {
						newItem = new CorrDataItem(item);
						newItem.outcomeNat = item.sumCur_Nat.getSumNat();
						newItem.outcomeCur = item.sumCur_Nat.getSumCur();
					}
					resultSetMap.put(key, newItem);
				}
			}
		}

		if (needPlan) {
			if (fieldsClauseIncome.length() > 0) {
				strSQL = fillCorDSCreateSQL(true, true, sWhere, sJoin);
				List<CorrDataItem> list = JdbcTemplate.getInstance().query(strSQL, params, new CorrDataItemRM());

				for (Iterator<CorrDataItem> it = list.listIterator(); it.hasNext();){
					CorrDataItem item = it.next();
					Integer key = item.accId;
					CorrDataItem newItem = null;
					if (resultSetMap.containsKey(key)) {
						newItem = resultSetMap.get(key);
						newItem.incomeNatPlan = newItem.incomeNatPlan.add(item.sumCur_Nat.getSumNat());
						newItem.incomeCurPlan = newItem.incomeCurPlan.add(item.sumCur_Nat.getSumCur());
					} else
					{
						newItem = new CorrDataItem(item);
						newItem.incomeNatPlan = item.sumCur_Nat.getSumNat();
						newItem.incomeCurPlan = item.sumCur_Nat.getSumCur();
					}
					resultSetMap.put(key, newItem);
				}
			}

			if (fieldsClauseOutcome.length() > 0) {
				strSQL = fillCorDSCreateSQL(false, true, sWhere, sJoin);
				List<CorrDataItem> list = JdbcTemplate.getInstance().query(strSQL, params, new CorrDataItemRM());

				for (Iterator<CorrDataItem> it = list.listIterator(); it.hasNext();)
				{
					CorrDataItem item = it.next();
					Integer key = item.accId;
					CorrDataItem newItem = null;
					if (resultSetMap.containsKey(key)) {
						newItem = resultSetMap.get(key);
						newItem.outcomeNatPlan = newItem.outcomeNatPlan.add(item.sumCur_Nat.getSumNat());
						newItem.outcomeCurPlan = newItem.outcomeCurPlan.add(item.sumCur_Nat.getSumCur());
					} else {
						newItem = new CorrDataItem(item);
						newItem.outcomeNatPlan = item.sumCur_Nat.getSumNat();
						newItem.outcomeCurPlan = item.sumCur_Nat.getSumCur();
					}
					resultSetMap.put(key, newItem);
				}
			}

		}
		return new LinkedList<CorrDataItem>(resultSetMap.values());
	}
	
}
