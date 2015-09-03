/**
 * OmittedWhitespaceStringType.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.support.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.type.StringType;

/**
 * Строковый тип удаляющий лидирующие и завершающие пробелы
 * 
 * @author Oleg V. Safonov
 * @version $Id: OmittedWhitespaceStringType.java,v 1.1 2008/10/09 13:21:21 safonov Exp $
 */
public class OmittedWhitespaceStringType extends StringType {

	/* (non-Javadoc)
	 * @see org.hibernate.type.StringType#get(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object get(ResultSet rs, String name) throws SQLException {
		String result = (String) super.get(rs, name);
		return result == null ? null : result.trim();
	}

}
