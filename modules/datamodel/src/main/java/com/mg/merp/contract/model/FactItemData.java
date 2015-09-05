/*
 * FactItemData.java
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
package com.mg.merp.contract.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;

/**
 * Структура данных для создания фактического пункта контракта
 * 
 * @author Artem V. Sharapov
 * @version $Id: FactItemData.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
public class FactItemData {

	// Fields

	private DocHead docHead;
	private Date processDate;
	private BigDecimal performedSum;
	private ItemKind itemKind;
	private FactItemContractorSource contractorSource;
	private boolean isCreateSpec;
	private com.mg.merp.document.model.DocType сontractType;
	private String сontractNumber;
	private Date contractDate;
	private DocHead contract;
	

	/* Default consractor */
	public FactItemData() {
	}

	public FactItemData(DocHead docHead, Date processDate, BigDecimal performedSum, ItemKind itemKind, FactItemContractorSource contractorSource, boolean isCreateSpec, DocType сontractType, String сontractNumber, Date contractDate, DocHead contract) {
		this.docHead = docHead;
		this.processDate = processDate;
		this.performedSum = performedSum;
		this.itemKind = itemKind;
		this.contractorSource = contractorSource;
		this.isCreateSpec = isCreateSpec;
		this.сontractType = сontractType;
		this.сontractNumber = сontractNumber;
		this.contractDate = contractDate;
		this.contract = contract;
	}

	// Property accessors

	/**
	 * @return the contractorSource
	 */
	public FactItemContractorSource getContractorSource() {
		return contractorSource;
	}

	/**
	 * @param contractorSource the contractorSource to set
	 */
	public void setContractorSource(FactItemContractorSource contractorSource) {
		this.contractorSource = contractorSource;
	}

	/**
	 * @return the docHead
	 */
	public DocHead getDocHead() {
		return docHead;
	}

	/**
	 * @param docHead the docHead to set
	 */
	public void setDocHead(DocHead docHead) {
		this.docHead = docHead;
	}

	/**
	 * @return the isCreateSpec
	 */
	public boolean isCreateSpec() {
		return isCreateSpec;
	}

	/**
	 * @param isCreateSpec the isCreateSpec to set
	 */
	public void setCreateSpec(boolean isCreateSpec) {
		this.isCreateSpec = isCreateSpec;
	}

	/**
	 * @return the itemKind
	 */
	public ItemKind getItemKind() {
		return itemKind;
	}

	/**
	 * @param itemKind the itemKind to set
	 */
	public void setItemKind(ItemKind itemKind) {
		this.itemKind = itemKind;
	}

	/**
	 * @return the performedSum
	 */
	public BigDecimal getPerformedSum() {
		return performedSum;
	}

	/**
	 * @param performedSum the performedSum to set
	 */
	public void setPerformedSum(BigDecimal performedSum) {
		this.performedSum = performedSum;
	}

	/**
	 * @return the processDate
	 */
	public Date getProcessDate() {
		return processDate;
	}

	/**
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	/**
	 * @return the contractDate
	 */
	public Date getContractDate() {
		return contractDate;
	}

	/**
	 * @param contractDate the contractDate to set
	 */
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	/**
	 * @return the сontractNumber
	 */
	public String getсontractNumber() {
		return сontractNumber;
	}

	/**
	 * @param сontractNumber the сontractNumber to set
	 */
	public void setсontractNumber(String сontractNumber) {
		this.сontractNumber = сontractNumber;
	}

	/**
	 * @return the сontractType
	 */
	public com.mg.merp.document.model.DocType getсontractType() {
		return сontractType;
	}

	/**
	 * @param сontractType the сontractType to set
	 */
	public void setсontractType(com.mg.merp.document.model.DocType сontractType) {
		this.сontractType = сontractType;
	}

	/**
	 * @return the contract
	 */
	public DocHead getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(DocHead contract) {
		this.contract = contract;
	}

}
