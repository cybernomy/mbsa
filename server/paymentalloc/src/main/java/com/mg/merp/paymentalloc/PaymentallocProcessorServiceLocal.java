/*
 * PaymentallocProcessorServiceLocal.java
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
package com.mg.merp.paymentalloc;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Сервис процессора модуля "Журнал платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentallocProcessorServiceLocal.java,v 1.2 2007/05/31 14:08:57 sharapov Exp $
 */
public interface PaymentallocProcessorServiceLocal extends BusinessObjectService {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/paymentalloc/PaymentallocProcessor"; //$NON-NLS-1$

  /**
   * Этап ДО "Создать запись в журнале платежей"
   *
   * @param docFlowParams     - параметры ДО
   * @param processorListener - слушатель процессора
   */
  void createPayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;

  /**
   * Откат этапа ДО "Создать запись в журнале платежей"
   *
   * @param docFlowParams - параметры ДО
   */
  void rollBackCreatePayment(DocFlowPluginInvokeParams docFlowParams);

  /**
   * Этап ДО "Отработка в журнале платежей"
   *
   * @param docFlowParams     - параметры ДО
   * @param processorListener - слушатель процессора
   */
  void allocatePayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;

  /**
   * Откат этапа ДО "Отработка в журнале платежей"
   *
   * @param docFlowParams - параметры ДО
   */
  void rollBackAllocatePayment(DocFlowPluginInvokeParams docFlowParams);

  /**
   * Этап ДО "Отработка в журнале платежей интерактивная"
   *
   * @param docFlowParams     - параметры ДО
   * @param processorListener - слушатель процессора
   */
  void allocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;

  /**
   * Откат этапа ДО "Отработка в журнале платежей интерактивная"
   *
   * @param docFlowParams - параметры ДО
   */
  void rollBackAllocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams);

}
