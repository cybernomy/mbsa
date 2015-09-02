/*
 * ScrapMachineHeadServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.ScrapDocumentHead;

/**
 * ������-��������� "��� �� �������� ������ �������, ������������� �������������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ScrapMachineHeadServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface ScrapMachineHeadServiceLocal
   extends com.mg.merp.document.GoodsDocument<ScrapDocumentHead, Integer, ScrapMachineModelServiceLocal, ScrapMachineSpecServiceLocal>
{
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/manufacture/ScrapMachineHead";
	
	/**
	 * ��� ����� ��� ����� �� �������� ������ �������, ������������� �������������
	 */
	final static short FOLDER_PART = 12509;
	
	/**
	 * docsection ��� ����� �� �������� ������ �������, ������������� �������������
	 */
	final static short DOCSECTION = 12008;
}
