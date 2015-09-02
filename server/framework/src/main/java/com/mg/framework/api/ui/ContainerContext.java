/**
 * ContainerContext.java
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
package com.mg.framework.api.ui;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ����� ���������� ���������� WEB ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ContainerContext.java,v 1.1 2007/11/09 11:50:39 safonov Exp $
 */
public interface ContainerContext {

	/**
	 * �������� HTTP ������
	 * 
	 * @return	HTTP ������
	 */
	HttpSession getHttpSession();
	
	/**
	 * �������� �������� ��������
	 * 
	 * @return	�������� ��������
	 */
	ServletContext getServletContext();
	
	/**
	 * �������� HTTP ������
	 * 
	 * @return	HTTP ������
	 */
	HttpServletRequest getServletRequest();

	/**
	 * �������� HTTP �����
	 * 
	 * @return	HTTP �����
	 */
	HttpServletResponse getServletResponse();
	
	/**
	 * �������� ������������ ��������
	 * 
	 * @return	������������ ��������
	 */
	ServletConfig getServletConfig();

	/**
	 * �������� URL web ������� 
	 * 
	 * @return	URL web �������
	 */
	String getServerURL();
	
	/**
	 * �������� URL web ����������
	 * 
	 * @return	URL web ����������
	 */
	String getApplicationURL();
	
}
