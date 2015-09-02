/**
 * DataBusinessServiceInterceptor.java
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
package com.mg.framework.api;

import java.io.Serializable;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;

/**
 * ����������� �������� ������-����������� ����������� �������, ���������� ������� ���������� ������������
 * ��� ��������� ������� ������������ � ������-����������� (��������, �����������, ��������, �����������, �������� ������).
 * ���������� ������� ������������ ����� ������������ ���������������� �������������� ��������,
 * �������� ������ �������� JNDI, JDBC, JMS, EJB, ��������� ��������� �������.
 * <strong>��������, � ����������� ������� ������� ���������� ��������� ��������� �������� � UI</strong>
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptor.java,v 1.1 2007/12/13 13:01:32 safonov Exp $
 */
public interface DataBusinessServiceInterceptor extends Serializable {

	/**
	 * �������� ��� ������������
	 * 
	 * @return	��� ������������
	 */
	String getName();
	
	/**
	 * ���������� ������ ���� �������������� ������-�����������
	 * 
	 * @return	������ ����
	 */
	String[] getHandledServices();
	
	/**
	 * �������� �� ������� "�����������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onClone(final DataBusinessObjectService<T, ID> service, final T entity);

	/**
	 * �������� �� ������� "��������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * �������� �� ������� "��������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onErase(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * �������� �� ������� "�������������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onInitialize(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * �������� �� ������� "����� ��������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onPostCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * �������� �� ������� "���������"
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onStore(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * �������� �� ������� "�������� ������"
	 * 
	 * @param service	������-���������
	 * @param context	�������� �������� ������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean onValidate(final DataBusinessObjectService<T, ID> service, final ValidationContext context, final T entity);
	
}
