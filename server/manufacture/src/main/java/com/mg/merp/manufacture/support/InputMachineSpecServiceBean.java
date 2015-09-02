/*
 * InputMachineSpecServiceBean.java
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

package com.mg.merp.manufacture.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.merp.manufacture.InputMachineHeadServiceLocal;
import com.mg.merp.manufacture.InputMachineSpecServiceLocal;
import com.mg.merp.manufacture.generic.InputDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.InputDocumentHead;
import com.mg.merp.manufacture.model.InputDocumentSpec;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.mfreference.support.ConfigurationHelper;

/**
 * Бизнес-компонент "Спецификация актов на списание времени, отработанного оборудованием в НЗП" 
 * 
 * @author leonova
 * @version $Id: InputMachineSpecServiceBean.java,v 1.6 2007/08/06 12:44:53 safonov Exp $
 */
@Stateless(name="merp/manufacture/InputMachineSpecService")
public class InputMachineSpecServiceBean extends InputDocumentSpecServiceBean<InputDocumentSpec> implements InputMachineSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.generic.InputDocumentSpecServiceBean#doCreateSpecifications(com.mg.merp.manufacture.model.InputDocumentHead)
	 */
	@Override
	protected void doCreateSpecifications(InputDocumentHead docHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		for (JobMachine jobMachine : ManufactureUtils.loadJobRouteMachine(docHead.getOper())) {
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

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InputMachineHeadServiceLocal.DOCSECTION;
	}

}
