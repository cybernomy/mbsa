/*
 * Messages.java
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
package com.mg.merp.core.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.8 2008/07/15 05:59:29 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String LOGIN_DIALOG_TITLE = "LoginDialog.Title"; //$NON-NLS-1$
  public static final String LOGIN_DIALOG_USERNAME = "LoginDialog.Username"; //$NON-NLS-1$
  public static final String LOGIN_DIALOG_PASSWORD = "LoginDialog.Password"; //$NON-NLS-1$
  public static final String LOGIN_DIALOG_LANGUAGE = "LoginDialog.Language"; //$NON-NLS-1$
  public static final String LOGIN_DIALOG_PRINTER = "LoginDialog.Printer"; //$NON-NLS-1$
  public static final String GLOBAL_MENU_FILE = "GlobalMenu.File"; //$NON-NLS-1$
  public static final String GLOBAL_MENU_EXIT = "GlobalMenu.Exit"; //$NON-NLS-1$
  public static final String GLOBAL_MENU_HELP = "GlobalMenu.Help"; //$NON-NLS-1$
  public static final String GLOBAL_MENU_ROOT_HELP = "GlobalMenu.RootHelp"; //$NON-NLS-1$
  public static final String GLOBAL_MENU_ABOUT = "GlobalMenu.About"; //$NON-NLS-1$
  public static final String CONTROL_CENTER_TITLE = "ControlCenter.Title"; //$NON-NLS-1$
  public static final String TASK_BAR_TITLE = "TaskBar.Title"; //$NON-NLS-1$
  public static final String ABOUT_PRODUCT_TITLE = "About.Product.Title"; //$NON-NLS-1$
  public static final String ENTITYMAPPERS_LOAD_ERROR = "EntityMappersLoadError"; //$NON-NLS-1$
  public static final String ENTITYMAPPER_EDIT_ERROR = "EntityMapperEditError";
  public static final String ENTITYMAPPER_ADD_ERROR = "EntityMapperAddError";
  public static final String ENTITYMAPPER_VERSION_ERROR = "EntityMapperVersionError";
  public static final String ENTITYMAPPERS_DELETE_ERROR = "EntityMappersDeleteError";
  public static final String CURRENT_USER_NAME = "CurrentUserName"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.core.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
