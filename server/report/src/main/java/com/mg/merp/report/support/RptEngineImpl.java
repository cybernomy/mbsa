/*
 * ReportEngineImpl.java
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

import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BAI_CODE;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BAI_ENGINE_PARAM;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BUSINESS_SERVICE;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.CURRENT_SESSION_PARAM;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.DATASET_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.ENTITY_IDS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.MERP_DATASET_ID;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.REPORT_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.APP_REPORT_CONTEXT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IEngineTask;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.OdaDataSetHandle;
import org.eclipse.birt.report.model.api.elements.structures.DataSetParameter;
import org.eclipse.birt.report.model.api.util.ParameterValidationUtil;

import com.ibm.icu.math.BigDecimal;
import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.service.UIProfileManagerLocator;
import com.mg.framework.support.report.ReportUtils;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.UniversalFileWebResourceProvider;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.report.ReportException;
import com.mg.merp.report.model.RptMain;
import com.mg.merp.report.parameters.ReportParameter;
import com.mg.merp.report.parameters.ReportParametersDialog;
import com.mg.merp.report.parameters.SelectionChoice;
import com.mg.merp.report.parameters.support.ReportParameterImpl;
import com.mg.merp.report.parameters.support.SelectionChoiceImpl;
import com.mg.merp.report.support.ui.ReportParametersDialogImpl;

/**
 * Реализация генератора отчётов
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineImpl.java,v 1.36 2009/01/22 07:52:20 safonov Exp $
 */
public class RptEngineImpl {
	public static final String CONTEXT_ID = "CONTEXT_ID"; //$NON-NLS-1$;
	private static final String DOC_SUFFIX = ".rptdocument"; //$NON-NLS-1$;
	private static final String REPORT_SUFFIX = ".rptdesign"; //$NON-NLS-1$;
	private static final String PARAMETER_SEPARATOR = "&"; //$NON-NLS-1$;
	private static final String EQUALS_OPERATOR = "="; //$NON-NLS-1$
	private static final String VIEWER_CONTEXT_PARAM_NAME = "merpContextId"; //$NON-NLS-1$

	private Logger log = ServerUtils.getLogger(RptEngineImpl.class);
	
	private int bufferSize = 1024 * 100;
	
	private IReportEngine engine;

	private IReportRunnable design;

	private Map<String, ReportParameter> reportParams;
	/**
	 * список параметров установленных через API запуска отчета
	 */
	private Set<String> externalParams;
	
	private RptProperties rptProperties;
	
	private Map<String, Object> context;
	
	/**
	 * BIRT не работает с именованными параметрами. Используется для
	 * установления соответствия между порядковым номером и именем параметром
	 * 
	 */
	private Map<String, ArrayList<String>> datasetParams = new LinkedHashMap<String, ArrayList<String>>();

	private String htmlReportViewerApp;

	private String reportFolder;

	public RptEngineImpl(IReportEngine engine, Map<String, Object> context, String htmlReportViewerApp, String reportFolder) {
		this.engine = engine;
		this.context = context;
		this.htmlReportViewerApp = htmlReportViewerApp;
		this.reportFolder = reportFolder;
	}

	/**
	 * загрузить список значений параметра отчета
	 * 
	 * @param paramName	имя параметра
	 * @return	список значений
	 */
	public List<SelectionChoice> getParameterSelectionList(String paramName) {
		IGetParameterDefinitionTask task = null;
		try {
			task = engine.createGetParameterDefinitionTask(design);
			if ( task != null ) {
				setupEngineTask(task, rptProperties);
				return SelectionChoiceImpl.convertEngineParameterSelectionChoice(task.getSelectionList(paramName));
			}
		} finally {
			if (task != null)
				task.close();
		}

		return null;
	}

