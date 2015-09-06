/*
 * FinOperProcessor.java
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
package com.mg.merp.finance;

import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentDocFlowListener;

/**
 * Бизнес-компонент "Процессор финансовых операций"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: FinOperProcessor.java,v 1.1 2007/11/30 12:55:41 alikaev Exp $
 */
public interface FinOperProcessor {

  /**
   * Отработка этапа документооборота "Создать ФО"
   *
   * @param params   - параметры документооборота
   * @param listener - слушатель на событие открыть созданную ФО
   */
  void processCreateFinOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

  /**
   * Откат этапа документооборота "Создать ФО"
   *
   * @param params - параметры документооборота
   */
  void rollbackCreateFinOper(DocFlowPluginInvokeParams params);

}
