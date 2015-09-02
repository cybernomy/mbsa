/*
 * StandartDocModelSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocSection;

/**
 * Поиск стандартных (унаследованных от {@link com.mg.merp.document.model.DocHeadModel DocHeadModel}) образов документов.
 * Содержит контекст импорта из следующих параметров: {@link com.mg.merp.document.model.DocSection DocSection}
 *  - раздел документа, {@link com.mg.merp.core.model.Folder DocModelFolder} - папка образца (если <code>null</code> то
 * поиск будет производиться без учета папки)
 * 
 * @author Oleg V. Safonov
 * @version $Id: StandartDocModelSearchHelp.java,v 1.3 2006/11/02 15:52:48 safonov Exp $
 */
public class StandartDocModelSearchHelp extends AbstractSearchHelp {
	private static final String SEARCH_FORM = "com/mg/merp/document/resources/StandartDocModelSearchForm.mfd.xml"; //$NON-NLS-1$
	private static final String DOC_MODEL_FOLDER = "DocModelFolder"; //$NON-NLS-1$
	private static final String DOC_SECTION = "DocSection"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		StandartDocModelSearchForm form = (StandartDocModelSearchForm) UIProducer.produceForm(SEARCH_FORM);
		form.addSearchHelpListener(this);
		form.setDocSection((DocSection) getImportContextValue(DOC_SECTION));
		form.setFolder((Folder) getImportContextValue(DOC_MODEL_FOLDER));
		form.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {DOC_SECTION, DOC_MODEL_FOLDER};
	}

}
