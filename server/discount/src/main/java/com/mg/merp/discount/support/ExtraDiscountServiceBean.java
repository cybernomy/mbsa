/*
 * ExtradiscountServiceBean.java
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

package com.mg.merp.discount.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.discount.ExtraDiscountServiceLocal;
import com.mg.merp.discount.model.ExtraDiscount;
import com.mg.merp.security.model.SecUser;

/**
 * Реализация бизнес-компонента "Дополнительные скидки дисконтной карты" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ExtraDiscountServiceBean.java,v 1.4 2007/10/30 14:13:48 sharapov Exp $
 */
@Stateless(name="merp/discount/ExtraDiscountService")
public class ExtraDiscountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ExtraDiscount, Integer> implements ExtraDiscountServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(ExtraDiscount entity) {
		adjustUser(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(ExtraDiscount entity) {
		adjustUser(entity);
	}
	
	/**
	 * Установить текущего пользователя системы
	 * @param extraDiscount - доп. скидка 
	 */
	protected void adjustUser(ExtraDiscount extraDiscount) {
		extraDiscount.setUser(ServerUtils.getPersistentManager().find(SecUser.class, ServerUtils.getUserProfile().getIdentificator()));		
	}

}
