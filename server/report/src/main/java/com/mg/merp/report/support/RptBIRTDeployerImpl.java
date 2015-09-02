/*
 * RptBIRTDeployerImpl.java
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
package com.mg.merp.report.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.report.OldReportException;
import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.report.model.RptMain;
import com.mg.merp.report.model.RptRight;
import com.mg.merp.report.service.RptBIRTDeployer;
import com.mg.merp.security.model.Groups;

/**
 * Класс-реализация интерфейса {@link RptBIRTDeployer}
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptBIRTDeployerImpl.java,v 1.13 2007/08/30 14:31:07 safonov Exp $
 */
public class RptBIRTDeployerImpl implements RptBIRTDeployer {

	public RptMainTransfer[] getReports(String likeSentence) throws Exception {
		byte[] str = likeSentence.getBytes();
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '*')
				str[i] = '%';
			else if (str[i] == '?')
				str[i] = '_';

		}
		likeSentence = new String(str);
		UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
		utx.begin();
		try {
			List<RptMain> rpt = OrmTemplate.getInstance().findByCriteria(RptMain.class, Restrictions.like("Code", likeSentence));

			RptMainTransfer[] result = new RptMainTransfer[rpt.size()];
			for (int i = 0; i < rpt.size(); i++) {
				RptMainTransfer rmt = new RptMainTransfer();
				RptMain elt = rpt.get(i);
				rmt.id = elt.getId();
				rmt.code = elt.getCode().trim();
				rmt.comment = (elt.getComment() != null) ? elt.getComment().trim() : "";
				rmt.name = elt.getRptName().trim();
				rmt.template = elt.getTemplate();
				rmt.askParams = elt.isAskParams();
				rmt.directPrint = elt.isDirectPrint();
				rmt.priority = elt.getPriority();
				rmt.sysVersion = elt.getSysVersion();
				rmt.classNames = elt.getClassNames();
				rmt.paramsFormName = elt.getParamsFormName();
				rmt.sysClientId = elt.getSysClient().getId();
				rmt.outputFormat = elt.getOutputFormat() != null ? elt.getOutputFormat().trim() : null;
				rmt.secGroupsIds = new ArrayList<Integer>(elt.getPermissions().size());
				rmt.secGroupsNames = new ArrayList<String>(rmt.secGroupsIds.size());

				for(RptRight rr : elt.getPermissions()){
					rmt.secGroupsIds.add(rr.getSecGroups().getId());
					rmt.secGroupsNames.add(rr.getSecGroups().getName().trim());
				}
				
				result[i] = rmt;
			}
			utx.commit();
			return result;
		} catch (Exception ex) {
			utx.rollback();
			throw ex;
		}
	}

	public RptMainTransfer persistTemplate(RptMainTransfer rmt)
			throws Exception {
		UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
		utx.begin();
		PersistentManager pm = ServerUtils.getPersistentManager();
		try {
			RptMain rpt = OrmTemplate.getInstance().findUniqueByCriteria(RptMain.class, Restrictions.eq("Code", rmt.code));
			if (rpt == null)
				// отсутствует запись с таким кодом
				throw new ApplicationException(Messages.getInstance().getMessage(Messages.REPORT_MISSING, new Object[]{rmt.code}));
			//дополнительная проверка на то, чтобы изменяли действительно тот отчёт, который забрали в репозитарий
			//и из той же базы(в разных базах отчёты с одинаковым кодом)
			if ((rpt.getId().intValue() != rmt.id.intValue()) || rpt.getSysVersion().after(rmt.sysVersion))
				throw new OldReportException();
			rpt.setTemplate(rmt.template);

			pm.persist(rpt);
			utx.commit();
			rmt.sysVersion = rpt.getSysVersion();

			return rmt;
		} catch (Exception ex) {
			utx.rollback();
			throw ex;
		}

	}

	public RptMainTransfer addReport(RptMainTransfer rmt) throws Exception {
		UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
		utx.begin();
		try {
			PersistentManager pm = ServerUtils.getPersistentManager();
			RptMain rpt = new RptMain();
			rpt.setCode(rmt.code);
			rpt.setRptName(rmt.name);
			rpt.setComment(rmt.comment);
			rpt.setDirectPrint(rmt.directPrint);
			rpt.setPriority(rmt.priority);
			rpt.setAskParams(rmt.askParams);
			rpt.setDirectPrint(rmt.directPrint);
			rpt.setParamsFormName(rmt.paramsFormName);
			rpt.setOutputFormat(rmt.outputFormat);

			SysClient sc = OrmTemplate.getInstance().findUniqueByCriteria(SysClient.class, Restrictions.eq("Id", 1));

			rpt.setSysClient(sc);
			rpt.setTemplate(rmt.template);

			pm.persist(rpt);
			rmt.id = rpt.getId();
			rmt.sysVersion = rpt.getSysVersion();
			rpt.setClassNames(rmt.classNames);
			rpt.setPermissions(createRights(rmt.secGroupsIds, rpt, sc));
			
			pm.persist(rpt);
		} catch (Exception ex) {
			utx.rollback();
			throw ex;
		}
		utx.commit();
		return rmt;
	}

	private Set<RptRight> createRights(ArrayList<Integer> secGroupsIds, RptMain rpt, SysClient sc){
		Map<Integer, RptRight> newRights = new HashMap<Integer, RptRight>(secGroupsIds.size());
		for(int j = 0; j < secGroupsIds.size(); j++){
			RptRight rr = new RptRight();
			rr.setRights(1);
			rr.setRpt(rpt);
			rr.setSysClient(sc);
			Groups gr = OrmTemplate.getInstance().findUniqueByCriteria(Groups.class, Restrictions.eq("Id", secGroupsIds.get(j)));
			rr.setSecGroups(gr);
			newRights.put(gr.getId(), rr);
		}
		
		if (rpt.getPermissions() == null)
			rpt.setPermissions(new HashSet<RptRight>());
		Set<RptRight> removedLinks = new HashSet<RptRight>();

		for (RptRight rr : rpt.getPermissions())
			if (!newRights.containsKey(rr.getSecGroups().getId()))
				removedLinks.add(rr);
			else
				newRights.remove(rr.getSecGroups().getId());
		rpt.getPermissions().removeAll(removedLinks);

		rpt.getPermissions().addAll(newRights.values());
		return rpt.getPermissions();
	}
	
	public void deleteReportList(Integer[] ids) throws Exception {
		UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
		utx.begin();
		try {
			OrmTemplate.getInstance().bulkUpdate("delete from RptMain rm where rm.Id in (:ids)", "ids", Arrays.asList(ids));
		} catch (Exception ex) {
			utx.rollback();
			throw ex;
		}
		utx.commit();
	}

	public RptMainTransfer editReport(RptMainTransfer rmt) throws Exception {
		UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
		utx.begin();
		PersistentManager pm = ServerUtils.getPersistentManager();
		try {
			RptMain rpt = OrmTemplate.getInstance().findUniqueByCriteria(RptMain.class, Restrictions.eq("Code", rmt.code));
			if ((rpt.getId().intValue() != rmt.id.intValue()) || rpt.getSysVersion().after(rmt.sysVersion))
				throw new OldReportException();
			rpt.setCode(rmt.code);
			rpt.setRptName(rmt.name);
			rpt.setComment(rmt.comment);
			rpt.setDirectPrint(rmt.directPrint);
			rpt.setPriority(rmt.priority);
			rpt.setAskParams(rmt.askParams);
			rpt.setDirectPrint(rmt.directPrint);
			rpt.setParamsFormName(rmt.paramsFormName);
			rpt.setTemplate(rmt.template);
			rpt.setOutputFormat(rmt.outputFormat);
			rpt.setClassNames(rmt.classNames);
			rpt.setPermissions(createRights(rmt.secGroupsIds, rpt, rpt.getSysClient()));
			
			pm.persist(rpt);
			utx.commit();

			rmt.sysVersion = rpt.getSysVersion();
			return rmt;
		} catch (Exception ex) {
			utx.rollback();
			throw ex;
		}
	}

}
