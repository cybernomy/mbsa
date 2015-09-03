/*
 * Amortization.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * Объектная модель бизнес-компонента "Ведомость начисления амортизации"
 * 
 * @author hbm2java
 * @author Konstantin S. Alikaev
 * @version $Id: Amortization.java,v 1.4 2008/05/08 09:05:38 alikaev Exp $
 */
@DataItemName("Account.Amortization")	   
public class Amortization extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private int Id;
	private com.mg.merp.account.model.Inventory Inventory;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.lang.Short NMonths;
	private java.lang.Integer Batch;
	private java.math.BigDecimal BalanceCost;
	private java.math.BigDecimal AmRate;
	private java.math.BigDecimal SumRate;
	private Float ExplPeriodY;
	private Float ExplPeriodM;
	private java.math.BigDecimal SumPeriod;
	private Float ProductEst;
	private Float ProductFact;
	private java.math.BigDecimal SumProduct;
	private Float Factor;
	private java.math.BigDecimal SumAdd;
	private java.math.BigDecimal DeprValue;
	private java.math.BigDecimal SumDeprValue;
	private java.math.BigDecimal SumTotal;
	private short IMonth;
	private java.math.BigDecimal EndCost;
	private java.lang.Integer AmCodeId;

	// Constructors

	/** default constructor */
	public Amortization() {
	}

	/** constructor with id */
	public Amortization(int Id) {
		this.Id = Id;
	}

	// Property accessors
	
	@DataItemName("ID")	   
	public int getId () {
		return this.Id;
	}

	public void setId (int Id) {
		this.Id = Id;
	}

	public com.mg.merp.account.model.Inventory getInventory () {
		return this.Inventory;
	}

	public void setInventory (com.mg.merp.account.model.Inventory Inventory) {
		this.Inventory = Inventory;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("Account.Amortization.NMonths")
	public java.lang.Short getNMonths () {
		return this.NMonths;
	}

	public void setNMonths (java.lang.Short NMonths) {
		this.NMonths = NMonths;
	}

	public java.lang.Integer getBatch () {
		return this.Batch;
	}

	public void setBatch (java.lang.Integer Batch) {
		this.Batch = Batch;
	}

	@DataItemName("Account.Amortization.BalanceCost")
	public java.math.BigDecimal getBalanceCost () {
		return this.BalanceCost;
	}

	public void setBalanceCost (java.math.BigDecimal BalanceCost) {
		this.BalanceCost = BalanceCost;
	}

	@DataItemName("Account.Amortization.AmRate")
	public java.math.BigDecimal getAmRate () {
		return this.AmRate;
	}

	public void setAmRate (java.math.BigDecimal AmRate) {
		this.AmRate = AmRate;
	}

	@DataItemName("Account.Amortization.SumRate")
	public java.math.BigDecimal getSumRate () {
		return this.SumRate;
	}

	public void setSumRate (java.math.BigDecimal SumRate) {
		this.SumRate = SumRate;
	}

	@DataItemName("Account.Amortization.ExplPeriodY")
	public Float getExplPeriodY () {
		return this.ExplPeriodY;
	}

	public void setExplPeriodY (Float ExplPeriodY) {
		this.ExplPeriodY = ExplPeriodY;
	}

	@DataItemName("Account.Amortization.ExplPeriodM")
	public Float getExplPeriodM () {
		return this.ExplPeriodM;
	}

	public void setExplPeriodM (Float ExplPeriodM) {
		this.ExplPeriodM = ExplPeriodM;
	}

	@DataItemName("Account.Amortization.SumPeriod")
	public java.math.BigDecimal getSumPeriod () {
		return this.SumPeriod;
	}

	public void setSumPeriod (java.math.BigDecimal SumPeriod) {
		this.SumPeriod = SumPeriod;
	}

	@DataItemName("Account.Amortization.ProductEst")
	public Float getProductEst () {
		return this.ProductEst;
	}

	public void setProductEst (Float ProductEst) {
		this.ProductEst = ProductEst;
	}
	
	@DataItemName("Account.Amortization.ProductFact")
	 public Float getProductFact () {
		 return this.ProductFact;
	 }

	 public void setProductFact (Float ProductFact) {
		 this.ProductFact = ProductFact;
	 }

	@DataItemName("Account.Amortization.SumProduct")
	 public java.math.BigDecimal getSumProduct () {
		 return this.SumProduct;
	 }

	 public void setSumProduct (java.math.BigDecimal SumProduct) {
		 this.SumProduct = SumProduct;
	 }

	@DataItemName("Account.Amortization.Factor")
	 public Float getFactor () {
		 return this.Factor;
	 }

	 public void setFactor (Float Factor) {
		 this.Factor = Factor;
	 }

	@DataItemName("Account.Amortization.SumAdd")
	 public java.math.BigDecimal getSumAdd () {
		 return this.SumAdd;
	 }

	 public void setSumAdd (java.math.BigDecimal SumAdd) {
		 this.SumAdd = SumAdd;
	 }

	@DataItemName("Account.Amortization.EndCost")
	 public java.math.BigDecimal getDeprValue () {
		 return this.DeprValue;
	 }

	 public void setDeprValue (java.math.BigDecimal DeprValue) {
		 this.DeprValue = DeprValue;
	 }

	@DataItemName("Account.Amortization.SumDeprValue")
	 public java.math.BigDecimal getSumDeprValue () {
		 return this.SumDeprValue;
	 }

	 public void setSumDeprValue (java.math.BigDecimal SumDeprValue) {
		 this.SumDeprValue = SumDeprValue;
	 }

	@DataItemName("Account.Amortization.SumTotal")
	 public java.math.BigDecimal getSumTotal () {
		 return this.SumTotal;
	 }

	 public void setSumTotal (java.math.BigDecimal SumTotal) {
		 this.SumTotal = SumTotal;
	 }

	@DataItemName("Account.Amortization.IMonth")
	 public short getIMonth () {
		 return this.IMonth;
	 }

	 public void setIMonth (short IMonth) {
		 this.IMonth = IMonth;
	 }

	@DataItemName("Account.Inventory.EndCost")
	 public java.math.BigDecimal getEndCost () {
		 return this.EndCost;
	 }

	 public void setEndCost (java.math.BigDecimal EndCost) {
		 this.EndCost = EndCost;
	 }

	 public java.lang.Integer getAmCodeId () {
		 return this.AmCodeId;
	 }

	 public void setAmCodeId (java.lang.Integer AmCodeId) {
		 this.AmCodeId = AmCodeId;
	 }
	 
}