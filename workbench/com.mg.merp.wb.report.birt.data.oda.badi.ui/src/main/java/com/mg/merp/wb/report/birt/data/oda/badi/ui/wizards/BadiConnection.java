/*
 * BadiConnection.java
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
package com.mg.merp.wb.report.birt.data.oda.badi.ui.wizards;

import com.mg.merp.wb.report.birt.data.oda.badi.Connection;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BadiConnection.java,v 1.3 2006/11/07 08:16:05 poroxnenko Exp $
 */
public class BadiConnection {
	//conncetion instantce
	private Connection conn;

	//query info
	private String relationInfo;

	Connection getConnection() {
		if (conn == null) {
			conn = new Connection();
		}
		return conn;
	}

	/**
	 * set relation information
	 * @param info
	 */
	void setRelationInformation(String info) {
		relationInfo = info;
	}

	/**
	 * get relation information
	 * @return
	 */
	String getRelationInformation() {
		return relationInfo == null ? "" : relationInfo;
	}
}
