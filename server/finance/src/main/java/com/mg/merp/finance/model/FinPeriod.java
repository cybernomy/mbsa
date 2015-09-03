/*
 * FinPeriod.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Периоды финансового учета"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: FinPeriod.java,v 1.7 2006/12/26 12:38:16 sharapov Exp $
 */
@DataItemName("Finance.FinPeriod") //$NON-NLS-1$
public class FinPeriod extends com.mg.framework.service.PersistentObjectHibernate 
implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String PName;

	private java.util.Date DateFrom;

	private java.util.Date DateTo;

	private java.lang.String WhoClosed;

	private java.util.Date DateClose;

	// Constructors

	/** default constructor */
	public FinPeriod() {
	}

	/** constructor with id */
	public FinPeriod(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	/**
	 * @return Id - идентификатор сущности
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	/**
	 * @param Id
	 */
	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * @return SysClient
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	/**
	 * @param SysClient
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * @return java.lang.String - Наименование фин. периода
	 */
	@DataItemName("Finance.Period.PName") //$NON-NLS-1$
	public java.lang.String getPName() {
		return this.PName;
	}

	/**
	 * @param Pname -
	 *            Наименование фин. периода
	 */
	public void setPName(java.lang.String Pname) {
		this.PName = Pname;
	}

	/**
	 * @return java.util.Date - Дата начала фин. периода
	 */
	@DataItemName("Finance.Period.DateFrom") //$NON-NLS-1$
	public java.util.Date getDateFrom() {
		return this.DateFrom;
	}

	/**
	 * @param Datefrom -
	 *            Дата начала фин. периода
	 */
	public void setDateFrom(java.util.Date Datefrom) {
		this.DateFrom = Datefrom;
	}

	/**
	 * @return java.util.Date - Дата окончания фин. периода
	 */
	@DataItemName("Finance.Period.DateTo") //$NON-NLS-1$
	public java.util.Date getDateTo() {
		return this.DateTo;
	}

	/**
	 * @param Dateto -
	 *            Дата окончания фин. периода
	 */
	public void setDateTo(java.util.Date Dateto) {
		this.DateTo = Dateto;
	}

	/**
	 * @return java.lang.String - Кто закрыл фин.период
	 */
	@DataItemName("Finance.Period.WhoClosed") //$NON-NLS-1$
	public java.lang.String getWhoClosed() {
		return this.WhoClosed;
	}

	/**
	 * @param Whoclosed -
	 *            Кто закрыл фин.период
	 */
	public void setWhoClosed(java.lang.String Whoclosed) {
		this.WhoClosed = Whoclosed;
	}

	/**
	 * @return java.util.Date - дата закрытия фин. периода
	 */
	@DataItemName("Finance.Period.DateClose") //$NON-NLS-1$
	public java.util.Date getDateClose() {
		return this.DateClose;
	}

	/**
	 * @param Dateclose -
	 *            дата закрытия фин. периода
	 */
	public void setDateClose(java.util.Date Dateclose) {
		this.DateClose = Dateclose;
	}
}