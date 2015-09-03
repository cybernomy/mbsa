/*
 * PaymentServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.paymentalloc.support;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentalloc.PaymentServiceLocal;
import com.mg.merp.paymentalloc.model.Payment;
import com.mg.merp.reference.CurrencyConversionResult;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Currency;

/**
 * Реализация бизнес-компонента "Журнал платежей" 
 * 
 * @author leonova
 * @author Artem V Sharapov
 * @version $Id: PaymentServiceBean.java,v 1.10 2007/07/12 10:59:33 safonov Exp $
 */
@Stateless(name="merp/paymentalloc/PaymentService") //$NON-NLS-1$
public class PaymentServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Payment, Integer> implements PaymentServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Payment entity) {
		context.addRule(new MandatoryAttribute(entity, "CurCode")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateAuthority")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "PDate")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentalloc.PaymentServiceLocal#createByPattern(com.mg.merp.paymentalloc.model.Payment, com.mg.merp.core.model.Folder)
	 */
	@PermitAll
	public Payment createByPattern(Payment pattern, Folder folder) {
		return doCreateByPattern(pattern, folder);
	}

	protected Payment doCreateByPattern(Payment pattern, Folder folder) {
		AttributeMap attributes = pattern.getAllAttributes();
		Payment payment = initialize();
		attributes.remove("Id"); //$NON-NLS-1$
		payment.setAttributes(attributes);
		payment.setIsModel(false);
		if(pattern.getDestFolder() != null)
			payment.setFolder(pattern.getDestFolder());
		else
			payment.setFolder(folder);
		return payment;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentalloc.PaymentServiceLocal#computeCurRate(com.mg.merp.paymentalloc.model.Payment)
	 */
	@PermitAll
	public void computeCurRateAndSumNat(Payment payment) {
		doComputeCurRateAndSumNat(payment);
	}

	protected void doComputeCurRateAndSumNat(Payment payment) {
		BigDecimal sumToConvert = BigDecimal.ZERO;
		
		if(payment.getSumCur() != null)
			sumToConvert = payment.getSumCur();
			
		CurrencyConversionResult convertionResult = getCurencyConverter().conversionEx(
				(Currency) getModuleConfiguration().getAttribute("NatCurrency"), //$NON-NLS-1$
				payment.getCurCode(), 
				payment.getCurRateAuthority(),
				payment.getCurRateType(),
				payment.getPDate(),
				sumToConvert);
		payment.setCurRate(convertionResult.getRate());
		
		if(payment.getSumCur() != null)
			payment.setSumNat(convertionResult.getAmount());
	}

	private CurrencyServiceLocal getCurencyConverter() {
		return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
	}

	private PersistentObject getModuleConfiguration() {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		return ServerUtils.getPersistentManager().find("com.mg.merp.account.model.AccConfig", sysClient.getId()); //$NON-NLS-1$
	}

}
