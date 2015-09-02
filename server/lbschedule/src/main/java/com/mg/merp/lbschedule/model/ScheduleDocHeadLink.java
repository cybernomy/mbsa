/*
 * ScheduleDocHeadLink.java
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
package com.mg.merp.lbschedule.model;

import java.io.Serializable;

import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;

/**
 * Модель бизнес-компонента "Связь документа с графиком исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleDocHeadLink.java,v 1.1 2007/04/21 11:49:33 sharapov Exp $
 */
public class ScheduleDocHeadLink extends PersistentObjectHibernate implements Serializable {

	// Fields

	private Integer id;
	private Schedule schedule;
	private DocHead docHead;
	private SysClient sysClient;

	// Constructors
	
	public ScheduleDocHeadLink() {
	}
	
	public ScheduleDocHeadLink(Integer id) {
		this.id = id;
	}
	
	// Property accessors
	
	/**
	 * @return the docHead
	 */
	public DocHead getDocHead() {
		return docHead;
	}
	
	/**
	 * @param docHead the docHead to set
	 */
	public void setDocHead(DocHead docHead) {
		this.docHead = docHead;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	/**
	 * @return the sysClient
	 */
	public SysClient getSysClient() {
		return sysClient;
	}
	
	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(SysClient sysClient) {
		this.sysClient = sysClient;
	}
	
}
