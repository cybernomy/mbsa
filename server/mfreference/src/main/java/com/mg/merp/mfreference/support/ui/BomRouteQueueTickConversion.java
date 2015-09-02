/*
 * BomRouteQueueTickConversion.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.merp.mfreference.support.ui.TimeTickConversion;
import com.mg.merp.reference.model.Measure;

/**
 * ����������� �������� QueueTime ������� JobRoute
 * 
 * @author Oleg V. Safonov
 * @version $Id: BomRouteQueueTickConversion.java,v 1.1 2006/07/07 10:20:33 leonova Exp $
 */
public class BomRouteQueueTickConversion extends TimeTickConversion {

	/**
	 * Move time UM
	 */
	private static final String QUEUE_TIME_UM = "QueueTimeUM"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractConversionRoutine#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {QUEUE_TIME_UM};
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.support.ui.TimeTickConversion#getTimeUM()
	 */
	@Override
	protected Measure getTimeUM() {
		return (Measure) getImportContextValue(QUEUE_TIME_UM);
	}

}
