/*
 * Warehouse.java
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

import java.io.Serializable;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.reference.model.OrgUnit;

/**
 * Модель бизнес-компонента "Склады"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: Warehouse.java,v 1.11 2008/06/05 12:25:28 sharapov Exp $
 */
@DataItemName("Warehouse.Warehouse") //$NON-NLS-1$
public class Warehouse extends OrgUnit implements Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private StockPolicy StockPolicy;

	private WarehouseType WarehouseType;

	/**
	 * учитывать закрытие операционных дней
	 */
	private boolean CheckTransactionDay;

	/**
	 * закрыть склад для фактического движения
	 */
	private boolean WarehouseTransactionClosed;

	/**
	 * закрыт по число
	 */
	private Date closedDateTill;

	/**
	 * сотрудник кто закрыл
	 */
	private String userStockClosed;

	/**
	 * дата закрытия
	 */
	private Date operationDate;

	private boolean CalcFact;

	private boolean CalcFactSign;

	private boolean CalcPlanIn;

	private boolean CalcPlanInSign;

	private boolean CalcPlanOut;

	private boolean CalcPlanOutSign;

	private boolean CalcReserve;

	private boolean CalcReserveSign;

	private boolean UseBinLocation;

	private boolean BinSizing;

	/**
	 * BAi рассчёта цены прихода складской партии
	 */
	private com.mg.merp.baiengine.model.Repository batchPriceBAi;

	/**
	 * BAi cтратегии списания
	 */
	private com.mg.merp.baiengine.model.Repository DisposalStrategy;

	// Constructors

	/** default constructor */
	public Warehouse() {
	}

	/** constructor with id */
	public Warehouse(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * Получить BAi рассчёта цены прихода складской партии
	 */
	@DataItemName("Warehouse.Warehouse.AlgRepository") //$NON-NLS-1$
	public com.mg.merp.baiengine.model.Repository getBatchPriceBAi() {
		return this.batchPriceBAi;
	}

	public void setBatchPriceBAi(com.mg.merp.baiengine.model.Repository batchPriceBAi) {
		this.batchPriceBAi = batchPriceBAi;
	}

	/**
	 * 
	 */
	public StockPolicy getStockPolicy() {
		return this.StockPolicy;
	}

	public void setStockPolicy(StockPolicy StockPolicy) {
		this.StockPolicy = StockPolicy;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcFact") //$NON-NLS-1$
	public boolean getCalcFact() {
		return this.CalcFact;
	}

	public void setCalcFact(boolean CalcFact) {
		this.CalcFact = CalcFact;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcFactSign") //$NON-NLS-1$
	public boolean getCalcFactSign() {
		return this.CalcFactSign;
	}

	public void setCalcFactSign(boolean CalcFactSign) {
		this.CalcFactSign = CalcFactSign;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcPlanIn") //$NON-NLS-1$
	public boolean getCalcPlanIn() {
		return this.CalcPlanIn;
	}

	public void setCalcPlanIn(boolean CalcPlanIn) {
		this.CalcPlanIn = CalcPlanIn;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcPlanInSign") //$NON-NLS-1$
	public boolean getCalcPlanInSign() {
		return this.CalcPlanInSign;
	}

	public void setCalcPlanInSign(boolean CalcPlanInSign) {
		this.CalcPlanInSign = CalcPlanInSign;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcPlanOut") //$NON-NLS-1$
	public boolean getCalcPlanOut() {
		return this.CalcPlanOut;
	}

	public void setCalcPlanOut(boolean CalcPlanOut) {
		this.CalcPlanOut = CalcPlanOut;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcPlanOutSign") //$NON-NLS-1$
	public boolean getCalcPlanOutSign() {
		return this.CalcPlanOutSign;
	}

	public void setCalcPlanOutSign(boolean CalcPlanOutSign) {
		this.CalcPlanOutSign = CalcPlanOutSign;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcReserve") //$NON-NLS-1$
	public boolean getCalcReserve() {
		return this.CalcReserve;
	}

	public void setCalcReserve(boolean CalcReserve) {
		this.CalcReserve = CalcReserve;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.CalcReserveSign") //$NON-NLS-1$
	public boolean getCalcReserveSign() {
		return this.CalcReserveSign;
	}

	public void setCalcReserveSign(boolean CalcReserveSign) {
		this.CalcReserveSign = CalcReserveSign;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.UseBinLocat") //$NON-NLS-1$
	public boolean getUseBinLocation() {
		return this.UseBinLocation;
	}

	public void setUseBinLocation(boolean UseBinLocation) {
		this.UseBinLocation = UseBinLocation;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.Warehouse.BinSizing") //$NON-NLS-1$
	public boolean getBinSizing() {
		return this.BinSizing;
	}

	public void setBinSizing(boolean BinSizing) {
		this.BinSizing = BinSizing;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		SysClient = sysClient;
	}

	public com.mg.merp.baiengine.model.Repository getDisposalStrategy() {
		return DisposalStrategy;
	}

	public void setDisposalStrategy(
			com.mg.merp.baiengine.model.Repository disposalStrategy) {
		DisposalStrategy = disposalStrategy;
	}

	public WarehouseType getWarehouseType() {
		return WarehouseType;
	}

	public void setWarehouseType(WarehouseType warehouseType) {
		WarehouseType = warehouseType;
	}

	@DataItemName("Warehouse.CheckTransactionDay") //$NON-NLS-1$
	public boolean getCheckTransactionDay() {
		return CheckTransactionDay;
	}

	public void setCheckTransactionDay(boolean CheckTransactionDay) {
		this.CheckTransactionDay = CheckTransactionDay;
	}

	@DataItemName("Warehouse.WarehouseTransactionClosed") //$NON-NLS-1$
	public boolean getWarehouseTransactionClosed() {
		return WarehouseTransactionClosed;
	}

	public void setWarehouseTransactionClosed(boolean WarehouseTransactionClosed) {
		this.WarehouseTransactionClosed = WarehouseTransactionClosed;
	}

	@DataItemName("Warehouse.ClosedDateTill") //$NON-NLS-1$
	public Date getClosedDateTill() {
		return closedDateTill;
	}

	public void setClosedDateTill(Date closedDateTill) {
		this.closedDateTill = closedDateTill;
	}

	@DataItemName("Warehouse.UserStockClosed") //$NON-NLS-1$
	public String getUserStockClosed() {
		return userStockClosed;
	}

	public void setUserStockClosed(String userStockClosed) {
		this.userStockClosed = userStockClosed;
	}

	@DataItemName("Warehouse.OperationDate") //$NON-NLS-1$
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

}