/*
 * ResourceFamilyServiceBean.java
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

package com.mg.merp.mfreference.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.mfreference.ResourceFamilyServiceLocal;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.ResourceFamily;
import com.mg.merp.mfreference.model.ResourceGroup;

/**
 * Бизнес-компонент "Семейства ресурсов" 
 * 
 * @author leonova
 * @version $Id: ResourceFamilyServiceBean.java,v 1.4 2008/03/04 13:37:11 alikaev Exp $
 */
@Stateless(name="merp/mfreference/ResourceFamilyService") //$NON-NLS-1$
public class ResourceFamilyServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ResourceFamily, Integer> implements ResourceFamilyServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, final ResourceFamily entity) {
		context.addRule(new MandatoryAttribute(entity, "PlanningLevel"));		 //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "ChildResourceGroup"));		 //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "ParentResourceGroup")); //$NON-NLS-1$
		context.addRule(new Rule() {

			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.INVALID_RESOURSE_FAMILY_STRUCTURE);
			}

			public void validate(ValidationContext context) {
				ResourceGroup parentResGroup = entity.getParentResourceGroup();
				ResourceGroup childResGroup = entity.getChildResourceGroup();
				if (entity.getPlanningLevel() != null && parentResGroup != null && childResGroup != null 
						&& (parentResGroup.getResourceGroupCode().compareTo(childResGroup.getResourceGroupCode()) == 0 
						|| !internalCheckStructure(entity)))
					context.getStatus().error(this);
			}			
		});
		context.addRule(new Rule() {

			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.INVALID_RESOURSE_FAMILY_PLANNINGLEVEL);
			}

			public void validate(ValidationContext context) {
				PlanningLevel planningLevel = entity.getPlanningLevel();
				if (planningLevel != null && planningLevel.getPlanningLevelNum() == 1)
					context.getStatus().error(this);
			}			
		});
	}

	/**
	 * Если на каком-то уровне группа A является родительской для группы B, 
	 * то для этого уровня нельзя добавить запись, где группа A была бы для группы B дочерней
	 * 
	 * @param resourceFamily
	 * 				-	сущность
	 * @return		-	<code>false</code> на данном уровне планирования есть запись в которой значение родительской группы 
	 * совпадает со значением дочерней группы у текущей сущности и значение дочерней группы ресурсов совпадает с родительской у текущей сущности 
	 */
	private boolean internalCheckStructure(ResourceFamily resourceFamily) {
		List<ResourceFamily> resFamilyList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ResourceFamily.class)
				.add(Restrictions.eq("ChildResourceGroup", resourceFamily.getParentResourceGroup())) //$NON-NLS-1$
				.add(Restrictions.eq("PlanningLevel", resourceFamily.getPlanningLevel()))); //$NON-NLS-1$
		for (ResourceFamily resFamily : resFamilyList)
			if (resFamily.getParentResourceGroup().getResourceGroupCode().trim().compareTo(resourceFamily.getChildResourceGroup().getResourceGroupCode().trim()) == 0)
				return false;
		return true;
	}
	
}
