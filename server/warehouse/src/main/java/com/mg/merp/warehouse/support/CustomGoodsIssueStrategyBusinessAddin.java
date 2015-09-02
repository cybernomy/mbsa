/*
 * CustomGoodsIssueStrategyBusinessAddin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.warehouse.GoodsIssueStrategy;

/**
 * ������-���������� "���������������� ��������� �������� �������� ������� �� ������".
 * ����� ��������� ������ ������������� ��������� ����� 
 * <code>protected GoodsIssueStrategy createGoodsIssueStrategy()</code>.
 * ����� ���������� ��������� �������� �������� ������� �� ������.
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CustomGoodsIssueStrategyBusinessAddin.java,v 1.1 2007/09/04 13:09:28 safonov Exp $
 */
public abstract class CustomGoodsIssueStrategyBusinessAddin extends AbstractBusinessAddin<GoodsIssueStrategy> {

	@Override
	protected final void doPerform() throws Exception {
		complete(createGoodsIssueStrategy());
	}

	/**
	 * 
	 * @return ��������� ��������
	 */
	public abstract GoodsIssueStrategy createGoodsIssueStrategy();

}
