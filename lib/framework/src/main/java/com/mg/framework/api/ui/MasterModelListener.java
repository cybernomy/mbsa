/*
 * MasterModelListener.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.EventListener;

/**
 * Слушатель события смены объекта мастера в модели
 * 
 * @author Oleg V. Safonov
 * @version $Id: MasterModelListener.java,v 1.2 2006/08/31 09:01:06 safonov Exp $
 */
public interface MasterModelListener extends EventListener, Serializable {
	
	/**
	 * вызов происходит в момент смены текущего объекта мастера в модели
	 * 
	 * @param event
	 */
	void masterChange(ModelChangeEvent event);
}
