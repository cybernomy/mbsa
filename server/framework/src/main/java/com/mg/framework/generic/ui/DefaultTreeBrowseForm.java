/*
 * DefaultTreeBrowseForm.java
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
package com.mg.framework.generic.ui;

import java.io.Serializable;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.CustomActionListener;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.dnd.DnDComponent;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.service.CustomActionManagerLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.CustomActionExecutionContextImpl;
import com.mg.framework.support.ui.dnd.DefaultTreeBrowseTransferHandler;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.BeanUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Стандартная форма для отображения дерева бизнес-компонетов имеющих иерархическую структуру
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultTreeBrowseForm.java,v 1.9 2009/02/09 14:26:42 safonov Exp $
 */
public class DefaultTreeBrowseForm extends AbstractSearchForm implements
		MaintenanceBrowseForm {
	/**
	 * наименование дерева
	 */
	protected final String TREE_WIDGET = "tree";
	protected DataBusinessObjectService<PersistentObject, Serializable> folderService;
	protected MaintenanceTreeController tree;
	protected AttributeMap treeUIProperties = new LocalDataTransferObject();
	protected PersistentObject currentNode = null;
	protected CustomActionListener customActionListener = null;

	public DefaultTreeBrowseForm() {
		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			@Override
			protected void doLoad() {
				setRootNode(loadFolders());
			}
			
		}) {

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTreeController#doSetupPermissions()
			 */
			@Override
			protected void doSetupPermissions() {
				setupFolderPermissions();
			}
		};
		
		tree.addMasterModelListener(new MasterModelListener() {
			public void masterChange(ModelChangeEvent event) {
				currentNode = event.getEntity();
			}
		});
	}
	
	/**
	 * загрузка модели иерархической структуры, должен быть переопределен в наследнике
	 * <p> Например:
	 * <blockquote><pre>
	 *   List<CatalogFolder> list = OrmTemplate.getInstance().find(CatalogFolder.class, "from CatalogFolder cf where".concat(DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1).concat(" order by cf.Id")));
	 *   return CatalogFolderTreeNode.createTree(list);
	 * </pre></blockquote>
	 * 
	 * @return	модель иерархической структуры
	 */
	protected TreeNode loadFolders() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (folderService == null)
			throw new IllegalStateException("Folder service can't be null");
		
		tree.initController(folderService);
		super.doOnRun();

		boolean selectFirstNode = true;
		if (getTargetEntity() != null)
			selectFirstNode = tree.locate(BeanUtils.getIdentifierProperty(getTargetEntity())) == null;
		
		if (selectFirstNode)
			//выделим 0й элемент дерева
			((MaintenanceTree) view.getWidget(TREE_WIDGET)).setSelectionRow(0);

		view.getWidget(TREE_WIDGET).requestFocus();

		//права на изменения иерархии бизнес-компонента
		if (SecurityUtils.tryCheckPermission(new BusinessMethodPermission(folderService.getBusinessServiceMetadata().getName(), BusinessMethodPermission.CHANGE_HIERARCHY_METHOD))) {
			DnDComponent dndTree = (DnDComponent) view.getWidget(TREE_WIDGET);
			dndTree.setDragEnabled(true);
			dndTree.setTransferHandler(new DefaultTreeBrowseTransferHandler(folderService, tree));
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		PersistentObject[] items = new PersistentObject[1];
		items[0] = currentNode;
		return items;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService<PersistentObject, Serializable> service) {
		folderService = service;
	}
	
	/**
	 * настройка прав на элементы иерархии, в стандартной реализации настройка происходит для видов иерархии 0 (общие папки),
	 * необходимо переопределить если используется другой вид иерархии
	 */
	protected void setupFolderPermissions() {
		if (currentNode != null)
			ServerUtils.getSecuritySystem().setupTreePermission((Integer) currentNode.getPrimaryKey(), 0, "com.mg.merp.core.model.Folder", "Folder.Id");
	}

	/**
	 * получить слушатель событий от настраиваемых действий пользователя
	 * 
	 * @return
	 */
	protected CustomActionListener getCustomActionListener() {
		if (customActionListener == null)
			customActionListener = new CustomActionListener() {

			public void aborted() {
			}

			public void completed(boolean refresh) {
				if (refresh)
					tree.refresh();
			}

		};
		return customActionListener;
	}

	/**
	 * обработчик запуска настраиваемых действий пользователя
	 * 
	 * @param event	событие
	 */
	public void onActionExecuteCustomUserAction(WidgetEvent event) {
		Serializable[] selectedIdentifiers = null;
		if (currentNode != null)
			selectedIdentifiers = new Serializable[] {(Serializable) currentNode.getPrimaryKey()};
		CustomActionExecutionContextImpl context = new CustomActionExecutionContextImpl(event.getActionCommand()
				, folderService, selectedIdentifiers, getCustomActionListener());
		CustomActionManagerLocator.locate().executeAction(context);
	}

	/**
	 * обработчик создания папки
	 * 
	 * @param event
	 */
	public void onActionAddFolder(WidgetEvent event) {
		tree.add();
	}

	/**
	 * обработчик изменения папки
	 * 
	 * @param event
	 */
	public void onActionEditFolder(WidgetEvent event) {
		tree.edit();
	}

	/**
	 * обработчик удаления папки
	 * 
	 * @param event
	 */
	public void onActionEraseFolder(WidgetEvent event) {
		tree.erase();
	}

	/**
	 * обработчик просмотра
	 * 
	 * @param event
	 */
	public void onActionViewFolder(WidgetEvent event) {
		tree.view();
	}

	/**
	 * обработчик настройки прав на папки
	 * 
	 * @param event
	 */
	public void onActionSetupFolderPermission(WidgetEvent event) {
		tree.setupPermissions();
	}

	/**
	 * обработчик обновления папок
	 * 
	 * @param event
	 */
	public void onActionRefreshTree(WidgetEvent event) {
		tree.refresh();
	}

}
