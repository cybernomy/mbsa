/*
 * Logger.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api;

import java.io.Serializable;

import com.mg.framework.service.NullLoggerPlugin;
import com.mg.framework.utils.ServerUtils;

/**
 * Logger
 *
 * @author Oleg V. Safonov
 * @version $Id: Logger.java,v 1.3 2008/03/05 14:51:04 safonov Exp $
 */
public class Logger
      implements Serializable
{
   /** The LoggerPlugin implementation class to use */
   protected static Class<?> pluginClass = null;
   /** The class name of the LoggerPlugin implementation class to use */
   protected static String pluginClassName = null;

   static
   {
      init();
   }

   /** The logger name. */
   private final String name;

   protected transient LoggerPlugin loggerDelegate = null;

   /** The LoggerPlugin implementation class name in use
    * @return LoggerPlugin implementation class name
    */
   public static String getPluginClassName()
   {
      return Logger.pluginClassName;
   }
   /** Set the LoggerPlugin implementation class name in use
    * @param pluginClassName the LoggerPlugin implementation class name
    */
   public static void setPluginClassName(String pluginClassName)
   {
      Logger.pluginClassName = pluginClassName;
   }

   /**
    * Creates new Logger the given logger name.
    *
    * @param name the logger name.
    */
   protected Logger(final String name)
   {
      this.name = name;
      this.loggerDelegate = getDelegatePlugin(name);
   }

   /**
    * Return the name of this logger.
    *
    * @return The name of this logger.
    */
   public String getName()
   {
      return name;
   }

   public LoggerPlugin getLoggerPlugin()
   {
      return this.loggerDelegate;
   }

   /**
    * Check to see if the TRACE level is enabled for this logger.
    *
    * @return true if a {@link #trace(Object)} method invocation would pass
    *         the msg to the configured appenders, false otherwise.
    */
   public boolean isTraceEnabled()
   {
      return loggerDelegate.isTraceEnabled();
   }

   /**
    * Issue a log msg with a level of TRACE.
    * Invokes log.log(XLevel.TRACE, message);
    */
   public void trace(Object message)
   {
      loggerDelegate.trace(message);
   }

   /**
    * Issue a log msg and throwable with a level of TRACE.
    * Invokes log.log(XLevel.TRACE, message, t);
    */
   public void trace(Object message, Throwable t)
   {
      loggerDelegate.trace(message, t);
   }

   /**
    * Check to see if the DEBUG level is enabled for this logger.
    *
    * @return true if a {@link #debug(Object)} method invocation would pass
    * the msg to the configured appenders, false otherwise.
    */
   public boolean isDebugEnabled()
   {
      return loggerDelegate.isDebugEnabled();
   }

   /**
    * Issue a log msg with a level of DEBUG.
    * Invokes log.log(Level.DEBUG, message);
    */
   public void debug(Object message)
   {
      loggerDelegate.debug(message);
   }

   /**
    * Issue a log msg and throwable with a level of DEBUG.
    * Invokes log.log(Level.DEBUG, message, t);
    */
   public void debug(Object message, Throwable t)
   {
      loggerDelegate.debug(message, t);
   }

   /**
    * Check to see if the INFO level is enabled for this logger.
    *
    * @return true if a {@link #info(Object)} method invocation would pass
    * the msg to the configured appenders, false otherwise.
    */
   public boolean isInfoEnabled()
   {
      return loggerDelegate.isInfoEnabled();
   }

   /**
    * Issue a log msg with a level of INFO.
    * Invokes log.log(Level.INFO, message);
    */
   public void info(Object message)
   {
      loggerDelegate.info(message);
   }

   /**
    * Issue a log msg and throwable with a level of INFO.
    * Invokes log.log(Level.INFO, message, t);
    */
   public void info(Object message, Throwable t)
   {
      loggerDelegate.info(message, t);
   }

   /**
    * Issue a log msg with a level of WARN.
    * Invokes log.log(Level.WARN, message);
    */
   public void warn(Object message)
   {
      loggerDelegate.warn(message);
   }

   /**
    * Issue a log msg and throwable with a level of WARN.
    * Invokes log.log(Level.WARN, message, t);
    */
   public void warn(Object message, Throwable t)
   {
      loggerDelegate.warn(message, t);
   }

   /**
    * Issue a log msg with a level of ERROR.
    * Invokes log.log(Level.ERROR, message);
    */
   public void error(Object message)
   {
      loggerDelegate.error(message);
   }

   /**
    * Issue a log msg and throwable with a level of ERROR.
    * Invokes log.log(Level.ERROR, message, t);
    */
   public void error(Object message, Throwable t)
   {
      loggerDelegate.error(message, t);
   }

   /**
    * Issue a log msg with a level of FATAL.
    * Invokes log.log(Level.FATAL, message);
    */
   public void fatal(Object message)
   {
      loggerDelegate.fatal(message);
   }

   /**
    * Issue a log msg and throwable with a level of FATAL.
    * Invokes log.log(Level.FATAL, message, t);
    */
   public void fatal(Object message, Throwable t)
   {
      loggerDelegate.fatal(message, t);
   }

   /////////////////////////////////////////////////////////////////////////
   //                         Custom Serialization                        //
   /////////////////////////////////////////////////////////////////////////

   private void readObject(java.io.ObjectInputStream stream)
         throws java.io.IOException, ClassNotFoundException
   {
      // restore non-transient fields (aka name)
      stream.defaultReadObject();

      // Restore logging
      if (pluginClass == null)
      {
         init();
      }
      this.loggerDelegate = getDelegatePlugin(name);
   }


   /////////////////////////////////////////////////////////////////////////
   //                            Factory Methods                          //
   /////////////////////////////////////////////////////////////////////////

   /**
    * Create a Logger instance given the logger name.
    *
    * @param name    the logger name
    */
   public static Logger getLogger(String name)
   {
      return new Logger(name);
   }

   /**
    * Create a Logger instance given the logger name with the given suffix.
    *
    * <p>This will include a logger seperator between classname and suffix
    *
    * @param name     The logger name
    * @param suffix   A suffix to append to the classname.
    */
   public static Logger getLogger(String name, String suffix)
   {
      return new Logger(name + "." + suffix); //$NON-NLS-1$
   }

   /**
    * Create a Logger instance given the logger class. This simply
    * calls create(clazz.getName()).
    *
    * @param clazz    the Class whose name will be used as the logger name
    */
   public static Logger getLogger(Class<?> clazz)
   {
      return new Logger(clazz.getName());
   }

   /**
    * Create a Logger instance given the logger class with the given suffix.
    *
    * <p>This will include a logger seperator between classname and suffix
    *
    * @param clazz    The Class whose name will be used as the logger name.
    * @param suffix   A suffix to append to the classname.
    */
   public static Logger getLogger(Class<?> clazz, String suffix)
   {
      return new Logger(clazz.getName() + "." + suffix); //$NON-NLS-1$
   }

   protected static LoggerPlugin getDelegatePlugin(String name)
   {
      LoggerPlugin plugin = null;
      try
      {
         plugin = (LoggerPlugin) pluginClass.newInstance();
      }
      catch (Throwable e)
      {
         plugin = new NullLoggerPlugin();
      }
      try
      {
         plugin.init(name);
      }
      catch(Throwable e)
      {
         System.err.println("Failed to initalize plugin: "+plugin); //$NON-NLS-1$
         plugin = new NullLoggerPlugin();
      }

      return plugin;
   }

   /** Initialize the LoggerPlugin class to use as the delegate to the
    * logging system. This first checks to see if a pluginClassName has
    * been specified via the {@link #setPluginClassName(String)} method,
    * then the PLUGIN_CLASS_PROP system property and finally the
    * LOG4J_PLUGIN_CLASS_NAME default. If the LoggerPlugin implementation
    * class cannot be loaded the default NullLoggerPlugin will be used.
    */
   protected static void init()
   {
      try
      {
         if( pluginClassName == null )
         {
            pluginClassName = ServerUtils.getLoggerClassName();
         }

         // Try to load the plugin via the TCL
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         pluginClass = cl.loadClass(pluginClassName);
      }
      catch (Throwable e)
      {
         // The plugin could not be setup, default to a null logger
         pluginClass = org.jboss.logging.NullLoggerPlugin.class;
      }
   }
}
