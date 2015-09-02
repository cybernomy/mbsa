/*
 * RescheduleFlag.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ������ ����������������
 * 
 * @author leonova
 * @version $Id: RescheduleFlag.java,v 1.1 2007/07/30 10:37:30 safonov Exp $
 */
@DataItemName ("Planning.RescheduleFlag")
public enum RescheduleFlag {
	/**
	 * ���������� �������� ���
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.RescheduleKind.MRPRescheduleSuggested")
	SUGGESTED,
	
	/**
	 * ���������������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.RescheduleKind.MRPReschedule")
	RESCHEDULE,
	
	/**
	 * ��������������. ��������������� ������ �� �����
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.RescheduleKind.MRPRescheduleWarning")	
	WARNING,
	
	/**
	 * ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.RescheduleKind.MRPRescheduleCancel")	
	CANCEL,
	
	/**
	 * ����������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.RescheduleKind.MRPRescheduleUnusedExpired")	
	UNUSED_EXPIRED

}