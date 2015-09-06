/* RptFormController.java
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
package com.mg.merp.wb.report.deployer.support;

import com.mg.merp.wb.core.support.CoreUtils;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

/**
 * Контроллер формы редактирования отчёта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: RptEditorController.java,v 1.3 2006/12/21 09:15:31 poroxnenko Exp $
 */
public class RptEditorController implements IStructuredContentProvider, ITableLabelProvider {

  public Object[] getElements(Object inputElement) {
    return CoreUtils.stringToStringArray(((String) inputElement), ";");
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

  public String getColumnText(Object element, int columnIndex) {
    String value = null;
    if (element != null) {
      String elt = (String) element;
      int pos = elt.indexOf("->");
      switch (columnIndex) {
        case 0: {
          value = elt.substring(0, pos); //$NON-NLS-1$
          break;
        }
        case 1: {
          value = elt.substring(pos + 2, elt.length());
          break;
        }
      }
    }
    return value;
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
}
