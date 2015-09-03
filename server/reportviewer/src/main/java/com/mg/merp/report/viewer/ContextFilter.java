/**
 * ContextFilter.java
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
package com.mg.merp.report.viewer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mg.framework.api.report.RptEngine;
import com.mg.framework.service.RptEngineLocator;

/**
 * Фильр для включения контекста приложения в контекст генератора отчетов
 * 
 * @author Oleg V. Safonov
 * @version $Id: ContextFilter.java,v 1.1 2008/08/12 09:05:31 safonov Exp $
 */
public class ContextFilter implements Filter {
	/**
	 * имя параметра содержащего идентификатор контекста приложения, переданного из merp,
	 * должно соотвествовать {@link com.mg.merp.report.support.RptEngineImpl#VIEWER_CONTEXT_PARAM_NAME}
	 */
	private static final String VIEWER_CONTEXT_PARAM_NAME = "merpContextId"; //$NON-NLS-1$
	/**
	 * Application Context Attribute Name, see {@link org.eclipse.birt.report.utility.ParameterAccessor#ATTR_APPCONTEXT_KEY}
	 */
	private static final String ATTR_APPCONTEXT_KEY = "AppContextKey"; //$NON-NLS-1$
	/**
	 * Application Context Attribute value, see {@link org.eclipse.birt.report.utility.ParameterAccessor#ATTR_APPCONTEXT_VALUE}
	 */
	private static final String ATTR_APPCONTEXT_VALUE = "AppContextValue"; //$NON-NLS-1$
	/**
	 * Имя параметра содержащего контекст приложения, см. {@link com.mg.merp.wb.report.birt.data.oda.badi.util.Constants#APP_REPORT_CONTEXT}
	 */
	private static final String APP_REPORT_CONTEXT_PARAM_NAME = "__mg_ReportContext"; //$NON-NLS-1$

	private RptEngine rptEngine;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		rptEngine = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String key = (String) request.getAttribute(ATTR_APPCONTEXT_KEY);
		if (key == null) {
			//идентификатор контекста передается как параметр запроса
			String contextId = request.getParameter(VIEWER_CONTEXT_PARAM_NAME);
			if (contextId != null) {
				//загружаем контекст из сервиса и устанавливаем как атрибут запроса
				//BIRT загружает данный контекст в контекст генератора отчетов при обработке запроса
				Object rptContext = rptEngine.getReportContext(contextId);
				if (rptContext != null) {
					request.setAttribute(ATTR_APPCONTEXT_KEY, APP_REPORT_CONTEXT_PARAM_NAME);
					request.setAttribute(ATTR_APPCONTEXT_VALUE, rptContext);
				}
			}
		}
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		rptEngine = RptEngineLocator.locate();
	}

}