	/**
	 * загрузить список значений каскадного параметра
	 * 
	 * @param paramName	имя параметра
	 * @param groupName	имя каскадной группы
	 * @param groupKeys	список значений параметров находящихся выше по иерархии в каскадной группе
	 * @return	список значений
	 */
	@SuppressWarnings("deprecation")
	public List<SelectionChoice> getSelectionListForCascadingGroup(String paramName, String groupName, Object[] groupKeys) {
		IGetParameterDefinitionTask task = null;
		try {
			task = engine.createGetParameterDefinitionTask(design);
			if ( task != null ) {
				setupEngineTask(task, rptProperties);
				task.evaluateQuery(groupName);
				return SelectionChoiceImpl.convertEngineParameterSelectionChoice(task.getSelectionListForCascadingGroup(groupName, groupKeys));
			}
		} finally {
			if (task != null)
				task.close();
		}

		return null;
	}

	/**
	 * найти параметр отчета по имени
	 * 
	 * @param name	имя параметра
	 * @return	параметр или <code>null</code> если не найден
	 */
	public ReportParameter findReportParameter(String name) {
		return reportParams.get(name);
	}

	/**
	 * загрузить список параметров группы
	 * 
	 * @param groupName	имя группы
	 * @return	список параметров
	 */
	public List<ReportParameter> findGroupReportParameters(String groupName) {
		List<ReportParameter> result = new ArrayList<ReportParameter>();
		if (!StringUtils.stringNullOrEmpty(groupName))
			for (ReportParameter param : reportParams.values())
				if (groupName.equals(param.groupName()))
					result.add(param);
		return result;
	}

	/**
	 * Заполнение отчёта
	 */
	private void fillReport(RptMain report, RptProperties properties) {
		String reportDoc = generateReportFileName(report, properties);
		
		IReportDocument rd = null;
		byte[] reportResult = null;

		try {
			IRunTask runTask = engine.createRunTask(design);
			setupEngineTask(runTask, properties);
			try {
				runTask.run(reportDoc);
			} finally {
				runTask.close();
			}

			rd = engine.openReportDocument(reportDoc);

			IRenderTask rendTask = engine.createRenderTask(rd);
			setupRenderTask(rendTask, properties);

			try {
				rendTask.render();

				reportResult = ((ByteArrayOutputStream) rendTask.getRenderOption().getOutputStream()).toByteArray();
			} finally {
				rendTask.close();
			}
		} catch (EngineException e) {
			if (e.getCause() instanceof ApplicationException)
				throw (ApplicationException) e.getCause();
			else
				throw new ReportException(Messages.getInstance().getMessage(Messages.BIRT_ERROR), e);
		} finally {
			if (rd != null)
				rd.close();
			File f = new File(reportDoc);
			f.delete();
			f = null;
		}

		showReport(report, reportResult, properties);
	}

	private void setupReportContext(Map<String, Object> reportContext, RptProperties properties) {
		// драйвер не имеет прямого доступа к этим параметрам. Хотя, может это и
		// не надо
		reportContext.put(REPORT_PARAMS, reportParams);
		// BIRT не даёт работать с поименованными параметрами, приходится
		// обходить
		reportContext.put(DATASET_PARAMS, datasetParams);

		reportContext.put(ENTITY_IDS, properties.getEntityIds());
		reportContext.put(BUSINESS_SERVICE, properties.getBusinessService());
		reportContext.put(BAI_ENGINE_PARAM, new RptBAiStarterImpl());
		reportContext.put(CURRENT_SESSION_PARAM, ServerUtils.getCurrentSession());	
	}

	private void setupEngineTask(IEngineTask engineTask, RptProperties properties) {
		Map<String, Object> params = new HashMap<String, Object>();
		for (String key : reportParams.keySet()) {
			ReportParameter param = reportParams.get(key);
			if (param.getDataType() == ReportParameter.DataType.ENTITY)
				params.put(key, ReportUtils.createEntityParam(param.getValue()));
			else
				params.put(key, param.getValue());
		}

		engineTask.setParameterValues(params);

		setupReportContext(context, properties);
		Map<String, Object> appContext = new HashMap<String, Object>();
		appContext.put(APP_REPORT_CONTEXT, context);

		engineTask.setAppContext(appContext);
		engineTask.setLocale(properties.getLocale());
	}
	
