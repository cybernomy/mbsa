/*
 * MaintenanceTreeController.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.CustomActionDescriptor;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.widget.Tree;
import com.mg.framework.service.CustomActionManagerLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.ServerUtils;

/**
 * ���������� �������� ������ ��������� �������� ��������� 
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTreeController.java,v 1.13 2009/02/09 13:46:23 safonov Exp $
 */
public class MaintenanceTreeController implements MaintenanceTreeControllerAdapter, Serializable {
	private List<TreeSelectionListener> selectionListeners = new ArrayList<TreeSelectionListener>();
	private List<MasterModelListener> modelListeners = new ArrayList<MasterModelListener>();
	private TreeModel treeModel;
	private Tree treeWidget;
	private Serializable currentNodeId = null;
	private DataBusinessObjectService<PersistentObject, Serializable> service;
	private AttributeMap uiProperties = null;
	private String parentPropertyName = "PARENT_ID";
	private String addForm, editForm, viewForm = null;

	public MaintenanceTreeController(AttributeMap uiProperties, TreeModel treeModel) {
		this.uiProperties = uiProperties;
		this.treeModel = treeModel;
	}
	
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void initController(DataBusinessObjectService service) throws ApplicationException {
		this.service = service;
	}
	
	public void addTreeSelectionListener(TreeSelectionListener listener) {
		this.selectionListeners.add(listener);
	}
	
	public void addMasterModelListener(MasterModelListener listener) {
		this.modelListeners.add(listener);
	}
	
	public void fireSelectionChange(TreeChangeEvent event) {
		for(TreeSelectionListener listener : selectionListeners)
			listener.valueChanged(event);
	}
	
	public void fireMasterModelChange(ModelChangeEvent event) {
		for(MasterModelListener listener : modelListeners)
			listener.masterChange(event);
	}
	
	/**
	 * ��������� ����� ��������� �������, ��������������� ��� ���� �����
	 * ��������� (����������, ���������, ��������)
	 * 
	 * @param formName ������������ �����
	 */
	public void setMaintenanceForm(String formName) {
		addForm = formName;
		editForm = formName;
		viewForm = formName;
	}
	
	/**
	 * ��������� ����� ��������� ���������� �������
	 * 
	 * @param formName ������������ �����
	 */
	public void setAddForm(String formName) {
		addForm = formName;
	}
	
	/**
	 * ��������� ����� ��������� ��������� �������
	 * 
	 * @param formName ������������ �����
	 */
	public void setEditForm(String formName) {
		editForm = formName;
	}
	
	/**
	 * ��������� ����� ��������� ��������� �������
	 * 
	 * @param formName ������������ �����
	 */
	public void setViewForm(String formName) {
		viewForm = formName;
	}
	
	/**
	 * ���������� ���������� ��������
	 *
	 */
	protected void doAdd() {
		if (currentNodeId != null) {
			if (uiProperties == null)
				throw new IllegalStateException("Properties cann't be null");
			
			uiProperties.put(parentPropertyName, currentNodeId);
			MaintenanceHelper.add(service, uiProperties, addForm, new MaintenanceFormActionListener() {
				public void performed(MaintenanceFormEvent event) {
					currentNodeId = (Serializable) event.getEntity().getPrimaryKey();
					refresh();
				}

				public void canceled(MaintenanceFormEvent event) {
					//do noting
				}
			});
		}
	}
	
	/**
	 * ���������� ��������� ��������
	 *
	 */
	protected void doEdit() {
		if (currentNodeId != null) {
			MaintenanceHelper.edit(service, currentNodeId, editForm, new MaintenanceFormActionListener() {
				public void performed(MaintenanceFormEvent event) {
					refresh();
				}

				public void canceled(MaintenanceFormEvent event) {
					//do noting
				}
			});
		}		
	}
	
	/**
	 * ���������� ��������� ��������
	 *
	 */
	protected void doView() {
		if (currentNodeId != null) {
			MaintenanceHelper.view(service, currentNodeId, viewForm, null);
		}
	}
	
