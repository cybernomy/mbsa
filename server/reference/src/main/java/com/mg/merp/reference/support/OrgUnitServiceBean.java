/*
 * OrgUnitServiceBean.java
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

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.OrgUnitServiceLocal;
import com.mg.merp.reference.generic.ContractorServiceBean;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.model.OrgUnitType;

/**
 * Подразделения
 * 
 * @author Oleg V. Safonov
 * @version $Id: OrgUnitServiceBean.java,v 1.9 2009/01/12 14:39:21 safonov Exp $
 */
@Stateless(name="merp/reference/OrgUnitService")
public class OrgUnitServiceBean extends ContractorServiceBean<OrgUnit> implements OrgUnitServiceLocal {
	
	private void setOrdUnitType(OrgUnit entity) {
		if (entity.getOrgUnitKind() == OrgUnitType.DEPARTMENT || entity.getOrgUnitKind() == OrgUnitType.ORGUNIT) {
			if (entity.getPartner() != null) {
				throw new ApplicationException(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.EXISTS_PARTNER));
			}
		}
	}
	
	@Override
	protected void onCreate(OrgUnit entity) {
		super.onCreate(entity);
		setOrdUnitType(entity);
	}

	@Override
	protected void onStore(OrgUnit entity) {
		super.onStore(entity);
		setOrdUnitType(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(OrgUnit entity) {
		entity.setOrgUnitKind(OrgUnitType.ORGANIZATION);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final OrgUnit entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "FullName"));
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") {
			@Override
			protected void doValidate(ValidationContext context) {
				if (OrmTemplate.getInstance().findUniqueByCriteria(OrgUnit.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId())) != null)
					context.getStatus().error(this);
			}
		});		
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.generic.ContractorServiceBean#doMove(java.lang.Integer[], java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			OrgUnit entity = load(key);
			OrgUnit targetOrgUnit = (OrgUnit) targetEntity;
			if (entity.getFolderId() != null && entity.getId() > targetOrgUnit.getId() && entity.getFolderId() != targetOrgUnit.getId()) {
				entity.setFolderId(targetOrgUnit.getId());
				result = true;
			}
		}
		return result;
	}

}
