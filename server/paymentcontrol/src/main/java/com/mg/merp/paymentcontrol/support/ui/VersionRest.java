/*
 * VersionRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;

/**
 * Контроллер формы условий отбора версий планирования платежей
 * 
 * @author leonova
 * @version $Id: VersionRest.java,v 1.2 2006/09/01 08:04:34 leonova Exp $ 
 */
public class VersionRest extends DefaultRestrictionForm {

	@DataItemName("PaymentControl.Cond.Version.AvailableOnly")
	private boolean availableOnly = false;

	@Override
	protected void doClearRestrictionItem() {
		this.availableOnly = false;
	}

	/**
	 * @return Returns the availableOnly.
	 */
	protected boolean isAvailableOnly() {
		return availableOnly;
	}


}
