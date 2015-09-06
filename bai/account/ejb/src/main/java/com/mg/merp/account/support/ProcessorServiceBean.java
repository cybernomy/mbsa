/*
 * ProcessorServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean;
import com.mg.merp.account.AccCalcAverageOutCostResult;
import com.mg.merp.account.AccCheckLastBatchResult;
import com.mg.merp.account.CalculateOutCostResult;
import com.mg.merp.account.ProcessorServiceLocal;
import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: ProcessorServiceBean.java,v 1.7 2008/05/08 09:07:41 alikaev Exp $
 */
@Stateful(name = "merp/account/ProcessorService")
public class ProcessorServiceBean extends AbstractPOJOBusinessObjectStatelessServiceBean implements ProcessorServiceLocal {

  private ProcessorImpl delegate = new ProcessorImpl();

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.Processor#processCreateEconomicOper(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
   */
  @PermitAll
  @Remove
  public void processCreateEconomicOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
    delegate.processCreateEconomicOper(params, listener);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.Processor#rollbackCreateEconomicOper(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollbackCreateEconomicOper(DocFlowPluginInvokeParams params) {
    delegate.rollbackCreateEconomicOper(params);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.Processor#calculateOutCost(java.util.Date, com.mg.merp.account.model.AccBatch, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor, java.math.BigDecimal)
   */
  public CalculateOutCostResult calculateOutCost(Date operDate, AccBatch accBatch, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
                                                 Catalog catalog, Contractor contractor, BigDecimal quantity) {
    return delegate.calculateOutCost(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.Processor#accCalcAverageOutCost(java.util.Date, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor, java.math.BigDecimal)
   */
  public AccCalcAverageOutCostResult accCalcAverageOutCost(Date operDate, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
                                                           Catalog catalog, Contractor contractor, BigDecimal quantity) {
    return delegate.accCalcAverageOutCost(operDate, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.Processor#accCheckLastBatch(java.util.Date, com.mg.merp.account.model.AccBatch, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
   */
  public AccCheckLastBatchResult accCheckLastBatch(Date operDate, AccBatch accBatch, BigDecimal quantity, BigDecimal summaNat, BigDecimal summaCur) {
    return delegate.accCheckLastBatch(operDate, accBatch, quantity, summaNat, summaCur);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.Processor#evaluateOutCost(com.mg.merp.account.model.RemnVal)
   */
  public void evaluateOutCost(RemnVal remnVal) {
    delegate.evaluateOutCost(remnVal);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.Processor#evaluateOutCost(java.lang.Integer)
   */
  public void evaluateOutCost(Integer remnValId) {
    delegate.evaluateOutCost(remnValId);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.Processor#calculateQuantityEnd(java.util.Date, com.mg.merp.account.model.AccBatch, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor)
   */
  @PermitAll
  public BigDecimal calculateQuantityEnd(Date operDate, AccBatch accBatch,
                                         AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
                                         AnlPlan anl4, AnlPlan anl5, Catalog catalog, Contractor contractor) {
    return delegate.calculateQuantityEnd(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor);
  }

}
