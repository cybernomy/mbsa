/*
 * AccRevaluateParams.java
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
package com.mg.merp.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocType;

/**
 * Класс для передачи параметров в метод {@link #revaluate()} сервиса бизнес-компонента "Инвентарная картотека"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccRevaluateParams.java,v 1.1 2008/04/28 10:09:51 alikaev Exp $
 */
public class AccRevaluateParams implements Serializable {
	
	private Date revalDate;
	
	private Folder folder;
	
	private short kind;
	
	private BigDecimal value;
	
	private DocType docType;
	
	private String docNumber;
	
	private Date docDate;
	
	private DocType baseDocType;
	
	private String baseDocNumber;
	
	private Date baseDocDate;
	
	private AccPlan accPlan;
	
	private AnlPlan anl1;

	private AnlPlan anl2;
	
	private AnlPlan anl3;
	
	private AnlPlan anl4;
	
	private AnlPlan anl5;

	private boolean isOverestimation;
	
	/**
	 * Конструктор
	 * 
	 * @param revalDate
	 * 			- дата учета
	 * @param folder
	 * 			- папка приемник для хоз-операции
	 * @param kind
	 * 			- признак: 0 - фактор, 1 - до суммы, 2 - на сумму
	 * @param value
	 * 			- значение по признаку
	 * @param docType
	 * 			- тип документа
	 * @param docNumber
	 * 			- номер документа
	 * @param docDate
	 * 			- дата документа
	 * @param baseDocType
	 * 			- тип документа-основания
	 * @param baseDocNumber
	 * 			- номер документа-основания
	 * @param baseDocDate
	 * 			- дата документа-основания
	 * @param accPlan
	 * 			- счет
	 * @param anl1
	 * 			- аналитика 1-го уровня
	 * @param anl2
	 * 			- аналитика 2-го уровня
	 * @param anl3
	 * 			- аналитика 3-го уровня
	 * @param anl4
	 * 			- аналитика 4-го уровня
	 * @param anl5
	 * 			- аналитика 5-го уровня
	 * @param isOverestimation
	 * 			- <code>false</code> - переоценка, <code>true</code> - дооценка
	 */
	public AccRevaluateParams(Date revalDate, Folder folder, short kind, BigDecimal value,	DocType docType, String docNumber, Date docDate,
			DocType baseDocType, String baseDocNumber, Date baseDocDate, AccPlan accPlan, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
			AnlPlan anl4, AnlPlan anl5, boolean isOverestimation) {
		this.revalDate = revalDate;
		this.folder = folder;
		this.kind = kind;
		this.value = value;
		this.docType = docType;
		this.docNumber = docNumber;
		this.docDate = docDate;
		this.baseDocType = baseDocType;
		this.baseDocNumber = baseDocNumber;
		this.baseDocDate = baseDocDate;
		this.accPlan = accPlan;
		this.anl1 = anl1;
		this.anl2 = anl2;
		this.anl3 = anl3;
		this.anl4 = anl4;
		this.anl5 = anl5;
		this.isOverestimation = isOverestimation;
	}

	/**
	 * Дата учета
	 * 
	 * @return revalDate
	 */
	public Date getRevalDate() {
		return revalDate;
	}

	/**
	 * @param revalDate задаваемое revalDate
	 */
	public void setRevalDate(Date revalDate) {
		this.revalDate = revalDate;
	}

	/**
	 * Признак: 0 - фактор, 1 - до суммы, 2 - на сумму
	 * @return kind
	 */
	public short getKind() {
		return kind;
	}

	/**
	 * @param kind задаваемое kind
	 */
	public void setKind(short kind) {
		this.kind = kind;
	}

	/**
	 * Значение по признаку
	 * @return value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value задаваемое value
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * Тип документа
	 * @return docType
	 */
	public DocType getDocType() {
		return docType;
	}

