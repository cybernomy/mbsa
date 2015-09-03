/*
 * LiabilitySearchHelp.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;

/**
 * Поисковик сущностей "Обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: LiabilitySearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class LiabilitySearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		LiabilitySearchForm form = (LiabilitySearchForm) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/LiabilitySearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		form.run(UIUtils.isModalMode());
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		LiabilityServiceLocal liabilityService  = (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LiabilityServiceLocal.LOCAL_SERVICE_NAME);
		MaintenanceHelper.view(liabilityService, (Integer) entity.getPrimaryKey(), null, null);
	}
	
}
