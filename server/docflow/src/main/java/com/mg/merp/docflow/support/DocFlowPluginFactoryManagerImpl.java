/*
 * DocFlowPluginFactoryManagerImpl.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.DocFlowPluginFactoryManager;
import com.mg.merp.docflow.PluginNotImplementedException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация менеджера фабрик реализаций этапов ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryManagerImpl.java,v 1.2 2007/01/29 14:06:55 safonov Exp $
 */
public class DocFlowPluginFactoryManagerImpl implements DocFlowPluginFactoryManager {
  private Logger log = ServerUtils.getLogger(getClass());
  private Map<Integer, DocFlowPluginFactory> pluginFactories = Collections.synchronizedMap(new HashMap<Integer, DocFlowPluginFactory>());

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#registerPluginFactory(int, com.mg.merp.docflow.DocFlowPluginFactory)
   */
  public void registerPluginFactory(DocFlowPluginFactory pluginFactory) {
    pluginFactories.put(pluginFactory.getIdentifier(), pluginFactory);
    log.info("Register docflow plugin factory: ".concat(pluginFactory.getName()));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#unregisterPluginFactory(int, com.mg.merp.docflow.DocFlowPluginFactory)
   */
  public void unregisterPluginFactory(DocFlowPluginFactory pluginFactory) {
    pluginFactories.remove(pluginFactory.getIdentifier());
    log.info("Unregister docflow plugin factory: ".concat(pluginFactory.getName()));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#findPluginFactory(int)
   */
  public DocFlowPluginFactory findPluginFactory(int identifier)
      throws PluginNotImplementedException {
    DocFlowPluginFactory result = pluginFactories.get(identifier);
    if (result == null)
      throw new PluginNotImplementedException();
    if (log.isDebugEnabled())
      log.debug(String.format("Factory %s located by identificator %d", result.getName(), identifier));
    return result;
  }

}
