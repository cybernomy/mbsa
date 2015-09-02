/*
 * DocFlowStageRightsDialog.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.docprocess.model.ActionUserGrant;

/**
 * @author Oleg V. Safonov
 * @version $Id: DocFlowStageRightsDialog.java,v 1.1 2006/08/25 11:48:17 safonov Exp $
 */
public class DocFlowStageRightsDialog extends DefaultDialog {
	private ActionUserGrant grants = ActionUserGrant.NONE;

	/**
	 * @return Returns the grants.
	 */
	public ActionUserGrant getGrants() {
		return grants;
	}

	/**
	 * @param grants The grants to set.
	 */
	public void setGrants(ActionUserGrant grants) {
		this.grants = grants;
	}
}
