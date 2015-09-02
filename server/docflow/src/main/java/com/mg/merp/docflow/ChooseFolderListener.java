/*
 * ChooseFolderListener.java
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
package com.mg.merp.docflow;

import java.util.EventListener;

import com.mg.merp.core.model.Folder;

/**
 * Слушатель выбора папки назначения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ChooseFolderListener.java,v 1.1 2006/12/12 15:23:33 safonov Exp $
 */
public interface ChooseFolderListener extends EventListener {

	/**
	 * папка выбрана
	 * 
	 * @param folder	папка
	 */
	void performed(Folder folder);
	
	/**
	 * выбор папки отменен
	 *
	 */
	void canceled();	
	
}
