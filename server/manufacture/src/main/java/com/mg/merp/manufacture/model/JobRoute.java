/*
 * JobRoute.java
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
package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: JobRoute.java,v 1.8 2007/07/30 10:27:49 safonov Exp $
 */
@DataItemName("Manufacture.JobRoute")
public class JobRoute extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.CostDetail ActCostDetail;

	private com.mg.merp.reference.model.Measure schedOffSetTimeUM;

	private com.mg.merp.reference.model.Measure setupTimeUM;

	private com.mg.merp.reference.model.Measure moveTimeUM;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.CostDetail StdCostDetail;

	private com.mg.merp.reference.model.Measure schedTimeUM;

	private com.mg.merp.mfreference.model.WorkCenter workCenter;

	private com.mg.merp.reference.model.Measure runTimeUM;

	private com.mg.merp.manufacture.model.Job Job;

	private com.mg.merp.reference.model.Measure queueTimeUM;

	private java.lang.Integer OperNum;

	private java.lang.String Description;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	private boolean CompleteFlag;

	private java.math.BigDecimal EfficiencyFactor;

	private java.util.Date StartDate;

	private java.util.Date EndDate;

	private java.lang.Long startTick;

	private java.lang.Long endTick;

	private java.lang.Long moveTicks;

	private java.lang.Long setupTicks;

	private java.lang.Long runTicks;

	private java.lang.Long schedTicks;

	private java.lang.Long schedOffsetTicks;

	private java.lang.Long queueTicks;

	private boolean FreezeScheduleFlag;

	private java.math.BigDecimal QtyReceived;

	private java.math.BigDecimal QtyComplete;

	private java.math.BigDecimal QtyScrapped;

	private java.math.BigDecimal QtyMoved;

	private boolean ControlPointFlag;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public JobRoute() {
	}

	/** constructor with id */
	public JobRoute(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.CostDetail getActCostDetail() {
		return this.ActCostDetail;
	}

	public void setActCostDetail(
			com.mg.merp.mfreference.model.CostDetail MfCostDetail) {
		this.ActCostDetail = MfCostDetail;
	}

	/**
	 * 
	 */	
 	@DataItemName("Manufacture.JobRoute.SchedOffSetTimeUm")
	public com.mg.merp.reference.model.Measure getSchedOffSetTimeUM() {
		return this.schedOffSetTimeUM;
	}

	public void setSchedOffSetTimeUM(com.mg.merp.reference.model.Measure Measure) {
		this.schedOffSetTimeUM = Measure;
	}

	/**
	 * 
	 */	
	@DataItemName("Manufacture.JobRoute.SetupTimeUm")	 
	public com.mg.merp.reference.model.Measure getSetupTimeUM() {
		return this.setupTimeUM;
	}

	public void setSetupTimeUM(com.mg.merp.reference.model.Measure Measure_1) {
		this.setupTimeUM = Measure_1;
	}

	/**
	 * 
	 */	
	@DataItemName("Manufacture.JobRoute.MoveTimeUm")	 
	public com.mg.merp.reference.model.Measure getMoveTimeUM() {
		return this.moveTimeUM;
	}

	public void setMoveTimeUM(com.mg.merp.reference.model.Measure Measure_2) {
		this.moveTimeUM = Measure_2;
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

	public com.mg.merp.mfreference.model.CostDetail getStdCostDetail() {
		return this.StdCostDetail;
	}

	public void setStdCostDetail(
			com.mg.merp.mfreference.model.CostDetail MfCostDetail_1) {
		this.StdCostDetail = MfCostDetail_1;
	}

	/**
	 * 
	 */	
	@DataItemName("Manufacture.JobRoute.SchedTimeUm")	 
	public com.mg.merp.reference.model.Measure getSchedTimeUM() {
		return this.schedTimeUM;
	}

	public void setSchedTimeUM(com.mg.merp.reference.model.Measure Measure_3) {
		this.schedTimeUM = Measure_3;
	}

	/**
	 * 
	 */	
	public com.mg.merp.mfreference.model.WorkCenter getWorkCenter() {
		return this.workCenter;
	}

	public void setWorkCenter(com.mg.merp.mfreference.model.WorkCenter MfWorkCenter) {
		this.workCenter = MfWorkCenter;
	}

	/**
	 * 
	 */	
	@DataItemName("Manufacture.JobRoute.RunTimeUm")	 
	public com.mg.merp.reference.model.Measure getRunTimeUM() {
		return this.runTimeUM;
	}

	public void setRunTimeUM(com.mg.merp.reference.model.Measure Measure_4) {
		this.runTimeUM = Measure_4;
	}

	/**
	 * 
	 */

	public com.mg.merp.manufacture.model.Job getJob() {
		return this.Job;
	}

	public void setJob(com.mg.merp.manufacture.model.Job MfJob) {
		this.Job = MfJob;
	}

	/**
	 * 
	 */	
	@DataItemName("Manufacture.JobRoute.QueueTimeUm")	 
	public com.mg.merp.reference.model.Measure getQueueTimeUM() {
		return this.queueTimeUM;
	}

	public void setQueueTimeUM(com.mg.merp.reference.model.Measure Measure_5) {
		this.queueTimeUM = Measure_5;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.OperNum")
	public java.lang.Integer getOperNum() {
		return this.OperNum;
	}

	public void setOperNum(java.lang.Integer OperNum) {
		this.OperNum = OperNum;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.Description")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.CompleteFlag")
	public boolean getCompleteFlag() {
		return this.CompleteFlag;
	}

	public void setCompleteFlag(boolean CompleteFlag) {
		this.CompleteFlag = CompleteFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.EfficiencyFactor")
	public java.math.BigDecimal getEfficiencyFactor() {
		return this.EfficiencyFactor;
	}

	public void setEfficiencyFactor(java.math.BigDecimal EfficiencyFactor) {
		this.EfficiencyFactor = EfficiencyFactor;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.StartDate")
	public java.util.Date getStartDate() {
		return this.StartDate;
	}

	public void setStartDate(java.util.Date StartDate) {
		this.StartDate = StartDate;
	}

	/**
	 * 
	 */

	@DataItemName("Manufacture.JobRoute.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date EndDate) {
		this.EndDate = EndDate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.StartTick")
	public java.lang.Long getStartTick() {
		return this.startTick;
	}

	public void setStartTick(java.lang.Long StartTick) {
		this.startTick = StartTick;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.EndTick")
	public java.lang.Long getEndTick() {
		return this.endTick;
	}

	public void setEndTick(java.lang.Long EndTick) {
		this.endTick = EndTick;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.MoveTicks")
	public java.lang.Long getMoveTicks() {
		return this.moveTicks;
	}

	public void setMoveTicks(java.lang.Long MoveTicks) {
		this.moveTicks = MoveTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.SetupTicks")
	public java.lang.Long getSetupTicks() {
		return this.setupTicks;
	}

	public void setSetupTicks(java.lang.Long SetupTicks) {
		this.setupTicks = SetupTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.RunTicks")
	public java.lang.Long getRunTicks() {
		return this.runTicks;
	}

	public void setRunTicks(java.lang.Long RunTicks) {
		this.runTicks = RunTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.SchedTicks")
	public java.lang.Long getSchedTicks() {
		return this.schedTicks;
	}

	public void setSchedTicks(java.lang.Long SchedTicks) {
		this.schedTicks = SchedTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.SchedOffsetTicks")
	public java.lang.Long getSchedOffsetTicks() {
		return this.schedOffsetTicks;
	}

	public void setSchedOffsetTicks(java.lang.Long SchedOffsetTicks) {
		this.schedOffsetTicks = SchedOffsetTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.QueueTicks")
	public java.lang.Long getQueueTicks() {
		return this.queueTicks;
	}

	public void setQueueTicks(java.lang.Long QueueTicks) {
		this.queueTicks = QueueTicks;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.FreezeScheduleFlag")
	public boolean getFreezeScheduleFlag() {
		return this.FreezeScheduleFlag;
	}

	public void setFreezeScheduleFlag(boolean FreezeScheduleFlag) {
		this.FreezeScheduleFlag = FreezeScheduleFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.QtyReceived")
	public java.math.BigDecimal getQtyReceived() {
		return this.QtyReceived;
	}

	public void setQtyReceived(java.math.BigDecimal QtyReceived) {
		this.QtyReceived = QtyReceived;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.QtyComplete")
	public java.math.BigDecimal getQtyComplete() {
		return this.QtyComplete;
	}

	public void setQtyComplete(java.math.BigDecimal QtyComplete) {
		this.QtyComplete = QtyComplete;
	}

	/**
	 * 
	 */

	@DataItemName("Manufacture.JobRoute.QtyScrapped")
	public java.math.BigDecimal getQtyScrapped() {
		return this.QtyScrapped;
	}

	public void setQtyScrapped(java.math.BigDecimal QtyScrapped) {
		this.QtyScrapped = QtyScrapped;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.QtyMoved")
	public java.math.BigDecimal getQtyMoved() {
		return this.QtyMoved;
	}

	public void setQtyMoved(java.math.BigDecimal QtyMoved) {
		this.QtyMoved = QtyMoved;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.ControlPointFlag")
	public boolean getControlPointFlag() {
		return this.ControlPointFlag;
	}

	public void setControlPointFlag(boolean ControlPointFlag) {
		this.ControlPointFlag = ControlPointFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobRoute.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}
}