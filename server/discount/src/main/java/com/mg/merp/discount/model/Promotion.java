/*
 * Promotion.java
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
package com.mg.merp.discount.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.core.model.SysClient;

/**
 * Модель бизнес-компонента "Рекламное мероприятие"
 * 
 * @author Artem V. Sharapov
 * @version $Id: Promotion.java,v 1.1 2007/10/30 14:02:22 sharapov Exp $
 */
public class Promotion extends PersistentObjectHibernate implements Serializable {
	
	// Fields
	
	private Integer id;
	
	private String name;
	
	private String code;
	
	private Date dateFrom;
	
	private Date dateTill;
	
	private Integer priority;
	
	private PromotionType promotionType;
		
	private String number;
	
	private Date dateApprove;
				
	private boolean isActive;
	
	private boolean isApplyDiscountGroup;
	
	private boolean isApplyDiscountOnDoc;
	
	private String comment;
			
	private Set<PromotionLine> promotionLines;
				
	private SysClient sysClient;
	
	
	// Default constructor
	public Promotion() {
	}
	
	// Constructor with id
	public Promotion(Integer id) {
		this.id = id;
	}

	
	// Property accessors
	
	/**
	 * @return the id
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@DataItemName("Discount.Promotion.Name") //$NON-NLS-1$
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	@DataItemName("Discount.Promotion.Code") //$NON-NLS-1$
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the priority
	 */
	@DataItemName("Discount.Promotion.Priority") //$NON-NLS-1$
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the number
	 */
	@DataItemName("Discount.Promotion.Number") //$NON-NLS-1$
	public String getNumber() {
		return this.number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the isActive
	 */
	@DataItemName("Discount.Promotion.IsActive") //$NON-NLS-1$
	public boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the comment
	 */
	@DataItemName("Discount.Promotion.Comment") //$NON-NLS-1$
	public String getComment() {
		return this.comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the sysClient
	 */
	public SysClient getSysClient() {
		return this.sysClient;
	}

	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(SysClient sysClient) {
		this.sysClient = sysClient;
	}

	/**
	 * @return the promotionLines
	 */
	public Set<PromotionLine> getPromotionLines() {
		return this.promotionLines;
	}

	/**
	 * @param promotionLines the promotionLines to set
	 */
	public void setPromotionLines(Set<PromotionLine> promotionLines) {
		this.promotionLines = promotionLines;
	}

	/**
	 * @return the dateFrom
	 */
	@DataItemName("Discount.Promotion.DateFrom") //$NON-NLS-1$
	public Date getDateFrom() {
		return this.dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTill
	 */
	@DataItemName("Discount.Promotion.DateTill") //$NON-NLS-1$
	public Date getDateTill() {
		return this.dateTill;
	}

	/**
	 * @param dateTill the dateTill to set
	 */
	public void setDateTill(Date dateTill) {
		this.dateTill = dateTill;
	}

	/**
	 * @return the dateApprove
	 */
	@DataItemName("Discount.Promotion.DateApprove") //$NON-NLS-1$
	public Date getDateApprove() {
		return this.dateApprove;
	}

	/**
	 * @param dateApprove the dateApprove to set
	 */
	public void setDateApprove(Date dateApprove) {
		this.dateApprove = dateApprove;
	}

	/**
	 * @return the promotionType
	 */
	public PromotionType getPromotionType() {
		return this.promotionType;
	}

	/**
	 * @param promotionType the promotionType to set
	 */
	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	/**
	 * @return the isApplyDiscountGroup
	 */
	@DataItemName("Discount.Promotion.IsApplyDiscountGroup") //$NON-NLS-1$
	public boolean getIsApplyDiscountGroup() {
		return this.isApplyDiscountGroup;
	}

	/**
	 * @param isApplyDiscountGroup the isApplyDiscountGroup to set
	 */
	public void setIsApplyDiscountGroup(boolean isApplyDiscountGroup) {
		this.isApplyDiscountGroup = isApplyDiscountGroup;
	}

	/**
	 * @return the isApplyDiscountOnDoc
	 */
	@DataItemName("Discount.Promotion.IsApplyDiscountOnDoc") //$NON-NLS-1$
	public boolean getIsApplyDiscountOnDoc() {
		return this.isApplyDiscountOnDoc;
	}

	/**
	 * @param isApplyDiscountOnDoc the isApplyDiscountOnDoc to set
	 */
	public void setIsApplyDiscountOnDoc(boolean isApplyDiscountOnDoc) {
		this.isApplyDiscountOnDoc = isApplyDiscountOnDoc;
	}

	
}
