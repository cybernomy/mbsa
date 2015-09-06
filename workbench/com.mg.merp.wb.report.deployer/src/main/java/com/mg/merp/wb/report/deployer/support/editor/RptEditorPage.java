/* RptEditorPage.java
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
package com.mg.merp.wb.report.deployer.support.editor;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.security.model.Groups;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.BeanSelectionDialog;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.dialogs.SecGroupsSelectionDialog;
import com.mg.merp.wb.core.ui.editor.StandartEditorPage;
import com.mg.merp.wb.report.deployer.DeployerPlugin;
import com.mg.merp.wb.report.deployer.support.RptEditorController;
import com.mg.merp.wb.report.deployer.support.utils.RptTool;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Содержание формы редактирования отчёта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: RptEditorPage.java,v 1.4 2007/08/30 15:00:28 safonov Exp $
 */
public class RptEditorPage extends StandartEditorPage<RptMainTransfer> {

  private static final String RIGHTS_DESC = "rights.desc";
  private static final String SYS_CLASS_DESC = "sc.desc";
  private static final String FIELD_CODE = "report.form.edit.code";
  private static final String FIELD_NAME = "report.form.edit.name";
  private static final String FIELD_DESC = "report.form.edit.desc";
  private static final String FIELD_PRIOR = "report.form.edit.prior";
  private static final String FIELD_OUTPUT_FORMAT = "report.form.edit.outputformat";
  private static final String MAIN_TAB_NAME = "report.form.edit.tab.name1";
  private static final String BEANS_TAB_NAME = "report.form.edit.tab.name2";
  private static final String RIGHTS_TAB_NAME = "report.form.edit.tab.name3";
  private static final String FIELD_PARAM_FORM_NAME = "report.form.edit.param.form.name";
  private static final String FIELD_ASK_PARAM = "report.form.edit.ask";
  private static final String BEANS_COL1_NAME = "report.form.edit.beans.col1";
  private static final String BEANS_COL2_NAME = "report.form.edit.beans.col2";
  private static final int DESC_FIELD_WIDTH = 395;
  private static final int PARAM_FIELD_WIDTH = 345;
  private static final int CODE_FIELD_WIDTH = 220;
  private static final int PRIOR_FIELD_WIDTH = 30;
  private static final int BEANS_COL1_WIDTH = 230;

	/*private static final String BUTT_SAVE_TEXT = "report.butt.save";

	private static final String BUTT_CANCEL_TEXT = "report.butt.cancel";*/
  private static final int BEANS_COL2_WIDTH = 400;
  private static final String TEMPLATE_NULL = "tmpl.isnull";
  private static GridData commonGridData = new GridData();
  private static GridLayout commonGridLayout = new GridLayout();

  static {
    commonGridData.horizontalAlignment = GridData.FILL;
    commonGridData.grabExcessHorizontalSpace = true;
    commonGridData.verticalAlignment = GridData.CENTER;

    commonGridLayout.marginWidth = 1;
    commonGridLayout.marginHeight = 1;
    commonGridLayout.numColumns = 3;
  }

  private RptMainTransfer rmt;
  /**
   * Контроллер формы редактирования
   */
  private RptEditorController formController;
  /**
   * Контроллер таблицы "Бизнес-компоненты"
   */
  private TableViewer beanTableViewer;
  /**
   * Контроллер таблицы "Права на отчёты"
   */
  private TableViewer rightsTableViewer;
  private String bufferClassnames;
  /**
   * Текстовое поле, содержащее код отчёта
   */
  private Text tCode = null;

  /**
   * Текстовое поле, содержащее наименование отчёта
   */
  private Text tName = null;

  /**
   * формат вывода отчета
   */
  private Combo cOutputFormat = null;

  /**
   * Текстовое поле, содержащее описание отчёта
   */
  private Text tDescription = null;

  /**
   * Полное имя формы запроса параметров отчёта
   */
  private Text tParamsFormName = null;

  /**
   * Приоритет отчёта
   */
  private Spinner spPrior = null;

  /**
   * Запрос параметров
   */
  private Button cbAsk = null;

  //private Button cbDP = null;


  public RptEditorPage(FormEditor editor) {
    super(editor);
    formController = new RptEditorController();
  }

  @Override
  public void createEditorArea(Composite parent) {
    rmt = ((RptEditorInput) getEditorInput()).getData();
    bufferClassnames = new String(rmt.classNames);

    form.setText(String.format(DeployerPlugin.getDefault().getString(
        RptEditorInput.FORM_PART), rmt.code));

    Composite editorComposite = form.getBody();
    editorComposite.setLayout(new GridLayout());
    editorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    createSection1(editorComposite);
    createSection2(editorComposite);
    createSection3(editorComposite);
  }

