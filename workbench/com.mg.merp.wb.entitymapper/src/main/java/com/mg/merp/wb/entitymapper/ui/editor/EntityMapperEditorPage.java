/* EntityMapperEditorPage.java
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
package com.mg.merp.wb.entitymapper.ui.editor;

import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.core.ui.editor.StandartEditorPage;
import com.mg.merp.wb.entitymapper.Activator;

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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Содержание формы редактирования маппинга преобразования классов
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperEditorPage.java,v 1.1 2007/05/07 13:09:12 poroxnenko Exp $
 */
public class EntityMapperEditorPage extends StandartEditorPage<EntityTransformerMapping> {

  private static final String FIELD_MAPID = "entitymapper.form.edit.mapid";
  private static final String FIELD_APPLAER = "entitymapper.form.edit.applayer";
  private static final String FIELD_CLASSA = "entitymapper.form.edit.classa";
  private static final String FIELD_CLASSB = "entitymapper.form.edit.classb";
  private static final String BROWSE_FORM = "entitymapper.form.edit.browse.form.text";
  private static final String BROWSE_DIALOG_TITLE = "entitymapper.form.edit.browse.dialog.title";
  private static final int CLASS_FIELD_WIDTH = 435;
  private static final int MAPID_FIELD_WIDTH = 220;
  private static final int APPLAYER_FIELD_WIDTH = 30;
  /**
   * Данные маппинга преобразования классов
   */
  private EntityTransformerMapping mapping;
  /**
   * Текстовое поле, содержащее уникальный идентификатор маппинга
   */
  private Text tMapId = null;

  /**
   * Уровень приложения
   */
  private Spinner spAppLayer = null;

  /**
   * Текстовое поле, содержащее имя класса А
   */
  private Text tClassAName = null;

  /**
   * Текстовое поле, содержащее имя класса B
   */
  private Text tClassBName = null;

  private boolean isNew;

  public EntityMapperEditorPage(FormEditor editor) {
    super(editor);
  }

  @Override
  public void createEditorArea(Composite parent) {
    this.mapping = ((StandartEditorInput<EntityTransformerMapping>) getEditorInput()).getData();
    this.isNew = ((StandartEditorInput<EntityTransformerMapping>) getEditorInput()).isCreateNew();
    Section section = toolkit.createSection(parent,
        ExpandableComposite.TITLE_BAR);
    section.setLayout(new GridLayout());
    section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    form.setText(String.format(Activator.getDefault().getString(
        EntityMapperEditorInput.ENTITYMAPPER_FORM_PART), mapping.getMapId()));

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
    createCmpz3(sectionClient);

    toolkit.paintBordersFor(sectionClient);
  }

  @Override
  public void doOnSaveClick() {
    String mapId = tMapId.getText();
    String classA = tClassAName.getText();
    String classB = tClassBName.getText();
    if ((mapId.length() > 0) && (classA.length() > 0) && (classB.length() > 0)) {
      mapping.setMapId(mapId);
      mapping.setClassA(classA);
      mapping.setClassB(classB);
      mapping.setApplicationLayer(ApplicationLayer.values()[spAppLayer.getSelection()]);
      editor.doSave(null);
    } else if (mapId.length() == 0)
      tMapId.setFocus();
    else if (classA.length() == 0)
      tClassAName.setFocus();
    else if (classB.length() == 0)
      tClassAName.setFocus();
  }

  private void createCmpz1(Composite parent) {
    Composite cmpz1 = toolkit.createComposite(parent);
    cmpz1.setLayout(commonGridLayout);
    cmpz1.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz1, Activator.getDefault().getString(FIELD_MAPID));
    label.setForeground(toolkit.getColors().getColor(REQUIRED_FIELD_COLOR));

    // 1 Blank column
    toolkit.createLabel(cmpz1, "");

    toolkit.createLabel(cmpz1, Activator.getDefault().getString(FIELD_APPLAER));

    // MapId
    tMapId = toolkit.createText(cmpz1, isNew ? "" : mapping.getMapId().trim(), SWT.BORDER);
    GridData tMapIdDataLayout = new GridData();
    tMapIdDataLayout.widthHint = MAPID_FIELD_WIDTH;
    tMapId.setLayoutData(tMapIdDataLayout);

    // 1 Blank column
    Label dummy = toolkit.createLabel(cmpz1, "");
    GridData dummyLabelDataLayout = new GridData(
        GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 50;
    dummy.setLayoutData(dummyLabelDataLayout);

    //ApplAyer
    spAppLayer = new Spinner(cmpz1, SWT.BORDER);
    spAppLayer.setSelection(mapping.getApplicationLayer() == null ? 0 : mapping.getApplicationLayer().ordinal());
    spAppLayer.setDigits(0);
    spAppLayer.setMaximum(7);
    spAppLayer.setMinimum(0);
    spAppLayer.setIncrement(1);

    spAppLayer.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
    GridData appLayerDataLayout = new GridData();
    appLayerDataLayout.widthHint = APPLAYER_FIELD_WIDTH;
    spAppLayer.setLayoutData(appLayerDataLayout);
  }

