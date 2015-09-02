/*
 * DocHeadStateBrowseItem.java
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
package com.mg.merp.docflow;

import java.util.Date;

/**
 * @author Oleg V. Safonov
 * @version $Id: DocHeadStateBrowseItem.java,v 1.1 2006/03/01 15:03:50 safonov Exp $
 */
public class DocHeadStateBrowseItem {
	public int id;
	public double readySum;
	public String docSection;
	public String document;
	public Date dateTime;
	public String userName;
	public Integer data1;
	public Integer data2;
	public boolean createDoc;
}
