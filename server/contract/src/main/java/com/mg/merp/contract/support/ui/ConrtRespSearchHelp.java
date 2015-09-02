/*
 * ConrtRespSearchHelp.java
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
package com.mg.merp.contract.support.ui;

import com.mg.merp.reference.support.ui.ContractorResponsibleSearchHelp;

/**
 * SearchHelp для ответственного сотрудника нашей организации
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ConrtRespSearchHelp.java,v 1.3 2007/04/16 05:34:17 sharapov Exp $
 */
public class ConrtRespSearchHelp extends ContractorResponsibleSearchHelp {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.support.ui.ContractorResponsibleSearchHelp#getContractorContextNames()
	 */
	@Override
	protected String[] getContractorContextNames() {		
		return new String[] {"fromCode", "From"}; //$NON-NLS-1$ //$NON-NLS-1$
	}

}
