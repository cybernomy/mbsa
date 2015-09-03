/*
 * AdvanceRepSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.AdvanceRepHeadServiceLocal;
import com.mg.merp.account.AdvanceRepSpecServiceLocal;
import com.mg.merp.account.model.AdvanceRepHead;
import com.mg.merp.account.model.AdvanceRepSpec;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;

/**
 * Бизнес-компонент "Спецификация авансовых отчетов" 
 * 
 * @author leonova
 * @version $Id: AdvanceRepSpecServiceBean.java,v 1.7 2008/03/12 11:19:58 alikaev Exp $
 */
@Stateless(name="merp/account/AdvanceRepSpecService")
public class AdvanceRepSpecServiceBean extends GoodsDocumentSpecificationServiceBean<AdvanceRepSpec, Integer> implements AdvanceRepSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return AdvanceRepHeadServiceLocal.DOCSECTION;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doAdjust(AdvanceRepSpec entity) {
		// без этой строчки налоги не пересчитываются
		entity.setDocHead(ServerUtils.getPersistentManager().find(AdvanceRepHead.class, entity.getDocHead().getId()));
		super.doAdjust(entity);
	}	

}
