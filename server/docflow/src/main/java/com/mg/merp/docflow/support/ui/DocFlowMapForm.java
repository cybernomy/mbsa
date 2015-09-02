/*
 * DocFlowMapForm.java
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
package com.mg.merp.docflow.support.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.GraphController;
import com.mg.framework.support.ui.widget.graph.Edge;
import com.mg.framework.support.ui.widget.graph.GraphElement;
import com.mg.framework.support.ui.widget.graph.Rectangle;
import com.mg.framework.support.ui.widget.graph.Vertex;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowStageServiceLocal;
import com.mg.merp.docflow.support.Messages;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.docprocess.model.LinkStage;
import com.mg.merp.document.model.DocType;

/**
 * @author Oleg V. Safonov
 * @version $Id: DocFlowMapForm.java,v 1.8 2009/02/04 12:17:38 safonov Exp $
 */
public class DocFlowMapForm extends AbstractForm {
	private DocFlowStageServiceLocal stageService;
	private DocType docType;
	private GraphController map;
	private Map<GraphElement, PersistentObject> elementsMap = new HashMap<GraphElement, PersistentObject>();
	
	public DocFlowMapForm() {
		this.map = new GraphController() {
			protected void doCellChanged(GraphElement cell) {
				if (cell instanceof Rectangle) {
					Rectangle rect = (Rectangle) cell;
					DocProcessStage stage = (DocProcessStage) elementsMap.get(cell);
					stage.setCoorX(rect.getX());
					stage.setCoorY(rect.getY());
					stage.setSizeX(rect.getWidth());
					stage.setSizeY(rect.getHeight());
					stageService.store(stage);
				}
			}
		};
	}
	
	private Vertex createVertex(DocProcessStage stage) {
		Rectangle result = new Rectangle(stage.getCoorX(), stage.getCoorY(), stage.getSizeX(), stage.getSizeY());
		result.setLabel(stage.getName() == null || stage.getName().equals("") ? stage.getStage().getName() : String.format("%s (%s)", stage.getName(), stage.getStage().getName()));
		elementsMap.put(result, stage);
		map.insertVertex(result);
		return result;
	}
	
	private Edge createEdge(LinkStage link, Vertex source, Vertex target) {
		Edge result = new Edge(source, target);
		elementsMap.put(result, link);
		map.insertEdge(result);
		return result;
	}
	
	public void onActionAddEdge(WidgetEvent event) {
		List<GraphElement> selected = map.getSelectedElements();
		if (selected.size() == 2 && selected.get(0) instanceof Vertex && selected.get(1) instanceof Vertex) {
			//если отмечены две вершины то создаем связь между ними
			Vertex source = (Vertex) selected.get(0);
			Vertex target = (Vertex) selected.get(1);
			DocProcessStage sourceStage = (DocProcessStage) elementsMap.get(source);
			DocProcessStage targetStage = (DocProcessStage) elementsMap.get(target);
			//этапы создания не могут быть целью
			boolean createLink = (targetStage.getStage().getId() != 1) && (targetStage.getStage().getId() != 2);
			if (createLink)
				for (Entry<GraphElement, PersistentObject> entry : elementsMap.entrySet()) {
					LinkStage link;
					if (entry.getValue() instanceof LinkStage) {
						link = (LinkStage) entry.getValue();
						if (link.getPrevStage().equals(sourceStage) && link.getNextStage().equals(targetStage)) {
							createLink = false;
							break;
						}
					}
					
				}
			if (createLink) {
				LinkStage link = new LinkStage();
				link.setPrevStage(sourceStage);
				link.setNextStage(targetStage);
				createEdge(link, source, target);
				ServerUtils.getPersistentManager().persist(link);
			}
		}
	}

