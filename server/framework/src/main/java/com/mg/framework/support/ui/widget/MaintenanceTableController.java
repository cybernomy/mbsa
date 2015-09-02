/*
 * MaintenanceTableController.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.report.RptEngine;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.CustomActionDescriptor;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomActionManagerLocator;
import com.mg.framework.service.RptEngineLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * ���������� �������� ������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableController.java,v 1.35 2009/02/17 12:33:42 safonov Exp $
 */
@SuppressWarnings("deprecation")
public class MaintenanceTableController implements MaintenanceTableControllerAdapter, MasterModelListener, Serializable {
	@Deprecated
	private final BrowseCond cond;
	private final AttributeMap uiProperties;
	private List<MasterModelListener> modelListeners = new ArrayList<MasterModelListener>();
	private DataBusinessObjectService<PersistentObject, Serializable> service;
	private RestrictionForm restrictionForm = null;
	private MaintenanceTableModel tableModel;
	private MaintenanceTableModel tableModelLegacy;
	private String addForm, editForm, viewForm = null;
	private Table tableWidget = null;
	private Object newPrimaryKey = null;
	private boolean selectFirstRow = true;
	
	/**
	 * �����������
	 * 
	 * @param cond
	 * @param uiProperties
	 * 
	 * @deprecated
	 */
	@Deprecated
	public MaintenanceTableController(BrowseCond cond, AttributeMap uiProperties) {
		this.cond = cond;
		this.uiProperties = uiProperties;
	}

	/**
	 * �����������
	 * 
	 * @param uiProperties	���������������� �������� ������� ������������ ����� ������ �������
	 */
	public MaintenanceTableController(AttributeMap uiProperties) {
		this.cond = null;
		this.uiProperties = uiProperties;
	}

	/**
	 * ���������� ���������� ��������
	 *
	 */
	protected void doAdd() {
		MaintenanceHelper.add(service, uiProperties, addForm, new MaintenanceFormActionListener() {
			public void performed(MaintenanceFormEvent event) {
				newPrimaryKey = event.getEntity().getPrimaryKey();
				try {
					refresh();
				} finally {
					newPrimaryKey = null;
				}
			}

			public void canceled(MaintenanceFormEvent event) {
				//do nothing
			}
		});
	}
	
	/**
	 * ���������� ��������� ��������
	 *
	 */
	protected void doEdit() {
		Serializable[] keys = tableModel.getSelectedPrimaryKeys();
		//������������ ������ ���� ���������� ������
		if (keys.length != 1)
			return;
		MaintenanceHelper.edit(service, keys[0], editForm, new MaintenanceFormActionListener() {
			public void performed(MaintenanceFormEvent event) {
				refresh();
			}

			public void canceled(MaintenanceFormEvent event) {
				//do nothing
			}
		});
	}
	
	/**
	 * ���������� ��������� ��������
	 *
	 */
	protected void doView() {
		Serializable[] keys = tableModel.getSelectedPrimaryKeys();
		//������������ ������ ���� ���������� ������
		if (keys.length != 1)
			return;
		MaintenanceHelper.view(service, keys[0], viewForm, null);
	}
	
	protected void doClone(boolean deepClone) {
		Serializable[] keys = tableModel.getSelectedPrimaryKeys();
		//������������ ������ ���� ���������� ������
		if (keys.length != 1)
			return;
		MaintenanceHelper.clone(service, keys[0], deepClone, editForm,  new MaintenanceFormActionListener() {
			public void performed(MaintenanceFormEvent event) {
				newPrimaryKey = event.getEntity().getPrimaryKey();
				try {
					refresh();
				} finally {
					newPrimaryKey = null;
				}
			}

			public void canceled(MaintenanceFormEvent event) {
				//do nothing
			}
		});
	}
	
