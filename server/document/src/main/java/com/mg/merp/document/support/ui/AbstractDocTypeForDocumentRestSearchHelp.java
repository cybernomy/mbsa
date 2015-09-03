/*
 * AbstractDocTypeForDocumentRestSearchHelp.java
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
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocumentKind;

/**
 * Абстрактный класс для SearchHelp типов документов. Используется в формах условий отбора
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AbstractDocTypeForDocumentRestSearchHelp.java,v 1.1 2008/02/12 08:10:11 alikaev Exp $
 */
public abstract class AbstractDocTypeForDocumentRestSearchHelp extends AbstractSearchHelp {

	protected abstract DocumentKind getDocumentKind();
	
	protected String docSection = "docSection";
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {docSection};
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		DocTypeSearchForm form = (DocTypeSearchForm) UIProducer.produceForm("com/mg/merp/document/resources/DocTypeSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		Integer docId = 0;
		DocSection documentSection = (DocSection) getImportContextValue(docSection);
		if (documentSection != null)
		docId = documentSection.getId();									
		if (docId != 0) 				
			form.setParam(docId, getDocumentKind());				
		form.run(UIUtils.isModalMode());
	}

}
