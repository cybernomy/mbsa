/*
 * PromotionLineServiceBean.java
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

import java.math.BigDecimal;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.discount.PromotionLineServiceLocal;
import com.mg.merp.discount.model.PromotionLine;

/**
 * Реализация бизнес-компонента "Позиция рекламного мероприятия"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionLineServiceBean.java,v 1.1 2007/10/30 14:10:57 sharapov Exp $
 */
@Stateless(name="merp/discount/PromotionLineService") //$NON-NLS-1$
public class PromotionLineServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PromotionLine, Integer> implements PromotionLineServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(PromotionLine entity) {
		entity.setDiscount(BigDecimal.ZERO);
		entity.setPrice(BigDecimal.ZERO);
	}
	
}
