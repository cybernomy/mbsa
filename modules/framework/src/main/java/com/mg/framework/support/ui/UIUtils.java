/*
 * UIUtils.java
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
package com.mg.framework.support.ui;

import java.awt.ComponentOrientation;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.transaction.Synchronization;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.InternalException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.ReasonException;
import com.mg.framework.api.Session;
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.ConversationBeginner;
import com.mg.framework.api.ui.FileChooseHandler;
import com.mg.framework.api.ui.FileChooserConfig;
import com.mg.framework.api.ui.FileLoadHandler;
import com.mg.framework.api.ui.FileStoreHandler;
import com.mg.framework.api.ui.ShowExceptionDialog;
import com.mg.framework.api.ui.WebResourceProvider;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.Alert.MessageType;
import com.mg.framework.service.HelpSystemLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * User interface utilities
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIUtils.java,v 1.20 2009/01/26 16:36:24 safonov Exp $
 */
public class UIUtils {
	/**
	 * разделитель, отделяющий название ресурса, поддерживается следующая нотация
	 * resource_bundle#resource_name
	 */
	public static final String RESOURCE_NAME_DELIM = "#"; //$NON-NLS-1$

	/**
	 * префикс текстовой ссылки указывающий на принадлежность к ресурсам, поддерживается
	 * следующая нотация resource://resource_bundle
	 */
	public static final String RESOURCE_PREFIX = "resource://"; //$NON-NLS-1$

	/**
	 * имя переменной хранящая состояние пользовательской транзакции
	 */
	public final static String MG_CONVERSATION_FLAG = "MG_CONVERSATION_FLAG"; //$NON-NLS-1$
	
	/**
	 * имя переменной хранящей режим модальности
	 */
	private final static String MG_MODAL_MODE_FLAG = "MG_MODAL_MODE_FLAG"; //$NON-NLS-1$
	
	private static Logger logger = ServerUtils.getLogger(UIUtils.class);
	
	private static boolean isResourceReference(String text) {
		//support next notation resource://resource_bundle
		return text.startsWith(RESOURCE_PREFIX);
	}
	
	private static String loadFromResources(Locale locale, String name) {
		if (name == null)
			return null;
		try {
			//support next notation resource_bundle#resource_name
			int delimIdx = name.indexOf(RESOURCE_NAME_DELIM);
			if (delimIdx == -1 || delimIdx == name.length()) {
				logger.warn("Invalid resource format: " + name); //$NON-NLS-1$
				return name;
			}
			
			return ResourceBundle.getBundle(name.substring(RESOURCE_PREFIX.length(), delimIdx), locale, Thread.currentThread().getContextClassLoader()).getString(name.substring(delimIdx + 1));
		}
		catch (Exception e) {
			logger.warn("Resource load failure", e); //$NON-NLS-1$
			return name;
		}
	}

	/**
	 * загрузка локализованного сообщения
	 * 
	 * @param locale	локаль
	 * @param text		сообщение
	 * @return			локализованное сообщение, <code>text</code> если невозможно локализовать или <code>null</code> если <code>text == null</code>
	 */
	public static String loadL10nText(Locale locale, String text) {
		if (text == null)
			return null;
		
		if (isResourceReference(text))
			return loadFromResources(locale, text);
		else
			return text;
	}

	/**
	 * загрузка локализованного сообщения по текущей локали пользователя
	 * 
	 * @param text		сообщение
	 * @return			локализованное сообщение или <code>text</code> если невозможно локализовать
	 */
	public static String loadL10nText(String text) {
		return loadL10nText(ServerUtils.getUserLocale(), text);
	}

	/**
	 * ориентация текста текущей локали пользователя
	 * 
	 * @return	<code>true</code> если поддерживается ориентация слева/направо
	 */
	public static boolean isLeftToRight() {
		return ComponentOrientation.getOrientation(ServerUtils.getUserLocale()).isLeftToRight();
	}
	
	/**
	 * показать диалог для отображения исключительной ситуации
	 * 
	 * @param th	ИС
	 */
	public static void handleException(Throwable th) {
		if (handleSecurityException(th))
			return;
		handleException(Messages.getInstance().getMessage(Messages.MESSAGE_TITLE), th);
	}

