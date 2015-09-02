/*
 * CorrDataItemRM.java
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

import java.sql.*;
import com.mg.framework.api.jdbc.RowMapper;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: CorrDataItemRM.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class CorrDataItemRM implements RowMapper<CorrDataItem>
{
	public CorrDataItem mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		CorrDataItem cdi = new CorrDataItem();
		
		try
		{
		  cdi.accId = rs.getInt("SRCACC_ID");
		}catch (Exception ex)
		 {
			cdi.accId = rs.getInt("DSTACC_ID");
		 }
		
		cdi.accCode = rs.getString("ACC_CODE").trim();
		
		cdi.sumCur_Nat = new IncomeOutcomeItem(rs.getBigDecimal("sumnat"), rs.getBigDecimal("sumcur"));
		return cdi;
	}

}
