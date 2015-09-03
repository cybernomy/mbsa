/*
 * AmRateServiceBean.java
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

package com.mg.merp.account.support;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.account.AmRateServiceLocal;
import com.mg.merp.account.model.AmCode;
import com.mg.merp.account.model.AmRate;

/**
 * Реализация бизнес-компонента "Нормы амортизации" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: AmRateServiceBean.java,v 1.4 2008/04/29 05:01:04 alikaev Exp $
 */
@Stateless(name="merp/account/AmRateService")
public class AmRateServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AmRate, Integer> implements AmRateServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.account.AmRateServiceLocal#getAmortRate(com.mg.merp.account.model.AmCode, java.lang.Short)
	 */
	@PermitAll
	public BigDecimal getAmortRate(AmCode amCode, Short month) {
		return doGetAmortRate(amCode, month);
	}
	
	/**
	 * Возвращает норму амортизации в процентах
	 * 
	 * @param amCode
	 * 			- шифр нормы амортизации
	 * @param aMonth
	 * 				- месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
	 * @return
	 */
	protected BigDecimal doGetAmortRate(AmCode amCode, Short month) {
		BigDecimal rate = BigDecimal.ZERO;
		List<AmRate> amRates = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AmRate.class)
				.add(Restrictions.eq("AmCode", amCode))
				.setFlushMode(FlushMode.MANUAL)
				.addOrder(Order.desc("ActMonth")));
		if (!amRates.isEmpty())
			rate = amRates.get(0).getAmRate();
		for (AmRate amRate : amRates) 
			if (amRate.getActMonth() > month)
				rate = amRate.getAmRate();		
		return rate;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.AmRateServiceLocal#getVolumeProd(com.mg.merp.account.model.AmCode, java.lang.Short)
	 */
	@PermitAll
	public BigDecimal getVolumeProd(AmCode amCode, Short month) {
		return doGetVolumeProd(amCode, month);
	}

	/**
	 * Возращает предполагаемый объем продукции
	 * 
	 * @param amCode
	 * 			- шифр нормы амортизации
	 * @param aMonth
	 * 				- месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
	 * @return
	 */
	protected BigDecimal doGetVolumeProd(AmCode amCode, Short month) {
		BigDecimal vol = BigDecimal.ZERO;
		List<AmRate> amRates = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AmRate.class)
				.add(Restrictions.eq("AmCode", amCode))
				.setFlushMode(FlushMode.MANUAL)
				.addOrder(Order.desc("ActMonth")));
		if (!amRates.isEmpty())
			vol = amRates.get(0).getVolumeProd();
		for (AmRate amRate : amRates) 
			if (amRate.getActMonth() > month)
				vol = amRate.getVolumeProd();		
		return vol;
	}
		
}
