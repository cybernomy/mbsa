/* RptEngineServiceImpl.java
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
package com.mg.merp.report.support;

import static com.mg.merp.report.support.Messages.ENGINE_HOME_ERROR;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.FileUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.report.ReportException;
import com.mg.merp.report.RptMainServiceLocal;
import com.mg.merp.report.model.RptMain;
import com.mg.merp.report.support.ui.ChooseReportDialog;

/**
 * ���������� ������� MBIRT (Millennium BI and Report Tools)
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineServiceImpl.java,v 1.10 2008/08/12 09:27:53 safonov Exp $
 */
public class RptEngineServiceImpl {
	private Logger log = ServerUtils.getLogger(RptEngineServiceImpl.class);
	private String htmlReportViewerApp;
	private String reportFolder;

	/**
	 * ������������ ������ BIRT
	 */
	private EngineConfig config;

	/**
	 * ��������� ������ BIRT
	 */
	private IReportEngine engine;

	/**
	 * ���������� ���� ��������� BIRT
	 */
	private String engineHome;
	
	/**
	 * ������ ������ �� ���������
	 */
	private RptProperties.OutputFormat defaultOutputFormat;
	
	/**
	 * ������� �����������
	 */
	private Level logLevel;
	
	private Map<String, Map<String, Object>> contextMap = new ConcurrentHashMap<String, Map<String,Object>>();
	
	/**
	 * �����������
	 * 
	 * @param engineHome	���������� ���� � ��������� BIRT
	 */
	public RptEngineServiceImpl(String engineHome, String defaultOutputFormat, String logLevel,
			String htmlReportViewerApp, String reportFolder) {
		this.engineHome = engineHome;
		this.defaultOutputFormat = getOutputFormat(defaultOutputFormat);
		this.logLevel = getLogLevel(logLevel);
		this.htmlReportViewerApp = htmlReportViewerApp;
		this.reportFolder = reportFolder;
	}

	/**
	 * �������� ������� �����������
	 * 
	 * @param logLevel	������������ ������ �����������
	 * @return	������� ����������� {@link java.util.logging.Level}
	 */
	private Level getLogLevel(String logLevel) {
		Level level = Level.SEVERE;
		if ( "SEVERE".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.SEVERE;
	    }
	    else if ( "WARNING".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.WARNING;
	    }
	    else if ( "INFO".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.INFO;
	    }
	    else if ( "CONFIG".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.CONFIG;
	    }
	    else if ( "FINE".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.FINE;
	    }
	    else if ( "FINER".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.FINER;
	    }
	    else if ( "FINEST".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.FINEST;
	    }
	    else if ( "OFF".equalsIgnoreCase( logLevel ) ) //$NON-NLS-1$
	    {
	    	level = Level.OFF;
	    }
		return level;
	}
	
	/**
	 * �������� ������ ������ �� ������
	 * 
	 * @param defaultOutputFormat	��������� ������������� ������� ������
	 * @return	������ ������
	 */
	private RptProperties.OutputFormat getOutputFormat(String defaultOutputFormat) {
		if (!StringUtils.stringNullOrEmpty(defaultOutputFormat))
			for (RptProperties.OutputFormat format : RptProperties.OutputFormat.values())
				if (format.toString().compareToIgnoreCase(defaultOutputFormat.trim()) == 0) {
					log.debug("Use default output format, set to " + format.toString());
					return format;
				}
		log.debug("Default output format is null, set to HTML");		
		return RptProperties.OutputFormat.HTML;
	}

	/**
	 * �������� ������������ ��������� BIRT
	 * 
	 * @return	������������
	 */
	@SuppressWarnings("unchecked")
	private EngineConfig createEngineConfig() {
		EngineConfig result = new EngineConfig();
		result.setEngineHome(engineHome);
		result.setLogConfig(ServerUtils.getServerLogDir().getAbsolutePath(), logLevel);
		result.setResourcePath(engineHome.concat("/resources"));
		result.setTempDir(ServerUtils.getServerTempDir().getAbsolutePath());
		
		//��������� ��� HTML �������
		HTMLRenderOption renderOption = new HTMLRenderOption();
		renderOption.setActionHandler(new HTMLActionHandler());
		HTMLServerImageHandler imageHandler = new HTMLServerImageHandler();
		renderOption.setImageHandler(imageHandler);
		result.getEmitterConfigs().put("html", renderOption); //$NON-NLS-1$

		return result;
	}
	
	/**
	 * �������� ���������
	 */
	private void checkEngine() {
		if (engine == null)
			throw new ReportException(Messages.getInstance().getMessage(ENGINE_HOME_ERROR));
	}
	
