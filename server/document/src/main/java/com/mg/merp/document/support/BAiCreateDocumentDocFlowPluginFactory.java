/**
 * BAiCreateDocumentDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document.support;

import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowBusinessAddin;
import com.mg.merp.document.generic.AbstractCreateDocumentDocFlowPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация фабрики реализаций этапа ДО "Создание документа на основе текущего
 * бизнес-расширением"
 *
 * @author Oleg V. Safonov
 * @version $Id: BAiCreateDocumentDocFlowPluginFactory.java,v 1.1 2007/09/27 07:19:59 safonov Exp $
 */
public class BAiCreateDocumentDocFlowPluginFactory extends
    AbstractDocFlowPluginFactory {

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractCreateDocumentDocFlowPlugin() {

      private Map<String, DocFlowPluginInvokeParams> createParams() {
        Map<String, DocFlowPluginInvokeParams> result = new HashMap<String, DocFlowPluginInvokeParams>();
        result.put(DocFlowBusinessAddin.DOCFLOW_PARAMS_NAME, getParams());
        return result;
      }

      @Override
      protected void doExecute() throws Exception {
        BusinessAddinEngineLocator.locate().perform(getParams().getPerformedStage().getPerformBusinessAddin(), createParams(), new BusinessAddinListener<Void>() {

          public void completed(BusinessAddinEvent<Void> event) {
            complete();
          }

          public void aborted(BusinessAddinEvent<Void> event) {
            cancel();
          }

        });
      }

      @Override
      protected void doRoolback() throws Exception {
        BusinessAddinEngineLocator.locate().perform(getParams().getPerformedStage().getRollbackBusinessAddin(), createParams(), new BusinessAddinListener<Void>() {

          public void completed(BusinessAddinEvent<Void> event) {
            complete();
          }

          public void aborted(BusinessAddinEvent<Void> event) {
            cancel();
          }

        });
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
   */
  @Override
  public int getIdentifier() {
    return DocFlowManager.CREATE_DOC_BY_BAI;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
   */
  @Override
  public String getName() {
    return "Create document by BAi";
  }

}
