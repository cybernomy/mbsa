/*
 * InvoiceHeadBr.java
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
package com.mg.merp.warehouse.support.ui;

/**
 * Контроллер формы списка накладных
 *
 * @author leonova
 * @version $Id: InvoiceHeadBr.java,v 1.2 2008/02/22 11:08:33 alikaev Exp $
 */
public class InvoiceHeadBr extends WarehouseDocumentBr {
  protected final String INIT_QUERY_TEXT = "select %s from InvoiceHead d %s %s order by d.DocDate, d.Id ";

}
