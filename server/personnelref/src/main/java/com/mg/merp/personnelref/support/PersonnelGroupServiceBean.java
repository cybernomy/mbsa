/*
 * PersonnelGroupServiceBean.java
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

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.PersonnelGroupServiceLocal;
import com.mg.merp.personnelref.model.PersonnelGroup;

/**
 * Иерархическая структура основныех сведений о сотрудниках
 * 
 * @author leonova
 * @version $Id: PersonnelGroupServiceBean.java,v 1.5 2007/08/16 14:12:16 safonov Exp $
 */
@Stateless(name="merp/personnelref/PersonnelGroupService")
public class PersonnelGroupServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelGroup, Integer> implements PersonnelGroupServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			PersonnelGroup entity = load(key);
			PersonnelGroup targetGroup = (PersonnelGroup) targetEntity;
			if (entity.getParentId() != null && entity.getId() > targetGroup.getId() && entity.getParentId() != targetGroup.getId()) {
				entity.setParentId(targetGroup.getId());
				result = true;
			}
		}
		return result;
	}

}
