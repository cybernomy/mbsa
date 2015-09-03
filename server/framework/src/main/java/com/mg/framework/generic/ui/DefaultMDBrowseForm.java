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
 * Реализация контроллера формы мастер-деталь
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMDBrowseForm.java,v 1.3 2008/12/23 09:34:18 safonov Exp $
 */
public class DefaultMDBrowseForm extends AbstractForm implements
		MaintenanceBrowseForm {
	/**
	 * таблица мастера
	 */
	protected MaintenanceTableController master;
	/**
	 * таблица детали
	 */
	protected MaintenanceTableController detail;
	/**
	 * атрибуты мастера
	 */
	protected AttributeMap masterUIProperties = new LocalDataTransferObject();
	/**
	 * атрибуты детали
	 */
	protected AttributeMap detailUIProperties = new LocalDataTransferObject();
	/**
	 * сервис бизнес-компонента мастера
	 */
	protected DataBusinessObjectService<PersistentObject, Serializable> masterService;
	/**
	 * сервис бизнес-компонента детали
	 */
	protected DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> detailService;

	public DefaultMDBrowseForm() {
		master = new MaintenanceTableController(masterUIProperties);
		detail = new MaintenanceTableController(detailUIProperties);
		master.addMasterModelListener(detail);
	}
	
	/**
	 * создание модели таблицы для браузера мастера, необходимо переопределять в классе наследнике
	 * 
	 * @return	модель таблицы
	 */
	protected MaintenanceTableModel createMasterModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}

	/**
	 * создание модели таблицы для браузера детали, необходимо переопределять в классе наследнике
	 * 
	 * @return	модель таблицы
	 */
	protected MaintenanceTableModel createDetailModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}

	/**
	 * загрузка модели таблицы мастера
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
	 * обработчик создания сущности
	 * 
	 * @param event
	 */
	public void onActionAddEntity(WidgetEvent event) {
		master.add();
	}

	/**
	 * обработчик изменения сущности
	 * 
	 * @param event
	 */
	public void onActionEditEntity(WidgetEvent event) {
		master.edit();
	}

	/**
	 * обработчик удаления сущности
	 * 
	 * @param event
	 */
	public void onActionEraseEntity(WidgetEvent event) {
		master.erase();
	}

	/**
	 * обработчик просмотра сущности
	 * 
	 * @param event
	 */
	public void onActionViewEntity(WidgetEvent event) {
		master.view();
	}

	/**
	 * обработчик копирования сущности
	 * 
	 * @param event
	 */
	public void onActionCloneEntity(WidgetEvent event) {
		master.clone(false);
	}

	/**
	 * обработчик копирования сущности с деталями
	 * 
	 * @param event
	 */
	public void onActionDeepCloneEntity(WidgetEvent event) {
		master.clone(true);
	}

	/**
	 * обработчик обновления браузера сущностей
	 * 
	 * @param event
	 */
	public void onActionRefreshBrowse(WidgetEvent event) {
		master.refresh();
	}

	/**
	 * обработчик установки условий отбора сущностей мастера
	 * 
	 * @param event
	 */
	public void onActionSetupRestriction(WidgetEvent event) {
		master.setRestriction();
	}

	/**
	 * обработчик запуска генератора отчетов сервиса мастера
	 * 
	 * @param event
	 */
	public void onActionPrintReport(WidgetEvent event) {
		master.print();
	}

	/**
	 * обработчик настройки таблицы мастера
	 * 
	 * @param event
	 */
	public void onActionSetupTable(WidgetEvent event) {
		master.setup();
	}

	/**
	 * обработчик создания сущности детали
	 * 
	 * @param event
	 */
	public void onActionAddDetailEntity(WidgetEvent event) {
		detail.add();
	}

	/**
	 * обработчик изменения сущности детали
	 * 
	 * @param event
	 */
	public void onActionEditDetailEntity(WidgetEvent event) {
		detail.edit();
	}

	/**
	 * обработчик удаления сущности детали
	 * 
	 * @param event
	 */
	public void onActionEraseDetailEntity(WidgetEvent event) {
		detail.erase();
	}

	/**
	 * обработчик просмотра сущности детали
	 * 
	 * @param event
	 */
	public void onActionViewDetailEntity(WidgetEvent event) {
		detail.view();
	}

	/**
	 * обработчик копирования сущности детали
	 * 
	 * @param event
	 */
	public void onActionCloneDetailEntity(WidgetEvent event) {
		detail.clone(false);
	}

	/**
	 * обработчик копирования сущности детали с деталями
	 * 
	 * @param event
	 */
	public void onActionDeepCloneDetailEntity(WidgetEvent event) {
		detail.clone(true);
	}

	/**
	 * обработчик обновления браузера сущностей деталей
	 * 
	 * @param event
	 */
	public void onActionRefreshDetailBrowse(WidgetEvent event) {
		detail.refresh();
	}

	/**
	 * обработчик установки условий отбора сущностей деталей
	 * 
	 * @param event
	 */
	public void onActionSetupRestrictionDetail(WidgetEvent event) {
		detail.setRestriction();
	}

	/**
	 * обработчик запуска генератора отчетов сервиса деталей
	 * 
	 * @param event
	 */
	public void onActionPrintReportDetail(WidgetEvent event) {
		detail.print();
	}

	/**
	 * обработчик настройки таблицы деталей
	 * 
	 * @param event
	 */
	public void onActionSetupTableDetail(WidgetEvent event) {
		detail.setup();
	}

}
