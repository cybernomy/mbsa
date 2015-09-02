/*
 * GoodsDocumentSpecification.java
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
package com.mg.merp.document;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;


/**
 * ������-��������� "������������ ���������"
 * 
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocumentSpecification.java,v 1.6 2009/02/11 14:09:26 safonov Exp $
 */
public interface GoodsDocumentSpecification<T extends com.mg.merp.document.model.DocSpec, ID extends Serializable>
		extends DataBusinessObjectService<T, ID> {

	/**
	 * �������� ������ ���������
	 * 
	 * @return	������ ���������
	 */
	DocSection getDocSection();
	
	/**
	 * �������� ���������� ������������
	 * 
	 * @param docHead	��������� ���������
	 * @param goodsInfoList	������ ������������ ��� �������� ������������
	 */
	void bulkCreate(DocHead docHead, CreateSpecificationInfo[] goodsInfoList);
	
	/**
	 * ������ ��������� ������� ������������, ��� ������������� ������� ������� �������� � ���������
	 * ����� ������� ������ �� ���������, ���� ������� {@link com.mg.merp.document.model.DocSpec#isAdjusted() Adjusted}
	 * ���������� � <code>true</code>, �� ������ ������������ �� �����, �.�. �������� ��������
	 * ����������� ��������� �������
	 * 
	 * @param entity
	 */
	void adjust(T entity);

	/**
	 * ������ ����� �������� � ������� ������������ ���������
	 * 
	 * @param entity	��������
	 */
	void updateSpecBestBefore(DocHead docHead);
	

}
