/*
 * DocTypeServiceLocal.java
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
package com.mg.merp.document;

import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocTypeDocSectionLink;

/**
 * ������-��������� "��� ���������"
 * 
 * @author leonova
 * @version $Id: DocTypeServiceLocal.java,v 1.2 2006/08/31 09:09:30 safonov Exp $
 */
public interface DocTypeServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<DocType, Integer>
{

	/**
	 * �������� ������ ���� ��������� � ���������� ������ ����������
	 * 
	 * @see com.mg.merp.document.model.DocumentKind
	 * 
	 * @param docType	��� ���������
	 * @return	��������� ������ ������ � ������ ����������, ������ ��������� - ���� ����������
	 */
	DocTypeDocSectionLink[][] loadDocSectionLinks(DocType docType);

	/**
	 * �������� ������ ���� ��������� � ������ ����������
	 * 
	 * @param links	����� ���� ���������
	 */
	void removeDocSectionLinks(DocTypeDocSectionLink[] links);
	
	/**
	 * �������� ������ ���� ��������� � ������ ����������
	 * 
	 * @param links	links	����� ���� ���������
	 */
	void createDocSectionLinks(DocTypeDocSectionLink[] links);

}
