/*
 * SettlementProcessorServiceLocal.java
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
package com.mg.merp.settlement;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Сервис процессора модуля "Расчеты с партнерами"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: SettlementProcessorServiceLocal.java,v 1.2 2007/03/19 15:05:29 sharapov Exp $
 */
public interface SettlementProcessorServiceLocal extends BusinessObjectService {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/settlement/SettlementProcessor"; //$NON-NLS-1$

	/**
	 * Этап ДО "Поставить в план расчётов с партнёром"
	 * @param docFlowParams - параметры документооборота
	 */
	void setToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * Откат этапа ДО "Поставить в план расчётов с партнёром"
	 * @param docFlowParams - параметры документооборота
	 */
	void rollBackSetToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * Этап ДО "Отработать в расчетах"
	 * @param docFlowParams - параметры документооборота
	 */
	void processInSettlement(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * Откат этапа ДО "Отработать в расчетах"
	 * @param docFlowParams - параметры документооборота
	 */
	void rollBackProcessInSettlement(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * Этап ДО "Снять с плана расчётов с партнёром"
	 * @param docFlowParams - параметры документооборота
	 */
	void unsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * Откат этапа ДО "Снять с плана расчётов с партнёром"
	 * @param docFlowParams - параметры документооборота
	 */
	void rollBackUnsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

}
