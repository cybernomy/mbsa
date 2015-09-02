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
 * ���������� ����������� ����� ��������� ��� ������-����������. ����������� ����������� ������������� ��������
 * ��� ��������-��������� ������-���������� �������, ��������, �����������.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMaintenanceForm.java,v 1.27 2009/02/04 10:15:49 safonov Exp $
 */
public class DefaultMaintenanceForm extends AbstractForm implements MaintenanceForm {
	/**
	 * ������������ ������ "������"
	 */
	public static final String CANCEL_BUTTON_NAME = "CancelButton";

	/**
	 * ������������ ������ "��"
	 */
	public static final String OK_BUTTON_NAME = "OkButton";

	private List<MaintenanceFormActionListener> actionListeners = new ArrayList<MaintenanceFormActionListener>();
	private List<MasterModelListener> modelListeners = new ArrayList<MasterModelListener>();
	private PersistentObject entity;
	private MaintenanceAction action;
	private DataBusinessObjectService<PersistentObject, Serializable> service;
	private MaintenanceConversationSession maintenanceSession;
	
	/**
	 * ����� ���������� ��������, �� ��������� ���������� ���������
	 */
	private EnumSet<RefreshMode> refreshMode = EnumSet.noneOf(RefreshMode.class);
	
	/**
	 * ������� �������������� �������� ������� (� ����� �������������� ���������
	 * ������ ������ �������� ������� ����� ��������), ������ ������� ���������
	 * � ��� �����, �� ������ ����� ����������� � ���������� ������������� ������-������,
	 * �� ������ ����� �������� �������� ��������-�������
	 */
	private boolean isMasterDetail = false;
	
	/**
	 * ������� ������ ������� ������� � ���������� ���������
	 */
	private boolean isSaved = true;
	
	/**
	 * ������� "�������������" ����������� �������������� ��� �������� �������, ��� �������� �������,
	 * ����� ������� "��������" �������������� ������� ������� ���������� �����������, �����������
	 * � ��������, ��� ������� ���������� ���������� ��������� ������� � �������
	 */
	private boolean isFreezeMaster = false;
	
	/**
	 * ��������� �������� "������ ��� ������"
	 * 
	 * @see #doSetDependentReadOnly(boolean)
	 * 
	 * @param readOnly	������� "������ ��� ������"
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
	 * �������� ������� "�������������"
	 * 
	 * @see #isFreezeMaster
	 * 
	 * @return	������� "�������������"
	 */
	public boolean isFreezeMaster() {
		return isFreezeMaster;
	}
	
	/**
	 * ���������� ������� "�������������"
	 * 
	 * @see #isFreezeMaster
	 * 
	 * @param isFreezeMaster
	 */
	public void setFreezeMaster(boolean isFreezeMaster) {
		this.isFreezeMaster = isFreezeMaster;
	}

	/**
	 * �������� ����� ���������� ��������
	 * 
	 * @return the refreshMode ����� ����������
	 */
	protected EnumSet<RefreshMode> getRefreshMode() {
		return refreshMode;
	}

	/**
	 * ���������� ����� ���������� ��������, � ������ ���� ��� ���������� � ��������� ���������� ���������
	 * ��������, �������� � ������� �������� ����, � ��������� �������� ������ ��������� � ����� ���������
	 * �� ���������� ���������� ��������������� ����� ����������
	 * 
	 * @see RefreshMode
	 * 
	 * @param refreshMode ����� ����������
	 */
	protected void setRefreshMode(EnumSet<RefreshMode> refreshMode) {
		this.refreshMode = refreshMode;
	}

	/**
	 * ������� �� �������� "��������"
	 *
	 */
	protected void doOnAdd() {
		//���� ������-������, �� ������� ����� ������ �� ������ ��������,
		//������� ���� ������
		if (isMasterDetail) {
			isSaved = false;
			setDependentReadOnly(true);
			((Button) view.getWidget(OK_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.ADD_BUTTON_TEXT));
		}
	}

