/*
 * SolutionServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;

/**
 * 
 * @author leonova
 * @version $Id: SolutionServiceLocal.java,v 1.3 2007/08/22 11:57:19 alikaev Exp $
 */
public interface SolutionServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<Solution, Integer>
{
	/**
	 * тип папки для решений
	 */
	final static short FOLDER_PART = 13503;
	
	/**
	 * добавить проблему решению
	 * 
	 * @param problem		проблема
	 * @param solution		решение
	 * @throws com.mg.framework.api.ApplicationException
	 */
	public void linkProblem(Problem problem, Solution solution);
	
	/**
	 * удалить проблему из решения
	 * 
	 * @param problem		проблема
	 * @param solution		решение
	 * @throws com.mg.framework.api.ApplicationException
	 */
	public void unLinkProblem(Problem problem, Solution solution);

}
