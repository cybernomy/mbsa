/*
 * DefaultLegacyRestrictionForm.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.support.LocalDataTransferObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyRestrictionForm.java,v 1.2 2006/09/22 08:56:13 safonov Exp $
 */
@Deprecated
public abstract class DefaultLegacyRestrictionForm extends DefaultRestrictionForm {
	protected AttributeMap restrictionItem = new LocalDataTransferObject();

	protected abstract void collectRestrictionItem();
	
	protected abstract void extractRestrictionItem();
	
	public AttributeMap getRestrictionItem() {
		restrictionItem.clear();
		collectRestrictionItem();
		return restrictionItem;
	}
	
}
