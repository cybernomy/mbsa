/*
 * BucketSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.mfreference.model.PlanningLevel;

/**
 * @author leonova
 * @version $Id: BucketSearchHelp.java,v 1.1 2006/12/21 12:49:12 leonova Exp $
 */
public abstract class BucketSearchHelp extends AbstractSearchHelp {

	protected String planningLevel = "PlanningLevel";
	
	protected abstract boolean isBegin();
	
	@Override
	protected String[] defineImportContext() {
		return new String[] {planningLevel};
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		BucketSearchForm form = (BucketSearchForm) UIProducer.produceForm("com/mg/merp/mfreference/resources/PlanningLevelBucketSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.setSearchParams((PlanningLevel)getImportContextValue(planningLevel), isBegin());
		form.run(UIUtils.isModalMode());

	}
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

}
