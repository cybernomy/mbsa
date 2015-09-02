/**
 * TestConverter.java
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
package com.mg.test.framework;

import org.junit.Before;
import org.junit.Test;

import com.mg.framework.api.Logger;
import com.mg.framework.dbconverter.ant.ConverterTask;

/**
 * @author Oleg V. Safonov
 * @version $Id: TestConverter.java,v 1.2 2008/11/14 08:36:37 safonov Exp $
 */
public class TestConverter {

	@Before
	public void initTest() {
		Logger.setPluginClassName("com.mg.test.framework.ConsoleLoggerPlugin");
	}
	
	@Test
	public void executeFirebirdConvertation() throws Exception {
		ConverterTask ct = new ConverterTask();
		//Logger.get
		
		ct.setCharacterSet("WIN1251");
		ct.setDatabaseName("firebird");
		ct.setDbmsPath("D:/opt/Firebird_2_0/bin");
		ct.setJdbcDriverClass("org.firebirdsql.jdbc.FBDriver");
		ct.setPassword("1");
		ct.setScriptPath("D:/projects/merp40x/db/scripts/updatesql");
		ct.setScriptLogDir("D:/projects/merp40x/tools/dbconverter/output");
		ct.setUserName("SYSDBA");
		ct.setDbUrl("rd01:E:/MERP4.FDB");
		ct.setTempDir("D:/projects/merp40x/tools/dbconverter/output");
		
		ct.execute();
	}

	@Test
	public void executePostgreSQLConvertation() throws Exception {
		ConverterTask ct = new ConverterTask();
		
		ct.setDatabaseName("postgresql");
		ct.setDbmsPath("d:/opt/PostgreSQL/8.3/bin");
		ct.setJdbcDriverClass("org.postgresql.Driver");
		ct.setPassword("1");
		ct.setScriptPath("D:/projects/merp40x/db/scripts/updatesql");
		ct.setScriptLogDir("D:/projects/merp40x/tools/dbconverter/output");
		ct.setUserName("postgres");
		ct.setDbmsHost("rd01");
		ct.setDbName("mbsa");
		ct.setTempDir("D:/projects/merp40x/tools/dbconverter/output");
		
		ct.execute();
	}

}
