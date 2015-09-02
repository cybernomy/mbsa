/*
 * GraphController.java
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
package com.mg.framework.support.ui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.support.ui.widget.graph.Edge;
import com.mg.framework.support.ui.widget.graph.GraphElement;
import com.mg.framework.support.ui.widget.graph.Vertex;

/**
 * @author Oleg V. Safonov
 * @version $Id: GraphController.java,v 1.1 2006/03/27 10:43:09 safonov Exp $
 */
public class GraphController implements GraphControllerAdapter {
	private List<GraphElement> elements;
	private GraphModelListener modelListener;
	private List<GraphElement> selectedElements;

	protected void doCellChanged(GraphElement cell) {
		
	}
	
	public GraphController() {
		this.elements = new ArrayList<GraphElement>();
		this.selectedElements = new ArrayList<GraphElement>();
	}

	public void fireVertexAdded(GraphModelEvent event) {
		if (modelListener != null)
			modelListener.vertexAdded(event);
	}

	public void fireEdgeAdded(GraphModelEvent event) {
		if (modelListener != null)
			modelListener.edgeAdded(event);
	}

	public void fireEdgeRemoved(GraphModelEvent event) {
		if (modelListener != null)
			modelListener.cellRemoved(event);
	}

	public List<GraphElement> getSelectedElements() {
		return new ArrayList<GraphElement>(selectedElements);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#getElements()
	 */
	public List<? extends GraphElement> getElements() {
		return new ArrayList<GraphElement>(elements);//clone
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#addGraphModelListener(com.mg.framework.support.ui.widget.GraphModelListener)
	 */
	public void addGraphModelListener(GraphModelListener listener) {
		modelListener = listener;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#insertVertex(com.mg.framework.support.ui.widget.graph.Vertex)
	 */
	public void insertVertex(Vertex vertex) {
		elements.add(vertex);
		fireVertexAdded(new GraphModelEvent(this, vertex));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#insertEdge(com.mg.framework.support.ui.widget.graph.Edge)
	 */
	public void insertEdge(Edge edge) {
		elements.add(edge);
		fireEdgeAdded(new GraphModelEvent(this, edge));
	}

	public void removeCell(GraphElement cell) {
		fireEdgeRemoved(new GraphModelEvent(this, cell));
		elements.remove(cell);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#selectCells()
	 */
	public void selectCells(GraphElement[] cells, boolean[] areNewRoots) {
		//selectedElements.clear();
		for (int i = 0; i < cells.length; i++)
			if (!areNewRoots[i])
				selectedElements.remove(cells[i]);
			else if (!selectedElements.contains(cells[i]))
				selectedElements.add(cells[i]);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.GraphControllerAdapter#cellChanged(com.mg.framework.support.ui.widget.graph.GraphElement)
	 */
	public void cellChanged(GraphElement cell) {
		doCellChanged(cell);
	}

}
