/*
 * DocFlowStageServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowStageServiceLocal;
import com.mg.merp.docprocess.model.ActionUserGrant;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.DocProcessStageRights;
import com.mg.merp.docprocess.model.StageAction;
import com.mg.merp.document.model.DocType;
import com.mg.merp.security.model.Groups;

/**
 * Реализация бизнес-компонента "Этап ДО"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowStageServiceBean.java,v 1.6 2007/10/02 09:14:13 safonov Exp $
 */
@Stateless(name="merp/docflow/DocFlowStageService")
public class DocFlowStageServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<DocProcessStage, Integer> implements
		DocFlowStageServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(DocProcessStage entity) {
		entity.setSizeX(62);
		entity.setSizeY(38);
		entity.setPriority((short) 1);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, DocProcessStage entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
		context.addRule(new MandatoryAttribute(entity, "Priority"));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowStageServiceLocal#grantRightsForGroups(com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.security.model.Groups[], com.mg.merp.docprocess.model.ActionUserGrant)
	 */
	@PermitAll
	public void grantRightsForGroups(DocProcessStage stage, Groups[] groups, ActionUserGrant grant) {
		for (Groups group : groups) {
			DocProcessStageRights right = new DocProcessStageRights();
			right.setDocProcessStage(stage);
			right.setSecGroups(group);
			right.setGrants(grant);
			ServerUtils.getPersistentManager().persist(right);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowStageServiceLocal#revokeRights(com.mg.merp.security.model.Groups[])
	 */
	@PermitAll
	public void revokeRights(DocProcessStageRights[] rights) {
		for (DocProcessStageRights right : rights)
			ServerUtils.getPersistentManager().remove(right);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowStageServiceLocal#grantRights(com.mg.merp.docprocess.model.DocProcessStageRights[], com.mg.merp.docprocess.model.ActionUserGrant)
	 */
	@PermitAll
	public void grantRights(DocProcessStageRights[] rights, ActionUserGrant grant) {
		for (DocProcessStageRights right : rights) {
			//нет прав, просто удалим, нет смысла хранить запись без прав
			if (grant == ActionUserGrant.NONE)
				ServerUtils.getPersistentManager().remove(right);
			else {
				right.setGrants(grant);
				ServerUtils.getPersistentManager().merge(right);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowStageServiceLocal#initializeDocFlow(com.mg.merp.document.model.DocType)
	 */
	@PermitAll
	public void initializeDocFlow(DocType docType) {
		//этап создание документа
		DocProcessStage stage = initialize();
		stage.setDocType(docType);
		stage.setStage(getPersistentManager().find(StageAction.class, DocFlowManager.DOC_FLOW_CREATE_DOC));
		stage.setCode(stage.getStage().getName().substring(0, Math.min(20, stage.getStage().getName().length())));
		stage.setName(stage.getStage().getName());
		stage.setCoorX(50);
		stage.setCoorY(50);
		create(stage);
		//этап создание документа на основе
		stage = initialize();
		stage.setDocType(docType);
		stage.setStage(getPersistentManager().find(StageAction.class, DocFlowManager.DOC_FLOW_BASED_ON));
		stage.setCode(stage.getStage().getName().substring(0, Math.min(20, stage.getStage().getName().length())));
		stage.setName(stage.getStage().getName());
		stage.setCoorX(200);
		stage.setCoorY(50);
		create(stage);
	}

}
