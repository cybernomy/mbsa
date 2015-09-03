/*
 * AmortizationMt.java
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
package com.mg.merp.account.support.ui;

import java.math.BigDecimal;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.model.AmCode;
import com.mg.merp.account.model.Amortization;
import com.mg.merp.account.model.Inventory;
import com.mg.merp.account.support.ConfigurationHelper;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы поддержки бизнес-компонента "Ведомость начисления амортизации"
 *  
 * @author Konstantin S. Alikaev
 * @version $Id: AmortizationMt.java,v 1.1 2008/05/08 09:04:14 alikaev Exp $
 */
public class AmortizationMt extends DefaultMaintenanceForm {

	@DataItemName("Account.InvHead.GroupNum")
	private String groupNum;

	@DataItemName("Account.InvHead.CardNum")
	private String cardNum;

	@DataItemName("Account.InvHead.ObjNum")
	private String objNum;
	
	private Contractor contractor;
	
	private Catalog catalog;
	
	private AmCode amCode;
	
	private BigDecimal sumFactor;
	
	public AmortizationMt() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		Amortization amortization = (Amortization) getEntity();
		Inventory inventory = amortization.getInventory();
		if (inventory != null) {
			this.groupNum = inventory.getGroupNum();
			this.cardNum = inventory.getCardNum();
			this.objNum = inventory.getObjNum();
			this.contractor = inventory.getContractor();
			this.catalog = inventory.getCatalog();
		}
		this.sumFactor = MathUtils.subtractNullable(amortization.getSumTotal(), amortization.getSumAdd(), new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec()));
		this.amCode = ServerUtils.getPersistentManager().find(AmCode.class, (amortization.getAmCodeId()));
		super.doOnRun();
	}

	public String getGroupNum() {
		return groupNum;
	}

	public String getCardNum() {
		return cardNum;
	}

	public String getObjNum() {
		return objNum;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public AmCode getAmCode() {
		return amCode;
	}

	public BigDecimal getSumFactor() {
		return sumFactor;
	}
	
}
