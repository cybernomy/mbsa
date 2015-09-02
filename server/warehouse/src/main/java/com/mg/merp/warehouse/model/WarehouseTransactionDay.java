/*
 * WarehouseTransactionDay.java
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
package com.mg.merp.warehouse.model;

import java.io.Serializable;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;


/**
 * Модель бизнес-компонента "Операционные дни склада"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTransactionDay.java,v 1.1 2007/11/29 08:38:53 alikaev Exp $
 */
@DataItemName("Warehouse.WarehouseTransactionDay")
public class WarehouseTransactionDay extends com.mg.framework.service.PersistentObjectHibernate implements Serializable {
	/**
	 * идентификатор
	 */
	private int id;
	
	/**
	 * склад
	 */
	private Warehouse stock;
	
	/**
	 * закрытый день
	 */
	private Date closedDay;
	
	/**
	 * кто закрыл
	 */
	private String userStockClosed;
	
	/**
	 * дата закрытия
	 */
	private Date operationDate;

	
	public WarehouseTransactionDay() {
	}

	public WarehouseTransactionDay(int id) {
		this.id = id;
	}

	@DataItemName("ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DataItemName("Warehouse.WarehouseTransactionDay.Stock")
	public Warehouse getStock() {
		return stock;
	}

	public void setStock(Warehouse stock) {
		this.stock = stock;
	}

	@DataItemName("Warehouse.WarehouseTransactionDay.ClosedDay")
	public Date getClosedDay() {
		return closedDay;
	}

	public void setClosedDay(Date closedDay) {
		this.closedDay = closedDay;
	}

	@DataItemName("Warehouse.WarehouseTransactionDay.UserStockClosed")
	public String getUserStockClosed() {
		return userStockClosed;
	}

	public void setUserStockClosed(String userStockClosed) {
		this.userStockClosed = userStockClosed;
	}

	@DataItemName("Warehouse.WarehouseTransactionDay.OperationDate")
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

}
