/*
 * PriceTypeBoundedSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import java.io.Serializable;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.PriceTypeServiceLocal;
import com.mg.merp.reference.model.PriceListHead;

/**
 * Поиск типов цен из списка связей с прайс-листом
 * 
 * @author Oleg V. Safonov
 * @version $Id: PriceTypeBoundedSearchHelp.java,v 1.1 2007/05/10 07:31:13 safonov Exp $
 */
public abstract class PriceTypeBoundedSearchHelp extends AbstractSearchHelp {
	private static final String SEARCH_HELP_FORM = "com/mg/merp/reference/resources/PriceTypeSearchForm.mfd.xml";

	protected abstract String getPriceHeadProperty();
	
	@Override
	protected String[] defineImportContext() {
		return new String[] {getPriceHeadProperty()};
	}
	@Override
	protected void doSearch() throws Exception {		
		PriceTypeSearchForm form = (PriceTypeSearchForm) UIProducer.produceForm(SEARCH_HELP_FORM);
		form.addSearchHelpListener(this);		
		form.setPriceListHead((PriceListHead)getImportContextValue(getPriceHeadProperty()));
		form.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		PriceTypeServiceLocal prType = (PriceTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Reference/PriceType");
		MaintenanceHelper.view(prType, (Serializable) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

}
