/* BAiViewController.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.bai.ui.support;

import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.wb.bai.BAiPlugin;
import com.mg.merp.wb.bai.ui.BAiView;
import com.mg.merp.wb.bai.ui.editor.BAiEditorForm;
import com.mg.merp.wb.bai.ui.editor.BAiEditorInput;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.view.StandartBrowserViewController;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import java.util.List;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BAiViewController.java,v 1.6 2007/11/07 13:44:55 safonov Exp $
 */
public class BAiViewController extends
    StandartBrowserViewController<BAiView, Repository> {

  private static final String FILTER_ITEMS_KEY = "ccmbFilter";

  private static final String GET_BAIS_FAIL = "server.exceptions.getalgs.failed";

  private static final String NEW_BAI = "bai.form.edit.page.new";

  private static final String BAI_DELETE_ERROR = "bai.delete.error";

  /**
   * @param view
   */
  public BAiViewController(BAiView view) {
    super(view);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#abstractinputChanged(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  public void abstractinputChanged(Viewer viewer, Object oldInput,
                                   Object newInput) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#dispose()
   */
  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#getColumnImage(java.lang.Object,
   *      int)
   */
  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#getColumnText(java.lang.Object,
   *      int)
   */
  @Override
  public String getColumnText(Object element, int columnIndex) {
    String value = null;
    if (element != null) {
      switch (columnIndex) {
        case 0: {
          value = (String) ((Repository) element).getCode().trim(); //$NON-NLS-1$
          break;
        }
        case 1: {
          String s = (String) ((Repository) element).getName();
          if (s != null)
            value = s.trim(); //$NON-NLS-1$
          break;
        }
        case 2: {
          String s = (String) ((Repository) element)
              .getImplementationName();
          if (s != null)
            value = s.trim(); //$NON-NLS-1$
          break;
        }
      }
    }
    return value;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    return (Repository[]) inputElement;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#isLabelProperty(java.lang.Object,
   *      java.lang.String)
   */
  @Override
  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#refreshView(java.lang.String)
   */
  @Override
  public void refreshView(String query) {
    try {
      Repository[] rpz = BAiPlugin.getDefault().synchronize(query);
      view.getTableViewer().setInput(rpz);
      isSynchronized = true;
    } catch (Exception e) {
      isSynchronized = false;
      Dialogs.openError(BAiPlugin.getDefault().getString(GET_BAIS_FAIL),
          UiPlugin.getDefault().getString(UiPlugin.CHECK_SERVER),
          BAiPlugin.ID, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#loadFilter()
   */
  @Override
  public String[] loadFilter() {
    return CoreUtils.loadStringArray(BAiPlugin.getDefault(),
        FILTER_ITEMS_KEY);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#storeFilter(java.lang.String[])
   */
  @Override
  public void storeFilter(String[] array) {
    CoreUtils.storeStringArray(BAiPlugin.getDefault(), FILTER_ITEMS_KEY,
        array);
  }

  @Override
  public void doOnAddMenuAction() {
    Repository r = new Repository();
    r.setCode(BAiPlugin.getDefault().getString(NEW_BAI));
    BAiPlugin.openEditor(new BAiEditorInput(r, true),
        BAiEditorForm.EDITOR_ID);
  }

  @Override
  public void doOnDelMenuAction(List<Repository> objects) {
    if (objects != null && objects.size() > 0) {
      Integer[] ids = new Integer[objects.size()];
      int i = 0;
      for (Repository rpz : objects) {
        ids[i] = rpz.getId();
        i++;
      }
      try {
        BAiPlugin.getDefault().deleteBusinessObjectsList(ids);
      } catch (java.rmi.ConnectException e) {
        Dialogs.openError(BAiPlugin.getDefault()
            .getString(BAI_DELETE_ERROR), BAiPlugin.getDefault()
            .getString(UiPlugin.CHECK_SERVER), BAiPlugin.ID, e);
      } catch (RuntimeException e) {
        Dialogs.openError(BAiPlugin.getDefault()
            .getString(BAI_DELETE_ERROR), e.getLocalizedMessage(), BAiPlugin.ID, e);
      } catch (Exception e) {
        Dialogs.openError(UiPlugin.getDefault().getString(
            UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.getDefault()
            .getString(UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.ID, e);
      }
      view.refresh();
    }
  }

  @Override
  public void doOnEditMenuAction(Repository data) {
    BAiPlugin.openEditor(new BAiEditorInput(data, false),
        BAiEditorForm.EDITOR_ID);
  }

}
