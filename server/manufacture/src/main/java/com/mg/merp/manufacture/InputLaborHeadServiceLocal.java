/*
 * InputLaborHeadServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.InputDocumentHead;

/**
 * Бизнес-компонент "Акт на списание времени, отработанного РС в ЗНП"
 *
 * @author Oleg V. Safonov
 * @version $Id: InputLaborHeadServiceLocal.java,v 1.4 2007/08/06 12:46:24 safonov Exp $
 */
public interface InputLaborHeadServiceLocal
    extends com.mg.merp.document.GoodsDocument<InputDocumentHead, Integer, InputLaborModelServiceLocal, InputLaborSpecServiceLocal> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/InputLaborHead";

  /**
   * тип папки для актов на списание времени, отработанного РС в НЗП
   */
  final static short FOLDER_PART = 12504;

  /**
   * docsection для актов на списание времени, отработанного РС в НЗП
   */
  final static short DOCSECTION = 12003;
}
