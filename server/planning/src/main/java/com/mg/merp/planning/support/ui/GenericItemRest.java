/*
 * GenericItemRest.java
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
package com.mg.merp.planning.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;

/**
 * @author leonova
 * @version $Id: GenericItemRest.java,v 1.2 2006/08/25 10:16:52 leonova Exp $ 
 */
public class GenericItemRest extends DefaultRestrictionForm {

	@DataItemName("Planning.Code")
	private String genericItemCode = "";
	@DataItemName("Planning.Name")
	private String genericItemName = "";
	@DataItemName("Planning.Cond.GenericItem.PlanningShelfLifeFrom")
	private short planningShelfLifeFrom = 0;
	@DataItemName("Planning.Cond.GenericItem.PlanningShelfLifeTill")
	private short planningShelfLifeTill = 0;
	private int planningItemFlag = 0;
	
	@Override
	protected void doClearRestrictionItem() {
		this.genericItemCode = "";
		this.genericItemName = "";
		this.planningShelfLifeFrom = 0;
		this.planningShelfLifeTill = 0;
		this.planningItemFlag = 0;
	}

	/**
	 * @return Returns the genericItemCode.
	 */
	protected String getGenericItemCode() {
		return genericItemCode;
	}

	/**
	 * @return Returns the genericItemName.
	 */
	protected String getGenericItemName() {
		return genericItemName;
	}

	/**
	 * @return Returns the planningItemFlag.
	 */
	protected int getPlanningItemFlag() {
		return planningItemFlag;
	}

	/**
	 * @return Returns the planningShelfLifeFrom.
	 */
	protected short getPlanningShelfLifeFrom() {
		return planningShelfLifeFrom;
	}

	/**
	 * @return Returns the planningShelfLifeTill.
	 */
	protected short getPlanningShelfLifeTill() {
		return planningShelfLifeTill;
	}



}
