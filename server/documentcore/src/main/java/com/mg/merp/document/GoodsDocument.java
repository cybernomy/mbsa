/*
 * GoodsDocument.java
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

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;


/**
 * ������-��������� "�������� �� ��������������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: GoodsDocument.java,v 1.5 2007/09/26 09:38:34 safonov Exp $
 */
public interface GoodsDocument<T extends DocHead, ID extends Serializable, M extends DocumentPattern, S extends GoodsDocumentSpecification> extends Document<T, ID, M> {
	
	/**
	 * �������� ������-��������� ������������ ���������
	 * 
	 * @return	������-��������� ������������ ���������
	 */
	S getSpecificationService();
	
	/**
	 * ��������� ������������ ���������, ���������� ������� ��� ��������� ��������� ���������
	 * ����� ��������� ������������, � ������ ������������� ������� �������� ������� ����� �� ���������
	 * 
	 * @param docSpec	������������
	 */
	void modifySpecifaction(DocSpec ... docSpecs);
	
	/**
	 * �������� ������������ ���������, ���������� ������� ��� ��������� ��������� ���������
	 * ����� ���������� ������������, � ������ ������������� ������� �������� ������� ����� �� ���������.
	 * ��� ������������ ������ ������������ ������ ���������
	 * 
	 * @param docSpecs	������������
	 */
	void createSpecifaction(DocSpec ... docSpecs);
	
	/**
	 * �������� ������������ ���������, ���������� ������� ��� ��������� ��������� ���������
	 * ����� �������� ������������, � ������ ������������� ������� �������� ������� ����� �� ���������
	 * 
	 * @param docSpec	������������
	 */
	void removeSpecifaction(DocSpec ... docSpecs);
	
}
