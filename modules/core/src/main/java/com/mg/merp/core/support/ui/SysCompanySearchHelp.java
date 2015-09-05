/**
 * SysCompanySearchHelp.java
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
package com.mg.merp.core.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.merp.core.SysCompanyServiceLocal;

/**
 * Поиск сущностей бизнес-компонента "Балансовые единицы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysCompanySearchHelp.java,v 1.1 2007/09/20 15:07:10 safonov Exp $
 */
public class SysCompanySearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return SysCompanyServiceLocal.SERVICE_NAME;
	}

}
