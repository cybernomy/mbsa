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
	 * �����������, ���������� �������� �������, �������������� ��������� �������
	 * resource_bundle#resource_name
	 */
	public static final String RESOURCE_NAME_DELIM = "#"; //$NON-NLS-1$

	/**
	 * ������� ��������� ������ ����������� �� �������������� � ��������, ��������������
	 * ��������� ������� resource://resource_bundle
	 */
	public static final String RESOURCE_PREFIX = "resource://"; //$NON-NLS-1$

	/**
	 * ��� ���������� �������� ��������� ���������������� ����������
	 */
	public final static String MG_CONVERSATION_FLAG = "MG_CONVERSATION_FLAG"; //$NON-NLS-1$
	
	/**
	 * ��� ���������� �������� ����� �����������
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
	 * �������� ��������������� ���������
	 * 
	 * @param locale	������
	 * @param text		���������
	 * @return			�������������� ���������, <code>text</code> ���� ���������� ������������ ��� <code>null</code> ���� <code>text == null</code>
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
	 * �������� ��������������� ��������� �� ������� ������ ������������
	 * 
	 * @param text		���������
	 * @return			�������������� ��������� ��� <code>text</code> ���� ���������� ������������
	 */
	public static String loadL10nText(String text) {
		return loadL10nText(ServerUtils.getUserLocale(), text);
	}

	/**
	 * ���������� ������ ������� ������ ������������
	 * 
	 * @return	<code>true</code> ���� �������������� ���������� �����/�������
	 */
	public static boolean isLeftToRight() {
		return ComponentOrientation.getOrientation(ServerUtils.getUserLocale()).isLeftToRight();
	}
	
	/**
	 * �������� ������ ��� ����������� �������������� ��������
	 * 
	 * @param th	��
	 */
	public static void handleException(Throwable th) {
		if (handleSecurityException(th))
			return;
		handleException(Messages.getInstance().getMessage(Messages.MESSAGE_TITLE), th);
	}

	/**
	 * �������� ������ ��� ����������� �������������� ��������
	 * 
	 * @param title	��������� �������
	 * @param th	��
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
	 * ��������� �� ������������, ��� ������������� �� ������������ �� ���������� �����������
	 * ������ ����� ��������� ������������ ����� ������, ������ �������� � ��������� ����
	 * 
	 * @param th	�� ������������
	 * @return		<code>true</code> ���� <code>th</code> ��� �� ������������ � ��� ����������, <code>false</code> � ��������� ������
	 */
	private static boolean handleSecurityException(Throwable th) {
		boolean handled = (th instanceof SecurityException) || (th instanceof com.mg.framework.api.SecurityException);
		if (handled) {
			logger.info("Security exception handled", th);
			Messages msgs = Messages.getInstance();
			//���� ���������� �� �� ��������� ������ �� ���, � ��������� ������ ��������� �����������
			String message = msgs.getMessage(Messages.NO_PERMISSION);
			if (th instanceof com.mg.framework.api.SecurityException)
				message = ((com.mg.framework.api.SecurityException) th).getLocalizedMessage();
			showAlert(Alert.MessageType.WARNING_MESSAGE, msgs.getMessage(Messages.SECURITY_SYSTEM), message, msgs.getMessage(Messages.OK_BUTTON_TEXT));
		}
		return handled;
	}
	
	/**
	 * �������� ����� �� ���������� ���������������� �����������
	 * 
	 * @param form	���������� �����
	 * @return	<code>true</code> ���� ����� ��������� ��������������� �����������
	 */
	public static boolean isFormBeginnerOfConversation(Object form) {
		return form instanceof ConversationBeginner;
	}
	
	/**
	 * �������� ������ ���������������� ����������
	 * 
	 * @return	<code>true</code> ���� ���������� ����������
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
	 * ����� ���������������� ����������
	 *
	 */
	public static void startConversation() {
		Integer flag = (Integer) ServerUtils.getCurrentSession().getAttribute(MG_CONVERSATION_FLAG);
		if (flag == null) {
			try {
				//������������ ������ ��� ������ ���������������� ���������� ��� ����������
				//���������� �������, � ������� ���������� ������� ����� ���������� ���������
				//���� ���������� ������� ��������� ����������, �� ������� ����������������
				
				//��������������� �� ������� https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4984
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
	 * ���������� ���������������� ����������
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
	 * ���������� ���������������� ����������
	 *
	 */
	public static void abortConversation() {
		logger.debug("Abort conversation"); //$NON-NLS-1$
		ServerUtils.setTransactionRollbackOnly();
		endConversation();
		//��������������� �� ������� https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4984
		//ServerUtils.getCurrentSession().setAttribute(MG_CONVERSATION_FLAG, null);
	}
	
	/**
	 * ��������� ���������� ������, ������������ ��� ���������� �����, �� �������� � ���������� ����
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
	 * ���������� ���������� ������, ������������ ��� ���������� �����, �� �������� � ���������� ����
	 *
	 */
	public static void modalModeOff() {
		Session session = ServerUtils.getCurrentSession();
		Integer value = (Integer) session.getAttribute(MG_MODAL_MODE_FLAG);
		if ((value != null) && (value != 0)) //���� �� ��������� � ��������� ������ �� �� ������ � �����, ������ �� ������ ���� ����� ��������
			session.setAttribute(MG_MODAL_MODE_FLAG, value - 1);			
	}

	/**
	 * �������� ��������� ���������� ������, ������������ ��� ����������� ������� ������� ��������� ����,
	 * ���� ���������� ������� ��������� ���� � ������� ������� ����������� ����, �� ��� �� ����� ���������
	 * ������������, �.�. ��� ������������� ���� �� ������ ���������� ����, ��� ����������� ���� ������
	 * ���� ����������
	 * 
	 * @return	<code>true</code> ���� ���������� ������� ���� �� ���� ��������� ����
	 */
	public static boolean isModalMode() {
		Session session = ServerUtils.getCurrentSession();
		Integer value = (Integer) session.getAttribute(MG_MODAL_MODE_FLAG);
		return (value != null) && (value != 0);
	}

	/**
	 * �������� ������ �������������� ������������ �������� ������������� ���� �� ������� ������ ������������
	 * 
	 * @param <T>	���
	 * @param value	����� ������������� ����
	 * @return	������ ������������
	 */
	public static <T extends Enum<?>> String[] enumToEnumConstantsText(Class<T> value) {
		return enumToEnumConstantsText(ServerUtils.getUserLocale(), value);
	}

	/**
	 * �������� ������ �������������� ������������ �������� ������������� ����
	 * 
	 * @param <T>	���
	 * @param locale	������
	 * @param value	����� ������������� ����
	 * @return	������ ������������
	 */
	public static <T extends Enum<?>> String[] enumToEnumConstantsText(Locale locale, Class<T> value) {
		Enum<?>[] enumConstants = value.getEnumConstants();//�.�. ��� enum, �� �� ������ ���� null
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
	 * �������� ���� ������� �������������� � ����� �������
	 * 
	 * @param messageType		��� ��������������
	 * @param title				���������
	 * @param message			���������
	 * @param firstButtonLabel	����� ������
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel) {
		showAlert(messageType, title, message, firstButtonLabel, null, null, null);
	}

	/**
	 * �������� ���� ������� �������������� � ����� �������
	 * 
	 * @param messageType		��� ��������������
	 * @param title				���������
	 * @param message			���������
	 * @param firstButtonLabel	����� ������
	 * @param listener			��������� �������
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 * @see AlertListener
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel, AlertListener listener) {
		showAlert(messageType, title, message, firstButtonLabel, null, null, listener);
	}

	/**
	 * �������� ���� ������� �������������� � ����� ��������
	 * 
	 * @param messageType		��� ��������������
	 * @param title				���������
	 * @param message			���������
	 * @param firstButtonLabel	����� ������ ������
	 * @param secondButtonLabel	����� ������ ������
	 * @param listener			��������� �������
	 * 
	 * @see com.mg.framework.api.ui.Alert.MessageType
	 * @see AlertListener
	 */
	public static void showAlert(MessageType messageType, String title, String message, String firstButtonLabel, String secondButtonLabel, AlertListener listener) {
		showAlert(messageType, title, message, firstButtonLabel, secondButtonLabel, null, listener);
	}

	/**
	 * �������� ���� ������� �������������� � ����� ��������
	 * 
	 * @param messageType		��� ��������������
	 * @param title				���������
	 * @param message			���������
	 * @param firstButtonLabel	����� ������ ������
	 * @param secondButtonLabel	����� ������ ������
	 * @param thirdButtonLabel	����� ������� ������
	 * @param listener			��������� �������
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
	 * �������� ����������� ������
	 * 
	 * @param helpTopic	������������ ������ ������
	 * 
	 * @see com.mg.framework.api.help.HelpSystem
	 */
	public static void showContextHelp(String helpTopic) {
		HelpSystemLocator.locate().showContextHelp(helpTopic);
	}
	
	/**
	 * �������� �������� ����������� ������� �������� ����������, �������� ���� ���������� ��������
	 * �������� <code>http://localhost:8080/jmx-console</code>, �� ���������� �������� ���� <code>jmx-console</code>
	 * 
	 * @param urlString	���� � ��������� �� ���������� ����� �������� �������
	 * 
	 * @see com.mg.framework.api.ui.ClientContext
	 */
	public static void showLocalDocument(String urlString) {
		ClientContextFactory.getInstance().getDefaultClientContext().showLocalDocument(urlString);
	}

	/**
	 * �������� ��������
	 * 
	 * @param urlString	������ ���� � ���������, �������� <code>"http://www.m-g.ru"</code>
	 * 
	 * @see com.mg.framework.api.ui.ClientContext
	 */
	public static void showDocument(String urlString) {
		ClientContextFactory.getInstance().getDefaultClientContext().showDocument(urlString);
	}

	/**
	 * �������� ��������
	 * 
	 * @param resourceProvider	��������� ���������
	 */
	public static void showDocument(WebResourceProvider resourceProvider) {
		String id = DownloadManager.getInstance().put(resourceProvider);
		DownloadManager.getInstance().showDocument(id);		
	}
	
	/**
	 * ��������� �������� "������ ��� ������" �������� ����������������� ����������
	 * 
	 * @param widget	������� ����������������� ����������, ���� <code>null</code>, �� �� ����� ������� ��������
	 * @param readOnly	�������� ��������
	 */
	public static void setReadOnlyProperty(Widget widget, boolean readOnly) {
		if (widget != null)
			widget.setReadOnly(readOnly);
	}

	/**
	 * ��������� �������� "��������" �������� ����������������� ����������
	 * 
	 * @param widget	������� ����������������� ����������, ���� <code>null</code>, �� �� ����� ������� ��������
	 * @param enabled	�������� ��������
	 */
	public static void setEnabledProperty(Widget widget, boolean enabled) {
		if (widget != null)
			widget.setEnabled(enabled);
	}

	/**
	 * ��������� �������� "�������" �������� ����������������� ����������
	 * 
	 * @param widget	������� ����������������� ����������, ���� <code>null</code>, �� �� ����� ������� ��������
	 * @param visible	�������� ��������
	 */
	public static void setVisibleProperty(Widget widget, boolean visible) {
		if (widget != null)
			widget.setVisible(visible);
	}

	/**
	 * ��������� ������� "������� � ���������" �������� ����������������� ����������
	 * 
	 * @param widget	������� ����������������� ����������, ���� <code>null</code>, �� �� ����� ������� ��������
	 * @param visibleEnabled	�������� ��������
	 */
	public static void setVisibleEnabledProperty(Widget widget, boolean visibleEnabled) {
		if (widget != null) {
			widget.setVisible(visibleEnabled);
			widget.setEnabled(visibleEnabled);
		}
	}

	/**
	 * �������� ����� �� ������ ���������� �� ������� �������
	 * 
	 * @param handler	���������� ��������
	 */
	public static void loadFile(FileLoadHandler handler) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler);
	}

	/**
	 * �������� ����� �� ������ ���������� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ��������
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	public static void loadFile(FileLoadHandler handler, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, maximumFileSize);
	}

	/**
	 * �������� ����� �� ������ ���������� �� ������� �������
	 * 
	 * @param handler	���������� ��������
	 * @param fileChooserConfig	����������� ������� ��������
	 */
	public static void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, fileChooserConfig);
	}

	/**
	 * �������� ����� �� ������ ���������� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ��������
	 * @param fileChooserConfig	����������� ������� ��������
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	public static void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().loadFile(handler, fileChooserConfig, maximumFileSize);
	}

	/**
	 * ���������� ����� �� ������� �������
	 * 
	 * @param handler	���������� ����������
	 * @param fileName	��� �����, ����� ���� <code>null</code>
	 */
	public static void storeFile(FileStoreHandler handler, String fileName) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileName);
	}

	/**
	 * ���������� ����� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ����������
	 * @param fileName	��� �����, ����� ���� <code>null</code>
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	public static void storeFile(FileStoreHandler handler, String fileName, long maximumFileSize) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileName, maximumFileSize);
	}

	/**
	 * ���������� ����� �� ������� �������
	 * 
	 * @param handler	���������� ����������
	 * @param fileChooserConfig	����������� ������� ����������
	 */
	public static void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().storeFile(handler, fileChooserConfig);
	}

	/**
	 * ���������� ����� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ����������
	 * @param fileChooserConfig	����������� ������� ����������
	 * @param maximumFileSize	������������ ������ ����� � ������
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
	 * ����� ������������� ������ ��� ���������� ������
	 * 
	 * @param fileChooseHandler	���������� ������
	 * @param fileChooserConfig	����������� ������� ������
	 */
	public static void chooseFile(FileChooseHandler fileChooseHandler, FileChooserConfig fileChooserConfig) {
		ClientContextFactory.getInstance().getDefaultClientContext().chooseFile(fileChooseHandler, fileChooserConfig);
	}

}
