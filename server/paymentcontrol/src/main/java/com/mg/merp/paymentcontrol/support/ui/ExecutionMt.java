/*
 * ExecutionMt.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;

/**
 * Контроллер формы поддержки бизнес-компонента "Исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ExecutionMt.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class ExecutionMt extends DefaultMaintenanceForm implements MasterModelListener {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if(getAction() == MaintenanceAction.EDIT) 
			getExecutionService().checkForOperationPossibility((Execution) getEntity());
		
		super.doOnRun();

		if(((Execution) getEntity()).getApproved()) {
			view.getWidget("SumCur").setReadOnly(true); //$NON-NLS-1$
			view.getWidget("SumNat").setReadOnly(true); //$NON-NLS-1$
		}
	}
	
	private ExecutionServiceLocal getExecutionService() {
		return (ExecutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ExecutionServiceLocal.LOCAL_SERVICE_NAME);
	}

}
