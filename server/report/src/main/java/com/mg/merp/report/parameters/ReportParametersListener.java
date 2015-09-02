/* ReportParameterListener.java
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
package com.mg.merp.report.parameters;

import java.io.Serializable;

/**
 * ��������� ��� ������� ���������� ������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: ReportParametersListener.java,v 1.2 2008/03/27 08:12:15 safonov Exp $
 * 
 * @deprecated
 */
@Deprecated
public interface ReportParametersListener extends Serializable {

	/**
	 * �������� ��������� ������
	 * 
	 * @param event �������, ���������� ��������� ����� ����������
	 */
	void getParameters(ReportParametersEvent event);
}
