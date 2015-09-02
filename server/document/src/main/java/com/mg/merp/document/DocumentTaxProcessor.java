/*
 * DocumentTaxProcessor.java
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
package com.mg.merp.document;

import java.math.BigDecimal;

import javax.ejb.Local;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.math.RoundContext;
import com.mg.merp.document.model.DocSpec;

/**
 * ѕроцессор обработки налогов на документах
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentTaxProcessor.java,v 1.2 2007/09/07 12:10:41 safonov Exp $
 */
@Local
public interface DocumentTaxProcessor extends BusinessObjectService {

	/**
	 * им€ сервиса
	 */
	final static String SERVICE_NAME = "merp/document/DocumentTaxProcessor";
	
	/**
	 * расчет налогов дл€ спецификации документа
	 * 
	 * @param spec			спецификаци€ документа
	 * @param total			если true то расчет от summa, price (от общих), иначе расчет от summa1, price1 (от сум/цен с включенными налогами)
	 * @param roundContext	контекст расчета
	 */
	void calculateDocumentSpecTaxes(DocSpec spec, boolean total, RoundContext roundContext);

	/**
	 * расчет налогов дл€ спецификации документа по указанной цене и сумме
	 * 
	 * @param spec			спецификаци€ документа
	 * @param price			цена дл€ расчета
	 * @param sum			сумма дл€ расчета
	 * @param total			если true то расчет от summa, price (от общих), иначе расчет от summa1, price1 (от сум/цен с включенными налогами)
	 * @param roundContext	контекст расчета
	 */
	void calculateDocumentSpecTaxes(DocSpec spec, BigDecimal price, BigDecimal sum, boolean total, RoundContext roundContext);
	
}
