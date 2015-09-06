/* RptViewController.java
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

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.view.StandartBrowserViewController;
import com.mg.merp.wb.report.deployer.DeployerPlugin;
import com.mg.merp.wb.report.deployer.support.utils.RptTool;
import com.mg.merp.wb.report.deployer.ui.RptView;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер панели RptView
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: RptViewController.java,v 1.9 2007/05/07 13:02:51 poroxnenko Exp $
 */
public class RptViewController extends StandartBrowserViewController<RptView, RptMainTransfer> {

  public static final String FILTER_ITEMS_KEY = "ccmbFilter";

  private static final String GET_REPORTS_FAIL = "server.exceptions.gettemplates.failed";

  private Map<String, RptMainTransfer> reports;

  public RptViewController(RptView view) {
    super(view);
  }

  @Override
  public void abstractinputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public void doOnAddMenuAction() {
    // TODO Auto-generated method stub

  }

  @Override
  public void doOnDelMenuAction(List<RptMainTransfer> objects) {
    // TODO Auto-generated method stub

  }

  @Override
  public void doOnEditMenuAction(RptMainTransfer data) {
    // TODO Auto-generated method stub

  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    String value = null;
    if (element != null) {
      switch (columnIndex) {
        case 0: {
          value = (String) ((RptMainTransfer) element).code; //$NON-NLS-1$
          break;
        }
        case 1: {
          String s = (String) ((RptMainTransfer) element).name;//$NON-NLS-1$
          if (s != null)
            value = s;
          break;
        }
        case 2: {
          String s = (String) ((RptMainTransfer) element).comment;//$NON-NLS-1$
          if (s != null)
            value = s;
          break;
        }
      }
    }
    return value;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return (RptMainTransfer[]) inputElement;
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String[] loadFilter() {
    return CoreUtils.loadStringArray(DeployerPlugin.getDefault(), FILTER_ITEMS_KEY);
  }

  @Override
  public void refreshView(String query) {
    try {
      RptMainTransfer[] r = DeployerPlugin.getDefault().synchronize(query);
      reports = new HashMap<String, RptMainTransfer>(r.length);
      for (RptMainTransfer tr : r)
        reports.put(tr.code.trim(), tr);
      view.getTableViewer().setInput(r);
      isSynchronized = true;
    } catch (Exception e) {
      isSynchronized = false;
      Dialogs.openError(DeployerPlugin.getDefault().getString(GET_REPORTS_FAIL), UiPlugin.getDefault()
          .getString(UiPlugin.CHECK_SERVER), DeployerPlugin.ID, e);
    }
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void storeFilter(String[] array) {
    CoreUtils.storeStringArray(DeployerPlugin.getDefault(), FILTER_ITEMS_KEY, array);
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  /**
   * Поместить шаблон в проект
   */
  public void putTemplateToProject() {
    int index = view.getTblItems().getSelectionIndex();
    if (index != -1) {
      RptMainTransfer report = getCurrentSelectedItem();
      if (report.template != null && report.template.length > 0) {
        IResource pr = RptTool.selectResourceForTemplate();
        if (pr != null) {
          File f = new File(pr.getLocation() + "/" + report.code + RptTool.REPORT_DESIGN_EXT);

          if (!f.exists()
              || Dialogs.showMessage(DeployerPlugin.getDefault().getFormattedString("tmpl.exist.question",
              report.code,
              pr.getName()), SWT.YES | SWT.NO
              | SWT.ICON_QUESTION) == SWT.YES) {
            try {
              BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
              bos.write(report.template);
              bos.close();
              pr.refreshLocal(IProject.DEPTH_ONE, null);
            } catch (Exception e) {
              Dialogs.openError(null,
                  DeployerPlugin.getDefault().getString("tmpl.save.error"),
                  DeployerPlugin.ID,
                  e);
            }
          } else
            Dialogs.showMessage(DeployerPlugin.getDefault().getFormattedString("tmpl.isnull", report.code), SWT.OK
                | SWT.ICON_ERROR);
        }
      }
    }
  }

  public boolean isContainCode(String code) {
    return reports.containsKey(code);
  }

  public Map<String, RptMainTransfer> getReports() {
    return reports;
  }
}
