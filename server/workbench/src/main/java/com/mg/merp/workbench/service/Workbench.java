/* Workbench.java
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
package com.mg.merp.workbench.service;

import java.util.List;

import javax.swing.tree.TreeModel;

import com.mg.merp.security.model.Groups;

/**
 * Интерфейс взаимодействия студии разработки с сервером приложений
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: Workbench.java,v 1.3 2007/04/11 06:52:06 poroxnenko Exp $ 
 */
public interface Workbench {

	public void testConnectionOK();
	
    /**
     * @return Возвращает список бизнес компонентов в виде
     * строки BeanName1:=:Description1;...BeanNameN:=:DescriptionN
     */
	TreeModel getSysClasses() throws Exception;
	
	/**
	 * 
	 * @return список групп пользователей
	 * @throws Exception
	 */
	List<Groups> getSecGroups() throws Exception;
}
