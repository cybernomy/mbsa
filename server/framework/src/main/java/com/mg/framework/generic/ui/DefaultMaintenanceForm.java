/*
 * DefaultMaintenanceForm.java
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.ui.MaintenanceForm;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Button;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.MenuItem;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.UserActionInterceptorManagerLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.ui.MaintenanceConversationSessionImpl;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация стандартной формы поддержки для бизнес-компонента. Выполняются стандартные интерактивные операции
 * над объектом-сущностью бизнес-компонента создать, изменить, просмотреть.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMaintenanceForm.java,v 1.27 2009/02/04 10:15:49 safonov Exp $
 */
public class DefaultMaintenanceForm extends AbstractForm implements MaintenanceForm {
	/**
	 * наименование кнопки "Отмена"
	 */
	public static final String CANCEL_BUTTON_NAME = "CancelButton";

	/**
	 * наименование кнопки "Ок"
	 */
	public static final String OK_BUTTON_NAME = "OkButton";

	private List<MaintenanceFormActionListener> actionListeners = new ArrayList<MaintenanceFormActionListener>();
	private List<MasterModelListener> modelListeners = new ArrayList<MasterModelListener>();
	private PersistentObject entity;
	private MaintenanceAction action;
	private DataBusinessObjectService<PersistentObject, Serializable> service;
	private MaintenanceConversationSession maintenanceSession;
	
	/**
	 * режим обновления сущности, по умолчанию обновление отключено
	 */
	private EnumSet<RefreshMode> refreshMode = EnumSet.noneOf(RefreshMode.class);
	
	/**
	 * признак редактирования сложного объекта (в форме редактирования находятся
	 * списки других объектов которые можно изменять), данные объекты создаются
	 * в два этапа, на первом этапе заполняется и становится персистентным объект-мастер,
	 * на втором этапе возможно создание объектов-деталей
	 */
	private boolean isMasterDetail = false;
	
	/**
	 * признак записи объекта мастера в постоянное хранилище
	 */
	private boolean isSaved = true;
	
	/**
	 * признак "замораживания" возможности редактирования для сложного объекта, при создании объекта,
	 * после функции "добавить" редактирование объекта мастера становится невозможным, применяется
	 * в объектах, для которых невозможно совместное изменение мастера и деталей
	 */
	private boolean isFreezeMaster = false;
	
	/**
	 * установка признака "только для чтения"
	 * 
	 * @see #doSetDependentReadOnly(boolean)
	 * 
	 * @param readOnly	признак "только для чтения"
	 */
	private void setDependentReadOnly(boolean readOnly) {
		doSetDependentReadOnly(readOnly);
	}
	
	private MaintenanceConversationSession getMaintenanceSession() {
		if (maintenanceSession == null)
			maintenanceSession = new MaintenanceConversationSessionImpl(service, action, view, entity);
		return maintenanceSession;
	}
	
	/**
	 * @return Returns the isMasterDetail.
	 */
	public boolean isMasterDetail() {
		return isMasterDetail;
	}

	/**
	 * @param isMasterDetail The isMasterDetail to set.
	 */
	public void setMasterDetail(boolean isMasterDetail) {
		this.isMasterDetail = isMasterDetail;
	}	
	
	/**
	 * получить признак "замораживания"
	 * 
	 * @see #isFreezeMaster
	 * 
	 * @return	признак "замораживания"
	 */
	public boolean isFreezeMaster() {
		return isFreezeMaster;
	}
	
	/**
	 * установить признак "замораживания"
	 * 
	 * @see #isFreezeMaster
	 * 
	 * @param isFreezeMaster
	 */
	public void setFreezeMaster(boolean isFreezeMaster) {
		this.isFreezeMaster = isFreezeMaster;
	}

	/**
	 * получить режим обновления сущности
	 * 
	 * @return the refreshMode режим обновления
	 */
	protected EnumSet<RefreshMode> getRefreshMode() {
		return refreshMode;
	}

	/**
	 * установить режим обновления сущности, в случае если при сохранении в хранилище происходит изменение
	 * сущности, например с помощью триггера СУБД, и требуется показать данные изменения в форме поддержки
	 * то необходимо установить соответствующий режим обновления
	 * 
	 * @see RefreshMode
	 * 
	 * @param refreshMode режим обновления
	 */
	protected void setRefreshMode(EnumSet<RefreshMode> refreshMode) {
		this.refreshMode = refreshMode;
	}

