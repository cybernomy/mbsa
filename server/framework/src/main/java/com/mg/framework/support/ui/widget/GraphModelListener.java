/*
 * GraphModelListener.java
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
package com.mg.framework.support.ui.widget;

import java.util.EventListener;

/**
 * ��������� ������ �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: GraphModelListener.java,v 1.2 2006/11/21 15:39:15 safonov Exp $
 */
public interface GraphModelListener extends EventListener {
	/**
	 * ���������� ������� �������� ���� �����
	 * 
	 * @param event	�������
	 */
	void vertexAdded(GraphModelEvent event);
	
	/**
	 * ���������� ������� ��������� ����� �����
	 * 
	 * @param event	�������
	 */
	void edgeAdded(GraphModelEvent event);
	
	/**
	 * ���������� ������� ������ ������� �����
	 * 
	 * @param event	�������
	 */
	void cellRemoved(GraphModelEvent event);
}
