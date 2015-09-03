/*
 * StaffListUnitServiceBean.java
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

package com.mg.merp.personnelref.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.StaffListUnitServiceLocal;
import com.mg.merp.personnelref.model.StaffListUnit;

/**
 * Реализация бизнес-компонента "Подразделения в штатном расписании" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListUnitServiceBean.java,v 1.7 2007/11/08 06:57:15 sharapov Exp $
 */
@Stateless(name="merp/personnelref/StaffListUnitService") //$NON-NLS-1$
public class StaffListUnitServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StaffListUnit, Integer> implements StaffListUnitServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, StaffListUnit entity) {
		context.addRule(new MandatoryStringAttribute(entity, "UCode")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "UName"));	//$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(StaffListUnit entity) {
		doSetStaffList(entity);
	}

	/**
	 * Установить вариант ШР для подразделения в ШР
	 * @param staffListUnit - подразделение в ШР
	 */
	protected void doSetStaffList(StaffListUnit staffListUnit) {
		if(staffListUnit.getParent() != null && staffListUnit.getStaffList() == null)
			staffListUnit.setStaffList(staffListUnit.getParent().getStaffList());
		if(staffListUnit.getStaffList() == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.CHOOSE_ROOT_FOLDER));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			StaffListUnit entity = load(key);
			StaffListUnit targetUnit = (StaffListUnit) targetEntity;
			//не копируем корневую папку и папку на саму себя, идентификатор должен быть больше идентификатора приемника, в противном
			//случае невозможно построить дерево иерархии
			if (entity.getParent() != null && entity.getId() > targetUnit.getId() && entity.getParent().getId() != targetUnit.getId()) {
				entity.setParent(targetUnit);
				result = true;
			}
		}
		return result;
	}

}
