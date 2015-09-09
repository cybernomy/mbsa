/*
 * RemoteConsole.java
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
package com.mg.framework.console;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import org.apache.log4j.Category;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.naming.InitialContext;

/**
 * Command line application server console
 *
 * @author Oleg V. Safonov
 * @version $Id: RemoteConsole.java,v 1.5 2007/05/14 15:41:32 safonov Exp $
 */
public class RemoteConsole {
  public static final String PROGRAM_NAME = System.getProperty("program.name", "appserverconsole"); //$NON-NLS-1$
  private static final String MERP_SERVICE = "jboss:service=MERP";
  private static final String APPLICATION_SERVER_SERVICE = "merp:service=ApplicationServerService";
  private Category log;
  private InitialContext initialContext;
  private MBeanServerConnection server;

  private static void displayUsage() {
    System.out.println("A JMX client to control a remote MBSA application server."); //$NON-NLS-1$
    System.out.println("Copyright (c) 1998 - 2007 BusinessTechnology, Ltd."); //$NON-NLS-1$
    System.out.println();
    System.out.println("usage: " + PROGRAM_NAME + " [options] <operation>"); //$NON-NLS-1$
    System.out.println();
    System.out.println("options:"); //$NON-NLS-1$
    System.out.println("    -h, --help                  Show this help message"); //$NON-NLS-1$
    System.out.println("operations:"); //$NON-NLS-1$
    System.out.println("    -C, --convert               Convert database"); //$NON-NLS-1$
    System.out.println("    -d, --localdeploy <file>    Deploy file (full file name on server machine)"); //$NON-NLS-1$
    System.out.println("    -D, --remotedeploy <file>   Deploy file (full file name on local machine)"); //$NON-NLS-1$
    System.out.println("    -P, --stoppool[pool name]   Stop connection pool"); //$NON-NLS-1$
    System.out.println("    -p, --startpool[pool name]  Start connection pool"); //$NON-NLS-1$
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      displayUsage();
      System.exit(0);
    }

    String sopts = "-:hCD:d:P::p::";
    LongOpt[] lopts =
        {
            new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
            new LongOpt("convert", LongOpt.NO_ARGUMENT, null, 'C'),
            new LongOpt("localdeploy", LongOpt.REQUIRED_ARGUMENT, null, 'd'),
            new LongOpt("remotedeploy", LongOpt.REQUIRED_ARGUMENT, null, 'D'),
            new LongOpt("stoppool", LongOpt.OPTIONAL_ARGUMENT, null, 'P'),
            new LongOpt("startpool", LongOpt.OPTIONAL_ARGUMENT, null, 'p')
        };

    Getopt getopt = new Getopt(PROGRAM_NAME, args, sopts, lopts);
    int code;

    RemoteConsole console = new RemoteConsole();

    try {
      console.init();

      while ((code = getopt.getopt()) != -1) {
        switch (code) {
          case ':':
          case '?':
            // for now both of these should exit with error status
            System.exit(1);
            break;
          case 1:
            // this will catch non-option arguments
            // (which we don't currently care about)
            System.err.println(PROGRAM_NAME + ": unused non-option argument: " + //$NON-NLS-1$
                getopt.getOptarg());
            break;
          case 'h':
            displayUsage();
            System.exit(0);
            break;
          case 'C':
            console.convertDatabase();
            break;
          case 'd':
            console.deployLocalFile(getopt.getOptarg());
            break;
          case 'D':
            console.deployRemoteFile(getopt.getOptarg());
            break;
          case 'P':
            console.stopDatabasePool(getopt.getOptarg());
            break;
          case 'p':
            console.startDatabasePool(getopt.getOptarg());
            break;
        }
      }
    } catch (Exception e) {
      console.log.error(e);
    }
  }

  private void init() throws Exception {
    log = Category.getInstance(getClass());
    Properties p = new Properties();
    p.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
    initialContext = new InitialContext(p);
  }

  private MBeanServerConnection getServer() throws Exception {
    if (server == null) {
      server = (MBeanServerConnection) initialContext.lookup("jmx/invoker/RMIAdaptor"); //$NON-NLS-1$
    }
    return server;
  }

  private Object invoke(MBeanServerConnection server, ObjectName name, String method, Object[] args, String[] sig) throws Exception {
    try {
      return server.invoke(name, method, args, sig);
    } catch (javax.management.MBeanException e) {
      log.error("MBeanException", e.getTargetException()); //$NON-NLS-1$
      throw e.getTargetException();
    } catch (javax.management.ReflectionException e) {
      log.error("ReflectionException", e.getTargetException()); //$NON-NLS-1$
      throw e.getTargetException();
    } catch (javax.management.RuntimeOperationsException e) {
      log.error("RuntimeOperationsException", e.getTargetException()); //$NON-NLS-1$
      throw e.getTargetException();
    } catch (javax.management.RuntimeMBeanException e) {
      log.error("RuntimeMBeanException", e.getTargetException()); //$NON-NLS-1$
      throw e.getTargetException();
    } catch (javax.management.RuntimeErrorException e) {
      log.error("RuntimeErrorException", e.getTargetError()); //$NON-NLS-1$
      throw e.getTargetError();
    }
  }

  private void convertDatabase() throws Exception {
    log.info("Database convertion in progress");
    ObjectName serviceName = new ObjectName(APPLICATION_SERVER_SERVICE);
    invoke(getServer(), serviceName, "convertDatabase", new Object[0], new String[0]); //$NON-NLS-1$
    log.info("Database convertion completed successfully");
  }

  private void startDatabasePool(String connectionPoolName) throws Exception {
    log.info("Start connection pool");
    ObjectName serviceName = new ObjectName(APPLICATION_SERVER_SERVICE);
    invoke(getServer(), serviceName, "startDatabasePool", new Object[]{connectionPoolName}, new String[]{String.class.getName()}); //$NON-NLS-1$
    log.info("Connection pool started successfully");
  }

  private void stopDatabasePool(String connectionPoolName) throws Exception {
    log.info("Stop connection pool");
    ObjectName serviceName = new ObjectName(APPLICATION_SERVER_SERVICE);
    invoke(getServer(), serviceName, "stopDatabasePool", new Object[]{connectionPoolName}, new String[]{String.class.getName()}); //$NON-NLS-1$
    log.info("Connection pool stoped successfully");
  }

  private void deployLocalFile(String name) throws Exception {
    log.info("Deploing local file: ".concat(name));
    ObjectName serviceName = new ObjectName(MERP_SERVICE);
    invoke(getServer(), serviceName, "delpoyLocalFile", new Object[]{name}, new String[]{String.class.getName()}); //$NON-NLS-1$
    log.info("Deployment completed successfully");
  }

  private void deployRemoteFile(String name) throws Exception {
    log.info("Deploing local file: ".concat(name));

    FileInputStream fis = new FileInputStream(name);
    File f = new File(name);
    //f.
    //ByteArrayInputStream bis = new ByteArrayInputStream();
    //bis.
    //ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //bos.
    byte[] bytes = new byte[(int) f.length()];
    //fis.
    fis.read(bytes);
    fis.close();

    ObjectName serviceName = new ObjectName(MERP_SERVICE);
    invoke(getServer(), serviceName, "delpoyRemoteFile", new Object[]{f.getName(), bytes}, new String[]{String.class.getName(), byte[].class.getName()}); //$NON-NLS-1$
    log.info("Deployment completed successfully");
  }
}
