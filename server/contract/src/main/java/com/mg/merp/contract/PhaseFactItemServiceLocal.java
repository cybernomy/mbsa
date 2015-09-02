/*
 * PhaseFactItemServiceLocal.java
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.FactItemData;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.document.model.DocHead;

/**
 * —ервис бизнес-компонента "‘актический пункт контракта"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseFactItemServiceLocal.java,v 1.4 2008/03/11 08:54:50 sharapov Exp $
 */
public interface PhaseFactItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PhaseFactItem, Integer> {
	
	/**
	 * »м€ сервиса
	 */
	static final String SERVICE_NAME = "merp/contract/PhaseFactItem"; //$NON-NLS-1$
	
	/**
	 * –ассчитать аттрибуты фактического пункта контракта
	 * @param phaseFactItem - фактический пункт контракта
	 */
	void adjust(PhaseFactItem phaseFactItem);
	
	/**
	 * –аспределить фактичекую сумму автоматически
	 * @param phaseFactItem - фактический пункт контракта
	 */
	void autoDistributionFactSum(PhaseFactItem phaseFactItem);

	/**
	 * –аспределить фактичекую сумму вручную
	 * @param data - структура данных дл€ распределени€ фактичекой суммы
	 * @param factItemId - идентификатор фактического пункта контракта
	 */
	void manualDistributionFactSum(ManualDistributionData[] data, Integer factItemId);

	/**
	 * јннулировать распределение фактической суммы
	 * @param factItemId - идентификатор фактического пункта контракта
	 */
	void avoidDistributionFactSum(Integer factItemId);
	
	/**
	 * —оздать фактический пункт контракта
	 * @param factItemData - данные дл€ создани€
	 * @return фактический пункт контракта
	 */
	PhaseFactItem createContractFactItem(FactItemData factItemData);

	/**
	 * ќткат создани€ фактического пункта контракта
	 * @param docHead - заголовок документа содержащего ссылку на контракт
	 * @param contractFactItemId - идентификатор фактического пункта контракта
	 * @param data - дополнительный признак
	 */
	void rollBackCreateContractFactItem(DocHead docHead, Integer contractFactItemId, Integer data);
	
}
