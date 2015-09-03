/*
 * OriginalDocumentRest.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;

/**
 * Контроллер формы условий отбора бизнес-компонента "Оригиналы документов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentRest.java,v 1.1 2007/04/03 05:51:15 sharapov Exp $
 */
public class OriginalDocumentRest extends DefaultHierarhyRestrictionForm {
	
	// Fields
	
	private java.util.Date docDateFrom;
	private java.util.Date docDateTo;

	private java.util.Date createDateFrom;
	private java.util.Date createDateTo;
	
	// Methods

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		docDateFrom = null;
		docDateTo = null;
		createDateFrom = null;
		createDateTo = null;
	}
	
	// Property accessors
	
	/**
	 * @return the createDateFrom
	 */
	public java.util.Date getCreateDateFrom() {
		return createDateFrom;
	}
	
	/**
	 * @param createDateFrom the createDateFrom to set
	 */
	public void setCreateDateFrom(java.util.Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}
	
	/**
	 * @return the createDateTo
	 */
	public java.util.Date getCreateDateTo() {
		return createDateTo;
	}
	
	/**
	 * @param createDateTo the createDateTo to set
	 */
	public void setCreateDateTo(java.util.Date createDateTo) {
		this.createDateTo = createDateTo;
	}
	
	/**
	 * @return the docDateFrom
	 */
	public java.util.Date getDocDateFrom() {
		return docDateFrom;
	}
	
	/**
	 * @param docDateFrom the docDateFrom to set
	 */
	public void setDocDateFrom(java.util.Date docDateFrom) {
		this.docDateFrom = docDateFrom;
	}
	
	/**
	 * @return the docDateTo
	 */
	public java.util.Date getDocDateTo() {
		return docDateTo;
	}
	
	/**
	 * @param docDateTo the docDateTo to set
	 */
	public void setDocDateTo(java.util.Date docDateTo) {
		this.docDateTo = docDateTo;
	}

}
