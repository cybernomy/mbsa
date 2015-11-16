/*
 * DocFlowManagerImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.Session;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.security.LicensedAction;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.LicenseControllerLocator;
import com.mg.framework.service.LockManagerLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.AlreadyCompletedException;
import com.mg.merp.docflow.ChooseDocumentPatternListener;
import com.mg.merp.docflow.ChooseDocumentStateListener;
import com.mg.merp.docflow.ChooseFolderListener;
import com.mg.merp.docflow.ChooseNextStageListener;
import com.mg.merp.docflow.DocFlowListener;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowParamsStrategy;
import com.mg.merp.docflow.DocFlowPluginEvent;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocFlowPluginListener;
import com.mg.merp.docflow.DocumentNotFound;
import com.mg.merp.docflow.DocumentOwnerMismatch;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.InaccesibleStateException;
import com.mg.merp.docflow.InputDocumentParamsListener;
import com.mg.merp.docflow.RollbackNotAllowedException;
import com.mg.merp.docprocess.model.ActionType;
import com.mg.merp.docprocess.model.ActionUserGrant;
import com.mg.merp.docprocess.model.DocAction;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.DocProcessStageRights;
import com.mg.merp.docprocess.model.DocSpecState;
import com.mg.merp.docprocess.model.LinkStage;
import com.mg.merp.docprocess.model.StageState;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.MeasureControl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Реализация сервиса менеджера документооборота
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowManagerImpl.java,v 1.41 2009/02/25 08:46:10 safonov Exp $
 */
@Stateful(name = "merp/docflow/DocFlowManagerService")
public class DocFlowManagerImpl extends AbstractPOJOBusinessObjectStatefulServiceBean implements DocFlowManager {
  private static final String LOAD_CREATE_DOCFLOW_STAGE_SQL = "select d.id from docprocessstage d, typedoc t, doctype_docsection_link ddl where (t.id = ?) and (t.id = d.doctype_id) and (d.stage = ?) and (ddl.kind = 0) and (ddl.doctype_id = t.id) and (ddl.docsection_id = ?)";
  private static final String UPDATE_DOCACTION_STAGE_SQL = "update docaction set stage_id = ? where (actiontype = 0) and (document_id = ?)";
  private static final String COUNT_DOCACTION_SQL = "select count(*) from docaction da where (da.document_id = ?) and (da.actiontype = 0)";
  //private final static String LOAD_READY_DOC_SUM_EJBQL = "select sum(dhs.ReadySum) from DocHeadState dhs where (dhs.DocAction = :docAction)";
  //private final static String LOAD_DOCUMENT_EJBQL = "from DocHead where Id = :id";
  //private final static String LOAD_DOCUMENT_SPEC_EJBQL = "from DocSpec spec where spec.DocHead = :doc";
  //private final static String LOAD_DOCACTION_EJBQL = "from DocAction action where action.DocHead = :doc and action.Stage is not null order by action";
  //private final static String LOAD_DOCFLOW_MAP_EJBQL = "from DocProcessStage stage where stage.DocType = :docType";
  //private final static String LOAD_DOCHEAD_STATE_EJBQL = "from DocHeadState state where state.DocAction = :docAction";
  //private final static String LOAD_DOCSPEC_STATE_BY_DOCACTION_EJBQL = "from DocSpecState state where state.DocHeadState.DocAction = :docAction";
  //private final static String LOAD_DOCSPEC_STATE_BY_HEADSTATE_EJBQL = "from DocSpecState state where state.DocHeadState = :headState";
  //private final static String LOAD_READY_DOCSPEC_EJBQL = "select new com.mg.merp.docflow.support.ReadyDocSpec(dss.DocSpec.Id, sum(dss.ReadySum), sum(dss.ReadyQuantity1), sum(dss.ReadyQuantity2)) from DocSpecState as dss where dss.DocHeadState.DocAction = :docAction group by dss.DocSpec";
  protected final Set<StageState> partialAndComplete = EnumSet.of(StageState.PARTITION, StageState.COMPLETE);
  //private int documentOwner = 0;
  protected List<DocAction> docActions = null;
  protected List<DocProcessStage> nextActions = new ArrayList<DocProcessStage>();
  private Logger logger = ServerUtils.getLogger(DocFlowManagerImpl.class);
  private DocFlowListener flowListener = null;
  //private DocFlowPluginInvoker docFlowPluginInvoker = null;
  private DocHead document = null;
  private int oldDocumentOwner = 0;
  private List<DocProcessStage> docFlowMap = null;
  private DocProcessStage startStage = null;
  private Date processDate = null;
  private boolean performForStage = false;
  private boolean isBulkProcess = false;
  private boolean isUseDocDateForBulk = false;
  private boolean isSilent = false;
  private boolean isBulkPartial = false;
  private String stageCodeForBulk = null;
  private DocProcessStage stageForBulk = null;
  private Folder folderForBulk = null;
  private Integer docPatternForBulk = null;
  private DocFlowParamsStrategy paramsStrategy = null;
  private OrmTemplate ormTemplate = null;
  private boolean isCanceledDocFlow = false;
  private boolean isCompletedDocFlow = false;
  private Comparator<DocProcessStage> docProcessStageComparator = new Comparator<DocProcessStage>() {

    public int compare(DocProcessStage o1, DocProcessStage o2) {
      return o1.getPriority().compareTo(o2.getPriority()); //приоритеты должны быть не null
    }

  };

  //current state
  private DocProcessStage currentStage = null;
  private StageState currentStageState = StageState.NONE;
  private BigDecimal currentReadySum = null;
  //private Date currentProcessDate = null;
  //private BigDecimal performedDocSum = null;
  private boolean currentFullRollback = false;
  private DocHeadState currentHeadState = null;

	/*private class DocFlowAction {
        DocProcessStage mapStage = null;
		DocFlowAction prevStage = null;
		StageState state;
		Set<DocFlowAction> nextActions = new HashSet<DocFlowAction>();
		Set<DocFlowAction> prevActions = new HashSet<DocFlowAction>();
		
		DocFlowAction(DocProcessStage mapStage) {
			this.mapStage = mapStage;
			
			for (LinkStage linkStage : mapStage.getSetOfNextStages())
				nextActions.add(new DocFlowAction(linkStage.getNextStage()));
			
			for (LinkStage linkStage : mapStage.getSetOfPrevStages())
				prevActions.add(new DocFlowAction(linkStage.getPrevStage()));
		}
	}*/

  private OrmTemplate getOrmTemplate() {
    if (ormTemplate == null)
      ormTemplate = OrmTemplate.getInstance();
    return ormTemplate;
  }

  @SuppressWarnings("unchecked")
  private DocHead loadDocument(Serializable documentId) {
    //загрузим идентификатор раздела документа, данный объект будет почти всегда в кэше
    List<Integer> list = MiscUtils.convertUncheckedList(Integer.class, getOrmTemplate().findByNamedParam("select dh.DocSection.Id from DocHead dh where dh.Id = :docId", "docId", documentId));
    if (list.isEmpty())
      throw new IllegalStateException("DocSection is null in document " + documentId);
    DocSection docSection = ServerUtils.getPersistentManager().find(DocSection.class, list.get(0));
    //используем сервис документа для загрузки реального объекта заголовка документа,
    //исключаем очень сложный запрос который образуется вследствии неявного полиморфизма в мапинге документов
    DocHead docHead = (DocHead) DocumentUtils.getDocumentService(docSection).load((Integer) documentId);
    if (docHead == null)
      throw new DocumentNotFound();
    return docHead;
  }

