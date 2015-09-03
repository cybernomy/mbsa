/*
 * UniversalDocModelSearchHelp.java
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

import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.document.model.DocSection;

/**
 * Универсальный поиск образцов документов всех типов, использует в качестве SearchHelp
 * класс установленный в атрибуте DocSection.DocumentModelSearchHelp.
 * Содержит контекст импорта из следующих параметров: {@link com.mg.merp.document.model.DocSection DocSection}
 * - раздел документа, {@link com.mg.merp.core.model.Folder DocModelFolder} - папка образца (если <code>null</code> то
 * поиск будет производиться без учета папки)
 * 
 * @see com.mg.merp.document.model.DocSection
 * 
 * @author Oleg V. Safonov
 * @version $Id: UniversalDocModelSearchHelp.java,v 1.2 2006/11/02 15:52:48 safonov Exp $
 */
public class UniversalDocModelSearchHelp extends AbstractSearchHelp {
	private static final String DOC_MODEL_FOLDER = "DocModelFolder"; //$NON-NLS-1$
	private static final String DOC_SECTION = "DocSection"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		DocSection docSection = (DocSection) getImportContextValue(DOC_SECTION);
		if (docSection != null && docSection.getDocumentModelSearchHelp() != null) {
			SearchHelp searchHelp = SearchHelpProcessor.createSearch(docSection.getDocumentModelSearchHelp());
			searchHelp.addSearchHelpListener(this);
			Map<String, Object> context = new HashMap<String, Object>();
			context.put(DOC_SECTION, getImportContextValue(DOC_SECTION));
			context.put(DOC_MODEL_FOLDER, getImportContextValue(DOC_MODEL_FOLDER));
			searchHelp.setImportContext(context);
			searchHelp.search();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {DOC_SECTION, DOC_MODEL_FOLDER};
	}
	
}
