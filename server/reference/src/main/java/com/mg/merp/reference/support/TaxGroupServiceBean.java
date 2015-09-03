/*
 * TaxGroupServiceBean.java
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

package com.mg.merp.reference.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.TaxGroupServiceLocal;
import com.mg.merp.reference.model.Tax;
import com.mg.merp.reference.model.TaxGroup;
import com.mg.merp.reference.model.TaxLink;

/**
 * Бизнес-компонент "Группы налогов"
 * 
 * @author leonova
 * @version $Id: TaxGroupServiceBean.java,v 1.6 2006/09/20 13:12:59 safonov Exp $
 */
@Stateless(name="merp/reference/TaxGroupService")
public class TaxGroupServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TaxGroup, Integer> implements TaxGroupServiceLocal {

 	
 	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TaxGroup entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "TgName"));		
	}

	/**
 	 * @ejb.interface-method view-type = "local"
 	 *
 	 * @param taxGroup
 	 * @param tax
 	 * @param feeOrder
 	 * @throws ApplicationException
 	 */
 	public TaxLink includeTax(TaxGroup taxGroup, Tax tax, short feeOrder) throws ApplicationException {
 		TaxLink result = new TaxLink();
 		result.setTax(tax);
 		result.setTaxGroup(taxGroup);
 		result.setFeeOrder(feeOrder);
 		ServerUtils.getPersistentManager().persist(result);
 		return result;
 		//((TaxGroupDomainImpl) getDomain()).includeTax(taxGroup, tax, feeOrder);
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local" 
 	 * 
 	 * @param taxLink
 	 * @throws ApplicationException
 	 */
 	public void excludeTax(TaxLink taxLink) throws ApplicationException {
 		ServerUtils.getPersistentManager().remove(taxLink);
 		//((TaxGroupDomainImpl) getDomain()).excludeTax(taxLink);
 	}	
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param taxKink
 	 * @param feeOrder
 	 * @throws ApplicationException
 	 */
 	public void editTax(TaxLink taxKink, short feeOrder) throws ApplicationException {
 		taxKink.setFeeOrder(feeOrder);
 		ServerUtils.getPersistentManager().merge(taxKink);
 		//((TaxGroupDomainImpl) getDomain()).editTax(taxKink, feeOrder);
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param taxGroup
 	 * @return
 	 * @throws ApplicationException
 	 */
 	public List<TaxLink> loadTaxesLink(TaxGroup taxGroup) throws ApplicationException {
 		return MiscUtils.convertUncheckedList(TaxLink.class, OrmTemplate.getInstance().findByNamedParam("from TaxLink tl where tl.TaxGroup = :taxGroup", "taxGroup", taxGroup));
 	}
 	
 }
