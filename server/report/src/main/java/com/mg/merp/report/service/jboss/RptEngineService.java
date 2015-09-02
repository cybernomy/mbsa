/*
 * RptEngineService.java
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
package com.mg.merp.report.service.jboss;

import java.util.List;
import java.util.Map;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.report.RptProperties;
import com.mg.merp.report.model.RptMain;
import com.mg.merp.report.support.RptEngineServiceImpl;

/**
 * Реализация сервиса MBIRT (Millennium BI and Report Tools) для AS JBoss
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineService.java,v 1.7 2008/08/12 09:24:56 safonov Exp $
 */
public class RptEngineService extends ServiceMBeanSupport implements
		RptEngineServiceMBean {
	private RptEngineServiceImpl delegate;
	private String birtLocation;
	private String defaultOutputFormat;
	private String logLevel;
	private String htmlReportViewerApp;
	private String reportFolder;

	@Override
	protected void createService() throws Exception {
		delegate = new RptEngineServiceImpl(birtLocation, defaultOutputFormat, logLevel, htmlReportViewerApp, reportFolder);
	}

	@Override
	protected void destroyService() throws Exception {
		delegate = null;
	}

	@Override
	protected void startService() throws Exception {
		delegate.createEngine();
	}

	@Override
	protected void stopService() throws Exception {
		delegate.destroyEngine();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#getBIRTLocation()
	 */
	public String getBIRTLocation() {
		return birtLocation;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#setBIRTLocation(java.lang.String)
	 */
	public void setBIRTLocation(String birtLocation) {
		this.birtLocation = birtLocation;
	}

	public String getDefaultOutputFormat() {
		return defaultOutputFormat;
	}

	public void setDefaultOutputFormat(String outputFormat) {
		defaultOutputFormat = outputFormat;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptEngine#run(com.mg.framework.api.report.RptProperties)
	 */
	public void runAndRenderReport(RptProperties properties) throws ApplicationException {
		delegate.runAndRenderReport(properties);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptEngine#createProperies()
	 */
	public RptProperties createProperies() {
		return delegate.createProperies();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptEngine#rendReport(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.report.RptProperties)
	 */
	public void runAndRenderReport(PersistentObject report, RptProperties properties) {
		delegate.runAndRenderReport((RptMain)report, properties);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#performBAi(java.lang.String, java.util.Map)
	 */
	public List<Object[]> performBAi(String code, Map<String, Object> params) {
		return delegate.performBAi(code, params);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptEngine#getDelegate()
	 */
	public Object getDelegate() {
		return delegate.getEngine();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptEngine#getReportContext()
	 */
	public Object getReportContext(String contextId) {
		return delegate.getReportContext(contextId);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#getHTMLReportViewerApp()
	 */
	public String getHTMLReportViewerApp() {
		return htmlReportViewerApp;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#getReportFolder()
	 */
	public String getReportFolder() {
		return reportFolder;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#setHTMLReportViewerApp(java.lang.String)
	 */
	public void setHTMLReportViewerApp(String viwerApp) {
		htmlReportViewerApp = viwerApp;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.service.jboss.RptEngineServiceMBean#setReportFolder(java.lang.String)
	 */
	public void setReportFolder(String reportFolder) {
		this.reportFolder = reportFolder;
	}

}
