/*
 * CostDetailTableController.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.mfreference.model.CostDetail;

/**
 * @author Oleg V. Safonov
 * @version $Id: CostDetailTableController.java,v 1.1 2007/07/30 10:24:19 safonov Exp $
 */
public class CostDetailTableController extends DefaultTableController {
	private String propertyName = "StandartCostDetail";

	public CostDetailTableController() {
		super(new CostDetailMaintenanceEJBQLTableModel());
	}

	public CostDetailTableController(String propertyName) {
		super(new CostDetailMaintenanceEJBQLTableModel());
		this.propertyName = propertyName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.DefaultTableController#fireMasterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	@Override
	public void fireMasterChange(ModelChangeEvent event) {
		((CostDetailMaintenanceEJBQLTableModel) tableModel).setCostDetail((CostDetail) event.getEntity().getAttribute(propertyName));
		super.fireMasterChange(event);
	}

}
