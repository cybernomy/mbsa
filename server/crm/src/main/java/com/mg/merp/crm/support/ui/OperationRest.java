/*
 * OperationRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.crm.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.crm.model.Contact;
import com.mg.merp.crm.model.OperationState;
import com.mg.merp.crm.model.OperationStatusKind;
import com.mg.merp.crm.model.Relation;
import com.mg.merp.crm.model.User;

/**
 * Контроллер формы условий отбора
 * 
 * @author leonova
 * @version $Id: OperationRest.java,v 1.2 2006/09/06 05:23:43 leonova Exp $ 
 */
public class OperationRest extends DefaultRestrictionForm {

	private Relation relationName = null;
	private Contact contactCode = null; 
	@DataItemName("CRM.Cond.Operation.CreateFrom")
	private Date createFrom = null;
	@DataItemName("CRM.Cond.Operation.CreateTill")
	private Date createTill = null;
	@DataItemName("CRM.Cond.Operation.PlanFrom")
	private Date planFrom = null;
	@DataItemName("CRM.Cond.Operation.PlanTill")
	private Date planTill = null;
	@DataItemName("CRM.Cond.Operation.FactFrom")
	private Date factFrom = null;
	@DataItemName("CRM.Cond.Operation.FactTill")
	private Date factTill = null;
	@DataItemName("CRM.Operation.Responsible")
	private User responsible = null; 
	@DataItemName("CRM.Operation.Curator")
	private User curator = null;
	private OperationStatusKind status = null;
	private OperationState state = null; 
	@DataItemName("CRM.Cond.Operation.Owner")
	private User owner = null;
	private int planState = 0;
	private int operationState = 0;
	
	@Override
	protected void doClearRestrictionItem() {
		this.relationName = null;
		this.contactCode = null;
		this.planFrom = null;
		this.planTill = null;
		this.createFrom = null;
		this.createTill = null;
		this.factFrom = null;
		this.factTill = null;
		this.responsible = null;
		this.curator = null;
		this.state = null;
		this.status = null;
		this.owner = null;
		this.planState = 0;
		this.operationState = 0;
	}

	/**
	 * @return Returns the contactCode.
	 */
	protected Contact getContactCode() {
		return contactCode;
	}

	/**
	 * @return Returns the createFrom.
	 */
	protected Date getCreateFrom() {
		return createFrom;
	}

	/**
	 * @return Returns the createTill.
	 */
	protected Date getCreateTill() {
		return createTill;
	}

	/**
	 * @return Returns the curator.
	 */
	protected User getCurator() {
		return curator;
	}

	/**
	 * @return Returns the factFrom.
	 */
	protected Date getFactFrom() {
		return factFrom;
	}

	/**
	 * @return Returns the factTill.
	 */
	protected Date getFactTill() {
		return factTill;
	}

	/**
	 * @return Returns the operationState.
	 */
	protected int getOperationState() {
		return operationState;
	}

	/**
	 * @return Returns the owner.
	 */
	protected User getOwner() {
		return owner;
	}

	/**
	 * @return Returns the planFrom.
	 */
	protected Date getPlanFrom() {
		return planFrom;
	}

	/**
	 * @return Returns the planState.
	 */
	protected int getPlanState() {
		return planState;
	}

	/**
	 * @return Returns the planTill.
	 */
	protected Date getPlanTill() {
		return planTill;
	}

	/**
	 * @return Returns the relationName.
	 */
	protected Relation getRelationName() {
		return relationName;
	}

	/**
	 * @return Returns the responsible.
	 */
	protected User getResponsible() {
		return responsible;
	}

	/**
	 * @return Returns the state.
	 */
	protected OperationState getState() {
		return state;
	}

	/**
	 * @return Returns the status.
	 */
	protected OperationStatusKind getStatus() {
		return status;
	}


}