	/**
	 * ��������� � ������ ��� ������� �������� (<code>isMasterDetail == true</code>) �������� "������ ��� ������",
	 * ��� ������� ������������ ��� �������������� ��������� ������� ��������� �� �������-�������, ��������
	 * ��������� �������� ��������-������� ���� ������-������ ��� �� ������. ����������� ����������� ���������:
	 * ������������ �������� {@link com.mg.framework.api.ui.widget.MaintenanceTable MaintenanceTable}. � ��������
	 * �������� ��������������� ������������ ���������. 
	 * 
	 * @param readOnly	������� "������ ��� ������"
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
	 * ������� �� �������� "��������"
	 *
	 */
	protected void doOnEdit() {
		
	}
	
	/**
	 * ������� �� �������� "����������"
	 *
	 */
	protected void doOnClone() {
		
	}
	
	/**
	 * ������� �� �������� "��������"
	 *
	 */
	protected void doOnView() {
		if (isMasterDetail)
			setDependentReadOnly(true);
		((Button) view.getWidget(OK_BUTTON_NAME)).setEnabled(false);
		((Button) view.getWidget(CANCEL_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.CLOSE_BUTTON_TEXT));
	}
	
	/**
	 * ������� �� �������� "���������"
	 *
	 */
	protected void doOnSave() {
		
	}
	
	/**
	 * ������� �� �������� "��������"
	 *
	 */
	protected void doOnCancel() {
		
	}

	/**
	 * �������� ������� ��������
	 * 
	 * @see com.mg.framework.api.ui.MaintenanceAction
	 * 
	 * @return	������� ��������
	 */
	protected MaintenanceAction getAction() {
		return action;
	}
	
	/**
	 * �������� ������� ������-��������
	 * 
	 * @return	������-��������
	 * @throws IllegalStateException ���� ������-�������� <code>null</code>
	 */
	protected PersistentObject getEntity() {
		if (entity == null)
			throw new IllegalStateException("Entity is null");
		return entity;
	}
	
	/**
	 * ��������� �������-��������
	 * 
	 * @param entity
	 */
	protected void setEntity(PersistentObject entity) {
		this.entity = entity;
	}
	
