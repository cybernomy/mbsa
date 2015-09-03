/*
 * AccountBookRest.java
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
package com.mg.merp.account.support.ui;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора бухгалтерских книг
 * 
 * @author leonova
 * @version $Id: AccountBookRest.java,v 1.3 2006/10/17 11:51:47 leonova Exp $ 
 */
public class AccountBookRest extends DefaultHierarhyRestrictionForm {
	
	@DataItemName("Account.Cond.BuyBook.InsertDate1")
	private Date insertDate1 = null;
	@DataItemName("Account.Cond.BuyBook.InsertDate2")
	private Date insertDate2 = null;
	@DataItemName("Account.Cond.BuyBook.Nds1Sum1")
	private BigDecimal nds1Sum1 = null;
	@DataItemName("Account.Cond.BuyBook.Nds1Sum2")
	private BigDecimal nds1Sum2 = null;	
	@DataItemName("Account.Cond.BuyBook.Nds2Sum1")
	private BigDecimal nds2Sum1 = null;
	@DataItemName("Account.Cond.BuyBook.Nds2Sum2")
	private BigDecimal nds2Sum2 = null;	
	@DataItemName("Account.Cond.BuyBook.Nds3Sum1")
	private BigDecimal nds3Sum1 = null;
	@DataItemName("Account.Cond.BuyBook.Nds3Sum2")
	private BigDecimal nds3Sum2 = null;		
	@DataItemName("Account.Cond.BuyBook.TotalSum2")
	private BigDecimal totalSum2 = null;	
	@DataItemName("Account.Cond.BuyBook.TotalSum1")
	private BigDecimal totalSum1 = null;	
	@DataItemName("Account.BuyBook.Provider")
	private Contractor contractorCode = null;
	@DataItemName("Account.Cond.BuyBook.DocFrom")
	private Date docDate1 = null;
	@DataItemName("Account.Cond.BuyBook.DocTill")
	private Date docDate2 = null;
	@DataItemName("Account.Cond.BuyBook.DocNumber")
	private String docNumber = null;
	@DataItemName("Account.Cond.BuyBook.DocType")
	private DocType docType = null;
	


	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.insertDate1 = null;
		this.insertDate2 = null;
		this.nds1Sum1 = null;
		this.nds1Sum2 = null;
		this.nds2Sum1 = null;
		this.nds2Sum2 = null;
		this.nds3Sum1 = null;
		this.nds3Sum2 = null;
		this.totalSum1 = null;	
		this.totalSum2 = null;
		this.contractorCode = null;
		this.docDate1 = null;	
		this.docDate2 = null;	
		this.docNumber = null;	
		this.docType = null;			
	}



	/**
	 * @return Returns the contractorCode.
	 */
	protected Contractor getContractorCode() {
		return contractorCode;
	}



	/**
	 * @return Returns the docDate1.
	 */
	protected Date getDocDate1() {
		return docDate1;
	}



	/**
	 * @return Returns the docDate2.
	 */
	protected Date getDocDate2() {
		return docDate2;
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
	 * @return Returns the insertDate1.
	 */
	protected Date getInsertDate1() {
		return insertDate1;
	}



	/**
	 * @return Returns the insertDate2.
	 */
	protected Date getInsertDate2() {
		return insertDate2;
	}



	/**
	 * @return Returns the nds1Sum1.
	 */
	protected BigDecimal getNds1Sum1() {
		return nds1Sum1;
	}



	/**
	 * @return Returns the nds1Sum2.
	 */
	protected BigDecimal getNds1Sum2() {
		return nds1Sum2;
	}



	/**
	 * @return Returns the nds2Sum1.
	 */
	protected BigDecimal getNds2Sum1() {
		return nds2Sum1;
	}



	/**
	 * @return Returns the nds2Sum2.
	 */
	protected BigDecimal getNds2Sum2() {
		return nds2Sum2;
	}



	/**
	 * @return Returns the nds3Sum1.
	 */
	protected BigDecimal getNds3Sum1() {
		return nds3Sum1;
	}



	/**
	 * @return Returns the nds3Sum2.
	 */
	protected BigDecimal getNds3Sum2() {
		return nds3Sum2;
	}



	/**
	 * @return Returns the totalSum1.
	 */
	protected BigDecimal getTotalSum1() {
		return totalSum1;
	}



	/**
	 * @return Returns the totalSum2.
	 */
	protected BigDecimal getTotalSum2() {
		return totalSum2;
	}
}
