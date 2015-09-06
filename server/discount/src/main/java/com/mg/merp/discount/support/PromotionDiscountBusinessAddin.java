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

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.discount.model.PromotionLine;
import com.mg.merp.document.model.DocSpec;

import java.util.Map;

/**
 * Базовый класс BAi расчета скидок/наценок по рекламным мероприятиям. Класс-расширение должен
 * реализовывать метод <code>protected void doPerform() throws Exception</code>. <p> Данный метод
 * должен возвращать результат расчета скидок/наценок
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionDiscountBusinessAddin.java,v 1.2 2007/11/16 14:30:03 sharapov Exp $
 * @see com.mg.merp.discount.support.PromotionDiscountResult <p> Пример(с использованием
 * пользовательского интерфейса): Если пользователь отвечает "Да", то ему предоставляется
 * скидка/наценка указанная в "позиции рекламного мероприятия" (цена берется из позиц.спецификации)
 * Если пользователь отвечает "Нет", то данное рекламное мероприятие на него НЕ РАСПРОСТРАНЯЕТСЯ
 * ВООБЩЕ
 * <pre>
 * 	...
 *
 * 	private final String STUDENT_QUESTION = "Вы студент?";
 * 	private final String YES_BUTTON_TEXT = "Да";
 * 	private final String NO_BUTTON_TEXT = "Нет";
 *
 * 	private PromotionDiscountResult result = new PromotionDiscountResult();
 *
 * 	protected void doPerform() throws Exception {
 * 		PromotionLine promotionLine = getPromotionLine();
 * 		Promotion promotion = promotionLine.getPromotion();
 * 		// проверим запускался ли уже пользовательский диалог
 * 		if(getProcessContext().containsKey(STUDENT_QUESTION))
 * 			// если запускался, то результат возьмем из контекста выполненния
 * 			completeOperation((String) getProcessContext().get(STUDENT_QUESTION));
 * 		else
 * 			// иначе запустим пользовательский диалог
 * 			showQuestionDialog(promotion.getName());
 * 	}
 *
 * 	protected void showQuestionDialog(String promotionName) {
 * 		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, promotionName, STUDENT_QUESTION,
 * YES_BUTTON_TEXT, NO_BUTTON_TEXT, new AlertListener() {
 * 			public void alertClosing(String value) {
 * 				// разместим результат пользовательского диалога в контексте выполненния
 * 				getProcessContext().put(STUDENT_QUESTION, value);
 * 				completeOperation(value);
 * 			}
 * 		});
 * 	}
 *
 * 	protected void completeOperation(String dialogResult) {
 * 		if (YES_BUTTON_TEXT.equals(dialogResult)) {
 * 			// расчиываем значение скидки/наценки (цена из позиции спецификации, %скидки/наценки из
 * позиции рекламного мероприятия)
 * 			BigDecimal discount = MathUtils.divide(getSpecification().getPrice1().multiply(getPromotionLine().getDiscount()),
 * MathUtils.HUNDRED, new RoundContext(6));
 * 			result.setDiscount(discount);
 * 			result.setIsApplied(true);
 * 		}
 * 		else
 * 			result.setIsApplied(false);
 * 		// успешно завершить выполнение BAi и вернуть результат
 * 		complete(result);
 * 	}
 * </pre>
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
   * Получить позицию спецификации
   *
   * @return позиция спецификации
   */
  public DocSpec getSpecification() {
    return this.specification;
  }

  /**
   * Получить позицию рекламного мероприятия
   *
   * @return позиция рекламного мероприятия
   */
  public PromotionLine getPromotionLine() {
    return this.promotionLine;
  }

  /**
   * Получить контекст выполненния
   *
   * @return контекст выполненния
   */
  public Map<String, Object> getProcessContext() {
    return this.processContext;
  }

}

