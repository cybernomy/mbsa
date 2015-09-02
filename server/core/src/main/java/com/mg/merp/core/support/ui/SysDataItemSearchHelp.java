/**
 * SysDataItemSearchHelp.java
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
package com.mg.merp.core.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.merp.core.SysDataItemServiceLocal;

/**
 * �������� ������ ��������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysDataItemSearchHelp.java,v 1.1 2008/03/03 13:05:04 safonov Exp $
 */
public class SysDataItemSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return SysDataItemServiceLocal.SERVICE_NAME;
	}

}
