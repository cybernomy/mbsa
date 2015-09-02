/*
 * PayRollRest.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.personnelref.support.ui.ItemWithDateIntervalRest;
import com.mg.merp.salary.model.RollKind;

/**
 * Контроллер формы условий отбора расчетных ведомостей
 * 
 * @author leonova
 * @version $Id: PayRollRest.java,v 1.4 2006/08/28 12:42:47 leonova Exp $ 
 */
public class PayRollRest extends ItemWithDateIntervalRest {

	private RollKind rollKindName = null;
	@DataItemName("Salary.PayRoll.Number")
	private String rNumber = "";
	@DataItemName("Salary.Name")
	private String rName = "";
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.rollKindName = null;
		this.rNumber = "";
		this.rName = "";
	}

	/**
	 * @return Returns the rName.
	 */
	protected String getRName() {
		return rName;
	}

	/**
	 * @return Returns the rNumber.
	 */
	protected String getRNumber() {
		return rNumber;
	}

	/**
	 * @return Returns the rollKindName.
	 */
	protected RollKind getRollKindName() {
		return rollKindName;
	}



}
