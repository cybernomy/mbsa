/*
 * MRPInputsServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.planning.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.merp.planning.MRPInputsServiceLocal;
import com.mg.merp.planning.model.MrpInputs;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Бизнес-компонент "Поступление товаров для расчета ППМ"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MRPInputsServiceBean.java,v 1.3 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name="merp/planning/MRPInputsService")
@PermitAll
public class MRPInputsServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<MrpInputs, Integer> implements MRPInputsServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.MRPInputsServiceLocal#clear(com.mg.merp.planning.model.MrpVersionControl)
	 */
	public void clear(MrpVersionControl mrpVersion) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.MRPInputs.clear", "mrpVersionControl", mrpVersion);
	}

}
