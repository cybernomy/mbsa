/*
 * InputMaterialHeadServiceLocal.java
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
 * Бизнес-компонент "Акт на списание материалов в НЗП"
 *
 * @author Oleg V. Safonov
 * @version $Id: InputMaterialHeadServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface InputMaterialHeadServiceLocal
    extends com.mg.merp.document.GoodsDocument<InputDocumentHead, Integer, InputMaterialModelServiceLocal, InputMaterialSpecServiceLocal> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/InputMaterialHead";

  /**
   * тип папки для актов на списание материалов в НЗП
   */
  final static short FOLDER_PART = 12503;

  /**
   * docsection для актов на списание материалов в НЗП
   */
  final static short DOCSECTION = 12002;

}