	/**
	 * показать диалог для отображения исключительной ситуации
	 * 
	 * @param title	заголовок диалога
	 * @param th	ИС
	 */
	public static void handleException(String title, Throwable th) {
		logger.debug("Application exception catched", th); //$NON-NLS-1$
		String mes;
		if (th instanceof ApplicationException) {
			mes = ((ApplicationException) th).getLocalizedMessage();
		}
		else {
			mes = Messages.getInstance().getMessage(Messages.INTERNAL_SOFTWARE_EXCEPTION, "Internal software exception"); //$NON-NLS-1$
		}
		StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        //write reason of exception
		if (th instanceof ReasonException) {
			out.println(Messages.getInstance().getMessage(Messages.REASON_EXCEPTION, "Reason of exception:")); //$NON-NLS-1$
			out.println(((ReasonException) th).getReason());
			out.println();
		}
        th.printStackTrace(out);

        ShowExceptionDialog dialog = WidgetFactoryFactory.getInstance().getDefaultWidgetFactory().createExceptionDialog();
        dialog.show(title, mes, sw.toString());
	}
	
	/**
	 * обработка ИС безопасности, при возникновении ИС безопасности не показываем стандартный
	 * диалог чтобы исключить визуализацию стека вызова, просто сообщаем о нарушении прав
	 * 
	 * @param th	ИС безопасности
	 * @return		<code>true</code> если <code>th</code> это ИС безопасности и она обработана, <code>false</code> в противном случае
	 */
	private static boolean handleSecurityException(Throwable th) {
		boolean handled = (th instanceof SecurityException) || (th instanceof com.mg.framework.api.SecurityException);
		if (handled) {
			logger.info("Security exception handled", th);
			Messages msgs = Messages.getInstance();
			//если прикладная ИС то сообщение возмем из нее, в противном случае установим стандартное
			String message = msgs.getMessage(Messages.NO_PERMISSION);
			if (th instanceof com.mg.framework.api.SecurityException)
				message = ((com.mg.framework.api.SecurityException) th).getLocalizedMessage();
			showAlert(Alert.MessageType.WARNING_MESSAGE, msgs.getMessage(Messages.SECURITY_SYSTEM), message, msgs.getMessage(Messages.OK_BUTTON_TEXT));
		}
		return handled;
	}
	
	/**
	 * проверка формы на управление пользовательской транзакцией
	 * 
	 * @param form	контроллер формы
	 * @return	<code>true</code> если форма управляет пользователькой транзакцией
	 */
	public static boolean isFormBeginnerOfConversation(Object form) {
		return form instanceof ConversationBeginner;
	}
	
	/**
	 * проверка старта пользовательской транзакции
	 * 
	 * @return	<code>true</code> если транзакция стартована
	 */
	public static boolean isConversation() {
		Session session = ServerUtils.getCurrentSession();
		return session != null ? ((Integer) ServerUtils.getCurrentSession().getAttribute(MG_CONVERSATION_FLAG)) != null : false;
	}
	
	static class ConversationSynchronization implements Synchronization {
		private Session session;

		private ConversationSynchronization(Session session) {
			super();
			this.session = session;
		}

		public void afterCompletion(int status) {
			if (logger.isDebugEnabled())
				logger.debug("reset conversation flag, transaction status: " + status);
			session.setAttribute(MG_CONVERSATION_FLAG, null);
		}

		public void beforeCompletion() {
		}
		
	}
	
