/*
 * CurrencySearchForm.java
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

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.mfreference.model.WeekCalendar;

/**
 * Форма поиска бизнес-компонента "Недельный календар"
 * 
 * @author leonova
 * @version $Id: WeekCalendarSearchForm.java,v 1.2 2009/02/09 11:58:46 safonov Exp $
 */
public class WeekCalendarSearchForm extends AbstractSearchForm {
	private final static String LOAD_DAY_CALENDAR_EJBQL = "from WeekCalendar"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"Code"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
	
	private DefaultTableController weekCalendarList;
	
	public WeekCalendarSearchForm() {
		weekCalendarList = new DefaultTableController(new DefaultEntityListTableModel<WeekCalendar>() {
			@Override
			protected void doLoad() {
				setEntityList(MiscUtils.convertUncheckedList(WeekCalendar.class, OrmTemplate.getInstance().find(LOAD_DAY_CALENDAR_EJBQL)), fieldList);
			}
		});
		weekCalendarList.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<?>) weekCalendarList.getModel()).getSelectedEntities();
	}

}
