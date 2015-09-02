/*
 * SaleBookServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.account.SaleBookServiceLocal;
import com.mg.merp.account.model.SaleBook;

/**
 * Бизнес-компонент "Книга продаж" 
 * 
 * @author leonova
 * @version $Id: SaleBookServiceBean.java,v 1.4 2006/08/23 10:29:20 leonova Exp $
 */
@Stateless(name="merp/account/SaleBookService")
public class SaleBookServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SaleBook, Integer> implements SaleBookServiceLocal {

 	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, SaleBook entity) {
		context.addRule(new MandatoryAttribute(entity, "DocType"));
		context.addRule(new MandatoryAttribute(entity, "DocDate"));
		context.addRule(new MandatoryStringAttribute(entity, "DocNumber"));
		context.addRule(new MandatoryAttribute(entity, "Customer"));
	}

	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param dateFrom
 	 * @param dateTill
 	 * @param folderId
 	 * @param contractorId
 	 * @param docType
 	 * @param docNumber
 	 * @throws ApplicationException
 	 */
  	public void makeSaleBook(java.util.Date dateFrom, java.util.Date dateTill, int folderId, 
			int contractorId, String docType, String docNumber) throws ApplicationException {
 	//	((SaleBookDomainImpl) getDomain()).makeSaleBook(dateFrom, dateTill, folderId, contractorId, docType, docNumber);
 	}	
}
