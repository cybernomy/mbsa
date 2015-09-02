/*
 * StaffListPositionWithStaffListUnitSearchHelp.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.StaffListPositionServiceLocal;
import com.mg.merp.personnelref.model.StaffListPosition;

/**
 * Поисковик сущностей "Должности в штатном расписании"
 * 
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionWithStaffListUnitSearchHelp.java,v 1.1 2007/07/10 07:34:05 sharapov Exp $
 */
public class StaffListPositionWithStaffListUnitSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/personnelref/resources/StaffListPositionSearchForm.mfd.xml"; //$NON-NLS-1$
	
	private final String STAFF_LIST_UNIT_EXPORT = "StaffListUnit"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		StaffListPositionSearchForm searchForm = (StaffListPositionSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.setStaffListId(getCurrentStaffListId());
		searchForm.run(UIUtils.isModalMode());
	}
	
	/**
	 * Получить идентификатор текущего штатного расписания
	 */
	private Integer getCurrentStaffListId() {
		return ((CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME)).getCurrentCalcPeriod().getStaffList().getId();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {STAFF_LIST_UNIT_EXPORT};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		setExportContextValue(STAFF_LIST_UNIT_EXPORT, ((StaffListPosition) event.getItems()[0]).getStaffListUnit());
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
		MaintenanceHelper.view((StaffListPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListPosition"), (Integer) entity.getPrimaryKey(), null, null); //$NON-NLS-1$
	}
		
}
