/*
 * WarehouseBatchServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.warehouse.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.model.StockBatch;

/**
 * Бизнес-компонент "Партии" 
 * 
 * @author leonova
 * @version $Id: WarehouseBatchServiceBean.java,v 1.4 2007/07/30 11:07:32 safonov Exp $
 */
@Stateless(name="merp/warehouse/WarehouseBatchService")
@PermitAll
public class WarehouseBatchServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StockBatch, Integer> implements WarehouseBatchServiceLocal {

}
