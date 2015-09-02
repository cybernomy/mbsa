/* ReportParamsForm.java
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
package com.mg.merp.report.support.ui;

import static com.mg.merp.report.parameters.ReportParameter.ParameterType.SCALAR_PARAMETER;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.api.ui.widget.Button;
import com.mg.framework.api.ui.widget.ComboBox;
import com.mg.framework.api.ui.widget.RadioButtonGroup;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.AbstractRule;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.report.generic.ui.AbstractReportParametersDialog;
import com.mg.merp.report.model.RptMain;
import com.mg.merp.report.parameters.ReportParameter;
import com.mg.merp.report.parameters.ReportParametersDialog;
import com.mg.merp.report.parameters.SelectionChoice;
import com.mg.merp.report.parameters.support.SelectionChoiceImpl;
import com.mg.merp.report.support.Messages;

/**
 * Реализация интерфейса {@link ReportParametersDialog} Контроллер для
 * автоматической формы запроса параметров
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: ReportParametersDialogImpl.java,v 1.9 2009/03/18 09:21:15 safonov Exp $
 */
public class ReportParametersDialogImpl extends AbstractReportParametersDialog {
	private boolean isListParamSetup = false;

	/**
	 * установка значений параметров со списком
	 */
	private void setupListParam() {
		if (params == null)
			return;

		boolean hasListValues = false;
		for (Map.Entry<String, ReportParameter> paramEntry : params.entrySet()) {
			//грузим значения для параметров со списком, для каскадных параметров грузим только
			//верхний по иерархии, остальные будут загружены по событию изменения значения текущего параметра
			if (paramEntry.getValue().hasListValues() && paramEntry.getValue().indexInGroup() <= 0) {
				Object[] items = SelectionChoiceImpl.convertParameterSelectionChoiceToValues(paramEntry.getValue().getSelectionList());
				if (items.length > 0) {
					Widget widget = view.getWidget(paramEntry.getKey());
					if (widget instanceof ComboBox) {
						((ComboBox) widget).setItems(items);
						hasListValues = true;
					}
					else if (widget instanceof RadioButtonGroup) {
						((RadioButtonGroup) widget).setItems(items);
						hasListValues = true;
					}
				}
			}
		}
		isListParamSetup = true;
		//после загрузки списков установим на значения параметров
		if (hasListValues)
			view.flushModel();

		//workaround https://issues.m-g.ru/bugzilla/show_bug.cgi?id=5040
		//т.к. значения динамических параметров заполняются после "упаковки" и показа формы,
		//то размер формы не учитывает эти значения, еще раз "пакуем" формы для учитывания
		//значений параметров, текущее API не позволяет "паковать" форму после показа поэтому
		//используем reflection
		try {
			Object dialog = ((Widget) view.getWindow()).getDelegate();
			Method m = dialog.getClass().getMethod("pack");
			m.invoke(dialog);
		} catch (Exception e) {
			getLogger().error("pack dialog failed", e);
		}
	}

	@Override
	protected void doOnRun() {
		if (StringUtils.stringNullOrEmpty(getTitle()))
			setTitle(Messages.getInstance().getMessage(Messages.REPORT_FORM_TITLE));
		view.pack();
		super.doOnRun();
		setupListParam();
	}

	@Override
	protected void doSetFieldValue(String name, Object value) throws NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		if (this.params == null)
			throw new IllegalStateException("params is null");

