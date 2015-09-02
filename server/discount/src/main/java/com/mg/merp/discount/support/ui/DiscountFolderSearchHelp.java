/*
 * DiscountFolderSearchHelp.java
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
package com.mg.merp.discount.support.ui;

import com.mg.merp.discount.DiscountServiceLocal;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

/**
 * @author leonova
 * @version $Id: DiscountFolderSearchHelp.java,v 1.1 2006/10/16 11:37:26 leonova Exp $
 */
public class DiscountFolderSearchHelp extends FolderByTypeSearchHelp {

	@Override
	protected short getFolderType() {
		return DiscountServiceLocal.FOLDER_PART;
	}

}
