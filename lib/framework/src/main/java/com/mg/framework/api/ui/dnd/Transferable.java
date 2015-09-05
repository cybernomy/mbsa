/*
 * Transferable.java
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

/**
 * Can be used to provide data for a drag and drop transfer operation between widgets
 * 
 * @author Oleg V. Safonov
 * @version $Id: Transferable.java,v 1.2 2008/05/14 10:39:28 safonov Exp $
 */
public interface Transferable extends Serializable {

	/**
	 * Returns the data to be transferred represented as an object. The
	 * representationclass of the flavor defines the returned object's class.
	 * 
	 * @param flavor	the specified flavor for the data
	 * @return			the object representing the data
	 */
	DnDData getTransferData(DataFlavor flavor);

}
