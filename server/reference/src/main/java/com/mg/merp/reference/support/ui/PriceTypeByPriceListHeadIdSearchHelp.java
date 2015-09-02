/*
 * PriceTypeByPriceListHeadIdSearchHelp.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.PriceTypeServiceLocal;
import com.mg.merp.reference.model.PriceListHead;

/**
 * Поиск типов цен из списка связей с прайс-листом
 * 
 * @author Artem V. Sharapov
 * @version $Id: PriceTypeByPriceListHeadIdSearchHelp.java,v 1.1 2008/10/13 05:50:58 sharapov Exp $
 */
public abstract class PriceTypeByPriceListHeadIdSearchHelp extends AbstractSearchHelp {

	private static final String SEARCH_HELP_FORM = "com/mg/merp/reference/resources/PriceTypeSearchForm.mfd.xml";
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {getPriceListHeadIdAttribute()};
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {		
		PriceTypeSearchForm searchForm = (PriceTypeSearchForm) UIProducer.produceForm(SEARCH_HELP_FORM);
		searchForm.addSearchHelpListener(this);		
		searchForm.setPriceListHead(findPriceListHead((Integer) getImportContextValue(getPriceListHeadIdAttribute())));
		searchForm.run(UIUtils.isModalMode());
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
	
	/**
	 * Получть наименование аттрибута, содержащего идентификатор заголовка прайс-листа
	 * @return наименование аттрибута
	 */
	protected abstract String getPriceListHeadIdAttribute();
	
	private PriceListHead findPriceListHead(Integer primaryKey) {
		return primaryKey != null ? ServerUtils.getPersistentManager().find(PriceListHead.class, primaryKey) : null;
	}
	
}