	/**
	 * ���������� �������� ��������
	 *
	 */
	protected void doErase() {
		if (currentNodeId != null) {
			Messages msg = Messages.getInstance();
			final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT);
			UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(Messages.ERASE_ALERT_TITLE), msg.getMessage(Messages.ERASE_ALERT_QUESTION), yesButton, msg.getMessage(Messages.NO_BUTTON_TEXT), new AlertListener() {
				public void alertClosing(String value) {
					if (value.equals(yesButton)) {
						service.erase(currentNodeId);
						currentNodeId = null;
						ServerUtils.getPersistentManager().flush();
						refresh();
					}
				}
			});
		}
	}
	
	/**
	 * ���������� ���������� ������ ���������
	 *
	 */
	protected void doRefresh() {
		int[] selectedRows = null;
		//����� �������� ��������� ���� ���� ��������
		if (currentNodeId == null)
			selectedRows = treeWidget.getSelectionRows();
		treeModel.load();
		if (selectedRows == null || selectedRows.length == 0)
			locate(currentNodeId);
		else
			treeWidget.setSelectionRow(selectedRows[0] - 1); //��� �������� ��������� �� ��� ����
	}
	
	/**
	 * ���������� ��������� ���� ������� �� ������� ������
	 *
	 */
	protected void doSetupPermissions() {
		//do nothing
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeControllerAdapter#setCurrentNode(com.mg.framework.support.ui.widget.tree.TreeNode)
	 */
	public void setCurrentNode(TreeNode node) {
		currentNodeId = ((EntityTreeNode) node).getPrimaryKey();
		PersistentObject currentNode = ((EntityTreeNode) node).getEntity();
		fireSelectionChange(new TreeChangeEvent(this, node));
		if (currentNode != null)
			fireMasterModelChange(new ModelChangeEvent(this, currentNode));
		else
			fireMasterModelChange(new ModelChangeEvent(this, currentNodeId));
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#add()
	 */
	public final void add() {
		doAdd();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#edit()
	 */
	public final void edit() {
		doEdit();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#view()
	 */
	public final void view() {
		doView();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#refresh()
	 */
	public final void refresh() {
		doRefresh();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#erase()
	 */
	public final void erase() {
		doErase();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#permissions()
	 */
	public void setupPermissions() {
		doSetupPermissions();
	}

	/**
	 * ��������� �������� ����������� �� �������� ������ 
	 * 
	 * @param value	������������ ��������
	 */
	public void setParentPropertyName(String value) {
		this.parentPropertyName = value;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeControllerAdapter#getModel()
	 */
	public TreeModel getModel() {
		return treeModel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#getCustomUserActions()
	 */
	public CustomActionDescriptor[] getCustomUserActions() {
		return service != null ? CustomActionManagerLocator.locate().getCustomActions(service) : null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#getCurrentNodeId()
	 */
	public Serializable getCurrentNodeId() {
		return currentNodeId;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTreeControllerAdapter#getService()
	 */
	public BusinessObjectService getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TreeControllerAdapter#setTree(com.mg.framework.api.ui.widget.Tree)
	 */
	public void setTree(Tree tree) {
		this.treeWidget = tree;
	}

	/**
	 * ����� � ���������������� � ������ �� �������� ���������� �����, ���� � ������ ������ ����
	 * �� ������������ ���������������� �� ������ ����
	 * 
	 * @param primaryKey	�������� ���������� �����, ���� <code>null</code> �� ����� �� ������������
	 * @return	���������� ������� �� ���������� ���� ��� <code>null</code> ���� ������� �� ������
	 */
	public EntityTreeNode[] locate(Object primaryKey) {
		if (primaryKey != null) {
			EntityTreeNode[] path = ((EntityTreeNode) treeModel.getRootNode()).findPathToEntity((Serializable) primaryKey);
			treeWidget.setSelectionPath(path);
			return path;
		}
		return null;
	}

}