		ReportParameter param = this.params.get(name);
		if (param.hasListValues()) {
			int index = value == null ? -1 : Integer.parseInt(value.toString());
			if (index == -1)
				param.setValue(null);
			else {
				List<SelectionChoice> selectionList = param.getSelectionList();
				if (index < selectionList.size())
					param.setValue(selectionList.get(index).getValue());
				else
					param.setValue(null);
			}
		}
		else
			param.setValue(value);
	}

	@Override
	protected Object doGetFieldValue(String name) throws NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		if (this.params == null)
			throw new IllegalStateException("params is null");

		ReportParameter param = this.params.get(name);
		if (param.hasListValues())
			//не устанваливаем значение если списки еще не были загружены
			return isListParamSetup ? SelectionChoiceImpl.findSelectionIndexByValue(param.getSelectionList(), param.getValue()) : -1;
		else
			return param.getValue();
	}

	public static String createForm(RptMain report, Map<String, ReportParameter> reportParams) {
		StringBuilder prms = new StringBuilder();
		for (ReportParameter param : reportParams.values()) {
			//не отображаем скрытые параметры
			if (param.isHidden())
				continue;

			if (param.hasListValues())
				createListParam(prms, param);
			 else
				crealeFlatParam(prms, param);
		}
		StringBuilder form = new StringBuilder("<jfd:form xmlns:jfd=\"http://xmlns.m-g.ru/jet/ui\" ")
				.append("controller=\"com.mg.merp.report.support.ui.ReportParametersDialogImpl\" ")
				.append("title=\"resource://com.mg.merp.report.resources.formelements#ReportParametersDialog.Title\" ")
				.append("orientation=\"ver\" > ")
				.append("<jfd:container id=\"reportParameters\" layout=\"jfd:box\" columns=\"2\" alignment=\"expand_expand\">")
				.append("<jfd:border style=\"bevel\" type=\"raised\" />");
		//создание метки с именем отчета
		String reportName = !StringUtils.stringNullOrEmpty(report.getComment()) ? report.getComment() : report.getRptName();
		if (!StringUtils.stringNullOrEmpty(reportName))
			form.append("<jfd:label id=\"titleLabel\" alignment=\"center_top\" horizontalSpan=\"2\" text=\"").append(reportName).append("\" />");
		form.append(prms).append("</jfd:container>")
				.append("<jfd:include name=\"com/mg/framework/resources/DefaultDialogFooterMacros.xml\" /></jfd:form>");
		return form.toString();
	}

	private static StringBuilder createListParam(StringBuilder text, ReportParameter param){
		String name = StringUtils.EMPTY_STRING;

		switch (param.getControlType()) {
		case COMBO_BOX:
			name = WidgetFactory.COMBOBOX_WIDGET;
			break;
		case RADIO_BUTTON:
			name = WidgetFactory.RADIOBUTTON_GROUP_WIDGET;
			break;
		case LIST_BOX:
			name = WidgetFactory.LIST_WIDGET;
			break;
		default:
			return new StringBuilder(); //если неизвестный контрол, то непонятно что делать
		}
		String label = param.getDisplayName() != null ? param.getDisplayName() : param.getName();

		//List<SelectionChoice> selectionList = param.getSelectionList();
		text.append("<").append(name)
				.append(" id=\"").append(param.getName())
				/*.append("\" selectedIndex=\"")
				.append(SelectionChoiceImpl.findSelectionIndexByValue(selectionList, param.getValue()))*/
				.append("\" label=\"").append(label).append("\"");

		if (param.cascade())
			text.append(" ").append(Button.ACTION_LISTENER).append("=\"ChangeCascadeParam\"");

		/*text.append("><jfd:items>");
		for (SelectionChoice choice : selectionList)
			text.append("<jfd:item value=\"").append(choice.getLabel()).append("\" />");

		return text.append("</jfd:items></").append(name).append(">");*/
		return text.append(" alignment=\"expand_top\" />");
	}

	protected void onActionChangeCascadeParam(WidgetEvent event) {
		//загрузим новые значения для каскадных параметров той же группы что и изменившийся
		//но которые находятся ниже по иерархии
		ReportParameter currentParam = params.get(event.getWidget().getName());
		if (currentParam != null && currentParam.cascade()) {
			//System.out.println("current param: " + currentParam.getName());
			int selfIndex = currentParam.indexInGroup();
			for (ReportParameter groupParam : params.values()) {
				//загрузим только один следующий за текущим, остальные в каскаде обновятся рекурсивно
				if (currentParam.groupName().equals(groupParam.groupName()) && selfIndex == groupParam.indexInGroup() - 1) {
					Widget widget = view.getWidget(groupParam.getName());
					if (widget instanceof ComboBox) {
						//System.out.println("group param: " + groupParam.getName() + ", index: " + groupParam.indexInGroup());
						groupParam.setValue(null);
						((ComboBox) widget).setItems(SelectionChoiceImpl.convertParameterSelectionChoiceToValues(groupParam.getSelectionList()));
						//System.out.println("after set combobox items");
					}
					break;
				}
			}
		}
	}

	private static StringBuilder crealeFlatParam(StringBuilder text, ReportParameter param){
		String label = param.getDisplayName() != null ? param.getDisplayName() : param.getName();
		text.append("<jfd:label id=\"").append(param.getName())
				.append("Label\" text=\"").append(label).append("\" alignment=\"left_top\"/>")
				.append("<");
		if (param.getParameterType() == SCALAR_PARAMETER) {
			switch (param.getDataType()) {
			case STRING:
				text.append(param.isValueConcealed() ? WidgetFactory.SECRET_EDIT_WIDGET : WidgetFactory.TEXT_EDIT_WIDGET).append(" length=\"25\" ");
				break;
			case FLOAT:
				text.append(WidgetFactory.NUMBER_EDIT_WIDGET);
				break;
			case NUMBER:
				text.append(WidgetFactory.NUMBER_EDIT_WIDGET);
				break;
			case DATE_TIME:
				text.append(WidgetFactory.DATE_TIME_EDIT_WIDGET);
				break;
			case BOOLEAN:
				text.append(WidgetFactory.CHECKBOX_WIDGET);
				break;
			case INTEGER:
				text.append(WidgetFactory.INTEGER_EDIT_WIDGET);
				break;
			case DATE:
				text.append(WidgetFactory.DATE_EDIT_WIDGET);
				break;
			case TIME:
				text.append(WidgetFactory.TIME_EDIT_WIDGET);
				break;
			case ENTITY:
				text.append(WidgetFactory.ENTITY_EDIT_WIDGET);
				String searchHelpName = param.getSearchHelpName();
				if (searchHelpName != null)
					text.append(" searchHelp=\"").append(param.getSearchHelpName()).append("\" ");

				String entityPropertyText = param.getEntityPropertyText();
				if(!StringUtils.stringNullOrEmpty(entityPropertyText))
					text.append(" entityPropertyText=\"").append(entityPropertyText).append("\" ");

				String entityPropertyTextFormat = param.getEntityPropertyTextFormat();
				if(!StringUtils.stringNullOrEmpty(entityPropertyTextFormat))
					text.append(" entityFormat=\"").append(entityPropertyTextFormat).append("\" ");

				break;
			default:
				text.append(WidgetFactory.TEXT_EDIT_WIDGET);
				break;
			}
		}

		text.append(" id=\"").append(param.getName()).append("\" alignment=\"expand_top\" />");
		return text;
	}

	private class MandatoryParameterRule extends AbstractRule {
		private ReportParameter param;

		private MandatoryParameterRule(ReportParameter param) {
			super(com.mg.framework.support.Messages.getInstance().getMessage(com.mg.framework.support.Messages.MANDATORY_VALIDATOR), param.getValue());
			this.param = param;
		}

		@Override
		protected void doValidate(ValidationContext context) {
			Object toValidate = toValidate();
			//проверка на null
			if (toValidate == null)
				context.getStatus().error(this);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.validator.AbstractRule#getMessage()
		 */
		@Override
		public String getMessage() {
			String msg = super.getMessage();
			return String.format(ServerUtils.getUserLocale(), "%s '%s'", msg, param.getDisplayName() != null ? param.getDisplayName() : param.getName());
		}

	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		//проверка обязательных параметров
		ValidationContext validationContext = new ValidationContext();
		for (ReportParameter param : params.values()) {
			if (param.isRequired()) {
				validationContext.addRule(new MandatoryParameterRule(param));
			}
		}
		validationContext.validate();
		super.onActionOk(event);
	}

}
