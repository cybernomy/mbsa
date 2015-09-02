/*
 * FolderSearchForm.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Форма для выбора папок, загружает список папок по установленному типу папки
 * 
 * @author Oleg V. Safonov
 * @version $Id: FolderSearchForm.java,v 1.1 2006/08/23 11:40:49 safonov Exp $
 */
public class FolderSearchForm extends DefaultTreeBrowseForm {
	protected short folderType;

	public FolderSearchForm() {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		tree.setParentPropertyName("Folder.Id");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() {
		return ReferenceUtils.loadFolderHierarchy(folderType);
	}

	/**
	 * получить тип папки
	 * 
	 * @return Returns the folderType.
	 */
	public short getFolderType() {
		return folderType;
	}

	/**
	 * установить тип папки
	 * 
	 * @param folderType The folderType to set.
	 */
	public void setFolderType(short folderType) {
		this.folderType = folderType;
	}

}
