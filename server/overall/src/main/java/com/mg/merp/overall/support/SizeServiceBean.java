/*
 * SizeServiceBean.java
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

package com.mg.merp.overall.support;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.overall.SizeServiceLocal;
import com.mg.merp.overall.model.Size;

/**
 * Реализация бизнес-компонента "Размеры"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: SizeServiceBean.java,v 1.1 2008/06/30 04:20:11 alikaev Exp $
 */
@Stateless(name="merp/overall/SizeService")
public class SizeServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Size, Integer> implements SizeServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, final Size entity) {
		context.addRule(new MandatoryAttribute(entity, "CatalogGroupsTypeId"));
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "CatalogGroupsTypeId") {

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.validator.AbstractRule#doValidate(com.mg.framework.api.validator.ValidationContext)
			 */
			@Override
			protected void doValidate(ValidationContext context) {
				if (!findByCriteria(Restrictions.eq("CatalogGroupsTypeId", entity.getCatalogGroupsTypeId()), Restrictions.eq("OvrCard", entity.getOvrCard()), entity.getId() != null ? Restrictions.ne("Id", entity.getId()) : Restrictions.isNotNull("Id")).isEmpty())
					context.getStatus().error(this);
			}			
		});
	}

}