	/**
	 * событие на действие "добавить"
	 *
	 */
	protected void doOnAdd() {
		//если мастер-деталь, то изменим текст кнопки по смыслу действия,
		//сбросим флаг записи
		if (isMasterDetail) {
			isSaved = false;
			setDependentReadOnly(true);
			((Button) view.getWidget(OK_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.ADD_BUTTON_TEXT));
		}
	}

	/**
	 * установка в формах для сложных объектах (<code>isMasterDetail == true</code>) признака "только для чтения",
	 * как правило используется для предотвращения изменений свойств зависящих от объекта-мастера, например
	 * запретить создание объектов-деталей если объект-мастер еще не создан. Реализовано стандартное поведение:
	 * блокирование элемента {@link com.mg.framework.api.ui.widget.MaintenanceTable MaintenanceTable}. В потомках
	 * возможно переопределение стандартного поведения. 
	 * 
	 * @param readOnly	признак "только для чтения"
	 */
	protected void doSetDependentReadOnly(boolean readOnly) {
		for (Widget widget : view.getWidgets()) {
			if (widget instanceof MaintenanceTable) {
				widget.setReadOnly(readOnly);
				//https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4323
				MenuItem refreshMenuItem = widget.getPopupMenu().getMenuItem(MaintenanceTable.REFRESH_MENU_ITEM);
				if (refreshMenuItem != null)
					refreshMenuItem.setEnabled(!readOnly || readOnly && action != MaintenanceAction.ADD);
			}
		}
	}

	/**
	 * событие на действие "изменить"
	 *
	 */
	protected void doOnEdit() {
		
	}
	
	/**
	 * событие на действие "копировать"
	 *
	 */
	protected void doOnClone() {
		
	}
	
	/**
	 * событие на действие "просмотр"
	 *
	 */
	protected void doOnView() {
		if (isMasterDetail)
			setDependentReadOnly(true);
		((Button) view.getWidget(OK_BUTTON_NAME)).setEnabled(false);
		((Button) view.getWidget(CANCEL_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.CLOSE_BUTTON_TEXT));
	}
	
	/**
	 * событие на действие "сохранить"
	 *
	 */
	protected void doOnSave() {
		
	}
	
	/**
	 * событие на действие "отменить"
	 *
	 */
	protected void doOnCancel() {
		
	}

	/**
	 * получить текущее действие
	 * 
	 * @see com.mg.framework.api.ui.MaintenanceAction
	 * 
	 * @return	текущее действие
	 */
	protected MaintenanceAction getAction() {
		return action;
	}
	
	/**
	 * получить текущий объект-сущность
	 * 
	 * @return	объект-сущность
	 * @throws IllegalStateException если объект-сущность <code>null</code>
	 */
	protected PersistentObject getEntity() {
		if (entity == null)
			throw new IllegalStateException("Entity is null");
		return entity;
	}
	
	/**
	 * установка объекта-сущности
	 * 
	 * @param entity
	 */
	protected void setEntity(PersistentObject entity) {
		this.entity = entity;
	}
	
