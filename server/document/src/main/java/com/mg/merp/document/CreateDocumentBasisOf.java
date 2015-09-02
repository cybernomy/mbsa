/* CreateDocumentBasisOf.java
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

import java.util.Date;
import java.util.List;

import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

/**
 * ������ �������� ��������� �� ��������� �������
 * <p>
 * <br>S - ����� ������� ���������
 * <br>D - �������������� �����
 * <br>P - �������
 * 
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: CreateDocumentBasisOf.java,v 1.6 2009/02/27 09:00:56 safonov Exp $ 
 */
public interface CreateDocumentBasisOf<S extends DocHead, D extends DocHead, P extends DocHeadModel> {

	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:document=CreateDocumentBasisOfService"; //$NON-NLS-1$
	
	/**
	 * �������� ��������� �� ���������
	 * 
	 * @param srcDoc
	 * 			�������� ��������
	 * @param dstClass
	 * 			����� ��������� ���������
	 * @param model
	 * 			�������
	 * @param date
	 * 			���� �������� ���������, ���� <code>null</code>, �� ����� ������������ ���� �� ��������� ���������
	 * @param specList
	 * 			������ ������������ � ���������
	 * @param destFolder
	 * 			����� ����������
	 * 
	 * @return ����� ��������
	 */
	D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder);
	
	/**
	 * �������� ��������� �� ���������
	 * 
	 * @param srcDoc
	 * 			�������� ��������
	 * @param dstDoc
	 * 			��������-�������
	 * @param model
	 * 			�������
	 * @param date
	 * 			���� �������� ���������, ���� <code>null</code>, �� ����� ������������ ���� �� ��������� ���������
	 * @param specList
	 * 			������ ������������ � ���������
	 * @param destFolder
	 * 			����� ����������
	 */
	void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder);

	/**
	 * �������� ��������� �� ���������
	 * 
	 * @param srcDoc	�������� ��������
	 * @param dstClass	����� ��������� ���������
	 * @param model		�������
	 * @param date		���� �������� ���������, ���� <code>null</code>, �� ����� ������������ ���� �� ��������� ���������
	 * @param specList	������ ������������ � ���������
	 * @param destFolder	����� ����������
	 * @param createCallback	������ ��������� ������ ��� ��������� ��������� �������� ���������, ����� ���� <code>null</code>
	 * @return	����� ��������
	 */
	D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
			CreateDocumentBasisOfCallback createCallback);

	/**
	 * �������� ��������� �� ���������
	 * 
	 * @param srcDoc	�������� ��������
	 * @param dstDoc	��������-�������
	 * @param model		�������
	 * @param date		���� �������� ���������, ���� <code>null</code>, �� ����� ������������ ���� �� ��������� ���������
	 * @param specList	������ ������������ � ���������
	 * @param destFolder	����� ����������
	 * @param createCallback	������ ��������� ������ ��� ��������� ��������� �������� ���������, ����� ���� <code>null</code>
	 */
	void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
			CreateDocumentBasisOfCallback createCallback);

}
