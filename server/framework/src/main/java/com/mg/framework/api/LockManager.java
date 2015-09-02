/*
 * LockManager.java
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
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;

/**
 * �������� ���������� ������������ ��������, ������������ ���������������� �������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockManager.java,v 1.1 2006/12/15 09:26:17 safonov Exp $
 */
public interface LockManager {
	/**
	 * ���������� �������
	 * 
	 * @param entity	������
	 * @throws LockingException ���� ������ ��� ������������ ������������� �����������
	 */
	void lock(PersistentObject entity) throws LockingException;
	
	/**
	 * �������� ���������� �������
	 * 
	 * @param entity	������
	 * @return	<code>true</code> ���� ������ ������ ������������, � ��������� ������ <code>false</code>
	 */
	boolean tryLock(PersistentObject entity);
	
	/**
	 * ����� ���������� �������
	 * 
	 * @param entity	������
	 */
	void unlock(PersistentObject entity);
	
}
