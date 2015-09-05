/*
 * BomRoute.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: BomRoute.java,v 1.6 2009/03/05 12:30:14 safonov Exp $
 */
public class BomRoute extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.Bom Bom;

	private com.mg.merp.reference.model.Measure schedOffSetTimeUM;

	private com.mg.merp.reference.model.Measure setupTimeUM;

	private com.mg.merp.reference.model.Measure moveTimeUM;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.CostDetail standartCostDetail;

	private com.mg.merp.reference.model.Measure schedTimeUM;

	private com.mg.merp.mfreference.model.WorkCenter WorkCenter;

	private com.mg.merp.reference.model.Measure runTimeUM;

	private com.mg.merp.reference.model.Measure queueTimeUM;

	private java.lang.Integer OperNum;

	private java.lang.String Description;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	private java.math.BigDecimal Efficiency;

	private java.lang.Long moveTicks;

	private java.lang.Long setupTicks;

	private java.lang.Long runTicks;

	private java.lang.Long schedTicks;

	private java.lang.Long schedOffsetTicks;

	private java.lang.Long queueTicks;

	private boolean ControlPointFlag;

	private java.lang.String Comment;

	private java.util.Set<BomRouteResource> routeResources;

	// Constructors

	/** default constructor */
	public BomRoute() {
	}

	/** constructor with id */
	public BomRoute(java.lang.Integer Id) {
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

	public com.mg.merp.mfreference.model.Bom getBom() {
		return this.Bom;
	}

	public void setBom(com.mg.merp.mfreference.model.Bom Bom) {
		this.Bom = Bom;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SchedOffSetTimeUm")
	public com.mg.merp.reference.model.Measure getSchedOffSetTimeUM() {
		return this.schedOffSetTimeUM;
	}

	public void setSchedOffSetTimeUM(
			com.mg.merp.reference.model.Measure SchedOffSetTimeUm) {
		this.schedOffSetTimeUM = SchedOffSetTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SetupTimeUm")
	public com.mg.merp.reference.model.Measure getSetupTimeUM() {
		return this.setupTimeUM;
	}

	public void setSetupTimeUM(com.mg.merp.reference.model.Measure SetupTimeUm) {
		this.setupTimeUM = SetupTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.MoveTimeUm")
	public com.mg.merp.reference.model.Measure getMoveTimeUM() {
		return this.moveTimeUM;
	}

	public void setMoveTimeUM(com.mg.merp.reference.model.Measure MoveTimeUm) {
		this.moveTimeUM = MoveTimeUm;
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

	public com.mg.merp.mfreference.model.CostDetail getStandartCostDetail() {
		return this.standartCostDetail;
	}

	public void setStandartCostDetail(
			com.mg.merp.mfreference.model.CostDetail CostDetail) {
		this.standartCostDetail = CostDetail;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SchedTimeUm")
	public com.mg.merp.reference.model.Measure getSchedTimeUM() {
		return this.schedTimeUM;
	}

	public void setSchedTimeUM(com.mg.merp.reference.model.Measure SchedTimeUm) {
		this.schedTimeUM = SchedTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.WorkCenter")
	public com.mg.merp.mfreference.model.WorkCenter getWorkCenter() {
		return this.WorkCenter;
	}

	public void setWorkCenter(
			com.mg.merp.mfreference.model.WorkCenter WorkCenter) {
		this.WorkCenter = WorkCenter;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.RunTimeUm")
	public com.mg.merp.reference.model.Measure getRunTimeUM() {
		return this.runTimeUM;
	}

	public void setRunTimeUM(com.mg.merp.reference.model.Measure RunTimeUm) {
		this.runTimeUM = RunTimeUm;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomRoute.QueueTimeUm")
	public com.mg.merp.reference.model.Measure getQueueTimeUM() {
		return this.queueTimeUM;
	}

	public void setQueueTimeUM(com.mg.merp.reference.model.Measure QueueTimeUm) {
		this.queueTimeUM = QueueTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.OperNum")
	public java.lang.Integer getOperNum() {
		return this.OperNum;
	}

	public void setOperNum(java.lang.Integer OperNum) {
		this.OperNum = OperNum;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.Description")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.Efficiency")
	public java.math.BigDecimal getEfficiency() {
		return this.Efficiency;
	}

	public void setEfficiency(java.math.BigDecimal Efficiency) {
		this.Efficiency = Efficiency;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.MoveTicks")
	public java.lang.Long getMoveTicks() {
		return this.moveTicks;
	}

	public void setMoveTicks(java.lang.Long MoveTicks) {
		this.moveTicks = MoveTicks;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SetupTicks")
	public java.lang.Long getSetupTicks() {
		return this.setupTicks;
	}

	public void setSetupTicks(java.lang.Long SetupTicks) {
		this.setupTicks = SetupTicks;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.RunTicks")
	public java.lang.Long getRunTicks() {
		return this.runTicks;
	}

	public void setRunTicks(java.lang.Long RunTicks) {
		this.runTicks = RunTicks;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SchedTicks")
	public java.lang.Long getSchedTicks() {
		return this.schedTicks;
	}

	public void setSchedTicks(java.lang.Long SchedTicks) {
		this.schedTicks = SchedTicks;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.SchedOffsetTicks")
	public java.lang.Long getSchedOffsetTicks() {
		return this.schedOffsetTicks;
	}

	public void setSchedOffsetTicks(java.lang.Long SchedOffsetTicks) {
		this.schedOffsetTicks = SchedOffsetTicks;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.QueueTicks")
	public java.lang.Long getQueueTicks() {
		return this.queueTicks;
	}

	public void setQueueTicks(java.lang.Long QueueTicks) {
		this.queueTicks = QueueTicks;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomRoute.ControlPointFlag")
	public boolean getControlPointFlag() {
		return this.ControlPointFlag;
	}

	public void setControlPointFlag(boolean ControlPointFlag) {
		this.ControlPointFlag = ControlPointFlag;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomRoute.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */

	public java.util.Set<BomRouteResource> getRouteResources() {
		return this.routeResources;
	}

	public void setRouteResources(java.util.Set<BomRouteResource> routeResources) {
		this.routeResources = routeResources;
	}

}