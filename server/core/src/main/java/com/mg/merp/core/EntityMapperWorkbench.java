/* EntityMapperWorkbench.java
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
package com.mg.merp.core;

import com.mg.merp.core.model.EntityTransformerMapping;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperWorkbench.java,v 1.1 2007/05/10 05:39:55 poroxnenko Exp $
 */
public interface EntityMapperWorkbench {
	String SERVICE_NAME = "merp:service=EntityMapperWorkbenchService";

	EntityTransformerMapping[] getEntityMappers(String query) throws Exception;

	EntityTransformerMapping editEntityMapper(EntityTransformerMapping mapping) throws Exception;
	                         
	EntityTransformerMapping addEntityMapper(EntityTransformerMapping mapping) throws Exception;

	void deleteEntityMappersList(Integer[] ids) throws Exception;
}
