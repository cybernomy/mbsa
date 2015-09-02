/*
 * PromotionDiscountBusinessAddin.java
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
package com.mg.merp.discount.support;

import java.util.Map;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.discount.model.PromotionLine;
import com.mg.merp.document.model.DocSpec;

/**
 * ������� ����� BAi ������� ������/������� �� ��������� ������������.
 * �����-���������� ������ ������������� ����� <code>protected void doPerform() throws Exception</code>.
 * <p>
 * ������ ����� ������ ���������� ��������� ������� ������/������� 
 * @see com.mg.merp.discount.support.PromotionDiscountResult
 * <p>
 * ������(� �������������� ����������������� ����������):
 * ���� ������������ �������� "��", �� ��� ��������������� ������/������� ��������� � "������� ���������� �����������" (���� ������� �� �����.������������)
 * ���� ������������ �������� "���", �� ������ ��������� ����������� �� ���� �� ���������������� ������
 * <pre>
 * 	...
 *  
 * 	private final String STUDENT_QUESTION = "�� �������?";
 *	private final String YES_BUTTON_TEXT = "��";
 *	private final String NO_BUTTON_TEXT = "���";
 *
 *	private PromotionDiscountResult result = new PromotionDiscountResult();
 *
 *	protected void doPerform() throws Exception {
 *		PromotionLine promotionLine = getPromotionLine();
 *		Promotion promotion = promotionLine.getPromotion();
 *		// �������� ���������� �� ��� ���������������� ������
 *		if(getProcessContext().containsKey(STUDENT_QUESTION))
 *			// ���� ����������, �� ��������� ������� �� ��������� �����������
 *			completeOperation((String) getProcessContext().get(STUDENT_QUESTION));
 *		else 
 *			// ����� �������� ���������������� ������
 *			showQuestionDialog(promotion.getName());
 *	}
 *
 *	protected void showQuestionDialog(String promotionName) {
 *		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, promotionName, STUDENT_QUESTION, YES_BUTTON_TEXT, NO_BUTTON_TEXT, new AlertListener() {
 *			public void alertClosing(String value) {
 *				// ��������� ��������� ����������������� ������� � ��������� �����������
 *				getProcessContext().put(STUDENT_QUESTION, value);
 *				completeOperation(value);
 *			}
 *		});
 *	}
 *
 *	protected void completeOperation(String dialogResult) {
 *		if (YES_BUTTON_TEXT.equals(dialogResult)) {
 *			// ���������� �������� ������/������� (���� �� ������� ������������, %������/������� �� ������� ���������� �����������)
 *			BigDecimal discount = MathUtils.divide(getSpecification().getPrice1().multiply(getPromotionLine().getDiscount()), MathUtils.HUNDRED, new RoundContext(6)); 
 *			result.setDiscount(discount);
 *			result.setIsApplied(true);
 *		}
 *		else
 *			result.setIsApplied(false);
 *		// ������� ��������� ���������� BAi � ������� ���������
 *		complete(result);
 *	}
 * </pre>
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionDiscountBusinessAddin.java,v 1.2 2007/11/16 14:30:03 sharapov Exp $
 */
public abstract class PromotionDiscountBusinessAddin extends AbstractBusinessAddin<PromotionDiscountResult> {

	public static final String DOC_SPEC_PARAM = "DOC_SPEC_PARAM"; //$NON-NLS-1$
	public static final String PROMOTION_LINE_PARAM = "PROMOTION_LINE_PARAM"; //$NON-NLS-1$
	public static final String PROCESS_CONTEXT_PARAM = "PROCESS_CONTEXT_PARAM"; //$NON-NLS-1$
	
	private Map<String, Object> processContext;
	private DocSpec specification;
	private PromotionLine promotionLine;
		

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected void extractParams(Map<String, ? extends Object> params) {
		processContext = (Map<String, Object>) params.get(PROCESS_CONTEXT_PARAM);
		specification = (DocSpec) params.get(DOC_SPEC_PARAM);
		promotionLine = (PromotionLine) params.get(PROMOTION_LINE_PARAM);
	}

	/**
	 * �������� ������� ������������
	 * @return ������� ������������
	 */
	public DocSpec getSpecification() {
		return this.specification;
	}

	/**
	 * �������� ������� ���������� �����������
	 * @return ������� ���������� �����������
	 */
	public PromotionLine getPromotionLine() {
		return this.promotionLine;
	}

	/**
	 * �������� �������� �����������
	 * @return �������� �����������
	 */
	public Map<String, Object> getProcessContext() {
		return this.processContext;
	}
	
}

