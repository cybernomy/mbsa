/*
 * RptEngineServiceMBean.java
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
package com.mg.merp.report.service.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.report.RptEngine;

/**
 * ������ MBIRT (Millennium BI and Report Tools) ��� AS JBoss
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineServiceMBean.java,v 1.3 2008/08/12 09:24:56 safonov Exp $
 */
public interface RptEngineServiceMBean extends RptEngine, ServiceMBean {
	/**
	 * �������� ������������ ���������
	 * 
	 * @return	���������� ���� � ���������
	 */
	String getBIRTLocation();

	/**
	 * ���������� ������������ ���������
	 * 
	 * @param birtLocation	���������� ���� � ���������
	 */
	void setBIRTLocation(String birtLocation);

	/**
	 * �������� ������ ������ ������ �� ���������
	 * 
	 * @return	������ ������
	 */
	String getDefaultOutputFormat();
	
	/**
	 * ���������� ������ ������ ������ �� ���������
	 * 
	 * @param outputFormat	������ ������
	 */
	void setDefaultOutputFormat(String outputFormat);
	
	/**
	 * �������� ������� �����������
	 * 
	 * @return	������� �����������
	 */
	String getLogLevel();
	
	/**
	 * ���������� ������� �����������
	 * 
	 * @param logLevel	������� �����������
	 */
	void setLogLevel(String logLevel);
	
	/**
	 * �������� ��� ���������� ��� ��������� �������
	 * 
	 * @return	��� ����������
	 */
	String getHTMLReportViewerApp();
	
	/**
	 * ���������� ��� ���������� ��� ��������� �������
	 * 
	 * @param viwerApp	��� ����������
	 */
	void setHTMLReportViewerApp(String viwerApp);
	
	/**
	 * �������� ����� ��� �������� �������� �������
	 * 
	 * @return	����� �������� ��������
	 */
	String getReportFolder();
	
	/**
	 * ���������� ����� ��� �������� �������� �������
	 * 
	 * @param reportFolder	����� �������� ��������
	 */
	void setReportFolder(String reportFolder);

}
