/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс текстовых ресурсов
 * 
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.30 2008/12/23 09:24:06 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.framework.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String COPYRIGHT = "Copyright";
	public static final String COPYRIGHT_WARNING = "Copyright.Warning"; //$NON-NLS-1$
	public static final String APPLICATION_TITLE = "ApplicationTitle";
	
	public static final String INTERNAL_SOFTWARE_EXCEPTION = "InternalSoftwareException"; //$NON-NLS-1$
	public static final String SESSION_EXPIRED = "SessionExpired";
	public static final String MESSAGE_TITLE = "MessageTitle";
	public static final String CONNECTION_FAILED = "ConnectionFailed";
	public static final String INVALID_USER_NAME_PASSWD = "InvalidUserNameOrPassword";
	public static final String REASON_EXCEPTION = "ReasonException";
	public static final String CREATE_BUSINESS_BEAN_FAILED = "CreateBusinessBeanFailed";
	public static final String BUSINESS_BEAN_NOT_FOUND = "BusinessBeanNotFound";
	public static final String BUSINESS_BEAN_IMPL_NOT_FOUND = "BusinessBeanImplNotFound";
	public static final String BUSINESS_BEAN_IMPL_NOT_DEPLOYED = "BusinessBeanImplNotDeployed";
	public static final String BUSINESS_BEAN_IMPL_NOT_SUPPORTED = "BusinessBeanImplNotSupported";
	public static final String SECURITY_SYSTEM = "SecuritySystem";
	public static final String NO_PERMISSION = "NoPermission";
	public static final String SUBSYSTEM_ACCESS_DENIED = "SubsystemAccessDenied";
	public static final String FORM_DESCRIPTOR_NOT_FOUND = "FormDescriptorNotFound";
	public static final String FORM_NOT_FOUND = "FormNotFound";
	public static final String DATAITEM_NOT_FOUND = "DataItemNotFound";
	public static final String DOMAIN_NOT_FOUND = "DomainNotFound";
	public static final String UNSUPPORTED_EXCEPTION = "UnsupportedException";
	public static final String FILE_SIZE_EXCEEDED_EXCEPTION = "FileSizeExceededException";
	
	public static final String TRANSIENT_LICENSE_MANAGER = "TransientLicensingManager";
	public static final String TRANSIENT_LICENSE_KEY = "TransientLicensingKey";
	public static final String SUBSYSTEM_LICENSE_SURCHANGE = "SubsystemLicenseSurcharge";
	public static final String SERVER_LICENSE_SURCHANGE = "ServerLicenseSurcharge";
	public static final String LOCAL_LICENSE_WARNING = "LocalLicenseWarning";
	public static final String GENERAL_LICENSE_ERROR = "GeneralLicenseError";
	public static final String DEMONSTRATION_LICENSE = "DemostrationLicense";
	
	public static final String LOCK_FAILED = "LockFailed";
	
	public static final String DUPLICATE_WIDGET_NAME_ERROR = "DuplicateWidgetNameError";
	public static final String CREATE_WIDGET_ERROR = "CreateWidgetError";
	
	public static final String OK_BUTTON_TEXT = "OkButton";
	public static final String CANCEL_BUTTON_TEXT = "CancelButton";
	public static final String YES_BUTTON_TEXT = "YesButton";
	public static final String NO_BUTTON_TEXT = "NoButton";
	public static final String CLOSE_BUTTON_TEXT = "CloseButton";
	public static final String ADD_BUTTON_TEXT = "AddButton";
	public static final String EDIT_BUTTON_TEXT = "EditButton";
	public static final String VIEW_BUTTON_TEXT = "ViewButton";
	public static final String CLONE_BUTTON_TEXT = "CloneButton";
	public static final String DEEP_CLONE_BUTTON_TEXT = "DeepCloneButton";
	public static final String ERASE_BUTTON_TEXT = "EraseButton";
	public static final String REFRESH_BUTTON_TEXT = "RefreshButton";
	public static final String PERMISSION_BUTTON_TEXT = "PermissionButton";
	public static final String RESTRICTION_BUTTON_TEXT = "RestrictionButton";
	public static final String CLEAR_BUTTON_TEXT = "ClearButton";
	public static final String CHOOSE_BUTTON_TEXT = "ChooseButton";
	public static final String DETAIL_ON_BUTTON_TEXT = "DetailOnButton";
	public static final String DETAIL_OFF_BUTTON_TEXT = "DetailOffButton";
	public static final String PRINT_BUTTON_TEXT = "PrintButton";
	public static final String SETUP_TABLE_BUTTON_TEXT = "SetupTableButton";
	public static final String CUSTOM_ACTION_MENU_TEXT = "CustomActionMenuText";
	public static final String ERASE_ALERT_TITLE = "EraseAlertTitle";
	public static final String ERASE_ALERT_QUESTION = "EraseAlertQuestion";
	public static final String CHOOSE_DATE_BUTTON_TOOL_TIP_TEXT = "ChooseDateButtonToolTip";
	public static final String CHOOSE_DATE_DIALOG_TITLE = "ChooseDateDialogTitle";
	public static final String TRUE_TEXT = "True";
	public static final String FALSE_TEXT = "False";
	public static final String ALERT_ERROR_TITLE = "AlertErrorTitle";
	public static final String ALERT_INFORMATION_TITLE = "AlertInformationTitle";
	public static final String ALERT_QUESTION_TITLE = "AlertQuestionTitle";
	public static final String ALERT_WARNING_TITLE = "AlertWarningTitle";
	public static final String CLOSE_MAINTENANCE_FORM_WARNING = "CloseMaintenanceFormWarning";
	public static final String CONFIRMATION_ALERT_QUESTION = "ConfirmationAlertQuestion";
	
	public static final String VALIDATION_ERROR = "ValidationError";
	public static final String MANDATORY_VALIDATOR = "MandatoryValidator";
	public static final String UNIQUE_VALIDATOR = "UniqueValidator";
	public static final String POSITIVE_ATTRIBUTE_VALIDATOR = "PositiveAttribute";	
	
	public static final String DBMS_FOREIGN_KEY_VIOLATION = "DBMSForeignKeyViolation";
	public static final String DBMS_PRIMARY_KEY_VIOLATION = "DBMSPrimaryKeyViolation";
	public static final String DBMS_LOCK_CONFLICT = "DBMSLockConflict";
	
	public static final String FIND_DIALOG_TITLE = "FindDialogTitle";
	public static final String FIND_DIALOG_WHAT = "FindDialogWhat";
	public static final String FIND_DIALOG_MATCH = "FindDialogMatch";
	public static final String FIND_DIALOG_WHOLE_FIELD = "FindDialogWholeField";
	public static final String FIND_DIALOG_ANY_PART_FIELD = "FindDialogAnyPartField";
	public static final String FIND_DIALOG_BEGINNING_FIELD = "FindDialogBeginningField";
	public static final String FIND_DIALOG_MATCH_CASE = "FindDialogMatchCase";
	public static final String FIND_DIALOG_FIRST = "FindDialogFirst";
	public static final String FIND_DIALOG_NEXT = "FindDialogNext";
	public static final String FIND_DIALOG_PREVIOUS = "FindDialogPrevious";
	public static final String FIND_DIALOG_BACK = "FindDialogBack";
	public static final String FIND_DIALOG_INFORMATION_TITLE = "FindDialogInformationTitle";
	public static final String FIND_DIALOG_QUESTION_TITLE = "FindDialogQuestionTitle";
	public static final String FIND_DIALOG_INFORMATION = "FindDialogInformation";
	public static final String FIND_DIALOG_QUESTION_START = "FindDialogQuestionStart";
	public static final String FIND_DIALOG_QUESTION_END = "FindDialogQuestionEnd";
	
	public static final String ADMIN_MESSAGE_TITLE = "AdminMessageTitle";
	public static final String ADMIN_MESSAGE_BODY = "AdminMessageBody";
	
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}