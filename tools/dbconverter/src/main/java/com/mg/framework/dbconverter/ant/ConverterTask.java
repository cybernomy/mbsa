/**
 * ConverterTask.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.dbconverter.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Oleg V. Safonov
 * @version $Id: ConverterTask.java,v 1.4 2008/11/14 08:36:37 safonov Exp $
 */
public class ConverterTask extends Task {
  private String databaseName;
  private Properties properties = new Properties();

  /* (non-Javadoc)
   * @see org.apache.tools.ant.Task#init()
   */
  @Override
  public void init() throws BuildException {
    super.init();
    //Logger.setPluginClassName("com.mg.framework.dbconverter.ant.ConsoleLoggerPlugin");
  }

  /* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
  @Override
  public void execute() throws BuildException {
    try {
      Class<?> converterClass = Class.forName("com.mg.framework.service.DatabaseConverter");
      Method getInstanceMethod = converterClass.getMethod("getInstance", String.class);
      Object converter = getInstanceMethod.invoke(null, databaseName);
      Method convertMethod = converter.getClass().getMethod("convert", Properties.class);
      convertMethod.invoke(converter, properties);
    } catch (InvocationTargetException e) {
      e.getTargetException().printStackTrace(System.out);
      throw new BuildException("Database convertation failed", e.getTargetException());
    } catch (Exception e) {
      e.printStackTrace(System.out);
      throw new BuildException("Database convertation failed", e);
    }
  }

  /**
   * @param databaseName the databaseName to set
   */
  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public void setDbmsPath(String dbmsPath) {
    properties.put("database.systempath", dbmsPath);
  }

  public void setDbmsHost(String dbmsHost) {
    properties.put("database.host", dbmsHost);
  }

  public void setDbmsPort(String dbmsPort) {
    properties.put("database.port", dbmsPort);
  }

  public void setJdbcDriverClass(String jdbcDriverClass) {
    properties.put("database.jdbcdriverclass", jdbcDriverClass);
  }

  public void setDbUrl(String dbUrl) {
    properties.put("database.url", dbUrl);
  }

  public void setDbName(String dbName) {
    properties.put("database.name", dbName);
  }

  public void setDbSID(String dbSID) {
    properties.put("database.SID", dbSID);
  }

  public void setScriptPath(String scriptPath) {
    properties.put("database.scriptpath", scriptPath);
  }

  public void setScriptLogDir(String scriptLogDir) {
    properties.put("database.scriptlogdir", scriptLogDir);
  }

  public void setTempDir(String tempDir) {
    properties.put("database.tempdir", tempDir);
  }

  public void setUserName(String userName) {
    properties.put("database.username", userName);
  }

  public void setPassword(String passwd) {
    properties.put("database.password", passwd);
  }

  public void setCharacterSet(String characterSet) {
    properties.put("database.characterset", characterSet);
  }

}
