/*
 * FeeSummarySpec.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Спецификация сводов н/у по аналитике" 
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeSummarySpec.java,v 1.5 2007/08/27 06:17:21 sharapov Exp $
 */
public class FeeSummarySpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

	// Fields

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

	// Constructors

	/** default constructor */
	public FeeSummarySpec() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Salary.FeeModel.CostsAnl1") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
		return this.CostsAnl1;
	}

	public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
		this.CostsAnl1 = PrefCostsAnl;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeModel.CostsAnl2") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
		return this.CostsAnl2;
	}

	public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
		this.CostsAnl2 = PrefCostsAnl_1;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeModel.CostsAnl3") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
		return this.CostsAnl3;
	}

	public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
		this.CostsAnl3 = PrefCostsAnl_2;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeModel.CostsAnl4") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
		return this.CostsAnl4;
	}

	public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
		this.CostsAnl4 = PrefCostsAnl_3;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeModel.CostsAnl5") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
		return this.CostsAnl5;
	}

	public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
		this.CostsAnl5 = PrefCostsAnl_4;
	}

}