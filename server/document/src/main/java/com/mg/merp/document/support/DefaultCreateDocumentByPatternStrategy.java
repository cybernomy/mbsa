/**
 * DefaultCreateDocumentByPatternStrategy.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.Document;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.generic.AbstractCreateDocumentByPatternStrategy;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.model.Currency;

import java.math.BigDecimal;

/**
 * Стандартная реализация стратегии создания документа по образцу
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultCreateDocumentByPatternStrategy.java,v 1.3 2008/11/06 14:25:14 safonov Exp
 *          $
 */
public class DefaultCreateDocumentByPatternStrategy extends
    AbstractCreateDocumentByPatternStrategy {
  private Folder folder = null;
  private boolean cloneCustomFields = false;
  @SuppressWarnings("unchecked")
  private Document documentService;
  @SuppressWarnings("unchecked")
  private DocumentPattern documentPatternService;

  public DefaultCreateDocumentByPatternStrategy(Folder folder) {
    this.folder = folder;
  }

  @SuppressWarnings("unchecked")
  public DefaultCreateDocumentByPatternStrategy(
      Folder folder,
      boolean cloneCustomFields,
      Document documentService,
      DocumentPattern documentPatternService) {
    super();
    this.folder = folder;
    this.cloneCustomFields = cloneCustomFields;
    this.documentService = documentService;
    this.documentPatternService = documentPatternService;
  }

  /**
   * проверка имен атрибутов для изменения в заголовке
   *
   * @param attributeName имя атрибута
   * @return <code>true</code> если атрибут нельзя менять
   */
  private boolean isImmutableDocHeadAttribute(String attributeName) {
    String[] attributeNames = new String[]{"Id", "Folder", "ModelDestFolder", "SysClient", "ModelName"};
    for (String element : attributeNames)
      if (element.equals(attributeName))
        return true;

    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.AbstractCreateDocumentByPatternStrategy#doPrepareDocument(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel)
   */
  @Override
  protected DocHead doCreateDocument(DocHead document,
                                     DocHeadModel documentPattern) {
    AttributeMap attributes = documentPattern.getAllAttributes();
    for (String name : attributes.keySet()) {
      if (logger.isDebugEnabled())
        logger.debug("find document pattern attribute: " + name);

      if (isImmutableDocHeadAttribute(name) || !document.hasAttribute(name))
        continue;

      if (logger.isDebugEnabled())
        logger.debug("handle document pattern attribute: " + name);

      Object value = attributes.get(name);
      if (logger.isDebugEnabled())
        logger.debug("document pattern attribute '" + name + "' has value: " + (value == null ? "null" : value.toString()));

      //копируем только имеющие значения, см. http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4386
      if (value != null)
        document.setAttribute(name, value);
    }
    // если папка-приемник не указана в образце, то создаем в текущей папке
    if (documentPattern.getModelDestFolder() != null)
      document.setFolder(documentPattern.getModelDestFolder());
    else
      document.setFolder(folder);

    //устанавливаем курс валюты если валюта документа отличается от национальной валюты
    Currency localCurrency = documentService != null ? documentService.getConfiguration().getLocalCurrency() : null;
    if (localCurrency != null && document.getCurrency() != null
        && document.getCurrencyRateAuthority() != null && document.getCurrencyRateType() != null
        && !localCurrency.equals(document.getCurrency())) {
      CurrencyRateServiceLocal currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
      try {
        //пытаемся получить прямой курс для валют
        document.setCurCource(currencyRateService.getCurrencyRate(localCurrency, document.getCurrency(), document.getCurrencyRateAuthority(), document.getCurrencyRateType(), document.getDocDate()));
      } catch (CurrencyRateNotFoundException e) {
        //если прямого курса нет, то пытаемся получить обратный курс
        document.setCurCource(MathUtils.divide(BigDecimal.ONE, currencyRateService.getIndirectCurrencyRate(document.getCurrency(), localCurrency, document.getCurrencyRateAuthority(), document.getCurrencyRateType(), document.getDocDate()), new RoundContext(CurrencyRateServiceLocal.DEFAULT_RATE_SCALE)));
      }
    }

    //clone custom fields
    if (cloneCustomFields && documentService != null && documentPatternService != null)
      CustomFieldsManagerLocator.locate().cloneValues(documentPatternService, documentPattern, documentService, document);

    return document;
  }

}
