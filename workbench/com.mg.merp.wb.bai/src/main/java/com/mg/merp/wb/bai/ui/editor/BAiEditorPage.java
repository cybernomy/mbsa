/* BAiEditorPage.java
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
package com.mg.merp.wb.bai.ui.editor;

import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.wb.bai.BAiPlugin;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.core.ui.editor.StandartEditorPage;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Содержание формы редактирования BAi
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: BAiEditorPage.java,v 1.3 2007/05/04 10:48:42 poroxnenko Exp $
 */
public class BAiEditorPage extends StandartEditorPage<Repository> {

  private static final String FIELD_CODE = "bai.form.edit.code";
  private static final String FIELD_NAME = "bai.form.edit.name";
  private static final String FIELD_IMPL = "bai.form.edit.impl";
  private static final String BROWSE_FORM = "bai.form.edit.browse.form.text";
  private static final String BROWSE_DIALOG_TITLE = "bai.form.edit.browse.dialog.title";
  private static final int IMPL_FIELD_WIDTH = 435;
  private static final int CODE_FIELD_WIDTH = 220;
  /**
   * Данные BAi
   */
  private Repository repository;
  /**
   * Текстовое поле, содержащее код BAi
   */
  private Text tCode = null;

  /**
   * Текстовое поле, содержащее наименование BAi
   */
  private Text tName = null;

  /**
   * Текстовое поле, содержащее имя класса, реализующего BAi
   */
  private Text tImplName = null;

  private boolean isNew;

  public BAiEditorPage(FormEditor editor) {
    super(editor);
  }

  @Override
  public void createEditorArea(Composite parent) {
    this.repository = ((StandartEditorInput<Repository>) getEditorInput())
        .getData();
    this.isNew = ((StandartEditorInput<Repository>) getEditorInput())
        .isCreateNew();
    Section section = toolkit.createSection(parent,
        ExpandableComposite.TITLE_BAR);
    section.setLayout(new GridLayout());
    section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    form.setText(String.format(BAiPlugin.getDefault().getString(
        BAiEditorInput.FORM_PART), repository.getCode()));

    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);

    GridLayout gridLayout = new GridLayout();
    gridLayout.verticalSpacing = 1;
    gridLayout.marginWidth = 1;
    gridLayout.marginHeight = 1;
    gridLayout.horizontalSpacing = 1;
    gridLayout.numColumns = 1;

    sectionClient.setLayout(gridLayout);

    createCmpz1(sectionClient);
    createCmpz2(sectionClient);

    toolkit.paintBordersFor(sectionClient);
  }

  @Override
  public void doOnSaveClick() {
    String code = tCode.getText();
    String impl = tImplName.getText();
    if ((code.length() > 0) && (impl.length() > 0)) {
      repository.setCode(code);
      repository
          .setName(tName.getText() != null ? tName.getText() : null);
      repository.setImplementationName(tImplName.getText());
      editor.doSave(null);
    } else if (code.length() == 0)
      tCode.setFocus();
    else if (impl.length() == 0)
      tImplName.setFocus();
  }

  private void createCmpz1(Composite parent) {
    Composite cmpz1 = toolkit.createComposite(parent);
    cmpz1.setLayout(commonGridLayout);
    cmpz1.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz1, BAiPlugin.getDefault()
        .getString(FIELD_CODE));
    label.setForeground(toolkit.getColors().getColor(REQUIRED_FIELD_COLOR));

    // 1 Blank column
    toolkit.createLabel(cmpz1, "");

    label = toolkit.createLabel(cmpz1, BAiPlugin.getDefault().getString(
        FIELD_NAME));
    // Code
    tCode = toolkit.createText(cmpz1, isNew ? "" : repository.getCode()
        .trim(), SWT.BORDER);
    GridData tCodeDataLayout = new GridData();
    tCodeDataLayout.widthHint = CODE_FIELD_WIDTH;
    tCode.setLayoutData(tCodeDataLayout);

    // 1 Blank column
    Label dummy = toolkit.createLabel(cmpz1, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 50;
    dummy.setLayoutData(dummyLabelDataLayout);

    // Name
    tName = toolkit
        .createText(cmpz1, repository.getName() != null ? repository
            .getName().trim() : "", SWT.BORDER);
    tName.setLayoutData(tCodeDataLayout);
  }

  private void createCmpz2(Composite parent) {
    Composite cmpz2 = toolkit.createComposite(parent);
    cmpz2.setLayout(commonGridLayout);
    cmpz2.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz2, BAiPlugin.getDefault()
        .getString(FIELD_IMPL));
    label.setForeground(toolkit.getColors().getColor(REQUIRED_FIELD_COLOR));
    // 2 Blank column
    toolkit.createLabel(cmpz2, "");
    toolkit.createLabel(cmpz2, "");

    // ImplementationName
    GridData tImplNameDataLayout = new GridData();
    tImplNameDataLayout.widthHint = IMPL_FIELD_WIDTH;
    tImplName = toolkit.createText(cmpz2, repository
        .getImplementationName() != null ? repository
        .getImplementationName().trim() : "", SWT.BORDER);
    tImplName.setLayoutData(tImplNameDataLayout);

    Label dummy = toolkit.createLabel(cmpz2, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 10;
    dummy.setLayoutData(dummyLabelDataLayout);

    FormText ftBrowse = toolkit.createFormText(cmpz2, true);
    ftBrowse.setText(String.format(BAiPlugin.getDefault().getString(
        BROWSE_FORM), UiPlugin.getDefault().getString(
        UiPlugin.BUTT_BROWSE_TEXT)), true, false);
    ftBrowse.setImage("browse", UiPlugin.getImageDescriptor(
        UiPlugin.VIEW_ICO).createImage());
    ftBrowse.addHyperlinkListener(new IHyperlinkListener() {

      public void linkActivated(HyperlinkEvent e) {
        String res = selectBAiImpl();
        if (res.length() > 0)
          tImplName.setText(res);
      }

      public void linkEntered(HyperlinkEvent e) {
        // TODO Auto-generated method stub

      }

      public void linkExited(HyperlinkEvent e) {
        // TODO Auto-generated method stub

      }

    });
  }

  private String selectBAiImpl() {
    IJavaSearchScope scope = null;
    IType type = BAiPlugin.getBusinessAddinType();
    try {
      if (type == null)
        scope = SearchEngine.createWorkspaceScope();
      else
        scope = SearchEngine.createHierarchyScope(type);

      Shell sh = CoreUtils.getMainShell();
      SelectionDialog dialog = JavaUI.createTypeDialog(sh,
          new ProgressMonitorDialog(sh), scope,
          IJavaElementSearchConstants.CONSIDER_CLASSES, false);

      dialog.setTitle(BAiPlugin.getDefault().getString(
          BROWSE_DIALOG_TITLE));
      if (dialog.open() == Window.OK)
        return ((IType) dialog.getResult()[0]).getFullyQualifiedName();
      else
        return "";
    } catch (JavaModelException e1) {
      return "";
    }
  }

  @Override
  public String getEditorText() {
    return repository.getCode();
  }

  @Override
  public void formContentPrecreating(Repository object) {
    this.repository = object;
  }

}
