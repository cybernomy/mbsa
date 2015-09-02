/*
 * MeasureSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.MeasureServiceLocal;

/**
 * @author leonova
 * @version $Id: MeasureSearchHelp.java,v 1.3 2007/07/30 10:24:19 safonov Exp $
 * @deprecated
 */
@Deprecated
public class MeasureSearchHelp extends AbstractSearchHelp{

	@Override
	protected void doSearch() throws Exception {		
		MeasureSearchForm form = (MeasureSearchForm) UIProducer.produceForm("com/mg/merp/mfreference/resources/MeasureSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		//form.setSearchParams((AccPlan) getImportContextValue(getAccPlanContextName()), getAnalitikaLevel());
		form.run(UIUtils.isModalMode());
	}

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		MeasureServiceLocal service = (MeasureServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Measure");
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}
}
