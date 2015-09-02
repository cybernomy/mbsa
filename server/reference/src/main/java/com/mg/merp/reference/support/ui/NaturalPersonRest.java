/*
 * NaturalPersonRest.java
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
package com.mg.merp.reference.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;

/**
 * Контроллер формы условия отбора физических лиц
 * 
 * @author leonova
 * @version $Id: NaturalPersonRest.java,v 1.5 2006/10/19 04:18:44 leonova Exp $ 
 */
public class NaturalPersonRest extends DefaultHierarhyRestrictionForm {

	@DataItemName("Reference.NaturalPerson.Name")
	private String name = "";
	@DataItemName("Reference.NaturalPerson.Surname")
	private String surName = "";
	@DataItemName("Reference.NaturalPerson.Patronymic")
	private String patronymic = "";
	@DataItemName("Reference.NaturalPerson.BornDate")
	private Date bornDate = null;
	@DataItemName("Reference.Cond.NaturalPerson.ActDateTill")
	private Date actDateTill = null;
	@DataItemName("Reference.Cond.NaturalPerson.ActDateFrom")
	private Date actDateFrom = null;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.name = "";
		this.surName = "";
		this.patronymic = "";
		this.bornDate = null;
		this.actDateTill = null;
		this.actDateFrom = null;		
	}

	/**
	 * @return Returns the actDateFrom.
	 */
	public Date getActDateFrom() {
		return actDateFrom;
	}

	/**
	 * @return Returns the actDateTill.
	 */
	public Date getActDateTill() {
		return actDateTill;
	}

	/**
	 * @return Returns the bornDate.
	 */
	public Date getBornDate() {
		return bornDate;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the patronymic.
	 */
	public String getPatronymic() {
		return patronymic;
	}

	/**
	 * @return Returns the surName.
	 */
	public String getSurName() {
		return surName;
	}
	
}
