/*
 * AccountServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.finance.model.Account;

/**
 * 
 * @author leonova
 * @version $Id: AccountServiceLocal.java,v 1.2 2006/08/28 12:47:43 leonova Exp $
 */
public interface AccountServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<Account, Integer>
{
	/**
	 * тип папки для финансового плана счетов
	 */
	final static short FOLDER_PART = 38;
	
   public com.mg.framework.api.AttributeMap getFullRecord( java.lang.String code ) throws com.mg.framework.api.ApplicationException;

   public int canChangeAnalytics( int accId ) throws com.mg.framework.api.ApplicationException;

}
