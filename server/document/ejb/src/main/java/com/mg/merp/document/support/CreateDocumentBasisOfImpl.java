/* CreateDocumentBasisOfImpl.java
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
package com.mg.merp.document.support;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.EntityTransformer;
import com.mg.framework.api.Logger;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.metadata.EntityCustomFieldsStorage;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.service.EntityTransformerLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.CreateDocumentBasisOf;
import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.CreateDocumentByPatternStrategy;
import com.mg.merp.document.Document;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация сервиса создания документа на основе
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: CreateDocumentBasisOfImpl.java,v 1.14 2009/02/27 09:00:15 safonov Exp $
 */
public class CreateDocumentBasisOfImpl<S extends DocHead, D extends DocHead, P extends DocHeadModel> implements CreateDocumentBasisOf<S, D, P> {
  private Logger logger = ServerUtils.getLogger(CreateDocumentBasisOfImpl.class);

  /* (non-Javadoc)
   * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, java.lang.Class, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder)
   */
  public D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder) {
    return doCreate(srcDoc, dstClass, model, date, specList, destFolder, null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder)
   */
  public void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder) {
    doCreate(srcDoc, dstDoc, model, date, specList, destFolder, null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, java.lang.Class, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder, com.mg.merp.document.CreateDocumentBasisOfCallback)
   */
  public D doCreate(S srcDoc, Class<D> dstClass, P model, Date date,
                    List<DocumentSpecItem> specList, Folder destFolder,
                    CreateDocumentBasisOfCallback createCallback) {
    if (logger.isDebugEnabled())
      logger.debug("create document basis of id: " + srcDoc.getId());
    EntityTransformer entityTransformer = EntityTransformerLocator.locate();
    Map<String, Object> superDoc = new HashMap<String, Object>();
    //представление документа-источника в виде "супердокумента"
    entityTransformer.map(srcDoc, superDoc);
    //создание результирующего документа из "супердокумента"
    D e = entityTransformer.map(superDoc, dstClass);

    return internalCreate(srcDoc, e, model, date, specList, destFolder, createCallback);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder, com.mg.merp.document.CreateDocumentBasisOfCallback)
   */
  public void doCreate(S srcDoc, D dstDoc, P model, Date date,
                       List<DocumentSpecItem> specList, Folder destFolder,
                       CreateDocumentBasisOfCallback createCallback) {
    if (logger.isDebugEnabled())
      logger.debug("create document basis of id: " + srcDoc.getId());
    EntityTransformer entityTransformer = EntityTransformerLocator.locate();
    Map<String, Object> superDoc = new HashMap<String, Object>();
    //представление документа-источника в виде "супердокумента"
    entityTransformer.map(srcDoc, superDoc);
    //создание результирующего документа из "супердокумента"
    entityTransformer.map(superDoc, dstDoc);

    internalCreate(srcDoc, dstDoc, model, date, specList, destFolder, createCallback);
  }

  /**
   * Конвертация суммы документа основания в сумму создаваемого документа
   *
   * @param srcDoc   Документ-основание
   * @param dstDoc   Результирующий документ
   * @param model    Образец
   * @param date     Дата создания документа
   * @param specList Список спецификаций результирующего документа
   */
  @SuppressWarnings("unchecked")
  protected void convertDocSum(S srcDoc, D dstDoc, Date date, List<DocumentSpecItem> specList) {
    CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
    Document docServ = DocumentUtils.getDocumentService(dstDoc.getDocSection());
    Configuration config = docServ.getConfiguration();

    BigDecimal tmpSumCur = BigDecimal.ZERO;
    RoundContext rc = new RoundContext(config.getCurrencyScale());

    //докумет-источник не имеет спецификаций
    if (specList == null || specList.size() == 0) {
      tmpSumCur = srcDoc.getSumCur();
    } else {
      for (DocumentSpecItem dsi : specList)
        tmpSumCur = MathUtils.add(tmpSumCur, dsi.getPerformedSum(), rc);
    }

    BigDecimal sumCur = BigDecimal.ZERO;
    if (tmpSumCur != null && MathUtils.compareToZero(tmpSumCur) != 0)
      sumCur = MathUtils.round(curServ.conversion(dstDoc.getCurrency(), srcDoc.getCurrency(),
          dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), date, tmpSumCur), rc);
    dstDoc.setSumCur(sumCur);

    BigDecimal sumNat = sumCur;
    if (!config.getLocalCurrency().getId().equals(dstDoc.getCurrency().getId()))
      sumNat = MathUtils.round(curServ.conversion(config.getLocalCurrency(), dstDoc.getCurrency(),
          config.getCurrencyRateAuthority(), config.getCurrencyRateType(), date, sumCur), new RoundContext(config.getCurrencyScale()));
    dstDoc.setSumNat(sumNat);
  }

  /**
   * проверка имен атрибутов для изменения в спецификации
   *
   * @param attributeName имя атрибута
   * @return <code>true</code> если атрибут можно менять
   */
  protected boolean isMutableDocSpecAttribute(String attributeName) {
    String[] attributeNames = new String[]{"Id", "DocHead", "SysClient", "UNID", "Quantity", "Quantity2", "Price", "Price1", "Summa", "Summa1", "Taxes", "SerialNumbers"};
    for (String element : attributeNames)
      if (element.equals(attributeName))
        return false;

    return true;
  }

  /**
   * Копирование спецификаций
   *
   * @param srcDoc   Документ-основание
   * @param dstDoc   Результирующий документ
   * @param date     Дата создания документа
   * @param specList Список спецификаций результирующего документа
   */
  @SuppressWarnings("unchecked")
  protected void doCopyDocSpecs(S srcDoc, D dstDoc, Date date, List<DocumentSpecItem> specList, CreateDocumentBasisOfCallback createCallback) {
    if (specList != null && specList.size() > 0) {
      if (dstDoc.getDocSection().isWithSpec()) {
        //оба документа со спецификациями
        GoodsDocumentSpecification dstDocSpecServ = DocumentUtils.getGoodsDocumentSpecificationService(dstDoc.getDocSection());
        GoodsDocumentSpecification srcDocSpecServ = DocumentUtils.getGoodsDocumentSpecificationService(srcDoc.getDocSection());
        DocSpec[] newDocSpecs = new DocSpec[specList.size()];
        int i = 0;
        GoodsDocument dstDocServ = DocumentUtils.getGoodsDocumentService(dstDoc.getDocSection());
        for (DocumentSpecItem dsi : specList) {
          DocSpec dstDocSpec = (DocSpec) dstDocSpecServ.initialize();
          DocSpec srcDocSpec = dsi.getDocSpec();

          for (String key : srcDocSpec.getAllAttributes().keySet()) {
            if (isMutableDocSpecAttribute(key)) {
              Object o = srcDocSpec.getAttribute(key);
              if (o != null && dstDocSpec.hasAttribute(key)) {
                if (logger.isDebugEnabled())
                  logger.debug(String.format("set document line attribute %s to %s", key, o.toString()));
                dstDocSpec.setAttribute(key, o);
              }
            }
          }
          dstDocSpec.setDocHead(dstDoc);

          CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
          Configuration config = dstDocServ.getConfiguration();

          BigDecimal price1 = BigDecimal.ZERO;
          if (srcDocSpec.getPrice() != null && MathUtils.compareToZero(srcDocSpec.getPrice()) != 0)
            price1 = MathUtils.round(curServ.conversion(dstDoc.getCurrency(), srcDoc.getCurrency(),
                    dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), date, srcDocSpec.getPrice()),
                new RoundContext(config.getCurrencyScale()));

          BigDecimal price2 = BigDecimal.ZERO;
          if (srcDocSpec.getPrice1() != null && MathUtils.compareToZero(srcDocSpec.getPrice1()) != 0)
            price2 = MathUtils.round(curServ.conversion(dstDoc.getCurrency(), srcDoc.getCurrency(),
                    dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), date, srcDocSpec.getPrice1()),
                new RoundContext(config.getCurrencyScale()));

          dstDocSpec.setPrice(price1);
          dstDocSpec.setPrice1(price2);

          dstDocSpec.setQuantity(dsi.getPerformedQuantity1());
          dstDocSpec.setQuantity2(dsi.getPerformedQuantity2());

          //clone custom fields
          CustomFieldsManagerLocator.locate().cloneValues(srcDocSpecServ, srcDocSpec, dstDocSpecServ, dstDocSpec);

          //выполним дополнительную обработку, если существует
          if (createCallback != null) {
            logger.debug("execute document line callback");
            createCallback.processDocumentSpec(dstDoc, srcDoc, dstDocSpec, srcDocSpec);
          }

          dstDocSpec.setBulkOperation(true); //чтобы не изменял заголовок, после добавления всех применим массовую операцию на заголовке
          dstDocSpecServ.create(dstDocSpec);

          newDocSpecs[i++] = dstDocSpec;
        }
        dstDocServ.createSpecifaction(newDocSpecs);
        for (DocSpec docSpec : newDocSpecs)
          docSpec.setBulkOperation(false);
      } else
        convertDocSum(srcDoc, dstDoc, date, specList); //если создаваемый документ не предусматривает спецификации
    } else if (!dstDoc.getDocSection().isWithSpec()) //оба документа не предусматривают спецификаций
      convertDocSum(srcDoc, dstDoc, date, null);
  }

  @SuppressWarnings("unchecked")
  protected D internalCreate(S srcDoc, D dstDoc, P pattern, Date date, List<DocumentSpecItem> specList, Folder destFolder, CreateDocumentBasisOfCallback createCallback) {
    //полученый документ накрываем данными из модели
    CreateDocumentByPatternStrategy createByPatternStrategy = new DefaultCreateDocumentByPatternStrategy(destFolder);
    dstDoc = (D) createByPatternStrategy.createDocument(dstDoc, pattern);

    //установка параметров документа-основания
    dstDoc.setBaseDocDate(srcDoc.getDocDate());
    dstDoc.setBaseDocNumber(srcDoc.getDocNumber());
    dstDoc.setBaseDocType(srcDoc.getDocType());
    dstDoc.setBaseDocument(srcDoc);
    //если не передали дату создания документа, то используем из документа источника или образца
    //https://issues.m-g.ru/bugzilla/show_bug.cgi?id=5020
    if (date != null)
      dstDoc.setDocDate(date);

    //курс валюты
    Configuration cfg = DocumentUtils.getDocumentService(dstDoc.getDocSection()).getConfiguration();
    CurrencyRateServiceLocal currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
    try {
      //пытаемся получить прямой курс для валют
      dstDoc.setCurCource(currencyRateService.getCurrencyRate(cfg.getLocalCurrency(), dstDoc.getCurrency(),
          dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), dstDoc.getDocDate()));
    } catch (CurrencyRateNotFoundException e) {
      //если прямого курса нет, то пытаемся получить обратный курс
      dstDoc.setCurCource(currencyRateService.getIndirectCurrencyRate(dstDoc.getCurrency(), cfg.getLocalCurrency(),
          dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), dstDoc.getDocDate()));
    }

    //сохраняем заголовок
    Document docServ = DocumentUtils.getDocumentService(dstDoc.getDocSection());
    //clone custom fields
    doMergeCustomFields(docServ, dstDoc, srcDoc, pattern);

    //выполним дополнительную обработку, если существует
    if (createCallback != null) {
      logger.debug("execute document callback");
      createCallback.processDocumentHead(dstDoc, srcDoc, pattern);
    }

    docServ.create(dstDoc);

    doCopyDocSpecs(srcDoc, dstDoc, dstDoc.getDocDate(), specList, createCallback);

    return dstDoc;
  }

  /**
   * Выполнить слияние доп.признаков
   *
   * @param dstDocService - сервис создаваемого документа
   * @param dstDoc        - создаваемый документ
   * @param srcDoc        - документ основание
   * @param pattern       - образец документа
   */
  protected void doMergeCustomFields(DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> dstDocService, DocHead dstDoc, DocHead srcDoc, DocHeadModel pattern) {
    Map<String, Object> srcDocCustomFieldValues = srcDoc.getStorage().getValues();
    Map<String, Object> patternCustomFieldValues = pattern.getStorage().getValues();
    EntityCustomFieldsStorage dstDocCustomFieldsStorage = dstDoc.getStorage();
    // загрузим метаданные доп.признаков, которые присутствуют в создаваемом документе
    Map<String, Object> dstDocCustomFieldValues = CustomFieldsManagerLocator.locate().loadValues(dstDocService, dstDoc.getId());

    Set<String> dstDocCustomFieldNames = dstDocCustomFieldValues.keySet();
    for (String dstDocCustomFieldName : dstDocCustomFieldNames) {
      Object value = patternCustomFieldValues.get(dstDocCustomFieldName);
      if (value == null)
        value = srcDocCustomFieldValues.get(dstDocCustomFieldName);

      if (value != null)
        dstDocCustomFieldsStorage.setValue(dstDocCustomFieldName, value);
    }
  }

}