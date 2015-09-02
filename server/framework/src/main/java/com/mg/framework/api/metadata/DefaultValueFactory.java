/*
 * DefaultValueFactory.java
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
package com.mg.framework.api.metadata;

/**
 * ������� �������� �������� �� ��������� ��� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultValueFactory.java,v 1.1 2006/09/30 11:41:43 safonov Exp $
 */
public interface DefaultValueFactory<T> {

	/**
	 * �������� �������� �� ���������
	 * 
	 * @return	������ ��������
	 */
	T createDefaultValue();

}
