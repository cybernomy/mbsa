/*
 * WarehouseDocumentHeadModelOutServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.warehouse.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.warehouse.WarehouseDocumentHeadModelOutServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentHeadOutServiceLocal;
import com.mg.merp.warehouse.model.StockDocumentHeadModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы расходных ордеров"
 *
 * @author leonova
 * @version $Id: WarehouseDocumentHeadModelOutServiceBean.java,v 1.3 2006/09/12 10:48:36 leonova Exp
 *          $
 */
@Stateless(name = "merp/warehouse/WarehouseDocumentHeadModelOutService")
public class WarehouseDocumentHeadModelOutServiceBean extends DocumentModelServiceBean<StockDocumentHeadModel, Integer> implements WarehouseDocumentHeadModelOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return WarehouseDocumentHeadOutServiceLocal.DOCSECTION;
  }


}
