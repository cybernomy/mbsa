/* BeanSelectionDialog.java
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
package com.mg.merp.wb.core.ui.dialogs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.support.connector.WorkbenchService;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.widgets.MemoryFilter;



/**
 * Диалог выбора бизнес компонентов системы MBSA
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: BeanSelectionDialog.java,v 1.4 2007/07/11 05:57:03 poroxnenko Exp $ 
 */
public class BeanSelectionDialog extends SelectionDialog {

	public static final String TITLE = "dialogs.beans.title";
	public static final String LOAD_BEANS_ERR = "dialog.beans.load.error";
	
	
	private TreeModel beans;
	private CheckboxTreeViewer treeViewer;
	private MemoryFilter memoryFilter;
	private PatternFilter patternFilter;
	private List<String> selectedBeans = new LinkedList<String>(); 
	private List<String> preChecked = new LinkedList<String>();
	
	
	class MCheckStateListener implements ICheckStateListener{
	    public void checkStateChanged(CheckStateChangedEvent event) {
	    	check(event);
	    }

		private void check(CheckStateChangedEvent event) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)event.getElement();
			if (node.getDepth() == 1){
				treeViewer.setGrayed(node, false);
				checkChild(node,event.getChecked());
			}
			else
				checkParent(node,event.getChecked());
		}

		private void checkParent(DefaultMutableTreeNode node, boolean checked) {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
			int count = parent.getChildCount();
			int checkedNodesCount = 0;
			for(int i = 0; i < count; i++){
				if (treeViewer.getChecked(parent.getChildAt(i))){
					checkedNodesCount++;
					addToFilter(node, checked);
				}
			}
			if (checkedNodesCount == count){
				treeViewer.setChecked(parent, true);
				treeViewer.setGrayed(parent, false);
			}else if (checkedNodesCount == 0){
				treeViewer.setChecked(parent, false);
				treeViewer.setGrayed(parent, false);
			}
			else
				treeViewer.setGrayChecked(parent, true);
			
		}

		private void checkChild(DefaultMutableTreeNode node, boolean checked) {
			/*корневой узел в PatternFilter не обязательно. т.к. если включены дети. то родитель 
			 * автоматически включается в условия отбора*/
			for(int i = 0; i<node.getChildCount();i++){
				List<Object> chkd = new LinkedList<Object>();
				try{
					chkd = Arrays.asList(memoryFilter.getPatternFilter().filter(treeViewer, node, null));
				}catch(Exception ex){};
				
				DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getChildAt(i);
				/*двойная проверка условия, похоже ещё один баг в 
				 * CheckboxTreeViewer. Элемент наприсован, но считается невидимым*/
				if (memoryFilter.getPatternFilter().isElementVisible(treeViewer, child)
					|| chkd.contains(child)){
					treeViewer.setChecked(child, checked);
					addToFilter((DefaultMutableTreeNode)child, checked);
				}else
					if (checked)treeViewer.setGrayChecked(node, true);
			}
		}
		
		private void addToFilter(DefaultMutableTreeNode node, boolean checked){
			if (checked){
				((ExtPatternFilter)memoryFilter.getPatternFilter()).addMatch((String)node.getUserObject());
				String obj = ((String)((DefaultMutableTreeNode)node.getParent()).getUserObject())+"->"+(String)node.getUserObject();
				if (!selectedBeans.contains(obj))
					selectedBeans.add(obj);
			}
			else{
				((ExtPatternFilter)memoryFilter.getPatternFilter()).removeMatch((String)node.getUserObject());
				selectedBeans.remove(((String)((DefaultMutableTreeNode)node.getParent()).getUserObject())+"->"+(String)node.getUserObject());
			}
		}

	}
	private MCheckStateListener mChckLstnr = new MCheckStateListener();
	
	class MTreeContentProvider implements ITreeContentProvider{
		
		private List<DefaultMutableTreeNode> preCheckedNodes = new LinkedList<DefaultMutableTreeNode>();
		
		public Object[] getChildren(Object parentElement) {
			DefaultMutableTreeNode parent = ((DefaultMutableTreeNode)parentElement);
			DefaultMutableTreeNode[] result = new DefaultMutableTreeNode[parent.getChildCount()];
			int count = parent.getChildCount();
			for(int i = 0; i < count; i++)
				result[i] = (DefaultMutableTreeNode)parent.getChildAt(i);
			return result;
		}

		public Object getParent(Object element) {
			return ((DefaultMutableTreeNode)element).getParent();
		}

		public boolean hasChildren(Object element) {
			return (((DefaultMutableTreeNode)element).getAllowsChildren() && ((DefaultMutableTreeNode)element).getChildCount() > 0);
		}

		public Object[] getElements(Object inputElement) {
			TreeModel model = ((TreeModel)inputElement);
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot(); 
			int count = root.getChildCount();
			DefaultMutableTreeNode[] result = new DefaultMutableTreeNode[count];
			for(int i = 0; i<count;i++){
				result[i] = (DefaultMutableTreeNode)root.getChildAt(i);
				int childCount = result[i].getChildCount();
				for(int j = 0; j < childCount; j++){
					String title = (String)((DefaultMutableTreeNode)result[i].getChildAt(j)).getUserObject();
					if (preChecked.contains(title)){
						DefaultMutableTreeNode nd = (DefaultMutableTreeNode)result[i].getChildAt(j);
						preCheckedNodes.add(nd);
					}
				}
			}
			/*обнуляем, т.к. только при первой загрузке нужно установить выделение*/
			preChecked = new LinkedList<String>();
			return result;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public List<DefaultMutableTreeNode> getPreCheckedNodes() {
			return preCheckedNodes;
		}
	}
	private MTreeContentProvider mTreeContent = new MTreeContentProvider();
	
	public BeanSelectionDialog() {
		super(CoreUtils.getMainShell());
		try {
			this.beans = WorkbenchService.getSysClasses();
		} catch (Exception ex) {
			Dialogs.openError(UiPlugin.getDefault().getString(LOAD_BEANS_ERR), UiPlugin.getDefault().getString(UiPlugin.CHECK_SERVER),
					UiPlugin.ID, ex);
		}
		setTitle(UiPlugin.getDefault().getString(TITLE));
	}
	
	protected Control createDialogArea(Composite parent) {
		// page group
		Composite composite=(Composite) super.createDialogArea(parent);

		Font font= parent.getFont();
		composite.setFont(font);

		createMessageArea(composite);

		createTreeArea(parent);
		return composite;
	}
	public Object[] getResult() {
		setResult(selectedBeans);
		return super.getResult();
	}
	
	public List<String> getSelectedBeans(){
		return selectedBeans;
	}
	
	private void createTreeArea(Composite parent) {
		memoryFilter = new MemoryFilter(parent, SWT.FILL);
		if (patternFilter == null)
			memoryFilter.setPatternFilter(new ExtPatternFilter());
		else
			memoryFilter.setPatternFilter(patternFilter);
		GridData mfData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		memoryFilter.setLayoutData(mfData);

        treeViewer = new CheckboxTreeViewer(parent);
		treeViewer.setContentProvider(mTreeContent);
		
		treeViewer.setLabelProvider(new ILabelProvider(){
			public Image getImage(Object element) {
				return null;
			}
			
			public String getText(Object element) {
				return (String)((DefaultMutableTreeNode)element).getUserObject();
			}
			
			public void addListener(ILabelProviderListener listener) {
			}
			
			public void dispose() {
				beans = null;
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
			}
		});
		
		treeViewer.addCheckStateListener(mChckLstnr);

		treeViewer.addFilter(memoryFilter.getPatternFilter());
		
		treeViewer.setInput(beans);
    
		//treeViewer.setCheckedElements(mTreeContent.getPreCheckedNodes());
		for(DefaultMutableTreeNode cn:mTreeContent.getPreCheckedNodes()){
			treeViewer.setChecked(cn, true);
			mChckLstnr.checkStateChanged(new CheckStateChangedEvent(treeViewer,cn,true));
		}
		
		memoryFilter.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent e) {
				
			}

			public void widgetSelected(SelectionEvent e) {
				/*Похоже баг в CheckboxTreeViewer: после обновления treeViewer узлы. выделенные не кликом,
				 * а методом setChecked(..) остаётся выделенным только корень.
				 * Поэтому запоминаем те, что выделены сейчас*/
				Object[] ch = treeViewer.getCheckedElements();
				treeViewer.refresh();
				/*устанавливаем обратно*/
				treeViewer.setCheckedElements(ch);
			}
			
		});
		
		memoryFilter.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13)
					treeViewer.refresh();
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint= 400;
		data.widthHint= 400;
		treeViewer.getTree().setLayoutData(data);
	}

	public PatternFilter getPatternFilter() {
		return patternFilter;
	}

	public void setPatternFilter(PatternFilter patternFilter) {
		this.patternFilter = patternFilter;
	}
	
	public void setPreChecked(List<String> preChecked){
		this.preChecked = preChecked;
	}
	
}
