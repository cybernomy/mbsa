/*
 * DocumentNumberStrategy.java
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

import com.mg.merp.document.model.DocHead;

/**
 * ��������� ������������ ������ ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentNumberStrategy.java,v 1.1 2007/03/23 13:52:02 safonov Exp $
 */
public interface DocumentNumberStrategy {

	/**
	 * ������������ ������ ���������
	 * 
	 * @param docHead	�������� ���������
	 * @return	����� ���������
	 */
	String generateNumber(DocHead docHead);

}
