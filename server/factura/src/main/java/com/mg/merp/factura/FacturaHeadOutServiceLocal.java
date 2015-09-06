/*
 * FacturaHeadOutServiceLocal.java
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
package com.mg.merp.factura;

import com.mg.merp.factura.model.FacturaHead;

/**
 * @author leonova
 * @version $Id: FacturaHeadOutServiceLocal.java,v 1.3 2006/09/20 10:53:41 safonov Exp $
 */
public interface FacturaHeadOutServiceLocal
    extends com.mg.merp.document.GoodsDocument<FacturaHead, Integer, FacturaHeadModelOutServiceLocal, FacturaSpecOutServiceLocal> {
  /**
   * тип папки для входящих счет - фактур
   */
  final static short FOLDER_PART = 23;

  /**
   * docsection для входящих счет - фактур
   */
  final static short DOCSECTION = 13;
}