  private void createCmpz2(Composite parent) {
    Composite cmpz2 = toolkit.createComposite(parent);
    cmpz2.setLayout(commonGridLayout);
    cmpz2.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz2, Activator.getDefault().getString(FIELD_CLASSA));
    label.setForeground(toolkit.getColors().getColor(REQUIRED_FIELD_COLOR));
    // 2 Blank column
    toolkit.createLabel(cmpz2, "");
    toolkit.createLabel(cmpz2, "");

    // ClassA
    GridData tClassADataLayout = new GridData();
    tClassADataLayout.widthHint = CLASS_FIELD_WIDTH;
    tClassAName = toolkit.createText(cmpz2, mapping.getClassA() != null ? mapping.getClassA().trim() : "", SWT.BORDER);
    tClassAName.setLayoutData(tClassADataLayout);

    Label dummy = toolkit.createLabel(cmpz2, "");
    GridData dummyLabelDataLayout = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 10;
    dummy.setLayoutData(dummyLabelDataLayout);

    FormText ftBrowse = toolkit.createFormText(cmpz2, true);
    ftBrowse.setText(String.format(Activator.getDefault().getString(BROWSE_FORM), UiPlugin.getDefault().getString(
        UiPlugin.BUTT_BROWSE_TEXT)), true, false);
    ftBrowse.setImage("browse", UiPlugin.getImageDescriptor(UiPlugin.VIEW_ICO).createImage());
    ftBrowse.addHyperlinkListener(new IHyperlinkListener() {

      public void linkActivated(HyperlinkEvent e) {
        String res = selectAvailableClasses();
        if (res.length() > 0)
          tClassAName.setText(res);
      }

      public void linkEntered(HyperlinkEvent e) {
      }

      public void linkExited(HyperlinkEvent e) {
      }
    });
  }

  private void createCmpz3(Composite parent) {
    Composite cmpz3 = toolkit.createComposite(parent);
    cmpz3.setLayout(commonGridLayout);
    cmpz3.setLayoutData(commonGridData);

    Label label = toolkit.createLabel(cmpz3, Activator.getDefault().getString(FIELD_CLASSB));
    label.setForeground(toolkit.getColors().getColor(REQUIRED_FIELD_COLOR));
    //2 Blank column
    toolkit.createLabel(cmpz3, "");
    toolkit.createLabel(cmpz3, "");

    //ClassB
    GridData tClassBDataLayout = new GridData();
    tClassBDataLayout.widthHint = CLASS_FIELD_WIDTH;
    tClassBName = toolkit.createText(cmpz3, mapping.getClassB() != null ? mapping.getClassB().trim() : "", SWT.BORDER);
    tClassBName.setLayoutData(tClassBDataLayout);

    Label dummy = toolkit.createLabel(cmpz3, "");
    GridData dummyLabelDataLayout = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
    dummyLabelDataLayout.horizontalSpan = 1;
    dummyLabelDataLayout.widthHint = 10;
    dummy.setLayoutData(dummyLabelDataLayout);

    FormText ftBrowse = toolkit.createFormText(cmpz3, true);
    ftBrowse.setText(String.format(Activator.getDefault().getString(BROWSE_FORM), UiPlugin.getDefault().getString(
        UiPlugin.BUTT_BROWSE_TEXT)), true, false);
    ftBrowse.setImage("browse", UiPlugin.getImageDescriptor(UiPlugin.VIEW_ICO).createImage());
    ftBrowse.addHyperlinkListener(new IHyperlinkListener() {

      public void linkActivated(HyperlinkEvent e) {
        String res = selectAvailableClasses();
        if (res.length() > 0)
          tClassBName.setText(res);
      }

      public void linkEntered(HyperlinkEvent e) {
      }

      public void linkExited(HyperlinkEvent e) {
      }
    });
  }

  private String selectAvailableClasses() {
    try {
      IJavaSearchScope scope = SearchEngine.createWorkspaceScope();

      Shell sh = CoreUtils.getMainShell();
      SelectionDialog dialog = JavaUI.createTypeDialog(sh,
          new ProgressMonitorDialog(sh), scope,
          IJavaElementSearchConstants.CONSIDER_CLASSES, false);

      dialog.setTitle(Activator.getDefault().getString(
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
    return mapping.getMapId();
  }

  @Override
  public void formContentPrecreating(EntityTransformerMapping object) {
    this.mapping = object;
  }

}
