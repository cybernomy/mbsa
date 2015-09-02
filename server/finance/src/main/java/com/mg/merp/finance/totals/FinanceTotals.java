/*
 * FinanceTotals.java
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

/**
 * @author Valentin A. Poroxnenko
 * @author Konstantin S. Alikaev
 * @version $Id: FinanceTotals.java,v 1.10 2008/02/12 14:27:50 alikaev Exp $
 */
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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.dataset.AutoInc;
import com.mg.framework.api.dataset.ColumnDef;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.api.jdbc.DataAccessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.support.dataset.DataSetImpl;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;
import com.mg.merp.finance.support.ui.FinUtils;
import com.mg.merp.finance.totals.helperclasses.ResStr;
import com.mg.merp.finance.totals.helperclasses.Utils;
import com.mg.merp.finance.totals.helperclasses.caches.AccountCache;
import com.mg.merp.finance.totals.helperclasses.caches.PeriodCache;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.FinTurnAccItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.FinTurnAccItemRM;


public abstract class FinanceTotals {
	
	public static final byte MAX_FINANLLEVEL = 5;
	public static final String DLM_FIELDSSET = ";";

	public enum FinanceTotalsKind {
		ftkNone, ftkAcc, ftkFeat
	}

	enum ComputeKind {
		ckNone, ckIncomeNat, ckIncomeCur, ckOutcomeNat, ckOutcomeCur, ckEndNat, ckEndCur, ckIncomeNatPlan, ckIncomeCurPlan, ckOutcomeNatPlan, ckOutcomeCurPlan, ckEndNatPlan, ckEndCurPlan
	};

	public enum DatePositionInPeriod {
		dppUnknown, dppOutside, dppBegin, dppInside, dppEnd
	};

	enum FinanceDataSetKind {
		fdskMain, fdskCor, fdskTotalCor
	};

	private String[] integerFields = { "ID", "PERIOD_ID", "ACC_ID",
			"ACCFOLDER_ID", "ANL1_ID", "ANL2_ID", "ANL3_ID", "ANL4_ID",
			"ANL5_ID", "FANL1_ID", "FANL2_ID", "FANL3_ID", "FANL4_ID", "FANL5_ID"};
	private String[] strFields = { "PNAME", "ACC_CODE", "FEAT_CODE", "CURCODE", "ANL",
			"ANL1_CODE", "ANL2_CODE", "ANL3_CODE", "ANL4_CODE", "ANL5_CODE",
			"FANL1_CODE", "FANL2_CODE", "FANL3_CODE", "FANL4_CODE", "FANL5_CODE"};
	private String[] currencyFields = { "REMNBEGNAT", "REMNBEGCUR",
			"INCOMENAT", "INCOMECUR", "OUTCOMENAT", "OUTCOMECUR", "REMNENDNAT",
			"REMNENDCUR", "REMNBEGNATPLAN", "REMNBEGCURPLAN", "INCOMENATPLAN",
			"INCOMECURPLAN", "OUTCOMENATPLAN", "OUTCOMECURPLAN",
			"REMNENDNATPLAN", "REMNENDCURPLAN", "REMNBEGNATDIFF",
			"REMNBEGCURDIFF", "INCOMENATDIFF", "INCOMECURDIFF",
			"OUTCOMENATDIFF", "OUTCOMECURDIFF", "REMNENDNATDIFF",
			"REMNENDCURDIFF" };
	protected LinkedHashSet<ComputeKind> computeKinds;
	protected FinanceTotalsKind ftKind;
	protected String table;
	protected LinkedHashSet<String> fields;
	protected List<Integer> accountFolderIds;
	protected Map<Integer, Set<Integer>> anlIdList;
	protected int periodId1 = 0;
	protected int periodId2 = 0;
	protected String currencyCode;
	protected Date date1;
	protected Date date2;
	protected DataSet ds;
	protected Set<Integer> accountIds;

	abstract protected void init();

	abstract protected String[] createSQLParts(String fields, String from,
			String where, String order) throws DataAccessException;
	
	protected abstract Date getPeriodBeginDate(int periodId);


	public FinanceTotals() {
		accountIds = new LinkedHashSet<Integer>();
		computeKinds = new LinkedHashSet<ComputeKind>();
	}

	private void createDataSet(boolean makeIndex) {
		this.ds = new DataSetImpl();
		ColumnDef[] cd = new ColumnDef[1 + fields.size()];
		cd[0] = new ColumnDef(FinanceTotalsGate.ROW_ID_NAME, AutoInc.class);
		int j = 1;
		for (Iterator i = fields.iterator(); i.hasNext();) {
			String name = (String) i.next();
			cd[j] = new ColumnDef(name, getColumnTypeByName(name));
			j++;
		}
		ds.defineColumns(cd);
		if (makeIndex)
			ds.setIndexFields(indexFields());
	}
	
