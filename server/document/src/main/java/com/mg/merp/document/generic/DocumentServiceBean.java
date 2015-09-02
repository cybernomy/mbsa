/*
 * DocumentServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.document.generic;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.security.PermitAll;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.CreateDocumentByPatternStrategy;
import com.mg.merp.document.Document;
import com.mg.merp.document.DocumentNumberStrategy;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.support.ConfigurationImpl;
import com.mg.merp.document.support.DefaultCreateDocumentByPatternStrategy;
import com.mg.merp.document.support.DocumentNumberStrategyFactory;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;

/**
 * ������� ���������� ������-���������� "��������"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author leonova
 * @version $Id: DocumentServiceBean.java,v 1.22 2008/10/29 15:16:14 safonov Exp $
 */
public abstract class DocumentServiceBean<T extends com.mg.merp.document.model.DocHead, ID extends Serializable, M extends DocumentPattern>
		extends AbstractPOJODataBusinessObjectServiceBean<T, ID> implements Document<T, ID, M> {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(T entity) {
		entity.setUNID(DataUtils.generateUUID());
		if (entity.getDocDate() == null)
			entity.setDocDate(DateTimeUtils.getDayStart(DateTimeUtils.nowDate()));
		entity.setDocSection(getDocSection());
		entity.setRequester(com.mg.merp.docflow.support.DocumentUtils.getCurrentDocumentOwner());

		Configuration cfg = getConfiguration();
		if (cfg != null) {
			entity.setCurrency(cfg.getBaseCurrency());
			entity.setCurrencyRateType(cfg.getCurrencyRateType());
			entity.setCurrencyRateAuthority(cfg.getCurrencyRateAuthority());
			CurrencyRateServiceLocal currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CurrencyRate"); //$NON-NLS-1$
			try {
				//�������� �������� ������ ���� ��� �����
				entity.setCurCource(currencyRateService.getCurrencyRate(cfg.getLocalCurrency(), entity.getCurrency(), entity.getCurrencyRateAuthority(), entity.getCurrencyRateType(), entity.getDocDate()));
			} catch (CurrencyRateNotFoundException e) {
				//���� ������� ����� ���, �� �������� �������� �������� ����
				entity.setCurCource(MathUtils.divide(BigDecimal.ONE, currencyRateService.getCurrencyRate(entity.getCurrency(), cfg.getLocalCurrency(), entity.getCurrencyRateAuthority(), entity.getCurrencyRateType(), entity.getDocDate()), new RoundContext(CurrencyRateServiceLocal.DEFAULT_RATE_SCALE)));
			}
		} else {
			//���� ��� ������������, �� ������ ��������� ��� ����������
			entity.setCurCource(BigDecimal.ONE);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final T entity) {
		context.addRule(new MandatoryStringAttribute(entity, "UNID")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DocType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "SysCompany")); //$NON-NLS-1$
		if (entity.isManualDocNumber())
			context.addRule(new MandatoryStringAttribute(entity, "DocNumber")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DocDate")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Currency")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateAuthority")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurCource")); //$NON-NLS-1$

	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(T entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(T entity) {
		DocFlowHelper.createDocument(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(T entity) {
		DocFlowHelper.checkStatus(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(T entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onClone(T entity) {
		entity.setUNID(DataUtils.generateUUID());
		entity.setRequester(com.mg.merp.docflow.support.DocumentUtils.getCurrentDocumentOwner());
		entity.setBaseDocument(null);
		entity.setManualDocNumber(false); //����� ����� �������������� ����� �����
	}

	/**
	 * �������� ������������� ������� ���������, ������ ���� ������������� � ������ ��������� ���������
	 * 
	 * @return	�������������
	 */
	protected abstract int getDocSectionIdentifier();

	/**
	 * ���������� ������� ��������� ���������
	 * 
	 * @param entity
	 */
	protected void doAdjust(T entity) {
		//generate document number
		if (!entity.isManualDocNumber()) {
			DocumentNumberStrategy dns = DocumentNumberStrategyFactory.createStrategy(entity);
			if (dns != null) {
				entity.setDocNumber(dns.generateNumber(entity));
				entity.setManualDocNumber(true); //���������� ����� ���� ���, ����� ��������� � ������ �����
			}
			else
				getLogger().debug("Document number strategy is null"); //$NON-NLS-1$
		}
		//https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4877
		/*entity.setDocNumber(StringUtils.padLeft(entity.getDocNumber(), DocHead.DOCNUMBER_LENGTH, ' ', true));
		if (!StringUtils.stringNullOrEmpty(entity.getBaseDocNumber()))
			entity.setBaseDocNumber(StringUtils.padLeft(entity.getBaseDocNumber(), DocHead.DOCNUMBER_LENGTH, ' ', true));
		if (!StringUtils.stringNullOrEmpty(entity.getContractNumber()))
			entity.setContractNumber(StringUtils.padLeft(entity.getContractNumber(), DocHead.DOCNUMBER_LENGTH, ' ', true));*/
		//calculate sum in local currency
		entity.setSumNat(entity.getSumCur() == null ? null :
			MathUtils.multiply(entity.getSumCur(), entity.getCurCource(), new RoundContext(getConfiguration().getCurrencyScale())));
	}

	/**
	 * �������� ������������ ���������
	 * 
	 * @return
	 */
	protected Configuration doGetConfiguration() {
		return new ConfigurationImpl(null, null, 4, null, null);
	}

	/**
	 * ������� �������� �� �������
	 * 
	 * @param pattern - �������
	 * @param folder - �����-��������
	 * @return ��������
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected T doCreateByPattern(DocHeadModel pattern, Folder folder, CreateDocumentByPatternStrategy createStrategy) {
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		
		pattern = (DocHeadModel) getPatternService().load(pattern.getId());
		T docHead = initialize();
		
		//���� ��������� �� ������ ���������� �����������
		if (createStrategy == null) {
			getLogger().debug("use default strategy for create document by pattern");
			createStrategy = new DefaultCreateDocumentByPatternStrategy(folder, true, this, getPatternService());
		}
		
		return (T) createStrategy.createDocument(docHead, pattern);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#getDocSection()
	 */
	@PermitAll
	public DocSection getDocSection() {
		return getPersistentManager().find(DocSection.class, getDocSectionIdentifier());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#adjust(com.mg.merp.document.model.DocHead)
	 */
	@PermitAll
	public void adjust(T entity) {
		if (!entity.isAdjusted())
			doAdjust(entity);			
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#getConfiguration()
	 */
	@PermitAll
	public Configuration getConfiguration() {
		return doGetConfiguration();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#getTemplateService()
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@PermitAll
	public M getPatternService() {
		return (M) DocumentUtils.getDocumentPatternService(getDocSection());
	}

	public String createDocumentNumber(String docType, java.sql.Date docDate) throws ApplicationException {
		return null;//((Document) getDomain()).createDocumentNumber(docType, docDate);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.Document#createByPattern(com.mg.merp.document.model.DocHeadModel, com.mg.merp.core.model.Folder)
	 */
	@PermitAll
	public T createByPattern(DocHeadModel pattern, Folder folder) {
		return doCreateByPattern(pattern, folder, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#store(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public T store(T entity) {
		T result = super.store(entity);
		//��������� �� onStore, �������� ����� ���� ���������, �������� ��� ����� ���������� � ���������
		//� ��������� ����������� ���������
		DocFlowHelper.modifyDocument(result);		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#createByPatternUseStrategy(com.mg.merp.document.model.DocHeadModel, com.mg.merp.document.CreateDocumentByPatternStrategy)
	 */
	public DocHead createByPatternUseStrategy(DocHeadModel pattern,
			CreateDocumentByPatternStrategy createStrategy) {
		return doCreateByPattern(pattern, null, createStrategy);
	}

	public void lock(int id) throws ApplicationException {
		//((Document) getDomain()).lock(id);
	}

	public void unlock(int id) throws ApplicationException {
		//((Document) getDomain()).unlock(id);
	}

	public String getDefaultRptTemplate(int id) throws ApplicationException {
		return null;//((Document) getDomain()).getDefaultRptTemplate(id);
	}

	public byte[] getOriginal(int id) throws ApplicationException {
		return null;//((Document) getDomain()).getOriginal(id);
	}

	public void setOriginal(int id, byte[] original) throws ApplicationException {
		//((Document) getDomain()).setOriginal(id, original);
	}

	public boolean checkDirectAfterCreateStage(int id) throws ApplicationException {
		return true;//((Document) getDomain()).checkDirectAfterCreateStage(id);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentBody(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public byte[] loadAttachmentBody(Integer originalId) {
		return extractOriginalBody(load((ID) originalId));
	}

	private byte[] extractOriginalBody(T entity) {
		if (entity == null)
			throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

		return DataUtils.extractOriginalBody(entity.getOriginal());
	}


	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentName(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@PermitAll
	public String loadAttachmentName(Integer originalId) {
		return extractOriginalName(load((ID) originalId));
	}

	private String extractOriginalName(T entity) {
		if (entity == null)
			throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

		return DataUtils.extractOriginalName(entity.getOriginal());
	}


	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#removeAttachment(java.io.Serializable[])
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void removeAttachment(Serializable[] originalIds) {
		for (int i = 0; i < originalIds.length; i++) {
			T entity = load((ID) originalIds[i]);
			entity.setOriginal(null);
			getPersistentManager().merge(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#storeAttachment(byte[], java.lang.String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void storeAttachment(byte[] body, String name, Integer originalId) {
		T entity = load((ID) originalId);
		entity.setOriginal(DataUtils.originalToBinary(body, name));
		getPersistentManager().merge(entity);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.Document#createDocumentBasisOf(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel)
	 */
	@PermitAll
	public <T1 extends DocHead, T2 extends DocHeadModel> T createDocumentBasisOf(T1 srcDoc, T dstDoc, T2 model){
		return doCreateDocumentBasisOf(srcDoc, dstDoc, model);
	}
	
	/**
	 * ����� ���������� �������������� �������������� ��������� dstDoc
	 * 
	 * @param <T1>
	 * 			��� ���������-���������
	 * @param <T2>
	 * 			��� ������ ���������
	 * @param srcDoc
	 * 			��������-��������
	 * @param dstDoc
	 * 			�������������� ��������
	 * @param model
	 * 			������ ���������
	 * 
	 * @return �������������� ��������
	 */
	protected <T1 extends DocHead, T2 extends DocHeadModel> T doCreateDocumentBasisOf(T1 srcDoc, T dstDoc, T2 model){
		return dstDoc;
	}

}