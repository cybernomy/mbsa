/*
 * DocTypeRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.document.model.DocSection;

/**
 * Контроллер формы условий отбора типов документов
 * 
 * @author leonova
 * @version $Id: DocTypeRest.java,v 1.2 2006/10/17 12:31:41 leonova Exp $ 
 */
public class DocTypeRest extends DefaultRestrictionForm {

	@DataItemName("Document.DocType.Code")
	private String code = "";
	private DocSection docSection = null;


	@Override
	protected void doClearRestrictionItem() {
		this.code = "";
		this.docSection = null;
		
	}


	/**
	 * @return Returns the code.
	 */
	protected String getCode() {
		return code;
	}


	/**
	 * @return Returns the docSection.
	 */
	protected DocSection getDocSection() {
		return docSection;
	}



}
