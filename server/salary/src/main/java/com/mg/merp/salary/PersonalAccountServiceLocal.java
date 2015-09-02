/*
 * PersonalAccountServiceLocal.java
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

import com.mg.merp.personnelref.model.PersonalAccount;

/**
 * Сервис бизнес-компонента "Лицевой счет сотрудника" (плоский список?)
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonalAccountServiceLocal.java,v 1.3 2007/07/09 08:20:19 sharapov Exp $
 */
public interface PersonalAccountServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PersonalAccount, Integer> {

	/**
	 * Имя сервиса
	 */
	final static String LOCAL_SERVICE_NAME= "merp/salary/PersonalAccount"; //$NON-NLS-1$

	
	public void copyFee( int sourceFeeModelId,int[] destPersonalAccountId ) throws com.mg.framework.api.ApplicationException;

	public void removeFee( int feeRefId,java.util.Date beginDate,java.util.Date endDate,int[] personalAccountId ) throws com.mg.framework.api.ApplicationException;

	public void closeFee( int feeRefId,java.util.Date closeDate,int[] personalAccountId ) throws com.mg.framework.api.ApplicationException;

}
