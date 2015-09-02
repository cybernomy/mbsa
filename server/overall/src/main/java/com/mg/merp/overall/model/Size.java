/*
 * Size.java
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
 * Модель бизнес-компонента "Размеры"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: Size.java,v 1.4 2008/06/30 04:15:16 alikaev Exp $
 */
public class Size extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.overall.model.OvrCard OvrCard;

	private com.mg.merp.core.model.SysClient SysClient;

	private CatalogGroupsType CatalogGroupsTypeId;

	private java.lang.String ClothesSize;

	private java.lang.String ShoesSize;

	private java.lang.String HatSize;

	private java.lang.String GasMaskSize;

	private java.lang.String RespiratorSize;

	private java.lang.String MittensSize;

	private java.lang.String GlovesSize;

	// Constructors

	/** default constructor */
	public Size() {
	}

	/** constructor with id */
	public Size(java.lang.Integer Id) {
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

	public com.mg.merp.overall.model.OvrCard getOvrCard() {
		return this.OvrCard;
	}

	public void setOvrCard(com.mg.merp.overall.model.OvrCard OvrCard) {
		this.OvrCard = OvrCard;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	public CatalogGroupsType getCatalogGroupsTypeId() {
		return this.CatalogGroupsTypeId;
	}

	public void setCatalogGroupsTypeId(CatalogGroupsType CatalogGroupsTypeId) {
		this.CatalogGroupsTypeId = CatalogGroupsTypeId;
	}

	@DataItemName("Overall.Card.ClothesSize")
	public java.lang.String getClothesSize() {
		return this.ClothesSize;
	}

	public void setClothesSize(java.lang.String ClothesSize) {
		this.ClothesSize = ClothesSize;
	}

	@DataItemName("Overall.Card.ShoesSize")
	public java.lang.String getShoesSize() {
		return this.ShoesSize;
	}

	public void setShoesSize(java.lang.String ShoesSize) {
		this.ShoesSize = ShoesSize;
	}

	@DataItemName("Overall.Card.HatSize")
	public java.lang.String getHatSize() {
		return this.HatSize;
	}

	public void setHatSize(java.lang.String HatSize) {
		this.HatSize = HatSize;
	}

	@DataItemName("Overall.Card.GasMaskSize")
	public java.lang.String getGasMaskSize() {
		return this.GasMaskSize;
	}

	public void setGasMaskSize(java.lang.String GasMaskSize) {
		this.GasMaskSize = GasMaskSize;
	}

	@DataItemName("Overall.Card.RespiratorSize")
	public java.lang.String getRespiratorSize() {
		return this.RespiratorSize;
	}

	public void setRespiratorSize(java.lang.String RespiratorSize) {
		this.RespiratorSize = RespiratorSize;
	}

	@DataItemName("Overall.Card.MittensSize")
	public java.lang.String getMittensSize() {
		return this.MittensSize;
	}

	public void setMittensSize(java.lang.String MittensSize) {
		this.MittensSize = MittensSize;
	}

	@DataItemName("Overall.Card.GlovesSize")
	public java.lang.String getGlovesSize() {
		return this.GlovesSize;
	}

	public void setGlovesSize(java.lang.String GlovesSize) {
		this.GlovesSize = GlovesSize;
	}

}