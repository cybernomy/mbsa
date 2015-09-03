/*
 * SaleBookModelSearchHelp.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.merp.account.model.SaleBook;

/**
 * Механизм поиска образцов "Книги продаж"
 * 
 * @author Artem V. Sharapov
 * @version $Id: SaleBookModelSearchHelp.java,v 1.1 2009/03/16 14:27:22 sharapov Exp $
 */
public class SaleBookModelSearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		SaleBookModelSearchForm searchForm = new SaleBookModelSearchForm();
		fireSearchPerformed(new SearchHelpEvent(searchForm, searchForm.getSearchedEntities()));
	}
	
	private class SaleBookModelSearchForm extends AbstractSearchForm {

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
		 */
		@Override
		protected PersistentObject[] getSearchedEntities() {
			return new PersistentObject[] {new SaleBook()};
		}
		
	}

}
