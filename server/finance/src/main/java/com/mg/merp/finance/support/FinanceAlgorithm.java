/*
 * FinanceAlgorithm.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * ������� ����� BAi ������������ ���������� ��������.  ����� ��������� ������
 * ������������� ��������� ����� <code>protected Double doPerform() throws Exception</code>.
 * ����� ���������� ����� ��������.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected Double doPerform() throws Exception {
 *     complete(25.0);
 * }</pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: FinanceAlgorithm.java,v 1.4 2007/12/03 15:04:59 safonov Exp $
 * @deprecated use {@link com.mg.merp.finance.support.FinanceBusinessAddin} instead
 */
@Deprecated
public abstract class FinanceAlgorithm extends AbstractBusinessAddin<Double> {

	/**
	 * ���������� ������� ��������� ����������� ���������� ��������
	 * 
	 * @param name	������������ ��������, ����� ����� ��������� ��������:
	 * <dl>
	 *   <dt>KEEPDATE
	 *   <dd>���� ��������, ����
	 *	 <dt>COMMENT
	 *   <dd>���������� ��������, ������
	 *	 <dt>DOCTYPE
	 *   <dd>��� ���������, ������
	 *	 <dt>DOCNUMBER
	 *   <dd>����� ���������, ������
	 *	 <dt>DOCDATE
	 *   <dd>���� ���������, ����
	 *	 <dt>BASETYPE
	 *   <dd>��� ���������-���������, ������
	 *	 <dt>BASENUMBER
	 *   <dd>����� ���������-���������, ������
	 *	 <dt>BASEDATE
	 *   <dd>��� ���������-���������, ������
	 *	 <dt>CONTRACTTYPE
	 *   <dd>��� ��������, ������
	 *	 <dt>CONTRACTNUMBER
	 *   <dd>����� ��������, ������
	 *	 <dt>CONTRACTDATE
	 *   <dd>���� ��������, ����
	 *	 <dt>RESPONSIBLE
	 *   <dd>������������� ����������� "�������������", ����� 
	 *	 <dt>CURRATE
	 *   <dd>���� ������, �����
	 *	 <dt>CURCODE
	 *   <dd>��� ������, ������
	 * </dl>
	 * @return		�������� ��������
	 * @throws ApplicationException
	 */
	final public Object getFinOperParam(String name) throws ApplicationException {
		//return DataUtils.variantToObject(nativeGetFinOperParam(handle, name));
		//TODO
		return null;
	}

	/**
	 * ���������� ������� ����������� ��������
	 * 
	 * @param name	������������ ��������, ����� ����� ��������� ��������:
	 * <dl>
	 *   <dt> ACCSRC
	 *   <dd> ������������� �����-���������, �����
	 *   <dt> ANLSRC1
	 *   <dd> ������������� ��������� 1 �����-���������, �����
	 *   <dt> ANLSRC2
	 *   <dd> ������������� ��������� 2 �����-���������, �����
	 *   <dt> ANLSRC3
	 *   <dd> ������������� ��������� 3 �����-���������, �����
	 *   <dt> ANLSRC4
	 *   <dd> ������������� ��������� 4 �����-���������, �����
	 *   <dt> ANLSRC5
	 *   <dd> ������������� ��������� 5 �����-���������, �����
	 *   <dt> ACCDST
	 *   <dd> ������������� �����-���������, �����
	 *   <dt> ANLDST1
	 *   <dd> ������������� ��������� 1 �����-���������, �����
	 *   <dt> ANLDST2
	 *   <dd> ������������� ��������� 2 �����-���������, �����
	 *   <dt> ANLDST3
	 *   <dd> ������������� ��������� 3 �����-���������, �����
	 *   <dt> ANLDST4
	 *   <dd> ������������� ��������� 4 �����-���������, �����
	 *   <dt> ANLDST5
	 *   <dd> ������������� ��������� 5 �����-���������, �����
	 * </dl>
	 * @return		�������� ��������
	 * @throws ApplicationException
	 */
	final public Object getFinSpecParam(String name) throws ApplicationException {
		//return DataUtils.variantToObject(nativeGetFinSpecParam(handle, name));
		//TODO
		return null;
	}
	
	/**
	 * ������������� ��� ������ ��������� ��������� ����������� ���������� ��������
	 * 
	 * @param name	������������ ��������
	 * @param value	�������� ��������
	 * @throws ApplicationException
	 * 
	 * @see #getFinOperParam
	 */
	final public void setFinOperParam(String name, Object value) throws ApplicationException {
		//nativeSetFinOperParam(handle, name, DataUtils.objectToVariant(value));
		//TODO
	}

	/**
	 * ������������� ��� ������ ��������� �������� ����������� ���������� ��������
	 * 
	 * @param name	������������ ��������
	 * @param value	�������� ��������
	 * @throws ApplicationException
	 * 
	 * @see #getFinSpecParam
	 */
	final public void setFinSpecParam(String name, Object value) throws ApplicationException {
		//nativeSetFinSpecParam(handle, name, DataUtils.objectToVariant(value));
		//TODO
	}

}
