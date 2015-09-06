/*
 * GoodsDocumentServiceBean.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.support.DocumentUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

/**
 * Базовая реализация бизнес-компонента "Документ со спецификациями"
 *
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocumentServiceBean.java,v 1.13 2008/07/31 12:45:29 safonov Exp $
 */
public abstract class GoodsDocumentServiceBean<T extends com.mg.merp.document.model.DocHead, ID extends Serializable, M extends DocumentPattern, S extends GoodsDocumentSpecification>
    extends DocumentServiceBean<T, ID, M> implements GoodsDocument<T, ID, M, S> {

  /**
   * реализация изменения спецификации документа
   *
   * @param docSpecs спецификации документа
   */
  protected void doModifySpecifaction(DocSpec... docSpecs) {
    DefaultDocumentAggregatePropertiesStrategy st = new DefaultDocumentAggregatePropertiesStrategy(this, docSpecs, false, getConfiguration().getCurrencyScale());
    st.calculate();
  }

  /**
   * реализация создания спецификации документа
   *
   * @param docSpecs спецификации документа
   */
  protected void doCreateSpecifaction(DocSpec... docSpecs) {
    DefaultDocumentAggregatePropertiesStrategy st = new DefaultDocumentAggregatePropertiesStrategy(this, docSpecs, false, getConfiguration().getCurrencyScale());
    st.calculate();
  }

  /**
   * реализация удаления спецификации документа
   *
   * @param docSpecs спецификации документа
   */
  protected void doRemoveSpecifaction(DocSpec... docSpecs) {
    DefaultDocumentAggregatePropertiesStrategy st = new DefaultDocumentAggregatePropertiesStrategy(this, docSpecs, true, getConfiguration().getCurrencyScale());
    st.calculate();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#onClone(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void onClone(T entity) {
    super.onClone(entity);
    //https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4810
    //если копируется со строками, то агрегатные значения будут вычисляться
    entity.setSumCur(null);
    entity.setSumNat(null);
    entity.setVolume(null);
    entity.setWeight(null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doDeepClone(T entity, T entityClone) {
    S specService = getSpecificationService();
    List<DocSpec> specs = specService.findByCriteria(Restrictions.eq("DocHead", entity));
    List<DocSpec> clonedSpecs = new ArrayList<DocSpec>();
    AttributeMap initAttr = new LocalDataTransferObject();
    initAttr.put("DocHead", entityClone);
    initAttr.put("BulkOperation", true); //признак группового создания спецификаций
    for (DocSpec docSpec : specs) {
      clonedSpecs.add((DocSpec) specService.clone(docSpec, true, initAttr));
    }
    //изменим заголовок, т.к. был установлен признак BulkOperation
    createSpecifaction(clonedSpecs.toArray(new DocSpec[clonedSpecs.size()]));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocument#modifySpecifaction(com.mg.merp.document.model.DocSpec)
   */
  @PermitAll
  public void modifySpecifaction(DocSpec... docSpecs) {
    if (docSpecs == null || docSpecs.length == 0)
      return;

    //TODO реализовать блокировку объекта-сущности
    doModifySpecifaction(docSpecs);
    DocFlowHelper.modifyDocument(docSpecs[0].getDocHead());
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocument#createSpecifaction(com.mg.merp.document.model.DocSpec)
   */
  @PermitAll
  public void createSpecifaction(DocSpec... docSpecs) {
    if (docSpecs == null || docSpecs.length == 0)
      return;

    // TODO реализовать блокировку объекта-сущности
    doCreateSpecifaction(docSpecs);
    DocFlowHelper.modifyDocument(docSpecs[0].getDocHead());
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocument#removeSpecifaction(com.mg.merp.document.model.DocSpec)
   */
  @PermitAll
  public void removeSpecifaction(DocSpec... docSpecs) {
    if (docSpecs == null || docSpecs.length == 0)
      return;

    // TODO реализовать блокировку объекта-сущности
    doRemoveSpecifaction(docSpecs);
    DocFlowHelper.modifyDocument(docSpecs[0].getDocHead());
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    // TODO Автоматически созданная заглушка метода
    return 0;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#onStore(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void onStore(T entity) {
    super.onStore(entity);
    getSpecificationService().updateSpecBestBefore(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.GoodsDocument#getSpecificationService()
   */
  @SuppressWarnings("unchecked")
  @PermitAll
  public S getSpecificationService() {
    return (S) DocumentUtils.getGoodsDocumentSpecificationService(getDocSection());
  }

  public void applyDiscount(AttributeMap document, double discount) throws ApplicationException {
    //((GoodsDocument) getDomain()).applyDiscount(document, discount);
  }

  public void allotAddExpences(int docId, double addExpences) throws ApplicationException {
    //	((GoodsDocument) getDomain()).allotAddExpences(docId, addExpences);
  }

}
