/*
 * MaintenanceConversationSession.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;

/**
 * ���������� ������, ������ ��� �������� ������������ ���������� � ��������
 * �������������� �������������� � �������������, �������� � �������� beforeOutput �
 * afterInput ��� ���� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceConversationSession.java,v 1.1 2006/10/26 13:19:12 safonov Exp $
 */
public interface MaintenanceConversationSession extends Serializable {

    /**
     * ���������� ������ ������������� ������� ����� ������������ 
     * 
     * @return  ������
     */
    DataBusinessObjectService getService();
    
    /**
     * �������� �������� ����������� �������������
     * 
     * @return
     */
    MaintenanceAction getMaintenanceAction();

    /**
     * �������� ������� ����������������� ����������
     * 
     * @param name	��� ��������
     * @return	������� ��� <code>null</code> ���� �� ������
     */
    Widget getWidget(String name);

    /**
     * �������� ������-��������
     * 
     * @return	������-��������
     */
    PersistentObject getEntity();
    
    /**
     * �������� �������� �������� ������
     * 
     * @param name	��� �������� ������
     * @return	��������
     */
    Object getModelAttribute(String name) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException;
    
    /**
     * ���������� �������� �������� ������
     * 
     * @param name	��� �������� ������
     * @param value	��������
     */
    void setModelAttribute(String name, Object value) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException;
    
    /**
     * �������� �������� �������� ���������� ������ �� �����
     * 
     * @param name  ������������ ��������
     * @return      �������� ��������
     */
    Serializable getAttribute(String name);
    
    /**
     * ������������� �������� �������� � ���������� ������
     * 
     * @param name  ������������ ��������
     * @param value �������� ��������
     */
    void setAttribute(String name, Serializable value);
}
