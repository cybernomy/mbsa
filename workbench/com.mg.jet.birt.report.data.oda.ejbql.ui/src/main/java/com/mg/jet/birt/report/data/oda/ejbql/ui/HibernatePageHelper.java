/**
 * HibernatePageHelper.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.jet.birt.report.data.oda.ejbql.ui;


import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.util.Properties;

/**
 * Утилиты
 *
 * @author Oleg V. Safonov
 * @version $Id: HibernatePageHelper.java,v 1.1 2007/10/29 08:54:16 safonov Exp $
 */
public class HibernatePageHelper {
  static final String DEFAULT_MESSAGE = Messages.getString("wizard.defaultMessage.dataSourcePage.Title");
  private static final int ERROR_FOLDER = 1;
  private static final int ERROR_EMPTY_PATH = 2;
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private WizardPage m_wizardPage;
  private PreferencePage m_propertyPage;
  private transient Text m_mapLocation = null;
  private transient Button m_browseMapButton = null;
  private transient Text m_configLocation = null;
  private transient Button m_browseConfigButton = null;
  private transient Text jndiName = null;

  HibernatePageHelper(WizardPage page) {
    m_wizardPage = page;
  }

  HibernatePageHelper(PreferencePage page) {
    m_propertyPage = page;
  }

  void createCustomControl(Composite parent) {
    Composite content = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout(3, false);
    content.setLayout(layout);

    //GridData data;
    setupConfigLocation(content);
    setupMapLocation(content);
    setupJndiName(content);
  }

  String getMapDir() {
    if (m_mapLocation == null)
      return EMPTY_STRING;
    return m_mapLocation.getText();
  }

  String getConfig() {
    if (m_configLocation == null)
      return EMPTY_STRING;
    return m_configLocation.getText();
  }

  String getODAJndiName() {
    if (jndiName == null)
      return EMPTY_STRING;
    return jndiName.getText();
  }

  Properties collectCustomProperties(Properties props) {
    if (props == null)
      props = new Properties();

    // set custom driver specific properties
    props.setProperty("HIBCONFIG", getConfig());
    props.setProperty("MAPDIR", getMapDir());
    props.setProperty("JNDI_NAME", getODAJndiName());

    return props;
  }

  void initCustomControl(Properties profileProps) {
    setPageComplete(true);
    setMessage(DEFAULT_MESSAGE, IMessageProvider.NONE);

    if (profileProps == null || profileProps.isEmpty()/* ||
            m_configLocation == null */)
      return;     // nothing to initialize

    String configPath = profileProps.getProperty("HIBCONFIG");
    if (configPath == null)
      configPath = EMPTY_STRING;
    m_configLocation.setText(configPath);

    String mapPath = profileProps.getProperty("MAPDIR");
    if (mapPath == null)
      mapPath = EMPTY_STRING;
    m_mapLocation.setText(mapPath);

    String jndiNameStr = profileProps.getProperty("JNDI_NAME");
    if (jndiNameStr == null)
      jndiNameStr = EMPTY_STRING;
    jndiName.setText(jndiNameStr);

    verifyConfigLocation();
  }

  private void setupConfigLocation(Composite composite) {
    Label label = new Label(composite, SWT.NONE);
    label.setText(Messages.getString("wizard.dataSourcePage.SelectCfgFile")); //$NON-NLS-1$

    GridData data = new GridData(GridData.FILL_HORIZONTAL);

    m_configLocation = new Text(composite, SWT.BORDER);
    m_configLocation.setLayoutData(data);
    setPageComplete(true);
    m_configLocation.addModifyListener(
        new ModifyListener() {
          public void modifyText(ModifyEvent e) {
            verifyConfigLocation();
          }

        });

    m_browseConfigButton = new Button(composite, SWT.NONE);
    m_browseConfigButton.setText("..."); //$NON-NLS-1$
    m_browseConfigButton.addSelectionListener(
        new SelectionAdapter() {
          /*
            * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
            */
          public void widgetSelected(SelectionEvent e) {
            FileDialog dialog = new FileDialog(
                m_configLocation.getShell());
            if (m_configLocation.getText() != null &&
                m_configLocation.getText().trim().length() > 0) {
              dialog.setFilterPath(m_configLocation.getText());
            }

            dialog.setText(Messages.getString("wizard.dataSourcePage.SelectCfgFile"));
            String selectedLocation = dialog.open();
            if (selectedLocation != null) {
              m_configLocation.setText(selectedLocation);
            }
          }
        });
  }

  private int verifyConfigLocation() {
    int result = 0;
    if (m_configLocation.getText().trim().length() > 0) {
      File f = new File(m_configLocation.getText().trim());
      if (f.exists()) {
        setMessage(DEFAULT_MESSAGE, IMessageProvider.NONE);
        setPageComplete(true);
      } else {
        setMessage(Messages.getString("wizard.dataSourcePage.cfgFileError"), IMessageProvider.ERROR); //$NON-NLS-1$
        setPageComplete(false);
        result = ERROR_FOLDER;
      }
    } else {
      setMessage(Messages.getString("wizard.dataSourcePage.cfgIsEmpty"), IMessageProvider.ERROR); //$NON-NLS-1$
      setPageComplete(true);
      result = ERROR_EMPTY_PATH;
    }
    return result;
  }


  private void setupMapLocation(Composite composite) {
    Label label = new Label(composite, SWT.NONE);
    label.setText(Messages.getString("wizard.dataSourcePage.SelectMapDirectory")); //$NON-NLS-1$

    GridData data = new GridData(GridData.FILL_HORIZONTAL);

    m_mapLocation = new Text(composite, SWT.BORDER);
    m_mapLocation.setLayoutData(data);
    setPageComplete(false);

    m_browseMapButton = new Button(composite, SWT.NONE);
    m_browseMapButton.setText("..."); //$NON-NLS-1$
    m_browseMapButton.addSelectionListener(
        new SelectionAdapter() {
          /*
            * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
            */
          public void widgetSelected(SelectionEvent e) {
            DirectoryDialog dialog = new DirectoryDialog(
                m_mapLocation.getShell());
            if (m_mapLocation.getText() != null &&
                m_mapLocation.getText().trim().length() > 0) {
              dialog.setFilterPath(m_mapLocation.getText());
            }

            dialog.setMessage(Messages.getString("wizard.dataSourcePage.SelectMapDirectory"));
            String selectedLocation = dialog.open();
            if (selectedLocation != null) {
              m_mapLocation.setText(selectedLocation);
            }
          }
        });
  }

  private void setupJndiName(Composite composite) {
    Label label = new Label(composite, SWT.NONE);
    label.setText("JNDI URL:"); //$NON-NLS-1$

    GridData data = new GridData(GridData.FILL_HORIZONTAL);

    jndiName = new Text(composite, SWT.BORDER);
    jndiName.setLayoutData(data);
    setPageComplete(false);
  }

  private void setPageComplete(boolean complete) {
    if (m_wizardPage != null)
      m_wizardPage.setPageComplete(complete);
    else if (m_propertyPage != null)
      m_propertyPage.setValid(complete);
  }

  private void setMessage(String newMessage, int newType) {
    if (m_wizardPage != null)
      m_wizardPage.setMessage(newMessage, newType);
    else if (m_propertyPage != null)
      m_propertyPage.setMessage(newMessage, newType);
  }
}
