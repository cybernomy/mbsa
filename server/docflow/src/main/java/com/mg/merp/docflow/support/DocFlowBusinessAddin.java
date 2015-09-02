/*
 * DocFlowBusinessAddin.java
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

import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.generic.BaseDocFlowBusinessAddin;
import com.mg.merp.docprocess.model.DocHeadState;

/**
 * ������� ����� BAi ��������� (������) ��������� � ����������������. ����� BAi ������
 * ������������� ��������� ����� <code>protected String doPerform() throws Exception</code>.
 * � ������ ��������� ���������� ���������� ������� {@link #complete(Void)}, ���� ����������
 * �������� ���������� ���������������� ���������� ������� {@link #abort()}. ����� BAi
 * ��������������� ��� ��������� ����� ������������� ������ {@link #doGetDocActionResultTextRepresentation(DocHeadState)} �
 * {@link #doShowDocActionResult(DocHeadState)} ��������������� ��� ������ � ����������������
 * ���������� ����������� ���������� ����� ��.
 * 
 * <p>������ ������ {@link #doPerform()}:
 * <pre>
 *  protected void doPerform() throws Exception {
 *  	...
 *  	if (continue)
 *  		complete(null);
 *  	else
 *  		abort();
 *  }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowBusinessAddin.java,v 1.4 2007/12/14 08:48:53 safonov Exp $
 */
public abstract class DocFlowBusinessAddin extends BaseDocFlowBusinessAddin implements com.mg.merp.docflow.BAiDocFlowPluginViewer {

	/**
	 * ���������� ��������� ���������� ������������� ���������� ���������� ����� ��,
	 * ���������� ���������� ����� �� ������ �������������� ������ ����� ���� ���� �����������
	 * �������� ��������� � ��������� ����, ������ ������ ����� �������� � ����� ������� ��
	 * 
	 * @param docHeadState	��������� ��
	 * @return	��������� �������������
	 */
	protected String doGetDocActionResultTextRepresentation(DocHeadState docHeadState) {
		return StringUtils.EMPTY_STRING;
	}

	/**
	 * ���������� ������ ���������� ���������� ����� �� (�������� ���� ���� ������ ��������, �� �����
	 * �������� ������ �������� � UI), ������ ����� ����� ������ ��� ������ ������ ���� "�������� ��������� �����"
	 * � ������� ��
	 * 
	 * @param docHeadState	��������� ��
	 */
	protected void doShowDocActionResult(DocHeadState docHeadState) {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.BAiDocFlowPluginViewer#getDocActionResultTextRepresentation(com.mg.merp.docprocess.model.DocHeadState)
	 */
	public final String getDocActionResultTextRepresentation(DocHeadState docHeadState) {
		return doGetDocActionResultTextRepresentation(docHeadState);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.BAiDocFlowPluginViewer#showDocActionResult(com.mg.merp.docprocess.model.DocHeadState)
	 */
	public final void showDocActionResult(DocHeadState docHeadState) {
		doShowDocActionResult(docHeadState);
	}
	
}
