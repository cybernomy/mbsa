/*
 * GoodsDocumentSpecificationServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.document.generic;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.math.Constants;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.DocumentSpecPackageServiceLocal;
import com.mg.merp.document.DocumentSpecSerialNumServiceLocal;
import com.mg.merp.document.DocumentTaxProcessor;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecPackage;
import com.mg.merp.document.model.DocumentSpecSerialNum;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.CatalogServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogExpDate;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.TimePeriodKind;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;

/**
 * Базовая реализация бизнес-компонента "Спецификации документа"
 *
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocumentSpecificationServiceBean.java,v 1.20 2009/02/11 14:10:28 safonov Exp
 *          $
 */
public abstract class GoodsDocumentSpecificationServiceBean<T extends com.mg.merp.document.model.DocSpec, ID extends Serializable>
    extends AbstractPOJODataBusinessObjectServiceBean<T, ID> implements GoodsDocumentSpecification<T, ID> {

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocumentSpecification#getDocSection()
   */
  @PermitAll
  public DocSection getDocSection() {
    return getPersistentManager().find(DocSection.class, getDocSectionIdentifier());
  }

  protected RoundContext getRoundContext() {
    return new RoundContext(getConfiguration().getCurrencyScale());
  }

  protected int getDocSectionIdentifier() {
    throw new UnsupportedOperationException();
  }

  public Configuration getConfiguration() {
    return DocumentUtils.getDocumentService(getDocSection()).getConfiguration();
  }

  protected void doAdjust(T entity) {
    //TODO реализовать алгоритм принятия решения что пересчитать в спецификации

    RoundContext rc = getRoundContext();
    //расчет от цен/сум со всеми налогами
    boolean total = false;//MathUtils.compareToZero(entity.getPrice()) != 0 && MathUtils.compareToZero(entity.getSumma()) != 0;

    BigDecimal quantity = entity.getQuantity();
    if (entity.getPrice1() == null || MathUtils.compareToZero(entity.getPrice1()) == 0) {
      entity.setPrice1(quantity == null || MathUtils.compareToZero(quantity) == 0 ?
          entity.getSumma1() : MathUtils.divide(entity.getSumma1(), quantity, rc));
    } else {
      entity.setSumma1(quantity == null || MathUtils.compareToZero(quantity) == 0 ?
          entity.getPrice1() : MathUtils.multiply(entity.getPrice1(), quantity, rc));
    }

    if (getDocSection().isWithTaxes()) {
      DocumentTaxProcessor taxProcessor = (DocumentTaxProcessor) ApplicationDictionaryLocator.locate().getBusinessService(DocumentTaxProcessor.SERVICE_NAME);
      taxProcessor.calculateDocumentSpecTaxes(entity, total, rc);
    } else {
      entity.setSumma(entity.getSumma1());
      entity.setPrice(entity.getPrice1());
    }
    updateSpecBestBefore(entity, DocumentUtils.loadDocumentHead(entity.getDocHead(), getDocSection()).getDocDate());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(T entity) {
    entity.setUNID(DataUtils.generateUUID());
    entity.setShelfLifeMeas(TimePeriodKind.NONE);
    entity.setVolume(BigDecimal.ZERO);
    entity.setWeight(BigDecimal.ZERO);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(T entity) {
    adjust(entity);
    if (!entity.isBulkOperation())
      DocumentUtils.getGoodsDocumentService(getDocSection()).modifySpecifaction(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(T entity) {
    adjust(entity);
    if (!entity.isBulkOperation())
      DocumentUtils.getGoodsDocumentService(getDocSection()).createSpecifaction(new DocSpec[]{entity});
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(T entity) {
    if (!entity.isBulkOperation())
      DocumentUtils.getGoodsDocumentService(getDocSection()).removeSpecifaction(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, T entity) {
    context.addRule(new MandatoryStringAttribute(entity, "UNID")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "Catalog"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(T entity) {
    entity.setUNID(DataUtils.generateUUID());
    entity.setAdjusted(true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(T entity, T entityClone) {
    AttributeMap attributeMap = DataUtils.toAttributeMap("DocSpec", entityClone);
    //налоги
    List<DocumentSpecTax> taxes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocumentSpecTax.class)
        .add(Restrictions.eq("DocSpec", entity)));
    for (DocumentSpecTax tax : taxes) {
      DocumentSpecTax taxClone = (DocumentSpecTax) tax.cloneEntity(attributeMap);
      getPersistentManager().persist(taxClone);
    }
    //упаковки
    DocumentSpecPackageServiceLocal packageService = (DocumentSpecPackageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DocumentSpecPackageServiceLocal.SERVICE_NAME);
    for (DocumentSpecPackage pkg : packageService.findByCriteria(Restrictions.eq("DocSpec", entity))) {
      packageService.clone(pkg, true, attributeMap);
    }
    //серийные номера
    DocumentSpecSerialNumServiceLocal serNumService = (DocumentSpecSerialNumServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DocumentSpecSerialNumServiceLocal.SERVICE_NAME);
    for (DocumentSpecSerialNum serNum : serNumService.findByCriteria(Restrictions.eq("DocSpec", entity))) {
      serNumService.clone(serNum, true, attributeMap);
    }
  }

  protected T initializeForBulkCreate(DocHead docHead, CreateSpecificationInfo goodsInfo) {
    if (getLogger().isDebugEnabled())
      getLogger().debug("Create document line for catalog: " + goodsInfo.getCatalogId());

    T specEntity = initialize();
    specEntity.setBulkOperation(true);
    specEntity.setDocHead(docHead);
    Catalog catalog = getPersistentManager().find(Catalog.class, goodsInfo.getCatalogId());
    specEntity.setCatalog(catalog);
    specEntity.setTaxGroup(catalog.getTaxGroup());
    specEntity.setShelfLife(catalog.getShelfLife());
    specEntity.setShelfLifeMeas(catalog.getShelfLifeMeas());
    specEntity.setMeasure1(catalog.getMeasure1());
    specEntity.setMeasure2(catalog.getMeasure2());
    specEntity.setQuantity(goodsInfo.getQuantity1());
    specEntity.setQuantity2(goodsInfo.getQuantity2());
    if (catalog.getWeight() != null)
      specEntity.setWeight(MathUtils.multiply(catalog.getWeight(), specEntity.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
    else
      specEntity.setWeight(BigDecimal.ZERO);
    if (catalog.getVolume() != null)
      specEntity.setVolume(MathUtils.multiply(catalog.getVolume(), specEntity.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
    else
      specEntity.setWeight(BigDecimal.ZERO);

    BigDecimal exactPrice = goodsInfo.getPrice();
    if (exactPrice == null)
      exactPrice = BigDecimal.ZERO;

    if (goodsInfo.getPricelistId() == null) {
      //добавили из каталога
      specEntity.setPrice1(exactPrice);
    } else {
      //добавили из прайс-листа
      specEntity.setPriceListSpec(getPersistentManager().find(PriceListSpec.class, goodsInfo.getPricelistId()));
      Currency docCur = docHead.getCurrency();
      Currency priceListCur = specEntity.getPriceListSpec().getFolder().getPriceListHead().getCurrency();
      //пересчитаем при разных валютах документа и прайс-листа
      if (priceListCur != null && docCur != null && priceListCur.getId() != docCur.getId()) {
        CurrencyServiceLocal curService = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Currency");
        exactPrice = curService.conversion(docCur, priceListCur, docHead.getCurrencyRateAuthority(), docHead.getCurrencyRateType(), docHead.getDocDate(), goodsInfo.getPrice());
      }
      specEntity.setPrice1(exactPrice);
    }

    if (catalog.getMeasureControl() != null)
      switch (catalog.getMeasureControl()) {
        case SINGLE:
          specEntity.setQuantity2(null);
          break;
        case POTENCY:
          MeasureConversionServiceLocal uomConversionService = (MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/MeasureConversion");
          specEntity.setQuantity2(uomConversionService.conversion(specEntity.getMeasure1(), specEntity.getMeasure2(), catalog, docHead.getDocDate(), specEntity.getQuantity()));
          break;
        case CATCHWEIGHT:
          break;
      }

    specEntity.setSumma1(exactPrice.multiply(specEntity.getQuantity()));

    //clone custom fields
    CustomFieldsManagerLocator.locate().cloneValues((CatalogServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CatalogServiceLocal.SERVICE_NAME),
        catalog, this, specEntity);

    return specEntity;
  }

  protected void doBulkCreate(DocHead docHead, CreateSpecificationInfo[] goodsInfoList) {
    List<DocSpec> docSpecs = new ArrayList<DocSpec>();
    for (CreateSpecificationInfo info : goodsInfoList) {
      T specEntity = initializeForBulkCreate(docHead, info);
      create(specEntity);
      docSpecs.add(specEntity);
    }
    DocumentUtils.getGoodsDocumentService(getDocSection()).createSpecifaction(docSpecs.toArray(new DocSpec[docSpecs.size()]));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocumentSpecification#bulkCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.CreateSpecificationInfo[])
   */
  @PermitAll
  public void bulkCreate(DocHead docHead, CreateSpecificationInfo[] goodsInfoList) {
    if (docHead == null)
      throw new IllegalArgumentException("DocHead is null");
    if (goodsInfoList == null)
      throw new IllegalArgumentException("Goods list is null");

    //загрузим сущность документа в сессиию и используем при инициализации спецификаций, чтобы при дальнейших действиях
    //использовалась сущность из сессии, иначе возможны побочные действия влияющие на расчет
    //агрегатных значений документа
    doBulkCreate(DocumentUtils.loadDocumentHead(docHead, getDocSection()), goodsInfoList);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocumentSpecification#adjust(com.mg.merp.document.model.DocSpec)
   */
  @PermitAll
  public void adjust(T entity) {
    if (!entity.isAdjusted())
      doAdjust(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocumentSpecification#updateSpecBestBefore(com.mg.merp.document.model.DocSpec)
   */
  @PermitAll
  public void updateSpecBestBefore(DocHead dochead) {
    doUpdateSpecBestBefore(dochead);
  }

  /**
   * Расчет срока годности у позиций спецификации документа
   *
   * @param entity документ
   */
  protected void doUpdateSpecBestBefore(DocHead dochead) {
    for (T docSpec : findByCriteria(Restrictions.eq("DocHead", dochead)))
      if (docSpec.getCatalog().getExpDateCalcKind() == CatalogExpDate.EDCKDOCUMENT)
        updateSpecBestBefore(docSpec, dochead.getDocDate());
  }

  /**
   * Расчет срока годности у позиции спецификации
   *
   * @param entity  спецификация
   * @param docDate дата документа
   */
  private void updateSpecBestBefore(T entity, Date docDate) {
    // срок хранения
    BigDecimal shelfLife = entity.getShelfLife();
    // ед. изм. срока хранения
    TimePeriodKind shelfLifeMeas = entity.getShelfLifeMeas();
    if (MathUtils.compareToZeroOrNull(shelfLife) != 0 && shelfLifeMeas != null && shelfLifeMeas != TimePeriodKind.NONE) {
      // тип расчета даты срока итечение срока годности
      CatalogExpDate calcKind = entity.getCatalog().getExpDateCalcKind();
      // срок годности
      Date bestBefore = null;
      if (calcKind == CatalogExpDate.EDCKPRODUCTION) {
        bestBefore = calculateBestBefore(entity.getProductionDate(), shelfLife, shelfLifeMeas);
      } else if (calcKind == CatalogExpDate.EDCKDOCUMENT) {
        bestBefore = calculateBestBefore(docDate, shelfLife, shelfLifeMeas);
      }
      if (bestBefore != null)
        entity.setBestBefore(bestBefore);
    }
  }

  /**
   * Расчет даты срока истечения годности
   *
   * @param date          дата относительно которой расчитывается дата срока истечения годности
   * @param shelfLife     срок хранения
   * @param shelfLifeMeas ед. изм. срока хранения
   */
  private Date calculateBestBefore(Date date, BigDecimal shelfLife, TimePeriodKind shelfLifeMeas) {
    if (date != null) {
      switch (shelfLifeMeas) {
        case HOUR:
          return DateTimeUtils.incHour(date, shelfLife.intValue());
        case DAY:
          return DateTimeUtils.incDay(date, shelfLife.intValue());
        case MONTH:
          return DateTimeUtils.incMonth(date, shelfLife.intValue());
        case YEAR:
          return DateTimeUtils.incYear(date, shelfLife.intValue());
      }
    }
    return null;
  }

}