	/**
	 * старт пользовательской транзакции
	 *
	 */
	public static void startConversation() {
		Integer flag = (Integer) ServerUtils.getCurrentSession().getAttribute(MG_CONVERSATION_FLAG);
		if (flag == null) {
			try {
				//регистрируем ресурс для сброса пользовательской транзакции при завершении
				//транзакции сервера, в текущей реализации границы обеих транзакций совпадают
				//если произойдет таймаут системной транзакции, то прервем пользовательскую
				
				//закоментировано по запросу https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4984
				//ServerUtils.getTransactionManager().getTransaction().registerSynchronization(new ConversationSynchronization(ServerUtils.getCurrentSession()));
				if (logger.isDebugEnabled())
					logger.debug("Start conversation"); //$NON-NLS-1$
				ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, new Integer(1));
			} catch (ApplicationException e) {
				throw e;
			} catch (Exception e) {
				logger.debug("register transaction resource failed", e);
				throw new InternalException(e);
			}
		}
		else {
			logger.debug("Attach to conversation with deep: ".concat(Integer.toString(flag.intValue() + 1))); //$NON-NLS-1$
			ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, new Integer(flag.intValue() + 1));
		}
	}
	
	/*private static Boolean isLast() {
		Integer flag = (Integer) ServerUtils.getCurrentSession().getAttribute(MG_CONVERSATION_FLAG);
		if (flag == null)
			return null;
		return flag.intValue() == 1;
	}*/
	
	/**
	 * завершение пользовательской транзакции
	 *
	 */
	public static void endConversation() {
		Integer flag = (Integer) ServerUtils.getCurrentSession().getAttribute(MG_CONVERSATION_FLAG);
		if (flag != null) {
			int val = flag.intValue();
			if (val == 1) {
				logger.debug("End conversation"); //$NON-NLS-1$
				ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, null);
			}
			else {
				logger.debug("Deattach from conversation with deep: ".concat(Integer.toString(val))); //$NON-NLS-1$
				ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, new Integer(--val));
			}
		}
	}
	
	/**
	 * прерывание пользовательской транзакции
	 *
	 */
	public static void abortConversation() {
		logger.debug("Abort conversation"); //$NON-NLS-1$
		ServerUtils.setTransactionRollbackOnly();
		endConversation();
		//закоментировано по запросу https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4984
		//ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, null);
	}
	
	/**
	 * включение модального режима, используется для внутренних целей, не вызывать в прикладном коде
	 *
	 */
	public static void modalModeOn() {
		Session session = ServerUtils.getCurrentSession();
		Integer value = (Integer) session.getAttribute(MG_MODAL_MODE_FLAG);
		if (value == null)
			value = 0;
		session.setAttribute(MG_MODAL_MODE_FLAG, value + 1);
	}

	/**
	 * выключение модального режима, используется для внутренних целей, не вызывать в прикладном коде
	 *
	 */
	public static void modalModeOff() {
		Session session = ServerUtils.getCurrentSession();
		Integer value = (Integer) session.getAttribute(MG_MODAL_MODE_FLAG);
		if ((value != null) && (value != 0)) //если не находимся в модальном режиме то не уходим в минус, вообще не должно быть такой ситуации
			session.setAttribute(MG_MODAL_MODE_FLAG, value - 1);			
	}

	/**
	 * проверка установки модального режима, используется для определения наличия видимых модальных окон,
	 * если существует видимое модальное окно и сделать видимым немодальное окно, то оно не будет доступным
	 * пользователю, т.е. при существовании хотя бы одного модального окна, все последующие окна должны
	 * быть модальными
	 * 
	 * @return	<code>true</code> если существует видимым хотя бы одно модальное окно
	 */
	public static boolean isModalMode() {
		Session session = ServerUtils.getCurrentSession();
		Integer value = (Integer) session.getAttribute(MG_MODAL_MODE_FLAG);
		return (value != null) && (value != 0);
	}

	/**
	 * получить список локализованных наименований констант перечислимого типа по текущей локали пользователя
	 * 
	 * @param <T>	тип
	 * @param value	класс перечислимого типа
	 * @return	список наименований
	 */
	public static <T extends Enum<?>> String[] enumToEnumConstantsText(Class<T> value) {
		return enumToEnumConstantsText(ServerUtils.getUserLocale(), value);
	}

	/**
	 * получить список локализованных наименований констант перечислимого типа
	 * 
	 * @param <T>	тип
	 * @param locale	локаль
	 * @param value	класс перечислимого типа
	 * @return	список наименований
	 */
	public static <T extends Enum<?>> String[] enumToEnumConstantsText(Locale locale, Class<T> value) {
		Enum<?>[] enumConstants = value.getEnumConstants();//т.к. тип enum, то не должно быть null
		String[] enumConstantsText = new String[enumConstants.length];
		for(int i = 0; i < enumConstants.length; i++) {
			Field fld = ReflectionUtils.findDeclaredField(value, enumConstants[i].name());
			EnumConstantText textAnnot = null;
			if (fld != null)
				textAnnot = fld.getAnnotation(EnumConstantText.class);

			if (textAnnot != null)
				enumConstantsText[i] = UIUtils.loadL10nText(locale, textAnnot.value());
			else
				enumConstantsText[i] = enumConstants[i].name();
		}
		return enumConstantsText;
	}
	
	/**
	 * Показать окно диалога предупреждения с одной кнопкой
	 * 
	 * @param messageType		тип предупреждения
	 * @param title				заголовок
	 * @param message			сообщение
	 * @param firstButtonLabel	метка кнопки
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel) {
		showAlert(messageType, title, message, firstButtonLabel, null, null, null);
	}

	/**
	 * Показать окно диалога предупреждения с одной кнопкой
	 * 
	 * @param messageType		тип предупреждения
	 * @param title				заголовок
	 * @param message			сообщение
	 * @param firstButtonLabel	метка кнопки
	 * @param listener			слушатель диалога
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 * @see AlertListener
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel, AlertListener listener) {
		showAlert(messageType, title, message, firstButtonLabel, null, null, listener);
	}

	/**
	 * Показать окно диалога предупреждения с двумя кнопками
	 * 
	 * @param messageType		тип предупреждения
	 * @param title				заголовок
	 * @param message			сообщение
	 * @param firstButtonLabel	метка первой кнопки
	 * @param secondButtonLabel	метка второй кнопки
	 * @param listener			слушатель диалога
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 * @see AlertListener
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel, String secondButtonLabel, AlertListener listener) {
		showAlert(messageType, title, message, firstButtonLabel, secondButtonLabel, null, listener);
	}

	/**
	 * Показать окно диалога предупреждения с тремя кнопками
	 * 
	 * @param messageType		тип предупреждения
	 * @param title				заголовок
	 * @param message			сообщение
	 * @param firstButtonLabel	метка первой кнопки
	 * @param secondButtonLabel	метка второй кнопки
	 * @param thirdButtonLabel	метка третьей кнопки
	 * @param listener			слушатель диалога
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 * @see AlertListener
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel, String secondButtonLabel, String thirdButtonLabel, AlertListener listener) {
		Alert alert = WidgetFactoryFactory.getInstance().getDefaultWidgetFactory().createAlert();
		alert.addAlertListener(listener);
		alert.show(messageType, title, message, firstButtonLabel, secondButtonLabel, thirdButtonLabel);
	}

	/**
	 * Показать контекстную помощь
	 * 
	 * @param helpTopic	тематический раздел помощи
	 * 
	 * @see com.mg.framework.api.help.HelpSystem
	 */
	public static void showContextHelp(String helpTopic) {
		HelpSystemLocator.locate().showContextHelp(helpTopic);
	}
	
	/**
	 * показать документ управляемый текущим сервером приложения, например если необходимо показать
	 * документ <code>http://localhost:8080/jmx-console</code>, то необходимо передать путь <code>jmx-console</code>
	 * 
	 * @param urlString	путь к документу не включающий адрес текущего сервера
	 * 
	 * @see com.mg.framework.api.ui.ClientContext
	 */
	public static void showLocalDocument(String urlString) {
		ClientContextFactory.getInstance().getDefaultClientContext().showLocalDocument(urlString);
	}

	/**
	 * показать документ
	 * 
	 * @param urlString	полный путь к документу, например <code>"http://www.m-g.ru"</code>
	 * 
	 * @see com.mg.framework.api.ui.ClientContext
	 */
	public static void showDocument(String urlString) {
		ClientContextFactory.getInstance().getDefaultClientContext().showDocument(urlString);
	}

	/**
	 * показать документ
	 * 
	 * @param resourceProvider	провайдер документа
	 */
	public static void showDocument(WebResourceProvider resourceProvider) {
		String id = DownloadManager.getInstance().put(resourceProvider);
		DownloadManager.getInstance().showDocument(id);		
	}
	
	/**
	 * установка свойства "только для чтения" элемента пользовательского интерфейса
	 * 
	 * @param widget	элемент пользовательского интерфейса, если <code>null</code>, то не будет никаких действий
	 * @param readOnly	значение свойства
	 */
	public static void setReadOnlyProperty(Widget widget, boolean readOnly) {
		if (widget != null)
			widget.setReadOnly(readOnly);
	}

	/**
	 * установка свойства "доступно" элемента пользовательского интерфейса
	 * 
	 * @param widget	элемент пользовательского интерфейса, если <code>null</code>, то не будет никаких действий
	 * @param enabled	значение свойства
	 */
	public static void setEnabledProperty(Widget widget, boolean enabled) {
		if (widget != null)
			widget.setEnabled(enabled);
	}

	/**
	 * установка свойства "видимый" элемента пользовательского интерфейса
	 * 
	 * @param widget	элемент пользовательского интерфейса, если <code>null</code>, то не будет никаких действий
	 * @param visible	значение свойства
	 */
	public static void setVisibleProperty(Widget widget, boolean visible) {
		if (widget != null)
			widget.setVisible(visible);
	}

	/**
	 * установка свойств "видимый и доступный" элемента пользовательского интерфейса
	 * 
	 * @param widget	элемент пользовательского интерфейса, если <code>null</code>, то не будет никаких действий
	 * @param visibleEnabled	значение свойства
	 */
	public static void setVisibleEnabledProperty(Widget widget, boolean visibleEnabled) {
		if (widget != null) {
			widget.setVisible(visibleEnabled);
			widget.setEnabled(visibleEnabled);
		}
	}

	/**
	 * загрузка файла на сервер приложения со стороны клиента
	 * 
	 * @param handler	обработчик загрузки
	 */
	public static void loadFile(FileLoadHandler handler) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler);
	}

	/**
	 * загрузка файла на сервер приложения со стороны клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик загрузки
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	public static void loadFile(FileLoadHandler handler, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, maximumFileSize);
	}

	/**
	 * загрузка файла на сервер приложения со стороны клиента
	 * 
	 * @param handler	обработчик загрузки
	 * @param fileChooserConfig	кофигурация диалога загрузки
	 */
	public static void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, fileChooserConfig);
	}

	/**
	 * загрузка файла на сервер приложения со стороны клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик загрузки
	 * @param fileChooserConfig	кофигурация диалога загрузки
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	public static void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, fileChooserConfig, maximumFileSize);
	}

	/**
	 * сохранение файла на стороне клиента
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileName	имя файла, может быть <code>null</code>
	 */
	public static void storeFile(FileStoreHandler handler, String fileName) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileName);
	}

	/**
	 * сохранение файла на стороне клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileName	имя файла, может быть <code>null</code>
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	public static void storeFile(FileStoreHandler handler, String fileName, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileName, maximumFileSize);
	}

	/**
	 * сохранение файла на стороне клиента
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileChooserConfig	кофигурация диалога сохранения
	 */
	public static void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileChooserConfig);
	}

	/**
	 * сохранение файла на стороне клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileChooserConfig	кофигурация диалога сохранения
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	public static void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileChooserConfig, maximumFileSize);
	}

	/**
	 * Returns the raw IP address string "%d&#46%d&#46%d&#46%d".
	 * 
	 * @return	the raw IP address string "%d.%d.%d.%d"
	 */
	public static String getAddress() {
		return ClientContextFactory.getInstance().getDefaultClientContext().getAddress();
	}

	/**
	 * Returns the host name.
	 * 
	 * @return	the host name
	 */
	public static String getHost() {
		return ClientContextFactory.getInstance().getDefaultClientContext().getHost();
	}

	/**
	 * Emits an audio beep.
	 */
	public static void beep() {
		ClientContextFactory.getInstance().getDefaultClientContext().beep();
	}

	/**
	 * Выбор пользователем одного или нескольких файлов
	 * 
	 * @param fileChooseHandler	обработчик выбора
	 * @param fileChooserConfig	кофигурация диалога выбора
	 */
	public static void chooseFile(FileChooseHandler fileChooseHandler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().chooseFile(fileChooseHandler, fileChooserConfig);
	}

}
