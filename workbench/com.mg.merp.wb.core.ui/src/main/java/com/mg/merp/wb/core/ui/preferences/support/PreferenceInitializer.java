package com.mg.merp.wb.core.ui.preferences.support;

import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.preferences.MainPreferencePage;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  public void initializeDefaultPreferences() {
    IPreferenceStore store = UiPlugin.getDefault().getPreferenceStore();
    store.setDefault(MainPreferencePage.P_SERVERS_COMBO, 0);
  }

}
