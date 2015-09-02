/**
 * CreateDocumentDocFlowListener.java
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

import java.util.EventListener;

/**
 * ��������� �������� ��������� �� ������ ������ ��
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateDocumentDocFlowListener.java,v 1.1 2007/09/27 07:17:11 safonov Exp $
 */
public interface CreateDocumentDocFlowListener extends EventListener {

	/**
	 * �������� ���������
	 */
	void completed();
	
	/**
	 * �������� ��������
	 */
	void canceled();
	
}
