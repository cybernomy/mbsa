/*
 * Document.java
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
package com.mg.merp.document;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.reference.AttachmentHandler;

/**
 * ������ ������-���������� "��������"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: Document.java,v 1.8 2007/09/28 12:03:43 safonov Exp $
 */
public interface Document<T extends DocHead, ID extends Serializable, M extends DocumentPattern> extends DataBusinessObjectService<T, ID>, AttachmentHandler {

	/**
	 * �������� ������ ���������
	 * 
	 * @return	������ ���������
	 */
	DocSection getDocSection();

	/**
	 * �������� ������������ ������ � �������� ����������� ��������
	 * 
	 * @return	������������
	 */
	Configuration getConfiguration();

	/**
	 * �������� ������-��������� ������� ���������
	 * 
	 * @return
	 */
	M getPatternService();

	/**
	 * ������� �������� �� ������� � �������������� ����������� ��������� ��������
	 * 
	 * @param patern	�������
	 * @param folder	�����-���������� ���������
	 * @return	��������
	 */
	DocHead createByPattern(DocHeadModel patern, Folder folder);

	/**
	 * ������� �������� �� �������
	 * 
	 * @param patern	�������
	 * @param createStrategy	��������� ��������
	 * @return	��������
	 */
	DocHead createByPatternUseStrategy(DocHeadModel pattern, CreateDocumentByPatternStrategy createStrategy);
	
	/**
	 * ������ ��������� ���������, ��� ������������� ������� ������� �������� � ���������
	 * ����� ������� ������ �� ���������, ���� ������� {@link com.mg.merp.document.model.DocHead#isAdjusted() Adjusted}
	 * ���������� � <code>true</code>, �� ������ ������������ �� �����, �.�. �������� ��������
	 * ����������� ��������� �������
	 * 
	 * @param entity ��������
	 */
	void adjust(T entity);

}
