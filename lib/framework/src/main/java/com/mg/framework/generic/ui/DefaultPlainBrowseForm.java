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
 * Стандартная форма для отображения списков бизнес-компонетов
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultPlainBrowseForm.java,v 1.15 2009/02/09 14:29:39 safonov Exp $
 */
public class DefaultPlainBrowseForm extends AbstractSearchForm implements MaintenanceBrowseForm, MasterModelListener {
	/**
	 * наименование таблицы
	 */
	protected final String TABLE_WIDGET = "table";
	private DefaultRestrictionForm restrictionForm = null;
	protected MaintenanceTableController table;
	protected AttributeMap uiProperties = new LocalDataTransferObject();
	private boolean modalFlag;
	protected CustomActionListener customActionListener = null;
	
	/**
	 * бизнес-компонент
	 */
	protected DataBusinessObjectService<PersistentObject, Serializable> service;
	
	/**
	 * наименование формы фильтра
	 */
	protected String restrictionFormName = null;

	public DefaultPlainBrowseForm() {
		table = createTableController();
	}
	
	/**
	 * создание контроллера таблицы, в базовой реализации создает стандартный контроллер
	 * 
	 * @return	контроллер таблицы
	 */
	protected MaintenanceTableController createTableController() {
		MaintenanceTableController result = new MaintenanceTableController(this.uiProperties);
		result.addMasterModelListener(this);
		return result;
	}
	
	/**
	 * создание формы ограничений, в базовой реализации создает форму по имени {@link #restrictionFormName}
	 * 
	 * @return	форма ограничений
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
	 * возвращает список полей по умолчанию отображаемых в браузере, возможно переопределение
	 * в классе наследнике
	 * 
	 * @return	список полей
	 */
	protected Set<String> getDefaultFieldsSet() {
		return new LinkedHashSet<String>();
	}
	
	/**
	 * возвращает список полей отображаемых в браузере
	 * 
	 * @return
	 */
	protected final Set<String> getFieldsSet() {
		//TODO implement
		return getDefaultFieldsSet();
	}

	/**
	 * возвращает текст запроса для получения выборки данного браузера
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
	 * создание модели таблицы для браузера, необходимо переопределять в классе наследнике
	 * 
	 * @return	модель таблицы
	 */
	protected MaintenanceTableModel createModel() {
		throw new UnsupportedOperationException("Must be override in descendants");
	}
	
	/**
	 * загрузка модели таблицы
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
		//обходим поведение стандартной формы, показываем форму условий отбора, и только после нее
		//запускаем форму браузера, переопределяем run т.к. нельзя прерывать стек вызовов метода doOnRun
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
	 * обработчик запуска настраиваемых действий пользователя
	 * 
	 * @param event	событие
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
	 * обработчик создания сущности
	 * 
	 * @param event
	 */
	public void onActionAddEntity(WidgetEvent event) {
		table.add();
	}

	/**
	 * обработчик изменения сущности
	 * 
	 * @param event
	 */
	public void onActionEditEntity(WidgetEvent event) {
		table.edit();
	}

	/**
	 * обработчик удаления сущности
	 * 
	 * @param event
	 */
	public void onActionEraseEntity(WidgetEvent event) {
		table.erase();
	}

	/**
	 * обработчик просмотра сущности
	 * 
	 * @param event
	 */
	public void onActionViewEntity(WidgetEvent event) {
		table.view();
	}

	/**
	 * обработчик копирования сущности
	 * 
	 * @param event
	 */
	public void onActionCloneEntity(WidgetEvent event) {
		table.clone(false);
	}

	/**
	 * обработчик копирования сущности с деталями
	 * 
	 * @param event
	 */
	public void onActionDeepCloneEntity(WidgetEvent event) {
		table.clone(true);
	}

	/**
	 * обработчик обновления браузера сущностей
	 * 
	 * @param event
	 */
	public void onActionRefreshBrowse(WidgetEvent event) {
		table.refresh();
	}

	/**
	 * обработчик установки условий отбора
	 * 
	 * @param event
	 */
	public void onActionSetupRestriction(WidgetEvent event) {
		table.setRestriction();
	}

	/**
	 * обработчик запуска генератора отчетов
	 * 
	 * @param event
	 */
	public void onActionPrintReport(WidgetEvent event) {
		table.print();
	}

	/**
	 * обработчик настройки таблицы
	 * 
	 * @param event
	 */
	public void onActionSetupTable(WidgetEvent event) {
		table.setup();
	}

}