	public void onActionAddVertex(WidgetEvent event) {
		ChooseDocFlowStageDialog dialog = (ChooseDocFlowStageDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/ChooseDocFlowStageDialog.mfd.xml");
		dialog.addOkActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
				DocProcessStage stage = stageService.initialize(null);
				stage.setStage(((ChooseDocFlowStageDialog) event.getForm()).getStageAction());
				stage.setDocType(docType);
				stage.setCoorX(0);
				stage.setCoorY(0);
				MaintenanceHelper.add(stageService, stage, null, new MaintenanceFormActionListener() {
					public void performed(MaintenanceFormEvent event) {
						createVertex((DocProcessStage) event.getEntity());
					}

					public void canceled(MaintenanceFormEvent event) {
					}
				});
			}
		});
		dialog.execute();
	}
	
	public void onActionEdit(WidgetEvent event) {
		List<GraphElement> selected = map.getSelectedElements();
		//не редактируем если отмечено несколько элементов или нет отмеченных
		if (selected.size() != 1)
			return;
		
		final GraphElement element = selected.get(0);
		if (element instanceof Rectangle) {
			//редактируем этап
			MaintenanceHelper.edit(stageService, elementsMap.get(element), null, new MaintenanceFormActionListener() {
				public void performed(MaintenanceFormEvent event) {
					map.cellChanged(element);
				}

				public void canceled(MaintenanceFormEvent event) {
				}
			});
		}
		else if (element instanceof Edge) {
			//редактируем связь
			final DocFlowStageLinkMt linkForm = (DocFlowStageLinkMt) UIProducer.produceForm("com/mg/merp/docflow/resources/DocFlowStageLinkMt.mfd.xml");
			final LinkStage link = (LinkStage) elementsMap.get(element);
			linkForm.setLink(link);
			linkForm.addOkActionListener(new FormActionListener() {
				public void actionPerformed(FormEvent event) {
					link.setDirectly(linkForm.isDirectly());
					ServerUtils.getPersistentManager().merge(link);
				}
			});
			linkForm.execute();
		}
		else
			throw new IllegalArgumentException();
	}

	private void removeElements() {
		for (GraphElement cell : map.getSelectedElements()) {
			if (cell instanceof Rectangle)
				stageService.erase((DocProcessStage) elementsMap.get(cell));
			else
				ServerUtils.getPersistentManager().remove(elementsMap.get(cell));
			//в карте могут остаться связи ссылающиеся на вершину, при этом выделили только вершину
			elementsMap.remove(cell);
			map.removeCell(cell);
		}
	}
	
	public void onActionRemove(WidgetEvent event) {
		com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
		final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT);
		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE,
				msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_TITLE),
				msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_QUESTION),
				yesButton, msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT), new AlertListener() {

			public void alertClosing(String value) {
				if (value.equals(yesButton)) {
					removeElements();
				}
			}

		});
	}
	
	public void execute(Serializable docTypeId) {
		docType = ServerUtils.getPersistentManager().find(DocType.class, docTypeId);
		stageService = (DocFlowStageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/docflow/DocFlowStage");
		List<DocProcessStage> stages = stageService.findByCriteria(Restrictions.eq("DocType", docType));
		
		if (stages.size() == 0) {
			//документооборот не инициализирован
			stageService.initializeDocFlow(docType);
			stages = stageService.findByCriteria(Restrictions.eq("DocType", docType));
		}
		
		Map<DocProcessStage, Vertex> stageVertexMap = new HashMap<DocProcessStage, Vertex>();
		
		setTitle(String.format(ServerUtils.getUserLocale(), Messages.getInstance().getMessage(Messages.DOCFLOW_MAP_TITLE), docType.getCode().trim(), docType.getName().trim()));
		
		//заполним этапы ДО, сохраним связь между этапами и графическими элементами
		for (DocProcessStage stage : stages) {
			stageVertexMap.put(stage, createVertex(stage));
		}
		//заполним связи между этапами ДО
		for (DocProcessStage stage : stages) {
			if (stage.getNextStages() != null)
				for (LinkStage link : stage.getNextStages()) {
					createEdge(link, stageVertexMap.get(link.getPrevStage()), stageVertexMap.get(link.getNextStage()));
				}
		}
		
		run();
	}
}
