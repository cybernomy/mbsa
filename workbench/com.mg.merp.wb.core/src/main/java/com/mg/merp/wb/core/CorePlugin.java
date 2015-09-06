package com.mg.merp.wb.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class CorePlugin extends Plugin {

  private static final String ID = "com.mg.merp.wb.core";//$NON-NLS-1$;
  //The shared instance.
  private static CorePlugin plugin;

  /**
   * The constructor.
   */
  public CorePlugin() {
    plugin = this;
  }

  /**
   * Returns the shared instance.
   */
  public static CorePlugin getDefault() {
    return plugin;
  }

  public static String getID() {
    return ID;
  }

  /**
   * This method is called upon plug-in activation
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /**
   * This method is called when the plug-in is stopped
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

}
