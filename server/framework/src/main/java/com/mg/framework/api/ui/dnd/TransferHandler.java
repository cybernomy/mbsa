/*
 * TransferHandler.java
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

import java.io.Serializable;
import java.util.EnumSet;

import com.mg.framework.api.ui.Widget;

/**
 * The <code>TransferHandler</code> is used to configure the behavior of a
 * specific widget during Drag & Drop operations. It is also responsible
 * for handling data import and export. Typically, each widget has its
 * own instance of <code>TransferHandler</code>.
 * 
 * @author Oleg V. Safonov
 * @version $Id: TransferHandler.java,v 1.2 2008/05/14 10:39:28 safonov Exp $
 */
public interface TransferHandler extends Serializable {

	/**
	 * Returns the Drag & Drop actions supported by the component provided when
	 * actingas drag source
	 * 
	 * @return	the Drag & Drop action supported by the component when acting as drag source
	 */
	EnumSet<DropAction> getSourceActions();
	
	/**
	 * Returns the Drag & Drop actions supported by the component provided when
	 * actingas drop target
	 * 
	 * @return	the Drag & Drop action supported by the component when acting as drop target
	 */
	EnumSet<DropAction> getTargetActions();
	
	/**
	 * Called by the Drag & Drop system to import transferable data into the target
	 * component. During a single Drag & Drop operation, this method is called on
	 * the drop target component when the drop occurs. <p></p> The following data
	 * flavors are supported: <table> <tr><td>Data
	 * Flavor</td><td>Type</td><td>Description</td></tr>
	 * <tr><td>{@link DataFlavor#DRAG_FLAVOR}</td><td>{@link com.mg.framework.api.ui.dnd.DnDData}
	 * </td><td>contains the data provided by the drag source component</td></tr>
	 * <tr><td>{@link DataFlavor#DROP_FLAVOR}</td><td>{@link com.mg.framework.api.ui.dnd.DnDData}
	 * </td><td>contains the data provided by the drop target component</td></tr>
	 * </table>
	 * 
	 * @param targetComponent	the Drag & Drop target component
	 * @param transferable		the transferable data container
	 * @return					<code>true</code>, if the import succeeded, false otherwise
	 */
	boolean importData(Widget targetComponent, Transferable transferable);
	
	/**
	 * Called by the Drag & Drop system to complete the export of transferable data
	 * from the drag source component. During a single Drag & Drop operation, this
	 * method is called on the drag source component when the drop occurs. <p></p>
	 * ifthe import of the transferable data on the drop target component was
	 * successful, the transferable data container contains the transferable data
	 * actually transferred between the drag source and the drop target component.
	 * Inthis case, the following data flavors are supported: <table> <tr><td>Data
	 * Flavor</td><td>Type</td><td>Description</td></tr>
	 * <tr><td>{@link DataFlavor#DRAG_FLAVOR}</td><td>{@link com.mg.framework.api.ui.dnd.DnDData}
	 * </td><td>contains the data provided by the drag source component</td></tr>
	 * <tr><td>{@link DataFlavor#DROP_FLAVOR}</td><td>{@link com.mg.framework.api.ui.dnd.DnDData}
	 * </td><td>contains the data provided by the drop target component</td></tr>
	 * </table> <p></p> If the import failed, the transferable data object is null,
	 * and the drop action provided is {@link DropAction#ACTION_NONE}.
	 * 
	 * @param sourceComponent	the Drag & Drop source component
	 * @param transferable		the transferable data container
	 * @param dropAction		the action executed on the transfered data
	 */
	void exportDone(Widget sourceComponent, Transferable transferable, DropAction dropAction);
	
}
