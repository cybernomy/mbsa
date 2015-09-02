/*
 * ShuttleListener.java
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

import java.io.Serializable;
import java.util.EventListener;

import com.mg.framework.api.ui.ShuttleChangeEvent;

/**
 * ��������� ������� �������� "Shuttle", ������������ ��� �������� ������������
 * �� ������� ����������� ��������� ����� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleListener.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleListener extends EventListener, Serializable {

	/**
	 * ���������� ���������� �� ������ ��������� � ������ ��������
	 * 
	 * @param event	�������
	 */
	void shuttleContentsMoved(ShuttleChangeEvent event);

	/**
	 * ���������� ���������� �� ������ ��������� � ������ ���������
	 * 
	 * @param event	�������
	 */
	void shuttleContentsRemoved(ShuttleChangeEvent event);

}
