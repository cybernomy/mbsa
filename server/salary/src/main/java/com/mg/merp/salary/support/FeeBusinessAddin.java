/*
 * FeeBusinessAddin.java
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
 * ������� ����� ������-���������� ������� ����������/��������� ��.
 * 
 * <p>����� ���������� ������ ������������� ��������� �����: <code>protected void doPerform() throws Exception</code>.
 * <p>����� ���������� ��������� ���������� ������� ����������/��������� ��.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected void doPerform() throws Exception {
 *     	BigDecimal base = (BigDecimal) getParam("������_������");
 *		BigDecimal numberOfRates = (BigDecimal) getParam("���_������");
 *		BigDecimal percent = (BigDecimal) getParam("�������");
 *			
 *		CostsAnlResult costsAnls = new CostsAnlResult();
 *		if(isCostsAnlEmpty())
 *			costsAnls = getCostsAnlFromTariff("�����_������");
 *				
 *		complete(new FeeBAiResult(
 *				base.multiply(numberOfRates).multiply(percent).divide(new BigDecimal(100)),
 *				null,
 *				null,
 *				null,
 *				null,
 *				costsAnls.getCostsAnl1(),
 *				costsAnls.getCostsAnl2(),
 *				costsAnls.getCostsAnl3(),
 *				costsAnls.getCostsAnl4(),
 *				costsAnls.getCostsAnl5()));
 * }
 * </pre>
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeBusinessAddin.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public abstract class FeeBusinessAddin extends SalaryBusinessAddin<FeeBAiResult> {

}
