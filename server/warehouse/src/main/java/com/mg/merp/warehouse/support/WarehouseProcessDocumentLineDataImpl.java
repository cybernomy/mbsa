/**
 * WarehouseProcessDocumentLineDataImpl.java
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
package com.mg.merp.warehouse.support;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.MeasureControl;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Реализация данных по отработке
 * 
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessDocumentLineDataImpl.java,v 1.1 2008/04/18 15:22:11 safonov Exp $
 */
public class WarehouseProcessDocumentLineDataImpl implements
		WarehouseProcessDocumentLineData {
	private DocumentSpecItem documentSpecItem;
	private Catalog catalog;
	private DocSpec docSpec;
	private Contractor srcMol;
	private Contractor srcStock;
	private Contractor dstMol;
	private Contractor dstStock;
	private Measure measure1;
	private Measure measure2;
	private BigDecimal quantity1;
	private BigDecimal quantity2;
	private BigDecimal price;
	private BigDecimal price1;
	private BigDecimal sum;
	private BigDecimal sum1;
	private Date processDate;

	public WarehouseProcessDocumentLineDataImpl(DocumentSpecItem documentSpecItem, Date processDate) {
		this.documentSpecItem = documentSpecItem;
		docSpec = documentSpecItem.getDocSpec();
		this.catalog = docSpec.getCatalog();
		this.srcMol = docSpec.getSrcMol() != null ? docSpec.getSrcMol() : docSpec.getDocHead().getSrcMol();
		this.srcStock = docSpec.getSrcStock() != null ? docSpec.getSrcStock() : docSpec.getDocHead().getSrcStock();
		this.dstMol = docSpec.getDstMol() != null ? docSpec.getDstMol() : docSpec.getDocHead().getDstMol();
		this.dstStock = docSpec.getDstStock() != null ? docSpec.getDstStock() : docSpec.getDocHead().getDstStock();
		this.measure1 = docSpec.getMeasure1();
		this.measure2 = docSpec.getMeasure2();
		this.quantity1 = documentSpecItem.getPerformedQuantity1();
		this.quantity2 = documentSpecItem.getPerformedQuantity2();
		this.price = docSpec.getPrice() != null ? docSpec.getPrice() : BigDecimal.ZERO;
		this.price1 = docSpec.getPrice1() != null ? docSpec.getPrice1() : BigDecimal.ZERO;
		this.sum = docSpec.getSumma() != null ? docSpec.getSumma() : BigDecimal.ZERO;
		this.sum1 = docSpec.getSumma1() != null ? docSpec.getSumma1() : BigDecimal.ZERO;
		this.processDate = processDate;
		convertQuantitiesAndPrices(docSpec, processDate);
	}

	private void convertQuantitiesAndPrices(DocSpec docSpec, Date processDate) {
		//если не совпадают ЕИ в спецификации документа и в каталоге, то преобразуем количества
		//к ЕИ каталога, т.к. склад хранит в ЕИ каталога
		if ((docSpec.getMeasure1().getId() != docSpec.getCatalog().getMeasure1().getId())
				|| (docSpec.getMeasure2() != null && docSpec.getCatalog().getMeasure2() != null
				&& (docSpec.getMeasure2().getId() != docSpec.getCatalog().getMeasure2().getId()))) {
			MeasureConversionServiceLocal convers = (MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate()
					.getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME);
			BigDecimal newQuantity = convers.conversion(docSpec.getMeasure1(), docSpec.getCatalog().getMeasure1(),
					docSpec.getCatalog(), processDate, this.quantity1);
			BigDecimal measureFactor = (MathUtils.compareToZero(newQuantity) == 1 ? this.quantity1.divide(newQuantity) : BigDecimal.ONE);
			this.quantity1 = newQuantity;
			this.price = this.price.multiply(measureFactor);

			// на складах в 2х количествах учитываем только "учетно/весовые"
			// для остальных 2е количество всегда = 0
			if (this.quantity2 != null && docSpec.getPrice1() != null
					&& (docSpec.getCatalog().getMeasureControl() == MeasureControl.CATCHWEIGHT)) {

				this.quantity2 = convers.conversion(docSpec.getMeasure2(),	docSpec.getCatalog().getMeasure2(),
						docSpec.getCatalog(), processDate, this.quantity2);
				this.price1 = this.price1.multiply(measureFactor);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getDocumentSpecItem()
	 */
	public DocumentSpecItem getDocumentSpecItem() {
		return this.documentSpecItem;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getCatalog()
	 */
	public Catalog getCatalog() {
		return this.catalog;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getDocumentSpec()
	 */
	public DocSpec getDocumentSpec() {
		return this.docSpec;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getDstMol()
	 */
	public Employees getDstMol() {
		if (this.dstMol != null && !(this.dstMol instanceof Employees))
			this.dstMol = ServerUtils.getPersistentManager().find(Employees.class, this.dstMol.getId());
		return (Employees) this.dstMol;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getDstStock()
	 */
	public Warehouse getDstStock() {
		if (this.dstStock != null && !(this.dstStock instanceof Warehouse))
			this.dstStock = ServerUtils.getPersistentManager().find(Warehouse.class, this.dstStock.getId());
		return (Warehouse) this.dstStock;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getMeasure1()
	 */
	public Measure getMeasure1() {
		return this.measure1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getMeasure2()
	 */
	public Measure getMeasure2() {
		return this.measure2;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getPrice()
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getPrice1()
	 */
	public BigDecimal getPrice1() {
		return this.price1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getQuantity1()
	 */
	public BigDecimal getQuantity1() {
		return this.quantity1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getQuantity2()
	 */
	public BigDecimal getQuantity2() {
		return this.quantity2;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getSrcMol()
	 */
	public Employees getSrcMol() {
		if (this.srcMol != null && !(this.srcMol instanceof Employees))
			this.srcMol = ServerUtils.getPersistentManager().find(Employees.class, this.srcMol.getId());
		return (Employees) this.srcMol;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getSrcStock()
	 */
	public Warehouse getSrcStock() {
		if (this.srcStock != null && !(this.srcStock instanceof Warehouse))
			this.srcStock = ServerUtils.getPersistentManager().find(Warehouse.class, this.srcStock.getId());
		return (Warehouse) this.srcStock;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getSum()
	 */
	public BigDecimal getSum() {
		return this.sum;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getSum1()
	 */
	public BigDecimal getSum1() {
		return this.sum1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessDocumentLineData#getProcessDate()
	 */
	public Date getProcessDate() {
		return this.processDate;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	/**
	 * @param srcMol the srcMol to set
	 */
	public void setSrcMol(Contractor srcMol) {
		this.srcMol = srcMol;
	}

	/**
	 * @param srcStock the srcStock to set
	 */
	public void setSrcStock(Contractor srcStock) {
		this.srcStock = srcStock;
	}

	/**
	 * @param dstMol the dstMol to set
	 */
	public void setDstMol(Contractor dstMol) {
		this.dstMol = dstMol;
	}

	/**
	 * @param dstStock the dstStock to set
	 */
	public void setDstStock(Contractor dstStock) {
		this.dstStock = dstStock;
	}

	/**
	 * @param measure1 the measure1 to set
	 */
	public void setMeasure1(Measure measure1) {
		this.measure1 = measure1;
	}

	/**
	 * @param measure2 the measure2 to set
	 */
	public void setMeasure2(Measure measure2) {
		this.measure2 = measure2;
	}

	/**
	 * @param quantity1 the quantity1 to set
	 */
	public void setQuantity1(BigDecimal quantity1) {
		this.quantity1 = quantity1;
	}

	/**
	 * @param quantity2 the quantity2 to set
	 */
	public void setQuantity2(BigDecimal quantity2) {
		this.quantity2 = quantity2;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @param price1 the price1 to set
	 */
	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	/**
	 * @param sum the sum to set
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	/**
	 * @param sum1 the sum1 to set
	 */
	public void setSum1(BigDecimal sum1) {
		this.sum1 = sum1;
	}

}
