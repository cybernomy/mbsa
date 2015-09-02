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
 * Ñòðóêòóðà äàííûõ äëÿ ñîçäàíèÿ ôàêòè÷åñêîãî ïóíêòà êîíòðàêòà
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
	private com.mg.merp.document.model.DocType ñontractType;
	private String ñontractNumber;
	private Date contractDate;
	private DocHead contract;
	

	/* Default consractor */
	public FactItemData() {
	}

	public FactItemData(DocHead docHead, Date processDate, BigDecimal performedSum, ItemKind itemKind, FactItemContractorSource contractorSource, boolean isCreateSpec, DocType ñontractType, String ñontractNumber, Date contractDate, DocHead contract) {
		this.docHead = docHead;
		this.processDate = processDate;
		this.performedSum = performedSum;
		this.itemKind = itemKind;
		this.contractorSource = contractorSource;
		this.isCreateSpec = isCreateSpec;
		this.ñontractType = ñontractType;
		this.ñontractNumber = ñontractNumber;
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
	 * @return the ñontractNumber
	 */
	public String getñontractNumber() {
		return ñontractNumber;
	}

	/**
	 * @param ñontractNumber the ñontractNumber to set
	 */
	public void setñontractNumber(String ñontractNumber) {
		this.ñontractNumber = ñontractNumber;
	}

	/**
	 * @return the ñontractType
	 */
	public com.mg.merp.document.model.DocType getñontractType() {
		return ñontractType;
	}

	/**
	 * @param ñontractType the ñontractType to set
	 */
	public void setñontractType(com.mg.merp.document.model.DocType ñontractType) {
		this.ñontractType = ñontractType;
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
