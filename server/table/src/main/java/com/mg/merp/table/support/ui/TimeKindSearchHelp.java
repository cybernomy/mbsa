/*
 * TimeKindSearchHelp.java
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
package com.mg.merp.table.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Механизм поиска сущностей "Тип времени"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeKindSearchHelp.java,v 1.3 2008/08/12 14:38:08 sharapov Exp $
 */
public class TimeKindSearchHelp extends AbstractSearchHelp {

	private final String SEARCH_FORM_NAME = "com/mg/merp/table/resources/TimeKindSearchForm.mfd.xml"; //$NON-NLS-1$
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		TimeKindSearchForm searchForm = (TimeKindSearchForm) UIProducer.produceForm(SEARCH_FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.setIsWholeDay(getIsWholeDay());
		searchForm.run(UIUtils.isModalMode());
	}
	
	/**
	 * Получить признак "Учитывать по дням"
	 */
	protected Boolean getIsWholeDay() {
		return null;
	}

}
