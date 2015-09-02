/*
 * StockPlanHistory.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: StockPlanHistory.java,v 1.3 2007/09/04 11:08:20 safonov Exp $
 */
public class StockPlanHistory extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.warehouse.model.StockCard StockCard;

	private com.mg.merp.document.model.DocSpec DocSpec;

	private com.mg.merp.document.model.DocHead DocHead;

	private com.mg.merp.core.model.SysClient SysClient;

	private StockPlanHistoryKind Kind;

	private StockPlanHistoryDirection Direction;

	private java.util.Date ProcessDate;

	private java.util.Date SysDateTime;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal Quantity2;

	// Constructors

	/** default constructor */
	public StockPlanHistory() {
	}

	/** constructor with id */
	public StockPlanHistory(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.warehouse.model.StockCard getStockCard() {
		return this.StockCard;
	}

	public void setStockCard(com.mg.merp.warehouse.model.StockCard StockCard) {
		this.StockCard = StockCard;
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

	public com.mg.merp.document.model.DocHead getDocHead() {
		return this.DocHead;
	}

	public void setDocHead(com.mg.merp.document.model.DocHead DocHead) {
		this.DocHead = DocHead;
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

	public StockPlanHistoryKind getKind() {
		return this.Kind;
	}

	public void setKind(StockPlanHistoryKind Kind) {
		this.Kind = Kind;
	}

	/**
	 * 
	 */

	public StockPlanHistoryDirection getDirection() {
		return this.Direction;
	}

	public void setDirection(StockPlanHistoryDirection Direction) {
		this.Direction = Direction;
	}

	/**
	 * 
	 */

	public java.util.Date getProcessDate() {
		return this.ProcessDate;
	}

	public void setProcessDate(java.util.Date ProcessDate) {
		this.ProcessDate = ProcessDate;
	}

	/**
	 * 
	 */

	public java.util.Date getSysDateTime() {
		return this.SysDateTime;
	}

	public void setSysDateTime(java.util.Date SysDateTime) {
		this.SysDateTime = SysDateTime;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getQuantity2() {
		return this.Quantity2;
	}

	public void setQuantity2(java.math.BigDecimal Quantity2) {
		this.Quantity2 = Quantity2;
	}

}