	/**
	 * ���������� �������� ��������
	 *
	 */
	protected void doErase() {
		final Serializable[] keys = tableModel.getSelectedPrimaryKeys();
		//���� ��� ���������� �� ������� ������, bug 4175
		if (keys.length == 0)
			return;
		Messages msg = Messages.getInstance();
		final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT);
		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(Messages.ERASE_ALERT_TITLE), msg.getMessage(Messages.ERASE_ALERT_QUESTION), yesButton, msg.getMessage(Messages.NO_BUTTON_TEXT), new AlertListener() {
			public void alertClosing(String value) {
				if (value.equals(yesButton)) {
					for (Serializable key : keys)
						service.erase(key);
					ServerUtils.getPersistentManager().flush();
					refresh();
				}
			}
		});
	}
	
	/**
	 * ���������� ���������� ������ ���������
	 *
	 */
	protected void doRefresh() {
		Object pkValue = newPrimaryKey;
		//���� ��� ������ ��������, �.�. �� ���� ���������� ��� �����������, �� �����
		//������� ���������� ������ � ������������ �������� ����������������� �� ���
		if (pkValue == null) {
			Object[] pkValues = tableModel.getSelectedPrimaryKeys();
			if (pkValues.length > 0)
				pkValue = pkValues[0];
		}
		getModel().load();
		//���� �� ����� ������ �� ��������� �� 1�
		if (locate(pkValue) == -1 && selectFirstRow && getModel().getRowCount() > 0)
			tableWidget.setRowSelectionInterval(0, 0);
			
	}

	/**
	 * ���������� ��������� �����������
	 *
	 */
	protected void doSetRestriction() {
		if (restrictionForm == null)
			return;
		
		//������ ���������� ����� ������� ������, ������ �� ������ ������ ��������� �������
		for (FormActionListener listener : restrictionForm.getOkActionListenerList())
			restrictionForm.removeOkActionListener(listener);
		restrictionForm.addOkActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
//				if (restrictionForm instanceof DefaultLegacyRestrictionForm && cond != null)
//					cond.browseCond = ((DefaultLegacyRestrictionForm) restrictionForm).getRestrictionItem();
				refresh();
			}
		});
		this.restrictionForm.execute();
	}

	/**
	 * ���������� ��������� �������
	 */
	protected void doSetup() {
		((SetupMaintenanceTableForm) ApplicationDictionaryLocator.locate().getWindow(SetupMaintenanceTableForm.FORM_NAME)).execute(tableModel);
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

	@SuppressWarnings("unchecked") //$NON-NLS-1$ $NON-NLS-2$
	@Deprecated
	public void initController(DataBusinessObjectService service) throws ApplicationException {
		this.service = service;
//		AttributeMetadata[] attrList = new AttributeMetadata[0];//service.loadMetadata().attr_metadata_list;
//		//������� legacy ���������� ��� ������ loadBrowse
//		//this.tableModelLegacy = new LegacyTableModelImpl(service, cond, attrList);
//		
//		List<TableColumnModel> columns = new ArrayList<TableColumnModel>();
//		for (int i = 0; i < attrList.length; i++)
//			if (!attrList[i].browse_caption.equals(""))
//				columns.add(new TableColumnModel(attrList[i]));
		TableColumnModel[] columnsArray = new TableColumnModel[0];//columns.toArray(new TableColumnModel[columns.size()]);
		this.tableModelLegacy.setColumns(columnsArray);

		this.tableModel = this.tableModelLegacy;
	}
	
	/**
	 * ������������� ��������
	 * 
	 * @param service	������-���������
	 * @param model		������ �������
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void initController(DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service, MaintenanceTableModel model) {
		this.service = (DataBusinessObjectService<PersistentObject, Serializable>) service;
		this.tableModel = model;
		this.tableModel.setService(service);
	}

	/**
	 * ������������� ��������
	 * 
	 * @param serviceName	��� ������-����������
	 * @param model			������ �������
	 */
	@SuppressWarnings("unchecked")
	public void initController(String serviceName, MaintenanceTableModel model) {
		this.service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(serviceName);
		this.tableModel = model;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setSelectedRows(int[])
	 */
	public void setSelectedRows(int[] rows) {
		tableModel.setSelectedRows(rows);
		Serializable[] keys = tableModel.getSelectedPrimaryKeys();
		//comment, https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4784 
		//if (keys.length == 0)
			//return;
		fireMasterModelChange(new ModelChangeEvent(this, keys.length == 0 ? null : keys[0]));
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#add()
	 */
	public final void add() {
		doAdd();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#edit()
	 */
	public final void edit() {
		doEdit();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#view()
	 */
	public final void view() {
		doView();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#erase()
	 */
	public final void erase() {
		doErase();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#clone(boolean)
	 */
	public void clone(boolean deepClone) {
		doClone(deepClone);
	}

	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#refresh()
	 */
	public final void refresh() {
		doRefresh();
	}
	
	/**
	 * ���������� ������� ����� ������� ������
	 * 
	 * @param event	������� ����� �������
	 */
	public void fireMasterChange(ModelChangeEvent event) {
		tableModel.setCurrentMaster(event.getModelKey());
		if (cond != null)
			cond.browseMasterKey = event.getModelKey();
		//���� ������ ������ �� �������� ���������� �� ������� ������ �� ������ ��� ���������� ������� �������
		//���� ������� �� �� ������ ����������, ��� ������ � ��� ���������
		if (event.getSource() instanceof MaintenanceTreeController) {
			if (restrictionForm == null || restrictionForm instanceof HierarchyRestrictionSupport && ((HierarchyRestrictionSupport) restrictionForm).isUseHierarchy()) {
				tableModel.load();//refresh(); prevent fire onRefresh event
				if (selectFirstRow && tableModel.getRowCount() > 0)
					tableWidget.setRowSelectionInterval(0, 0);
			}
		}
		else {
			tableModel.load();//refresh(); prevent fire onRefresh event
			if (selectFirstRow && tableModel.getRowCount() > 0)
				tableWidget.setRowSelectionInterval(0, 0);
		}
	}
	
	/**
	 * �������� ��������� �� ������� ��������� ��������� ������, ����� �����������
	 * �������� ��� �������� ����� ������-������, ��� �������� ������ ������� �������
	 * 
	 * @param listener	���������
	 */
	public void addMasterModelListener(MasterModelListener listener) {
		this.modelListeners.add(listener);
	}

	/**
	 * �������� ��������� �� ������� ��������� ��������� ������
	 * 
	 * @param listener	���������
	 */
	public void removeMasterModelListener(MasterModelListener listener) {
		this.modelListeners.remove(listener);
	}

	/**
	 * �������� ������ ���������� �� ������� ��������� ��������� ������
	 * 
	 * @return	������ ����������
	 */
	public MasterModelListener[] getMasterModelListeners() {
		return this.modelListeners.toArray(new MasterModelListener[this.modelListeners.size()]);
	}
	
	/**
	 * ��������� ������� ��������� ��������� ������
	 * 
	 * @param event	������� ��������� ��������� ������
	 */
	public void fireMasterModelChange(ModelChangeEvent event) {
		for(MasterModelListener listener : modelListeners)
			listener.masterChange(event);
	}
	
	/**
	 * ���������� ����� ������� ������
	 * 
	 * @param restrictionForm	����� ������� ������
	 */
	public void setRestrictionForm(RestrictionForm restrictionForm) {
		this.restrictionForm = restrictionForm;
		//������� ����� ���� "������� ������" ���� � ������ �� ������������ ������� ������
		if (tableWidget != null)
			UIUtils.setVisibleEnabledProperty(tableWidget.getPopupMenu().getMenuItem(MaintenanceTable.RESTRICTION_MENU_ITEM), restrictionForm != null);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.core.support.ui.IMaintenanceTableController#getCustomUserActions()
	 */
	public CustomActionDescriptor[] getCustomUserActions() throws ApplicationException {
		return service != null ? CustomActionManagerLocator.locate().getCustomActions(service) : null;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.core.support.ui.IMaintenanceTableController#setColumns(com.mg.merp.core.support.ui.TableColumnModel[])
	 */
	public void setColumns(TableColumnModel[] columns) {
		((MaintenanceTableModel) getModel()).setColumns(columns);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		fireMasterChange(event);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#setRestriction()
	 */
	public final void setRestriction() {
		doSetRestriction();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getModel()
	 */
	public TableModel getModel() {
		return tableModel;
	}

	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#print()
	 */
	public void print() {
		RptEngine re = RptEngineLocator.locate();
		RptProperties prop = re.createProperies();
		prop.setEntityIds(tableModel.getSelectedPrimaryKeys());
		prop.setBusinessService(service);
		prop.setLocale(ServerUtils.getUserLocale());
		re.runAndRenderReport(prop);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#getService()
	 */
	public BusinessObjectService getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableControllerAdapter#setup()
	 */
	public void setup() {
		doSetup();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setTable(com.mg.framework.api.ui.widget.Table)
	 */
	public void setTable(Table table) {
		this.tableWidget = table;
		//������� ����� ���� "������� ������" ���� � ������ �� ������������ ������� ������
		if (restrictionForm == null)
			UIUtils.setVisibleEnabledProperty(tableWidget.getPopupMenu().getMenuItem(MaintenanceTable.RESTRICTION_MENU_ITEM), false);
	}

	/**
	 * ����� � ���������������� � ������� �� �������� ���������� �����, ���� � ������ ������ ���
	 * �� ������������ ���������������� �� ������ ���
	 * 
	 * @param primaryKey	�������� ���������� �����, ���� <code>null</code> �� ����� �� ������������
	 * @return	���������� ����� ���������� ������� ��� <code>-1</code> ���� �� ������
	 */
	public int locate(Object primaryKey) {
		int result = -1;
		if (primaryKey != null) {
			int rowCount = tableModel.getRowCount();
			int row = 0;
			for (; row < rowCount; row++) {
				if (primaryKey.equals(tableModel.getPrimaryKey(row)))
					break;
			}
			if (row < rowCount) {
				tableWidget.setRowSelectionInterval(row, row);
				result = row;
			}
			
		}
		return result;
	}

	/**
	 * ��������� �������� ��������� ������� ���� ������� ��� ��������� ������� ����� �������,
	 * � ��������� ������� �� ��������� �������������� ���������������� (�������� ��� �������
	 * ���������� ������, ��� ���������������� ���������� ����� ������ �� ���������� �����)
	 * 
	 * @param selectFirstRow	���� <code>true</code> ����� ������������� ��������� ������� ���� 
	 */
	public void setSelectFirstRow(boolean selectFirstRow) {
		this.selectFirstRow = selectFirstRow;
	}

}