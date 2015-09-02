/*
 * PhaseFactItemServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.FactItemData;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.document.model.DocHead;

/**
 * ������ ������-���������� "����������� ����� ���������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseFactItemServiceLocal.java,v 1.4 2008/03/11 08:54:50 sharapov Exp $
 */
public interface PhaseFactItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PhaseFactItem, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/contract/PhaseFactItem"; //$NON-NLS-1$
	
	/**
	 * ���������� ��������� ������������ ������ ���������
	 * @param phaseFactItem - ����������� ����� ���������
	 */
	void adjust(PhaseFactItem phaseFactItem);
	
	/**
	 * ������������ ���������� ����� �������������
	 * @param phaseFactItem - ����������� ����� ���������
	 */
	void autoDistributionFactSum(PhaseFactItem phaseFactItem);

	/**
	 * ������������ ���������� ����� �������
	 * @param data - ��������� ������ ��� ������������� ���������� �����
	 * @param factItemId - ������������� ������������ ������ ���������
	 */
	void manualDistributionFactSum(ManualDistributionData[] data, Integer factItemId);

	/**
	 * ������������ ������������� ����������� �����
	 * @param factItemId - ������������� ������������ ������ ���������
	 */
	void avoidDistributionFactSum(Integer factItemId);
	
	/**
	 * ������� ����������� ����� ���������
	 * @param factItemData - ������ ��� ��������
	 * @return ����������� ����� ���������
	 */
	PhaseFactItem createContractFactItem(FactItemData factItemData);

	/**
	 * ����� �������� ������������ ������ ���������
	 * @param docHead - ��������� ��������� ����������� ������ �� ��������
	 * @param contractFactItemId - ������������� ������������ ������ ���������
	 * @param data - �������������� �������
	 */
	void rollBackCreateContractFactItem(DocHead docHead, Integer contractFactItemId, Integer data);
	
}
