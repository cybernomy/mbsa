/*
 * DefaultHierarchyBrowseForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.dnd.DnDComponent;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.dnd.DefaultHierarchyBrowseTransferHandler;
import com.mg.framework.support.ui.dnd.DefaultTableTransferHandler;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.BeanUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Стандартная форма для отображения списков бизнес-компонетов имеющих связь с иерархической структурой
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultHierarchyBrowseForm.java,v 1.11 2009/02/09 14:44:36 safonov Exp $
 */
public class DefaultHierarchyBrowseForm extends DefaultPlainBrowseForm {
	/**
	 * наименование дерева
	 */
	protected final String TREE_WIDGET = "tree";
	protected DataBusinessObjectService<PersistentObject, Serializable> folderService;
	protected MaintenanceTreeController tree;
	protected AttributeMap treeUIProperties = new LocalDataTransferObject();
	protected PersistentObject folderEntity = null;

	public DefaultHierarchyBrowseForm() {
		tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
			@Override
			protected void doLoad() {
				setRootNode(loadFolders());
			}
		}
		) {

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTreeController#doSetupPermissions()
			 */
			@Override
			protected void doSetupPermissions() {
				setupFolderPermissions();
			}
			
		};
		tree.addMasterModelListener(table);
		tree.addTreeSelectionListener(new TreeSelectionListener(){

			public void valueChanged(TreeChangeEvent event) {
				folderEntity = ((EntityTreeNode) event.getNode()).getEntity();
				initializeMaster(folderEntity);
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
	
	/**
	 * Метод устанавливает объект мастер. Необходимо переопределить в
	 * наследнике.
	 * <p> Например:
	 * <blockquote><pre>
	 *   uiProperties.put("Folder", master)
	 * </pre></blockquote>
	 * 
	 * @param master объект сущность мастера
	 */
	protected void initializeMaster(PersistentObject master) {
		
	}

	/**
	 * настройка прав на элементы иерархии, в стандартной реализации настройка происходит для видов иерархии 0 (общие папки),
	 * необходимо переопределить если используется другой вид иерархии
	 *
	 */
	protected void setupFolderPermissions() {
		if (folderEntity != null)
			ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 0, "com.mg.merp.core.model.Folder", "Folder.Id");
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#loadTableModel()
	 */
	@Override
	protected void loadTableModel() {
		//переопределим метод, загрузка модели в таблицу произойдет после установки
		//на элемент иерархии при инициализации, т.к. таблица является слушателем события 
		//изменения текущего элемента иерархии
		
		//defect #3938 fixed
		RestrictionForm restForm = getRestrictionForm();
		if (restForm instanceof HierarchyRestrictionSupport && !((HierarchyRestrictionSupport) restForm).isUseHierarchy())
			super.loadTableModel();

		boolean selectFirstNode = true;
		if (getTargetEntity() != null) {
				try {
					Object attrValue = getTargetEntity().getAttribute(getFolderAttributeName());
					//если сущность, то получим первичный ключ
					Object folderId = attrValue instanceof PersistentObject ? BeanUtils.getIdentifierProperty(attrValue) : attrValue;
					//сбросим признак установки на 1ю запись, если найдем по первичному ключу, то
					//установится в методе locate
					table.setSelectFirstRow(false);
					try {
						//поиск в дереве, после позиционировании будут загружены нужные детали и уже в них
						//производим поиск
						if (tree.locate(folderId) != null) {
							selectFirstNode = false;
							if (table.locate(BeanUtils.getIdentifierProperty(getTargetEntity())) == -1)
								((Table) view.getWidget(TABLE_WIDGET)).setRowSelectionInterval(0, 0);
						}
					} finally {
						table.setSelectFirstRow(true);
					}
				} catch (Exception e) {
					getLogger().debug("get folder identifier failed", e);
				}
		}
		if (selectFirstNode)
			//выделим 0й элемент дерева чтобы сработала загрузка деталей дерева
			((MaintenanceTree) view.getWidget(TREE_WIDGET)).setSelectionRow(0);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (folderService == null)
			throw new IllegalStateException("Folder service can't be null");
		
		tree.initController(folderService);
		super.doOnRun();
		
		//права на перенос объектов между папками
		boolean movePerm = SecurityUtils.tryCheckPermission(new BusinessMethodPermission(service.getBusinessServiceMetadata().getName(), BusinessMethodPermission.MOVE_METHOD));
		if (movePerm) {
			DnDComponent dndTable = (DnDComponent) view.getWidget(TABLE_WIDGET);
			dndTable.setDragEnabled(true);
			dndTable.setTransferHandler(new DefaultTableTransferHandler());			
		}
		//права на изменения иерархии бизнес-компонента
		boolean changeHierarPerm = SecurityUtils.tryCheckPermission(new BusinessMethodPermission(service.getBusinessServiceMetadata().getName(), BusinessMethodPermission.CHANGE_HIERARCHY_METHOD));
		if (changeHierarPerm || movePerm) {
			DnDComponent dndTree = (DnDComponent) view.getWidget(TREE_WIDGET);
			dndTree.setDragEnabled(true);
			dndTree.setTransferHandler(new DefaultHierarchyBrowseTransferHandler(service, folderService, table, tree, changeHierarPerm));
		}
		if (!changeHierarPerm) {
			PopupMenu popupMenu = view.getWidget(TREE_WIDGET).getPopupMenu();
			popupMenu.getMenuItem(MaintenanceTree.ADD_MENU_ITEM).setEnabled(false);
			popupMenu.getMenuItem(MaintenanceTree.EDIT_MENU_ITEM).setEnabled(false);
			popupMenu.getMenuItem(MaintenanceTree.ERASE_MENU_ITEM).setEnabled(false);			
			popupMenu.getMenuItem(MaintenanceTree.PERMISSION_MENU_ITEM).setEnabled(false);			
		}
	}

	@Override
	protected void setupFocus() {
		//фокус устанавливаем на дерево, поведение аналогичное со 2ой версией
		view.getWidget(TREE_WIDGET).requestFocus();
	}

	/**
	 * получить имя атрибута сущности отвечающего за иерархию в данном браузере, имеет стандартное
	 * значение <literal>Folder</literal>, требует переопределения если имя атрибута отвечающего
	 * за иерархию отличается от стандартного
	 * 
	 * @return
	 */
	protected String getFolderAttributeName() {
		return "Folder";
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
