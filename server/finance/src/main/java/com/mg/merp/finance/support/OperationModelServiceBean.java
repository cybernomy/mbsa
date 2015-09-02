/*
 * OperationModelServiceBean.java
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

package com.mg.merp.finance.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.finance.OperationModelServiceLocal;
import com.mg.merp.finance.model.OperationModel;

/**
 * Бизнес-компонент "Образцы финансовых операций" 
 * 
 * @author leonova
 * @version $Id: OperationModelServiceBean.java,v 1.4 2006/10/11 05:09:35 leonova Exp $
 */
@Stateless(name="merp/finance/OperationModelService")
public class OperationModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OperationModel, Integer> implements OperationModelServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, OperationModel entity) {
		context.addRule(new MandatoryStringAttribute(entity, "ModelName"));
	}



}
