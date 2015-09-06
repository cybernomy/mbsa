/*
 * FinOperProcessorServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support;

import com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.finance.FinOperProcessorServiceLocal;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: FinOperProcessorServiceBean.java,v 1.1 2007/11/30 13:01:38 alikaev Exp $
 */
@Stateful(name = "merp/finance/FinOperProcessorService")
public class FinOperProcessorServiceBean extends AbstractPOJOBusinessObjectStatelessServiceBean implements FinOperProcessorServiceLocal {

  private FinOperProcessorImpl delegate = new FinOperProcessorImpl();

  /* (non-Javadoc)
   * @see com.mg.merp.finance.FinOperProcessor#processCreateFinOper(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void processCreateFinOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
    delegate.processCreateFinOper(params, listener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.finance.FinOperProcessor#rollbackCreateFinOper(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollbackCreateFinOper(DocFlowPluginInvokeParams params) {
    delegate.rollbackCreateFinOper(params);
  }

}
