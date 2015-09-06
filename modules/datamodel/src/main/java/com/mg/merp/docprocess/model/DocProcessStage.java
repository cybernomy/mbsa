/*
 * DocProcessStage.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DocProcessStage.java,v 1.10 2007/12/14 08:47:52 safonov Exp $
 */
public class DocProcessStage extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.baiengine.model.Repository performBusinessAddin;

  private com.mg.merp.docprocess.model.StageAction Stage;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.document.model.DocType DocType;

  private com.mg.merp.baiengine.model.Repository rollbackBusinessAddin;

  private boolean Partial;

  private boolean prevComplete;

  private boolean ShowNewDocument;

  private java.lang.String name;

  private java.lang.String Comment;

  private com.mg.merp.document.model.DocSection LinkDocSection;

  private com.mg.merp.document.model.DocType LinkDocType;

  private java.lang.Integer LinkDocModel;

  private com.mg.merp.core.model.Folder LinkDocDestFolder;

  private boolean isJoinFlow;

  private boolean isForkFlow;

  private java.lang.Short Priority;

  private com.mg.merp.core.model.Folder LinkDocModelFolder;

  private int CoorX;

  private int CoorY;

  private int sizeX;

  private int sizeY;

  private boolean Dependent;

  private boolean UseCurrentDate;

  private java.lang.String Code;

  private boolean ValueOutOfBound;

  private boolean linkDocRollback;

  private DocProcessInteractiveKind linkDocRollbackInteractive;

  private StageState state = StageState.NONE;

  private DocProcessStage prevStage;

  private com.mg.merp.baiengine.model.Repository prePerformBusinessAddin;

  private com.mg.merp.baiengine.model.Repository preRollbackBusinessAddin;

  private java.util.Set<LinkStage> prevStages;

  private java.util.Set<DocProcessStageRights> userGrants;

  private java.util.Set<LinkStage> nextStages;

  // Constructors

  /**
   * default constructor
   */
  public DocProcessStage() {
  }

  /**
   * constructor with id
   */
  public DocProcessStage(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  @DataItemName("DocFlow.PerformBusinessAddin")
  public com.mg.merp.baiengine.model.Repository getPerformBusinessAddin() {
    return this.performBusinessAddin;
  }

  public void setPerformBusinessAddin(
      com.mg.merp.baiengine.model.Repository AlgRepository) {
    this.performBusinessAddin = AlgRepository;
  }

  /**
   *
   */

  public com.mg.merp.docprocess.model.StageAction getStage() {
    return this.Stage;
  }

  public void setStage(com.mg.merp.docprocess.model.StageAction DpStageAction) {
    this.Stage = DpStageAction;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  public com.mg.merp.document.model.DocType getDocType() {
    return this.DocType;
  }

  public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
    this.DocType = Typedoc;
  }

  /**
   *
   */
  @DataItemName("DocFlow.RollbackBusinessAddin")
  public com.mg.merp.baiengine.model.Repository getRollbackBusinessAddin() {
    return this.rollbackBusinessAddin;
  }

  public void setRollbackBusinessAddin(
      com.mg.merp.baiengine.model.Repository AlgRepository_1) {
    this.rollbackBusinessAddin = AlgRepository_1;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Partial")
  public boolean isPartial() {
    return this.Partial;
  }

  public void setPartial(boolean Partial) {
    this.Partial = Partial;
  }

  /**
   *
   */
  @DataItemName("DocFlow.PrevComplete")
  public boolean isPrevComplete() {
    return this.prevComplete;
  }

  public void setPrevComplete(boolean Previscomplete) {
    this.prevComplete = Previscomplete;
  }

  /**
   *
   */
  @DataItemName("DocFlow.ShowNewDocument")
  public boolean isShowNewDocument() {
    return this.ShowNewDocument;
  }

  public void setShowNewDocument(boolean Shownewdocument) {
    this.ShowNewDocument = Shownewdocument;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Name")
  public java.lang.String getName() {
    return this.name;
  }

  public void setName(java.lang.String Dpsname) {
    this.name = Dpsname;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("DocFlow.LinkDocSection")
  public com.mg.merp.document.model.DocSection getLinkDocSection() {
    return this.LinkDocSection;
  }

  public void setLinkDocSection(
      com.mg.merp.document.model.DocSection Linkdocsection) {
    this.LinkDocSection = Linkdocsection;
  }

  /**
   *
   */
  @DataItemName("DocFlow.LinkDocType")
  public com.mg.merp.document.model.DocType getLinkDocType() {
    return this.LinkDocType;
  }

  public void setLinkDocType(com.mg.merp.document.model.DocType Linkdoctype) {
    this.LinkDocType = Linkdoctype;
  }

  /**
   *
   */

  public java.lang.Integer getLinkDocModel() {
    return this.LinkDocModel;
  }

  public void setLinkDocModel(java.lang.Integer Linkdocmodel) {
    this.LinkDocModel = Linkdocmodel;
  }

  /**
   *
   */
  @DataItemName("DocFlow.LinkDocDestFolder")
  public com.mg.merp.core.model.Folder getLinkDocDestFolder() {
    return this.LinkDocDestFolder;
  }

  public void setLinkDocDestFolder(com.mg.merp.core.model.Folder Linkdocdestfolder) {
    this.LinkDocDestFolder = Linkdocdestfolder;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Priority")
  public java.lang.Short getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Short Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("DocFlow.LinkDocModelFolder")
  public com.mg.merp.core.model.Folder getLinkDocModelFolder() {
    return this.LinkDocModelFolder;
  }

  public void setLinkDocModelFolder(com.mg.merp.core.model.Folder Linkdocmodelfolder) {
    this.LinkDocModelFolder = Linkdocmodelfolder;
  }

  /**
   *
   */

  public int getCoorX() {
    return this.CoorX;
  }

  public void setCoorX(int Coorx) {
    this.CoorX = Coorx;
  }

  /**
   *
   */

  public int getCoorY() {
    return this.CoorY;
  }

  public void setCoorY(int Coory) {
    this.CoorY = Coory;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Dependent")
  public boolean isDependent() {
    return this.Dependent;
  }

  public void setDependent(boolean Dependent) {
    this.Dependent = Dependent;
  }

  /**
   *
   */
  @DataItemName("DocFlow.UseCurrentDate")
  public boolean isUseCurrentDate() {
    return this.UseCurrentDate;
  }

  public void setUseCurrentDate(boolean UseCurrentDate) {
    this.UseCurrentDate = UseCurrentDate;
  }

  /**
   *
   */
  @DataItemName("DocFlow.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("DocFlow.ValueOutOfBound")
  public boolean isValueOutOfBound() {
    return this.ValueOutOfBound;
  }

  public void setValueOutOfBound(boolean ValueOutOfBound) {
    this.ValueOutOfBound = ValueOutOfBound;
  }

  /**
   * @return Returns the linkDocRollback.
   */
  @DataItemName("DocFlow.LinkDocRollback")
  public boolean isLinkDocRollback() {
    return linkDocRollback;
  }

  /**
   * @param linkDocRollback The linkDocRollback to set.
   */
  public void setLinkDocRollback(boolean linkDocRollback) {
    this.linkDocRollback = linkDocRollback;
  }

  /**
   * @return Returns the linkDocRollbackInteractive.
   */
  public DocProcessInteractiveKind getLinkDocRollbackInteractive() {
    return linkDocRollbackInteractive;
  }

  /**
   * @param linkDocRollbackInteractive The linkDocRollbackInteractive to set.
   */
  public void setLinkDocRollbackInteractive(
      DocProcessInteractiveKind linkDocRollbackInteractive) {
    this.linkDocRollbackInteractive = linkDocRollbackInteractive;
  }

  /**
   *
   */

  public java.util.Set<LinkStage> getPrevStages() {
    return this.prevStages;
  }

  public void setPrevStages(java.util.Set<LinkStage> SetOfLinkstage) {
    this.prevStages = SetOfLinkstage;
  }

  /**
   *
   */

  public java.util.Set<DocProcessStageRights> getUserGrants() {
    return this.userGrants;
  }

  public void setUserGrants(
      java.util.Set<DocProcessStageRights> SetOfDocprocessstageRights) {
    this.userGrants = SetOfDocprocessstageRights;
  }

  /**
   *
   */

  public java.util.Set<LinkStage> getNextStages() {
    return this.nextStages;
  }

  public void setNextStages(java.util.Set<LinkStage> SetOfLinkstage_1) {
    this.nextStages = SetOfLinkstage_1;
  }

  /**
   * @return Returns the prevStage.
   */
  public DocProcessStage getPrevStage() {
    return prevStage;
  }

  /**
   * @param prevStage The prevStage to set.
   */
  public void setPrevStage(DocProcessStage prevStage) {
    this.prevStage = prevStage;
  }

  /**
   * @return Returns the state.
   */
  public StageState getState() {
    return state;
  }

  /**
   * @param state The state to set.
   */
  public void setState(StageState state) {
    this.state = state;
  }

  /**
   * @return Returns the isJoinFlow.
   */
  @DataItemName("DocFlow.JoinFlow")
  public boolean isJoinFlow() {
    return isJoinFlow;
  }

  /**
   * @param isJoinFlow The isJoinFlow to set.
   */
  public void setJoinFlow(boolean isJoinFlow) {
    this.isJoinFlow = isJoinFlow;
  }

  /**
   * @return Returns the isForkFlow.
   */
  @DataItemName("DocFlow.ForkFlow")
  public boolean isForkFlow() {
    return isForkFlow;
  }

  /**
   * @param isForkFlow The isForkFlow to set.
   */
  public void setForkFlow(boolean isSplitFlow) {
    this.isForkFlow = isSplitFlow;
  }

  /**
   * @return Returns the sizeX.
   */
  public int getSizeX() {
    return sizeX;
  }

  /**
   * @param sizeX The sizeX to set.
   */
  public void setSizeX(int sizeX) {
    this.sizeX = sizeX;
  }

  /**
   * @return Returns the sizeY.
   */
  public int getSizeY() {
    return sizeY;
  }

  /**
   * @param sizeY The sizeY to set.
   */
  public void setSizeY(int sizeY) {
    this.sizeY = sizeY;
  }

  /**
   * @return the prePerformBusinessAddin
   */
  @DataItemName("DocFlow.PrePerformBusinessAddin")
  public com.mg.merp.baiengine.model.Repository getPrePerformBusinessAddin() {
    return prePerformBusinessAddin;
  }

  /**
   * @param prePerformBusinessAddin the prePerformBusinessAddin to set
   */
  public void setPrePerformBusinessAddin(
      com.mg.merp.baiengine.model.Repository prePerformBusinessAddin) {
    this.prePerformBusinessAddin = prePerformBusinessAddin;
  }

  /**
   * @return the preRollbackBusinessAddin
   */
  @DataItemName("DocFlow.PreRollbackBusinessAddin")
  public com.mg.merp.baiengine.model.Repository getPreRollbackBusinessAddin() {
    return preRollbackBusinessAddin;
  }

  /**
   * @param preRollbackBusinessAddin the preRollbackBusinessAddin to set
   */
  public void setPreRollbackBusinessAddin(
      com.mg.merp.baiengine.model.Repository preRollbackBusinessAddin) {
    this.preRollbackBusinessAddin = preRollbackBusinessAddin;
  }

}