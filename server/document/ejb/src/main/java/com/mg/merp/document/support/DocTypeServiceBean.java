/*
 * DocTypeServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.document.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.DocProcessStageRights;
import com.mg.merp.docprocess.model.LinkStage;
import com.mg.merp.document.DocTypeServiceLocal;
import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocTypeDocSectionLink;
import com.mg.merp.document.model.DocumentKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Тип документа"
 *
 * @author Oleg V. Safonov
 * @version $Id: DocTypeServiceBean.java,v 1.8 2007/11/08 06:40:50 sharapov Exp $
 */
@Stateless(name = "merp/document/DocTypeService") //$NON-NLS-1$
public class DocTypeServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<DocType, Integer> implements DocTypeServiceLocal {

  private void adjustDocType(DocType entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(DocType entity) {
    adjustDocType(entity);
    super.onCreate(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(DocType entity) {
    adjustDocType(entity);
    super.onStore(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, final DocType entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
//		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") {
//		@Override
//		protected void doValidate(ValidationContext context) {
//		if (OrmTemplate.getInstance().findUniqueByCriteria(DocType.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId() == null ? 0 : entity.getId())) != null)
//		context.getStatus().error(this);
//		}
//		});
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeServiceLocal#loadDocSectionLinks()
   */
  @PermitAll
  public DocTypeDocSectionLink[][] loadDocSectionLinks(DocType docType) {
    DocTypeDocSectionLink[][] result = new DocTypeDocSectionLink[DocumentKind.values().length][0];
    List<DocTypeDocSectionLink> allLinks = MiscUtils.convertUncheckedList(DocTypeDocSectionLink.class, OrmTemplate.getInstance().findByNamedParam("from DocTypeDocSectionLink l where l.DocType = :docType", "docType", docType)); //$NON-NLS-1$ //$NON-NLS-2$
    List<DocTypeDocSectionLink> docLinks = new ArrayList<DocTypeDocSectionLink>();
    List<DocTypeDocSectionLink> confLinks = new ArrayList<DocTypeDocSectionLink>();
    List<DocTypeDocSectionLink> baseLinks = new ArrayList<DocTypeDocSectionLink>();
    List<DocTypeDocSectionLink> contractLinks = new ArrayList<DocTypeDocSectionLink>();
    for (DocTypeDocSectionLink link : allLinks) {
      switch (link.getKind()) {
        case DOCUMENT:
          docLinks.add(link);
          break;
        case CONFIRMATION:
          confLinks.add(link);
          break;
        case BASE:
          baseLinks.add(link);
          break;
        case CONTRACT:
          contractLinks.add(link);
          break;
      }
    }
    result[DocumentKind.DOCUMENT.ordinal()] = docLinks.toArray(new DocTypeDocSectionLink[docLinks.size()]);
    result[DocumentKind.CONFIRMATION.ordinal()] = confLinks.toArray(new DocTypeDocSectionLink[confLinks.size()]);
    result[DocumentKind.BASE.ordinal()] = baseLinks.toArray(new DocTypeDocSectionLink[baseLinks.size()]);
    result[DocumentKind.CONTRACT.ordinal()] = contractLinks.toArray(new DocTypeDocSectionLink[contractLinks.size()]);
    return result;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeServiceLocal#createDocSectionLinks(com.mg.merp.document.model.DocSection[], com.mg.merp.document.model.DocType, com.mg.merp.document.model.DocumentKind)
   */
  public void createDocSectionLinks(DocTypeDocSectionLink[] links) {
    for (DocTypeDocSectionLink link : links)
      ServerUtils.getPersistentManager().persist(link);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocTypeServiceLocal#removeDocSectionLinks(com.mg.merp.document.model.DocTypeDocSectionLink[])
   */
  public void removeDocSectionLinks(DocTypeDocSectionLink[] links) {
    for (DocTypeDocSectionLink link : links)
      ServerUtils.getPersistentManager().remove(link);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(DocType entity) {
    entity.setCode(DataUtils.generateUniqueString(15));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(DocType entity, DocType entityClone) {
    List<DocProcessStage> clonedDocProcessStages = new ArrayList<DocProcessStage>();
    List<DocProcessStageRights> clonedDocProcessStageRights = new ArrayList<DocProcessStageRights>();
    List<LinkStage> clonedLinkStages = new ArrayList<LinkStage>();

    // копирование связей типов документов с классами документов
    List<DocTypeDocSectionLink> clonedDocTypeDocSectionLinks = cloneDocTypeDocSectionLink(entity, entityClone);

    List<DocProcessStage> docProcessStages = getDocProcessStages(entity);
    for (DocProcessStage docProcessStage : docProcessStages) {
      // копирование этапов ДО
      DocProcessStage clonedDocProcessStage = cloneDocProcessStage(docProcessStage, entityClone);
      clonedDocProcessStages.add(clonedDocProcessStage);
      // копирование прав на этапы
      clonedDocProcessStageRights.addAll(cloneUserGrants(docProcessStage.getUserGrants(), clonedDocProcessStage));
    }
    // копирование связей этапов
    for (int i = 0; i < clonedDocProcessStages.size(); i++)
      clonedLinkStages.addAll(cloneLinkStages(clonedDocProcessStages.get(i), docProcessStages.get(i).getNextStages(), docProcessStages, clonedDocProcessStages));

    createDocSectionLinks(clonedDocTypeDocSectionLinks.toArray(new DocTypeDocSectionLink[clonedDocTypeDocSectionLinks.size()]));
    createDocProcessStages(clonedDocProcessStages);
    createDocProcessStageRights(clonedDocProcessStageRights);
    createLinkStages(clonedLinkStages);
  }

  /**
   * Выполнить копирование связей между этапами ДО
   *
   * @param clonedDocProcessStage  - этап ДО
   * @param nextStages             - связи этапа
   * @param docProcessStages       - список этапов ДО
   * @param clonedDocProcessStages - список копий этапов ДО
   * @return список связей между этапами ДО
   */
  protected List<LinkStage> cloneLinkStages(DocProcessStage clonedDocProcessStage, Set<LinkStage> nextStages, List<DocProcessStage> docProcessStages, List<DocProcessStage> clonedDocProcessStages) {
    List<LinkStage> clonedLinkStages = new ArrayList<LinkStage>();
    for (LinkStage linkStage : nextStages) {
      AttributeMap initAttr = linkStage.getAllAttributes();
      initAttr.remove("Id"); //$NON-NLS-1$
      initAttr.put("NextStage", clonedDocProcessStages.get(docProcessStages.indexOf(linkStage.getNextStage()))); //$NON-NLS-1$
      initAttr.put("PrevStage", clonedDocProcessStage); //$NON-NLS-1$
      clonedLinkStages.add((LinkStage) linkStage.cloneEntity(initAttr));
    }
    return clonedLinkStages;
  }

  /**
   * Выполнить копирование этапа ДО
   *
   * @param docProcessStage - этап ДО
   * @param docType         - тип документа
   * @return объект-копия этапа ДО
   */
  protected DocProcessStage cloneDocProcessStage(DocProcessStage docProcessStage, DocType docType) {
    AttributeMap initAttr = docProcessStage.getAllAttributes();
    initAttr.remove("Id"); //$NON-NLS-1$
    initAttr.remove("PrevStages"); //$NON-NLS-1$
    initAttr.remove("NextStages"); //$NON-NLS-1$
    initAttr.remove("UserGrants"); //$NON-NLS-1$
    initAttr.put("DocType", docType); //$NON-NLS-1$
    return (DocProcessStage) docProcessStage.cloneEntity(initAttr);
  }

  /**
   * Выполнить копирование связей типов документов с классами документов
   *
   * @param entity      - тип документа
   * @param entityClone - копия типа документа
   * @return список объектов-копий связей типов документов с классами документов
   */
  protected List<DocTypeDocSectionLink> cloneDocTypeDocSectionLink(DocType entity, DocType entityClone) {
    DocTypeDocSectionLink[][] docTypeDocSectionLinks = loadDocSectionLinks(entity);
    List<DocTypeDocSectionLink> clonedDocTypeDocSectionLinks = new ArrayList<DocTypeDocSectionLink>();
    for (DocTypeDocSectionLink[] docKinds : docTypeDocSectionLinks) {
      for (DocTypeDocSectionLink docTypeDocSectionLink : docKinds) {
        DocTypeDocSectionLink clonedDocTypeDocSectionLink = new DocTypeDocSectionLink();
        AttributeMap initAttr = docTypeDocSectionLink.getAllAttributes();
        initAttr.remove("Id"); //$NON-NLS-1$
        initAttr.put("DocType", entityClone); //$NON-NLS-1$
        clonedDocTypeDocSectionLinks.add((DocTypeDocSectionLink) clonedDocTypeDocSectionLink.cloneEntity(initAttr));
      }
    }
    return clonedDocTypeDocSectionLinks;
  }

  /**
   * Выполнить копирование прав доступа на этап ДО
   *
   * @param docProcessStageRights - права доступа на этап ДО
   * @param docProcessStage       - этап ДО
   * @return список прав доступа на этап ДО
   */
  protected List<DocProcessStageRights> cloneUserGrants(Set<DocProcessStageRights> docProcessStageRights, DocProcessStage docProcessStage) {
    List<DocProcessStageRights> clonedDocProcessStageRights = new ArrayList<DocProcessStageRights>();
    for (DocProcessStageRights docProcessStageRight : docProcessStageRights) {
      AttributeMap initAttr = docProcessStageRight.getAllAttributes();
      initAttr.remove("Id"); //$NON-NLS-1$
      initAttr.put("DocProcessStage", docProcessStage); //$NON-NLS-1$
      clonedDocProcessStageRights.add((DocProcessStageRights) docProcessStageRight.cloneEntity(initAttr));
    }
    return clonedDocProcessStageRights;
  }

  /**
   * Получить список этапов ДО для типа документа
   *
   * @param docType - тип документа
   * @return список этапов ДО для типа документа
   */
  protected List<DocProcessStage> getDocProcessStages(DocType docType) {
    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocProcessStage.class)
        .add(Restrictions.eq("DocType", docType))); //$NON-NLS-1$
  }

  protected void createDocProcessStages(List<DocProcessStage> docProcessStages) {
    for (DocProcessStage docProcessStage : docProcessStages)
      ServerUtils.getPersistentManager().persist(docProcessStage);
  }

  protected void createDocProcessStageRights(List<DocProcessStageRights> docProcessStageRights) {
    for (DocProcessStageRights docProcessStageRight : docProcessStageRights)
      ServerUtils.getPersistentManager().persist(docProcessStageRight);
  }

  protected void createLinkStages(List<LinkStage> linkStages) {
    for (LinkStage linkStage : linkStages)
      ServerUtils.getPersistentManager().persist(linkStage);
  }

}
