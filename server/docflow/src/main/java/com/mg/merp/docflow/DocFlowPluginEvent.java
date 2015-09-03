/*
 * DocFlowPluginEvent.java
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
package com.mg.merp.docflow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

/**
 * Событие выполнения этапа ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginEvent.java,v 1.2 2008/06/09 11:35:28 safonov Exp $
 */
public class DocFlowPluginEvent extends EventObject {
	private Date processDate;
	private BigDecimal performedSum;
	private Integer data1;
	private Integer data2;
	private Serializable data;
	private List<DocumentSpecItem> specList;

	public DocFlowPluginEvent(DocFlowPlugin source, Date processDate, BigDecimal performedSum, Integer data1, Integer data2, Serializable data, List<DocumentSpecItem> specList) {
		super(source);
		this.processDate = processDate;
		this.performedSum = performedSum;
		this.data1 = data1;
		this.data2 = data2;
		this.data = data;
		this.specList = specList;
	}

	/**
	 * @return the processDate
	 */
	public Date getProcessDate() {
		return processDate;
	}

	public BigDecimal getPerformedSum() {
		return performedSum;
	}
	
	public Integer getData1() {
		return data1;
	}

	public Integer getData2() {
		return data2;
	}

	/**
	 * @return Returns the data.
	 */
	public Serializable getData() {
		return data;
	}

	/**
	 * @return Returns the specList.
	 */
	public List<DocumentSpecItem> getSpecList() {
		return specList;
	}

}
