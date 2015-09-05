/*
 * MaintenanceConversationSessionImpl.java
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
package com.mg.framework.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Controller;
import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.Widget;

/**
 * Реализация диалоговой сессии поддержки бизнес-компонента
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceConversationSessionImpl.java,v 1.1 2006/10/26 13:29:01 safonov Exp $
 */
public class MaintenanceConversationSessionImpl implements MaintenanceConversationSession {
	private Map<String, Serializable> attributeMap = new HashMap<String, Serializable>();
	private PersistentObject entity;
	private MaintenanceAction maintenanceAction;
	private Controller cotroller;
	private View view;
	private DataBusinessObjectService service;

	public MaintenanceConversationSessionImpl(DataBusinessObjectService service, MaintenanceAction maintenanceAction, View view,
			PersistentObject entity) {
		this.service = service;
		this.maintenanceAction = maintenanceAction;
		this.entity = entity;
		this.cotroller = view.getController();
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getAttribute(java.lang.String)
	 */
	public Serializable getAttribute(String name) {
		return attributeMap.get(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getEntity()
	 */
	public PersistentObject getEntity() {
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getMaintenanceAction()
	 */
	public MaintenanceAction getMaintenanceAction() {
		return maintenanceAction;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getModelAttribute(java.lang.String)
	 */
	public Object getModelAttribute(String name) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		return cotroller.getFieldValue(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getService()
	 */
	public DataBusinessObjectService getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#getWidget(java.lang.String)
	 */
	public Widget getWidget(String name) {
		return view.getWidget(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#setAttribute(java.lang.String, java.io.Serializable)
	 */
	public void setAttribute(String name, Serializable value) {
		attributeMap.put(name, value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceConversationSession#setModelAttribute(java.lang.String, java.lang.Object)
	 */
	public void setModelAttribute(String name, Object value) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		cotroller.setFieldValue(name, value);
	}

}
