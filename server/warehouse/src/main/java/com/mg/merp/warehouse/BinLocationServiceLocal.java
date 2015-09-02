/*
 * BinLocationServiceLocal.java
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

import java.util.List;

import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.StockBatch;

/**
 * Бизнес-компонент "Cекции хранения на складах"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BinLocationServiceLocal.java,v 1.4 2008/05/30 12:39:54 sharapov Exp $
 */
public interface BinLocationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<BinLocation, Integer> {
	
	/**
	 * Имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/BinLocation";
	
	/**
	 * Тип движения: приход
	 */
	static final short RECEIPT_KIND = 0;
	
	/**
	 * Тип движения: расход
	 */
	static final short ISSUE_KIND = 1; 
	
	/**
	 * Получить список секций хранения в партии
	 * @param stockBatch - партия
	 * @return список секций хранения в партии
	 */
	List<BinLocationDetailData> getBinLocationDetails(StockBatch stockBatch);
	
}
