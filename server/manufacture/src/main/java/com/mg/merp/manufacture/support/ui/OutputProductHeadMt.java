/*
 * OutputProductHeadMt.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.manufacture.OutputProductSpecServiceLocal;
import com.mg.merp.manufacture.model.OutputProductHead;

import java.util.Set;

/**
 * Контроллер формы поддержки Актов выпуска готовой продукции
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OutputProductHeadMt.java,v 1.8 2008/12/25 10:17:46 safonov Exp $
 */
public class OutputProductHeadMt extends GoodsDocumentMaintenanceForm {

  public OutputProductHeadMt() throws Exception {
    super();
    specService = ((OutputProductSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/OutputProductSpec"));

    spec.initController(specService, new ManufactureDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
      }

    });

    addMasterModelListener(spec);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doAddSpecification()
   */
  @Override
  protected void doAddSpecification() {
    ((OutputProductSpecServiceLocal) specService).createSpecifications((OutputProductHead) getEntity());
    spec.refresh();
  }

}
