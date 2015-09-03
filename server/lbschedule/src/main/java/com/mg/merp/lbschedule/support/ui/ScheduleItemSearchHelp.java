/*
 * ScheduleItemSearchHelp.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.Schedule;

/**
 * Поисковик объектов-сущностей "Пунктов графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleItemSearchHelp.java,v 1.1 2007/04/17 12:51:41 sharapov Exp $
 */
public class ScheduleItemSearchHelp extends AbstractSearchHelp {

	private final String scheduleItemIdImportContext = "Id"; //$NON-NLS-1$
	private final String scheduleImportContext = "Schedule"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		ScheduleItemSearchForm form = (ScheduleItemSearchForm) UIProducer.produceForm("com/mg/merp/lbschedule/resources/ScheduleItemSearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		Integer itemId = (Integer) getImportContextValue(scheduleItemIdImportContext);
		Schedule schedule = (Schedule) getImportContextValue(scheduleImportContext);

		if(schedule == null) {
			Item item = ServerUtils.getPersistentManager().find(Item.class, itemId);
			schedule = item.getSchedule();
		}
		form.setSearchParams(itemId, schedule);
		form.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {scheduleItemIdImportContext, scheduleImportContext};
	}

}
