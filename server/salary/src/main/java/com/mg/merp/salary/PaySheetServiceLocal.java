/*
 * PaySheetServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.salary;

import com.mg.merp.salary.model.PaySheet;

/**
 * Сервис бизнес-компонента "Платежные ведомости"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaySheetServiceLocal.java,v 1.2 2007/08/27 06:16:11 sharapov Exp $
 */
public interface PaySheetServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PaySheet, Integer> {

	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME= "merp/salary/PaySheet"; //$NON-NLS-1$ 

	public int createCashDocIn( int paySheetId,int docModelId ) throws com.mg.framework.api.ApplicationException;

	public int createCashDocOut( int paySheetId,int docModelId ) throws com.mg.framework.api.ApplicationException;

}
