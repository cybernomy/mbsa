/*
 * CalcListServiceLocal.java
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
package com.mg.merp.salary;

import java.io.Serializable;

import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.salary.model.CalcList;

/**
 * ������ ������-���������� "��������� ������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListServiceLocal.java,v 1.2 2007/07/09 08:20:19 sharapov Exp $
 */
public interface CalcListServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcList, Integer> {

	/**
	 * ��� �������
	 */
	final static String LOCAL_SERVICE_NAME= "merp/salary/CalcList"; //$NON-NLS-1$
	
	/**
	 * �������� ��������� ������ � ��������� ���������
	 * @param positionFills - ������ ���������� ���������� ������������
	 * @param payRollId - ������������� ��������� ���������
	 */
	void addCalcLists(PositionFill[] positionFills, int payRollId);
	
	/**
	 * ����������
	 * @param calcListIds - ������ ��������������� ��������� �������
	 * @param isClear - ������� "�������" �.�. ����� ��������
	 */
	void calculate(Serializable[] calcListIds, boolean isClear);
		
	/**
	 * ����������/����� ������� "�������� � ������"
	 * @param calcListIds - ������ ��������������� ��������� �������
	 * @param isClosed - ������� "�������� � ������"
	 */
	void setClosed(Serializable[] calcListIds, boolean isClosed);

	public int getFromNextPeriod( int calcListId ) throws com.mg.framework.api.ApplicationException;

	public int getFromPrevPeriod( int calcListId ) throws com.mg.framework.api.ApplicationException;

	public int getFromAnotherPayRoll( int calcListId,int payRollId ) throws com.mg.framework.api.ApplicationException;

}