  @Override
  public void doOnSaveClick() {
    rmt.code = tCode.getText();
    rmt.name = tName.getText();
    rmt.paramsFormName = tParamsFormName.getText().length() > 0 ? tParamsFormName.getText() : null;
    rmt.comment = tDescription.getText();
    rmt.askParams = cbAsk.getSelection();
    rmt.priority = spPrior.getSelection();
    rmt.directPrint = false;
    rmt.classNames = bufferClassnames;
    rmt.outputFormat = cOutputFormat.getText();

    rmt.template = RptTool.getData(((RptEditorInput) getEditorInput()).getTemplate());
    if (rmt.template != null)
      editor.doSave(null);
    else
      Dialogs.showMessage(String.format(DeployerPlugin.getDefault()
          .getString(TEMPLATE_NULL), rmt.code), SWT.OK
          | SWT.ICON_ERROR);
  }

  @Override
  public void formContentPrecreating(RptMainTransfer object) {
    this.rmt = object;
  }

  @Override
  public String getEditorText() {
    return rmt.code.trim();
  }

  private void createSection1(Composite parent) {
    Section section = toolkit.createSection(parent,
        ExpandableComposite.TITLE_BAR | Section.TWISTIE);
    section.setText(DeployerPlugin.getDefault().getString(MAIN_TAB_NAME));
    section.setLayout(new GridLayout());
    section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    section.setExpanded(true);
    section.addExpansionListener(new IExpansionListener() {
      public void expansionStateChanging(ExpansionEvent e) {
        form.reflow(true);
      }

      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });

    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);

    GridLayout gridLayout = new GridLayout();
    gridLayout.verticalSpacing = 1;
    gridLayout.marginWidth = 1;
    gridLayout.marginHeight = 1;
    gridLayout.horizontalSpacing = 1;
    gridLayout.numColumns = 1;

