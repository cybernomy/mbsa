/*
 * PromotionTypeSearchHelp.java
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
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.discount.PromotionTypeServiceLocal;

/**
 * Поиcковик сущностей "Тип рекламного мероприятия"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionTypeSearchHelp.java,v 1.1 2007/10/30 14:15:02 sharapov Exp $
 */
public class PromotionTypeSearchHelp extends AbstractSearchHelp {

	private final static String SEARCH_FORM_NAME = "com/mg/merp/discount/resources/PromotionTypeSearchForm.mfd.xml"; //$NON-NLS-1$


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PromotionTypeSearchForm searchForm = (PromotionTypeSearchForm) UIProducer.produceForm(SEARCH_FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
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
		MaintenanceHelper.view((PromotionTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PromotionTypeServiceLocal.SERVICE_NAME), (Integer) entity.getPrimaryKey(), null, null);
	}

}
