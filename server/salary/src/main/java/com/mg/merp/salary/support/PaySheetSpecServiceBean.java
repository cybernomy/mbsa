/*
 * PaySheetSpecServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.PaySheetSpecServiceLocal;
import com.mg.merp.salary.model.PaySheetSpec;

/**
 * Бизнес-компонент "Спецификация платежной ведомости" 
 * 
 * @author leonova
 * @version $Id: PaySheetSpecServiceBean.java,v 1.4 2006/09/13 10:48:29 leonova Exp $
 */
@Stateless(name="merp/salary/PaySheetSpecService")
public class PaySheetSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PaySheetSpec, Integer> implements PaySheetSpecServiceLocal {



 	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PaySheetSpec entity) {
		context.addRule(new MandatoryAttribute(entity, "SummaFull"));
		context.addRule(new MandatoryAttribute(entity, "PositionFill"));		
	}

	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param paySheetSpecIdSeq
 	 * @throws ApplicationException
 	 */
 	public void payOut(int[] paySheetSpecIdSeq) throws ApplicationException {
 		//((PaySheetSpecDomainImpl) getDomain()).payOut(paySheetSpecIdSeq);
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param paySheetSpecIdSeq
 	 * @throws ApplicationException
 	 */
 	public void deposit(int[] paySheetSpecIdSeq) throws ApplicationException {
 		//((PaySheetSpecDomainImpl) getDomain()).deposit(paySheetSpecIdSeq);
 	}
 }
