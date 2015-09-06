/*
 * FacturaHeadModelOutServiceBean.java
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

package com.mg.merp.factura.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.factura.FacturaHeadModelOutServiceLocal;
import com.mg.merp.factura.FacturaHeadOutServiceLocal;
import com.mg.merp.factura.model.FacturaHeadModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы исходящих счет - фактур"
 *
 * @author leonova
 * @version $Id: FacturaHeadModelOutServiceBean.java,v 1.3 2006/09/12 11:11:21 leonova Exp $
 */
@Stateless(name = "merp/factura/FacturaHeadModelOutService")
public class FacturaHeadModelOutServiceBean extends DocumentModelServiceBean<FacturaHeadModel, Integer> implements FacturaHeadModelOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return FacturaHeadOutServiceLocal.DOCSECTION;
  }


}
