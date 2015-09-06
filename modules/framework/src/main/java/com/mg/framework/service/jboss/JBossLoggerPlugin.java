/*
 * Created on 22.06.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.framework.service.jboss;

import com.mg.framework.api.LoggerPlugin;

/**
 * @author safonov
 *
 *         To change the template for this generated type comment go to Window>Preferences>Java>Code
 *         Generation>Code and Comments
 */
public class JBossLoggerPlugin implements LoggerPlugin {

  private org.jboss.logging.Logger log = null;

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#init(java.lang.String)
   */
  public void init(String name) {
    log = org.jboss.logging.Logger.getLogger(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#isTraceEnabled()
   */
  public boolean isTraceEnabled() {
    return log.isTraceEnabled();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#trace(java.lang.Object)
   */
  public void trace(Object message) {
    log.trace(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#trace(java.lang.Object, java.lang.Throwable)
   */
  public void trace(Object message, Throwable t) {
    log.trace(message, t);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#isDebugEnabled()
   */
  public boolean isDebugEnabled() {
    return log.isDebugEnabled();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#debug(java.lang.Object)
   */
  public void debug(Object message) {
    log.debug(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#debug(java.lang.Object, java.lang.Throwable)
   */
  public void debug(Object message, Throwable t) {
    log.debug(message, t);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#isInfoEnabled()
   */
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#info(java.lang.Object)
   */
  public void info(Object message) {
    log.info(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#info(java.lang.Object, java.lang.Throwable)
   */
  public void info(Object message, Throwable t) {
    log.info(message, t);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#warn(java.lang.Object)
   */
  public void warn(Object message) {
    log.warn(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#warn(java.lang.Object, java.lang.Throwable)
   */
  public void warn(Object message, Throwable t) {
    log.warn(message, t);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#error(java.lang.Object)
   */
  public void error(Object message) {
    log.error(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#error(java.lang.Object, java.lang.Throwable)
   */
  public void error(Object message, Throwable t) {
    log.error(message, t);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#fatal(java.lang.Object)
   */
  public void fatal(Object message) {
    log.fatal(message);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.LoggerPlugin#fatal(java.lang.Object, java.lang.Throwable)
   */
  public void fatal(Object message, Throwable t) {
    log.fatal(message, t);
  }

}