	private ArrayList<Integer> indexFields() {
		ArrayList<Integer> hashFields = new ArrayList<Integer>();
		int posACC = ds.getColIDbyName("ACC_ID");
		if (posACC > 0)
			hashFields.add(posACC);
		
		for (byte i = 1; i <= FinanceTotals.MAX_FINANLLEVEL; i++) {
			int posId = ds.getColIDbyName("ANL"+i+"_ID");
			int posCode = ds.getColIDbyName("ANL"+i+"_CODE"); 
			if(posId > 0)
				hashFields.add(posId);
			else if(posCode > 0)
				hashFields.add(posCode);
		}
		return hashFields;
	}

	private void calculateRow() throws DataAccessException {
		RowCalculator calculator = new RowCalculator(ftKind, (Integer) ds.getValueAt("ID")
				, (Integer) ds.getValueAt("ACC_ID"), (Integer) ds.getValueAt("PERIOD_ID")
				, computeKinds, PeriodCache.getInstance(), AccountCache.getInstance(), date1,date2);

		for (Iterator<String> it = fields.iterator(); it.hasNext();) {
			String colName = it.next();
			Field fld = ReflectionUtils.findDeclaredField(RowCalculator.class, colName);
			if (fld != null)
				try	{
					BigDecimal value = (BigDecimal) ds.getValueAt(colName);
					if (value != null)
						fld.set(calculator, value);
				} catch (Exception ex){}
		}
		calculator.calculate();
		for (Iterator<String> it = fields.iterator(); it.hasNext();) {
			String colName = it.next();
			Field fld = ReflectionUtils.findDeclaredField(RowCalculator.class, colName);
			if (fld != null)
				try {
					BigDecimal value = (BigDecimal) fld.get(calculator);
					if (value != null)
						ds.setValueAt(value, colName);
				} catch (Exception ex){}
		}
	}

	private List<FinTurnAccItem> getFinTurnAcc() throws DataAccessException	{
		String sFields = "r.ID, r.PERIOD_ID, p.DATEFROM";
		String sFrom = this.table + " r left join finperiod p on p.id=r.period_id";
		String sWhere = "(0=0)";
		String sOrder = "p.DATEFROM";

		Date dt1 = null;
		Date dt2 = null;
		Date oneDate = null;

		if (periodId1 > 0) {
			dt1 = getPeriodBeginDate(periodId1);
			sWhere = sWhere + " and (p.datefrom >= ?)";
			oneDate = dt1;
		}
		if (periodId2 > 0) {
			dt2 = getPeriodBeginDate(periodId2);
			sWhere = sWhere + " and (p.datefrom <= ?)";
			oneDate = dt2;
		}
		
		Object[] query = createSQLParts(sFields, sFrom, sWhere, sOrder);
		String strSQL = String.format(ServerUtils.getUserLocale(),
				"select %s from %s where %s order by %s", query);

		List<FinTurnAccItem> ftacclst;

		if ((periodId1 > 0) && (periodId2 > 0))
			ftacclst = JdbcTemplate.getInstance().query(strSQL,
					new Object[] { new Timestamp(dt1.getTime()), new Timestamp(dt2.getTime()) },
					new FinTurnAccItemRM());
		else if ((periodId1 > 0) || (periodId2 > 0))
			ftacclst = JdbcTemplate.getInstance().query(strSQL,
					new Object[] { new Timestamp(oneDate.getTime()) },
					new FinTurnAccItemRM());
		else
			ftacclst = JdbcTemplate.getInstance().query(strSQL, null, new FinTurnAccItemRM());
		return ftacclst;
	}

	protected Class getColumnTypeByName(String fieldName) {
		for (Integer i = 0; i < integerFields.length; i++) 
			if (fieldName.equalsIgnoreCase(integerFields[i]))
				return Integer.class;
		for (Integer i = 0; i < strFields.length; i++) 
			if (fieldName.equalsIgnoreCase(strFields[i]))
				return String.class;
		for (Integer i = 0; i < currencyFields.length; i++)	
			if (fieldName.equalsIgnoreCase(currencyFields[i]))
				return BigDecimal.class;
		if (fieldName.equalsIgnoreCase("DATEFROM") || fieldName.equalsIgnoreCase("DATETO"))
			return Date.class;
		throw new IllegalArgumentException(String.format(ResStr.SINVALID_FIELD_NAME, "'" + fieldName + "'"));
	}

