/*
 * WarehouseZoneServiceBean.java
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.warehouse.WarehouseZoneServiceLocal;
import com.mg.merp.warehouse.model.WarehouseZone;

/**
 * Бизнес-компонент "Зоны хранения" 
 * 
 * @author leonova
 * @version $Id: WarehouseZoneServiceBean.java,v 1.4 2006/10/03 05:53:40 leonova Exp $
 */
@Stateless(name="merp/warehouse/WarehouseZoneService")
public class WarehouseZoneServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WarehouseZone, Integer> implements WarehouseZoneServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, WarehouseZone entity) {
		context.addRule(new MandatoryAttribute(entity, "Warehouse"));
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));		
	}



}
