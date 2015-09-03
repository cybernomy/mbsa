/*
 * WareCardServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.model.StockCard;

/**
 * Бизнес-компонент "Карточки складского учета"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WareCardServiceBean.java,v 1.11 2008/04/18 15:22:11 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WareCardService")
public class WareCardServiceBean extends AbstractPOJODataBusinessObjectServiceBean<StockCard, Integer> implements
	WareCardServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(StockCard entity) {
		entity.setCardNumber(DataUtils.generateUniqueString(20));
	}

	private StockCard internalFindStockCard(Contractor warehouse, Contractor mol, Catalog catalog, boolean onlyAvailable, boolean byContractor) {
		Criteria criteria = OrmTemplate.createCriteria(StockCard.class, "sc")
				.add(Restrictions.eq("sc.Catalog", catalog))
				.add(Restrictions.eq("sc.Stock", warehouse));
		//учитываем МОЛ
		if (byContractor) {
			if (mol != null)
				criteria.add(Restrictions.eq("sc.Mol", mol));
			else
				criteria.add(Restrictions.isNull("sc.Mol"));
		}
		//учитываем права пользователя
		if (onlyAvailable)
			DatabaseUtils.generateFlatBrowseCriteria(criteria, "sc.Stock", 4);
		return OrmTemplate.getInstance().findUniqueByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WareCardServiceLocal#findStockCard(com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Catalog, boolean)
	 */
	@PermitAll
	public StockCard findStockCard(Contractor warehouse, Contractor mol, Catalog catalog, boolean onlyAvailable) {
		return internalFindStockCard(warehouse, mol, catalog, onlyAvailable, true);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WareCardServiceLocal#findStockCard(com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Catalog, boolean)
	 */
	@PermitAll
	public StockCard findStockCard(Contractor warehouse, Catalog catalog, boolean onlyAvailable) {
		return internalFindStockCard(warehouse, null, catalog, onlyAvailable, false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WareCardServiceLocal#deleteStockCards(java.util.List)
	 */
	@PermitAll
	public void deleteStockCards(StockCard ... sctockCards) {
		OrmTemplate.getInstance().bulkUpdate("delete from StockCard sc where sc in (:card) and not exists (select sb from StockBatch sb where sb.StockCard in (:card)) and not exists (select sph from StockPlanHistory sph where sph.StockCard in (:card))",
				"card", sctockCards);
	}

}