	private void preProcessing() {
		if (fields.contains("REMNENDNAT"))
			fields.add("REMNBEGNAT");
		if (fields.contains("REMNENDNATPLAN"))
			fields.add("REMNBEGNATPLAN");
		if (fields.contains("REMNENDCURPLAN"))
			fields.add("REMNBEGCURPLAN");
		if (fields.contains("REMNBEGNATDIFF")) {
			fields.add("REMNBEGNAT");
			fields.add("REMNBEGNATPLAN");
		}
		if (fields.contains("REMNBEGCURDIFF")) {
			fields.add("REMNBEGCUR");
			fields.add("REMNBEGCURPLAN");
		}
		if (fields.contains("INCOMENATDIFF")) {
			fields.add("INCOMENAT");
			fields.add("INCOMENATPLAN");
		}
		if (fields.contains("INCOMECURDIFF")) {
			fields.add("INCOMECUR");
			fields.add("INCOMECURPLAN");
		}
		if (fields.contains("OUTCOMECURDIFF")) {
			fields.add("OUTCOMECUR");
			fields.add("OUTCOMECURPLAN");
		}
		if (fields.contains("OUTCOMENATDIFF")) {
			fields.add("OUTCOMENAT");
			fields.add("OUTCOMENATPLAN");
		}
		if (fields.contains("REMNENDNATDIFF")) {
			fields.add("REMNENDNAT");
			fields.add("REMNENDNATPLAN");
		}
		if (fields.contains("REMNENDCURDIFF")) {
			fields.add("REMNENDCUR");
			fields.add("REMNENDCURPLAN");
		}

		boolean hasANL = fields.contains("ANL");
		for (byte i = 1; i <= MAX_FINANLLEVEL; i++)
			if (hasANL || fields.contains("ANL" + i + "_CODE"))
				fields.add("ANL" + i + "_ID");
		hasANL = fields.contains("FANL");
		for (byte i = 1; i <= MAX_FINANLLEVEL; i++)
			if (hasANL || fields.contains("FANL" + i + "_CODE"))
				fields.add("FANL" + i + "_ID");

	}

	private void getAnalytics() throws ApplicationException, NamingException {
		int pos = ds.getColIDbyName("ANL");
		if (pos > 0) {
			String s = StringUtils.EMPTY_STRING;
			for (byte i = MAX_FINANLLEVEL; i >= 1; i--) {
				s = Utils.getAnlCode((Integer) ds.getValueAt("ACC_ID"),
						(Integer) ds.getValueAt("ANL" + i + "_ID"), i) + s;
				if ((i > 1) && (!s.equals("")))
					s = "," + s;
			}
			ds.setValueAt(s, pos);
		}
	}

	public DataSet getDataSet()	{
		return ds;
	}

