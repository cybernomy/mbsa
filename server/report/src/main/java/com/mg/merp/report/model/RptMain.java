/*
 * RptMain.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.report.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.bytecode.javassist.FieldHandler;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysClass;

/**
 * @author hbm2java
 * @version $Id: RptMain.java,v 1.20 2008/03/28 06:26:43 safonov Exp $
 */
public class RptMain extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	private FieldHandler fieldHandler;

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String RptName;

	private boolean AskParams;

	private boolean DirectPrint;

	private java.lang.String Comment;

	private boolean InvokeFromEdit;

//	private java.lang.String ClassIds;

	private java.lang.String ClassNames;

	private byte[] template;

	private java.lang.Integer Priority;

	private java.util.Date SysVersion;

	private java.util.Set<com.mg.merp.report.model.RptRight> permissions;

	private java.util.Set<com.mg.merp.report.model.ClassLink> classLinks;
	
	private java.lang.String ParamsFormName;

	private java.lang.String outputFormat;
	
	// Constructors

	/** default constructor */
	public RptMain() {
	}

	/** constructor with id */
	public RptMain(java.lang.Integer Id) {
		this.Id = Id;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#getFieldHandler()
	 */
	public FieldHandler getFieldHandler() {
		return fieldHandler;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.javassist.FieldHandled#setFieldHandler(org.hibernate.bytecode.javassist.FieldHandler)
	 */
	public void setFieldHandler(FieldHandler handler) {
		this.fieldHandler = handler;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BigCode")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getRptName() {
		return this.RptName;
	}

	public void setRptName(java.lang.String Rptname) {
		this.RptName = Rptname;
	}

	/**
	 * 
	 */

	public boolean isAskParams() {
		return this.AskParams;
	}

	public void setAskParams(boolean Askparams) {
		this.AskParams = Askparams;
	}

	/**
	 * 
	 */

	public boolean isDirectPrint() {
		return this.DirectPrint;
	}

	public void setDirectPrint(boolean DirectPrint) {
		this.DirectPrint = DirectPrint;
	}

	/**
	 * 
	 */
	@DataItemName("Report.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */

	public boolean isInvokeFromEdit() {
		return this.InvokeFromEdit;
	}

	public void setInvokeFromEdit(boolean InvokeFromEdit) {
		this.InvokeFromEdit = InvokeFromEdit;
	}

	/**
	 * 
	 */

	public byte[] getTemplate() {
		return fieldHandler != null ? (byte[]) fieldHandler.readObject(this, "Template", this.template) : null;
	}

	public void setTemplate(byte[] template) {
		if (fieldHandler != null)
			fieldHandler.writeObject(this, "Template", this.template, template);
		this.template = template;
	}

	/**
	 * 
	 */

	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	public java.util.Date getSysVersion() {
		return SysVersion;
	}

	public void setSysVersion(java.util.Date version) {
		SysVersion = version;
	}

	public java.lang.String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(java.lang.String outputFormat) {
		this.outputFormat = outputFormat;
	}

	/**
	 * 
	 */

	/*public java.lang.String getClassIds() {
		if (this.ClassIds == null) {
			java.util.Set<com.mg.merp.report.model.ClassLink> links = getClassLinks();
			if (links != null) {
				this.ClassIds = "";
				for (com.mg.merp.report.model.ClassLink link : links)
					this.ClassIds = this.ClassIds
							+ Integer.toString(link.getSysClass().getId())
							+ ";";
			}
		}
		return this.ClassIds;
	}*/

	/*public void setClassIds(java.lang.String classIds) {
		this.ClassIds = classIds;
		if (classIds != null) {
			java.util.Set<ClassLink> links = getClassLinks();

			List<String> ids = StringUtils.split(classIds, ";");

			if ((ids == null) || ids.isEmpty()) {
				links.clear();
				return;
			}

			if (links == null) {
				links = new HashSet<ClassLink>();
				setClassLinks(links);
			}

			java.util.Set<ClassLink> removedLink = new HashSet<ClassLink>();
			for (ClassLink link : links) {
				int idx = ids.indexOf(link.getSysClass().getId().toString());
				if (idx == -1)
					removedLink.add(link);
				else
					ids.remove(idx);
			}
			links.removeAll(removedLink);

			PersistentManager pm = ServerUtils.getPersistentManager();
			for (String id : ids) {
				ClassLink l = new ClassLink();
				try {
					l
							.setSysClass(pm.find(SysClass.class, Integer
									.parseInt(id)));
				} catch (NumberFormatException e) {
					throw new RuntimeException(e);
				} catch (ApplicationException e) {
					throw new RuntimeException(e);
				}
				l.setReport(this);
				l.setSysClient(getSysClient());
				links.add(l);
			}
		}
	}*/

	/**
	 * 
	 */

	public java.lang.String getClassNames() {
		if (this.ClassNames == null) {
			java.util.Set<com.mg.merp.report.model.ClassLink> links = getClassLinks();
			if (links != null) {
				StringBuffer sb = new StringBuffer();
				for (com.mg.merp.report.model.ClassLink link : links)
					sb.append(link.getSysClass().getSysModule().getName())
							.append("(")
							.append(link.getSysClass().getSysModule().getDescription())
							.append(")")
							.append("->")
							.append(link.getSysClass().getBeanName())
							.append("(")
							.append(link.getSysClass().getDescription())
							.append(");");
				this.ClassNames = new String(sb);
			}
		}
		return this.ClassNames;
	}

	public void setClassNames(java.lang.String ClassNames) {
		this.ClassNames = ClassNames;
		java.util.Set<ClassLink> links = getClassLinks();
		if (ClassNames != null && ClassNames.length() > 0) {
			List<String> newNames = StringUtils.split(ClassNames, ";");
			List<String> names = new LinkedList<String>();
			//получаем список БК, которые необходимо прикрутить
			for (String nm : newNames) {
				int pos = nm.indexOf("->");
				names.add(nm.substring(pos + 2, nm.indexOf("(", pos)));
			}

			if (links == null) {
				links = new HashSet<ClassLink>();
				setClassLinks(links);
			}

			//БК, которых нет в списке удаляем из classLinks()
			java.util.Set<ClassLink> removedLink = new HashSet<ClassLink>();
			for (ClassLink link : links) {
				String ss = link.getSysClass().getBeanName().toString();
				if (!names.contains(ss))
					removedLink.add(link);
				else
					names.remove(ss);
			}
			links.removeAll(removedLink);

			//если необходимо добавить новые БК
			if (names.size() > 0) {
				PersistentManager pm = ServerUtils.getPersistentManager();

				List<SysClass> scL = MiscUtils.convertUncheckedList(SysClass.class,
						OrmTemplate.getInstance().findByNamedParam("select sc from SysClass sc where sc.BeanName in (:beans)", "beans", names));

				for (SysClass sc : scL) {
					ClassLink cl = new ClassLink();

					cl.setSysClass(sc);
					cl.setReport(this);
					//XXX: поле SysClient автоматически устанавливается только в рабочем контексте.
					//А для RptBIRTDeployerImpl его нет 
					cl.setSysClient(getSysClient());
					
					links.add(cl);
					pm.persist(cl);
				}
			}
		} else if (links != null)
			links.clear();
	}

	/**
	 * 
	 */

	public java.util.Set<com.mg.merp.report.model.RptRight> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(
			java.util.Set<com.mg.merp.report.model.RptRight> SetOfRptRights) {
		this.permissions = SetOfRptRights;
	}

	/**
	 * 
	 */

	public java.util.Set<com.mg.merp.report.model.ClassLink> getClassLinks() {
		return this.classLinks;
	}

	public void setClassLinks(
			java.util.Set<com.mg.merp.report.model.ClassLink> SetOfRptClassLink) {
		this.classLinks = SetOfRptClassLink;
	}

	public java.lang.String getParamsFormName() {
		return ParamsFormName;
	}

	public void setParamsFormName(java.lang.String paramsFormName) {
		ParamsFormName = paramsFormName;
	}

}