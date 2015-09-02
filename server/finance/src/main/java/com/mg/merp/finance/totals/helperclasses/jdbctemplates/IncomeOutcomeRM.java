/*
 * IncomeOutcomeRM.java
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
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mg.framework.api.jdbc.RowMapper;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: IncomeOutcomeRM.java,v 1.4 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class IncomeOutcomeRM implements RowMapper<IncomeOutcomeItem>
{

	public IncomeOutcomeItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		BigDecimal sumnat=null;
		BigDecimal sumcur=null;
		try
		{
			sumnat = rs.getBigDecimal("sumnat");
		}catch (SQLException ex){}
		try
		{
			sumcur = rs.getBigDecimal("sumcur");
		}catch (SQLException ex){}
		return new IncomeOutcomeItem(sumnat, sumcur);
	}

}
