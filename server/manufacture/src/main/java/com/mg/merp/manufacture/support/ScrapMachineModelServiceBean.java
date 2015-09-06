/*
 * ScrapMachineModelServiceBean.java
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
import com.mg.merp.manufacture.ScrapMachineHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMachineModelServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы актов на списание потерь времени, отработанного оборудование"
 *
 * @author leonova
 * @version $Id: ScrapMachineModelServiceBean.java,v 1.3 2006/09/12 11:08:13 leonova Exp $
 */
@Stateless(name = "merp/manufacture/ScrapMachineModelService")
public class ScrapMachineModelServiceBean extends DocumentModelServiceBean<ScrapDocumentModel, Integer> implements ScrapMachineModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return ScrapMachineHeadServiceLocal.FOLDER_PART;
  }


}
