/*
 * CustomsDeclarationSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * ���������� ����� ������ ������-���������� "�������� ���������� ����������"
 *  
 * @author Konstantin S. Alikaev
 * @version $Id: CustomsDeclarationSearchHelp.java,v 1.1 2008/03/20 09:46:50 alikaev Exp $
 */
public class CustomsDeclarationSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return "merp/reference/CustomsDeclaration";
	}

}