	private void setupRenderTask(IRenderTask rendTask, RptProperties properties) {
		String outputFormat = null;
		switch (properties.getOutputFormat()) {
		case HTML:
			outputFormat = "html";
			break;
		case DOC:
			outputFormat = "doc";
			break;
		case PDF:
			outputFormat = "pdf";
			break;
		case PPT:
			outputFormat = "ppt";
			break;
		case XLS:
			outputFormat = "xls";
			break;
		default:
			outputFormat = "html";
		}
		IRenderOption renderOption = new RenderOption();
		renderOption.setOutputStream(new ByteArrayOutputStream(bufferSize));
		renderOption.setOutputFormat(outputFormat);
		rendTask.setRenderOption(renderOption);
		rendTask.setLocale(properties.getLocale());
	}
	
	private void addReportParameter(RptProperties properties, String name, IScalarParameterDefn param, IParameterGroupDefn paramGroup, Object defaultValue) {
		if (RptProperties.ENTITY_IDS_DATASET_PARAMETER.equalsIgnoreCase(name))
			defaultValue = ReportUtils.createEntityIdsParam(properties.getEntityIds());
		else if (RptProperties.BUSINESS_SERVICE_DATASET_PARAMETER.equalsIgnoreCase(name)) {
			BusinessObjectService service = properties.getBusinessService();
			if (service != null)
				defaultValue = service.getBusinessServiceMetadata().getName();
		} else if (properties.hasParameterValue(name)) {
			defaultValue = properties.getParameterValue(name);
			externalParams.add(name);
		}

		reportParams.put(name, new ReportParameterImpl(param, paramGroup, defaultValue, this));
	}

	@SuppressWarnings("unchecked")
	private void addGroupReportParameter(RptProperties properties, String name, IParameterGroupDefn param, IGetParameterDefinitionTask paramTask) {
		Collection<IParameterDefnBase> elements = ((IParameterGroupDefn) param).getContents();
		for (IParameterDefnBase param2 : elements)
			if (param2 instanceof IParameterGroupDefn)
				addGroupReportParameter(properties, param2.getName(), (IParameterGroupDefn) param2, paramTask);
			else if (param2 instanceof IScalarParameterDefn)
				addReportParameter(properties, param2.getName(), (IScalarParameterDefn) param2, (IParameterGroupDefn) param, paramTask.getDefaultValue(param2));		
	}

	@SuppressWarnings("unchecked")
	private void prepareReportParams(RptProperties properties) {
		externalParams = new HashSet<String>();
		reportParams = new LinkedHashMap<String, ReportParameter>(); //use LinkedHashMap http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4577
		IGetParameterDefinitionTask paramTask = engine.createGetParameterDefinitionTask(design);
		try {
			Collection<IParameterDefnBase> paramsC = paramTask.getParameterDefns(true);
			for (IParameterDefnBase param : paramsC) {
				if (param instanceof IParameterGroupDefn)
					addGroupReportParameter(properties, param.getName(), (IParameterGroupDefn) param, paramTask);
				else if (param instanceof IScalarParameterDefn)
					addReportParameter(properties, param.getName(), (IScalarParameterDefn) param, null, paramTask.getDefaultValue(param));
			}
		} finally {
			paramTask.close();
		}
		
		/*
		 * BIRT не даёт работать с поименованными параметрами, приходится
		 * обходить
		 */
		List<DataSetHandle> dshL = (List<DataSetHandle>) design.getDesignHandle().getModuleHandle().getAllDataSets();

		for (DataSetHandle dsH : dshL) {
			if (dsH instanceof OdaDataSetHandle && MERP_DATASET_ID.equals(((OdaDataSetHandle) dsH).getExtensionID())) {
				ArrayList<String> paramNames = new ArrayList<String>();
				ArrayList<DataSetParameter> prms = (ArrayList<DataSetParameter>) dsH.getListProperty("parameters");
				if (prms != null) {
					for (DataSetParameter prm : prms)
						paramNames.add(prm.getName());

					datasetParams.put(dsH.getStringProperty(BAI_CODE), paramNames);
				}
			}
		}
		// *********************************************************//
	}
	
