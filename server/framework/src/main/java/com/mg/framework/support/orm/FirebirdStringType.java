/**
 * FirebirdStringType.java
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

import org.apache.commons.lang.StringUtils;
import org.hibernate.type.StringType;

/**
 * Строковый тип для поддержки СУБД Firebird, данная СУБД загружает значения полей с типом CHAR
 * дополняя до требуемой длинны пробелами. В прикладном коде приводит к лишним действиям по удалению
 * пробелов. Применяем данный тип для всех строковых свойств сущностей.
 * 
 * @author Oleg V. Safonov
 * @version $Id: FirebirdStringType.java,v 1.1 2008/10/09 13:26:30 safonov Exp $
 */
public class FirebirdStringType extends StringType {

	/* (non-Javadoc)
	 * @see org.hibernate.type.StringType#get(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object get(ResultSet rs, String name) throws SQLException {
		String result = (String) super.get(rs, name);
		return result == null ? null : StringUtils.stripEnd(result, null);
	}

}
