/*
 * ProcessContractorCardDocFlowPluginFactory.java
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
package com.mg.merp.settlement.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.settlement.SettlementProcessorServiceLocal;

/**
 * Реализация фабрики реализации этапа ДО "Отработать в расчетах"
 *
 * @author Artem V. Sharapov
 * @version $Id: ProcessContractorCardDocFlowPluginFactory.java,v 1.1 2007/03/19 15:05:29 sharapov
 *          Exp $
 */
public class ProcessContractorCardDocFlowPluginFactory extends
    AbstractDocFlowPluginFactory {

  public final static int FACTORY_IDENTIFIER = 31;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractDocFlowPlugin() {

      SettlementProcessorServiceLocal settlementProcessorService = (SettlementProcessorServiceLocal)
          ApplicationDictionaryLocator.locate().getBusinessService(SettlementProcessorServiceLocal.LOCAL_SERVICE_NAME);

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
       */
      @Override
      protected void doExecute() throws Exception {
        settlementProcessorService.processInSettlement(getParams());
        complete();
      }

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
       */
      @Override
      protected void doRoolback() throws Exception {
        settlementProcessorService.rollBackProcessInSettlement(getParams());
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
    return "Process contractor card in settlement"; //$NON-NLS-1$
  }

}
