/* MainPreferencePage.java
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
package com.mg.merp.wb.core.ui.preferences;

import com.mg.merp.wb.core.CorePlugin;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.support.connector.ServiceConnector;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.widgets.ComboFieldEditor;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Страница главных настроек студии разработки
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: MainPreferencePage.java,v 1.5 2007/09/03 13:04:05 safonov Exp $
 */
public class MainPreferencePage extends FieldEditorPreferencePage implements
    IWorkbenchPreferencePage {

  public static final String P_SERVERS_COMBO = "serversCombo";
  public static final String P_HOSTS_COMBO = "hostsCombo";
  public static final String P_PORTS_COMBO = "portsCombo";
  public static final String P_TEST_CONNECT = "testConnect";

  public static final String AS_JBOSS_4X = "JBoss AS 4.x";
  public static final String AS_WEBSPHERE_5X = "WebSphere AS 5.x";

  String[] servNames = {AS_JBOSS_4X, AS_WEBSPHERE_5X};

  ComboFieldEditor serv;
  ComboFieldEditor host;
  ComboFieldEditor port;

  public MainPreferencePage() {
    super(GRID);
    setPreferenceStore(UiPlugin.getDefault().getPreferenceStore());
    setDescription(UiPlugin.getDefault().getString("preferences.main.description"));
  }

  @Override
  protected void createFieldEditors() {
    //servers
    serv = new ComboFieldEditor(P_SERVERS_COMBO, UiPlugin.getDefault().getString("preferences.main.servers.label"),
        servNames, SWT.BORDER | SWT.READ_ONLY, getFieldEditorParent());
    addField(serv);
    //host name
    host = new ComboFieldEditor(P_HOSTS_COMBO, UiPlugin.getDefault().getString("preferences.main.hosts.label"), 5, SWT.BORDER, getFieldEditorParent());
    host.setMinimumWidth(90);
    addField(host);
    //port
    port = new ComboFieldEditor(P_PORTS_COMBO, UiPlugin.getDefault().getString("preferences.main.ports.label"), 5, SWT.BORDER, getFieldEditorParent());
    port.setMinimumWidth(50);
    addField(port);

    TestConnectionFieldEditor testConn = new TestConnectionFieldEditor(P_TEST_CONNECT, UiPlugin.getDefault().getString("preferences.main.testconnection.label"), getFieldEditorParent());
    testConn.setChangeButtonText(UiPlugin.getDefault().getString("preferences.main.testconnection.button.label"));
    testConn.getTextControl(getFieldEditorParent()).setEditable(false);
    addField(testConn);
  }

  public void init(IWorkbench workbench) {
  }

  public boolean performOk() {
    setConnectionParams();
    return super.performOk();
  }

  private void setConnectionParams() {
    int s = 0;
    if (serv.getComboControl(getFieldEditorParent()).getText().equals(AS_JBOSS_4X))
      s = ServiceConnector.AS_JBOSS_4X;
    else if (serv.getComboControl(getFieldEditorParent()).getText().equals(AS_WEBSPHERE_5X))
      s = ServiceConnector.AS_WEBSPHERE_5X;

    String hst = host.getComboControl(getFieldEditorParent()).getText();
    Integer prt = Integer.parseInt(port.getComboControl(getFieldEditorParent()).getText());
    if (CoreUtils.loadInt(CorePlugin.getDefault(), ServiceConnector.SERVER_TYPE) != s
        || !CoreUtils.loadString(CorePlugin.getDefault(), ServiceConnector.PRVD_HOST).equals(hst)
        || !CoreUtils.loadInt(CorePlugin.getDefault(), ServiceConnector.PRVD_PORT).equals(prt)) {
      CoreUtils.storeInt(CorePlugin.getDefault(), ServiceConnector.SERVER_TYPE, s);
      CoreUtils.storeString(CorePlugin.getDefault(), ServiceConnector.PRVD_HOST, host.getComboControl(getFieldEditorParent()).getText());
      CoreUtils.storeInt(CorePlugin.getDefault(), ServiceConnector.PRVD_PORT, Integer.parseInt(port.getComboControl(getFieldEditorParent()).getText()));
      ServiceConnector.updateConfig();
    }
  }

  private class TestConnectionFieldEditor extends StringButtonFieldEditor {

    public TestConnectionFieldEditor(String name, String labelText,
                                     Composite parent) {
      init(name, labelText);
      createControl(parent);
    }

    @Override
    protected String changePressed() {
      setConnectionParams();
      if (UiPlugin.testConnection())
        return UiPlugin.getDefault().getString("preferences.main.testconnection.ok");
      else
        return UiPlugin.getDefault().getString("preferences.main.testconnection.failed");
    }

    protected void doLoad() {
    }

    protected void doStore() {
    }
  }

}
