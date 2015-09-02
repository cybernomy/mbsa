/*
 * InputDocumentParamsListener.java
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
package com.mg.merp.docflow;

import java.math.BigDecimal;
import java.util.EventListener;
import java.util.List;

/**
 * ��������� ������ ���������� ��������� � ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputDocumentParamsListener.java,v 1.1 2006/12/12 15:23:33 safonov Exp $
 */
public interface InputDocumentParamsListener extends EventListener {

	/**
	 * ��������� �����������
	 * 
	 * @param docSum	����� � ���������
	 * @param specList	������������ � ���������
	 */
	void performed(BigDecimal docSum, List<DocumentSpecItem> specList);
	
	/**
	 * ��������� ���������� ��������
	 *
	 */
	void canceled();	

}
