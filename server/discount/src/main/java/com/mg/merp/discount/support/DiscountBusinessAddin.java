/*
 * DiscountBusinessAddin.java.java
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
import com.mg.merp.discount.model.Discount;
import com.mg.merp.document.model.DocSpec;

/**
 * ������� ����� BAi ������� ������/�������.
 * �����-���������� ������ ������������� ����� <code>protected void doPerform() throws Exception</code>.
 * <p>
 * ������ ����� ������ ���������� ��������� ������� ������/������� 
 * @see com.mg.merp.discount.support.DiscountResult
 * <p>
 * ������(��� ������������� ����������������� ����������):
 * ��������������� ������ 50% (���� �� �����.������������)
 * ������ �� �������� ���������
 * <pre>
 * 	protected void doPerform() throws Exception {
 *		DiscountResult result = new DiscountResult();
 *		// ���������� �������� ������ (���� �� ������� ������������, ������ 50%)
 *		BigDecimal discount = MathUtils.divide(getSpecification().getPrice1().multiply(new BigDecimal(50).negate()), MathUtils.HUNDRED, new RoundContext(6)); 
 *		result.setDiscount(discount);
 *		result.setIsApplyDiscountOnDoc(true);
 *		result.setIsApplied(true);
 *		complete(result);
 *	}
 * <p>
 * ������(� �������������� ����������������� ����������):
 * ���� ������������ �������� "��", �� ��� ��������������� ������ 15% (���� �� �����.������������)
 * ���� ������������ �������� "���", �� ������ ������ �� ���� �� ���������������� ������
 * 
 * ...	
 * private final String PASSPORT_QUESTION = "� ��� ���� �������?";
 * private final String YES_BUTTON_TEXT = "��";
 * private final String NO_BUTTON_TEXT = "���";
 * private DiscountResult result = new DiscountResult();
 * 
 * protected void doPerform() throws Exception {
 *	// �������� ���������� �� ��� ���������������� ������
 *	if(getProcessContext().containsKey(PASSPORT_QUESTION))
 *		// ���� ����������, �� ��������� ������� �� ��������� �����������
 *		completeOperation((String) getProcessContext().get(PASSPORT_QUESTION));
 *	else // ����� �������� ���������������� ������
 *		showQuestionDialog(StringUtils.EMPTY_STRING);	 
 * }
 * 
 * protected void showQuestionDialog(String promotionName) {
 *	UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, promotionName, PASSPORT_QUESTION, YES_BUTTON_TEXT, NO_BUTTON_TEXT, new AlertListener() {
 *		public void alertClosing(String value) {
 *			// ��������� ��������� ����������������� ������� � ��������� �����������
 *			getProcessContext().put(PASSPORT_QUESTION, value);
 *			completeOperation(value);
 *		}
 *	});
 * }
 *	
 * protected void completeOperation(String dialogResult) {
 *	if (YES_BUTTON_TEXT.equals(dialogResult)) {
 *		// ���������� �������� ������ (���� �� �����.������������, c����� 15%)
 *		BigDecimal discount = MathUtils.divide(getSpecification().getPrice1().multiply(new BigDecimal(15).negate()), MathUtils.HUNDRED, new RoundContext(6)); 
 *		result.setDiscount(discount);
 *		result.setIsApplied(true);
 *	else 
 *		result.setIsApplied(false);
 *	// ������� ��������� ���������� BAi � ������� ���������
 *	complete(result);
 * }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: DiscountBusinessAddin.java,v 1.3 2007/11/16 14:30:03 sharapov Exp $
 */
public abstract class DiscountBusinessAddin extends AbstractBusinessAddin<DiscountResult> {
	
	public static final String DOCUMENT_SPEC_PARAM_NAME = "DOCUMENT_SPEC_PARAM_NAME"; //$NON-NLS-1$
	public static final String DISCOUNT_PARAM_NAME = "DISCOUNT_PARAM_NAME"; //$NON-NLS-1$
	public static final String PROCESS_CONTEXT_PARAM_NAME = "PROCESS_CONTEXT_PARAM_NAME"; //$NON-NLS-1$
	
	private DocSpec specification;
	private Discount discount;
	private Map<String, Object> processContext;
	
	
	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected void extractParams(Map<String, ? extends Object> params) {
		specification = (DocSpec) params.get(DOCUMENT_SPEC_PARAM_NAME);
		discount = (Discount) params.get(DISCOUNT_PARAM_NAME);
		processContext = (Map<String, Object>) params.get(PROCESS_CONTEXT_PARAM_NAME);
	}

	/**
	 * �������� ������������ ��������� ��� ������� ������������ ������ ������/�������
	 * @return ������������ ��������� ��� ������� ������������ ������ ������/�������
	 */
	protected DocSpec getSpecification() {
		return specification;
	}

	/**
	 * �������� ������/�������(��������) 
	 * @return ������/�������(��������)
	 */
	public Discount getDiscount() {
		return this.discount;
	}

	/**
	 * �������� �������� �����������
	 * @return �������� �����������
	 */
	public Map<String, Object> getProcessContext() {
		return this.processContext;
	}
	
}
