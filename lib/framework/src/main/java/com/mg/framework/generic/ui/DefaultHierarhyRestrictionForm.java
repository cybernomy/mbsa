/*
 * DefaultHierarhyRestrictionForm.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.ui.HierarchyRestrictionSupport;

/**
 * @author leonova
 * @version $Id: DefaultHierarhyRestrictionForm.java,v 1.3 2008/10/09 06:51:44 safonov Exp $
 */
public abstract class DefaultHierarhyRestrictionForm extends
		DefaultRestrictionForm implements HierarchyRestrictionSupport {
	private static final String BY_FOLDER_ATTR_NAME = "isUseHierarchy"; //$NON-NLS-1$
	private boolean isUseHierarchy = true;

	public DefaultHierarhyRestrictionForm() {
		super();
		registerRestrictionItem(BY_FOLDER_ATTR_NAME, true, true);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.HierarchyRestrictionSupport#isUseHierarchy()
	 */
	public boolean isUseHierarchy() {
		return isUseHierarchy;
	}

}
