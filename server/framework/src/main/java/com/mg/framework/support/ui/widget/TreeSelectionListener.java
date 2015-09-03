/*
 * TreeSelectionListener.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;
import java.util.EventListener;

import com.mg.framework.api.ui.TreeChangeEvent;


/**
 * Слушатель события выделения элемента дерева
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreeSelectionListener.java,v 1.2 2006/08/31 08:49:31 safonov Exp $
 */
public interface TreeSelectionListener extends EventListener, Serializable {
	
	/**
	 * происходит в момент выделения жлемента дерева
	 * 
	 * @param event	событие изменения дерева
	 */
	public void valueChanged(TreeChangeEvent event);
}
