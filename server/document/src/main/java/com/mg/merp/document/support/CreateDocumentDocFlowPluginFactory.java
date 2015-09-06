/*
 * CreateDocumentDocFlowPluginFactory.java
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
package com.mg.merp.document.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.DocumentProcessorServiceLocal;
import com.mg.merp.document.generic.AbstractCreateDocumentDocFlowPlugin;

/**
 * Реализация фабрики реализаций этапа ДО "Создание документа на основе текущего"
 *
 * @author Oleg V. Safonov
 * @version $Id: CreateDocumentDocFlowPluginFactory.java,v 1.4 2009/02/04 09:37:32 safonov Exp $
 */
public class CreateDocumentDocFlowPluginFactory extends AbstractDocFlowPluginFactory {
  public final static int FACTORY_IDENTIFIER = 3;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#createPlugin()
   */
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractCreateDocumentDocFlowPlugin() {

      @Override
      protected void doExecute() throws Exception {
        DocumentProcessorServiceLocal service = (DocumentProcessorServiceLocal) ApplicationDictionaryLocator.locate()
            .getBusinessService(DocumentProcessorServiceLocal.LOCAL_SERVICE_NAME);
        service.processCreateDocumentBasisOf(getParams(), new CreateDocumentDocFlowListener() {

          public void canceled() {
            cancel();
          }

          public void completed() {
            complete();
          }

        });
      }

      @Override
      protected void doRoolback() throws Exception {
        DocumentProcessorServiceLocal service = (DocumentProcessorServiceLocal) ApplicationDictionaryLocator.locate()
            .getBusinessService(DocumentProcessorServiceLocal.LOCAL_SERVICE_NAME);
        service.rollbackCreateDocumentBasisOf(getParams(), new CreateDocumentDocFlowListener() {

          public void canceled() {
            cancel();
          }

          public void completed() {
            complete();
          }

        });
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#getIdentifier()
   */
  public int getIdentifier() {
    return FACTORY_IDENTIFIER;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#getName()
   */
  public String getName() {
    return "Create document";
  }

}
