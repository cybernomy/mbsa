/*
 * PersonAddressSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.model.IdentDoc;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Механизм поиска сущностей "Адреса проживания"
 *  
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PersonAddressSearchHelp.java,v 1.4 2008/03/27 13:28:31 alikaev Exp $
 */
public class PersonAddressSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/reference/resources/PersonAddressSearchForm.mfd.xml"; //$NON-NLS-1$
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PersonAddressSearchForm searchForm = (PersonAddressSearchForm) UIProducer.produceForm(FORM_NAME);
		IdentDoc identDoc = (IdentDoc) getImportContextValue("entity");
		NaturalPerson person = identDoc != null ? identDoc.getNaturalPerson() : null;
		searchForm.setPerson(person);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {"entity"};
	}
		
}
