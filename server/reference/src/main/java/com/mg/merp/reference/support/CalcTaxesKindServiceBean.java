/*
 * CalcTaxesKindServiceBean.java
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
import com.mg.merp.reference.CalcTaxesKindServiceLocal;
import com.mg.merp.reference.model.CalcTaxesKind;
import com.mg.merp.reference.model.CalcTaxesLink;
import com.mg.merp.reference.model.CalcTaxesSubject;
import com.mg.merp.reference.model.Tax;

/**
 * Бизнес-компонент "Виды начисления налогов"
 * 
 * @author leonova
 * @version $Id: CalcTaxesKindServiceBean.java,v 1.9 2007/06/20 12:00:28 safonov Exp $
 */
@Stateless(name="merp/reference/CalcTaxesKindService")
public class CalcTaxesKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcTaxesKind, Integer> implements CalcTaxesKindServiceLocal {
 	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CalcTaxesKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "KName"));		
	}

	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param id
 	 * @param calcTaxesKindId
 	 * @param taxId
 	 * @param taxCode
 	 * @param feeOrder
 	 * @param included
 	 * @param subject
 	 * @throws ApplicationException
 	 */
 	public CalcTaxesLink includeTax(CalcTaxesKind calcTaxKind, Tax tax, Short feeOrder, boolean included, CalcTaxesSubject subject) throws ApplicationException {
 		CalcTaxesLink result = null;
 		if (subject == null) {
 			throw new ApplicationException(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.CHECK_SUBJECT, new Object[] {subject}));
 		} else {
 	 		result = new CalcTaxesLink();
 	 		result.setTax(tax);
 	 		result.setCalcTaxesKind(calcTaxKind);
 	 		result.setSubject(subject);
 	 		result.setFeeOrder(feeOrder);
 	 		result.setIncluded(included); 	 		
 	 		ServerUtils.getPersistentManager().persist(result); 	 		 		
 		}
 		return result;
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param linkId
 	 * @throws ApplicationException
 	 */
 	public void excludeTax(CalcTaxesLink calkTaxLink) throws ApplicationException {
 		ServerUtils.getPersistentManager().remove(calkTaxLink); 		
 	}

 	public void editTax(CalcTaxesLink taxLink, Tax tax, Short feeOrder, boolean included, CalcTaxesSubject subject) throws ApplicationException {
 		if (subject == null) {
 			throw new ApplicationException(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.CHECK_SUBJECT));
 		} else { 
 	 		taxLink.setTax(tax); 	
 	 		taxLink.setFeeOrder(feeOrder);
 	 		taxLink.setIncluded(included);
 	 		taxLink.setSubject(subject); 		
 	 		ServerUtils.getPersistentManager().merge(taxLink); 			
 		}	
	}
 	
 	public List<CalcTaxesLink> loadTaxesLink(CalcTaxesKind calcTaxesKind) throws ApplicationException {
 		return MiscUtils.convertUncheckedList(CalcTaxesLink.class, OrmTemplate.getInstance().findByNamedParam("from CalcTaxesLink ctl where ctl.CalcTaxesKind = :calcTaxesKind", "calcTaxesKind", calcTaxesKind));
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param id
 	 * @param calcTaxesKindId
 	 * @param taxId
 	 * @param taxCode
 	 * @param feeOrder
 	 * @param included
 	 * @param subject
 	 * @throws ApplicationException
 	 */
 	public void updateLink(int id, int calcTaxesKindId, int taxId,
            String taxCode, long feeOrder, 
            boolean included, int subject) throws ApplicationException {
 	//	((CalcTaxesKindDomainImpl) getDomain()).updateLink(id, calcTaxesKindId, taxId,
      //          taxCode, feeOrder, included, subject);
 	}
 	
 	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param kindId
 	 * @return
 	 * @throws ApplicationException
 	 */
 	public byte[] loadTaxesBrowse(int kindId) throws ApplicationException {
 		return null;//((CalcTaxesKindDomainImpl) getDomain()).loadTaxesBrowse(kindId);
 	}

}
