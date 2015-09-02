/*
 * KbkServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.KbkServiceLocal;
import com.mg.merp.reference.model.Kbk;

/**
 * ������-��������� "���� ���" 
 * 
 * @author leonova
 * @version $Id: KbkServiceBean.java,v 1.4 2006/10/19 04:43:02 leonova Exp $
 */
@Stateless(name="merp/reference/KbkService")
public class KbkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Kbk, Integer> implements KbkServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, Kbk entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
	}

}
