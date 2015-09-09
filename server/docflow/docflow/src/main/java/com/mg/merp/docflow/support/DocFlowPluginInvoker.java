/*
 * DocFlowPluginInvoker.java
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
package com.mg.merp.docflow.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocFlowPluginListener;
import com.mg.merp.docflow.PluginNotImplementedException;
import com.mg.merp.docflow.generic.BaseDocFlowBusinessAddin;

import java.util.HashMap;
import java.util.Map;

/**
 * Активатор этапов ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginInvoker.java,v 1.2 2007/12/14 08:48:53 safonov Exp $
 */
public class DocFlowPluginInvoker {
  private Logger logger = ServerUtils.getLogger(DocFlowPluginInvoker.class);
  //private DocFlowManager manager;
  private DocFlowPluginListener listener;

  public DocFlowPluginInvoker(final DocFlowManager manager, final DocFlowPluginListener listener) {
    this.listener = listener;
  }

  private Map<String, DocFlowPluginInvokeParams> createParams(DocFlowPluginInvokeParams params) {
    Map<String, DocFlowPluginInvokeParams> result = new HashMap<String, DocFlowPluginInvokeParams>();
    result.put(BaseDocFlowBusinessAddin.DOCFLOW_PARAMS_NAME, params);
    return result;
  }

  private DocFlowPlugin getPlugin(int pluginIdentifier) {
    DocFlowPluginFactory factory = DocFlowPluginFactoryManagerServiceLocator.locate().findPluginFactory(pluginIdentifier);
    DocFlowPlugin result = factory.createPlugin();
    if (result == null)
      throw new PluginNotImplementedException();
    result.registerListener(listener);
    if (logger.isDebugEnabled())
      logger.debug(String.format("Created document flow plug-in: %s", factory.getName()));
    return result;
  }

  private void internalExecute(DocFlowPluginInvokeParams params) throws Exception {
    DocFlowPlugin plugin = getPlugin(params.getPerformedStage().getStage().getId());
    if (params.getPerformedStage().getPrePerformBusinessAddin() != null)
      BusinessAddinEngineLocator.locate().perform(params.getPerformedStage().getPrePerformBusinessAddin(), createParams(params), new BusinessAddinListenerImpl(params, plugin, true));
    else
      plugin.execute(params);
  }

  private void internalRollback(DocFlowPluginInvokeParams params) throws Exception {
    DocFlowPlugin plugin = getPlugin(params.getPerformedStage().getStage().getId());
    if (params.getPerformedStage().getPreRollbackBusinessAddin() != null)
      BusinessAddinEngineLocator.locate().perform(params.getPerformedStage().getPreRollbackBusinessAddin(), createParams(params), new BusinessAddinListenerImpl(params, plugin, false));
    else
      plugin.rollback(params);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowAlgorithm#execute()
   */
  public void execute(DocFlowPluginInvokeParams params) throws Exception {
    internalExecute(params);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowAlgorithm#rollback()
   */
  public void rollback(DocFlowPluginInvokeParams params) throws Exception {
    internalRollback(params);
  }

  private class BusinessAddinListenerImpl implements BusinessAddinListener<Void> {
    private DocFlowPlugin plugin;
    private boolean isPerform;
    private DocFlowPluginInvokeParams params;

    private BusinessAddinListenerImpl(DocFlowPluginInvokeParams params, DocFlowPlugin plugin, boolean isPerform) {
      this.params = params;
      this.plugin = plugin;
      this.isPerform = isPerform;
    }

    public void aborted(BusinessAddinEvent<Void> event) {
      listener.canceled();
    }

    public void completed(BusinessAddinEvent<Void> event) {
      try {
        if (isPerform)
          plugin.execute(params);
        else
          plugin.rollback(params);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    }

  }

}
