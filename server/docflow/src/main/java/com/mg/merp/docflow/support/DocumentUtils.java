/*
 * DocumentUtils.java
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
package com.mg.merp.docflow.support;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.document.Document;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocSpec;

/**
 * ������� ������ � �����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentUtils.java,v 1.6 2007/12/17 14:39:44 safonov Exp $
 */
public class DocumentUtils {

	private static final String DOCUMENT_OWNER = "MG_DOCUMENT_OWNER";

	private static BusinessObjectService getService(DocSection docSection, String propertyName) {
		PersistentManager pm = ServerUtils.getPersistentManager();
		if (!pm.contains(docSection))
			docSection = pm.find(DocSection.class, docSection.getId());
		return ApplicationDictionaryLocator.locate().getBusinessService(((SysClass) docSection.getAttribute(propertyName)).getBeanName());
	}

	/**
	 * �������� ������-���������� ���������
	 * 
	 * @param docSection	������ ���������
	 * @return	������-���������
	 */
	@SuppressWarnings("unchecked")
	public static Document getDocumentService(DocSection docSection) {
		return (Document) getService(docSection, "SysClass");
	}

	/**
	 * �������� ������-���������� ������������
	 * 
	 * @param docSection	������ ���������
	 * @return	������-���������
	 */
	@SuppressWarnings("unchecked")
	public static <T extends DocSpec, ID extends Serializable> GoodsDocumentSpecification<T, ID> getGoodsDocumentSpecificationService(DocSection docSection) {
		return (GoodsDocumentSpecification) getService(docSection, "SpecSysClass"); //$NON-NLS-1$
	}

	/**
	 * �������� ������-���������� ��������� �� ��������������
	 * 
	 * @param docSection	������ ���������
	 * @return	������-���������
	 */
	@SuppressWarnings("unchecked")
	public static GoodsDocument getGoodsDocumentService(DocSection docSection) {
		return (GoodsDocument) getService(docSection, "SysClass"); //$NON-NLS-1$
	}

	/**
	 * �������� ������-���������� ������� ���������
	 * 
	 * @param docSection	������ ���������
	 * @return	������-���������
	 */
	@SuppressWarnings("unchecked")
	public static DocumentPattern getDocumentPatternService(DocSection docSection) {
		return (DocumentPattern) getService(docSection, "ModelSysClass"); //$NON-NLS-1$
	}

	/**
	 * �������� ������� ���������
	 * 
	 * @param sysClass	��� ������� ������-����������
	 * @param modelId	������������� �������
	 * @return	�������
	 */
	@SuppressWarnings("unchecked")
	public static PersistentObject loadDocumentModel(SysClass sysClass, Integer modelId) {
		sysClass = ServerUtils.getPersistentManager().find(SysClass.class, sysClass.getPrimaryKey());
		DataBusinessObjectService service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(sysClass.getBeanName());
		return service.load(modelId);
	}
	
	/**
	 * ��������� ���������� ��������� ���������
	 * 
	 * @param docHead	������ ���������
	 * @return	��������� ���������
	 */
	public static String generateDocumentTitle(DocHead docHead) {
		return generateDocumentTitle(docHead.getDocType() != null ? docHead.getDocType().getCode() : null,
				docHead.getDocNumber(), docHead.getDocDate());
	}

	/**
	 * ��������� ���������� ��������� ���������
	 * 
	 * @param docTypeCode	��� ���� ���������
	 * @param docNumber		����� ���������
	 * @param docDate		���� ���������
	 * @return	��������� ���������
	 */
	public static String generateDocumentTitle(String docTypeCode, String docNumber, Date docDate) {
		docTypeCode = docTypeCode != null ? docTypeCode.trim() : "<unknown>";
		docNumber = docNumber != null ? docNumber.trim() : "<unknown>";
		String docDateStr = docDate != null ? SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, ServerUtils.getUserLocale()).format(docDate) : "<unknown>"; 
		return String.format(ServerUtils.getUserLocale(), Messages.getInstance().getMessage(Messages.DOCUMENT_TITLE), docTypeCode, docNumber, docDateStr);
	}

	/**
	 * �������� �������� ��������� �����������
	 * 
	 * @return	������� ��������, ���� �� �������������� �� 0 (�������� ������� ������������)
	 */
	public static Short getCurrentDocumentOwner() {
		Short result = (Short) ServerUtils.getCurrentSession().getAttribute(DOCUMENT_OWNER); 
		return result == null ? (short) 0 : result;
	}
	
	/**
	 * ��������� �������� ��������� �����������
	 * 
	 * @param owner	������� ��������
	 * @return	������ �������� ���� �����������
	 */
	public static Short setCurrentDocumentOwner(Short owner) {
		Short oldValue = getCurrentDocumentOwner();
		ServerUtils.getCurrentSession().setAttribute(DOCUMENT_OWNER, owner);
		return oldValue;
	}
	
}
