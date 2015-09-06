/* DocumentProcessor.java
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
package com.mg.merp.document;


import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Бизнес-компонент "Процессор документов"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: DocumentProcessor.java,v 1.4 2009/02/04 09:33:09 safonov Exp $
 */
public interface DocumentProcessor {

  /**
   * Этап "Создание документа на основании текущего"
   *
   * @param params   параметры этапа
   * @param listener слушатель создания документа на основе этапом ДО
   */
  void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

  /**
   * Этап "Создание документа на основании текущего"
   *
   * @param params         параметры этапа
   * @param listener       слушатель создания документа на основе этапом ДО
   * @param createCallback объект обратного вызова создания документа
   */
  void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener, CreateDocumentBasisOfCallback createCallback);

  /**
   * Откат этапа "Создание документа на основании текущего"
   *
   * @param params параметры
   */
  void rollbackCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

  /**
   * Этап "Создание документа на комплектующие"
   *
   * @param params параметры этапа
   */
  void processCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);


  /**
   * Откат этапа "Создание документа на комплектующие"
   *
   * @param params параметры
   */
  void rollbackCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);
}
