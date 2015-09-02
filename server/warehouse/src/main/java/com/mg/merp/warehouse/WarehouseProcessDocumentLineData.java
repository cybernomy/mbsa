/**
 * WarehouseProcessDocumentLineData.java
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
package com.mg.merp.warehouse;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Данные по спецификации для отработки по складам
 * 
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessDocumentLineData.java,v 1.1 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseProcessDocumentLineData {

	/**
	 * получить данные ДО
	 * 
	 * @return
	 */
	DocumentSpecItem getDocumentSpecItem();
	
	/**
	 * получить спецификацию
	 * 
	 * @return
	 */
	DocSpec getDocumentSpec();
	
	/**
	 * получить МОЛ источника
	 * 
	 * @return
	 */
	Employees getSrcMol();
	
	/**
	 * получить склад источник
	 * 
	 * @return
	 */
	Warehouse getSrcStock();
	
	/**
	 * получить МОЛ приемника
	 * 
	 * @return
	 */
	Employees getDstMol();
	
	/**
	 * получить склад приемник
	 * 
	 * @return
	 */
	Warehouse getDstStock();
	
	/**
	 * получить позицию каталога
	 * 
	 * @return
	 */
	Catalog getCatalog();
	
	/**
	 * получить 1ю ЕИ
	 * 
	 * @return
	 */
	Measure getMeasure1();
	
	/**
	 * получить 2ю ЕИ
	 * 
	 * @return
	 */
	Measure getMeasure2();
	
	/**
	 * получить 1е количество к отработке
	 * 
	 * @return
	 */
	BigDecimal getQuantity1();
	
	/**
	 * получить 2е количество к отработке
	 * 
	 * @return
	 */
	BigDecimal getQuantity2();
	
	/**
	 * получить цену без не включенных в цену налогов
	 * 
	 * @return
	 */
	BigDecimal getPrice();
	
	/**
	 * получить полную ценю
	 * 
	 * @return
	 */
	BigDecimal getPrice1();

	/**
	 * получить сумму без не включенных в цену налогов
	 * 
	 * @return
	 */
	BigDecimal getSum();
	
	/**
	 * получить полную сумму
	 * 
	 * @return
	 */
	BigDecimal getSum1();

	/**
	 * получить дату транзакции
	 * 
	 * @return
	 */
	Date getProcessDate();
	
}
