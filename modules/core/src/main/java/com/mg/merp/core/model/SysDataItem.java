/*
 * SysDataItem.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.core.model;

import java.util.Locale;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.api.metadata.DataItemDocumentation;
import com.mg.framework.api.metadata.EntityText;
import com.mg.framework.api.metadata.DataItemKind;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: SysDataItem.java,v 1.3 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName ("Core.SysDataItem")
public class SysDataItem extends PersistentObjectHibernate implements java.io.Serializable, com.mg.framework.api.metadata.DataItem {
	private Integer id;
	private String name;
	private String description;
	private SysDomain sysDomain;
	private String defaultComponentName;
	private String shortLabel;
	private String mediumLabel;
	private String longLabel;
	private String header;
	private String reportLabel;
	private String searchHelpName;
	private String entityPropertyText;
	private String entityTextFormat;
	private boolean isReadOnly;
	private DataItemKind dataItemKind;
	private String referenceDataItemName;
	private String assignParameterName;
	private ApplicationLayer applicationLayer;
	
	public SysDataItem() {
	}
	
	public SysDataItem(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the defaultComponentName.
	 */
	@DataItemName ("Core.SysDataItem.DefaultComponentName")
	public String getDefaultComponentName() {
		return defaultComponentName;
	}

	/**
	 * @param defaultComponentName The defaultComponentName to set.
	 */
	public void setDefaultComponentName(String defaultComponentName) {
		this.defaultComponentName = defaultComponentName;
	}

	/**
	 * @return Returns the description.
	 */
	@DataItemName ("Core.Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the domain.
	 */
	public SysDomain getSysDomain() {
		return sysDomain;
	}

	/**
	 * @param domain The domain to set.
	 */
	public void setSysDomain(SysDomain domain) {
		this.sysDomain = domain;
	}

	/**
	 * @return the referenceDataItemName
	 */
	@DataItemName ("Core.SysDataItem.ReferenceDataItemName")
	public String getReferenceDataItemName() {
		return referenceDataItemName;
	}

	/**
	 * @param referenceDataItemName the referenceDataItemName to set
	 */
	public void setReferenceDataItemName(String referenceDataItemName) {
		this.referenceDataItemName = referenceDataItemName;
	}

	/**
	 * @return Returns the id.
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the longLabel.
	 */
	@DataItemName ("Core.SysDataItem.LongLabel")
	public String getLongLabel() {
		return longLabel;
	}

	/**
	 * @param longLabel The longLabel to set.
	 */
	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}

	/**
	 * @return Returns the name.
	 */
	@DataItemName ("Core.SysDataItem.Name")
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the searchHelpName.
	 */
	@DataItemName ("Core.SysDataItem.SearchHelpName")
	public String getSearchHelpName() {
		return searchHelpName;
	}

	/**
	 * @param searchHelpName The searchHelpName to set.
	 */
	public void setSearchHelpName(String searchHelp) {
		this.searchHelpName = searchHelp;
	}

	/**
	 * @return Returns the entityPropertyText.
	 */
	@DataItemName ("Core.SysDataItem.EntityPropertyText")
	public String getEntityPropertyText() {
		return entityPropertyText;
	}

	/**
	 * @param entityPropertyText The entityPropertyText to set.
	 */
	public void setEntityPropertyText(String entityPropertyText) {
		this.entityPropertyText = entityPropertyText;
	}

	/**
	 * @return Returns the entityTextFormat.
	 */
	@DataItemName ("Core.SysDataItem.EntityTextFormat")
	public String getEntityTextFormat() {
		return entityTextFormat;
	}

	/**
	 * @param entityTextFormat The entityTextFormat to set.
	 */
	public void setEntityTextFormat(String entityTextFormat) {
		this.entityTextFormat = entityTextFormat;
	}

	/**
	 * @return Returns the isReadOnly.
	 */
	@DataItemName ("Core.SysDataItem.ReadOnly")
	public boolean isReadOnly() {
		return isReadOnly;
	}

	/**
	 * @param isReadOnly The isReadOnly to set.
	 */
	public void setReadOnly(boolean isNotNull) {
		this.isReadOnly = isNotNull;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getType()
	 */
	public DataItemKind getKind() {
		return dataItemKind;
	}

	/**
	 * @param dataItemType the dataItemType to set
	 */
	public void setKind(DataItemKind kind) {
		this.dataItemKind = kind;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabel()
	 */
	@DataItemName ("Core.SysDataItem.ShortLabel")
	public String getShortLabel() {
		return shortLabel;
	}

	/**
	 * @param shortLabel the shortLabel to set
	 */
	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabel(java.util.Locale)
	 */
	public String getShortLabel(Locale locale) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getShortLabelMaxLength()
	 */
	public int getShortLabelMaxLength() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabel()
	 */
	@DataItemName ("Core.SysDataItem.MediumLabel")
	public String getMediumLabel() {
		return mediumLabel;
	}

	/**
	 * @param mediumLabel the mediumLabel to set
	 */
	public void setMediumLabel(String mediumLabel) {
		this.mediumLabel = mediumLabel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabel(java.util.Locale)
	 */
	public String getMediumLabel(Locale locale) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getMediumLabelMaxLength()
	 */
	public int getMediumLabelMaxLength() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getLongLabel(java.util.Locale)
	 */
	public String getLongLabel(Locale locale) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getLongLabelMaxLength()
	 */
	public int getLongLabelMaxLength() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeader()
	 */
	@DataItemName ("Core.SysDataItem.Header")
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeader(java.util.Locale)
	 */
	public String getHeader(Locale locale) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getHeaderMaxLength()
	 */
	public int getHeaderMaxLength() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabel()
	 */
	@DataItemName ("Core.SysDataItem.ReportLabel")
	public String getReportLabel() {
		return reportLabel;
	}

	/**
	 * @param reportLabel the reportLabel to set
	 */
	public void setReportLabel(String reportLabel) {
		this.reportLabel = reportLabel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabel(java.util.Locale)
	 */
	public String getReportLabel(Locale locale) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getReportLabelMaxLength()
	 */
	public int getReportLabelMaxLength() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDocumentation()
	 */
	public DataItemDocumentation getDocumentation() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getSearchHelp()
	 */
	public SearchHelp getSearchHelp() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getEntityText()
	 */
	public EntityText getEntityText() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getAssignParameterID()
	 */
	@DataItemName ("Core.SysDataItem.AssignParameterName")
	public String getAssignParameterName() {
		return assignParameterName;
	}

	/**
	 * @param assignParameterName the assignParameterName to set
	 */
	public void setAssignParameterName(String assignParameterName) {
		this.assignParameterName = assignParameterName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.DataItem#getDomain()
	 */
	public com.mg.framework.api.metadata.Domain getDomain() {
		return sysDomain;
	}

	/**
	 * @return the applicationLayer
	 */
	public ApplicationLayer getApplicationLayer() {
		return applicationLayer;
	}

	/**
	 * @param applicationLayer the applicationLayer to set
	 */
	public void setApplicationLayer(ApplicationLayer applicationLayer) {
		this.applicationLayer = applicationLayer;
	}

}
