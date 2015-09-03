/**
 * AbstractWarehouseDocumentSpec.java.java
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
package com.mg.merp.warehouse.generic;

import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.warehouse.model.BaseStockDocumentSpec;

/**
 * Базовая реализация спецификаций товарных документов
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractWarehouseDocumentSpec.java,v 1.3 2008/02/05 10:06:59 safonov Exp $
 */
public abstract class AbstractWarehouseDocumentSpec<T extends BaseStockDocumentSpec> extends
		GoodsDocumentSpecificationServiceBean<T, Integer> {

	@Override
	protected void doAdjust(T entity) {
		DefaultWarehouseSpecPropertiesCalculationStrategy strategy = new DefaultWarehouseSpecPropertiesCalculationStrategy(getDocSection().isWithTaxes(), entity, getRoundContext());
		strategy.adjust();
	}

}