	/**
	 * ���������� ������� ��������� ������
	 * 
	 * @param properties	��������
	 * @return	�������������� ��������
	 */
	private RptProperties prepareProperties(RptMain report, RptProperties properties) {
		RptProperties result = properties;
		//�������� ��������� �� ���������
		if (result == null) {
			log.warn("Generate default report properties");
			result = new RptPropertiesImpl();
		}
		//��������� ������ ������ �� ��������� ������
		if (report.getOutputFormat() != null)
			result.setOutputFormat(getOutputFormat(report.getOutputFormat()));
		//���� ������ �� ����������, �� ���������� �� ���������
		if (result.getOutputFormat() == null) {
			log.warn("Report output format is null, set to default format " + defaultOutputFormat.toString());
			result.setOutputFormat(defaultOutputFormat);			
		}
		return result;
	}
	
	/**
	 * �������� ������ ��������� ������� ��� ������-����������
	 * 
	 * @return ��������� ������
	 */
	private List<RptMain> getAvailableReports(BusinessObjectService businessService) {
		return ((RptMainServiceLocal) ApplicationDictionaryLocator.locate()
					.getBusinessService(RptMainServiceLocal.SERVICE_NAME)).loadAvailableReports(businessService.getBusinessServiceMetadata().getIdentificator());
	}

	/**
	 * ������������� ���������� �������
	 * 
	 * @param properties
	 */
	private void executeReportDialog(final List<RptMain> rptList, final RptProperties properties) {
		final ChooseReportDialog dialog = (ChooseReportDialog) ApplicationDictionaryLocator.locate().getWindow(ChooseReportDialog.FORM_NAME);
		dialog.addOkActionListener(new FormActionListener() {
			
			public void actionPerformed(FormEvent event) {
				runAndRenderReport(dialog.getReport(), properties);
			}
			
		});
		dialog.setReports(rptList);
		dialog.execute();
	}

	
	/**
	 * �������� � ����� �������
	 */
	public void createEngine() throws Exception {
		if (config != null && engine != null)
			throw new IllegalStateException("Engine already exist");
		
		if (StringUtils.stringNullOrEmpty(engineHome))
			throw new IllegalStateException("Attribute BIRTLocation is empty");
		
		config = createEngineConfig();

		// XXX OVS workaround bug
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=156877, fixed in
		// Eclipse framework 3.3 (BIRT runtime version unknown)
//		String entry = System.getProperty("java.protocol.handler.pkgs");
//		System.setProperty("java.protocol.handler.pkgs", entry.replaceAll(
//				"|org.jboss.net.protocol", "").replaceAll(
//				"org.jboss.net.protocol", ""));
		// ---------------------------------------------------------------------

		Platform.startup(config);
		IReportEngineFactory factory = (IReportEngineFactory) Platform
				.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		engine = factory.createReportEngine(config);
		
		File reportDesignFolder = new File(engineHome + File.separator + reportFolder);
		reportDesignFolder.mkdir();
	}

	/**
	 * ��������� � ������������ �������� �������
	 */
	public void destroyEngine() {
		engine.destroy();
		Platform.shutdown();
		
		FileUtils.deleteDir(engineHome + File.separator + reportFolder);
	}

	/**
	 * �������� ������ ��������� BIRT
	 * 
	 * @return	������ ���������
	 */
	public IReportEngine getEngine() {
		return engine;
	}

	/**
	 * ������ ��������� ������
	 * 
	 * @param properties	��������� ������
	 */
	public void runAndRenderReport(RptProperties properties) {
		List<RptMain> rptList = getAvailableReports(properties.getBusinessService());
		if (rptList.isEmpty())
			return;
		if (rptList.size() == 1)
			runAndRenderReport(rptList.get(0), properties);
		else
			executeReportDialog(rptList, properties);
	}

	/**
	 * �������� ���������� ���������� ���������
	 * 
	 * @return	��������� ����������
	 */
	public RptProperties createProperies() {
		return new RptPropertiesImpl();
	}
	
	/**
	 * ������ ��������� ������
	 * 
	 * @param report		�����
	 * @param properties	��������� ������
	 */
	public void runAndRenderReport(RptMain report, RptProperties properties) {
		checkEngine();
		Map<String, Object> rptContext = new HashMap<String, Object>();
		String contextId = DataUtils.generateUUID();
		rptContext.put(RptEngineImpl.CONTEXT_ID, contextId);
		contextMap.put(contextId, rptContext);
		RptEngineImpl impl = new RptEngineImpl(engine, rptContext, htmlReportViewerApp, reportFolder);
		impl.runAndRenderReport(report, prepareProperties(report, properties));
	}

	public List<Object[]> performBAi(String code, Map<String, Object> params) {
		//TODO ���������� BAi �� ������ ����������, ��� ���������� ������� ���������������� ���������
		return new ArrayList<Object[]>();
	}

	/**
	 * �������� �������� ���������� ��� ������
	 * 
	 * @param contextId	������������� ���������
	 * @return	�������� ��� <code>null</code> ���� �� ����������
	 */
	public Object getReportContext(String contextId) {
		return contextId == null ? null : contextMap.get(contextId);
	}

}
