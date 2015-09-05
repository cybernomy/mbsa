/**
 * CustomActionExecutionContextImpl.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.ui.CustomActionExecutionContext;
import com.mg.framework.api.ui.CustomActionListener;

/**
 * Реализация контекста запуска настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionExecutionContextImpl.java,v 1.1 2007/11/15 08:44:38 safonov Exp $
 */
public class CustomActionExecutionContextImpl implements
		CustomActionExecutionContext {
	private String action;
	private BusinessObjectService service;
	private CustomActionListener listener;
	private Serializable[] selectedIdentifiers;
	
	/**
	 * конструктор
	 * 
	 * @param action	код действия
	 * @param service	бизнес-компонент
	 * @param selectedIdentifiers	список идентификаторов сущностей
	 * @param listener	слушатель действия
	 */
	public CustomActionExecutionContextImpl(String action,
			BusinessObjectService service, Serializable[] selectedIdentifiers, CustomActionListener listener) {
		super();
		this.action = action;
		this.service = service;
		this.selectedIdentifiers = selectedIdentifiers;
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionExecutionContext#getAction()
	 */
	public String getAction() {
		return action;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionExecutionContext#getListeners()
	 */
	public CustomActionListener[] getListeners() {
		return new CustomActionListener[] {listener};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionExecutionContext#getService()
	 */
	public BusinessObjectService getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionExecutionContext#getSelectedIdentifiers()
	 */
	public Serializable[] getSelectedIdentifiers() {
		return selectedIdentifiers;
	}

}
