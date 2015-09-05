/*
 * Created on 22.06.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.framework.service;

import com.mg.framework.api.LoggerPlugin;

/**
 * @author safonov
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NullLoggerPlugin implements LoggerPlugin {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#init(java.lang.String)
	 */
	public void init(String name) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#trace(java.lang.Object)
	 */
	public void trace(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#trace(java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Object message, Throwable t) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#debug(java.lang.Object)
	 */
	public void debug(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#info(java.lang.Object)
	 */
	public void info(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object message, Throwable t) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#warn(java.lang.Object)
	 */
	public void warn(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#error(java.lang.Object)
	 */
	public void error(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object message, Throwable t) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LoggerPlugin#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t) {
	}

}
