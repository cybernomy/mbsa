/*
 * DiscountAlgorithm.java
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
package com.mg.merp.discount.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * ������� ����� BAi ������� ������/�������. ����� ��������� ������
 * ������������� ��������� ����� <code>protected Double doPerform() throws Exception</code>
 * ������� ���������� ������������ ����� - ���������� �������� ������/�������.
 * ��� �������� ����� ���������� � ���� ������������ ��� ��������� �������� ����.
 * 
 * <p>��������, ����� ���� 20% ������ �� ����, ����� ������� ��������� ��������:
 * <pre>
 * protected Double doPerform() throws Exception {
 *     complete(getSpecificationParam("Price") * (-0.2));
 * }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: DiscountAlgorithm.java,v 1.4 2007/09/07 12:02:18 safonov Exp $
 * @deprecated ����������� {@link DiscountBusinessAddin}
 */
@Deprecated
public abstract class DiscountAlgorithm extends AbstractBusinessAddin<Double> {

	/**
	 * ���������� ��������, �������� ������������� � ���� "������/������� �� ��������"
	 * 
	 * @return	�������� ������/�������
	 * @throws ApplicationException
	 */
	final public double getDiscountOnDoc() throws ApplicationException {
		//return nativeGetDiscountOnDoc(handle);
		//TODO
		return 0;
	}
	
}
