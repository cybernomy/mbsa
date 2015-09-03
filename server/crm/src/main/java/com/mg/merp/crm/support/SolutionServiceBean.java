/*
 * SolutionServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System..
 *
 */

package com.mg.merp.crm.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.SolutionServiceLocal;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;

/**
 * Бизнес-компонент "Решения" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: SolutionServiceBean.java,v 1.5 2007/08/22 11:58:13 alikaev Exp $
 */
@Stateless(name="merp/crm/SolutionService")
public class SolutionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Solution, Integer> implements SolutionServiceLocal {

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
 	public void linkProblem(Problem problem, Solution solution) {
 		ProblemServiceLocal problemService = (ProblemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Problem");
 		problemService.linkSolution(problem, solution);
 	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
 	public void unLinkProblem(Problem problem, Solution solution) {
		ProblemServiceLocal problemService = (ProblemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Problem");
		problemService.unLinkSolution(problem, solution);
	}
 	
}
