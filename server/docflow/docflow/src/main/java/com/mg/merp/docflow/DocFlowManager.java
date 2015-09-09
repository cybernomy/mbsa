/*
 * DocFlowManager.java
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
package com.mg.merp.docflow;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.document.model.DocHead;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Local;

/**
 * Сервис менеджер документооборота
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowManager.java,v 1.6 2008/07/31 14:21:03 safonov Exp $
 */
@Local
public interface DocFlowManager extends BusinessObjectService {

  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/docflow/DocFlowManager";

  /**
   * Зарезервированный ID этапа создание документа
   */
  final int DOC_FLOW_CREATE_DOC = 1;

  /**
   * Зарезервированный ID этапа создание документа на основе
   */
  final int DOC_FLOW_BASED_ON = 2;

  /**
   * Зарезервированный ID этапа создание документа с помощью BAi (Business Add-in)
   */
  final int CREATE_DOC_BY_BAI = 50;

  /**
   * Зарезервированный ID этапа завершения ДО, после прохождения данного этапа ДО будет считаться
   * выполненным
   */
  final int DOC_FLOW_END = 60;

  /**
   * Владельцем документа является ДО
   */
  final int DOCFLOW_OWNER = 0x10;

  /**
   * выполнить ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @throws AlreadyCompletedException если ДО выполнен
   * @throws DocumentNotFound          если документ не найден
   */
  void execute(Serializable documentId, final DocFlowListener listener)
      throws AlreadyCompletedException, DocumentNotFound;

  /**
   * выполнить ДО
   *
   * @param documentId     идентификатор документа
   * @param listener       слушатель ДО
   * @param paramsStrategy стратегия обработки параметров ДО
   * @throws AlreadyCompletedException если ДО выполнен
   * @throws DocumentNotFound          если документ не найден
   */
  void execute(Serializable documentId, final DocFlowListener listener, final DocFlowParamsStrategy paramsStrategy)
      throws AlreadyCompletedException, DocumentNotFound;

  /**
   * выполнить ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @param requester  инициатор выполнения
   * @param silent     признак использования пользовательского интерфейса, если <code>true</code> то
   *                   не использовать UI
   * @throws AlreadyCompletedException если ДО выполнен
   * @throws DocumentNotFound          если документ не найден
   */
  void execute(Serializable documentId, final DocFlowListener listener, int requester, boolean silent)
      throws AlreadyCompletedException, DocumentNotFound;

  /**
   * выполнить ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @param stageCode  код этапа ДО для выполнения
   * @throws AlreadyCompletedException если ДО выполнен
   * @throws DocumentNotFound          если документ не найден
   * @throws InaccesibleStateException если указанный код не доступен для выполнения
   */
  void execute(Serializable documentId, final DocFlowListener listener, String stageCode)
      throws AlreadyCompletedException, DocumentNotFound, InaccesibleStateException;

  /**
   * выполнить ДО
   *
   * @param documentId  идентификатор документа
   * @param listener    слушатель ДО
   * @param stageCode   код этапа ДО для выполнения
   * @param processDate дата отработки ДО
   * @param requester   инициатор выполнения
   * @param silent      признак использования пользовательского интерфейса, если <code>true</code>
   *                    то не использовать UI
   * @throws AlreadyCompletedException если ДО выполнен
   * @throws DocumentNotFound          если документ не найден
   * @throws InaccesibleStateException если указанный код не доступен для выполнения
   */
  void execute(Serializable documentId, final DocFlowListener listener, String stageCode, Date processDate, int requester, boolean silent)
      throws AlreadyCompletedException, DocumentNotFound, InaccesibleStateException;

  /**
   * откатить выполнение ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   */
  void rollback(Serializable documentId, final DocFlowListener listener);

  /**
   * откатить выполнение ДО
   *
   * @param documentId     идентификатор документа
   * @param listener       слушатель ДО
   * @param paramsStrategy стратегия обработки параметров ДО
   */
  void rollback(Serializable documentId, final DocFlowListener listener, final DocFlowParamsStrategy paramsStrategy);

  /**
   * откатить выполнение ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @param requester  инициатор выполнения
   * @param silent     признак использования пользовательского интерфейса, если <code>true</code> то
   *                   не использовать UI
   */
  void rollback(Serializable documentId, final DocFlowListener listener, int requester, boolean silent);

  /**
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @param stageCode  код этапа ДО для выполнения
   */
  void rollback(Serializable documentId, final DocFlowListener listener, String stageCode);

  /**
   * откатить выполнение ДО
   *
   * @param documentId идентификатор документа
   * @param listener   слушатель ДО
   * @param stageCode  код этапа ДО для выполнения
   * @param requester  инициатор выполнения
   * @param silent     признак использования пользовательского интерфейса, если <code>true</code> то
   *                   не использовать UI
   */
  void rollback(Serializable documentId, final DocFlowListener listener, String stageCode, int requester, boolean silent);

  /**
   * регистрация создания документа
   *
   * @param docHead объект документ
   */
  void createDocument(DocHead docHead);

  /**
   * регистрация изменения документа
   *
   * @param docHead объект документ
   */
  void modifyDocument(DocHead docHead);

  /**
   * проверка возможности изменения документа
   *
   * @param docHead объект документ
   */
  void checkStatus(DocHead docHead);

}
