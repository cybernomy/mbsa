/*
 * ContainerPage.java
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
package com.mg.merp.wb.badi.library;

import com.mg.merp.wb.badi.library.util.ContainerPageController;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import java.util.LinkedList;
import java.util.List;

import static com.mg.merp.wb.badi.library.util.Constants.DESCRIPTION;
import static com.mg.merp.wb.badi.library.util.Constants.WIZARD_DESC;

/**
 * Страница выбора наполнения для библиотеки
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: ContainerPage.java,v 1.5 2007/07/11 07:31:29 poroxnenko Exp $
 */
public class ContainerPage extends WizardPage implements
    IClasspathContainerPage, IClasspathContainerPageExtension {
  private static final Path containerPath = new Path(BadiLibraryPlugin
      .getDefault().getContainerID());
  private IClasspathEntry entry;
  private CheckboxTableViewer tableViewer;
  private ContainerPageController controller;
  private IJavaProject curProj;

  public ContainerPage() {
    super(BadiLibraryPlugin.getDefault().getString(DESCRIPTION));
    setDescription(BadiLibraryPlugin.getDefault().getString(WIZARD_DESC));

    controller = new ContainerPageController(this);
  }

  public void createControl(Composite parent) {
    initializeDialogUnits(parent);

    Table tableLibArchives = new Table(parent, SWT.MULTI | SWT.CHECK);
    tableLibArchives.setHeaderVisible(true);
    tableLibArchives.setLinesVisible(true);
    TableColumn checker = new TableColumn(tableLibArchives, SWT.CHECK);
    checker.setWidth(20);
    checker.setAlignment(SWT.LEFT);

    TableColumn colName = new TableColumn(tableLibArchives, SWT.NONE);
    colName.setWidth(170);
    colName.setAlignment(SWT.LEFT);
    colName.setText(BadiLibraryPlugin.getDefault().getString(
        "wizard.table.column2"));

    TableColumn colVersion = new TableColumn(tableLibArchives, SWT.NONE);
    colVersion.setWidth(50);
    colVersion.setAlignment(SWT.CENTER);
    colVersion.setText(BadiLibraryPlugin.getDefault().getString(
        "wizard.table.column3"));

    TableColumn colProvider = new TableColumn(tableLibArchives, SWT.NONE);
    colProvider.setWidth(190);
    colProvider.setAlignment(SWT.LEFT);
    colProvider.setText(BadiLibraryPlugin.getDefault().getString(
        "wizard.table.column4"));

    setControl(tableLibArchives);

    tableViewer = new CheckboxTableViewer(tableLibArchives);
    tableViewer.setContentProvider(controller);
    tableViewer.setLabelProvider(controller);
    tableViewer.setInput(BadiLibraryPlugin.getLibs());
    tableViewer.addCheckStateListener(controller);

    controller.setCheckedElements();
  }

  public boolean finish() {
    List<String> lst = new LinkedList<String>(controller.getLibs());
    lst.removeAll(BadiLibraryPlugin.getThirdLibs());

    BadiLibraryPlugin.storeLibsInPropFile(curProj, lst);

    try {
      IClasspathContainer container = new Container(containerPath,
          curProj);
      JavaCore.setClasspathContainer(containerPath,
          new IJavaProject[]{curProj},
          new IClasspathContainer[]{container}, null);
    } catch (JavaModelException e) {
    }

    return true;
  }

  public IClasspathEntry getSelection() {
    return entry;
  }

  public void setSelection(IClasspathEntry containerEntry) {
    if (containerEntry == null) {
      entry = JavaCore.newContainerEntry(containerPath);
    } else {
      entry = containerEntry;
      controller.initLibs(curProj);
    }
  }

  public boolean isPageComplete() {
    return controller.getLibs().size() > 0;
  }

  public CheckboxTableViewer getTableViewer() {
    return tableViewer;
  }

  public void initialize(IJavaProject project,
                         IClasspathEntry[] currentEntries) {
    curProj = project;
  }
}
