/**
 * DataBusinessServiceInterceptorManager.java
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
 * �������� ������������� �������� ������-����������� ����������� �������. 
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManager.java,v 1.1 2007/12/13 13:01:32 safonov Exp $
 */
public interface DataBusinessServiceInterceptorManager extends Serializable {

	/**
	 * ���������������� ����������� � ���������
	 * 
	 * @param interceptor
	 */
	void registerInterceptor(DataBusinessServiceInterceptor interceptor);
	
	/**
	 * ������� ����������� �� ���������
	 * 
	 * @param interceptor
	 */
	void unregisterInterceptor(DataBusinessServiceInterceptor interceptor);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnClone(final DataBusinessObjectService<T, ID> service, final T entity);

	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnErase(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnInitialize(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnPostCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnStore(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * ����� ���� ������������� ��� ������-���������� �� ������� "�����������", ����������� �������� �� �����
	 * ��������� ���� ���� �� ���� ����������� ��������� ������ ������������ ��������
	 * 
	 * @param service	������-���������
	 * @param context	�������� �������� ������
	 * @param entity	��������
	 * @return	���� <code>true</code> �� ����������� �������� ������-���������� �� ����� ���������
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnValidate(final DataBusinessObjectService<T, ID> service, final ValidationContext context, final T entity);
	
}
