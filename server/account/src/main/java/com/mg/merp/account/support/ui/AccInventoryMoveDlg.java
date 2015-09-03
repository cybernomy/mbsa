/*
 * AccInventoryMoveDlg.java
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

import java.util.Date;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.InvLocation;
import com.mg.merp.account.support.Messages;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер ввода данных для проведения операции перемещение инвентарной карточки
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccInventoryMoveDlg.java,v 1.1 2008/04/29 05:29:04 alikaev Exp $
 */
public class AccInventoryMoveDlg extends DefaultWizardDialog {

	private String dialogTitle;
	
	private Date revalDate;	
	
	private Contractor contractor;
	
	private Date inOperDate;
	
	private String inOperDocNum;
	
	private InvLocation invLocation;

	private DocType docType;
	
	private String docNumber;
	
	private Date docDate;

	private AccPlan AccPlan;

	@DataItemName("Account.Inventory.Anl1")
	private AnlPlan anl1;

	@DataItemName("Account.Inventory.Anl2")
	private AnlPlan anl2;
	
	@DataItemName("Account.Inventory.Anl3")
	private AnlPlan anl3;
	
	@DataItemName("Account.Inventory.Anl4")
	private AnlPlan anl4;
	
	@DataItemName("Account.Inventory.Anl5")
	private AnlPlan anl5;

	private AccPlan AccKt;
	
	@DataItemName("Account.EconSpec.AnlKt1")
	private AnlPlan anlKt1;

	@DataItemName("Account.EconSpec.AnlKt2")
	private AnlPlan anlKt2;
	
	@DataItemName("Account.EconSpec.AnlKt3")
	private AnlPlan anlKt3;
	
	@DataItemName("Account.EconSpec.AnlKt4")
	private AnlPlan anlKt4;
	
	@DataItemName("Account.EconSpec.AnlKt5")
	private AnlPlan anlKt5;

	public AccInventoryMoveDlg() {
	}

	public Date getRevalDate() {
		return revalDate;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public Date getInOperDate() {
		return inOperDate;
	}

	public String getInOperDocNum() {
		return inOperDocNum;
	}

	public InvLocation getInvLocation() {
		return invLocation;
	}

	public DocType getDocType() {
		return docType;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public Date getDocDate() {
		return docDate;
	}

	public AccPlan getAccPlan() {
		return AccPlan;
	}

	public AnlPlan getAnl1() {
		return anl1;
	}

	public AnlPlan getAnl2() {
		return anl2;
	}

	public AnlPlan getAnl3() {
		return anl3;
	}

	public AnlPlan getAnl4() {
		return anl4;
	}

	public AnlPlan getAnl5() {
		return anl5;
	}

	public AccPlan getAccKt() {
		return AccKt;
	}

	public AnlPlan getAnlKt1() {
		return anlKt1;
	}

	public AnlPlan getAnlKt2() {
		return anlKt2;
	}

	public AnlPlan getAnlKt3() {
		return anlKt3;
	}

	public AnlPlan getAnlKt4() {
		return anlKt4;
	}

	public AnlPlan getAnlKt5() {
		return anlKt5;
	}

	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		view.setTitle(this.dialogTitle);
		super.doOnRun();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		if (this.revalDate == null || this.contractor == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_INVENTORY_MOVE_CHOOSE_DATE_CONTRACTOR));
		else
			super.onActionOk(event);
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}
	
}
