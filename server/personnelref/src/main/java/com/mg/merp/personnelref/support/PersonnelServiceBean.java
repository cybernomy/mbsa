/*
 * PersonnelServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.merp.personnelref.PersonnelServiceLocal;
import com.mg.merp.personnelref.model.Personnel;
import com.mg.merp.personnelref.model.PersonnelGroup;

/**
 * Бизнес-компонент "Основные сведения о сотрудниках" 
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: PersonnelServiceBean.java,v 1.4 2008/01/11 09:43:04 safonov Exp $
 */
@Stateless(name="merp/personnelref/PersonnelService")
public class PersonnelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Personnel, Integer> implements PersonnelServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	protected boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		for (Integer key : primaryKeys) {
			Personnel entity = load(key);
			entity.setGroup((PersonnelGroup) targetEntity);
		}
		return true;
	}

}
