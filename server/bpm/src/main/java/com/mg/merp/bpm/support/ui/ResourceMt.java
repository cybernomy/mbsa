/*
 * ResourceMt.java
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
package com.mg.merp.bpm.support.ui;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.bpm.ResourceServiceLocal;
import com.mg.merp.bpm.model.Resource;
import com.mg.merp.bpm.model.ResourceGroupLink;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.support.ui.SecGroupSearchHelp;

import java.util.Set;

/**
 * Контроллер формы поддержки ресурсов
 *
 * @author Oleg V. Safonov
 * @version $Id: ResourceMt.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ResourceMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected DefaultTableController linksTable;
  private Integer currentRoleId;

  public ResourceMt() {
    setMasterDetail(true);
    linksTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(ResourceGroupLink.class, "Group.Id", "l.Group.Id", true));
        result.add(new TableEJBQLFieldDef(ResourceGroupLink.class, "Group.Name", "l.Group.Name", true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          currentRoleId = null;
        else
          currentRoleId = (Integer) getRowList().get(rows[0])[0];
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery("select l.Group.Id, l.Group.Name from ResourceGroupLink l where l.Resource = :resource", new String[]{"resource"}, new Object[]{getEntity()});
      }

    });

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    UIUtils.setReadOnlyProperty(view.getWidget("linksTable").getPopupMenu().getMenuItem("addMember"), readOnly);
    UIUtils.setReadOnlyProperty(view.getWidget("linksTable").getPopupMenu().getMenuItem("removeMember"), readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    linksTable.getModel().load();
  }

  public void onActionAddMember(WidgetEvent event) throws Exception {
    SecGroupSearchHelp searchHelp = new SecGroupSearchHelp();
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        ((ResourceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/bpm/Resource")).addMember(((Resource) getEntity()).getId(), ((Groups) event.getItems()[0]).getId());
        linksTable.getModel().load();
      }

    });
    searchHelp.search();
  }

  public void onActionRemoveMember(WidgetEvent event) {
    ((ResourceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/bpm/Resource")).removeMember(((Resource) getEntity()).getId(), currentRoleId);
    linksTable.getModel().load();
  }

}
