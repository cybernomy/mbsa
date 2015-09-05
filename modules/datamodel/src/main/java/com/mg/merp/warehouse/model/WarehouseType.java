/*
 * WarehouseType.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонент "Тип склада"
 *  
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseType.java,v 1.1 2007/09/17 12:45:56 alikaev Exp $
 */
@DataItemName("Warehouse.WarehouseType")
public class WarehouseType extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {
	
	private int Id;


	private com.mg.merp.core.model.SysClient SysClient;

	private String Code;
	
	private String Name;
	
	
	public WarehouseType() {
	}
	
	public WarehouseType(int id) {
		Id = id;
	}

	@DataItemName("Warehouse.Code")
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@DataItemName("ID")
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@DataItemName("Warehouse.Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		SysClient = sysClient;
	}
	
}
