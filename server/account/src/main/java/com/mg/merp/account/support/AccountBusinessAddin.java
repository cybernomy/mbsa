/*
 * AccountBusinessAddin.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support;

import java.math.BigDecimal;
import java.util.Map;

import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

/**
 * ������� ����� BAi ������������ �������� ���. ��������. ����� ������
 * ������������� ��������� ����� <code>protected void doPerform() throws Exception</code>.
 * ����� ���������� ��������� (��� ����� ���� ����� � ������ ��������������� ��������� ��� ����������).
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected void doPerform() throws Exception {
 *     complete(getDocumentSpecItem().getPerformedSum()); //����� ������������ ���������� � ���������
 * }</pre>
 * @author Konstantin S. Alikaev
 * @version $Id: AccountBusinessAddin.java,v 1.1 2008/03/13 06:20:53 alikaev Exp $
 */
public abstract class AccountBusinessAddin extends AbstractBusinessAddin<BigDecimal> {

	/**
	 * ��� ��������� ���� ��
	 */
	public static final String DOCFLOW_PARAM_NAME = "DOCFLOW_PARAM_NAME";
	/**
	 * ��� ��������� ������������ ��������� ���������� � ���������
	 */
	public static final String DOCSPEC_PARAM_NAME = "DOCSPEC_PARAM_NAME";
	/**
	 * ��� ��������� ������������� ��������	 
	 */
	public static final String ECONOMIC_OPER_PARAM_NAME = "ECONOMIC_OPER_PARAM_NAME";
	/**
	 * ��� ��������� ������������� ��������/�������
	 */
	public static final String ECONOMIC_SPEC_PARAM_NAME = "ECONOMIC_SPEC_PARAM_NAME";
	
	private DocFlowPluginInvokeParams mDocFlowParams;
	private DocumentSpecItem mDocumentSpecItem;
	private EconomicOper mEconomicOper;
	private EconomicSpec mEconomicSpec;

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		mDocFlowParams = (DocFlowPluginInvokeParams) params.get(DOCFLOW_PARAM_NAME);
		mDocumentSpecItem = (DocumentSpecItem) params.get(DOCSPEC_PARAM_NAME);
		mEconomicOper = (EconomicOper) params.get(ECONOMIC_OPER_PARAM_NAME);
		mEconomicSpec = (EconomicSpec) params.get(ECONOMIC_SPEC_PARAM_NAME);
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
	 * �������� ������������� ��������
	 * 
	 * @return	����������� ������������� ��������
	 */
	public EconomicOper getEconomicOper() {
		return mEconomicOper;
	}

	/**
	 * �������� ������������� ������������
	 * 
	 * @return	����������� ������������� ������������
	 */
	public EconomicSpec getEconomicSpec() {
		return mEconomicSpec;
	}

}
