/*
 * PatternSpecServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.table;

import java.util.List;

import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternSpec;

/**
 * ������-��������� "������������ ������� �������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PatternSpecServiceLocal.java,v 1.3 2008/08/12 14:00:54 sharapov Exp $
 */
public interface PatternSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PatternSpec, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/table/PatternSpec"; //$NON-NLS-1$

	/**
	 * �������� ������������ �������
	 * @param specList - ������ �������
	 */
	void updateSpecs(List<PatternSpec[]> specList);
	
	/**
	 * ��������� ������������ �������
	 * @param patternHead - ��������� �������
	 * @return ������ �������
	 */
	List<PatternSpec> loadSpecs(PatternHead patternHead);
	
}
