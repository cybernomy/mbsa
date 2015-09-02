/*
 * UniversalContractorSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.ui;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.model.Partner;

/**
 * Универсальный поиск контрагентов, предназначен для поиска партнеров, подразделений, сотрудников
 * 
 * @author Oleg V. Safonov
 * @version $Id: UniversalContractorSearchHelp.java,v 1.1 2006/12/02 12:12:58 safonov Exp $
 */
public abstract class UniversalContractorSearchHelp extends AbstractSearchHelp {
	private static final String SEARCH_FORM = "com/mg/merp/reference/resources/ContractorSearchForm.mfd.xml"; //$NON-NLS-1$

	/**
	 * получить список типов контрагентов для поиска, может состоять из следующих значений:
	 * <code>partner</code>, <code>orgunit</code>, <code>employee</code> 
	 * 
	 * @return	список типов контрагентов
	 */
	protected abstract String[] getContractorKinds();

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		ContractorSearchForm form = (ContractorSearchForm) UIProducer.produceForm(SEARCH_FORM);
		form.addSearchHelpListener(this);
		form.execute(getContractorKinds());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doView(PersistentObject entity) {
		if (entity == null)
			return;
		
		String serviceName = null;
		PersistentObject realEntity = ServerUtils.getPersistentManager().find(Contractor.class, entity.getPrimaryKey());
		if (realEntity instanceof Partner)
			serviceName = "merp/reference/Partner";
		else if (realEntity instanceof OrgUnit)
			serviceName = "merp/reference/OrgUnit";
		else if (realEntity instanceof Employees)
			serviceName = "merp/reference/Employees";
		
		if (serviceName == null)
			return;
		
		DataBusinessObjectService service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(serviceName);
		MaintenanceHelper.view(service, (Serializable) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

}
