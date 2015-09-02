/*
 * FinOperRest.java
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
package com.mg.merp.finance.support.ui;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

/**
 * Контроллер формы условий отбора финансовых операций
 * 
 * @author leonova
 * @version $Id: FinOperRest.java,v 1.6 2006/12/19 06:55:24 leonova Exp $
 */
public class FinOperRest extends FinRest {
	@DataItemName("Reference.Cond.DateFrom")
	private Date dateFrom = null;
	@DataItemName("Reference.Cond.DateTill")	
	private Date dateTill = null;	
	@DataItemName("Document.From")
	private Contractor fromCode = null;
	@DataItemName("Document.To")
	private Contractor toCode = null;	
	@DataItemName("Document.Cond.SumNatMin")
	private BigDecimal fromSumNat = null;
	@DataItemName("Document.Cond.SumNatMax")
	private BigDecimal toSumNat = null;	
	@DataItemName("Document.Cond.SumCurMin")
	private BigDecimal fromSumCur = null;
	@DataItemName("Document.Cond.SumCurMax")
	private BigDecimal toSumCur = null;	
	@DataItemName("Document.DocDate")	
	private Date contractDate = null;
	@DataItemName("Document.DocNumber")
	private String contractNumber = "";
	@DataItemName("Finance.Oper.ContractType")
	private DocType contractType = null;	
	@DataItemName("Finance.Oper.BaseType")
	private DocType baseDocType = null;
	@DataItemName("Document.DocDate")
	private Date baseDocDate = null;
	@DataItemName("Document.DocNumber")
	private String baseDocNumber = "";	
	@DataItemName("Finance.Oper.Type")
	private DocType docType = null;	
	@DataItemName("Document.DocDate")
	private Date docDate = null;
	@DataItemName("Document.DocNumber")
	private String docNumber = "";
	@DataItemName("Finance.Oper.Responsible")	
	private Contractor responsibleCode = null;
	private Currency curCode = null;
	private int kind = 0;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.dateFrom = null;		
		this.dateTill = null;
		this.fromCode = null;
		this.toCode = null;
		this.contractDate = null;
		this.contractNumber = "";
		this.contractType = null;	
		this.baseDocDate = null;
		this.baseDocNumber = "";
		this.baseDocType = null;
		this.docNumber = "";
		this.docDate = null;		
		this.docType = null;
		this.fromSumNat = null;
		this.toSumNat = null;
		this.fromSumCur = null;
		this.toSumCur = null;
		this.responsibleCode = null;
		this.curCode = null;
		this.kind = 0;
		
	
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
	 * @return Returns the curCode.
	 */
	protected Currency getCurCode() {
		return curCode;
	}

	/**
	 * @return Returns the dateFrom.
	 */
	protected Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @return Returns the dateTill.
	 */
	protected Date getDateTill() {
		return dateTill;
	}

	/**
	 * @return Returns the docDate.
	 */
	protected Date getDocDate() {
		return docDate;
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
	 * @return Returns the fromSumCur.
	 */
	protected BigDecimal getFromSumCur() {
		return fromSumCur;
	}

	/**
	 * @return Returns the fromSumNat.
	 */
	protected BigDecimal getFromSumNat() {
		return fromSumNat;
	}

	/**
	 * @return Returns the kind.
	 */
	protected int getKind() {
		return kind;
	}

	/**
	 * @return Returns the responsibleCode.
	 */
	protected Contractor getResponsibleCode() {
		return responsibleCode;
	}


	/**
	 * @return Returns the toCode.
	 */
	protected Contractor getToCode() {
		return toCode;
	}

	/**
	 * @return Returns the toSumCur.
	 */
	protected BigDecimal getToSumCur() {
		return toSumCur;
	}

	/**
	 * @return Returns the toSumNat.
	 */
	protected BigDecimal getToSumNat() {
		return toSumNat;
	}


}
