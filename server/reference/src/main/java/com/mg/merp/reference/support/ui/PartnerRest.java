/*
 * PartnerRest.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;

/**
 * Форма условий отбора партнеров
 * 
 * @author leonova
 * @version $Id: PartnerRest.java,v 1.3 2006/10/18 10:50:12 leonova Exp $ 
 */
public class PartnerRest extends DefaultHierarhyRestrictionForm {

	@DataItemName("Reference.Contractor.Code")
	private String code = "";
	@DataItemName("Reference.Contractor.Name")
	private String fullName = "";
	@DataItemName("Reference.Partner.Inn")
	private String inn = "";
	@DataItemName("Reference.Cond.Partner.Account")
	private String bankAcc = "";
	@DataItemName("Reference.Bank.CorrAcc")
	private String corrAcc = "";
	@DataItemName("Reference.Bank.BIK.Name")
	private String bik = "";
//	@DataItemName("Reference.Cond.Partner.SupCreditHigh")
//	private BigDecimal supCreditHigh = BigDecimal.ZERO;
//	@DataItemName("Reference.Cond.Partner.CusCreditLow")
//	private BigDecimal cusCreditLow = BigDecimal.ZERO;
//	@DataItemName("Reference.Cond.Partner.CusCreditHigh")
//	private BigDecimal cusCreditHigh = BigDecimal.ZERO;
//	@DataItemName("Reference.Cond.Partner.SupCreditLow")
//	private BigDecimal supCreditLow = BigDecimal.ZERO;
//	@DataItemName("Reference.Cond.Partner.SupCreditCur")
//	private Currency supCreditCur = null;
//	@DataItemName("Reference.Cond.Partner.CusCreditCur")
//	private Currency cusCreditCur = null;	
//	@DataItemName("Reference.Cond.Partner.TermCusCreditLow")
//	private int termCusCreditLow = 0;
//	@DataItemName("Reference.Cond.Partner.TermSupCreditHigh")
//	private int termSupCreditHigh = 0;
//	@DataItemName("Reference.Cond.Partner.TermCusCreditHigh")
//	private int termCusCreditHigh = 0;
//	@DataItemName("Reference.Cond.Partner.TermSupCreditLow")
//	private int termSupCreditLow = 0;

	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.code = "";
		this.fullName = "";
		this.inn = "";
		this.bankAcc = "";
		this.corrAcc = "";
		this.bik = "";
//		this.supCreditHigh = BigDecimal.ZERO;
//		this.cusCreditLow = BigDecimal.ZERO;
//		this.cusCreditHigh = BigDecimal.ZERO;
//		this.supCreditLow = BigDecimal.ZERO;
//		this.supCreditCur = null;
//		this.cusCreditCur = null;
//		this.termCusCreditLow = 0;
//		this.termSupCreditHigh = 0;
//		this.termSupCreditLow = 0;
//		this.termCusCreditHigh = 0;		
	}
	/**
	 * @return Returns the bankAcc.
	 */
	public String getBankAcc() {
		return bankAcc;
	}
	/**
	 * @return Returns the bik.
	 */
	public String getBik() {
		return bik;
	}
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return Returns the corrAcc.
	 */
	public String getCorrAcc() {
		return corrAcc;
	}
	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @return Returns the inn.
	 */
	public String getInn() {
		return inn;
	}


}
