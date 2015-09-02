/*
 * PriceListAlgorithm.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support;


/**
 * ������� ����� ��������� ������� ���� �����-�����. ����� ��������� ������
 * ������������� ��������� ����� <code>protected void doPerform() throws Exception</code>.
 * ����� ���������� ����������� ���� �����-�����.
 * 
 * <p>������ ������� ������:
 * <pre>
 * protected void doPerform() throws Exception {
 *     //�������� ������� ���� �� 2
 *     complete(getPriceListSpec().getPrice().multiply(new BigDecimal(2.0)));
 * }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: PriceListAlgorithm.java,v 1.9 2007/09/04 12:37:32 safonov Exp $
 * @deprecated ����������� {@link PriceListBusinessAddin}
 */
@Deprecated
public abstract class PriceListAlgorithm extends PriceListBusinessAddin {

}
