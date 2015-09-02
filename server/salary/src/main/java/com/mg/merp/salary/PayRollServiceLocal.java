/*
 * PayRollServiceLocal.java
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

import java.io.Serializable;

import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.salary.model.FeeSummaryHead;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.PaySheet;

/**
 * —ервис бизнес-компонента "–асчетные ведомости"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PayRollServiceLocal.java,v 1.2 2007/08/27 06:16:11 sharapov Exp $
 */
public interface PayRollServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PayRoll, Integer> {

	/**
	 * »м€ сервиса
	 */
	final static String SERVICE_NAME= "merp/salary/PayRoll"; //$NON-NLS-1$ 
	
	/**
	 * —оздать платежную ведомость на основе рассчетной
	 * @param payRollId - идентификатор рассчетной ведомости
	 * @return платежна€ ведомость
	 */
	PaySheet createPaySheet(Integer payRollId);

	/**
	 * —оздать свод начислений/удержаний по аналитике 
	 * @param payRollIds - список идентификаторов расчетных ведомостей
	 * @param feeSummaryPattern - образец свода начислений/удержаний по аналитике
	 * @return свод начислений/удержаний по аналитике
	 */
	FeeSummaryHead createFeeSummary(Serializable[] payRollIds, DocHeadModel feeSummaryPattern);
	
	
	public void clearAndRecalc( int payRollId ) throws com.mg.framework.api.ApplicationException;

	public void recalc( int payRollId ) throws com.mg.framework.api.ApplicationException;

}
