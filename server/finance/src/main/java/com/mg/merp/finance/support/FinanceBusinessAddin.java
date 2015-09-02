/**
 * FinanceBusinessAddin.java
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
package com.mg.merp.finance.support;

import java.math.BigDecimal;
import java.util.Map;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.Specification;

/**
 * ������� ����� BAi ������������ ���������� ��������. ����� ������
 * ������������� ��������� ����� <code>protected void doPerform() throws Exception</code>.
 * ����� ���������� ����� �������� � ������ ��������������� ���������.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected void doPerform() throws Exception {
 *     complete(getDocumentSpecItem().getPerformedSum()); //����� ������������ ���������� � ���������
 * }</pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: FinanceBusinessAddin.java,v 1.1 2007/12/03 15:04:59 safonov Exp $
 */
public abstract class FinanceBusinessAddin extends AbstractBusinessAddin<BigDecimal> {
	/**
	 * ��� ��������� ���� ��
	 */
	public static final String DOCFLOW_PARAM_NAME = "DOCFLOW_PARAM_NAME";
	/**
	 * ��� ��������� ������������ ��������� ���������� � ���������
	 */
	public static final String DOCSPEC_PARAM_NAME = "DOCSPEC_PARAM_NAME";
	/**
	 * ��� ��������� ���������� ��������
	 */
	public static final String FINOPER_PARAM_NAME = "FINOPER_PARAM_NAME";
	/**
	 * ��� ��������� ���������� ��������/�������
	 */
	public static final String FINSPEC_PARAM_NAME = "FINSPEC_PARAM_NAME";
	
	private DocFlowPluginInvokeParams mDocFlowParams;
	private DocumentSpecItem mDocumentSpecItem;
	private FinOperation mFinanceOperation;
	private Specification mFinanceSpecification;

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		mDocFlowParams = (DocFlowPluginInvokeParams) params.get(DOCFLOW_PARAM_NAME);
		mDocumentSpecItem = (DocumentSpecItem) params.get(DOCSPEC_PARAM_NAME);
		mFinanceOperation = (FinOperation) params.get(FINOPER_PARAM_NAME);
		mFinanceSpecification = (Specification) params.get(FINSPEC_PARAM_NAME);
	}

	/**
	 * �������� �������������� ��������
	 * 
	 * @return	������� ��������
	 */
	protected DocHead getDocument() {
		return mDocFlowParams.getDocument();
	}
	
	/**
	 * �������� �������������� ����� ���������, ������������ ��� ���������� ��� ������������
	 * 
	 * @return	�������������� ����� ���������
	 */
	protected BigDecimal getPerformedSum() {
		return mDocFlowParams.getPerformedSum();
	}
	
	/**
	 * �������� ������� ���� ��
	 * 
	 * @return	������� ���� ��
	 */
	protected DocProcessStage getDocProcessStage() {
		return mDocFlowParams.getPerformedStage();
	}
	
	/**
	 * �������� ������� ������������ ���������
	 * 
	 * @return	������� ������������ ��������� ��� <code>null</code> ���� �������� ��� ������������
	 */
	protected DocumentSpecItem getDocumentSpecItem() {
		return mDocumentSpecItem;
	}

	/**
	 * �������� ���������� ��������
	 * 
	 * @return	����������� ���������� ��������
	 */
	protected FinOperation getFinanceOperation() {
		return mFinanceOperation;
	}

	/**
	 * �������� ���������� ������������/�������
	 * 
	 * @return	����������� ���������� ������������/�������
	 */
	protected Specification getFinanceSpecification() {
		return mFinanceSpecification;
	}

}