  private DocHead loadDocument(DocHead document) {
    if (document == null)
      throw new IllegalStateException("Document is null");
    PersistentManager pm = ServerUtils.getPersistentManager();
    //присоединим к текущей сессии с помощью merge, была попытка использовать lock,
    //но возникли проблемы http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4569, http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4566
    //т.к. если сущность изменилась и используя lock измененные поля не считаются новым состоянием сущности, что верно
    //merge сливает состояния, однако конечно по сути происходит обновление сущности документа минуя сервис бизнес-компонента
    if (!pm.contains(document))
      return pm.merge(document);
    return document;
  }

  private List<DocAction> loadDocActions() {
    return getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocAction.class).add(Restrictions.and(Restrictions.eq("DocHead", document), Restrictions.isNotNull("Stage"))).addOrder(Order.asc("Id")));
  }

  private List<DocProcessStage> loadDocFlowMap() {
    List<DocProcessStage> map = getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocProcessStage.class).add(Restrictions.eq("DocType", document.getDocType())));
//		List<DocFlowAction> result = new ArrayList<DocFlowAction>();
//		for (DocProcessStage mapStage : map)
//			result.add(new DocFlowAction(mapStage));
    return map;
  }

  private List<DocHeadState> loadDocHeadStates(DocAction action) {
    return getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocHeadState.class).add(Restrictions.eq("DocAction", action)));
  }

  private BigDecimal getReadyDocumentSumma(DocAction docAction) {
    List<BigDecimal> list = getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocHeadState.class).add(Restrictions.eq("DocAction", docAction)).setProjection(Projections.sum("ReadySum")));

