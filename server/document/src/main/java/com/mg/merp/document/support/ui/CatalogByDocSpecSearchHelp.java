/*
 * CatalogByDocSpecSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.support.ui.CatalogSearchHelp;

/**
 * Контроллер формы поиска бизнес-компонента "Каталог".
 * Устанавливает в спецификации атрибуты 'Единица измерения в основной ЕИ'
 * 'Единица измерения в дополнительной ЕИ'из выбранной каталожной позиции 
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogByDocSpecSearchHelp.java,v 1.1 2008/03/20 14:08:46 alikaev Exp $
 */
public class CatalogByDocSpecSearchHelp extends CatalogSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {"Measure1", "Measure2"}; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		Catalog catalog = (Catalog) event.getItems()[0];
		setExportContextValue("Measure1", catalog.getMeasure1()); //$NON-NLS-1$
		setExportContextValue("Measure2", catalog.getMeasure2()); //$NON-NLS-1$
	}
		
}
