/*
 * MilitaryRankKindServiceBean.java
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

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.MilitaryRankKindServiceLocal;
import com.mg.merp.personnelref.model.MilitaryRankKind;

/**
 * Бизнес-компонент "Составы воинских званий" 
 * 
 * @author leonova
 * @version $Id: MilitaryRankKindServiceBean.java,v 1.4 2006/10/20 05:55:13 leonova Exp $
 */
@Stateless(name="merp/personnelref/MilitaryRankKindService")
public class MilitaryRankKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MilitaryRankKind, Integer> implements MilitaryRankKindServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, MilitaryRankKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
	}


}
