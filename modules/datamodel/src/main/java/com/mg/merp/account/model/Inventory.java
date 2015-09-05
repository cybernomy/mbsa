/*
 * Inventory.java
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
 * Объектная модель бизнес-компонента "Данные по видам учета"
 * 
 * @author hbm2java
 * @author Konstantin S. Alikaev
 * @version $Id: Inventory.java,v 1.9 2008/04/28 10:13:27 alikaev Exp $
 */
@DataItemName("Account.Inventory")	   
public class Inventory extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.account.model.InvLocation InvLocation;

	private com.mg.merp.account.model.AccPlan AccKt;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.account.model.AmCode AmCode;

	private com.mg.merp.account.model.AnlPlan Anl3;

	private com.mg.merp.account.model.AnlPlan Anl1;

	private com.mg.merp.account.model.AccPlan accPlan;

	private com.mg.merp.account.model.AnlPlan Anl2;

	private com.mg.merp.account.model.AnlPlan AnlKt3;

	private com.mg.merp.account.model.AnlPlan AnlDb5;

	private com.mg.merp.account.model.AnlPlan AnlKt1;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.account.model.InvHead InvHead;

	private com.mg.merp.account.model.AnlPlan Anl4;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.account.model.AnlPlan AnlKt2;

	private com.mg.merp.account.model.AnlPlan AnlDb2;

	private com.mg.merp.account.model.AccKind AccKind;

	private com.mg.merp.account.model.AnlPlan Anl5;

	private com.mg.merp.account.model.Okof Okof;

	private com.mg.merp.account.model.Inventory Inventory;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.AnlPlan AnlDb3;

	private com.mg.merp.account.model.AnlPlan AnlDb1;

	private com.mg.merp.account.model.AnlPlan AnlKt5;

	private com.mg.merp.account.model.AnlPlan AnlDb4;

	private com.mg.merp.account.model.AccGroup AccGroup;

	private com.mg.merp.account.model.AccPlan AccDb;

	private com.mg.merp.account.model.AnlPlan AnlKt4;

	private java.lang.String GroupNum;

	private java.lang.String CardNum;

	private java.lang.String ObjNum;

	private java.lang.String Manufacturer;

	private java.lang.String Model;

	private java.lang.String SerialNum;

	private java.lang.String PasspNum;

	private java.lang.String InOperDocNum;

	private java.util.Date InOperDate;

	private java.lang.String OutOperDocNum;

	private java.util.Date OutOperDate;

	private java.math.BigDecimal BalanceCost;

	private java.math.BigDecimal BeginCost;

	private java.math.BigDecimal BeginLoss;

	private java.math.BigDecimal Amort;

	private java.math.BigDecimal EndCost;

	private java.util.Date BeginLossDate;

	private java.util.Date AmortDate;

	private boolean IsComplex;

	private boolean IsCommon;

	private java.lang.Float Factor;

	private AmortAlgorithmType Algorithm;

	private java.math.BigDecimal YearAmortRate;

	private java.lang.Float ExplPeriodY;

	private java.lang.Float ExplPeriodM;

	private java.lang.Double Production;

	private java.lang.String Comment;

	private java.math.BigDecimal Initialloss;

	private java.lang.String IncomeDocNum;

	private java.util.Date IncomeDate;

	private java.lang.String InvName;

	private java.util.Set<Amortization> amortizations;

	private java.util.Set<InvHistory> invHistories;

	// Constructors

	/** default constructor */
	public Inventory() {
	}

	/** constructor with id */
	public Inventory(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	
	@DataItemName("ID")	
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.account.model.InvLocation getInvLocation() {
		return this.InvLocation;
	}

	public void setInvLocation(com.mg.merp.account.model.InvLocation InvLocation) {
		this.InvLocation = InvLocation;
	}

	@DataItemName("Account.EconSpec.AccKt")
	public com.mg.merp.account.model.AccPlan getAccKt() {
		return this.AccKt;
	}

	public void setAccKt(com.mg.merp.account.model.AccPlan AccKt) {
		this.AccKt = AccKt;
	}

	public com.mg.merp.account.model.AmCode getAmCode() {
		return this.AmCode;
	}

	public void setAmCode(com.mg.merp.account.model.AmCode AmCode) {
		this.AmCode = AmCode;
	}

	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	@DataItemName("Account.Inventory.Anl3")
	public com.mg.merp.account.model.AnlPlan getAnl3() {
		return this.Anl3;
	}

	public void setAnl3(com.mg.merp.account.model.AnlPlan Anl3) {
		this.Anl3 = Anl3;
	}

	@DataItemName("Account.Inventory.Anl1")
	public com.mg.merp.account.model.AnlPlan getAnl1() {
		return this.Anl1;
	}

	public void setAnl1(com.mg.merp.account.model.AnlPlan Anl1) {
		this.Anl1 = Anl1;
	}

	@DataItemName("Account.Inventory.Acc")
	public com.mg.merp.account.model.AccPlan getAccPlan() {
		return accPlan;
	}

	public void setAccPlan(com.mg.merp.account.model.AccPlan accPlan) {
		this.accPlan = accPlan;
	}

	@DataItemName("Account.Inventory.Anl2")
	public com.mg.merp.account.model.AnlPlan getAnl2() {
		return this.Anl2;
	}

	public void setAnl2(com.mg.merp.account.model.AnlPlan Anl2) {
		this.Anl2 = Anl2;
	}

	@DataItemName("Account.EconSpec.AnlKt3")
	public com.mg.merp.account.model.AnlPlan getAnlKt3() {
		return this.AnlKt3;
	}

	public void setAnlKt3(com.mg.merp.account.model.AnlPlan AnlKt3) {
		this.AnlKt3 = AnlKt3;
	}

	@DataItemName("Account.EconSpec.AnlDb5")
	public com.mg.merp.account.model.AnlPlan getAnlDb5() {
		return this.AnlDb5;
	}

	public void setAnlDb5(com.mg.merp.account.model.AnlPlan AnlDb5) {
		this.AnlDb5 = AnlDb5;
	}

	@DataItemName("Account.EconSpec.AnlKt1")
	public com.mg.merp.account.model.AnlPlan getAnlKt1() {
		return this.AnlKt1;
	}

	public void setAnlKt1(com.mg.merp.account.model.AnlPlan AnlKt1) {
		this.AnlKt1 = AnlKt1;
	}

	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	public com.mg.merp.account.model.InvHead getInvHead() {
		return this.InvHead;
	}

	public void setInvHead(com.mg.merp.account.model.InvHead InvHead) {
		this.InvHead = InvHead;
	}

	@DataItemName("Account.Inventory.Anl4")
	public com.mg.merp.account.model.AnlPlan getAnl4() {
		return this.Anl4;
	}

	public void setAnl4(com.mg.merp.account.model.AnlPlan Anl4) {
		this.Anl4 = Anl4;
	}

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
	}

	@DataItemName("Account.EconSpec.AnlKt2")
	public com.mg.merp.account.model.AnlPlan getAnlKt2() {
		return this.AnlKt2;
	}

	public void setAnlKt2(com.mg.merp.account.model.AnlPlan AnlKt2) {
		this.AnlKt2 = AnlKt2;
	}

	@DataItemName("Account.EconSpec.AnlDb2")
	public com.mg.merp.account.model.AnlPlan getAnlDb2() {
		return this.AnlDb2;
	}

	public void setAnlDb2(com.mg.merp.account.model.AnlPlan AnlDb2) {
		this.AnlDb2 = AnlDb2;
	}

	public com.mg.merp.account.model.AccKind getAccKind() {
		return this.AccKind;
	}

	public void setAccKind(com.mg.merp.account.model.AccKind AccKind) {
		this.AccKind = AccKind;
	}

	@DataItemName("Account.Inventory.Anl5")
	public com.mg.merp.account.model.AnlPlan getAnl5() {
		return this.Anl5;
	}

	public void setAnl5(com.mg.merp.account.model.AnlPlan Anl5) {
		this.Anl5 = Anl5;
	}

	public com.mg.merp.account.model.Okof getOkof() {
		return this.Okof;
	}

	public void setOkof(com.mg.merp.account.model.Okof Okof) {
		this.Okof = Okof;
	}

	public com.mg.merp.account.model.Inventory getInventory() {
		return this.Inventory;
	}

	public void setInventory(com.mg.merp.account.model.Inventory Inventory) {
		this.Inventory = Inventory;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("Account.EconSpec.AnlDb3")
	public com.mg.merp.account.model.AnlPlan getAnlDb3() {
		return this.AnlDb3;
	}

	public void setAnlDb3(com.mg.merp.account.model.AnlPlan AnlDb3) {
		this.AnlDb3 = AnlDb3;
	}

	@DataItemName("Account.EconSpec.AnlDb1")
	public com.mg.merp.account.model.AnlPlan getAnlDb1() {
		return this.AnlDb1;
	}

	public void setAnlDb1(com.mg.merp.account.model.AnlPlan AnlDb1) {
		this.AnlDb1 = AnlDb1;
	}

	@DataItemName("Account.EconSpec.AnlKt5")
	public com.mg.merp.account.model.AnlPlan getAnlKt5() {
		return this.AnlKt5;
	}

	public void setAnlKt5(com.mg.merp.account.model.AnlPlan AnlKt5) {
		this.AnlKt5 = AnlKt5;
	}

	@DataItemName("Account.EconSpec.AnlDb4")
	public com.mg.merp.account.model.AnlPlan getAnlDb4() {
		return this.AnlDb4;
	}

	public void setAnlDb4(com.mg.merp.account.model.AnlPlan AnlDb4) {
		this.AnlDb4 = AnlDb4;
	}

	public com.mg.merp.account.model.AccGroup getAccGroup() {
		return this.AccGroup;
	}

	public void setAccGroup(com.mg.merp.account.model.AccGroup AccGroup) {
		this.AccGroup = AccGroup;
	}

	@DataItemName("Account.EconSpec.AccDb")
	public com.mg.merp.account.model.AccPlan getAccDb() {
		return this.AccDb;
	}

	public void setAccDb(com.mg.merp.account.model.AccPlan AccDb) {
		this.AccDb = AccDb;
	}

	@DataItemName("Account.EconSpec.AnlKt4")
	public com.mg.merp.account.model.AnlPlan getAnlKt4() {
		return this.AnlKt4;
	}

	public void setAnlKt4(com.mg.merp.account.model.AnlPlan AnlKt4) {
		this.AnlKt4 = AnlKt4;
	}

	@DataItemName("Account.InvHead.GroupNum")
	public java.lang.String getGroupNum() {
		return this.GroupNum;
	}

	public void setGroupNum(java.lang.String GroupNum) {
		this.GroupNum = GroupNum;
	}

	@DataItemName("Account.InvHead.CardNum")
	public java.lang.String getCardNum() {
		return this.CardNum;
	}

	public void setCardNum(java.lang.String CardNum) {
		this.CardNum = CardNum;
	}

	@DataItemName("Account.InvHead.ObjNum")
	public java.lang.String getObjNum() {
		return this.ObjNum;
	}

	public void setObjNum(java.lang.String ObjNum) {
		this.ObjNum = ObjNum;
	}

	public java.lang.String getManufacturer() {
		return this.Manufacturer;
	}

	public void setManufacturer(java.lang.String Manufacturer) {
		this.Manufacturer = Manufacturer;
	}

	public java.lang.String getModel() {
		return this.Model;
	}

	public void setModel(java.lang.String Model) {
		this.Model = Model;
	}

	public java.lang.String getSerialNum() {
		return this.SerialNum;
	}

	public void setSerialNum(java.lang.String SerialNum) {
		this.SerialNum = SerialNum;
	}

	public java.lang.String getPasspNum() {
		return this.PasspNum;
	}

	public void setPasspNum(java.lang.String PasspNum) {
		this.PasspNum = PasspNum;
	}

	public java.lang.String getInOperDocNum() {
		return this.InOperDocNum;
	}

	public void setInOperDocNum(java.lang.String InOperDocNum) {
		this.InOperDocNum = InOperDocNum;
	}

	public java.util.Date getInOperDate() {
		return this.InOperDate;
	}

	public void setInOperDate(java.util.Date InOperDate) {
		this.InOperDate = InOperDate;
	}

	public java.lang.String getOutOperDocNum() {
		return this.OutOperDocNum;
	}

	public void setOutOperDocNum(java.lang.String OutOperDocNum) {
		this.OutOperDocNum = OutOperDocNum;
	}

	public java.util.Date getOutOperDate() {
		return this.OutOperDate;
	}

	public void setOutOperDate(java.util.Date OutOperDate) {
		this.OutOperDate = OutOperDate;
	}

	@DataItemName("Account.Inventory.BalanceCost")
	public java.math.BigDecimal getBalanceCost() {
		return this.BalanceCost;
	}

	public void setBalanceCost(java.math.BigDecimal BalanceCost) {
		this.BalanceCost = BalanceCost;
	}

	@DataItemName("Account.Inventory.BeginCost")
	public java.math.BigDecimal getBeginCost() {
		return this.BeginCost;
	}

	public void setBeginCost(java.math.BigDecimal BeginCost) {
		this.BeginCost = BeginCost;
	}

	@DataItemName("Account.Inventory.BeginLoss")
	public java.math.BigDecimal getBeginLoss() {
		return this.BeginLoss;
	}

	public void setBeginLoss(java.math.BigDecimal BeginLoss) {
		this.BeginLoss = BeginLoss;
	}

	@DataItemName("Account.Inventory.Amort")
	public java.math.BigDecimal getAmort() {
		return this.Amort;
	}

	public void setAmort(java.math.BigDecimal Amort) {
		this.Amort = Amort;
	}

	@DataItemName("Account.Inventory.EndCost")
	public java.math.BigDecimal getEndCost() {
		return this.EndCost;
	}

	public void setEndCost(java.math.BigDecimal EndCost) {
		this.EndCost = EndCost;
	}

	@DataItemName("Account.Inventory.BeginLossDate")
	public java.util.Date getBeginLossDate() {
		return this.BeginLossDate;
	}

	public void setBeginLossDate(java.util.Date BeginLossDate) {
		this.BeginLossDate = BeginLossDate;
	}

	@DataItemName("Account.Inventory.AmortDate")
	public java.util.Date getAmortDate() {
		return this.AmortDate;
	}

	public void setAmortDate(java.util.Date AmortDate) {
		this.AmortDate = AmortDate;
	}

	public boolean getIsComplex() {
		return this.IsComplex;
	}

	public void setIsComplex(boolean IsComplex) {
		this.IsComplex = IsComplex;
	}

	public boolean getIsCommon() {
		return this.IsCommon;
	}

	public void setIsCommon(boolean IsCommon) {
		this.IsCommon = IsCommon;
	}

	@DataItemName("Account.Inventory.Factor")
	public java.lang.Float getFactor() {
		return this.Factor;
	}

	public void setFactor(java.lang.Float Factor) {
		this.Factor = Factor;
	}

	public AmortAlgorithmType getAlgorithm() {
		return this.Algorithm;
	}

	public void setAlgorithm(AmortAlgorithmType Algorithm) {
		this.Algorithm = Algorithm;
	}

	@DataItemName("Account.Inventory.YearAmortRate")
	public java.math.BigDecimal getYearAmortRate() {
		return this.YearAmortRate;
	}

	public void setYearAmortRate(java.math.BigDecimal YearAmortRate) {
		this.YearAmortRate = YearAmortRate;
	}

	@DataItemName("Account.Inventory.ExplPeriodY")
	public java.lang.Float getExplPeriodY() {
		return this.ExplPeriodY;
	}

	public void setExplPeriodY(java.lang.Float ExplPeriodY) {
		this.ExplPeriodY = ExplPeriodY;
	}

	@DataItemName("Account.Inventory.ExplPeriodM")
	public java.lang.Float getExplPeriodM() {
		return this.ExplPeriodM;
	}

	public void setExplPeriodM(java.lang.Float ExplPeriodM) {
		this.ExplPeriodM = ExplPeriodM;
	}

	public java.lang.Double getProduction() {
		return this.Production;
	}

	public void setProduction(java.lang.Double Production) {
		this.Production = Production;
	}

	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	@DataItemName("Account.Inventory.Initialloss")
	public java.math.BigDecimal getInitialloss() {
		return this.Initialloss;
	}

	public void setInitialloss(java.math.BigDecimal Initialloss) {
		this.Initialloss = Initialloss;
	}

	public java.lang.String getIncomeDocNum() {
		return this.IncomeDocNum;
	}

	public void setIncomeDocNum(java.lang.String InComeDocNum) {
		this.IncomeDocNum = InComeDocNum;
	}

	public java.util.Date getIncomeDate() {
		return this.IncomeDate;
	}

	public void setIncomeDate(java.util.Date InComeDate) {
		this.IncomeDate = InComeDate;
	}

	public java.lang.String getInvName() {
		return this.InvName;
	}

	public void setInvName(java.lang.String InvName) {
		this.InvName = InvName;
	}

	public java.util.Set<Amortization> getAmortizations() {
		return amortizations;
	}

	public void setAmortizations(java.util.Set<Amortization> amortizations) {
		this.amortizations = amortizations;
	}

	public java.util.Set<InvHistory> getInvHistories() {
		return invHistories;
	}

	public void setInvHistories(java.util.Set<InvHistory> invHistories) {
		this.invHistories = invHistories;
	}
	
}