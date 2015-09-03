/*
 * UserServiceBean.java
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

package com.mg.merp.security.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.UserServiceLocal;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.LinkUsersGroups;
import com.mg.merp.security.model.SecUser;

/**
 * Бизнес-компонент "Пользователи" 
 * 
 * @author leonova
 * @version $Id: UserServiceBean.java,v 1.5 2007/02/24 14:20:52 safonov Exp $
 */
@Stateless(name="merp/security/UserService")
public class UserServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SecUser, Integer> implements UserServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, SecUser entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
		context.addRule(new MandatoryStringAttribute(entity, "FullName"));		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(SecUser entity) {
		ServerUtils.getSecuritySystem().deleteUser(entity.getName());
	}

	private void doAddRole(int userId, int roleId) {
		LinkUsersGroups link = new LinkUsersGroups();
		link.setUser(getPersistentManager().find(SecUser.class, userId));
		link.setGroup(getPersistentManager().find(Groups.class, roleId));
		getPersistentManager().persist(link);
	}
	
	private void doRemoveRole(int userId, int roleId) {
		for (Object link : OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(LinkUsersGroups.class)
				.add(Restrictions.eq("User.Id", userId))
				.add(Restrictions.eq("Group.Id", roleId))))
			getPersistentManager().remove((PersistentObject) link);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.security.UserServiceLocal#includeInGroup(int, int)
	 */
	@PermitAll
	public void addRole(int userId, int roleId) {
		doAddRole(userId, roleId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.security.UserServiceLocal#excludeFromGroup(int, int)
	 */
	@PermitAll
	public void removeRole(int userId, int roleId) {
		doRemoveRole(userId, roleId);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.security.UserServiceLocal#findUser(java.lang.String)
	 */
	@PermitAll
	public SecUser findUser(String name) {
		return OrmTemplate.getInstance().findUniqueByCriteria(SecUser.class, Restrictions.eq("Name", name));
	}

}
