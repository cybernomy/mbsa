/* EntityMapperViewController.java
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
package com.mg.merp.wb.entitymapper.ui.support;

import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.view.StandartBrowserViewController;
import com.mg.merp.wb.entitymapper.Activator;
import com.mg.merp.wb.entitymapper.ui.EntityMapperView;
import com.mg.merp.wb.entitymapper.ui.editor.EntityMapperEditorForm;
import com.mg.merp.wb.entitymapper.ui.editor.EntityMapperEditorInput;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import java.util.List;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperViewController.java,v 1.2 2007/11/07 13:44:54 safonov Exp $
 */
public class EntityMapperViewController extends
    StandartBrowserViewController<EntityMapperView, EntityTransformerMapping> {

  private static final String FILTER_ITEMS_KEY = "ccmbFilter";

  private static final String GET_ENTITYMAPPER_FAIL = "server.exceptions.getentitymapper.failed";

  private static final String NEW_ENTITYMAPPER = "entitymapper.form.edit.page.new";

  private static final String ENTITYMAPPER_DELETE_ERROR = "entitymapper.delete.error";

  /**
   * @param view
   */
  public EntityMapperViewController(EntityMapperView view) {
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
          value = ((EntityTransformerMapping) element).getMapId().trim(); //$NON-NLS-1$
          break;
        }
        case 1: {
          value = ((EntityTransformerMapping) element).getClassA().trim();
          break;
        }
        case 2: {
          value = ((EntityTransformerMapping) element).getClassB().trim();
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
    return (EntityTransformerMapping[]) inputElement;
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
      EntityTransformerMapping[] etm = Activator.getDefault().synchronize(query);
      view.getTableViewer().setInput(etm);
      isSynchronized = true;
    } catch (Exception e) {
      isSynchronized = false;
      Dialogs.openError(Activator.getDefault().getString(GET_ENTITYMAPPER_FAIL),
          UiPlugin.getDefault().getString(UiPlugin.CHECK_SERVER),
          Activator.ID, e);
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
    return CoreUtils.loadStringArray(Activator.getDefault(),
        FILTER_ITEMS_KEY);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserViewController#storeFilter(java.lang.String[])
   */
  @Override
  public void storeFilter(String[] array) {
    CoreUtils.storeStringArray(Activator.getDefault(), FILTER_ITEMS_KEY,
        array);
  }

  @Override
  public void doOnAddMenuAction() {
    EntityTransformerMapping r = new EntityTransformerMapping();
    r.setMapId(Activator.getDefault().getString(NEW_ENTITYMAPPER));
    Activator.openEditor(new EntityMapperEditorInput(r, true),
        EntityMapperEditorForm.EDITOR_ID);
  }

  @Override
  public void doOnEditMenuAction(EntityTransformerMapping data) {
    Activator.openEditor(new EntityMapperEditorInput(data, false),
        EntityMapperEditorForm.EDITOR_ID);
  }

  @Override
  public void doOnDelMenuAction(List<EntityTransformerMapping> objects) {
    if (objects != null && objects.size() > 0) {
      Integer[] ids = new Integer[objects.size()];
      int i = 0;
      for (EntityTransformerMapping etm : objects) {
        ids[i] = etm.getId();
        i++;
      }
      try {
        Activator.getDefault().deleteBusinessObjectsList(ids);
      } catch (java.rmi.ConnectException e) {
        Dialogs.openError(Activator.getDefault()
            .getString(ENTITYMAPPER_DELETE_ERROR), Activator.getDefault()
            .getString(UiPlugin.CHECK_SERVER), Activator.ID, e);
      } catch (RuntimeException e) {
        Dialogs.openError(Activator.getDefault()
            .getString(ENTITYMAPPER_DELETE_ERROR), e.getLocalizedMessage(), Activator.ID, e);
      } catch (Exception e) {
        Dialogs.openError(UiPlugin.getDefault().getString(
            UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.getDefault()
            .getString(UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.ID, e);
      }
      view.refresh();
    }
  }

}
