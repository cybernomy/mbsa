/**
 * CoreUtils.java
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
package com.mg.merp.core.support;

import java.util.List;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.support.ui.FolderTreeNode;

/**
 * ������� ��������� Jet
 * 
 * @author Oleg V. Safonov
 * @version $Id: CoreUtils.java,v 1.2 2007/11/08 16:44:12 safonov Exp $
 */
public class CoreUtils {
	
	/**
	 * �������� ������������� ��������� �����, ������������ ��� ��������� ������ ������
	 * � ������������� ���������
	 * 
	 * @param folderType	��� �����
	 * @return	������������� ���������
	 */
	public static TreeNode loadFolderHierarchy(short folderType) {
		List<Folder> list = OrmTemplate.getInstance().findByCriteria(DatabaseUtils.generateFlatBrowseCriteria(OrmTemplate.createCriteria(Folder.class, "f"), "f.Id", 0)
				.add(Restrictions.eq("f.FolderType", folderType))
				.addOrder(Order.asc("f.Folder.Id"))
				.addOrder(Order.asc("f.FName")));
		return FolderTreeNode.createTree(list);
	}

}
