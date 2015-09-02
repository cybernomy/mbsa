/*
 * SalaryAlgorithmContext.java
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
package com.mg.merp.salary.support;

import java.util.Date;

import com.mg.framework.api.ApplicationException;

/**
 * ����� ��������� ���������� ������� �������� 
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SalaryAlgorithmContext {
	
	/**
	 * 
	 * @return	������������� �������� �/�
	 * @throws ApplicationException
	 */
	final public int getStaffListId() throws ApplicationException {
		//return nativeGetStaffListId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ������� �/� �� �/� ����������
	 * @throws ApplicationException
	 */
	final public int getFeeModelId() throws ApplicationException {
		//return nativeGetFeeModelId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ������� �/�
	 * @throws ApplicationException
	 */
	final public int getPayRollId() throws ApplicationException {
		//return nativeGetPayRollId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� �������� �/�
	 * @throws ApplicationException
	 */
	final public int getCalcListId() throws ApplicationException {
		//return nativeGetCalcListId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� �������� �/�
	 * @throws ApplicationException
	 */
	final public int getCalcListFeeId() throws ApplicationException {
		//return nativeGetCalcListFeeId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ���������� ���������, �� ������� ������������ ������
	 * @throws ApplicationException
	 */
	final public int getPositionFillId() throws ApplicationException {
		//return nativeGetPositionFillId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� �/� ����������
	 * @throws ApplicationException
	 */
	final public int getPersonalAccountId() throws ApplicationException {
		//return nativeGetPersonalAccountId(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	��������� �
	 * @throws ApplicationException
	 */
	final public Date getBeginDate() throws ApplicationException {
		//return new Date(nativeGetBeginDate(handle));
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @return	��������� ��
	 * @throws ApplicationException
	 */
	final public Date getEndDate() throws ApplicationException {
		//return new Date(nativeGetEndDate(handle));
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @return	�� ������ �
	 * @throws ApplicationException
	 */
	final public Date getPeriodBeginDate() throws ApplicationException {
		//return new Date(nativeGetPeriodBeginDate(handle));
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @return	�� ������ ��
	 * @throws ApplicationException
	 */
	final public Date getPeriodEndDate() throws ApplicationException {
		//return new Date(nativeGetPeriodEndDate(handle));
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @return	������������� ��������� ������� ������ 1-�� ������
	 * @throws ApplicationException
	 */
	final public int getCostsAnl1Id() throws ApplicationException {
		//return nativeGetCostsAnl1Id(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ��������� ������� ������ 2-�� ������
	 * @throws ApplicationException
	 */
	final public int getCostsAnl2Id() throws ApplicationException {
		//return nativeGetCostsAnl2Id(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ��������� ������� ������ 3-�� ������
	 * @throws ApplicationException
	 */
	final public int getCostsAnl3Id() throws ApplicationException {
		//return nativeGetCostsAnl3Id(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ��������� ������� ������ 4-�� ������
	 * @throws ApplicationException
	 */
	final public int getCostsAnl4Id() throws ApplicationException {
		//return nativeGetCostsAnl4Id(handle);
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @return	������������� ��������� ������� ������ 5-�� ������
	 * @throws ApplicationException
	 */
	final public int getCostsAnl5Id() throws ApplicationException {
		//return nativeGetCostsAnl5Id(handle);
		//TODO
		return 0;
	}
	
	/**
	 * �������� ��������� ������� ������
	 * 
	 * @return	<code>true</code> ���� ��� ���� ��������� ������� ������ = 0
	 * @throws ApplicationException
	 */
	final public boolean getIsEmptyCostsAnl() throws ApplicationException {
		//return nativeGetIsEmptyCostsAnl(handle);
		//TODO
		return false;
	}
	
}
