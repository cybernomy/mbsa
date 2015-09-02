/*
 * NormHead.java
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
package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Норма выдачи спецодежды"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: NormHead.java,v 1.8 2008/06/30 04:15:16 alikaev Exp $
 */
public class NormHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.personnelref.model.PrefPosition StfPosition;

	private com.mg.merp.personnelref.model.PrefJob StfJob;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String OvrNormName;

	private java.lang.Integer OvrNormTypeId;

	private java.util.Date OvrNormBeginDate;

	private java.util.Date OvrNormEndDate;

	private java.util.Set<NormSpec> normSpecs;

	// Constructors

	/** default constructor */
	public NormHead() {
	}

	/** constructor with id */
	public NormHead(int Id) {
		this.Id = Id;
	}

	// Property accessors
	
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public com.mg.merp.personnelref.model.PrefPosition getStfPosition() {
		return this.StfPosition;
	}

	public void setStfPosition(com.mg.merp.personnelref.model.PrefPosition PrefPosition) {
		this.StfPosition = PrefPosition;
	}

	public com.mg.merp.personnelref.model.PrefJob getStfJob() {
		return this.StfJob;
	}

	public void setStfJob(com.mg.merp.personnelref.model.PrefJob PrefJob) {
		this.StfJob = PrefJob;
	}

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("Overall.Name")
	public java.lang.String getOvrNormName() {
		return this.OvrNormName;
	}

	public void setOvrNormName(java.lang.String OvrNormName) {
		this.OvrNormName = OvrNormName;
	}

	public java.lang.Integer getOvrNormTypeId() {
		return this.OvrNormTypeId;
	}

	public void setOvrNormTypeId(java.lang.Integer OvrNormTypeId) {
		this.OvrNormTypeId = OvrNormTypeId;
	}

	@DataItemName("Overall.Norm.OvrNormBeginDate")
	public java.util.Date getOvrNormBeginDate() {
		return this.OvrNormBeginDate;
	}

	public void setOvrNormBeginDate(java.util.Date OvrNormBeginDate) {
		this.OvrNormBeginDate = OvrNormBeginDate;
	}

	@DataItemName("Overall.Norm.OvrNormEndDate")
	public java.util.Date getOvrNormEndDate() {
		return this.OvrNormEndDate;
	}

	public void setOvrNormEndDate(java.util.Date OvrNormEndDate) {
		this.OvrNormEndDate = OvrNormEndDate;
	}

	public java.util.Set<NormSpec> getNormSpecs() {
		return this.normSpecs;
	}

	public void setNormSpecs(java.util.Set<NormSpec> normSpecs) {
		this.normSpecs = normSpecs;
	}

}