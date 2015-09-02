/*
 * DocFlowStageServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.docprocess.model.ActionUserGrant;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.DocProcessStageRights;
import com.mg.merp.document.model.DocType;
import com.mg.merp.security.model.Groups;

/**
 * ������-��������� "���� ����������������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowStageServiceLocal.java,v 1.2 2006/10/18 10:23:13 safonov Exp $
 */
public interface DocFlowStageServiceLocal extends DataBusinessObjectService<DocProcessStage, Integer> {

	/**
	 * ������ ����� �� ������ � �� ������ �������������
	 * 
	 * @param stage		���� ��
	 * @param groups	������ �������������
	 * @param grant		����� �� ���� ��
	 */
	void grantRightsForGroups(DocProcessStage stage, Groups[] groups, ActionUserGrant grant);
	
	/**
	 * ������� ����� �� ������ � ��
	 * 
	 * @param rights	����� �� ����
	 */
	void revokeRights(DocProcessStageRights[] rights);

	/**
	 * �������� ������������ ����� �� ������ � ��
	 * 
	 * @param rights	������������ ����� �� ����
	 * @param grant		����� �����
	 */
	void grantRights(DocProcessStageRights[] rights, ActionUserGrant grant);
	
	/**
	 * ���������������� ��������� �� ��� ���� ���������
	 * 
	 * @param docType	��� ���������
	 */
	void initializeDocFlow(DocType docType);
	
}
