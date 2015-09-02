/*
 * DefaultMDBrowseForm.java
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
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;

/**
 * ���������� ����������� ����� ������-������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMDBrowseForm.java,v 1.3 2008/12/23 09:34:18 safonov Exp $
 */
public class DefaultMDBrowseForm extends AbstractForm implements
		MaintenanceBrowseForm {
	/**
	 * ������� �������
	 */
	protected MaintenanceTableController master;
	/**
	 * ������� ������
	 */
	protected MaintenanceTableController detail;
	/**
	 * �������� �������
	 */
	protected AttributeMap masterUIProperties = new LocalDataTransferObject();
	/**
	 * �������� ������
	 */
	protected AttributeMap detailUIProperties = new LocalDataTransferObject();
	/**
	 * ������ ������-���������� �������
	 */
	protected DataBusinessObjectService<PersistentObject, Serializable> masterService;
	/**
	 * ������ ������-���������� ������
	 */
	protected DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> detailService;

	public DefaultMDBrowseForm() {
		master = new MaintenanceTableController(masterUIProperties);
		detail = new MaintenanceTableController(detailUIProperties);
		master.addMasterModelListener(detail);
	}
	
	/**
	 * �������� ������ ������� ��� �������� �������, ���������� �������������� � ������ ����������
	 * 
	 * @return	������ �������
	 */
	protected MaintenanceTableModel createMasterModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}

	/**
	 * �������� ������ ������� ��� �������� ������, ���������� �������������� � ������ ����������
	 * 
	 * @return	������ �������
	 */
	protected MaintenanceTableModel createDetailModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}

	/**
	 * �������� ������ ������� �������
	 *
	 */
	protected void loadMasterTableModel() {
		master.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (masterService == null || detailService == null)
			throw new IllegalStateException("Service can't be null");
		
		master.initController(masterService, createMasterModel());
		detail.initController(detailService, createDetailModel());
		
		super.doOnRun();
		
		loadMasterTableModel();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(
			DataBusinessObjectService<PersistentObject, Serializable> service) {
		masterService = service;
	}

	/**
	 * ���������� �������� ��������
	 * 
	 * @param event
	 */
	public void onActionAddEntity(WidgetEvent event) {
		master.add();
	}

	/**
	 * ���������� ��������� ��������
	 * 
	 * @param event
	 */
	public void onActionEditEntity(WidgetEvent event) {
		master.edit();
	}

	/**
	 * ���������� �������� ��������
	 * 
	 * @param event
	 */
	public void onActionEraseEntity(WidgetEvent event) {
		master.erase();
	}

	/**
	 * ���������� ��������� ��������
	 * 
	 * @param event
	 */
	public void onActionViewEntity(WidgetEvent event) {
		master.view();
	}

	/**
	 * ���������� ����������� ��������
	 * 
	 * @param event
	 */
	public void onActionCloneEntity(WidgetEvent event) {
		master.clone(false);
	}

	/**
	 * ���������� ����������� �������� � ��������
	 * 
	 * @param event
	 */
	public void onActionDeepCloneEntity(WidgetEvent event) {
		master.clone(true);
	}

	/**
	 * ���������� ���������� �������� ���������
	 * 
	 * @param event
	 */
	public void onActionRefreshBrowse(WidgetEvent event) {
		master.refresh();
	}

	/**
	 * ���������� ��������� ������� ������ ��������� �������
	 * 
	 * @param event
	 */
	public void onActionSetupRestriction(WidgetEvent event) {
		master.setRestriction();
	}

	/**
	 * ���������� ������� ���������� ������� ������� �������
	 * 
	 * @param event
	 */
	public void onActionPrintReport(WidgetEvent event) {
		master.print();
	}

	/**
	 * ���������� ��������� ������� �������
	 * 
	 * @param event
	 */
	public void onActionSetupTable(WidgetEvent event) {
		master.setup();
	}

	/**
	 * ���������� �������� �������� ������
	 * 
	 * @param event
	 */
	public void onActionAddDetailEntity(WidgetEvent event) {
		detail.add();
	}

	/**
	 * ���������� ��������� �������� ������
	 * 
	 * @param event
	 */
	public void onActionEditDetailEntity(WidgetEvent event) {
		detail.edit();
	}

	/**
	 * ���������� �������� �������� ������
	 * 
	 * @param event
	 */
	public void onActionEraseDetailEntity(WidgetEvent event) {
		detail.erase();
	}

	/**
	 * ���������� ��������� �������� ������
	 * 
	 * @param event
	 */
	public void onActionViewDetailEntity(WidgetEvent event) {
		detail.view();
	}

	/**
	 * ���������� ����������� �������� ������
	 * 
	 * @param event
	 */
	public void onActionCloneDetailEntity(WidgetEvent event) {
		detail.clone(false);
	}

	/**
	 * ���������� ����������� �������� ������ � ��������
	 * 
	 * @param event
	 */
	public void onActionDeepCloneDetailEntity(WidgetEvent event) {
		detail.clone(true);
	}

	/**
	 * ���������� ���������� �������� ��������� �������
	 * 
	 * @param event
	 */
	public void onActionRefreshDetailBrowse(WidgetEvent event) {
		detail.refresh();
	}

	/**
	 * ���������� ��������� ������� ������ ��������� �������
	 * 
	 * @param event
	 */
	public void onActionSetupRestrictionDetail(WidgetEvent event) {
		detail.setRestriction();
	}

	/**
	 * ���������� ������� ���������� ������� ������� �������
	 * 
	 * @param event
	 */
	public void onActionPrintReportDetail(WidgetEvent event) {
		detail.print();
	}

	/**
	 * ���������� ��������� ������� �������
	 * 
	 * @param event
	 */
	public void onActionSetupTableDetail(WidgetEvent event) {
		detail.setup();
	}

}
