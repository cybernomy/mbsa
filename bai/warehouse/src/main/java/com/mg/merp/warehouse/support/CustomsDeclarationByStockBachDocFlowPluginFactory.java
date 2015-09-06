/*
 * CustomsDeclarationByStockBachDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessorServiceLocal;

/**
 * Реализация фабрики реализации этапа ДО "Проставить номер ГТД из складской партии"
 *
 * @author Artem V. Sharapov
 * @version $Id: CustomsDeclarationByStockBachDocFlowPluginFactory.java,v 1.1 2008/08/27 09:41:21
 *          sharapov Exp $
 */
public class CustomsDeclarationByStockBachDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

  /**
   * Идентификатор фабрики
   */
  public final static int FACTORY_IDENTIFIER = 10005;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractDocFlowPlugin() {
      private WarehouseProcessorServiceLocal warehouseProcessorService = (WarehouseProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseProcessorServiceLocal.LOCAL_SERVICE_NAME);

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
       */
      @Override
      protected void doExecute() throws Exception {
        warehouseProcessorService.proccessCustomsDeclarationByStockBach(getParams(), new WarehouseProcessListener() {

          /* (non-Javadoc)
           * @see com.mg.merp.warehouse.WarehouseProcessListener#aborted()
           */
          public void aborted() {
            aborted();
          }

          /* (non-Javadoc)
           * @see com.mg.merp.warehouse.WarehouseProcessListener#completed()
           */
          public void completed() {
            complete();
          }
        });
      }

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
       */
      @Override
      protected void doRoolback() throws Exception {
        warehouseProcessorService.rollbackCustomsDeclarationByStockBach(getParams());
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
    return "Set customs declaration by stockBatch";
  }

}
