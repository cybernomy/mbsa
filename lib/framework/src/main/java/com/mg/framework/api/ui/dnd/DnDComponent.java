/*
 * DnDComponent.java
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
package com.mg.framework.api.ui.dnd;

/**
 * Элемент пользователького интерфейса поддерживающий DnD
 * 
 * @author Oleg V. Safonov
 * @version $Id: DnDComponent.java,v 1.1 2007/08/16 13:48:06 safonov Exp $
 */
public interface DnDComponent {

	/**
	 * Sets the transfer handler instance used to configure the Drag & Drop behavior
	 * for this component and to handle data import / export.
	 * 
	 * @param transferHandler	an instance of TransferHandler
	 */
	void setTransferHandler(TransferHandler transferHandler);

	/**
	 * Returns the transfer handler
	 * 
	 * @return	the instance of TransferHandler
	 */
	TransferHandler getTransferHandler();
	
	/**
	 * Determines if Drag & Drop support is enabled or disabled
	 * 
	 * @param enabled	true, if Drag & Drop support should be enabled, false otherwise
	 */
	void setDragEnabled(boolean enabled);
	
	/**
	 * Determines if Drag & Drop support is enabled or disabled
	 * 
	 * @return	true, if Drag & Drop support is enabled, false otherwise
	 */
	boolean isDragEnabled();
	
}
