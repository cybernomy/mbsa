/*
 * MRPReportServiceBean.java
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
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.planning.MRPReportServiceLocal;
import com.mg.merp.planning.model.MrpReport;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Бизнес-компонент "Итоги расчета ППМ" 
 * 
 * @author leonova
 * @version $Id: MRPReportServiceBean.java,v 1.4 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name="merp/planning/MRPReportService")
@PermitAll
public class MRPReportServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MrpReport, Integer> implements MRPReportServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.MRPReportServiceLocal#clear(com.mg.merp.planning.model.MrpVersionControl)
	 */
	public void clear(MrpVersionControl mrpVersion) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.MRPReport.clear", "mrpVersionControl", mrpVersion);
	}

}
