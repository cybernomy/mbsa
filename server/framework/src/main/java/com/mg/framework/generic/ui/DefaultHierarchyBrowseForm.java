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
 * ����������� ����� ��� ����������� ������� ������-���������� ������� ����� � ������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultHierarchyBrowseForm.java,v 1.11 2009/02/09 14:44:36 safonov Exp $
 */
public class DefaultHierarchyBrowseForm extends DefaultPlainBrowseForm {
	/**
	 * ������������ ������
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
	 * �������� ������ ������������� ���������, ������ ���� ������������� � ����������
	 * <p> ��������:
	 * <blockquote><pre>
	 *   List<CatalogFolder> list = OrmTemplate.getInstance().find(CatalogFolder.class, "from CatalogFolder cf where".concat(DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1).concat(" order by cf.Id")));
	 *   return CatalogFolderTreeNode.createTree(list);
	 * </pre></blockquote>
	 * 
	 * @return	������ ������������� ���������
	 */
	protected TreeNode loadFolders() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}
	
	/**
	 * ����� ������������� ������ ������. ���������� �������������� �
	 * ����������.
	 * <p> ��������:
	 * <blockquote><pre>
	 *   uiProperties.put("Folder", master)
	 * </pre></blockquote>
	 * 
	 * @param master ������ �������� �������
	 */
	protected void initializeMaster(PersistentObject master) {
		
	}

	/**
	 * ��������� ���� �� �������� ��������, � ����������� ���������� ��������� ���������� ��� ����� �������� 0 (����� �����),
	 * ���������� �������������� ���� ������������ ������ ��� ��������
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
		//������������� �����, �������� ������ � ������� ���������� ����� ���������
		//�� ������� �������� ��� �������������, �.�. ������� �������� ���������� ������� 
		//��������� �������� �������� ��������
		
		//defect #3938 fixed
		RestrictionForm restForm = getRestrictionForm();
		if (restForm instanceof HierarchyRestrictionSupport && !((HierarchyRestrictionSupport) restForm).isUseHierarchy())
			super.loadTableModel();

		boolean selectFirstNode = true;
		if (getTargetEntity() != null) {
				try {
					Object attrValue = getTargetEntity().getAttribute(getFolderAttributeName());
					//���� ��������, �� ������� ��������� ����
					Object folderId = attrValue instanceof PersistentObject ? BeanUtils.getIdentifierProperty(attrValue) : attrValue;
					//������� ������� ��������� �� 1� ������, ���� ������ �� ���������� �����, ��
					//����������� � ������ locate
					table.setSelectFirstRow(false);
					try {
						//����� � ������, ����� ���������������� ����� ��������� ������ ������ � ��� � ���
						//���������� �����
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
			//������� 0� ������� ������ ����� ��������� �������� ������� ������
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
		
		//����� �� ������� �������� ����� �������
		boolean movePerm = SecurityUtils.tryCheckPermission(new BusinessMethodPermission(service.getBusinessServiceMetadata().getName(), BusinessMethodPermission.MOVE_METHOD));
		if (movePerm) {
			DnDComponent dndTable = (DnDComponent) view.getWidget(TABLE_WIDGET);
			dndTable.setDragEnabled(true);
			dndTable.setTransferHandler(new DefaultTableTransferHandler());			
		}
		//����� �� ��������� �������� ������-����������
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
		//����� ������������� �� ������, ��������� ����������� �� 2�� �������
		view.getWidget(TREE_WIDGET).requestFocus();
	}

	/**
	 * �������� ��� �������� �������� ����������� �� �������� � ������ ��������, ����� �����������
	 * �������� <literal>Folder</literal>, ������� ��������������� ���� ��� �������� �����������
	 * �� �������� ���������� �� ������������
	 * 
	 * @return
	 */
	protected String getFolderAttributeName() {
		return "Folder";
	}

	/**
	 * ���������� �������� �����
	 * 
	 * @param event
	 */
	public void onActionAddFolder(WidgetEvent event) {
		tree.add();
	}

	/**
	 * ���������� ��������� �����
	 * 
	 * @param event
	 */
	public void onActionEditFolder(WidgetEvent event) {
		tree.edit();
	}

	/**
	 * ���������� �������� �����
	 * 
	 * @param event
	 */
	public void onActionEraseFolder(WidgetEvent event) {
		tree.erase();
	}

	/**
	 * ���������� ���������
	 * 
	 * @param event
	 */
	public void onActionViewFolder(WidgetEvent event) {
		tree.view();
	}

	/**
	 * ���������� ��������� ���� �� �����
	 * 
	 * @param event
	 */
	public void onActionSetupFolderPermission(WidgetEvent event) {
		tree.setupPermissions();
	}

	/**
	 * ���������� ���������� �����
	 * 
	 * @param event
	 */
	public void onActionRefreshTree(WidgetEvent event) {
		tree.refresh();
	}

}
