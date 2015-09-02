/*
 * PriceListSpecServiceLocal.java
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
package com.mg.merp.reference;

import java.util.List;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.PriceListSpecPrice;

/**
 * Бизнес-компонент "Спецификация прайс-листов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListSpecServiceLocal.java,v 1.5 2008/05/16 05:52:31 alikaev Exp $
 */
public interface PriceListSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PriceListSpec, Integer> {
	
	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/reference/PriceListSpec"; //$NON-NLS-1$
	
	/**
	 * Пересчет цен спецификации прайс-листа
	 * 
	 * @param priceListSpec - объект-сущность спецификации прайс-листа 
	 */
	void calcPrices(PriceListSpec priceListSpec);

	/**
	 * Добавление спецификаций прайс-листа по кааталожным позициям
	 * 
	 * @param priceListHeadId
	 * 				- идентификатор заголовока прайс-листа
	 * @param priceListFolder
	 * 				- папка прайс-листа
	 * @param catalogs
	 * 				- список каталожных позиций
	 */
	void addFromCatalog(Integer priceListHeadId, PriceListFolder priceListFolder, List<Catalog> catalogs);

	/**
	 * поиск позиции прайс-листа по штрих-коду
	 * 
	 * @param barCode
	 * @param priceListId
	 * @param priceTypeId
	 * @param aDate
	 * @return
	 * @throws com.mg.framework.api.ApplicationException
	 */
	PriceListSpecPrice findByBarCode(java.lang.String barCode, int priceListId, int priceTypeId, java.util.Date date);

}
