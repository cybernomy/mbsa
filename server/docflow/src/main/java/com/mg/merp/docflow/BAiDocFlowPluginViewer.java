/**
 * BAiDocFlowPluginViewer.java
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
package com.mg.merp.docflow;

import java.io.Serializable;

import com.mg.merp.docprocess.model.DocHeadState;

/**
 * ����� ����������� ���������� ����� �� ������-����������� � UI
 * 
 * @author Oleg V. Safonov
 * @version $Id: BAiDocFlowPluginViewer.java,v 1.1 2007/09/27 09:21:05 safonov Exp $
 */
public interface BAiDocFlowPluginViewer extends Serializable {

	/**
	 * �������� ��������� ������������� ���������� ���������� ����� ��
	 * 
	 * @param docHeadState	��������� ��
	 * @return	��������� �������������
	 */
	String getDocActionResultTextRepresentation(DocHeadState docHeadState);
	
	/**
	 * �������� ��������� ���������� ����� �� (�������� ���� ���� ������ ��������, �� �����
	 * �������� ������ �������� � UI)
	 * 
	 * @param docHeadState	��������� ��
	 */
	void showDocActionResult(DocHeadState docHeadState);

}
