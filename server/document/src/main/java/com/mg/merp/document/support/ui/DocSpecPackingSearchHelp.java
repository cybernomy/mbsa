/*
 * DocSpecPackingSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecPackage;

/**
 * Поисковик сущностей "упаковки товара позиции спецификации документа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocSpecPackingSearchHelp.java,v 1.2 2007/08/10 13:22:24 safonov Exp $
 */
public class DocSpecPackingSearchHelp extends AbstractSearchHelp {

	private final String SEARCH_FORM = "com/mg/merp/document/resources/DocSpecPackingSearchForm.mfd.xml"; //$NON-NLS-1$
	private final String DOCSPEC_IMPORT = "DocSpec"; //$NON-NLS-1$
	private final String DOCSPEC_PACKAGE_ID_IMPORT = "Id"; //$NON-NLS-1$
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		DocSpecPackingSearchForm searchForm = (DocSpecPackingSearchForm) UIProducer.produceForm(SEARCH_FORM);
		searchForm.addSearchHelpListener(this);
		DocSpec docSpec = (DocSpec) getImportContextValue(DOCSPEC_IMPORT);
		if(docSpec == null) {
			DocumentSpecPackage documentSpecPackage = ServerUtils.getPersistentManager().find(DocumentSpecPackage.class, getImportContextValue(DOCSPEC_PACKAGE_ID_IMPORT));
			if(documentSpecPackage != null)
				docSpec = documentSpecPackage.getDocSpec();
		}
		searchForm.execute(docSpec == null ? null : docSpec.getCatalog());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {DOCSPEC_IMPORT, DOCSPEC_PACKAGE_ID_IMPORT};
	}

}
