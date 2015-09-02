/*
 * FeeParamBusinessAddin.java
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
package com.mg.merp.salary.support;


/**
 * ������� ����� ������-���������� ������� ���������� ����������/���������.
 * 
 * <p>����� ���������� ������ ������������� ��������� �����: <code>protected void doPerform() throws Exception</code>.
 * <p>����� ���������� ��������� ���������� ������� ���������� ����������/���������.
 * 
 * <p>������ ������� ������:
 * <pre>protected void doPerform() throws Exception {
 * 	complete(getNumberOfRates(getPositionFill()));
 * }
 * </pre>
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeParamBusinessAddin.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public abstract class FeeParamBusinessAddin extends SalaryBusinessAddin<Object> {

}
