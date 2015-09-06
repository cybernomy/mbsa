/*
 * ScrapMachineSpecServiceBean.java
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

package com.mg.merp.manufacture.support;

import com.mg.merp.manufacture.ScrapMachineHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMachineSpecServiceLocal;
import com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;
import com.mg.merp.mfreference.support.ConfigurationHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификации актов на списание потерь времени, отработанного оборудованием"
 *
 * @author leonova
 * @version $Id: ScrapMachineSpecServiceBean.java,v 1.6 2007/08/06 12:44:53 safonov Exp $
 */
@Stateless(name = "merp/manufacture/ScrapMachineSpecService")
public class ScrapMachineSpecServiceBean extends ScrapDocumentSpecServiceBean<ScrapDocumentSpec> implements ScrapMachineSpecServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return ScrapMachineHeadServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean#doCreateSpecifications(com.mg.merp.manufacture.model.ScrapDocumentHead)
   */
  @Override
  protected void doCreateSpecifications(ScrapDocumentHead docHead) {
    List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
    for (JobMachine jobMachine : ManufactureUtils.loadJobRouteMachine(docHead.getDetectOper())) {
      docSpecs.add(new CreateManufactureSpecificationInfoImpl(
          ConfigurationHelper.getConfiguration().getMachineTime().getId(),
          null,
          BigDecimal.ZERO,
          jobMachine.getMchNumber(),
          null,
          jobMachine,
          jobMachine.getMchCostCategory(),
          null,
          null));
    }
    bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
  }

}
