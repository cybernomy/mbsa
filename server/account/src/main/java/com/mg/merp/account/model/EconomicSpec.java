/*
 * EconomicSpec.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: EconomicSpec.java,v 1.8 2009/02/25 09:33:43 safonov Exp $
 */
public class EconomicSpec extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.account.model.EconomicSpecFeature Feature1;

	private com.mg.merp.account.model.AccBatchHistory AccBatchHistoryDb;

	private com.mg.merp.account.model.AccBatchHistory AccBatchHistoryKt;

	private com.mg.merp.account.model.AccPlan AccKt;

	private com.mg.merp.account.model.RemnAnl RemnAnlDb;

	private com.mg.merp.account.model.AnlPlan AnlKt3;

	private com.mg.merp.account.model.EconomicSpecFeature Feature4;

	private com.mg.merp.account.model.RemnDbKt RemnDb;

	private com.mg.merp.account.model.AnlPlan AnlDb5;

	private com.mg.merp.account.model.RemnVal RemnValDb;

	private com.mg.merp.account.model.AnlPlan AnlKt1;

	private com.mg.merp.account.model.RemnAcc RemnAccDb;

	private com.mg.merp.account.model.RemnAcc RemnAccKt;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.account.model.EconomicSpecFeature Feature2;

	private com.mg.merp.account.model.RemnVal RemnValKt;

	private com.mg.merp.account.model.AnlPlan AnlKt2;

	private com.mg.merp.account.model.AnlPlan AnlDb2;

	private com.mg.merp.account.model.EconomicSpecFeature Feature3;

	private com.mg.merp.account.model.RemnAnl RemnAnlKt;

	private com.mg.merp.account.model.EconomicOper EconomicOper;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.RemnDbKt RemnKt;

	private com.mg.merp.account.model.AnlPlan AnlDb3;

	private com.mg.merp.account.model.AnlPlan AnlKt5;

	private com.mg.merp.account.model.AnlPlan AnlDb4;

	private com.mg.merp.account.model.AnlPlan AnlDb1;

	private com.mg.merp.account.model.AccPlan AccDb;

	private com.mg.merp.account.model.EconomicSpecFeature Feature5;

	private com.mg.merp.account.model.AccBatch accBatchDb;

	private com.mg.merp.account.model.AnlPlan AnlKt4;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal SummaNat;

	private java.math.BigDecimal SummaCur;

	private java.math.BigDecimal CurCource;

	private com.mg.merp.account.model.AccBatch accBatchKt;

	private boolean isBulkOperation;

	// Constructors

	public boolean isBulkOperation() {
		return isBulkOperation;
	}

	public void setBulkOperation(boolean isBulkOperation) {
		this.isBulkOperation = isBulkOperation;
	}

	/** default constructor */
	public EconomicSpec() {
	}

	/** constructor with id */
	public EconomicSpec(java.lang.Integer Id) {
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
	@DataItemName("Account.EconSpec.Feature1")
	public com.mg.merp.account.model.EconomicSpecFeature getFeature1() {
		return this.Feature1;
	}

	public void setFeature1(
			com.mg.merp.account.model.EconomicSpecFeature Feature1) {
		this.Feature1 = Feature1;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.AccBatchHistory getAccBatchHistoryDb() {
		return this.AccBatchHistoryDb;
	}

	public void setAccBatchHistoryDb(
			com.mg.merp.account.model.AccBatchHistory AccBatchHistoryDb) {
		this.AccBatchHistoryDb = AccBatchHistoryDb;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.AccBatchHistory getAccBatchHistoryKt() {
		return this.AccBatchHistoryKt;
	}

	public void setAccBatchHistoryKt(
			com.mg.merp.account.model.AccBatchHistory AccBatchHistoryKt) {
		this.AccBatchHistoryKt = AccBatchHistoryKt;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AccKt")
	public com.mg.merp.account.model.AccPlan getAccKt() {
		return this.AccKt;
	}

	public void setAccKt(com.mg.merp.account.model.AccPlan AccKt) {
		this.AccKt = AccKt;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnAnl getRemnAnlDb() {
		return this.RemnAnlDb;
	}

	public void setRemnAnlDb(com.mg.merp.account.model.RemnAnl RemnAnlDb) {
		this.RemnAnlDb = RemnAnlDb;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlKt3")
	public com.mg.merp.account.model.AnlPlan getAnlKt3() {
		return this.AnlKt3;
	}

	public void setAnlKt3(com.mg.merp.account.model.AnlPlan AnlKt3) {
		this.AnlKt3 = AnlKt3;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.Feature4")
	public com.mg.merp.account.model.EconomicSpecFeature getFeature4() {
		return this.Feature4;
	}

	public void setFeature4(
			com.mg.merp.account.model.EconomicSpecFeature Feature4) {
		this.Feature4 = Feature4;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnDbKt getRemnDb() {
		return this.RemnDb;
	}

	public void setRemnDb(com.mg.merp.account.model.RemnDbKt RemnDb) {
		this.RemnDb = RemnDb;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlDb5")
	public com.mg.merp.account.model.AnlPlan getAnlDb5() {
		return this.AnlDb5;
	}

	public void setAnlDb5(com.mg.merp.account.model.AnlPlan AnlDb5) {
		this.AnlDb5 = AnlDb5;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnVal getRemnValDb() {
		return this.RemnValDb;
	}

	public void setRemnValDb(com.mg.merp.account.model.RemnVal RemnValDb) {
		this.RemnValDb = RemnValDb;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlKt1")
	public com.mg.merp.account.model.AnlPlan getAnlKt1() {
		return this.AnlKt1;
	}

	public void setAnlKt1(com.mg.merp.account.model.AnlPlan AnlKt1) {
		this.AnlKt1 = AnlKt1;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnAcc getRemnAccDb() {
		return this.RemnAccDb;
	}

	public void setRemnAccDb(com.mg.merp.account.model.RemnAcc RemnAccDb) {
		this.RemnAccDb = RemnAccDb;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnAcc getRemnAccKt() {
		return this.RemnAccKt;
	}

	public void setRemnAccKt(com.mg.merp.account.model.RemnAcc RemnAccKt) {
		this.RemnAccKt = RemnAccKt;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.Feature2")
	public com.mg.merp.account.model.EconomicSpecFeature getFeature2() {
		return this.Feature2;
	}

	public void setFeature2(
			com.mg.merp.account.model.EconomicSpecFeature Feature2) {
		this.Feature2 = Feature2;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnVal getRemnValKt() {
		return this.RemnValKt;
	}

	public void setRemnValKt(com.mg.merp.account.model.RemnVal RemnValKt) {
		this.RemnValKt = RemnValKt;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlKt2")
	public com.mg.merp.account.model.AnlPlan getAnlKt2() {
		return this.AnlKt2;
	}

	public void setAnlKt2(com.mg.merp.account.model.AnlPlan AnlKt2) {
		this.AnlKt2 = AnlKt2;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlDb2")
	public com.mg.merp.account.model.AnlPlan getAnlDb2() {
		return this.AnlDb2;
	}

	public void setAnlDb2(com.mg.merp.account.model.AnlPlan AnlDb2) {
		this.AnlDb2 = AnlDb2;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.Feature3")
	public com.mg.merp.account.model.EconomicSpecFeature getFeature3() {
		return this.Feature3;
	}

	public void setFeature3(
			com.mg.merp.account.model.EconomicSpecFeature Feature3) {
		this.Feature3 = Feature3;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.RemnAnl getRemnAnlKt() {
		return this.RemnAnlKt;
	}

	public void setRemnAnlKt(com.mg.merp.account.model.RemnAnl RemnAnlKt) {
		this.RemnAnlKt = RemnAnlKt;
	}

	/**
	 * 
	 */

	public com.mg.merp.account.model.EconomicOper getEconomicOper() {
		return this.EconomicOper;
	}

	public void setEconomicOper(
			com.mg.merp.account.model.EconomicOper EconomicOper) {
		this.EconomicOper = EconomicOper;
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

	public com.mg.merp.account.model.RemnDbKt getRemnKt() {
		return this.RemnKt;
	}

	public void setRemnKt(com.mg.merp.account.model.RemnDbKt RemnKt) {
		this.RemnKt = RemnKt;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlDb3")
	public com.mg.merp.account.model.AnlPlan getAnlDb3() {
		return this.AnlDb3;
	}

	public void setAnlDb3(com.mg.merp.account.model.AnlPlan AnlDb3) {
		this.AnlDb3 = AnlDb3;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlKt5")
	public com.mg.merp.account.model.AnlPlan getAnlKt5() {
		return this.AnlKt5;
	}

	public void setAnlKt5(com.mg.merp.account.model.AnlPlan AnlKt5) {
		this.AnlKt5 = AnlKt5;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlDb4")
	public com.mg.merp.account.model.AnlPlan getAnlDb4() {
		return this.AnlDb4;
	}

	public void setAnlDb4(com.mg.merp.account.model.AnlPlan AnlDb4) {
		this.AnlDb4 = AnlDb4;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlDb1")
	public com.mg.merp.account.model.AnlPlan getAnlDb1() {
		return this.AnlDb1;
	}

	public void setAnlDb1(com.mg.merp.account.model.AnlPlan AnlDb1) {
		this.AnlDb1 = AnlDb1;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AccDb")
	public com.mg.merp.account.model.AccPlan getAccDb() {
		return this.AccDb;
	}

	public void setAccDb(com.mg.merp.account.model.AccPlan AccDb) {
		this.AccDb = AccDb;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.Feature5")
	public com.mg.merp.account.model.EconomicSpecFeature getFeature5() {
		return this.Feature5;
	}

	public void setFeature5(
			com.mg.merp.account.model.EconomicSpecFeature Feature5) {
		this.Feature5 = Feature5;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AccBatchDb")
	public com.mg.merp.account.model.AccBatch getAccBatchDb() {
		return this.accBatchDb;
	}

	public void setAccBatchDb(com.mg.merp.account.model.AccBatch AccBatch) {
		this.accBatchDb = AccBatch;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AnlKt4")
	public com.mg.merp.account.model.AnlPlan getAnlKt4() {
		return this.AnlKt4;
	}

	public void setAnlKt4(com.mg.merp.account.model.AnlPlan AnlKt4) {
		this.AnlKt4 = AnlKt4;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.Quantity")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.SummaNat")
	public java.math.BigDecimal getSummaNat() {
		return this.SummaNat;
	}

	public void setSummaNat(java.math.BigDecimal SummaNat) {
		this.SummaNat = SummaNat;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.SummaCur")
	public java.math.BigDecimal getSummaCur() {
		return this.SummaCur;
	}

	public void setSummaCur(java.math.BigDecimal SummaCur) {
		this.SummaCur = SummaCur;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.CurCource")
	public java.math.BigDecimal getCurCource() {
		return this.CurCource;
	}

	public void setCurCource(java.math.BigDecimal CurCource) {
		this.CurCource = CurCource;
	}

	/**
	 * 
	 */
	@DataItemName("Account.EconSpec.AccBatchKt")
	public com.mg.merp.account.model.AccBatch getAccBatchKt() {
		return this.accBatchKt;
	}

	public void setAccBatchKt(com.mg.merp.account.model.AccBatch accBatchKt) {
		this.accBatchKt = accBatchKt;
	}
}