	/**
	 * �������� ������ ������-���������� ��������������� ������ ������
	 * 
	 * @return	������ ������-����������
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
		//�������� ��������� ������ ��� �������� ����� ������� � ��� ����������� Ctrl+F4
		if (EnumSet.of(MaintenanceAction.ADD, MaintenanceAction.EDIT, MaintenanceAction.CLONE).contains(action)
				&& hasModelChanges()) {
			Messages msg = Messages.getInstance();
			final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT), noButton = msg.getMessage(Messages.NO_BUTTON_TEXT);
			//��� ��������� �������� ������: ���������? ��, ���, ��������
			UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, null,
					msg.getMessage(Messages.CLOSE_MAINTENANCE_FORM_WARNING),
					yesButton, noButton,
					msg.getMessage(Messages.CANCEL_BUTTON_TEXT), new AlertListener() {

				public void alertClosing(String value) {
					if (yesButton.equals(value))
						//��������� ����������, ������� ����� ������, �.�. ������ ����� ������� ������ Ok
						doSave(new WidgetEvent(view.getWidget(OK_BUTTON_NAME)));
					else if (noButton.equals(value))
						try {
							doCancel();
						} catch (Exception e) {
							//� ������ �� �������� ������� �����, ����� ��� �������� ������� �� �������
							logger.error("cancel maintenance failed", e);
							close();
						}
				}

			});
		} else
			doCancel(); //�������� ������� ������ ������� ��������� � ������������ � ������� "��������"
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
		//�������� ��������� ����� ������������� widgets
		if (action != MaintenanceAction.ADD)
			fireMasterModelChange(new ModelChangeEvent(this, this.entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doGetFieldValue(java.lang.String)
	 */
	@Override
	protected Object doGetFieldValue(String name) throws NoSuchFieldException, IllegalAccessException {
		//�������� ��������� �� ����� ������� entity, ����� �� ����� �����
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
	 * ���������� �������� �������-�������� �� ���������
	 */
	protected void doRefreshModel() {
		PersistentManager pm = ServerUtils.getPersistentManager();
		if (pm.contains(entity))
			pm.refresh(entity);
		else
			entity = pm.find(ReflectionUtils.getEntityClass(entity), entity.getPrimaryKey());
	}
	
	/**
	 * ��������� ����������
	 * 
	 * @param event	������� �� �������� ���������� �������
	 */
	protected void doSave(WidgetEvent event) {
		doOnSave();
		if (isMasterDetail) {
			if (MaintenanceAction.CLONE.equals(action)) {
				//��� ����������� �������� ������� �� ��������� isFreezeMaster ��������� �������
				//� �������������� �������� ��������� �� �������, ��� ������ �������
				//��������� ������ ��, ����������� ���������� ������ ������� ������
				//������ �� ����������
				entity = service.store(entity);
				//��� ��������� ������ �� ����
				ServerUtils.getPersistentManager().flush();
				if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_STORE))
					doRefreshModel();
				if (isFreezeMaster()) {
					Button okButton = (Button) event.getWidget();
					//XXX �� ��������� ������ ���������� ��� �� ��� ���� ������
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
					//���� ������-������ � �� ����������, �� ���������� ������ � ���������
					//��������� � ����� ����������� ���������
					service.create(entity);
					//��� ��������� ������ �� ����
					ServerUtils.getPersistentManager().flush();
					if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_CREATE))
						doRefreshModel();
					isSaved = true;
					Button okButton = (Button) event.getWidget();
					if (isFreezeMaster)
						okButton.setEnabled(false);
					okButton.setText(Messages.getInstance().getMessage(Messages.OK_BUTTON_TEXT));
					//������� �� "�������", �.�. ������� ������ ��� �������� � ��, � �������� ��� �������� ������
					((Button) view.getWidget(CANCEL_BUTTON_NAME)).setText(Messages.getInstance().getMessage(Messages.CLOSE_BUTTON_TEXT));
					//�������� ������� � ����� �������, � ������� �������� ���������� ������
					//������� ���������� ���������
					fireMasterModelChange(new ModelChangeEvent(this, this.entity));
					setDependentReadOnly(false);
				}
				else {
					//������ ��� ��� ��������
					entity = service.store(entity);
					//��� ��������� ������ �� ����
					ServerUtils.getPersistentManager().flush();
					if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_STORE))
						doRefreshModel();
					fireSaveAction(new MaintenanceFormEvent(this, entity));
					close();
				}
			}
		}
		else {
			//��������� ����������� ������� �� �������� �����
			switch (action) {
			case ADD:
				service.create(entity);
				//��� ��������� ������ �� ����
				ServerUtils.getPersistentManager().flush();
				if (refreshMode != null && refreshMode.contains(RefreshMode.AFTER_CREATE))
					doRefreshModel();
				break;
			case EDIT:
			case CLONE:
				entity = service.store(entity);
				//��� ��������� ������ �� ����
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
	 * ��������� ������ ��������
	 *
	 */
	protected void doCancel() {
		doOnCancel();
		//� ������� ������� �� ����� "�������� ��������" ���� ���� ������� � ������ (isSaved == true)
		//�������� ������� ������ ���� �� ������, �.�. ���� �������� �������,
		//� ��������� ������� �������� ������� ������ � ��������� �������� �� ���� ��� ��������������
		//������� ���������� ������ (���� �������� ������, �� ������ ������������� ������ ���������
		//��� ����� �������� ������� ������� ������������ � �����), �������� ��� �������� ��������� undo,
		//������ �������� ��� ����������� �� ��������� �������� �������� � �� ��������������,
		//��������� ��� ����� �������������� ������ � �� � �������� ����� �������� ���� �������� �� ������
		//� ����� ��������� � ����������� ������, � ������ ������������� refresh ���� �� �������� �
		//��������� ������������� �� ����� ���������
		if (isMasterDetail && isSaved && action == MaintenanceAction.ADD)
			fireSaveAction(new MaintenanceFormEvent(this, entity));
		else if (action == MaintenanceAction.CLONE) {
			Button okButton = (Button) view.getWidget(OK_BUTTON_NAME);
			//XXX �� ��������� ������ ���������� ��� �� ��� ���� ������, ���� ���, �� ������ ���������
			//� ���� ��� ������ ���������� ������� ������
			if (isFreezeMaster() && !okButton.isEnabled())
				fireSaveAction(new MaintenanceFormEvent(this, entity));
			else {
				//�������, �.�. ��� ����������� �������� ������� ���������, ������������,
				//�� ��� ����������� �����������
				ServerUtils.getPersistentManager().remove(entity);
				fireCancelAction(new MaintenanceFormEvent(this, entity));
			}
		} else {
			/*PersistentManager pm = ServerUtils.getPersistentManager();
			if (pm.contains(entity))
				try {
					pm.refresh(entity);
				} catch (RuntimeException e) {
					//��� ���������� �������� ��, �������� ���� �������� ��� �� ���������� � ����
					logger.debug("refresh entity failed", e);
				}*/
			undoAll();
			fireCancelAction(new MaintenanceFormEvent(this, entity));
		}
		close();
	}
	
	/**
	 * ������� ������������� ����� ������� ������ � ���
	 *
	 */
	protected void doBeforeOutput() {
		UserActionInterceptorManagerLocator.locate().invokeBeforeOutputInterceptor(getMaintenanceSession());
	}
	
	/**
	 * ������� ������������� ����� ������ �������� �� ���� � ������
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
		//�������� �������� ������ ��������� ������ "���������" � "��������"
		boolean isWidget = args.length > 0 && args[0] instanceof WidgetEvent;
		String widgetName = null;
		if (isWidget)
			widgetName = ((WidgetEvent) args[0]).getWidget().getName();
		doAfterInput(OK_BUTTON_NAME.equals(widgetName), CANCEL_BUTTON_NAME.equals(widgetName));
		//����� ����������� �������
		Object result = super.doInvokeHandler(handlerName, args);
		//������� ���������� ���� ����� �� �������
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
	 * �������� ��������� �� ������� ����� ������ ����� ���������
	 * 
	 * @param listener	���������
	 */
	public void addMasterModelListener(final MasterModelListener listener) {
		if (listener != null)
			this.modelListeners.add(listener);
	}

	/**
	 * ������� ��������� �� ������� ����� ������ ����� ���������
	 * 
	 * @param listener	���������
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
	 * �������� ��������� � ������� ����� ��������� "���������"
	 * 
	 * @param event	�������
	 */
	public void fireSaveAction(MaintenanceFormEvent event) {
		for (MaintenanceFormActionListener listener : actionListeners)
			listener.performed(event);
	}
	
	/**
	 * �������� ��������� � ������� ����� ��������� "��������"
	 * 
	 * @param event	�������
	 */
	public void fireCancelAction(MaintenanceFormEvent event) {
		for (MaintenanceFormActionListener listener : actionListeners)
			listener.canceled(event);
	}
	
	/**
	 * �������� ��������� � ����� ������ ����� ���������, ������ ������� �� �����������
	 * ��� ������� "��������" �������� �������, ��������� ����� ������� ��� �������� �������
	 * ������ ����� ����, ��� �� ������ �������������, ������ {@link #isMasterDetail}
	 * 
	 * @param event	�������
	 */
	public void fireMasterModelChange(ModelChangeEvent event) {
		for(MasterModelListener listener : modelListeners)
			listener.masterChange(event);
	}

	/**
	 * ���������� ������� "���������"
	 * 
	 * @param event	�������
	 */
	protected void onActionSave(WidgetEvent event) {
		doSave(event);
	}
	
	/**
	 * ���������� ������� "��������"
	 * 
	 * @param event	�������
	 */
	protected void onActionCancel(WidgetEvent event) {
		doCancel();
	}

	/**
	 * ���������� �������� �������-�������� �� ��������� � �������� ���
	 *
	 */
	public void refreshModel() {
		doRefreshModel();
		view.flushModel();		
	}
	
}
