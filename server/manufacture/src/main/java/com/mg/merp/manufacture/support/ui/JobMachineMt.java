/*
 * JobMachineMt.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.mfreference.support.ui.CostDetailTableController;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: JobMachineMt.java,v 1.5 2007/07/30 10:27:10 safonov Exp $
 */
public class JobMachineMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap stdDetailLineProperties = new LocalDataTransferObject();
  protected AttributeMap actDetailLineProperties = new LocalDataTransferObject();
  private DefaultTableController stdDetailLine;
  private DefaultTableController actDetailLine;

  public JobMachineMt() throws Exception {
    stdDetailLine = new CostDetailTableController("StdCostDetail");
    addMasterModelListener(stdDetailLine);

    actDetailLine = new CostDetailTableController("ActCostDetail");
    addMasterModelListener(actDetailLine);

    addMasterModelListener(this);
  }

  public void masterChange(ModelChangeEvent event) {
    stdDetailLineProperties.put("JobRoute", event.getEntity());
    actDetailLineProperties.put("JobRoute", event.getEntity());
  }
}
