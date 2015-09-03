/*
 * StockBatchHistory.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "История фактического движения по партии"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: StockBatchHistory.java,v 1.5 2008/06/06 05:54:44 sharapov Exp $
 */
public class StockBatchHistory extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.document.model.DocHead DocHead;

	private com.mg.merp.document.model.DocSpec DocSpec;

	private com.mg.merp.warehouse.model.StockBatchHistory StockBatchHistory;

	private com.mg.merp.warehouse.model.StockBatch StockBatch;

	private StockBatchHistoryKind Kind;

	private java.util.Date ProcessDate;

	private java.util.Date DateTime;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal Quantity2;

	private com.mg.merp.core.model.SysClient SysClient;

	// Constructors

	/** default constructor */
	public StockBatchHistory() {
	}

	/** constructor with id */
	public StockBatchHistory(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead DocHead) {
		this.DocHead = DocHead;
	}

	/**
	 * 
	 */
	public com.mg.merp.warehouse.model.StockBatchHistory getStockBatchHistory() {
		return this.StockBatchHistory;
	}

	public void setStockBatchHistory(com.mg.merp.warehouse.model.StockBatchHistory StockBatchHistory) {
		this.StockBatchHistory = StockBatchHistory;
	}

	/**
	 * 
	 */
	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.DocSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec DocSpec) {
		this.DocSpec = DocSpec;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	public com.mg.merp.warehouse.model.StockBatch getStockBatch() {
		return this.StockBatch;
	}

	public void setStockBatch(com.mg.merp.warehouse.model.StockBatch StockBatch) {
		this.StockBatch = StockBatch;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockBatchHistory.DateTime") //$NON-NLS-1$
	public java.util.Date getDateTime() {
		return this.DateTime;
	}

	public void setDateTime(java.util.Date DateTime) {
		this.DateTime = DateTime;
	}

	/**
	 * 
	 */
	public StockBatchHistoryKind getKind() {
		return this.Kind;
	}

	public void setKind(StockBatchHistoryKind Kind) {
		this.Kind = Kind;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockBatchHistory.Quantity1") //$NON-NLS-1$
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockBatchHistory.ProcessDate") //$NON-NLS-1$
	public java.util.Date getProcessDate() {
		return this.ProcessDate;
	}

	public void setProcessDate(java.util.Date ProcessDate) {
		this.ProcessDate = ProcessDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockBatchHistory.Quantity2") //$NON-NLS-1$
	public java.math.BigDecimal getQuantity2() {
		return this.Quantity2;
	}

	public void setQuantity2(java.math.BigDecimal Quantity2) {
		this.Quantity2 = Quantity2;
	}

}