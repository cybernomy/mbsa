/*
 * AnlForm.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Вид аналитической ведомости
 * 
 * @author leonova
 * @version $Id: AnlForm.java,v 1.1 2006/03/30 11:22:13 safonov Exp $
 */
@DataItemName ("Account.Plan.AnlForm")
public enum AnlForm {
	/**
	 * Нет
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.None")
	NONE,

	/**
	 * Основные средства
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.BaseMeans")
	BASEMEANS,
	
	/**
	 * Капитальные вложения
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Capital")
	CAPITAL,
	
	/**
	 * Материалы, товары учетные цены
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.CalcCost")
	CALCCOST,
	
	/**
	 * Материалы, товары партионный учет
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.BatchCalc")
	BATCHCALC,
	
	/**
	 * Материалы, товары средние цены
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.AverageCost")
	AVERAGECOST,
	
	/**
	 * Материалы, товары FIFO
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.FIFO")
	FIFO,
	
	/**
	 * Материалы, товары LIFO
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.LIFO")
	LIFO,
	
	/**
	 * МБП
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.MBP")
	MBP,
	
	/**
	 * Торговая наценка
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.TradeAdd")
	TRADEADD,
	
	/**
	 * Реализация
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Realisation")
	REALISARION,
	
	/**
	 * Дебиторы-кредиторы (по основанию)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKBase")
	DKBASE,
	
	/**
	 * Дебиторы-кредиторы (по договору)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKContract")
	DKCONTRACT,
	
	/**
	 * Дебиторы-кредиторы (по основанию/договору)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKBaseContract")
	DKBASECONTRACT,
	
	/**
	 * Дебиторы-кредиторы (по партнеру)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKPartner")
	DKPARTNER,
	
	/**
	 * Подотчетные лица
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.ReportFace")
	REPORTFACE,
	
	/**
	 * Затраты
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Expense")
	EXPENSE,
	
	/**
	 * Расходы будущих периодов
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.ExpenseFuture")
	EXPENSEFUTURE,
	
	/**
	 * Денежные средства
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.MoneyMeans")
	MONEYMEANS
}     