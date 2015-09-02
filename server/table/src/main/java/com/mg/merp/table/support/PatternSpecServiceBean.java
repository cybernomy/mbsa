/*
 * PatternSpecServiceBean.java
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

package com.mg.merp.table.support;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.table.PatternSpecServiceLocal;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternSpec;

/**
 * Реализация бизнес-компонента "Спецификация шаблона графика"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: PatternSpecServiceBean.java,v 1.6 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name="merp/table/PatternSpecService") //$NON-NLS-1$
public class PatternSpecServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<PatternSpec, Integer> implements PatternSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.table.PatternSpecServiceLocal#updateSpecs(java.util.List)
	 */
	@PermitAll
	public void updateSpecs(List<PatternSpec[]> specList) {
		doUpdateSpecs(specList);
	}

	protected void doUpdateSpecs(List<PatternSpec[]> specList) {
		for (int i = 0; i < specList.size(); i++) {
			PatternSpec[] specs = specList.get(i);
			for (int j = 1; j < specs.length; j++) {
				PatternSpec spec = specs[j];
				// позиция спецификации была добавлена
				if(spec.getId() == null) {
					if(i == 0) { 
						// был установлен тип времени с учетом по дням или кол-во часов
						if((spec.getHoursQuantity() == null && !spec.getTimeKind().getId().equals(specs[0].getTimeKind().getId())) || (MathUtils.compareToZeroOrNull(spec.getHoursQuantity()) != 0)) 
							create(spec);
					} else if(MathUtils.compareToZeroOrNull(spec.getHoursQuantity()) != 0)
						create(spec);
				}
				// позиция спецификации была изменена
				else if(i == 0) {
					// был установлен тип времени с учетом по дням или кол-во часов
					if((spec.getHoursQuantity() == null && !spec.getTimeKind().getId().equals(specs[0].getTimeKind().getId())) || (MathUtils.compareToZeroOrNull(spec.getHoursQuantity()) != 0))
						store(spec);
					else // кол-во часов = 0
						erase(spec);
				} else { 
					if(MathUtils.compareToZeroOrNull(spec.getHoursQuantity()) == 0)
						erase(spec);
					else
						store(spec);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.table.PatternSpecServiceLocal#loadSpecs(com.mg.merp.table.model.PatternHead)
	 */
	@PermitAll
	public List<PatternSpec> loadSpecs(PatternHead patternHead) {
		return doLoadSpecs(patternHead);
	}

	protected List<PatternSpec> doLoadSpecs(PatternHead patternHead) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PatternSpec.class)
				.add(Restrictions.eq("PatternHead", patternHead))); //$NON-NLS-1$
	}

}
