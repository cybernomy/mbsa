/* ContainerPageController.java
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
package com.mg.merp.wb.badi.library.util;

import com.mg.merp.wb.badi.library.BadiLibraryPlugin;
import com.mg.merp.wb.badi.library.ContainerPage;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Контроллер для {@link ContainerPageController}
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: ContainerPageController.java,v 1.2 2006/11/24 09:06:40 poroxnenko Exp $
 */
public class ContainerPageController implements IStructuredContentProvider,
    ITableLabelProvider, ICheckStateListener {

  private ContainerPage page;

  private Set<String> libs;

  private Vector<String> libVector;

  public ContainerPageController(ContainerPage page) {
    this.page = page;
    libs = new LinkedHashSet<String>();
  }

  public void dispose() {
    // TODO Auto-generated method stub

  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub
  }

  public Image getColumnImage(Object element, int columnIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub
  }

  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
   *      int)
   */
  public String getColumnText(Object element, int columnIndex) {
    String value = null;
    if (element != null) {
      switch (columnIndex) {
        case 1: {
          String s = ((LibInfo) element).libTitle;
          if (s != null)
            value = s; //$NON-NLS-1$
          break;
        }
        case 2: {
          String s = ((LibInfo) element).libVersion;
          if (s != null)
            value = s; //$NON-NLS-1$
          break;
        }
        case 3: {
          String s = ((LibInfo) element).libVendor;
          if (s != null)
            value = s; //$NON-NLS-1$
          break;
        }

      }
    }

    return value;
  }

  @SuppressWarnings("unchecked")
  public Object[] getElements(Object inputElement) {
    Map<String, LibInfo> input = (Map<String, LibInfo>) inputElement;
    LibInfo[] lis = new LibInfo[input.values().size()];
    libVector = new Vector<String>(input.keySet());
    return input.values().toArray(lis);
  }

  public void checkStateChanged(CheckStateChangedEvent event) {
    if (event.getChecked())
      addArchToMainLib((LibInfo) event.getElement());
    else
      removeArchFromLib((LibInfo) event.getElement());
  }

  private void removeArchFromLib(LibInfo info) {
    libs.remove(info.libName);
    page.setPageComplete(page.isPageComplete());
  }

  private void addArchToMainLib(LibInfo info) {
    addDependencies(info.libName);
    page.setPageComplete(page.isPageComplete());
  }

  private void addDependencies(String libName) {
    libs.add(libName);
    page.getTableViewer().getTable().getItem(libVector.indexOf(libName))
        .setChecked(true);
    for (String nm : BadiLibraryPlugin.getLibs().get(libName).libDepends) {
      if (!libs.contains(nm))
        addDependencies(nm);
    }

  }

  public void setCheckedElements() {
    for (int i = 0; i < page.getTableViewer().getTable().getItemCount(); i++) {
      TableItem ti = page.getTableViewer().getTable().getItem(i);
      ti.setChecked(libs.contains(((LibInfo) ti.getData()).libName));
    }
  }

  public Set<String> getLibs() {
    return libs;
  }

  public void setLibs(Set<String> libs) {
    this.libs = libs;
  }

  /**
   * Заполняет список имёнами архивов, которые будут помещены в библиотеку, на основе конфигурации
   * проекта
   *
   * @param project текущий Java-проект
   */
  public void initLibs(IJavaProject project) {
    libs = BadiLibraryPlugin.initLibsFromPropFile(project);
  }

}
