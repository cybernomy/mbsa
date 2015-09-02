/**
 * CreateDocumentBasisOfCallback.java
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

import java.io.Serializable;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSpec;

/**
 * ������ ��������� ������ ��� ��������� ��������� �������� ���������, � ��������
 * ���������� ������������� ������������ ��������� ������ �������������� ��
 * {@link com.mg.merp.document.generic.AbstractCreateDocumentBasisOfCallback}
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateDocumentBasisOfCallback.java,v 1.1 2007/09/28 12:03:43 safonov Exp $
 */
public interface CreateDocumentBasisOfCallback extends Serializable {

	/**
	 * ���������� ��� ��������� ��������� ���������, �������� ��������� ���������
	 * ������� <code>dstDoc</code>
	 * 
	 * @param dstDoc	����������� ��������
	 * @param srcDoc	��������� ��������
	 * @param pattern	������� ������������ ���������
	 */
	void processDocumentHead(DocHead dstDoc, DocHead srcDoc, DocHeadModel pattern);

	/**
	 * ���������� ��� ��������� ��������� ���������, �������� ��������� ���������
	 * ������� <code>dstDocSpec</code>
	 * 
	 * @param dstDoc	����������� ��������
	 * @param srcDoc	��������� ��������
	 * @param dstDocSpec	����������� ������������ ���������
	 * @param srcDocSpec	������������ ��������� ���������
	 */
	void processDocumentSpec(DocHead dstDoc, DocHead srcDoc, DocSpec dstDocSpec, DocSpec srcDocSpec);
	
}
