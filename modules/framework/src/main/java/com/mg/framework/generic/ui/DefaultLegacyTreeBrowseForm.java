/*
 * DefaultLegacyTreeBrowseForm.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyTreeBrowseForm.java,v 1.8 2009/02/09 14:31:28 safonov Exp $
 */
@Deprecated
public class DefaultLegacyTreeBrowseForm extends AbstractForm implements MaintenanceBrowseForm, SearchHelpForm {
	private SearchHelpListener listener = null;
	protected DataBusinessObjectService folderService;
	protected MaintenanceTreeController tree;
	protected AttributeMap treeUIProperties = new LocalDataTransferObject();

	public DefaultLegacyTreeBrowseForm() {
		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			@Override
			protected void doLoad() {
				//setRootNode(LegacyTreeNode.convertDataSetToTreeNode(loadFolders()));
			}
		});
	}

	protected Object loadFolders() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (folderService == null)
			throw new IllegalStateException("Folder service cann't be null");
		
		tree.initController(folderService);
		//tree.setData(loadFolders());
		super.doOnRun();
		
		//обработка SearchHelp, если используется в данном качестве, то откроем кнопку chooseButton
		if (listener != null) {
			Widget chooseButton = view.getWidget("сhooseButton");
			if (chooseButton != null)
				chooseButton.setVisible(true);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService service) throws ApplicationException {
		folderService = service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#addSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void addSearchHelpListener(SearchHelpListener listener) {
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#getSearchHelpListeners()
	 */
	public SearchHelpListener[] getSearchHelpListeners() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#removeSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void removeSearchHelpListener(SearchHelpListener listener) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#setEntity(com.mg.framework.api.orm.PersistentObject)
	 */
	public void setTargetEntity(PersistentObject entity) {
	}

}
