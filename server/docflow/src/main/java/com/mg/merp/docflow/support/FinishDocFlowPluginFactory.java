/**
 * FinishDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.docflow.support;

import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;

/**
 * Реализация фабрики реализаций этапа ДО "Завершение ДО" (встроенный этап, предназначен для
 * завершения ДО даже если существуют этапы для выполнения)
 *
 * @author Oleg V. Safonov
 * @version $Id: FinishDocFlowPluginFactory.java,v 1.1 2008/07/31 14:22:00 safonov Exp $
 */
public class FinishDocFlowPluginFactory extends AbstractDocFlowPluginFactory {
  public final static int FACTORY_IDENTIFIER = DocFlowManager.DOC_FLOW_END;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new FinishDocFlowPlugin();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
   */
  @Override
  public int getIdentifier() {
    return FACTORY_IDENTIFIER;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
   */
  @Override
  public String getName() {
    return "Finish docflow";
  }

  class FinishDocFlowPlugin extends AbstractDocFlowPlugin {

    @Override
    protected void doExecute() throws Exception {
      //do nothing
      complete();
    }

    @Override
    protected void doRoolback() throws Exception {
      //do nothing
      complete();
    }

  }

}
