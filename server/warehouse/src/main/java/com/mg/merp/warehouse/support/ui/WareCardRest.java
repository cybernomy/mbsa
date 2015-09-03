/*
 * WareCardRest.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.ui;

import java.math.BigDecimal;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.reference.model.Contractor;

/**
 * Условия отбора КСУ
 * 
 * @author Valentin A. Poroxnenko
 * @version $$Id: WareCardRest.java,v 1.2 2007/03/28 13:59:22 poroxnenko Exp $$
 */
public class WareCardRest extends DefaultHierarhyRestrictionForm {
	@DataItemName("Warehouse.StockCardCond.CardNumberFrom")	
	private String CardNumberFrom = null;	
	
	@DataItemName("Warehouse.StockCardCond.CardNumberTo")	
	private String CardNumberTo = null;
	
	@DataItemName ("Reference.Catalog.PluCode")	
	private String PluCode = "";
	
	@DataItemName("Reference.Catalog.BarCode")	
	private String BarCode = "";
	
	@DataItemName("Reference.Code")
	private String Code = "";
	
	@DataItemName("Reference.Name")
	private String Name = "";
	
	@DataItemName("Warehouse.StockCard.Mol")
	private Contractor Mol = null;
	
	@DataItemName ("Warehouse.StockCardCond.SupplyNormFrom")	
	private BigDecimal SupplyNormFrom = null;
	
	@DataItemName("Warehouse.StockCardCond.SupplyNormTo")	
	private BigDecimal SupplyNormTo = null;
	
	@DataItemName("Warehouse.StockCardCond.ReserveFrom")
	private BigDecimal ReserveFrom = null;
	
	@DataItemName("Warehouse.StockCardCond.ReserveTo")
	private BigDecimal ReserveTo = null;
	
	@DataItemName ("Warehouse.StockCardCond.QuantityFrom")	
	private BigDecimal QuantityFrom = null;
	
	@DataItemName("Warehouse.StockCardCond.QuantityTo")	
	private BigDecimal QuantityTo = null;
	
	@DataItemName("Warehouse.StockCardCond.PlanInFrom")
	private BigDecimal PlanInFrom = null;
	
	@DataItemName("Warehouse.StockCardCond.PlanInTo")
	private BigDecimal PlanInTo = null;
	
	@DataItemName("Warehouse.StockCardCond.PlanOutFrom")
	private BigDecimal PlanOutFrom = null;
	
	@DataItemName("Warehouse.StockCardCond.PlanOutTo")
	private BigDecimal PlanOutTo = null;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.CardNumberFrom = null;
		this.CardNumberTo = null;
		this.BarCode = "";
		this.PluCode = "";
		this.Code = "";
		this.Name = "";
		this.Mol = null;
		this.SupplyNormFrom = null;
		this.SupplyNormTo = null;
		this.ReserveFrom = null;
		this.ReserveTo = null;
		this.QuantityFrom = null;
		this.QuantityTo = null;
		this.PlanInFrom = null;
		this.PlanInTo = null;
		this.PlanOutFrom = null;
		this.PlanOutTo = null;
	}

	public String getCardNumberFrom() {
		return CardNumberFrom;
	}

	public String getCardNumberTo() {
		return CardNumberTo;
	}

	public String getBarCode() {
		return BarCode;
	}

	public String getPluCode() {
		return PluCode;
	}

	public String getCode() {
		return Code;
	}

	public String getName() {
		return Name;
	}

	public Contractor getMol() {
		return Mol;
	}

	public BigDecimal getReserveFrom() {
		return ReserveFrom;
	}

	public BigDecimal getReserveTo() {
		return ReserveTo;
	}

	public BigDecimal getSupplyNormFrom() {
		return SupplyNormFrom;
	}

	public BigDecimal getSupplyNormTo() {
		return SupplyNormTo;
	}

	public BigDecimal getPlanInFrom() {
		return PlanInFrom;
	}

	public BigDecimal getPlanInTo() {
		return PlanInTo;
	}

	public BigDecimal getPlanOutFrom() {
		return PlanOutFrom;
	}

	public BigDecimal getPlanOutTo() {
		return PlanOutTo;
	}

	public BigDecimal getQuantityFrom() {
		return QuantityFrom;
	}

	public BigDecimal getQuantityTo() {
		return QuantityTo;
	}

}
