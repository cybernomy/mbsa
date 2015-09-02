/*
 * OvrCard.java
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
import com.mg.framework.utils.StringUtils;

/**
 * Модель бизнес-компонента "Лицевые карточки сотрудников"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCard.java,v 1.9 2008/06/30 04:15:16 alikaev Exp $
 */
public class OvrCard extends com.mg.framework.service.PersistentObjectHibernate	implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.PrefPosition StfPosition;

	private com.mg.merp.personnelref.model.PrefJob StfJob;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.Personnel RefPersonnel;

	private com.mg.merp.reference.model.Contractor OrgUnit;

	private java.util.Date ActDateFrom;

	private java.util.Date ActDateTo;

	private java.lang.String OvrCardNumber;

	private java.lang.Integer OvrNormHeadId;

	private java.util.Set<OvrCardHist> cardHistories;

	private java.util.Set<Size> sizes;

	private java.util.Set<OvrCardDocumentLink> cardDocumentLinks;

	// Constructors

	/** default constructor */
	public OvrCard() {
	}

	/** constructor with id */
	public OvrCard(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	@DataItemName("Overall.Card.Position")
	public com.mg.merp.personnelref.model.PrefPosition getStfPosition() {
		return this.StfPosition;
	}

	public void setStfPosition(
			com.mg.merp.personnelref.model.PrefPosition PrefPosition) {
		this.StfPosition = PrefPosition;
	}

	@DataItemName("Overall.Card.Job")
	public com.mg.merp.personnelref.model.PrefJob getStfJob() {
		return this.StfJob;
	}

	public void setStfJob(com.mg.merp.personnelref.model.PrefJob PrefJob) {
		this.StfJob = PrefJob;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("PersonnelRef.Personnel")
	public com.mg.merp.personnelref.model.Personnel getRefPersonnel() {
		return this.RefPersonnel;
	}

	public void setRefPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.RefPersonnel = PrefPersonnel;
	}

	@DataItemName("Overall.Card.OrgUnit")
	public com.mg.merp.reference.model.Contractor getOrgUnit() {
		return this.OrgUnit;
	}

	public void setOrgUnit(com.mg.merp.reference.model.Contractor OrgUnit) {
		this.OrgUnit = OrgUnit;
	}

	@DataItemName("Overall.Card.ActDateFrom")
	public java.util.Date getActDateFrom() {
		return this.ActDateFrom;
	}

	public void setActDateFrom(java.util.Date ActdateFrom) {
		this.ActDateFrom = ActdateFrom;
	}

	@DataItemName("Overall.Card.ActDateTo")
	public java.util.Date getActDateTo() {
		return this.ActDateTo;
	}

	public void setActDateTo(java.util.Date ActdateTo) {
		this.ActDateTo = ActdateTo;
	}

	@DataItemName("Overall.Card.OvrCardNumber")
	public java.lang.String getOvrCardNumber() {
		return this.OvrCardNumber;
	}

	public void setOvrCardNumber(java.lang.String OvrCardNumber) {
		this.OvrCardNumber = OvrCardNumber;
	}

	public void setRefPersonnelName(java.lang.String RefPersonnelName) {
		// this.RefPersonnelName = RefPersonnelName;
	}

	public java.lang.String getRefPersonnelName() {
		if (getRefPersonnel() == null)
			return StringUtils.EMPTY_STRING;
		com.mg.merp.reference.model.NaturalPerson p = getRefPersonnel().getPerson();
		if (p == null)
			return StringUtils.EMPTY_STRING;
		return StringUtils.format("%s %s %s", p.getSurname(), p.getName(), p.getPatronymic());
	}

	public java.lang.Integer getOvrNormHeadId() {
		return this.OvrNormHeadId;
	}

	public void setOvrNormHeadId(java.lang.Integer OvrNormHeadId) {
		this.OvrNormHeadId = OvrNormHeadId;
	}

	/**
	 * @return cardHistories
	 */
	public java.util.Set<OvrCardHist> getCardHistories() {
		return cardHistories;
	}

	/**
	 * @param cardHistories задаваемое cardHistories
	 */
	public void setCardHistories(java.util.Set<OvrCardHist> ovrCardHists) {
		this.cardHistories = ovrCardHists;
	}

	/**
	 * @return sizes
	 */
	public java.util.Set<Size> getSizes() {
		return sizes;
	}

	/**
	 * @param sizes задаваемое sizes
	 */
	public void setSizes(java.util.Set<Size> ovrSizes) {
		this.sizes = ovrSizes;
	}

	/**
	 * @return cardDocumentLinks
	 */
	public java.util.Set<OvrCardDocumentLink> getCardDocumentLinks() {
		return cardDocumentLinks;
	}

	/**
	 * @param cardDocumentLinks задаваемое cardDocumentLinks
	 */
	public void setCardDocumentLinks(
			java.util.Set<OvrCardDocumentLink> ovrCardDocumentLinks) {
		this.cardDocumentLinks = ovrCardDocumentLinks;
	}

}