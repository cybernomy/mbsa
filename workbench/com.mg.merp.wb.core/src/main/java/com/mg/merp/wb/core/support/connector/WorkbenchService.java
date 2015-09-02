/* WorkbenchService.java
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
package com.mg.merp.wb.core.support.connector;

import java.util.List;

import javax.management.ObjectName;
import javax.swing.tree.TreeModel;

import com.mg.merp.security.model.Groups;


/**
 * ������ �������������� ������ ���������� � �������� ����������.
 * ������������� ������ � ��������� ������ � ��������.
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WorkbenchService.java,v 1.3 2007/07/10 11:30:33 poroxnenko Exp $ 
 */
public class WorkbenchService{
	
	private static final String WORKBENCH_SERVICE_NAME = "merp:service=WorkbenchService";
	
	/**
	 * @return
	 * 			 ������ ������ ����������� �������
	 * @throws Exception
	 */
	public static TreeModel getSysClasses() throws Exception{
		return (TreeModel)ServiceConnector.getServiceConnector().invoke(new ObjectName(WORKBENCH_SERVICE_NAME), "getSysClasses",  new Object[] {}, new String[] {});
	}
	
	/**
	 * @return
	 * 			������ ����� �������������
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Groups> getSecGroups() throws Exception{
		return (List<Groups>)ServiceConnector.getServiceConnector().invoke(new ObjectName(WORKBENCH_SERVICE_NAME), "getSecGroups",  new Object[] {}, new String[] {});
	}
	
	/**
	 * �������� ����� ������ ���������� � �������� ����������
	 * 
	 * @return
	 * 			true-����� ����
	 * 			false-����� �����������
	 */
    public static boolean testConnectionOK(){
    	try{
    		ServiceConnector.getServiceConnector().invoke(new ObjectName(WORKBENCH_SERVICE_NAME), "testConnectionOK", new Object[] {}, new String[] {}); //$NON-NLS-1$;
    		return true;
    	}catch (Exception ex){
    		return false;
    	}
    }

}
