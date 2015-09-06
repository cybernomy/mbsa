package com.mg.merp.wb.core.ui;

import com.mg.merp.wb.core.ui.plugin.MerpUIPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import java.util.ResourceBundle;

/**
 * The main plugin class to be used in the desktop.
 */
public class UiPlugin extends MerpUIPlugin {

  public static final String ID = "com.mg.merp.wb.core.ui";
  public static final String CHECK_SERVER = "server.check.message";
  public static final String OPERATION_ERROR = "server.operation.message";
  public static final String UNKNOWN_EXCEPTION = "server.unknown.error";
  public static final String MENU_ADD = "menu.add";
  public static final String ADD_ICO = "icons/addbuttonicon.png";
  public static final String MENU_EDIT = "menu.edit";
  public static final String EDIT_ICO = "icons/editbuttonicon.png";
  public static final String MENU_DEL = "menu.delete";
  public static final String DEL_ICO = "icons/erasebuttonicon.png";
  public static final String BUTT_SAVE_TEXT = "button.save";
  public static final String BUTT_CANCEL_TEXT = "button.cancel";
  public static final String BUTT_OK_TEXT = "button.ok";
  public static final String BUTT_BROWSE_TEXT = "button.browse";
  public static final String FIND_ICO = "icons/restbuttonicon.png";
  public static final String VIEW_ICO = "icons/viewbuttonicon.png";
  public static final String BUTT_YES_TEXT = "button.yes";
  public static final String BUTT_NO_TEXT = "button.no";
  private static final String RESOURCE_NAME = "com.mg.merp.wb.core.ui.messages";
  // The shared instance.
  private static UiPlugin plugin;

  /**
   * The constructor.
   */
  public UiPlugin() {
    plugin = this;
  }

  /**
   * Returns the shared instance.
   */
  public static UiPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in relative path.
   *
   * @param path the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
  }

  /**
   * This method is called upon plug-in activation
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
  }

  /**
   * This method is called when the plug-in is stopped
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }
}
