/*
 * DefaultLegacyHierarhyRestrictionForm.java
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

import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyHierarhyRestrictionForm.java,v 1.3 2006/09/22 08:56:13 safonov Exp $
 */
@Deprecated
public abstract class DefaultLegacyHierarhyRestrictionForm extends
		DefaultLegacyRestrictionForm implements HierarchyRestrictionSupport {
	private boolean byFolder = true;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyRestrictionForm#collectRestrictionItem()
	 */
	@Override
	protected void collectRestrictionItem() {
		restrictionItem.put(BrowseCond.BY_FOLDER_ATTRIBUTE, byFolder);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyRestrictionForm#extractRestrictionItem()
	 */
	@Override
	protected void extractRestrictionItem() {
		byFolder = (Boolean) restrictionItem.get(BrowseCond.BY_FOLDER_ATTRIBUTE);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		byFolder = true;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.HierarchyRestrictionSupport#isUseHierarchy()
	 */
	public boolean isUseHierarchy() {
		return byFolder;
	}

}
