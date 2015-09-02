/*
 * StaffListUnitBr.java
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
package com.mg.merp.personnelref.support.ui;

import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.personnelref.StaffListUnitServiceLocal;
import com.mg.merp.personnelref.model.StaffListUnit;

/**
 * @author leonova
 * @version $Id: StaffListUnitBr.java,v 1.5 2007/11/08 16:21:40 safonov Exp $
 */
public class StaffListUnitBr extends DefaultTreeBrowseForm{	
	
	public StaffListUnitBr() throws Exception{
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListUnit");
		tree.setParentPropertyName("Parent.Id");
		
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() {
		List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList.Id = :staffList order by slu.Parent.Id, slu.UName", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)),  "staffList", StaffListUnitServiceLocal.STAFF_LIST_ID));				
		return StaffListUnitTreeNode.createTree(list);
	}

}

