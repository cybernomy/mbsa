/*
 * InputSpecificationServiceLocal.java
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

import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.manufacture.model.InputDocumentHead;
import com.mg.merp.manufacture.model.InputDocumentSpec;

/**
 * Базовый интерфейс актов на списание ресурсов
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputSpecificationServiceLocal.java,v 1.1 2007/08/06 12:46:24 safonov Exp $
 */
public interface InputSpecificationServiceLocal<T extends InputDocumentSpec> extends
		GoodsDocumentSpecification<T, Integer> {

	/**
	 * создание спецификаций документа
	 * 
	 * @param docHead документ
	 */
	void createSpecifications(InputDocumentHead docHead);

}
