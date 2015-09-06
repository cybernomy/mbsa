/*
 * InputMachineModelServiceBean.java
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

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.manufacture.InputMachineHeadServiceLocal;
import com.mg.merp.manufacture.InputMachineModelServiceLocal;
import com.mg.merp.manufacture.model.InputDocumentModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы актов на списание времени, отработанного оборудование в НЗП"
 *
 * @author leonova
 * @version $Id: InputMachineModelServiceBean.java,v 1.3 2006/09/12 11:08:13 leonova Exp $
 */
@Stateless(name = "merp/manufacture/InputMachineModelService")
public class InputMachineModelServiceBean extends DocumentModelServiceBean<InputDocumentModel, Integer> implements InputMachineModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return InputMachineHeadServiceLocal.FOLDER_PART;
  }


}
