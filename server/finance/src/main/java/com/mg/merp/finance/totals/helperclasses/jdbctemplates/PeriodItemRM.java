/*
 * PeriodItemRM.java
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
 * @version $Id: PeriodItemRM.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class PeriodItemRM implements RowMapper<PeriodItem>
{

	public PeriodItem mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		int id;
		Date dateto ;
		try
		{
			id = rs.getInt("id");
		}catch(Exception ex){id = 0;}
		try
		{
			dateto = rs.getDate("dateto");
		}catch(Exception ex){dateto = null;}
		//вставлена проверка, т.к. этот RowMapper используется и 
		//в выборке, возвращающей только dateto
		
		return new PeriodItem(id , rs.getDate("datefrom"), dateto);
	}

}
