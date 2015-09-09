/**
 * BaseDocFlowBusinessAddin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.docflow.generic;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

import java.util.Map;

/**
 * Базовый класс BAi используемых в документообороте
 *
 * @author Oleg V. Safonov
 * @version $Id: BaseDocFlowBusinessAddin.java,v 1.1 2007/12/14 08:48:53 safonov Exp $
 */
public abstract class BaseDocFlowBusinessAddin extends AbstractBusinessAddin<Void> {
  public final static String DOCFLOW_PARAMS_NAME = "DOCFLOW_PARAMS";

  private DocFlowPluginInvokeParams docFlowParams;

  /**
   * получить параметры выполнения этапа ДО
   *
   * @return параметры выполнения ДО
   */
  protected DocFlowPluginInvokeParams getDocFlowParams() {
    return docFlowParams;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    this.docFlowParams = (DocFlowPluginInvokeParams) params.get(DOCFLOW_PARAMS_NAME);
    if (docFlowParams == null)
      throw new IllegalArgumentException("Document flow params is null");
  }

}
