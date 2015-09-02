/*
 * DocTypeMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.document.support.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.ShuttleController;
import com.mg.framework.support.ui.widget.ShuttleListener;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.DocTypeServiceLocal;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocTypeDocSectionLink;
import com.mg.merp.document.model.DocumentKind;

/**
 * Контроллер формы поддержки типа документа
 * 
 * @author leonova
 * @version $Id: DocTypeMt.java,v 1.5 2007/07/10 12:55:03 safonov Exp $
 */
public class DocTypeMt extends DefaultMaintenanceForm implements MasterModelListener {
	private ShuttleController documentLink;
	private Map<String, DocSection> mapDocSections = new HashMap<String, DocSection>();
	private Map<String, DocTypeDocSectionLink> mapDocSectionLinks = new HashMap<String, DocTypeDocSectionLink>();
	private ShuttleController docConfirmLink;
	private Map<String, DocSection> mapConfirmDocSections = new HashMap<String, DocSection>();
	private Map<String, DocTypeDocSectionLink> mapConfirmDocSectionLinks = new HashMap<String, DocTypeDocSectionLink>();
	private ShuttleController docBaseLink;
	private Map<String, DocSection> mapBaseDocSections = new HashMap<String, DocSection>();
	private Map<String, DocTypeDocSectionLink> mapBaseDocSectionLinks = new HashMap<String, DocTypeDocSectionLink>();
	private ShuttleController docContractLink;
	private Map<String, DocSection> mapContractDocSections = new HashMap<String, DocSection>();
	private Map<String, DocTypeDocSectionLink> mapContractDocSectionLinks = new HashMap<String, DocTypeDocSectionLink>();
	private DocTypeServiceLocal docTypeService;

	public DocTypeMt() {
		setMasterDetail(true);
		docTypeService = (DocTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/document/DocType");
		documentLink = new ShuttleController();
		for (DocSection docSection : OrmTemplate.getInstance().find(DocSection.class, "from DocSection")) {
			mapDocSections.put(docSection.getDSName(), docSection);
			mapConfirmDocSections.put(docSection.getDSName(), docSection);
			mapBaseDocSections.put(docSection.getDSName(), docSection);
			mapContractDocSections.put(docSection.getDSName(), docSection);
		}
		documentLink.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				createLink(event.getContents(), mapDocSections, mapDocSectionLinks, DocumentKind.DOCUMENT);
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				removeLink(event.getContents(), mapDocSections, mapDocSectionLinks);
			}
		});
		docConfirmLink = new ShuttleController();
		docConfirmLink.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				createLink(event.getContents(), mapConfirmDocSections, mapConfirmDocSectionLinks, DocumentKind.CONFIRMATION);
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				removeLink(event.getContents(), mapConfirmDocSections, mapConfirmDocSectionLinks);
			}
		});
		docBaseLink = new ShuttleController();
		docBaseLink.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				createLink(event.getContents(), mapBaseDocSections, mapBaseDocSectionLinks, DocumentKind.BASE);
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				removeLink(event.getContents(), mapBaseDocSections, mapBaseDocSectionLinks);
			}
		});
		docContractLink = new ShuttleController();
		docContractLink.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				createLink(event.getContents(), mapContractDocSections, mapContractDocSectionLinks, DocumentKind.CONTRACT);
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				removeLink(event.getContents(), mapContractDocSections, mapContractDocSectionLinks);
			}
		});
		addMasterModelListener(this);
	}
	
	private void removeLink(Object[] contents, Map<String, DocSection> docSectionMap,
			Map<String, DocTypeDocSectionLink> linkMap) {
		List<DocTypeDocSectionLink> links = new ArrayList<DocTypeDocSectionLink>();
		for (Object value : contents) {
			String key = (String) value;
			DocTypeDocSectionLink link = linkMap.get(value);
			links.add(link);
			docSectionMap.put(key, link.getDocSection());
			linkMap.remove(key);
		}
		docTypeService.removeDocSectionLinks(links.toArray(new DocTypeDocSectionLink[0]));
	}
	
	private void createLink(Object[] contents, Map<String, DocSection> docSectionMap,
			Map<String, DocTypeDocSectionLink> linkMap, DocumentKind kind) {
		List<DocTypeDocSectionLink> links = new ArrayList<DocTypeDocSectionLink>();
		for (Object value : contents) {
			String key = (String) value;
			DocTypeDocSectionLink link = new DocTypeDocSectionLink();
			link.setDocSection(docSectionMap.get(key));
			link.setDocType((DocType) getEntity());
			link.setKind(kind);
			docSectionMap.remove(key);					
			linkMap.put((String) value, link);
			links.add(link);
		}
		docTypeService.createDocSectionLinks(links.toArray(new DocTypeDocSectionLink[0]));
	}
	
	private void fillDocSectionLinks(DocTypeDocSectionLink[] links, ShuttleController shuttle,
			Map<String, DocSection> docSectionMap, Map<String, DocTypeDocSectionLink> linkMap) {
		for (DocTypeDocSectionLink link : links) {
			String key = link.getDocSection().getDSName();
			linkMap.put(key, link);
			docSectionMap.remove(key);
		}
		shuttle.getModel().setLeadingList(docSectionMap.keySet().toArray(new String[0]));
		shuttle.getModel().setTrailingList(linkMap.keySet().toArray(new String[0]));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDetailReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		view.getWidget("docFlowMap").setEnabled(!readOnly);
		view.getWidget("documentLink").setReadOnly(readOnly);
		view.getWidget("docConfirmLink").setReadOnly(readOnly);
		view.getWidget("docBaseLink").setReadOnly(readOnly);
		view.getWidget("docContractLink").setReadOnly(readOnly);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		//на создании объекта инициализируем списки разделами документов
		documentLink.getModel().setLeadingList(mapDocSections.keySet().toArray(new String[0]));
		docConfirmLink.getModel().setLeadingList(mapDocSections.keySet().toArray(new String[0]));
		docBaseLink.getModel().setLeadingList(mapDocSections.keySet().toArray(new String[0]));
		docContractLink.getModel().setLeadingList(mapDocSections.keySet().toArray(new String[0]));
		super.doOnAdd();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		//заполним связями типа документа с видами документов
		DocTypeDocSectionLink[][] links = docTypeService.loadDocSectionLinks((DocType) getEntity());
		fillDocSectionLinks(links[DocumentKind.DOCUMENT.ordinal()], documentLink, mapDocSections, mapDocSectionLinks);
		fillDocSectionLinks(links[DocumentKind.CONFIRMATION.ordinal()], docConfirmLink, mapConfirmDocSections, mapConfirmDocSectionLinks);
		fillDocSectionLinks(links[DocumentKind.BASE.ordinal()], docBaseLink, mapBaseDocSections, mapBaseDocSectionLinks);
		fillDocSectionLinks(links[DocumentKind.CONTRACT.ordinal()], docContractLink, mapContractDocSections, mapContractDocSectionLinks);
	}

	public void onActionShowDocFlowMap(WidgetEvent event) throws ApplicationException {
		DocFlowHelper.showDocFlowMap(((DocType) getEntity()).getId());
	}
}
