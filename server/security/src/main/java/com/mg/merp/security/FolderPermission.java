/*
 * FolderPermission.java
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
package com.mg.merp.security;

/**
 * @author Oleg V. Safonov
 * @version $Id: FolderPermission.java,v 1.1 2007/02/24 14:16:36 safonov Exp $
 */
public class FolderPermission {
  private Integer roleId;
  private String roleName;
  private Boolean permission;
  private Integer permissionId;

  public FolderPermission(Integer roleId, String roleName, Boolean permission, Integer permissionId) {
    super();
    this.roleId = roleId;
    this.roleName = roleName;
    this.permission = permission;
    this.permissionId = permissionId;
  }

  /**
   * @return the permission
   */
  public Boolean getPermission() {
    return permission;
  }

  /**
   * @param permission the permission to set
   */
  public void setPermission(Boolean permission) {
    this.permission = permission;
  }

  /**
   * @return the permissionId
   */
  public Integer getPermissionId() {
    return permissionId;
  }

  /**
   * @param permissionId the permissionId to set
   */
  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
  }

  /**
   * @return the roleId
   */
  public Integer getRoleId() {
    return roleId;
  }

  /**
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

}
