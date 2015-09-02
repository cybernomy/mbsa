/*
 * OkpServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.OkdpServiceLocal;
import com.mg.merp.reference.model.Okdp;

/**
 * Бизнес-компонент "Коды ОКР" 
 * 
 * @author leonova
 * @version $Id: OkdpServiceBean.java,v 1.4 2006/10/19 04:27:51 leonova Exp $
 */
@Stateless(name="merp/reference/OkdpService")
 public class OkdpServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Okdp, String> implements OkdpServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, Okdp entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "OName"));		
	}

	private void adjustOkdp(Okdp entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}
	
	@Override
	protected void onCreate(Okdp entity) {
		adjustOkdp(entity);
	}

	@Override
	protected void onStore(Okdp entity) {
		adjustOkdp(entity);
	}
	
 }
