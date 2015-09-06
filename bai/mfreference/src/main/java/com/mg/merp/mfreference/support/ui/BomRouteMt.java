/*
 * BomRouteMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BomRouteMt.java,v 1.5 2007/07/30 10:24:19 safonov Exp $
 */
public class BomRouteMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap costDetailLineProperties = new LocalDataTransferObject();
  private DefaultTableController costDetailLine;

  public BomRouteMt() throws Exception {
    costDetailLine = new CostDetailTableController();
    addMasterModelListener(costDetailLine);

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    costDetailLineProperties.put("BomRoute", event.getEntity());
  }
}
