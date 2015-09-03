/**
 * CustomActionExecutionContext.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.BusinessObjectService;

/**
 * Контекст запуска настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionExecutionContext.java,v 1.1 2007/11/15 08:35:11 safonov Exp $
 */
public interface CustomActionExecutionContext {

	/**
	 * код действия
	 * 
	 * @return	код
	 */
	String getAction();
	
	/**
	 * бизнес-компонент для которого было вызвано действие
	 * 
	 * @return	бизнес-компонент
	 */
	BusinessObjectService getService();
	
	/**
	 * список идентификаторов отмеченных сущностей
	 * 
	 * @return	список идентификаторов
	 */
	Serializable[] getSelectedIdentifiers();
	
	/**
	 * список слушателей настраиваемого действия
	 * 
	 * @return	список слушателей
	 */
	CustomActionListener[] getListeners();
	
}
