/*
 * UpdateJobFromScrapDocumentDocFlowPluginFactory.java
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
package com.mg.merp.manufacture.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.manufacture.ManufactureProcessorServiceLocal;

/**
 * @author Oleg V. Safonov
 * @version $Id: UpdateJobFromScrapDocumentDocFlowPluginFactory.java,v 1.1 2007/08/06 12:44:54
 *          safonov Exp $
 */
public class UpdateJobFromScrapDocumentDocFlowPluginFactory extends
    AbstractDocFlowPluginFactory {
  public final static int FACTORY_IDENTIFIER = 12004;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractDocFlowPlugin() {

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
       */
      @Override
      protected void doExecute() throws Exception {
        ((ManufactureProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ManufactureProcessorServiceLocal.SERVICE_NAME))
            .updateJobFromScrapDocument(getParams());
        complete();
      }

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
       */
      @Override
      protected void doRoolback() throws Exception {
        ((ManufactureProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ManufactureProcessorServiceLocal.SERVICE_NAME))
            .rollbackUpdateJobFromScrapDocument(getParams());
        complete();
      }

    };
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
    return "Update job from scrap document";
  }

}
