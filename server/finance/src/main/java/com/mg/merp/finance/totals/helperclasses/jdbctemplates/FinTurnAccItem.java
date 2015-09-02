/*
 * FinTurnAccItem.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.finance.totals.helperclasses.jdbctemplates;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.finance.totals.FinanceTotals;

/**
 * @author Valentin A. Poroxnenko
 * @author Konstantin S. Alikaev
 * @version $Id: FinTurnAccItem.java,v 1.4 2008/02/12 14:28:20 alikaev Exp $
 */
public class FinTurnAccItem
{
    //common
	protected int ID;
	protected int PERIOD_ID;
	protected String PNAME;
	protected String CURCODE;
	protected Date DATEFROM;
	protected Date DATETO;
	//accounts
	protected int ACC_ID;
	protected int ACCFOLDER_ID;
	public String ACC_CODE;
	protected String ANL;
	public int[] anlId;
	public String[] anlCode;
	//features	
	protected int FEAT_ID;
	protected int FEATFOLDER_ID;
	public String FEAT_CODE;
	protected String FANL;
	public int[] fAnlId;
	public String[] fAnlCode;
	//total
	protected BigDecimal REMNBEGNAT;
	protected BigDecimal REMNBEGCUR;
	protected BigDecimal INCOMENAT;
	protected BigDecimal INCOMECUR;
	protected BigDecimal OUTCOMENAT;
	protected BigDecimal OUTCOMECUR;
	protected BigDecimal REMNENDNAT;
	protected BigDecimal REMNENDCUR;
	protected BigDecimal REMNBEGNATPLAN;
	protected BigDecimal REMNBEGCURPLAN;
	protected BigDecimal INCOMENATPLAN;
	protected BigDecimal INCOMECURPLAN;
	protected BigDecimal OUTCOMENATPLAN;
	protected BigDecimal OUTCOMECURPLAN;
	protected BigDecimal REMNENDNATPLAN;
	protected BigDecimal REMNENDCURPLAN;
		
	public FinTurnAccItem()
	{
		anlId = new int[FinanceTotals.MAX_FINANLLEVEL];
		anlCode = new String[FinanceTotals.MAX_FINANLLEVEL];
		fAnlId = new int[FinanceTotals.MAX_FINANLLEVEL];
		fAnlCode = new String[FinanceTotals.MAX_FINANLLEVEL];
	}
}
