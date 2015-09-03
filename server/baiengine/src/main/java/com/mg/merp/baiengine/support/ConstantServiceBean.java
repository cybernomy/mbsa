/*
 * ConstantServiceBean.java
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

package com.mg.merp.baiengine.support;

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.baiengine.ConstantServiceLocal;
import com.mg.merp.baiengine.model.Constant;
import com.mg.merp.baiengine.model.ConstantValue;

/**
 * Бизнес-компонент "Константы"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantServiceBean.java,v 1.3 2007/08/24 09:17:47 alikaev Exp $
 *
 */
@Stateless(name="merp/baiengine/ConstantService") //$NON-NLS-1$
public class ConstantServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Constant, Integer> implements ConstantServiceLocal{
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.baiengine.ConstantServiceLocal#getActualValue(java.lang.String, java.util.Date)
	 */
	@PermitAll
	public Object getActualValue(String code, Date actualDate) {
		return doGetActualValue(code, actualDate);
	}
	
	/**
	 * реализация получения значения константы по коду и дате актуальности
	 * 
	 * @param code
	 * @param actualDate
	 * @return
	 */
	protected Object doGetActualValue(String code, Date actualDate) {
		List<ConstantValue> constV = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ConstantValue.class)
				 .createAlias("Const", "c")
				 .add(Restrictions.eq("c.Code", code))
				 .add(Restrictions.between("StartDate", DateTimeUtils.ZERO_DATE, actualDate))
				 .addOrder(Order.desc("StartDate"))
				 .setMaxResults(1));
		
		if (constV.isEmpty())
			return null;
		else {
			ConstantValue constantValue = constV.get(0);
			return BusinessAddinUtils.convertConstantValue(constantValue.getVal(), constantValue.getConst().getDataType());
		}
	}

	@Override
	protected void onValidate(ValidationContext context, Constant entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryAttribute(entity, "DataType"));
	}
	
}
