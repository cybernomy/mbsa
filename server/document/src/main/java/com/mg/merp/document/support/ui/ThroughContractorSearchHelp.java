/*
 * ToContractorSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.reference.support.ui.UniversalContractorSearchHelp;

/**
 * Поиск контрагента для поля документа "Через кого"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ThroughContractorSearchHelp.java,v 1.3 2007/11/16 07:59:01 sharapov Exp $
 */
public class ThroughContractorSearchHelp extends UniversalContractorSearchHelp {
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.support.ui.UniversalContractorSearchHelp#getContractorKinds()
	 */
	@Override
	protected String[] getContractorKinds() {
		return new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_ORGUNIT, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
	}

}
