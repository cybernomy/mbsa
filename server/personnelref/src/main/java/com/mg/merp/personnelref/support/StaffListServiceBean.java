/*
 * StaffListServiceBean.java
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

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.merp.personnelref.StaffListPositionServiceLocal;
import com.mg.merp.personnelref.StaffListServiceLocal;
import com.mg.merp.personnelref.StaffListUnitServiceLocal;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.StaffListUnit;

/**
 * Реализация бизнес-компонента "Варианты штатного расписания" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListServiceBean.java,v 1.5 2007/11/08 06:57:15 sharapov Exp $
 */
@Stateless(name="merp/personnelref/StaffListService") //$NON-NLS-1$
public class StaffListServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StaffList, Integer> implements StaffListServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, StaffList entity) {
		context.addRule(new MandatoryStringAttribute(entity, "LName")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(StaffList entity) {
		doCreateStaffListUnitForStaffList(entity);
	}

	/**
	 * Создать подразделение в штатном расписании для варианта ШР
	 * @param staffList - вариант штатного расписания
	 */
	protected void doCreateStaffListUnitForStaffList(StaffList staffList) {
		StaffListUnitServiceLocal staffListUnitService = (StaffListUnitServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(StaffListUnitServiceLocal.LOCAL_SERVICE_NAME);
		StaffListUnit staffListUnit = staffListUnitService.initialize();
		staffListUnit.setStaffList(staffList);
		staffListUnit.setUName(staffList.getLName());
		staffListUnit.setUCode("ROOT"); //$NON-NLS-1$
		staffListUnitService.create(staffListUnit);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onClone(StaffList entity) {
		entity.setLName(entity.getLName().trim().concat(DataUtils.generateUniqueString(5)));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(StaffList entity, StaffList entityClone) {
		final String STAFF_LIST_ATTRIBUTE_NAME = "StaffList"; //$NON-NLS-1$
		final String STAFF_LIST_UNIT_ATTRIBUTE_NAME = "StaffListUnit"; //$NON-NLS-1$
		StaffListUnitServiceLocal staffListUnitService = (StaffListUnitServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(StaffListUnitServiceLocal.LOCAL_SERVICE_NAME);
		StaffListPositionServiceLocal staffListPositionService = (StaffListPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListPosition"); //$NON-NLS-1$
		List<StaffListUnit> staffListUnits = staffListUnitService.findByCriteria(Restrictions.eq(STAFF_LIST_ATTRIBUTE_NAME, entity));
		List<StaffListUnit> clonedStaffListUnits = new ArrayList<StaffListUnit>();

		AttributeMap staffListPositionInitAttr = new LocalDataTransferObject();
		AttributeMap StaffListUnitInitAttr = new LocalDataTransferObject();
		StaffListUnitInitAttr.put(STAFF_LIST_ATTRIBUTE_NAME, entityClone);

		// удаление корневого подразделения
		for (StaffListUnit rootStaffListUnit : staffListUnitService.findByCriteria(Restrictions.eq(STAFF_LIST_ATTRIBUTE_NAME, entityClone)))
			staffListUnitService.erase(rootStaffListUnit);

		// копирование подразделений
		for (StaffListUnit staffListUnit : staffListUnits)
			clonedStaffListUnits.add(staffListUnitService.clone(staffListUnit, true, StaffListUnitInitAttr));

		for (int i = 0; i < clonedStaffListUnits.size(); i++) {	
			StaffListUnit clonedStaffListUnit = clonedStaffListUnits.get(i);
			StaffListUnit staffListUnit = staffListUnits.get(i);
			// настройка иерархии подразделений
			if(clonedStaffListUnit.getParent() != null)
				clonedStaffListUnit.setParent(clonedStaffListUnits.get(staffListUnits.indexOf(staffListUnit.getParent())));
			// копирование должностей в штатном расписании
			staffListPositionInitAttr.put(STAFF_LIST_UNIT_ATTRIBUTE_NAME, clonedStaffListUnit);
			for (StaffListPosition staffListPosition : staffListPositionService.findByCriteria(Restrictions.eq(STAFF_LIST_UNIT_ATTRIBUTE_NAME, staffListUnit)))
				staffListPositionService.clone(staffListPosition, true, staffListPositionInitAttr);
		}
	}

}