	private void internalRun(RptMain report, RptProperties properties) {
		if (report == null) {
			log.debug("Report entity is null");
			return;
		}
		
		byte[] reportTemplate = report.getTemplate();
		if (reportTemplate == null)
			throw new ReportException(Messages.getInstance().getMessage(Messages.REPORT_TEMPLATE_NULL));
		
		try {
			design = engine.openReportDesign(new ByteArrayInputStream(reportTemplate));
		} catch (EngineException ex) {
			throw new ReportException(Messages.getInstance().getMessage(Messages.BIRT_ERROR), ex);
		}

		this.rptProperties = properties;
		prepareReportParams(properties);

		if (!reportParams.isEmpty())
			executeParamsDialog(report, properties);
		else
			internalShowReport(report, properties, true);
	}
	
	private void internalShowReport(RptMain report, RptProperties properties, boolean showParams) {
		//используем BIRT Report Viewer только для HTML отчетов, остальные сразу генерируем в нужном формате
		if (properties.getOutputFormat() == null || properties.getOutputFormat() == RptProperties.OutputFormat.HTML) {
			StringBuilder sb1 = new StringBuilder(reportFolder).append(File.separatorChar)
					.append("report").append(Math.abs(new Random().nextInt()))
					/*.append(report.getCode().trim())*/.append(REPORT_SUFFIX);
			String relativeFileName = sb1.toString();
			try {
				File f = new File(sb1.insert(0, File.separatorChar).insert(0, engine.getConfig().getBIRTHome()).toString());
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(report.getTemplate());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				log.error("Create report file failed", e);
				throw new ReportException(e);
			}

			showHTMLReportViewer(report, relativeFileName, properties, showParams);
		} else
			fillReport(report, properties);
	}
	
