/*
 * CurrentStockSituationService.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.jboss;

import java.io.Serializable;
import java.util.List;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.reference.CurrentStockSituation;
import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.support.CurrentStockSituationImpl;

/**
 * Реализация сервиса рассчёта количества на складах для JBoss
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituationService.java,v 1.4 2008/09/09 12:29:01 sharapov Exp $
 */
@Service(objectName=CurrentStockSituationServiceMBean.SERVICE_NAME, name="merp/warehouse/CurrentStockSituationService")
@Management(CurrentStockSituationServiceMBean.class)
public class CurrentStockSituationService extends ServiceMBeanSupport implements
		CurrentStockSituationServiceMBean {
	private CurrentStockSituation delegate = new CurrentStockSituationImpl();

	public StockSituationValues getSituation(OrgUnit warehouse, Contractor mol, Catalog catalog) {
		return delegate.getSituation(warehouse, mol, catalog);
	}

	public List<StockSituationValues> getSituation(Catalog catalog) {
		return delegate.getSituation(catalog);
	}

	public void showSituationForm(Catalog catalog) {
		delegate.showSituationForm(catalog);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Catalog, boolean)
	 */
	public StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog, boolean onlyAvailable) {
		return delegate.getSituation(warehouse, catalog, onlyAvailable);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Catalog)
	 */
	public StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog) {
		return delegate.getSituation(warehouse, catalog);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Catalog, boolean)
	 */
	public StockSituationValues getSituation(OrgUnit warehouse, Contractor mol, Catalog catalog, boolean onlyAvailable) {
		return delegate.getSituation(warehouse, mol, catalog, onlyAvailable);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#showSituationForm(java.io.Serializable[])
	 */
	public void showSituationForm(Serializable[] catalogIds) {
		delegate.showSituationForm(catalogIds);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#getAgregateSituation(com.mg.merp.reference.model.Catalog)
	 */
	public StockSituationValues getAgregateSituation(Catalog catalog) {
		return delegate.getAgregateSituation(catalog);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CurrentStockSituation#getAgregateSituation(java.lang.Integer)
	 */
	public StockSituationValues getAgregateSituation(Integer catalogId) {
		return delegate.getAgregateSituation(catalogId);
	}

}
