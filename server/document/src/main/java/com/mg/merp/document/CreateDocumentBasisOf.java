/* CreateDocumentBasisOf.java
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
package com.mg.merp.document;

import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

import java.util.Date;
import java.util.List;

/**
 * Сервис создания документа на основании другого <p> <br>S - класс объекта источника <br>D -
 * результирующий класс <br>P - Образец
 *
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: CreateDocumentBasisOf.java,v 1.6 2009/02/27 09:00:56 safonov Exp $
 */
public interface CreateDocumentBasisOf<S extends DocHead, D extends DocHead, P extends DocHeadModel> {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:document=CreateDocumentBasisOfService"; //$NON-NLS-1$

  /**
   * создание документа на основании
   *
   * @param srcDoc     Исходный документ
   * @param dstClass   Класс документа приемника
   * @param model      Образец
   * @param date       Дата создания документа, если <code>null</code>, то будет использована дата
   *                   из документа источника
   * @param specList   Список спецификаций к отработке
   * @param destFolder Папка назначения
   * @return Новый документ
   */
  D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder);

  /**
   * создание документа на основании
   *
   * @param srcDoc     Исходный документ
   * @param dstDoc     Документ-приёмник
   * @param model      Образец
   * @param date       Дата создания документа, если <code>null</code>, то будет использована дата
   *                   из документа источника
   * @param specList   Список спецификаций к отработке
   * @param destFolder Папка назначения
   */
  void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder);

  /**
   * создание документа на основании
   *
   * @param srcDoc         Исходный документ
   * @param dstClass       Класс документа приемника
   * @param model          Образец
   * @param date           Дата создания документа, если <code>null</code>, то будет использована
   *                       дата из документа источника
   * @param specList       Список спецификаций к отработке
   * @param destFolder     Папка назначения
   * @param createCallback объект обратного вызова для перехвата обработки создания документа, может
   *                       быть <code>null</code>
   * @return Новый документ
   */
  D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
             CreateDocumentBasisOfCallback createCallback);

  /**
   * создание документа на основании
   *
   * @param srcDoc         Исходный документ
   * @param dstDoc         Документ-приёмник
   * @param model          Образец
   * @param date           Дата создания документа, если <code>null</code>, то будет использована
   *                       дата из документа источника
   * @param specList       Список спецификаций к отработке
   * @param destFolder     Папка назначения
   * @param createCallback объект обратного вызова для перехвата обработки создания документа, может
   *                       быть <code>null</code>
   */
  void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
                CreateDocumentBasisOfCallback createCallback);

}
