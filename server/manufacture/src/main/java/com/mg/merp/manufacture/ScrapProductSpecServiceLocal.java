/*
 * ScrapProductSpecServiceLocal.java
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

import com.mg.merp.document.model.DocSpec;
import com.mg.merp.manufacture.model.ScrapDocumentHead;

/**
 * Бизнес-компонент "Спецификация актов на списание потерь с операции
 * 
 * @author Oleg V. Safonov
 * @version $Id: ScrapProductSpecServiceLocal.java,v 1.4 2007/08/06 12:46:24 safonov Exp $
 */
public interface ScrapProductSpecServiceLocal
		extends com.mg.merp.document.GoodsDocumentSpecification<DocSpec, Integer> {
	
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/ScrapProductSpec";

	/**
	 * создание спецификаций
	 * 
	 * @param docHead	документ
	 */
	void createSpecifications(ScrapDocumentHead docHead);

}
