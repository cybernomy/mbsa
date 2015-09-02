/*
 * ExtraDiscountSerachHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.discount.ExtraDiscountServiceLocal;
import com.mg.merp.discount.model.Card;

/**
 * @author leonova
 * @version $Id: ExtraDiscountSearchHelp.java,v 1.2 2006/10/16 11:50:54 leonova Exp $
 */
public class ExtraDiscountSearchHelp extends AbstractSearchHelp {

	protected String card = "DiscountCard";
			
	@Override
	protected String[] defineImportContext() {
		return new String[] {card};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {		
		ExtraDiscountSearchForm form = (ExtraDiscountSearchForm) UIProducer.produceForm("com/mg/merp/discount/resources/ExtraDiscountSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.setCard((Card) getImportContextValue(card));
		form.run(UIUtils.isModalMode());
	}

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		ExtraDiscountServiceLocal service = (ExtraDiscountServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/discount/ExtraDiscount");
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}


}
