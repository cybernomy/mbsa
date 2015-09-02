/*
 * DocumentUtils.java
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
package com.mg.merp.document.support;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.Document;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;

/**
 * ”тилиты работы с документами
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentUtils.java,v 1.9 2008/08/20 06:06:55 safonov Exp $
 */
public class DocumentUtils {

	/**
	 * создание бизнес-компонента документа
	 * 
	 * @param docSection	раздел документа
	 * @return	бизнес-компонент
	 */
	@SuppressWarnings("unchecked")
	public static Document getDocumentService(DocSection docSection) {
		return com.mg.merp.docflow.support.DocumentUtils.getDocumentService(docSection);
	}

	/**
	 * создание бизнес-компонента документа со спецификаци€ми
	 * 
	 * @param docSection	раздел документа
	 * @return	бизнес-компонент
	 */
	@SuppressWarnings("unchecked")
	public static GoodsDocument getGoodsDocumentService(DocSection docSection) {
		return com.mg.merp.docflow.support.DocumentUtils.getGoodsDocumentService(docSection);
	}

	/**
	 * создание бизнес-компонента спецификации
	 * 
	 * @param docSection	раздел документа
	 * @return	бизнес-компонент
	 */
	public static <T extends DocSpec, ID extends Serializable> GoodsDocumentSpecification<T, ID> getGoodsDocumentSpecificationService(DocSection docSection) {
		return com.mg.merp.docflow.support.DocumentUtils.getGoodsDocumentSpecificationService(docSection);
	}

	/**
	 * создание бизнес-компонента образца документа
	 * 
	 * @param docSection	раздел документа
	 * @return	бизнес-компонент
	 */
	@SuppressWarnings("unchecked")
	public static DocumentPattern getDocumentPatternService(DocSection docSection) {
		return com.mg.merp.docflow.support.DocumentUtils.getDocumentPatternService(docSection);
	}

	/**
	 * установка курса валют документа
	 * 
	 * @param document	сервис документа
	 * @param docHead	сущность документа
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public static void setExchangeRate(DataBusinessObjectService document, DocHead docHead) {
		Document docService = (Document) document;
		Configuration cfg = docService.getConfiguration();
		CurrencyRateServiceLocal currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
		try {
			//пытаемс€ получить пр€мой курс дл€ валют
			docHead.setCurCource(currencyRateService.getCurrencyRate(cfg.getLocalCurrency(), docHead.getCurrency(),
					docHead.getCurrencyRateAuthority(), docHead.getCurrencyRateType(), docHead.getDocDate()));
		} catch (CurrencyRateNotFoundException e) {
			//если пр€мого курса нет, то пытаемс€ получить обратный курс
			docHead.setCurCource(MathUtils.divide(BigDecimal.ONE, currencyRateService.getCurrencyRate(docHead.getCurrency(), cfg.getLocalCurrency(),
					docHead.getCurrencyRateAuthority(), docHead.getCurrencyRateType(), docHead.getDocDate()), new RoundContext(CurrencyRateServiceLocal.DEFAULT_RATE_SCALE)));
		}
		docService.adjust(docHead);		
	}

	/**
	 * ¬ыбрать контракт
	 * @param searchHelpListener - слушатель выбора контракта
	 */
	public static void chooseContract(SearchHelpListener searchHelpListener) {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.contract.support.ui.ContractSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(searchHelpListener);
		searchHelp.search();
	}
	
	/**
	 * ѕросмотреть контракт у документа
	 * @param docHead - документ
	 */
	public static void viewContract(DocHead docHead) {
		if(docHead != null)
			viewDocument(docHead.getContract());
	}

	/**
	 * ѕросмотреть документ-основание у документа
	 * @param docHead - документ
	 */
	public static void viewBaseDocument(DocHead docHead) {
		if(docHead != null)
			viewDocument(docHead.getBaseDocument());
	}
	
	/**
	 * ѕросмотреть документ
	 * @param docHead - документ
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public static void viewDocument(DocHead docHead) {
		if(docHead != null) {
			if(!ServerUtils.getPersistentManager().contains(docHead))
				docHead = ServerUtils.getPersistentManager().find(DocHead.class, docHead.getId());
			MaintenanceHelper.view(getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
		}
	}
	
	/**
	 * ѕросмотреть документ
	 * @param entity - сущность
	 * @param documentAttributeName - наименование атрибута документа у сущности
	 */
	public static void viewDocument(PersistentObject entity, String documentAttributeName) {
		if(entity == null || StringUtils.stringNullOrEmpty(documentAttributeName))
			return;
		
		if(entity.hasAttribute(documentAttributeName) && entity.getPrimaryKey() != null) {
			entity = ServerUtils.getPersistentManager().find(entity.getClass(), entity.getPrimaryKey());
			Object docHead = entity.getAttribute(documentAttributeName);
			if(docHead instanceof DocHead)
				viewDocument((DocHead) docHead);
		}
	}

	/**
	 * получить раздел документов
	 * 
	 * @param docSection	идентификатор раздела
	 * @return	раздел документов
	 */
	public static DocSection getDocSection(int docSection) {
		return ServerUtils.getPersistentManager().find(DocSection.class, docSection);
	}

	/**
	 * получить сущность заголовка документа через сервис документов, если сущность не находитс€ в сессии
	 * то будет загружена нова€ сущность из хранилища
	 * 
	 * @param docHead	сущность заголовка документа
	 * @param docSection	раздел документов
	 * @return	сущность заголовка документа
	 */
	@SuppressWarnings("unchecked")
	public static DocHead loadDocumentHead(DocHead docHead, DocSection docSection) {
		if (!ServerUtils.getPersistentManager().contains(docHead))
			return (DocHead) DocumentUtils.getGoodsDocumentService(docSection).load(docHead.getId());
		else
			return docHead;		
	}

	/**
	 * получить сущность заголовка документа через сервис документов, если сущность не находитс€ в сессии
	 * то будет загружена нова€ сущность из хранилища
	 * 
	 * @param docHead	сущность заголовка документа
	 * @param docSection	идентификатор раздела документов
	 * @return	сущность заголовка документа
	 */
	public static DocHead loadDocumentHead(DocHead docHead, int docSection) {
		return loadDocumentHead(docHead, getDocSection(docSection));
	}

}
