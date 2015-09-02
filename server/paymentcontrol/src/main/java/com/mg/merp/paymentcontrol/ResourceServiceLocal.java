/*
 * ResourceServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TurnResult;

/**
 * ������ ������-���������� "�������� �������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ResourceServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface ResourceServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PmcResource, Integer> {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Resource"; //$NON-NLS-1$
	
	/**
	 * ��� ����� ��� �������� �������
	 */
	final static short FOLDER_PART = 13400;
	
	/**
	 * �������� ������ �������� ������� �� �������� ����
	 * @param resourceId - ������������� �������� �������
	 * @param dateBalance - ���� �������
	 * @return ������
	 */
	BigDecimal getBalance(Integer resourceId, Date dateBalance);
	
	/**
	 * �������� ������ �����(�����) ������� �������
	 * @param parentId - ������������� ������������ ������(�����)
	 * @return ������ �����(�����) ������� �������
	 */
	List<Folder> getResurceGroups(Integer parentId);
	 
	/**
	 * �������� ������ ������� ������� ������������� � ������(�����)
	 * @param folderId - ������������� ������(�����)
	 * @return ������ ������� �������
	 */
	List<PmcResource> getResourcesByGroup(Integer folderId);
	
	/**
	 * �������� �������� �� �������� �������
	 * @param resourceId - ������������� �������� ������� 
	 * @param versionId - ������������� ������ ������������
	 * @param dateFrom - ������(���� �)
	 * @param dateTill - ������(���� ��)
	 * @return �������� �� �������� �������
	 */
	TurnResult getTurnByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill);
	
	/**
	 * �������� �������� �� ������(�����) �������� �������
	 * @param resourceFolderId - ������������� ������(�����) �������� �������
	 * @param versionId - ������������� ������ ������������
	 * @param dateFrom - ������(���� �)
	 * @param dateTill - ������(���� ��)
	 * @return �������� �� ������(�����) �������� �������
	 */
	TurnResult getTurnByResourceGroup(Integer resourceFolderId, Integer versionId, Date dateFrom, Date dateTill);
	
}
