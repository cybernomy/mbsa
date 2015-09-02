/*
 * DefaultPlainBrowseForm.java
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

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.CustomActionListener;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.service.CustomActionManagerLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.CustomActionExecutionContextImpl;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.BeanUtils;

/**
 * ����������� ����� ��� ����������� ������� ������-����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultPlainBrowseForm.java,v 1.15 2009/02/09 14:29:39 safonov Exp $
 */
public class DefaultPlainBrowseForm extends AbstractSearchForm implements MaintenanceBrowseForm, MasterModelListener {
	/**
	 * ������������ �������
	 */
	protected final String TABLE_WIDGET = "table";
	private DefaultRestrictionForm restrictionForm = null;
	protected MaintenanceTableController table;
	protected AttributeMap uiProperties = new LocalDataTransferObject();
	private boolean modalFlag;
	protected CustomActionListener customActionListener = null;
	
	/**
	 * ������-���������
	 */
	protected DataBusinessObjectService<PersistentObject, Serializable> service;
	
	/**
	 * ������������ ����� �������
	 */
	protected String restrictionFormName = null;

	public DefaultPlainBrowseForm() {
		table = createTableController();
	}
	
	/**
	 * �������� ����������� �������, � ������� ���������� ������� ����������� ����������
	 * 
	 * @return	���������� �������
	 */
	protected MaintenanceTableController createTableController() {
		MaintenanceTableController result = new MaintenanceTableController(this.uiProperties);
		result.addMasterModelListener(this);
		return result;
	}
	
	/**
	 * �������� ����� �����������, � ������� ���������� ������� ����� �� ����� {@link #restrictionFormName}
	 * 
	 * @return	����� �����������
	 */
	protected RestrictionForm getRestrictionForm() {
		if (restrictionFormName != null && restrictionForm == null) {
			restrictionForm = (DefaultRestrictionForm) UIProducer.produceForm(restrictionFormName);
			table.setRestrictionForm(restrictionForm);
			restrictionForm.addOkActionListener(new FormActionListener() {
				public void actionPerformed(FormEvent event) {
					runForm();
				}
			});
		}
		return restrictionForm;
	}
	
	/**
	 * ���������� ������ ����� �� ��������� ������������ � ��������, �������� ���������������
	 * � ������ ����������
	 * 
	 * @return	������ �����
	 */
	protected Set<String> getDefaultFieldsSet() {
		return new LinkedHashSet<String>();
	}
	
	/**
	 * ���������� ������ ����� ������������ � ��������
	 * 
	 * @return
	 */
	protected final Set<String> getFieldsSet() {
		//TODO implement
		return getDefaultFieldsSet();
	}

	/**
	 * ���������� ����� ������� ��� ��������� ������� ������� ��������
	 * 
	 * @return
	 */
	protected String createQueryText() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		PersistentObject[] items = new PersistentObject[keys.length];
		int index = 0;
		for (Serializable key : keys)
			items[index++] = service.load(key);
		return items;
	}

	/**
	 * �������� ������ ������� ��� ��������, ���������� �������������� � ������ ����������
	 * 
	 * @return	������ �������
	 */
	protected MaintenanceTableModel createModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}
	
	/**
	 * �������� ������ �������
	 *
	 */
	protected void loadTableModel() {
		table.getModel().load();

		boolean selectFirstRow = true;
		if (getTargetEntity() != null)
			selectFirstRow = table.locate(BeanUtils.getIdentifierProperty(getTargetEntity())) == -1;
		if (selectFirstRow)
			((Table) view.getWidget(TABLE_WIDGET)).setRowSelectionInterval(0, 0);
	}
	
	/**
	 * �������� ��������� ������� �� ������������� �������� ������������
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
					table.refresh();
			}

		};
		return customActionListener;
	}
	
	private void runForm() {
		super.run(modalFlag);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (service == null)
			throw new IllegalStateException("Service can't be null");

		table.initController(service, createModel());
		super.doOnRun();
		loadTableModel();

		setupFocus();
	}

	protected void setupFocus() {
		view.getWidget(TABLE_WIDGET).requestFocus();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#run(boolean)
	 */
	@Override
	public void run(boolean modal) {
		//������� ��������� ����������� �����, ���������� ����� ������� ������, � ������ ����� ���
		//��������� ����� ��������, �������������� run �.�. ������ ��������� ���� ������� ������ doOnRun
		modalFlag = modal;
		RestrictionForm restForm = getRestrictionForm();
		if (restForm != null && restForm.isShowOnEnter())
			restForm.execute();
		else
			runForm();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService<PersistentObject, Serializable> service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
	}

	/**
	 * ���������� ������� ������������� �������� ������������
	 * 
	 * @param event	�������
	 */
	public void onActionExecuteCustomUserAction(WidgetEvent event) {
		Serializable[] selectedIdentifiers = null;
		if (table.getModel() instanceof MaintenanceTableModel)
			selectedIdentifiers = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		CustomActionExecutionContextImpl context = new CustomActionExecutionContextImpl(event.getActionCommand()
				, service, selectedIdentifiers, getCustomActionListener());
		CustomActionManagerLocator.locate().executeAction(context);
	}

	/**
	 * ���������� �������� ��������
	 * 
	 * @param event
	 */
	public void onActionAddEntity(WidgetEvent event) {
		table.add();
	}

	/**
	 * ���������� ��������� ��������
	 * 
	 * @param event
	 */
	public void onActionEditEntity(WidgetEvent event) {
		table.edit();
	}

	/**
	 * ���������� �������� ��������
	 * 
	 * @param event
	 */
	public void onActionEraseEntity(WidgetEvent event) {
		table.erase();
	}

	/**
	 * ���������� ��������� ��������
	 * 
	 * @param event
	 */
	public void onActionViewEntity(WidgetEvent event) {
		table.view();
	}

	/**
	 * ���������� ����������� ��������
	 * 
	 * @param event
	 */
	public void onActionCloneEntity(WidgetEvent event) {
		table.clone(false);
	}

	/**
	 * ���������� ����������� �������� � ��������
	 * 
	 * @param event
	 */
	public void onActionDeepCloneEntity(WidgetEvent event) {
		table.clone(true);
	}

	/**
	 * ���������� ���������� �������� ���������
	 * 
	 * @param event
	 */
	public void onActionRefreshBrowse(WidgetEvent event) {
		table.refresh();
	}

	/**
	 * ���������� ��������� ������� ������
	 * 
	 * @param event
	 */
	public void onActionSetupRestriction(WidgetEvent event) {
		table.setRestriction();
	}

	/**
	 * ���������� ������� ���������� �������
	 * 
	 * @param event
	 */
	public void onActionPrintReport(WidgetEvent event) {
		table.print();
	}

	/**
	 * ���������� ��������� �������
	 * 
	 * @param event
	 */
	public void onActionSetupTable(WidgetEvent event) {
		table.setup();
	}

}
