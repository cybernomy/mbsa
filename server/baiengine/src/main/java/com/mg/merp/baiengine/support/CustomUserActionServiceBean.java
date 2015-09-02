/*
 * CustomUserActionServiceBean.java
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

package com.mg.merp.baiengine.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.baiengine.CustomUserActionServiceLocal;
import com.mg.merp.baiengine.model.CustomUserAction;
import com.mg.merp.baiengine.model.CustomUserActionPermiss;
import com.mg.merp.security.model.Groups;

/**
 * Бизнес-компонент "Настраиваемые действия пользователя" 
 * 
 * @author leonova
 * @version $Id: CustomUserActionServiceBean.java,v 1.1 2007/11/15 09:19:05 safonov Exp $
 */
@Stateless(name="merp/baiengine/CustomUserActionService")
public class CustomUserActionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CustomUserAction, Integer> implements CustomUserActionServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CustomUserAction entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryAttribute(entity, "SysClass"));
		context.addRule(new MandatoryAttribute(entity, "BAi"));		
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.CustomUserActionServiceLocal#grantPermission(int, int)
	 */
	@PermitAll
	public void grantPermission(int actionId, int roleId) {
		CustomUserActionPermiss perm = new CustomUserActionPermiss();
		perm.setSecGroup(getPersistentManager().find(Groups.class, roleId));
		perm.setCustomUserAction(load(actionId));
		getPersistentManager().persist(perm);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.CustomUserActionServiceLocal#revokePermission(com.mg.merp.baiengine.model.CustomUserActionPermiss[])
	 */
	@PermitAll
	public void revokePermission(CustomUserActionPermiss[] perms) {
		if (perms == null || perms.length == 0)
			return;
		
		for (CustomUserActionPermiss perm : perms)
			getPersistentManager().remove(perm);
	}

}
