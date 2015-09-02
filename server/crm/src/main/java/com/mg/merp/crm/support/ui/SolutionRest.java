/*
 * SolutionRest.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.crm.model.Problem;

/**
 * Контроллер условий отбора решений
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: SolutionRest.java,v 1.1 2007/08/17 08:44:50 alikaev Exp $
 */
public class SolutionRest extends DefaultHierarhyRestrictionForm {
	
	@DataItemName("CRM.BigName") //$NON-NLS-1$
	public String name = "";	 //$NON-NLS-1$
	public Problem problem = null;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.name = ""; //$NON-NLS-1$
		this.problem = null;
	}
	
	public String getName(){
		return name;
	}
	
	public Problem getProblem(){
		return problem;
	}

}
