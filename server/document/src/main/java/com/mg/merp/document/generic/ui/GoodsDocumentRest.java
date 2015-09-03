/*
 * GoodsDocumentRest.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условия отбора товарных документов
 * 
 * @author leonova
 * @version $Id: GoodsDocumentRest.java,v 1.6 2006/12/20 07:45:09 leonova Exp $
 */
public class GoodsDocumentRest extends DocumentRest {

	private Catalog catalogName = null;
	private CatalogFolder catalogFolder = null;	
	@DataItemName("Document.DstStock")
	private Contractor dstStockCode = null;
	@DataItemName("Document.DstMol")
	private Contractor dstMolCode = null;
	@DataItemName("Document.StockSrc")
	private Contractor srcStockCode = null;	
	@DataItemName("Document.SrcMol")	
	private Contractor srcMolCode = null;	
	protected String[] contractorThroughKinds;

	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.catalogName = null;
		this.catalogFolder = null;
		this.dstStockCode = null;
		this.dstMolCode = null;	
		this.srcStockCode = null;
		this.srcMolCode = null;			
	}

	/**
	 * @return Returns the catalogFolder.
	 */
	public CatalogFolder getCatalogFolder() {
		return catalogFolder;
	}

	/**
	 * @return Returns the catalogName.
	 */
	public Catalog getCatalogName() {
		return catalogName;
	}

	/**
	 * @return Returns the dstMolCode.
	 */
	public Contractor getDstMolCode() {
		return dstMolCode;
	}

	/**
	 * @return Returns the dstStockCode.
	 */
	public Contractor getDstStockCode() {
		return dstStockCode;
	}

	/**
	 * @return Returns the srcMolCode.
	 */
	public Contractor getSrcMolCode() {
		return srcMolCode;
	}

	/**
	 * @return Returns the srcStockCode.
	 */
	public Contractor getSrcStockCode() {
		return srcStockCode;
	}



}
