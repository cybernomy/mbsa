/*
 * HibernateFirebirdDialect.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

import org.hibernate.dialect.FirebirdDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.exception.ViolatedConstraintNameExtracter;

/**
 * Диалект СУБД Firebird
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernateFirebirdDialect.java,v 1.2 2007/04/03 14:41:15 safonov Exp $
 */
public class HibernateFirebirdDialect extends FirebirdDialect {
	private static ViolatedConstraintNameExtracter EXTRACTER = new InterbaseFirebirdViolatedConstraintNameExtracter();

	public HibernateFirebirdDialect() {
		super();
		
		registerFunction("nvl", new StandardSQLFunction("COALESCE"));
	}
	
	public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
		return EXTRACTER;
	}

}
