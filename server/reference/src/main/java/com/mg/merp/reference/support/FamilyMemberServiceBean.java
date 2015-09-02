/*
 * FamilyMemberServiceBean.java
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.FamilyMemberServiceLocal;
import com.mg.merp.reference.model.FamilyMember;

/**
 * Бизнес-копмонент "Состав семьи"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: FamilyMemberServiceBean.java,v 1.5 2007/08/23 10:35:50 alikaev Exp $
 */
@Stateless(name="merp/reference/FamilyMemberService")
public class FamilyMemberServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FamilyMember, Integer> implements FamilyMemberServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, FamilyMember entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Surname"));
		context.addRule(new MandatoryStringAttribute(entity, "FamilyRelation"));
	}	

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(FamilyMember entity) {
		adjustFamilyMember(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(FamilyMember entity) {
		adjustFamilyMember(entity);
	}

	/**
	 * функция заполнения полей "Имя" и "Отчество" 
	 * 
	 * @param entity
	 */
	private void adjustFamilyMember(FamilyMember entity) {
		if(entity.getFName() == null)
			entity.setFName(StringUtils.EMPTY_STRING);
		if(entity.getPatronymic() == null)
			entity.setPatronymic(StringUtils.EMPTY_STRING);
	}

}
