/*
 * PaymentRest.java
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
package com.mg.merp.paymentalloc.support.ui;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.model.DocType;
import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора  журнала платежей
 * 
 * @author leonova
 * @version $Id: PaymentRest.java,v 1.4 2008/03/04 11:26:59 alikaev Exp $ 
 */
public class PaymentRest extends DefaultHierarhyRestrictionForm {

	@DataItemName("Reference.Cond.DateFrom") //$NON-NLS-1$
	private Date date1 = null;
	@DataItemName("Reference.Cond.DateTill") //$NON-NLS-1$
	private Date date2 = null;
	@DataItemName("PaymentAlloc.Cond.SummaFrom") //$NON-NLS-1$
	private BigDecimal sum1 = null;
	@DataItemName("PaymentAlloc.Cond.SummaTill") //$NON-NLS-1$
	private BigDecimal sum2 = null;
	@DataItemName("PaymentAlloc.Payment.ContractorFrom") //$NON-NLS-1$
	private Contractor fromCode = null;
	@DataItemName("PaymentAlloc.Payment.ContractorTo")	 //$NON-NLS-1$
	private Contractor toCode = null;
	@DataItemName("Document.DocDate") //$NON-NLS-1$
	private Date baseDocDate = null;
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	private String baseDocNumber = StringUtils.EMPTY_STRING;	
	private DocType baseDocType = null;
	@DataItemName("Document.DocDate")	 //$NON-NLS-1$
	private Date contractDate = null;
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	private String contractNumber = StringUtils.EMPTY_STRING;	
	private DocType contractType = null;	
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	private String docNumber = StringUtils.EMPTY_STRING;
	@DataItemName("Document.DocDate") //$NON-NLS-1$
	private Date docDate = null;	
	private DocType docType = null;	
	@DataItemName("Document.DocDate")	 //$NON-NLS-1$
	private Date linkedDate = null;
	@DataItemName("Document.DocNumber") //$NON-NLS-1$
	private String linkedNumber = StringUtils.EMPTY_STRING;	
	private DocType linkedType = null;
	@DataItemName("PaymentAlloc.Payment.Name") //$NON-NLS-1$
	private String name = StringUtils.EMPTY_STRING;
	@DataItemName("PaymentAlloc.Payment.Description") //$NON-NLS-1$
	private String description = StringUtils.EMPTY_STRING;
	private int kind = 0;
	private int allocKind = 0;
	@DataItemName("PaymentAlloc.Cond.DocGroupCode") //$NON-NLS-1$
	private DocGroup docGroupCode = null;
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.date1 = null;
		this.date2 = null;
		this.sum1 = null;
		this.sum2 = null;
		this.fromCode = null;
		this.toCode = null;
		this.baseDocDate = null;
		this.baseDocNumber = StringUtils.EMPTY_STRING;
		this.baseDocType = null;
		this.contractDate = null;
		this.contractNumber = StringUtils.EMPTY_STRING;
		this.contractType = null;	
		this.docNumber = StringUtils.EMPTY_STRING;
		this.docDate = null;		
		this.docType = null;	
		this.linkedNumber = StringUtils.EMPTY_STRING;
		this.linkedDate = null;		
		this.linkedType = null;	
		this.name = StringUtils.EMPTY_STRING;
		this.description = StringUtils.EMPTY_STRING;
		this.kind = 0;
		this.allocKind = 0;
		this.docGroupCode = null;
	}

	/**
	 * @return Returns the allocKind.
	 */
	protected int getAllocKind() {
		return allocKind;
	}

	/**
	 * @return Returns the baseDocDate.
	 */
	protected Date getBaseDocDate() {
		return baseDocDate;
	}

	/**
	 * @return Returns the baseDocNumber.
	 */
	protected String getBaseDocNumber() {
		return baseDocNumber;
	}

	/**
	 * @return Returns the baseDocType.
	 */
	protected DocType getBaseDocType() {
		return baseDocType;
	}

	/**
	 * @return Returns the contractDate.
	 */
	protected Date getContractDate() {
		return contractDate;
	}

	/**
	 * @return Returns the contractNumber.
	 */
	protected String getContractNumber() {
		return contractNumber;
	}

	/**
	 * @return Returns the contractType.
	 */
	protected DocType getContractType() {
		return contractType;
	}

	/**
	 * @return Returns the date1.
	 */
	protected Date getDate1() {
		return date1;
	}

	/**
	 * @return Returns the date2.
	 */
	protected Date getDate2() {
		return date2;
	}

	/**
	 * @return Returns the description.
	 */
	protected String getDescription() {
		return description;
	}

	/**
	 * @return Returns the docDate.
	 */
	protected Date getDocDate() {
		return docDate;
	}

	/**
	 * @return Returns the docGroupCode.
	 */
	protected DocGroup getDocGroupCode() {
		return docGroupCode;
	}

	/**
	 * @return Returns the docNumber.
	 */
	protected String getDocNumber() {
		return docNumber;
	}

	/**
	 * @return Returns the docType.
	 */
	protected DocType getDocType() {
		return docType;
	}

	/**
	 * @return Returns the fromCode.
	 */
	protected Contractor getFromCode() {
		return fromCode;
	}

	/**
	 * @return Returns the kind.
	 */
	protected int getKind() {
		return kind;
	}

	/**
	 * @return Returns the linkedDate.
	 */
	protected Date getLinkedDate() {
		return linkedDate;
	}

	/**
	 * @return Returns the linkedNumber.
	 */
	protected String getLinkedNumber() {
		return linkedNumber;
	}

	/**
	 * @return Returns the linkedType.
	 */
	protected DocType getLinkedType() {
		return linkedType;
	}

	/**
	 * @return Returns the name.
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @return Returns the sum1.
	 */
	protected BigDecimal getSum1() {
		return sum1;
	}

	/**
	 * @return Returns the sum2.
	 */
	protected BigDecimal getSum2() {
		return sum2;
	}

	/**
	 * @return Returns the toCode.
	 */
	protected Contractor getToCode() {
		return toCode;
	}

}