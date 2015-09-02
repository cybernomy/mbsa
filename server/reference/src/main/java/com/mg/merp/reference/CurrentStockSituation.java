/*
 * CurrentStockSituation.java
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
package com.mg.merp.reference;

import java.io.Serializable;
import java.util.List;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;


/**
 * ������ �������� ���������� �� �������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituation.java,v 1.4 2008/09/09 13:33:54 sharapov Exp $
 */
public interface CurrentStockSituation {
	String SERVICE_NAME = "merp:warehouse=CurrentStockSituationService";
	
	/**
	 * ������� ���������� ������� �������� <code>catalog</code> 
	 * �� ������ <code>warehouse</code> � ��� <code>mol</code> 
	 * 
	 * @param warehouse
	 * 			�����
	 * @param mol
	 * 			���
	 * @param catalog
	 * 			������� ��������
	 * @return ���������� �� ������ ��� <code>null</code> ���� �� ������ ��� ��������� ���������
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
		Catalog catalog);

	/**
	 * ������� ���������� ������� �������� <code>catalog</code> 
	 * �� ������ <code>warehouse</code> � ��� <code>mol</code> 
	 * 
	 * @param warehouse
	 * 			�����
	 * @param mol
	 * 			���
	 * @param catalog
	 * 			������� ��������
	 * @param onlyAvailable
	 * 			������ ������ ��������� ��� �������� ������������
	 * @return ���������� �� ������ ��� <code>null</code> ���� �� ������ ��� ��������� ���������
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
		Catalog catalog, boolean onlyAvailable);

	/**
	 * ������� ���������� ������� �������� <code>catalog</code> 
	 * �� ������ <code>warehouse</code>
	 * 
	 * @param warehouse	�����
	 * @param catalog	������� ��������
	 * @return	���������� �� ������ ��� <code>null</code> ���� �� ������ ��� ��������� ���������
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog);

	/**
	 * ������� ���������� ������� �������� <code>catalog</code> 
	 * �� ������ <code>warehouse</code>
	 * 
	 * @param warehouse	�����
	 * @param catalog	������� ��������
	 * @param onlyAvailable ������ ������ ��������� ��� �������� ������������
	 * @return	���������� �� ������ ��� <code>null</code> ���� �� ������ ��� ��������� ���������
	 */
	StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog, boolean onlyAvailable);

	/**
	 * ������� ���������� �� ���� �������
	 * 
	 * @return ���������� �� �������
	 */
	List<StockSituationValues> getSituation(Catalog catalog);
	
	/**
	 * ������� ��������������� ���������� ������� �������� �� ���� ������� 
	 * @param catalogId - ������������� ������� ��������
	 * @return ���������� �� ���� �������
	 */
	StockSituationValues getAgregateSituation(Integer catalogId);
	
	/**
	 * ������� ��������������� ���������� ������� �������� �� ���� ������� 
	 * @param catalog - ������� ��������
	 * @return ���������� �� ���� �������
	 */
	StockSituationValues getAgregateSituation(Catalog catalog);
		
	/**
	 * ����� �����, ������������ ��������� ���������� �� �������
	 *  ��� ������� �������� <code>catalog</code>
	 * 
	 * @param catalog
	 * 		������� ��������
	 */
	void showSituationForm(Catalog catalog);

	/**
	 * ����� ����, ������������ ��������� ���������� �� �������
	 * ��� ������� ��������
	 * 
	 * @param catalogIds	������ ��������������� ��������
	 */
	void showSituationForm(Serializable[] catalogIds);

}
