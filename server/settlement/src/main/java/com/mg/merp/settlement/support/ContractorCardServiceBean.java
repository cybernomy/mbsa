/*
 * ContractorCardServiceBean.java
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

package com.mg.merp.settlement.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.settlement.ContractorCardServiceLocal;
import com.mg.merp.settlement.model.ContractorCard;

/**
 * Бизнес-компонент "Карточки расчетов с партнерами" 
 * 
 * @author leonova
 * @version $Id: ContractorCardServiceBean.java,v 1.5 2008/02/01 11:33:54 safonov Exp $
 */
@Stateless(name="merp/settlement/ContractorCardService")
public class ContractorCardServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractorCard, Integer> implements ContractorCardServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ContractorCard entity) {
		context.addRule(new MandatoryAttribute(entity, "Contractor"));
	}

}
