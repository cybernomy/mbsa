/*
 * PmcPeriodSearchForm.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.util.List;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.paymentcontrol.model.PmcPeriod;

/**
 * Контроллер формы поиска сущности "Период планирования"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcPeriodSearchForm.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcPeriodSearchForm extends AbstractSearchForm {

	private PersistentObject folderEntity = null;
	private MaintenanceTreeController tree;
	private AttributeMap treeUIProperties = new LocalDataTransferObject();
	private DataBusinessObjectService folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Period"); //$NON-NLS-1$


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {

		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
			 */
			@Override
			public TreeNode getRootNode() {
				List<PmcPeriod> list = OrmTemplate.getInstance().find(PmcPeriod.class, String.format("from PmcPeriod pp order by pp.Id")); //$NON-NLS-1$
				return PeriodTreeNode.createTree(list);
			}
		});

		tree.initController(folderService);
		tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeChangeEvent event) {
				folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
			}
		});
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return new PersistentObject[] {folderEntity};
	}

}
