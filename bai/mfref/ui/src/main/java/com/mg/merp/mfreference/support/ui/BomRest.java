/*
 * BomRest.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.mfreference.model.BomStatus;
import com.mg.merp.mfreference.model.BomType;
import com.mg.merp.mfreference.model.Cell;
import com.mg.merp.mfreference.model.LaborClass;
import com.mg.merp.mfreference.model.ResourceGroup;
import com.mg.merp.mfreference.model.ScheduleDirection;
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.util.Date;

/**
 * Контроллер условий отбора состава изделий
 *
 * @author leonova
 * @version $Id: BomRest.java,v 1.4 2007/07/30 10:24:19 safonov Exp $
 */
public class BomRest extends DefaultHierarhyRestrictionForm {

  private BomType bomType = null;
  private BomStatus bomStatus = null;
  private int approverFlag = 0;
  @DataItemName("MfReference.Cond.BOM.RevisionDateFrom")
  private Date revisionDateFrom = null;
  @DataItemName("MfReference.Cond.BOM.RevisionDateTill")
  private Date revisionDateTill = null;
  private Catalog catalogCode = null;
  @DataItemName("MfReference.BOM.DefSrcStock")
  private Contractor srcStock = null;
  @DataItemName("MfReference.BOM.DefSrcMol")
  private Contractor srcMol = null;
  @DataItemName("MfReference.BOM.DefDstStock")
  private Contractor dstStock = null;
  @DataItemName("MfReference.BOM.DefDstMol")
  private Contractor dstMol = null;
  private ScheduleDirection schedDirection = null;
  private Cell cellCode = null;
  @DataItemName("MfReference.Cond.BOM.WorkCenter")
  private WorkCenter wcCode = null;
  @DataItemName("MfReference.Cond.BOM.Material")
  private Catalog materialCode = null;
  @DataItemName("MfReference.Cond.BOM.ResourceCodeMaterail")
  private ResourceGroup resourceCodeMaterail = null;
  @DataItemName("MfReference.Cond.BOM.ResourceCodeMachine")
  private ResourceGroup resourceCodeMachine = null;
  @DataItemName("MfReference.Cond.BOM.ResourceCodeLabor")
  private ResourceGroup resourceCodeLabor = null;
  private LaborClass laborClass = null;


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.bomType = null;
    this.bomStatus = null;
    this.approverFlag = 0;
    this.revisionDateFrom = null;
    this.revisionDateTill = null;
    this.catalogCode = null;
    this.srcMol = null;
    this.srcStock = null;
    this.dstStock = null;
    this.dstMol = null;
    this.schedDirection = null;
    this.cellCode = null;
    this.wcCode = null;
    this.resourceCodeLabor = null;
    this.resourceCodeMaterail = null;
    this.resourceCodeMachine = null;
    this.materialCode = null;
    this.laborClass = null;
  }


  /**
   * @return Returns the approverFlag.
   */
  protected int getApproverFlag() {
    return approverFlag;
  }


  /**
   * @return Returns the bomStatus.
   */
  protected BomStatus getBomStatus() {
    return bomStatus;
  }


  /**
   * @return Returns the bomType.
   */
  protected BomType getBomType() {
    return bomType;
  }


  /**
   * @return Returns the catalogCode.
   */
  protected Catalog getCatalogCode() {
    return catalogCode;
  }


  /**
   * @return Returns the cellCode.
   */
  protected Cell getCellCode() {
    return cellCode;
  }


  /**
   * @return Returns the dstMol.
   */
  protected Contractor getDstMol() {
    return dstMol;
  }


  /**
   * @return Returns the dstStock.
   */
  protected Contractor getDstStock() {
    return dstStock;
  }


  /**
   * @return Returns the laborClass.
   */
  protected LaborClass getLaborClass() {
    return laborClass;
  }


  /**
   * @return Returns the materialCode.
   */
  protected Catalog getMaterialCode() {
    return materialCode;
  }


  /**
   * @return Returns the resourceCodeLabor.
   */
  protected ResourceGroup getResourceCodeLabor() {
    return resourceCodeLabor;
  }


  /**
   * @return Returns the resourceCodeMachine.
   */
  protected ResourceGroup getResourceCodeMachine() {
    return resourceCodeMachine;
  }


  /**
   * @return Returns the resourceCodeMaterail.
   */
  protected ResourceGroup getResourceCodeMaterail() {
    return resourceCodeMaterail;
  }


  /**
   * @return Returns the revisionDateFrom.
   */
  protected Date getRevisionDateFrom() {
    return revisionDateFrom;
  }


  /**
   * @return Returns the revisionDateTill.
   */
  protected Date getRevisionDateTill() {
    return revisionDateTill;
  }


  /**
   * @return Returns the schedDirection.
   */
  protected ScheduleDirection getSchedDirection() {
    return schedDirection;
  }


  /**
   * @return Returns the srcMol.
   */
  protected Contractor getSrcMol() {
    return srcMol;
  }


  /**
   * @return Returns the srcStock.
   */
  protected Contractor getSrcStock() {
    return srcStock;
  }


  /**
   * @return Returns the wcCode.
   */
  protected WorkCenter getWcCode() {
    return wcCode;
  }


}
