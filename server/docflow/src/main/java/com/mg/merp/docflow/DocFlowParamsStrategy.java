/*
 * DocFlowParamsStrategy.java
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
package com.mg.merp.docflow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

/**
 * ��������� ������ ���������� ���������� ��
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowParamsStrategy.java,v 1.1 2006/12/12 15:23:33 safonov Exp $
 */
public interface DocFlowParamsStrategy {

	/**
	 * ����� ����� ��� ���������
	 * 
	 * @param docHead		��������
	 * @param processDate	���� ���������� ��
	 * @param stages		����� ��������� ��� ���������
	 * @param listener		��������� �������
	 */
	void chooseNextStage(final DocHead docHead, final Date processDate, final List<DocProcessStage> stages, final ChooseNextStageListener listener);
	
	/**
	 * ����� ������������ ��������� ��� ���������
	 * 
	 * @param processDate		���� ���������� ��
	 * @param performedStage	�������������� ���� 
	 * @param docSum			����� ��������� � ���������
	 * @param specList			������������ ��������� ��� ���������
	 * @param listener			��������� �������
	 */
	void inputDocumentSpecList(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final InputDocumentParamsListener listener);

	/**
	 * ���� ����� � ���������
	 * 
	 * @param processDate		���� ���������� ��
	 * @param performedStage	�������������� ����
	 * @param docSum			����� ��������� � ���������
	 * @param listener			��������� �������
	 */
	void inputDocumentSum(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final InputDocumentParamsListener listener);

	/**
	 * ����� ����� ��������� ��� ������������ ���������
	 * 
	 * @param processDate		���� ���������� ��
	 * @param performedStage	�������������� ����
	 * @param docSum			����� ��������� � ���������
	 * @param specList			������������ � ���������
	 * @param listener			��������� �������
	 */
	void chooseDestanationFolder(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final ChooseFolderListener listener);

	/**
	 * ����� ������� ��������� �� ��������� �������� ����� ����������� ��������
	 * 
	 * @param processDate		���� ���������� ��
	 * @param performedStage	�������������� ����
	 * @param docSum			����� ��������� � ���������
	 * @param specList			������������ � ���������
	 * @param listener			��������� �������
	 */
	void chooseDocumentPattern(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final ChooseDocumentPatternListener listener);

	/**
	 * ����� ��������� ��������� ��� ������
	 * 
	 * @param processDate		���� ���������� ��
	 * @param performedStage	������������ ����
	 * @param docHeadStates		��������� ��������� ��������� � ������
	 * @param specList			������������ ��������� � ������
	 * @param listener			��������� �������
	 */
	void chooseDocHeadState(final Date processDate, final DocProcessStage performedStage, final List<DocHeadState> docHeadStates, final List<DocumentSpecItem> specList, final ChooseDocumentStateListener listener);

}
