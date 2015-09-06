/*
 * FacturaHeadModelInServiceBean.java
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
import com.mg.merp.factura.FacturaHeadInServiceLocal;
import com.mg.merp.factura.FacturaHeadModelInServiceLocal;
import com.mg.merp.factura.model.FacturaHeadModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы входящих счет - фактур"
 *
 * @author leonova
 * @version $Id: FacturaHeadModelInServiceBean.java,v 1.3 2006/09/12 11:11:21 leonova Exp $
 */
@Stateless(name = "merp/factura/FacturaHeadModelInService")
public class FacturaHeadModelInServiceBean extends DocumentModelServiceBean<FacturaHeadModel, Integer> implements FacturaHeadModelInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return FacturaHeadInServiceLocal.DOCSECTION;
  }

}
