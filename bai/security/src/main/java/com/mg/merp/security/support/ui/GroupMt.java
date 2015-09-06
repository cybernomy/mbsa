/*
 * GroupMt.java
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
package com.mg.merp.security.support.ui;

import com.mg.framework.api.AttributeMap;
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
import com.mg.framework.support.LocalDataTransferObject;
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
 * @version $Id: GroupMt.java,v 1.5 2007/02/24 14:23:54 safonov Exp $
 */
public class GroupMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected DefaultTableController users;
  //	private MaintenanceTableController userGroup;
//	private DataBusinessObjectService userGroupService;
  protected AttributeMap userGroupProperties = new LocalDataTransferObject();
  private Integer currentUserId = null;

  public GroupMt() throws Exception {
    setMasterDetail(true);
/*		userGroupService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/security/User");*/
    users = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(SecUser.class, "Id", null, true));
        result.add(new TableEJBQLFieldDef(SecUser.class, "Name", null, true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          currentUserId = null;
        else
          currentUserId = (Integer) getRowList().get(rows[0])[0];
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setRowList(OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(LinkUsersGroups.class, "lug")
            .createAlias("lug.User", "u")
            .setProjection(Projections.projectionList(Projections.property("u.Id"), Projections.property("u.Name")))
            .add(Restrictions.eq("lug.Group", getEntity()))));
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
    PopupMenu menu = view.getWidget("users").getPopupMenu();
    menu.getMenuItem("addMember").setEnabled(!readOnly);
    menu.getMenuItem("removeMember").setEnabled(!readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    users.getModel().load();
  }

  public void onActionAddMember(WidgetEvent event) throws Exception {
    SecUserSearchHelp searchHelp = new SecUserSearchHelp();
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).addRole(((SecUser) event.getItems()[0]).getId(), ((Groups) getEntity()).getId());
        users.getModel().load();
      }

    });
    searchHelp.search();
  }

  public void onActionRemoveMember(WidgetEvent event) {
    if (currentUserId == null)
      return;

    ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).removeRole(currentUserId, ((Groups) getEntity()).getId());
    users.getModel().load();
  }

}
