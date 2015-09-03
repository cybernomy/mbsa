/*
 * ColumnMappingPage.java
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
package com.mg.merp.wb.report.birt.data.oda.badi.ui.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.report.designer.internal.ui.util.ExceptionHandler;
import org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage;
import org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPageContainer;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.OdaDataSetHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.OdaResultSetColumn;
import org.eclipse.birt.report.model.api.util.CompatibilityUtil;
import org.eclipse.birt.report.model.elements.interfaces.IDataSetModel;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.mg.merp.wb.report.birt.data.oda.badi.ui.OdaUiPlugin;
import com.mg.merp.wb.report.birt.data.oda.badi.util.DataTypes;
import com.mg.merp.wb.report.birt.data.oda.badi.util.RelationInformation;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ColumnMappingPage.java,v 1.10 2007/10/25 12:31:50 safonov Exp $
 */
public class ColumnMappingPage extends WizardPage implements IPropertyPage,
		ITableLabelProvider {

	private final int DEFAULT_WIDTH = 300;

	private final int DEFAULT_HEIGHT = 200;

	private ColumnMappingTableViewer columnMappingTable;

	private Group rightGroup;

	private Text algCodeTextField;

	private String baiCode;

	private IPropertyPageContainer propertyContainer;

	private OdaDataSetHandle dataSetHandle;

	private Map<String, ColumnMappingElement> columnMap;

	private List<ColumnMappingElement> columnMappingList = new ArrayList<ColumnMappingElement>();

	private BadiConnection merpConn;

	private ColumnMappingElement newColumn;

	private static String COLUMN_NAME = OdaUiPlugin.getDefault().getString(
			"dataset.editor.columnName");

	private static String TYPE_NAME = OdaUiPlugin.getDefault().getString(
			"dataset.editor.datatype");

	private static String DEFAULT_PAGE_NAME = OdaUiPlugin.getDefault()
			.getString("merpBadi.messages.columnMapping");

	private static String DEFAULT_PAGE_MESSAGE = OdaUiPlugin.getDefault()
			.getString("wizard.title.defineColumnMapping");

	private static String[] dataTypeDisplayNames = new String[] {
			OdaUiPlugin.getDefault().getString("datatypes.dateTime"), //$NON-NLS-1$
			OdaUiPlugin.getDefault().getString("datatypes.decimal"), //$NON-NLS-1$
			OdaUiPlugin.getDefault().getString("datatypes.float"), //$NON-NLS-1$
			OdaUiPlugin.getDefault().getString("datatypes.integer"), //$NON-NLS-1$
			OdaUiPlugin.getDefault().getString("datatypes.date"),
			OdaUiPlugin.getDefault().getString("datatypes.time"),
			OdaUiPlugin.getDefault().getString("datatypes.string"),
			OdaUiPlugin.getDefault().getString("datatypes.boolean")};

	private static Map<String, String> javaTypeOdaTypeMap = new HashMap<String, String>();
	
	static {
		javaTypeOdaTypeMap.put("java.lang.Integer", DesignChoiceConstants.COLUMN_DATA_TYPE_INTEGER);
		javaTypeOdaTypeMap.put("java.lang.Double", DesignChoiceConstants.COLUMN_DATA_TYPE_FLOAT);
		javaTypeOdaTypeMap.put("java.lang.String", DesignChoiceConstants.COLUMN_DATA_TYPE_STRING);
		javaTypeOdaTypeMap.put("java.util.Date", DesignChoiceConstants.COLUMN_DATA_TYPE_DATE);
		javaTypeOdaTypeMap.put("java.sql.Time", DesignChoiceConstants.COLUMN_DATA_TYPE_TIME);
		javaTypeOdaTypeMap.put("java.sql.Timestamp", DesignChoiceConstants.COLUMN_DATA_TYPE_DATETIME);
		javaTypeOdaTypeMap.put("java.math.BigDecimal", DesignChoiceConstants.COLUMN_DATA_TYPE_DECIMAL);
		javaTypeOdaTypeMap.put("java.lang.Boolean", DesignChoiceConstants.COLUMN_DATA_TYPE_BOOLEAN);
	}

	public ColumnMappingPage() {
		super(DEFAULT_PAGE_NAME);
	}

	/**
	 * @param pageName
	 */
	protected ColumnMappingPage(String pageName) {
		super(pageName);
		this.setTitle(pageName);
		this.setMessage(DEFAULT_PAGE_MESSAGE);
		this.columnMap = new HashMap<String, ColumnMappingElement>();
		this.columnMappingList = new ArrayList<ColumnMappingElement>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#createPageControl(org.eclipse.swt.widgets.Composite)
	 */
	public Control createPageControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		initConnection();
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		createTopComposite(composite);

		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		Label algCodeL = new Label(composite, SWT.NONE);
		algCodeL.setText(OdaUiPlugin.getDefault().getString("dataset.editor.algorithmcode"));
		algCodeTextField = new Text(composite, SWT.BORDER);
		algCodeTextField.setLayoutData(data);
		algCodeTextField.setText(this.baiCode);

		setPageProperties();
		return composite;

	}

	/**
	 * when the page activated, the information about column will retrieved from
	 * the datasetHandle
	 * 
	 */
	private void initConnection() {
		this.merpConn = new BadiConnection();
		if (this.dataSetHandle != null) {
			merpConn.setRelationInformation((String) dataSetHandle.getQueryText());
			String tmp = (String) dataSetHandle.getProperty(Constants.BAI_CODE);
			this.baiCode = tmp != null ? tmp : "";
		}
	}

	/**
	 * create the top composite
	 * 
	 * @param parent
	 */
	private void createTopComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		FormLayout layout = new FormLayout();
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		FormData data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(100, -5);
		data.bottom = new FormAttachment(100, -5);
		data.width = DEFAULT_WIDTH;
		data.height = DEFAULT_HEIGHT;
		rightGroup = new Group(composite, SWT.NONE);

		rightGroup.setLayout(new FillLayout());
		rightGroup.setText(OdaUiPlugin.getDefault().getString("merpBadi.messages.columnMapping"));
		rightGroup.setLayoutData(data);

		rightGroup.setEnabled(true);
		columnMappingTable = new ColumnMappingTableViewer(rightGroup, true,	true, true);

		columnMappingTable.getViewer().getTable().setHeaderVisible(true);
		columnMappingTable.getViewer().getTable().setLinesVisible(true);

		TableColumn column = new TableColumn(columnMappingTable.getViewer().getTable(), SWT.LEFT);
		column.setText(COLUMN_NAME); //$NON-NLS-1$
		column.setWidth(100);

		column = new TableColumn(columnMappingTable.getViewer().getTable(),	SWT.LEFT);
		column.setText(TYPE_NAME); //$NON-NLS-1$
		column.setWidth(60);

		columnMappingTable.getViewer().setContentProvider(new IStructuredContentProvider() {

			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof ArrayList) {
					List<ColumnMappingElement> inputList = new ArrayList<ColumnMappingElement>(10);
					inputList.addAll(columnMappingList);

					if (newColumn == null) {
						newColumn = new ColumnMappingElement();
					}

					inputList.add(newColumn);
					return inputList.toArray();
				}

				return new Object[0];
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			public void dispose() {
			}

		});

		columnMappingTable.getViewer().setLabelProvider(this);
		columnMappingTable.getViewer().setInput(columnMappingList);
		refreshColumnMappingViewer();

		setupEditors();
		addListeners();
	}

	/**
	 * check whether the column is duplicated
	 * 
	 * @param newColumn1
	 * @return
	 */
	private boolean isUniqueName(String columnName,	ColumnMappingElement actualElement) {
		boolean success = true;
		if (columnMap != null) {
			if (columnMap.get(columnName) != actualElement && columnMap.get(columnName) != null) {
				setDetailsMessage(OdaUiPlugin.getDefault().getFormattedString("error.columnMapping.sameColumnName",
						new Object[] { columnName }), IMessageProvider.ERROR);
				success = false;
			} else {
				setDetailsMessage(DEFAULT_PAGE_NAME, IMessageProvider.NONE);
			}
		} else {
			setDetailsMessage(DEFAULT_PAGE_NAME, IMessageProvider.NONE);
			columnMap = new HashMap<String, ColumnMappingElement>();
			columnMappingList = new ArrayList<ColumnMappingElement>();
		}
		return success;
	}

	/**
	 * add the listener to the columnMappingTable.
	 */
	private void addListeners() {
		columnMappingTable.getViewer().getTable().addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					removeSelectedItem();
					setPageProperties();
				}
			}

		});

		columnMappingTable.getRemoveButton().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				removeSelectedItem();
				setPageProperties();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		columnMappingTable.getRemoveMenuItem().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				removeSelectedItem();
				setPageProperties();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		columnMappingTable.getRemoveAllMenuItem().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				removeAllItem();
				setPageProperties();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		columnMappingTable.getUpButton().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				upMoveSelectedItem();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		columnMappingTable.getDownButton().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				downMoveSelectedItem();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
	}

	/**
	 * remove the selected item in the table
	 * 
	 */
	private void removeSelectedItem() {
		int index = columnMappingTable.getViewer().getTable().getSelectionIndex();
		int count = columnMappingTable.getViewer().getTable().getItemCount();

		if (index > -1 && index < count) {
			TableItem item = columnMappingTable.getViewer().getTable().getItem(index);
			Object element = item.getData();
			String elementName = "";
			if (element instanceof ColumnMappingElement && element != newColumn) {
				ColumnMappingElement entry = (ColumnMappingElement) element;
				elementName = (String) entry.getColumnName();

				columnMappingTable.getViewer().getTable().select(index);

				this.columnMap.remove(elementName);
				this.columnMappingList.remove(index);
				merpConn.setRelationInformation(saveQueryString());
			}
		}
		if (columnMappingList.size() <= 0) {
			merpConn.setRelationInformation("");
		}
		refreshColumnMappingViewer();
	}

	/**
	 * remove all items of the table
	 * 
	 */
	private void removeAllItem() {
		int count = columnMappingTable.getViewer().getTable().getItemCount();
		for (int index = 0; index < count - 1; index++) {
			TableItem item = columnMappingTable.getViewer().getTable().getItem(0);

			Object element = item.getData();
			String elementName = "";
			if (element instanceof ColumnMappingElement) {
				ColumnMappingElement entry = (ColumnMappingElement) element;
				elementName = (String) entry.getColumnName();
			}
			columnMappingTable.getViewer().getTable().remove(0);
			this.columnMap.remove(elementName);
			this.columnMappingList.remove(0);
		}
		merpConn.setRelationInformation("");
		refreshColumnMappingViewer();
	}

	/**
	 * up move action
	 */
	private void upMoveSelectedItem() {
		int count = columnMappingTable.getViewer().getTable().getItemCount();
		int index = columnMappingTable.getViewer().getTable().getSelectionIndex();

		if (index > 0 && index < count) {
			ColumnMappingElement elt = this.columnMappingList.get(index);
			this.columnMappingList.set(index, this.columnMappingList.get(index - 1));
			this.columnMappingList.set(index - 1, elt);
			merpConn.setRelationInformation(saveQueryString());
			refreshColumnMappingViewer();
		}
	}

	/**
	 * down move action
	 * 
	 */
	private void downMoveSelectedItem() {
		int count = columnMappingTable.getViewer().getTable().getItemCount();
		int index = columnMappingTable.getViewer().getTable().getSelectionIndex();
		if (index > -1 && index < count - 2) {
			ColumnMappingElement elt = this.columnMappingList.get(index);
			this.columnMappingList.set(index, this.columnMappingList.get(index + 1));
			this.columnMappingList.set(index + 1, elt);
			merpConn.setRelationInformation(saveQueryString());
			refreshColumnMappingViewer();
		}
	}

	private void setupEditors() {
		CellEditor[] editors = new CellEditor[2];

		editors[0] = new TextCellEditor(columnMappingTable.getViewer().getTable(), SWT.NONE);
		editors[1] = new ComboBoxCellEditor(columnMappingTable.getViewer().getTable(), dataTypeDisplayNames, SWT.READ_ONLY);

		columnMappingTable.getViewer().setCellEditors(editors);
		columnMappingTable.getViewer().setColumnProperties(new String[] {COLUMN_NAME, TYPE_NAME});

		columnMappingTable.getViewer().setCellModifier(new ICellModifier() {

			public boolean canModify(Object element, String property) {
				return !(element == newColumn && !property.equals(COLUMN_NAME));
			}

			public Object getValue(Object element, String property) {
				Object value = null;
				try {
					if (property.equals(COLUMN_NAME))
						value = ((ColumnMappingElement) element).getColumnName();
					else if (property.equals(TYPE_NAME)) {
						String temp = ((ColumnMappingElement) element).getType();
						if (temp == null) {
							value = new Integer(0);
						} else {
							for (int i = 0; i < dataTypeDisplayNames.length; i++) {
								if (temp.equals(dataTypeDisplayNames[i])) {
									value = new Integer(i);
									break;
								}
							}
						}
					}

				} catch (Exception e) {
					ExceptionHandler.handle(e);
				}
				if (value == null) {
					value = ""; //$NON-NLS-1$
				}
				return value;
			}

			public void modify(Object element, String property, Object value) {
				Object actualElement = ((TableItem) element).getData();
				if (value != null) {
					if (property.equals(COLUMN_NAME)) {
						if (isUniqueName((String) value, (ColumnMappingElement) actualElement)) {
							((ColumnMappingElement) actualElement).setColumnName((String) value);
						} else
							return;
					} else if (property.equals(TYPE_NAME)) {
						int selectedType = ((Integer) value).intValue();

						((ColumnMappingElement) actualElement).setType(dataTypeDisplayNames[selectedType]);
					}
					columnMappingTable.getViewer().update(((TableItem) element).getData(), null);
					if (actualElement instanceof ColumnMappingElement) {
						if (newColumn.getColumnName() != null
								&& newColumn.getColumnName().trim().length() > 0) {
							columnMap.put(newColumn.getColumnName(), newColumn);
							columnMappingList.add(newColumn);
							clearNewColumnMapping();
							columnMappingTable.getViewer().refresh();
							setPageProperties();
						}
						refreshConnection();
					}
				}
			}
		});
	}

	private void refreshConnection() {
		if (this.merpConn != null)
			merpConn.setRelationInformation(saveQueryString());
	}

	private void clearNewColumnMapping() {
		newColumn = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#setContainer(org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPageContainer)
	 */
	public void setContainer(IPropertyPageContainer parentContainer) {
		propertyContainer = parentContainer;
		dataSetHandle = (OdaDataSetHandle) propertyContainer.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#canLeave()
	 */
	public boolean canLeave() {
		//TODO: Проверить на наличие хоть одного столбца
		//И поля код алгоритма 
		setDataSetConfig(this.merpConn.getRelationInformation());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#performOk()
	 */
	public boolean performOk() {
		if (algCodeTextField != null)
			this.baiCode = algCodeTextField.getText();
		setDataSetConfig(this.merpConn.getRelationInformation());
		return true;
	}

	/**
	 * set the detail error/warning/info messages
	 * 
	 * @param message
	 * @param type
	 */
	private void setDetailsMessage(String message, int type) {
		if (propertyContainer != null)
			propertyContainer.setMessage(message, type);
		else
			this.setMessage(message, type);
	}

	private void setDataSetConfig(String str) {
		try {
			if (this.dataSetHandle != null) {
				if (this.baiCode == null)
					this.baiCode = (String) this.dataSetHandle.getProperty(Constants.BAI_CODE);
				this.dataSetHandle.setQueryText(str);
				this.dataSetHandle.setProperty(Constants.BAI_CODE, this.baiCode);
				//обновить список resultSet, новое API BIRT 2.2, в дальнейшем
				//необходимо привести к новому API
				List<OdaResultSetColumn> list = new ArrayList<OdaResultSetColumn>();
				for (int i = 0; i < columnMappingList.size(); i++) {
					ColumnMappingElement element = columnMappingList.get(i);
					OdaResultSetColumn column = new OdaResultSetColumn();
					column.setColumnName(element.getColumnName());
					column.setNativeName(element.getColumnName());
					column.setDataType(javaTypeOdaTypeMap.get(element.getTypeStandardString()));
					column.setNativeDataType(DataTypes.getType(element.getTypeStandardString()));
					column.setPosition(i + 1); //index from 1
					list.add(column);
				}
				//т.к. список результата пересчитан полностью очистим старый список если он есть
				ArrayList resultSetPropValue = this.dataSetHandle.getPropertyHandle(IDataSetModel.RESULT_SET_PROP).getListValue();
				if (resultSetPropValue != null)
					resultSetPropValue.clear();
				//используем утилиты BIRT обратной совместимости
				CompatibilityUtil.addResultSetColumn(dataSetHandle, list);
			}
		} catch (SemanticException e) {
			throw new RuntimeException(e);
		} catch (OdaException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * get the query string from the list of column element
	 * 
	 * @return
	 */
	private String saveQueryString() {
		StringBuilder queryStr = new StringBuilder();
		Iterator<ColumnMappingElement> rowObj = this.columnMappingList.iterator();
		if (rowObj != null) {
			while (rowObj.hasNext()) {
				ColumnMappingElement element = rowObj.next();
				queryStr.append("{").append(element.getColumnName()).append(";")
						.append(element.getTypeStandardString()).append("}");
				if (rowObj.hasNext())
					queryStr.append(",");
			}
		}
		return queryStr.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#performCancel()
	 */
	public boolean performCancel() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#pageActivated()
	 */
	public void pageActivated() {
		setDetailsMessage(DEFAULT_PAGE_NAME, IMessageProvider.NONE);
		this.baiCode = (String) dataSetHandle.getProperty(Constants.BAI_CODE);
		String infoString = (String) dataSetHandle.getQueryText();
		initConnection();

		merpConn.setRelationInformation(infoString);
		RelationInformation info = null;
		if (infoString != null && infoString.trim().length() > 0)
			try {
				//pups
				info = new RelationInformation(infoString);
			} catch (OdaException e) {
				throw new RuntimeException(e);
			}

		this.columnMap = new HashMap<String, ColumnMappingElement>();
		this.columnMappingList = columnMappingTable.refresh(info, this.columnMap);

		refreshColumnMappingViewer();

		setPageProperties();
	}

	/**
	 * refresh the column mapping viewer
	 * 
	 * @param columnMap
	 */
	private void refreshColumnMappingViewer() {
		columnMappingTable.getViewer().setInput(columnMappingList);
		for (int i = 0; i < columnMappingTable.getViewer().getTable().getItemCount() - 1; i++) {
			TableItem ti = columnMappingTable.getViewer().getTable().getItem(i);

			Object element = ti.getData();

			String c1 = "", c2 = ""; //$NON-NLS-2$ //$NON-NLS-3$

			if (element instanceof ColumnMappingElement) {
				ColumnMappingElement colElement = (ColumnMappingElement) element;

				c1 = colElement.getColumnName();
				c2 = colElement.getType();
			}
			ti.setText(0, c1);
			ti.setText(1, c2);
		}
		clearNewColumnMapping();
		columnMappingTable.getViewer().refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#getToolTip()
	 */
	public String getToolTip() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		setControl(createPageControl(parent));
	}

	/**
	 * @param dataSetHandle
	 */
	public void setDataSet(DataSetHandle dataSetHandle) {
		this.dataSetHandle = (OdaDataSetHandle) dataSetHandle;
		this.baiCode = (String) dataSetHandle.getProperty(Constants.BAI_CODE);
		if (this.baiCode == null)
			this.baiCode = "";
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		String value = null;
		try {
			if (element != newColumn) {
				switch (columnIndex) {
				case 0: {
					value = (String) ((ColumnMappingElement) element).getColumnName();
					break;
				}
				case 1: {
					value = (String) ((ColumnMappingElement) element).getType();
					break;
				}
				}
			} else if (columnIndex == 0) {
				value = OdaUiPlugin.getDefault().getString("ColumnMappingDialog.prompt.new");
			}
		} catch (Exception ex) {
			ExceptionHandler.handle(ex);
		}
		if (value == null) {
			value = ""; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * Depending on the column mapping, the properties of various controls
	 * on this page are set
	 */
	private void setPageProperties() {
		boolean columnMappingExist = false;

		columnMappingExist = (columnMappingList != null && columnMappingList.size() > 0);
		columnMappingTable.getDownButton().setEnabled(columnMappingExist);
		columnMappingTable.getUpButton().setEnabled(columnMappingExist);
		columnMappingTable.getRemoveButton().setEnabled(columnMappingExist);
		columnMappingTable.getRemoveMenuItem().setEnabled(columnMappingExist);
		columnMappingTable.getRemoveAllMenuItem().setEnabled(columnMappingExist);
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}

	public String getBAiCode() {
		return baiCode;
	}
}
