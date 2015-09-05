/*
 * FactItemContractorSource.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Источник контрагента
 * 
 * @author Artem V. Sharapov
 * @version $Id: FactItemContractorSource.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
@DataItemName ("Contract.FactItemContractorSource") //$NON-NLS-1$
public enum FactItemContractorSource {

	/**
	 * От кого
	 */	
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#ItemContractorSource.From") //$NON-NLS-1$
	FROM,

	/**
	 * Кому
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#ItemContractorSource.To") //$NON-NLS-1$
	TO

}