	public void open(boolean makeIndex) throws Exception {
		init();
		preProcessing();
		createDataSet(makeIndex);

		List<FinTurnAccItem> finTurnAcc = getFinTurnAcc();
		ColumnDef[] cd = ds.getColumnsInfo();
		for (Iterator it = finTurnAcc.listIterator(); it.hasNext();) {
			LinkedList<Object> lst = new LinkedList<Object>();
			FinTurnAccItem item = (FinTurnAccItem) it.next();
			Class cls = item.getClass();
			for (byte i = 0; i < ds.getColumnCount(); i++) {
				String colName = cd[i].getFieldName();
				if (colName.indexOf("ANL") == 0 && colName.endsWith("_ID")) {
					int aID = item.anlId[(Integer.valueOf(colName.substring(3, 4))) - 1];
					lst.add(aID);
				} else if (colName.indexOf("ANL") == 0 && colName.endsWith("_CODE")) {
					int anlLevel = Integer.valueOf(colName.substring(3, 4));
					int aID = item.anlId[anlLevel - 1];
					String aCode = getFieldName(item.ACC_CODE, aID, anlLevel, StringUtils.format("Anl%sClass", anlLevel));
					lst.add(aCode);
				} else if (colName.indexOf("FANL") == 0	&& colName.endsWith("_ID"))	{
					int faID = item.fAnlId[(Integer.valueOf(colName.substring(4, 5))) - 1];
					lst.add(faID);
				} else if (colName.indexOf("FANL") == 0	&& colName.endsWith("_CODE")) {
					int fAnlLevel = Integer.valueOf(colName.substring(4, 5));
					int faID = item.fAnlId[fAnlLevel - 1];
					String faCode = getFieldName(item.FEAT_CODE, faID, fAnlLevel, StringUtils.format("Anl%sClass", fAnlLevel));
					lst.add(faCode);
				} else {
					Field fld = ReflectionUtils.findDeclaredField(cls, colName);
					if (fld != null)
						try	{
							Object obj = fld.get(item);
							lst.add(obj);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					else
						lst.add(null);
				}
			}
			ds.addRow(lst);
			getAnalytics();
			calculateRow();
		}
	}

	public void setAccountFolderIds(List<Integer> accountFolderIds)	{
		this.accountFolderIds = accountFolderIds;
	}

	public void addAccount(Integer nAccountId) {
		accountIds.add(nAccountId);
	}

	public void clearAccounts()	{
	}

	public void setCurrencyCode(String curCode)	{
		if (curCode == null)
			curCode = StringUtils.EMPTY_STRING;
		currencyCode = curCode.toUpperCase(ServerUtils.getUserLocale());
	}

	public void setAnlIdList(Map<Integer, Set<Integer>> anlIdList) {
		this.anlIdList = anlIdList;
	}

	public void addAnlId(Integer level, Integer value) {

	}

	public void setFieldSet(Set<String> fields) throws RuntimeException	{
		fields.add("ID");
		fields.add("ACC_ID");
		fields.add("PERIOD_ID");
		fields.add("DATEFROM");
		for (Iterator<String> it = fields.iterator(); it.hasNext();)
			setComputeKind(it.next());
		this.fields = new LinkedHashSet<String>(fields);
	}

	public void setPeriods(Integer periodId1, Integer periodId2) {
		this.periodId1 = periodId1;
		this.periodId2 = periodId2;
	}

	public void setDates(Date date1, Date date2) {
		this.date1 = date1;
		this.date2 = date2;
	}

	public void setAccountsIDs(Set<Integer> accountIds) {
		if (accountIds!=null)
			this.accountIds = accountIds;
	}
	/*------------private--------------------------*/
	private void setComputeKind(String fieldName) {
		if (fieldName.equals("INCOMENAT"))
			computeKinds.add(ckIncomeNat);
		else if (fieldName.equals("OUTCOMENAT"))
			computeKinds.add(ckOutcomeNat);
		else if (fieldName.equals("INCOMECUR"))
			computeKinds.add(ckIncomeCur);
		else if (fieldName.equals("OUTCOMECUR"))
			computeKinds.add(ckOutcomeCur);
		else if (fieldName.equals("REMNENDNAT")) {
			computeKinds.add(ckIncomeNat);
			computeKinds.add(ckOutcomeNat);
			computeKinds.add(ckEndNat);
		} else if (fieldName.equals("REMNENDCUR")) {
			computeKinds.add(ckIncomeCur);
			computeKinds.add(ckOutcomeCur);
			computeKinds.add(ckEndCur);
		}
		if (fieldName.equals("INCOMENATPLAN"))
			computeKinds.add(ckIncomeNatPlan);
		else if (fieldName.equals("OUTCOMENATPLAN"))
			computeKinds.add(ckOutcomeNatPlan);
		else if (fieldName.equals("INCOMECURPLAN"))
			computeKinds.add(ckIncomeCurPlan);
		else if (fieldName.equals("OUTCOMECURPLAN"))
			computeKinds.add(ckOutcomeCurPlan);
		else if (fieldName.equals("REMNENDNATPLAN")) {
			computeKinds.add(ckIncomeNatPlan);
			computeKinds.add(ckOutcomeNatPlan);
			computeKinds.add(ckEndNatPlan);
		} else if (fieldName.equals("REMNENDCURPLAN")) {
			computeKinds.add(ckIncomeCurPlan);
			computeKinds.add(ckOutcomeCurPlan);
			computeKinds.add(ckEndCurPlan);
		}
	}
	
	/**
	 * Возвращает код аналитики
	 * @param accountCode
	 * 				- код счета
	 * @param anlId
	 * 				- идентификатор аналитики
	 * @param anlLevel
	 * 				- уровень аналитики
	 * @param anlClass
	 * 				- класс аналитики
	 * @return
	 */
	protected String getFieldName(String accountCode, int anlId, int anlLevel, String anlClass) {
		List<Account> accList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Account.class)
				.add(Restrictions.eq("Code", accountCode))
				.setMaxResults(1));
		if(accList.isEmpty() || anlId == 0)
			return StringUtils.EMPTY_STRING;
		Account account = accList.get(0);
		if (anlId != 0) {
			if ((account.isAnl1Kind() && anlLevel == 1) || (account.isAnl2Kind() && anlLevel == 2) || (account.isAnl3Kind() && anlLevel == 3) || (account.isAnl4Kind() && anlLevel == 4) || (account.isAnl5Kind() && anlLevel == 5)) {			
				return FinUtils.getAnlName(FinUtils.getBeanName((SysClass)account.getAttribute(anlClass)), anlId).trim();
			} else {
				return ServerUtils.getPersistentManager().find(Analytics.class, anlId).getCode().trim();
			}				
		} else 
			return StringUtils.EMPTY_STRING;
	}

}
