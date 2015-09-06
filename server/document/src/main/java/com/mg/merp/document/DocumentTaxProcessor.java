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

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.math.RoundContext;
import com.mg.merp.document.model.DocSpec;

import java.math.BigDecimal;

import javax.ejb.Local;

/**
 * Процессор обработки налогов на документах
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentTaxProcessor.java,v 1.2 2007/09/07 12:10:41 safonov Exp $
 */
@Local
public interface DocumentTaxProcessor extends BusinessObjectService {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/document/DocumentTaxProcessor";

  /**
   * расчет налогов для спецификации документа
   *
   * @param spec         спецификация документа
   * @param total        если true то расчет от summa, price (от общих), иначе расчет от summa1,
   *                     price1 (от сум/цен с включенными налогами)
   * @param roundContext контекст расчета
   */
  void calculateDocumentSpecTaxes(DocSpec spec, boolean total, RoundContext roundContext);

  /**
   * расчет налогов для спецификации документа по указанной цене и сумме
   *
   * @param spec         спецификация документа
   * @param price        цена для расчета
   * @param sum          сумма для расчета
   * @param total        если true то расчет от summa, price (от общих), иначе расчет от summa1,
   *                     price1 (от сум/цен с включенными налогами)
   * @param roundContext контекст расчета
   */
  void calculateDocumentSpecTaxes(DocSpec spec, BigDecimal price, BigDecimal sum, boolean total, RoundContext roundContext);

}
