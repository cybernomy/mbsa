/*
 * PaymentcontrolProcessorServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Сервис процессора модуля "Платежный календарь"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentcontrolProcessorServiceLocal.java,v 1.2 2007/06/01 07:14:11 sharapov Exp $
 */
public interface PaymentcontrolProcessorServiceLocal extends BusinessObjectService {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/PaymentcontrolProcessor"; //$NON-NLS-1$
	
	/**
	 * Этап ДО "Создать запись реестра обязательств"
	 * @param docFlowParams - параметры документооборота
	 * @param processorListener - слушатель процессора
	 */
	void createLiability(DocFlowPluginInvokeParams docFlowParams, PaymentControlProcessorListener processorListener)  throws Exception;
	
	/**
	 * Откат этапа ДО "Создать запись реестра обязательств"
	 * @param docFlowParams - параметры документооборота
	 */
	void rollBackCreateLiability(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * Этап ДО "Подтверждение исполнения обязательства"
	 * @param docFlowParams - параметры документооборота
	 */
	void confirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * Откат этапа ДО "Подтверждение исполнения обязательства"
	 * @param docFlowParams - параметры документооборота
	 */
	void rollBackConfirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams);
	
}