	private String encodeURL(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported encoding UTF-8", e);
			return str;
		}		
	}
	
	private void showHTMLReportViewer(RptMain report, String reportFileName, RptProperties properties, boolean showParams) {
		setupReportContext(context, properties);
		
		//документация по BIRT Viewer
		//http://www.eclipse.org/birt/phoenix/deploy/viewerUsage2.2.php
		StringBuilder sb = new StringBuilder(htmlReportViewerApp).append("/frameset?__report=")
				.append(StringUtils.encodeBase64(reportFileName)).append("&__encodedPaths=true")
				.append("&__format=html")
				.append("&__parameterpage=false")
				.append("&__title=").append(encodeURL(!StringUtils.stringNullOrEmpty(report.getComment()) ? report.getComment() : report.getRptName()))
				.append("&__locale=").append(ServerUtils.getUserLocale())
				.append("&").append(VIEWER_CONTEXT_PARAM_NAME).append("=").append(context.get(CONTEXT_ID));
		
		//формирование параметров отчета
		for (String key : reportParams.keySet()) {
			ReportParameter param = reportParams.get(key);
			Object paramValue = null;
			if (param.getDataType() == ReportParameter.DataType.ENTITY) {
				Object obj = param.getValue();
				if (obj != null)
					paramValue = ReportUtils.createEntityParam(obj);
				else
					sb.append("&__isnull=").append(key);
			}
			else
				paramValue = param.getValue();
			if (paramValue != null) {
				String strParamValue = null;
				//this is a total hack, see org.eclipse.birt.report.utility.DataUtil#getDisplayValue
				if (paramValue instanceof Float || paramValue instanceof Double)
					strParamValue = paramValue.toString();
				else if (paramValue instanceof BigDecimal
						|| paramValue instanceof com.ibm.icu.math.BigDecimal)
					strParamValue = paramValue.toString( ).replaceFirst( "E\\+", "E" ); //$NON-NLS-1$//$NON-NLS-2$
				else
					strParamValue = ParameterValidationUtil.getDisplayValue(paramValue);
					
				sb.append(PARAMETER_SEPARATOR).append(key).append(EQUALS_OPERATOR).append(encodeURL(strParamValue));
			}
		}
		
		UIUtils.showLocalDocument(sb.toString());
	}
	
	/**
	 * Генерация отчёта
	 * 
	 * @param report		отчет
	 * @param properties	параметры генерации
	 */
	public void runAndRenderReport(RptMain report, RptProperties properties) {
		internalRun(ServerUtils.getPersistentManager().find(RptMain.class, report.getId()), properties);
	}

	/**
	 * Запрос параметров отчёта
	 * 
	 */
	private void executeParamsDialog(final RptMain report, final RptProperties properties) {
		if (report.isAskParams() && properties.isShowParametersDialog()) {
			ReportParametersDialog dlg = null;
			if (report.getParamsFormName() != null)
				// запрос параметров с помощью формы пользователя
				dlg = (ReportParametersDialog) UIProducer.produceForm(report.getParamsFormName());
			else
				// автоматическое создание формы
				dlg = (ReportParametersDialog) UIProducer.produceFormFromString(
						ReportParametersDialogImpl.createForm(report, reportParams));

			dlg.addOkActionListener(new FormActionListener() {

				public void actionPerformed(FormEvent event) {
					saveParamsValue(report);
					internalShowReport(report, properties, false);
				}
				
			});
			restoreParamsValue(report);
			dlg.setParameters(reportParams);
			dlg.execute();
		} else
			internalShowReport(report, properties, false);
	}

	private UIProfile getReportUIProfile(RptMain report) {
		return UIProfileManagerLocator.locate().load("com.mg.merp.report.params." + report.getCode());
	}

	/**
	 * сохранение параметров отчета
	 * 
	 * @param report
	 */
	private void saveParamsValue(RptMain report) {
		try {
			UIProfile profile = getReportUIProfile(report);
			for (Map.Entry<String, ReportParameter> entry : reportParams.entrySet()) {
				//не сохраняем системные параметры
				if (!entry.getKey().startsWith("__"))
					profile.setProperty(entry.getKey(), DataUtils.valueToString(entry.getValue().getValue()));
			}
			UIProfileManagerLocator.locate().store(profile);
		} catch (Exception e) {
			log.error("Cann't save report parameters, report code: " + report.getCode(), e);
		}
	}

	/**
	 * восстановление парамтеров отчета
	 * 
	 * @param report
	 */
	private void restoreParamsValue(RptMain report) {
		try {
			UIProfile profile = getReportUIProfile(report);
			for (Map.Entry<String, ReportParameter> entry : reportParams.entrySet()) {
				//не восстанавливаем системные и установленные через API параметры
				if (!entry.getKey().startsWith("__") && !externalParams.contains(entry.getKey())) {
					String propValue = profile.getProperty(entry.getKey());
					if (propValue != null)
						entry.getValue().setValue(DataUtils.stringToValue(propValue, entry.getValue().getJavaType()));
				}
			}
		} catch (Exception e) {
			log.error("Cann't restore report parameters, report code: " + report.getCode(), e);
		}
	}
	
	/**
	 * генерация имени файла документа отчета
	 * 
	 * @param rptMain
	 * @param properties
	 * @return	имя файла документа отчёта
	 */
	private static String generateReportFileName(RptMain rptMain, RptProperties properties) {
		return new StringBuilder(ServerUtils.getServerTempDir().getAbsolutePath()).append(File.separatorChar)
				.append("tmp").append(Math.abs(new Random().nextInt()))
				.append(rptMain.getCode().trim()).append(DOC_SUFFIX).toString();
	}

	private String generateFullReportName(String reportName, RptProperties properties) {
		String suffix = null;
		switch (properties.getOutputFormat()) {
		case DOC:
			suffix = "doc";
			break;
		case HTML:
			suffix = "html";
			break;
		case PDF:
			suffix = "pdf";
			break;
		case PPT:
			suffix = "ppt";
			break;
		case XLS:
			suffix = "xls";
			break;
		}
		return new StringBuilder(reportName).append(".").append(suffix).toString();
	}
	
	private String getMimeType(RptProperties properties) {
		switch (properties.getOutputFormat()) {
		case DOC:
			return "application/msword";
		case HTML:
			return "text/html";
		case PDF:
			return "application/pdf";
		case PPT:
			return "application/vnd.ms-powerpoint";
		case XLS:
			return "application/vnd.ms-excel";
		default:
			return "text/html";
		}
	}
	
	/**
	 * Отображение отчёта
	 * 
	 * @param path	имя файла отчёта
	 */
	private void showReport(RptMain report, byte[] reportResult, RptProperties properties) {
		UIUtils.showDocument(new UniversalFileWebResourceProvider(getMimeType(properties),
				generateFullReportName(report.getCode().trim(), properties), reportResult));
	}

}
