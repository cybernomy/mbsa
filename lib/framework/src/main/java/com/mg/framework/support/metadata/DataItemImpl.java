/*
 * DataItemImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.support.metadata;

import java.util.Locale;

import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.DataItemDocumentation;
import com.mg.framework.api.metadata.DataItemKind;
import com.mg.framework.api.metadata.Domain;
import com.mg.framework.api.metadata.EntityText;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: DataItemImpl.java,v 1.6 2008/06/07 07:00:09 safonov Exp $
 */
public class DataItemImpl implements DataItem {
	public String name;
	public Domain domain;
	public String shortLabel;
	private DataItem model;

	public DataItemImpl(DataItem model) {
		if (model == null)
			throw new NullPointerException("DataItem model must not be null");
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getName()
	 */
	public String getName() {
		return model.getName();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDescription()
	 */
	public String getDescription() {
		return model.getDescription();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getType()
	 */
	public DataItemKind getKind() {
		return model.getKind();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDomain()
	 */
	public Domain getDomain() {
		if (domain == null)
			domain = new DomainImpl((Domain) model.getDomain());
		return domain;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDefaultComponentName()
	 */
	public String getDefaultComponentName() {
		return model.getDefaultComponentName();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabel()
	 */
	public String getShortLabel() {
		return getShortLabel(ServerUtils.getUserLocale());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabel(java.util.Locale)
	 */
	public String getShortLabel(Locale locale) {
		return UIUtils.loadL10nText(locale, model.getShortLabel());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabelMaxLength()
	 */
	public int getShortLabelMaxLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabel()
	 */
	public String getMediumLabel() {
		return getMediumLabel(ServerUtils.getUserLocale());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabel(java.util.Locale)
	 */
	public String getMediumLabel(Locale locale) {
		String result = UIUtils.loadL10nText(locale, model.getMediumLabel());
		//используем короткую метку если не установлена средняя
		if (result == null)
			result = getShortLabel(locale);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabelMaxLength()
	 */
	public int getMediumLabelMaxLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getLongLabel()
	 */
	public String getLongLabel() {
		return getLongLabel(ServerUtils.getUserLocale());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getLongLabel(java.util.Locale)
	 */
	public String getLongLabel(Locale locale) {
		String result = UIUtils.loadL10nText(locale, model.getLongLabel());
		//используем среднюю метку если нет длинной
		if (result == null)
			result = getMediumLabel(locale);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getLongLabelMaxLength()
	 */
	public int getLongLabelMaxLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeader()
	 */
	public String getHeader() {
		return getHeader(ServerUtils.getUserLocale());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeader(java.util.Locale)
	 */
	public String getHeader(Locale locale) {
		String result = UIUtils.loadL10nText(locale, model.getHeader());
		//если не установлен, то используем длинную метку в качестве заголовка
		if (result != null)
			return result;
		else
			return getLongLabel(locale);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeaderMaxLength()
	 */
	public int getHeaderMaxLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabel()
	 */
	public String getReportLabel() {
		return getReportLabel(ServerUtils.getUserLocale());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabel(java.util.Locale)
	 */
	public String getReportLabel(Locale locale) {
		String result = UIUtils.loadL10nText(locale, model.getReportLabel());
		if (result != null)
			return result;
		else
			return getLongLabel(locale);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabelMaxLength()
	 */
	public int getReportLabelMaxLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDocumentation()
	 */
	public DataItemDocumentation getDocumentation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getSearchHelp()
	 */
	public SearchHelp getSearchHelp() {
		return SearchHelpProcessor.createSearch(getSearchHelpName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getSearchHelpName()
	 */
	public String getSearchHelpName() {
		return (String) ((PersistentObject) model).getAttribute("SearchHelpName");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getAssignParameterID()
	 */
	public String getAssignParameterName() {
		return model.getAssignParameterName();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getEntityText()
	 */
	public EntityText getEntityText() {
		EntityText result = new EntityText();
		result.propertyText = MiscUtils.generateEntityPropertyText(getEntityPropertyText());
		result.textFormat = UIUtils.loadL10nText(getEntityTextFormat());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getEntityPropertyText()
	 */
	public String getEntityPropertyText() {
		return (String) ((PersistentObject) model).getAttribute("EntityPropertyText");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getEntityTextFormat()
	 */
	public String getEntityTextFormat() {
		return (String) ((PersistentObject) model).getAttribute("EntityTextFormat");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#isReadOnly()
	 */
	public boolean isReadOnly() {
		return model.isReadOnly();
	}

}
