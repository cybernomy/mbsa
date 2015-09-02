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
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.account;

import com.mg.merp.account.model.AccPlan;

/**
 * Контроллер плана счетов
 * 
 * @author leonova
 * @version $Id: AccountServiceLocal.java,v 1.3 2008/03/13 06:18:18 alikaev Exp $
 */
public interface AccountServiceLocal extends com.mg.framework.api.DataBusinessObjectService<AccPlan, Integer> {
	/**
	 * тип папки для плана счетов
	 */
	final static short FOLDER_PART = 2;
	
   public com.mg.framework.api.AttributeMap getFullRecord( java.lang.String code ) throws com.mg.framework.api.ApplicationException;

}