	/**
	 * @param docType задаваемое docType
	 */
	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	/**
	 * Номер документа
	 * 
	 * @return docNumber
	 */
	public String getDocNumber() {
		return docNumber;
	}

	/**
	 * @param docNumber задаваемое docNumber
	 */
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	/**
	 * Дата документа
	 * 
	 * @return docDate
	 */
	public Date getDocDate() {
		return docDate;
	}

	/**
	 * @param docDate задаваемое docDate
	 */
	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	/**
	 * Тип документа-основания
	 * 
	 * @return baseDocType
	 */
	public DocType getBaseDocType() {
		return baseDocType;
	}

	/**
	 * @param baseDocType задаваемое baseDocType
	 */
	public void setBaseDocType(DocType baseDocType) {
		this.baseDocType = baseDocType;
	}

	/**
	 * Номер документа-основания
	 * 
	 * @return baseDocNumber
	 */
	public String getBaseDocNumber() {
		return baseDocNumber;
	}

	/**
	 * @param baseDocNumber задаваемое baseDocNumber
	 */
	public void setBaseDocNumber(String baseDocNumber) {
		this.baseDocNumber = baseDocNumber;
	}

	/**
	 * Дата документа-основания
	 * 
	 * @return baseDocDate
	 */
	public Date getBaseDocDate() {
		return baseDocDate;
	}

	/**
	 * @param baseDocDate задаваемое baseDocDate
	 */
	public void setBaseDocDate(Date baseDocDate) {
		this.baseDocDate = baseDocDate;
	}

	/**
	 * Счет
	 * @return accPlan
	 */
	public AccPlan getAccPlan() {
		return accPlan;
	}

	/**
	 * @param accPlan задаваемое accPlan
	 */
	public void setAccPlan(AccPlan accPlan) {
		this.accPlan = accPlan;
	}

	/**
	 * Аналитика 1-го уровня
	 * @return anl1
	 */
	public AnlPlan getAnl1() {
		return anl1;
	}

	/**
	 * @param anl1 задаваемое anl1
	 */
	public void setAnl1(AnlPlan anl1) {
		this.anl1 = anl1;
	}

	/**
	 * Аналитика 2-го уровня
	 * 
	 * @return anl2
	 */
	public AnlPlan getAnl2() {
		return anl2;
	}

	/**
	 * @param anl2 задаваемое anl2
	 */
	public void setAnl2(AnlPlan anl2) {
		this.anl2 = anl2;
	}

	/**
	 * Аналитика 3-го уровня
	 * 
	 * @return anl3
	 */
	public AnlPlan getAnl3() {
		return anl3;
	}

	/**
	 * @param anl3 задаваемое anl3
	 */
	public void setAnl3(AnlPlan anl3) {
		this.anl3 = anl3;
	}

	/**
	 * Аналитика 4-го уровня
	 * 
	 * @return anl4
	 */
	public AnlPlan getAnl4() {
		return anl4;
	}

	/**
	 * @param anl4 задаваемое anl4
	 */
	public void setAnl4(AnlPlan anl4) {
		this.anl4 = anl4;
	}

	/**
	 * Аналитика 5-го уровня
	 * 
	 * @return anl5
	 */
	public AnlPlan getAnl5() {
		return anl5;
	}

	/**
	 * @param anl5 задаваемое anl5
	 */
	public void setAnl5(AnlPlan anl5) {
		this.anl5 = anl5;
	}

	/**
	 *  <code>false</code> - переоценка, <code>true</code> - дооценка
	 *  
	 * @return isOverestimation
	 */
	public boolean isOverestimation() {
		return isOverestimation;
	}

	/**
	 * @param isOverestimation задаваемое isOverestimation
	 */
	public void setOverestimation(boolean isOverestimation) {
		this.isOverestimation = isOverestimation;
	}

	/**
	 * Папка приемник для хоз. операции
	 * 
	 * @return folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder задаваемое folder
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
}
