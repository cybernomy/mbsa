/*
 * Feature.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * Модель бизнес-компонента "Дополнительные признаки"
 * 
 * @version $Id: Feature.java,v 1.8 2007/11/08 12:02:38 sharapov Exp $
 */
@DataItemName("Core.Feature") //$NON-NLS-1$
public class Feature extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClass SysClass;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private DataKind DataType;

	private java.lang.Integer Priority;

	private java.lang.String NullValue;

	private java.lang.String Code;

	private boolean IsArray;
	
	private Short arraySize;

	private String classIds;
	
	private java.util.Set<com.mg.merp.core.model.FeatureLink> featureLinks;

	private java.util.Set<FeatureVal> featureValues;

	// Constructors

	/** default constructor */
	public Feature() {
	}

	/** constructor with id */
	public Feature(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName ("Core.Feature.SysClass") //$NON-NLS-1$
	public com.mg.merp.core.model.SysClass getSysClass() {
		return this.SysClass;
	}

	public void setSysClass(com.mg.merp.core.model.SysClass SysClass) {
		this.SysClass = SysClass;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName ("Reference.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	public DataKind getDataType() {
		return this.DataType;
	}

	public void setDataType(DataKind DataType) {
		this.DataType = DataType;
	}

	/**
	 * 
	 */
	@DataItemName ("Core.Feature.Priority") //$NON-NLS-1$
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	@DataItemName ("Core.Feature.NullValue") //$NON-NLS-1$
	public java.lang.String getNullValue() {
		return this.NullValue;
	}

	public void setNullValue(java.lang.String NullValue) {
		this.NullValue = NullValue;
	}

	/**
	 * 
	 */
	@DataItemName ("Reference.BigCode") //$NON-NLS-1$
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName ("Core.Feature.IsArray") //$NON-NLS-1$
	public boolean getIsArray() {
		return this.IsArray;
	}

	public void setIsArray(boolean IsArray) {
		this.IsArray = IsArray;
	}

	/**
	 * @return the arraySize
	 */
	@DataItemName ("Core.Feature.ArraySize") //$NON-NLS-1$
	public Short getArraySize() {
		return arraySize;
	}

	/**
	 * @param arraySize the arraySize to set
	 */
	public void setArraySize(Short arraySize) {
		this.arraySize = arraySize;
	}
	
	public String getClassIds() {
		if (classIds != null)
			return classIds;
		
		String result = ""; //$NON-NLS-1$
		java.util.Set<com.mg.merp.core.model.FeatureLink> links = getFeatureLinks();
		if (links == null)
			return ""; //$NON-NLS-1$
		for(com.mg.merp.core.model.FeatureLink link : links)
			if (link.getRecId() == null)
				result = result + link.getSysClass().getId() + ";"; //$NON-NLS-1$
		return result;
	}
	
	public void setClassIds(String classIds) {
		this.classIds = classIds;
		/*java.util.Set<com.mg.merp.reference.model.FeatureLink> links = getSetOfFeatureLink();
		if (links == null) {
			links = new java.util.HashSet<com.mg.merp.reference.model.FeatureLink>();
			setSetOfFeatureLink(links);
		}

		//prepare classes ids
		List<String> iDsList = StringUtil.split(classIds, ";");
		if (iDsList == null)
			iDsList = new ArrayList<String>();
		
		//find in old
		java.util.Set<com.mg.merp.reference.model.FeatureLink> notFinded = new java.util.HashSet<com.mg.merp.reference.model.FeatureLink>();
		for(com.mg.merp.reference.model.FeatureLink link : links) {
			if (iDsList.indexOf(String.valueOf(link.getId())) != -1)
				notFinded.add(link);
			else {
				com.mg.merp.reference.model.FeatureLink newLink = new com.mg.merp.reference.model.FeatureLink();
				//TODO initialize link
				links.add(newLink);
			}
		}
		links.removeAll(notFinded);*/
	}
	
	public String getClassNames() {
		String result = ""; //$NON-NLS-1$
		java.util.Set<com.mg.merp.core.model.FeatureLink> links = getFeatureLinks();
		if (links == null)
			return ""; //$NON-NLS-1$
		for(com.mg.merp.core.model.FeatureLink link : links)
			if (link.getRecId() == null)
				result = result + link.getSysClass().getDescription() + ";"; //$NON-NLS-1$
		return result;
	}
	
	public void setClassNames(String classIds) {
		
	}

	/**
	 * @return the featureLinks
	 */
	public java.util.Set<com.mg.merp.core.model.FeatureLink> getFeatureLinks() {
		return this.featureLinks;
	}

	/**
	 * @param featureLinks the featureLinks to set
	 */
	public void setFeatureLinks(
			java.util.Set<com.mg.merp.core.model.FeatureLink> featureLinks) {
		this.featureLinks = featureLinks;
	}

	/**
	 * @return the featureValues
	 */
	public java.util.Set<FeatureVal> getFeatureValues() {
		return this.featureValues;
	}

	/**
	 * @param featureValues the featureValues to set
	 */
	public void setFeatureValues(java.util.Set<FeatureVal> featureValues) {
		this.featureValues = featureValues;
	}
	
}