/*
 * DocumentSpecTax.java
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
package com.mg.merp.document.model;

import java.io.Serializable;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * Налог на спецификации документа
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentSpecTax.java,v 1.3 2006/12/03 12:42:16 safonov Exp $
 */
public class DocumentSpecTax extends PersistentObjectHibernate implements
		Serializable {

	// Fields

	private java.lang.Integer id;

	private com.mg.merp.reference.model.Tax tax;

	private com.mg.merp.document.model.DocSpec docSpec;

	private java.math.BigDecimal sumElement;

	private java.math.BigDecimal priceElement;

	// Constructors

	/** default constructor */
	public DocumentSpecTax() {
	}

	/** constructor with id */
	public DocumentSpecTax(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors

	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * получить налог
	 * 
	 * @return	налог
	 */
	public com.mg.merp.reference.model.Tax getTax() {
		return this.tax;
	}

	public void setTax(com.mg.merp.reference.model.Tax Tax) {
		this.tax = Tax;
	}

	/**
	 * получить спецификацию документа
	 * 
	 * @return	спецификация документа
	 */
	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.docSpec;
	}

	public void setDocSpec(com.mg.merp.document.model.DocSpec DocSpec) {
		this.docSpec = DocSpec;
	}

	/**
	 * получить сумму не включенного налога
	 * 
	 * @return	сумма налога
	 */
	@DataItemName("Document.DocumentSpecTax.Sum")
	public java.math.BigDecimal getSumElement() {
		return this.sumElement;
	}

	public void setSumElement(java.math.BigDecimal Summ) {
		this.sumElement = Summ;
	}

	/**
	 * получить величину не включенного налога в цене
	 * 
	 * @return	величина налога в цене
	 */
	@DataItemName("Document.DocumentSpecTax.Price")
	public java.math.BigDecimal getPriceElement() {
		return this.priceElement;
	}

	public void setPriceElement(java.math.BigDecimal Price) {
		this.priceElement = Price;
	}

}
