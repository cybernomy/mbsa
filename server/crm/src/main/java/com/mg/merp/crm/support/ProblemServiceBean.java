/*
 * ProblemServiceBean.java
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

package com.mg.merp.crm.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.model.LinkProblemSolution;
import com.mg.merp.crm.model.LinkProblemSolutionId;
import com.mg.merp.crm.model.LinkSymptomProblem;
import com.mg.merp.crm.model.LinkSymptomProblemId;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;
import com.mg.merp.crm.model.Symptom;

/**
 * Реализация бизнес-компонента "Проблемы" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProblemServiceBean.java,v 1.6 2007/08/22 11:58:48 alikaev Exp $
 */
@Stateless(name="merp/crm/ProblemService") //$NON-NLS-1$
public class ProblemServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Problem, Integer> implements ProblemServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ProblemServiceLocal#linkSymptom(com.mg.merp.crm.model.Problem, com.mg.merp.crm.model.Symptom)
	 */
	public void linkSymptom(Problem problem, Symptom symptom) {
		if(!isExistLinkProblemSymptom(problem, symptom))
			ServerUtils.getPersistentManager().persist(createSymptomProblemLink(problem, symptom));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ProblemServiceLocal#unLinkSymptom(com.mg.merp.crm.model.Problem, com.mg.merp.crm.model.Symptom)
	 */
	public void unLinkSymptom(Problem problem, Symptom symptom) {
		ServerUtils.getPersistentManager().remove(createSymptomProblemLink(problem, symptom));
	}

	/**
	 * Создать связь "Проблема - симптом"
	 * @param problem - проблема
	 * @param symptom - симптом
	 * @return объект "проблема-симптом"
	 */
	private LinkSymptomProblem createSymptomProblemLink(Problem problem, Symptom symptom) {
		getPersistentManager().refresh(problem);
		LinkSymptomProblem linkSymptomProblem = new LinkSymptomProblem();
		LinkSymptomProblemId linkSymptomProblemId = new LinkSymptomProblemId();
		linkSymptomProblemId.setCrmProblem(problem);
		linkSymptomProblemId.setCrmSymptom(symptom);
		linkSymptomProblemId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
		linkSymptomProblem.setId(linkSymptomProblemId);
		return linkSymptomProblem;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ProblemServiceLocal#linkSolution(com.mg.merp.crm.model.Problem, com.mg.merp.crm.model.Solution)
	 */
	public void linkSolution(Problem problem, Solution solution) {
		if(!isExistLinkProblemSolution(problem, solution))
			ServerUtils.getPersistentManager().persist(createSolutionProblemLink(problem, solution));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ProblemServiceLocal#unLinkSolution(com.mg.merp.crm.model.Problem, com.mg.merp.crm.model.Solution)
	 */
	public void unLinkSolution(Problem problem, Solution solution) {
		ServerUtils.getPersistentManager().remove(createSolutionProblemLink(problem, solution));
	}

	/**
	 * Создать связь "Проблема - решение"
	 * @param problem - проблема
	 * @param solution - решение
	 * @return объект "проблема-решение"
	 */
	private LinkProblemSolution createSolutionProblemLink(Problem problem, Solution solution) {
//		getPersistentManager().refresh(problem);
		LinkProblemSolution linkProblemSolution = new LinkProblemSolution();
		LinkProblemSolutionId linkProblemSolutionId = new LinkProblemSolutionId();
		linkProblemSolutionId.setCrmProblem(problem);
		linkProblemSolutionId.setCrmSolution(solution);
		linkProblemSolutionId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
		linkProblemSolution.setId(linkProblemSolutionId);
		return linkProblemSolution;
	}

	/**
	 * Проверка существования связи "Проблема - решение"
	 */
	private boolean isExistLinkProblemSolution(Problem problem, Solution solution) {
		LinkProblemSolutionId linkProblemSolutionId = new LinkProblemSolutionId();
		linkProblemSolutionId.setCrmProblem(problem);
		linkProblemSolutionId.setCrmSolution(solution);
		linkProblemSolutionId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
		LinkProblemSolution linkProblemSolution = ServerUtils.getPersistentManager().find(LinkProblemSolution.class, linkProblemSolutionId);
		if(linkProblemSolution != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Проверка существования связи "Проблема - симптом"
	 */
	private boolean isExistLinkProblemSymptom(Problem problem, Symptom symptom) {
		LinkSymptomProblemId linkSymptomProblemId = new LinkSymptomProblemId();
		linkSymptomProblemId.setCrmProblem(problem);
		linkSymptomProblemId.setCrmSymptom(symptom);
		linkSymptomProblemId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
		LinkSymptomProblem linkSymptomProblem = ServerUtils.getPersistentManager().find(LinkSymptomProblem.class, linkSymptomProblemId);
		if(linkSymptomProblem != null)
			return true;
		else
			return false;
	}

}
