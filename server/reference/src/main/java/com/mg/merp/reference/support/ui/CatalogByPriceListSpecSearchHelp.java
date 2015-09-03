/*
 * CatalogByPriceListSpecSearchHelp.java
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

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.merp.reference.model.Catalog;

/**
 * Механизм поиска сущности бизнес-компонента "Каталог" для спецификации прайс-листа
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogByPriceListSpecSearchHelp.java,v 1.1 2008/05/13 10:32:01 alikaev Exp $
 */
public class CatalogByPriceListSpecSearchHelp extends CatalogSearchHelp {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {"SName"};
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		Catalog catalog = (Catalog) event.getItems()[0];
		setExportContextValue("SName", catalog.getFullName());
	}
	
}
