/*
 * FeeRefSerachHelp.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.salary.FeeRefServiceLocal;
import com.mg.merp.salary.model.FeeRef;

/**
 * Поисковик сущностей "Начисление/удержание"
 * Специализирован для БК "Образцы начислений/удержаний"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeRefSearchHelp.java,v 1.2 2007/07/09 08:33:47 sharapov Exp $
 */
public class FeeRefSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/salary/resources/FeeRefSearchForm.mfd.xml"; //$NON-NLS-1$
	
	private final String ROLL_KIND_EXPORT = "RollKind"; //$NON-NLS-1$
	private final String COSTS_ANL_1_EXPORT = "CostsAnl1"; //$NON-NLS-1$
	private final String COSTS_ANL_2_EXPORT = "CostsAnl2"; //$NON-NLS-1$
	private final String COSTS_ANL_3_EXPORT = "CostsAnl3"; //$NON-NLS-1$
	private final String COSTS_ANL_4_EXPORT = "CostsAnl4"; //$NON-NLS-1$
	private final String COSTS_ANL_5_EXPORT = "CostsAnl5"; //$NON-NLS-1$

		
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		FeeRefSearchForm searchForm = (FeeRefSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {ROLL_KIND_EXPORT, COSTS_ANL_1_EXPORT, COSTS_ANL_2_EXPORT, COSTS_ANL_3_EXPORT, COSTS_ANL_4_EXPORT, COSTS_ANL_5_EXPORT};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		FeeRef feeRef = (FeeRef) event.getItems()[0];
		if(feeRef != null) {
			setExportContextValue(ROLL_KIND_EXPORT, feeRef.getRollKind());
			setExportContextValue(COSTS_ANL_1_EXPORT, feeRef.getCostsAnl1());
			setExportContextValue(COSTS_ANL_2_EXPORT, feeRef.getCostsAnl2());
			setExportContextValue(COSTS_ANL_3_EXPORT, feeRef.getCostsAnl3());
			setExportContextValue(COSTS_ANL_4_EXPORT, feeRef.getCostsAnl4());
			setExportContextValue(COSTS_ANL_5_EXPORT, feeRef.getCostsAnl5());
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		MaintenanceHelper.view((FeeRefServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FeeRefServiceLocal.LOCAL_SERVICE_NAME), (Integer) entity.getPrimaryKey(), null, null);
	}

}
