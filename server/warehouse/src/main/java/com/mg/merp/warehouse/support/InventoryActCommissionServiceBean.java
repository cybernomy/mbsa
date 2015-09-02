/*
 * InventoryActCommissionServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.merp.warehouse.InventoryActCommissionServiceLocal;
import com.mg.merp.warehouse.model.InventoryActCommission;

/**
 * 
 * @author Oleg V. Safonov
 * @version $Id: InventoryActCommissionServiceBean.java,v 1.3 2006/09/20 12:31:27 safonov Exp $
 */
@Stateless(name="merp/warehouse/InventoryActCommissionService")
public class InventoryActCommissionServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<InventoryActCommission, Integer> implements InventoryActCommissionServiceLocal {

}
