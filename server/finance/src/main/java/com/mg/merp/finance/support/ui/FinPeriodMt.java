/*
 * FinPeriodMt.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceForm;

/**
 * Контроллер формы поддержки бизнес-компонента "Периоды финансового учета"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FinPeriodMt.java,v 1.1 2007/04/04 12:08:07 sharapov Exp $
 */
public class FinPeriodMt extends DefaultMaintenanceForm {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		super.doOnEdit();
		view.getWidget("DateFrom").setReadOnly(true); //$NON-NLS-1$
		view.getWidget("DateTo").setReadOnly(true); //$NON-NLS-1$
	}

}
