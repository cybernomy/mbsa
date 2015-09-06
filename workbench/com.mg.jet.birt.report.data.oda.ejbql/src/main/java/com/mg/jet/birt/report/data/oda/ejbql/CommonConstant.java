/**
 * CommonConstant.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.jet.birt.report.data.oda.ejbql;

/**
 * This is the class which hosts the definitions of package-wide constants.
 *
 * @author Oleg V. Safonov
 * @version $Id: CommonConstant.java,v 1.1 2007/10/29 08:33:24 safonov Exp $
 */
final class CommonConstant {
  public static final String DELIMITER_COMMA = ",";
  public static final String DELIMITER_SPACE = " ";
  public static final String DELIMITER_DOUBLEQUOTE = "\"";
  public static final String KEYWORD_SELECT = "SELECT";
  public static final String KEYWORD_FROM = "FROM";
  public static final String KEYWORD_AS = "AS";
  public static final String KEYWORD_ASTERISK = "*";
  public static final String DRIVER_NAME = "ODA HIBERNATE DRIVER";
  public static final String HIBERNATE_CLASSES = "hibfiles";
  public static final String HIBERNATE_LIBS = "lib";
  public static final String MGFRAMEWORK = "mgframework.jar";
  public static final String DATAWAREHOUSE = "mdatawarehouse.jar";
  public static final String DATAWAREHOUSE_CFG_FILE = "mbsa.config.xml";
  public static final String DEFAULT_JNDI_URL = "java:/hibernate/MERPSessionFactory";
  public static final int DRIVER_MAJOR_VERSION = 0;
  public static final int DRIVER_MINOR_VERSION = 1;
  public static final int ODA_MAJOR_VERSION = 1;
  public static final int ODA_MINOR_VERSION = 0;
  public static final int MaxConnections = 0;
  public static final int MaxStatements = 0;


  /**
   * Private contructure which ensure the non-instantiatial of the class
   *
   */
  private CommonConstant() {
  }
}