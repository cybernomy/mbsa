/*
 * CurrentStockSituation.java
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
package com.mg.merp.reference;

import java.io.Serializable;
import java.util.List;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;


/**
 * —ервис рассчЄта количества на складах
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituation.java,v 1.4 2008/09/09 13:33:54 sharapov Exp $
 */
public interface CurrentStockSituation {
	String SERVICE_NAME = "merp:warehouse=CurrentStockSituationService";
	
	/**
	 * –ассчЄт количества позиций каталога <code>catalog</code> 
	 * на складе <code>warehouse</code> с ћќЋ <code>mol</code> 
	 * 
	 * @param warehouse
	 * 			склад
	 * @param mol
	 * 			ћќЋ
	 * @param catalog
	 * 			позици€ каталога
	 * @return количество на складе или <code>null</code> если на складе нет доступных количеств
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
		Catalog catalog);

	/**
	 * –ассчЄт количества позиций каталога <code>catalog</code> 
	 * на складе <code>warehouse</code> с ћќЋ <code>mol</code> 
	 * 
	 * @param warehouse
	 * 			склад
	 * @param mol
	 * 			ћќЋ
	 * @param catalog
	 * 			позици€ каталога
	 * @param onlyAvailable
	 * 			искать только доступные дл€ текущего пользовател€
	 * @return количество на складе или <code>null</code> если на складе нет доступных количеств
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
		Catalog catalog, boolean onlyAvailable);

	/**
	 * –ассчЄт количества позиций каталога <code>catalog</code> 
	 * на складе <code>warehouse</code>
	 * 
	 * @param warehouse	склад
	 * @param catalog	позици€ каталога
	 * @return	количество на складе или <code>null</code> если на складе нет доступных количеств
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog);

	/**
	 * –ассчЄт количества позиций каталога <code>catalog</code> 
	 * на складе <code>warehouse</code>
	 * 
	 * @param warehouse	склад
	 * @param catalog	позици€ каталога
	 * @param onlyAvailable искать только доступные дл€ текущего пользовател€
	 * @return	количество на складе или <code>null</code> если на складе нет доступных количеств
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog, boolean onlyAvailable);

	/**
	 * –ассчЄт количества на всех складах
	 * 
	 * @return количество на складах
	 */
	List<StockSituationValues> getSituation(Catalog catalog);
	
	/**
	 * –ассчЄт агрегированного количества позиций каталога на всех складах 
	 * @param catalogId - идентификатор позиции каталога
	 * @return количества на всех складах
	 */
	StockSituationValues getAgregateSituation(Integer catalogId);
	
	/**
	 * –ассчЄт агрегированного количества позиций каталога на всех складах 
	 * @param catalog - позици€ каталога
	 * @return количества на всех складах
	 */
	StockSituationValues getAgregateSituation(Catalog catalog);
		
	/**
	 * ¬ызов формы, отображающей доступное количество на складах
	 *  дл€ позиции каталога <code>catalog</code>
	 * 
	 * @param catalog
	 * 		позици€ каталога
	 */
	void showSituationForm(Catalog catalog);

	/**
	 * ¬ызов форм, отображающих доступное количество на складах
	 * дл€ позиций каталога
	 * 
	 * @param catalogIds	список идентификаторов каталога
	 */
	void showSituationForm(Serializable[] catalogIds);

}
