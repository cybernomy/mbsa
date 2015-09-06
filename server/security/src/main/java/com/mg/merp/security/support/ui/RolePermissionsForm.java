/*
 * RolePermissionsForm.java
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
package com.mg.merp.security.support.ui;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.ShuttleController;
import com.mg.framework.support.ui.widget.ShuttleListener;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.core.model.SysMethod;
import com.mg.merp.core.model.SysModule;
import com.mg.merp.security.MethodAccessServiceLocal;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.MethodAccess;
import com.mg.merp.security.support.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Форма настройки прав на бизнес-компоненты
 *
 * @author Oleg V. Safonov
 * @version $Id: RolePermissionsForm.java,v 1.4 2009/03/17 09:22:32 safonov Exp $
 */
public class RolePermissionsForm extends AbstractForm {
  protected DefaultTableController subsystems;
  protected DefaultTableController beans;
  protected MethodAccessServiceLocal methodAccessService = (MethodAccessServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MethodAccessServiceLocal.SERVICE_NAME);
  Map<String, MethodItem> methodPerms = new HashMap<String, MethodItem>();
  private ShuttleController methods;
  private Integer subSystemId = null;
  private String subSystemDescription = null;
  private Groups role = null;

  public RolePermissionsForm() {
    subsystems = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0) {
          subSystemId = null;
          subSystemDescription = null;
        } else {
          subSystemId = (Integer) getRowList().get(rows[0])[0];
          subSystemDescription = (String) getRowList().get(rows[0])[1];
        }
        beans.getModel().load();
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(SysModule.class, "Id", null, true));
        result.add(new TableEJBQLFieldDef(SysModule.class, "Description", null, true));
        result.add(new TableEJBQLFieldDef(SysModule.class, "Name", null, true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setRowList(OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SysModule.class)
            .setProjection(Projections.projectionList(Projections.property("Id"), Projections.property("Description"), Projections.property("Name")))
            .addOrder(Order.asc("Description"))));
      }

    });

    beans = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length != 0)
          fillMethods((Integer) getRowList().get(rows[0])[0], methods);
        else
          clearMethods(methods);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(SysClass.class, "Id", null, true));
        result.add(new TableEJBQLFieldDef(SysClass.class, "Description", null, true));
        result.add(new TableEJBQLFieldDef(SysClass.class, "BeanName", null, true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setRowList(OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SysClass.class)
            .add(Restrictions.eq("SysModule.Id", subSystemId))
            .setProjection(Projections.projectionList(Projections.property("Id"), Projections.property("Description"), Projections.property("BeanName")))
            .addOrder(Order.asc("Description"))));
      }

    });

    methods = new ShuttleController();
    methods.addShuttleListener(new ShuttleListener() {
      public void shuttleContentsMoved(ShuttleChangeEvent event) {
        grantPermission(event.getContents());
      }

      public void shuttleContentsRemoved(ShuttleChangeEvent event) {
        revokePermission(event.getContents());
      }
    });
  }

  private void grantPermission(Object[] methods) {
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (Object method : methods) {
      MethodItem item = methodPerms.get(method);
      if (item != null) {
        if (item.permId != null) {
          MethodAccess ma = pm.find(MethodAccess.class, item.permId);
          ma.setPermission(true);
          pm.merge(ma);
        } else {
          MethodAccess ma = new MethodAccess();
          ma.setGroup(role);
          ma.setMethod(pm.find(SysMethod.class, item.methodId));
          ma.setPermission(true);
          pm.persist(ma);

          item.permId = ma.getId();
          item.perm = true;
        }
      }
    }
  }

  private void revokePermission(Object[] methods) {
    PersistentManager pm = ServerUtils.getPersistentManager();
    for (Object method : methods) {
      MethodItem item = methodPerms.get(method);
      MethodAccess ma = pm.find(MethodAccess.class, item.permId);
      ma.setPermission(false);
      pm.merge(ma);
    }
  }

  private void fillMethods(Integer beanId, ShuttleController shuttle) {
    List<MethodItem> perms = JdbcTemplate.getInstance().query("select m.id as method_id, m.corba_name, m.description, a.id, a.permission " +
        "from sys_method m left join sec_method_access a on (m.id = a.method_id) and (a.group_id = ?) where (m.class_id = ?)"
        , new Object[]{role.getId(), beanId}, new RowMapper<MethodItem>() {

      public MethodItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MethodItem(rs.getInt(1), rs.getString(2), rs.getString(3), JdbcUtils.getIntegerValue(rs, 4), rs.getBoolean(5));
      }

    });

    List<String> leadingList = new ArrayList<String>();
    List<String> trailingList = new ArrayList<String>();
    methodPerms.clear();
    for (MethodItem item : perms) {
      if (item.perm == null || !item.perm)
        leadingList.add(item.toString());
      else
        trailingList.add(item.toString());

      methodPerms.put(item.toString(), item);
    }

    shuttle.getModel().setLeadingList(leadingList.toArray(new String[leadingList.size()]));
    shuttle.getModel().setTrailingList(trailingList.toArray(new String[trailingList.size()]));
  }

  private void clearMethods(ShuttleController shuttle) {
    shuttle.getModel().setLeadingList(new String[0]);
    shuttle.getModel().setTrailingList(new String[0]);
  }

  /**
   * запуск формы
   *
   * @param role роль
   */
  public void execute(Groups role) {
    this.role = role;
    view.setTitle(Messages.getInstance().getMessage(Messages.ROLE_PERMISSION_TITLE, new Object[]{role.getName()}));
    subsystems.getModel().load();
    run(UIUtils.isModalMode());
  }

  /**
   * Обработчик КМ "Права для всех компонентов модуля"
   *
   * @param event - событие
   */
  public void onActionPermissionForSubsystemBusinessObjects(WidgetEvent event) {
    final PermissionForSubsystemBusinessObjectsDlg setupPermissionDlg = (PermissionForSubsystemBusinessObjectsDlg) ApplicationDictionaryLocator.locate().getWindow(PermissionForSubsystemBusinessObjectsDlg.FORM_NAME);
    setupPermissionDlg.addCancelActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        // do nothing
      }
    });

    setupPermissionDlg.addOkActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        methodAccessService.setPermissionForSubsystemBusinessObjects(subSystemId, role, setupPermissionDlg.isGrantPermission(), setupPermissionDlg.getSelectedMethodsList());
      }
    });
    setupPermissionDlg.execute(subSystemDescription);
  }

  /**
   * Обработчик события "Закрыть форму"
   */
  public void onActionOk(WidgetEvent event) {
    close();
  }

  class MethodItem {
    private Integer methodId;
    private String name;
    private String desc;
    private Integer permId;
    private Boolean perm;

    private MethodItem(Integer methodId, String name, String desc, Integer permId, Boolean perm) {
      super();
      this.methodId = methodId;
      this.name = name;
      this.desc = desc;
      this.permId = permId;
      this.perm = perm;
    }

    @Override
    public String toString() {
      if (StringUtils.stringNullOrEmpty(desc))
        return name;
      else
        return String.format(ServerUtils.getUserLocale(), "%s (%s)", desc, name);
    }

  }

}
