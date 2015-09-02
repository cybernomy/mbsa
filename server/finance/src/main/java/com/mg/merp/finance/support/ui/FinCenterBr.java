/*
 * FinCenterBr.java
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
package com.mg.merp.finance.support.ui;

import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.finance.model.Center;

/**
 * Браузер центров финансового учета
 * 
 * @author leonova
 * @version $Id: FinCenterBr.java,v 1.5 2007/11/08 16:04:24 safonov Exp $
 */
public class FinCenterBr extends DefaultTreeBrowseForm {

	public FinCenterBr() throws Exception{
		super();	
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Center");
		tree.setParentPropertyName("Parent");
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() {
		List<Center> list = OrmTemplate.getInstance().find(Center.class, String.format("from Center c order by c.Parent, c.Name"));
		return CenterTreeNode.createTree(list);
	}

}
