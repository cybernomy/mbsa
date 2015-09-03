/*
 * CustomsDeclaration.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * Модель бизнес-компонента "Грузовые таможенные декларации"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomsDeclaration.java,v 1.1 2007/01/17 11:50:58 sharapov Exp $
 */
@DataItemName("Reference.CustomsDeclaration") //$NON-NLS-1$
public class CustomsDeclaration extends PersistentObjectHibernate implements java.io.Serializable{

	private java.lang.Integer id;

	private com.mg.merp.core.model.SysClient sysClient;

	private java.lang.String number;

	private java.lang.String note;

	public CustomsDeclaration() {
	}

	public CustomsDeclaration(java.lang.Integer id) {
		this.id = id;
	}

	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	@DataItemName("Reference.CustomsDeclaration.Note") //$NON-NLS-1$
	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	@DataItemName("Reference.CustomsDeclaration.Number") //$NON-NLS-1$
	public java.lang.String getNumber() {
		return number;
	}

	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		this.sysClient = sysClient;
	}

}
