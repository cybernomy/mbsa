/*
 * RptPropertiesImpl.java
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
package com.mg.merp.report.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.report.RptProperties;

/**
 * Реализация интерфейса {@link RptProperties}
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptPropertiesImpl.java,v 1.5 2008/10/24 14:00:30 safonov Exp $
 */
public class RptPropertiesImpl implements RptProperties {
	private Serializable[] entityIds;
	private OutputFormat outputFormat;
	private BusinessObjectService businessService;
	private Locale locale;
	private boolean isShowParametersDialog = true;
	private Map<String, Object> paramValues = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setOutputFormat(com.mg.framework.api.report.RptProperties.OutputFormat)
	 */
	public void setOutputFormat(OutputFormat outputFormat) {
		this.outputFormat = outputFormat;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#getOutputFormat()
	 */
	public OutputFormat getOutputFormat() {
		return outputFormat;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setEntityIds(java.io.Serializable[])
	 */
	public void setEntityIds(final Serializable[] entityIds) {
		this.entityIds = entityIds;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#getEntityIds()
	 */
	public final Serializable[] getEntityIds() {
		return entityIds;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setBusinessService(com.mg.framework.api.BusinessObjectService)
	 */
	public void setBusinessService(final BusinessObjectService businessService) {
		this.businessService = businessService;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#getBusinessService()
	 */
	public final BusinessObjectService getBusinessService() {
		return businessService;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#getLocale()
	 */
	public Locale getLocale() {
		return locale;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setLocale(java.util.Locale)
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#getParameterValue(java.lang.String)
	 */
	public Object getParameterValue(String name) {
		if (name == null)
			throw new NullPointerException("param name is null");
		return paramValues.get(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setParameterValue(java.lang.String, java.lang.Object)
	 */
	public void setParameterValue(String name, Object value) {
		if (name == null)
			throw new NullPointerException("param name is null");
		paramValues.put(name, value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#hasParameterValue(java.lang.String)
	 */
	public boolean hasParameterValue(String name) {
		return paramValues.containsKey(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#isShowParametersDialog()
	 */
	public boolean isShowParametersDialog() {
		return isShowParametersDialog;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.report.RptProperties#setShowParametersDialog(boolean)
	 */
	public void setShowParametersDialog(boolean show) {
		isShowParametersDialog = show;
	}

}
