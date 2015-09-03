/*
 * InventoryParametrs.java
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
package com.mg.merp.warehouse.support;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;

/**
 * Параметры инвентаризации
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: InventoryParametrs.java,v 1.3 2007/11/29 14:02:00 alikaev Exp $
 */
public class InventoryParametrs {
	
	/**
	 * Правила формирования строк по партиям(с учетом цены)
	 */
	@DataItemName("InventoryParams.StockInvKind")
	public static enum StockInventoryKind { 
		/**
		 * одна строка ТМЦ по одной цене прихода
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.StockInvKind.OnePriceKind") 
		ONEPRICEKIND, 
		
		/**
		 *одна строка ТМЦ по всем ценам прихода 
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.StockInvKind.AgregatePriceKind") 
		AGREGATEPRICEKIND
	}

	/**
	 * 	правило формирования строк по партиям(с учетом МОЛ-ов)
	 */
	@DataItemName("InventoryParams.MolInvKind")
	public static enum MolInventoryKind {
		/**
		 * одна строка ТМЦ по одному МОЛ-у склада инвентаризации
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.MolInvKind.OnePriceKind") 
		ONEMOLKIND, 
		
		/**
		 * одна строка ТМЦ по всем МОЛ-ам склада инвентаризации
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.MolInvKind.AgregatePriceKind") 
		AGREGATEMOLKIND
		
	}

	/**
	 * 	склад инвентаризации
	 */
	private OrgUnit stock;
	
	/**
	 *	список МОЛ - ов склада инвентаризации
	 */
	private Contractor mol;

	/**
	 *	дата инвентаризации
	 */
	private Date endDate;

	/**
	 * начало диапазона кодов позиций каталога
	 */
	private String beginCode;

	/**
	 * конец диапазона кодов позиций каталога
	 */
	private String endCode;

	/**
	 * 	правило формирования строк по партиям(с учетом цен)
	 */
	private StockInventoryKind stockKind;
	
	/**
	 * 	правило формирования строк по партиям(с учетом МОЛ-ов)
	 */
	private MolInventoryKind molKind;

	/**
	 * 	признак включения позиций с нулевым остатком
	 */
	private boolean isIncludeEmpty;

	/**
	 * 	признак удаления спецификаций из акта инвентаризации перед инвентаризацией 
	 */
	private boolean isDeleteSpecList;

	
	public InventoryParametrs() {
	}
	
	/**
	 * Конструктор для задания параметров инвентаризации
	 * 
	 * @param stock				склад инвентаризации
	 * @param molList			список МОЛ - ов склада
	 * @param endDate			дата инвентаризации
	 * @param beginCode			начало диапазона кодов позиций каталога
	 * @param endCode			конец диапазона кодов позиций каталога
	 * @param stockKind			правило формирования строк по партиям:
	 *								0 - одна строка ТМЦ по одной цене прихода;
	 *								1 - одна строка ТМЦ по всем ценам прихода;
	 * @param molKind			правило формирования строк по партиям:
	 *								0 - одна строка ТМЦ по одному МОЛ-у склада инвентаризации;
	 *								1 - одна строка ТМЦ по всем МОЛ-ам склада инвентаризации;
	 * @param isIncludeEmpty	признак включения позиций с нулевым остатком
	 * @param isDeleteSpecList	признак удаления спецификаций из акта инвентаризации перед инвентаризацией 
	 */
	public InventoryParametrs(OrgUnit stock, Contractor mol,
			Date endDate, String beginCode, String endCode, StockInventoryKind stockKind,
			MolInventoryKind molKind, boolean isIncludeEmpty, boolean isDeleteSpecList) {
		this.stock = stock;
		this.mol = mol;
		this.endDate = endDate;
		this.beginCode = beginCode;
		this.endCode = endCode;
		this.stockKind = stockKind;
		this.molKind = molKind;
		this.isIncludeEmpty = isIncludeEmpty;
		this.isDeleteSpecList = isDeleteSpecList;
	}

	/**
	 * Возвращает склад 
	 * 
	 * @return stock	склад
	 */
	public OrgUnit getStock() {
		return stock;
	}

	/**
	 * Устанавливает значение склада
	 * 
	 * @param stock задаваемое stock
	 */
	public void setStock(OrgUnit stock) {
		this.stock = stock;
	}

	/**
	 * Возвращает список МОЛ - ов склада по которым производиться инвентаризация
	 * 
	 * @return molList	
	 */
	public Contractor getMol() {
		return mol;
	}

	/**
	 * Устанавливает список МОЛ - ов склада по которым производиться инвентаризация
	 * @param molList задаваемое molList
	 */
	public void setMol(Contractor mol) {
		this.mol = mol;
	}

	/**
	 * Возвращает дату инвентаризации
	 * 
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Устанавливает дату инвентаризации
	 * 
	 * @param endDate задаваемое endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Возвращает начало диапазона кодов позиций каталога
	 * 
	 * @return beginCode
	 */
	public String getBeginCode() {
		return beginCode;
	}

	/**
	 * Устанавливает начало диапазона кодов позиций каталога
	 * 
	 * @param beginCode задаваемое beginCode
	 */
	public void setBeginCode(String beginCode) {
		this.beginCode = beginCode;
	}

	/**
	 * Возвращает конец диапазона кодов позиций каталога
	 * @return endCode
	 */
	public String getEndCode() {
		return endCode;
	}

	/**
	 * Устанавливает конец диапазона кодов позиций каталога
	 * 
	 * @param endCode задаваемое endCode
	 */
	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	/**
	 * Возращает правило формирования строк по партиям(по ценам)
	 * 
	 * @return stockKind
	 */
	public StockInventoryKind getStockKind() {
		return stockKind;
	}

	/**
	 * Устанавливает правило формирования строк по партиям(по ценам)
	 * 
	 * @param stockKind задаваемое stockKind
	 */
	public void setStockKind(StockInventoryKind stockKind) {
		this.stockKind = stockKind;
	}

	/**
	 * Возвращает правило формирования строк по партиям(по МОЛ-ам)
	 * 
	 * @return molKind
	 */
	public MolInventoryKind getMolKind() {
		return molKind;
	}

	/**
	 * Устанавливает правило формирования строк по партиям(по МОЛ-ам)
	 * 
	 * @param molKind задаваемое molKind
	 */
	public void setMolKind(MolInventoryKind molKind) {
		this.molKind = molKind;
	}

	/**
	 * Возвращает признак включения позиций с нулевым остатком
	 * 
	 * @return isIncludeEmpty
	 */
	public boolean isIncludeEmpty() {
		return isIncludeEmpty;
	}

	/**
	 * Устанавливает признак включения позиций с нулевым остатком
	 * 
	 * @param isIncludeEmpty задаваемое isIncludeEmpty
	 */
	public void setIncludeEmpty(boolean isIncludeEmpty) {
		this.isIncludeEmpty = isIncludeEmpty;
	}

	/**
	 * Возвращает признак удаления спецификаций из акта инвентаризации перед инвентаризацией
	 * @return isDeleteSpecList
	 */
	public boolean isDeleteSpecList() {
		return isDeleteSpecList;
	}

	/** 
	 * Устанавливает признак удаления спецификаций из акта инвентаризации перед инвентаризацией
	 * 
	 * @param isDeleteSpecList задаваемое isDeleteSpecList
	 */
	public void setDeleteSpecList(boolean isDeleteSpecList) {
		this.isDeleteSpecList = isDeleteSpecList;
	}


}

