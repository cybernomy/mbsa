/*
 * CreateFactContractClausesDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.contract.support;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.contract.PhaseFactItemServiceLocal;
import com.mg.merp.contract.model.FactItemData;
import com.mg.merp.contract.support.ui.CreatePhaseFactItemDlg;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.document.model.DocHead;

/**
 * Реализация фабрики реализаций этапа ДО "Создание фактических пунктов договора" (Отработать
 * документ как факт по договору)
 *
 * @author Artem V. Sharapov
 * @version $Id: CreateFactContractClausesDocFlowPluginFactory.java,v 1.1 2007/03/07 12:26:27
 *          sharapov Exp $
 */
public class CreateFactContractClausesDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

  public final static int FACTORY_IDENTIFIER = 25;

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
   */
  @Override
  protected DocFlowPlugin doCreatePlugin() {
    return new AbstractDocFlowPlugin() {

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
       */
      @Override
      protected void doExecute() throws Exception {
        final CreatePhaseFactItemDlg dialog = (CreatePhaseFactItemDlg) UIProducer.produceForm("com/mg/merp/contract/resources/CreatePhaseFactItemDlg.mfd.xml"); //$NON-NLS-1$

        dialog.addOkActionListener(new FormActionListener() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
           */
          public void actionPerformed(FormEvent event) {
            PhaseFactItemServiceLocal service = (PhaseFactItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhaseFactItem"); //$NON-NLS-1$
            FactItemData factItemData = new FactItemData(
                getParams().getDocument(),
                getParams().getProcessDate(),
                getParams().getPerformedSum(),
                dialog.getItemKind(),
                dialog.getContractorSource(),
                dialog.isCreateSpec(),
                dialog.getсontractType(),
                dialog.getсontractNumber(),
                dialog.getContractDate(),
                dialog.getContract());
            getParams().setData1(service.createContractFactItem(factItemData).getId());
            complete();
          }

        });
        dialog.addCancelActionListener(new FormActionListener() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
           */
          public void actionPerformed(FormEvent event) {
            cancel();
          }
        });
        DocHead contract = null;
        if (getParams().getDocument().getContract() == null) {
          ContractServiceLocal contractService = (ContractServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/Contract"); //$NON-NLS-1$
          contract = contractService.findByParams(getParams().getDocument().getContractType(), getParams().getDocument().getContractDate(), getParams().getDocument().getContractNumber());
        } else
          contract = getParams().getDocument().getContract();
        if (contract == null)
          getParams().setData2(1);

        dialog.setContract(contract);
        dialog.setсontractType(getParams().getDocument().getContractType());
        dialog.setContractDate(getParams().getDocument().getContractDate());
        dialog.setсontractNumber(getParams().getDocument().getContractNumber());
        dialog.execute();
      }

      /* (non-Javadoc)
       * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
       */
      @Override
      protected void doRoolback() throws Exception {
        PhaseFactItemServiceLocal service = (PhaseFactItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhaseFactItem"); //$NON-NLS-1$
        service.rollBackCreateContractFactItem(getParams().getDocument(), getParams().getData1(), getParams().getData2());
        complete();
      }
    };
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
   */
  @Override
  public int getIdentifier() {
    return FACTORY_IDENTIFIER;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
   */
  @Override
  public String getName() {
    return "Create contract fact items"; //$NON-NLS-1$
  }

}
