/*
 * FacturaBr.java
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
package com.mg.merp.factura.support.ui;

import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;

/**
 * Контроллер формы списка счет - фактур
 *
 * @author leonova
 * @version $Id: FacturaBr.java,v 1.6 2008/02/21 12:20:40 alikaev Exp $
 */
public class FacturaBr extends GoodsDocumentBrowseForm {

  protected final String INIT_QUERY_TEXT = "select %s from FacturaHead d %s %s order by d.DocDate, d.Id ";

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.Through", restGoodDocument.getThroughCode(), "throughCode", paramsName, paramsValue, false));

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

}