    sectionClient.setLayout(gridLayout);
    createReportTab(sectionClient);
    toolkit.paintBordersFor(sectionClient);
  }

  private void createSection2(Composite parent) {
    Section section = toolkit.createSection(parent,
        ExpandableComposite.TITLE_BAR | Section.TWISTIE);
    section.setText(DeployerPlugin.getDefault().getString(BEANS_TAB_NAME));
    section.setLayout(new GridLayout());
    section.setLayoutData(new GridData(GridData.FILL_BOTH));
    section.setExpanded(true);
    section.addExpansionListener(new IExpansionListener() {
      public void expansionStateChanging(ExpansionEvent e) {
        form.reflow(true);
      }

      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });

    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);
    sectionClient.setLayout(new GridLayout());
    createClassesTab(sectionClient);
    toolkit.paintBordersFor(sectionClient);
  }

  private void createSection3(Composite parent) {
    Section section = toolkit.createSection(parent,
        ExpandableComposite.TITLE_BAR | Section.TWISTIE);
    section.setText(DeployerPlugin.getDefault().getString(RIGHTS_TAB_NAME));
    section.setLayout(new GridLayout());
    section.setLayoutData(new GridData(GridData.FILL_BOTH));
    section.setExpanded(true);
    section.addExpansionListener(new IExpansionListener() {
      public void expansionStateChanging(ExpansionEvent e) {
        form.reflow(true);
      }

      public void expansionStateChanged(ExpansionEvent e) {
        form.reflow(true);
      }
    });

    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);
    sectionClient.setLayout(new GridLayout());
    createRightsTab(sectionClient);
    toolkit.paintBordersFor(sectionClient);
  }

  private void createClassesTab(Composite parent) {
    Table tblBeans = toolkit.createTable(parent, SWT.BORDER | SWT.V_SCROLL
        | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
    tblBeans.setHeaderVisible(true);
    tblBeans.setLinesVisible(true);
    tblBeans.setLayoutData(new GridData(GridData.FILL_BOTH));

    TableColumn tBeansCol1 = new TableColumn(tblBeans, SWT.NONE);
    tBeansCol1.setWidth(BEANS_COL1_WIDTH);
    tBeansCol1.setText(DeployerPlugin.getDefault().getString(
        BEANS_COL1_NAME));
    tBeansCol1.setAlignment(SWT.LEFT);

    TableColumn tBeansCol2 = new TableColumn(tblBeans, SWT.NONE);
    tBeansCol2.setWidth(BEANS_COL2_WIDTH);
    tBeansCol2.setText(DeployerPlugin.getDefault().getString(
        BEANS_COL2_NAME));
    tBeansCol2.setAlignment(SWT.LEFT);

    beanTableViewer = new TableViewer(tblBeans);
    beanTableViewer.setContentProvider(formController);
    beanTableViewer.setLabelProvider(formController);

    beanTableViewer.setInput(bufferClassnames);

    Menu menu = new Menu(tblBeans);
    MenuItem miAdd = new MenuItem(menu, SWT.NONE);
    miAdd.setText(DeployerPlugin.getDefault().getString("sc.menu.add"));
    miAdd.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }

      public void widgetSelected(SelectionEvent e) {
        BeanSelectionDialog bsd;
        try {
          bsd = new BeanSelectionDialog();
          bsd.setInitialElementSelections(Arrays.asList(CoreUtils
              .stringToStringArray(bufferClassnames, ";")));
          bsd.setMessage(DeployerPlugin.getDefault().getString(SYS_CLASS_DESC));

          List<String> lst = new LinkedList<String>();
          String[] st = CoreUtils.stringToStringArray(bufferClassnames,
              ";");
          if (st != null)
            for (int i = 0; i < st.length; i++)
              lst.add(st[i].substring(st[i].indexOf("->") + 2,
                  st[i].length()));
          bsd.setPreChecked(lst);
          if (bsd.open() == Window.OK) {
            bufferClassnames = CoreUtils.stringListToString(bsd
                .getSelectedBeans(), ";");
            beanTableViewer.setInput(bufferClassnames);
          }
        } catch (Exception ex) {
          Dialogs.openError(DeployerPlugin.getDefault().getString(
                  "sc.load.error"), DeployerPlugin.getDefault()
                  .getString("server.check.message"),
              DeployerPlugin.ID, ex);
        }
      }

    });
    MenuItem miDel = new MenuItem(menu, SWT.NONE);
    miDel.setText(DeployerPlugin.getDefault().getString("sc.menu.del"));
    miDel.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }

      public void widgetSelected(SelectionEvent e) {
        TableItem[] curBeans = beanTableViewer.getTable()
            .getSelection();
        StringBuilder sb = new StringBuilder(bufferClassnames);
        for (int i = 0; i < curBeans.length; i++) {
          int posS = sb.indexOf(curBeans[i].getData().toString());
          int posE = sb.indexOf(";", posS);
          sb = sb.delete(posS, posE + 1);
        }
        bufferClassnames = new String(sb);
        beanTableViewer.setInput(bufferClassnames);
      }

    });
    tblBeans.setMenu(menu);

  }


  private void createRightsTab(Composite parent) {
    Table tblRights = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
    tblRights.setHeaderVisible(true);
    tblRights.setLinesVisible(true);

    TableColumn colGroup = new TableColumn(tblRights, SWT.NONE);
    colGroup.setWidth(630);
    colGroup.setAlignment(SWT.LEFT);

    rightsTableViewer = new TableViewer(tblRights);
    rightsTableViewer.setContentProvider(new IStructuredContentProvider() {

      @SuppressWarnings("unchecked")
      public Object[] getElements(Object inputElement) {
        return ((ArrayList<Object>) inputElement).toArray();
      }

      public void dispose() {
        // TODO Auto-generated method stub

      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub

      }

    });
    rightsTableViewer.setLabelProvider(new ITableLabelProvider() {

      public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
      }

      public String getColumnText(Object element, int columnIndex) {
        return (String) element;
      }

      public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

      }

      public void dispose() {
        // TODO Auto-generated method stub

      }

      public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
      }

      public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

      }

    });

    rightsTableViewer.setInput(rmt.secGroupsNames);

    Menu menu = new Menu(tblRights);
    MenuItem miAdd = new MenuItem(menu, SWT.NONE);
    miAdd.setText(UiPlugin.getDefault().getFormattedString(UiPlugin.MENU_ADD, new Object[]{""}));
    miAdd.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }

      public void widgetSelected(SelectionEvent e) {
        SecGroupsSelectionDialog sgsd;

        sgsd = new SecGroupsSelectionDialog();
        sgsd.setMessage(DeployerPlugin.getDefault().getString(RIGHTS_DESC));
        if (sgsd.open() == Window.OK) {
          Groups group = sgsd.getSelectedGroup();
          if (!rmt.secGroupsIds.contains(group.getId())) {
            rmt.secGroupsIds.add(group.getId());
            rmt.secGroupsNames.add(group.getName().trim());
            rightsTableViewer.setInput(rmt.secGroupsNames);
          }
        }
      }

    });
    MenuItem miDel = new MenuItem(menu, SWT.NONE);
    miDel.setText(UiPlugin.getDefault().getFormattedString(UiPlugin.MENU_DEL, new Object[]{""}));
    miDel.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

      }

      public void widgetSelected(SelectionEvent e) {
        int[] selectedRights = rightsTableViewer.getTable().getSelectionIndices();
        for (int i = 0; i < selectedRights.length; i++) {
          rmt.secGroupsIds.remove(selectedRights[i]);
          rmt.secGroupsNames.remove(selectedRights[i]);
        }
        rightsTableViewer.setInput(rmt.secGroupsNames);
      }

    });
    tblRights.setMenu(menu);
  }

  private void createReportTab(Composite parent) {
    createCmpz1(parent);
    createCmpz2(parent);
    createCmpz3(parent);
  }

  private void createCmpz1(Composite parent) {
    Composite cmpz1 = toolkit.createComposite(parent);
    cmpz1.setLayout(commonGridLayout);
    cmpz1.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz1, DeployerPlugin.getDefault()
        .getString(FIELD_CODE));
    label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

    // 1 Blank column
    toolkit.createLabel(cmpz1, "");

    label = toolkit.createLabel(cmpz1, DeployerPlugin.getDefault()
        .getString(FIELD_NAME));
    // Code
    tCode = toolkit.createText(cmpz1, rmt.code, SWT.BORDER);
    GridData tCodeDataLayout = new GridData();
    tCodeDataLayout.widthHint = CODE_FIELD_WIDTH;
    tCode.setLayoutData(tCodeDataLayout);
    tCode.setEditable(false);

    // 1 Blank column
    Label dummy = toolkit.createLabel(cmpz1, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 50;
    dummy.setLayoutData(dummyLabelDataLayout);

    // Name
    tName = toolkit.createText(cmpz1, rmt.name, SWT.BORDER);
    tName.setLayoutData(tCodeDataLayout);
  }

  private void createCmpz2(Composite parent) {
    Composite cmpz2 = toolkit.createComposite(parent);
    cmpz2.setLayout(commonGridLayout);
    cmpz2.setLayoutData(commonGridData);

    toolkit.createLabel(cmpz2, DeployerPlugin.getDefault().getString(
        FIELD_DESC));
    // 1 Blank column
    toolkit.createLabel(cmpz2, DeployerPlugin.getDefault().getString(FIELD_OUTPUT_FORMAT));

    toolkit.createLabel(cmpz2, DeployerPlugin.getDefault().getString(
        FIELD_PRIOR));

    // Description
    GridData tDescDataLayout = new GridData();
    tDescDataLayout.widthHint = DESC_FIELD_WIDTH;
    tDescription = toolkit.createText(cmpz2, rmt.comment, SWT.BORDER);
    tDescription.setLayoutData(tDescDataLayout);

    // 1 Blank column
//		Label dummy = toolkit.createLabel(cmpz2, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 50;
//		dummy.setLayoutData(dummyLabelDataLayout);
//		dummy.setLayoutData(dummyLabelDataLayout);

    //output format
    cOutputFormat = new Combo(cmpz2, SWT.READ_ONLY | SWT.BORDER);
    cOutputFormat.setLayoutData(dummyLabelDataLayout);
    cOutputFormat.setItems(new String[]{"HTML", "PDF", "XLS", "DOC", "PPT"});
    if (rmt.outputFormat != null)
      cOutputFormat.setText(rmt.outputFormat);
    //cOutputFormat.

    // Priority
    spPrior = new Spinner(cmpz2, SWT.BORDER);
    spPrior.setSelection(rmt.priority == null ? 0 : rmt.priority.intValue());
    spPrior.setDigits(0);
    spPrior.setMaximum(100);
    spPrior.setMinimum(0);
    spPrior.setIncrement(1);

    spPrior.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
    GridData priorDataLayout = new GridData();
    priorDataLayout.widthHint = PRIOR_FIELD_WIDTH;
    spPrior.setLayoutData(priorDataLayout);
  }

  private void createCmpz3(Composite parent) {
    Composite cmpz3 = toolkit.createComposite(parent);
    cmpz3.setLayout(commonGridLayout);
    cmpz3.setLayoutData(commonGridData);

    // Params form name
    toolkit.createLabel(cmpz3, DeployerPlugin.getDefault().getString(
        FIELD_PARAM_FORM_NAME));

    // 2 Blank column
    toolkit.createLabel(cmpz3, "");
    toolkit.createLabel(cmpz3, "");

    // Params form name
    GridData tParamsFormDataLayout = new GridData();
    tParamsFormDataLayout.widthHint = PARAM_FIELD_WIDTH;
    tParamsFormName = toolkit.createText(cmpz3, rmt.paramsFormName,
        SWT.BORDER);
    tParamsFormName.setLayoutData(tParamsFormDataLayout);

    // 1 Blank column
    Label dummy = toolkit.createLabel(cmpz3, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 25;
    dummy.setLayoutData(dummyLabelDataLayout);

    // Ask params
    cbAsk = toolkit.createButton(cmpz3, DeployerPlugin.getDefault()
        .getString(FIELD_ASK_PARAM), SWT.CHECK);
    cbAsk.setSelection(rmt.askParams);
  }

}