//		if (list == null)
//			return new BigDecimal(0.0);
//		else {
//			BigDecimal result = list.get(0);
//			return result != null ? result : BigDecimal.ZERO;
//		}
    BigDecimal result = list.get(0);
    return result != null ? result : BigDecimal.ZERO;
  }

  private List<DocSpecState> loadDocSpecStates(DocAction docAction) {
    return getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocSpecState.class).createAlias("DocHeadState", "dhs").add(Restrictions.eq("dhs.DocAction", docAction)));
  }

  private List<DocSpecState> loadDocSpecStates(DocHeadState headState) {
    return getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocSpecState.class).add(Restrictions.eq("DocHeadState", headState)));
  }

  private List<ReadyDocSpec> loadReadyDocSpec(DocAction docAction) {
    return getOrmTemplate().findByCriteria(OrmTemplate.createCriteria(DocSpecState.class).createAlias("DocHeadState", "dhs")
        .add(Restrictions.eq("dhs.DocAction", docAction))
        .setProjection(Projections.projectionList(Projections.groupProperty("DocSpec.Id"), Projections.sum("ReadySum"), Projections.sum("ReadyQuantity1"), Projections.sum("ReadyQuantity2")))
        .setResultTransformer(new ResultTransformer<ReadyDocSpec>() {

          public ReadyDocSpec transformTuple(Object[] tuple, String[] aliases) {
            return new ReadyDocSpec((Integer) tuple[0], (BigDecimal) tuple[1], (BigDecimal) tuple[2], (BigDecimal) tuple[3]);
          }

        }));
  }

  private List<DocumentSpecItem> loadDocSpecListItem(DocAction docAction) {
    List<DocumentSpecItem> result = new ArrayList<DocumentSpecItem>();

    List<DocSpecState> specStates = null;
    if (docAction != null)
      specStates = loadDocSpecStates(docAction);

    //используем сервис спецификации документа для загрузки реальных объектов спецификаций документа,
    //исключаем очень сложный запрос который образуется вследствии неявного полиморфизма в мапинге спецификаций документов
    List<DocSpec> docSpecs = DocumentUtils.getGoodsDocumentSpecificationService(document.getDocSection()).findByCriteria(Restrictions.eq("DocHead", document));
    for (DocSpec docSpec : docSpecs) {
      BigDecimal freeQuantity1 = docSpec.getQuantity();
      BigDecimal freeQuantity2 = docSpec.getQuantity2();
      BigDecimal freeSum = docSpec.getSumma();
      BigDecimal readyQuantity1 = BigDecimal.ZERO;
      BigDecimal readyQuantity2 = BigDecimal.ZERO;
      BigDecimal readySum = BigDecimal.ZERO;
//			Integer data1 = null;
//			Integer data2 = null;
//			Serializable specStateValue;

      if (specStates != null) {
        for (DocSpecState specState : specStates) {
          if (specState.getDocSpec().getId().equals(docSpec.getId())) {
            readyQuantity1 = readyQuantity1.add(specState.getReadyQuantity1());
            readyQuantity2 = readyQuantity2.add(specState.getReadyQuantity2());
            readySum = readySum.add(specState.getReadySum());
          }
        }
      }

      freeQuantity1 = freeQuantity1 == null ? BigDecimal.ZERO : freeQuantity1.subtract(readyQuantity1);
      freeQuantity2 = freeQuantity2 == null ? BigDecimal.ZERO : freeQuantity2.subtract(readyQuantity2);
      freeSum = freeSum == null ? BigDecimal.ZERO : freeSum.subtract(readySum);

      if (MathUtils.compareToZero(freeSum) != 0 ||
          MathUtils.compareToZero(freeQuantity1) != 0 ||
          //учетно - весовые, надо проверить и вторую ЕИ
          MeasureControl.CATCHWEIGHT.equals(docSpec.getCatalog().getMeasureControl()) && (MathUtils.compareToZero(freeQuantity1) != 0 || MathUtils.compareToZero(freeQuantity2) != 0)) {
        DocumentSpecItem specItem = new DocumentSpecItem(docSpec, freeQuantity1, freeQuantity2, freeSum, readyQuantity1, readyQuantity2, readySum);
        result.add(specItem);
      }
    }

    return result;
  }

  private DocProcessStage getCreateDocFlowStage(DocHead docHead) {
    int stageId = docHead.getBaseDocument() == null ? DOC_FLOW_CREATE_DOC : DOC_FLOW_BASED_ON;
    List<Integer> stages = JdbcTemplate.getInstance().query(LOAD_CREATE_DOCFLOW_STAGE_SQL,
        new Object[]{docHead.getDocType().getId(), stageId, docHead.getDocSection().getId()}, new RowMapper<Integer>() {
          public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt(1);
          }
        });
    if (stages.size() == 0)
      throw new IllegalStateException(String.format("Started docflow stage is not found, document id: %s", docHead.getId()));
    DocProcessStage result = ServerUtils.getPersistentManager().find(DocProcessStage.class, stages.get(0));
    return result;
  }

  private void prepareDocFlowMap() {
    //установим состояние узлов
    for (DocProcessStage docFlowAction : docFlowMap) {
      for (DocAction docAction : docActions)
        if (docFlowAction.getId() == docAction.getStage().getId())
          docFlowAction.setState(docAction.getStageState());
    }

    //установим текущий узел
    int count = docFlowMap.size();
    int idx = 0, actionCount = docActions.size() - 1;
    if (actionCount != -1) {
      while ((idx <= count) && (docActions.get(actionCount).getStage().getId() != docFlowMap.get(idx).getId())) {
        idx++;
      }
      if (idx <= count)
        startStage = docFlowMap.get(idx);
    } else {
      int id = document.getBaseDocument() == null ? DOC_FLOW_CREATE_DOC : DOC_FLOW_BASED_ON;
      for (DocProcessStage docFlowAction : docFlowMap)
        if (docFlowAction.getStage().getId() == id) {
          startStage = docFlowAction;
          break;
        }
    }
  }

  private void fireFlowPerformed() {
    if (flowListener != null)
      flowListener.performed();
  }

  private void fireFlowCalceled() {
    if (flowListener != null)
      flowListener.canceled();
  }

  private void lockDocument() {
    assert document != null;
    LockManagerLocator.locate().lock(document);
  }

  private void unlockDocument() {
    if (document != null)
      LockManagerLocator.locate().unlock(document);
  }

  private Date checkProcessDate(final Date date) {
    Date result = (Date) LicenseControllerLocator.locate().doAsLicensed(new LicensedAction<Date>() {
      public Date run() {
        return date;
      }
    });
    return result == null ? DateTimeUtils.convertToTolerance(date) : date;
  }

  private void checkOwner(int documentOwner) {
    if (documentOwner < document.getRequester())
      throw new DocumentOwnerMismatch();
  }

  private void addAuditEvent(DocHead docHead, String operation) {
    String docType = docHead.getDocType() == null ? "null" : docHead.getDocType().getCode().trim();
    String docNumber = docHead.getDocNumber() == null ? "null" : docHead.getDocNumber().trim();
    ServerUtils.addSystemAuditEvent(getBusinessServiceMetadata().getName(), operation, String.format("Id: %d; DocType: %s; DocDate: %s; DocNumber: %s", document.getId(), docType, docHead.getDocDate(), docNumber));
  }

  private void internalInitialization(Serializable documentId, int documentOwner) {
    document = loadDocument(documentId);

    checkOwner(documentOwner);

    //параметр тихий зависит от наличия сессии и ее интерактивности, если сессия не интерактивна,
    //то выполнение ДО должно быть тихим
    isSilent = isSilent || ServerUtils.getCurrentSession() == null || !ServerUtils.getCurrentSession().isInteractive();

    lockDocument();
    try {
      this.oldDocumentOwner = DocumentUtils.getCurrentDocumentOwner();
      if (this.oldDocumentOwner < documentOwner)
        DocumentUtils.setCurrentDocumentOwner((short) documentOwner);
      //this.documentOwner = documentOwner;

      if (stageCodeForBulk != null) {
        stageForBulk = findStageByCode(stageCodeForBulk);
        if (stageForBulk == null)
          throw new InaccesibleStateException(stageCodeForBulk, document);
        performForStage = true;
      }

      if (paramsStrategy == null) {
        if (isSilent)
          paramsStrategy = new DefaultSilentParamsStrategy();
        else if (isBulkProcess)
          paramsStrategy = new DefaultBulkDocFlowParamsStrategy(isBulkPartial, stageForBulk, folderForBulk, docPatternForBulk);
        else
          paramsStrategy = new DefaultInteractiveParamsStrategy();
      }

      if ((!isBulkProcess || isBulkProcess && isUseDocDateForBulk) &&
          (!performForStage || performForStage && processDate == null))
        processDate = document.getDocDate();

      processDate = checkProcessDate(processDate);
      docActions = loadDocActions();
      docFlowMap = loadDocFlowMap();
      prepareDocFlowMap();
    } catch (RuntimeException e) {
      DocumentUtils.setCurrentDocumentOwner((short) this.oldDocumentOwner);
      unlockDocument();
      throw e;
    }
  }

  private void internalFinalization() {
    DocumentUtils.setCurrentDocumentOwner((short) this.oldDocumentOwner);
    unlockDocument();
    document = null;
  }

  private void checkEndState() throws AlreadyCompletedException {
    if (nextActions.isEmpty())
      throw new AlreadyCompletedException(document);

    //если среди выполненных есть этап завершения ДО то выбрасываем ИС
    for (DocAction docAction : docActions)
      if (docAction.getStage().getStage().getId() == DOC_FLOW_END)
        throw new AlreadyCompletedException(document);
  }

  private void doExecute() {
    prepareStageListForExecute();

    checkEndState();

    doProcess1(processDate, performForStage);
  }

  private void checkStartState() throws RollbackNotAllowedException {
    if (nextActions.isEmpty())
      throw new RollbackNotAllowedException();
  }

  private void doRollback() {
    prepareStageListForRollback();

    checkStartState();

    doRollbackProcess1(processDate, performForStage, true, true);
  }

  private boolean checkPermission(DocProcessStage action, ActionUserGrant grant) {
    return checkPermission(action, grant, ServerUtils.getUserProfile().getGroups());
  }

  protected boolean checkPermission(DocProcessStage action, ActionUserGrant grant, int[] groups) {
    int len = groups.length;
    for (DocProcessStageRights item : action.getUserGrants()) {
      for (int i = 0; i < len; i++)
        if (item.getSecGroups().getId() == groups[i] && EnumSet.of(grant, ActionUserGrant.BOTH).contains(item.getGrants()))
          return true;
    }
    return false;
  }

  private boolean addToNextActionsList(DocProcessStage action, DocProcessStage fromAction) {
    boolean result = !nextActions.contains(action) && checkPermission(action, ActionUserGrant.EXECUTE);

    if (result) {
      action.setPrevStage(fromAction);
      nextActions.add(action);
    }

    return result;
  }

  protected boolean haveIsComplete(Set<LinkStage> nextStage) {
    boolean result = false;
    if (!nextStage.isEmpty())
      for (LinkStage linkStage : nextStage) {
        result = partialAndComplete.contains(linkStage.getNextStage().getState());
        if (result)
          break;
      }
    return result;
  }

  private boolean allPrevIsComplete(DocProcessStage action, Set<StageState> states) {
    boolean result = true;
    if (!action.getPrevStages().isEmpty())
      for (LinkStage linkStage : action.getPrevStages()) {
        result = result && states.contains(linkStage.getPrevStage().getState());
        if (!result)
          break;
      }
    return result;
  }

  private void bypassDown(DocProcessStage prevAction) {
    boolean hIsC = haveIsComplete(prevAction.getNextStages());
    boolean addFl = false;

    for (LinkStage linkStage : prevAction.getNextStages()) {
      DocProcessStage nextStage = linkStage.getNextStage();
      //если "делитель" OR и есть след. выполненный частично, то добавим его
      if (hIsC && prevAction.isForkFlow()) {
        if (partialAndComplete.contains(prevAction.getState()) &&
            nextStage.isPrevComplete() && StageState.PARTITION.equals(nextStage.getStage())) {
          addFl = addToNextActionsList(nextStage, prevAction);
        }
      } else if (!nextStage.isJoinFlow()) {
        if (StageState.COMPLETE.equals(prevAction.getState()) && nextStage.isPrevComplete() || //предыдущий пройден полностью
            partialAndComplete.contains(prevAction.getState()) && !nextStage.isPrevComplete() && //предыдущий пройден полностью или частично
                !StageState.COMPLETE.equals(nextStage.getState())) {
          addFl = addToNextActionsList(nextStage, prevAction);
        }
      }
      //если сумматор "AND"
      else if (allPrevIsComplete(nextStage, EnumSet.of(StageState.COMPLETE)) && nextStage.isPrevComplete() ||
          allPrevIsComplete(nextStage, partialAndComplete) && !nextStage.isPrevComplete()) {
        addFl = addToNextActionsList(nextStage, prevAction);
      }

      //если выполнен то "спускаемся" дальше
      if (partialAndComplete.contains(nextStage.getState()))
        bypassDown(nextStage);

      //если узел "делитель" OR, то в список может попасть только один выполненный частично
      if (prevAction.isForkFlow() && addFl && hIsC)
        break;
    }
  }

  private void bypassUp(DocProcessStage nextAction, DocProcessStage fromAction) {
    for (LinkStage linkStage : nextAction.getPrevStages()) {
      //пойдем вверх туда, откудо пришли и если узел не "сумматор" AND
      if (!nextAction.isJoinFlow() && partialAndComplete.contains(linkStage.getPrevStage().getState()))
        bypassUp(linkStage.getPrevStage(), nextAction);
    }
    if (StageState.PARTITION.equals(nextAction.getState()))
      addToNextActionsList(nextAction, null);
    //пришли из ниоткуда
    if (fromAction != null) {
      for (LinkStage linkStage : nextAction.getNextStages())
        //пойдем вниз, то только ни туда откуда пришли
        if (fromAction.getId() != linkStage.getNextStage().getId())
          bypassDown(nextAction);
    }
  }

  private void prepareStageListForExecute() {
    assert startStage != null;

    nextActions.clear();

    //если текущий этап выполнен частично или не выполнен, то он первый кондидат на выполнение
    if (startStage.getState() != StageState.COMPLETE)
      addToNextActionsList(startStage, null);

    //начинаем обход "вниз"
    bypassDown(startStage);
    //начинаем обход "вверх"
    bypassUp(startStage, null);
  }

  private void prepareStageListForRollback() {
    nextActions.clear();
    //откатываем этапы у которых нет следующих выполненных
    for (DocProcessStage stage : docFlowMap) {
      if (partialAndComplete.contains(stage.getState()) && !haveIsComplete(stage.getNextStages()) &&
          stage.getStage().getId() != DOC_FLOW_CREATE_DOC && stage.getStage().getId() != DOC_FLOW_BASED_ON &&
          checkPermission(stage, ActionUserGrant.ROLLBACK))
        nextActions.add(stage);
    }
  }

  private void internalExecute(Serializable documentId, int documentOwner) {
    internalInitialization(documentId, documentOwner);
    try {
      addAuditEvent(document, "execute");
      doExecute();
    } catch (RuntimeException rte) {
      internalFinalization();
      throw rte;
    }
  }

  private void internalRollback(Serializable documentId, int documentOwner) {
    internalInitialization(documentId, documentOwner);
    try {
      addAuditEvent(document, "rollback");
      doRollback();
    } catch (RuntimeException rte) {
      internalFinalization();
      throw rte;
    }
  }

  protected boolean isDirectly(DocProcessStage prev, DocProcessStage next) {
    boolean result = false;
    if (prev != next) {
      for (LinkStage linkStage : prev.getNextStages())
        if (linkStage.getNextStage() == next) {
          result = linkStage.isDirectly();
          break;
        }
    }
    return result;
  }

  protected DocAction findDocState(DocHead docHead, DocProcessStage stage) {
    for (DocAction item : docActions)
      if (item.getDocHead().getId().equals(docHead.getId()) && item.getStage().getId().equals(stage.getId()))
        return item;

    return null;
  }

  protected DocProcessStage getDependensOnStage(DocProcessStage performedStage) {
    if (performedStage.isDependent())
      for (LinkStage link : performedStage.getPrevStages()) {
        DocProcessStage prevStage = link.getPrevStage();
        if (partialAndComplete.contains(prevStage.getState()) &&
            prevStage.getStage().getId() != DOC_FLOW_CREATE_DOC && prevStage.getStage().getId() != DOC_FLOW_BASED_ON)
          return prevStage;
      }
    return null;
  }

  private void loadDocumentSpecList(final Date processDate, final DocProcessStage performedStage, BigDecimal docSum, DocProcessStage dependensOnStage) {
    DocAction action = findDocState(document, performedStage);
    List<DocumentSpecItem> specList = loadDocSpecListItem(action);

    if (dependensOnStage != null) {
      //зависим от предыдущего
      List<ReadyDocSpec> readySpecs = loadReadyDocSpec(findDocState(document, dependensOnStage));
      List<DocumentSpecItem> tmpSpecList = new ArrayList<DocumentSpecItem>();
      tmpSpecList.addAll(specList);
      specList.clear();
      for (ReadyDocSpec readySpec : readySpecs) {
        for (DocumentSpecItem specItem : tmpSpecList)
          if (specItem.getDocSpec().getId().equals(readySpec.docSpecId) &&
              (MathUtils.compareToZero(readySpec.readyQuantity1) != 0 || MathUtils.compareToZero(readySpec.readyQuantity2) != 0)) {
            specList.add(new DocumentSpecItem(specItem.getDocSpec(),
                readySpec.readyQuantity1.subtract(specItem.getReadyQuantity1()), readySpec.readyQuantity2.subtract(specItem.getReadyQuantity2()), readySpec.readySum.subtract(specItem.getReadySum()),
                specItem.getReadyQuantity1(), specItem.getReadyQuantity2(), specItem.getReadySum()));
          }
      }
      tmpSpecList.clear();
    }

    paramsStrategy.inputDocumentSpecList(processDate, performedStage, docSum, specList, new InputDocumentParamsListener() {
      public void canceled() {
        cancelDocFlow();
      }

      public void performed(BigDecimal docSum, List<DocumentSpecItem> specList) {
        doProcess3(performedStage, processDate, docSum, specList);
      }
    });
  }

  private void loadDocumentSum(final Date processDate, final DocProcessStage performedStage, BigDecimal docSum, DocProcessStage dependensOnStage) {
    if (dependensOnStage != null) {
      //зависим от предыдущего, возмем минимальное между суммой документа и разницей выполнения
      //текущего этапа и этапа от которого он зависит
      DocAction action = findDocState(document, performedStage);
      docSum = docSum.min(getReadyDocumentSumma(findDocState(document, dependensOnStage)).subtract(getReadyDocumentSumma(action)));
    }

    if (performedStage.isPartial()) {
      //только для этапов не зависящих от предыдущего, т.к. для зависящих этапов сумма уже расчитана
      if (dependensOnStage == null) {
        DocAction action = findDocState(document, performedStage);
        docSum = docSum.subtract(getReadyDocumentSumma(action));
      }

      paramsStrategy.inputDocumentSum(processDate, performedStage, docSum, new InputDocumentParamsListener() {
        public void canceled() {
          cancelDocFlow();
        }

        public void performed(BigDecimal docSum, List<DocumentSpecItem> specList) {
          doProcess3(performedStage, processDate, docSum, null);
        }
      });
    } else
      doProcess3(performedStage, processDate, docSum, null);
  }

  protected boolean isCreateDocument(DocProcessStage performedStage) {
    return performedStage.getStage().getId() != CREATE_DOC_BY_BAI && performedStage.getStage().isCreateDoc();
  }

  private BigDecimal checkPartition(DocProcessStage performedStage, BigDecimal docSum, List<DocumentSpecItem> specList) {
    boolean isPartition;
    BigDecimal result;

    DocAction action = findDocState(document, performedStage);
    if (action == null)
      result = BigDecimal.ZERO;
    else
      result = getReadyDocumentSumma(action);
    BigDecimal tmpDocSum = document.getSumCur();
    if (tmpDocSum == null)
      tmpDocSum = BigDecimal.ZERO;
    isPartition = MathUtils.compareToZero(tmpDocSum.subtract(result).subtract(docSum)) != 0;

    if (MathUtils.compareToZero(tmpDocSum) == 0 &&
        document.getDocSection().isWithSpec()) {
      for (DocumentSpecItem item : specList) {
        //#2384 fixed, проверим на равенство, полученные значения могут превышать количества
        if (item.isPartition()) {
          isPartition = true;
          break;
        }
      }
    }

    if (/*performedStage.isPartial() &&*/ isPartition)
      currentStageState = StageState.PARTITION;

    return result;
  }

  protected DocProcessStage findNextStageByCode(String code) {
    for (DocProcessStage stage : nextActions) {
      if (stage.getCode().equals(code))
        return stage;
    }
    return null;
  }

  private DocProcessStage findStageByCode(String code) {
    return getOrmTemplate().findUniqueByCriteria(OrmTemplate.createCriteria(DocProcessStage.class)
        .add(Restrictions.eq("DocType", document.getDocType()))
        .add(Restrictions.eq("Code", code)));
  }

  private DocFlowPluginInvokeParams createInvokeParams(DocProcessStage performedStage, Date processDate, BigDecimal docSum, List<DocumentSpecItem> specList) {
    return new DocFlowPluginInvokeParams(document, processDate, performedStage, isSilent, docSum, specList);
  }

  private DocFlowPluginInvokeParams createInvokeParams(DocProcessStage performedStage, Date processDate, DocHeadState headState, List<DocumentSpecItem> specList) {
    DocFlowPluginInvokeParams result = new DocFlowPluginInvokeParams(document, processDate, performedStage, isSilent, null, specList);
    result.setHeadStateValue(headState.getStateValue());
    result.setData1(headState.getData1());
    result.setData2(headState.getData2());
    return result;
  }

  private DocProcessStage cloneDocProcessStage(DocProcessStage stage, Folder folder, Integer modelId) {
    DocProcessStage result = (DocProcessStage) stage.cloneEntity(null);
    result.setId(stage.getId());
    //replace by new values
    result.setLinkDocDestFolder(folder);
    result.setLinkDocModel(modelId);
    return result;
  }

  //старт процесса
  private void doProcess1(final Date processDate, final boolean performForStage) {
    //Date processDate = new Date(processDate.getTime());

    if (performForStage) {
      assert stageForBulk == null;
      DocProcessStage stage = findNextStageByCode(stageForBulk.getCode());
      if (stage != null)
        doProcess2(processDate, stage);
      else
        throw new InaccesibleStateException(stageForBulk.getCode(), document);
    } else {
      //если есть список следующих этапов или следует не непосредственно
      //то выдадим на  клиенте запрос о выборе следующего этапа
      int size = nextActions.size();
      Collections.sort(nextActions, docProcessStageComparator); //http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4406
      DocProcessStage aloneStage = size == 1 ? nextActions.get(0) : null;
      if (size > 1 || size == 1 && aloneStage.getPrevStage() == null ||
          (aloneStage.getPrevStage() != null && !isDirectly(aloneStage.getPrevStage(), aloneStage))) {
        paramsStrategy.chooseNextStage(document, processDate, nextActions, new ChooseNextStageListener() {
          public void canceled() {
            cancelDocFlow();
          }

          public void performed(Date processDate, DocProcessStage performedStage) {
            doProcess2(processDate, performedStage);
          }
        });
      } else {
        DocProcessStage performedStage = nextActions.get(0);
        //XXX данное условие не позволит выполнить этапы следующие непосредственно, необходима проверка итераций
        //при массовой отработке не идем по документообороту если не совпадают этапы
        if (isBulkProcess) {
          assert stageForBulk == null;
          if (stageForBulk.getId() != performedStage.getId())
            throw new IllegalStateException();
        }

        doProcess2(processDate, performedStage);
      }
    }
  }

  //подготовка, запрос списка спицификаций или суммы документа
  private void doProcess2(Date processDate, DocProcessStage performedStage) {
    BigDecimal docSum = document.getSumCur();
    if (performedStage.isUseCurrentDate())
      processDate = DateTimeUtils.nowDate();

    DocProcessStage dependensOnStage = getDependensOnStage(performedStage);

    //спросим дополнительные параметры если необходимо
    //сумма или список спецификаций документа, если возможна частичная отработка
    if (document.getDocSection().isWithSpec())
      loadDocumentSpecList(processDate, performedStage, docSum, dependensOnStage);
    else
      loadDocumentSum(processDate, performedStage, docSum, dependensOnStage);
  }

  //подготовка, запрос папки
  private void doProcess3(final DocProcessStage performedStage, final Date processDate, BigDecimal docSum, final List<DocumentSpecItem> specList) {
    if (specList != null) {
      //пересчитаем итоговую сумму к отработке на основании отмеченных спецификаций
      docSum = BigDecimal.ZERO;
      for (DocumentSpecItem specItem : specList)
        docSum = docSum.add(specItem.getFreeSum());
    }
    final BigDecimal tmpDocSum = docSum != null ? docSum : BigDecimal.ZERO;

    Folder folder = performedStage.getLinkDocDestFolder();
    if (isCreateDocument(performedStage) && folder == null) {
      paramsStrategy.chooseDestanationFolder(processDate, performedStage, tmpDocSum, specList, new ChooseFolderListener() {
        public void canceled() {
          cancelDocFlow();
        }

        public void performed(Folder folder) {
          doProcess4(performedStage, processDate, tmpDocSum, folder, specList);
        }
      });
    } else
      doProcess4(performedStage, processDate, tmpDocSum, folder, specList);
  }

  //подготовка, запрос образца
  private void doProcess4(final DocProcessStage performedStage, final Date processDate, final BigDecimal docSum, final Folder folder, final List<DocumentSpecItem> specList) {
    Integer modelId = performedStage.getLinkDocModel();
    if (isCreateDocument(performedStage) && modelId == null) {
      paramsStrategy.chooseDocumentPattern(processDate, performedStage, docSum, specList, new ChooseDocumentPatternListener() {
        public void canceled() {
          cancelDocFlow();
        }

        public void performed(Integer patternId) {
          doProcessFinal(performedStage, processDate, docSum, folder, patternId, specList);
        }
      });
    } else
      doProcessFinal(performedStage, processDate, docSum, folder, modelId, specList);
  }

  //завершение процесса выполнения
  private void doProcessFinal(DocProcessStage performedStage, Date processDate, BigDecimal docSum, Folder folder, Integer modelId, List<DocumentSpecItem> specList) {
    internalProcess(performedStage, processDate, docSum, folder, modelId, specList);
  }

  private void internalProcess(DocProcessStage performedStage, Date processDate, BigDecimal docSum, Folder folder, Integer modelId, List<DocumentSpecItem> specList) {
    DocProcessStage stage = cloneDocProcessStage(performedStage, folder, modelId);
    currentStage = performedStage;
    currentStageState = StageState.COMPLETE;
    //currentProcessDate = processDate;

    if (!stage.isDependent())
      currentReadySum = checkPartition(stage, docSum, specList);
    else
      currentReadySum = docSum;

    DocFlowPluginInvoker docFlowPluginInvoker = new DocFlowPluginInvoker(this, new DocFlowPluginListener() {

      public void performed(DocFlowPluginEvent event) {
        executionCompleted(event);
      }

      public void canceled() {
        executionCanceled();
      }

    });

    //в случае проблем отменяем ДО, т.к. невозможно восстановить состояние контекста которое было
    //до запуска данного этапа
    try {
      docFlowPluginInvoker.execute(createInvokeParams(stage, processDate, docSum, specList));
    } catch (ApplicationException ae) {
      cancelDocFlow();
      throw ae;
    } catch (Exception e) {
      cancelDocFlow();
      throw new ApplicationException(e);
    }
  }

  //старт отката
  private void doRollbackProcess1(final Date processDate, final boolean performForStage,
                                  final boolean first, final boolean directly) {
    if (performForStage) {
      assert stageForBulk == null;
      DocProcessStage stage = findNextStageByCode(stageForBulk.getCode());
      if (stage != null)
        doRollbackProcess2(processDate, stage);
      else
        throw new InaccesibleStateException(stageForBulk.getCode(), document);
    } else {
      if ((first || !directly))
        paramsStrategy.chooseNextStage(document, DateTimeUtils.getDayStart(DateTimeUtils.nowDate()), nextActions, new ChooseNextStageListener() {
          public void canceled() {
            cancelDocFlow();
          }

          public void performed(Date processDate, DocProcessStage performedStage) {
            doRollbackProcess2(processDate, performedStage);
          }
        });
      else
        doRollbackProcess2(processDate, nextActions.get(0));
    }
  }

  //подготовка отката, запрос действия для отката
  private void doRollbackProcess2(final Date processDate, final DocProcessStage performedStage) {
    DocAction action = findDocState(document, performedStage);

    final List<DocHeadState> headStates = loadDocHeadStates(action);
    final List<DocumentSpecItem> specList = null;

    if (headStates.size() > 1)
      //спросим у юзера какую часть откатить если их много
      paramsStrategy.chooseDocHeadState(processDate, performedStage, headStates, specList, new ChooseDocumentStateListener() {
        public void canceled() {
          cancelDocFlow();
        }

        public void performed(Integer stateId) {
          boolean find = false;
          for (DocHeadState state : headStates)
            if (state.getId().equals(stateId)) {
              find = true;
              doRollbackProcessFinal(processDate, performedStage, state, false, specList);
              break;
            }
          if (!find)
            throw new IllegalStateException();
        }
      });
      //chooseDocHeadState(processDate, performedStage, action, headStates, specList);
    else
      doRollbackProcessFinal(processDate, performedStage, headStates.get(0), true, specList);
  }

  //завершение процесса отката
  private void doRollbackProcessFinal(Date processDate, DocProcessStage performedStage, DocHeadState headState, boolean fullRollback, List<DocumentSpecItem> specList) {
    //загрузим состояние спецификаций
    List<DocumentSpecItem> specList1 = new ArrayList<DocumentSpecItem>();
    List<DocSpecState> specStateList = loadDocSpecStates(headState);
    for (DocSpecState specState : specStateList) {
      DocumentSpecItem item = new DocumentSpecItem(specState.getDocSpec(), specState.getReadyQuantity1(),
          specState.getReadyQuantity2(), specState.getReadySum(), specState.getData1(), specState.getData2(), specState.getStateValue());
      specList1.add(item);
    }

    internalRollbackProcess(processDate, performedStage, headState, fullRollback, specList1);
  }

  private void internalRollbackProcess(Date processDate, DocProcessStage performedStage, DocHeadState headState, boolean fullRollback, List<DocumentSpecItem> specList) {
    currentStage = performedStage;
    currentFullRollback = fullRollback;
    currentHeadState = headState;

    DocFlowPluginInvoker docFlowPluginInvoker = new DocFlowPluginInvoker(this, new DocFlowPluginListener() {

      public void performed(DocFlowPluginEvent event) {
        rollbackCompleted(event);
      }

      public void canceled() {
        rollbackCanceled();
      }

    });

    //в случае проблем отменяем ДО, т.к. невозможно восстановить состояние контекста которое было
    //до запуска данного этапа
    try {
      docFlowPluginInvoker.rollback(createInvokeParams(performedStage, processDate, headState, specList));
    } catch (ApplicationException ae) {
      cancelDocFlow();
      throw ae;
    } catch (Exception e) {
      cancelDocFlow();
      throw new ApplicationException(e);
    }
  }

  private void executionCompleted(DocFlowPluginEvent event) {
    //производим сброс в хранилище данных, если генерируется ИС то отменяем выполнение ДО, т.к.
    //невозможно восстановить контекст до состояния которое было до запуска этапа
    try {
      ServerUtils.getPersistentManager().flush();
    } catch (RuntimeException e) {
      cancelDocFlow();
      throw e;
    }

    if (logger.isDebugEnabled())
      logger.debug("Documents flow execution completed for: ".concat(document.getId().toString()));

    BigDecimal docSum = document.getSumCur();
    if (docSum == null)
      docSum = BigDecimal.ZERO;
    if (/*currentStage.isPartial() &&*/ !currentStage.isDependent() && MathUtils.compareToZero(docSum.subtract(currentReadySum).subtract(event.getPerformedSum())) > 0)
      currentStageState = StageState.PARTITION;

    StageState oldState = currentStage.getState();
    currentStage.setState(currentStageState);

    if (StageState.PARTITION.equals(oldState)) {
      //этап уже частично отработан
      updateMark(currentStage, currentStageState, event);
    } else {
      //этап отрабатывается с нуля
      insertMark(currentStage, currentStageState, ActionType.STAGE, event);
    }

    //проверка "следует непосредственно"
    startStage = currentStage;
    prepareStageListForExecute();
    boolean isDirectly = false;
    List<DocProcessStage> notDirectlyStages = new ArrayList<DocProcessStage>();
    for (DocProcessStage stage : nextActions)
      if (isDirectly(currentStage, stage)) {
        isDirectly = true; //признак установим если есть хотя бы один "следует непосредственно"
      } else
        notDirectlyStages.add(stage);
    nextActions.removeAll(notDirectlyStages);
    notDirectlyStages.clear();

    if (isDirectly) {
      //продолжение ДО для текущего документа
      doProcess1(event.getProcessDate(), false);
    } else {
      //для текущего документа ДО завершен, проверяем в очереди другие документы,
      //выполняем для них ДО
      completeDocFlow();

      checkDocumentQueue();
      /*DocHead nextDoc = checkDocumentQueue();
      if (nextDoc != null)
				internalExecute(nextDoc.getId(), documentOwner);
			else
				fireFlowPerformed();*/
    }
  }

  private void rollbackCompleted(DocFlowPluginEvent event) {
    //производим сброс в хранилище данных, если генерируется ИС то отменяем выполнение ДО, т.к.
    //невозможно восстановить контекст до состояния которое было до запуска этапа
    try {
      ServerUtils.getPersistentManager().flush();
    } catch (RuntimeException e) {
      cancelDocFlow();
      throw e;
    }

    if (logger.isDebugEnabled())
      logger.debug("Documents flow rollback completed for: ".concat(document.getId().toString()));

    DocAction action = currentHeadState.getDocAction();

    //изменим списки состояний и этапов
    if (currentFullRollback) {
      //полностью откатили этап
      currentStage.setState(StageState.NONE);

      docActions.remove(action);
      startStage = docActions.get(docActions.size() - 1).getStage();

      ServerUtils.getPersistentManager().remove(action);
    } else {
      //частично откатили этап
      currentStage.setState(StageState.PARTITION);
      action.setStageState(StageState.PARTITION);

      for (LinkStage link : currentStage.getNextStages()) {
        if (!link.getNextStage().isDependent())
          continue;

        DocAction docAction = findDocState(document, link.getNextStage());
        if (docAction != null) {
          link.getNextStage().setState(StageState.COMPLETE);
          docAction.setStageState(StageState.COMPLETE);
        }
      }

      ServerUtils.getPersistentManager().remove(currentHeadState);
    }

    prepareStageListForRollback();
    if (!nextActions.isEmpty() && isDirectly(nextActions.get(0), currentStage)) {
      doRollbackProcess1(processDate, false, false, true);
    } else {
      completeDocFlow();
    }
  }

  private void updateMark(DocProcessStage performedStage, StageState state, DocFlowPluginEvent event) {
    DocAction action = findDocState(document, currentStage);
    if (action == null)
      throw new IllegalStateException();

    action.setStageState(state);

    DocHeadState headState = insertMarkHead(action, event);

    //если следующие этапы зависят от данного, то установим их в состояние "выполнен частично"
    for (LinkStage link : performedStage.getNextStages()) {
      if (link.getNextStage().isDependent()) {
        link.getNextStage().setState(StageState.PARTITION);
        DocAction nextAction = findDocState(document, link.getNextStage());
        if (nextAction != null)
          nextAction.setStageState(StageState.PARTITION);
      }
    }

    insertMarkSpec(headState, event);
  }

  private void insertMark(DocProcessStage performedStage, StageState state, ActionType actionType, DocFlowPluginEvent event) {
    DocAction action = new DocAction();
    action.setDocHead(document);
    action.setActionType(actionType);
    action.setStage(performedStage);
    action.setStageState(state);

    ServerUtils.getPersistentManager().persist(action);

    DocHeadState headState = null;

    if (EnumSet.of(ActionType.STAGE, ActionType.UPDATE).contains(actionType))
      headState = insertMarkHead(action, event);

    if (ActionType.STAGE.equals(actionType))
      insertMarkSpec(headState, event);

    if (docActions != null)
      docActions.add(action);
  }

  private DocHeadState insertMarkHead(DocAction action, DocFlowPluginEvent event) {
    DocHeadState headState = new DocHeadState();
    headState.setDocAction(action);
    if (event != null) {
      headState.setReadySum(event.getPerformedSum());
      headState.setData1(event.getData1());
      headState.setData2(event.getData2());
      headState.setStateValue(event.getData());
    }
    headState.setDateTime(new Date()); //current date
    headState.setAttribute("User.Id", ServerUtils.getUserProfile().getIdentificator()); //TODO в профайл загрузить entity объект

    ServerUtils.getPersistentManager().persist(headState);

    return headState;
  }

  private void insertMarkSpec(DocHeadState headState, DocFlowPluginEvent event) {
    if (event != null && event.getSpecList() != null)
      for (DocumentSpecItem item : event.getSpecList()) {
        DocSpecState specState = new DocSpecState();
        specState.setDocHeadState(headState);
        specState.setDocSpec(item.getDocSpec());
        specState.setReadyQuantity1(item.getPerformedQuantity1());
        specState.setReadyQuantity2(item.getPerformedQuantity2());
        specState.setReadySum(item.getPerformedSum());
        specState.setData1(item.getData1());
        specState.setData2(item.getData2());
        specState.setStateValue(item.getStateValue());

        ServerUtils.getPersistentManager().persist(specState);
      }
  }

  private void executionCanceled() {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow execution calceled for: ".concat(document.getId().toString()));

    cancelDocFlow();
  }

  private void rollbackCanceled() {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow rollback calceled for: ".concat(document.getId().toString()));

    cancelDocFlow();
  }

  private void cancelDocFlow() {
    if (isCanceledDocFlow)
      return;

    try {
      internalFinalization();
    } finally {
      //транзакцию обязательно необходимо перевести в rollback only, т.к. возможно в контексте хранилища
      //находятся сущности и они не должны быть занесены в систему хранения
      ServerUtils.setTransactionRollbackOnly();
      isCanceledDocFlow = true;
    }

    fireFlowCalceled();
  }

  private void completeDocFlow() {
    if (isCompletedDocFlow)
      return;

    internalFinalization();
    resetCurrentStates();

    isCompletedDocFlow = true;

    fireFlowPerformed();
  }

  @SuppressWarnings("unchecked")
  private Queue<Integer> getDocumentQueue() {
    //очередь документов создаем в сессии пользователя, все документы из очереди будут
    //отрабатываться от имени этого пользователя (выполнившего этап "Создать на основе")
    Session session = ServerUtils.getCurrentSession();
    if (session != null) {
      Queue<Integer> docQueue = (Queue<Integer>) session.getAttribute("MG_DOCFLOW_QUEUE");
      if (docQueue == null) {
        docQueue = new LinkedList<Integer>();
        session.setAttribute("MG_DOCFLOW_QUEUE", docQueue);
      }
      return docQueue;
    }
    return null;
  }

  private void checkDocumentQueue() {
    Queue<Integer> docQueue = getDocumentQueue();
    //запустим ДО для документа из очереди
    if (docQueue != null && !docQueue.isEmpty())
      DocFlowHelper.execute(docQueue.poll());
  }

  private void addDocumentToDocFlowQueue(DocHead docHead, DocProcessStage stage) {
    //если у документа созданного на основе после этапа "Создан на основе" есть этап
    //следующий непосредственно, то добавляем его в очередь
    List<Integer> list = getOrmTemplate().findByNamedQueryAndNamedParam("DocFlow.DocFlowManager.checkDirectlyAfterCreateBasedOn", new String[]{"docProcessStage", "docType"}, new Object[]{stage, document.getDocType()});
    if (!list.isEmpty()) {
      Queue<Integer> docQueue = getDocumentQueue();
      if (docQueue != null) {
        docQueue.add(docHead.getId());
        logger.info("document add to docflow queue, id: " + docHead.getId());
      }
    }
  }

  private void resetCurrentStates() {
    currentStage = null;
    currentStageState = StageState.NONE;
    currentReadySum = null;
    //performedDocSum = null;
    currentFullRollback = false;
    currentHeadState = null;
  }

  /**
   * получить количество пройденных этапов ДО
   *
   * @param docHead документ
   * @return количество пройденных этапов, если 1, то документ был создан или создан на основе, т.е.
   * не проходил ДО
   */
  private int getDocActionCount(DocHead docHead) {
    List<Integer> count = JdbcTemplate.getInstance().query(COUNT_DOCACTION_SQL,
        new Object[]{docHead.getId()}, new RowMapper<Integer>() {
          public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt(1);
          }
        });
    if (count.isEmpty())
      return 0;
    else
      return count.get(0);
  }

  private void doCheckStatus(DocHead docHead) {
    short currentOwner = DocumentUtils.getCurrentDocumentOwner();
    checkOwner(currentOwner);
    int count = getDocActionCount(docHead);
    //если документ создавал пользователь и он прошел документооборот,
    //то не разрешаем изменять его
    //added by OVS, 30.11.2001
    //если создатель больше или равен текущему запросчику он прошел документооборот,
    //то не разрешаем изменять его
    //This is a total hack!!!
    if (count > 1 && (currentOwner == 0 || currentOwner <= docHead.getRequester())) {
      throw new AlreadyCompletedException(docHead);
    }
  }

  private void doModifyDocument(DocHead docHead) {
    //при модификации документа не проверяем ДО, иначе невозможно модифицировать
    //документ штатными методами сервисов после прохождения ДО, проверку на прохождение ДО
    //возлагаем на прикладной код, там где это требуется, например при интерактивном
    //изменении документа пользователем (http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4436)
    //doCheckStatus(docHead);

    //выполняем проверку прохождения ДО чтобы не выполнялись следующие действия
    //см. http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4479
    //т.е. меняем ссылки на этапы создания только у документа не прошедшего ДО,
    //если же у документа прошедшего ДО будет сменен тип, то последствия вообще
    //не предсказуемы
    if (getDocActionCount(docHead) == 1) {
      DocProcessStage dpStage = getCreateDocFlowStage(docHead);
      //при изменении документа могли изменить тип, нужно подправить ссылки на этапы для нового типа
      JdbcTemplate.getInstance().update(UPDATE_DOCACTION_STAGE_SQL, new Object[]{dpStage.getId(), docHead.getId()});
    }
    insertMark(null, StageState.COMPLETE, ActionType.UPDATE, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEnterpriseBean#doDestroy()
   */
  @Override
  protected void doPreDestroy() {
    super.doDestroy();
    //при разрушении на всякий случай отменим ДО, для предотвращения ситуации
    //когда не вызван метод завершения или отмены ДО
    if (document != null && !isCompletedDocFlow)
      cancelDocFlow();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#execute(com.mg.merp.docflow.DocFlowListener)
   */
  @Remove
  @PermitAll
  public void execute(Serializable documentId, DocFlowListener listener)
      throws AlreadyCompletedException, DocumentNotFound {
    execute(documentId, listener, DOCFLOW_OWNER, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#roolback(com.mg.merp.docflow.DocFlowListener)
   */
  @Remove
  @PermitAll
  public void rollback(Serializable documentId, DocFlowListener listener) {
    rollback(documentId, listener, DOCFLOW_OWNER, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#execute(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, int, boolean)
   */
  @Remove
  @PermitAll
  public void execute(Serializable documentId, DocFlowListener listener, int requester, boolean silent) throws AlreadyCompletedException, DocumentNotFound {
    execute(documentId, listener, null, null, requester, silent);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#rollback(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, int, boolean)
   */
  @Remove
  @PermitAll
  public void rollback(Serializable documentId, DocFlowListener listener, int requester, boolean silent) {
    rollback(documentId, listener, null, requester, silent);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#execute(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, java.lang.String, java.util.Date, int, boolean)
   */
  @Remove
  @PermitAll
  public void execute(Serializable documentId, DocFlowListener listener,
                      String stageCode, Date processDate, int requester, boolean silent)
      throws AlreadyCompletedException, DocumentNotFound,
      InaccesibleStateException {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow execute for: ".concat(documentId.toString()));

    this.flowListener = listener;
    this.isSilent = silent;
    this.stageCodeForBulk = stageCode;
    if (processDate != null)
      this.processDate = processDate;

    internalExecute(documentId, requester);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#execute(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, java.lang.String)
   */
  @Remove
  @PermitAll
  public void execute(Serializable documentId, DocFlowListener listener,
                      String stageCode) throws AlreadyCompletedException,
      DocumentNotFound, InaccesibleStateException {
    execute(documentId, listener, stageCode, null, DOCFLOW_OWNER, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#rollback(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, java.lang.String, int, boolean)
   */
  @Remove
  @PermitAll
  public void rollback(Serializable documentId, DocFlowListener listener,
                       String stageCode, int requester, boolean silent) {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow rollback for: ".concat(documentId.toString()));

    this.flowListener = listener;
    this.isSilent = silent;
    this.stageCodeForBulk = stageCode;

    internalRollback(documentId, requester);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#rollback(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, java.lang.String)
   */
  @Remove
  @PermitAll
  public void rollback(Serializable documentId, DocFlowListener listener,
                       String stageCode) {
    rollback(documentId, listener, stageCode, DOCFLOW_OWNER, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#execute(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, com.mg.merp.docflow.DocFlowParamsStrategy)
   */
  @Remove
  @PermitAll
  public void execute(Serializable documentId, DocFlowListener listener,
                      DocFlowParamsStrategy paramsStrategy)
      throws AlreadyCompletedException, DocumentNotFound {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow execute for: ".concat(documentId.toString()));

    this.flowListener = listener;
    this.paramsStrategy = paramsStrategy;

    internalExecute(documentId, DOCFLOW_OWNER);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#rollback(java.io.Serializable, com.mg.merp.docflow.DocFlowListener, com.mg.merp.docflow.DocFlowParamsStrategy)
   */
  @Remove
  @PermitAll
  public void rollback(Serializable documentId, DocFlowListener listener,
                       DocFlowParamsStrategy paramsStrategy) {
    if (logger.isDebugEnabled())
      logger.debug("Documents flow rollback for: ".concat(documentId.toString()));

    this.flowListener = listener;
    this.paramsStrategy = paramsStrategy;

    internalRollback(documentId, DOCFLOW_OWNER);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#createDocument(com.mg.merp.document.model.DocHead)
   */
  @Remove
  @PermitAll
  public void createDocument(DocHead docHead) {
    document = loadDocument(docHead);
    try {
      DocProcessStage stage = getCreateDocFlowStage(document);
      insertMark(stage, StageState.COMPLETE, ActionType.STAGE, null);
      //если документ создан на основе, то добавляем его в очередь документов
      if (stage.getStage().getId() == DOC_FLOW_BASED_ON)
        addDocumentToDocFlowQueue(docHead, stage);
    } finally {
      document = null;
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowManager#modifyDocument(com.mg.merp.document.model.DocHead)
   */
  @Remove
  @PermitAll
  public void modifyDocument(DocHead docHead) {
    document = loadDocument(docHead);
    try {
      doModifyDocument(document);
    } finally {
      document = null;
    }
  }

  @Remove
  @PermitAll
  public void checkStatus(DocHead docHead) {
    document = loadDocument(docHead);
    try {
      doCheckStatus(document);
    } finally {
      document = null;
    }
  }

}