	/**
	 * получить сервис бизнес-компонента поддерживаемого данной формой
	 * 
	 * @return	сервис бизнес-компонента
	 */
	protected DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> getService() {
		if (service == null)
			throw new IllegalStateException("Service is null");
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnActionClose(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	protected void doOnActionClose(WidgetEvent event) {
		//проверка изменения модели при закрытии формы кнопкой Х или комбинацией Ctrl+F4
		if (EnumSet.of(MaintenanceAction.ADD, MaintenanceAction.EDIT, MaintenanceAction.CLONE).contains(action)
				&& hasModelChanges()) {
			Messages msg = Messages.getInstance();
			final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT), noButton = msg.getMessage(Messages.NO_BUTTON_TEXT);
			//при изменении вызываем диалог: Сохранить? Да, Нет, Отменить
			UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, null,
					msg.getMessage(Messages.CLOSE_MAINTENANCE_FORM_WARNING),
					yesButton, noButton,
					msg.getMessage(Messages.CANCEL_BUTTON_TEXT), new AlertListener() {

				public void alertClosing(String value) {
					if (yesButton.equals(value))
						//выполняем сохранение, создаем новое событе, т.к. данный метод ожидает кнопку Ok
						doSave(new WidgetEvent(view.getWidget(OK_BUTTON_NAME)));
					else if (noButton.equals(value))
						try {
							doCancel();
						} catch (Exception e) {
							//в случае ИС всеравно закроем форму, иначе нет никакого способа ее закрыть
							logger.error("cancel maintenance failed", e);
							close();
						}
				}

			});
		} else
			doCancel(); //посылаем событие отмены приводя поведение в соответствии с кнопкой "Отменить"
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		view.pack();
		super.doOnRun();
		switch (action) {
			case ADD:
				doOnAdd();
				break;
			case EDIT:
				doOnEdit();
				break;
			case CLONE:
				doOnClone();
				break;
			case VIEW:
				doOnView();
				break;
		}
		//отправим сообщение после инициализации widgets
		if (action != MaintenanceAction.ADD)
			fireMasterModelChange(new ModelChangeEvent(this, this.entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doGetFieldValue(java.lang.String)
	 */
	@Override
	protected Object doGetFieldValue(String name) throws NoSuchFieldException, IllegalAccessException {
		//пытаемся загрузить из полей объекта entity, затем из полей формы
		try {
			return entity.getAttribute(name);
		}
		catch (RuntimeException e) {
			return super.doGetFieldValue(name);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doGetFieldType(java.lang.String)
	 */
	@Override
	protected Class<?> doGetFieldType(String name) {
		try {
			return ReflectionUtils.getPropertyReflectionMetadata(service.getEntityClass(), name).getPropertyType();
		} catch (RuntimeException e) {
			return super.doGetFieldType(name);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doSetFieldValue(java.lang.String, java.lang.Object)
	 */
	@Override
	protected void doSetFieldValue(String name, Object value) throws NoSuchFieldException, IllegalAccessException {
		try {
			entity.setAttribute(name, value);
		}
		catch (RuntimeException e) {
			super.doSetFieldValue(name, value);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doGetFieldMetadata(java.lang.String)
	 */
	@Override
	protected FieldMetadata doGetFieldMetadata(String name) {
		try {
			return ApplicationDictionaryLocator.locate().getFieldMetadata(service.getEntityClass(), name);
		}
		catch (RuntimeException e) {
			return super.doGetFieldMetadata(name);
		}
	}
	
	/**
	 * перечитать значение объекта-сущности из хранилища
	 */
	protected void doRefreshModel() {
		PersistentManager pm = ServerUtils.getPersistentManager();
		if (pm.contains(entity))
			pm.refresh(entity);
		else
			entity = pm.find(ReflectionUtils.getEntityClass(entity), entity.getPrimaryKey());
	}
	
	/**
	 * выполнить сохранение
	 * 
	 * @param event	событие от элемента вызвавшего событие
	 */
	protected void doSave(WidgetEvent event) {
		doOnSave();
		if (isMasterDetail) {
			if (MaintenanceAction.CLONE.equals(action)) {
				//при копировании сложного объекта на основании isFreezeMaster принимаем решение
				//о редактировании объектов зависящих от деталей, при первом проходе
				//закрываем кнопку ОК, контроллеры наследники должны открыть нужные
				//детали на добавление
				entity = service.store(entity);
				//для отработки логики на СУБД
				ServerUtils.getPersistentManager().flush();
				if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_STORE))
					doRefreshModel();
				if (isFreezeMaster()) {
					Button okButton = (Button) event.getWidget();
					//XXX по состоянию кнопки определяем был ли уже один проход
					if (okButton.isEnabled())
						okButton.setEnabled(false);
					else {
						fireSaveAction(new MaintenanceFormEvent(this, entity));
						close();
					}
				} else {
					fireSaveAction(new MaintenanceFormEvent(this, entity));
					close();
				}
			} else {
				if (!isSaved) {
					//если мастер-деталь и не сохранялся, то сбрасываем данные в хранилище
					//переводим в режим стандартной обработки
					service.create(entity);
					//для отработки логики на СУБД
					ServerUtils.getPersistentManager().flush();
					if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_CREATE))
						doRefreshModel();
					isSaved = true;
					Button okButton = (Button) event.getWidget();
					if (isFreezeMaster)
						okButton.setEnabled(false);
					okButton.setText(Messages.getInstance().getMessage(Messages.OK_BUTTON_TEXT));
					//изменим на "Закрыть", т.к. сложный объект уже добавлен в бд, и отменить это действие нельзя
					((Button) view.getWidget(CANCEL_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.CLOSE_BUTTON_TEXT));
					//отправим событие о смене мастера, в сложных объектах существуют детали
					//которые необходимо загружать
					fireMasterModelChange(new ModelChangeEvent(this, this.entity));
					setDependentReadOnly(false);
				}
				else {
					//объект уже был добавлен
					entity = service.store(entity);
					//для отработки логики на СУБД
					ServerUtils.getPersistentManager().flush();
					if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_STORE))
						doRefreshModel();
					fireSaveAction(new MaintenanceFormEvent(this, entity));
					close();
				}
			}
		}
		else {
			//выполняем стандартные функции по закрытию формы
			switch (action) {
			case ADD:
				service.create(entity);
				//для отработки логики на СУБД
				ServerUtils.getPersistentManager().flush();
				if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_CREATE))
					doRefreshModel();
				break;
			case EDIT:
			case CLONE:
				entity = service.store(entity);
				//для отработки логики на СУБД
				ServerUtils.getPersistentManager().flush();
				if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_STORE))
					doRefreshModel();
				break;
			}
			fireSaveAction(new MaintenanceFormEvent(this, entity));
			close();
		}
	}
	
	/**
	 * выполнить отмену действий
	 *
	 */
	protected void doCancel() {
		doOnCancel();
		//в сложном объекте на этапе "создания сущности" если была вставка в сессию (isSaved == true)
		//посылаем событие записи даже на отмене, т.к. была операция вставки,
		//в остальных случаях посылаем событие отмены и обновляем сущность из базы для предотвращения
		//вставки отмененных данных (если сущность прокси, то сессия автоматически внесет изменения
		//тех полей сущности которые изменил пользователь в форме), заменили это действие операцией undo,
		//данная операция вне зависимости от состояния сущности приведет к ее восстановлению,
		//исключаем тем самым дополнительный запрос к БД и ситуацию когда сущность была изменена до показа
		//в форме поддержки и произведена отмена, в случае использования refresh были бы сброшены и
		//изменения произведенные до формы поддержки
		if (isMasterDetail && isSaved && action == MaintenanceAction.ADD)
			fireSaveAction(new MaintenanceFormEvent(this, entity));
		else if (action == MaintenanceAction.CLONE) {
			Button okButton = (Button) view.getWidget(OK_BUTTON_NAME);
			//XXX по состоянию кнопки определяем был ли уже один проход, если был, то запись сохраняли
			//и даже при отмене генерируем событие записи
			if (isFreezeMaster() && !okButton.isEnabled())
				fireSaveAction(new MaintenanceFormEvent(this, entity));
			else {
				//удаляем, т.к. при копировании сущность сначала создается, неоптимально,
				//но так реализовано копирование
				ServerUtils.getPersistentManager().remove(entity);
				fireCancelAction(new MaintenanceFormEvent(this, entity));
			}
		} else {
			/*PersistentManager pm = ServerUtils.getPersistentManager();
			if (pm.contains(entity))
				try {
					pm.refresh(entity);
				} catch (RuntimeException e) {
					//при обновлении возможны ИС, например если сущность еще не существует в базе
					logger.debug("refresh entity failed", e);
				}*/
			undoAll();
			fireCancelAction(new MaintenanceFormEvent(this, entity));
		}
		close();
	}
	
	/**
	 * событие срабатывающее перед сбросом модели в вид
	 *
	 */
	protected void doBeforeOutput() {
		UserActionInterceptorManagerLocator.locate().invokeBeforeOutputInterceptor(getMaintenanceSession());
	}
	
	/**
	 * событие срабатывающее после сброса значений из вида в модель
	 *
	 */
	protected void doAfterInput(boolean isSaveAction, boolean isCloseAction) {
		ValidationContext validationContext = new ValidationContext();
		UserActionInterceptorManagerLocator.locate().invokeAfterInputInterceptor(getMaintenanceSession(), validationContext, isSaveAction, isCloseAction);
		validationContext.validate();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doInvokeHandler(java.lang.String, java.lang.Object[])
	 */
	@Override
	protected Object doInvokeHandler(String handlerName, Object ... args) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		//проверка частного случая обработки кнопок "Сохранить" и "Отменить"
		boolean isWidget = args.length > 0 && args[0] instanceof WidgetEvent;
		String widgetName = null;
		if (isWidget)
			widgetName = ((WidgetEvent) args[0]).getWidget().getName();
		doAfterInput(OK_BUTTON_NAME.equals(widgetName), CANCEL_BUTTON_NAME.equals(widgetName));
		//вызов обработчика события
		Object result = super.doInvokeHandler(handlerName, args);
		//вызовем обработчик если форма не закрыта
		if (view.isVisible())
			doBeforeOutput();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceForm#execute(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.ui.MaintenanceAction)
	 */
	public void execute(final DataBusinessObjectService<PersistentObject, Serializable> service,
			final PersistentObject entity, final MaintenanceAction action) {
		this.service = service;
		this.action = action;
		this.entity = entity;
		doBeforeOutput();
		run(UIUtils.isModalMode());
		view.getWindow().setDefaultButton((Button) view.getWidget(OK_BUTTON_NAME));
	}
	
	/**
	 * добавить слушателя на событие смены модели формы поддержки
	 * 
	 * @param listener	слушатель
	 */
	public void addMasterModelListener(final MasterModelListener listener) {
		if (listener != null)
			this.modelListeners.add(listener);
	}

	/**
	 * удалить слушателя на событие смены модели формы поддержки
	 * 
	 * @param listener	слушатель
	 */
	public void removeMasterModelListener(final MasterModelListener listener) {
		this.modelListeners.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceForm#addMaintenanceFormActionListener(com.mg.framework.api.ui.MaintenanceFormActionListener)
	 */
	public void addMaintenanceFormActionListener(final MaintenanceFormActionListener listener) {
		if (listener != null)
			this.actionListeners.add(listener);
	}
	
	/*(non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceForm#removeMaintenanceFormActionListener(com.mg.framework.api.ui.MaintenanceFormActionListener)
	 */
	public void removeMaintenanceFormActionListener(final MaintenanceFormActionListener listener) {
		this.actionListeners.remove(listener);
	}
	
	/**
	 * отправка сообщения о событии формы поддержки "Выполнено"
	 * 
	 * @param event	событие
	 */
	public void fireSaveAction(MaintenanceFormEvent event) {
		for (MaintenanceFormActionListener listener : actionListeners)
			listener.performed(event);
	}
	
	/**
	 * отправка сообщения о событии формы поддержки "Отменено"
	 * 
	 * @param event	событие
	 */
	public void fireCancelAction(MaintenanceFormEvent event) {
		for (MaintenanceFormActionListener listener : actionListeners)
			listener.canceled(event);
	}
	
	/**
	 * отправка сообщения о смене модели формы поддержки, данное событие не срабатывает
	 * при функции "добавить" простого объекта, сообщение будет послано для сложного объекта
	 * только после того, как он станет персистентным, смотри {@link #isMasterDetail}
	 * 
	 * @param event	событие
	 */
	public void fireMasterModelChange(ModelChangeEvent event) {
		for(MasterModelListener listener : modelListeners)
			listener.masterChange(event);
	}

	/**
	 * обработчик события "Сохранить"
	 * 
	 * @param event	событие
	 */
	protected void onActionSave(WidgetEvent event) {
		doSave(event);
	}
	
	/**
	 * обработчик события "Отменить"
	 * 
	 * @param event	событие
	 */
	protected void onActionCancel(WidgetEvent event) {
		doCancel();
	}

	/**
	 * перечитать значение объекта-сущности из хранилища и обновить вид
	 *
	 */
	public void refreshModel() {
		doRefreshModel();
		view.flushModel();		
	}
	
}
