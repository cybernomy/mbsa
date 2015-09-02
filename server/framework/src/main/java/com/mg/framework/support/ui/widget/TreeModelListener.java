/*
 * TreeModelListener.java
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
package com.mg.framework.support.ui.widget;

import java.util.EventListener;

/**
 * слушатель модели дерева
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreeModelListener.java,v 1.3 2006/06/26 11:47:21 safonov Exp $
 */
public interface TreeModelListener extends EventListener {
	
	/**
	 * срабатывает в случае радикального изменения структуры модели дерева
	 *
	 */
	void treeStructureChanged();
}
