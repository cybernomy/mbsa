/*
 * ProblemServiceLocal.java
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;
import com.mg.merp.crm.model.Symptom;

/**
 * ������ ������-���������� "��������" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProblemServiceLocal.java,v 1.3 2007/01/26 13:16:04 sharapov Exp $
 */
public interface ProblemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Problem, Integer> {

	/**
	 * ��� ����� ��� ���������
	 */
	final static short FOLDER_PART = 13502;

	/**
	 * �������� ������� ��������
	 * @param problem
	 * @param symptom
	 */
	void linkSymptom(Problem problem, Symptom symptom);

	/**
	 * ������� ������� ��������
	 * @param problem
	 * @param symptom
	 */
	void unLinkSymptom(Problem problem, Symptom symptom);

	/**
	 * �������� ������� ��������
	 * @param problem
	 * @param solution
	 */
	void linkSolution(Problem problem, Solution solution);

	/**
	 * ������� ������� ��������
	 * @param problem
	 * @param solution
	 */
	void unLinkSolution(Problem problem, Solution solution);

}
