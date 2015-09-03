/*
 * DocFlowHelper.java
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
package com.mg.merp.docflow.support;

import java.io.Serializable;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowParamsStrategy;
import com.mg.merp.docflow.support.ui.DocFlowHistoryForm;
import com.mg.merp.docflow.support.ui.DocFlowMapForm;
import com.mg.merp.document.model.DocHead;

/**
 * Класс helper для взаимодействия с сервисом ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowHelper.java,v 1.7 2007/10/23 15:20:03 safonov Exp $
 */
public class DocFlowHelper {

	private static DocFlowManager getDocFlowManager() {
		return (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
	}
	
	/**
	 * запуск ДО
	 * 
	 * @param documentId	идентификатор документа
	 */
	public static void execute(Serializable documentId) {
		DocFlowManager manager = getDocFlowManager();
		manager.execute(documentId, null);
	}

	/**
	 * запуск ДО
	 * 
	 * @param documentId	идентификатор документа
	 * @param requester		инициатор выполнения
	 * @param silent		признак использования пользовательского интерфейса, если <code>true</code> то не использовать UI
	 */
	public static void execute(Serializable documentId, int requester, boolean silent) {
		DocFlowManager manager = getDocFlowManager();
		manager.execute(documentId, null, requester, silent);		
	}
	
	/**
	 * запуск ДО
	 * 
	 * @param documentId	идентификатор документа
	 * @param stageCode		код этапа ДО для выполнения
	 */
	public static void execute(Serializable documentId, String stageCode) {
		DocFlowManager manager = getDocFlowManager();
		manager.execute(documentId, null, stageCode);
	}
	
	/**
	 * запуск ДО
	 * 
	 * @param documentId		идентификатор документа
	 * @param paramsStrategy	стратегия обработки параметров ДО
	 */
	public static void execute(Serializable documentId, DocFlowParamsStrategy paramsStrategy) {
		DocFlowManager manager = getDocFlowManager();
		manager.execute(documentId, null, paramsStrategy);
	}
	
	/**
	 * откат ДО
	 * 
	 * @param documentId	идентификатор документа
	 */
	public static void rollback(Serializable documentId) {
		DocFlowManager manager = getDocFlowManager();
		manager.rollback(documentId, null);
	}

	/**
	 * откат ДО
	 * 
	 * @param documentId	идентификатор документа
	 * @param stageCode		код этапа ДО для выполнения
	 */
	public static void rollback(Serializable documentId, String stageCode) {
		DocFlowManager manager = getDocFlowManager();
		manager.rollback(documentId, null, stageCode);		
	}
	
	/**
	 * откат ДО
	 * 
	 * @param documentId		идентификатор документа
	 * @param paramsStrategy	стратегия обработки параметров ДО
	 */
	public static void rollback(Serializable documentId, DocFlowParamsStrategy paramsStrategy) {
		DocFlowManager manager = getDocFlowManager();
		manager.rollback(documentId, null, paramsStrategy);		
	}
	
	/**
	 * откат ДО
	 * 
	 * @param documentId	идентификатор документа
	 * @param requester		инициатор выполнения
	 * @param silent		признак использования пользовательского интерфейса, если <code>true</code> то не использовать UI
	 */
	public static void rollback(Serializable documentId, int requester, boolean silent) {
		DocFlowManager manager = getDocFlowManager();
		manager.rollback(documentId, null, requester, silent);
	}

	/**
	 * показ истории ДО
	 * 
	 * @param documentId	идентификатор документа
	 */
	public static void showDocumentHistory(Serializable documentId) {
		final DocFlowHistoryForm form = (DocFlowHistoryForm) UIProducer.produceForm("com/mg/merp/docflow/resources/DocFlowHistoryForm.mfd.xml");
		form.execute(documentId);
	}

	/**
	 * показ карты ДО
	 * 
	 * @param docTypeId		идентификатор типа документа
	 */
	public static void showDocFlowMap(Serializable docTypeId) {
		final DocFlowMapForm form = (DocFlowMapForm) UIProducer.produceForm("com/mg/merp/docflow/resources/DocFlowMapForm.mfd.xml");
		form.execute(docTypeId);
	}
	
	/**
	 * регистрация создания документа
	 * 
	 * @param docHead	документ
	 */
	public static void createDocument(DocHead docHead) {
		DocFlowManager manager = (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
		manager.createDocument(docHead);
	}

	/**
	 * регистрация изменения документа
	 * 
	 * @param docHead	документ
	 */
	public static void modifyDocument(DocHead docHead) {
		DocFlowManager manager = (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
		manager.modifyDocument(docHead);
	}

	/**
	 * проверка возможности изменения документа
	 * 
	 * @param docHead	документ
	 */
	public static void checkStatus(DocHead docHead) {
		DocFlowManager manager = (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
		manager.checkStatus(docHead);
	}

}
