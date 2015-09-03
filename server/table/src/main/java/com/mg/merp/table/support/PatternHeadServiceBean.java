/*
 * PatternHeadServiceBean.java
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

package com.mg.merp.table.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.table.PatternHeadServiceLocal;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternKind;

/**
 * Бизнес-компонент "Шаблоны графиков" 
 * 
 * @author leonova
 * @version $Id: PatternHeadServiceBean.java,v 1.4 2006/10/12 07:12:47 leonova Exp $
 */
@Stateless(name="merp/table/PatternHeadService")
public class PatternHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PatternHead, Integer> implements PatternHeadServiceLocal {

	@Override
	protected void onInitialize(PatternHead entity) {
		entity.setPatternKind(PatternKind.WEEKLY);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PatternHead entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
		context.addRule(new MandatoryAttribute(entity, "Duration"));		
	
	}


}
