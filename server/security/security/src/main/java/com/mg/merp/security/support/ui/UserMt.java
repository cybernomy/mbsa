/*
 * UserMt.java
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
package com.mg.merp.security.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.security.UserServiceLocal;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.LinkUsersGroups;
import com.mg.merp.security.model.SecUser;

import java.util.Set;

/**
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: UserMt.java,v 1.5 2007/02/24 14:23:54 safonov Exp $
 */
public class UserMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected DefaultTableController groups;
  private Integer currentRoleId = null;

  public UserMt() throws Exception {
    setMasterDetail(true);
    groups = new DefaultTableController(new DefaultEJBQLTableModel() {

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
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Groups.class, "Id", null, true));
        result.add(new TableEJBQLFieldDef(Groups.class, "Name", null, true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setRowList(OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(LinkUsersGroups.class, "lug")
            .createAlias("lug.Group", "g")
            .setProjection(Projections.projectionList(Projections.property("g.Id"), Projections.property("g.Name")))
            .add(Restrictions.eq("lug.User", getEntity()))));
      }

    });

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    super.doOnEdit();
    view.getWidget("Name").setReadOnly(true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    PopupMenu menu = view.getWidget("groups").getPopupMenu();
    menu.getMenuItem("addRole").setEnabled(!readOnly);
    menu.getMenuItem("removeRole").setEnabled(!readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    groups.getModel().load();
  }

  public void onActionAddRole(WidgetEvent event) throws Exception {
    SecGroupSearchHelp searchHelp = new SecGroupSearchHelp();
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).addRole(((SecUser) getEntity()).getId(), ((Groups) event.getItems()[0]).getId());
        groups.getModel().load();
      }

    });
    searchHelp.search();
  }

  public void onActionRemoveRole(WidgetEvent event) {
    if (currentRoleId == null)
      return;

    ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).removeRole(((SecUser) getEntity()).getId(), currentRoleId);
    groups.getModel().load();
  }

}