/*
 * CatalogNumberingAlgorithm.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * ������� ����� BAi ������������ ���� ������� �������� ������� � �����. ����� ��������� ������
 * ������������� ��������� ����� <code>protected String doPerform() throws Exception</code>.
 * ����� ���������� �������������� ��� ������� ���.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected String doPerform() throws Exception {
 *     complete("My code");
 * }</pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: CatalogNumberingAlgorithm.java,v 1.5 2006/09/21 14:42:28 safonov Exp $
 *
 */
public abstract class CatalogNumberingAlgorithm extends AbstractBusinessAddin<String> {
    
    /**
     * ���������� ���, �������������� ���������� ��-���������. ������ ��������� ���������
     * ��� �� ������������������ ����� �����, ������������ ���� �� ����� � ���������������
     * RootId �� ������� �����, ����������� �������� Separator. ������������������ �����������
     * ������� �������� � ������ �����.
     * 
     * @param separator     �����������
     * @param rootId        ����� ���� 0, ����� ���� ������� �� �������� �����
     * @param includeRoot   ���������, �������� �� � ���� ����� RootId
     * @param numWidth      ���������� ������ ��������������� �������� ���� (������ ��������). ���� ����� �������� � ������ ������ NumWidth, �� ����� ����������� ����� ������
     * @return
     */
	final public String createDefaultCatalogCode(String separator, int rootId, boolean includeRoot, int numWidth) throws ApplicationException {
		return internalCreateDefaultCatalogCode(separator, rootId, includeRoot, numWidth);
	}

    /**
     * ���������� ���, �������������� ���������� ��-���������. ������ ��������� ���������
     * ��� �� ������������������ ����� �����, ������������ ���� �� ����� � ���������������
     * RootId �� ������� �����, ����������� �������� Separator. ������������������ �����������
     * ������� �������� � ������ �����.
     * 
     * @param separator     �����������
     * @param rootId        ����� ���� 0, ����� ���� ������� �� �������� �����
     * @param includeRoot   ���������, �������� �� � ���� ����� RootId
     * @return
     */
	final public String createDefaultCatalogCode(String separator, int rootId, boolean includeRoot) throws ApplicationException {
		return internalCreateDefaultCatalogCode(separator, rootId, includeRoot, 0);
	}

    /**
     * ���������� ��������������� ������� �������� �������� (��. ���������� ���������
     * ������-�����������, ���������� �� ���� �������������� ���������).
     * 
     * @param name  ������������ ��������, ����� ����� ��������� ��������:
     * <dl>
     *   <dt>Id
     *   <dd>�������������
     *   <dt>FolderId
     *   <dd>������������� �����
     *   <dt>Code
     *   <dd>���
     *   <dt>FullName
     *   <dd>������������
     *   <dt>BarCode
     *   <dd>�����-���
     *   <dt>PLUCode
     *   <dd>��� PLU
     *   <dt>OKDPCode
     *   <dd>��� ����
     *   <dt>Measure1_Id
     *   <dd>������������� �������� ������� ���������
     *   <dt>Measure2_Id
     *   <dd>������������� �������������� ������� ���������
     *   <dt>Weight
     *   <dd>���
     *   <dt>Volume
     *   <dd>�����
     *   <dt>Articul
     *   <dd>�������
     * </dl>
     * @return      �������� ��������
     * @throws ApplicationException
     */
	final public Object getAttribute(String name) throws ApplicationException {
		return internalGetAttribute(name);
	}

	protected Object internalGetAttribute(String name) throws ApplicationException {
		//TODO
		//return DataUtils.variantToObject(nativeGetAttribute(handle, name));
		return null;
	}

	protected String internalCreateDefaultCatalogCode(String separator, int rootId, boolean includeRoot, int numWidth) throws ApplicationException {
		//TODO
		//return nativeCreateDefaultCatalogCode(handle, separator, rootId, includeRoot, numWidth);
		return null;
	}

}
