/*
 * AccountItemRM.java
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
 * @version $Id: AccountItemRM.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class AccountItemRM implements RowMapper<AccountItem>
{

	public AccountItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new AccountItem(rs.getInt("id"), (rs.getString("upcode")).trim());
	}

}
